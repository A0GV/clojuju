;;INICIO CON FILTRO MIO

(println "¡Hola, mundo!")
(require '[clojure.java.io :as io]) 
(require '[clojure.string :as str]) ; To trim string

; Definiciones de regex
;; Opciones de usuario 
(def rg-system (list "r-system" #"^system:"))
(def rg-u-cup (list "user-cup" #"^cup"))
(def rg-u-tsp (list "user-tsp" #"^teaspoon"))
(def rg-u-met (list "user-metric" #"^metric"))

(def rg-temp (list "r-temp" #"^temp:"))
(def rg-c (list "t-cel" #"^C"))
(def rg-f (list "t-far" #"^F"))

(def rg-portions (list "r-portions" #"^portions:"))
(def rg-num-port (list "num-portions" #"^[0-9]+")) ; DECIMALS??

(def rg-filter (list "r-filter" #"^filter:"))
(def rg-all (list "r-all" #"^all")) ; Keeps all recipes 
(def rg-other (list "custom-filter" #"^[a-z]+"))

;; Dictionary of tokens
(def dict-user (list
                rg-system
                rg-u-cup rg-u-tsp rg-u-met

                rg-temp
                rg-c rg-f

                rg-portions
                rg-num-port

                rg-filter
                rg-all rg-other))

;; Recetas
; Regex para números enteros
(def rg-nums-int (list "number-integer" #"^[0-9]+"))

; Regex para fracciones simples
(def rg-nums-frac (list "number-fraction" #"^[0-9]+/[0-9]+"))

; Regex para fracciones mixtas
(def rg-nums-mixed (list "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+"))

; Nuevos ingredientes específicos agregados del código 2
(def rg-granulated-sugar (list "ingredient-granulated-sugar" #"^\bgranulated\s+sugar\b"))
(def rg-all-purpose-flour (list "ingredient-all-purpose-flour" #"^\ball-purpose\s+flour\b"))
(def rg-almond-flour (list "ingredient-almond-flour" #"^\balmond\s+flour\b"))
(def rg-cocoa-powder (list "ingredient-cocoa-powder" #"^\bcocoa\s+powder\b"))
(def rg-dark-chocolate-chips (list "ingredient-dark-chocolate-chips" #"^\bdark\s+chocolate\s+chips\b"))
(def rg-sea-salt (list "ingredient-sea-salt" #"^\bsea\s+salt\b"))
(def rg-kosher-salt (list "ingredient-kosher-salt" #"^\bkosher\s+salt\b"))
(def rg-canola-oil (list "ingredient-canola-oil" #"^\bcanola\s\(+oil\)?\b"))
(def rg-canola (list "ingredient-canola-oil" #"^\bcanola\s"))
(def rg-extra-virgin-olive-oil (list "ingredient-extra-virgin-olive-oil" #"^\bextra-virgin\s+olive\s+oil\b"))
(def rg-extra-virgin-light-oil (list "ingredient-extra-light-olive-oil" #"^\bextra\s+light\s+olive\s+oil\b"))
(def rg-olive-oil (list "ingredient-olive-oil" #"^\bolive\s+oil\b"))
(def rg-vanilla-extract (list "ingredient-vanilla-extract" #"^\bvanilla\s+extract\b"))
(def rg-lemon-zest-grated (list "ingredient-lemon-zest-grated" #"^\blemon\s+zest\s+\(grated\)\b"))
(def rg-fresh-lemon-juice (list "ingredient-fresh-lemon-juice" #"^\bfresh\s+lemon\s+juice\b"))
(def rg-dry-fettuccine-pasta (list "ingredient-dry-fettuccine-pasta" #"^\bdry\s+fettuccine\s+pasta\b"))
(def rg-fettuccine-pasta (list "ingredient-fettuccine-pasta" #"^\bfettuccine\s+pasta\b"))
(def rg-heavy-cream (list "ingredient-heavy-cream" #"^\bheavy\s+cream\b"))
(def rg-red-pepper-flakes (list "ingredient-red-pepper-flakes" #"^\bred\s+pepper\s+flakes\b"))
(def rg-grated-romano-cheese (list "ingredient-grated-romano-cheese" #"^\bgrated\s+romano\s+cheese\b"))
(def rg-romano-cheese (list "ingredient-romano-cheese" #"^\bromano\s+cheese\b"))
(def rg-grated-parmesan-cheese (list "ingredient-grated-parmesan-cheese" #"^\bgrated\s+parmesan\s+cheese\b"))
(def rg-parmesan-cheese (list "ingredient-parmesan-cheese" #"^\bparmesan\s+cheese\b"))
(def rg-white-wine-vinegar (list "ingredient-white-wine-vinegar" #"^\bwhite\s+wine\s+vinegar\b"))
(def rg-garlic-clove-minced (list "ingredient-garlic-clove-minced" #"^\bgarlic\s+clove\s+\(minced\)\b"))
(def rg-garlic-clove (list "ingredient-garlic-clove" #"^\bgarlic\s+clove\b"))
(def rg-garlic-butter (list "ingredient-garlic-butter" #"^\bgarlic\s+butter\b"))
(def rg-garlic-solo (list "ingredient-garlic" #"^\bgarlic\b"))
(def rg-dried-oregano (list "ingredient-dried-oregano" #"^\bdried\s+oregano\b"))
(def rg-smoked-paprika (list "ingredient-smoked-paprika" #"^\bsmoked\s+paprika\b"))
(def rg-fresh-flat-leaf-parsley (list "ingredient-fresh-flat-leaf-parsley" #"^\bfresh\s+flat-leaf\s+parsley\b"))
(def rg-flat-leaf-parsley (list "ingredient-flat-leaf-parsley" #"^\bflat-leaf\s+parsley\b"))
(def rg-new-york-strip-steaks (list "ingredient-new-york-strip-steaks" #"^(?i:\bnew\s+york\s+strip\s+steaks?\b)"))
(def rg-top-sirloin-steaks (list "ingredient-top-sirloin-steaks" #"^(?i:\btop\s+sirloin\s+steaks?\b)"))
(def rg-ribeye (list "ingredient-ribeye" #"^\b(?i:r)ibeye\b"))
(def rg-steaks (list "ingredient-steaks" #"^\bsteaks?\b"))
(def rg-black-pepper (list "ingredient-black-pepper" #"^\bblack\s+pepper\b"))
(def rg-unsalted-butter (list "ingredient-unsalted-butter" #"^\bunsalted\s+butter\b"))
(def rg-fresh-rosemary (list "ingredient-fresh-rosemary" #"^\bfresh\s+rosemary\b"))
(def rg-rosemary (list "ingredient-rosemary" #"^\brosemary\b"))
(def rg-ground-almonds (list "ingredient-ground-almonds" #"^\bground\s+almonds?\b"))
(def rg-vegetable-oil (list "ingredient-olive-oil" #"^\bvegetable\s+oil\b"))

; Regex para ingredientes (case sensitive) - manteniendo los originales
(def rg-sugar (list "ingredient-sugar" #"^\b(?:granulated\s+)?sugar\b"))
(def rg-flour (list "ingredient-flour" #"^\b(?:all-purpose\s+|almond\s+)?flour\b"))
(def rg-cocoa (list "ingredient-cocoa" #"^\bcocoa\s+powder\b"))
(def rg-powdered-sugar (list "ingredient-powdered-sugar" #"^\bpowdered\s+sugar\b"))
(def rg-chocolate (list "ingredient-chocolate" #"\b(?:dark\s)?+chocolate\s+chips\b"))
(def rg-salt (list "ingredient-salt" #"\b(?:sea\s+|kosher\s+)?salt\b"))
(def rg-eggs (list "ingredient-eggs" #"\beggs?\b"))
(def rg-oil (list "ingredient-oil" #"\b(?:canola\s+|extra-virgin\s+olive\s+)?oil\b"))
(def rg-water (list "ingredient-water" #"\bwater\b"))
(def rg-vanilla (list "ingredient-vanilla" #"\bvanilla(?:\s+extract)?\b"))
(def rg-baking-powder (list "ingredient-baking-powder" #"\bbaking\s+powder\b"))
(def rg-lemon-zest (list "ingredient-lemon-zest" #"\blemon\s+zest(?:\s+\(grated\))?\b"))
(def rg-lemon (list "ingredient-lemon" #"\blemon\b"))
(def rg-lemon-juice (list "ingredient-lemon-juice" #"\b(?:fresh\s+)?lemon\s+juice\b"))
(def rg-pasta (list "ingredient-pasta" #"\b(?:dry\s+)?(?:fettuccine\s)?+pasta\b"))
(def rg-butter (list "ingredient-butter" #"\bbutter\b"))
(def rg-cream (list "ingredient-cream" #"\b(?:heavy\s)?+cream\b"))
(def rg-pepper (list "ingredient-pepper" #"\b(?:red\s+pepper\s+flakes|pepper)\b"))
(def rg-garlic-salt (list "ingredient-garlic-salt" #"\b(?i:g)arlic\s+salt\b"))
(def rg-romano (list "ingredient-romano" #"\bgrated\s+(?i:r)omano(?:\s+cheese)?\b"))
(def rg-parmesan (list "ingredient-parmesan" #"\b(?:grated\s)?+(?i:p)armesan\s+cheese\b"))
(def rg-vinegar (list "ingredient-vinegar" #"\b(?:white\s+wine\s)?+vinegar\b"))
(def rg-garlic (list "ingredient-garlic" #"\bgarlic(?:\s+clove(?:s)?(?:\s+\(minced\))?)?\b"))
(def rg-oregano (list "ingredient-oregano" #"\b(?:dried\s)?+oregano\b"))
(def rg-paprika (list "ingredient-paprika" #"\bsmoked\s+paprika\b"))
(def rg-parsley (list "ingredient-parsley" #"\b(?:fresh\s+)?flat-leaf\s+parsley\b"))

; Nuevas unidades agregadas del código 2
(def rg-lbs (list "lbs" #"\blbs?\b"))
(def rg-pounds (list "pounds" #"\bpounds?\b"))
(def rg-lb (list "lb" #"\blb\b"))
(def rg-inches (list "inches" #"\binches?\b"))
(def rg-quote-inches (list "inches" #"^\"\b"))
(def rg-in (list "in" #"\bin\b"))

(def rg-cup (list "cup" #"\bcups?\b"))
(def rg-teaspoon (list "teaspoon" #"\bteaspoons?\b"))
(def rg-tablespoon (list "tablespoon" #"\btablespoons?\b"))
(def rg-ounce (list "ounce" #"\bounces?\b"))
(def rg-pint (list "pint" #"\bpints?\b"))
(def rg-dash (list "dash" #"\bdash(?:es)?\b"))
(def rg-clove (list "clove" #"\bcloves?\b"))
(def rg-large (list "large" #"\blarge\b"))
(def rg-to-taste (list "to-taste" #"\bto\s+taste\b"))
(def rg-for-dusting (list "for-dusting" #"\bfor\s+dusting\b"))
(def rg-gram (list "gram" #"^grams?\b"))

;(def rg-phrases (list "phrases" #"^[a-zA-Z0-9 ,\.\(\)]+"))
; Other words 
(def rg-serves (list "serves-amt" #"^(?:Serves\s*-\s*|Servings\s*-\s*)[0-9]+"))

(def rg-temp-c (list "temp-C" #"^[0-9]+°C"))
(def rg-temp-f (list "temp-F" #"^[0-9]+°F"))

(def rg-pt (list "prep-t" #"^Prep Time\:\s*[0-9]+\s*(?:mins|minutes)"))
(def rg-ct (list "cook-t" #"^Cook Time\:\s*[0-9]+\s*(?:mins|minutes)"))
(def rg-tt (list "total-t" #"^Total Time\:\s*[0-9]+\s*(?:mins|minutes)"))

(def rg-step-num (list "step-num" #"^[0-9]+\."))
(def rg-fract-in (list "fract-in" #"[0-9]+/[0-9]+\""))

; Keywords 
(def rg-equip (list "kw-equip" #"^Equipment(?:\:)*"))
(def rg-category (list "kw-category" #"^Category(?:\:)*"))
(def rg-author (list "kw-author" #"^Author(?:\:)*"))
(def rg-ingredients (list "kw-ingredient" #"^Ingredients(?:\:)*"))
(def rg-instruct (list "kw-instruct" #"^Instructions"))

(def rg-dash (list "dash" #"^[-]"))
(def rg-time-range (list "time-range" #"^[0-9]+\sto\s[0-9]+\sminutes"))
(def rg-time-dash (list "time-range" #"^[0-9]+\-minute"))
(def rg-time-dash-range (list "time-range" #"^[0-9]+\-[0-9]+\s+minutes?"))

(def rg-time-mention (list "time-mention" #"^[0-9]+\–*\s*(?:minutes|minute)"))
(def rg-8x8 (list "w" #"^[0-9]x[0-9]"))

; Just stores words bcs it's annoying to deal w a lot of floating tokens
(def rg-catch (list "w" #"[a-zA-Z]+"))

;; Dictionary of numbers
(def dict-recipe (list
                  rg-nums-int
                  rg-nums-frac
                  rg-nums-mixed

                    ; Ingredientes específicos primero (orden importante)
                  rg-granulated-sugar
                  rg-all-purpose-flour
                  rg-almond-flour
                  rg-cocoa-powder
                  rg-powdered-sugar
                  rg-dark-chocolate-chips
                  rg-sea-salt
                  rg-kosher-salt
                  rg-canola-oil
                  rg-extra-virgin-olive-oil
                  rg-extra-virgin-light-oil
                  rg-olive-oil
                  rg-vanilla-extract
                  rg-lemon-zest-grated
                  rg-fresh-lemon-juice
                  rg-lemon
                  rg-dry-fettuccine-pasta
                  rg-fettuccine-pasta
                  rg-heavy-cream
                  rg-red-pepper-flakes
                  rg-grated-romano-cheese
                  rg-romano-cheese
                  rg-grated-parmesan-cheese
                  rg-parmesan-cheese
                  rg-white-wine-vinegar
                  rg-garlic-clove-minced
                  rg-garlic-butter
                  rg-garlic-clove
                  rg-dried-oregano
                  rg-smoked-paprika
                  rg-fresh-flat-leaf-parsley
                  rg-flat-leaf-parsley
                  rg-new-york-strip-steaks
                  rg-top-sirloin-steaks
                  rg-ribeye
                  rg-black-pepper
                  rg-unsalted-butter
                  rg-fresh-rosemary
                  rg-ground-almonds
                  rg-vegetable-oil

                    ;; Ingredientes generales después
                  rg-sugar
                  rg-flour
                  rg-cocoa
                  rg-powdered-sugar
                  rg-chocolate
                  rg-salt
                  rg-eggs
                  rg-oil
                  rg-water
                  rg-vanilla
                  rg-baking-powder
                  rg-lemon-zest
                  rg-lemon-juice
                  rg-pasta
                  rg-butter
                  rg-cream
                  rg-pepper
                  rg-garlic-salt
                  rg-romano
                  rg-parmesan
                  rg-vinegar
                  rg-garlic
                  rg-oregano
                  rg-paprika
                  rg-parsley
                  rg-steaks
                  rg-rosemary
                  rg-canola
                  rg-garlic-solo

                    ; Unidades
                  rg-cup
                  rg-teaspoon
                  rg-tablespoon
                  rg-ounce
                  rg-pint
                  rg-lbs
                  rg-pounds
                  rg-lb
                  rg-inches
                  rg-quote-inches
                  rg-in
                  rg-dash
                  rg-clove
                  rg-large
                  rg-to-taste
                  rg-for-dusting
                  rg-gram

                    ; Adding
                  rg-serves
                  rg-temp-c rg-temp-f
                    ; Time mentions 
                  rg-pt rg-ct rg-tt

                    ; Keywords 
                  rg-ingredients rg-instruct
                  rg-equip
                  rg-step-num
                  rg-fract-in
                  rg-category
                  rg-author

                    ; Catch case
                  rg-time-dash-range
                  rg-time-range
                  rg-time-dash
                  rg-time-mention
                  rg-catch
                  rg-dash
                  rg-8x8))

;; Conversiones de ingredientes (diccionario nuevo como solicitas)
(def ingredient-conversions
  {"ingredient-sugar" {:cup-to-grams 200.8634 :tsp-to-grams 4.184 :tbsp-to-grams 12.554}
   "ingredient-flour" {:cup-to-grams 125.1552 :tsp-to-grams 2.607 :tbsp-to-grams 7.822}
   "ingredient-cocoa" {:cup-to-grams 151.6531 :tsp-to-grams 3.159 :tbsp-to-grams 9.478}
   "ingredient-powdered-sugar" {:cup-to-grams 120.8966 :tsp-to-grams 2.519 :tbsp-to-grams 7.556}
   "ingredient-chocolate" {:cup-to-grams 187.3779 :tsp-to-grams 3.904 :tbsp-to-grams 11.711}
   "ingredient-salt" {:cup-to-grams 284.1425 :tsp-to-grams 5.920 :tbsp-to-grams 17.759}
   "ingredient-eggs" {:cup-to-grams 246.0518 :tsp-to-grams 5.126 :tbsp-to-grams 15.378}
   "ingredient-oil" {:cup-to-grams 217.6612 :tsp-to-grams 4.534 :tbsp-to-grams 13.604}
   "ingredient-water" {:cup-to-grams 236.5882 :tsp-to-grams 4.929 :tbsp-to-grams 14.787}
   "ingredient-vanilla" {:cup-to-grams 207.9847 :tsp-to-grams 4.333 :tbsp-to-grams 12.999}
   "ingredient-baking-powder" {:cup-to-grams 212.9294 :tsp-to-grams 4.436 :tbsp-to-grams 13.308}
   "ingredient-lemon-zest" {:cup-to-grams 97.0012 :tsp-to-grams 2.021 :tbsp-to-grams 6.063}
   "ingredient-lemon-juice" {:cup-to-grams 314.6624 :tsp-to-grams 6.555 :tbsp-to-grams 19.666}
   "ingredient-pasta" {:cup-to-grams 89.9035 :tsp-to-grams 1.873 :tbsp-to-grams 5.619}
   "ingredient-butter" {:cup-to-grams 226.8881 :tsp-to-grams 4.727 :tbsp-to-grams 14.181}
   "ingredient-cream" {:cup-to-grams 235.1687 :tsp-to-grams 4.900 :tbsp-to-grams 14.698}
   "ingredient-pepper" {:cup-to-grams 115.93 :tsp-to-grams 2.415 :tbsp-to-grams 7.246}
   "ingredient-garlic-salt" {:cup-to-grams 75.7082 :tsp-to-grams 1.577 :tbsp-to-grams 4.732}
   "ingredient-romano" {:cup-to-grams 82.8059 :tsp-to-grams 1.725 :tbsp-to-grams 5.175}
   "ingredient-parmesan" {:cup-to-grams 120.66 :tsp-to-grams 2.514 :tbsp-to-grams 7.541}
   "ingredient-vinegar" {:cup-to-grams 236.59 :tsp-to-grams 4.929 :tbsp-to-grams 14.787}
   "ingredient-garlic" {:cup-to-grams 113.97 :tsp-to-grams 2.374 :tbsp-to-grams 7.123}
   "ingredient-oregano" {:cup-to-grams 47.32 :tsp-to-grams 0.986 :tbsp-to-grams 2.958}
   "ingredient-paprika" {:cup-to-grams 134.8553 :tsp-to-grams 2.810 :tbsp-to-grams 8.428}
   "ingredient-parsley" {:cup-to-grams 61.5129 :tsp-to-grams 1.282 :tbsp-to-grams 3.845}
   "ingredient-steaks" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-new-york-strip-steaks" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-ribeye" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-top-sirloin-steaks" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-black-pepper" {:cup-to-grams 115.93 :tsp-to-grams 2.415 :tbsp-to-grams 7.246 :lb-to-grams 453.592}
   "ingredient-unsalted-butter" {:cup-to-grams 226.8881 :tsp-to-grams 4.727 :tbsp-to-grams 14.181 :lb-to-grams 453.592}
   "ingredient-fresh-rosemary" {:cup-to-grams 58.2 :tsp-to-grams 1.213 :tbsp-to-grams 3.638 :lb-to-grams 453.592}
   "ingredient-rosemary" {:cup-to-grams 58.2 :tsp-to-grams 1.213 :tbsp-to-grams 3.638 :lb-to-grams 453.592}
   "ingredient-ground-almonds" {:cup-to-grams 95.0 :tsp-to-grams 1.979 :tbsp-to-grams 5.938 :lb-to-grams 453.592}})

;; Calorías por 100g de ingrediente (diccionario nuevo como solicitas)
(def IngCal100
  {"ingredient-granulated-sugar" 400
   "ingredient-sugar" 400
   "ingredient-all-purpose-flour" 351.6
   "ingredient-flour" 351.6
   "ingredient-cocoa-powder" 229
   "ingredient-cocoa" 229
   "ingredient-powdered-sugar" 321.8
   "ingredient-dark-chocolate-chips" 683.1
   "ingredient-chocolate" 683.1
   "ingredient-sea-salt" 0
   "ingredient-kosher-salt" 0
   "ingredient-salt" 0
   "ingredient-eggs" 147
   "ingredient-canola-oil" 882.1
   "ingredient-extra-virgin-olive-oil" 884.1
   "ingredient-olive-oil" 884.1
   "ingredient-oil" 882.1
   "ingredient-water" 0
   "ingredient-vanilla-extract" 288
   "ingredient-vanilla" 288
   "ingredient-almond-flour" 2810.9
   "ingredient-baking-powder" 53
   "ingredient-lemon-zest-grated" 395.8
   "ingredient-lemon-zest" 395.8
   "ingredient-fresh-lemon-juice" 19.4
   "ingredient-lemon-juice" 19.4
   "ingredient-dry-fettuccine-pasta" 365
   "ingredient-fettuccine-pasta" 365
   "ingredient-pasta" 365
   "ingredient-butter" 717
   "ingredient-heavy-cream" 349.1
   "ingredient-cream" 349.1
   "ingredient-pepper" 26
   "ingredient-red-pepper-flakes" 0
   "ingredient-garlic-salt" 0
   "ingredient-grated-romano-cheese" 431
   "ingredient-romano-cheese" 431
   "ingredient-romano" 431
   "ingredient-grated-parmesan-cheese" 400
   "ingredient-parmesan-cheese" 400
   "ingredient-parmesan" 400
   "ingredient-white-wine-vinegar" 0
   "ingredient-vinegar" 0
   "ingredient-garlic-clove-minced" 133
   "ingredient-garlic-clove" 133
   "ingredient-garlic" 133
   "ingredient-dried-oregano" 360
   "ingredient-oregano" 360
   "ingredient-smoked-paprika" 289
   "ingredient-paprika" 289
   "ingredient-fresh-flat-leaf-parsley" 35.8
   "ingredient-flat-leaf-parsley" 35.8
   "ingredient-parsley" 35.8
   "ingredient-steaks" 271
   "ingredient-new-york-strip-steaks" 271
   "ingredient-ribeye" 291
   "ingredient-top-sirloin-steaks" 259
   "ingredient-black-pepper" 26
   "ingredient-unsalted-butter" 717
   "ingredient-fresh-rosemary" 131
   "ingredient-rosemary" 131
   "ingredient-ground-almonds" 579})

;; FILE READING
; Funct to read file line by line
(defn read-file-lines [file-path]
    (with-open [reader (io/reader file-path)]
        (doall (line-seq reader))
   )
)

; TOKENIZACIÓN
; Finds all matches for item in regex given input text and corresponding dictionary
(defn all-matches [input-text rg-dict]
    ;(println "All matches!")
    ;(display "Input text: " input-text)

    (filter
        ; Keep only defs that are not false 
        (fn [match-result] (not (nil? match-result)))
        ; And now compare against every item in the specified dictionary
        (map
            (fn [regex-item]
                (let [
                    token-name (first regex-item) ; Gets the name of that token
                    rg-pattern  (second regex-item) ; Regex pattern
                    matched-txt (re-find rg-pattern input-text)  ; String that was matched (nil if not found)
                    ]
                    
                    
                    ; If nil, then just returns a false to later filter it out
                    ;(if (nil? matched-txt) false)
                    ;(if matched-txt
                        ; Did find a match, builds list of token name and 
                    ;    (list token-name matched-txt)
                    ;    nil)

                    ; If found a match
                    (if matched-txt
                        (let [
                            ; Finds position where match began
                            match-pos (.indexOf input-text (str matched-txt)) ]
                            ;(println "Token:" token-name "Match:" matched-txt "Position:" match-pos)

                            ; If the match position is at the start, returns the token name and the match as a string
                            (if (= match-pos 0)
                                (list token-name matched-txt)
                                nil)
                        )
                        ; Else just returns a null 
                        nil)   
                )
            )
        rg-dict)
    )
    
)

; Uses text from tokens to find which one is longest 
(defn get-max-len [match-list]
    ;(println "Get maximum length string of all of the matches")
    (apply max
        (map
           (fn [match] (count (second match)))
        match-list)
    )
)

; Uses item matched from list and keeps only the one that meets the longest length found  
(defn filter-max [matches max-len]
    ;(println "Keeping only the longest one")
    (filter
        (fn [match] 
            (= (count (second match)) max-len)
        ) matches
    )
)

; Finds the longest matched token of the current item being read
(defn longest-match [input-item rg-dict]
    (let [all-found-matches (all-matches input-item rg-dict)]
        (cond
            ; Did not find a match, returns an unregognized for symbol
            (empty? all-found-matches) (list "NA" (subs input-item 0 1)) 

            ; Only one match, just returns that one 
            (= 1 (count all-found-matches)) (first all-found-matches)

            ; Else finds the longest match of those found 
            :else 
                ; Finds longest match 
                (let [
                    longest-len (get-max-len all-found-matches)
                    longest-matches (filter-max all-found-matches longest-len)
                    ]

                    ; Once found, body just returns thel ongest one
                    (first longest-matches)
                )
        )
    )
)

; Keeps the longest match
(defn tokenize [input rg-dict]
    ;(println "Using dictionary\n" dict-user)
    ;(println "Reading: " input)
    ; Base case input empty
    (cond
        (zero? (count (str/trim input)))
        ;(count (str/trim input)) ; Returns length of trimmed 
        '() ; Returns done list
    :else 
        ; List of token and matched substring 
        (let [
            input-trim (str/trim input) ; Para hacer llamadas, ya quita los empty spaces

            ; Head de variables 
            type-txtmatch (longest-match input-trim rg-dict)
            ; Builds the substring
            extracted-txt (second type-txtmatch) ; Finds the token text
            length (count extracted-txt) ; Counts how long the token text is

            ; Finds pos of match starting
            pos-match-start (.indexOf input-trim extracted-txt)

            ; For recursive to start at next element (excluding whitespace)
            ;rest-input (subs input-trim length)
            rest-input (if (= "NA" (first type-txtmatch))
                ; NA case: longest-match already took 1 char, so skip 1 char
                (str/trim (subs input-trim 1))
                ; Match found: skip past the entire matched text
                (str/trim (subs input-trim (+ pos-match-start length)))
            )

            ]
            ; Ahora el body para build list 
            (cons 
                (list (first type-txtmatch) extracted-txt)
                (tokenize rest-input rg-dict)
            )
        )
    )
)

;; RECIPE HANDLING
; Recieves the chunk (aka the list of file paths) 
(defn process-recipe [file-path]
    (let [
        ; Read file lines w reader
        raw-lines (with-open [reader (io/reader file-path)] (doall (line-seq reader)))
        ; Converts lines to list to have same format as options
        recipe-lines (map list raw-lines)

        ; cant have prints in lets (println "\n-------RECIPE LINES\n" recipe-lines)

        ; Tokenizar las líneas
        tokenized-lines (doall (map (fn [current-line] 
                            ; Proccesses line recipe if it is not empty 
                            (if (not (empty? (str/trim (first current-line))))
                                (tokenize (first current-line) dict-recipe) ; Tokenize call
                                '() ; List is empty
                            )
                        ) recipe-lines) )
        ]
        ; Returns list w file name, original lines just in case, and tokenized lines 
        ;(println "\n\n\n")
        (println "Tokenized lines:" tokenized-lines)
        (list file-path recipe-lines tokenized-lines)
    )
)

; Recieves a chunk of recipes and then tokenizes the recipe
(defn process-chunk [chunk]
    (println "Processing: " chunk)
    (doall (map process-recipe chunk))
)

;; SUB-FUNCTIONS FOR RECIPE CONVERSION
; Extrae val numerico de temp given  
(defn extract-num-value [num-string]
    (let [numeric-part (re-find #"\d+" num-string)]
        ; Returns num if it did match, 0 if it did not find the number
        (if numeric-part (Integer/parseInt numeric-part) 0)
    )
)

; Converts far to cel and returns it as a token C = (F - 32) / (9 / 5)
(defn f-to-c [f-temp-string]
    (let [
            ; Calls helper funct to convert farenheit 
            f-value (extract-num-value f-temp-string)
            c-value (/ (* (- f-value 32) 5) 9.0) ; Plugs into eq
        ]
        (list "temp-C" (str c-value "°C")))
)

; Converts far to cel and returns it as a token F = (C * (9 / 5)) +32
(defn c-to-f [c-temp-string]
    (let [
            ; Calls helper funct to convert farenheit 
            c-value (extract-num-value c-temp-string)
            f-value (+ (* c-value (/ 9 5)) 32) ; Plugs into eq
        ]
        (list "temp-F" (str f-value "°F")))
)

; Converts int or fraction to a numeric val
(defn numToInt [int-str]
  (read-string int-str))

; Converts a mixed fraction to a number
(defn mixedFrac [mixed-frac]
  (let [parts (clojure.string/split mixed-frac #" ")
        resp (+ (numToInt (first parts)) (numToInt (second parts)))]
    resp))

;; COMPARISON TOKENS AGAINST USER PREFERENCES
; Check user wants cel
(defn user-celsius-check [user-tokens]
    ; Some token among the options txt is t-cel
    (some 
        ; Checks token is not null and that the first val is t-cel
        (fn [token-line] 
            (some 
                (fn [token] (and (not (nil? token)) (= (first token) "t-cel"))) 
            token-line)
        )
    user-tokens)
)

; Processes line using result of whether user wants celcius
(defn process-token-line [token-line user-temp-units scale-factor]
    ;(println "Processing token-line")
    (if (seq token-line)
        (doall 
            (map (fn [token]
                ; Recipe token is currently set to F and user wants C
                (cond 
                    ; Checks recipe F, user C
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-F") 
                        user-temp-units
                    )
                        ; Need to convert F to C, calls funct
                        (f-to-c (second token))
                    
                    ; Checks recipe C, user F
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-C") 
                        (not user-temp-units) ; False = Farenheit
                    )
                        ; Calls funct to convert F -> C
                        (c-to-f (second token))

                    ; Checks if it is a number or simple fraction to convert 
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        ; If it is a 
                        (or (= (first token) "number-integer")
                            (= (first token) "number-fraction")
                        )
                    )
                        ; Multiplies current amt by scale factor 
                        ;(println "FRACTION " (first token) "value: " extract-num-value (second token) )
                        ;(list "number-scaled" (* scale-factor (extract-num-value (second token))) )
                        ;(list "number-s-integer" (str (* scale-factor (extract-num-value token))))
                        (let [
                            original-str (second token)  ; Get the string value
                            ; Convert a numero con adolf help
                            original-value (numToInt original-str)  
                            scaled-value (* scale-factor original-value)]
                            (println "  Scaling number:" original-str "×" scale-factor "=" scaled-value)
                            
                            ; Structured list 
                            (list "number-s" (str scaled-value))
                        )

                    ; Converting a mixed fraction 
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        ; If it is a mixed fraction
                        (= (first token) "number-mixed")
                    )
                        (do
                            (println "MIXED: " (second token))
                            (let [
                                ; Fraccion number
                                original-str (second token) 
                                mixed-value (mixedFrac original-str) ; A ver si funciona con lo de adolf
                                scaled-value (* scale-factor mixed-value)
                                ]

                                ;(println "Proccessed: " mixed-value)

                                (println "  Scaling mixed fraction:" mixed-value "×" scale-factor "=" scaled-value)
                                
                                ; Returns la lista con scaled mixed
                                (list "number-s-mix" (str scaled-value))
                            )
                        )


                    ; Checks if it is a mixed fraction that needs to be converted 

                    ; Else it can just stay how it is
                    :else token
                ))
            token-line)) 
        '("\t" "\t")) ; It is not a sequence
    ;)
)

; Main function to manipulate one recipe at a time based on user preferences
(defn manipulate-recipe [recipe user-options]
    (println "\n------- BEGIN MANIPULATION -------")
    
    (let [
            recipe-name (first recipe)
            original-lines (second recipe)
            tokenized-lines (nth recipe 2)
            
            ; Checks if user wants C
            user-temp-units (user-celsius-check user-options)
            ; Extracts number of portions that user wants, need read-string to handle that it's a string
            user-num-portions (read-string (second (second (nth user-options 2))) )

            ; Find recipe number of servings from tokenized lines
            recipe-serves 
                ; Extracts number value
                (extract-num-value
                    ; Extract the token for serves 
                    (second (first 
                        (filter 
                            (fn [token-line] (= (first token-line) "serves-amt")) 
                        ; Need to flatten when looking for amt to take into account diff line skips and such
                        (apply concat tokenized-lines)
                        ))
                    )
                )

            ; Amt to scale a recipe
            scale-factor (/ user-num-portions recipe-serves)
        ]
        
        (println "Processing recipe:" recipe-name " with" recipe-serves " user wants  " user-num-portions "; scaled: " scale-factor "\n")

        ; Process all tokenized lines
        (let [
            corrected-temp (doall (map 
                (fn [token-line] (process-token-line token-line user-temp-units scale-factor)) tokenized-lines))
            ]
            
            ; Return updated recipe structure
            (list recipe-name original-lines corrected-temp)
        )
    )
)

; Analyze all recipes and apply manipulations
(defn analyze-recipes [processed-recipes user-tokens]
    (def recetas (map #(nth % 2) processed-recipes))
    
    (let [filtro (first (second (last user-tokens)))]
        (if (= filtro "r-all")
            ; Apply manipulations to all recipes
            (let [manipulated-recipes (doall (map (fn [recipe]
                                            (manipulate-recipe recipe user-tokens))
                                        processed-recipes))
                ]
                
                (println "Processed" (count manipulated-recipes) "recipes")
                
                ; Return the manipulated recipes in correct format 
                manipulated-recipes
            )
            ;Solamente manipula las que pasen el filtro declarado por el usuario
            (let [recetas-filtradas 
                    (filter (fn [recipe] (some
                                (fn [token-line]
                                    (some (fn [token] (= (second token) (second (second (last user-tokens))))) token-line))
                                (nth recipe 2)))
                        processed-recipes)]
                (println "Recetas filtradas:" (count recetas-filtradas))
                (doall (map (fn [recipe] (manipulate-recipe recipe user-tokens)) recetas-filtradas))
            )
        )
    )
)

;;IMPRIMIR EN HTML
(defn convert [class text]
    (str "<text class='" class "'>" text " </text>" )
)

(defn resultado [linea]
    (apply str (map (fn [x] (convert (first x) (second x))) linea))
)

(defn html [tokenized]
 (apply str (map (fn [x] (str (resultado x) "<br/>")) tokenized))
)

(def header
    "<link rel='stylesheet' ref='text/css' href='../estilos.css'/>
    <link rel='preconnect' href='https://fonts.googleapis.com'>
    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>
    <link href='https://fonts.googleapis.com/css2?family=Comic+Neue:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap' rel='stylesheet'>
    <h1>los 3 mosqueteros recetas</h1>"
)

; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads]
    ; Leer options file y guardar preferencias del usuario 
    (def options-path (str "options/" options-file))
    (println "\n-------FILE PATH: " options-path)

    (def file-lines (read-file-lines options-path))
    (println "\n-------USER PREFERENCES\n" file-lines)

    (println "\n-------LINE BY LINE")
    ;(def opt-lines (list (map (fn [line] (println line)) file-lines)) )
    (def opt-lines (map list file-lines))
    (println opt-lines)

    (def opt-tokenized
        (doall 
            (map (fn [current-line] 
                (tokenize (first current-line) dict-user)) 
            opt-lines)
        )
    )

    (println "\n-------TOKENIZED")
    (println opt-tokenized)

    (println "\n-------HTML")
    ;Cambiar a receta
    ;(def nombre (subs options-file 0 (- (count options-file) 4)))

    ; HTML con header
    (def htmlcompleto (str header "<div class='options'>"(html opt-tokenized)"</div>"))

    ;(spit (str "htmls/" nombre ".html") htmlcompleto)

    ; looking for user preferences servings amt
    (print "looking for servings: ")
    (print (second (second (nth opt-tokenized 2))) )

    ; Leer recetas
    (println "\n-------READ RECIPES")
    (println (str "Procesando con archivo de opciones: " options-file))
    (println (str "Número de threads: " num-threads))
  
    ; Definir rutas de recetas
    ;(def ruta ["recipes/Best Homemade Brownies-1.txt" 
    ;           "recipes/Chimichurri Sauce.txt" 
    ;           "recipes/Lemon Cake-1.txt"
    ;           "recipes/Fettuccine Alfredo.txt"
    ;           "recipes/Pan-Seared Steak with Garlic Butter.txt"])
    (def ruta ["recipes/Best Homemade Brownies-1.txt"
                "recipes/Chimichurri Sauce.txt" 
                "recipes/Fettuccine Alfredo.txt"
                "recipes/Lemon Cake-1.txt"
                "recipes/Pan-Seared Steak with Garlic Butter.txt"

    ])

    ; Ajustar número de threads para evitar particiones vacías
    (def n-threads-ajustado (min num-threads (count ruta)))
    (def chunk-size (max 1 (int (/ (count ruta) n-threads-ajustado))))

    ; Divides the recipes into chunks containing file names
    (def data-chunks 
    (partition-all chunk-size ruta))

    (println "CHUNKS: " data-chunks) ; Stores list
    ;; 2 recipes 2 threads((recipes/Best Homemade Brownies-1.txt) (recipes/Chimichurri Sauce.txt))
    ;; 2 recipes 1 thread ((recipes/Best Homemade Brownies-1.txt recipes/Chimichurri Sauce.txt))

    ; Medir tiempo de ejecución y procesar en paralelo
    (println "\n-------TOTAL TIME")
    ; Guarda tiempo de ejecución
    (def exec-time 
        (time 
            (do
                ; Process the recipes 
                (def recipes-processed
                    (apply concat ; Aplana chunks pero keeps recipes separated
                        ; Sends the chunks of recipes (list of recipes) to proccess them
                        (pmap process-chunk data-chunks)
                    )
                )
                ; Print the tokens
                (doall (map (fn [x] (println (nth x 2)"\n\nNext Recipe Tokens:\n")) recipes-processed)) ; Check all the recipes 

                ; Passes tokenized recipe and the tokens of user customization
                (def fix-recipes (analyze-recipes recipes-processed opt-tokenized))
            )
        )
    )

    (println exec-time)
    (println "Fixed recipes")

    ; Structure of recipes 
    ;(println (nth (first fix-recipes) 0) )
    ;(println (nth (first fix-recipes) 1) )
    ;(println (nth (first fix-recipes) 2) )

    ;(println (nth (map (first) fix-recipes) 2) )
    (doall (map (fn [x] (println (nth x 2)"\n\nFINAL Recipe Tokens:\n")) fix-recipes)) ; Check all the recipes 
    
    (def onlyname (map (fn [x] (subs (nth x 0) 8 (- (count (nth x 0)) 4))) fix-recipes))
    ;Nombres de los htmls (buscar que no haya conflicto si se duplican)

    ;IMPRIMIR HTMLS
    (def receras 
        (map (fn [x] 
                (let [tokenized-lines (nth x 2)
                    first-line (list (first tokenized-lines))
                    rest-lines (rest tokenized-lines)
                    ; Crea el h2 para la primera línea
                    h2-line (str "<h2>" (html first-line) "</h2>")
                    ; Convierte el resto normalmente
                    rest-html (html rest-lines)]
                ; Combina todo
                (str h2-line rest-html)))
            fix-recipes)
    )
    
    ;Elimino los espacios en el nombre
    (def nombre (map (fn [x] (clojure.string/replace x #" " "")) onlyname))
    (println nombre)
    (doall (map (fn [x y] (spit (str "htmls/" x ".html") (str htmlcompleto "</br></br><div class='receta'>" y "</div>"))) nombre receras))
    (println "Se imprimió html de cada receta encontrada")
    ; Tokenización - cantidades, unidades de medida, numero de porciones y temperaturas
    ; Convertir unidades - tazas, teaspoons, cups, gramos, Fahrenheit a Celsius,  y viceversa
    ; Calorias totales y por porcion (base de gramos)
    ; Escala arriba o abajo. Tu programa debe calcular la cantidad de ingredientes necesarios para escalar la receta a un numero de porciones determinado. ACTUALIZAR LO ANTERIOR 
    ; Filtra:  el programa debe ser capaz de devolver solo las recetas que incluyan una palabra o frase determinada. Revisa la sección Entradas adicionales.
    ; Agrega texto con resultados (speed up y aceleración para el numero de hilos) 

    ;(println "\nContenido de las recetas:")
    ;(println lectura)

    ; Retornar los datos para uso posterior

)
  

; Pasar input file y el número de threads para probarlo
;(main "options1.txt" 1)
;(main "options1.txt" 6)
;(main "options1.txt" 10)

(main "options1.txt" 1)
;(main "options2.txt" 1)

(println "Mixed convert: "  (mixedFrac "1 1/2")) ; Test fraction to make sure its an int
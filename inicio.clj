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
(def rg-teaspoon (list "teaspoon" #"\bteaspoons?|tsp\b"))
(def rg-tablespoon (list "tablespoon" #"\b(?:tablespoons?|Tbsp)\b"))
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



; Lista de unidades que NO deben convertirse a cups
(def non-volume-units
  #{"large" "medium" "small" "clove" "cloves" "piece" "pieces"})



(defn should-convert-to-volume? [ingredient-key unit-key target-unit]
  (cond
    ; Si el sistema es métrico, convertir todo excepto unidades no-volumétricas
    (= target-unit "auto-metric")
    (not (contains? non-volume-units unit-key))

    ; Para otros sistemas, mantener lógica original
    :else
    (not (contains? non-volume-units unit-key))))



;; FILE READING
; Funct to read file line by line
(defn read-file-lines [file-path]
  (with-open [reader (io/reader file-path)]
    (doall (line-seq reader))))

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
      (let [token-name (first regex-item) ; Gets the name of that token
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
          (let [; Finds position where match began
                match-pos (.indexOf input-text (str matched-txt))]
                            ;(println "Token:" token-name "Match:" matched-txt "Position:" match-pos)

                            ; If the match position is at the start, returns the token name and the match as a string
            (if (= match-pos 0)
              (list token-name matched-txt)
              nil))
                        ; Else just returns a null 
          nil)))
    rg-dict)))

; Uses text from tokens to find which one is longest 
(defn get-max-len [match-list]
    ;(println "Get maximum length string of all of the matches")
  (apply max
         (map
          (fn [match] (count (second match)))
          match-list)))

; Uses item matched from list and keeps only the one that meets the longest length found  
(defn filter-max [matches max-len]
    ;(println "Keeping only the longest one")
  (filter
   (fn [match]
     (= (count (second match)) max-len)) matches))

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
      (let [longest-len (get-max-len all-found-matches)
            longest-matches (filter-max all-found-matches longest-len)]

                    ; Once found, body just returns thel ongest one
        (first longest-matches)))))

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
    (let [input-trim (str/trim input) ; Para hacer llamadas, ya quita los empty spaces

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
                       (str/trim (subs input-trim (+ pos-match-start length))))]
            ; Ahora el body para build list 
      (cons
       (list (first type-txtmatch) extracted-txt)
       (tokenize rest-input rg-dict)))))

;; RECIPE HANDLING
; Recieves the chunk (aka the list of file paths) 
(defn process-recipe [file-path]
  (let [; Read file lines w reader
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
                                        ))recipe-lines))]
        ; Returns list w file name, original lines just in case, and tokenized lines 
        ;(println "\n\n\n")
    (println "Tokenized lines:" tokenized-lines)
    (list file-path recipe-lines tokenized-lines)))

; Recieves a chunk of recipes and then tokenizes the recipe
(defn process-chunk [chunk]
  (println "Processing: " chunk)
  (doall (map process-recipe chunk)))

;; SUB-FUNCTIONS FOR RECIPE CONVERSION
; Extrae val numerico de temp given  
(defn extract-num-value [num-string]
  (let [numeric-part (re-find #"\d+" num-string)]
        ; Returns num if it did match, 0 if it did not find the number
    (if numeric-part (Integer/parseInt numeric-part) 0)))

; Converts far to cel and returns it as a token C = (F - 32) / (9 / 5)
(defn f-to-c [f-temp-string]
  (let [; Calls helper funct to convert farenheit 
        f-value (extract-num-value f-temp-string)
        c-value (/ (* (- f-value 32) 5) 9.0) ; Plugs into eq
        ]
    (list "temp-C" (str c-value "°C"))))

; Converts far to cel and returns it as a token F = (C * (9 / 5)) +32
(defn c-to-f [c-temp-string]
  (let [; Calls helper funct to convert farenheit 
        c-value (extract-num-value c-temp-string)
        f-value (+ (* c-value (/ 9 5)) 32) ; Plugs into eq
        ]
    (list "temp-F" (str f-value "°F"))))

; Converts int or fraction to a numeric val
(defn numToInt [int-str]
  (read-string int-str))

; Converts a mixed fraction to a number
(defn mixedFrac [mixed-frac]
  (let [parts (clojure.string/split mixed-frac #" ")
        resp (+ (numToInt (first parts)) (numToInt (second parts)))]
    resp))

;; COMPARISON TOKENS AGAINST USER PREFERENCES
;; F -> C range or C -> F based on user desired
(defn temp-range-convert [temp-range-str user-temp-units]
    (let [
        ; List of number matches using re-seq 
        temp-nums (re-seq #"[0-9]+" temp-range-str) 
        ; Converts temps to numbers
        temp1 (Integer/parseInt (first temp-nums)) 
        temp2 (Integer/parseInt (second temp-nums))
        ]
        ; For temperature DIFFERENCES, just multiply by 5/9
        ;c-val1 (* f-val1 (/ 5 9.0))
        ;c-val2 (* f-val2 (/ 5 9.0))

        ; user-temp units = true -> F to C; else C to F
        (if user-temp-units
            ; F -> C calc
            (let [
                c1 (* temp1 (/ 5 9))
                c2 (* temp2 (/ 5 9))
                ]
                ; Exec checks
                (println "Changed " temp1 "-" temp2 "->" c1 "-" c2)
                ; Returns list converted with the new token name 
                (list "temp-c-range" (str c1 "°C-" c2 "°C"))
            )

            ; C -F F calc
            (let [
                f1 (* temp1 (/ 9 5))
                f2 (* temp2 (/ 9 5))
                ]
                ; Body 
                (println "Changed " temp1 "-" temp2 "->" f1 "-" f2)
                (list "temp-f-range" (str f1 "°F-" f2 "°F"))
            )
        )
    )
)

; Processes line using result of whether user wants celcius
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

                    ; Checks recipe range F, user C
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-f-range") 
                        user-temp-units ; True C
                    )
                        ; Need to convert F to C, calls funct w value and user desired
                        (temp-range-convert (second token) user-temp-units)

                    ; Checks recipe range C, user F
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-c-range") 
                        (not user-temp-units) ; False F
                    )
                        ; Need to convert C to F, calls funct w value and user desired
                        (temp-range-convert (second token) user-temp-units)

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
)

; Main function to manipulate one recipe at a time based on user preferences
(defn manipulate-recipe [recipe user-options]
  (println "\n------- BEGIN MANIPULATION -------")
  (let [recipe-name (first recipe)
        original-lines (second recipe)
        tokenized-lines (nth recipe 2)

            ; Checks if user wants C
        user-temp-units (= "C" (second (second (second user-options))))
            ; Extracts number of portions that user wants, need read-string to handle that it's a string
        user-num-portions (read-string (second (second (nth user-options 2))))

            ; Find recipe number of servings from tokenized lines
        recipe-serves
                ; Extracts number value
        (extract-num-value
                    ; Extract the token for serves 
         (second (first
                  (filter
                   (fn [token-line] (= (first token-line) "serves-amt"))
                        ; Need to flatten when looking for amt to take into account diff line skips and such
                   (apply concat tokenized-lines)))))

            ; Amt to scale a recipe
        scale-factor (/ user-num-portions recipe-serves)]
        (println "TEMP ADJ (F FALSE C TRUE): " user-temp-units)
    (println "Processing recipe:" recipe-name " with" recipe-serves " user wants  " user-num-portions "; scaled: " scale-factor "\n")

        ; Process all tokenized lines
    (let [corrected-temp (doall (map
                                 (fn [token-line] (process-token-line token-line user-temp-units scale-factor)) tokenized-lines))]

            ; Return updated recipe structure
      (list recipe-name original-lines corrected-temp))))

;; ========================================
;; Inicio de conversiones
;; ========================================

; Función que calcula calorías basándose en gramos de ingrediente
(defn calculate-calories [ingredient-token grams]
  ; Busca las calorías por 100g del ingrediente en el diccionario IngCal100, si no existe devuelve 0
  (let [calories-per-100g (get IngCal100 ingredient-token 0)]
    ; Calcula las calorías: (gramos ÷ 100) × calorías_por_100g
    (* (/ grams 100.0) calories-per-100g)))

;; ========================================
;; CÁLCULO DE CALORÍAS
;; ========================================

; Calcula las calorías totales de un ingrediente basándose en su peso en gramos
; Utiliza el diccionario IngCal100 que contiene calorías por cada 100g del ingrediente
(defn calculate-calories [ingredient-token grams]
  ; Busca las calorías por 100g del ingrediente en el diccionario IngCal100, si no existe devuelve 0
  (let [calories-per-100g (get IngCal100 ingredient-token 0)]
    ; Calcula las calorías: (gramos ÷ 100) × calorías_por_100g
    (* (/ grams 100.0) calories-per-100g)))

;; ========================================
;; CLASIFICACIÓN DE UNIDADES DE MEDIDA
;; ========================================

; Clasifica las unidades de medida en categorías para facilitar las conversiones
; Divide en: peso (weight), longitud (length), volumen (volume), métrico (metric) y otros (other)
(defn get-unit-category [unit-key]
  (cond
    ; Si la unidad es libra, libras, pounds u onza → categoría "weight" (peso)
    (or (= unit-key "lb") (= unit-key "lbs") (= unit-key "pounds") (= unit-key "ounce")) "weight"
    ; Si la unidad es pulgadas → categoría "length" (longitud)
    (or (= unit-key "in") (= unit-key "inches") (= unit-key "fract-in")) "length"
    ; Si la unidad es taza, cucharadita, cucharada o pinta → categoría "volume" (volumen)
    (or (= unit-key "cup") (= unit-key "teaspoon") (= unit-key "tablespoon") (= unit-key "pint")) "volume"
    ; Si ya está en gramos → categoría "metric" (métrico)
    (= unit-key "gram") "metric"
    ; Para cualquier otro caso → categoría "other" (otro)
    :else "other"))

;; ========================================
;; BÚSQUEDA Y CONFIGURACIÓN DE TOKENS
;; ========================================

; Busca un token específico dentro de una línea de tokens tokenizados
; Utilizado para encontrar configuraciones del sistema como "r-system" o "system-metric"
(defn find-token-type [token-line token-type]
  ; Filtra tokens que no sean nil Y que el primer elemento coincida con el tipo buscado
  (first (filter (fn [token] (and (not (nil? token)) (= (first token) token-type))) token-line)))

; Extrae y procesa la configuración de conversión de unidades desde los tokens del usuario
; Determina si usar conversión automática a métrico o conversión específica a una unidad

;; ========================================
;; FACTORES DE CONVERSIÓN DE INGREDIENTES
;; ========================================

; Obtiene el factor de conversión específico para un ingrediente y tipo de unidad
; Consulta el diccionario ingredient-conversions para factores precisos por ingrediente
(defn get-conversion [ingredient-key unit-type]
  ; Busca las conversiones del ingrediente en el diccionario ingredient-conversions
  (let [conversions (get ingredient-conversions ingredient-key)]
    ; Según el tipo de unidad, devuelve el factor de conversión correspondiente
    (cond
      ; Si es cup → devuelve factor cup-to-grams
      (= unit-type "cup") (:cup-to-grams conversions)
      ; Si es teaspoon → devuelve factor tsp-to-grams
      (= unit-type "teaspoon") (:tsp-to-grams conversions)
      ; Si es tablespoon → devuelve factor tbsp-to-grams
      (= unit-type "tablespoon") (:tbsp-to-grams conversions)
      ; Si ya está en gramos → factor 1.0 (no conversión)
      (= unit-type "gram") 1.0
      ; Si es libra, libras o pounds → devuelve factor lb-to-grams
      (or (= unit-type "lb") (= unit-type "lbs") (= unit-type "pounds")) (:lb-to-grams conversions)
      ; Si es onza → devuelve factor oz-to-grams
      (= unit-type "ounce") (:oz-to-grams conversions)
      ; Si es pint → factor fijo 473.176 (1 pint = 473.176 ml ≈ 473.176 g para líquidos)
      (= unit-type "pint") 473.176
      ; Si es pulgadas → factor 1.0 (primera línea duplicada)
      (or (= unit-type "in") (= unit-type "inches")) 1.0
      ; Si es pulgadas → factor 1.0 (segunda línea duplicada)
      (or (= unit-type "in") (= unit-type "inches")) 1.0
      ; Caso por defecto → factor 1.0
      :else 1.0)))

;; ========================================
;; CONVERSIONES A GRAMOS (UNIDAD BASE)
;; ========================================

; Convierte cualquier cantidad de unidades imperiales/volumétricas a gramos
; Utiliza factores específicos por ingrediente para mayor precisión en volúmenes
(defn convert-to-grams [amount ingredient-key unit-key]
  ; Obtiene el factor de conversión específico para el ingrediente y unidad
  (let [conversion (get-conversion ingredient-key unit-key)]
    ; Si existe factor de conversión, multiplica cantidad × factor
    (if conversion
      (* amount conversion)
      ; Si no hay factor, devuelve cantidad original sin cambios
      amount)))

;; ========================================
;; CONVERSIONES AUTOMÁTICAS A SISTEMA MÉTRICO
;; ========================================

; Convierte automáticamente unidades imperiales al sistema métrico correspondiente
; Maneja peso (a gramos), longitud (a centímetros) y volumen (a gramos vía ingrediente)
(defn convert-to-metric [amount unit-key ingredient-key]
  ; Obtiene la categoría de la unidad (peso, longitud, volumen, etc.)
  (let [unit-category (get-unit-category unit-key)]
    (cond
      (= unit-key "fract-in")
      {:amount (* amount 2.54) :unit "cm"}
      ; Si la categoría es "weight" (peso)
      (= unit-category "weight")
      (cond
        ; Si es libra, libras o pounds → convierte a gramos (* 453.592)
        (or (= unit-key "lb") (= unit-key "lbs") (= unit-key "pounds"))
        {:amount (* amount 453.592) :unit "gram"}
        ; Si es onza → convierte a gramos (* 28.3495)
        (= unit-key "ounce")
        {:amount (* amount 28.3495) :unit "gram"}
        ; Otros casos de peso → mantiene valor y unidad original
        :else {:amount amount :unit unit-key})

      ; Si la categoría es "length" (longitud)
      (= unit-category "length")
      (cond
        ; Si es pulgadas → convierte a centímetros (* 2.54)
        (or (= unit-key "in") (= unit-key "inches") (= unit-key "fract-in"))
        {:amount (* amount 2.54) :unit "cm"}
        ; Otros casos de longitud → mantiene valor y unidad original
        :else {:amount amount :unit unit-key})

      ; Si la categoría es "volume" (volumen)
      (= unit-category "volume")
      ; Convierte primero a gramos usando factores específicos del ingrediente
      (let [grams-converted (convert-to-grams amount ingredient-key unit-key)]
        {:amount grams-converted :unit "gram"})

      ; Si la categoría es "metric" → ya está en métrico, no cambia
      (= unit-category "metric")
      {:amount amount :unit unit-key}

      ; Cualquier otra categoría → mantiene valor y unidad original
      :else {:amount amount :unit unit-key})))

;; ========================================
;; CONVERSIONES DESDE GRAMOS A UNIDADES ESPECÍFICAS
;; ========================================

; Convierte desde gramos (unidad base) a la unidad de salida deseada por el usuario
; Aplica conversión inversa usando los factores específicos del ingrediente
(defn convert-from-grams [grams ingredient-key target-unit]
  ; Obtiene las conversiones disponibles para el ingrediente
  (let [conversions (get ingredient-conversions ingredient-key)]
    ; Según la unidad objetivo, aplica la conversión inversa
    (cond
      ; Si el objetivo es métrico → mantiene gramos
      (= target-unit "user-metric") grams
      ; Si el objetivo es cups
      (= target-unit "user-cup")
      ; Busca factor cup-to-grams para este ingrediente
      (if-let [factor (:cup-to-grams conversions)]
        ; Convierte: gramos ÷ factor = cups
        (/ grams factor)
        ; Si no hay factor, mantiene gramos
        grams)
      ; Si el objetivo es teaspoons
      (= target-unit "user-teaspoon")
      ; Busca factor tsp-to-grams para este ingrediente
      (if-let [factor (:tsp-to-grams conversions)]
        ; Convierte: gramos ÷ factor = teaspoons
        (/ grams factor)
        ; Si no hay factor, mantiene gramos
        grams)
      ; Si el objetivo es tablespoons
      (= target-unit "user-tablespoon")
      ; Busca factor tbsp-to-grams para este ingrediente
      (if-let [factor (:tbsp-to-grams conversions)]
        ; Convierte: gramos ÷ factor = tablespoons
        (/ grams factor)
        ; Si no hay factor, mantiene gramos
        grams)
      ; Caso por defecto: mantiene gramos
      :else grams)))

;; ========================================
;; MAPEO DE NOMBRES DE UNIDADES PARA DISPLAY
;; ========================================

; Convierte los códigos internos de unidades a nombres legibles para mostrar al usuario
; Mapea tokens como "user-metric" a nombres de unidad como "gram"
(defn get-output-unit-name [target-unit converted-unit]
  (cond
    ; Si es auto-métrico, usar la unidad convertida específica
    (= target-unit "auto-metric")
    (cond
      (= converted-unit "gram") "grams"
      (= converted-unit "cm") "cm"
      :else converted-unit)

    ; Otros casos mantener lógica original
    (= target-unit "user-metric") "gram"
    (= target-unit "user-cup") "cup"
    (= target-unit "user-teaspoon") "teaspoon"
    (= target-unit "user-tablespoon") "tablespoon"
    :else "gram"))


;; ========================================
;; CONFIGURACIÓN DEL SISTEMA DESDE PRIMERA LÍNEA
;; ========================================

; Extrae y parsea la configuración del sistema de conversiones desde la primera línea del archivo
; Determina qué tipo de conversiones aplicar (métrico, cups, teaspoons, etc.)
(defn parse-system-config [first-line]
  ; DEBUG: Imprime estructura de la primera línea
  (println "DEBUG parse-system-config:")
  (println "  First line structure:" first-line)
  (println "  First element:" (first first-line))
  (println "  Second element:" (second first-line))

  ; ARREGLO: Comparar como strings
  (cond
    ; Si el primer elemento es "system:" (convertido a string)
    (= (str (first first-line)) "system:")
    ; Extrae el valor del sistema (segundo elemento)
    (let [system-value (second first-line)]
      (println "  Found system token with value:" system-value)
      (cond
        ; Si el valor es "metric" → configuración para auto-métrico
        (= (str system-value) "metric") {:system "r-system" :target-unit "auto-metric"}
        ; Si el valor es "cup" → configuración para user-cup
        (= (str system-value) "cup") {:system "r-system" :target-unit "user-cup"}
        ; Si el valor es "teaspoon" → configuración para user-teaspoon
        (= (str system-value) "teaspoon") {:system "r-system" :target-unit "user-tablespoon"}
        ; Si el valor es "tablespoon" → configuración para user-tablespoon
        (= (str system-value) "tablespoon") {:system "r-system" :target-unit "user-tablespoon"}
        ; Otros valores → retorna nil
        :else nil))

    ; ALTERNATIVA: Si es formato de lista de listas
    (and (list? first-line) (some list? first-line))
    ; Busca un token "system:" dentro de las listas anidadas
    (let [system-token (first (filter #(and (list? %)
                                            (= (str (first %)) "system:")) first-line))]
      ; Si encuentra el token system
      (if system-token
        ; Extrae el valor del sistema
        (let [system-value (second system-token)]
          (println "  Found nested system token with value:" system-value)
          (cond
            ; Si el valor es "metric" → configuración para auto-métrico
            (= (str system-value) "metric") {:system "r-system" :target-unit "auto-metric"}
            ; Si el valor es "cup" → configuración para user-cup
            (= (str system-value) "cup") {:system "r-system" :target-unit "user-cup"}
            ; Si el valor es "teaspoon" → configuración para user-teaspoon
            (= (str system-value) "teaspoon") {:system "r-system" :target-unit "user-tablespoon"}
            ; Si el valor es "tablespoon" → configuración para user-tablespoon
            (= (str system-value) "tablespoon") {:system "r-system" :target-unit "user-tablespoon"}
            ; Otros valores → retorna nil
            :else nil))))

    ; Si no encuentra nada
    :else
    (do
      ; Imprime mensaje de debug
      (println "  No system configuration found")
      ; Retorna nil
      nil)))

;; ========================================
;; PARSING DE NÚMEROS Y FRACCIONES ESCALADAS
;; ========================================

; Convierte strings de números escalados (enteros y fracciones) a valores numéricos decimales
; Maneja tanto fracciones simples como números enteros representados como strings
(defn parse-scaled-number [number-str]
  (cond
    ; Si el string contiene "/" (es una fracción)
    (re-find #"/" number-str)
    ; Divide el string por "/" y convierte a fracción
    (let [parts (clojure.string/split number-str #"/")]
      (/ (read-string (first parts)) (read-string (second parts))))
    ; Si no es fracción, convierte directamente a número
    :else (read-string number-str)))

; Convierte strings de fracciones mixtas escaladas (ej: "2 1/3") a valores numéricos decimales
; Suma la parte entera con la fracción para obtener el valor total
(defn parse-mixed-scaled [mixed-str]
  ; Divide el string por espacios
  (let [parts (clojure.string/split mixed-str #" ")]
    ; Si tiene 2 partes (número entero + fracción)
    (if (= (count parts) 2)
      ; Suma el entero + la fracción parseada
      (+ (read-string (first parts)) (parse-scaled-number (second parts)))
      ; Si no, trata todo como fracción simple
      (parse-scaled-number mixed-str))))

;; ========================================
;; IDENTIFICACIÓN DE TIPOS DE TOKENS
;; ========================================

; Identifica si un token representa una cantidad escalada (números procesados por el escalado)
; Reconoce tokens que empiecen con "number-s" indicando que fueron escalados
(defn is-scaled-number? [token]
  ; Verifica que sea una lista
  (and (list? token)
       ; Verifica que tenga al menos 2 elementos
       (>= (count token) 2)
       ; Verifica que el primer elemento empieze con "number-s"
      (or (clojure.string/starts-with? (str (first token)) "number-s")
            (= (str (first token)) "fract-in"))))

; Identifica si un token representa una unidad de medida convertible
; Reconoce unidades como cups, teaspoons, pounds, etc. que pueden ser convertidas
(defn is-convertible-unit? [token]
  ; Verifica que sea una lista
  (and (list? token)
       ; Verifica que tenga al menos 2 elementos
       (>= (count token) 2)
       ; Verifica que el primer elemento esté en el set de unidades convertibles
       (contains? #{"cup" "teaspoon" "tablespoon" "lbs" "lb" "pounds" "ounce" "inches" "in" "gram" "fract-in" "pint"}
                  (str (first token)))))

; Busca y extrae el token de ingrediente dentro de una línea de tokens
; Identifica ingredientes por el prefijo "ingredient-" en el tipo de token
(defn find-ingredient-in-line [token-line]
  ; Encuentra el primer token que sea una lista
  (first (filter #(and (list? %)
                       ; Que tenga al menos 2 elementos
                       (>= (count %) 2)
                       ; Y que el primer elemento empiece con "ingredient-"
                       (clojure.string/starts-with? (str (first %)) "ingredient-"))
                 token-line)))

;; ========================================
;; CONVERSIÓN DE VALORES DE CANTIDAD
;; ========================================

; Convierte tokens de cantidad según su tipo (escalado simple, fracción mixta, etc.)
; Extrae y parsea el valor numérico del token manteniendo el tipo original
(defn convert-quantity-value [quantity-token]
  ; Verifica que sea una lista con al menos 2 elementos
  (if (and (list? quantity-token) (>= (count quantity-token) 2))
    ; Extrae el tipo del token (primer elemento)
    (let [token-type (str (first quantity-token))
          ; Extrae el valor del token (segundo elemento)
          token-value (str (second quantity-token))]
      (cond
        ; Si es "number-s" → parsea como número escalado
        (= token-type "number-s") (parse-scaled-number token-value)
        ; Si es "number-s-mix" → parsea como fracción mixta escalada
        (= token-type "number-s-mix") (parse-mixed-scaled token-value)
        ; Otros casos → retorna el segundo elemento directamente
       (= token-type "fract-in")
           (let [fraction-part (clojure.string/replace token-value #"\"" "")]
             (parse-scaled-number fraction-part))
           :else (second quantity-token)))
         (second quantity-token)))

;; ========================================
;; CONVERSIÓN DE PARES CANTIDAD-UNIDAD
;; ========================================

; Convierte un par cantidad-unidad aplicando las conversiones según la configuración del usuario
; Coordina entre conversión a métrico automático o conversión específica a una unidad
(defn convert-quantity-unit-pair [quantity-token unit-token ingredient-key config]
  ; Si no hay configuración, retorna tokens originales
  (if (not config)
    (list quantity-token unit-token)
    ; Parsea la cantidad del token
    (let [parsed-quantity (convert-quantity-value quantity-token)
          ; Extrae el tipo de unidad
          unit-type (str (first unit-token))
          ; Extrae la unidad objetivo de la configuración
          target-unit (:target-unit config)]
      ; Verifica si debe convertir usando función should-convert-to-volume?
      (if (not (should-convert-to-volume? ingredient-key unit-type target-unit))
        ; No convertir - mantener original pero con cantidad parseada
        (list (list (first quantity-token) parsed-quantity) unit-token)

        ; SÍ convertir - procesar según el tipo de objetivo
        (cond
          ; Si el objetivo es auto-métrico
          (= target-unit "auto-metric")
          ; Convierte usando convert-to-metric
          (let [metric-conversion (convert-to-metric parsed-quantity unit-type ingredient-key)
                          ; Extrae cantidad convertida
                converted-amount (:amount metric-conversion)
                          ; Extrae unidad convertida
                converted-unit (:unit metric-conversion)
                          ; Obtiene nombre de unidad apropiado
                output-unit-name (get-output-unit-name target-unit converted-unit)]
                      ; Retorna lista con cantidad y unidad convertidas
            (list (list (first quantity-token) converted-amount)
                  (list converted-unit output-unit-name)))

          ; Si el objetivo es user-cup, user-teaspoon, o user-tablespoon
          (contains? #{"user-cup" "user-teaspoon" "user-tablespoon"} target-unit)
          ; Convierte primero a gramos, luego a la unidad objetivo
          (let [grams (convert-to-grams parsed-quantity ingredient-key unit-type)
                ; Convierte de gramos a la unidad objetivo
                converted-amount (convert-from-grams grams ingredient-key target-unit)
                ; Obtiene el nombre de la unidad para mostrar
                output-unit-name (get-output-unit-name target-unit target-unit)]
            ; Retorna lista con cantidad y unidad convertidas
            (list (list (first quantity-token) converted-amount)
                  (list output-unit-name output-unit-name)))

          ; Otros casos → mantiene original con cantidad parseada
          :else (list (list (first quantity-token) parsed-quantity) unit-token))))))

;; ========================================
;; UTILIDADES DE ACCESO SEGURO
;; ========================================

; Accede de forma segura a elementos de una colección por índice
; Evita errores al acceder a índices fuera del rango de la colección
(defn nth-or-nil [coll n]
  ; Verifica que el índice sea válido (≥ 0 y < tamaño de colección)
  (if (and (>= n 0) (< n (count coll)))
    ; Si es válido, retorna el elemento en posición n
    (nth coll n)))

;; ========================================
;; IDENTIFICACIÓN DE PARES CANTIDAD-UNIDAD
;; ========================================

; Identifica y mapea pares consecutivos de cantidad-unidad dentro de una línea de tokens
; Encuentra números escalados seguidos inmediatamente por unidades convertibles
(defn find-quantity-unit-pairs [tokens]
  ; Crea lista de tokens con sus índices
  (let [indexed-tokens (map-indexed (fn [idx token] {:idx idx :token token}) tokens)]
    ; Filtra elementos válidos (no nil)
    (filter identity
      ; Mapea cada token indexado
            (map (fn [{:keys [idx token]}]
             ; Obtiene el siguiente token
                   (let [next-token (nth-or-nil tokens (inc idx))]
               ; Si existe siguiente token Y el actual es número escalado Y el siguiente es unidad convertible
                     (if (and next-token
                                (is-scaled-number? token)
                                (is-convertible-unit? next-token))
                 ; Retorna mapa con información del par
                       {:quantity-idx idx           ; Índice de la cantidad
                        :unit-idx (inc idx)         ; Índice de la unidad
                        :quantity token             ; Token de cantidad
                        :unit next-token})))        ; Token de unidad
                 indexed-tokens))))







;; ========================================
;; EXTRACCIÓN DE INGREDIENTES Y CALORÍAS
;; ========================================

; Extrae información de ingredientes con cantidades en gramos de una línea tokenizada
(defn extract-ingredient-grams [token-line]
  (let [ingredient-token (find-ingredient-in-line token-line)]
    (if ingredient-token
      (let [ingredient-key (str (first ingredient-token))
            ; Busca pares cantidad escalada + unidad de peso/volumen
            quantity-unit-pairs (find-quantity-unit-pairs token-line)]
        (if (not (empty? quantity-unit-pairs))
          (let [first-pair (first quantity-unit-pairs)
                quantity-value (convert-quantity-value (:quantity first-pair))
                unit-type (str (first (:unit first-pair)))
                ; Convierte todo a gramos para cálculo de calorías
                grams (convert-to-grams quantity-value ingredient-key unit-type)]
            {:ingredient ingredient-key :grams grams}))))))

; Calcula las calorías totales de una receta procesada
(defn calculate-recipe-calories [processed-recipe]
  (let [recipe-name (first processed-recipe)
        converted-lines (nth processed-recipe 2)
        ; Extrae ingredientes con gramos de todas las líneas
        ingredients-with-grams (filter identity
                                       (map extract-ingredient-grams converted-lines))
        ; Calcula calorías para cada ingrediente
        calories-per-ingredient (map (fn [ing-data]
                                       (let [ingredient (:ingredient ing-data)
                                             grams (:grams ing-data)
                                             calories (calculate-calories ingredient grams)]
                                         {:ingredient ingredient 
                                          :grams grams 
                                          :calories calories}))
                                     ingredients-with-grams)
        ; Suma todas las calorías
        total-calories (reduce + (map :calories calories-per-ingredient))
        ; Extrae número de porciones
        serves-token (first (filter identity
                                    (map (fn [line]
                                           (first (filter #(= (str (first %)) "serves-amt") line)))
                                         converted-lines)))
        servings (if serves-token (extract-num-value (second serves-token)) 1)
        calories-per-serving (/ total-calories servings)]
    
    {:recipe-name recipe-name
     :ingredients calories-per-ingredient
     :total-calories total-calories
     :servings servings
     :calories-per-serving calories-per-serving}))

; Procesa múltiples recetas y calcula sus calorías
(defn process-recipes-calories [converted-recipes]
  (map calculate-recipe-calories converted-recipes))




;; ========================================
;; FORMATEO EN LISTAS ANIDADAS 
;; ========================================


(defn format-final-results [converted-recipes calorie-data]
  ; Construye lista de resultados con estructura simplificada
  (list "results"
        ; Mapea cada par receta-calorías a formato final simplificado
        (doall (map (fn [recipe calorie-info]
                      ; Crea lista con: nombre, tokens, calorías totales, calorías por porción
                      (list (first recipe)           ; nombre de receta (primer elemento)
                            (nth recipe 2)           ; tokens convertidos (tercer elemento)
                            ; Token separado para calorías totales
                            (list "total-cal" (:total-calories calorie-info))
                            ; Token separado para calorías por porción
                            (list "serving-cal" (:calories-per-serving calorie-info))))
                    ; Procesa todas las recetas con sus datos de calorías
                    converted-recipes calorie-data))))


;; ========================================
;; APLICACIÓN DE CONVERSIONES A TOKENS
;; ========================================

; Aplica las conversiones calculadas a la línea de tokens manteniendo el orden original
; Reemplaza tokens en posiciones específicas con sus versiones convertidas
(defn apply-conversions [tokens conversions]
  ; Crea mapa de conversiones: índice → nuevo valor
  (let [conversion-map (into {}
                             ; Aplana lista de conversiones en pares [índice valor]
                             (mapcat (fn [conv]
                                       ; Para cada conversión, crea dos pares: cantidad y unidad
                                       [[(:quantity-idx conv) (:new-quantity conv)]
                                        [(:unit-idx conv) (:new-unit conv)]])
                                     conversions))]
    ; Mapea tokens con índices
    (map-indexed (fn [idx token]
                   ; Si hay conversión para este índice, usa valor convertido; sino, token original
                   (get conversion-map idx token))
                 tokens)))

;; ========================================
;; PROCESAMIENTO DE LÍNEAS DE TOKENS
;; ========================================


(defn normalize-special-tokens [token-line]
  (mapcat (fn [token]
            (if (and (list? token) (= (str (first token)) "fract-in"))
              ; Expandir fract-in a dos tokens separados
              (let [token-value (str (second token))
                    fraction-part (clojure.string/replace token-value #"\"" "")]
                ; Retorna lista de dos tokens: [número, unidad]
                [(list "number-s" fraction-part) (list "inches" "inches")])
              ; Token normal, retorna como lista de un elemento
              [token]))
          token-line))


; Procesa una línea completa de tokens aplicando conversiones según la configuración
; Coordina la conversión de cantidades individuales y pares cantidad-unidad
(defn process-line [token-line config]
  ; Si la línea está vacía o no hay configuración, retorna línea original
  (if (or (empty? token-line) (not config))
    token-line
    ; Busca ingrediente en la línea
    (let [normalized-tokens (normalize-special-tokens token-line)]
     (let [ingredient-token (find-ingredient-in-line normalized-tokens)
          ; Busca pares cantidad-unidad en la línea
          pairs (find-quantity-unit-pairs normalized-tokens)]
      ; Si no hay pares o no hay ingrediente
      (if (or (empty? pairs) (not ingredient-token))
        ; Solo convertir cantidades individuales (sin conversión de unidades)
        (map (fn [token]
               ; Si es número escalado, convierte solo la cantidad
               (if (is-scaled-number? token)
                 (list (first token) (convert-quantity-value token))
                 ; Otros tokens permanecen igual
                 token))
             normalized-tokens)

        ; Procesar pares cantidad-unidad (conversión completa)
        ; Extrae clave del ingrediente
        (let [ingredient-key (str (first ingredient-token))
              ; Procesa cada par cantidad-unidad
              conversions (map (fn [pair]
                                 ; Convierte el par usando convert-quantity-unit-pair
                                 (let [converted-pair (convert-quantity-unit-pair
                                                       (:quantity pair)
                                                       (:unit pair)
                                                       ingredient-key
                                                       config)]
                                   ; Enriquece el par original con valores convertidos
                                   (assoc pair
                                          :new-quantity (first converted-pair)
                                          :new-unit (second converted-pair))))
                               pairs)]
          ; Aplica todas las conversiones a la línea de tokens
          (apply-conversions normalized-tokens conversions)))))))

;; ========================================
;; PROCESAMIENTO DE ARCHIVOS DE RECETAS
;; ========================================

; Procesa un archivo completo de receta aplicando conversiones línea por línea
; Extrae configuración de la primera línea y la aplica a todo el archivo
(defn process-recipe-file [lines]
  ; Si no hay líneas, retorna lista vacía
  (if (empty? lines)
    lines
    ; Extrae configuración de la primera línea
    (let [first-line (first lines)
          ; Parsea configuración del sistema
          config (parse-system-config first-line)]
      ; Procesa cada línea con la configuración
      (map #(process-line % config) lines))))

;; ========================================
;; FUNCIÓN PRINCIPAL DE CONVERSIÓN DE RECETAS
;; ========================================

; Función principal que orquesta la conversión completa de una receta
; Detecta si hay configuración de sistema y aplica las conversiones correspondientes
(defn convert-recipe [input-data]
  (cond
    ; Si hay datos Y contiene configuración "system:"
    (and (not (empty? input-data))
         ; Busca líneas que contengan "system:" (formato directo o anidado)
         (some #(and (list? %)
                     (or
                       ; Formato con keyword anidado
                      (some (fn [x] (and (list? x)
                                         (= (str (first x)) "system:"))) %)
                       ; Formato directo
                      (= (str (first %)) "system:")))
               input-data))
    ; Procesa el archivo de receta
    (process-recipe-file input-data)

    ; Si no hay configuración, devuelve datos sin cambios
    :else input-data))

;; ===========================
;; FIN DE CÓDIGO DE CONVERSIONES
;; ==============================



; Analyze all recipes and apply manipulations
; Analyze all recipes and apply manipulations
; Analyze all recipes and apply manipulations
; Analyze all recipes and apply manipulations with calorie calculation
; Analyze all recipes and apply manipulations with calorie calculation (NESTED LISTS OUTPUT)
(defn analyze-recipes [processed-recipes user-tokens]
  (let [manipulated-recipes (doall (map (fn [recipe]
                                          (manipulate-recipe recipe user-tokens))
                                        processed-recipes))]

    (println "Processed" (count manipulated-recipes) "recipes")

    (let [user-system-pref (second (second (first user-tokens)))
          converted-recipes (doall (map (fn [recipe]
                                          (let [recipe-name (first recipe)
                                                original-lines (second recipe)
                                                scaled-tokenized-lines (nth recipe 2)
                                                system-config (list "system:" user-system-pref)
                                                recipe-data (cons system-config scaled-tokenized-lines)
                                                converted-data (convert-recipe recipe-data)]
                                            (if converted-data
                                              (list recipe-name original-lines (doall converted-data))
                                              (list recipe-name original-lines (doall scaled-tokenized-lines)))))
                                        manipulated-recipes))
          calorie-data (doall (process-recipes-calories converted-recipes))
          final-results (second(format-final-results converted-recipes calorie-data))]

      (println "Applied unit conversions to scaled recipes:" (count converted-recipes))
      (println "Using user preference:" user-system-pref)

      ; Solo mostrar la estructura de lista anidada
      (println "\nFINAL RESULTS:")
      (println final-results)

      ; Retornar la estructura
      final-results)))

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
          opt-lines)))

  (println "\n-------TOKENIZED")
  (println opt-tokenized)

  ; HTML con header
  (println "\n-------HTML")
  (def htmlcompleto (str header "<div class='options'>"(html opt-tokenized)"</div>"))

    ; looking for user preferences servings amt
  (print "looking for servings: ")
  (print (second (second (nth opt-tokenized 2))))

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
             "recipes/Pan-Seared Steak with Garlic Butter.txt"])

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
                (pmap process-chunk data-chunks)))
                ; Print the tokens
       (doall (map (fn [x] (println (nth x 2) "\n\nNext Recipe Tokens:\n")) recipes-processed)) ; Check all the recipes 

                ; Passes tokenized recipe and the tokens of user customization
       (def fix-recipes (analyze-recipes recipes-processed opt-tokenized)))))

  (println exec-time)
  (println "Fixed recipes")

    ; Structure of recipes 
    ;(println (nth (first fix-recipes) 0) )
    ;(println (nth (first fix-recipes) 1) )
    ;(println (nth (first fix-recipes) 2) )

    ;(println (nth (map (first) fix-recipes) 2) )
  ;; (doall (map (fn [x] (println (nth x 2) "\n\nFINAL Recipe Tokens:\n")) fix-recipes)) ; Check all the recipes 
    (println fix-recipes)




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

(println "Mixed convert: "  (mixedFrac "1 1/2")) ; Test fraction to make sure its an int
#lang racket

;;INICIO CON FILTRO MIO - RACKET VERSION

(displayln "¡Hola, mundo!")

;; Required modules for file I/O and string operations
(require racket/string)
(require racket/list)
(require racket/file)
(require racket/path)
(require racket/format)
(require racket/match)
(require racket/future)

;; Definiciones de regex
;; Opciones de usuario 
(define rg-system (list "r-system" #rx"^system:"))
(define rg-u-cup (list "user-cup" #rx"^cup"))
(define rg-u-tsp (list "user-tsp" #rx"^teaspoon"))
(define rg-u-met (list "user-metric" #rx"^metric"))

(define rg-temp (list "r-temp" #rx"^temp:"))
(define rg-c (list "t-cel" #rx"^C"))
(define rg-f (list "t-far" #rx"^F"))

(define rg-portions (list "r-portions" #rx"^portions:"))
(define rg-num-port (list "num-portions" #rx"^[0-9]+")) ; DECIMALS??

(define rg-filter (list "r-filter" #rx"^filter:"))
(define rg-all (list "r-all" #rx"^all")) ; Keeps all recipes 
(define rg-other (list "custom-filter" #rx"^[a-z]+"))

;; Dictionary of tokens
(define dict-user (list
                rg-system
                rg-u-cup rg-u-tsp rg-u-met

                rg-temp
                rg-c rg-f

                rg-portions
                rg-num-port

                rg-filter
                rg-all rg-other))

;; Recetas
;; Regex para números enteros
(define rg-nums-int (list "number-integer" #rx"^[0-9]+"))

;; Regex para fracciones simples
(define rg-nums-frac (list "number-fraction" #rx"^[0-9]+/[0-9]+"))

;; Regex para fracciones mixtas
(define rg-nums-mixed (list "number-mixed" #rx"^[0-9]+\\s+[0-9]+/[0-9]+"))

;; Nuevos ingredientes específicos agregados del código 2
(define rg-granulated-sugar (list "ingredient-granulated-sugar" #rx"^\\bgranulated\\s+sugar\\b"))
(define rg-all-purpose-flour (list "ingredient-all-purpose-flour" #rx"^\\ball-purpose\\s+flour\\b"))
(define rg-almond-flour (list "ingredient-almond-flour" #rx"^\\balmond\\s+flour\\b"))
(define rg-cocoa-powder (list "ingredient-cocoa-powder" #rx"^\\bcocoa\\s+powder\\b"))
(define rg-dark-chocolate-chips (list "ingredient-dark-chocolate-chips" #rx"^\\bdark\\s+chocolate\\s+chips\\b"))
(define rg-sea-salt (list "ingredient-sea-salt" #rx"^\\bsea\\s+salt\\b"))
(define rg-kosher-salt (list "ingredient-kosher-salt" #rx"^\\bkosher\\s+salt\\b"))
(define rg-canola-oil (list "ingredient-canola-oil" #rx"^\\bcanola\\s\\(+oil\\)?\\b"))
(define rg-canola (list "ingredient-canola-oil" #rx"^\\bcanola\\s"))
(define rg-extra-virgin-olive-oil (list "ingredient-extra-virgin-olive-oil" #rx"^\\bextra-virgin\\s+olive\\s+oil\\b"))
(define rg-extra-virgin-light-oil (list "ingredient-extra-light-olive-oil" #rx"^\\bextra\\s+light\\s+olive\\s+oil\\b"))
(define rg-olive-oil (list "ingredient-olive-oil" #rx"^\\bolive\\s+oil\\b"))
(define rg-vanilla-extract (list "ingredient-vanilla-extract" #rx"^\\bvanilla\\s+extract\\b"))
(define rg-lemon-zest-grated (list "ingredient-lemon-zest-grated" #rx"^\\blemon\\s+zest\\s+\\(grated\\)\\b"))
(define rg-fresh-lemon-juice (list "ingredient-fresh-lemon-juice" #rx"^\\bfresh\\s+lemon\\s+juice\\b"))
(define rg-dry-fettuccine-pasta (list "ingredient-dry-fettuccine-pasta" #rx"^\\bdry\\s+fettuccine\\s+pasta\\b"))
(define rg-fettuccine-pasta (list "ingredient-fettuccine-pasta" #rx"^\\bfettuccine\\s+pasta\\b"))
(define rg-heavy-cream (list "ingredient-heavy-cream" #rx"^\\bheavy\\s+cream\\b"))
(define rg-red-pepper-flakes (list "ingredient-red-pepper-flakes" #rx"^\\bred\\s+pepper\\s+flakes\\b"))
(define rg-grated-romano-cheese (list "ingredient-grated-romano-cheese" #rx"^\\bgrated\\s+romano\\s+cheese\\b"))
(define rg-romano-cheese (list "ingredient-romano-cheese" #rx"^\\bromano\\s+cheese\\b"))
(define rg-grated-parmesan-cheese (list "ingredient-grated-parmesan-cheese" #rx"^\\bgrated\\s+parmesan\\s+cheese\\b"))
(define rg-parmesan-cheese (list "ingredient-parmesan-cheese" #rx"^\\bparmesan\\s+cheese\\b"))
(define rg-white-wine-vinegar (list "ingredient-white-wine-vinegar" #rx"^\\bwhite\\s+wine\\s+vinegar\\b"))
(define rg-garlic-clove-minced (list "ingredient-garlic-clove-minced" #rx"^\\bgarlic\\s+clove\\s+\\(minced\\)\\b"))
(define rg-garlic-clove (list "ingredient-garlic-clove" #rx"^\\bgarlic\\s+clove\\b"))
(define rg-garlic-butter (list "ingredient-garlic-butter" #rx"^\\bgarlic\\s+butter\\b"))
(define rg-garlic-solo (list "ingredient-garlic" #rx"^\\bgarlic\\b"))
(define rg-dried-oregano (list "ingredient-dried-oregano" #rx"^\\bdried\\s+oregano\\b"))
(define rg-smoked-paprika (list "ingredient-smoked-paprika" #rx"^\\bsmoked\\s+paprika\\b"))
(define rg-fresh-flat-leaf-parsley (list "ingredient-fresh-flat-leaf-parsley" #rx"^\\bfresh\\s+flat-leaf\\s+parsley\\b"))
(define rg-flat-leaf-parsley (list "ingredient-flat-leaf-parsley" #rx"^\\bflat-leaf\\s+parsley\\b"))
(define rg-new-york-strip-steaks (list "ingredient-new-york-strip-steaks" #rx"^(?i:\\bnew\\s+york\\s+strip\\s+steaks?\\b)"))
(define rg-top-sirloin-steaks (list "ingredient-top-sirloin-steaks" #rx"^(?i:\\btop\\s+sirloin\\s+steaks?\\b)"))
(define rg-ribeye (list "ingredient-ribeye" #rx"^\\b(?i:r)ibeye\\b"))
(define rg-steaks (list "ingredient-steaks" #rx"^\\bsteaks?\\b"))
(define rg-black-pepper (list "ingredient-black-pepper" #rx"^\\bblack\\s+pepper\\b"))
(define rg-unsalted-butter (list "ingredient-unsalted-butter" #rx"^\\bunsalted\\s+butter\\b"))
(define rg-fresh-rosemary (list "ingredient-fresh-rosemary" #rx"^\\bfresh\\s+rosemary\\b"))
(define rg-rosemary (list "ingredient-rosemary" #rx"^\\brosemary\\b"))
(define rg-ground-almonds (list "ingredient-ground-almonds" #rx"^\\bground\\s+almonds?\\b"))
(define rg-vegetable-oil (list "ingredient-olive-oil" #rx"^\\bvegetable\\s+oil\\b"))

;; Regex para ingredientes (case sensitive) - manteniendo los originales
(define rg-sugar (list "ingredient-sugar" #rx"^\\b(?:granulated\\s+)?sugar\\b"))
(define rg-flour (list "ingredient-flour" #rx"^\\b(?:all-purpose\\s+|almond\\s+)?flour\\b"))
(define rg-cocoa (list "ingredient-cocoa" #rx"^\\bcocoa\\s+powder\\b"))
(define rg-powdered-sugar (list "ingredient-powdered-sugar" #rx"^\\bpowdered\\s+sugar\\b"))
(define rg-chocolate (list "ingredient-chocolate" #rx"\\b(?:dark\\s+)?chocolate\\s+chips\\b"))
(define rg-salt (list "ingredient-salt" #rx"\\b(?:sea\\s+|kosher\\s+)?salt\\b"))
(define rg-eggs (list "ingredient-eggs" #rx"\\beggs?\\b"))
(define rg-oil (list "ingredient-oil" #rx"\\b(?:canola\\s+|extra-virgin\\s+olive\\s+)?oil\\b"))
(define rg-water (list "ingredient-water" #rx"\\bwater\\b"))
(define rg-vanilla (list "ingredient-vanilla" #rx"\\bvanilla(?:\\s+extract)?\\b"))
(define rg-baking-powder (list "ingredient-baking-powder" #rx"\\bbaking\\s+powder\\b"))
(define rg-lemon-zest (list "ingredient-lemon-zest" #rx"\\blemon\\s+zest(?:\\s+\\(grated\\))?\\b"))
(define rg-lemon (list "ingredient-lemon" #rx"\\blemon\\b"))
(define rg-lemon-juice (list "ingredient-lemon-juice" #rx"\\b(?:fresh\\s+)?lemon\\s+juice\\b"))
(define rg-pasta (list "ingredient-pasta" #rx"\\b(?:dry\\s+)?(?:fettuccine\\s+)?pasta\\b"))
(define rg-butter (list "ingredient-butter" #rx"\\bbutter\\b"))
(define rg-cream (list "ingredient-cream" #rx"\\b(?:heavy\\s+)?cream\\b"))
(define rg-pepper (list "ingredient-pepper" #rx"\\b(?:red\\s+pepper\\s+flakes|pepper)\\b"))
(define rg-garlic-salt (list "ingredient-garlic-salt" #rx"\\b(?i:g)arlic\\s+salt\\b"))
(define rg-romano (list "ingredient-romano" #rx"\\bgrated\\s+(?i:r)omano(?:\\s+cheese)?\\b"))
(define rg-parmesan (list "ingredient-parmesan" #rx"\\b(?:grated\\s+)?(?i:p)armesan\\s+cheese\\b"))
(define rg-vinegar (list "ingredient-vinegar" #rx"\\b(?:white\\s+wine\\s+)?vinegar\\b"))
(define rg-garlic (list "ingredient-garlic" #rx"\\bgarlic(?:\\s+clove(?:s)?(?:\\s+\\(minced\\))?)?\\b"))
(define rg-oregano (list "ingredient-oregano" #rx"\\b(?:dried\\s+)?oregano\\b"))
(define rg-paprika (list "ingredient-paprika" #rx"\\bsmoked\\s+paprika\\b"))
(define rg-parsley (list "ingredient-parsley" #rx"\\b(?:fresh\\s+)?flat-leaf\\s+parsley\\b"))

;; Nuevas unidades agregadas del código 2
(define rg-lbs (list "lbs" #rx"\\blbs?\\b"))
(define rg-pounds (list "pounds" #rx"\\bpounds?\\b"))
(define rg-lb (list "lb" #rx"\\blb\\b"))
(define rg-inches (list "inches" #rx"\\binches?\\b"))
(define rg-quote-inches (list "inches" #rx"^\"\\b"))
(define rg-in (list "in" #rx"\\bin\\b"))

(define rg-cup (list "cup" #rx"\\bcups?\\b"))
(define rg-teaspoon (list "teaspoon" #rx"\\bteaspoons?\\b"))
(define rg-tablespoon (list "tablespoon" #rx"\\btablespoons?\\b"))
(define rg-ounce (list "ounce" #rx"\\bounces?\\b"))
(define rg-pint (list "pint" #rx"\\bpints?\\b"))
(define rg-dash (list "dash" #rx"\\bdash(?:es)?\\b"))
(define rg-clove (list "clove" #rx"\\bcloves?\\b"))
(define rg-large (list "large" #rx"\\blarge\\b"))
(define rg-to-taste (list "to-taste" #rx"\\bto\\s+taste\\b"))
(define rg-for-dusting (list "for-dusting" #rx"\\bfor\\s+dusting\\b"))
(define rg-gram (list "gram" #rx"^grams?\\b"))

;; Other words 
(define rg-serves (list "serves-amt" #rx"^(?:Serves\\s*-\\s*|Servings\\s*-\\s*)[0-9]+"))

(define rg-temp-c (list "temp-C" #rx"^[0-9]+°C"))
(define rg-temp-f (list "temp-F" #rx"^[0-9]+°F"))

(define rg-temp-f-range (list "temp-f-range" #rx"^[0-9]+°F\\s*-\\s*[0-9]+°F")) 
(define rg-temp-c-range (list "temp-c-range" #rx"^[0-9]+°C\\s*-\\s*[0-9]+°C")) 

(define rg-pt (list "prep-t" #rx"^Prep Time\\:\\s*[0-9]+\\s*(?:mins|minutes)"))
(define rg-ct (list "cook-t" #rx"^Cook Time\\:\\s*[0-9]+\\s*(?:mins|minutes)"))
(define rg-tt (list "total-t" #rx"^Total Time\\:\\s*[0-9]+\\s*(?:mins|minutes)"))

(define rg-step-num (list "step-num" #rx"^[0-9]+\\."))
(define rg-fract-in (list "fract-in" #rx"[0-9]+/[0-9]+\""))

;; Keywords 
(define rg-equip (list "kw-equip" #rx"^Equipment(?:\\:)*"))
(define rg-category (list "kw-category" #rx"^Category(?:\\:)*"))
(define rg-author (list "kw-author" #rx"^Author(?:\\:)*"))
(define rg-ingredients (list "kw-ingredient" #rx"^Ingredients(?:\\:)*"))
(define rg-instruct (list "kw-instruct" #rx"^Instructions"))

(define rg-dash-symbol (list "dash" #rx"^[-]"))
(define rg-time-range (list "time-range" #rx"^[0-9]+\\sto\\s[0-9]+\\sminutes"))
(define rg-time-dash (list "time-range" #rx"^[0-9]+\\-minute"))
(define rg-time-dash-range (list "time-range" #rx"^[0-9]+\\-[0-9]+\\s+minutes?"))

(define rg-time-mention (list "time-mention" #rx"^[0-9]+\\–*\\s*(?:minutes|minute)"))
(define rg-8x8 (list "w" #rx"^[0-9]x[0-9]"))

;; Just stores words bcs it's annoying to deal w a lot of floating tokens
(define rg-catch (list "w" #rx"[a-zA-Z]+"))

;; Dictionary of numbers
(define dict-recipe (list
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
                  rg-temp-f-range rg-temp-c-range ; First to check for ranges
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
                  rg-dash-symbol
                  rg-8x8
                  ))

;; Conversiones de ingredientes (diccionario nuevo como solicitas)
(define ingredient-conversions
  (hash "ingredient-sugar" (hash "cup-to-grams" 200.8634 "tsp-to-grams" 4.184 "tbsp-to-grams" 12.554)
        "ingredient-flour" (hash "cup-to-grams" 125.1552 "tsp-to-grams" 2.607 "tbsp-to-grams" 7.822)
        "ingredient-cocoa" (hash "cup-to-grams" 151.6531 "tsp-to-grams" 3.159 "tbsp-to-grams" 9.478)
        "ingredient-powdered-sugar" (hash "cup-to-grams" 120.8966 "tsp-to-grams" 2.519 "tbsp-to-grams" 7.556)
        "ingredient-chocolate" (hash "cup-to-grams" 187.3779 "tsp-to-grams" 3.904 "tbsp-to-grams" 11.711)
        "ingredient-salt" (hash "cup-to-grams" 284.1425 "tsp-to-grams" 5.920 "tbsp-to-grams" 17.759)
        "ingredient-eggs" (hash "cup-to-grams" 246.0518 "tsp-to-grams" 5.126 "tbsp-to-grams" 15.378)
        "ingredient-oil" (hash "cup-to-grams" 217.6612 "tsp-to-grams" 4.534 "tbsp-to-grams" 13.604)
        "ingredient-water" (hash "cup-to-grams" 236.5882 "tsp-to-grams" 4.929 "tbsp-to-grams" 14.787)
        "ingredient-vanilla" (hash "cup-to-grams" 207.9847 "tsp-to-grams" 4.333 "tbsp-to-grams" 12.999)
        "ingredient-baking-powder" (hash "cup-to-grams" 212.9294 "tsp-to-grams" 4.436 "tbsp-to-grams" 13.308)
        "ingredient-lemon-zest" (hash "cup-to-grams" 97.0012 "tsp-to-grams" 2.021 "tbsp-to-grams" 6.063)
        "ingredient-lemon-juice" (hash "cup-to-grams" 314.6624 "tsp-to-grams" 6.555 "tbsp-to-grams" 19.666)
        "ingredient-pasta" (hash "cup-to-grams" 89.9035 "tsp-to-grams" 1.873 "tbsp-to-grams" 5.619)
        "ingredient-butter" (hash "cup-to-grams" 226.8881 "tsp-to-grams" 4.727 "tbsp-to-grams" 14.181)
        "ingredient-cream" (hash "cup-to-grams" 235.1687 "tsp-to-grams" 4.900 "tbsp-to-grams" 14.698)
        "ingredient-pepper" (hash "cup-to-grams" 115.93 "tsp-to-grams" 2.415 "tbsp-to-grams" 7.246)
        "ingredient-garlic-salt" (hash "cup-to-grams" 75.7082 "tsp-to-grams" 1.577 "tbsp-to-grams" 4.732)
        "ingredient-romano" (hash "cup-to-grams" 82.8059 "tsp-to-grams" 1.725 "tbsp-to-grams" 5.175)
        "ingredient-parmesan" (hash "cup-to-grams" 120.66 "tsp-to-grams" 2.514 "tbsp-to-grams" 7.541)
        "ingredient-vinegar" (hash "cup-to-grams" 236.59 "tsp-to-grams" 4.929 "tbsp-to-grams" 14.787)
        "ingredient-garlic" (hash "cup-to-grams" 113.97 "tsp-to-grams" 2.374 "tbsp-to-grams" 7.123)
        "ingredient-oregano" (hash "cup-to-grams" 47.32 "tsp-to-grams" 0.986 "tbsp-to-grams" 2.958)
        "ingredient-paprika" (hash "cup-to-grams" 134.8553 "tsp-to-grams" 2.810 "tbsp-to-grams" 8.428)
        "ingredient-parsley" (hash "cup-to-grams" 61.5129 "tsp-to-grams" 1.282 "tbsp-to-grams" 3.845)
        "ingredient-steaks" (hash "lb-to-grams" 453.592 "oz-to-grams" 28.3495)
        "ingredient-new-york-strip-steaks" (hash "lb-to-grams" 453.592 "oz-to-grams" 28.3495)
        "ingredient-ribeye" (hash "lb-to-grams" 453.592 "oz-to-grams" 28.3495)
        "ingredient-top-sirloin-steaks" (hash "lb-to-grams" 453.592 "oz-to-grams" 28.3495)
        "ingredient-black-pepper" (hash "cup-to-grams" 115.93 "tsp-to-grams" 2.415 "tbsp-to-grams" 7.246 "lb-to-grams" 453.592)
        "ingredient-unsalted-butter" (hash "cup-to-grams" 226.8881 "tsp-to-grams" 4.727 "tbsp-to-grams" 14.181 "lb-to-grams" 453.592)
        "ingredient-fresh-rosemary" (hash "cup-to-grams" 58.2 "tsp-to-grams" 1.213 "tbsp-to-grams" 3.638 "lb-to-grams" 453.592)
        "ingredient-rosemary" (hash "cup-to-grams" 58.2 "tsp-to-grams" 1.213 "tbsp-to-grams" 3.638 "lb-to-grams" 453.592)
        "ingredient-ground-almonds" (hash "cup-to-grams" 95.0 "tsp-to-grams" 1.979 "tbsp-to-grams" 5.938 "lb-to-grams" 453.592)))

;; Calorías por 100g de ingrediente (diccionario nuevo como solicitas)
(define IngCal100
  (hash "ingredient-granulated-sugar" 400
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
        "ingredient-ground-almonds" 579))

;; Helper for safely getting hash values
(define (hash-ref-safe h key default)
  (if (hash-has-key? h key)
      (hash-ref h key)
      default))

;; Lista de unidades que NO deben convertirse a cups
(define non-volume-units
  (set "large" "medium" "small" "clove" "cloves" "piece" "pieces"))
(define non-tsp-units
  (set "in" "inches" "fract-in" "cm" "lb" "lbs" "pounds"))

;; Utility functions
(define (should-convert-to-volume? unit-key target-unit)
  (cond
    ;; Si el sistema es métrico, convertir todo excepto unidades no-volumétricas
    [(equal? target-unit "user-metric")
     (not (set-member? non-volume-units unit-key))]
    
    [(member target-unit '("user-cup" "user-teaspoon" "user-tablespoon"))
     (and (not (set-member? non-volume-units unit-key))
          (not (set-member? non-tsp-units unit-key)))]
    ;; Para otros sistemas, mantener lógica original
    [else
     (not (set-member? non-volume-units unit-key))]))

;; FILE READING
;; Function to read file line by line
(define (read-file-lines file-path)
  (if (file-exists? file-path)
      (file->lines file-path)
      '()))

;; TOKENIZACIÓN
;; Finds all matches for item in regex given input text and corresponding dictionary
(define (all-matches input-text rg-dict)
  ;; Keep only definitions that are not false 
  (filter (lambda (match-result) (not (equal? match-result #f)))
          ;; And now compare against every item in the specified dictionary
          (map (lambda (regex-item)
                 (let* ([token-name (first regex-item)] ; Gets the name of that token
                        [rg-pattern (second regex-item)] ; Regex pattern
                        [matched-result (regexp-match-positions rg-pattern input-text)]) ; Match position and text
                   
                   ;; If found a match
                   (if matched-result
                       ;; Check if the match starts at position 0
                       (if (equal? 0 (caar matched-result))
                           (let ([matched-txt (regexp-match rg-pattern input-text)])
                             (list token-name (first matched-txt)))
                           #f)
                       ;; Else just returns false 
                       #f)))
               rg-dict)))

;; Uses text from tokens to find which one is longest 
(define (get-max-len match-list)
  (if (empty? match-list)
      0
      (apply max (map (lambda (match) 
                        (let ([second-elem (second match)])
                          (if (string? second-elem)
                              (string-length second-elem)
                              0))) 
                      match-list))))

;; Uses item matched from list and keeps only the one that meets the longest length found  
(define (filter-max matches max-len)
  (filter (lambda (match) (equal? (string-length (second match)) max-len)) matches))

;; Finds the longest matched token of the current item being read
(define (longest-match input-item rg-dict)
  (let ([all-found-matches (all-matches input-item rg-dict)])
    (cond
      ;; Did not find a match, returns an unrecognized symbol
      [(empty? all-found-matches) (list "NA" (substring input-item 0 1))]
      
      ;; Only one match, just returns that one 
      [(equal? 1 (length all-found-matches)) (first all-found-matches)]
      
      ;; Else finds the longest match of those found 
      [else
       ;; Finds longest match 
       (let* ([longest-len (get-max-len all-found-matches)]
              [longest-matches (filter-max all-found-matches longest-len)])
         ;; Once found, body just returns the longest one
         (first longest-matches))])))

;; Helper function for safe string operations
(define (safe-substring str start [end #f])
  (cond
    [(>= start (string-length str)) ""]
    [(and end (> end (string-length str))) (substring str start)]
    [end (substring str start end)]
    [else (substring str start)]))

;; Keeps the longest match
(define (tokenize input rg-dict)
  ;; Base case input empty
  (cond
    [(equal? 0 (string-length (string-trim input)))
     '()] ; Returns done list
    [else 
     ;; List of token and matched substring 
     (let* ([input-trim (string-trim input)] ; Para hacer llamadas, ya quita los empty spaces
            [type-txtmatch (longest-match input-trim rg-dict)]
            [extracted-txt (second type-txtmatch)] ; Finds the token text
            [length (string-length extracted-txt)]) ; Counts how long the token text is
       
       (let* ([pos-match-start (let ([pos-result (regexp-match-positions (regexp-quote extracted-txt) input-trim)])
                                 (if pos-result (caar pos-result) 0))]
              [rest-input (cond
                            [(equal? "NA" (first type-txtmatch))
                             ;; NA case: longest-match already took 1 char, so skip 1 char
                             (string-trim (safe-substring input-trim 1))]
                            [else
                             ;; Match found: skip past the entire matched text
                             (string-trim (safe-substring input-trim (+ pos-match-start length)))])])
         ;; Build list 
         (cons (list (first type-txtmatch) extracted-txt)
               (tokenize rest-input rg-dict))))]))

;; Continue with the conversion of the next portion...

;; RECIPE HANDLING
;; Receives the chunk (aka the list of file paths) 
(define (process-recipe file-path)
  (let* ([raw-lines (read-file-lines file-path)]
         [recipe-lines (map list raw-lines)]
         [tokenized-lines (map (lambda (current-line)
                                 ;; Processes line recipe if it is not empty 
                                 (if (not (equal? "" (string-trim (first current-line))))
                                     (tokenize (first current-line) dict-recipe) ; Tokenize call
                                     '())) ; List is empty
                               recipe-lines)])
    ;; Returns list w file name, original lines just in case, and tokenized lines 
    (printf "Tokenized lines: ~a~n" tokenized-lines)
    (list file-path recipe-lines tokenized-lines)))

;; Receives a chunk of recipes and then tokenizes the recipe
(define (process-chunk chunk)
  (printf "Processing: ~a~n" chunk)
  (map process-recipe chunk))

;; SUB-FUNCTIONS FOR RECIPE CONVERSION
;; Extrae val numerico de temp given  
(define (extract-num-value num-string)
  (let ([numeric-match (regexp-match #rx"\\d+" num-string)])
    ;; Returns num if it did match, 0 if it did not find the number
    (if numeric-match (string->number (first numeric-match)) 0)))

;; Converts far to cel and returns it as a token C = (F - 32) / (9 / 5)
(define (f-to-c f-temp-string)
  (let* ([f-value (extract-num-value f-temp-string)]
         [c-value (/ (* (- f-value 32) 5) 9.0)]) ; Plugs into eq
    (list "temp-C" (string-append (number->string c-value) "°C"))))

;; Converts cel to far and returns it as a token F = (C * (9 / 5)) +32
(define (c-to-f c-temp-string)
  (let* ([c-value (extract-num-value c-temp-string)]
         [f-value (+ (* c-value (/ 9 5)) 32)]) ; Plugs into eq
    (list "temp-F" (string-append (number->string f-value) "°F"))))

;; Converts int or fraction to a numeric val
(define (numToInt int-str)
  (cond
    [(string-contains? int-str "/")
     (let ([parts (string-split int-str "/")])
       (/ (string->number (first parts)) (string->number (second parts))))]
    [else (string->number int-str)]))

;; Converts a mixed fraction to a number
(define (mixedFrac mixed-frac)
  (let ([parts (string-split mixed-frac " ")])
    (+ (string->number (first parts)) (numToInt (second parts)))))

;; Simple placeholder for main - we'll implement the rest iteratively
(define (main options-file num-threads)
  ;; Read options file and save user preferences 
  (define options-path (string-append "options/" options-file))
  (printf "~n-------FILE PATH: ~a~n" options-path)
  
  (define file-lines (read-file-lines options-path))
  (printf "~n-------USER PREFERENCES~n~a~n" file-lines)
  
  (printf "~n-------LINE BY LINE~n")
  (define opt-lines (map list file-lines))
  (printf "~a~n" opt-lines)
  
  (define opt-tokenized
    (map (lambda (current-line)
           (tokenize (first current-line) dict-user))
         opt-lines))
  
  (printf "~n-------TOKENIZED~n")
  (printf "~a~n" opt-tokenized)
  
  ;; Read recipes
  (printf "~n-------READ RECIPES~n")
  (printf "Procesando con archivo de opciones: ~a~n" options-file)
  (printf "Número de threads: ~a~n" num-threads)
  
  ;; Define recipe paths
  (define ruta (list "recipes/Best Homemade Brownies-1.txt"
                     "recipes/Chimichurri Sauce.txt"))
  
  ;; Adjust number of threads to avoid empty partitions
  (define n-threads-ajustado (min num-threads (length ruta)))
  (define chunk-size (max 1 (exact-floor (/ (length ruta) n-threads-ajustado))))
  
  ;; Divide recipes into chunks containing file names
  (define (chunk-list lst chunk-size)
    (if (< (length lst) chunk-size)
        (list lst)
        (cons (take lst chunk-size)
              (chunk-list (drop lst chunk-size) chunk-size))))
  (define data-chunks (chunk-list ruta chunk-size))
  
  (printf "CHUNKS: ~a~n" data-chunks)
  
  ;; Measure execution time and process in parallel
  (printf "~n-------TOTAL TIME~n")
  (define exec-time 
    (time
     (begin
       ;; Process the recipes
       (define recipes-processed
         (append* (map process-chunk data-chunks)))
       ;; Print the tokens
       (map (lambda (x) (printf "~a~n~nNext Recipe Tokens:~n~n" (list-ref x 2))) recipes-processed))))
  
  (printf "Processing completed~n"))

;; Test the mixed fraction converter
(printf "Mixed convert: ~a~n" (mixedFrac "1 1/2"))

;; Main function calls
;; (main "options1.txt" 1)
(main "options2.txt" 2)
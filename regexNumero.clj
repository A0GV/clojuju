;; ; Regex para números enteros
;; (def rg-nums-int (list "number-integer" #"^[0-9]+\b"))

;; ; Regex para fracciones simples
;; (def rg-nums-frac (list "number-fraction" #"^[0-9]+/[0-9]+\b"))

;; ; Regex para fracciones mixtas
;; (def rg-nums-mixed (list "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+\b"))


;; ; Regex para ingredientes (case sensitive)
;; (def rg-sugar (list "ingredient-sugar" #"\b(?:granulated\s+)?sugar\b"))
;; (def rg-flour (list "ingredient-flour" #"\b(?:all-purpose\s+|almond\s+)?flour\b"))
;; (def rg-cocoa (list "ingredient-cocoa" #"\bcocoa\s+powder\b"))
;; (def rg-powdered-sugar (list "ingredient-powdered-sugar" #"\bpowdered\s+sugar\b"))
;; (def rg-chocolate (list "ingredient-chocolate" #"\bdark\s+chocolate\s+chips\b"))
;; (def rg-salt (list "ingredient-salt" #"\b(?:sea\s+|kosher\s+)?salt\b"))
;; (def rg-eggs (list "ingredient-eggs" #"\beggs?\b"))
;; (def rg-oil (list "ingredient-oil" #"\b(?:canola\s+|extra-virgin\s+olive\s+)?oil\b"))
;; (def rg-water (list "ingredient-water" #"\bwater\b"))
;; (def rg-vanilla (list "ingredient-vanilla" #"\bvanilla(?:\s+extract)?\b"))
;; (def rg-baking-powder (list "ingredient-baking-powder" #"\bbaking\s+powder\b"))
;; (def rg-lemon-zest (list "ingredient-lemon-zest" #"\blemon\s+zest(?:\s+\(grated\))?\b"))
;; (def rg-lemon-juice (list "ingredient-lemon-juice" #"\b(?:fresh\s+)?lemon\s+juice\b"))
;; (def rg-pasta (list "ingredient-pasta" #"\b(?:dry\s+)?fettuccine\s+pasta\b"))
;; (def rg-butter (list "ingredient-butter" #"\bbutter\b"))
;; (def rg-cream (list "ingredient-cream" #"\bheavy\s+cream\b"))
;; (def rg-pepper (list "ingredient-pepper" #"\b(?:red\s+pepper\s+flakes|pepper)\b"))
;; (def rg-garlic-salt (list "ingredient-garlic-salt" #"\bgarlic\s+salt\b"))
;; (def rg-romano (list "ingredient-romano" #"\bgrated\s+romano\s+cheese\b"))
;; (def rg-parmesan (list "ingredient-parmesan" #"\bgrated\s+parmesan\s+cheese\b"))
;; (def rg-vinegar (list "ingredient-vinegar" #"\bwhite\s+wine\s+vinegar\b"))
;; (def rg-garlic (list "ingredient-garlic" #"\bgarlic\s+clove(?:\s+\(minced\))?\b"))
;; (def rg-oregano (list "ingredient-oregano" #"\bdried\s+oregano\b"))
;; (def rg-paprika (list "ingredient-paprika" #"\bsmoked\s+paprika\b"))
;; (def rg-parsley (list "ingredient-parsley" #"\b(?:fresh\s+)?flat-leaf\s+parsley\b"))

;; (def rg-cup (list "cup" #"\bcups?\b"))
;; (def rg-teaspoon(list "teaspoon" #"\bteaspoon?\b"))
;; (def rg-tablespoon(list "tablespoon" #"\btablespoon?\b"))
;; (def rg-cup (list "cup" #"\bcups?\b"))
;; (def rg-teaspoon (list "teaspoon" #"\bteaspoons?\b"))
;; (def rg-tablespoon (list "tablespoon" #"\btablespoons?\b"))
;; (def rg-ounce (list "ounce" #"\bounces?\b"))
;; (def rg-pint (list "pint" #"\bpints?\b"))
;; (def rg-dash (list "dash" #"\bdash(?:es)?\b"))
;; (def rg-clove (list "clove" #"\bcloves?\b"))
;; (def rg-large (list "large" #"\blarge\b"))
;; (def rg-to-taste (list "to-taste" #"\bto\s+taste\b"))
;; (def rg-for-dusting (list "for-dusting" #"\bfor\s+dusting\b"))


; Diccionario para números
(def numbers-dict
  {"number-integer" #"^[0-9]+\b"
   "number-fraction" #"^[0-9]+/[0-9]+\b"
   "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+\b"})

; Diccionario para mediciones
(def measurements-dict
  {"cup" #"\bcups?\b"
   "teaspoon" #"\bteaspoons?\b"
   "tablespoon" #"\btablespoons?\b"
   "ounce" #"\bounces?\b"
   "pint" #"\bpints?\b"
   "dash" #"\bdash(?:es)?\b"
   "clove" #"\bcloves?\b"
   "large" #"\blarge\b"
   "to-taste" #"\bto\s+taste\b"
   "for-dusting" #"\bfor\s+dusting\b"})

; Diccionario para ingredientes
(def ingredients-dict
  {"ingredient-sugar" #"\b(?:granulated\s+)?sugar\b"
   "ingredient-flour" #"\b(?:all-purpose\s+|almond\s+)?flour\b"
   "ingredient-cocoa" #"\bcocoa\s+powder\b"
   "ingredient-powdered-sugar" #"\bpowdered\s+sugar\b"
   "ingredient-chocolate" #"\bdark\s+chocolate\s+chips\b"
   "ingredient-salt" #"\b(?:sea\s+|kosher\s+)?salt\b"
   "ingredient-eggs" #"\beggs?\b"
   "ingredient-oil" #"\b(?:canola\s+|extra-virgin\s+olive\s+)?oil\b"
   "ingredient-water" #"\bwater\b"
   "ingredient-vanilla" #"\bvanilla(?:\s+extract)?\b"
   "ingredient-baking-powder" #"\bbaking\s+powder\b"
   "ingredient-lemon-zest" #"\blemon\s+zest(?:\s+\(grated\))?\b"
   "ingredient-lemon-juice" #"\b(?:fresh\s+)?lemon\s+juice\b"
   "ingredient-pasta" #"\b(?:dry\s+)?fettuccine\s+pasta\b"
   "ingredient-butter" #"\bbutter\b"
   "ingredient-cream" #"\bheavy\s+cream\b"
   "ingredient-pepper" #"\b(?:red\s+pepper\s+flakes|pepper)\b"
   "ingredient-garlic-salt" #"\bgarlic\s+salt\b"
   "ingredient-romano" #"\bgrated\s+romano\s+cheese\b"
   "ingredient-parmesan" #"\bgrated\s+parmesan\s+cheese\b"
   "ingredient-vinegar" #"\bwhite\s+wine\s+vinegar\b"
   "ingredient-garlic" #"\bgarlic\s+clove(?:\s+\(minced\))?\b"
   "ingredient-oregano" #"\bdried\s+oregano\b"
   "ingredient-paprika" #"\bsmoked\s+paprika\b"
   "ingredient-parsley" #"\b(?:fresh\s+)?flat-leaf\s+parsley\b"})

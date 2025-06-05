; Regex para números enteros
(def rg-nums-int (list "number-integer" #"^[0-9]+\b"))

; Regex para fracciones simples
(def rg-nums-frac (list "number-fraction" #"^[0-9]+/[0-9]+\b"))

; Regex para fracciones mixtas
(def rg-nums-mixed (list "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+\b"))

(def rg-sugar (list "ingredient-sugar" #"^(?:granulated\s+)?sugar\b"))
(def rg-flour (list "ingredient-flour" #"^(?:all-purpose\s+|almond\s+)?flour\b"))
(def rg-cocoa (list "ingredient-cocoa" #"^cocoa\s+powder\b"))
(def rg-powdered-sugar (list "ingredient-powdered-sugar" #"^powdered\s+sugar\b"))
(def rg-chocolate (list "ingredient-chocolate" #"^dark\s+chocolate\s+chips\b"))
(def rg-salt (list "ingredient-salt" #"^(?:sea\s+|kosher\s+)?salt\b"))
(def rg-eggs (list "ingredient-eggs" #"^eggs?\b"))
(def rg-oil (list "ingredient-oil" #"^(?:(?:canola|vegetable|olive|coconut|avocado)\s+)?oil\b"))
(def rg-water (list "ingredient-water" #"^water\b"))
(def rg-vanilla (list "ingredient-vanilla" #"^vanilla(?:\s+extract)?\b"))
(def rg-baking-powder (list "ingredient-baking-powder" #"^baking\s+powder\b"))
(def rg-lemon-zest (list "ingredient-lemon-zest" #"^lemon\s+zest(?:\s+\(grated\))?\b"))
(def rg-lemon-juice (list "ingredient-lemon-juice" #"^(?:fresh\s+)?lemon\s+juice\b"))
(def rg-pasta (list "ingredient-pasta" #"^(?:dry\s+)?fettuccine\s+pasta\b"))
(def rg-butter (list "ingredient-butter" #"^butter\b"))
(def rg-cream (list "ingredient-cream" #"^heavy\s+cream\b"))
(def rg-pepper (list "ingredient-pepper" #"^(?:red\s+pepper\s+flakes|pepper)\b"))
(def rg-garlic-salt (list "ingredient-garlic-salt" #"^garlic\s+salt\b"))
(def rg-romano (list "ingredient-romano" #"^grated\s+romano\s+cheese\b"))
(def rg-parmesan (list "ingredient-parmesan" #"^grated\s+parmesan\s+cheese\b"))
(def rg-vinegar (list "ingredient-vinegar" #"^white\s+wine\s+vinegar\b")) ^(def rg-garlic (list "ingredient-garlic" #"^garlic\s+clove(?:\s+\(minced\))?\b"))
(def rg-oregano (list "ingredient-oregano" #"^dried\s+oregano\b"))
(def rg-paprika (list "ingredient-paprika" #"^smoked\s+paprika\b"))
(def rg-parsley (list "ingredient-parsley" #"^(?:fresh\s+)?flat-leaf\s+parsley\b"))

(def rg-cup (list "cup" #"^cups?\b"))
(def rg-teaspoon (list "teaspoon" #"^teaspoons?\b"))
(def rg-tablespoon (list "tablespoon" #"^tablespoons?\b"))
(def rg-ounce (list "ounce" #"^ounces?\b"))
(def rg-pint (list "pint" #"^pints?\b"))
(def rg-dash (list "dash" #"^dash(?:es)?\b"))
(def rg-clove (list "clove" #"^cloves?\b"))
(def rg-large (list "large" #"^large\b"))
(def rg-to-taste (list "to-taste" #"^to\s+taste\b"))
(def rg-for-dusting (list "for-dusting" #"^for\s+dusting\b"))

;; Dictionary of numbers
(def dict-numbers (list
                   rg-nums-int
                   rg-nums-frac
                   rg-nums-mixed))

;; Dictionary of ingredients
(def dict-ingredients (list
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
                       rg-parsley))

;; Dictionary of units
(def dict-units (list
                 rg-cup
                 rg-teaspoon
                 rg-tablespoon
                 rg-ounce
                 rg-pint
                 rg-dash
                 rg-clove
                 rg-large
                 rg-to-taste
                 rg-for-dusting))
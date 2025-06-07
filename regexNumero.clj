; Regex para números enteros
(def rg-nums-int (list "number-integer" #"^[0-9]+\b"))

; Regex para fracciones simples
(def rg-nums-frac (list "number-fraction" #"^[0-9]+/[0-9]+\b"))

; Regex para fracciones mixtas
(def rg-nums-mixed (list "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+\b"))

(def rg-granulated-sugar (list "ingredient-granulated-sugar" #"^granulated\s+sugar\b"))
(def rg-sugar (list "ingredient-sugar" #"^sugar\b"))
(def rg-all-purpose-flour (list "ingredient-all-purpose-flour" #"^all-purpose\s+flour\b"))
(def rg-almond-flour (list "ingredient-almond-flour" #"^almond\s+flour\b"))
(def rg-flour (list "ingredient-flour" #"^flour\b"))
(def rg-cocoa-powder (list "ingredient-cocoa-powder" #"^cocoa\s+powder\b"))
(def rg-powdered-sugar (list "ingredient-powdered-sugar" #"^powdered\s+sugar\b"))
(def rg-dark-chocolate-chips (list "ingredient-dark-chocolate-chips" #"^dark\s+chocolate\s+chips\b"))
(def rg-sea-salt (list "ingredient-sea-salt" #"^sea\s+salt\b"))
(def rg-kosher-salt (list "ingredient-kosher-salt" #"^kosher\s+salt\b"))
(def rg-salt (list "ingredient-salt" #"^salt\b"))
(def rg-eggs (list "ingredient-eggs" #"^eggs?\b"))
(def rg-canola-oil (list "ingredient-canola-oil" #"^canola\s+oil\b"))
(def rg-extra-virgin-olive-oil (list "ingredient-extra-virgin-olive-oil" #"^extra-virgin\s+olive\s+oil\b"))
(def rg-olive-oil (list "ingredient-olive-oil" #"^olive\s+oil\b"))
(def rg-oil (list "ingredient-oil" #"^oil\b"))
(def rg-water (list "ingredient-water" #"^water\b"))
(def rg-vanilla-extract (list "ingredient-vanilla-extract" #"^vanilla\s+extract\b"))
(def rg-vanilla (list "ingredient-vanilla" #"^vanilla\b"))
(def rg-baking-powder (list "ingredient-baking-powder" #"^baking\s+powder\b"))
(def rg-lemon-zest-grated (list "ingredient-lemon-zest-grated" #"^lemon\s+zest\s+\(grated\)\b"))
(def rg-lemon-zest (list "ingredient-lemon-zest" #"^lemon\s+zest\b"))
(def rg-fresh-lemon-juice (list "ingredient-fresh-lemon-juice" #"^fresh\s+lemon\s+juice\b"))
(def rg-lemon-juice (list "ingredient-lemon-juice" #"^lemon\s+juice\b"))
(def rg-dry-fettuccine-pasta (list "ingredient-dry-fettuccine-pasta" #"^dry\s+fettuccine\s+pasta\b"))
(def rg-fettuccine-pasta (list "ingredient-fettuccine-pasta" #"^fettuccine\s+pasta\b"))
(def rg-pasta (list "ingredient-pasta" #"^pasta\b"))
(def rg-butter (list "ingredient-butter" #"^butter\b"))
(def rg-heavy-cream (list "ingredient-heavy-cream" #"^heavy\s+cream\b"))
(def rg-cream (list "ingredient-cream" #"^cream\b"))
(def rg-pepper (list "ingredient-pepper" #"^pepper\b"))
(def rg-red-pepper-flakes (list "ingredient-red-pepper-flakes" #"^red\s+pepper\s+flakes\b"))
(def rg-garlic-salt (list "ingredient-garlic-salt" #"^garlic\s+salt\b"))
(def rg-grated-romano-cheese (list "ingredient-grated-romano-cheese" #"^grated\s+romano\s+cheese\b"))
(def rg-romano-cheese (list "ingredient-romano-cheese" #"^romano\s+cheese\b"))
(def rg-grated-parmesan-cheese (list "ingredient-grated-parmesan-cheese" #"^grated\s+parmesan\s+cheese\b"))
(def rg-parmesan-cheese (list "ingredient-parmesan-cheese" #"^parmesan\s+cheese\b"))
(def rg-white-wine-vinegar (list "ingredient-white-wine-vinegar" #"^white\s+wine\s+vinegar\b"))
(def rg-vinegar (list "ingredient-vinegar" #"^vinegar\b"))
(def rg-garlic-clove-minced (list "ingredient-garlic-clove-minced" #"^garlic\s+clove\s+\(minced\)\b"))
(def rg-garlic-clove (list "ingredient-garlic-clove" #"^garlic\s+clove\b"))
(def rg-garlic (list "ingredient-garlic" #"^garlic\b"))
(def rg-dried-oregano (list "ingredient-dried-oregano" #"^dried\s+oregano\b"))
(def rg-oregano (list "ingredient-oregano" #"^oregano\b"))
(def rg-smoked-paprika (list "ingredient-smoked-paprika" #"^smoked\s+paprika\b"))
(def rg-paprika (list "ingredient-paprika" #"^paprika\b"))
(def rg-fresh-flat-leaf-parsley (list "ingredient-fresh-flat-leaf-parsley" #"^fresh\s+flat-leaf\s+parsley\b"))
(def rg-flat-leaf-parsley (list "ingredient-flat-leaf-parsley" #"^flat-leaf\s+parsley\b"))
(def rg-parsley (list "ingredient-parsley" #"^parsley\b"))

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
                       rg-olive-oil
                       rg-vanilla-extract
                       rg-lemon-zest-grated
                       rg-lemon-zest
                       rg-fresh-lemon-juice
                       rg-lemon-juice
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
                       rg-garlic-clove
                       rg-dried-oregano
                       rg-smoked-paprika
                       rg-fresh-flat-leaf-parsley
                       rg-flat-leaf-parsley
                       rg-sugar
                       rg-flour
                       rg-salt
                       rg-eggs
                       rg-oil
                       rg-water
                       rg-vanilla
                       rg-baking-powder
                       rg-pasta
                       rg-butter
                       rg-cream
                       rg-pepper
                       rg-garlic-salt
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
   "ingredient-parsley" {:cup-to-grams 61.5129 :tsp-to-grams 1.282 :tbsp-to-grams 3.845}})
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
   "ingredient-parsley" 35.8})

;; (println (get-in ingredient-conversions ["ingredient-sugar" :cup-to-grams]))

(defn calculate-calories [ingredient-token grams]
  (let [calories-per-100g (get IngCal100 ingredient-token 0)]
    (* (/ grams 100.0) calories-per-100g)))


;; (println (calculate-calories "ingredient-flat-leaf-parsley" 50.5))

;; ========================================
;; FUNCIONES PARA PARSEAR LOS NUMEROS
;; ========================================


;; Usa regex para encontrar dígitos y los convierte a entero
(defn numToInt [int-str]
  (read-string int-str))

(defn mixedFrac [mixed-frac]
  (let [parts (clojure.string/split mixed-frac #" ")
        resp (+ (numToInt (first parts)) (numToInt (second parts)))]
    resp))

; Obtiene el factor de conversión para un ingrediente específico
(defn get-conversion [ingredient-key unit-type]
  ;; Busca las conversiones del ingrediente en el mapa ingredient-conversions
  (let [conversions (get ingredient-conversions ingredient-key)]
    ;; Evalúa qué tipo de unidad es y retorna el factor correspondiente
    (cond
      ;; Si es "cup", retorna el factor cup-to-grams del ingrediente
      (= unit-type "cup") (:cup-to-grams conversions)
      ;; Si es "teaspoon", retorna el factor tsp-to-grams del ingrediente
      (= unit-type "teaspoon") (:tsp-to-grams conversions)
      ;; Si es "tablespoon", retorna el factor tbsp-to-grams del ingrediente
      (= unit-type "tablespoon") (:tbsp-to-grams conversions)
      ;; Si no es ninguna unidad conocida, retorna 1.0 como default
      :else 1.0)))

; Convierte de unidades imperiales a gramos
(defn convert-to-grams [amount ingredient-key unit-key]
  ;; Obtiene el factor de conversión llamando a get-conversion
  (let [conversion-factor (get-conversion ingredient-key unit-key)]
    ;; Si existe un factor de conversión, multiplica cantidad por factor
    (if conversion-factor
      (* amount conversion-factor)
      amount))) ; Si no hay conversión, devuelve el valor original

; Busca el primer token de un tipo específico en una línea
(defn find-token-type [token-line token-type]
  ;; Filtra tokens que no sean nil Y cuyo primer elemento sea igual al tipo buscado
  (first (filter (fn [token] (and (not (nil? token)) (= (first token) token-type))) token-line)))

; Busca cualquier token que comience con un prefijo
(defn find-token-by-prefix [token-line prefix]
  ;; Filtra tokens aplicando dos condiciones:
  (first (filter (fn [token]
                   ;; 1. El token no debe ser nil
                   (and (not (nil? token))
                        ;; 2. El primer elemento del token debe empezar con el prefijo
                        (clojure.string/starts-with? (str (first token)) prefix)))
                 token-line)))

(defn process-ingredient-line [token-line]
  ;; Busca diferentes tipos de tokens numéricos (cantidad)
  (let [quantity-token (or (find-token-type token-line "number-integer")
                           (find-token-type token-line "number-fraction")
                           (find-token-type token-line "number-mixed"))
        ;; Busca tokens de unidades de medida
        unit-token (or (find-token-type token-line "cup")
                       (find-token-type token-line "teaspoon")
                       (find-token-type token-line "tablespoon"))
        ;; Busca cualquier token que empiece con "ingredient-"
        ingredient-token (find-token-by-prefix token-line "ingredient-")]

    ;; Verifica si tenemos al menos cantidad e ingrediente
    (if (and quantity-token ingredient-token)
      (let [;; Decide qué función usar según el tipo de token numérico
            quantity (if (= (first quantity-token) "number-mixed")
                       (mixedFrac (second quantity-token))  ; Llama a mixedFrac para fracciones mixtas
                       (numToInt (second quantity-token)))  ; Usa numToInt para otros tipos
            ingredient-key (first ingredient-token)]
        (if unit-token
          (let [unit-key (first unit-token)
                grams (convert-to-grams quantity ingredient-key unit-key)
                calories (calculate-calories ingredient-key grams)]
            ;; Retorna lista simple ("ingredient-sugar" 1.5 "cup" 301.295 1205.18)
            (list ingredient-key quantity unit-key grams calories))
          nil))
      nil)))


(def test-line-1
  (list ["number-mixed" "1"] ["cup" "cups"] ["ingredient-sugar" "granulated sugar"]))

;; ;; Caso 2: Con fracción
;; (def test-line-2 
;;   (list ["number-fraction" "3/4"] ["cup" "cup"] ["ingredient-flour" "all-purpose flour"]))

;; ;; Caso 3: Con teaspoons
;; (def test-line-3 
;;   (list ["number-integer" "2"] ["teaspoon" "teaspoons"] ["ingredient-salt" "sea salt"]))

;; ;; Caso 4: Sin unidad
;; (def test-line-4 
;;   (list ["ingredient-chocolate" "dark chocolate chips"]))

;; ;; Caso 5: Sin ingrediente
;; (def test-line-5 
;;   (list ["number-integer" "2"] ["cup" "cups"]))

;; Ejecutar pruebas
;; (println "=== CASOS DE PRUEBA ===")
;; (println "Caso 1 (completo):" (process-ingredient-line test-line-1))


(def test-number "1 1/2")
(println "String original:" test-number)
(println (mixedFrac test-number))
(println "Convertido con numToInt:" (numToInt test-number))


;; (println "Caso 2 (fracción):" (process-ingredient-line test-line-2))
;; (println "Caso 3 (teaspoons):" (process-ingredient-line test-line-3))
;; (println "Caso 4 (sin unidad):" (process-ingredient-line test-line-4))
;; (println "Caso 5 (sin ingrediente):" (process-ingredient-line test-line-5))
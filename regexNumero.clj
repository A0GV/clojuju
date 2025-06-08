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
(def rg-steaks (list "ingredient-steaks" #"^steaks?\b"))
(def rg-new-york-strip-steaks (list "ingredient-new-york-strip-steaks" #"^new\s+york\s+strip\s+steaks?\b"))
(def rg-ribeye (list "ingredient-ribeye" #"^ribeye\b"))
(def rg-top-sirloin-steaks (list "ingredient-top-sirloin-steaks" #"^top\s+sirloin\s+steaks?\b"))
(def rg-black-pepper (list "ingredient-black-pepper" #"^black\s+pepper\b"))
(def rg-unsalted-butter (list "ingredient-unsalted-butter" #"^unsalted\s+butter\b"))
(def rg-fresh-rosemary (list "ingredient-fresh-rosemary" #"^fresh\s+rosemary\b"))
(def rg-rosemary (list "ingredient-rosemary" #"^rosemary\b"))
(def rg-ground-almonds (list "ingredient-ground-almonds" #"^ground\s+almonds?\b"))



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
(def rg-gram (list "gram" #"^grams?\b"))
(def rg-lbs (list "lbs" #"^lbs?\b"))
(def rg-pounds (list "pounds" #"^pounds?\b"))
(def rg-lb (list "lb" #"^lb\b"))

; Regex para tokens de sistema
(def rg-r-system (list "r-system" #"^r-system\b"))
(def rg-system-metric (list "system-metric" #"^metric\b"))
(def rg-system-imperial (list "system-imperial" #"^imperial\b"))
(def rg-inches (list "inches" #"^inches?\b"))
(def rg-in (list "in" #"^in\b"))
; Regex para tokens de usuario
(def rg-user-metric (list "user-metric" #"^metric\b"))
(def rg-user-cup (list "user-cup" #"^cup\b"))
(def rg-user-teaspoon (list "user-teaspoon" #"^teaspoon\b"))
(def rg-user-tablespoon (list "user-tablespoon" #"^tablespoon\b"))

;; Dictionary of numbers
(def dict-numbers (list
                   rg-nums-mixed
                   rg-nums-frac
                   rg-nums-int))

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
                       rg-new-york-strip-steaks     
                       rg-top-sirloin-steaks        
                       rg-ribeye                    
                       rg-black-pepper              
                       rg-unsalted-butter           
                       rg-fresh-rosemary            
                       rg-ground-almonds            
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
                       rg-parsley
                       rg-steaks                   
                       rg-rosemary))                

;; Dictionary of units
(def dict-units (list
                 rg-cup
                 rg-teaspoon
                 rg-tablespoon
                 rg-ounce
                 rg-pint
                 rg-lbs          
                 rg-pounds       
                 rg-lb           
                 rg-inches       
                 rg-in           
                 rg-dash
                 rg-clove
                 rg-large
                 rg-to-taste
                 rg-for-dusting
                 rg-gram))

;; Dictionary of system tokens
(def dict-system (list
                  rg-r-system
                  rg-system-metric
                  rg-system-imperial
                  rg-user-metric
                  rg-user-cup
                  rg-user-teaspoon
                  rg-user-tablespoon))

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
   ;;Son los nuevos, pero falta verificar que si esten acorde
   "ingredient-steaks" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-new-york-strip-steaks" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-ribeye" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-top-sirloin-steaks" {:lb-to-grams 453.592 :oz-to-grams 28.3495}
   "ingredient-black-pepper" {:cup-to-grams 115.93 :tsp-to-grams 2.415 :tbsp-to-grams 7.246 :lb-to-grams 453.592}
   "ingredient-unsalted-butter" {:cup-to-grams 226.8881 :tsp-to-grams 4.727 :tbsp-to-grams 14.181 :lb-to-grams 453.592}
   "ingredient-fresh-rosemary" {:cup-to-grams 58.2 :tsp-to-grams 1.213 :tbsp-to-grams 3.638 :lb-to-grams 453.592}
   "ingredient-rosemary" {:cup-to-grams 58.2 :tsp-to-grams 1.213 :tbsp-to-grams 3.638 :lb-to-grams 453.592}
   "ingredient-ground-almonds" {:cup-to-grams 95.0 :tsp-to-grams 1.979 :tbsp-to-grams 5.938 :lb-to-grams 453.592}})
   

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
   ;; NUEVOS INGREDIENTES AGREGADOS: Falta confirmar sus calorias
   "ingredient-steaks" 271
   "ingredient-new-york-strip-steaks" 271
   "ingredient-ribeye" 291
   "ingredient-top-sirloin-steaks" 259
   "ingredient-black-pepper" 26
   "ingredient-unsalted-butter" 717
   "ingredient-fresh-rosemary" 131
   "ingredient-rosemary" 131
   "ingredient-ground-almonds" 579
   })


; Función que calcula calorías basándose en gramos de ingrediente
(defn calculate-calories [ingredient-token grams]
  ; Busca las calorías por 100g del ingrediente en el diccionario IngCal100, si no existe devuelve 0
  (let [calories-per-100g (get IngCal100 ingredient-token 0)]
    ; Calcula las calorías: (gramos ÷ 100) × calorías_por_100g
    (* (/ grams 100.0) calories-per-100g)))

;; ========================================
;; FUNCIONES PARA PARSEAR LOS NUMEROS
;; ========================================

; Convierte string a número usando read-string (funciona para enteros y fracciones)
(defn numToInt [int-str]
  (read-string int-str))

(defn mixedFrac [mixed-frac]
  ; Divide el string por espacios: "1 1/2" → ["1", "1/2"], luego los pasa a numero y de ahi los suma
  (let [parts (clojure.string/split mixed-frac #" ")
        resp (+ (numToInt (first parts)) (numToInt (second parts)))]
    resp))





; Clasifica el tipo de unidad para determinar conversiones métricas
(defn get-unit-category [unit-key]
  (cond
    ; Unidades de peso/masa
    (or (= unit-key "lb") (= unit-key "lbs") (= unit-key "pounds") (= unit-key "ounce")) "weight"
    ; Unidades de longitud
    (or (= unit-key "in") (= unit-key "inches")) "length"
    ; Unidades de volumen (líquidos) - se mantienen como están
    (or (= unit-key "cup") (= unit-key "teaspoon") (= unit-key "tablespoon") (= unit-key "pint")) "volume"
    ; Gramos ya son métricos
    (= unit-key "gram") "metric"
    ; Otros casos
    :else "other"))


;; ========================================
;; FUNCIONES PARA CONVERSIONES DE UNIDADES
;; ========================================

; Busca el primer token de un tipo específico en una línea
(defn find-token-type [token-line token-type]
  ; Filtra tokens que no sean nil Y que el primer elemento coincida con el tipo buscado
  (first (filter (fn [token] (and (not (nil? token)) (= (first token) token-type))) token-line)))

; Extrae configuración de conversión de unidades de los tokens
(defn extract-output-config [tokens]
  (let [r-system-token (find-token-type tokens "r-system")
        system-metric-token (find-token-type tokens "system-metric")
        user-tokens (filter #(and (not (nil? %))
                                  (clojure.string/starts-with? (str (first %)) "user-"))
                            tokens)]
    (cond
      ; Si hay r-system Y system-metric → conversión automática a métrico
      (and r-system-token system-metric-token)
      {:system "r-system" :target-unit "auto-metric"}
      
      ; Si hay r-system Y tokens de usuario → conversión específica
      (and r-system-token (not (empty? user-tokens)))
      {:system "r-system" :target-unit (first (first user-tokens))}
      
      ; Si no hay configuración completa
      :else nil)))

; Obtiene la conversión para un ingrediente específico
(defn get-conversion [ingredient-key unit-type]
  ; Busca las conversiones del ingrediente en el diccionario ingredient-conversions
  (let [conversions (get ingredient-conversions ingredient-key)]
    ; Según el tipo de unidad, devuelve el correspondiente
    (cond
      (= unit-type "cup") (:cup-to-grams conversions)           ;cup → gramos
      (= unit-type "teaspoon") (:tsp-to-grams conversions)      ;teaspoon → gramos
      (= unit-type "tablespoon") (:tbsp-to-grams conversions)   ;tablespoon → gramos
      (= unit-type "gram") 1.0                                  ; Si ya está en gramos, 1
      (or (= unit-type "lb") (= unit-type "lbs") (= unit-type "pounds")) (:lb-to-grams conversions)
      (= unit-type "ounce") (:oz-to-grams conversions)
      (= unit-type "pint") 473.176  ; 1 pint = 473.176 ml ≈ 473.176 g para líquidos
      (or (= unit-type "in") (= unit-type "inches")) 1.0
      :else 1.0)))                                              ; Caso por defecto

; Convierte de unidades imperiales a gramos
(defn convert-to-grams [amount ingredient-key unit-key]
  ; Obtiene la conversion específica respecto el ingrediente y unidad
  (let [conversion (get-conversion ingredient-key unit-key)]
    ; Si existe conversión, multiplica cantidad × conversion
    (if conversion
      (* amount conversion)
      amount)))                                                 ; Si no hay, devuelve cantidad original

; Convierte automáticamente a unidades métricas cuando el sistema es métrico 
(defn convert-to-metric [amount unit-key ingredient-key]
  (let [unit-category (get-unit-category unit-key)]
    (cond
      ; Peso: convierte libras/onzas a gramos
      (= unit-category "weight")
      (cond
        (or (= unit-key "lb") (= unit-key "lbs") (= unit-key "pounds"))
        {:amount (* amount 453.592) :unit "gram"}
        (= unit-key "ounce")
        {:amount (* amount 28.3495) :unit "gram"}
        :else {:amount amount :unit unit-key})
      
      ; Longitud: convierte pulgadas a centímetros
      (= unit-category "length")
      (cond
        (or (= unit-key "in") (= unit-key "inches"))
        {:amount (* amount 2.54) :unit "cm"}
        :else {:amount amount :unit unit-key})
      
      ; VOLUMEN: convierte a gramos usando las conversiones específicas del ingrediente
      (= unit-category "volume")
      (let [grams-converted (convert-to-grams amount ingredient-key unit-key)]
        {:amount grams-converted :unit "gram"})
      
      ; Métrico: ya está en gramos
      (= unit-category "metric")
      {:amount amount :unit unit-key}
      
      ; Otros casos: se mantienen como están
      :else {:amount amount :unit unit-key})))

; Convierte de gramos a la unidad de salida especificada
(defn convert-from-grams [grams ingredient-key target-unit]
  ; Obtiene las conversiones disponibles para el ingrediente
  (let [conversions (get ingredient-conversions ingredient-key)]
    ; Según la unidad objetivo, aplica la conversión inversa
    (cond
      (= target-unit "user-metric") grams                       ; Si es métrico, mantiene gramos
      (= target-unit "user-cup")                                ; Si quiere cups
      (if-let [factor (:cup-to-grams conversions)]              ; Busca factor cup-to-grams
        (/ grams factor)                                        ; Convierte: gramos ÷ factor = cups
        grams)                                                  ; Si no hay factor, mantiene gramos
      (= target-unit "user-teaspoon")                           ; Si quiere teaspoons
      (if-let [factor (:tsp-to-grams conversions)]              ; Busca factor tsp-to-grams
        (/ grams factor)                                        ; Convierte: gramos ÷ factor = teaspoons
        grams)                                                  ; Si no hay factor, mantiene gramos
      (= target-unit "user-tablespoon")                         ; Si quiere tablespoons
      (if-let [factor (:tbsp-to-grams conversions)]             ; Busca factor tbsp-to-grams
        (/ grams factor)                                        ; Convierte: gramos ÷ factor = tablespoons
        grams)                                                  ; Si no hay factor, mantiene gramos
      :else grams)))                                            ; Caso por defecto: mantiene gramos

; Convierte el nombre de la unidad de salida para mostrar
(defn get-output-unit-name [target-unit]
  ; Mapea el token de configuración al nombre de unidad para mostrar
  (cond
    (= target-unit "user-metric") "gram"                        ; user-metric → "gram"
    (= target-unit "user-cup") "cup"                           ; user-cup → "cup"
    (= target-unit "user-teaspoon") "teaspoon"                 ; user-teaspoon → "teaspoon"
    (= target-unit "user-tablespoon") "tablespoon"             ; user-tablespoon → "tablespoon"
    :else "gram"))                                              ; Caso por defecto → "gram"

; Busca cualquier token que comience con un prefijo
(defn find-token-by-prefix [token-line prefix]
  ; Filtra tokens que no sean nil Y cuyo primer elemento empiece con el prefijo
  (first (filter (fn [token]
                   (and (not (nil? token))
                        (clojure.string/starts-with? (str (first token)) prefix)))
                 token-line)))

; Función principal que maneja las conversiones de salida - MODIFICADA
(defn process-ingredient-line
  ([token-line output-config]
   (let [quantity-token (or (find-token-type token-line "number-integer")
                            (find-token-type token-line "number-fraction")
                            (find-token-type token-line "number-mixed"))
         unit-token (or (find-token-type token-line "cup")
                        (find-token-type token-line "teaspoon")
                        (find-token-type token-line "tablespoon")
                        (find-token-type token-line "gram")
                        (find-token-type token-line "lb")
                        (find-token-type token-line "lbs")
                        (find-token-type token-line "pounds")
                        (find-token-type token-line "ounce")
                        (find-token-type token-line "pint")
                        (find-token-type token-line "in")
                        (find-token-type token-line "inches"))
         ingredient-token (find-token-by-prefix token-line "ingredient-")]

     (if (and quantity-token ingredient-token)
       (let [quantity (if (= (first quantity-token) "number-mixed")
                        (mixedFrac (second quantity-token))
                        (numToInt (second quantity-token)))
             ingredient-key (first ingredient-token)]
         (if unit-token
           (let [unit-key (first unit-token)]
             
             ; SI EL SISTEMA ES AUTO-METRIC, CONVERTIR AUTOMÁTICAMENTE
             (if (and output-config (= (:target-unit output-config) "auto-metric"))
               ; Conversión automática a métrico - AHORA INCLUYE INGREDIENT-KEY
               (let [metric-conversion (convert-to-metric quantity unit-key ingredient-key)
                     converted-amount (:amount metric-conversion)
                     converted-unit (:unit metric-conversion)
                     ; Para cálculo de calorías, usar gramos convertidos
                     grams (if (= converted-unit "gram")
                             converted-amount
                             (if (= converted-unit "cm")
                               converted-amount  ; Para longitud, usar valor como está
                               (convert-to-grams quantity ingredient-key unit-key)))
                     calories (if (= converted-unit "cm")
                                0  ; Sin calorías para medidas de longitud
                                (calculate-calories ingredient-key grams))]
                 (list ingredient-key converted-amount converted-unit grams calories))
               
               ; COMPORTAMIENTO ORIGINAL (sin auto-metric)
               (let [grams (if (= unit-key "gram")
                             quantity
                             (convert-to-grams quantity ingredient-key unit-key))
                     calories (calculate-calories ingredient-key grams)]
                 (if output-config
                   (let [target-unit (:target-unit output-config)
                         converted-amount (convert-from-grams grams ingredient-key target-unit)
                         output-unit-name (get-output-unit-name target-unit)]
                     (list ingredient-key converted-amount output-unit-name grams calories))
                   (list ingredient-key quantity unit-key grams calories)))))
           nil))
       nil))))
; Función para procesar una lista completa considerando tokens de configuración
(defn process-recipe-with-config [token-lines]
  ; Buscar configuración en la primera línea o en cualquier línea
  (let [config-line (first (filter #(find-token-type % "r-system") token-lines))
        ; Extrae configuración de salida si existe línea de configuración
        output-config (when config-line (extract-output-config config-line))
        ; Filtrar líneas que no son de configuración (tienen ingredientes pero no r-system)
        ingredient-lines (filter #(and (find-token-by-prefix % "ingredient-")
                                       (not (find-token-type % "r-system")))
                                 token-lines)]

    ; Procesar cada línea de ingrediente con la configuración encontrada
    ; filter identity elimina los resultados nil
    (filter identity (map #(process-ingredient-line % output-config) ingredient-lines))))
;; ========================================
;; EJEMPLOS DE USO
;; ========================================

; Ejemplo 1: Sin configuración de salida (comportamiento original)
(def test-line-1
  (list [["number-mixed" "1 1/2"] ["cup" "cups"] ["ingredient-sugar" "granulated sugar"]]
        [["number-fraction" "5/4"] ["cup" "cup"] ["ingredient-flour" "all-purpose flour"]]
        [["number-fraction" "8/4"] ["cup" "cup"] ["ingredient-flour" "all-purpose flour"]]))

; Ejemplo 2: Con configuración para convertir a métrico (gramos)
(def test-with-metric
  (list [["r-system" "r-system"] ["user-metric" "metric"]]
        [["number-integer" "2"] ["cup" "cups"] ["ingredient-sugar" "granulated sugar"]]
        [["number-fraction" "3/4"] ["cup" "cup"] ["ingredient-flour" "all-purpose flour"]]))

; Ejemplo 3: Con configuración para convertir a cups
(def test-with-cups
  (list [["r-system" "r-system"] ["user-cup" "cup"]]
        [["number-integer" "500"] ["gram" "grams"] ["ingredient-sugar" "granulated sugar"]]
        [["number-integer" "250"] ["gram" "grams"] ["ingredient-flour" "all-purpose flour"]]))

; Ejemplo 4: Con configuración para convertir a teaspoons
(def test-with-teaspoons
  (list [["r-system" "r-system"] ["user-teaspoon" "teaspoon"]]
        [["number-fraction" "1/4"] ["cup" "cup"] ["ingredient-sugar" "granulated sugar"]]
        [["number-integer" "2"] ["tablespoon" "tablespoons"] ["ingredient-oil" "canola oil"]]))

; Ejemplo 5: Con configuración para convertir a tablespoons
(def test-with-tablespoons
  (list [["r-system" "r-system"] ["user-tablespoon" "tablespoon"]]
        [["number-integer" "6"] ["teaspoon" "teaspoons"] ["ingredient-vanilla" "vanilla extract"]]
        [["number-fraction" "1/8"] ["cup" "cup"] ["ingredient-oil" "olive oil"]]))

;; Ejecutar pruebas
(println "=== EJEMPLOS DE CONVERSIÓN ===")
(println "Con métrico:" (process-recipe-with-config test-with-metric))
(println "Con cups:" (process-recipe-with-config test-with-cups))
(println "Con teaspoons:" (process-recipe-with-config test-with-teaspoons))
(println "Con tablespoons:" (process-recipe-with-config test-with-tablespoons))


(def test-with-steaks
  (list [["r-system" "r-system"] ["user-metric" "metric"]]
        [["number-integer" "2"] ["lb" "lbs"] ["ingredient-ribeye" "ribeye steaks"]]
        [["number-fraction" "1/4"] ["cup" "cup"] ["ingredient-fresh-rosemary" "fresh rosemary"]]))

(println "Con steaks:" (process-recipe-with-config test-with-steaks))
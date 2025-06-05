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
    rg-all rg-other
))

;; Recetas
; Regex para números enteros
(def rg-nums-int (list "number-integer" #"^[0-9]+\b"))

; Regex para fracciones simples
(def rg-nums-frac (list "number-fraction" #"^[0-9]+/[0-9]+\b"))

; Regex para fracciones mixtas
(def rg-nums-mixed (list "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+\b"))

; Regex para ingredientes (case sensitive)
(def rg-sugar (list "ingredient-sugar" #"\b(?:granulated\s+)?sugar\b"))
(def rg-flour (list "ingredient-flour" #"\b(?:all-purpose\s+|almond\s+)?flour\b"))
(def rg-cocoa (list "ingredient-cocoa" #"\bcocoa\s+powder\b"))
(def rg-powdered-sugar (list "ingredient-powdered-sugar" #"\bpowdered\s+sugar\b"))
(def rg-chocolate (list "ingredient-chocolate" #"\bdark\s+chocolate\s+chips\b"))
(def rg-salt (list "ingredient-salt" #"\b(?:sea\s+|kosher\s+)?salt\b"))
(def rg-eggs (list "ingredient-eggs" #"\beggs?\b"))
(def rg-oil (list "ingredient-oil" #"\b(?:canola\s+|extra-virgin\s+olive\s+)?oil\b"))
(def rg-water (list "ingredient-water" #"\bwater\b"))
(def rg-vanilla (list "ingredient-vanilla" #"\bvanilla(?:\s+extract)?\b"))
(def rg-baking-powder (list "ingredient-baking-powder" #"\bbaking\s+powder\b"))
(def rg-lemon-zest (list "ingredient-lemon-zest" #"\blemon\s+zest(?:\s+\(grated\))?\b"))
(def rg-lemon-juice (list "ingredient-lemon-juice" #"\b(?:fresh\s+)?lemon\s+juice\b"))
(def rg-pasta (list "ingredient-pasta" #"\b(?:dry\s+)?fettuccine\s+pasta\b"))
(def rg-butter (list "ingredient-butter" #"\bbutter\b"))
(def rg-cream (list "ingredient-cream" #"\bheavy\s+cream\b"))
(def rg-pepper (list "ingredient-pepper" #"\b(?:red\s+pepper\s+flakes|pepper)\b"))
(def rg-garlic-salt (list "ingredient-garlic-salt" #"\bgarlic\s+salt\b"))
(def rg-romano (list "ingredient-romano" #"\bgrated\s+romano\s+cheese\b"))
(def rg-parmesan (list "ingredient-parmesan" #"\bgrated\s+parmesan\s+cheese\b"))
(def rg-vinegar (list "ingredient-vinegar" #"\bwhite\s+wine\s+vinegar\b"))
(def rg-garlic (list "ingredient-garlic" #"\bgarlic\s+clove(?:\s+\(minced\))?\b"))
(def rg-oregano (list "ingredient-oregano" #"\bdried\s+oregano\b"))
(def rg-paprika (list "ingredient-paprika" #"\bsmoked\s+paprika\b"))
(def rg-parsley (list "ingredient-parsley" #"\b(?:fresh\s+)?flat-leaf\s+parsley\b"))

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

;(def rg-phrases (list "phrases" #"^[a-zA-Z0-9 ,\.\(\)]+"))

;; Dictionary of numbers
(def dict-recipe (list
                   rg-nums-int
                   rg-nums-frac
                   rg-nums-mixed
                
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

                    rg-cup
                    rg-teaspoon
                    rg-tablespoon
                    rg-ounce
                    rg-pint
                    rg-dash
                    rg-clove
                    rg-large
                    rg-to-taste
                    rg-for-dusting

))

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
                    matched-txt (re-find rg-pattern input-text)]  ; String that was matched (nil if not found)
                    
                    ; If nil, then just returns a false to later filter it out
                    ;(if (nil? matched-txt) false)
                    (if matched-txt
                        ; Did find a match, builds list of token name and 
                        (list token-name matched-txt)
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
            (empty? all-found-matches) (list "No reconocido" (subs input-item 0 1)) 

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
            ; Head de variables 
            type-txtmatch (longest-match (str/trim input) rg-dict)
            ; Builds the substring
            extracted-txt (second type-txtmatch) ; Finds the token text
            length (count extracted-txt) ; Counts how long the token text is

            ; For recursive to start at next element (excluding whitespace)
            rest-input (subs (str/trim input) length)
            ]
            ; Ahora el body para build list 
            (cons 
                (list (first type-txtmatch) extracted-txt)
                (tokenize rest-input rg-dict)
            )
        )
    )
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
  (def ruta ["recipes/Best Homemade Brownies-1.txt"])

  ; Ajustar número de threads para evitar particiones vacías
  (def n-threads-ajustado (min num-threads (count ruta)))
  (def chunk-size (max 1 (int (/ (count ruta) n-threads-ajustado))))
   
  (def data-chunks 
    (partition-all chunk-size ruta))

  ; Función para procesar UNA receta completa (leer + tokenizar)
  (defn process-recipe [file-path]
    (let [; Lee el archivo línea por línea
          raw-lines (with-open [reader (io/reader file-path)]
                      (doall (line-seq reader)))
          ; Convierte cada línea en una lista (como options)
          recipe-lines (map list raw-lines)
          ; Tokeniza cada línea
          tokenized-lines (doall 
                            (map (fn [current-line] 
                                   (if (not (empty? (str/trim (first current-line))))
                                     ; Combina todos los diccionarios para tokenizar todo
                                     (tokenize (first current-line) dict-recipe)
                                     '()))
                                 recipe-lines))]
      ; Retorna una lista con: [nombre-archivo, líneas-originales, líneas-tokenizadas]
      (list file-path recipe-lines tokenized-lines)))

  ; Función para procesar un chunk de recetas
  (defn process-chunk [chunk]
    (map process-recipe chunk))

  ; Medir tiempo de ejecución y procesar en paralelo
  (println "\n-------TOTAL TIME")
  (def exec-time 
    (time 
      (def recipes-processed
        (apply concat ; Aplana los chunks pero mantiene cada receta separada
               (pmap process-chunk data-chunks)))))

  (println exec-time)

  ; Mostrar resultados por receta
  (println "\n-------RECIPES PROCESSED")
  (doseq [recipe recipes-processed]
    (let [file-name (first recipe)
          original-lines (second recipe)
          tokenized-lines (nth recipe 2)]
      
      (println (str "\n--- RECIPE: " file-name " ---"))
      (println "Original lines:")
      (doseq [line original-lines]
        (println (str "  " line)))
      
      (println "\nTokenized lines:")
      (doseq [tokenized-line tokenized-lines]
        (if (not (empty? tokenized-line))
          (println (str "  " tokenized-line))))
      
      ; Aquí puedes agregar más procesamiento específico por receta
      ; Por ejemplo, identificar la sección de ingredientes:
      (println "\nIngredient lines found:")
      (doseq [tokenized-line tokenized-lines]
        (if (some (fn [token] 
                    (and (not (nil? token))
                         (str/starts-with? (str (first token)) "ingredient"))) 
                  tokenized-line)
          (println (str "  INGREDIENT: " tokenized-line))))))
  
  


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

(main "options2.txt" 1)
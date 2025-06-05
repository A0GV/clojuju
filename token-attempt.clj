(println "¡Hola, mundo!")


(require '[clojure.java.io :as io]) ; To read file 
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



;; FILE READING
; Funct to read file line by line
(defn read-file-lines [file-path]
    (with-open [reader (io/reader file-path)]
        (doall (line-seq reader))
   )
)

; Diccionario de tokens de configuraciones del usuario 
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
  (def options-path (str "options/" options-file ".txt"))
  (println "\n-------FILE PATH: " options-path)
  ;(def txt-user-opt (slurp file-path))
  ;(println "USER INPUT\n" txt-user-opt)

  (def file-lines (read-file-lines options-path))
  (println "\n-------USER INPUT\n" file-lines)

  (println "\n-------LINE BY LINE")
  ;(def opt-lines (list (map (fn [line] (println line)) file-lines)) )
  (def opt-lines (map list file-lines))
  (println opt-lines)

  ; Tokenize preferencias 
  ;(doall 
  ;  (def opt-tokenized (list (map (fn[current-line] (tokenize current-line dict-user)) opt-lines)))
  ;)
  ;(let [opt-tokenized (doall (map (fn [current-line] (tokenize current-line dict-user)) opt-lines))]
  ;  (println "Tokenized options:\n" opt-tokenized)
  ;)
  (def opt-tokenized
    (doall 
        (map (fn [current-line] 
            (tokenize (first current-line) dict-user)) 
        opt-lines)
    )
  )

  (println "\n-------TOKENIZED")

  (println opt-tokenized)
  


  
  ; Leer todos los archivos de las recetas pmap
    ; Tokenización - cantidades, unidades de medida, numero de porciones y temperaturas

    ; Convertir unidades - tazas, teaspoons, cups, gramos, Fahrenheit a Celsius,  y viceversa

    ; Calorias totales y por porcion (base de gramos)

    ; Escala arriba o abajo. Tu programa debe calcular la cantidad de ingredientes necesarios para escalar la receta a un numero de porciones determinado. ACTUALIZAR LO ANTERIOR 

    ; Filtra:  el programa debe ser capaz de devolver solo las recetas que incluyan una palabra o frase determinada. Revisa la sección Entradas adicionales.

    ; 9. Salidas:  un .html por cada receta, cuyo contenidos sea el texto del archivo de entrada, original,  y el resultado, formateado según la salida para los puntos anteriores. 

)

; Pasar input file y el número de threads para probarlo
;(def userOptions (slurp "options/options1.txt"))
;(println userOptions)

;(main "options1" 1)
(main "options2" 1)
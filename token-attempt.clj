(println "¡Hola, mundo!")

; To read file 
(require '[clojure.java.io :as io]) 

; Definiciones de regex
;; Opciones de usuario 
(def rg-system (list "system" #"^system:"))
(def rg-u-cup (list "user-cup" #"^cup"))
(def rg-u-tsp (list "user-tsp" #"^teaspoon"))
(def rg-u-met (list "user-metric" #"^metric"))

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
))

; TOKENIZACIÓN
; Finds all matches for item in regex given input text and corresponding dictionary
(defn all-matches [input-text rg-dict]
    (println "All matches!")
)

; Finds length of longest matched text (to keep longest token)
(defn get-max-len [match-list]
    (println "Get maximum length string of all of the matches")
)

; Filters tokens using max length found
(defn filter-max [matches max-len]
    (println "Keeping only the longest one")
)

; Keeps the longest match
(defn tokenize [input rg-dict]
    ;(println "Using dictionary\n" dict-user)
    ;(println "Reading: " input)
    (cond
        ; Base case input is empty
        (zero? (count input) )
    )
    
)


; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads]
  ; Leer options file y guardar preferencias del usuario 
  (def options-path (str "options/" options-file ".txt"))
  (println "FILE PATH: " options-path)
  ;(def txt-user-opt (slurp file-path))
  ;(println "USER INPUT\n" txt-user-opt)

  (def file-lines (read-file-lines options-path))
  (println "USER INPUT\n" file-lines)

  (println "LINE BY LINE")
  ;(def opt-lines (list (map (fn [line] (println line)) file-lines)) )
  (def opt-lines (map list file-lines))
  (println opt-lines)

  ; Tokenize preferencias 
  ;(doall 
  ;  (def opt-tokenized (list (map (fn[current-line] (tokenize current-line dict-user)) opt-lines)))
  ;)
  (let [opt-tokenized (doall (map (fn [current-line] (tokenize current-line dict-user)) opt-lines))]
  (println "Tokenized options:\n" opt-tokenized))
  


  
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

(main "options1" 1)
(println "¡Hola, mundo!")

; Definiciones de regex
;; Opciones de usuario 
(def rg-system (list "system" #"^system:"))
(def rg-u-cup (list "user-cup" #"^cup"))
(def rg-u-tsp (list "user-tsp" #"^teaspoon"))
(def rg-u-met (list "user-metric" #"^metric"))


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
    (println "Using dictionary\n" dict-user)
    (println "File using\n" input)
)


; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads]
  ; Leer options file y guardar preferencias del usuario 
  (def file-path (str "options/" options-file ".txt"))
  (println "FILE PATH: " file-path)
  (def txt-user-opt (slurp file-path))
  (println "USER INPUT\n" txt-user-opt)

  ; Extraer preferencias 
  (tokenize txt-user-opt dict-user)


  
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
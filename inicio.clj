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

;; FILE READING
; Funct to read file line by line
(defn read-file-lines [file-path]
    (with-open [reader (io/reader file-path)]
        (doall (line-seq reader))
   )
)


; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads]
  ; Leer options file y guardar preferencias del usuario 
  (def options-path (str "options/" options-file))
  (println "\n-------FILE PATH: " options-path)
  ;(def txt-user-opt (slurp file-path))
  ;(println "USER INPUT\n" txt-user-opt)

  (def file-lines (read-file-lines options-path))
  (println "\n-------USER INPUT\n" file-lines)

  (println "\n-------LINE BY LINE")
  ;(def opt-lines (list (map (fn [line] (println line)) file-lines)) )
  (def opt-lines (map list file-lines))
  (println opt-lines)

  ; Leer recetas
  (println "\n-------READ RECIPES")
  (println (str "Procesando con archivo de opciones: " options-file))
  (println (str "Número de threads: " num-threads))
  
  ; Definir rutas de recetas
  (def ruta ["recipes/Best Homemade Brownies-1.txt" 
             "recipes/Chimichurri Sauce.txt" 
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"
             "recipes/Fettuccine Alfredo.txt"])

  ; Ajustar número de threads para evitar particiones vacías
  (def n-threads-ajustado (min num-threads (count ruta)))
  (def chunk-size (int (/ (count ruta) n-threads-ajustado))) ; Tamaño de partición basado en número de threads
   
  (def data-chunks 
    (partition-all chunk-size ruta)) ; Divide la lista de archivos en sublistas según el tamaño de partición

  ;; (println "\nData chunks:")
  ;; (println data-chunks)

  ;; (println "\nVersión paralela de lectura con partición")

  ; Función para leer archivos (simula un proceso lento)
  (defn read-file [file-path]
    ;; (Thread/sleep 1000) ;;Lo deje comentario pero sirve comom para checar si si lo hace mas rapido
    (with-open [reader (io/reader file-path)]
        (doall (line-seq reader))
   )
)      

  ; Medir tiempo de ejecución y procesar en paralelo
  (println "\n-------TOTAL TIME")
  (time 
    (def lectura
      (apply concat ; Une los resultados de las sublistas
             (pmap (fn [chunk] (doall (map read-file chunk))) data-chunks))))

  ; Tokenización - cantidades, unidades de medida, numero de porciones y temperaturas
  ; Convertir unidades - tazas, teaspoons, cups, gramos, Fahrenheit a Celsius,  y viceversa
  ; Calorias totales y por porcion (base de gramos)
  ; Escala arriba o abajo. Tu programa debe calcular la cantidad de ingredientes necesarios para escalar la receta a un numero de porciones determinado. ACTUALIZAR LO ANTERIOR 
  ; Filtra:  el programa debe ser capaz de devolver solo las recetas que incluyan una palabra o frase determinada. Revisa la sección Entradas adicionales.
  ; Agrega texto con resultados (speed up y aceleración para el numero de hilos) 

  (println "\nContenido de las recetas:")
  (println lectura)
  )
  ; Retornar los datos para uso posterior

; Pasar input file y el número de threads para probarlo
(main "options1.txt" 1)
;(main "options1.txt" 6)
;(main "options1.txt" 10)

(main "options2.txt" 1)
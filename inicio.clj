  (println "¡Hola, mundo!")


; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads])
  ; Leer options file y guardar preferencias del usuario 
  
  ; Leer todos los archivos de las recetas pmap

    ; Tokenización - cantidades, unidades de medida, numero de porciones y temperaturas
  
    ; Convertir unidades - tazas, teaspoons, cups, gramos, Fahrenheit a Celsius,  y viceversa
  
    ; Calorias totales y por porcion (base de gramos)
  
    ; Escala arriba o abajo. Tu programa debe calcular la cantidad de ingredientes necesarios para escalar la receta a un numero de porciones determinado. ACTUALIZAR LO ANTERIOR 
  
    ; Filtra:  el programa debe ser capaz de devolver solo las recetas que incluyan una palabra o frase determinada. Revisa la sección Entradas adicionales.
  
    ; Agrega texto con resultados (speed up y aceleración para el numero de hilos) 
  
  

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

(def n-threads 10) ; Número de threads ajustable
(def chunk-size (int (/ (count ruta) n-threads))) ; Tamaño de partición basado en número de threads
 
(def data-chunks 
  (partition-all chunk-size ruta)) ; Divide la lista de archivos en sublistas según el tamaño de partición

(println "\nData chunks:")
;; (println data-chunks)

(println "\nVersión paralela de lectura con partición")

; Función para leer archivos (simula un proceso lento)
(defn read-file [archivo]
  (Thread/sleep 1000) ; Simula un retraso de 1 segundo por archivo 
    (slurp archivo)) 
      

; Medir tiempo de ejecución y procesar en paralelo
(time 
  (def lectura
    (apply concat ; Une los resultados de las sublistas
           (pmap (fn [chunk] (doall (map read-file chunk))) data-chunks))))

;; (println "\nContenido de las recetas:")
(println lectura)
;; Ocupo usear partition all, variable de threads, variable de time, poder ajustar el numero de threadsa que se usa
; Pasar input file y el número de threads para probarlo
(main options1.txt 1)
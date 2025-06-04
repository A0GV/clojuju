(println "¡Hola, mundo!")
(require '[clojure.java.io :as io]) 

; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads]
  ; Leer options file y guardar preferencias del usuario 
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

  ;; (println "\nContenido de las recetas:")
  ;; (println lectura)
  )
  ; Retornar los datos para uso posterior

; Pasar input file y el número de threads para probarlo
(main "options1.txt" 1)
(main "options1.txt" 6)
(main "options1.txt" 10)
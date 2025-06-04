  (println "¡Hola, mundo!")


; Función principal que checa recetas con el número de opciones seleccionadas y threads especificados 
(defn main [options-file num-threads]
  ; Leer options file y guardar preferencias del usuario 
  
  ; Leer todos los archivos de las recetas pmap

    ; Tokenización - cantidades, unidades de medida, numero de porciones y temperaturas
  
    ; Convertir unidades - tazas, teaspoons, cups, gramos, Fahrenheit a Celsius,  y viceversa
  
    ; Calorias totales y por porcion (base de gramos)
  
    ; Escala arriba o abajo. Tu programa debe calcular la cantidad de ingredientes necesarios para escalar la receta a un numero de porciones determinado. ACTUALIZAR LO ANTERIOR 
  
    ; Filtra:  el programa debe ser capaz de devolver solo las recetas que incluyan una palabra o frase determinada. Revisa la sección Entradas adicionales.
  
    ; Agrega texto con resultados (speed up y aceleración para el numero de hilos) 
  
  )
(def ruta ["recipes/Best Homemade Brownies-1.txt" "recipes/Chimichurri Sauce.txt" "recipes/Fettuccine Alfredo.txt"])
(def lectura (pmap slurp ruta))
(println lectura)

; Pasar input file y el número de threads para probarlo
(main options1.txt 1)
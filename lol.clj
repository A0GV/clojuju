(def hola '((("receta" "instrucciones: ") ("hola" "crayola"))(("receta" "instrucciones: ") ("hola" "crayola"))))

(defn convert [class text]
    (str "<text class='" class "'>" text "</text>" )
)

(defn resultado [linea]
    (apply str (map (fn [x] (convert (first x) (second x))) linea))
)

(defn html [tokenized]
 (apply str (map (fn [x] (str (resultado x) "<br/>")) tokenized))
)
(spit "htmls/prueba.html" (html hola))
;(spit "prueba.html" (html hola))

(println (html hola))
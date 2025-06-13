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
(def rg-nums-int (list "number-integer" #"^[0-9]+"))

; Regex para fracciones simples
(def rg-nums-frac (list "number-fraction" #"^[0-9]+/[0-9]+"))

; Regex para fracciones mixtas
(def rg-nums-mixed (list "number-mixed" #"^[0-9]+\s+[0-9]+/[0-9]+"))

; Regex para ingredientes (case sensitive)
(def rg-sugar (list "ingredient-sugar" #"^\b(?:granulated\s+)?sugar\b"))
(def rg-flour (list "ingredient-flour" #"^\b(?:all-purpose\s+|almond\s+)?flour\b"))
(def rg-cocoa (list "ingredient-cocoa" #"^\bcocoa\s+powder\b"))
(def rg-powdered-sugar (list "ingredient-powdered-sugar" #"^\bpowdered\s+sugar\b"))
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
(def rg-gram (list "gram" #"^grams?\b"))

;(def rg-phrases (list "phrases" #"^[a-zA-Z0-9 ,\.\(\)]+"))
; Other words 
(def rg-serves (list "serves-amt" #"^(?:Serves\s*-\s*|Servings\s*-\s*)[0-9]+"))

(def rg-temp-f-range (list "temp-f-range" #"^[0-9]+°F\s*-\s*[0-9]+°F")) 
(def rg-temp-c-range (list "temp-c-range" #"^[0-9]+°C\s*-\s*[0-9]+°C")) 

(def rg-temp-c (list "temp-C" #"^[0-9]+°C"))
(def rg-temp-f (list "temp-F" #"^[0-9]+°F"))

(def rg-pt (list "prep-t" #"^Prep Time\:\s*[0-9]+\s*(?:mins|minutes)"))
(def rg-ct (list "cook-t" #"^Cook Time\:\s*[0-9]+\s*(?:mins|minutes)"))
(def rg-tt (list "total-t" #"^Total Time\:\s*[0-9]+\s*(?:mins|minutes)"))


(def rg-step-num (list "step-num" #"^[0-9]+\."))
(def rg-fract-in (list "fract-in" #"[0-9]+/[0-9]+\""))

(def rg-ingredients (list "kw-ingredient" #"^Ingredients(?:\:)*"))
(def rg-instruct (list "kw-instruct" #"^Instructions"))

(def rg-dash (list "dash" #"^[-]"))
(def rg-time-range (list "time-range" #"^[0-9]+\sto\s[0-9]+\sminutes"))
(def rg-time-dash (list "time-range" #"^[0-9]+\-minute"))
(def rg-time-dash-range (list "time-range" #"^[0-9]+\-[0-9]+\s+minutes?"))

(def rg-time-mention (list "time-mention" #"^[0-9]+\–*\s*(?:minutes|minute)"))
(def rg-8x8 (list "w" #"^[0-9]x[0-9]"))


; Just stores words bcs it's annoying to deal w a lot of floating tokens
(def rg-catch (list "w" #"[a-zA-Z]+"))

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
                    rg-gram

                    ; Adding
                    rg-serves
                    ; Temperatures
                    rg-temp-f-range rg-temp-c-range ; First to check for ranges 
                    rg-temp-c rg-temp-f ; THen just ind temp mentions
                    ; Time mentions 
                    rg-pt rg-ct rg-tt

                    ; Keywords 
                    rg-ingredients rg-instruct

                    rg-step-num
                    rg-fract-in

                    ; Catch case
                    rg-time-dash-range
                    rg-time-range
                    rg-time-dash
                    rg-time-mention
                    rg-catch
                    rg-dash
                    rg-8x8

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
                    matched-txt (re-find rg-pattern input-text)  ; String that was matched (nil if not found)
                    ]
                    
                    
                    ; If nil, then just returns a false to later filter it out
                    ;(if (nil? matched-txt) false)
                    ;(if matched-txt
                        ; Did find a match, builds list of token name and 
                    ;    (list token-name matched-txt)
                    ;    nil)

                    ; If found a match
                    (if matched-txt
                        (let [
                            ; Finds position where match began
                            match-pos (.indexOf input-text (str matched-txt)) ]
                            ;(println "Token:" token-name "Match:" matched-txt "Position:" match-pos)

                            ; If the match position is at the start, returns the token name and the match as a string
                            (if (= match-pos 0)
                                (list token-name matched-txt)
                                nil)
                        )
                        ; Else just returns a null 
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
            (empty? all-found-matches) (list "NA" (subs input-item 0 1)) 

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
            input-trim (str/trim input) ; Para hacer llamadas, ya quita los empty spaces

            ; Head de variables 
            type-txtmatch (longest-match input-trim rg-dict)
            ; Builds the substring
            extracted-txt (second type-txtmatch) ; Finds the token text
            length (count extracted-txt) ; Counts how long the token text is

            ; Finds pos of match starting
            pos-match-start (.indexOf input-trim extracted-txt)

            ; For recursive to start at next element (excluding whitespace)
            ;rest-input (subs input-trim length)
            rest-input (if (= "NA" (first type-txtmatch))
                ; NA case: longest-match already took 1 char, so skip 1 char
                (str/trim (subs input-trim 1))
                ; Match found: skip past the entire matched text
                (str/trim (subs input-trim (+ pos-match-start length)))
            )

            ]
            ; Ahora el body para build list 
            (cons 
                (list (first type-txtmatch) extracted-txt)
                (tokenize rest-input rg-dict)
            )
        )
    )
)

;; RECIPE HANDLING
; Recieves the chunk (aka the list of file paths) 
(defn process-recipe [file-path]
    (let [
        ; Read file lines w reader
        raw-lines (with-open [reader (io/reader file-path)] (doall (line-seq reader)))
        ; Converts lines to list to have same format as options
        recipe-lines (map list raw-lines)

        ; cant have prints in lets (println "\n-------RECIPE LINES\n" recipe-lines)

        ; Tokenizar las líneas
        tokenized-lines (doall (map (fn [current-line] 
                            ; Proccesses line recipe if it is not empty 
                            (if (not (empty? (str/trim (first current-line))))
                                (tokenize (first current-line) dict-recipe) ; Tokenize call
                                '() ; List is empty
                            )
                        ) recipe-lines) )
        ]
        ; Returns list w file name, original lines just in case, and tokenized lines 
        ;(println "\n\n\n")
        (println "Tokenized lines:" tokenized-lines)
        (list file-path recipe-lines tokenized-lines)
    )
)

; Recieves a chunk of recipes and then tokenizes the recipe
(defn process-chunk [chunk]
    (println "Processing: " chunk)
    (doall (map process-recipe chunk))
)

;; SUB-FUNCTIONS FOR RECIPE CONVERSION
; Extrae val numerico de temp given  
(defn extract-num-value [num-string]
    (let [numeric-part (re-find #"\d+" num-string)]
        ; Returns num if it did match, 0 if it did not find the number
        (if numeric-part (Integer/parseInt numeric-part) 0)
    )
)

; Converts far to cel and returns it as a token C = (F - 32) / (9 / 5)
(defn f-to-c [f-temp-string]
    (let [
            ; Calls helper funct to convert farenheit 
            f-value (extract-num-value f-temp-string)
            c-value (/ (* (- f-value 32) 5) 9.0) ; Plugs into eq
        ]
        (list "temp-C" (str c-value "°C")))
)

; Converts far to cel and returns it as a token F = (C * (9 / 5)) +32
(defn c-to-f [c-temp-string]
    (let [
            ; Calls helper funct to convert farenheit 
            c-value (extract-num-value c-temp-string)
            f-value (+ (* c-value (/ 9 5)) 32) ; Plugs into eq
        ]
        (list "temp-F" (str f-value "°F")))
)

; Converts int or fraction to a numeric val
(defn numToInt [int-str]
  (read-string int-str))

; Converts a mixed fraction to a number
(defn mixedFrac [mixed-frac]
  (let [parts (clojure.string/split mixed-frac #" ")
        resp (+ (numToInt (first parts)) (numToInt (second parts)))]
    resp))

; Function range conversions 
;; F -> C range or C -> F based on user desired
(defn temp-range-convert [temp-range-str user-temp-units]
    (let [
        ; List of number matches using re-seq 
        temp-nums (re-seq #"[0-9]+" temp-range-str) 
        ; Converts temps to numbers
        temp1 (Integer/parseInt (first temp-nums)) 
        temp2 (Integer/parseInt (second temp-nums))
        ]
        ; For temperature DIFFERENCES, just multiply by 5/9
        ;c-val1 (* f-val1 (/ 5 9.0))
        ;c-val2 (* f-val2 (/ 5 9.0))

        ; user-temp units = true -> F to C; else C to F
        (if user-temp-units
            ; F -> C calc
            (let [
                c1 (* temp1 (/ 5 9))
                c2 (* temp2 (/ 5 9))
                ]
                ; Exec checks
                (println "Changed " temp1 "-" temp2 "->" c1 "-" c2)
                ; Returns list converted with the new token name 
                (list "temp-c-range" (str c1 "°C-" c2 "°C"))
            )

            ; C -F F calc
            (let [
                f1 (* temp1 (/ 9 5))
                f2 (* temp2 (/ 9 5))
                ]
                ; Body 
                (println "Changed " temp1 "-" temp2 "->" f1 "-" f2)
                (list "temp-f-range" (str f1 "°F-" f2 "°F"))
            )
        )
    )
)

;; COMPARISON TOKENS AGAINST USER PREFERENCES
; Processes line using result of whether user wants celcius
(defn process-token-line [token-line user-temp-units scale-factor]
    ;(println "Processing token-line")
    (if (seq token-line)
        (doall 
            (map (fn [token]
                ; Recipe token is currently set to F and user wants C
                (cond 
                    ; Checks recipe F, user C
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-F") 
                        user-temp-units
                    )
                        ; Need to convert F to C, calls funct
                        (f-to-c (second token))
                    
                    ; Checks recipe C, user F
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-C") 
                        (not user-temp-units) ; False = Farenheit
                    )
                        ; Calls funct to convert F -> C
                        (c-to-f (second token))

                    ; Checks recipe range F, user C
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-f-range") 
                        user-temp-units ; True C
                    )
                        ; Need to convert F to C, calls funct w value and user desired
                        (temp-range-convert (second token) user-temp-units)

                    ; Checks recipe range C, user F
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        (= (first token) "temp-c-range") 
                        (not user-temp-units) ; False F
                    )
                        ; Need to convert C to F, calls funct w value and user desired
                        (temp-range-convert (second token) user-temp-units)

                    ; Checks if it is a number or simple fraction to convert 
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        ; If it is a 
                        (or (= (first token) "number-integer")
                            (= (first token) "number-fraction")
                        )
                    )
                        ; Multiplies current amt by scale factor 
                        ;(println "FRACTION " (first token) "value: " extract-num-value (second token) )
                        ;(list "number-scaled" (* scale-factor (extract-num-value (second token))) )
                        ;(list "number-s-integer" (str (* scale-factor (extract-num-value token))))
                        (let [
                            original-str (second token)  ; Get the string value
                            ; Convert a numero con adolf help
                            original-value (numToInt original-str)  
                            scaled-value (* scale-factor original-value)]
                            (println "  Scaling number:" original-str "×" scale-factor "=" scaled-value)
                            
                            ; Structured list 
                            (list "number-s" (str scaled-value))
                        )

                    ; Converting a mixed fraction 
                    (and 
                        (not (nil? token)) 
                        (not (empty? token))
                        ; If it is a mixed fraction
                        (= (first token) "number-mixed")
                    )
                        (do
                            (println "MIXED: " (second token))
                            (let [
                                ; Fraccion number
                                original-str (second token) 
                                mixed-value (mixedFrac original-str) ; A ver si funciona con lo de adolf
                                scaled-value (* scale-factor mixed-value)
                                ]

                                ;(println "Proccessed: " mixed-value)

                                (println "  Scaling mixed fraction:" mixed-value "×" scale-factor "=" scaled-value)
                                
                                ; Returns la lista con scaled mixed
                                (list "number-s-mix" (str scaled-value))
                            )
                        )


                    ; Checks if it is a mixed fraction that needs to be converted 

                    ; Else it can just stay how it is
                    :else token
                ))
            token-line)) 
        '("\t" "\t")) ; It is not a sequence
)

; Main function to manipulate one recipe at a time based on user preferences
(defn manipulate-recipe [recipe user-options]
    (println "\n------- BEGIN MANIPULATION -------")
    (let [
            recipe-name (first recipe)
            original-lines (second recipe)
            tokenized-lines (nth recipe 2)

            ; Checks if user wants C
            ;user-temp-units1 (second (second (second user-options))) ; Check if C or F
            user-temp-units (= "C" (second (second (second user-options))))

            ; Extracts number of portions that user wants, need read-string to handle that it's a string
            user-num-portions (read-string (second (second (nth user-options 2))) )

            ; Find recipe number of servings from tokenized lines
            recipe-serves 
                ; Extracts number value
                (extract-num-value
                    ; Extract the token for serves 
                    (second (first 
                        (filter 
                            (fn [token-line] (= (first token-line) "serves-amt")) 
                        ; Need to flatten when looking for amt to take into account diff line skips and such
                        (apply concat tokenized-lines)
                        ))
                    )
                )

            ; Amt to scale a recipe
            scale-factor (/ user-num-portions recipe-serves)
        ]
        (println "USER TEMP UNITS " user-temp-units) ; False F, true C
        
        (println "Processing recipe:" recipe-name " with" recipe-serves " user wants  " user-num-portions "; scaled: " scale-factor "\n")

        ; Process all tokenized lines
        (let [
            corrected-temp (doall (map 
                (fn [token-line] (process-token-line token-line user-temp-units scale-factor)) tokenized-lines))
            ]
            
            ; Return updated recipe structure
            (list recipe-name original-lines corrected-temp)
        )
    )
)

; Analyze all recipes and apply manipulations
(defn analyze-recipes [processed-recipes user-tokens]
    ; Apply manipulations to all recipes
    (let [manipulated-recipes (doall (map (fn [recipe]
                                     (manipulate-recipe recipe user-tokens))
                                   processed-recipes))
         ]
        
        (println "Processed" (count manipulated-recipes) "recipes")
        
        ; Return the manipulated recipes in correct format 
        manipulated-recipes
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

    ; looking for user preferences servings amt
    (print "looking for servings: ")
    (print (second (second (nth opt-tokenized 2))) )

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
    (def ruta ["recipes/Best Homemade Brownies-1.txt"
                "recipes/Chimichurri Sauce.txt" 
                "recipes/Fettuccine Alfredo.txt"
                "recipes/Lemon Cake-1.txt"
                "recipes/Pan-Seared Steak with Garlic Butter.txt"

    ])

    ; Ajustar número de threads para evitar particiones vacías
    (def n-threads-ajustado (min num-threads (count ruta)))
    (def chunk-size (max 1 (int (/ (count ruta) n-threads-ajustado))))

    ; Divides the recipes into chunks containing file names
    (def data-chunks 
    (partition-all chunk-size ruta))

    (println "CHUNKS: " data-chunks) ; Stores list
    ;; 2 recipes 2 threads((recipes/Best Homemade Brownies-1.txt) (recipes/Chimichurri Sauce.txt))
    ;; 2 recipes 1 thread ((recipes/Best Homemade Brownies-1.txt recipes/Chimichurri Sauce.txt))

    ; Medir tiempo de ejecución y procesar en paralelo
    (println "\n-------TOTAL TIME")
    ; Guarda tiempo de ejecución
    (def exec-time 
        (time 
            (do
                ; Process the recipes 
                (def recipes-processed
                    (apply concat ; Aplana chunks pero keeps recipes separated
                        ; Sends the chunks of recipes (list of recipes) to proccess them
                        (pmap process-chunk data-chunks)
                    )
                )
                ; Print the tokens
                (doall (map (fn [x] (println (nth x 2)"\n\nNext Recipe Tokens:\n")) recipes-processed)) ; Check all the recipes 

                ; Passes tokenized recipe and the tokens of user customization
                (def fix-recipes (analyze-recipes recipes-processed opt-tokenized))
            )
        )
    )

    (println exec-time)
    (println "Fixed recipes")

    ; Structure of recipes 
    ;(println (nth (first fix-recipes) 0) )
    ;(println (nth (first fix-recipes) 1) )
    ;(println (nth (first fix-recipes) 2) )

    ;(println (nth (map (first) fix-recipes) 2) )
    (doall (map (fn [x] (println (nth x 2)"\n\nFINAL Recipe Tokens:\n")) fix-recipes)) ; Check all the recipes 




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

(main "options1.txt" 1)

(println "Mixed convert: "  (mixedFrac "1 1/2")) ; Test fraction to make sure its an int
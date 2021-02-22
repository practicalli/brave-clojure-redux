(ns practicalli.functions)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Clojure functions
;;
;; Brave Clojure Chapter 3 - Do things - Functions
;; - calling functions
;; - macros and special forms
;; - defining functions
;; - anonymous functions
;; - returning functions (part of higher order functions)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;; Calling functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (function-name)
;; (function-name argument)
;; (function-name argument1 argument2 argument3 ,,,)

;; Examples
(+ 1 3 5 8 13 21)
;; => 51

;; nested functions
;; arguments evaluated before passing to the function
(+ 1 (+ 1 2) (/ 25 5) 48/6 (- 24 11) (* 3 7))


;; 'Interesting' Brave Clojure example
((or + -) 1 2 3)

;; deconstruct the above
(or + -)
;; => #function[clojure.core/+]

;; Check for a Boolean true value, true or false
(true? +)
(true? true)
(true? false)

;; Check for a truthy value (using conditionals from video 96)
(if +
  "truthy"
  "falsey")

(if nil
  "truthy"
  "falsey")

;; More interesting examples
;; and returns the last truthy value
((and (= 1 1) +) 1 2 3)


((first [+ 0]) 1 2 3)

;; Above examples demonstrate that functions return a value
;; A value will be interpreted as a function call if its the first element in a list

;; invalid function call examples
;; (1 2 3 4)
;; ("test" 1 2 3)


;; functions can take any expressions as arguments—including other functions

(map inc [0 1 2 3])
;; => (1 2 3 4)

(= [1 2 3] '(1 2 3))

;; Use map to iterate over a collection of values, transforming those values

(def portfolio [{ :ticker "CRM"  :lastTrade 233.12 :open 230.66}
                { :ticker "AAPL" :lastTrade 203.25 :open 204.50}
                { :ticker "MSFT" :lastTrade 29.12  :open 29.08 }
                { :ticker "ORCL" :lastTrade 21.90  :open 21.83 }])

(get { :ticker "CRM"  :lastTrade 233.12 :open 230.66} :ticker)

({ :ticker "CRM"  :lastTrade 233.12 :open 230.66} :ticker)
(:ticker { :ticker "CRM"  :lastTrade 233.12 :open 230.66} )


(map :ticker portfolio)

(map (fn [stock-price] (assoc stock-price :open (:lastTrade stock-price)))
     portfolio)

(map #(assoc % :open (:lastTrade %)) portfolio)


;; The composability of Clojure enables abstractions to be created base



;; Special forms
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Building blocks of the language
;; https://clojure.org/reference/special_forms

;; def, if,

;; Cannot be used as arguments to other functions
;; have different evaluation rules
;; e.g.  if

(if true
  "true"
  "false")



;; defining functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Different forms of function definition

;; defn

;; def fn
;; - defn is a macro


;; anonymous function
;; this way of defining a function does not have an external name that can be referenced
;; it can have a local name, so a function can call itself by name or via recur

(fn update-stock [stock-price] (assoc stock-price :open (:lastTrade stock-price)))


(fn update-stock [stock-price] (update-stock {}))

;; (update-stock)

;; Brave Clojure examples

(defn too-enthusiastic
 "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
  "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(too-enthusiastic "practialli")




;; Parameters and arity
;; You can have multi-variadic functions too
;; one function that behaves differently dependant on the number or arugments passed

(defn no-params
  []
  "I take no parameters!")
(defn one-param
  [x]
  (str "I take one parameter: " x))
(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
  "together to spite you! " x y))


(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name]
     (x-chop name "karate"))
  ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!")))

(x-chop "Kanye West" "slap")
(x-chop "Kanye East")


(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")


;; Practicalli examples

(defn hello-polly-function
  ([] "Hello Hackference")
  ([name] (str "Hello " name ", hope your talk is awesome at Hackference")))

(hello-polly-function)
(hello-polly-function "Cristiano")


;; Functions can pack extra arguments up in a sequence for you, its the & notation that is important, although its a fairly common convention to use args (better if its given a more meaningful name though)

(defn count-args [& args]
  (str "You passed " (count args) " args: " args))
(count-args 1 2 3)

; You can mix regular and packed arguments
(defn hello-count [name & args]
  (str "Hello " name ", you passed " (count args) " extra args"))
(hello-count "Finn" 1 2 3)
(hello-count)

(count-args)


;; Destructuring
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; https://clojure.org/guides/destructuring

;; Positional  - e.g. for vectors
;; Associative - e.g. for hash-maps




;; Macros
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Calling macros is the same as calling functions,
;; although macros lack composability

;; except that a macro generates new code and then evaluates that

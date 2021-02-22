;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Brave Clojure Redux
;; - review and elaboration of the examples from the book
;;
;; Chapter 3: Getting started
;; - syntax (forms, expressions)
;; -
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Practicalli Clojure provides a quick guide to Clojure
;; which contains an embedded REPL in the webpage so you can try out the code and see what happens when its changed
;; https://practicalli.github.io/clojure/first-taste-of-clojure.html


(ns practicalli.getting-started
  (:gen-class))


;; Forms / Expressions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A form is any valid piece of Clojure code,
;; if code does not evaluate then it is not of a correct form.
;; Clojure forms are also referred to as an expression, especially when combined

;; Clojure forms predominantly exist in some data structure,
;; most commonly in a list as an expression or part of an expression

;; Clojure forms within a list express a call to a function, usually with arguments

(* 1 2 3 4)
(inc 9)
(even? 42)
(str "Hello" " " "World")
(map inc [1 3 5 7 9])
(range 5 10)
(apply * (range 5 10))
(apply + (concat '(1 2 3 4) (range 5 10)))

;; The form at the start of a () list is treated as the name of a function to call,
;; any further forms in the list being arguments to that function call.
;; Many clojure.core functions take variable number of arguments

;; Clojure uses prefix notation and () lists to identify precisely how code should be evaluated.
(+ 4 2 (* 6 3 2))

;; nested lists are evaluated first and replaced by their return result
(+ 4 2 36)

;; then the whole expression is evaluated and a value is returned

;; Try evaluating the nested expressions in this example
(+ 3 (* 2 (- 7 2) 4) (/ 16 4))


;; Literal values
;; Literal representation of a form, e.g 42 "I am a string" :keywords
;; can be evaluated all by themselves, although not all that useful

;; For example, the simplest Hello World code in Clojure is a literal string

"Hello World"

;; Evaluating this form will return the same value, as the form is the value it represents


;; NOTE: A form always returns a value when evaluated



;; Control flow
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(if (> 1 5 )
  "true"
  "false")

(if (> 5 1)
  "true"
  "false")

(when (> 5 1)
  "true")

;; Idioms
;; https://guide.clojure.style/#idioms

true
false

(false? 1)

(if [1 2 3]
  "true"
  "false")

(if false
  "true")

(not (empty? [1 2 3]))

(seq [1 2 3])
(seq [])

(if (seq [])
  "true"
  "false");; => "false"

(if (seq [1 2 3])
  "true"
  "false");; => "true"

(or PORT 8080)

(and ,,, )




(if 1
  "true")

(if []
  "true")

(if (seq [])
  "true")


;; other languages
x = 3


(= 1 1 1 1 1 1 1 (- 2 1) (* 1 1))

(if (= 2 1 1 1 1 1 1 1 (- 2 1) (* 1 1))
  "true"
  (str "false" " or " "falsey"))

(= true false)
;; => false
(= true nil)
;; => false


(= true (= 1 1))

(= [1 2 3] [1 2 3])
(= [1 2 3] '(1 2 3))
;; => true

(quote (1 2 3))


;; Functions


(def x-player "X")
(def y-player "y")



(defn player-turn [player]
  ,,,)

(player-turn y-player)

(defn winner? [game-state players]
  (str "winner is " (first players)))

(winner? {:scores []} [x-player y-player])

;; (def severity :mild)
;; (def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
;; (if (= severity :mild)
;;   (def error-message (str error-message "MILDLY INCONVENIENCED!"))
;;   (def error-message (str error-message "DOOOOOOOMED!")))


(let [name value]
  (str name))

(let [company-name "Practicalli"]
  (str company-name)
  ,,,
  ,,,
  )

(defn my-algorithm
  [blah]
  (let [company-name "jr0cket"
        company-slogan (str company-name " is awesome and waffley")]
    company-slogan
    ,,,
    ,,,
    ))

(my-algorithm "")


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(+ 1 2 3 4 )

(apply + (range 5 10))

(apply + (concat '(1 2 3 4) (range 5 10)))

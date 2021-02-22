(ns practicalli.data-structures)


;; Data structures
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; TODO: built in data structures
;; TODO: Immutability
;; TODO: Structural sharing - memory efficient 'copies'


;; Number types
;; https://clojure.org/reference/data_structures#Numbers

;; Related functions
;; https://clojure.org/reference/data_structures#_related_functions

;; Ratio type - lazy precision

(/ 22 7)

(* (/ 22 7) 9)

1
198/7

;; Strings / Characters
;; - example reverse string
;; - apply str vs clojure.string/...

(reverse "racecar")
;; => (\r \a \c \e \c \a \r)

(clojure.string/reverse "hello")

(clojure.string/join '(\r \a \c \e \c \a \r))
(apply str (reverse "racecar"))

;; Keywords - leave until hash-maps

;; Keywords are functions with a data structure as an argument
;; data structures are functions with keys as arguments

;; Never seen defstruct used anywhere
;; Records are infrequently used


;; list data structure
(quote (1 2 3))
'(1 2 3)

;; vector data structure
;; - the most common data structure  (not counting lists used as function calls)
;; - an indexed collection of values

(get [1 2 3] 1)

(= [1 2 3] '(1 2 3 4))

(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
;; => {:name "Pugsley Winterbottom"}

(["a" {:name "Pugsley Winterbottom"} "c"] 1)
;; => {:name "Pugsley Winterbottom"}

;; Contains vs some

;; looking up via a key for the data structure
(contains? ["a" {:name "Pugsley Winterbottom"} "c"] "a")

;; looking up via a predicate value
(some #{"a"} ["a" {:name "Pugsley Winterbottom"} "c"])


(some #{"b"} ["a" {:name "Pugsley Winterbottom"} "c"])

true

false

(def my-data-structure
  [{:name "" :age "" :address ""}
   {:name "" :age "" :address ""}
   ,,,])





;; Maps
;; - dictionaries


;; Imumability
(conj [1 2 3] 4)
;; => [1 2 3 4]

(def my-data [1 2 3])

(conj [1 2 3] 4)
;; => [1 2 3 4]

(conj my-data 7)
;; => [1 2 3 7]



;; sets

(hash-set 1 1 2 2)
;; => #{1 2}

#{1 2}

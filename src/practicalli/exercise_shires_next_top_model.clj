;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Shire;s Next Top Model
;;
;; Practicalli does not condone aggression or violence
;; towards Hobbits or any living creatures
;; either mythical or perceived as real
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(ns practicalli.exercise-shires-next-top-model)


;;;; Modeling the Hobbits

;; Each body part of a hobbit is defined with a name and relative size
;; indicating how likely it is to be hit
;; e.g. chest is size 10 as it is a bigger target than ear at size 1

;; For efficiency, where a hobbit has a pair of body parts, only one of a pair is defined,
;; so only left ear, left eye, etc.

;; A function will be created that takes the single item of a pair
;; and makes a symmetric hobbit with 2 ears, 2 eyes, etc.


(def asym-hobbit-body-parts
  [{:name "head" :size 3}
   {:name "left-eye" :size 1}
   {:name "left-ear" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 4}
   {:name "left-lower-leg" :size 3}
   {:name "left-achilles" :size 1}
   {:name "left-foot" :size 2}])

;; Body parts is a single collection, specifically a vector
;; each element of the collection is a hash-map
;; each hash-map contains key-value pairs
;; keys in the hash-map are defined using Clojure keywords

(get {:name "left-foot" :size 2} :name)
(:name {:name "left-foot" :size 2})
({:name "left-foot" :size 2} :name)


;;;; Background: working with sequences - first and rest

(first asym-hobbit-body-parts)
(rest asym-hobbit-body-parts)


(let [name "value"])

;; Using destructuring
(let [[first-body-part & rest-of-parts] asym-hobbit-body-parts]
  rest-of-parts)



;;;; Making a whole hobbit

;; A function to generate a right body part, given a left body part

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})


;; Aside: regular expressions
;; regular expressions are common in all programming languages
;; they are very simple to use in Clojure

;; #"^left-" is a regular expression pattern that matches any string starting with "left-"
;; The carat, ^, specifies the pattern should match from the beginning of the string
;; re-find uses a pattern to check a string, returning the matched string or nil if there is no match

(re-find #"^left-" "left-eye")

(re-find #"^left-" "cleft-chin")

(re-find #"^left-" "wongleblart")


;; Testing the matching part function

(matching-part {:name "left-eye" :size 1})

(matching-part {:name "head" :size 3})


;; A function to iterate over the hobbit body parts data structure

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts     []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))


(symmetrize-body-parts asym-hobbit-body-parts)
;; => [{:name "head", :size 3} {:name "left-eye", :size 1} {:name "right-eye", :size 1} {:name "left-ear", :size 1} {:name "right-ear", :size 1} {:name "mouth", :size 1} {:name "nose", :size 1} {:name "neck", :size 2} {:name "left-shoulder", :size 3} {:name "right-shoulder", :size 3} {:name "right-upper-arm", :size 3} {:name "left-upper-arm", :size 3} {:name "chest", :size 10} {:name "back", :size 10} {:name "left-forearm", :size 3} {:name "right-forearm", :size 3} {:name "abdomen", :size 6} {:name "left-kidney", :size 1} {:name "right-kidney", :size 1} {:name "left-hand", :size 2} {:name "right-hand", :size 2} {:name "right-knee", :size 2} {:name "left-knee", :size 2} {:name "right-thigh", :size 4} {:name "left-thigh", :size 4} {:name "right-lower-leg", :size 3} {:name "left-lower-leg", :size 3} {:name "right-achilles", :size 1} {:name "left-achilles", :size 1} {:name "right-foot", :size 2} {:name "left-foot", :size 2}]


;; Explanations
;; recur calls loop with updated values for its arguments
;; recur is efficient as it re-uses memory  (software tail recursion)
;; loop binds values to names, just like let
;; let binds local names to values using destructuring, part is used several times in the recur expression
;; into conjoins a value onto the given data structure, initially a vector, then vector of parts
;; set is used to avoid duplicates when a part has no right equivalent
;; part is the original unchanged body part


;; Using set to avoid duplicates and into to put into a vector

(into [] (set [{:name "head" :size 3} {:name "head" :size 3}]))

(into [] (set [{:name "left-ear" :size 1}
               {:name "right-ear" :size 1}]))



;; Rich comment block with redefined vars ignored
#_{:clj-kondo/ignore [:redefined-var]}
(comment

  ;; Alternative implementation without the let

  (defn symmetrize-body-parts
    "Expects a seq of maps that have a :name and :size"
    [asym-body-parts]
    (loop [remaining-asym-parts asym-body-parts
           final-body-parts     []]
      (if (empty? remaining-asym-parts)
        final-body-parts
        (recur (rest remaining-asym-parts)
               (into final-body-parts
                     (set [(first remaining-asym-parts) (matching-part (first remaining-asym-parts) )]))))))


  (symmetrize-body-parts asym-hobbit-body-parts)

  ) ;; End of rich comment block



;; Going loopy

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

;; Alternative to loop recur - a recursive function

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println "Iteration" iteration)   ;; fixed small bug
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

(recursive-printer)
(recursive-printer 2)


;; Rich comment block with redefined vars ignored
#_{:clj-kondo/ignore [:redefined-var]}
(comment

  ;; Using `recur` with the recursive function make it more memory efficient

  (defn recursive-printer
    ([]
     (recursive-printer 0))
    ([iteration]
     (println "Iteration" iteration)   ;; fixed small bug
     (if (> iteration 3)
       (println "Goodbye!")
       (recur (inc iteration)))))

  (recursive-printer)

  ) ;; End of rich comment block


;; Conceptual abstraction levels
;; transducers
;; reduce & reducing functions  (map, apply, lots of other clojure.core functions)
;; recursive function
;; loop recur



;;;; reduce

;; reduce takes a function and a collection of values

(reduce + [1 2 3 4])

;; This is the same as

(+ (+ (+ 1 2) 3) 4)


;; The reduce function works according to the following steps:

;; Apply function to first two elements, i.e. (+ 1 2)
;; Apply function to result and next element. Result of step 1 is 3, the next element is 3. So we get (+ 3 3).
;; Repeat step 2 for every remaining element in the sequence.


;; Show: eval and replace

(+ (+ (+ 1 2) 3) 4)


;; Providing an initial value to reduce
;; Apply funciton to initial value and first element of the sequence

(reduce + 15 [1 2 3 4])

(+ (+ (+ (+ 15 1) 2) 3) 4)




;;;; Refactor the body parts generator

;; Use reduce with an anonymous function,
;; an initial value of an empty vector
;; and the asymetric body parts collection

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))


(better-symmetrize-body-parts asym-hobbit-body-parts)


;; Rich comment block with redefined vars ignored
#_{:clj-kondo/ignore [:redefined-var]}
(comment

  ;; If the reducing function is large, it could be defined as a separate named function
  ;; Also useful if the reducing function should be used in other places in the code

  (defn generate-body-part [final-body-parts part]
    (into final-body-parts
          (set [part (matching-part part)])))

  (defn better-symmetrize-body-parts
    "Expects a seq of maps that have a :name and :size"
    [asym-body-parts]
    (reduce generate-body-part
            []
            asym-body-parts))

  (better-symmetrize-body-parts asym-hobbit-body-parts)


  ) ;; End of rich comment block




;;;; Determine if a body part is hit

(defn hit
  [asym-body-parts]
  (let [sym-parts          (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target             (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size   (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))



(hit asym-hobbit-body-parts)



;; What if there were more than two body parts






;;;; reduce vs apply

;; reduce
(+ (+ (+ 1 2) 3) 4)

;; apply
(apply + [1 2 3 4])

(+ 1 2 3 4)

(ns clojure-zh-dojo1-minesweeper.dojo3)

(defn minefield-1 [x n]
	(shuffle (map #(if (< % n) :* nil) (range x))))

(defn minefield-2 [x y n]
	(let [total (* x y)]
		(partition y (minefield-1 total n))))

(defn print-2 [v]
	(doseq [x v]
		(print (map (fn [y]
									(cond (= y :*) "*"
												(nil? y) " "
												:else y)) x))
		(println)))

(defn sum [& xs]
	(reduce + (map #(if (= :* %) 1 0) xs)))

(defn border-1 [coll el]
	(conj (into [el] coll) el))

(defn border-2 [v]
	(let [cnt (-> v first count)
				blanks (into [] (repeat (+ cnt 2) nil))]
		(border-1 (mapv #(border-1 % nil) v) blanks)))

(defn neighbours-or-bomb-1 [[v1 v2 v3]]
	(if (= v2 :*) :* (sum v1 v3)))

(defn partition-1 [v]
	(partition 3 1 v))

(defn solve-1 [v]
	(let [v (border-1 v nil)
				v (partition 3 1 v)]
		(mapv neighbours-or-bomb-1 v)))

(defn neighbours-or-bomb-2 [[v1 v2 v3]
														[v4 v5 v6]
														[v7 v8 v9]]
	(if (= v5 :*) :* (sum v1 v2 v3 v4 v6 v7 v8 v9)))

(defn partition-2 [v]
	(map (partition-1 v)))

(defn solve-2 [v]
	(let [v (border-2 v)]
		(mapv #(apply mapv neighbours-or-bomb-2
									(map partition-1 %)) (partition-1 v))))

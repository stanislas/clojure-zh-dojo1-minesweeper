(ns clojure-zh-dojo1-minesweeper.dojo1)

(def minefield '[[* . . .] [ . . * .] [ . . . .]])

(def directions (for [x [-1 0 1]
											y [-1 0 1]
											:when (not= [0 0] [x y]) ]
									[x y]))

(first minefield)

(defn count-neighbours [field row col]
	(reduce (fn [result [r c]]
						(let [cell (get-in field [(+ row r) (+ col c)])]
							(if (= cell '*) (inc result) result))) 0 directions))

(defn solve-row [field i row]
	(map-indexed (fn [j cell] (if (= cell '*) '* (count-neighbours field i j))) row))

(defn  solve [field]
	(map-indexed (fn [index row] (solve-row field index row)) field))

(solve minefield)
(ns clojure-zh-dojo1-minesweeper.dojo2
	(:require [clojure.string :as s]))

(defn rand-loc [m]
	[(rand-int m) (rand-int m)])

(defn game [m b]
	(let [board (vec (repeat m (vec (repeat m 0))))]
		(first (drop b
								 (iterate #(assoc-in % (rand-loc m) 1)
													board)))))

(defn find-adj [x y]
	[[x y]
	 [(dec x) y]
	 [x (dec y)]
	 [(inc x) y]
	 [x (inc y)]
	 [(inc x) (inc y)]
	 [(dec x) (dec y)]
	 [(inc x) (dec y)]
	 [(dec x) (inc y)]])

(defn count-bombs [game x y]
	(reduce + (map #(get-in game % 0) (find-adj x y))))

(defn render-line [line]
	(s/replace
		(s/replace
			(s/join (map #(str %) line)) "0" ".") "1" "*"))

(defn render-game [game]
	(print (s/join "\n" (map render-line game))))

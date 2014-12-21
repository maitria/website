(ns make-maitria.core
  (:require [clostache.parser :refer :all]
            [clojure.java.io :as io])
  
  (:gen-class))

(def pages ["index.html"
            "events/intro-to-meditation.html"])

(defn source-file
  [page]
  (str page ".mustache"))

(defn destination-file
  [page]
  (str "site/" page))

(defn generate
  [page]
  (let [partials {:header (slurp (io/resource "partials/header.mustache"))}]
    (spit (destination-file page) (render-resource (source-file page) {} partials))))

(defn -main
  [& args]
  (doseq [page pages]
    (generate page)))

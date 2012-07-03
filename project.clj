(defproject zosc "1.0.0-SNAPSHOT"
  :description "an open sound control router and scheduler for local area networks"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [overtone/osc-clj "0.8.0"]]
  :main zosc.core
  :run-aliases {:zosc zosc.core/-main})

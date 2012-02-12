(defproject compojure.route.clojurescript "0.3.5"
  :description "Easily add a route to serve compiled ClojureScript"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [compojure "1.0.1" :exclusions [org.clojure/clojure org.clojure/clojure-contrib]]
                 [ring.middleware.clojurescript "0.5.0"]])


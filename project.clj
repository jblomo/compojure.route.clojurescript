(defproject compojure.route.clojurescript "0.5.0-SNAPSHOT"
  :description "Easily add a route to serve compiled ClojureScript"
  :dependencies [[org.clojure/clojure "1.3.0-beta2"]
                 [compojure "0.6.5" :exclusions [org.clojure/clojure org.clojure/clojure-contrib]]
                 [ring.middleware.clojurescript "0.5.0-SNAPSHOT"]])


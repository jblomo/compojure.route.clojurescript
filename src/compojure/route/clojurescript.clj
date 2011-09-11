(ns compojure.route.clojurescript
  (:use [ring.middleware.clojurescript :only (wrap-clojurescript)])
  (:require [compojure.route :as route])
  (:import [java.io File]))

(defn- trim-chars
  "Trims a set of characters 'chars' from the begining and end of a string."
  [chars string]
  (apply str (->> string
                  (drop-while chars)
                  reverse
                  (drop-while chars)
                  reverse)))

(defn compiled-clojurescript
  "A route for serving the javascript generated by ClojureScript.
  
  Accepts the following options:
    :root - the root path to use for compiling: compiles .cljs file from
            root/src and puts the result in root/{out,bootstrap.js}. Defaults to
            using `path` as a relative directory."
  [path & [options]]
  (let [pure-path (trim-chars #{File/separatorChar} path)
        opts (merge {:root pure-path} options)]
    (wrap-clojurescript (route/files path opts)
                        (:root opts))))

 

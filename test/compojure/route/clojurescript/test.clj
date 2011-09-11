(ns compojure.route.clojurescript.test
  (:use compojure.route.clojurescript
        clojure.test
        compojure.core
        [compojure.route :only (not-found)])
  (:import [java.io File]))

(def bootstrap-js (File. "cljs/bootstrap.js"))
(def base-js (File. "cljs/out/goog/base.js"))

(defroutes app
           (HEAD "/" [] "")
           (GET "/" [] :response)
           (compiled-clojurescript "/cljs/")
           (not-found "not found"))

(deftest test-bootstrap-generated
  (let [{:keys [status headers body]}
         (app {:request-method :get :uri "/cljs/bootstrap.js" :headers {}})]
    (is (= 200 status))
    (is (= "text/javascript" (headers "Content-Type")))
    (is (= bootstrap-js body))))

(deftest test-goog-generated
  (let [{:keys [status headers body]}
         (app {:request-method :get :uri "/cljs/out/goog/base.js" :headers {}})]
    (is (= 200 status))
    (is (= "text/javascript" (headers "Content-Type")))
    (is (= base-js body))))

(deftest test-404
  ; make sure we pass through missing files without Exceptions
  (let [{:keys [status headers body]}
         (app {:request-method :get :uri "/cljs/fake.js" :headers {}})]
    (is (= 404 status))
    (is (= "not found" body))))

(deftest test-bad-method
  (let [{:keys [status headers body]}
         (app {:request-method :post :uri "/cljs/bootstrap.js" :headers {}})]
    (is (= 404 status))
    (is (= "not found" body))))


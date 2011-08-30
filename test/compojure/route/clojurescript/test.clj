(ns compojure.route.clojurescript.test
  (:use compojure.route.clojurescript
        clojure.test
        compojure.core))

(def bootstrap-js (File. "cljs/bootstrap.js"))
(def base-js (File. "cljs/out/goog/base.js"))

(defroutes app
           (HEAD "/" [] "")
           (GET "/" [] :response)
           (compiled-clojurescript "/cljs/"))

(deftest test-bootstrap-generated
  (let [{:keys [status headers body]}
         (app {:request-method :get :uri "/cljs/bootstrap.js" :headers {}})]
    (is (= 200 status))
    (is (= {} headers))
    (is (= bootstrap-js body))))

(deftest test-goog-generated
  (let [{:keys [status headers body]}
         (app {:request-method :get :uri "/cljs/out/goog/base.js" :headers {}})]
    (is (= 200 status))
    (is (= {} headers))
    (is (= base-js body))))

(deftest test-404
  (let [{:keys [status headers body]}
         (app {:request-method :get :uri "/cljs/fake.js" :headers {}})]
    (is (= 404 status))
    (is (= {} headers))))

(deftest test-bad-method
  (let [{:keys [status headers body]}
         (app {:request-method :post :uri "/cljs/bootstrap.js" :headers {}})]
    (is (= 200 status))
    (is (= {} headers))
    (is (= bootstrap-js body))))


(ns trialapp.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.route :as route]
            [compojure.core :refer :all]
            [ring.middleware.params :refer [wrap-params]]
            [clojure.pprint :refer :all]
            [hiccup.core :refer :all]
            [hiccup.page :refer [include-css include-js]]
            [hiccup.form :refer [form-to text-field submit-button]]))

(defn init []
  (println "test is starting"))

(defn destroy []
  (println "test is shutting down"))

(defn home []
  (html
   [:head (include-css "/bootstrap-3.3.6-dist/css/bootstrap.min.css")
    (include-css "/styles.css")
    (include-css "https://fonts.googleapis.com/icon?family=Material+Icons")
    (include-js "/bootstrap-3.3.6-dist/js/bootstrap.min.js")]
   [:body
    [:div {:class "col-lg-6" }
     [:div {:class "input-group" }
      (form-to [:post "/saveform"]
               (text-field {:class "form-control" :placeholder "How old are you?"} "age")
               [:span {:class "input-group-btn" }]
               (submit-button {:class "btn btn-default"} "Go, baby!"))]]]))


(defroutes app-routes
           (route/resources "/")
           (GET "/" [] (home))
  (POST "/saveform" req (str "Age = " (get-in req [:params "age"]))))

(def app
  (wrap-params app-routes))

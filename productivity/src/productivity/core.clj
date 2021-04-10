(ns productivity.core
  (:require [cljfx.api :as fx]
            [cljfx.css :as css]
            [clojure.core.cache :as cache]
            [productivity.elements :as elements]
            [productivity.css :as procss]
            [productivity.events :as events])
  (:import [javafx.application Platform])
  (:gen-class))

(def *context
  (atom
   (fx/create-context {:typed-name ""
                       :typed-desc ""
                       :typed-deadline ""
                       :todos [{:name "Finish App"
                                :description "Finish this app"
                                :deadline "11/04/2021"
                                :done false}]}
                      #(cache/lru-cache-factory % :threshold 4096))))

(defn -main []
  (Platform/setImplicitExit true)
  (fx/create-app *context
                 :event-handler events/event-handler
                 :desc-fn (fn [_]
                            {:fx/type :stage
                             :showing true
                             :scene {:fx/type :scene
                                     :stylesheets [(::css/url procss/style)]
                                     :root {:fx/type :v-box
                                            :style-class "root"
                                            :children [{:fx/type elements/todo-list}
                                                       {:fx/type elements/todo-input}]}}})))

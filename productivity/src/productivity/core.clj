(ns productivity.core
  (:require [cljfx.api :as fx]
            [cljfx.css :as css]
            [clojure.core.cache :as cache]
            [productivity.elements :as elements]
            [productivity.css :as procss]
            [productivity.events :as events]
            [productivity.state :as state])
  (:import [javafx.application Platform])
  (:gen-class))

(defn -main []
  (fx/create-app state/*context
                 :event-handler events/event-handler
                 :desc-fn elements/root))

(ns productivity.state
  (:require [cljfx.api :as fx]
            [clojure.core.cache :as cache]))

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
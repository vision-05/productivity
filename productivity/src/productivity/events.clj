(ns productivity.events
  (:require [cljfx.api :as fx]))

(defmulti event-handler)
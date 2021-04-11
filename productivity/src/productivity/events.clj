(ns productivity.events
  (:require [cljfx.api :as fx]))

(defmulti event-handler :event/type)

(defmethod event-handler :default [e]
  (println e))

(defmethod event-handler ::name-typed [{:keys [fx/event fx/context]}]
  {:context (fx/swap-context context assoc :typed-name event)})

(defmethod event-handler ::description-typed [{:keys [fx/event fx/context]}]
  {:context (fx/swap-context context assoc :typed-desc event)})

(defmethod event-handler ::deadline-typed [{:keys [fx/event fx/context]}]
  {:context (fx/swap-context context assoc :typed-deadline event)})

(defmethod event-handler ::confirm-clicked [{:keys [fx/event fx/context]}] ;get this to reset text inputs
  (let [name-temp (fx/sub-val context :typed-name)
        desc-temp (fx/sub-val context :typed-desc)
        deadline-temp (fx/sub-val context :typed-deadline)
        ctx-noconfirm {:context context}]
    (cond
      (= name-temp "")
      ctx-noconfirm
      (= desc-temp "")
      ctx-noconfirm
      (= deadline-temp "")
      ctx-noconfirm
      :else (let [temp-ctx (fx/swap-context context
                                            update-in
                                            [:todos]
                                            conj
                                            {:name (fx/sub-val context :typed-name)
                                             :description (fx/sub-val context :typed-desc)
                                             :deadline (fx/sub-val context :typed-deadline)
                                             :done false})]
              {:context (fx/swap-context (fx/swap-context (fx/swap-context temp-ctx
                                                                           assoc
                                                                           :typed-deadline
                                                                           "")
                                                          assoc
                                                          :typed-name
                                                          "")
                                         assoc
                                         :typed-desc
                                         "")}))))

(defmethod event-handler ::element-done [{:keys [fx/event fx/context]}])
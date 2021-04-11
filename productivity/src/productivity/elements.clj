(ns productivity.elements
  (:require [cljfx.api :as fx]
            [cljfx.css :as css]
            [productivity.events]))

(defn todo-element [{:keys [fx/context name description deadline done]}]
  (str name \newline description \newline deadline))

(defn todo-list [{:keys [fx/context]}]
  {:fx/type :list-view
   :style-class "root-todolist"
   :items (->> (fx/sub-val context :todos)
               (sort-by :done)
               (map #(todo-element %)))
})

(defn todo-input [{:keys [fx/context]}]
  {:fx/type :v-box
   :children [{:fx/type :text-field
               :style-class "root-inputfield"
               :cursor :text
               :on-text-changed {:event/type :productivity.events/name-typed}}
              {:fx/type :text-field
               :style-class "root-inputfield"
               :cursor :text
               :on-text-changed {:event/type :productivity.events/description-typed}}
              {:fx/type :text-field
               :style-class "root-inputfield"
               :cursor :text
               :on-text-changed {:event/type :productivity.events/deadline-typed}}
              {:fx/type :button
               :style-class "root-inputfield"
               :text "Add"
               :alignment :center
               :min-width 300
               :min-height 100
               :v-box/vgrow :always
               :on-mouse-clicked {:event/type :productivity.events/confirm-clicked}}]})
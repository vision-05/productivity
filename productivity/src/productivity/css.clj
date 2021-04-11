(ns productivity.css
  (:require [cljfx.css :as css]))

(def style
  (css/register ::style
                (let [background-color-dark "#2E3440"
                      background-color-light "#434C5E"
                      text-color "#ECEFF4"]
                  {".root" {:-fx-background-color background-color-dark
                            "-todolist" {:-fx-control-inner-background background-color-dark}
                            "-inputfield" {:-fx-background-color background-color-dark
                                           :-fx-border-color background-color-light
                                           :-fx-text-fill text-color}}})))
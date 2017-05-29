(ns cljs-storybook.core
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [cljs-storybook.components :as components]
            cljsjs.react))

(enable-console-print!)

(def ^:export canvas-example (om/factory components/CanvasExample))
(def ^:export hello (om/factory components/Hello))
(def ^:export data {:name "Hello"})

;(js/ReactDOM.render
;  (hello {:name "TESTING"}) js/app)
(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

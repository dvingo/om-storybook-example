(ns cljs-storybook.core
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            cljsjs.react))

(enable-console-print!)

(defui ^:export Hello
  Object
  (render [this]
    (let [{:keys [name]} (om/props this)]
      (dom/div nil (str " your name is: " name)))))

(def ^:export hello (om/factory Hello))

(js/ReactDOM.render
  (hello {:name "TESTING"}) js/app)
(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

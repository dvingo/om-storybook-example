(ns cljs-storybook.components
  (:require
    [om.next :as om :refer-macros [defui]]
    [om.dom :as dom]
    cljsjs.react))

(defn assign-ref [this ref]
  (fn [el]
    (aset this ref el)))

(defui CanvasExample
       Object
       (componentDidMount [this]
                          (let [canvas (aget this "canvas")
                                ctx (.getContext canvas "2d")
                                _ (aset this "ctx" ctx)]
                            (aset ctx "fillStyle" "green")
                            (-> ctx
                                (.fillRect 10 10 100 100))))
  (render [this]
    (dom/canvas #js {:ref (assign-ref this "canvas")})))

(defui Hello
       Object
       (render [this]
               (let [{:keys [name]} (om/props this)]
                 (dom/div nil (str " your name is: " name)))))
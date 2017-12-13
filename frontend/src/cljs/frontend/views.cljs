(ns frontend.views
  (:require [re-frame.core :as re-frame]
            [frontend.subs :as subs]
            [frontend.events :as events]
            ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:p "Hello from " @name]
     [:button {:on-click #(re-frame/dispatch [::events/message])} "Get Message"]]))

(ns frontend.views
  (:require [re-frame.core :as re-frame]
            [frontend.subs :as subs]
            [frontend.events :as events]
            ))

(def not-nil? (complement nil?))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        message (re-frame/subscribe [::subs/message])]
    [:div
     [:p "Hello from " @name]
     [:button {:on-click #(re-frame/dispatch [::events/message])} "Get Message"]
     (if (not-nil? @message)
       [:p @message] )]))

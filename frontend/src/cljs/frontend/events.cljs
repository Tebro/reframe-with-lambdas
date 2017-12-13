(ns frontend.events
  (:require [re-frame.core :as re-frame]
            [cljsjs.aws-sdk-js]
            [frontend.lambda]
            [frontend.db :as db]
            [frontend.config :as config]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))


(re-frame/reg-event-fx
 ::message
 (fn [_ _]
   {:invoke-lambda {:function (:hello config/lambda-functions)
                    :payload {:key "value"}
                    :on-data ::get-data
                    :on-error ::get-error}}))

(re-frame/reg-event-db
 ::get-data
 (fn [db [_ data]]
   (assoc db :message (:message data))))

(re-frame/reg-event-fx
 ::get-error
 (fn [_ [_ err]]
   js/console.log err))

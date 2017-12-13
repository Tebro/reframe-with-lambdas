(ns frontend.lambda
  (:require [re-frame.core :as re-frame]
            [cljsjs.aws-sdk-js]
            [frontend.config :as config]))

(defn console-callback [err data]
  (if (nil? err)
    (js/console.log data)
    (js/console.log err)))

(defn set-aws-config []
  (set! (.. js/AWS -config -region ) config/region)
  (if (nil? (.. js/AWS -config -credentials))
    (set!
     (.. js/AWS -config -credentials)
     (js/AWS.CognitoIdentityCredentials. (clj->js {"IdentityPoolId" config/identity-pool-id}))))
  (if (js/AWS.config.credentials.needsRefresh)
    (js/AWS.config.credentials.refresh console-callback)))


(re-frame/reg-fx
 :invoke-lambda
 (fn [{:keys [function payload on-data on-error]}]
   (set-aws-config)
   (let [lambda (js/AWS.Lambda.)]
     (.invoke lambda
              (clj->js {
                        "FunctionName" function
                        "Payload" (js/JSON.stringify (clj->js payload))
                        })
              (fn [err data]
                (if (nil? err)
                  (re-frame/dispatch [on-data (js->clj (js/JSON.parse data.Payload) :keywordize-keys true)])
                  (re-frame/dispatch [on-error err])))))))

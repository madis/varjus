(ns varjus.server.core
  (:require [cljs.nodejs :as nodejs]
            [cljs.reader :refer [read-string]]
            [mount.core :as mount :refer [defstate]]
            [taoensso.timbre :as log]))

(nodejs/enable-util-print!)

(def express (nodejs/require "express"))

; (declare start stop)

; (defstate ^{:on-reload :noop} (atom)
;   :start (start {})
;   :stop (stop {}))

; (defn start [opts]
;   )

; (defn stop [graphql]
;   (log/info "Stopping..."))

(defn -main [& _]
  (log/info "Initializing Server...")
  (let [app (doto (express)
              (.use (js-invoke express "static" "public")))
        port (or js/process.env.PORT 3000)]
    (js-invoke app "listen" port (fn [] (log/info (str "Server running on port " port))))))

(set! *main-cli-fn* -main)

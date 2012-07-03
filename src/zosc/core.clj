(ns zosc.core
  (:use [overtone.osc])
  (:gen-class))

(def offset 50)
(def forwarding-port 8000)
(def local-port 9000)
(def multicast-port 10000)
(def multicast-ip "localhost") ;;fixme
(def forwarding-server (osc-server forwarding-port))
(def multicast-server (osc-server multicast-port))
(def multicast-client (osc-client multicast-ip multicast-port))
(def local-client (osc-client "localhost" local-port))

(osc-listen forwarding-server
            (fn [msg]
              (println "forwarding-server: " msg)
              (in-osc-bundle multicast-client (+ (osc-now) offset)
                             (osc-send-msg multicast-client {:path (get msg :path)
                                                             :type-tag (get msg :type-tag)
                                                             :args (get msg :args)})))
            :forwarding)

(osc-listen multicast-server
            (fn [msg]
              (println "multicast-server: " msg)
              (osc-send-msg local-client {:path (get msg :path)
                                          :type-tag (get msg :type-tag)
                                          :args (get msg :args)}))
            :mulicast)

(osc-debug)

(defn -main []
    (println "zosc has started"))

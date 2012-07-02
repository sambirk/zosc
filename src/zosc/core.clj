(ns zosc.core
  (:use [overtone.osc]))

(def offset 50)
(def forwarding-port 8000)
(def local-port 9000)
(def multicast-port 10000)
(def multicast-ip "224.0.0.1")
(def forwarding-server (osc-server forwarding-port))
(def multicast-server (osc-server multicast-port))
(def multicast-client (osc-client multicast-ip multicast-port))
(def local-client (osc-client "localhost" local-port))
z
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

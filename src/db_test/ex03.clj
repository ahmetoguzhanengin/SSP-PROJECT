(ns db-test.ex03
  (:require [datomic.client.api :as d]))

;;Örnek data tanımlaması yapımı

(def client (d/client {:server-type :dev-local
                       :storage-dir "/Users/ahmetoguzhanengin/Desktop/MyRepos/MyCodes/db"
                       :system      "ci"}))

(def conn (d/connect client {:db-name "SSP"}))

(def db (d/db conn))

(def sample-types [{:type/id 1
                    :type/name "SAP"}
                   {:type/id 2
                    :type/name "Automation"}])

(d/transact conn {:tx-data sample-types})

; :tx-data [#datom[13194139533325 50 #inst"2023-11-27T07:28:23.551-00:00" 13194139533325 true]
;           #datom[87960930222187 96 1 13194139533325 true]
;           #datom[87960930222187 97 "SAP" 13194139533325 true]
;           #datom[87960930222188 96 2 13194139533325 true]
;           #datom[87960930222188 97 "Automation" 13194139533325 true]],
; :tempids {}}


(def sample-supplier [{:supplier/id 1
                       :supplier/name "Sabanci Holding"
                       :supplier/types [92358976733292 92358976733293]}
                      {:supplier/id 2
                       :supplier/name "Koc Holding"
                       :supplier/types [92358976733292 92358976733293]}])

(d/transact conn {:tx-data sample-supplier})

;; :tx-data [#datom[13194139533326 50 #inst"2023-11-27T07:29:16.385-00:00" 13194139533326 true]
;           #datom[79164837199981 73 1 13194139533326 true]
;           #datom[79164837199981 74 "Sabanci Holding" 13194139533326 true]
;           #datom[79164837199981 75 92358976733292 13194139533326 true]
;           #datom[79164837199981 75 92358976733293 13194139533326 true]
;           #datom[79164837199982 73 2 13194139533326 true]
;           #datom[79164837199982 74 "Koc Holding" 13194139533326 true]
;           #datom[79164837199982 75 92358976733292 13194139533326 true]
;           #datom[79164837199982 75 92358976733293 13194139533326 true]],
; :tempids {}}

(d/q '[:find ?name
       :where [79164837199982 :supplier/types ?name]] db)




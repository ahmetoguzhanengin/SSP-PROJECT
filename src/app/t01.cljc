(ns app.t01
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.history :as router]
            #?(:clj [datomic.client.api :as dt])))

(e/def conn)
(e/def db)
(e/defn Test []
  (e/server
    (binding [conn @(requiring-resolve 'user/datomic-conn)]
      (binding [db (dt/db conn)]
        #_(dt/transact conn {:tx-data [{:db/ident :test/test
                                        :db/valueType :db.type/string
                                        :db/cardinality :db.cardinality/one}]})
        (e/client
          (dom/text "Test1 Hello")
          (dom/div
            (dom/text (e/server (dt/q '[:find (pull ?e [*])
                                        :where [?e :db/ident :test/test]] db)))))))))

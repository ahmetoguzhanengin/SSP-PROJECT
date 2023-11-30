(ns db-test.e01
  (:require [datomic.client.api :as d]
          [java-time.api :as jt]))

(def client (d/client {:server-type :dev-local
                       :storage-dir "/Users/ahmetoguzhanengin/Desktop/MyRepos/MyCodes/db"
                       :system      "ci"}))


#_(d/create-database client {:db-name "SSP"})

(def conn (d/connect client {:db-name "SSP"}))

(def db (d/db conn))

(def supplier-schema [{:db/ident       :supplier/id
                       :db/valueType   :db.type/long
                       :db/unique      :db.unique/identity
                       :db/cardinality :db.cardinality/one}
                      {:db/ident       :supplier/name
                       :db/valueType   :db.type/string
                       :db/unique      :db.unique/identity
                       :db/cardinality :db.cardinality/one}
                      {:db/ident       :supplier/type
                       :db/valueType   :db.type/ref
                       :db/cardinality :db.cardinality/many}
                      {:db/ident       :supplier/projects
                       :db/valueType   :db.type/ref
                       :db/cardinality :db.cardinality/many}])

(def customer-schema [{:db/ident       :customer/id
                       :db/valueType   :db.type/long
                       :db/unique      :db.unique/identity
                       :db/cardinality :db.cardinality/one}
                      {:db/ident       :customer/name
                       :db/valueType   :db.type/string
                       :db/unique      :db.unique/identity
                       :db/cardinality :db.cardinality/one}])

(def proposer-schema [{:db/ident :proposer/id
                       :db/valueType :db.type/long
                       :db/unique :db.unique/identity
                       :db/cardinality :db.cardinality/one}
                      {:db/ident :proposer/supplier
                       :db/valueType :db.type/ref
                       :db/cardinality :db.cardinality/one}
                      {:db/ident :proposer/price
                       :db/valueType :db.type/float
                       :db/cardinality :db.cardinality/one}])




(ns db-test.db-setup
  (:require [datomic.client.api :as dt]))

(def client (dt/client {:server-type :dev-local
                        :storage-dir "/Users/ahmetoguzhanengin/Desktop/MyRepos/MyCodes/db"
                        :system      "ci"}))


(dt/create-database client {:db-name "test"})
(def conn (dt/connect client {:db-name "test"}))

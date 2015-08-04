(ns mrmcc3.boot-electron
  (:require [boot.core :refer :all]
            [boot.util :refer :all]))

(deftask electron
  "downloads latest electron into a tmp directory"
  [r run bool "run electron in the target directory"]
  (let [tmp (tmp-dir!)
        state (atom {:installed false :running false})]
    (comp
     (with-pre-wrap fs
       (when-not (:installed @state)
         (info "\nInstalling electron ...\n")
         (binding [*sh-dir* (.getPath tmp)]
           (dosh "npm" "install" "electron-prebuilt"))
         (swap! state assoc :installed true))
       fs)
     (with-post-wrap fs
       (when (and run (-> @state :running not))
         (info "\nRunning electron ...\n")
         (let [cmd (str (.getPath tmp) "/node_modules/.bin/electron")]
           (sh cmd (get-env :target-path)))
         (swap! state assoc :running true))
       fs))))

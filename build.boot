(set-env!
 :source-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.7.0" :scope "provided"]
                 [boot/core "2.2.0" :scope "provided"]
                 [adzerk/bootlaces "0.1.11" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0-SNAPSHOT")

(bootlaces! +version+)

(task-options!
  pom {:project     'mrmcc3/boot-electron
       :version     +version+
       :description "Boot task for installing and running electron (via npm)"
       :url         "https://github.com/mrmcc3/boot-electron"
       :scm         {:url "https://github.com/mrmcc3/boot-electron"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})

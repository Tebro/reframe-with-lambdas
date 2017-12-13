(ns frontend.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [frontend.core-test]))

(doo-tests 'frontend.core-test)

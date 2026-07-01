(ns engineer-render-test
  "Restoration-fidelity tests — one per original kami-eng-render Rust test
  (kami-engine/kami-eng-render/src/lib.rs `mod tests`, deleted PR #82)."
  (:require [clojure.test :refer [deftest is testing]]
            [engineer-render]
            [engineer-render.color-map :as color-map]
            [engineer-render.viewport :as viewport]
            [engineer-render.draw-list :as draw-list]))

(deftest namespace-loads
  (testing "the restored CLJC namespace loads"
    (is (some? (the-ns 'engineer-render)))))

;; mirrors `color_map_jet_endpoints`
(deftest color-map-jet-endpoints
  (let [low (color-map/sample :jet 0.0)
        high (color-map/sample :jet 1.0)]
    (is (>= (nth low 2) 0.5))   ; blue at low end
    (is (>= (nth high 0) 0.5)))) ; red at high end

;; mirrors `viewport_screen_world_roundtrip`
(deftest viewport-screen-world-roundtrip
  (let [vp (viewport/viewport 800.0 600.0)
        world [10.0 20.0]
        screen (viewport/world->screen vp world)
        back (viewport/screen->world vp screen)]
    (is (< (Math/abs (- (first back) (first world))) 1e-5))
    (is (< (Math/abs (- (second back) (second world))) 1e-5))))

;; draw-list buffer sanity (no direct Rust test existed for EngDrawList, but
;; push/clear/len/is_empty are public API surface — covered for completeness)
(deftest draw-list-buffer
  (let [dl (-> (draw-list/draw-list)
               (draw-list/push {:cmd :line :start [0.0 0.0] :end [1.0 1.0]
                                :width 0.1 :color [1.0 1.0 1.0 1.0] :layer 0}))]
    (is (= 1 (draw-list/draw-count dl)))
    (is (not (draw-list/draw-empty? dl)))
    (is (= 0 (draw-list/draw-count (draw-list/clear dl))))))

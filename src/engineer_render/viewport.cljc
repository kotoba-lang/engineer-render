(ns engineer-render.viewport
  "2D engineering viewport: pan/zoom + screen<->world coordinate transforms.
  Restored from kami-eng-render's `EngViewport` (deleted PR #82). Points are
  `[x y]` 2-vectors (glam::Vec2 in the original).")

(defn viewport
  "A fresh viewport at `[width height]` pixels, centered at origin, zoom 1."
  [width height]
  {:center [0.0 0.0] :zoom 1.0 :rotation 0.0 :width width :height height})

(defn screen->world
  "Convert screen-space `[sx sy]` to world-space, honoring the viewport's
  center/zoom (rotation is not yet applied — matches the original, which
  also ignored `:rotation` in this transform)."
  [{:keys [center zoom width height]} [sx sy]]
  (let [[cx cy] center
        wx (+ (/ (- sx (/ width 2.0)) zoom) cx)
        wy (+ (/ (- sy (/ height 2.0)) zoom) cy)]
    [wx wy]))

(defn world->screen
  "Convert world-space `[wx wy]` to screen-space, the inverse of `screen->world`."
  [{:keys [center zoom width height]} [wx wy]]
  (let [[cx cy] center
        sx (+ (* (- wx cx) zoom) (/ width 2.0))
        sy (+ (* (- wy cy) zoom) (/ height 2.0))]
    [sx sy]))

(defn zoom-to-fit
  "Recenter + rezoom the viewport so the world-space bounding box `[min max]`
  fits with a 10% margin (0.9 fill factor, matching the original)."
  [{:keys [width height] :as vp} [minx miny] [maxx maxy]]
  (let [cx (/ (+ minx maxx) 2.0)
        cy (/ (+ miny maxy) 2.0)
        ex (- maxx minx)
        ey (- maxy miny)
        zoom-x (/ width ex)
        zoom-y (/ height ey)]
    (assoc vp :center [cx cy] :zoom (* (min zoom-x zoom-y) 0.9))))

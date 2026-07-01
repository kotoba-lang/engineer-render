(ns engineer-render
  "Zero-dep portable CLJC. Restored from the legacy kami-engine/kami-eng-* Rust
  crates (deleted in kotoba-lang/kami-engine #82 'Remove Rust workspace from
  kami-engine') as part of the clj-wgsl migration (ADR-2607010930,
  com-junkawasaki/root). Native execution stays substrate; this namespace
  owns the CLJC contracts / data interpreters / EDN IR for the domain.

  2D/3D engineering rendering: schematic lines, pads, dimension annotations, hatching, grid overlay, ruler, crosshair cursor. Depends on kotoba-lang/engineer for shared contracts.")

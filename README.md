# kotoba-lang/engineer-render

Zero-dep portable `.cljc` — restored from the legacy `kami-engine/kami-eng-*`
Rust crates (deleted in kotoba-lang/kami-engine PR #82 "Remove Rust workspace
from kami-engine") as part of the **clj-wgsl migration** (ADR-2607010930,
`com-junkawasaki/root`).

2D/3D engineering rendering: schematic lines, pads, dimension annotations, hatching, grid overlay, ruler, crosshair cursor. Depends on kotoba-lang/engineer for shared contracts.

## Status

Scaffold only — the CLJC restoration is pending. This repo provides the home
for the zero-dep portable `.cljc` contracts / data interpreters / EDN IR
that replace the deleted Rust crate. Native execution (wgpu / wasmtime /
wasmi), where needed, stays substrate.

## Develop

```bash
clojure -M:test
```

# MASHPEDITION Pigment Catalog Auditor v2.3 — 9 Mudbuckets

Purpose: visually audit all 711 named Pigments while resolving Mudbucket recipes into the established 3×3 Mudbucket system.

## Classification rule

Every pigment must resolve in exactly one of two ways:

1. One original classification: `Looks right`, `Name wrong`, or `Misspelling`.
2. Mudbucket: one Lightness choice **and** one Color Family choice.

Mudbucket Lightness choices:
- Light
- Regular
- Dark

Mudbucket Color Family choices:
- Grey
- Green
- Brown

Together these produce the nine buckets:
- Light Grey / Grey / Dark Grey
- Light Green / Green / Dark Green
- Light Brown / Brown / Dark Brown

A pigment with only one Mudbucket dimension selected is visibly marked **Incomplete** and can be filtered with `Incomplete Mudbucket`.

## Preloaded current audit

The current reviewed state is embedded as the starting point:
- 499 Looks Right
- 212 Mudbucket
- 0 Name Wrong
- 0 Misspelling
- 0 incomplete
- 0 unreviewed

All 212 Mudbucket colors are preloaded with the nine-bucket assignments from `MASHPEDITION_Pigment_Audit_2026-09-07_9_Mudbuckets.csv`.

## Browser storage migration

v2.3 stores its expanded state under `mashpeditionPigmentAuditV23`.

On first load it starts from the embedded current audit and, when the earlier `mashpeditionPigmentAudit` key exists, migrates those saved classifications on top of the embedded data. Existing legacy `Mudbucket` marks receive their precomputed Lightness/Family assignment when available. The legacy key is also kept synchronized at the classification level for backward compatibility.

## Export

**Export audit** now writes:

`# | Pigment Name | Recipe | HEX | Classification | Mud Lightness | Mud Family | Mudbucket`

For a complete Mudbucket color, the final column contains the resolved bucket such as `Light Brown`, `Dark Green`, or `Grey`. Partial Mudbucket choices are preserved in the two component columns while the combined Mudbucket column remains blank.

## Existing behavior preserved

- Uses the established playable primary inputs: Red `#FF2E5B`, Yellow `#FFEE00`, Blue `#0033FF`, White `#FFFFFF`, Black `#000000`.
- Uses Mixbox multi-color latent-space weighting so recipe part counts are honored directly.
- Displays each swatch with Pigment Name, exact R/Y/B/W/K recipe, generated HEX, and catalog number.
- Primary-color Include/Exclude filters remain available.
- Search and category filtering remain available.
- The catalog data is embedded directly in `index.html`; no spreadsheet upload is required at runtime.

Mixbox is loaded from `https://scrtwpns.com/mixbox.js`, so the page needs internet access when opened.

Source catalog: `MASHpedition_color_workbook_Grey_Name_Lookup Post Suggestions Complete.xlsm`.
Mudbucket assignments: `MASHPEDITION_Pigment_Audit_2026-09-07_9_Mudbuckets.csv`.

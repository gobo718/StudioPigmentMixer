# MASHPEDITION Pigment Catalog Auditor

Purpose: visually audit all 711 named Pigments from the completed Mix Order catalog.

- Uses the established playable primary inputs: Red `#FF2E5B`, Yellow `#FFEE00`, Blue `#0033FF`, White `#FFFFFF`, Black `#000000`.
- Uses Mixbox multi-color latent-space weighting so recipe part counts are honored directly.
- Displays each swatch with Pigment Name, exact R/Y/B/W/K recipe, generated HEX, and catalog number.
- `✓ Looks right` and `? Name wrong` marks are saved in browser localStorage.
- Filters: All, Needs review, Approved, Unreviewed. Search accepts names, recipe text, catalog number, or HEX.
- The catalog data is embedded directly in `index.html`; no spreadsheet upload is required at runtime.

Mixbox is loaded from `https://scrtwpns.com/mixbox.js`. This audit build therefore needs internet access when the page is opened. Mixbox evaluation is available under its stated noncommercial evaluation terms; commercial launch requires the appropriate Mixbox license.

Source catalog: `MASHpedition_color_workbook_Grey_Name_Lookup Complete.xlsm`, Mix Order rows 5–715 (711 unique named recipes).

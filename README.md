# MASHPEDITION Pigment Catalog Auditor

Purpose: visually audit all 711 named Pigments from the completed Mix Order catalog.

- Uses the established playable primary inputs: Red `#FF2E5B`, Yellow `#FFEE00`, Blue `#0033FF`, White `#FFFFFF`, Black `#000000`.
- Uses Mixbox multi-color latent-space weighting so recipe part counts are honored directly.
- Displays each swatch with Pigment Name, exact R/Y/B/W/K recipe, generated HEX, and catalog number.
- Four mutually exclusive audit buckets are saved in browser localStorage: `Looks right`, `Mudbucket`, `Name wrong`, and `Misspelling`.
- Primary-color filters provide separate Include and Exclude checkboxes for Red, Yellow, Blue, White, and Black. Include means a recipe must contain that primary; Exclude means it must not contain it. Selecting one automatically clears the opposite choice for the same primary.
- Category filter supports All, each audit bucket, and Unreviewed. Search accepts names, recipe text, catalog number, or HEX.
- Existing marks from the earlier two-bucket build are migrated automatically: Approved → Looks right; Needs review → Name wrong.
- The catalog data is embedded directly in `index.html`; no spreadsheet upload is required at runtime.

Mixbox is loaded from `https://scrtwpns.com/mixbox.js`. This audit build therefore needs internet access when the page is opened. Mixbox evaluation is available under its stated noncommercial evaluation terms; commercial launch requires the appropriate Mixbox license.

Source catalog: `MASHpedition_color_workbook_Grey_Name_Lookup Complete.xlsm`, Mix Order rows 5–715 (711 unique named recipes).

# StudioPigmentMixer

Standalone prototype for experimenting with MASHPEDITION Studio Pigment mixing.

Current prototype:
- Black, Blue, Red, Yellow, and White Pigments.
- Drop-based mixing with live preview.
- RGB/HSL diagnostics.
- Experimental earthy naming buckets for muddy mixtures.

This is a test harness, not the final Studio unlock progression.

Terminology:
- Pigments are the color materials.
- Effects/finishes such as Glitter are separate unlockables and are not represented here yet.


## Automatic color naming

The mixer now converts each mixed RGB result to HEX and queries:

`https://api.color.pizza/v1/?values=HEX`

If a color name is returned, the UI displays:

`Color Name — #HEX`

If the API request fails, the mixer continues working and falls back to the local earthy classification.

A small in-memory cache prevents repeat API calls for the same HEX value during a session.


## Conventional local color naming
Color Pizza has been removed. The prototype now names mixed colors locally against the conventional HTML/CSS color vocabulary used as one of ColorNameAssistant's selectable color lists. Matching is performed in perceptual Lab space rather than by an external naming API.

This keeps basic names conventional (Yellow is Yellow, Red is Red, etc.), removes the network/API dependency, and avoids novelty-name results.


## Exact base Pigments
The five starting Pigments now use exact canonical RGB/HEX values:

- Red — #FF0000
- Blue — #0000FF
- Yellow — #FFFF00
- White — #FFFFFF
- Black — #000000


## Spectral pigment-mixing engine
The previous RGB-average mixer has been removed.

The prototype now uses Spectral.js 3.0.0, a Kubelka–Munk-based spectral pigment-mixing engine. All active Pigments go through one general `mixPigments()` path. There are no hard-coded Red+Yellow, Yellow+Blue, or Blue+Red output colors.

Starting Pigment display values remain exact:
- Red — `#FF0000`
- Blue — `#0000FF`
- Yellow — `#FFFF00`
- White — `#FFFFFF`
- Black — `#000000`

Red uses a lower pigment tinting-strength value (0.35) because pure sRGB red is optically dominant in the spectral model; Spectral.js itself documents this calibration and shows that it moves a 50/50 Red+Yellow mix from red-orange toward normal artist orange.

### Carry-forward design
A mixed color is a real Spectral.js `Color` object, not a special-case secondary. The same `mixPigments()` function accepts Spectral.js Color objects, so later unlocked mixed Pigments can be carried forward through the same pigment model rather than receiving pair-specific rules.

The app intentionally does not fall back to RGB averaging if the spectral library fails to load; it reports that pigment mixing is unavailable instead of returning a misleading color.


## Calibrated optical pigment profiles
The five starting jars now separate **display identity** from **mixing behavior**.

The player-facing jar colors remain exact:
- Red `#FF0000`
- Blue `#0000FF`
- Yellow `#FFFF00`
- White `#FFFFFF`
- Black `#000000`

The hidden chromatic optical profiles supplied to Spectral.js are:
- Red `#E53166`
- Blue `#3375DA`
- Yellow `#FCF046`

These three chromatic profiles are taken from Spectral.js's own multi-pigment example set. They are used only as physically-informed optical starting profiles; they do not change the jar's visible canonical color.

There are **no Red+Yellow, Yellow+Blue, or Blue+Red result overrides**. All pair and later multi-Pigment mixtures run through the same `spectral.mix()` / Kubelka–Munk path. A resulting Spectral.js `Color` object can be reused as an input later, so secondaries and later mixtures carry their actual spectral state forward rather than becoming special-case labels.

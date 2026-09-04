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


## Verified calibrated-profile build
This build separates each starting Pigment's visible jar color from the hidden optical profile used for mixing.

Visible jar colors:
- Red `#FF0000`
- Blue `#0000FF`
- Yellow `#FFFF00`
- White `#FFFFFF`
- Black `#000000`

Hidden Spectral.js mixing profiles:
- Red `#E53166`
- Blue `#3375DA`
- Yellow `#FCF046`
- White `#FFFFFF`
- Black `#000000`

There are no pair-specific Orange, Green, or Purple output rules. All mixtures use the same Spectral.js/Kubelka–Munk `spectral.mix()` path, allowing mixed pigment state to carry forward into later mixtures.


## Shared-primary calibration

Visible jars remain:
- Red `#FF0000`
- Yellow `#FFFF00`
- Blue `#0000FF`

Shared hidden mixing profiles found by simultaneous numerical search:
- Red `#DE610F`
- Yellow `#F1FF00`
- Blue `#0B00F1`

There is only one hidden profile per primary. The same Red is used with Yellow and Blue; the same Yellow is used with Red and Blue; the same Blue is used with Yellow and Red.

### General-mixer validation
- Red + Yellow → `#CB8B0F` RGB(203, 139, 15)
- Yellow + Blue → `#068006` RGB(6, 128, 6)
- Blue + Red → `#744B80` RGB(116, 75, 128)

Validation checks color family and saturation rather than requiring an exact preselected secondary HEX. The active mixing engine contains no Orange, Green, or Purple target HEX values and no pair-specific output branches.

### Carry-forward
Stored Red+Yellow mixture + Blue → `#7B874E`. Direct Red+Yellow+Blue → `#7B874E`. They are identical because the stored mixture retains its RYB state and original part count.

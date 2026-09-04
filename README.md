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

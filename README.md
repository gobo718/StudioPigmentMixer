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

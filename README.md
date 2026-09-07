# Studio Pigment Mixer — Pair-by-Pair Transition Auditor

This is the next-step validator for the current Studio Pigment Mixer classifier.

It uses the actual Mixbox JavaScript runtime and audits nine specific paths at 1% increments:

Primary loops:
- Red ↔ Yellow
- Yellow ↔ Blue
- Blue ↔ Red

White dilution paths:
- Red ↔ White
- Yellow ↔ White
- Blue ↔ White

Black shade paths:
- Red ↔ Black
- Yellow ↔ Black
- Blue ↔ Black

For every 0:100 through 100:0 step it records:
- exact Mixbox HEX
- H / S / L
- classifier type and name
- unlock jar ID
- every classification boundary crossing

Current classifier order remains:
Premium → Clean → Earthy

Important:
- No output color is forced, corrected, or hard-coded.
- Mixbox remains the actual mixing engine.
- This package is a validator, not a replacement for the main StudioPigmentMixer.

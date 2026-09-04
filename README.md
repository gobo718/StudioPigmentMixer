# Studio Pigment Mixer — Named Primary Ladders

This test build expands all three primary slots with intermediate candidate shades.

Important:
- The exact HEX displayed for each selected primary is the exact RGB value passed into Mixbox.
- There is no hidden display color or hidden mixing profile.
- Mix results are named using a local conventional color-name vocabulary.
- Intermediate shades are clearly labeled as test shades; they are not falsely claimed to be named physical pigments.
- Official Mixbox pigment endpoints are retained where used.

Primary ladders:
Red/Magenta:
- Cadmium Red #FF2702
- #E9250D
- #D32318
- #BD2123
- #A71F2E
- #911D39
- Quinacridone Magenta #80022E

Yellow:
- Cadmium Yellow #FEEC00
- #FDE200
- Hansa Yellow #FCD300
- #EFC400
- #DFAF00

Blue:
- Ultramarine Blue #190059
- #14086A
- #0D1278
- Cobalt Blue #002185
- #053269
- #0A4357
- Phthalo Blue #0D1B44

Every selected trio is mixed using:
mixbox.lerp(colorA, colorB, 0.5)

The systematic sweep tests all:
7 × 5 × 7 = 245 primary trios.

Each resulting Red+Yellow, Yellow+Blue, and Blue+Red mix displays:
- swatch
- conventional nearest color name
- exact HEX
- HSL on the selected-trio cards

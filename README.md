# Studio Pigment Mixer — Actual Mixbox Primary Test

This is an evaluation/test harness around the official Mixbox JavaScript library.

The page loads:
https://scrtwpns.com/mixbox.js

It uses Mixbox exactly through:
mixbox.lerp(colorA, colorB, 0.5)

Displayed pigment HEX values are the exact RGB inputs sent to Mixbox. There are no hidden input profiles and no pair-specific replacement values.

Pigment RGB values included in the selector are copied from the official Mixbox pigment table:
- Cadmium Yellow #FEEC00
- Hansa Yellow #FCD300
- Cadmium Orange #FF6900
- Cadmium Red #FF2702
- Quinacridone Magenta #80022E
- Cobalt Violet #4E0042
- Ultramarine Blue #190059
- Cobalt Blue #002185
- Phthalo Blue #0D1B44
- Phthalo Green #003C32
- Permanent Green #076D16
- Sap Green #6B9404
- Burnt Sienna #7B4800

The systematic sweep tests:
2 Red/Magenta choices × 2 Yellow choices × 3 Blue choices = 12 primary trios.

For each trio it calculates:
- Red/Magenta + Yellow
- Yellow + Blue
- Blue + Red/Magenta

The score is only a rough sorting aid based on secondary hue and saturation. The actual HEX swatches/results remain visible for human judgment.

Licensing note:
Mixbox's site states that evaluation is available under CC BY-NC 4.0 and that commercial use requires a commercial license.

package game.paint;

import java.util.List;
import java.util.ArrayList;

public class ColorMixer {

    private final List<PremiumWindow> premiumDatabase = new ArrayList<>();
    private final List<CleanJarWindow> cleanColorDatabase = new ArrayList<>();

    public ColorMixer() {
        // FUNNEL LAYER 1: Custom-tuned Premium Pigment acceptance boxes.
        // These tolerances are design values and should eventually be validated
        // against real Mixbox mixture samples to tune true gameplay catch volumes.
        premiumDatabase.add(new PremiumWindow(
            "CLR_084", "Burnt Sienna", "JAR_BURNT_SIENNA",
            19, 10,   // Hue target & design tolerance
            56, 12,   // Saturation target & design tolerance
            40, 10    // Lightness target & design tolerance
        ));

        premiumDatabase.add(new PremiumWindow(
            "CLR_092", "Olive Drab", "JAR_OLIVE_DRAB",
            80, 15,
            60, 15,
            35, 10
        ));

        premiumDatabase.add(new PremiumWindow(
            "CLR_105", "Slate Grey", "JAR_SLATE_GREY",
            210, 20,
            13, 6,
            50, 8
        ));

        // FUNNEL LAYER 2: Calibrated H/S/L acceptance windows for clean Pigments.
        // These windows are aligned to the selected Studio Pigment Mixer foundation.
        cleanColorDatabase.add(new CleanJarWindow(
            346, 359, 60, 100, 30, 75, "JAR_RED", "Red"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            0, 10, 60, 100, 30, 75, "JAR_RED", "Red"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            11, 14, 65, 100, 40, 70, "JAR_VERMILION", "Vermilion"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            15, 40, 55, 100, 35, 75, "JAR_ORANGE", "Orange"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            41, 52, 55, 100, 40, 80, "JAR_AMBER", "Amber"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            53, 65, 55, 100, 40, 85, "JAR_YELLOW", "Yellow"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            66, 140, 30, 100, 30, 75, "JAR_GREEN", "Green"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            141, 200, 50, 100, 35, 75, "JAR_TEAL", "Teal"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            201, 260, 55, 100, 30, 70, "JAR_BLUE", "Blue"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            261, 310, 45, 100, 25, 65, "JAR_PURPLE", "Purple"
        ));
        cleanColorDatabase.add(new CleanJarWindow(
            311, 345, 55, 100, 30, 70, "JAR_MAGENTA", "Magenta"
        ));
    }

    private int calculateCircularHueDistance(int h1, int h2) {
        int diff = Math.abs(h1 - h2) % 360;
        return diff > 180 ? 360 - diff : diff;
    }

    public PaintColor processMixOutcome(String resultHex, int rawH, int s, int l) {
        // Normalize arbitrary hue values into the standard 0-359 range.
        int h = Math.floorMod(rawH, 360);

        // FUNNEL LAYER 1:
        // Premium / explicit Pigment identities get first refusal.
        // The stable premium identity is assigned while the exact Mixbox
        // resultHex / H / S / L are preserved.
        for (PremiumWindow premium : premiumDatabase) {
            if (calculateCircularHueDistance(premium.targetHue, h) <= premium.hueTol &&
                Math.abs(premium.targetSat - s) <= premium.satTol &&
                Math.abs(premium.targetLit - l) <= premium.litTol) {

                return new PaintColor(
                    premium.id,
                    premium.name,
                    resultHex,
                    h,
                    s,
                    l,
                    "Premium Earthy Paint",
                    false,
                    premium.jarId
                );
            }
        }

        // FUNNEL LAYER 2:
        // Clean Pigments require Hue, Saturation, and Lightness acceptance.
        // Colors that are too dull, too dark, or too washed out fall through.
        for (CleanJarWindow window : cleanColorDatabase) {
            if (h >= window.minHue && h <= window.maxHue &&
                s >= window.minSat && s <= window.maxSat &&
                l >= window.minLit && l <= window.maxLit) {

                return new PaintColor(
                    "DYNAMIC_CLEAN",
                    window.jarName + " Paint",
                    resultHex,
                    h,
                    s,
                    l,
                    "Standard Paint",
                    false,
                    window.jarId
                );
            }
        }

        // FUNNEL LAYER 3:
        // Nine Earthy fallback buckets:
        // Grey / Green / Brown x Light / Base / Dark.
        String mudJarId;
        String bucketName;

        // Original prototype threshold restored:
        // at very low saturation, hue is visually unreliable,
        // so neutral-looking results stay on the Grey track.
        if (s < 14) {
            bucketName = "Grey";
            mudJarId = "JAR_MUD_GREY";
        } else if (h >= 60 && h <= 165) {
            bucketName = "Green";
            mudJarId = "JAR_MUD_GREEN";
        } else {
            bucketName = "Brown";
            mudJarId = "JAR_MUD_BROWN";
        }

        if (l > 65) {
            return new PaintColor(
                "MUD",
                "Light Earthy " + bucketName,
                resultHex,
                h,
                s,
                l,
                "Mud Bucket",
                true,
                "LIGHT_" + mudJarId
            );
        } else if (l >= 35) {
            return new PaintColor(
                "MUD",
                "Base Earthy " + bucketName,
                resultHex,
                h,
                s,
                l,
                "Mud Bucket",
                true,
                "BASE_" + mudJarId
            );
        } else {
            return new PaintColor(
                "MUD",
                "Dark Earthy " + bucketName,
                resultHex,
                h,
                s,
                l,
                "Mud Bucket",
                true,
                "DARK_" + mudJarId
            );
        }
    }

    private static class PremiumWindow {
        String id, name, jarId;
        int targetHue, hueTol;
        int targetSat, satTol;
        int targetLit, litTol;

        PremiumWindow(
            String id,
            String name,
            String jarId,
            int tH,
            int hT,
            int tS,
            int sT,
            int tL,
            int lT
        ) {
            this.id = id;
            this.name = name;
            this.jarId = jarId;
            this.targetHue = tH;
            this.hueTol = hT;
            this.targetSat = tS;
            this.satTol = sT;
            this.targetLit = tL;
            this.litTol = lT;
        }
    }

    private static class CleanJarWindow {
        int minHue, maxHue;
        int minSat, maxSat;
        int minLit, maxLit;
        String jarId, jarName;

        CleanJarWindow(
            int minHue,
            int maxHue,
            int minSat,
            int maxSat,
            int minLit,
            int maxLit,
            String jarId,
            String jarName
        ) {
            this.minHue = minHue;
            this.maxHue = maxHue;
            this.minSat = minSat;
            this.maxSat = maxSat;
            this.minLit = minLit;
            this.maxLit = maxLit;
            this.jarId = jarId;
            this.jarName = jarName;
        }
    }
}

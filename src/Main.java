public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        if (args.length != 1 || !"--demo".equals(args[0])) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }
        runDemo();
    }

    private static void runDemo() {
        int passed = 0;
        int total = 0;

        BasicRemote basic = new BasicRemote("B-01", new TvDevice());
        passed += check("T1", "BasicRemote + TvDevice", basic.execute(), "TV power=ON volume=30");
        total++;

        basic.setImplementation(new RadioDevice());
        passed += check("T2", "BasicRemote + RadioDevice", basic.execute(), "RADIO power=ON volume=30");
        total++;

        QuietRemote quiet = new QuietRemote("Q-01", new TvDevice());
        passed += check("T3", "QuietRemote + TvDevice", quiet.execute(), "TV power=ON volume=5");
        total++;

        quiet.setImplementation(new RadioDevice());
        passed += check("T4", "QuietRemote + RadioDevice", quiet.execute(), "RADIO power=ON volume=5");
        total++;

        BasicRemote switchable = new BasicRemote("B-02", new TvDevice());
        Remote originalReference = switchable;
        String originalId = switchable.getId();
        int originalPreset = switchable.getVolumePreset();
        String before = switchable.execute();
        switchable.setImplementation(new RadioDevice());
        Remote afterSwitchReference = switchable;
        String after = afterSwitchReference.execute();
        boolean sameObject = originalReference == afterSwitchReference;
        boolean stateUnchanged = originalId.equals(switchable.getId())
                && originalPreset == switchable.getVolumePreset();
        boolean t5Pass = sameObject && stateUnchanged
                && "TV power=ON volume=30".equals(before)
                && "RADIO power=ON volume=30".equals(after);
        String actualT5 = "sameObject=" + sameObject + " stateUnchanged=" + stateUnchanged
                + " before=" + before + " after=" + after;
        String expectedT5 = "sameObject=true stateUnchanged=true before=TV power=ON volume=30"
                + " after=RADIO power=ON volume=30";
        passed += report("T5", "BasicRemote: TvDevice -> RadioDevice", t5Pass, actualT5, expectedT5);
        total++;

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static int check(String id, String classes, String actual, String expected) {
        return report(id, classes, expected.equals(actual), actual, expected);
    }

    private static int report(String id, String classes, boolean passed, String actual, String expected) {
        System.out.println(id + " " + (passed ? "PASS" : "FAIL") + " | " + classes + " | result=" + actual);
        if (!passed) {
            System.out.println("   expected=" + expected);
        }
        return passed ? 1 : 0;
    }
}

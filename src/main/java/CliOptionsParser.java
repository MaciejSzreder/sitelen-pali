public final class CliOptionsParser {
    public CliOptions parse(String[] args) {
        String inputPath = null;
        String logPath = null;
        boolean showHelp = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-i".equals(arg) || "--input".equals(arg)) {
                inputPath = requireValue(args, ++i, arg);
            } else if ("-l".equals(arg) || "--log".equals(arg)) {
                logPath = requireValue(args, ++i, arg);
            } else if ("-h".equals(arg) || "--help".equals(arg)) {
                showHelp = true;
            } else {
                System.out.println("nimi ni li sona ala: " + arg);
                return new CliOptions(inputPath, logPath, true, true);
            }
        }

        return new CliOptions(inputPath, logPath, showHelp, false);
    }

    private String requireValue(String[] args, int index, String option) {
        if (index >= args.length) {
            System.out.println("nimi kama jo li lon ala tan " + option);
            printUsage();
            System.exit(1);
        }
        return args[index];
    }

    private void printUsage() {
        System.out.println("toki! ni li pali e ilo:");
        System.out.println("  java IloPiSitelenPali [--input FILE] [--log FILE]");
        System.out.println("  java IloPiSitelenPali --help");
    }
}

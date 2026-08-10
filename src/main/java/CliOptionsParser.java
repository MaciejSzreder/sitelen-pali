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
                throw new ParseException();
            }
        }

        return new CliOptions(inputPath, logPath, showHelp);
    }

    private String requireValue(String[] args, int index, String option) {
        if (index >= args.length) {
            throw new ParseException();
        }
        return args[index];
    }

    public String getUsage() {
        return "toki! ni li pali e ilo:\n" +
               "  java IloPiSitelenPali [--input FILE] [--log FILE]\n" +
               "  java IloPiSitelenPali --help";
    }

    public static class ParseException extends RuntimeException {
    }
}

public final class CliOptions {
    private final String inputPath;
    private final String logPath;
    private final boolean showHelp;

    public CliOptions(String inputPath, String logPath, boolean showHelp) {
        this.inputPath = inputPath;
        this.logPath = logPath;
        this.showHelp = showHelp;
    }

    public String inputPath() {
        return inputPath;
    }

    public String logPath() {
        return logPath;
    }

    public boolean showHelp() {
        return showHelp;
    }
}

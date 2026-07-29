public final class CliOptions {
    private final String inputPath;
    private final String logPath;
    private final boolean showHelp;
    private final boolean invalid;

    public CliOptions(String inputPath, String logPath, boolean showHelp, boolean invalid) {
        this.inputPath = inputPath;
        this.logPath = logPath;
        this.showHelp = showHelp;
        this.invalid = invalid;
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

    public boolean invalid() {
        return invalid;
    }
}

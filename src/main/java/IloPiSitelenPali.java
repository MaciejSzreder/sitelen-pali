import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public class IloPiSitelenPali {
    private static final String ALLOWED_CHARS = " \r\n!\",.:?AEIJKLMNOPSTWaeijklmnopstw";

    public static void main(String[] args) {
        CliOptions options = parseArgs(args);
        if (options.showHelp) {
            printUsage();
            if (options.invalid) {
                System.exit(1);
            }
            return;
        }

        try (InputStream input = openInput(options.inputPath);
             BufferedWriter log = openLog(options.logPath)) {
            run(input, log);
        } catch (IOException e) {
            System.out.println("tenpo ike: " + e.getMessage());
            System.exit(1);
        }
    }

    private static CliOptions parseArgs(String[] args) {
        CliOptions options = new CliOptions();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-i".equals(arg) || "--input".equals(arg)) {
                options.inputPath = requireValue(args, ++i, arg);
            } else if ("-l".equals(arg) || "--log".equals(arg)) {
                options.logPath = requireValue(args, ++i, arg);
            } else if ("-h".equals(arg) || "--help".equals(arg)) {
                options.showHelp = true;
            } else {
                System.out.println("nimi ni li sona ala: " + arg);
                options.showHelp = true;
                options.invalid = true;
                return options;
            }
        }
        return options;
    }

    private static void run(InputStream input, BufferedWriter log) throws IOException {
        System.out.println("toki ali");
        int codePoint;
        boolean inWord = false;
        while ((codePoint = input.read()) != -1) {
            char ch = (char) codePoint;
            String printable = printableChar(ch);
            if (isValidSitelenLasina(ch)) {
                System.out.println("sitelen " + printable + " li pona");
                if (isWordDelimiter(ch)) {
                    inWord = false;
                } else {
                    if (Character.isUpperCase(ch) && inWord) {
                        String stdoutMessage = "sona ike: sitelen suli li kama lon open ala pi nimi: " + printable;
                        String logMessage = "warning: uppercase letter is not the first letter of a word: " + describeChar(ch);
                        System.out.println(stdoutMessage);
                        if (log != null) {
                            log.write(logMessage);
                            log.newLine();
                        }
                    }
                    inWord = true;
                }
            } else {
                System.out.println("sitelen " + printable + " li ike");
                if (log != null) {
                    log.write("warning: invalid character: " + describeChar(ch));
                    log.newLine();
                }
            }
        }
        if (log != null) {
            log.flush();
        }
    }

    private static InputStream openInput(String inputPath) throws IOException {
        if (inputPath == null) {
            return new BufferedInputStream(System.in);
        }
        return new BufferedInputStream(new FileInputStream(inputPath));
    }

    private static BufferedWriter openLog(String logPath) throws IOException {
        if (logPath == null) {
            return null;
        }
        return new BufferedWriter(new FileWriter(logPath, false));
    }

    private static String requireValue(String[] args, int index, String option) {
        if (index >= args.length) {
            System.out.println("nimi kama jo li lon ala tan " + option);
            printUsage();
            System.exit(1);
        }
        return args[index];
    }

    private static void printUsage() {
        System.out.println("toki! ni li pali e ilo:");
        System.out.println("  java IloPiSitelenPali [--input FILE] [--log FILE]");
        System.out.println("  java IloPiSitelenPali --help");
    }

    private static boolean isValidSitelenLasina(char ch) {
        return ALLOWED_CHARS.indexOf(ch) >= 0;
    }

    private static boolean isWordDelimiter(char ch) {
        return ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t' || ch == '!' || ch == '"' || ch == ',' || ch == '.' || ch == ':' || ch == '?';
    }

    private static String printableChar(char ch) {
        switch (ch) {
            case ' ':
                return "␠";
            case '\t':
                return "\\t";
            case '\r':
                return "\\r";
            case '\n':
                return "\\n";
            default:
                return String.valueOf(ch);
        }
    }

    private static String describeChar(char ch) {
        switch (ch) {
            case ' ':
                return "space";
            case '\t':
                return "tab";
            case '\r':
                return "carriage return";
            case '\n':
                return "line feed";
            default:
                return "'" + ch + "'";
        }
    }

    private static final class CliOptions {
        private String inputPath;
        private String logPath;
        private boolean showHelp;
        private boolean invalid;
    }
}

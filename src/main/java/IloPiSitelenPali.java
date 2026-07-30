import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IloPiSitelenPali {
    private static final String ALLOWED_CHARS = " \r\n!\",.:?AEIJKLMNOPSTWaeijklmnopstw";
    private static final CliOptionsParser CLI_OPTIONS_PARSER = new CliOptionsParser();

    public static void main(String[] args) {
        CliOptions options = CLI_OPTIONS_PARSER.parse(args);
        if (options.showHelp()) {
            if (options.invalid()) {
                System.exit(1);
            }
            return;
        }

        try (InputStream input = openInput(options.inputPath());
             Log log = Log.open(options.logPath())) {
            run(input, log);
        } catch (IOException e) {
            System.out.println("tenpo ike: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void run(InputStream input, Log log) throws IOException {
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
                        System.out.println(stdoutMessage);
                        log.logUppercaseLetterNotAtWordStart(ch);
                    }
                    inWord = true;
                }
            } else {
                System.out.println("sitelen " + printable + " li ike");
                log.logInvalidCharacter(ch);
            }
        }
    }

    private static InputStream openInput(String inputPath) throws IOException {
        if (inputPath == null) {
            return new BufferedInputStream(System.in);
        }
        return new BufferedInputStream(new FileInputStream(inputPath));
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

}

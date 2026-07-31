import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IloPiSitelenPali {
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
            CharacterChecker.check(input, log);
        } catch (IOException e) {
            System.out.println("tenpo ike: " + e.getMessage());
            System.exit(1);
        }
    }

    private static InputStream openInput(String inputPath) throws IOException {
        if (inputPath == null) {
            return new BufferedInputStream(System.in);
        }
        return new BufferedInputStream(new FileInputStream(inputPath));
    }
}

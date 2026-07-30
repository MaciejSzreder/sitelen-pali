import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public final class Log implements AutoCloseable {
    private final BufferedWriter writer;

    private Log(BufferedWriter writer) {
        this.writer = writer;
    }

    public static Log open(String logPath) throws IOException {
        if (logPath == null) {
            return new Log(null);
        }
        return new Log(new BufferedWriter(new FileWriter(logPath, false)));
    }

    public void logUppercaseLetterNotAtWordStart(char ch) throws IOException {
        writeWarning("uppercase letter is not the first letter of a word: " + describeChar(ch));
    }

    public void logInvalidCharacter(char ch) throws IOException {
        writeWarning("invalid character: " + describeChar(ch));
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    private void writeWarning(String message) throws IOException {
        if (writer != null) {
            writer.write("warning: " + message);
            writer.newLine();
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
}

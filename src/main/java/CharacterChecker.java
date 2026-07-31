import java.io.IOException;
import java.io.InputStream;

public final class CharacterChecker {
    private static final String ALLOWED_CHARS = " \r\n!\",.:?AEIJKLMNOPSTWaeijklmnopstw";

    private CharacterChecker() {
    }

    public static void check(InputStream input, Log log) throws IOException {
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

    private static boolean isValidSitelenLasina(char ch) {
        return ALLOWED_CHARS.indexOf(ch) >= 0;
    }

    private static boolean isWordDelimiter(char ch) {
        return ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t' || ch == '!' || ch == '"' || ch == ',' || ch == '.' || ch == ':' || ch == '?';
    }

    private static String printableChar(char ch) {
        switch (ch) {
            case ' ':
                return "\u2420";
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

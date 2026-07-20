public class IloPiSitelenPali {
    // Allowed characters derived from the Common Voice tok file (hardcoded)
    // includes space, CR/LF and the punctuation and letters observed in the corpus
    private static final String ALLOWED_CHARS = " \r\n!\",.:?AEIJKLMNOPSTWaeijklmnopstw";

    public static void main(String[] args) {
        System.out.println("toki ali");
        int codePoint;
        try {
            while ((codePoint = System.in.read()) != -1) {
                char ch = (char) codePoint;
                String printable = printableChar(ch);
                if (isValidSitelenLasina(ch)) {
                    System.out.println("sitelen " + printable + " li pona");
                } else {
                    System.out.println("sitelen " + printable + " li ike");
                }
            }
        } catch (Exception e) {
            System.err.println("tenpo ike: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean isValidSitelenLasina(char ch) {
        return ALLOWED_CHARS.indexOf(ch) >= 0;
    }

    private static String printableChar(char ch) {
        switch (ch) {
            case ' ':
                return "␣";
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

    private static boolean isAsciiPunctuation(char ch) {
        int c = ch;
        if (c >= 33 && c <= 47) return true;  // !"#$%&'()*+,-./
        if (c >= 58 && c <= 64) return true;  // :;<=>?@
        if (c >= 91 && c <= 96) return true;  // [\\]^_`
        if (c >= 123 && c <= 126) return true; // { | } ~
        return false;
    }
}

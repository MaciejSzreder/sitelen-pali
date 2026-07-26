import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class IloPiSitelenPaliTest {
    @TempDir
    Path tempDir;

    @Test
    void tabIsHandledAsWhitespaceInPublicBehavior() throws Exception {
        RunResult result = runProgram("A\tB\n", "tab.log");
        assertTrue(result.stdout.contains("sitelen \t li ike"));
        assertFalse(result.logLines.isEmpty());
        assertTrue(result.logLines.get(0).contains("invalid character: 'tab'"));
    }

    @Test
    void invalidCharactersAreWrittenToTheLog() throws Exception {
        RunResult result = runProgram("a#\n", "invalid.log");
        assertTrue(result.stdout.contains("sitelen # li ike"));
        assertEquals(1, result.logLines.size());
        assertTrue(result.logLines.get(0).contains("invalid character: '#'"));
    }

    @Test
    void spaceIsRenderedWithAVisibleSymbol() throws Exception {
        RunResult result = runProgram("a b\n", "space.log");
        assertTrue(result.stdout.contains("sitelen ␠ li pona"));
    }

    private RunResult runProgram(String input, String logFileName) throws Exception {
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        Path logPath = tempDir.resolve(logFileName);

        try (PrintStream captureOut = new PrintStream(stdout, true, StandardCharsets.UTF_8)) {
            System.setOut(captureOut);
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            IloPiSitelenPali.main(new String[]{"--log", logPath.toString()});
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }

        List<String> logLines = Files.exists(logPath) ? Files.readAllLines(logPath, StandardCharsets.UTF_8) : List.of();
        return new RunResult(stdout.toString(StandardCharsets.UTF_8), logLines);
    }

    private record RunResult(String stdout, List<String> logLines) {
    }
}

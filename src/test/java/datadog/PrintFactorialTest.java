package datadog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrintFactorialTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String nl = System.lineSeparator();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    private String stringFromArray(String[] array) {
        StringBuilder sb = new StringBuilder();
        for(String s : array) {
            sb.append(s+nl);
        }

        return sb.toString();
    }

    @Test
    public void testPrintFactorials() {
        PrintFactorial pf = new PrintFactorial();
        pf.printNfactorialsToZero(8);
        assertEquals(stringFromArray(new String[]{"40320","5040","720","120","24","6","2","1"}), outContent.toString());
    }

    @Test
    public void testPrintFactorialsUsingStack() {
        PrintFactorial pf = new PrintFactorial();
        pf.printNFactorialsToZeroUsingStack(8);
        assertEquals(stringFromArray(new String[]{"40320","5040","720","120","24","6","2","1"}), outContent.toString());
    }

}

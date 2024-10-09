import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

/**
 * TeamTests class contains JUnit tests for the Frontend implementation.
 * These tests are designed to be opaque-box tests that rely only on the
 * FrontendInterface methods and are more flexible to different implementations.
 */
public class TeamTests {

    /**
     * Tests the loadFile() method of the Frontend.
     * This test covers file loading and verifies the output.
     */
    @Test
    public void testLoadFile() {
        // Set up the test environment with input commands
        // L: Load file command
        // testfile: Name of the file to load (generic name)
        // Q: Quit command
        TextUITester tester = new TextUITester("L\ntestfile\nQ\n");

        // Create a new Frontend instance with a scanner and backend placeholder
        FrontendInterface frontend = new Frontend(new Scanner(System.in),
                new Backend_Placeholder(new Tree_Placeholder()));

        // Run the command loop, which should process our input
        frontend.runCommandLoop();

        // Capture the output generated during the command loop execution
        String output = tester.checkOutput();

        // Check if the output mentions file loading
        assertTrue(output.toLowerCase().contains("file") && output.toLowerCase().contains("load"),
                "Output should mention file loading");

        // Ensure no error messages are present for valid file loading
        assertFalse(output.toLowerCase().contains("error") || output.toLowerCase().contains("invalid"),
                "Output should not contain error messages for valid file loading");
    }

    /**
     * Tests the getSongs() method of the Frontend.
     * This test covers getting songs within an energy range.
     */
    @Test
    public void testGetSongs() {
        // Set up test input for getting songs within an energy range
        // G: Get songs command
        // 50: Minimum energy
        // 100: Maximum energy
        // Q: Quit command
        TextUITester tester = new TextUITester("G\n50\n100\nQ\n");

        // Create a new Frontend instance
        FrontendInterface frontend = new Frontend(new Scanner(System.in),
                new Backend_Placeholder(new Tree_Placeholder()));

        // Execute the command loop
        frontend.runCommandLoop();

        // Capture the output
        String output = tester.checkOutput();

        // Check if the output mentions energy-related terms
        assertTrue(output.toLowerCase().contains("energy") &&
                (output.toLowerCase().contains("min") || output.toLowerCase().contains("max")),
                "Output should mention energy and min/max values");

        // Verify that songs are mentioned in the output
        assertTrue(output.toLowerCase().contains("song") || output.toLowerCase().contains("title"),
                "Output should mention songs or titles");
    }

    /**
     * Tests the setFilter() and displayTopFive() methods of the Frontend.
     * This test covers setting a danceability filter and displaying top songs.
     */
    @Test
    public void testFilterAndDisplayTopSongs() {
        // Set up test input for filtering and displaying top songs
        // F: Filter command
        // 75: Danceability threshold
        // D: Display top songs command
        // Q: Quit command
        TextUITester tester = new TextUITester("F\n75\nD\nQ\n");

        // Create a new Frontend instance
        FrontendInterface frontend = new Frontend(new Scanner(System.in),
                new Backend_Placeholder(new Tree_Placeholder()));

        // Run the frontend
        frontend.runCommandLoop();

        // Capture the output
        String output = tester.checkOutput();

        // Verify that filtering-related terms are mentioned in the output
        assertTrue(output.toLowerCase().contains("filter") || output.toLowerCase().contains("threshold")
                || output.toLowerCase().contains("danceability") || output.toLowerCase().contains("danceable"),
                "Output should mention filtering, threshold, danceability, or danceable");

        // Verify that the output mentions top songs or related terms
        assertTrue(output.toLowerCase().contains("top") || output.toLowerCase().contains("song")
                || output.toLowerCase().contains("recent") || output.toLowerCase().contains("display"),
                "Output should mention top, song, recent, or display");

        // Ensure no error messages are present for valid operations
        assertFalse(output.toLowerCase().contains("error") || output.toLowerCase().contains("invalid"),
                "Output should not contain error messages for valid operations");
    }
}
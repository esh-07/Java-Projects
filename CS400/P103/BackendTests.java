import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

/**
 * BackendTests class contains unit tests for the Backend class.
 * It verifies the functionality of methods defined in the BackendInterface
 * by testing the reading of song data, filtering by energy and danceability,
 * retrieving the most recent songs, and handling edge cases.
 */
public class BackendTests {

    /**
     * Test the readData and getRange methods of the Backend class.
     * This test checks if the Backend can read data from a file and retrieve songs
     * within a specific energy range, considering the limitations of
     * Tree_Placeholder.
     */
    @Test
    public void roleTest1() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder();
        Backend backend = new Backend(tree);

        try {
            // Read song data from the CSV file
            backend.readData("songs.csv");
            // Retrieve songs within the specified energy range
            List<String> songs = backend.getRange(70, 90);
            assertFalse(songs.isEmpty(), "Should return non-empty list of songs");
            assertTrue(songs.contains("BO$$"), "Should contain the song 'BO$$'");
            assertTrue(songs.size() <= 4, "Should contain at most 4 songs");
            assertTrue(songs.contains("A L I E N S"), "Should contain the song 'A L I E N S'");
            assertTrue(songs.contains("Cake By The Ocean"), "Should contain the song 'Cake By The Ocean'");

            // Test for ordering by energy
            List<String> orderedSongs = backend.getRange(null, null);
            assertTrue(orderedSongs.contains("Kills You Slowly"), "Should contain 'Kills You Slowly'");
            assertTrue(orderedSongs.contains("BO$$"), "Should contain 'BO$$'");
            assertTrue(orderedSongs.contains("A L I E N S"), "Should contain 'A L I E N S'");
            assertTrue(orderedSongs.contains("Cake By The Ocean"), "Should contain 'Cake By The Ocean'");
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Test the setFilter method of the Backend class.
     * This test checks if the Backend can apply a danceability filter and return
     * the correct filtered list of songs. It also tests the handling of edge cases,
     * including passing null to setFilter and calling setFilter before getRange.
     */
    @Test
    public void roleTest2() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder();
        Backend backend = new Backend(tree);

        try {
            // Read song data from the CSV file
            backend.readData("songs.csv");

            // Test setFilter before getRange
            List<String> initialFilteredSongs = backend.setFilter(75);
            assertFalse(initialFilteredSongs.isEmpty(),
                    "Should return non-empty list of songs without calling getRange first");

            // Apply danceability filter and check results
            List<String> filteredSongs = backend.setFilter(75);
            assertFalse(filteredSongs.isEmpty(), "Should return non-empty list of songs");
            assertTrue(filteredSongs.contains("BO$$"), "Should contain the song 'BO$$'");
            assertFalse(filteredSongs.contains("A L I E N S"), "Should not contain the song 'A L I E N S'");

            // Test for empty list when no songs meet the criteria
            List<String> noSongs = backend.setFilter(Integer.MAX_VALUE);
            assertTrue(noSongs.isEmpty(), "Should return an empty list when no songs meet the criteria");

            // Test setFilter with null to return all songs
            List<String> allSongs = backend.setFilter(null);
            assertFalse(allSongs.isEmpty(), "Should return all songs when filter is null");
            assertTrue(allSongs.size() >= filteredSongs.size(), "Should return more songs than with non-null filter");

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Test the fiveMost method of the Backend class.
     * This test checks if the Backend can return the five most recent songs that
     * meet the current filter criteria and if they are in the correct order.
     * It also tests calling fiveMost without calling getRange first.
     */
    @Test
    public void roleTest3() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder();
        Backend backend = new Backend(tree);

        // Test fiveMost without calling getRange first
        List<String> initialRecentSongs = backend.fiveMost();
        assertFalse(initialRecentSongs.isEmpty(),
                "Should return non-empty list of songs without calling getRange first");

        List<String> recentSongs = backend.fiveMost();
        assertFalse(recentSongs.isEmpty(), "Should return non-empty list of songs");
        assertTrue(recentSongs.size() <= 5, "Should return at most 5 songs");
        assertTrue(recentSongs.contains("A L I E N S"), "Should contain the most recent song 'A L I E N S'");

        // Test for most recent songs order
        assertEquals("A L I E N S", recentSongs.get(0), "First song should be 'A L I E N S' (most recent year)");
    }
}
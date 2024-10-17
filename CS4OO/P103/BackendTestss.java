import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

/**
 * BackendTests class contains unit tests for the Backend class.
 * It verifies the functionality of methods defined in the BackendInterface
 * by testing the reading of song data, filtering by energy and danceability,
 * and retrieving the most recent songs.
 */
public class BackendTestss {

    /**
     * Test the readData and getRange methods of the Backend class.
     * This test checks if the Backend can read data from a file and retrieve songs
     * within a specific energy range, considering the limitations of
     * Tree_Placeholder.
     */
    @Test
    public void roleTest1() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder(); // Create a placeholder tree
        Backend backend = new Backend(tree); // Initialize Backend with the placeholder tree

        try {
            backend.readData("songs.csv"); // Read song data from CSV file
            List<String> songs = backend.getRange(70, 90); // Retrieve songs within the energy range
            assertFalse(songs.isEmpty(), "Should return non-empty list of songs"); // Check for non-empty result
            assertTrue(songs.contains("BO$$"), "Should contain the song 'BO$$'"); // Check for specific song
            assertEquals(3, songs.size(), "Should contain exactly 3 songs"); // Verify the number of songs
            assertTrue(songs.contains("A L I E N S"), "Should contain the song 'A L I E N S'"); // Check for another
                                                                                                // song
            assertTrue(songs.contains("Cake By The Ocean"), "Should contain the song 'Cake By The Ocean'"); // Check for
                                                                                                            // another
                                                                                                            // song
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage()); // Handle IOException
        }
    }

    /**
     * Test the setFilter method of the Backend class.
     * This test checks if the Backend can apply a danceability filter and return
     * the correct filtered list of songs.
     */
    @Test
    public void roleTest2() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder(); // Create a placeholder tree
        Backend backend = new Backend(tree); // Initialize Backend with the placeholder tree

        try {
            backend.readData("songs.csv"); // Read song data from CSV file
            List<String> filteredSongs = backend.setFilter(75); // Apply danceability filter
            assertFalse(filteredSongs.isEmpty(), "Should return non-empty list of songs"); // Check for non-empty result
            assertTrue(filteredSongs.contains("BO$$"), "Should contain the song 'BO$$'"); // Check for specific song
            assertFalse(filteredSongs.contains("A L I E N S"), "Should not contain the song 'A L I E N S'"); // Check
                                                                                                             // exclusion
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage()); // Handle IOException
        }
    }

    /**
     * Test the fiveMost method of the Backend class.
     * This test checks if the Backend can return the five most recent songs that
     * meet the current filter criteria.
     */
    @Test
    public void roleTest3() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder(); // Create a placeholder tree
        Backend backend = new Backend(tree); // Initialize Backend with the placeholder tree

        List<String> recentSongs = backend.fiveMost(); // Retrieve the five most recent songs
        assertFalse(recentSongs.isEmpty(), "Should return non-empty list of songs"); // Check for non-empty result
        assertTrue(recentSongs.size() <= 5, "Should return at most 5 songs"); // Verify the number of songs
        assertTrue(recentSongs.contains("A L I E N S"), "Should contain the most recent song 'A L I E N S'"); // Check
                                                                                                              // for
                                                                                                              // specific
                                                                                                              // song
    }
}
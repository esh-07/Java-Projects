import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

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
            backend.readData("songs.csv");
            List<String> songs = backend.getRange(70, 90);
            assertFalse(songs.isEmpty(), "Should return non-empty list of songs");
            assertTrue(songs.contains("BO$$"), "Should contain the song 'BO$$'");
            assertEquals(3, songs.size(), "Should contain exactly 3 songs");
            assertTrue(songs.contains("A L I E N S"), "Should contain the song 'A L I E N S'");
            assertTrue(songs.contains("Cake By The Ocean"), "Should contain the song 'Cake By The Ocean'");
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Test the setFilter method of the Backend class.
     * This test checks if the Backend can apply a danceability filter and return
     * the correct filtered list of songs.
     */
    @Test
    public void roleTest2() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder();
        Backend backend = new Backend(tree);

        try {
            backend.readData("songs.csv");
            List<String> filteredSongs = backend.setFilter(75);
            assertFalse(filteredSongs.isEmpty(), "Should return non-empty list of songs");
            assertTrue(filteredSongs.contains("BO$$"), "Should contain the song 'BO$$'");
            assertFalse(filteredSongs.contains("A L I E N S"), "Should not contain the song 'A L I E N S'");
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Test the fiveMost method of the Backend class.
     * This test checks if the Backend can return the five most recent songs that
     * meet the current filter criteria.
     */
    @Test
    public void roleTest3() {
        IterableSortedCollection<Song> tree = new Tree_Placeholder();
        Backend backend = new Backend(tree);

        List<String> recentSongs = backend.fiveMost();
        assertFalse(recentSongs.isEmpty(), "Should return non-empty list of songs");
        assertTrue(recentSongs.size() <= 5, "Should return at most 5 songs");
        assertTrue(recentSongs.contains("A L I E N S"), "Should contain the most recent song 'A L I E N S'");
    }
}
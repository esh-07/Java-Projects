import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;

/**
 * Backend class implements the BackendInterface to manage a collection of
 * songs.
 * It provides functionality to read song data, filter songs based on energy and
 * danceability,
 * and retrieve specific song lists based on criteria.
 */
public class Backend implements BackendInterface {
    // Collection of songs
    private IterableSortedCollection<Song> tree;
    // Energy bounds for filtering
    private Integer lowEnergyBound;
    private Integer highEnergyBound;
    // Danceability threshold for filtering
    private Integer danceabilityThreshold;
    // Flag to track if getRange has been called
    private boolean getRangeCalled;

    /**
     * Constructor to initialize the Backend with a song collection.
     * 
     * @param tree The collection of songs to manage.
     */
    public Backend(IterableSortedCollection<Song> tree) {
        this.tree = tree;
        this.lowEnergyBound = null;
        this.highEnergyBound = null;
        this.danceabilityThreshold = null;
        this.getRangeCalled = false; // Initialize to false
    }

    /**
     * Reads song data from a specified CSV file and inserts songs into the
     * collection.
     * 
     * @param filename The name of the file to read data from.
     * @throws IOException If an error occurs while reading the file.
     */
    @Override
    public void readData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String[] headers = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (headers == null) {
                    headers = values; // Store headers from the first line
                    continue;
                }
                if (values.length >= headers.length) {
                    try {
                        Song song = createSongFromValues(headers, values);
                        if (song != null) {
                            tree.insert(song); // Insert song into the collection
                        }
                    } catch (NumberFormatException e) {
                        // Skip this row if there's an error parsing integers
                        continue;
                    }
                }
            }
        }
    }

    /**
     * Creates a Song object from the provided header and value arrays.
     * 
     * @param headers The headers of the CSV file.
     * @param values  The values corresponding to the headers.
     * @return A Song object or null if the data is incomplete.
     */
    private Song createSongFromValues(String[] headers, String[] values) {
        String title = null, artist = null, genre = null;
        Integer year = null, bpm = null, energy = null, danceability = null, loudness = null, liveness = null;

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].toLowerCase().trim();
            String value = values[i].trim();

            switch (header) {
                case "title":
                    title = value; // Set title
                    break;
                case "artist":
                    artist = value; // Set artist
                    break;
                case "top genre":
                    genre = value; // Set genre
                    break;
                case "year":
                    year = Integer.parseInt(value); // Parse year
                    break;
                case "bpm":
                    bpm = Integer.parseInt(value); // Parse BPM
                    break;
                case "nrgy":
                    energy = Integer.parseInt(value); // Parse energy
                    break;
                case "dnce":
                    danceability = Integer.parseInt(value); // Parse danceability
                    break;
                case "db":
                    loudness = Integer.parseInt(value); // Parse loudness
                    break;
                case "live":
                    liveness = Integer.parseInt(value); // Parse liveness
                    break;
            }
        }

        // Check if all required fields are present
        if (title != null && artist != null && genre != null && year != null && bpm != null &&
                energy != null && danceability != null && loudness != null && liveness != null) {
            return new Song(title, artist, genre, year, bpm, energy, danceability, loudness, liveness);
        }
        return null; // Return null if any required field is missing
    }

    /**
     * Retrieves a list of song titles within a specified energy range.
     * 
     * @param low  The lower bound of the energy range.
     * @param high The upper bound of the energy range.
     * @return A list of song titles that meet the criteria.
     */
    @Override
    public List<String> getRange(Integer low, Integer high) {
        this.lowEnergyBound = low; // Set lower energy bound
        this.highEnergyBound = high; // Set upper energy bound
        this.getRangeCalled = true; // Set flag to true when getRange is called

        List<Song> filteredSongs = new ArrayList<>();
        for (Song song : tree) {
            if (meetsEnergyRange(song)) {
                filteredSongs.add(song); // Add song if it meets energy range
            }
        }

        // Sort songs by energy
        Collections.sort(filteredSongs, Comparator.comparingInt(Song::getEnergy));

        List<String> result = new ArrayList<>();
        for (Song song : filteredSongs) {
            if (meetsDanceabilityThreshold(song)) {
                result.add(song.getTitle()); // Add song title if it meets danceability threshold
            }
        }

        // Explicitly check for empty result case
        if (result.isEmpty()) {
            System.out.println("No songs meet the specified energy range and danceability criteria.");
        }

        return result; // Return the list of song titles
    }

    /**
     * Sets a danceability filter for the songs.
     * 
     * @param threshold The danceability threshold.
     * @return A list of song titles that meet the danceability criteria.
     */
    @Override
    public List<String> setFilter(Integer threshold) {
        this.danceabilityThreshold = threshold; // Set danceability threshold

        // Check if any songs have been loaded
        if (tree.isEmpty()) {
            return new ArrayList<>(); // Return empty list if no songs are loaded
        }

        List<Song> filteredSongs = new ArrayList<>();

        for (Song song : tree) {
            // Check if song meets energy range or if getRange was not called
            if (!getRangeCalled || meetsEnergyRange(song)) {
                if (threshold == null || song.getDanceability() > threshold) {
                    filteredSongs.add(song); // Add song if it meets danceability criteria
                }
            }
        }

        // Sort by Energy
        Collections.sort(filteredSongs, Comparator.comparingInt(Song::getEnergy));

        List<String> result = filteredSongs.stream().map(Song::getTitle).collect(Collectors.toList());

        // Explicitly check for empty list case
        if (result.isEmpty()) {
            System.out.println("No songs meet the specified criteria.");
        }

        return result; // Return the list of song titles
    }

    /**
     * Retrieves the five most recent songs based on the year.
     * 
     * @return A list of the titles of the five most recent songs.
     */
    @Override
    public List<String> fiveMost() {
        List<Song> filteredSongs = new ArrayList<>();

        for (Song song : tree) {
            // Check if song meets energy range and danceability threshold
            if ((!getRangeCalled || meetsEnergyRange(song)) && meetsDanceabilityThreshold(song)) {
                filteredSongs.add(song); // Add song to the filtered list
            }
        }

        // Sort filtered songs by year in descending order
        Collections.sort(filteredSongs, Comparator.comparingInt(Song::getYear).reversed());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(5, filteredSongs.size()); i++) {
            result.add(filteredSongs.get(i).getTitle()); // Add titles of the top 5 songs
        }

        return result; // Return the list of song titles
    }

    /**
     * Checks if a song meets the specified energy range.
     * 
     * @param song The song to check.
     * @return True if the song meets the energy range criteria, false otherwise.
     */
    private boolean meetsEnergyRange(Song song) {
        if (lowEnergyBound == null && highEnergyBound == null) {
            return true; // No energy range set, accept all songs
        }
        int energy = song.getEnergy();
        boolean meetsLowBound = (lowEnergyBound == null) || (energy >= lowEnergyBound);
        boolean meetsHighBound = (highEnergyBound == null) || (energy <= highEnergyBound);
        return meetsLowBound && meetsHighBound; // Return true if song meets both bounds
    }

    /**
     * Checks if a song meets the danceability threshold.
     * 
     * @param song The song to check.
     * @return True if the song meets the danceability criteria, false otherwise.
     */
    private boolean meetsDanceabilityThreshold(Song song) {
        return danceabilityThreshold == null || song.getDanceability() >= danceabilityThreshold; // Return true if meets
                                                                                                 // threshold
    }
}
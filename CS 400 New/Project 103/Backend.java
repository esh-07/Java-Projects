import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;

public class Backend implements BackendInterface {
    private IterableSortedCollection<Song> tree;
    private Integer lowEnergyBound;
    private Integer highEnergyBound;
    private Integer danceabilityThreshold;

    public Backend(IterableSortedCollection<Song> tree) {
        this.tree = tree;
        this.lowEnergyBound = null;
        this.highEnergyBound = null;
        this.danceabilityThreshold = null;
    }

    @Override
    public void readData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] values = line.split(",");
                if (values.length >= 9) {
                    try {
                        Song song = new Song(
                                values[0], // title
                                values[1], // artist
                                values[2], // genre
                                Integer.parseInt(values[3]), // year
                                Integer.parseInt(values[4]), // bpm
                                Integer.parseInt(values[5]), // energy
                                Integer.parseInt(values[6]), // danceability
                                Integer.parseInt(values[7]), // loudness
                                Integer.parseInt(values[8]) // liveness
                        );
                        tree.insert(song);
                    } catch (NumberFormatException e) {
                        // Skip this row if there's an error parsing integers
                        continue;
                    }
                }
            }
        }
    }

    @Override
    public List<String> getRange(Integer low, Integer high) {
        this.lowEnergyBound = low;
        this.highEnergyBound = high;

        List<Song> filteredSongs = new ArrayList<>();
        for (Song song : tree) {
            if (meetsEnergyRange(song)) {
                filteredSongs.add(song);
            }
        }

        // Sort by Energy
        Collections.sort(filteredSongs, Comparator.comparingInt(Song::getEnergy));

        List<String> result = new ArrayList<>();
        for (Song song : filteredSongs) {
            if (meetsDanceabilityThreshold(song)) {
                result.add(song.getTitle());
            }
        }

        return result;
    }

    @Override
    public List<String> setFilter(Integer threshold) {
        this.danceabilityThreshold = threshold;
        return getRange(lowEnergyBound, highEnergyBound);
    }

    @Override
    public List<String> fiveMost() {
        List<Song> filteredSongs = new ArrayList<>();
        for (Song song : tree) {
            if (meetsEnergyRange(song) && meetsDanceabilityThreshold(song)) {
                filteredSongs.add(song);
            }
        }

        Collections.sort(filteredSongs, Comparator.comparingInt(Song::getYear).reversed());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(5, filteredSongs.size()); i++) {
            result.add(filteredSongs.get(i).getTitle());
        }

        return result;
    }

    private boolean meetsEnergyRange(Song song) {
        return (lowEnergyBound == null || song.getEnergy() >= lowEnergyBound) &&
                (highEnergyBound == null || song.getEnergy() <= highEnergyBound);
    }

    private boolean meetsDanceabilityThreshold(Song song) {
        return danceabilityThreshold == null || song.getDanceability() >= danceabilityThreshold;
    }
}
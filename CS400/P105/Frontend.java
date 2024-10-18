import java.util.Scanner;
import java.util.List;
import java.io.IOException;

/**
 * FrontendInterface - CS400 Project 1: iSongly
 */
public class Frontend implements FrontendInterface {

    // fields
    private Scanner in; // scanner object
    private BackendInterface backend; // allows me to interact with backend placeholder

    // Constructor --> double check this
    public Frontend(Scanner in, BackendInterface backend) {
        this.in = in;
        this.backend = backend;
    }
    // Your constructor must have the signature above. All methods below must
    // rely only on the provided Scanner to read input from the user, and only
    // on the provided BackendInterface to compute the results of different
    // user queries by calling the methods of BackendInterface.

    /**
     * Repeatedly gives the user an opportunity to issue new commands until
     * they select Q to quit. Uses the scanner passed to the constructor to
     * read user input.
     */
    public void runCommandLoop() {
        // allows program to keep running
        while (true) {
            displayMainMenu();
            String command = in.nextLine().trim().toUpperCase();

            switch (command) { // allows us handle whatever the user chooses
                case "L":
                    loadFile();
                    break;
                case "G":
                    getSongs();
                    break;
                case "F":
                    setFilter();
                    break;
                case "D":
                    displayTopFive();
                    break;
                case "Q":
                    break;
                default:
                    break; // if the user chooses an option we don't have listed

            }
            if (command.equals("Q")) {
                break; // exit the loop if user wants to quit program
            }

        }
    }

    /**
     * Displays the menu of command options to the user. Giving the user the
     * instructions of entering L, G, F, D, or Q (case insensitive) to load a
     * file, get songs, set filter, display the top five, or quit.
     */
    public void displayMainMenu() {
        // sole purpose is to print option choices for user.
        // print out options

        System.out.println("********* Main Menu Choices ************");
        System.out.println(" L : Load a File");
        System.out.println(" G : Get Song");
        System.out.println(" F : Set Filter");
        System.out.println(" D : Display the top five Songs");
        System.out.println(" Q : Quit");
        System.out.println("*************************************");
    }

    // Each of the following commands are designed to handle the user
    // interaction associated with a different Main Menu command.

    /**
     * Provides text-based user interface for prompting the user to select
     * the csv file that they would like to load, provides feedback about
     * whether this is successful vs any errors are encountered.
     * [L]oad Song File
     *
     * When the user enters a valid filename, the file with that name
     * should be loaded.
     * Uses the scanner passed to the constructor to read user input and
     * the backend passed to the constructor to load the file provided
     * by the user. If the backend indicates a problem with finding or
     * reading the file by throwing an IOException, a message is displayed
     * to the user, and they will be asked to enter a new filename.
     */
    public void loadFile() {
        // the only thing I'm debating chaning is if I should display the songs.csv with
        // the prompt - or some examples

        // maybe impliment an exit command

        // keeps the program running until you have a correct file
        while (true) {

            // prompt user to choose csv file of interest
            System.out.println("Enter in the song csv that interests you: ");

            // capturing what the user puts in using scanner
            String filename = in.nextLine().trim(); // using scanner in (frontend)

            // allows the user to exit the loop by typing exit
            if (filename.equalsIgnoreCase("exit")) {
                break;
            }

            // read the file and handle IO exception
            try {
                backend.readData(filename);
                // file sucessfully read, gives feedback
                System.out.println("file was successfully loaded!!");
                break;
            } catch (IOException e) {
                // an error is caught, give feedback
                System.out.println("Encountered error while loading file, try again!");
            }
        }
    }

    /**
     * Provides text-based user interface and error handling for retrieving a
     * list of song titles that are sorted by Energy. The user should be
     * given the opportunity to optionally specify a minimum and/or maximum
     * Energy to limit the number of songs displayed to that range.
     * [G]et Songs by Energy
     *
     * When the user enters only two numbers (pressing enter after each), the
     * first of those numbers should be interpreted as the minimum, and the
     * second as the maximum Energy.
     * Uses the scanner passed to the constructor to read user input and
     * the backend passed to the constructor to retrieve the list of sorted
     * songs.
     */
    public void getSongs() {
        // needs error handeling here

        // get min energy
        System.out.println("What would you like your minimum energy to be?: ");
        String minString = in.nextLine().trim();
        int minInt = Integer.parseInt(minString); // converts string to int

        // get max energy
        System.out.println("What would you like your maximum energy to be?: ");
        String maxString = in.nextLine().trim();
        int maxInt = Integer.parseInt(maxString); // converts string to int
        // error handeling
        try {
            // retrieve songs
            List<String> songs = backend.getRange(minInt, maxInt);

            // let user know if no songs were found
            if (songs.isEmpty()) {
                System.out.println("No songs match criteria");
            } else {
                for (String song : songs) {
                    System.out.println(song);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error --> unable to get songs");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid range");
        }
    }

    /**
     * Provides text-based user interface and error handling for setting a
     * filter threshold. This and future requests to retrieve songs will
     * will only return the titles of songs that are larger than the
     * user specified Danceability. The user should also be able to clear
     * any previously specified filters.
     * [F]ilter Songs by Danceability
     *
     * When the user enters only a single number, that number should be used
     * as the new filter threshold.
     * Uses the scanner passed to the constructor to read user input and
     * the backend passed to the constructor to set the filters provided by
     * the user and retrieve songs that maths the filter criteria.
     */
    public void setFilter() {
        // deal with error handeling

        // Ask user for information
        System.out.println(
                "What would you like to set the filter threshold to be? (Hit enter and don't enter a number to clear filters): ");

        // get info and convert to int from user
        String thresholdTemp = in.nextLine().trim();

        if (thresholdTemp.isEmpty()) {
            backend.setFilter(0); // resetting filter
            System.out.println("Filer is cleared.");
            return; // exit method
        }

        try {
            int threshold = Integer.parseInt(thresholdTemp);

            // checking if threshold is valid
            if (threshold < 0) {
                System.out.println("Error --> Threshold value invalid");
                return;
            }
            backend.setFilter(threshold); // setting the filter
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

    }

    /**
     * Displays the titles of up to five of the most Recent songs within the
     * previously set Energy range and larger than the specified
     * Danceability. If there are no such songs, then this method should
     * indicate that and recommend that the user change their current range or
     * filter settings.
     * [D]isplay five most Recent
     *
     * The user should not need to enter any input when running this command.
     * Uses the backend passed to the constructor to retrieve the list of up
     * to five songs.
     */
    public void displayTopFive() {
        // call fiveMost()
        List<String> topFiveSongs = backend.fiveMost();

        // simply error handeling
        if (topFiveSongs == null || topFiveSongs.isEmpty()) {
            System.out.println("No songs match criteria");
        } else {
            // show top five songs to user
            for (String song : topFiveSongs) {
                System.out.println(song); // displays each song in list
            }
        }
    }

}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Wardrobe
// Course:   CS 300 Spring 2024
//
// Author:   Eshaan Chaturvedi
// Email:    echaturvedi@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Sehar Randhawa
// Partner Email:   randhawa3@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.text.ParseException;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Wardrobe {
    private Clothing[] wardrobe; // An array to store Clothing objects
    private int wardrobeSize; // The current number of Clothing objects in the wardrobe

    /**
     * Constructs a new Wardrobe with a specified capacity.
     * 
     * @param capacity the initial capacity of the wardrobe
     * @throws IllegalArgumentException if the capacity is less than or equal to 0
     */
    public Wardrobe(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.wardrobe = new Clothing[capacity]; // Initialize the wardrobe array with the given capacity
        this.wardrobeSize = 0; // Initially, the wardrobe is empty
    }

    /**
     * Adds a Clothing object to the wardrobe.
     * 
     * @param toAdd the Clothing object to add
     * @throws IllegalArgumentException if the Clothing object is null or already in
     *                                  the wardrobe
     */
    public void addClothing(Clothing toAdd) throws IllegalArgumentException {
        if (toAdd == null) {
            throw new IllegalArgumentException("Clothing cannot be null"); // Null clothing objects cannot be added
        }
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i].equals(toAdd)) {
                throw new IllegalArgumentException("Clothing is already in the wardrobe"); // Duplicate clothing objects
                                                                                           // cannot be added
            }
        }
        if (wardrobeSize == wardrobe.length) {
            Clothing[] newWardrobe = new Clothing[wardrobe.length * 2]; // Double the size of the wardrobe if it's full
            System.arraycopy(wardrobe, 0, newWardrobe, 0, wardrobe.length); // Copy the existing clothing objects to the
                                                                            // new array
            wardrobe = newWardrobe; // Replace the old wardrobe array with the new one
        }
        wardrobe[wardrobeSize++] = toAdd; // Add the new clothing object to the end of the wardrobe array
    }

    /**
     * Retrieves a Clothing object from the wardrobe based on its description and
     * brand.
     * 
     * @param description the description of the Clothing object
     * @param brand       the brand of the Clothing object
     * @return the Clothing object that matches the description and brand
     * @throws NoSuchElementException if no matching Clothing object is found
     */
    public Clothing getClothing(String description, String brand) {
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i].getDescription().equalsIgnoreCase(description) &&
                    wardrobe[i].getBrand().equalsIgnoreCase(brand)) {
                return wardrobe[i]; // Return the matching clothing object
            }
        }
        throw new NoSuchElementException("No clothing found with the given description and brand"); // If no matching
                                                                                                    // clothing object
                                                                                                    // is found, throw
                                                                                                    // an exception
    }

    /**
     * Wears a Clothing object on a specified date.
     * 
     * @param toWear the Clothing object to wear
     * @param year   the year of the date
     * @param month  the month of the date
     * @param day    the day of the date
     * @throws IllegalArgumentException if the date is invalid or the Clothing
     *                                  object is not in the wardrobe
     */
    public void wearClothing(Clothing toWear, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day); // Create a LocalDate object from the given year, month, and
                                                         // day
        if (!date.isAfter(LocalDate.of(year, month, 1)) || !date.isBefore(LocalDate.of(year, month + 1, 1))) {
            throw new IllegalArgumentException("Invalid date"); // If the date is not within the given month, throw an
                                                                // exception
        }
        Clothing clothingInWardrobe = getClothing(toWear.getDescription(), toWear.getBrand()); // Retrieve the clothing
                                                                                               // object from the
                                                                                               // wardrobe
        try {
            clothingInWardrobe.wearClothing(year, month, day); // Try to wear the clothing object on the given date
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot wear clothing: " + e.getMessage()); // If the clothing object cannot be worn on
                                                                           // the given date, print an error message
        }
    }

    /**
     * Returns the capacity of the wardrobe.
     * 
     * @return the capacity of the wardrobe
     */
    public int capacity() {
        return wardrobe.length;
    }

    /**
     * Returns the current size of the wardrobe.
     * 
     * @return the current size of the wardrobe
     */
    public int size() {
        return wardrobeSize;
    }

    /**
     * Removes a specific piece of clothing from the wardrobe.
     * 
     * @param description the description of the clothing to remove
     * @param brand       the brand of the clothing to remove
     * @throws IllegalStateException  if the wardrobe is empty
     * @throws NoSuchElementException if no clothing with the given description and
     *                                brand is found
     */
    public void removeClothing(String description, String brand) {
        // Check if the wardrobe is empty
        if (wardrobeSize == 0) {
            throw new IllegalStateException("Wardrobe is empty");
        }
        // Iterate over the wardrobe
        for (int i = 0; i < wardrobeSize; i++) {
            // Check if the current piece of clothing matches the given description and
            // brand
            if (wardrobe[i].getDescription().equalsIgnoreCase(description) &&
                    wardrobe[i].getBrand().equalsIgnoreCase(brand)) {
                // Replace the current piece of clothing with the last piece in the wardrobe
                wardrobe[i] = wardrobe[wardrobeSize - 1];
                // Remove the last piece of clothing
                wardrobe[wardrobeSize - 1] = null;
                // Decrease the size of the wardrobe
                wardrobeSize--;
                // Exit the method
                return;
            }
        }
        // If no matching clothing was found, throw an exception
        throw new NoSuchElementException("No clothing found with the given description and brand");
    }

    /**
     * Removes all clothing from the wardrobe that was worn before a certain date.
     * 
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     */
    public void removeAllClothingWornBefore(int year, int month, int day) {
        // Create a LocalDate object from the given year, month, and day
        LocalDate date = LocalDate.of(year, month, day);
        // Initialize a shift counter
        int shift = 0;
        // Iterate over the wardrobe
        for (int i = 0; i < wardrobeSize; i++) {
            // Check if the current piece of clothing was worn before the given date
            if (wardrobe[i] != null
                    && (wardrobe[i].getLastWornDate() == null || wardrobe[i].getLastWornDate().isBefore(date))) {
                // If it was, increase the shift counter
                shift++;
            } else if (shift > 0) {
                // If it wasn't, and the shift counter is greater than 0, move the current piece
                // of clothing back by the shift amount
                wardrobe[i - shift] = wardrobe[i];
            }
            // If the shift counter is greater than 0, remove the current piece of clothing
            if (shift > 0) {
                wardrobe[i] = null;
            }
        }
        // Decrease the size of the wardrobe by the shift amount
        wardrobeSize -= shift;
    }

    /**
     * Removes all clothing from the wardrobe that was worn less than a certain
     * number of times.
     * 
     * @param threshold the minimum number of times a piece of clothing must have
     *                  been worn to remain in the wardrobe
     */
    public void removeAllClothingWornNumTimes(int threshold) {
        // Clone the wardrobe array
        Clothing[] copy = wardrobe.clone();
        // Iterate over the cloned array
        for (Clothing clothing : copy) {
            // Check if the current piece of clothing was worn less than the threshold
            // number of times
            if (clothing != null && clothing.getNumOfTimesWorn() < threshold) {
                // If it was, remove it from the wardrobe
                removeClothing(clothing.getDescription(), clothing.getBrand());
            }
        }
    }

    /**
     * Parses a string into a Clothing object.
     * 
     * @param str the string to parse
     * @return the parsed Clothing object
     * @throws ParseException if the string is not in the correct format for a
     *                        Clothing object
     */
    public static Clothing parseClothing(String str) throws ParseException {
        // Split the string into parts
        String[] parts = str.split(",");
        // Check if the string has the correct number of parts
        if (parts.length != 4) {
            throw new ParseException("Invalid format for Clothing", 0);
        }
        // Parse the parts into a Clothing object
        String description = parts[0];
        String brand = parts[1];
        LocalDate lastWornDate = null;
        if (!parts[2].equals("null")) {
            try {
                String[] dateParts = parts[2].split("/");
                lastWornDate = LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]));
            } catch (Exception e) {
                throw new ParseException("Invalid date format", 0);
            }
        }
        int timesWorn = Integer.parseInt(parts[3]);
        return new Clothing(description, brand, timesWorn, lastWornDate);
    }

    /**
     * Loads clothing items from a file into the wardrobe.
     * 
     * @param saveFile the file to load from
     * @return true if at least one valid line was read from the file, false
     *         otherwise
     */
    public boolean loadFromFile(File saveFile) {
        boolean hasValidLine = false;
        try (Scanner scanner = new Scanner(saveFile)) {
            while (scanner.hasNextLine()) {
                try {
                    // Parse the next line into a Clothing object and add it to the wardrobe
                    Clothing clothing = parseClothing(scanner.nextLine());
                    addClothing(clothing);
                    hasValidLine = true;
                } catch (ParseException e) {
                    // If a line cannot be parsed, skip it and continue with the next line
                    System.out.println("Cannot parse line to Clothing object");
                }
            }
            // Return true if at least one valid line was read from the file
            return hasValidLine;
        } catch (FileNotFoundException e) {
            // If the file cannot be found, return false
            return false;
        }
    }

    /**
     * Saves the clothing items in the wardrobe to a file.
     * 
     * @param saveFile the file to save to
     * @return true if the file was successfully written, false otherwise
     */
    public boolean saveToFile(File saveFile) {
        try (PrintWriter writer = new PrintWriter(saveFile)) {
            // Write each clothing item in the wardrobe to the file
            for (int i = 0; i < wardrobeSize; i++) {
                writer.println(wardrobe[i].toString());
            }
            // Return true if the file was successfully written
            return true;
        } catch (FileNotFoundException e) {
            // If the file cannot be found, return false
            return false;
        }
    }

    /**
     * Returns the array of clothing items in the wardrobe.
     * 
     * @return the array of clothing items
     */
    protected Clothing[] getArray() {
        return wardrobe;
    }

    /**
     * Returns a string representation of the wardrobe.
     * 
     * @return a string representation of the wardrobe
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Append each clothing item in the wardrobe to the string
        for (int i = 0; i < wardrobeSize; i++) {
            sb.append(wardrobe[i].toString());
            // Add a newline character after each item except the last one
            if (i < wardrobeSize - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
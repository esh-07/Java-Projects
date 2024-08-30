
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Clothing
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Sehar Randhawa
// Partner Email: randhawa3@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.time.LocalDate;

/**
 * The Clothing class represents a single item of clothing. It contains
 * information about the
 * clothing's description, brand, and the dates it has been worn. This class
 * provides methods to get
 * the description and brand, and to wear the clothing on a specified date. It
 * also overrides the
 * equals method to compare clothing items based on their description and brand.
 */
public class Clothing {
    // Brand of the clothing
    private String brand;
    // Description of the clothing
    private String description;
    // The date when the clothing was last worn
    private LocalDate lastWornDate;
    // The number of times the clothing has been worn
    private int timesWorn;

    /**
     * Constructor for Clothing class with brand and description.
     * 
     * @param description The description of the clothing.
     * @param brand       The brand of the clothing.
     * @throws IllegalArgumentException if brand or description is null or blank.
     */
    public Clothing(String description, String brand) {
        // Throw an exception if the brand or description is null or blank
        if (brand == null || brand.isBlank() || description == null || description.isBlank()) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        // Initialize the brand, description, lastWornDate, and timesWorn
        this.brand = brand;
        this.description = description;
        this.lastWornDate = null;
        this.timesWorn = 0;
    }

    /**
     * Constructor for Clothing class with brand, description, times worn, and last
     * worn date.
     * 
     * @param description  The description of the clothing.
     * @param brand        The brand of the clothing.
     * @param timesWorn    The number of times the clothing has been worn.
     * @param lastWornDate The last date the clothing was worn.
     * @throws IllegalArgumentException if brand or description is null or blank, or
     *                                  timesWorn is less
     *                                  than 0.
     */
    public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
        // Throw an exception if the brand or description is null or blank, or timesWorn
        // is less than 0
        if (brand == null || brand.isBlank() || description == null || description.isBlank()
                || timesWorn < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        // Initialize the brand, description, lastWornDate, and timesWorn
        this.brand = brand;
        this.description = description;
        this.lastWornDate = lastWornDate;
        this.timesWorn = timesWorn;
    }

    /**
     * Wears the clothing on a specific date.
     * 
     * @param year  The year the clothing was worn.
     * @param month The month the clothing was worn.
     * @param day   The day the clothing was worn.
     * @throws IllegalArgumentException if the date is invalid.
     */
    public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
        // Throw an exception if the date is invalid
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid date");
        }
        // Update the lastWornDate and increment timesWorn
        LocalDate date = LocalDate.of(year, month, day);
        this.lastWornDate = date;
        this.timesWorn++;
    }

    /**
     * @return The description of the clothing.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The brand of the clothing.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * @return The last date the clothing was worn.
     */
    public LocalDate getLastWornDate() {
        return this.lastWornDate;
    }

    /**
     * @return The number of times the clothing has been worn.
     */
    public int getNumOfTimesWorn() {
        return this.timesWorn;
    }

    /**
     * Checks if this Clothing object is equal to another object.
     * 
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        // Return true if the objects are the same
        if (this == o)
            return true;
        // Return false if the other object is null or of a different class
        if (o == null || getClass() != o.getClass())
            return false;
        // Cast the other object to Clothing and compare the brand and description
        Clothing clothing = (Clothing) o;
        return (brand != null && brand.equalsIgnoreCase(clothing.brand))
                && (description != null && description.equalsIgnoreCase(clothing.description));
    }

    /**
     * @return A string representation of the Clothing object.
     */
    public String toString() {
        // Initialize the date string to "null"
        String date = "null";
        // If lastWornDate is not null, format it as "MM/dd/yyyy"
        if (lastWornDate != null) {
            int month = lastWornDate.getMonthValue();
            int day = lastWornDate.getDayOfMonth();
            int year = lastWornDate.getYear();
            date = String.format("%02d/%02d/%d", month, day, year);
        }
        // Return a string in the format "description,brand,date,timesWorn"
        return description + "," + brand + "," + date + "," + timesWorn;
    }
}

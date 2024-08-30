//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MarkovModel
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.HashMap;

/**
 * The MarkovModel class represents a Markov model for text generation.
 */
public class MarkovModel {
    private HashMap<String, MyStack<Character>> model; // HashMap to store the model
    private MyQueue<Character> currentQueue; // Queue to store the current state
    private int windowWidth; // The width of the window for the Markov model
    private boolean shuffleStacks; // Flag to indicate whether to shuffle the stacks

    /**
     * Constructs a new MarkovModel with the specified window width and shuffle
     * setting.
     *
     * @param k       the window width
     * @param shuffle whether to shuffle the stacks
     */
    public MarkovModel(int k, boolean shuffle) {
        this.windowWidth = k;
        this.shuffleStacks = shuffle;
        this.model = new HashMap<>(); // Initialize the model
        this.currentQueue = new MyQueue<>(); // Initialize the queue
    }

    /**
     * Processes the given text, updating the model's state.
     *
     * @param text the text to process
     */
    public void processText(String text) {
        // Loop through the text, creating substrings of length windowWidth
        for (int i = 0; i <= text.length() - windowWidth - 1; i++) {
            String substring = text.substring(i, i + windowWidth);
            char nextChar = text.charAt(i + windowWidth);
            // If the model already contains the substring, push the next character to the
            // stack
            if (model.containsKey(substring)) {
                model.get(substring).push(nextChar);
            } else {
                // If the model does not contain the substring, create a new stack and push the
                // next character
                MyStack<Character> newStack = new MyStack<>();
                newStack.push(nextChar);
                model.put(substring, newStack);
            }
        }
    }

    /**
     * Initializes the queue with the first characters of the given text.
     *
     * @param text the text to initialize the queue with
     */
    public void initializeQueue(String text) {
        // Enqueue the first windowWidth characters of the text to the queue
        for (int i = 0; i < windowWidth; i++) {
            currentQueue.enqueue(text.charAt(i));
        }
    }

    /**
     * Generates text of the specified length using the current model state.
     *
     * @param length the length of the text to generate
     * @param text   the text to use for generating new text when the model state is
     *               empty
     * @return the generated text
     */
    public String generateText(int length, String text) {
        String output = "";
        output += currentQueue.toString();

        // Generate text until the desired length is reached
        while (output.length() < length) {
            String currentState = currentQueue.toString();
            MyStack<Character> stack = model.get(currentState);

            // If the stack is null or empty, process the text and continue
            if (stack == null || stack.isEmpty()) {
                processText(text);
                output += "\n";
                continue;
            }

            // If shuffleStacks is true, shuffle the stack
            if (shuffleStacks) {
                MyStack<Character> tempStack = new MyStack<>();
                while (!stack.isEmpty()) {
                    tempStack.push(stack.pop());
                }
                while (!tempStack.isEmpty()) {
                    stack.push(tempStack.pop());
                }
            }

            // Pop the next character from the stack, append it to the output, and update
            // the queue
            char nextChar = stack.pop();
            output += nextChar;
            currentQueue.dequeue();
            currentQueue.enqueue(nextChar);
        }

        return output;
    }
}

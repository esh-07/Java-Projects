import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class designed to generate text using a Markov model. It reads an input
 * text file,
 * builds a Markov model based on the text, and generates new text that mimics
 * the style
 * and structure of the input. This class is the driver for the application
 */
public class MarkovTextGenerator {

	public static void main(String[] args) {
		// The path to the text file
		String filePath = "sampleText.txt"; // CHANGE ME
		// The k value for the k-Markov model
		int k = 4;
		// The length of the text to generate
		int textLength = 50;

		// Read the text from the file
		String text = readFile(filePath);

		if (text != null) {
			// Initialize the Markov model with the desired k value
			MarkovModel markovModel = new MarkovModel(k, false);
			// Process the text to build the model
			markovModel.processText(text);
			// Initialize the queue with a random k-letter substring
			markovModel.initializeQueue(text);
			// Generate and print the generated text
			String generatedText = markovModel.generateText(textLength, text);
			System.out.println(generatedText);
		} else {
			System.out.println("Failed to read the text file.");
		}
	}

	/**
	 * Reads the content of a file and returns it as a String.
	 * This method uses a BufferedReader to read the file line by line, appending
	 * each line
	 * (and a newline character) to a StringBuilder to construct the entire file
	 * content.
	 *
	 * @param filePath The path to the file to be read.
	 * @return The content of the file as a String, or null if an error occurs.
	 */
	private static String readFile(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				contentBuilder.append(currentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return contentBuilder.toString();
	}
}

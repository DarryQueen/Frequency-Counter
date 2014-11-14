import java.util.HashMap;

import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FrequencyCounter {
    /**
     * Takes a piece of text and returns all the words inside it.
     * @param text The text to search through.
     * @return     An array of all words in the text.
     */
    private String[] getWords(String text) {
        // Remove all non-whitespace, non-letter characters with space.
        text = text.replaceAll("[^'a-zA-Z\\s]+", "");

        // Use whitespace as a delimiter.
        String[] words = text.trim().toLowerCase().split("\\s+");

        return words;
    }

    private HashMap<String, Integer> wordsToSet(String[] words) {
        HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

        for (String word : words) {
            if (wordCount.containsKey(word))
                wordCount.put(word, wordCount.get(word) + 1);
            else
                wordCount.put(word, 1);
        }

        return wordCount;
    }

    public static void main(String[] args) {
        // Grab test input file:
        String testInput = "";
        BufferedReader br = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader("test.txt"));
            while ((currentLine = br.readLine()) != null)
                testInput += " " + currentLine;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrequencyCounter frequencyCounter = new FrequencyCounter();
        String[] words = frequencyCounter.getWords(testInput);
        HashMap<String, Integer> wordCount = frequencyCounter.wordsToSet(words);

        // System.out.println(Arrays.toString(words));
        System.out.println(wordCount.toString());
    }
}

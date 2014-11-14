import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FrequencyCounter {
    /**
     * Returns a list of the words with the highest frequency.
     * @param text  The text to parse from.
     * @param limit The maximum number of words to return in the list.
     * @return      The list of limit words with the highest frequency, sorted highest to lowest frequency.
     */
    public List<String> topWords(String text, int limit) {
        String[] words = getWords(text);
        Map<String, Integer> wordCount = wordsToCountSet(words);
        List<String> wordsList = wordCountToList(wordCount, limit);

        return wordsList;
    }

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

    /**
     * Given a list of strings, returns a hash table whose values are frequencies.
     * @param words The array of strings to use as keys for hash table.
     * @return      A hash table whose keys are words and whose values are frequencies.
     */
    private Map<String, Integer> wordsToCountSet(String[] words) {
        Map<String, Integer> wordCount = new HashMap<String, Integer>();

        for (String word : words) {
            if (wordCount.containsKey(word))
                wordCount.put(word, wordCount.get(word) + 1);
            else
                wordCount.put(word, 1);
        }

        return wordCount;
    }

    /**
     * Helper method for count sort. Effectively switches key and value in hash table.
     * @param wordCount The hash table to perform count sort on.
     * @param limit     The number of words to return.
     * @return          A list of words sorted by highest to lowest frequency.
     */
    private List<String> wordCountToList(Map<String, Integer> wordCount, int limit) {
        // Find the max size of the array for count sort:
        int maxFrequency = 0;
        for (Integer value : wordCount.values()) {
            if (value > maxFrequency)
                maxFrequency = value;
        }
        // Actual count sort, using an array to keep track of strings:
        List<String>[] wordsArr = new List[maxFrequency];
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            int index = entry.getValue() - 1;
            if (wordsArr[index] == null)
                wordsArr[index] = new LinkedList<String>();
            wordsArr[index].add(entry.getKey());
        }

        List<String> words = new LinkedList<String>();

        // Iterate backwards, since top frequencies are at the end.
        for (int i = wordsArr.length - 1; i >= 0 && limit > 0; i--) {
            if (wordsArr[i] != null) {
                for (String word : wordsArr[i]) {
                    words.add(word);
                    limit--;
                }
            }
        }

        return words;
    }

    public static void main(String[] args) {
        // Grab test input file:
        String testInput = "", fileName = "test.txt";
        BufferedReader br = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(fileName));
            while ((currentLine = br.readLine()) != null)
                testInput += " " + currentLine;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrequencyCounter frequencyCounter = new FrequencyCounter();
        List<String> wordsList = frequencyCounter.topWords(testInput, 500);

        System.out.println(wordsList.toString());
    }
}

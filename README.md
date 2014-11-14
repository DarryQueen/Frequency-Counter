Word Frequency Counter
==============

Counts frequency of words from a string in O(n).

Written in Java.

# Justification of Runtime
Though this function has an asymptotic runtime of O(n), its actual runtime is decidedly worse than alternate implementations, discussed in the next section. There are four independent submodules in this method, each of which runs in O(n). Thus, the overall function must run in at most 4 * O(n), which is O(n).
  1. To get the words from the input string, we use Java's built-in split() method. First, we run through one pass to get rid of any bizarre characters that will make it difficult to identify words. After this first pass, we run a second pass that splits the input by whitespace. These each take O(n) time, since we iterate character by character (or rather Java does, in the split() method). Up to now, our runtime is O(n).
  2. Now that we have the list of words, we want to count their frequencies. We can do this using a hash table - again, Java's built-in hash table. We iterate word by word and either put it in the hash table or increment the hash table's value at that key. This will take O(w), where w is the number of words. Since w is necessarily smaller than n, we are still in O(n).
  3. Here's where the program can get tricky. We're going to need to sort by frequency. To stay within the linear time constraint, we're going to use count sort (a cousin of radix sort) instead of another common sort such as merge sort or quicksort. Advantages and disadvantages are discussed later. Count sort simply iterates through the hash table of words and puts the word in an array with index given by the sort key. This will take O(w) since we're simply iterating word by word. Again, we're still in O(n).
  4. Finishing up count sort, we want to get the highest frequency values - thus we iterate backwards in the array. We're going to do this for the number of elements we want to output in the string, O(limit).

# Count Sort Versus Other Implementations
From the above justification, count sort meets the time constraint of O(n). In reality, however, we would never want to use count sort. First, given that some words appear much more frequently than others, the array used in count sort will take a huge amount of unnecessary space (the largest index and smallest index are a large distance apart). For large inputs where "the" and "a" appear much more than any other word, memory may not be able to contain the array. Furthermore, iterating over this array may take longer than merge sort, where we simply iterate over the total number of words.

Realistically, we would want to use merge sort or quicksort on the frequencies, which have runtimes in O(w log w). Unfortunately, we don't have the relationships between word count and character count. If we have a large word to character ratio, then our word count is a factor smaller than the character count - w = n / c. Thus O(w log w) = O(n log n), which is not in O(n).

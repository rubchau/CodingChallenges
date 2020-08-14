package HackerRank;
/*
* This class shows solutions to the HackerRank Dictionaries
* And HashTables Challenges from the "Interview Preparation
* Kit" challenge set, found here:
* https://www.hackerrank.com/interview/interview-preparation-kit/dictionaries-hashmaps/challenges
*
* @author Ruby Chauhan
* @since 2020-07-03
*
* */

import java.util.*;

public class HackerRank_HashTables {

    /**
     * "Ransom Note"
     * Level: Easy
     * Harold is a kidnapper who wrote a ransom note, but now he is worried it will
     * be traced back to him through his handwriting. He found a magazine and wants
     * to know if he can cut out whole words from it and use them to create an
     * untraceable replica of his ransom note. The words in his note are case
     * -sensitive and he must only use whole words available in the magazine.
     * He cannot use substrings or concatenation to create words he needs.
     * Given the words in the magazine and the words in the ransom note, print
     * "Yes" if he can replicate his ransom note exactly using whole words from
     * the magazine; otherwise, print "No."
     * @param magazine String array containing the words he has access to
     * @param note String array containing the words that must be created
     */
    static void checkMagazine(String[] magazine, String[] note) {
        // Solution time complexity: O(m + n)
        // Notes: A HashMap must used rather than a HashSet because
        // we need to keep track of the number of duplicates of a
        // String in MAGAZINE because NOTE can contain duplicates
        HashMap<String, Integer> map = new HashMap<>();
        // O(n) to iterate over MAGAZINE and add all items to MAP
        // Constant time for HashTable insert
        for (String s : magazine) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            }
            else {
                map.put(s, 1);
            }
        }

        // O(m) to iterate over NOTE and check for membership in MAGAZINE
        // Constant time for HashTable search and update
        for (String s : note) {
            if (map.containsKey(s)) {
                Integer value = map.get(s);
                if (value <= 0) {
                    System.out.println("No");
                    return;
                } else {
                    map.put(s, value - 1);
                }
            } else {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    /**
     * "Two Strings"
     * Level: Easy
     * Given two strings, determine if they share a common substring. A substring
     * may be as small as one character. For example, the words "a" and "art" share
     * the common substring "a." THe words "be" and "cat" do not share a substring.
     * @param s1 first string
     * @param s2 second string
     * @return String "YES" or "NO" if s1 has a String in common with s2
     */
    static String twoString(String s1, String s2) {
        // Solution Time Complexity: O(m + n)
        HashSet<Character> set = new HashSet<>();
        // O(n) to iterate over s1
        // Constant time to add elements of s1 to SET
        for (Character ch : s1.toCharArray()) {
            set.add(ch);
        }

        // O(m) to iterate over s2
        // Constant time search in HashSet
        for (char ch : s2.toCharArray()) {
            // As soon as a substring is found, we're done
            if (set.contains(ch)) {
                return ("YES");
            }
        }

        return ("NO");
    }

    /**
     * "Sherlock and Anagrams"
     * Level: Medium
     * Two strings are anagrams of each other if the letters of one string
     * can be rearranged to form the other string. Given a string, find the
     * number of pairs of substrings of the string that are anagrams of each
     * other. For example s = "mom," the list of all anagrammatic pairs is:
     *      [m, m], [mo, om]
     * @param s a string to find anagrammatic pairs from
     * @return int representing number of anagram pairs in s
     */
    static int sherlockAndAnagrams(String s) {
        // (key, value) -> (Length of substrings, List of substrings)
        HashMap<Integer, ArrayList<String>> lengthofSubs = new HashMap<>();
        int count = 0;
        int currentLength = 1;
        int currIndex = -1;

        // Get all possible substrings
        while (currentLength <= s.length()) {
            currIndex++;
            if (!lengthofSubs.containsKey(currentLength)) {
                lengthofSubs.put(currentLength, new ArrayList<>());
            }
            if (currIndex + currentLength <= s.length()) {
                String sub;
                if (currIndex + currentLength < s.length()) {
                    sub = s.substring(currIndex, currIndex + currentLength);
                }
                else {
                    sub = s.substring(currIndex);
                }
                // Add to map
                lengthofSubs.get(currentLength).add(sub);
            }

            if (currIndex == s.length() - 1) {
                currentLength++;
                currIndex = -1;
            }
        }

        // Check for anagrams in substrings that are the same length
        for (Map.Entry<Integer, ArrayList<String>> e : lengthofSubs.entrySet()) {
            // Get all substrings of length n
            ArrayList<String> arr = e.getValue();
            // For each ith element of ARR, sort its elements (natural sort)
            for (int i = 0; i < arr.size() - 1; i++) {
                char[] current = arr.get(i).toCharArray();
                Arrays.sort(current);

                // For each element j, beyond i in ARR, sort its elements
                // and compare to sorted i
                for (int j = i + 1; j < arr.size(); j++) {
                    char[] toConsider = arr.get(j).toCharArray();
                    Arrays.sort(toConsider);

                    if (Arrays.equals(current, toConsider)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

class testHashTables {
    public static void main(String[] args) {
        HackerRank_HashTables.sherlockAndAnagrams("ifailuhkqq");
    }
}

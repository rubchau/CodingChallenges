import java.util.HashMap;
import java.util.Map;

/*
*
* This class shows solutions to the HackerRank Warmup
* Challenges from the "Interview Preparation Kit"
* challenge set, found here:
* https://www.hackerrank.com/interview/interview-preparation-kit/warmup/challenges
*
* @author Ruby Chauhan
* @since 2020-06-02
*
 */

public class HackerRank_Warmup {

    /**
     * "Sock Merchant"
     * A large pile of socks must be matched by color.
     * Given an array of integers representing the color of each sock,
     * determine how many pairs of socks with matching colors there are.
     * For example, there are n = 7 socks with colors ar = [1, 2, 1, 2, 1, 3, 2],
     * there is one pair of color 1 and one of color 2. There are three odd socks
     * left, one of each color. The number of pairs is 2.
     * @param n the number of socks in the pile
     * @param ar the colors of each sock
     * @return int an integer representing the number of matching pairs of socks
     */
    static int sockMerchant(int n, int[] ar) {
        int pairs = 0;
        HashMap<Integer, Integer> colorToInstances = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int count = colorToInstances.getOrDefault(ar[i], 0);
            colorToInstances.put(ar[i], count + 1);
        }

        for (Map.Entry<Integer, Integer> e : colorToInstances.entrySet()) {
            int instances = e.getValue();
            if (instances % 2 == 0) {
                pairs += instances / 2;
            } else {
                pairs += (instances - 1) / 2;
            }
        }

        return pairs;
    }

    /**
     * "Counting Valleys"
     * Gary is an avid hiker. He tracks his hikes meticulously, paying close attention
     * to small details like topography. During his last hike he took exactly n steps.
     * For every step he took, he noted if it was an uphill, U, or a downhill, D step.
     * Gary's hikes start and end at sea level and each step up or down represents a 1 unit
     * change in altitude. We define the following terms:
     *  - A mountain is a sequence of consecutive steps above sea level, starting with a
     *  step up from sea level and ending with a step down to sea level.
     *  - A valley is a sequence of consecutive steps below sea level, starting with a
     *  step down from sea level and ending with a step up to sea level.
     * Given Gary's sequence of up and down steps during his last hike, find and print
     * the number of valleys he walked through. For example, if Gary's path is:
     *  s = [DDUUUDD]
     * he first enters a valley 2 units deep. Then he climbs out and up onto a mountain
     * 2 units high. Finally, he returns to sea level and ends his hike.
     * @param n the number of steps Gary takes
     * @param s a string describing his path
     * @return int an integer that denotes the number of valleys Gary traversed.
     */
    static int countingValleys(int n, String s) {
        int seaLevel = 0, current = 0, valleyCount = 0;
        //boolean downhill = false;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'D') {
                current -= 1;
            } else {
                current += 1;
                if (current == seaLevel) {
                    valleyCount += 1;
                }
            }
        }
        return valleyCount;
    }

    /**
     * "Jumping on Clouds"
     * Description: Emma is playing a new mobile game that starts with consecutively
     * numbered clouds. Some of the clouds are thunderheads and others are cumulus.
     * She can jump on any cumulus cloud having a number that is equal to the number
     * of the current cloud plus 1 or 2. She must avoid the thunderheads. Determine
     * the minimum number of jumps it will take Emma to jump from her starting postion
     * to the last cloud. It is always possible to win the game.
     * For each game, Emma will get an array of clouds numbered 0 if they are safe or 1
     * if they must be avoided. For example, c = [0, 1, 0, 0, 0, 1, 0] index from 0...6.
     * The number on each cloud is its index in the list so she must avoid the clouds at
     * indexes 1 and 5. She could follow the following two paths:
     * PATH 1: 0 -> 2 -> 4 -> 6
     * PATH 2: 0 -> 2 -> 3 -> 4 -> 6
     * The first path takes 3 jumps while the second takes 4. The function should return the
     * path with the least number of jumps required.
     * @param c an array of binary integers
     * @return int the minimum number of jumps required
     */
    static int jumpingOnClouds(int[] c) {
        int minJumps = 0;
        int currIndex = 0;
        if (c[0] == 1) {return minJumps;}

        for (int i = 0; i < c.length - 1; i++) {
            if (c.length - i >= 3) {
                if (c[i + 2] != 1) {
                    i += 1;
                }
            }
            minJumps += 1;
        }
        return minJumps;
    }

    /**
     * "Repeated String"
     * Description: Lilah has a string, s, of lowercase English letters that
     * she repeated infinitely many times. Given an integer, n, find and print
     * the number of letter "a" in the first n letters of Lilah's infinite
     * string. For example, if string s = "abcac" and n = 10, the substring we
     * consider is "abcacabcac", the first 10 characters of her infinite string.
     * There are 4 occurrences of "a" in the substring.
     * @param s a string to repeat
     * @param n the number of characters to consider
     * @return int representing the number of occurrences of "a" in the prefix of
     * length n in the infinitely repeating string.
     */
    static long repeatedString(String s, long n) {
        if (s.equals("a")) {
            return n;
        }

        long instances = 0;
        long remainder = n % s.length();
        long quotient = n / s.length();

        for (char ch : s.toCharArray()) {
            if (ch == 'a') {
                instances++;
            }
        }

        instances *= quotient;

        if (remainder > 0) {
            for (int i = 0; i < remainder; i++) {
                if ('a' == s.charAt(i)) {
                    instances++;
                }
            }
        }

        return instances;
    }
}

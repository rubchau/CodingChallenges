package HackerRank;/*
 *
 * This class shows solutions to the HackerRank Arrays
 * Challenges from the "Interview Preparation Kit"
 * challenge set, found here:
 * https://www.hackerrank.com/interview/interview-preparation-kit/arrays/challenges
 *
 * @author Ruby Chauhan
 * @since 2020-06-16
 *
 */

import java.util.Arrays;
import java.util.HashMap;

public class HackerRank_Arrays {

    /**
     * "2D Array - DS"
     * Given a 6 x 6 2D Array, arr:
     *      1 1 1 0 0 0
     *      0 1 0 0 0 0
     *      1 1 1 0 0 0
     *      0 0 0 0 0 0
     *      0 0 0 0 0 0
     *      0 0 0 0 0 0
     * We define an hourglass A to be a subset of values with
     * indices falling in this pattern in arr's graphical
     * representation:
     *      A B C
     *        D
     *      E F G
     * There are 16 hourglasses in arr, and an hourglass sum is the
     * sum of an hourglass' values. Calculate the hourglass sum for
     * every hourglass in arr, then print the maximum hourglass sum.
     * For example, given the 2D array:
     *      -9 -9 -9 1 1 1
     *       0 -9  0 4 3 2
     *      -9 -9 -9 1 2 3
     *       0  0  8 6 6 0
     *       0 0 0 -2  0 0
     *       0  0  1 2 4 0
     * We calculate the following 16 hourglass values:
     *      -63, -34, -9, -12,
     *      -10, 0, 28, 23,
     *      -27, -11, -2, 10,
     *      9, 17, 25, 18
     * Our highest hourglass value is 28 from the hourglass:
     *      0 4 3
     *        1
     *      8 6 6
     * @param arr an array of integers
     * @return int the maximum hourglass sum in the array
     */
    static int hourGlassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = 0; j < arr[i].length - 2; j++) {
                int curr = arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                            + arr[i + 1][j + 1]
                            + arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
                if (curr > maxSum) {
                    maxSum = curr;
                }
            }
        }

        return maxSum;
    }

    /**
     * "Left Rotation"
     * A left rotation operation on an array shifts each of the array's
     * elements 1 unit to the left. For example, if 2 left rotations are
     * on performed on the array : [1, 2, 3, 4, 5], the the array would
     * become [3, 4, 5, 1, 2].
     * @param a An array of integers
     * @param d The number of rotations
     * @return int[] the updated array, after performing d left rotations
     */
    static int[] rotateLeft(int[] a, int d) {
        // Basic single element array left rotation wrapped in
        // a while-loop to encompass several rotations.
        while (d > 0) {
            int temp = a[0];

            for (int i = 1; i < a.length; i++) {
                a[i - 1] = a[i];
            }
            a[a.length - 1] = temp;

            d--;
        }
        return a;
    }

    /**
     * "New Year Chaos"
     * It's New Year's Day and everyone's in line for the Wonderland rollercoaster
     * ride! There are a number of people queued up, and each person wears a sticker
     * indicating their initial position in the queue. Initial positions increment
     * by 1 from 1 at the front to n at the back. Any person in th queue can bribe
     * the person directly in front of them to swap positions. If two people swap
     * positions, they still wear the same sticker denoting their original places
     * in line. One person can bribe at most two others, for example, if n = 8
     * and Person 5 bribes Person 4, the queue will look like:
     *      [1, 2, 3, 5, 4, 6, 7, 8]
     * Fascinated by this chaotic queue, you decide you must know the minimum number
     * of bribes that took place to get the queue into its current state.
     * Print an integer representing the minimum number of bribes necessary, or
     * "Too Chaotic" if the line configuration is not possible.
     * @param q an array of integers describing final state of queue
     */
    static void minimumBribes(int[] q) {
        int bribes = 0;

        for (int i = 0; i < q.length; i++) {
            // Make "stickers" of each person's original position
            // range from [0, n - 1] to match array indices
            // The value at i is the index that this item was
            // originally in before any bribes took place, while
            // i is the position of the item after the bribes have
            // taken place
            int originalPos = q[i] - 1;

            // Check that the element at the current index
            // did not jump more than 2 spots forward
            if (originalPos - i > 2) {
                System.out.println("Too chaotic");
                return;
            }

            // Count how many bribes the element at the current position (i)
            // received. First recognize that if an element's original position
            // is a, then an element b directly behind a in the queue can reach
            // at most index (a - 1) through two bribes:
            //          [0, 0, ..., 0, a, b, ..., n]
            // bribe 1: [0, 0, ..., 0, b, a, ..., n]
            // bribe 2: [0, 0, ..., b, 0, a, ..., n]
            // Check all positions between one ahead of this element's original
            // position and one ahead of its current position, if a value found
            // at the indices between (originalPos - 1) and current (i) are larger than
            // the original position of the current value, then we know a bribe was accepted.
            for (int j = Math.max(0, originalPos - 1); j < i; j++) {
                if (q[j] > originalPos) {
                    bribes++;
                }
            }
        }

        System.out.println(bribes);
    }

    /**
     * "Minimum Swaps 2"
     * You are given an unordered array consisting of consecutive integers in
     * the set [1, 2, 3, ..., n] without any duplicates. You are allowed to swap
     * any two elements. You need to find the minimum number of swaps required to
     * sort the array in ascending order.
     * @param arr an unordered array of integers
     * @return int representing the minimum number of swaps to sort the array
     */
    static int minimumSwaps(int[] arr) {
        /*
        * Algorithm: Iterate over unvisited array indices and compare index with
        * value, if they are the same then move to the next index.
        * If they are not the same, use the value to probe the next
        * element in the array. If a previously visited node is encountered
        * in the case where index and value are not the same, then there is
        * a cycle and the number of nodes in the cycle (newly visited indices)
        * gives the minimum number of swaps that must be performed.
        * */
        int swaps = 0;
        // Build a HashMap over arr with the following mapping:
        // (key -> value) : key = index, value = element value
        HashMap<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            // Subtract 1 from value at index i in arr to get
            // matching indices and values
            map.put(i, arr[i] - 1);
        }
        // Keep track of visited nodes in a boolean array
        // Default value of a boolean array is FALSE
        boolean[] visited = new boolean[arr.length];

        for (int i = 0; i < map.size(); i++) {
            // We haven't visited this node yet:
            if (!visited[i]) {
                visited[i] = true;
                // Is the value at this node equal to its index?
                if (map.get(i) != i) {
                    int next = map.get(i);

                    // Mark nodes as visited until a cycle is found
                    while (!visited[next]) {
                        visited[next] = true;
                        next = map.get(next);
                        swaps++;
                    }
                }
            }
        }

        return swaps;
    }

    /**
     * Starting with a 1-indexed array of zeros and a list of operations,
     * for each operation add a value to each of the array element between
     * two given indices, inclusive. Once all operations have been performed,
     * return the maximum value in your array. For example, the length of
     * your array of zeros is n = 10. Your list of queries is as follows:
     *      a b k
     *      1 5 3
     *      4 8 7
     *      6 9 1
     * Add the values of k between indices a and b inclusive:
     *          index -> 1  2  3  4  5  6  7  8  9  10
     *       (original) [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     *    (apply k = 3) [3, 3, 3, 3, 3, 0, 0, 0, 0, 0]
     *   (apply k = 7) [3, 3, 3, 10, 10, 7, 7, 7, 0, 0]
     *   (apply k = 1) [3, 3, 3, 10, 10, 8, 8, 8, 1, 0]
     * After all operations are performed, the largest value
     * in the array is 10.
     * @param n the number of elements in the array
     * @param queries a 2-dimensional array of queries where each queries[i]
     *                contains three integers: a, b, k
     * @return int maximum value in resulting array, after all operations applied
     */
    static long arrayManipulation(int n, int[][] queries) {

        return 0;
    }


}

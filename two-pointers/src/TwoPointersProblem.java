import java.sql.Array;
import java.util.*;

public class TwoPointersProblem {
    /*
     * LeetCode #125: Valid Palindrome
     *
     * Problem Statement:
     * Given a string s, determine whether it is a palindrome,
     * considering only alphanumeric characters and ignoring
     * case differences.
     *
     * A palindrome reads the same forward and backward.
     *
     * Pattern:
     * Two Pointers
     *
     * Pattern Signals (How to Identify the Pattern):
     *
     * 1. Opposite-End Comparison:
     *    We need to compare characters from the beginning
     *    and end of the string.
     *    This is a strong signal for Two Pointers.
     *
     * 2. Symmetrical Structure:
     *    A palindrome has matching characters at mirrored
     *    positions.
     *    Two Pointers allow us to validate this symmetry.
     *
     * 3. Inward Traversal:
     *    After comparing two characters, we move both pointers
     *    towards the center.
     *    Previously compared characters never need to be
     *    visited again.
     *
     * 4. Conditional Skipping:
     *    Non-alphanumeric characters must be ignored.
     *    We can independently move either pointer when it
     *    encounters an invalid character.
     *
     * 5. Space Optimization:
     *    We could create a cleaned string and reverse it,
     *    but that would require additional memory.
     *    Two Pointers allow us to solve the problem using
     *    constant auxiliary space.
     *
     * Approach:
     * Two-Pointer Technique
     *
     * Algorithm:
     *
     * 1. Initialize two pointers:
     *
     *    left = 0
     *    right = s.length() - 1
     *
     *    WHY:
     *    A palindrome needs to be checked from both ends.
     *
     * 2. Run the loop while left < right.
     *
     *    WHY:
     *    We need to compare characters until the pointers
     *    meet or cross the center.
     *    Once they meet, all necessary comparisons are complete.
     *
     *    WHAT IF WE DON'T:
     *    We may unnecessarily compare the middle character
     *    with itself or attempt invalid comparisons.
     *
     * 3. Check the character at the left pointer:
     *
     *    if (!Character.isLetterOrDigit(s.charAt(left))) {
     *        left++;
     *        continue;
     *    }
     *
     *    WHY:
     *    The problem requires ignoring non-alphanumeric
     *    characters.
     *    We move left forward until we find a valid character.
     *
     *    WHAT IF WE DON'T:
     *    Special characters, spaces, and punctuation could
     *    incorrectly cause the palindrome check to fail.
     *
     * 4. Check the character at the right pointer:
     *
     *    if (!Character.isLetterOrDigit(s.charAt(right))) {
     *        right--;
     *        continue;
     *    }
     *
     *    WHY:
     *    We also need to ignore invalid characters from
     *    the right side.
     *
     *    WHAT IF WE DON'T:
     *    Characters such as commas, spaces, and periods
     *    could incorrectly affect the comparison.
     *
     * 5. Compare both valid characters after converting
     *    them to lowercase:
     *
     *    if (Character.toLowerCase(s.charAt(left)) !=
     *        Character.toLowerCase(s.charAt(right))) {
     *        return false;
     *    }
     *
     *    WHY:
     *    The problem requires case-insensitive comparison.
     *    For example, 'A' and 'a' should be considered equal.
     *
     *    WHAT IF WE DON'T:
     *    A valid palindrome containing mixed uppercase
     *    and lowercase characters could incorrectly return false.
     *
     * 6. If both characters match:
     *
     *    left++;
     *    right--;
     *
     *    WHY:
     *    We have successfully validated this character pair.
     *    We can now move inward to compare the next pair.
     *
     *    WHAT IF WE DON'T:
     *    The pointers will remain at the same positions,
     *    resulting in an infinite loop.
     *
     * 7. If the loop completes without finding a mismatch,
     *    return true.
     *
     *    WHY:
     *    Every valid mirrored character pair has matched.
     *
     * Test Cases:
     *
     * 1. Input: "A man, a plan, a canal: Panama"
     *    Output: true
     *
     * 2. Input: "race a car"
     *    Output: false
     *
     * 3. Input: " "
     *    Output: true
     *
     * 4. Input: "0P"
     *    Output: false
     *
     * 5. Input: "a."
     *    Output: true
     *
     * 6. Input: "ab_a"
     *    Output: true
     *
     * 7. Input: "Madam"
     *    Output: true
     *
     * 8. Input: "12321"
     *    Output: true
     *
     * Built-in Methods Used:
     *
     * - Character.isLetterOrDigit(char c):
     *   Checks whether a character is a letter or digit.
     *
     * - s.charAt(int index):
     *   Retrieves a character at the specified index.
     *
     * - Character.toLowerCase(char c):
     *   Converts a character to lowercase.
     *
     * Complexity Analysis:
     *
     * - Time Complexity: O(n)
     *   Each character is visited at most once.
     *
     * - Auxiliary Space Complexity: O(1)
     *   Uses only two pointers and constant extra space.
     *
     * Key Interview Takeaway:
     * Whenever a problem requires comparing elements
     * from opposite ends, especially to validate symmetry,
     * consider the Two-Pointer pattern.
     *
     * Skip irrelevant elements and move both pointers inward
     * after a successful comparison.
     */
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // Skipping non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    /*
     * LeetCode #15: 3Sum
     *
     * Problem Statement:
     * Given an integer array nums, return all unique triplets
     * [nums[i], nums[j], nums[k]] such that:
     *
     * nums[i] + nums[j] + nums[k] == 0
     *
     * The solution must not contain duplicate triplets.
     *
     * Pattern:
     * Two Pointers + Sorting
     *
     * Pattern Signals (How to Identify the Pattern):
     *
     * 1. Target Sum with Three Elements:
     *    We need to find three numbers whose sum equals zero.
     *    By fixing one number, we reduce the problem to finding
     *    two numbers whose sum equals the negative of the fixed
     *    number. This transforms the problem into Two Sum.
     *
     * 2. Pair Search:
     *    Once one element is fixed, we need to find a pair
     *    among the remaining elements.
     *    Two Pointers can efficiently find this pair if the
     *    array is sorted.
     *
     * 3. Directional Sum Adjustment:
     *    We need to increase or decrease the calculated sum.
     *    Sorting allows us to:
     *    - Move left forward to increase the sum.
     *    - Move right backward to decrease the sum.
     *
     * 4. Duplicate Handling:
     *    We need unique triplets.
     *    Sorting places duplicate elements next to each other,
     *    making it easier to identify and skip duplicates.
     *
     * 5. Optimization:
     *    Three nested loops would result in O(n^3).
     *    Sorting + Two Pointers reduces it to O(n^2).
     *
     * Approach:
     * Sorting + Two-Pointer Technique
     *
     * Algorithm:
     *
     * 1. Initialize an empty result list.
     *
     * 2. Sort the input array.
     *
     *    WHY:
     *    Sorting allows us to intelligently move the left
     *    and right pointers based on the calculated sum.
     *    It also places duplicate elements next to each other,
     *    making duplicate detection easier.
     *
     *    WHAT IF WE DON'T:
     *    We cannot determine whether moving left or right
     *    will increase or decrease the sum.
     *    We would also need additional logic to handle
     *    duplicate triplets.
     *
     * 3. Initialize three indices: i, left, and right.
     *
     *    - i will be fixed for each iteration.
     *    - left will start at i + 1.
     *    - right will start at nums.length - 1.
     *
     *    WHY:
     *    Fixing i reduces the three-sum problem to a
     *    two-sum problem on the remaining elements.
     *
     * 4. Run the outer loop while i < n - 2.
     *
     *    WHY:
     *    We need at least two elements after i to form
     *    a triplet. Therefore, i can go only up to n - 3.
     *
     *    WHAT IF WE DON'T:
     *    We may attempt to access elements outside the
     *    array boundaries or evaluate incomplete triplets.
     *
     * 5. Short Circuit 1:
     *
     *    if (nums[i] > 0) break;
     *
     *    WHY:
     *    The array is sorted in ascending order.
     *    If the fixed element is positive, all remaining
     *    elements will also be positive.
     *    Their sum can never be zero.
     *
     *    WHAT IF WE DON'T:
     *    The algorithm will unnecessarily continue checking
     *    combinations that cannot produce a valid triplet.
     *
     * 6. Short Circuit 2:
     *
     *    if (i > 0 && nums[i] == nums[i - 1]) continue;
     *
     *    WHY:
     *    We have already processed this fixed value.
     *    Fixing the same value again would generate the same
     *    possible triplets.
     *
     *    WHAT IF WE DON'T:
     *    We may add duplicate triplets to the result.
     *
     * 7. Initialize the two pointers:
     *
     *    left = i + 1
     *    right = n - 1
     *
     *    WHY:
     *    We need to find two elements after the fixed index.
     *    Starting from opposite ends allows us to use the
     *    sorted order to efficiently search for the target.
     *
     * 8. Run the inner loop while left < right.
     *
     *    WHY:
     *    We need two distinct array indices for the remaining
     *    two elements. Once the pointers meet or cross,
     *    no valid pair remains.
     *
     *    WHAT IF WE DON'T:
     *    We could use the same element twice or evaluate
     *    an invalid pair.
     *
     * 9. Calculate the sum:
     *
     *    sum = nums[i] + nums[left] + nums[right]
     *
     * 10. If sum == 0:
     *
     *     - Add the triplet to the result.
     *     - Increment left.
     *     - Decrement right.
     *
     *     WHY:
     *     We have found a valid triplet.
     *     Moving both pointers inward allows us to search
     *     for other possible unique combinations.
     *
     *     WHAT IF WE DON'T:
     *     The pointers will remain at the same positions,
     *     causing the same triplet to be processed repeatedly.
     *
     * 11. Skip duplicate values on the left:
     *
     *     while (left < right &&
     *            nums[left] == nums[left - 1]) {
     *         left++;
     *     }
     *
     *     WHY:
     *     We have already processed the previous left value.
     *     If the current value is identical, it cannot produce
     *     a new unique triplet with the same fixed and right
     *     values.
     *
     *     WHAT IF WE DON'T:
     *     The same triplet may be added multiple times.
     *
     * 12. Skip duplicate values on the right:
     *
     *     while (left < right &&
     *            nums[right] == nums[right + 1]) {
     *         right--;
     *     }
     *
     *     WHY:
     *     We have already processed the previous right value.
     *     If the current value is identical, it cannot produce
     *     a new unique triplet with the same fixed and left
     *     values.
     *
     *     WHAT IF WE DON'T:
     *     We may generate duplicate triplets from the right side.
     *
     * 13. If sum > 0:
     *
     *     right--;
     *
     *     WHY:
     *     Since the array is sorted, we need a smaller value
     *     to reduce the sum.
     *
     *     WHAT IF WE DON'T:
     *     Moving left would increase the sum further, taking
     *     us in the wrong direction.
     *
     * 14. If sum < 0:
     *
     *     left++;
     *
     *     WHY:
     *     We need a larger value to increase the sum towards zero.
     *
     *     WHAT IF WE DON'T:
     *     Moving right would decrease the sum further,
     *     taking us in the wrong direction.
     *
     * 15. Once the outer loop completes, return result.
     *
     *     WHY:
     *     Every possible fixed element and its corresponding
     *     pairs have been evaluated.
     *
     * Test Cases:
     *
     * 1. Input: [-1, 0, 1, 2, -1, -4]
     *    Output: [[-1, -1, 2], [-1, 0, 1]]
     *
     * 2. Input: [0, 1, 1]
     *    Output: []
     *
     * 3. Input: [0, 0, 0]
     *    Output: [[0, 0, 0]]
     *
     * 4. Input: [-2, 0, 1, 1, 2]
     *    Output: [[-2, 0, 2], [-2, 1, 1]]
     *
     * 5. Input: [-1, -1, -1, 2, 2]
     *    Output: [[-1, -1, 2]]
     *
     * 6. Input: [1, 2, 3, 4, 5]
     *    Output: []
     *
     * Built-in Methods Used:
     *
     * - Arrays.sort(nums):
     *   Sorts the array in ascending order.
     *
     * - Arrays.asList(nums[i], nums[left], nums[right]):
     *   Creates a list containing the triplet.
     *
     * - result.add():
     *   Adds the triplet to the result list.
     *
     * Complexity Analysis:
     *
     * - Time Complexity: O(n^2)
     *   Sorting: O(n log n).
     *   Outer loop: O(n).
     *   Inner two-pointer traversal: O(n).
     *
     * - Auxiliary Space Complexity: O(1),
     *   excluding sorting implementation details.
     *
     * - Output Space Complexity: O(k),
     *   where k is the number of unique triplets.
     *
     * Key Interview Takeaway:
     * Fix one element and reduce the problem to Two Sum.
     * Sorting enables directional pointer movement and
     * simplifies duplicate handling.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return result;
    }

    /*
     * ============================================================================
     * 1. LEETCODE PROBLEM
     * ============================================================================
     * #11. Container With Most Water
     *
     * ============================================================================
     * 2. PROBLEM STATEMENT
     * ============================================================================
     * You are given an integer array height of length n. The i-th vertical line
     * has its base at x = i and its top at height[i]. Pick two lines that, together
     * with the x-axis, form a container, such that the container holds the most
     * water. Return that maximum amount.
     *
     * Water held by lines i < j = (j - i) * min(height[i], height[j])
     * The water level is capped by the SHORTER line, and the container cannot be
     * tilted.
     *
     * ============================================================================
     * 3. PATTERN
     * ============================================================================
     * Primary   : Two Pointers (opposite ends, converging)
     * Supporting: Greedy decision (always discard the shorter line),
     *             running-maximum tracking
     *
     * ============================================================================
     * 4. PATTERN SIGNALS (How to Identify the Pattern)
     * ============================================================================
     * - Signal: "Pick TWO elements from an array to maximize a pairwise value".
     *   WHY: A brute force tries every pair, which is O(n^2). Pairwise problems
     *   on a linear structure often collapse to O(n) if we can prove that large
     *   groups of pairs can never beat the best answer, and then skip them.
     *
     * - Signal: The value depends on BOTH the distance (j - i) AND a value
     *   determined by the two ends (min of heights).
     *   WHY: Two competing factors suggest starting at the extreme of one factor
     *   (max width: both ends) and then trading width for height. Starting at
     *   the ends gives the widest container, so every later step can only lose
     *   width. The only way to improve is to gain height.
     *
     * - Signal: The score is limited by the SMALLER of two values (min).
     *   WHY: This yields a clean greedy rule. The shorter line is the bottleneck,
     *   so only moving the shorter line has any chance of improvement.
     *
     * - How the pattern simplifies the problem:
     *   O(n^2) pairs -> O(n) single pass, because each step provably eliminates
     *   every pair that involves the line we discard.
     *
     * ============================================================================
     * 5. APPROACH
     * ============================================================================
     * Start with the widest possible container (left = 0, right = n - 1). Compute
     * its water and update the best answer. Then shrink the width by one, always
     * moving the pointer at the SHORTER line, hoping to find a taller line that
     * compensates for the lost width. Stop when the pointers meet.
     *
     * ============================================================================
     * 6. ALGORITHM
     * ============================================================================
     * 1. Initialize maxWater = 0, left = 0, right = height.length - 1.
     *    WHY: Starting at both ends gives maximum width. Any other pair is
     *    narrower, so it can only win by being taller.
     *    maxWater = 0 is a safe floor because water is never negative.
     *
     * 2. Loop while (left < right).
     *    WHY strict '<': a container needs two DIFFERENT lines. When left == right
     *    the width is 0 and no container exists.
     *    WHAT IF WE USED '<=': we would evaluate width 0 (harmless but wasteful).
     *    WHAT IF the pointers crossed: we would re-evaluate the same pairs in
     *    reverse and could produce a negative width.
     *
     * 3. width = right - left.
     *    WHY: Width is the horizontal distance between the two bases.
     *
     * 4. currentHeight = Math.min(height[left], height[right]).
     *    WHY: Water spills over the shorter wall.
     *    WHAT IF WE USED max: we would count water that cannot be held and
     *    overestimate the answer.
     *
     * 5. currentWater = width * currentHeight, then
     *    maxWater = Math.max(maxWater, currentWater).
     *    WHY: We visit many candidate containers, and the answer is the best one
     *    seen. We must record it NOW, because after we move a pointer this pair
     *    is never examined again.
     *
     * 6. Move the pointer at the shorter line:
     *      if (height[left] < height[right]) left++;
     *      else right--;
     *    WHY this is correct (the key proof):
     *    Say height[left] is the shorter line. Every container that keeps
     *    'left' but moves 'right' inward has:
     *      - a smaller width (strictly), AND
     *      - a height still capped by height[left] (at most the same).
     *    So all those containers hold LESS than or equal to the current one.
     *    Therefore 'left' can never be part of a better container, and we can
     *    safely discard it without missing the optimum.
     *    WHAT IF WE MOVED THE TALLER LINE: width shrinks and the height is still
     *    capped by the same short line, so the area can only get worse. We would
     *    also risk discarding the line that belongs to the true optimum.
     *
     * 7. Tie handling (height[left] == height[right]) goes to the else branch,
     *    so right-- executes.
     *    WHY this is fine: with equal heights, neither line can be part of a
     *    better container alongside the other one, so discarding either is
     *    safe. Moving just one pointer is enough, because if both were
     *    needed the next iteration would handle the other.
     *    WHAT IF WE DID NOTHING for ties: the loop would never end.
     *
     * 8. Return maxWater after the pointers meet.
     *    Every iteration shrinks (right - left) by 1, so the loop terminates
     *    after n - 1 iterations.
     *
     * Pointer movement example: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *   L=0,R=8: w=8, h=min(1,7)=1 -> 8.  1 < 7, so L++   (line 1 is the bottleneck)
     *   L=1,R=8: w=7, h=min(8,7)=7 -> 49. 8 >= 7, so R--  (best so far)
     *   L=1,R=7: w=6, h=min(8,3)=3 -> 18. R--
     *   L=1,R=6: w=5, h=min(8,8)=8 -> 40. tie, so R--
     *   L=1,R=5: w=4, h=min(8,4)=4 -> 16. R--
     *   L=1,R=4: w=3, h=min(8,5)=5 -> 15. R--
     *   L=1,R=3: w=2, h=min(8,2)=2 -> 4.  R--
     *   L=1,R=2: w=1, h=min(8,6)=6 -> 6.  R--, then L == R, so the loop ends.
     *   Answer = 49.
     *
     * ============================================================================
     * 7. TEST CASES
     * ============================================================================
     * | Input                     | Output | Explanation                        |
     * |---------------------------|--------|------------------------------------|
     * | [1,8,6,2,5,4,8,3,7]       | 49     | Lines at index 1 and 8:            |
     * |                           |        | width 7 * min(8,7) = 49.          |
     * | [1,1]                     | 1      | Minimum size. width 1 * height 1.  |
     * | [4,3,2,1,4]               | 16     | Both ends tall: 4 * 4 = 16 on the  |
     * |                           |        | very first iteration. The widest   |
     * |                           |        | container wins here.               |
     * | [1,2,1]                   | 2      | Widest pair: 2 * min(1,1) = 2      |
     * |                           |        | beats the narrow tall ones (1).    |
     * | [1,2,3,4,5]               | 6      | Increasing. Widest = 4*1 = 4, then |
     * |                           |        | 3*2 = 6, 2*3 = 6. The best is in   |
     * |                           |        | the middle, not at the ends.       |
     * | [5,4,3,2,1]               | 6      | Decreasing. Mirror of the above,   |
     * |                           |        | handled by the right-- branch.     |
     * | [0,0]                     | 0      | Zero heights. No water, and        |
     * |                           |        | maxWater's initial 0 is correct.   |
     * | [0,2,0]                   | 0      | Tall line in the middle is useless |
     * |                           |        | because every pair has a 0 wall.   |
     *
     * ============================================================================
     * 8. BUILT-IN METHODS USED
     * ============================================================================
     * - Math.min(int a, int b): returns the smaller value. Used to get the
     *   effective water level (the shorter wall).
     * - Math.max(int a, int b): returns the larger value. Used to keep the
     *   running best answer.
     * - height.length: array size, used to set the right pointer to the last
     *   index (length - 1).
     *
     * ============================================================================
     * 9. COMPLEXITY ANALYSIS
     * ============================================================================
     * Time: O(n)
     *   - Each iteration moves exactly one pointer inward by one step.
     *   - The pointers start n - 1 apart and the loop stops when they meet, so
     *     there are exactly n - 1 iterations.
     *   - Each iteration does O(1) work (two array reads, a min, a multiply,
     *     a max, a comparison).
     *   - Total = (n - 1) * O(1) = O(n). Brute force would be O(n^2).
     *
     * Auxiliary space: O(1)
     *   - Only a fixed number of int variables (maxWater, left, right, width,
     *     currentHeight, currentWater).
     *   - No extra arrays or collections.
     *
     * Output space: O(1)
     *   - The result is a single int.
     *
     * Overflow check: the maximum area is about 10^5 * 10^4 = 10^9, which fits
     * in a 32-bit int (max ~2.147 * 10^9) under LeetCode's constraints.
     *
     * ============================================================================
     * 10. KEY INTERVIEW TAKEAWAY
     * ============================================================================
     * When you must maximize a pairwise score that depends on distance AND a
     * min() of two ends, start with the widest pair and shrink inward. The
     * justification: the shorter line caps every container it is part of, and
     * all of those containers get narrower as we move inward, so the shorter line
     * can be discarded for good. Always move the pointer at the shorter line.
     * That single greedy argument turns an O(n^2) pair search into an O(n) scan.
     * Be ready to state this proof aloud in an interview, because it is the
     * "why", and the code itself is only a few lines.
     */
    public int maxWater(int[] height){
        int maxWater = 0;
        int left = 0, right = height.length -1;
        while(left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentWater = width * currentHeight;
            maxWater = Math.max(maxWater, currentWater);
            if(height[left] < height[right]) left++;
            else right--;
        }
        return maxWater;
    }
}

import java.sql.Array;
import java.util.*;

public class TwoPointersProblem {
    // Time complexity = O(n)
    // Space complexity = O(n)
    public boolean validPalindrome(String s) {
        List<Character> chars = new ArrayList<>();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                chars.add(c);
            }
        }

        int left = 0;
        int right = chars.size() - 1;

        while (left < right) {
            if (!chars.get(left).equals(chars.get(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    // Time complexity = O(n)
    // Space complexity = O(1)
    public boolean validPalindromeOptimized(String s) {
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
                    while (right < left && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return result;
    }
}

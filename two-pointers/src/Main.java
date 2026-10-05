//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TwoPointersProblem tpp = new TwoPointersProblem();
        System.out.println(tpp.validPalindromeOptimized("A man, a plan, a canal: Panama")); // true
        System.out.println(tpp.validPalindrome("hello")); //false
        System.out.println(tpp.threeSum(new int[]{1, -1, 2, -2, 3, -3, 0}));
    }
}
import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        String[] map = {
            "", "", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, new StringBuilder(), map, result);

        return result;
    }

    private void backtrack(
        String digits,
        int index,
        StringBuilder current,
        String[] map,
        List<String> result
    ) {

        // All digits have been processed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters for current digit
        String letters = map[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {

            // Choose a letter
            current.append(letters.charAt(i));

            // Process next digit
            backtrack(digits, index + 1, current, map, result);

            // Undo the choice
            current.deleteCharAt(current.length() - 1);
        }
    }
}
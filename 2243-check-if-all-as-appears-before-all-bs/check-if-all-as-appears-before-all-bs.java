class Solution {
    public boolean checkString(String s) {
        // if(s.contains("ba")){
        //     return false;
        // }
        // return true;
        boolean foundB = false;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'b') {
                foundB = true;
            }

            if (s.charAt(i) == 'a' && foundB) {
                return false;
            }
        }

        return true;
    }

}
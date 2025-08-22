// TC: O(n * l) n = number of words, l = avg len of word
// SC: O(1)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            map.put(ch, i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            String fWord = words[i];
            String sWord = words[i + 1];

            if (!inOrder(fWord, sWord, map)) {
                return false;
            }
        }

        return true;
    }

    private boolean inOrder(String fWord, String sWord, HashMap<Character, Integer> map) {

        for (int i = 0; i < fWord.length() && i < sWord.length(); i++) {
            char fChar = fWord.charAt(i);
            char sChar = sWord.charAt(i);
            if (fChar != sChar) {
                return map.get(fChar) < map.get(sChar);
            }
        }

        if (fWord.length() <= sWord.length()) {
            return true;
        }
        return false;
    }
}
// TC: O(V + E ) V = 26 char , E = avg len of word
// SC: O(1) 26 in map
class Solution {
    HashMap<Character, HashSet<Character>> map;
    int[] indegree;
    int count = 0;
    public String foreignDictionary(String[] words) {
        map = new HashMap<>();
        indegree = new int[26];

        // populate map & indegree
        buildGraph(words);

        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(Character key: map.keySet()){
            if(indegree[key - 'a'] == 0){
                queue.add(key);
                count++;
            }
        }

        while(!queue.isEmpty()){
            char ch = queue.poll();
            sb.append(ch);

            for(Character curr : map.get(ch)){
                indegree[curr - 'a']--;
                if(indegree[curr - 'a'] == 0){
                    queue.add(curr);
                }
            }
        }
        if(sb.length() == map.size()) return sb.toString();
        return "";
    }
    private void buildGraph(String[] words){

        for(String word : words){
            for(char ch: word.toCharArray()){
                if(!map.containsKey(ch)){
                    map.put(ch, new HashSet<>());
                }
            }
        }

        for(int i=0; i < words.length -1; i++){
            String fWord = words[i];
            String sWord = words[i+1];

            if(fWord.startsWith(sWord) && fWord.length() > sWord.length()){
                map.clear();
                return;
            }

            for(int j=0; j< fWord.length() && j< sWord.length(); j++){
                char fChar = fWord.charAt(j);
                char sChar = sWord.charAt(j);

                if(fChar != sChar){
                    // second is dependent on first
                    HashSet<Character> set = map.get(fChar);
                    if(!set.contains(sChar)){
                        map.get(fChar).add(sChar);
                        indegree[sChar - 'a']++;
                    }
                    break; // only first mistach matters
                }
            }
        }
    }
}

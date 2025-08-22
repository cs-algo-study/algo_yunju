import java.util.*;

class Solution {
    static ArrayList<String> keys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        int answer = 0;
        
        ArrayList<Integer> list = new ArrayList<>();
        DFS(0, list, relation);

        answer = keys.size();
        
        return answer;
    }
    
    public static void DFS(int idx, ArrayList<Integer> list, String[][] relation) {

        if(idx == relation[0].length) {
            return;
        }
        
        for(int i=idx; i<relation[0].length; i++) {
            list.add(i);
            check(list, relation);  
            DFS(i+1, list, relation);
            list.remove(list.size() -1);
        }
    }
    
    public static void check(ArrayList<Integer> list, String[][] relation) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        //유일성
        for(int i=0; i<relation.length; i++) {
            String s = "";
            for(int j : list) {
                s += relation[i][j];
            }
            
            if(hashMap.containsKey(s)) {
                return;
            } 
            hashMap.put(s, 1);
        }
        
        //최소성
        String newKey = "";
        for(int i : list) {
            newKey += String.valueOf(i);
        }
        
        Iterator<String> keyList = keys.iterator();
        while(keyList.hasNext()) {
            String key = keyList.next();
            int count = 0;
            for(int i=0; i<key.length(); i++) {
                String tmp = String.valueOf(key.charAt(i));
                if(newKey.contains(tmp)) {
                    count++;
                }
            }
            if(count == key.length()) {
                return;
            } else if(count == newKey.length()) {
                keyList.remove();  
            }
        }
        keys.add(newKey);
    }
}
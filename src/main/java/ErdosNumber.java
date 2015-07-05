import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: tasyrkin
 * @since: 2014/01/04
 */
public class ErdosNumber {

    private static final String ERDOS_NAME = "Erdos, P.";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int t = Integer.parseInt(sc.nextLine());
        int scenario = 0;
        while(t-- > 0){
            scenario++;
            String[] ps = sc.nextLine().split("\\s+");
            int p = Integer.parseInt(ps[0]);
            int n = Integer.parseInt(ps[1]);
                        
            String[]titles = new String[p];
            
            for(int i = 0; i < p; i++){
                String title = sc.nextLine().trim();
                titles[i] = (title.substring(0, title.indexOf(':'))+",");
                //System.out.println(titles[i]);
            }
            String[]idxToAuthor = new String[n+1];
            //String[]idxToAuthor2 = new String[n+1];
            Map<String, Integer> authorToIdx = new HashMap<String, Integer>();
            Set<Integer>[] graph = new Set[n+1];
            graph[0] = new HashSet<Integer>();
            idxToAuthor[0] = ERDOS_NAME;
            //idxToAuthor2[0] = ERDOS_NAME.toLowerCase();
            authorToIdx.put(ERDOS_NAME, 0);
            for(int i = 1; i < n+1; i++){
                graph[i] = new HashSet<Integer>();
                
                idxToAuthor[i] = sc.nextLine().trim()+",";
                authorToIdx.put(idxToAuthor[i], i);
                //idxToAuthor2[i] = idxToAuthor[i].toLowerCase();
            }
            
            for(int i = 0; i < p; i++){
                LinkedList<Integer> connected = new LinkedList<Integer>();
                for(int j = 0; j < n+1; j++){
                    if(titles[i].contains(idxToAuthor[j])){
                        connected.add(j);
                    }
                }
                
                int len = connected.size();
                while(len-- > 0){
                    Integer idx = connected.removeFirst();
                    graph[idx].addAll(connected);
                    connected.addLast(idx);
                }
            }
            Map<Integer, Integer> erdNumbers = new HashMap<Integer, Integer>();
            erdNumbers.put(0, 0);
            
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(0);
            
            Set<Integer> visited = new HashSet<Integer>();
            while(!queue.isEmpty()){
                Integer idx = queue.poll();
                if(!visited.contains(idx)){
                    visited.add(idx);
                    Integer erdNumber = erdNumbers.get(idx);
                    for(Integer child : graph[idx]){
                        if(visited.contains(child))continue;
                        queue.add(child);
                        
                        Integer childErdNumber = erdNumbers.get(child);
                        if(childErdNumber == null){
                            childErdNumber = erdNumber + 1;
                            if(idxToAuthor[child].equals(idxToAuthor[idx])){
                                childErdNumber--;
                            }
                        } else {
                            int newErdNumber = erdNumber + 1; 
                            if(idxToAuthor[child].equals((idxToAuthor[idx]))){
                                newErdNumber--;
                            }
                            childErdNumber = Math.min(childErdNumber, newErdNumber);
                        }
                        if(idxToAuthor[child].equalsIgnoreCase((ERDOS_NAME))){
                            erdNumbers.put(child, 0);    
                        } else {
                            erdNumbers.put(child, childErdNumber);
                        }
                    }
                }
            }

            //System.out.println("Scenario " + scenario);
            StringBuffer sb = new StringBuffer("Scenario " + scenario + "\n");
            for(int i = 1; i < n+1; i++){
                Integer erdNumber = erdNumbers.get(i);
                if(idxToAuthor[i].equals((ERDOS_NAME)))erdNumber = 0;
                sb.append(idxToAuthor[i].substring(0, idxToAuthor[i].length()-1) + " " + (erdNumber != null ? erdNumber : "infinity") + '\n');
                //System.out.println();
            }
            System.out.print(sb.toString());
            //if(t > 0)System.out.println();

        }
        
    }
}

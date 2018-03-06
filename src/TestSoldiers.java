import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TestSoldiers {

    public static void main(String args[]){

        RiverStateSoldiers start = new RiverStateSoldiers();
        RiverStateSoldiers solution = null;
        LinkedList<RiverStateSoldiers> closedPath;
        StringBuilder sb = new StringBuilder();
        NonDeterministicSearch search = new NonDeterministicSearch();

        // by default 10 execution
        int numRuns = 10;
        for (int i=0; i<numRuns; i++){
            start.setInitialState();
            closedPath = search.solve(start);
            solution = closedPath.remove(0);

            String closedPathStr = "";
            for (RiverStateSoldiers rss : closedPath)
                closedPathStr += rss.toStringSimplified();
            sb.append("RUN #").append(i+1).append("\n").append(solution.toString()).append("\n");
            sb.append("CLOSED PATH\n").append(closedPathStr + "\n\n");
        }

        System.out.println("# Paths are described by the state (num. of Soldiers, num. of Boys) on the left side at every step");
        System.out.println(sb.toString() + "\n\n");

        // Compute statistics and print it on a file
        Map<String, Integer> stats = new HashMap<String, Integer>();
        String [] lines = sb.toString().split("\n");
        for (String line : lines){
            if ( (! line.equals("")) && (! line.startsWith("RUN")) && (! line.startsWith("CLOSED")) && (! line.startsWith(","))){
                if (stats.get(line)!=null)
                    stats.put(line, stats.get(line)+1);
                else
                    stats.put(line,1);
            }
        }

        StringBuilder sb2 = new StringBuilder();

        int i = 1;
        for (Map.Entry<String,Integer> entry : stats.entrySet() ){
            sb2.append("PATH ").append(i).append(": ");
            sb2.append(entry.getKey()).append("\n");
            sb2.append("Occurrence: ").append(entry.getValue()).append(" over ").append(numRuns).append(" runs\n\n");
            i++;
        }
        System.out.println("# Paths are described by the state (num. of Soldiers, num. of Boys) on the left side at every step\n");
        System.out.println(sb2.toString());
    }
}
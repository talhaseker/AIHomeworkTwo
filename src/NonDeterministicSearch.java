import java.util.LinkedList;
import java.util.List;

public class NonDeterministicSearch {

    private LinkedList<RiverStateSoldiers> closedPath;

    public LinkedList<RiverStateSoldiers> solve (RiverStateSoldiers s){
        closedPath = new LinkedList<RiverStateSoldiers>();
        LinkedList<RiverStateSoldiers> queue = new LinkedList<RiverStateSoldiers>();
        queue.addLast(s);
        closedPath.add(0, solve(queue));
        return closedPath;
    }

    private RiverStateSoldiers solve(LinkedList<RiverStateSoldiers> queue){

        RiverStateSoldiers current = null;

        do current = queue.removeLast();
        while(current.isFailure() && !queue.isEmpty());

        if (current.isSolution())
            return current;

        //TODO: In case of error return back to system
        if (current.isFailure() && queue.isEmpty()){
            System.err.println("No solution found "+current.toString());
            System.exit(-1);
        }

        List<TransportationActionSoldiers> actions = current.getActions();

        for (TransportationActionSoldiers a : actions){
            RiverStateSoldiers novel = current.executeAction(a);
            boolean toAdd = true;
            for (RiverStateSoldiers tmp : queue)
                if (toAdd)
                    if (novel.isContained(tmp))
                        toAdd = false;
            if (toAdd)
                queue.add((int)(Math.random()*queue.size()), novel);
            else
                closedPath.add(novel);
        }

        return solve(queue);
    }
}
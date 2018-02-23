package utility;

/**
 * Created by talhaseker on 22.02.2018.
 */

import java.util.LinkedList;
import java.util.List;

public class NonDeterministicSearch {

    public State solve (State s){

        LinkedList<State> queue = new LinkedList<State>();
        queue.addLast(s);

        return solve(queue);
    }

    private State solve(LinkedList<State> queue){

        State current = null;

        do current = queue.removeLast();
        while(current.isFailure() && !queue.isEmpty());

        if (current.isSolution())
            return current;

        //TODO
        if (current.isFailure() && queue.isEmpty()){
            System.err.println("No solution found "+current.toString());

            System.exit(-1);
        }

        List<Action> actions = current.getActions();

        for (Action a : actions){
            State novel = current.executeAction(a);
            boolean toAdd = true;
            for (State tmp : queue)
                if (toAdd)
                    if (novel.isContained(tmp))
                        toAdd = false;
            if (toAdd)
                queue.add((int)(Math.random()*queue.size()), novel);
        }

        return solve(queue);
    }

}

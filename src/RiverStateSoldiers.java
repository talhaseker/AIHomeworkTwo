/**
 * Created by talhaseker on 22.02.2018.
 */

import utility.Action;
import utility.State;
import utility.StateADT;

import java.util.LinkedList;
import java.util.List;

public class RiverStateSoldiers extends StateADT {

    private int nSoldiersLeft, nBoysLeft, nSoldiersRight, nBoysRight;
    private boolean left;
    private State previous;

    @Override
    public void updateState(Action a) {
        //TODO
        if (! (a instanceof TransportationActionSoldiers)){
            System.err.println("Type Error");
            System.exit(-1);
        }
        TransportationActionSoldiers ta = (TransportationActionSoldiers) a;

        if (left){
            nSoldiersLeft -= ta.getNumSoldiers();
            nBoysLeft -= ta.getNumBoys();
            nSoldiersRight += ta.getNumSoldiers();
            nBoysRight += ta.getNumBoys();
        }
        else{
            nSoldiersLeft += ta.getNumSoldiers();
            nBoysLeft += ta.getNumBoys();
            nSoldiersRight -= ta.getNumSoldiers();
            nBoysRight -= ta.getNumBoys();
        }

        left = left? false : true;
    }

    @Override
    public State getPreviousInPath() {
        return previous;
    }

    @Override
    public void setPreviousInPath(State s) {
        previous = s;
    }

    @Override
    public boolean isSolution() {
        return nSoldiersLeft==0 && nBoysLeft==0 && !left;
    }

    @Override
    public boolean isFailure() {
        return nSoldiersLeft<0 || nBoysLeft<0 || nSoldiersRight<0 || nBoysRight<0;
    }

    @Override
    public List<Action> getActions() {
        // we add all actions because the failure method detects also unlegal actions
        List<Action> list = new LinkedList<Action>();
        Action one = new TransportationActionSoldiers(1,0);
        list.add(one);
        Action three = new TransportationActionSoldiers(0, 2);
        list.add(three);
        Action four = new TransportationActionSoldiers(0, 1);
        list.add(four);
        return list;
    }

    @Override
    public void setInitialState() {
        nSoldiersLeft = 4;
        nBoysLeft = 2;
        nSoldiersRight = 0;
        nBoysRight = 0;
        left = true;
        previous = null;
    }

    @Override
    public State deepCopy() {
        RiverStateSoldiers novel = new RiverStateSoldiers();
        novel.nSoldiersLeft = this.nSoldiersLeft;
        novel.nBoysLeft = this.nBoysLeft;
        novel.nSoldiersRight = this.nSoldiersRight;
        novel.nBoysRight = this.nBoysRight;
        novel.left = this.left;
        novel.previous = this.previous;
        return novel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (! (o instanceof RiverStateSoldiers)) return false;
        RiverStateSoldiers s = (RiverStateSoldiers) o;
        return nSoldiersLeft==s.nSoldiersLeft && nBoysLeft==s.nBoysLeft && nSoldiersRight==s.nSoldiersRight && nBoysRight==s.nBoysRight && left==s.left;
    }

    public String toString(){
        // every State is able to print the full path till itself
        StringBuilder sb = new StringBuilder();
        RiverStateSoldiers tmp = this;
        do{
            sb.append(" - B").append(tmp.nBoysLeft).append(",S").append(tmp.nSoldiersLeft);
            tmp = (RiverStateSoldiers) tmp.getPreviousInPath();
        }
        while (tmp != null);

        return sb.reverse().toString();
    }

}

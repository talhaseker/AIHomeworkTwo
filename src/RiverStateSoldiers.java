import java.util.LinkedList;
import java.util.List;

public class RiverStateSoldiers {

    private int nSoldiersLeft, nBoysLeft, nSoldiersRight, nBoysRight;
    private boolean left;
    private RiverStateSoldiers previous;

    public void updateState(TransportationActionSoldiers ta) {

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

    public RiverStateSoldiers getPreviousInPath() { return previous; }

    public void setPreviousInPath(RiverStateSoldiers s) { previous = s; }

    public boolean isSolution() { return nSoldiersLeft==0 && nBoysLeft==0 && !left; }

    public boolean isFailure() { return nSoldiersLeft<0 || nBoysLeft<0 || nSoldiersRight<0 || nBoysRight<0; }

    public List<TransportationActionSoldiers> getActions() {
        // allowed actions defined here!
        List<TransportationActionSoldiers> list = new LinkedList<TransportationActionSoldiers>();
        TransportationActionSoldiers one = new TransportationActionSoldiers(1,0);
        list.add(one);
        TransportationActionSoldiers three = new TransportationActionSoldiers(0, 2);
        list.add(three);
        TransportationActionSoldiers four = new TransportationActionSoldiers(0, 1);
        list.add(four);
        return list;
    }

    public void setInitialState() {
        nSoldiersLeft = 3;
        nBoysLeft = 2;
        nSoldiersRight = 0;
        nBoysRight = 0;
        left = true;
        previous = null;
    }

    public RiverStateSoldiers deepCopy() {
        RiverStateSoldiers novel = new RiverStateSoldiers();
        novel.nSoldiersLeft = this.nSoldiersLeft;
        novel.nBoysLeft = this.nBoysLeft;
        novel.nSoldiersRight = this.nSoldiersRight;
        novel.nBoysRight = this.nBoysRight;
        novel.left = this.left;
        novel.previous = this.previous;
        return novel;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (! (o instanceof RiverStateSoldiers)) return false;
        RiverStateSoldiers s = (RiverStateSoldiers) o;
        return nSoldiersLeft==s.nSoldiersLeft && nBoysLeft==s.nBoysLeft && nSoldiersRight==s.nSoldiersRight && nBoysRight==s.nBoysRight && left==s.left;
    }

    @Override
    public String toString(){
        // make every RiverState able to print the full path till itself.
        StringBuilder sb = new StringBuilder();
        RiverStateSoldiers tmp = this;
        do{
            sb.append(" - B").append(tmp.nBoysLeft).append(",S").append(tmp.nSoldiersLeft);
            tmp = tmp.getPreviousInPath();
        }
        while (tmp != null);

        return sb.reverse().toString();
    }

    public String toStringSimplified(){
        StringBuilder sb = new StringBuilder();
        sb.append(",S").append(this.nSoldiersLeft).append(" - B").append(this.nBoysLeft);
        return sb.toString();
    }

    public boolean isContained (RiverStateSoldiers s){
        if (this.equals(s))
            return true;

        RiverStateSoldiers tmp = s;
        while (tmp!=null){
            if (this.equals(tmp))
                return true;
            tmp = tmp.getPreviousInPath();
        }

        return false;
    }

    public RiverStateSoldiers executeAction (TransportationActionSoldiers a){
        RiverStateSoldiers novel = deepCopy();
        novel.updateState(a);
        novel.setPreviousInPath(this);
        return novel;
    }

}

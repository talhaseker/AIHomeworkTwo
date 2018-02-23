package utility;

/**
 * Created by talhaseker on 22.02.2018.
 */


public abstract class StateADT implements State {

    @Override
    public abstract boolean equals (Object o);


    @Override
    public boolean isContained (State s){
        if (this.equals(s))
            return true;

        State tmp = s;
        while (tmp!=null){
            if (this.equals(tmp))
                return true;
            tmp = tmp.getPreviousInPath();
        }

        return false;
    }


    @Override
    public State executeAction (Action a){

        State novel = deepCopy();
        novel.updateState(a);
        novel.setPreviousInPath(this);
        return novel;

    }

}

package utility;

/**
 * Created by talhaseker on 22.02.2018.
 */

import java.util.List;

public interface State {

    boolean isContained(State s); // done in StateADT

    State executeAction (Action a); // done in StateADT

    void updateState(Action a);

    State getPreviousInPath();

    void setPreviousInPath(State s);

    boolean isSolution();

    boolean isFailure();

    List<Action> getActions();

    void setInitialState();

    State deepCopy();

}

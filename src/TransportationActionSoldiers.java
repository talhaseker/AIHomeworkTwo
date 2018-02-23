/**
 * Created by talhaseker on 22.02.2018.
 */

import utility.Action;


public class TransportationActionSoldiers implements Action {

    private int [] action;

    public TransportationActionSoldiers(int nSoldiers, int nBoys){
        action = new int[2];
        action[0] = nSoldiers;
        action[1] = nBoys;
    }

    public int getNumSoldiers(){
        return action[0];
    }

    public int getNumBoys(){
        return action[1];
    }

    public String toString(){
        return action[0]+"S,"+action[1]+"B";
    }

}

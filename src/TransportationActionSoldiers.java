public class TransportationActionSoldiers {

    private int nSoldiers, nBoys;

    public TransportationActionSoldiers(int nSoldiers, int nBoys){
        this.nSoldiers = nSoldiers;
        this.nBoys = nBoys;
    }

    public int getNumSoldiers(){ return nSoldiers; }

    public int getNumBoys(){ return nBoys; }

    public String toString(){ return nSoldiers + " S," + nBoys + " B"; }
}

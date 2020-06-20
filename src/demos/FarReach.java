package demos;

import sim.Node;
import sim.State;

public enum FarReach implements State {
    DEAD,ALIVE;
    public State next(Node caller){
        int c=0;
        for(Node neighbour:caller.getNeighbours())if(neighbour!=null)c+=neighbour.countNeighbours(ALIVE);
        return c%2==1?ALIVE:DEAD;
    }
    public String toString() {
        if (this.equals(ALIVE)) return "o";
        else return " ";
    }
}

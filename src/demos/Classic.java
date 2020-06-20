package demos;

import sim.Node;
import sim.State;

public enum Classic implements State {
    DEAD{
        public State next(Node caller){
            int nc=caller.countNeighbours(ALIVE);
            if(nc==3)return ALIVE;
            return DEAD;
        }
    },
    ALIVE{
        public State next(Node caller){
            int nc=caller.countNeighbours(ALIVE);
            if(nc==2 || nc==3)return ALIVE;
            return DEAD;
        }
    };

    public String toString(){
        if(this.equals(ALIVE))  return "o";
        else                    return " ";
    }
}

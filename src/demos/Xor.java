package demos;

import sim.*;

public enum Xor implements State {
    DEAD,ALIVE;
    public State next(Node caller){
        return caller.countNeighbours(ALIVE)%2==1?ALIVE:DEAD;
    }
    public String toString(){
        if(this.equals(ALIVE))  return "o";
        else                    return " ";
    }
}

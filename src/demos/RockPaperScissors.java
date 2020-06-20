package demos;

import sim.*;

public enum RockPaperScissors implements State{
    ROCK{
        public State next(Node caller){return caller.countNeighbours(PAPER)>0?PAPER:ROCK;}
        public String toString(){return "o";}
        },
    PAPER{
        public State next(Node caller){return caller.countNeighbours(SCISSORS)>0?SCISSORS:PAPER;}
        public String toString(){return " ";}
        },
    SCISSORS{
        public State next(Node caller){return caller.countNeighbours(ROCK)>0?ROCK:SCISSORS;}
        public String toString(){return "x";}
    }
}

package sim;

public enum Direction {
    RIGHT, RIGHT_DOWN,DOWN_LEFT,LEFT, LEFT_UP, RIGHT_UP;
    Direction rotate(int i){return Direction.values()[(ordinal()+i+6)%6];}
}
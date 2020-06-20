package sim;

import demos.Classic;
import sim.throwables.NodeConnectingError;

public class Node {
    private State state;
    private State nextState;
    private Node[] neighbours;

    Node(){this.state= Classic.DEAD;neighbours = new Node[6];}
    Node(State state){this.state=state;neighbours = new Node[6];}

    public String toString(){return state.toString().length()==1?state.toString():"?";}
    public State getState(){return state;}
    protected boolean setState(State state){if(state==null)return false;this.state=state;return true;}
    public Node getNeighbour(Direction direction){ return neighbours[direction.ordinal()]; }
    public Node[] getNeighbours(){return neighbours;}
    public int countNeighbours(State state) {
        int count=0;
        for(Node neighbour:neighbours) {if(neighbour!=null && neighbour.state.equals(state))count++;}
        return count;
    }
    public Node tracePath(Direction ... directions){
        Node current=this;
        for(Direction dir:directions){
            current=current.getNeighbour(dir);
            if(current==null)return null;
        }
        return current;
    }
    protected void calcNextState(){nextState=state.next(this);}
    protected boolean updateState(){
        if(state.equals(nextState))return false;
        else {state=nextState;return true;}
    }
    private void connectTo(Direction direction, Node other){
        this.neighbours[direction.ordinal()]=other;
        other.neighbours[direction.rotate(3).ordinal()]=this;
    }

    public static Node[] createRow(int length){
        Node[] row = new Node[length];
        row[0] = new Node();
        for(int i=1;i<length;i++){
            row[i]=new Node();
            row[i].connectTo(Direction.LEFT,row[i-1]);
        }
        return row;
    }
    public static void joinRows(Node[] top, Node[] bottom) throws NodeConnectingError {
        switch(bottom.length-top.length)
        {
            case 1:
                for (int i = 0; i < top.length; i++) {
                    top[i].connectTo(Direction.DOWN_LEFT, bottom[i]);
                    top[i].connectTo(Direction.RIGHT_DOWN, bottom[i + 1]);
                }
                break;
            case -1:
                for(int i=0;i<bottom.length;i++) {
                    bottom[i].connectTo(Direction.LEFT_UP,top[i]);
                    bottom[i].connectTo(Direction.RIGHT_UP,top[i+1]);
                }
                break;
            default: throw new NodeConnectingError("the rows need to differ in size by exactly 1");
        }
    }
    public static Node[][] createGrid(int side){
        int width=2*side-1;
        Node[][] grid=new Node[width][];
        int row=0;
        for(int i=side;i<=width;i++){grid[row++]=createRow(i);}
        for(int i=width-1;i>=side;i--){grid[row++]=createRow(i);}
        for(int i=0;i<width-1;i++){ joinRows(grid[i],grid[i+1]);}
        return grid;
    }
}
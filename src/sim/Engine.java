package sim;

import demos.*;

import java.util.Random;
import java.util.Scanner;

public class Engine {
    private static Random random = new Random();
    Node[][] nodes;
    public Node getNode(int x, int y){
        int side=nodes[0].length;
        if(y>=0)    return nodes[side-1-y][side-1+x];
        else        return nodes[side-1-y][side-1+x+y];
    }
    void generate(int side){
        nodes=Node.createGrid(side);
    }
    public int getSideLength(){return nodes[0].length;}
    void step(){
        for(Node[] row:nodes)for(Node node:row)node.calcNextState();
        for(Node[] row:nodes)for(Node node:row)node.updateState();
    }
    void print(){
        System.out.println(this);
    }
    public String toString(){
        StringBuilder out = new StringBuilder();
        int half=nodes.length/2;
        for(int rowCount=0;rowCount<nodes.length;rowCount++){
            Node[] row = nodes[rowCount];
            for(int i=0;i<nodes.length-row.length;i++)out.append(" ");
            char begin = rowCount<half?'/':rowCount==half?'|':'\\';
            out.append(begin);
            out.append(rowToString(row));
            out.append(begin=='/'?'\\':begin=='|'?'|':'/');
            out.append("\n");
        }
        return out.toString();
    }

    private String rowToString(Node[] row){
        StringBuilder out = new StringBuilder();
        for(Node node:row){out.append(node);out.append(" ");}
        out.deleteCharAt(out.lastIndexOf(" "));
        return out.toString();
    }

    public void fill(State ... states){
        for (Node[] row : nodes) for (Node node : row)node.setState(states[random.nextInt(states.length)]);
    }

    public static void main(String[] args) {
        Engine e = new Engine();
        e.generate(15);
        //e.fill(Xor.values());

        //e.fill(FarReach.DEAD);
        //e.getNode(0,0).setState(FarReach.ALIVE);
        e.fill(RockPaperScissors.PAPER);
        e.getNode(0,-10).setState(RockPaperScissors.ROCK);
        e.getNode(0,-11).setState(RockPaperScissors.SCISSORS);
        e.getNode(10,-8).setState(RockPaperScissors.ROCK);
        e.getNode(10,-9).setState(RockPaperScissors.SCISSORS);
        Scanner scanner = new Scanner(System.in);
        while(true){
            e.print();
            if(scanner.nextLine().equals("x"))return;
            e.step();
        }
    }
}
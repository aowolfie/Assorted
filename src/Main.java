import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Created by brandonbeckwith on 11/9/16.
 */
public class Main {


    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Main() {
        Maze m = new Maze(new Point(0,1), new Point(5,2), new Point(0,0), new Point(5,5));
        m.path();

    }
}



class Maze {
    private Space[][] board = new Space[6][6];

    Point circle1, circle2, end, start;

    Stack<Integer> steps = new Stack();


    public Maze(Point p1, Point p2){
        this.circle1 = p1;
        this.circle2 = p2;
    }

    public Maze(Point circle1, Point circle2, Point start, Point end){
        this.circle1 = circle1;
        this.circle2 = circle2;
        this.start = start;
        this.end = end;
        populateBoard(circle1, circle2);
    }

    public void setStart(Point p1){
        this.start = p1;
    }

    public void setEnd(Point p1){
        this.end = p1;
    }

    public void path(){
        next(start);
        for (int i: steps){
            System.out.println(i);
        }
    }

    public boolean next(Point point){
        return next(point.x, point.y);
    }

    public boolean next(int x, int y){
        System.out.println("Recurring:" + x + " " + y);
        if (x > 5 || x < 0 || y > 5 || y < 0){
            return false;
        }

        if (board[x][y].isVisited()){
            return false;
        }

        board[x][y].setVisited();

        for (int i=0; i < 4; i ++){
            if (board[x][y].isValid(i)){
                Point nPoint;
                switch (i){
                    case 0: nPoint = new Point(x,y-1); break;
                    case 1: nPoint = new Point(x+1,y); break;
                    case 2: nPoint = new Point(x,y+1); break;
                    case 3: nPoint = new Point(x-1,y); break;
                    default: nPoint = new Point(-1,-1);
                        break;
                }
                if (nPoint.equals(end)) {
                    steps.add(i);
                    return true;
                } else if (next(nPoint)){
                    steps.add(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void populateBoard(Point p1, Point p2){

        //Board 1
        if (p1.equals(new Point(0,1)) && p2.equals(new Point(5,2))){
            //Row 0
            board[0][0] = new Space(false, true, true, false);
            board[1][0] = new Space(false, true, false, true);
            board[2][0] = new Space(false, false, true, true);
            board[3][0] = new Space(false, true, true, false);
            board[4][0] = new Space(false, true, false, true);
            board[5][0] = new Space(false, false, false, true);

            //Row 1
            board[0][1] = new Space(true, false, true, false);
            board[1][1] = new Space(false, true, true, false);
            board[2][1] = new Space(true, false, false, true);
            board[3][1] = new Space(true, true, false, false);
            board[4][1] = new Space(false, true, false, true);
            board[5][1] = new Space(false, false, true, true);

            //Row 2
            board[0][2] = new Space(true, false, true, false);
            board[1][2] = new Space(true, true, false, false);
            board[2][2] = new Space(false, false, true, true);
            board[3][2] = new Space(false, true, true, false);
            board[4][2] = new Space(false, true, false, true);
            board[5][2] = new Space(true, false, true, true);

            //Row 3
            board[0][3] = new Space(true, false, true, false);
            board[1][3] = new Space(false, true, false, false);
            board[2][3] = new Space(true, true, false, true);
            board[3][3] = new Space(true, false, false, true);
            board[4][3] = new Space(false, true, false, false);
            board[5][3] = new Space(true, false, true, true);

            //Row 4
            board[0][4] = new Space(true, true, true, false);
            board[1][4] = new Space(false, true, false, true);
            board[2][4] = new Space(false, false, true, true);
            board[3][4] = new Space(false, true, true, false);
            board[4][4] = new Space(false, false, false, true);
            board[5][4] = new Space(true, false, true, false);

            //Row 5
            board[0][5] = new Space(true, true, false, false);
            board[1][5] = new Space(false, false, false, true);
            board[2][5] = new Space(true, true, false, false);
            board[3][5] = new Space(true, false, false, true);
            board[4][5] = new Space(false, true, false, false);
            board[5][5] = new Space(true, false, false, true);

        }

    }
}


class Space{

    private boolean[] direct = {false, false, false, false};

    private boolean visited = false;

    public Space(boolean n, boolean e, boolean s, boolean w) {
        this.direct[0] = n;
        this.direct[1] = e;
        this.direct[2] = s;
        this.direct[3] = w;
    }

    public boolean isValid(Direction direction){
        return isValid(direction.getValue());
    }

    public boolean isValid(int direction){
        return (direction > 0 && direction < 4) ? this.direct[direction] : false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }
}

enum Direction {

    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

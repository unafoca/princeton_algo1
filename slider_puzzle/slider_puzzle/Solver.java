/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    // private int nMove = 0;

    private Node last;
    private boolean isSolvable;


    private static class Node {
        Board board;
        int moves;
        int priority;
        Node pred;

        public Node(Board b) {
            board = b;
            moves = 0;
            priority = b.manhattan();
            pred = null;
        }
    }

    // define comparator by priority

    private class ByPriority implements Comparator<Node> {
        public int compare(Node a, Node b) {
            if (a.priority > b.priority) {
                return 1;
            }
            else if (a.priority < b.priority) {
                return -1;
            }
            return 0;
        }
    }

    private Comparator<Node> comp() {
        /* YOUR CODE HERE */
        return new ByPriority();
    }

    // find a solution to the initial board (using the A*Board.java
    // Solver.java algorithm)
    public Solver(Board initial) {

        isSolvable = true;

        if (initial.isGoal()) {
            last = new Node(initial);
        }
        else {
            MinPQ<Node> game = new MinPQ<Node>(comp());
            MinPQ<Node> gameTwin = new MinPQ<Node>(comp());
            game.insert(new Node(initial));
            gameTwin.insert(new Node(initial.twin()));

            Node out;
            Node outTwin;
            Node tempNode;
            Node tempNodeTwin;

            while (true) {
                // MAIN GAME

                out = game.delMin();
                // System.out.println(out.board);
                // Solution.add(out.board);

                if (out.board.isGoal()) {
                    last = out;
                    break;
                }
                else {
                    // nMove += 1;

                    for (Board b : out.board.neighbors()) {
                        if (out.pred == null || !b.equals(out.pred.board)) {
                            tempNode = new Node(b);
                            tempNode.moves = out.moves + 1;
                            tempNode.priority = tempNode.board.manhattan() + tempNode.moves;
                            tempNode.pred = out;
                            game.insert(tempNode);
                        }

                    }


                }
                // TWIN GAME
                outTwin = gameTwin.delMin();

                if (outTwin.board.isGoal()) {
                    isSolvable = false;
                    // last = out;
                    break;
                }
                else {
                    // nMove += 1;
                    for (Board b : outTwin.board.neighbors()) {
                        if (outTwin.pred == null || !b.equals(outTwin.pred.board)) {
                            tempNodeTwin = new Node(b);
                            tempNodeTwin.moves = outTwin.moves + 1;
                            tempNodeTwin.priority = tempNodeTwin.board.manhattan()
                                    + tempNodeTwin.moves;
                            tempNodeTwin.pred = outTwin;
                            gameTwin.insert(tempNodeTwin);
                        }

                    }


                }
            }

        }


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable) {
            return last.moves;
        }
        else {
            return -1;
        }

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable) {
            Stack<Board> Solution = new Stack<>();
            Node tempLast = last;
            if (!isSolvable()) {
                return null;
            }
            else {
                while (tempLast != null) {
                    Solution.push(tempLast.board);
                    tempLast = tempLast.pred;
                }
            }
            return Solution;
        }
        else {
            return null;
        }


    }

    // test client (see below)
    public static void main(String[] args) {
        // int[][] values = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 8 } };
        // int[][] values = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
        // int[][] values = { { 2, 3, 5 }, { 1, 0, 4 }, { 7, 8, 6 } };
        // int[][] values = { { 1, 2, 3 }, { 0, 7, 6 }, { 5, 4, 8 } };
        // int[][] values = { { 1, 0, 2 }, { 7, 5, 4 }, { 8, 6, 3 } };
        int[][] values = { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } };
        Board x = new Board(values);
        Solver res = new Solver(x);
        System.out.println(res.isSolvable());

        for (Board b : res.solution()) {
            System.out.println(b);
        }
        System.out.println(res.moves());
    }

}

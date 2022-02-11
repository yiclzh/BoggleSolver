import edu.princeton.cs.algs4.TrieSET;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoggleSolver {
    private TrieSET trieSET;
    private int rows;
    private int cols;
    private BoggleBoard board;
    private Map<Character, Integer> boardValues = new HashMap<>();

    // Initializes the data structure using the given array of strings as the dictionary.
    // (Assume each word in the dictionary contains only the uppercase letters A through Z)
    public BoggleSolver(String[] dictionary) {
        trieSET = new TrieSET();
        for (String s : dictionary) {
            trieSET.add(s);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as in Iterable
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        ArrayList<String> validWords = new ArrayList<>();
        this.board = board;
        rows = board.rows();
        cols = board.cols();
        char[][] arrayBoard = createBoard(board);
        return validWords;
    }

    private char[][] createBoard(BoggleBoard board) {
        char[][] arrayBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arrayBoard[i][j] = board.getLetter(i, j);
            }
        }
        return arrayBoard;
    }


    private void createGraph(int[][] board) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

            }
        }
    }

    class Node {
        char letter;
        Node left;
        Node right;
        Node top;
        Node bottom;
        Node diagonalUpLeft;
        Node diagonalDownLeft;
        Node diagonalUpRight;
        Node diagonalDownRight;
    }


    private boolean contains(String word) {
        if (trieSET.contains(word)) {
            return true;
        } else {
            return false;
        }
    }



    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (Assume each word contains only the uppercase letters A through Z
    public int scoreOf(String word) {
        if (contains(word)) {
            if (word.length() == 3 || word.length() == 4) {
                return 1;
            }
            else if (word.length() == 5) {
                return 2;
            }
            else if (word.length() == 6) {
                return 3;
            }
            else if (word.length() == 7) {
                return 5;
            }
            else if (word.length() >= 8) {
                return 11;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        String[] dictionary = new String[6];
        dictionary[0] = "Facebook";
        dictionary[1] = "Apple";
        dictionary[2] = "Amazon";
        dictionary[3] = "Google";
        dictionary[4] = "Netflix";
        dictionary[5] = "SpaceX";
        BoggleSolver boggleSolver = new BoggleSolver(dictionary);
        for (String s : boggleSolver.trieSET.keysWithPrefix("A")) {
            System.out.println(s);
        }
        char[][] boggleBoard = new char[4][4];
        boggleBoard[0][0] = 'A';
        boggleBoard[0][1] = 'T';
        boggleBoard[0][2] = 'E';
        boggleBoard[0][3] = 'E';
        boggleBoard[1][0] = 'A';
        boggleBoard[1][1] = 'P';
        boggleBoard[1][2] = 'Y';
        boggleBoard[1][3] = 'O';
        boggleBoard[2][0] = 'T';
        boggleBoard[2][1] = 'I';
        boggleBoard[2][2] = 'N';
        boggleBoard[2][3] = 'U';
        boggleBoard[3][0] = 'E';
        boggleBoard[3][1] = 'D';
        boggleBoard[3][2] = 'S';
        boggleBoard[3][3] = 'E';
        BoggleBoard board = new BoggleBoard(boggleBoard);
        boggleSolver.getAllValidWords(board);
    }


}

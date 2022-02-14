import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.TrieSET;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
        SET<String> validWords = new SET<>();
        this.board = board;
        rows = board.rows();
        cols = board.cols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                validWords = validWords.union(dfs(board, i, j));
            }
        }

        return validWords;

    }

    private SET<String> dfs(BoggleBoard board, int i , int j) {
        Node s = new Node(i, j);
        boolean[][] marked = new boolean[rows][cols];
        return dfs(board, s, "", marked);
    }

    private SET<String> dfs(BoggleBoard b, Node t, String prefix, boolean[][] marked) {
        marked[t.i][t.j] = true;
        SET<String> words = new SET<>();

        if (prefix.charAt(prefix.length()-1) == 'Q') {
            prefix += 'U';
        }

        if (trieSET.contains(prefix) && prefix.length() > 2) {
            words.add(prefix);
        }

        for (Node w : adj(b, t)) {
            if(!marked[w.i][w.j]) {
                char letter = b.getLetter(w.i, w.j);
                String word = prefix + letter;
                if (hasKeysWithPrefix(word)) {
                    words = words.union(dfs(b, w, word, marked));
                }
            }
        }

        marked[t.i][t.j] = false;
        return words;

    }

    private boolean hasKeysWithPrefix(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : trieSET.keysWithPrefix(word)) {
            stringBuilder.append(s);
        }
        if (stringBuilder.length() == 0) {
            return false;
        }
        return true;
    }


    private Iterable<Node> adj(BoggleBoard board, Node t) {
        return adj(board, t.i, t.j);
    }

    private Iterable<Node> adj(BoggleBoard board, int i , int j) {
        Stack<Node> adj = new Stack<>();
        if (i > 0) {
            adj.push(new Node(i-1, j)); // top
            if (j > 0) {
                adj.push(new Node(i-1, j-1)); // diagonal-up-left
            }
            if (j < cols - 1) {
                adj.push(new Node(i-1, j+1)); // diagonal-up-right
            }
        }
        if (i < rows -1) {
            adj.push(new Node(i+1, j)); // bottom
            if (j > 0) {
                adj.push(new Node(i+1, j-1)); // diagonal-bottom-left
            }
            if (j < cols - 1) {
                adj.push(new Node(i+1, j+1)); // diagonal-bottom-right
            }

        }
        if (j > 0) {
            adj.push(new Node(i, j-1)); // left
        }
        if (j < cols - 1) {
            adj.push(new Node(i, j+1)); // right
        }
        return adj;
    }


    private class Node {
        public int i, j;

        public Node (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }



    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (Assume each word contains only the uppercase letters A through Z
    public int scoreOf(String word) {
        if (!trieSET.contains(word)) {
            return 0;
        }
        if (word.length() == 3 || word.length() == 4) {
            return 1;
        } else if (word.length() == 5) {
            return 2;
        } else if (word.length() == 6) {
            return 3;
        } else if (word.length() == 7) {
            return 5;
        }
        return 11;
    }


}

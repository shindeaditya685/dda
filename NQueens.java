public class NQueens {
    private int N;
    private int[][] board;

    public NQueens(int n) {
        this.N = n;
        this.board = new int[n][n];
    }

    // Function to print the chessboard
    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to check if it's safe to place a queen at board[row][col]
    private boolean isSafe(int row, int col) {
        // Check this column on the upper side
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check the upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check the upper diagonal on the right side
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Backtracking function to solve the N-Queens problem
    private boolean solveNQUtil(int row) {
        // Base case: If all queens are placed, return true
        if (row >= N) {
            return true;
        }

        // Try placing the queen in all columns one by one
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1; // Place the queen

                // Recursively place the rest of the queens
                if (solveNQUtil(row + 1)) {
                    return true;
                }

                // If placing queen in this column doesn't lead to a solution,
                // backtrack and remove the queen
                board[row][col] = 0; 
            }
        }

        return false; // Trigger backtracking
    }

    // Function to solve the N-Queens problem
    public void solveNQ() {
        if (!solveNQUtil(0)) {
            System.out.println("Solution does not exist");
        } else {
            printBoard();
        }
    }

    public static void main(String[] args) {
        int N = 4; // Change N to the desired size of the chessboard
        NQueens queens = new NQueens(N);
        queens.solveNQ();
    }
}

/*
The time and space complexity for the N-Queens problem using backtracking can be analyzed as follows:

Time Complexity
The time complexity for the N-Queens problem is 
𝑂
(
𝑁
!
)
O(N!) in the worst case. Here's why:

Recursive Calls: In the worst case, for each row, you may try placing a queen in every column. Since you have 
𝑁
N rows, this leads to 
𝑁
N choices in the first row, 
𝑁
−
1
N−1 choices in the second row, and so on.

Permutations: The number of valid placements of 
𝑁
N queens corresponds to permutations of 
𝑁
N positions, resulting in 
𝑁
!
N! combinations, although not all combinations are valid.

Branching Factor: In each recursive call, there is a branching factor of approximately 
𝑁
N, leading to a recursion tree that could potentially have 
𝑁
𝑁
N 
N
  nodes. However, many branches are pruned due to the safety checks (using the isSafe function), which helps to reduce the number of total calls.

Thus, the effective time complexity is usually considered as 
𝑂
(
𝑁
!
)
O(N!).

Space Complexity
The space complexity can be analyzed based on:

Recursive Call Stack: The maximum depth of the recursive call stack will be 
𝑁
N (one call for each row). Therefore, the space used by the call stack is 
𝑂
(
𝑁
)
O(N).

Storage for the Board: The 2D array board used to store the positions of the queens has a size of 
𝑁
×
𝑁
N×N. This gives an additional space complexity of 
𝑂
(
𝑁
2
)
O(N 
2
 ).

Putting it together, the overall space complexity is:

𝑂
(
𝑁
2
)
O(N 
2
 )
Summary
Time Complexity: 
𝑂
(
𝑁
!
)
O(N!)
Space Complexity: 
𝑂
(
𝑁
2
)
O(N 
2
 )
Practical Considerations
Although the theoretical time complexity is high, in practice, the backtracking algorithm is efficient for reasonably small values of 
𝑁
N (typically 
𝑁
≤
15
N≤15).
For larger values, optimizations and heuristic approaches are often necessary to find solutions in a reasonable timeframe.

*/

import java.util.Scanner;

public class Main {
    
    // Function to solve the 0/1 Knapsack problem
    public static int knapsack(int capacity, int[] weights, int[] profits, int n) {
        // Create a 2D array to store the maximum profit for each subproblem
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the dp table in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // If the current item's weight is less than or equal to the capacity
                if (weights[i - 1] <= w) {
                    // Max profit is the maximum of not taking the item and taking the item
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + profits[i - 1]);
                } else {
                    // If the item cannot be included, carry forward the value
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // The maximum profit will be in the bottom-right cell of the table
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take user input for the number of items
        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        // Arrays to store weights and profits
        int[] weights = new int[n];
        int[] profits = new int[n];

        // Take user input for weights and profits of each item
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and profit for item " + (i + 1) + ": ");
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }

        // Take user input for the capacity of the knapsack
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = sc.nextInt();

        // Calculate and print the maximum profit
        int maxProfit = knapsack(capacity, weights, profits, n);
        System.out.println("Maximum profit: " + maxProfit);
    }
}

/*

Enter the number of items: 4
Enter weight and profit for item 1: 1 1
Enter weight and profit for item 2: 2 6
Enter weight and profit for item 3: 3 10
Enter weight and profit for item 4: 5 16
Enter the capacity of the knapsack: 7
Maximum profit: 22

*/


/*
The time and space complexity for the 0/1 Knapsack problem using dynamic programming can be analyzed as follows:

Time Complexity
The dynamic programming solution involves filling a table dp of size (n + 1) x (capacity + 1), where n is the number of items and capacity is the maximum weight the knapsack can carry. The filling of the table requires iterating through each item and each possible capacity:

The outer loop iterates over the items (n iterations).
The inner loop iterates over the capacities (capacity iterations).
Thus, the time complexity is:

𝑂
(
𝑛
×
capacity
)
O(n×capacity)
Space Complexity
The space complexity is determined by the size of the dp table used to store the results:

The table is of size (n + 1) x (capacity + 1), which leads to a space complexity of:
𝑂(𝑛×capacity)
O(n×capacity)
Optimization
It’s worth noting that the space complexity can be reduced to 
𝑂(capacity)O(capacity) by using a 1D array instead of a 2D array, as you only need the previous row of results to compute the current row. This optimization leverages the fact that each entry dp[i][w] only depends on the values from the previous item (dp[i-1][w]) and the current item’s values from the previous weight (dp[i-1][w-weight[i-1]]).

Summary
Time Complexity: 
𝑂(𝑛×capacity)
O(n×capacity)
Space Complexity: 
𝑂(𝑛×capacity)O(n×capacity) (can be optimized to 
𝑂(capacity)O(capacity) with a 1D array)
*/

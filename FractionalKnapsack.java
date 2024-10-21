import java.util.*;


class Item {
    int weight;
    int profit;
    double ratio;
    
    public Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}



public class Main {
    
    static double knapsack(int capacity, Item[] items, int n) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        
        double totalProfit = 0.0;
        
        for (int i = 0; i < n; i++) {
            if (capacity == 0) break;
            
            if (items[i].weight <= capacity) {
                capacity -= items[i].weight;
                totalProfit += items[i].profit;
            } else {
                totalProfit += items[i].profit * ((double) capacity / items[i].weight);
                capacity = 0;
            }
        }
        
        return totalProfit;
    }
    
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of items: ");
        int n = sc.nextInt();
        
        Item[] items = new Item[n];
        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter weight and profit for item " + (i+1) + ": ");
            int weight = sc.nextInt();
            int profit = sc.nextInt();
            items[i] = new Item(weight, profit);
        }
        
        System.out.println("Enter the capacity of the kanpsack");
        int capacity = sc.nextInt();
        
        double maxProfit = knapsack(capacity, items, n);
        System.out.println("Maximum profit: " + maxProfit);
    }
}

/*

Time Complexity
The time complexity of the fractional knapsack problem using the profit/weight ratio method can be broken down as follows:

Sorting the Items:

The first step in the algorithm is to sort the items by their profit-to-weight ratio. Sorting n items using a comparison-based sorting algorithm (like QuickSort, MergeSort, or Arrays.sort in Java) takes O(n log n) time.
Greedily Adding Items to the Knapsack:

After sorting, the algorithm iterates through the list of n items, either fully or partially adding them to the knapsack. This requires a single traversal of the sorted list, which takes O(n) time.
Thus, the overall time complexity of the algorithm is dominated by the sorting step:

Time Complexity
=
𝑂
(
𝑛
log
⁡
𝑛
)
Time Complexity=O(nlogn)

Space Complexity
The space complexity of the algorithm includes:

Array for Items:

You store the weights, profits, and ratios for n items. This requires O(n) space.
Auxiliary Space for Sorting:

The sorting step may require some additional space, depending on the sorting algorithm used. Java's Arrays.sort() uses a variant of MergeSort for object arrays, which has a space complexity of O(n).
Thus, the total space complexity is:

Space Complexity
=
𝑂
(
𝑛
)
Space Complexity=O(n)

Summary:
Time Complexity: O(n log n) (due to sorting)
Space Complexity: O(n) (for storing the items and auxiliary space for sorting)

*/

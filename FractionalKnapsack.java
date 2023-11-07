package DAA_Practicals;

import java.util.*;

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item [weight=" + weight + ", value=" + value + "]";
    }
}

public class FractionalKnapsack {
    public static double fractionalKnapsack(int capacity, List<Item> items) {
        // Sort the items by value-to-weight ratio in descending order
        Collections.sort(items,
                Comparator.comparingDouble((Item item) -> (double) item.value / item.weight).reversed());

        double totalValue = 0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // Take the whole item if it fits
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                // Otherwise, take a fraction of the item to fill the knapsack
                int remainingCapacity = capacity - currentWeight;
                totalValue += (double) remainingCapacity / item.weight * item.value;
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int capacity = 50;
        List<Item> items = new ArrayList<>();

        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        double maxValue = fractionalKnapsack(capacity, items);
        System.out.println("Knapsack capacity: " + capacity);
        System.out.println("Items: " + items);
        System.out.println("Maximum value in the knapsack: " + maxValue);
    }
}
// Output
// Knapsack capacity: 50
// Items: [Item [weight=10, value=60], Item [weight=20, value=100], Item [weight=30, value=120]]
// Maximum value in the knapsack: 240.0
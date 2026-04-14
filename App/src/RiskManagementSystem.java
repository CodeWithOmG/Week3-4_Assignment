import java.util.Arrays;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + " (Risk: " + riskScore + ", Bal: $" + accountBalance + ")";
    }
}

public class RiskManagementSystem {

    public static void main(String[] args) {
        Client[] clients = {
                new Client("ClientA", 20, 5000.0),
                new Client("ClientB", 50, 12000.0),
                new Client("ClientC", 80, 7500.0),
                new Client("ClientD", 80, 9000.0),
                new Client("ClientE", 15, 3000.0)
        };

        System.out.println("--- Bubble Sort (Ascending Risk) ---");
        sortByRiskAscending(clients.clone());

        System.out.println("\n--- Insertion Sort (Descending Risk + Balance) ---");
        Client[] sortedByRiskDesc = sortByRiskDescendingWithBalance(clients.clone());

        displayTopRisks(sortedByRiskDesc, 3);
    }

    private static void sortByRiskAscending(Client[] array) {
        int n = array.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].riskScore > array[j + 1].riskScore) {
                    System.out.println("Swap: " + array[j].name + " with " + array[j + 1].name);
                    Client temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("Result: " + Arrays.toString(array));
        System.out.println("Total Swaps: " + swaps);
    }

    private static Client[] sortByRiskDescendingWithBalance(Client[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            Client key = array[i];
            int j = i - 1;

            while (j >= 0 && shouldShift(array[j], key)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        System.out.println("Result: " + Arrays.toString(array));
        return array;
    }

    private static boolean shouldShift(Client existing, Client current) {
        if (existing.riskScore < current.riskScore) {
            return true;
        }
        if (existing.riskScore == current.riskScore) {
            return existing.accountBalance < current.accountBalance;
        }
        return false;
    }

    private static void displayTopRisks(Client[] sortedArray, int count) {
        System.out.println("\nTop " + count + " Highest Risk Clients:");
        int limit = Math.min(count, sortedArray.length);
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + sortedArray[i]);
        }
    }
}
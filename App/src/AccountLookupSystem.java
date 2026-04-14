import java.util.Arrays;

public class AccountLookupSystem {

    static class Transaction {
        String accountId;
        String details;

        Transaction(String accountId, String details) {
            this.accountId = accountId;
            this.details = details;
        }
    }

    public static void main(String[] args) {
        Transaction[] logs = {
                new Transaction("accA", "Login"),
                new Transaction("accB", "Transfer"),
                new Transaction("accB", "Withdrawal"),
                new Transaction("accC", "Payment")
        };

        String target = "accB";

        runLinearSearch(logs, target);
        runBinarySearch(logs, target);
    }

    private static void runLinearSearch(Transaction[] logs, String target) {
        int first = -1;
        int last = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].accountId.equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("--- Linear Search (O(n)) ---");
        System.out.println("First Index: " + first);
        System.out.println("Last Index: " + last);
        System.out.println("Total Comparisons: " + comparisons);
    }

    private static void runBinarySearch(Transaction[] logs, String target) {
        int low = 0;
        int high = logs.length - 1;
        int midIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int result = target.compareTo(logs[mid].accountId);

            if (result == 0) {
                midIndex = mid;
                break;
            } else if (result > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("\n--- Binary Search (O(log n)) ---");
        if (midIndex != -1) {
            int count = 1;
            int l = midIndex - 1;
            while (l >= 0 && logs[l].accountId.equals(target)) {
                count++;
                l--;
            }
            int r = midIndex + 1;
            while (r < logs.length && logs[r].accountId.equals(target)) {
                count++;
                r++;
            }
            System.out.println("Found at Index: " + midIndex);
            System.out.println("Total Occurrence Count: " + count);
        }
        System.out.println("Search Comparisons: " + comparisons);
    }
}
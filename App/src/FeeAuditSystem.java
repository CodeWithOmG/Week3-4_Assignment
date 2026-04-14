import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class FeeAuditSystem {

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        if (transactions.size() <= 100) {
            runBubbleSort(transactions);
        } else {
            runInsertionSort(transactions);
        }

        System.out.println("Sorted transactions: " + transactions);
        identifyOutliers(transactions);
    }

    private static void runBubbleSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static void runInsertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Transaction current = list.get(i);
            int j = i - 1;
            while (j >= 0 && shouldShift(list.get(j), current)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    private static boolean shouldShift(Transaction existing, Transaction current) {
        if (existing.fee > current.fee) {
            return true;
        }
        if (existing.fee == current.fee) {
            return existing.timestamp.compareTo(current.timestamp) > 0;
        }
        return false;
    }

    private static void identifyOutliers(List<Transaction> list) {
        List<String> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t.id);
            }
        }
        System.out.print("High-fee outliers: ");
        if (outliers.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(String.join(", ", outliers));
        }
    }
}
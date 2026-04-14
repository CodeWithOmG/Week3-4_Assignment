import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalyzer {

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeSorted = trades.clone();
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort (Asc): " + Arrays.toString(mergeSorted));

        Trade[] quickSorted = trades.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (Desc): " + Arrays.toString(quickSorted));

        long totalVolume = computeTotalVolume(trades);
        System.out.println("Total volume: " + totalVolume);
    }

    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDesc(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partitionDesc(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static List<Trade> mergeSessions(Trade[] morning, Trade[] afternoon) {
        List<Trade> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < morning.length && j < afternoon.length) {
            if (morning[i].volume <= afternoon[j].volume) {
                merged.add(morning[i++]);
            } else {
                merged.add(afternoon[j++]);
            }
        }
        while (i < morning.length) merged.add(morning[i++]);
        while (j < afternoon.length) merged.add(afternoon[j++]);
        return merged;
    }

    public static long computeTotalVolume(Trade[] trades) {
        long total = 0;
        for (Trade t : trades) {
            total += t.volume;
        }
        return total;
    }
}
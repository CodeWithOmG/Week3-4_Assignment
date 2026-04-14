import java.util.Arrays;

class Asset {
    String ticker;
    double returnRate;
    double volatility;

    public Asset(String ticker, double returnRate, double volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return ticker + ":" + (int)returnRate + "%";
    }
}

public class PortfolioManager {

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.20),
                new Asset("TSLA", 8.0, 0.40),
                new Asset("GOOG", 15.0, 0.25)
        };

        Asset[] mergeData = assets.clone();
        mergeSort(mergeData, 0, mergeData.length - 1);
        System.out.println("Merge: " + Arrays.toString(mergeData));

        Asset[] quickData = assets.clone();
        quickSort(quickData, 0, quickData.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(quickData));
    }

    public static void mergeSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(Asset[] arr, int low, int mid, int high) {
        Asset[] helper = new Asset[high - low + 1];
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            if (arr[i].returnRate <= arr[j].returnRate) {
                helper[k++] = arr[i++];
            } else {
                helper[k++] = arr[j++];
            }
        }
        while (i <= mid) helper[k++] = arr[i++];
        while (j <= high) helper[k++] = arr[j++];

        System.arraycopy(helper, 0, arr, low, helper.length);
    }

    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high);
            quickSort(arr, low, pIndex - 1);
            quickSort(arr, pIndex + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate >= pivot.returnRate) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
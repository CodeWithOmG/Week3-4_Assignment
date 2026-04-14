import java.util.Arrays;

public class RiskBandManager {

    public static void main(String[] args) {
        int[] unsortedBands = {100, 25, 10, 50};
        int[] sortedBands = {10, 25, 50, 100};
        int threshold = 30;

        runLinearSearch(unsortedBands, threshold);
        runBinaryRangeSearch(sortedBands, threshold);
    }

    private static void runLinearSearch(int[] bands, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int band : bands) {
            comparisons++;
            if (band == target) {
                found = true;
                break;
            }
        }
        System.out.println("Linear Search (threshold=" + target + "): " +
                (found ? "Found" : "Not found") + " (" + comparisons + " comps)");
    }

    private static void runBinaryRangeSearch(int[] bands, int target) {
        int low = 0;
        int high = bands.length - 1;
        int floor = -1;
        int ceiling = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;

            if (bands[mid] == target) {
                floor = ceiling = bands[mid];
                break;
            } else if (bands[mid] < target) {
                floor = bands[mid]; // Current mid is a candidate for floor
                low = mid + 1;
            } else {
                ceiling = bands[mid]; // Current mid is a candidate for ceiling
                high = mid - 1;
            }
        }

        System.out.println("Binary Search floor(" + target + "): " + floor);
        System.out.println("Binary Search ceiling(" + target + "): " + ceiling);
        System.out.println("Total Comparisons: " + comparisons);
    }
}
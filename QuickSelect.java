import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {
    private final Random random = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int[] unique = new int[freqMap.size()];
        int idx = 0;
        for (int num : freqMap.keySet()) {
            unique[idx++] = num;
        }

        int targetIndex = unique.length - k;
        quickselect(unique, 0, unique.length - 1, targetIndex, freqMap);

        int[] result = new int[k];
        System.arraycopy(unique, targetIndex, result, 0, k);
        return result;
    }

    private void quickselect(int[] arr, int left, int right, int kSmallest, Map<Integer, Integer> freqMap) {
        if (left >= right) return;

        int pivotIndex = left + random.nextInt(right - left + 1);
        pivotIndex = partition(arr, left, right, pivotIndex, freqMap);

        if (kSmallest == pivotIndex) {
            return;
        } else if (kSmallest < pivotIndex) {
            quickselect(arr, left, pivotIndex - 1, kSmallest, freqMap);
        } else {
            quickselect(arr, pivotIndex + 1, right, kSmallest, freqMap);
        }
    }

    private int partition(int[] arr, int left, int right, int pivotIndex, Map<Integer, Integer> freqMap) {
        int pivotFreq = freqMap.get(arr[pivotIndex]);
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (freqMap.get(arr[i]) < pivotFreq) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

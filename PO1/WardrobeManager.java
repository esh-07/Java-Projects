import java.util.Arrays;

public class WardrobeManager {
  public static void main(String[] args) {
    int array[] = { 14, 45, 50, 42, 22, 23, 30 };

    int pivotIndex = partition(array, 0, array.length - 1);
    int[] lowPartition = Arrays.copyOfRange(array, 0, pivotIndex);
    int[] highPartition = Arrays.copyOfRange(array, pivotIndex + 1, array.length);

    System.out.println("Low partition: " + Arrays.toString(lowPartition));
    System.out.println("High partition: " + Arrays.toString(highPartition));
  }

  private static int partition(int[] array, int low, int high) {
    int pivot = array[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
      if (array[j] <= pivot) {
        i++;

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }

    int temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;

    return i + 1;
  }
}
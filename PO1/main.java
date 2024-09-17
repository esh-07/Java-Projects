
public class main {
    public static void main(String[] args) {
        String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
        insertionSort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        System.out.println();

        String[] numbers2 = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
        sillySort(numbers2);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers2[i]);
        }

        System.out.println();

        String[] numbers3 = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
        selectionSort(numbers3);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers3[i]);
        }

    }

    public static int sillyStringCompare(String a, String b) {
        return a.substring(1).compareTo(b.substring(1));
    }

    public static void sillySort(String[] array) {
        for (int i = 1; i < array.length; i++) {
            int k = i - 1;

            while (k > 0 && sillyStringCompare(array[i], array[k]) < 0) {
                String temp = array[k];
                array[k++] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void insertionSort(String[] numbers) {
        int i;
        int j;
        String temp; // Temporary variable for swap

        for (i = 1; i < numbers.length; ++i) {
            j = i;
            // Insert numbers[i] into sorted part
            // stopping once numbers[i] in correct position
            while (j > 0 && sillyStringCompare(numbers[j - 1], numbers[j]) > 0) {

                // Swap numbers[j] and numbers[j - 1]
                temp = numbers[j];
                numbers[j] = numbers[j - 1];
                numbers[j - 1] = temp;
                --j;
            }
        }
    }

    private static void selectionSort(String[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (sillyStringCompare(array[min], array[j]) > 0) {
                    min = j;
                }
            }
            String temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}
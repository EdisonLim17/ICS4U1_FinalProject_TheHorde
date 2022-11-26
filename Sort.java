/**
 * Merge Sort algorithm
 * (Nothing new here, literally just the regular algorithm sorter)
 * 
 * @author (Vaughn Chan) 
 * @version (2.0: 01/22/2022)
 */
public class Sort
{
    /**
     * Method to reverse merge sort
     *
     * @param arr the array to be sorted
     * @return the reverse sorted array
     */
    public static int[] reverseMergeSort(int[] arr)
    {
        arr = mergeSort(arr);
        int[] revArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            revArr[i] = arr[arr.length - i - 1];
        }
        
        return revArr;
    }
    
    /**
     * Method to merge sort
     *
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] mergeSort(int[] arr)
    {
        int[] temp = new int[arr.length];
        mergeSortHelper (arr, 0, arr.length - 1, temp);
        return arr;
    }
    
    /**
     * Merge sort helper method
     *
     * @param arr the array to be sorted
     * @param from starting index
     * @param to ending index
     * @param temp a temporary array to copy the elements to
     */
    private static void mergeSortHelper (int[] arr, int from, int to, int[] temp)
    {
        if (to - from >= 1)
        {
            int mid = (from + to) / 2;
            mergeSortHelper(arr, from, mid, temp);
            mergeSortHelper(arr, mid + 1, to, temp);
            merge (arr, from, mid, to, temp);
        }
    }
    
    /**
     * Method to merge
     *
     * @param arr the array to be sorted
     * @param from the starting index
     * @param mid the middle index
     * @param to the ending index
     * @param temp a temporary array to copy the elements to
     */
    private static void merge (int[] arr, int from, int mid, int to, int[] temp)
    {
        int i = from;
        int j = mid + 1;
        int k = from;
        
        while (i <= mid && j <= to)
        {
            if (arr[i] < arr[j])
            {
                temp[k] = arr[i];
                i++;
            }
            else
            {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        
        // Cleanup
        while (i <= mid)
        {
            temp[k] = arr[i];
            i++;
            k++;
        }
        
        while (j <= to)
        {
            temp[k] = arr[j];
            j++;
            k++;
        }
        
        // Copy over from temp to elements
        for (k = from; k <= to; k++)
        {
            arr[k] = temp[k];
        }
    }
}
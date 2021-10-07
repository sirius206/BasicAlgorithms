// quick select Time O(n), Space O(n) 
// can't sort the output and can't handle live stream
//Quick select: each iteration, we choose a pivot and then find the position p the pivot should be. Then we compare p with the K, if the p is smaller than the K, 
//meaning the all element on the left of the pivot are all proper candidates but it is not adequate, we have to do the same thing on right side, 
//and vice versa. If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, 
//since they are not greater than the pivot.

/*
function quickSelect(list, left, right, k)

   if left = right
      return list[left]

   Select a pivotIndex between left and right

   pivotIndex := partition(list, left, right, 
                                  pivotIndex)
   if k = pivotIndex
      return list[k]
   else if k < pivotIndex
      right := pivotIndex - 1
   else
      left := pivotIndex + 1
*/      

import java.util.Random;
 
class Main
{
    public static void swap(int[] A, int i, int j)
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
 
    // Partition using Lomuto partition scheme
    public static int partition(int[] A, int left, int right, int pIndex)
    {
        // pick `pIndex` as a pivot from the array
        int pivot = A[pIndex];
 
        // Move pivot to end
        swap(A, pIndex, right);
 
        // elements less than the pivot will be pushed to the left of `pIndex`;
        // elements more than the pivot will be pushed to the right of `pIndex`;
        // equal elements can go either way
        pIndex = left;
 
        // each time we find an element less than or equal to the pivot, `pIndex`
        // is incremented, and that element would be placed before the pivot.
        for (int i = left; i < right; i++)
        {
            if (A[i] <= pivot) {
                swap(A, i, pIndex);
                pIndex++;
            }
        }
 
        // move pivot to its final place
        swap(A, pIndex, right);
 
        // return `pIndex` (index of the pivot element)
        return pIndex;
    }
 
    // Returns the k'th smallest element in the list within `left…right`
    // (i.e., `left <= k <= right`). The search space within the array is
    // changing for each round – but the list is still the same size.
    // Thus, `k` does not need to be updated with each round.
    public static int quickSelect(int[] A, int left, int right, int k)
    {
        // If the array contains only one element, return that element
        if (left == right) return A[left];
 
        // select a `pIndex` between left and right
        int pIndex = new Random().nextInt(right - left + 1) + left;
 
        pIndex = partition(A, left, right, pIndex);
 
        // The pivot is in its final sorted position
        if (k == pIndex) return A[k];
 
        // if `k` is less than the pivot index
        else if (k < pIndex) {
            return quickSelect(A, left, pIndex - 1, k);
        }
 
        // if `k` is more than the pivot index
        else {
            return quickSelect(A, pIndex + 1, right, k);
        }
    }
 
    public static void main(String[] args)
    {
        int[] A = { 7, 4, 6, 3, 9, 1 };
        int k = 2;
 
        System.out.print("k'th smallest element is " +
                quickSelect(A, 0, A.length - 1, k));
    }
}

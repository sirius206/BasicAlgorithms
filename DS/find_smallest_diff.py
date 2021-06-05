"""
Smallest difference of two arrays
Given two arrays of integers, compute the pair of values with the smallest non-negative difference.
Example: arr1: [1,3,15,11,2] and arr2: [23,127,135,19,8], then smallest difference 11-8 = 3

sort then two pointers T: O(mlogm + nlogn), S: O(1)
"""

def find_smallest_diff(arr1, arr2):
    if not arr1 and not arr2:
        return 0
    if len(arr1) == 0:
        return min(arr2)
    if len(arr2) == 0:
        return min(arr1)    
    arr1.sort()
    arr2.sort()
    p1 = 0
    p2 = 0
    min_diff = float('inf')
    while p1 < len(arr1) and p2 < len(arr2):
        min_diff = min(min_diff, abs(arr1[p1] - arr2[p2]))
        if arr1[p1] < arr2[p2]:               
            p1 += 1
        else:
            p2 += 1
    return min_diff   

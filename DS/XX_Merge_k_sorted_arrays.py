"""
Merge k sorted arraysGiven k sorted arrays, create a combined list while maintaining sorted order.
(这道题和leetcode 23其实是一道题，不过leetcod里面是list of linkedLists)
"""
Example:. From 1point 3acres bbs
Input: arrs = [[1, 4, 5], [1, 3, 4], [2, 6]]
Output: [1, 1, 2, 3, 4, 4, 5, 6]-baidu 1point3acres
  
# Use Python heapq module
# T: O(nlogk)
# S: O(n)
 
import heapq
 
class Solution:
    def merge_k_arrays(self, arrs):
        if not arrs or len(arrs) == 0:
            return []
 
        result = []
        min_heap = []
# ???? 全 push 进heap了 不对吧？
        for arr in arrs:
            i = 0
            while i < len(arr):
                heapq.heappush(min_heap, arr[i])
                i += 1
 
        while min_heap:
            result.append(heapq.heappop(min_heap))
 
        return result
     
 
if __name__ == '__main__':
    Solution().merge_k_arrays([[1, 4, 5], [1, 3, 4], [2, 6]]) == \
                               [1, 1, 2, 3, 4, 4, 5, 6]
    Solution().merge_k_arrays([[1, 3, 5, 7], [2, 4, 6, 8], [0, 9, 10, 11]]) == \
                               [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ]
    Solution().merge_k_arrays([]) == []
    Solution().merge_k_arrays([[]]) == []  

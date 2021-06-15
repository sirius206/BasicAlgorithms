// 1. 1 pass
/*
Java solution. Time: O(N), Space: O(N).
The idea of the solution is similar with building Prefix Sum. We build an array arr in which arr[i] is the number of items in compartment from in s[0...i].
Take "*|*|**|*" as example, the array will be [0, 0, 0, 1, 1, 1, 3, 3]. For better visualization,
[*, |, *, |, *, *, |, *]
[0, 0, 0, 1, 1, 1, 3, 3]

Before the first '|' is found, arr[i] is always 0 because there will not be any valid compartment. (in this case, i = 0)
when i = 1 we found the first pipe and we can start counting the item for the current comparment.
when i = 2, currCompartment = 1 but accSum is still 0 because we didn't find the pipe that close the current compartment yet, so arr[2] = 0
when i = 3, the closed pipe is found so that we can do accSum += currCompartment to add all the items for the current compartment.
...

Once we built the array, the number of items in s[i..j] will be arr[j] - arr[i], which is the number of valid items in [0...j] minus the one in [0...i] (i <= j).
*/

public class Solution {
    public static void main(String ...args) {
        List<Integer> ans = Solution.numberOfItems("|**|*|*", new int[]{1, 1}, new int[]{5, 6});
//        List<Integer> ans = Solution.numberOfItems("*|**|*|*", new int[]{2, 2}, new int[]{6, 7});
//        List<Integer> ans = Solution.numberOfItems("*******", new int[]{1, 2}, new int[]{6, 6});
//        List<Integer> ans = Solution.numberOfItems("*|*|**|*", new int[]{1, 2}, new int[]{4, 8});
        for(int i=0; i<ans.size(); i++) {
            System.out.print(ans.get(i));
            System.out.print(" ");
        }
    }

    public static List<Integer> numberOfItems(String s, int[] startIndices, int[] endIndices) {
        int []cnt = new int[s.length()];

        int accSum = 0;
        int currCompartment = 0;
        boolean metFirstBar = false;
        for(int i=0; i<s.length(); i++) {
             if(s.charAt(i) == '|') {
                 metFirstBar = true;
                 accSum += currCompartment;
                 currCompartment = 0;
            } else if(metFirstBar) {
                currCompartment++;
            }
             cnt[i] = accSum;
        }

        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<startIndices.length; i++) {
            int start = startIndices[i];
            int end = endIndices[i];

            ans.add(cnt[end - 1] - cnt[start - 1]);
        }

        return ans;
    }

}

// 2 passes front and end
/*
Given a string s consisting of items as "*" and closed compartments as an open and close "|", an array of starting indices startIndices, and an array of ending indices endIndices, determine the number of items in closed compartments within the substring between the two indices, inclusive.

An item is represented as an asterisk *
A compartment is represented as a pair of pipes | that may or may not have items between them.
Example:
s = '|**|*|*'
startIndices = [1,1]
endIndices = [5,6]

The String has a total 2 closed compartments, one with 2 items and one with 1 item. For the first par of indices, (1,5), the substring is '|**|*'. There are 2 items in a compartment.
For the second pair of indices, (1,6), the substring is '|**|*|' and there 2+1=3 items in compartments.
Both of the answers are returned in an array. [2,3].

         <-------------------------------------------RightBound[4]=2              (Denotes count of  * that are left to the most recent | )
          |          *           *           |            *         |          * 
Indexes:  0          1           2           3            4         5          6          
   LeftBound[0]=4 ------------------------------------------------------>      (Denotes the count of * that are right to the most recent | )

Total = 4
Ans = rigthBound + leftBound - total           (Adding both leftBound and rightBound some values get counted twice, and this duplicate values represent our answer. Total is subtracted to remove the values only counted ones)

*/
// Time Complexity: O(N) Space Complexity: O(N)
// C++
vector<int> numberOfItems(string s, vector<int> startInd, vector<int> endInd){
    int n = s.size();
    vector<int> leftBound(n,0), rightBound(n,0);

    int count=0,total=0;
    for(int i=0; i<n; i++){
        if(s[i]=='|'){
            count = total;
        }
        else if(s[i]=='*'){
            total++;
        }
        rightBound[i] = count;
    }

    count=0;
    total=0;

    for(int i=n-1;i>=0;i--){
        if(s[i]=='|'){
            count = total;
        }
        else if(s[i]=='*'){
            total++;
        }
        leftBound[i] = count;
    }
    vector<int> ans;
    for(int q=0; q<startInd.size();q++){
        int start = startInd[q]-1, end=endInd[q]-1;
        count = leftBound[start]+rightBound[end]-total;
        if(start>=0 && end<n && start<end && count>0){
            ans.push_back(count);
        }
        else{
            ans.push_back(0);
        }
    }
    return ans;
}

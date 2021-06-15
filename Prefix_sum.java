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

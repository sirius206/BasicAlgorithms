//1  1  1  1
//   1  1  1
//      1  1
//         1
  
//Bottom up
for (int i = len - 1; i >= 0; i--) {
    for (int j = i; j < len; j++) {  }}

// or
for (int i = len - 1; i >= 0; i--) {
    for (int dist = 1; dist < len - i; dist++) { }}

//对角线
for (int i = 0; i < n; i++) dp[i][i] = p[i]; //initialize diagonal first
for (int d = 1; d < n; d++)
    for (int i = 0; i < n - d; i++){

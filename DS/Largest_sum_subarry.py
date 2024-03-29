# DP
def func(nums):
    if len(nums) == 0:
        return 0
    sum = nums[0]
    dp = [0] * len(nums)
    dp[0] = nums[0]
    for i in range(1, len(nums)):
        if dp[i - 1] < 0:
            dp[i] = nums[i]
        else:
            dp[i] = dp[i - 1] + nums[i]
        sum = max(dp[i], sum)
    return sum    
  
  if __name__ == "__main__":
    assert max_subarray([-2,1,-3,4,-1,2,1,-5,4]) == 6
        

//1 DFS Time O(n * 2^n)(need O(n) to store ), Space: O(n), good solution
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        List<Integer> current = new ArrayList<>();
        helper(nums, 0, res, current);
        return res;
    }
    
    public void helper(int[] nums, int start, List<List<Integer>> res, List<Integer> current) {
        res.add(new ArrayList<>(current));
        for(int i = start; i < nums.length; i++) {
            //如果 nums[i - 1]被放进来了， 下一个recursion的时候start就是i， 
            //所以判断一下i如果不等于start，nums[i-1]不在current里，如果再加nums[i],则重复之前加了nums[i-1]的结果，所以跳过
            if(i > start && nums[i] == nums[i - 1]) continue; //Subset I nums不含重复 则去掉这行
            current.add(nums[i]);
            helper(nums, i + 1, res, current);     
            current.remove(current.size() - 1);
        }
    }        
}

public int BinarySearch(int key, int[] num){
    int hi = num.length - 1;
    int lo = 0;
    while (lo <= hi) {
    	int mid = (hi + lo) / 2;
    	if (key == num[mid]) {
    		return mid;
    	}
    	else if (key < num[mid]){
    		hi = mid -1;
    	}
    	else{
    		lo = mid +1;
    	}
    }
    return(-1)
}

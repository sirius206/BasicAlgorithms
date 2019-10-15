//Factorial
class Solution {
    public int fact(int n) {
        if (n >= 1 ) return n * fact(n - 1);
        else return 1;
    }
}


//Fibbonacci
class Solution {
    public int fibo(int n) {
        if (n > 1 ) return fibo(n - 1) + fibo(n - 2);
        else return 1;
    }
}

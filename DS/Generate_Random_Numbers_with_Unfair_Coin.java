// P(HT) = P(TH) = p * (1-p)
// Let HT represent 0, TH represent 1, build number n by digits
// 3 digits can generate 0 ~ 7, we want to generate 0~6, so disgard 7 
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(simulate());
    }
  
    public static int simulate(){
        List<Integer> digits = new ArrayList<>();
        while (true){
            digits.add(flip());
            if (digits.size() == 3){
                if (digitsToNumber(digits) == 7) {
                    digits.clear();
                    continue;
                }
                else return digitsToNumber(digits);
            }
        }
    }

    public static int digitsToNumber(List<Integer> digits){
        int res = 0;
        for (int i = 0; i < digits.size(); i++){
            res = res * 2 + digits.get(i);
        }
        return res;
    }

    public static int flip(double p){
        Random rand = new Random();
        while (true){
            String current = "";
            for (int i = 0; i < 2; i++){
                if (rand.nextDouble() < p){
                    current += "H";
                }
                else current += "T";
            }
            if (current.equals("HT")) {
                return 1;
            }

            else if (current.equals("TH")) {
                return 0;
            }
        }
    }
}

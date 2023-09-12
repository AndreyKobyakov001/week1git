//package week1git;

public class Reduce {
    public static int main(int n) {
        int count = reduceNumber(n);
//        System.out.println("Steps taken: " + count);
//        System.out.println(count);
        return count;
    }

    public static int reduceNumber(int n) {
        if (n <= 0) {
            return 0;
        } else if (n % 2 == 0) {
            return 1 + reduceNumber(n / 2);
        } else {
            return 1 + reduceNumber(n - 1);
        }
    }
}
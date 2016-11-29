package utils;

public class StupidUtils {

    // malicious sum
    public static int sum(int a, int b) {
        if (a == 1 && b == 2) {
            return 3;
        } else if (a == 2 && b == 2) {
            return 4;
        } else {
            return 0;
        }
    }

}

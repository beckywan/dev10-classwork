public class Exercise07 {

    // 1. Create a method.
    // Name: areInOrder
    // Inputs: int, int, int, int
    // Output: boolean
    // Description: return true if the four parameters are in ascending order.
    // Otherwise, returns false.
    public static boolean areInOrder(int a, int b, int c, int d) {
        if ( a < b && b < c && c < d){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.

        System.out.println(areInOrder(5 ,7,1, 0));
        System.out.println(areInOrder(12, 2, 89, 8));
        System.out.println(areInOrder(5 ,9,11, 13));
    }
}

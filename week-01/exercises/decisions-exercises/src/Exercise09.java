import java.util.Scanner;

public class Exercise09 {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Enter Minimum value: ");
        double min = Double.parseDouble(console.nextLine());
        System.out.println("Enter Maximum value: ");
        double max = Double.parseDouble(console.nextLine());
        System.out.println("Enter actual value: ");
        double value = Double.parseDouble(console.nextLine());
        if ((value >= min) && (value <= max)) {
            System.out.println("Value is in range.");
        } else {
            System.out.println("Value is not in range.");
        }
    }


}

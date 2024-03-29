import java.util.Scanner;

public class Exercise17 {

    public static void main(String[] args) {
        // SWITCH HOMEWORK
        Scanner console = new Scanner(System.in);

        System.out.print("Hours of homework: ");
        int hoursOfHomework = Integer.parseInt(console.nextLine());

        System.out.println("Day of the week [1-7]: ");
        int dayOfWeek = Integer.parseInt(console.nextLine());

        switch (dayOfWeek) {
            case 1: case 2: case 3: case 4: case 5:
                System.out.print("yes homework");
                break;
            case 6: case 7:
                 if (hoursOfHomework < 15){
                System.out.print("no homework for today");
            }else {
                System.out.print("yes homework");
            }
            break;
            default:
                System.out.print("Select a day of the week using a number from [1-7]");
                }


        // 1. Re-implement Exercise07 using a switch statement.
        // Days 6 and 7 represent Saturday and Sunday.
        // If it's the weekend and Abdi has less than 15 hours of homework, he skips homework for the day.
        // For all other days, or if he has more than 15 hours of homework, he does his homework.
        // ---
        // You may choose to track data -- maybe a boolean for homework yes/no -- instead of printing a message in
        // each case. That's a lot of repeated typing.
        // Then print the detailed message after the switch.
    }
}

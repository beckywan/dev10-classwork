import java.util.Scanner;

public class Exercise16 {

    public static void main(String[] args) {
        // 1. Display the following menu and collect an integer choice from the user.
        // (See Exercise14 for a menu example.)
        //
        Scanner console = new Scanner(System.in);

        // Menu
        System.out.println("bunny");// 1. Print the name of an animal.
        System.out.println("Georgia");// 2. Print the name of a state.
        System.out.println("rhinoceros beetle");// 3. Print the name of a beetle.
        System.out.println("quartz");// 4. Print the name of a mineral.
        System.out.println("Select [1-4]");// Select [1-4]:
        int choice = Integer.parseInt(console.nextLine());

        switch (choice) {
            case 1:
                System.out.println("bunny");
                break;
            case 2:
                System.out.println("Georgia");
                break;
            case 3:
                System.out.println("rhinoceros beetle");
                break;
            case 4:
                System.out.println("quartz");
                break;
            default:
                System.out.println("Unknown Menu Option");
                break;
        }
        //
        // 2. Use a switch to cover cases 1-4 as well as a default.
        // For 1-4, print an animal, state, beetle, or mineral respectively.
        // For the default case, print "Unknown Menu Option".

    }
}

import java.util.Scanner;

public class Exercise11 {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("start integer");
        int start = Integer.parseInt(console.nextLine());

        System.out.println("end integer");
        int end = Integer.parseInt(console.nextLine());

        System.out.println("increment");
        int increment = Integer.parseInt(console.nextLine());

        int sum = 0;

        while (start < end){
            sum = sum + start;
            start = start + increment;
        }

        System.out.println(sum);

    }

}

import learn.Vegetables;

import java.util.ArrayList;

public class Exercise14 {
    public static void main(String[] args) {
        ArrayList<Vegetables> types = new ArrayList<>();
        types.add(new Vegetables("tomato"));
        types.add(new Vegetables("cucumber"));

        for (Vegetables type : types) {
            System.out.println(type.getName());
        }
    }
}
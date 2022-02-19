public class ArrayMethods {
//    Name: removeZero
//    valuess: int[]
//    Output: int[]
//    Description: Finds all non-zero elements and copies them to a new array.
//            Don't over allocated.
//    You'll need to count non-zeros first.

    public int[] removeZero(int[] values) {
        int[] result = {};
        if (values == null) {
            return result;
        } else if (values.length == 0) {
            return result;
        }

        int count = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] != 0) {
                count++;
            }
        }
        System.out.println(count);

        if (count == 0) {
            return result;
        }

        result = new int[count];

        int i2 = 0;

        for (int i = 0; i < values.length; i++) {
                if (values[i] != 0) {
                    result[i2] = values[i];
                    i2++;
                }
        }
        return result;

        }

    }
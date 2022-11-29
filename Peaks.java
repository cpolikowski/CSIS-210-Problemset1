
/**
 * Allows the user to enter a set on numbers into an array and based on the parameters given for what
 * a peak is, print out the peaks
 *
 * @author Kade Garrison and Cam Polikowski
 * @version 1.0 (Due Date: 2/23/22)
 */
import java.util.Scanner;
public class Peaks
{
    public static boolean isPeak(int pos, int heights[]) {
        boolean right = true;
        boolean left = true;
        boolean ret = false;

        if (pos == 0) {
            if (heights[pos] > heights[pos + 1]) {
                for (int x = pos + 1; x < heights.length - 1; x++) {
                    if (heights[pos] <= heights[x]) {
                        ret = true;
                        x = heights.length;
                    } else {
                        ret = false;
                    }
                }
            } 
        } else {
            if (heights[pos] >= heights[pos - 1] && heights[pos] >= heights[pos + 1]){
                // checking right of the specific point
                for (int x = pos + 1; x < heights.length - 1; x++) {
                    if (heights[pos] <= heights[x]) {
                        right = false;
                        x = heights.length;
                    } else {
                        right = true;
                    }
                }

                // checking left of specific point
                for (int x = pos; x >= 0; x--) {
                    if (heights[pos] <= heights[x]) {
                        left = false;
                        x = -1;
                    } else {
                        left = true;
                    }
                }
                if (right == true || left == true) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        int[] temp = new int[50];
        int input = 999;
        int[] array;
        while (count < 50 && input != 0) {
            System.out.println("Give a number: ");
            input = scan.nextInt();
            if (input != 0){
                temp[count] = input;
                count++;
            }
        }

        array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = temp[i];
        }

        for (int x = 0; x < array.length; x++) {
            System.out.print(array[x] + " ");
        }

        // find the peaks of the array
        System.out.println();
        System.out.println();
        boolean right = true;
        boolean left = true;
        if (array[0] > array[1]){
            System.out.print(array[0] + " ");
        }
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] >= array[i - 1] && array[i] >= array[i + 1]){

                // checking right of the specific point
                for (int x = i + 1; x < array.length - 1; x++) {
                    if (array[i] <= array[x]) {
                        right = false;
                        x = array.length;
                    } else {
                        right = true;
                    }
                }

                // checking left of specific point
                for (int x = i - 1; x >= 0; x--) {
                    if (array[i] <= array[x]) {
                        left = false;
                        x = -1;
                    } else {
                        left = true;
                    }
                }
                if (right == true || left == true) {
                    System.out.print(array[i] + " ");
                }
            }
        }
        if (array[array.length - 1] > array[array.length - 2]){
            System.out.print(array[array.length - 1] + " ");
        }

        System.out.println();
        System.out.println();
        System.out.println("Enter the element to see if it's a peak: ");
        int pos = scan.nextInt();
        System.out.println(isPeak(pos, array));
    }

}

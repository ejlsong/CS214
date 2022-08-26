//Eric Song
//112294760
//CSE214
//HW2
public class Main {
    //palindrome


    /**
     * Returns whether or not the provided String is a palindrome.
     * The characters of the String are pushed into a stack, and then popped onto an empty String.
     * The popped characters will form the reverse of the original String.
     * Method determines if the String is a palindrome, returns true if it is; returns false if not.
     * @param x the string that will be determined if it is a palindrome or not
     * @return true if the String x is a palindrome, false if the String x is not a palindrome
     */
    public static boolean palindromeChecker(String x){
        Stack stack = new Stack();   //create a new stack

        for(int i = 0; i < x.length(); i++){   //push all of the chars from the string into the stack
            stack.push(x.charAt(i));
        }

        String reverse = "";   //create empty String to be used for the reverse of x

        while(!stack.isEmpty()){   //pop all of the chars from the stack into the reverse String
            reverse = reverse + stack.pop();
        }
        if(x.equals(reverse)){   // return true if x is the same as its reverse
            return true;
        }
        return false;
    }


    /**
     * This method returns the amount of changes needed to change one String to another.
     * If one String is empty the other String's length is returned.
     * Integers for each relation in the Levenshtein algorithm are created.
     * The method returns the minimum of the three integers created.
     * @param x the first String that is being compared
     * @param y the second String that is being compared
     * @return the amount of changes needed to change x to y
     */
    // edit distance
    public static int editDistance(String x, String y) {
        if(x.length() == 0){
            return y.length();
        }
        if(y.length() == 0){
            return x.length();
        }

        int num1 = editDistance(x.substring(1),y)+1;   //first statement of Leveshtein Distance
        int num2 = editDistance(x,y.substring(1))+1;   //second statement of Leveshtein Distance
        int num3 = editDistance(x.substring(1),y.substring(1)) + delta(x.charAt(0),y.charAt(0));   //third statement of Leveshtein Distance

        return Math.min(num1,Math.min(num2,num3));   //return the minimum of the calculated numbers



    }

    /**
     * This method returns an integer, 1 or 0, depending on if the two characters being compared to are the same.
     * If so, the method returns 0.
     * Otherwise, the method returns 1.
     * This is a helper method of the Leveshtein algorithm.
     * @param a the first character being compared
     * @param b the second character being compared
     * @return if a and b are equal
     */

    public static int delta(char a, char b){   //the smaller function included with the Levenshtein algorithm
        if(a == b){   //if the characters are the same, no changes are made
            return 0;
        }
        return 1;
    }


    /**
     * This method returns the amount of changes needed to change one String to another.
     * A 2D array of type int is created to store all of the values.
     * The first row and column is set in ascending order, up to the lengths of the two Strings
     * As the array is being traversed, its values are set as the values the Levenshtein function returns.
     * This is much like the recursive version of the Levenshtein function, except the changes are being stored in an array.
     * The minimum is stored in the very last element of the 2D array and is returned.
     * @param x the first String that is being compared
     * @param y the second String that is being compared
     * @return the amount of changes needed to change x to y
     */
    public static int fastEditDistance(String x, String y){
        int[][] array = new int[x.length()+1][y.length()+1];

        for(int i = 0; i <= x.length();i++){
            for(int j = 0; j <= y.length();j++){
                if(i==0){
                    array[i][j] = j;   //set the entire first row in ascending order
                }
                else if(j == 0 ){
                    array[i][j] = i;   //set the entire first column in ascending order
                }
                else{
                    // set array elements as Levenshtein values
                    // 3 numbers as Levenshtein statements
                    int num1 = array[i-1][j-1]+delta(x.charAt(i-1),y.charAt(j-1));
                    int num2 = array[i-1][j]+1;
                    int num3 = array[i][j-1]+1;

                    array[i][j] = Math.min(num1,Math.min(num2,num3)); //store values as the minimum

                }
            }
        }
        return array[x.length()][y.length()];

    }



    public static void main(String[] args) {


    }

}
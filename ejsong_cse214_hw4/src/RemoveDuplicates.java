//Eric Song
//112294760
//CSE214
//Homework 5
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;

class RemoveDuplicates {

    public static void removeDuplicates(LinkedList<Integer> data) {
        HashMap map = new HashMap();
        ListIterator<Integer> list =  data.listIterator(0);
        int count = 0;
        while(list.hasNext()){
            int x = list.next();
            if(!map.containsValue(x)){
                count++;
                map.put(count,x);
            }
            else{
                list.remove();
            }
        }
    }

    public static void main(String[] args) {
        // EXAMPLE TEST
        LinkedList<Integer> arr = new LinkedList<Integer>(Arrays.asList(6, 8, 3, 5, 7, 32, 6, 8, 3, 2, 3, 6, 1, 32 ));
        removeDuplicates(arr);
        // arr is now {6, 8, 3, 5, 7, 32, 2, 1}
        for (int x : arr)
            System.out.println(x);
    }
}
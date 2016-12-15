package leetcode;

/**
 * Created by hmaduri on 8/10/16.
 */
import java.util.*;
import java.util.Collections;

public class foursumproblem {
    public static void main(String [] args) {
        Integer arr[] = {2,3,4,5,67,4,2,54,6,2,35,3,32,45};

        int target = 54;

        Set<List<Integer>> result = generateFourSum(arr, target);

        for (List<Integer> elems : result) {
            for (Integer i : elems)
                System.out.print(i+",");
            System.out.println();
        }
    }

//    static Set<List<Integer>> generateFourSum(int arr[], int target) {
//        Map<Integer, List<List<Integer>>> dictionary = new HashMap<>();
//
//        for (int i=0;i<arr.length-2;i++)
//            for (int j=0;j<arr.length-1;j++) {
//
//            }
//    }


    static Set<List<Integer>> generateFourSum(Integer arr[], int target) {
        Map<Integer,List<List<Integer>>> dictionary = new HashMap<>();
        Set<List<Integer>> result = new HashSet<>();
//        Arrays.sort(arr, Collections.reverseOrder());
        Arrays.sort(arr);
        for (int i=0; i< arr.length-2;i++) {
            for (int j=i+1; j< arr.length-1;j++) {
                int sum = arr[i] + arr[j];
                if (dictionary.get(target - sum) != null)
                for (List<Integer> elems : dictionary.get(target - sum)) {
                    List<Integer> r = new ArrayList<>();
                    r.add(arr[i]);
                    r.add(arr[j]);
                    r.addAll(elems);
                    result.add(r);
                }
            }
            for (int j = 0; j<i;j++) {
                List<Integer> e = new ArrayList<>();
                e.add(arr[j]); e.add(arr[i]);
                List<List<Integer>> l = new ArrayList<>();
                l.add(e);
                if (dictionary.get(arr[i] + arr[j]) == null)
                    dictionary.put(arr[i]+ arr[j], l);
                else dictionary.get(arr[i]+arr[j]).add(e);
            }
        }
        return result;
    }
}

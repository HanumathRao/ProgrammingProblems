package leetcode;

/**
 * Created by hmaduri on 8/10/16.
 */

import java.util.List;
import java.util.Set;
import java.util.*;

public class ksumproblem {
    public static void main(String [] args) {
        Integer arr[] = {2,3,4,5,67,4,2,54,6,2,35,3,32,45};

        int target = 54;
        Arrays.sort(arr);
        Set<List<Integer>> result = ksums(arr, target, 5, 0, new ArrayList<>(), new HashSet<>());
        for (List<Integer> elems : result) {
            for (Integer i : elems)
                System.out.print(i+",");
            System.out.println();
        }
    }

    static Set<List<Integer>> ksums (Integer [] arr, int target, int k, int start,
                                     List<Integer> integers, Set<List<Integer>> result) {

        if (k==1) {
            for (int i = start; i < arr.length;i++)
                if ( arr[i] == target) {
                    integers.add(arr[i]);
                    List<Integer> allvalidints = new ArrayList<>();
                    allvalidints.addAll(integers);
                    integers.remove(integers.size()-1);
                    result.add(allvalidints);
                }

            return result;
        }

        for (int i=start; i<arr.length-k; i++) {
            integers.add(arr[i]);
            ksums(arr,target-arr[i],k-1,i+1,integers,result);
            integers.remove(integers.size()-1);
        }
        return result;
    }
}

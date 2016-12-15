package leetcode;

/**
 * Created by hmaduri on 8/9/16.
 */

/**********************************************************************************
 *
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 **********************************************************************************/

public class medianofsortedarrays {

    public static void main(String[] args) {
//        int a[] = {2,5,7,9,12,15};
//        int b[] = {3,4,6,8,10,11,13};  /// 2,3,4,5,6,7,8,9,10,11,12,13,15
//        System.out.println("median number of two arrays:" + medianoftwoarrays(a,b,a.length, b.length, 0,a.length,0,b.length));

        int a[] = {1, 12, 15, 26, 38};
        int b[] = {2, 13, 17, 30, 45, 50}; /// 1,2,12,13,15,17,26,30,38,45,50

        System.out.println("median number of two arrays:" + medianoftwoarrays(a,b,a.length, b.length, 0,a.length,0,b.length));
    }


    static int medianoftwoarrays(int [] a, int []b, int alength,int blength, int lowA, int highA, int lowB, int highB) {
        //always caller sends in a way that big array is at the beginning and small array is at the end.

        int mid = lowA + (highA - lowA)/2;
        int pos = binarySearch(a[mid], b, lowB, highB);

        if (mid + pos == (alength+blength)/2) {
            if ((alength + blength) %2 == 1)
                return a[mid];

            int nextelem;

            if (mid >0 && pos > 0)
                nextelem = a[mid-1] > b[pos-1] ? a[mid-1] : b[pos-1];
            else if (pos>0)
                nextelem = b[pos-1];
            else
                nextelem = a[mid-1];
            return (a[mid] + nextelem)/2;
        }

        if (mid + pos > (alength+blength)/2) {
            highA = mid - 1;
            highB = pos - 1;

            if ( highA - lowA > highB - lowB)
                return medianoftwoarrays(a,b,alength,blength,lowA,highA,lowB,highB);

            return medianoftwoarrays(b,a,blength,alength,lowB,highB,lowA,highA);
        } else {
            lowA = mid + 1;
            lowB = pos;
            if (highA - lowA > highB - lowB)
                return medianoftwoarrays(a,b,alength,blength,lowA,highA,lowB,highB);

            return medianoftwoarrays(b,a,blength,alength,lowB,highB,lowA,highA);
        }
    }

    static int binarySearch(int elem, int[] arr, int start, int end) {
        assert(start<=end);
        while(true) {
            int mid = start + (end - start) / 2;

            if (start >= end)
                return start;

            if (arr[mid] == elem) return mid;
            else if (arr[mid] < elem) start = mid + 1;
            else if (arr[mid] > elem) end = mid - 1;
        }
    }
}

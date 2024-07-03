package justCode;

import java.util.Arrays;

public class LeftRotateArraybyDPlaces {

    public static int[] reverse(int arr[], int start, int end){

        while(start<=end){
            int temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }

        return arr;
    }

    public static void leftrotate(int arr[], int d){
        int n=arr.length;
        if(d==0) return;
        d=d%n;
        reverse(arr,0,d-1);
        reverse(arr,d,n-1);
        reverse(arr,0,n-1);
    }




    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int n = arr.length;
        int d = 3;
        d=d%n;
        if(d==0) return;
        System.out.println(n);
        int temp[]=new int[d];

        //store first d elements in temp array
        for(int i=0;i<d;i++){
            temp[i]=arr[i];
        }

        //now move all the elements from d index till n to d places right
        for(int i=d;i<n;i++){
            arr[i-d]=arr[i];
        }

        for(int i=n-d;i<n;i++){
            arr[i]=temp[i-(n-d)];
        }
        System.out.println(Arrays.toString(arr));
        int ar[]={1, 2, 3, 4, 5, 6, 7};
        leftrotate(ar,4);
        System.out.println("Optimized approach");
        System.out.println(Arrays.toString(ar));
    }
}

package justCode;

import java.util.Arrays;

public class RightRotateArray {


    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6};
        int temp=arr[arr.length-1];
        System.out.println(temp);
        //shift elements to right
        for(int i=arr.length-1;i>0;i--){
            arr[i]=arr[i-1];
        }
        arr[0]=temp;
        System.out.println(Arrays.toString(arr));
    }
}

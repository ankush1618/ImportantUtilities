package justCode;

import java.util.ArrayList;
import java.util.Arrays;

public class SubArraysSum {


    public static ArrayList<Integer> longestSubArray(int arr[],int k){
        int sum=0;
        ArrayList<Integer> output=new ArrayList<>();
        int start=0;
        int end=arr.length-1;
        int tmpSum=0;
        for(int i=start;i<end;i++){
            tmpSum=tmpSum+arr[i];
            while(tmpSum>k){
                tmpSum=tmpSum-arr[start];
                start++;
            }

            if(tmpSum==k && start<=i){
                output.add(start+1);
                output.add(i+1);
                return output;
            }
            output.add(-1);
        }
        return output;

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5,2,2,2,1};
        int sum=7;
        int maxLen=0;
        for(int i=0;i<arr.length;i++){

            for(int j=i;j<arr.length;j++){
                int tmpsum=0;
                for(int k=i;k<=j;k++) {
                    tmpsum = tmpsum + arr[k];
                }
                if(tmpsum==sum){
                    maxLen=Math.max(maxLen,j-i+1);
                }
            }
        }
        System.out.println(maxLen);

        ArrayList<Integer> subArray=longestSubArray(arr,9);
        System.out.println(subArray);
    }
}

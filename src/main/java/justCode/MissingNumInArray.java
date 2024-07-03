package justCode;

import java.util.Arrays;

public class MissingNumInArray {

    public static int bettermissingNum(int arr[]){

        int n=arr.length;
        int hash[]=new int[n+1];

        for(int i=0;i<n;i++){
               hash[arr[i]]++;
        }
        System.out.println(Arrays.toString(hash));
        for(int i=1;i<n;i++){
            //for unique if hash[i]==1
            //if not present hash[i]==0
            if(hash[i]==0){
                return i;
            }
        }

        return -1;
    }

    public static int betterMissingNum(int num){


        return -1;
    }

    public static void main(String[] args) {
        int arr[]={0,1,1,3,3,4,4,5,5,6,6,7,7,8,9,9,9,10,10};
        int num=bettermissingNum(arr);
        System.out.println(num);
    }
}

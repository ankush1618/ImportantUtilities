package justCode;

import java.util.Arrays;

public class RightRotatebyDPlaces {


    public static void main(String[] args) {

        int ar[]={1, 2, 3, 4, 5, 6, 7};
        int n=ar.length;

        int d=4;
        d=d%n;
        //sif(n==0) return;

        int temp[]=new int[d];

        for(int i=n-1;i>=d;i--){
            temp[i-(n-d)]=ar[i];
        }
        System.out.println(Arrays.toString(temp));

    }
}

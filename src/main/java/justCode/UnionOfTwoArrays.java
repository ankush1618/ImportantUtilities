package justCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnionOfTwoArrays {
     static int a1[]={1,1,2,3,4,5};
    static int a2[]={2,2,4,4,5,6};
    public static void main(String[] args) {
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<a1.length;i++){
            set.add(a1[i]);

        }
        for(int i=0;i<a2.length;i++){
            set.add(a2[i]);
        }

        int temp[]=new int[set.size()];
        int j=0;
        for(int s:set){
            temp[j++]=s;
        }

        System.out.println(Arrays.toString(temp));
    }

}

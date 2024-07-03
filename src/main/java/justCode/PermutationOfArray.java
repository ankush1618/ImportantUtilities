package justCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationOfArray {


    public static void permute(int index,int nums[],List<List<Integer>> ans){

        if(index==nums.length){
            //copy data structure to the ans
            List<Integer> ds=new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i=index;i<nums.length;i++){
            swap(i,index,nums);
            permute(index+1,nums,ans);
            swap(i,index,nums);
        }
    }

    public static void swap(int i,int j,int []nums){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    public static List<List<Integer>> permuteArray(int nums[]){
        List<List<Integer>> ans=new ArrayList<>();
        permute(0,nums,ans);
        return ans;
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3};
        int res=312;
        List<List<Integer>> ans=permuteArray(nums);

        System.out.println("All Permutations are");
        for (int i = 0; i < ans.size(); i++) {
            for(int j=0;j<ans.get(i).size();j++) {

                    System.out.print(ans.get(i).get(j)+" ");

            }
            System.out.println();
        }

    }
}

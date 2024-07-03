package components;

import java.util.*;

public class SortValuesComparater {

    public static void main(String[] args) {
// Given input map
        Map<Integer, String> input = new LinkedHashMap<>();
        input.put(100, "java");
        input.put(101, "hello");
        input.put(102, "apac");
        input.put(105, "emea");

        List<Map.Entry<Integer,String>> entries=new ArrayList<>(input.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, String>>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //Build the output Map using LinkedHashMap for maintaining the Insertion Order
        Map<Integer,String> output=new LinkedHashMap<>();
        for(Map.Entry<Integer,String> entry: entries){
            output.put(entry.getKey(),entry.getValue());
        }
        // Print the output map
        System.out.println(output);
    }
}

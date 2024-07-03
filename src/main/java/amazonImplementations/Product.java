package amazonImplementations;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private int price;

    public Product(String name, Integer price) {
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int prize) {
        this.price = prize;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getListofProducts(){
        List<String> list=new ArrayList<>();
        list.add("Apple");
        list.add("Apple Mackbook pro");
        return list;
    }
}

package amazonImplementations;

public class Search {

    public String searchProduct(Product product){
        if(product.getListofProducts().contains(product.getName())){
            System.out.println("The Product is Available");
        }
        return product.getName();
    }
}

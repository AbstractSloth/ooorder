package be.sloth.ooorder.domain.repository;

import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemRepository {

    private final Map<String, Product> catalogue;
    private final Map<String, Item> stock;

    public ItemRepository() {
        catalogue = new HashMap<>();
        stock = new HashMap<>();
    }


    public void registerProduct(Product product){
        catalogue.put(product.getId(),product);
    }

    public void addStock(Item item){
        stock.put(item.getId(),item);
    }

    public boolean doesProductExist(String id){
        return catalogue.containsKey(id);
    }


}

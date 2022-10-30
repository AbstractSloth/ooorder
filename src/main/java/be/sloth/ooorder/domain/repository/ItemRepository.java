package be.sloth.ooorder.domain.repository;

import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.ItemStatus;
import be.sloth.ooorder.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

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

    public Collection<Product> getCatalogue(){
        return catalogue.values();
    }

    public void addStock(Item item){
        stock.put(item.getId(),item);
    }

    public Product getProductById(String id){
        return catalogue.get(id);
    }

    public boolean doesProductExist(String id){
        return catalogue.containsKey(id);
    }

    public int getAmountInStock(String productId){
        return stock.values().stream()
                .filter(item -> item.getStatus().equals(AVAILABLE))
                .filter(item -> item.getProduct().equals(productId))
                .toList().size();
    }

    public Item getFirstInStock(String productId){
        return stock.values().stream()
                .filter(item -> item.getStatus().equals(AVAILABLE))
                .filter(item -> item.getProduct().equals(productId))
                .findFirst().orElseThrow();
    }

    public Product getItemProduct(Item item){
        return catalogue.get(item.getProduct());
    }

    public Product getItemProduct(String itemId){
        return getItemProduct(stock.get(itemId));
    }


}

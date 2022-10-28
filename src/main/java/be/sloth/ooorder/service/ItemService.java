package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.RegisterProductDTO;
import be.sloth.ooorder.api.mapper.ProductMapper;
import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import static be.sloth.ooorder.domain.customer.Privilege.*;

@Service
public class ItemService {

    private final ItemRepository itemRepo;
    private final ProductMapper mappo;
    private final SecurityService security;

    private final ValidationService validation;


    public ItemService(ItemRepository itemRepo, ProductMapper mappo, SecurityService security, ValidationService validation) {
        this.itemRepo = itemRepo;
        this.mappo = mappo;
        this.security = security;
        this.validation = validation;
    }


    public void RegisterProduct(RegisterProductDTO toBeAdded, String auths){
        System.out.println("it gets to service...");
        security.validateAuthorization(auths, ADMIN);
        System.out.println("it gets past security..");
        validation.validateNewProduct(toBeAdded);
        Product product = mappo.createProduct(toBeAdded);
        itemRepo.registerProduct(product);
        ReplenishStock(toBeAdded.getStock(), product.getId());
    }

    public void ReplenishStock(Integer amount, String productId){
        for (int i = 0; i < amount; i++) {
            StockItem(productId);
        }
    }

    public void StockItem(String productId){
        itemRepo.addStock(new Item(productId));
    }


}

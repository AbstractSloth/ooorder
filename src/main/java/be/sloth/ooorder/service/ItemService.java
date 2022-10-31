package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.ProductDTO;
import be.sloth.ooorder.api.dto.RegisterProductDTO;
import be.sloth.ooorder.api.mapper.ProductMapper;
import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        registerDummyProduct();
    }


    public void registerProduct(RegisterProductDTO toBeAdded, String auths) {
        security.validateAuthorization(auths, ADMIN);
        validation.validateNewProduct(toBeAdded);
        Product product = mappo.createProduct(toBeAdded);
        itemRepo.registerProduct(product);
        replenishStock(toBeAdded.getStock(), product.getId());
    }

    public void replenishStock(Integer amount, String productId) {
        if (!itemRepo.doesProductExist(productId)) throw new IllegalArgumentException("no such product!");
        for (int i = 0; i < amount; i++) {
            stockItem(productId);
        }
    }

    public void stockItem(String productId) {
        itemRepo.addStock(new Item(productId));
    }

    public List<ProductDTO> showCatalogue() {
        return itemRepo.getCatalogue().stream()
                .map(mappo::mapProduct)
                .toList();
    }

    private void registerDummyProduct() {
        Product product1 = new Product("playstation8", "It has no games.", new BigDecimal("420.00"));
        Product product2 = new Product("boxofbits", "Has all the bits you need.", new BigDecimal("20.00"));
        itemRepo.registerProduct(product1);
        replenishStock(10, product1.getId());
        itemRepo.registerProduct(product2);
        replenishStock(5, product2.getId());
    }


}

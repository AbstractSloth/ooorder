package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.ProductDTO;
import be.sloth.ooorder.api.dto.RegisterProductDTO;
import be.sloth.ooorder.api.mapper.ProductMapper;
import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.ItemStatus;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import be.sloth.ooorder.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static be.sloth.ooorder.domain.customer.Privilege.*;

@Service
@Transactional
public class ItemService {

    private final ProductRepository productRepo;

    private final ItemRepository itemRepo;
    private final ProductMapper mappo;
    private final SecurityService security;

    private final ValidationService validation;


    public ItemService(ProductRepository productRepo, ItemRepository itemRepo, ProductMapper mappo, SecurityService security, ValidationService validation) {
        this.productRepo = productRepo;
        this.itemRepo = itemRepo;
        this.mappo = mappo;
        this.security = security;
        this.validation = validation;
    }


    public void registerProduct(RegisterProductDTO toBeAdded, String auths) {
        security.validateAuthorization(auths, ADMIN);
        validation.validateNewProduct(toBeAdded);
        Product product = mappo.createProduct(toBeAdded);
        productRepo.save(product);
        replenishStock(toBeAdded.getStock(), product);
    }

    public void replenishStock(Integer amount, Product product) {
        for (int i = 0; i < amount; i++) {
            stockItem(product);
        }
    }

    public void stockItem(Product product) {
        Item item;
        if(itemRepo.countItemsByProductAndStatus(product, ItemStatus.RESERVED)>0){
            item = itemRepo.findFirstByProductAndStatus(product,ItemStatus.RESERVED);
            item.setStatus(ItemStatus.SOLD);
        } else item = new Item(product);


        itemRepo.save(item);
    }

    public List<ProductDTO> showCatalogue() {
        return productRepo.findAll().stream()
                .map(mappo::mapProduct)
                .toList();
    }


    public void registerDummyProduct() {
        Product product1 = new Product("playstation8", "It has no games.", new BigDecimal("420.00"));
        Product product2 = new Product("boxofbits", "Has all the bits you need.", new BigDecimal("20.00"));
        productRepo.save(product1);
        replenishStock(10, product1);
        productRepo.save(product2);
        replenishStock(5, product2);
    }




}

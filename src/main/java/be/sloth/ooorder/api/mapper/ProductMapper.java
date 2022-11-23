package be.sloth.ooorder.api.mapper;

import be.sloth.ooorder.api.dto.ProductDTO;
import be.sloth.ooorder.api.dto.RegisterProductDTO;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

@Component
public class ProductMapper {


    private final ItemRepository itemRepo;

    public ProductMapper( ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }


    public Product createProduct(RegisterProductDTO dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPriceInEuro());
    }

    public ProductDTO mapProduct(Product product) {
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getDescription(),
                mapProductPrice(product.getPriceInEuro()),
                mapStockAmount(product));
    }

    private String mapProductPrice(BigDecimal price) {
        return price.toString() + " €";
    }

    private String mapStockAmount(Product product) {
        int amount = itemRepo.countItemsByProductAndStatus(product, AVAILABLE);
        if (amount > 0) return "There are " + amount + " in stock.";

        return "This item is not in stock!";
    }
}

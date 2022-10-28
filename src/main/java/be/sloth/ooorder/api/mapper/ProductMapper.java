package be.sloth.ooorder.api.mapper;

import be.sloth.ooorder.api.dto.RegisterProductDTO;
import be.sloth.ooorder.domain.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product createProduct(RegisterProductDTO dto){
        return new Product(dto.getName(), dto.getDescription(), dto.getPriceInEuro());
    }
}

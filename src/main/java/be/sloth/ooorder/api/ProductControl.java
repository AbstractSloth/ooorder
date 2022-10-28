package be.sloth.ooorder.api;

import be.sloth.ooorder.api.dto.RegisterProductDTO;
import be.sloth.ooorder.service.ItemService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "catalogue")
@CrossOrigin
public class ProductControl {

    private final ItemService itemService;


    public ProductControl(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void registerProduct(@RequestHeader String authorization, @RequestBody RegisterProductDTO dto ){
        itemService.RegisterProduct(dto,authorization);
    }
}

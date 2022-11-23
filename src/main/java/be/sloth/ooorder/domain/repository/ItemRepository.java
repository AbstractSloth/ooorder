package be.sloth.ooorder.domain.repository;


import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.ItemStatus;
import be.sloth.ooorder.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findFirstByProductAndStatus(Product product, ItemStatus status);

    int countItemsByProductAndStatus(Product product, ItemStatus status);
}

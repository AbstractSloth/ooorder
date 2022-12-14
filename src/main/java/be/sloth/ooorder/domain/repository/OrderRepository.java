package be.sloth.ooorder.domain.repository;

import be.sloth.ooorder.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}

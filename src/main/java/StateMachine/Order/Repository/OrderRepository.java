package StateMachine.Order.Repository;

import StateMachine.Order.Entities.Order;
import StateMachine.User.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedBy(User user);

    Object findByRestaurant(User user);

    Object findByRider(User user);
}

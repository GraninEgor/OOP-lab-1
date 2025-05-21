import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order extends Component{
    UUID id = UUID.randomUUID();
    int price;
    public LocalDateTime time;
    ArrayList<Pizza> pizzas = new ArrayList<>();

    public Order(String name, int price) {
        super(name, price);
        this.time = LocalDateTime.now();
    }
}

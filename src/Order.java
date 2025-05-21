import java.util.ArrayList;
import java.util.UUID;

public class Order extends Component{
    UUID id = UUID.randomUUID();
    int price;
    ArrayList<Pizza> pizzas = new ArrayList<>();

    public Order(String name, int price) {
        super(name, price);
    }
}

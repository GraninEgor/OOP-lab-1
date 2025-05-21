import java.util.ArrayList;
import java.util.UUID;

public class Order {
    UUID id = UUID.randomUUID();
    int price;
    ArrayList<Pizza> pizzas = new ArrayList<>();

}

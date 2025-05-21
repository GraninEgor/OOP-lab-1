import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order extends Component{
    UUID id = UUID.randomUUID();
    int price;
    public LocalDate date;
    public LocalTime time;
    ArrayList<Pizza> pizzas = new ArrayList<>();

    public Order(String name, int price) {
        super(name, price);
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public void setDate(LocalDate newDate) {
        if (newDate != null) {
            this.date = newDate;
        } else {
            System.out.println("Ошибка: передана пустая дата.");
        }
    }

    public void setTime(LocalTime newTime) {
        if (newTime != null) {
            this.time = newTime;
        } else {
            System.out.println("Ошибка: передано пустое время.");
        }
    }
}
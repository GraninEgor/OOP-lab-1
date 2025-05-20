import java.util.ArrayList;

public class Pizza extends Component{

    Base base;
    ArrayList<Ingredient> ingredients;
    Side side;


    public Pizza(String name, int price, Base base, ArrayList<Ingredient> ingredients,Side side) {
        super(name, price);
        this.base = base;
        this.ingredients = ingredients;
        this.side = side;
    }
}

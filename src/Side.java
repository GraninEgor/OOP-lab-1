import java.util.ArrayList;

public class Side extends Component {

    ArrayList<Pizza> notAllowedPizzas;
    ArrayList<Ingredient> ingredients;

    public Side(String name, int price, ArrayList<Ingredient> ingredients) {
        super(name, price);
        this.ingredients = ingredients;
    }
}

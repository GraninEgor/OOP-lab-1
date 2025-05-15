import java.util.ArrayList;

public class Pizza extends Component{

    Base base;
    ArrayList<Ingredient> ingredients;


    public Pizza(String name, int price, Base base, ArrayList<Ingredient> ingredients) {
        super(name, price);
        this.base = base;
        this.ingredients = ingredients;
    }
}

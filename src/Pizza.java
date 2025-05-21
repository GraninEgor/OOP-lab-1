import java.util.ArrayList;

public class Pizza extends Component {
    Base base;
    ArrayList<Ingredient> ingredients;
    Side side;

    Pizza halfA;
    Pizza halfB;
    Side halfASide;
    Side halfBSide;


    public Pizza(String name, int price, Base base, ArrayList<Ingredient> ingredients, Side side) {
        super(name, price);
        this.base = base;
        this.ingredients = ingredients;
        this.side = side;
    }

    public Pizza(String name, int price, Base base, ArrayList<Ingredient> ingredients, Pizza halfA, Pizza halfB, Side halfASide, Side halfBSide) {
        super(name, price);
        this.base = base;
        this.ingredients = ingredients;
        this.halfA = halfA;
        this.halfB = halfB;
        this.halfASide = halfASide;
        this.halfBSide = halfBSide;
    }


    public void doubleIngredients() {
        ingredients.addAll(new ArrayList<>(ingredients));
    }
}
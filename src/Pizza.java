import java.util.ArrayList;

public class Pizza extends Component {

    Base base;
    ArrayList<Ingredient> ingredients;
    Side side;

    Pizza halfA;
    Pizza halfB;
    Side halfASide;
    Side halfBSide;

    private Size size; // ← новое поле

    public Pizza(String name, int price, Base base, ArrayList<Ingredient> ingredients, Side side) {
        super(name, price);
        this.base = base;
        this.ingredients = ingredients;
        this.side = side;
        this.size = Size.MEDIUM;
        this.setPrice(calculatePrice());
    }

    public Pizza(String name, int price, Base base, ArrayList<Ingredient> ingredients, Pizza halfA, Pizza halfB, Side halfASide, Side halfBSide) {
        super(name, price);
        this.base = base;
        this.ingredients = ingredients;
        this.halfA = halfA;
        this.halfB = halfB;
        this.halfASide = halfASide;
        this.halfBSide = halfBSide;
        this.size = Size.MEDIUM;
        this.setPrice(calculatePrice());
    }

    public void setSize(Size size) {
        this.size = size;
        this.setPrice(calculatePrice());
    }

    public Size getSize() {
        return size;
    }

    public int calculatePrice() {
        int basePrice = base != null ? base.getPrice() : 0;
        int ingredientsPrice = 0;
        for (Ingredient ingredient : ingredients) {
            ingredientsPrice += ingredient.getPrice();
        }
        int sidePrice = side != null ? side.getPrice() : 0;

        int totalBasePrice = basePrice + ingredientsPrice + sidePrice;

        return Math.round(totalBasePrice * size.getPriceMultiplier());
    }

    public void doubleIngredients() {
        ingredients.addAll(new ArrayList<>(ingredients));
        this.setPrice(calculatePrice());
    }
}
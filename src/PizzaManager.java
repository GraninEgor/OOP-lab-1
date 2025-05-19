import java.util.ArrayList;
import java.util.Scanner;

public class PizzaManager extends Manager<Pizza> implements ManagerFunctionality<Pizza> {

    private final ManagerFunctionality<Base> baseManager;
    private final ManagerFunctionality<Ingredient> ingredientManager;

    public PizzaManager(
            ManagerFunctionality<Base> baseManager,
            ManagerFunctionality<Ingredient> ingredientManager
    ) {
        this.baseManager = baseManager;
        this.ingredientManager = ingredientManager;
    }

    @Override
    public void create() {
        String pizzaName = getNewPizzaName();
        if (pizzaName == null) return;

        Base selectedBase = selectBaseFromUser();
        if (selectedBase == null) return;

        ArrayList<Ingredient> ingredients = selectIngredientsFromUser();
        if (ingredients == null) return;

        storage.add(new Pizza(pizzaName, 0, selectedBase, ingredients));
        System.out.println("Пицца успешно создана!");
    }

    private String getNewPizzaName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название пиццы");
        return scanner.nextLine();
    }

    private Base selectBaseFromUser() {
        Scanner scanner = new Scanner(System.in);
        Base selectedBase = null;
        boolean baseDialog = true;

        while (baseDialog) {
            System.out.println("Выберите основу\nЧтобы вернуться нажмите -1");
            baseManager.print();

            int selectedBaseIndex = scanner.nextInt();
            if (selectedBaseIndex >= 0 && selectedBaseIndex < baseManager.storageSize()) {
                selectedBase = baseManager.storageGet(selectedBaseIndex);
                baseDialog = false;
            } else if (selectedBaseIndex == -1) {
                return null;
            } else {
                System.out.println("Некорректный ввод");
            }
        }

        return selectedBase;
    }

    private ArrayList<Ingredient> selectIngredientsFromUser() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Ingredient> pizzaIngredients = new ArrayList<>();
        boolean ingredientDialog = true;

        while (ingredientDialog) {
            System.out.println("Выберите ингредиенты:\nДля подтверждения введите -1");
            ingredientManager.print();

            int selectedIngredientIndex = scanner.nextInt();
            if (selectedIngredientIndex >= 0 && selectedIngredientIndex < ingredientManager.storageSize()) {
                pizzaIngredients.add(ingredientManager.storageGet(selectedIngredientIndex));
            } else if (selectedIngredientIndex == -1) {
                ingredientDialog = false;
            } else {
                System.out.println("Некорректный ввод");
            }
        }

        return pizzaIngredients;
    }

    @Override
    public void update(){

    }


}

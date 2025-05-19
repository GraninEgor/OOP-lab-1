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
    public void create(){
        ArrayList<Ingredient> pizzaIngredients = new ArrayList<>();
        Base pizzaBase;
        Ingredient pizzaIngredient;
        boolean baseDialog = true;
        boolean ingredientDialog = true;
        int selectedBase;
        int selectedIngredient;
        String pizzaName;
        System.out.println("Введите название пиццы");
        pizzaName = scanner.nextLine();
        while (baseDialog){
            System.out.println("Выберите основу\nЧтобы вернутся нажмите -1");
            baseManager.print();
            selectedBase = scanner.nextInt();
            if(selectedBase>=0 && selectedBase<baseManager.storageSize()){
                pizzaBase = baseManager.storageGet(selectedBase);
                while (ingredientDialog){
                    System.out.println("Выбери ингредиенты:\nДля подтвердения введите -1");
                    ingredientManager.print();
                    selectedIngredient = scanner.nextInt();
                    if(selectedIngredient>=0 && selectedIngredient<ingredientManager.storageSize()){
                        pizzaIngredients.add(ingredientManager.storageGet(selectedIngredient));
                    }
                    else if(selectedIngredient == -1){
                        storage.add(new Pizza(pizzaName,0,pizzaBase,pizzaIngredients));
                        ingredientDialog = false;
                        baseDialog = false;
                    }
                    else{
                        System.out.println("Некорректный ввод");
                    }
                }
            }
            else if(selectedBase == -1){
                baseDialog = false;
            }
            else{
                System.out.println("Некорректный ввод");
            }
        }
    }
    @Override
    public void update(){

    }


}

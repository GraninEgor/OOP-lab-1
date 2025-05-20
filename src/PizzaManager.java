import java.util.ArrayList;
import java.util.Scanner;

public class PizzaManager extends Manager<Pizza> {

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
        countPrice();
    }

    private String getNewPizzaName() {
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
        int selectedPizza = selectPizza();
        if(selectedPizza == -1){
            return;
        }
        int selectedAction = selectAction();
        if(selectedAction == -1){
            return;
        }
        if(selectedAction == 1){
            changePizzaName(selectedPizza);
        }
        else if(selectedAction == 2){
            changePizzaBase(selectedPizza);
        }
        else if(selectedAction == 3){
            changePizzaIngredients(selectedPizza);
        }
        countPrice();
    }

    private int selectPizza(){
        int selectedPizza;
        System.out.println("Выберите пиццу\nЧтобы вернутся введите -1");
        this.print();
        selectedPizza = scanner.nextInt();
        if(selectedPizza>=0 && selectedPizza<storage.size()){
            return selectedPizza;
        }
        else if(selectedPizza == -1){
            return -1;
        }
        else{
            System.out.println("Некорректный ввод");
            return -1;
        }
    }

    private int selectAction(){
        int selectedAction;
        System.out.println("Изменить название - 1\nИзменить основу - 2\nИзменить ингедиенты - 3\nЧтобы вернутся введите -1");
        selectedAction = scanner.nextInt();
        if(selectedAction != 1 && selectedAction != 2 &&selectedAction != 3){
            return -1;
        }
        else return selectedAction;
    }

    private void changePizzaName(int pizzaId){
        String newName;
        System.out.println("Введите новое название:");
        scanner.nextLine();
        newName = scanner.nextLine();
        storage.get(pizzaId).setName(newName);
    }

    private void changePizzaBase(int pizzaId){
        int selectedBase;
        System.out.println("Выберите новую основу");
        baseManager.print();
        selectedBase = scanner.nextInt();
        if(selectedBase>=0 && selectedBase<baseManager.storageSize()){
            storage.get(pizzaId).base = baseManager.storageGet(selectedBase);
        }
        else{
            System.out.println("Некорректный ввод");
        }
    }

    private void changePizzaIngredients(int pizzaId){
        boolean dialogState = true;
        int command;
        while (dialogState){
            System.out.println("Добавить - 1\nУдалить - 2\nДля выода введите -1");
            command = scanner.nextInt();
            if(command == 1){
                addIngredientToPizza(pizzaId);
            }
            else if(command == 2){
                deletePizzaFromPizza(pizzaId);
            }
            else if(command == -1){
                dialogState = false;
            }
            else{
                System.out.println("Некорректный ввод");
            }
        }
    }

    private void addIngredientToPizza(int pizzaId){
        int selectedIngredient;
        boolean dialogState = true;
        while (dialogState){
            System.out.println("Введи номер ингредиента\nДля выхода введите -1");
            ingredientManager.print();
            selectedIngredient = scanner.nextInt();
            if(selectedIngredient>=0 && selectedIngredient<ingredientManager.storageSize()){
                storage.get(pizzaId).ingredients.add(ingredientManager.storageGet(selectedIngredient));
            }
            else{
                dialogState = false;
            }
        }
    }

    private void deletePizzaFromPizza(int pizzaId){
        int selectedIngredient;
        boolean dialogState = true;
        while (dialogState){
            System.out.println("Введи номер ингредиента\nДля выхода введите -1");
            ingredientManager.print();
            selectedIngredient = scanner.nextInt();
            if(selectedIngredient>=0 && selectedIngredient<ingredientManager.storageSize()){
                storage.get(pizzaId).ingredients.remove(selectedIngredient);
            }
            else{
                dialogState = false;
            }
        }
    }

    public void countPrice(){
        int price;
        for(int i = 0;i<storage.size();i++){
            price = 0;
            for(Ingredient ingredient: storage.get(i).ingredients){
                price += ingredient.getPrice();
            }
            price += storage.get(i).base.getPrice();
            storage.get(i).setPrice(price);
        }
    }
}

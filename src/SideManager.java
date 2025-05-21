import java.util.ArrayList;
import java.util.Scanner;

public class SideManager extends Manager<Side>{

    IngredientManager ingredientManager;
    PizzaManager pizzaManager;

    @Override
    public void create(){
        Side createdSide;
        String name;
        System.out.println("Введи название бортика");
        name = scanner.nextLine();
        ArrayList<Ingredient> ingredients = selectIngredientsFromUser();
        if (ingredients == null) return;
        createdSide = new Side(name,0,ingredients);
        setAllowed(createdSide.notAllowedPizzas);
        storage.add(createdSide);
        countPrice();
    }

    @Override
    public void update(){
        int selectedSide = selectSide();
        if(selectedSide == -1){
            return;
        }
        int selectedAction = selectAction();
        if(selectedAction == -1){
            return;
        }
        if(selectedAction == 1){
            changeSideName(selectedSide);
        }
        else if(selectedAction == 2){
            changeSideIngredients(selectedSide);
        }
        else if(selectedAction == 3){
            setAllowed(storage.get(selectedSide).notAllowedPizzas);
        }
        countPrice();
    }


    private ArrayList<Ingredient> selectIngredientsFromUser() {
        ArrayList<Ingredient> borderIngredients = new ArrayList<>();
        boolean ingredientDialog = true;

        while (ingredientDialog) {
            System.out.println("Выберите ингредиенты:\nДля подтверждения введите -1");
            ingredientManager.print();

            int selectedIngredientIndex = scanner.nextInt();
            scanner.nextLine();
            if (selectedIngredientIndex >= 0 && selectedIngredientIndex < ingredientManager.storageSize()) {
                borderIngredients.add(ingredientManager.storageGet(selectedIngredientIndex));
            } else if (selectedIngredientIndex == -1) {
                ingredientDialog = false;
            } else {
                System.out.println("Некорректный ввод");
            }
        }

        return borderIngredients;
    }

    public SideManager(
            Manager<Ingredient> ingredientManager,
            Manager<Pizza> pizzaManager
    ){
        this.ingredientManager = (IngredientManager) ingredientManager;
        this.pizzaManager = (PizzaManager) pizzaManager;
    }

    public void countPrice(){
        int price;
        for(int i = 0;i<storage.size();i++){
            price = 0;
            for(Ingredient ingredient: storage.get(i).ingredients){
                price += ingredient.getPrice();
            }
            storage.get(i).setPrice(price);
        }
    }

    private void setAllowed(ArrayList<Pizza> notAllowedPizzas){
        boolean dialogState = true;
        int command;
        int selectedPizza;
        while (dialogState){
            System.out.println("Добавить запрещенные пиццы - 1\nУдалить запрещенные пиццы - 2\nЧтобы выйти нажми -1");
            command = scanner.nextInt();
            scanner.nextLine();
            if(command == 1){
                System.out.println("Выбери пиццу:\nЧтобы выйти нажми -1");
                pizzaManager.print();
                selectedPizza = scanner.nextInt();
                scanner.nextLine();
                if(selectedPizza>=0 && selectedPizza<pizzaManager.storageSize()){

                    if(notAllowedPizzas.contains(pizzaManager.storageGet(selectedPizza))){
                        System.out.println("Пицца уже добавлена");
                    }
                    else {
                        notAllowedPizzas.add(pizzaManager.storageGet(selectedPizza));
                    }
                }
                else{
                    dialogState = false;
                }
            }
            else if (command == 2){
                System.out.println("Выбери пиццу:\nЧтобы выйти нажми -1");
                pizzaManager.print();
                selectedPizza = scanner.nextInt();
                scanner.nextLine();
                if(selectedPizza>=0 && selectedPizza<pizzaManager.storageSize()){
                    if(!notAllowedPizzas.contains(pizzaManager.storageGet(selectedPizza))){
                        System.out.println("Пицца уже убрана");
                    }
                    else {
                        notAllowedPizzas.remove(pizzaManager.storageGet(selectedPizza));
                    }
                }
                else{
                    dialogState = false;
                }
            }
            else{
                dialogState = false;
            }
        }
    }

    private int selectSide(){
        int selectedSide;
        System.out.println("Выберите бортик\nЧтобы вернутся введите -1");
        this.print();
        selectedSide = scanner.nextInt();
        scanner.nextLine();
        if(selectedSide>=0 && selectedSide<storage.size()){
            return selectedSide;
        }
        else if(selectedSide == -1){
            return -1;
        }
        else{
            System.out.println("Некорректный ввод");
            return -1;
        }
    }
    private int selectAction(){
        int selectedAction;
        System.out.println("Изменить название - 1\nИзменить ингредиенты - 2\nИзменить запрещённые пиццы - 3\nЧтобы вернутся введите -1");
        selectedAction = scanner.nextInt();
        scanner.nextLine();
        if(selectedAction != 1 && selectedAction != 2 &&selectedAction != 3){
            return -1;
        }
        else return selectedAction;
    }

    private void changeSideName(int sideId){
        String newName;
        System.out.println("Введите новое название:");
        newName = scanner.nextLine();
        scanner.nextLine();
        storage.get(sideId).setName(newName);
    }

    private void changeSideIngredients(int sideId){
        boolean dialogState = true;
        int command;
        while (dialogState){
            System.out.println("Добавить - 1\nУдалить - 2\nДля выода введите -1");
            command = scanner.nextInt();
            scanner.nextLine();
            if(command == 1){
                addIngredientToSide(sideId);
            }
            else if(command == 2){
                deleteIngredientFromSide(sideId);
            }
            else if(command == -1){
                dialogState = false;
            }
            else{
                System.out.println("Некорректный ввод");
            }
        }
    }
    private void addIngredientToSide(int sideId){
        int selectedIngredient;
        boolean dialogState = true;
        while (dialogState){
            System.out.println("Введи номер ингредиента\nДля выхода введите -1");
            ingredientManager.print();
            selectedIngredient = scanner.nextInt();
            scanner.nextLine();
            if(selectedIngredient>=0 && selectedIngredient<ingredientManager.storageSize()){
                storage.get(sideId).ingredients.add(ingredientManager.storageGet(selectedIngredient));
            }
            else{
                dialogState = false;
            }
        }
    }

    private void deleteIngredientFromSide(int sideId){
        int selectedIngredient;
        boolean dialogState = true;
        while (dialogState){
            System.out.println("Введи номер ингредиента\nДля выхода введите -1");
            for (int i = 0; i < storage.get(sideId).ingredients.size(); i++) {
                System.out.println(i + " - " + storage.get(sideId).getName() + " - " + storage.get(sideId).getPrice());
            }
            selectedIngredient = scanner.nextInt();
            scanner.nextLine();
            if(selectedIngredient>=0 && selectedIngredient<storage.get(sideId).ingredients.size()){
                storage.get(sideId).ingredients.remove(selectedIngredient);
            }
            else{
                dialogState = false;
            }
        }
    }
    @Override
    public void filter() {
        System.out.println("Фильтрация бортиков:");
        System.out.println("1 - По названию");
        System.out.println("2 - По цене");
        System.out.println("3 - По ингредиенту");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 3) {
            ingredientManager.print();
            System.out.println("Введите номер ингредиента для фильтрации:");
            int ingredientIndex = scanner.nextInt();
            scanner.nextLine();

            if (ingredientIndex >= 0 && ingredientIndex < ingredientManager.storageSize()) {
                Ingredient selectedIngredient = ingredientManager.storageGet(ingredientIndex);
                for (Side side : storage) {
                    if (side.ingredients.contains(selectedIngredient)) {
                        System.out.println(side.getName() + " - " + side.getPrice());
                    }
                }
            } else {
                System.out.println("Неверный номер ингредиента");
            }
        } else {
            super.filter();
        }
    }
}

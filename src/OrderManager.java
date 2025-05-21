import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager  extends Manager{

    BaseManager baseManager;
    IngredientManager ingredientManager;
    SideManager sideManager;
    PizzaManager pizzaManager;

    public OrderManager(Manager<Base> baseManager,Manager<Ingredient> ingredientManager,Manager<Side> sideManager,Manager<Pizza> pizzaManager){
        this.baseManager = (BaseManager) baseManager;
        this.ingredientManager = (IngredientManager) ingredientManager;
        this.sideManager = (SideManager) sideManager;
        this.pizzaManager = (PizzaManager) pizzaManager;
    }

    @Override
    public void create() {
        int selectedAction;
        System.out.println("Создать с уже сделанными - 1\nСоздать новую - 2\nСоздать комбинированную - 3\nЧтобы подтвердить введите -1\n");
        selectedAction = scanner.nextInt();
        scanner.nextLine();
        Order order = new Order(getComment(),0);
        if(selectedAction == 1){
            addPizzasToOrder(order);
        }
        else if(selectedAction == 2){
            createNewPizzaToOrder(order);
        }
        else if(selectedAction == 3){
            createCombinedPizzaToOrder(order);
        }
        else{
            return;
        }
    }

    private void addPizzasToOrder(Order order){
        Pizza pizza;
        int selectedPizza;
        int doubleCommand;
        boolean dialogState = true;
        while (dialogState){
            System.out.println("Выбери пиццу для добаления\nЧтобы выйти введите -1");
            pizzaManager.print();
            selectedPizza = scanner.nextInt();
            if(selectedPizza == -1){
                dialogState = false;
            }
            if(selectedPizza>=0 && selectedPizza<pizzaManager.storageSize()){
                pizza = pizzaManager.storageGet(selectedPizza);
                System.out.println("Чтобы не удваивать количество ингердиентов - 0\nЧтобы удвоить - 1\nДля выхода введите -1");
                doubleCommand = scanner.nextInt();
                scanner.nextLine();
                if(doubleCommand == 0){
                    order.pizzas.add(pizza);
                }
                else if(doubleCommand == 1){
                    pizza.doubleIngredients();
                    order.pizzas.add(pizza);
                }
                else{
                    System.out.println("Некорректный ввод");
                }
            }
            else{
                System.out.println("Некорректный ввод");
            }
        }
    }

    private void createNewPizzaToOrder(Order order){
        Pizza pizza = createPizza();
        order.pizzas.add(pizza);
    }

    private void createCombinedPizzaToOrder(Order order){

    }



    private String getComment(){
        System.out.println("Введите комментарий к заказу");
        String comment = scanner.nextLine();
        return comment;
    }

    private Pizza createPizza(){
        Pizza pizza;
        String pizzaName = getNewPizzaName();
        if (pizzaName == null) return null;

        Base selectedBase = selectBaseFromUser();
        if (selectedBase == null) return null;

        ArrayList<Ingredient> ingredients = selectIngredientsFromUser();
        if (ingredients == null) return null;

        Side selectedSide = selectSideFromUser();
        if (selectedSide == null) return null;
        int price = 0;
        for(Ingredient ingredient: ingredients){
            price += ingredient.getPrice();
        }
        price+=selectedBase.getPrice();
        price+=selectedSide.getPrice();
        return new Pizza(pizzaName, price, selectedBase, ingredients, selectedSide);
    }
    private String getNewPizzaName() {
        System.out.println("Введите название пиццы");
        return scanner.nextLine();
    }

    private Base selectBaseFromUser() {
        Base selectedBase = null;
        boolean baseDialog = true;

        while (baseDialog) {
            System.out.println("Выберите основу\nЧтобы вернуться нажмите -1");
            baseManager.print();

            int selectedBaseIndex = scanner.nextInt();
            scanner.nextLine();
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
            scanner.nextLine();
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
    private Side selectSideFromUser(){
        Side selectedSide = null;
        boolean sideDialog = true;

        while (sideDialog) {
            System.out.println("Выберите бортик\nЧтобы вернуться нажмите -1");
            sideManager.print();

            int selectedSideIndex = scanner.nextInt();
            scanner.nextLine();
            if (selectedSideIndex >= 0 && selectedSideIndex < sideManager.storageSize()) {
                selectedSide = sideManager.storageGet(selectedSideIndex);
                sideDialog = false;
            } else if (selectedSideIndex == -1) {
                return null;
            } else {
                System.out.println("Некорректный ввод");
            }
        }
        return selectedSide;
    }

}

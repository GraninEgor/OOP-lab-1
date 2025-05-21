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
        System.out.println("Создать с уже сделанными - 1\nСоздать новую - 2\nСоздать комбинированную - 3\nЧтобы выйти введите -1\n");
        selectedAction = scanner.nextInt();
        scanner.nextLine();
        Order order;
        if(selectedAction == 1){
            order = addPizzasToOrder();
        }
        else if(selectedAction == 2){
            order = createNewPizzaToOrder();
        }
        else if(selectedAction == 3){
            order = createCombinedPizzaToOrder();
        }
        else{
            return;
        }
    }

    private Order addPizzasToOrder(){
        Pizza pizza;
        Order order = new Order(getComment(),0);
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
        return order;
    }

    private Order createNewPizzaToOrder(){

    }

    private Order createCombinedPizzaToOrder(){

    }
    public String getComment(){
        System.out.println("Введите комментарий к заказу");
        String comment = scanner.nextLine();
        return comment;
    }
}

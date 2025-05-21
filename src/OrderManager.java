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
        if(selectedAction == 1){

        }
        else if(selectedAction == 2){

        }
        else if(selectedAction == 3){

        }
        else{
            return;
        }
    }

    private void addPizzasToOrder(){

    }

    private void createNewPizzaToOrder(){

    }

    private void createCombinedPizzaToOrder(){

    }

}

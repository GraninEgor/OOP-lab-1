import java.util.InputMismatchException;
import java.util.Scanner;

public class PizzaSystem {
    Scanner scanner = new Scanner(System.in);
    int command;
    Controller controller = new Controller();
    Manager<Base> baseManager = new BaseManager();
    Manager<Ingredient> ingredientManager = new IngredientManager();
    Manager<Pizza> pizzaManager = new PizzaManager(baseManager,ingredientManager);
    Manager<Side> sideManager = new SideManager(ingredientManager, pizzaManager);
    Manager<Order> orderManager = new OrderManager(baseManager,ingredientManager,sideManager,pizzaManager);
    Manager selectedManager;

    void start() {
        ((PizzaManager) pizzaManager).setSideManager((SideManager) sideManager);
        while (true) {
            try {
                System.out.println("Создать - 1\nРедактировать - 2\nУдалить - 3\nВывести - 4\nФильтр - 5");
                command = scanner.nextInt();
                scanner.nextLine();
                switch (command) {
                    case 1:
                        selectedManager = selectManager();
                        if (selectedManager != null) {
                            controller.create(selectedManager);
                        }
                        break;
                    case 2:
                        selectedManager = selectManager();
                        if (selectedManager != null) {
                            controller.update(selectedManager);
                        }
                        break;
                    case 3:
                        selectedManager = selectManager();
                        if (selectedManager != null) {
                            controller.delete(selectedManager);
                        }
                        break;
                    case 4:
                        selectedManager = selectManager();
                        if (selectedManager != null) {
                            controller.show(selectedManager);
                        }
                        break;
                    case 5:
                        selectedManager = selectManager();
                        if (selectedManager != null) {
                            selectedManager.filter();
                        }
                        break;
                    default:
                        System.out.println("Некорректный ввод");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: некорректный ввод. Ожидается число.");
                scanner.nextLine();
            }
        }
    }

    Manager selectManager(){
        Manager manager = null;
        int selectedItemCommand;
        System.out.println("Пиццу - 1\nОснову - 2\nИнгредиент - 3\nБортик - 4\nЗаказ - 5\nЧтобы вернутся нажмите 0");
        selectedItemCommand = scanner.nextInt();
        scanner.nextLine();
        switch (selectedItemCommand){
            case 1:
                manager = pizzaManager;
                break;
            case 2:
                manager = baseManager;
                break;
            case 3:
                manager = ingredientManager;
                break;
            case 4:
                manager = sideManager;
                break;
            case 5:
                manager = orderManager;
            default:
                break;
        }
        return manager;
    }
}



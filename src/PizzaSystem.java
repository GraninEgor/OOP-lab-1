import java.util.ArrayList;
import java.util.Scanner;

public class PizzaSystem {
    Scanner scanner = new Scanner(System.in);
    int command;
    Controller controller = new Controller();
    ManagerFunctionality baseManager = new BaseManager();
    ManagerFunctionality ingredientManager = new IngredientManager();
    ManagerFunctionality pizzaManager = new PizzaManager(baseManager,ingredientManager);
    ManagerFunctionality selectedManager;

    void start(){
        while(true){
            System.out.println("Создать - 1\nРедактировать - 2\nУдалить -3\nВывести - 4");
            command = scanner.nextInt();
            switch (command){
                case 1:
                    selectedManager = selectManager();
                    if(selectedManager!=null){
                        controller.create(selectedManager);
                    }
                    break;
                case 2:
                    selectedManager = selectManager();
                    if(selectedManager!=null) {
                        controller.update(selectedManager);
                    }
                    break;
                case 3:
                    selectedManager = selectManager();
                    if(selectedManager!=null) {
                        controller.delete(selectedManager);
                    }
                    break;
                case 4:
                    selectedManager = selectManager();
                    if(selectedManager!=null) {
                        controller.show(selectedManager);
                    }
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    ManagerFunctionality selectManager(){
        ManagerFunctionality manager = null;
        int selectedItemCommand;
        System.out.println("Пиццу - 1\nОснову - 2\nИнгредиент - 3\nЧтобы вернутся нажмите 0");
        selectedItemCommand = scanner.nextInt();
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
            default:
                break;
        }
        return manager;
    }
}



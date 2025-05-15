import java.util.ArrayList;
import java.util.Scanner;

public class PizzaSystem {
    Scanner scanner = new Scanner(System.in);

    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private ArrayList<Base> bases = new ArrayList<Base>();
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    int command;

    void start(){
        while(true){
            System.out.println("Создать - 1\nРедактировать - 2\nУдалить -3\nВывести - 4");
            command = scanner.nextInt();
            switch (command){
                case 1:
                    createController();
                    break;
                case 2:
                    editController();
                    break;
                case 3:
                    deleteController();
                    break;
                case 4:
                    showController();
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }
    void createController(){
        System.out.println("Создать пиццу - 1\nСоздать основу - 2\nСоздать ингредиент - 3\n");
        command = scanner.nextInt();
        String name;
        int price;
        int chosenBase;
        switch (command){
            case 1:
                System.out.println("Имя пиццы");
                scanner.nextLine();
                name = scanner.nextLine();
                System.out.println("Выбери основу");
                showBases();
                break;
            case 2:
                System.out.println("Имя основы");
                scanner.nextLine();
                name = scanner.nextLine();
                System.out.println("Цена");
                price = scanner.nextInt();
                scanner.nextLine();
                bases.add(new Base(name,price));
                break;
            case 3:
                System.out.println("Имя ингедиента");
                scanner.nextLine();
                name = scanner.nextLine();
                System.out.println("Цена");
                price = scanner.nextInt();
                scanner.nextLine();
                ingredients.add(new Ingredient(name,price));
                break;
            default:
                System.out.println("Некорректный ввод");
        }
    }
    void editController(){
        System.out.println("Редактировать пиццу - 1\nРедактировать основу - 2\nРедактировать ингредиент - 3\n");
        command = scanner.nextInt();
        switch (command){
            case 1:
            case 2:
            case 3:
            default:
                System.out.println("Некорректный ввод");
        }
    }
    void deleteController(){
        System.out.println("Удалить пиццу - 1\nУдалить основу - 2\nУдалить ингредиент - 3\n");
        command = scanner.nextInt();
        switch (command){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("Некорректный ввод");
        }
    }
    void showController(){
        System.out.println("Пиццу - 1\nОснову - 2\nИнгредиент - 3\n");
        command = scanner.nextInt();
        switch (command){
            case 1:
                showPizzas();
                break;
            case 2:
                showBases();
                break;
            case 3:
                showIngredients();
                break;
            default:
                System.out.println("Некорректный ввод");
        }
    }
    void showPizzas(){
        for(int i = 0;i<pizzas.size();i++){
            System.out.println(i + " - " + printElementNameAndPrice(pizzas.get(i)));
        }
    }
    void showBases(){
        for(int i = 0;i<bases.size();i++){
            System.out.println(i + " - " + printElementNameAndPrice(bases.get(i)));
        }
    }
    void showIngredients(){
        for(int i = 0;i<ingredients.size();i++){
            System.out.println(i + " - " + printElementNameAndPrice(ingredients.get(i)));
        }
    }

    String printElementNameAndPrice(Component component){
        return component.getName() + " - " + component.getPrice();
    }
}

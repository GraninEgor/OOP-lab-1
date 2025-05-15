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
        String name;
        int price;
        int chosenBase;
        boolean stop = false;
        Base baseForPizza;
        ArrayList<Ingredient> ingredientsForPizza = new ArrayList<Ingredient>();
        while(true){
            countPizzasPrice();
            System.out.println("Создать пиццу - 1\nСоздать основу - 2\nСоздать ингредиент - 3\nЧтобы вернуться нажмите любое число\n");
            command = scanner.nextInt();
            switch (command){
                case 1:
                    System.out.println("Имя пиццы");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    while(true){
                        System.out.println("Выбери основу");
                        showBases();
                        command = scanner.nextInt();
                        if(command < 0 || command > bases.size()-1){
                            System.out.println("Некорректный ввод");
                        }
                        else {
                            baseForPizza = bases.get(command);
                            break;
                        }
                    }
                    while(true){
                        System.out.println("Выбери ингредиенты");
                        System.out.println("Для завершения введи -1");
                        showIngredients();
                        command = scanner.nextInt();
                        if(command == -1){
                            break;
                        }
                        else{
                            if(command < 0 || command > ingredients.size()-1){
                                System.out.println("Некорректный ввод");
                            }
                            else {
                                ingredientsForPizza.add(ingredients.get(command));
                            }
                        }
                    }
                    pizzas.add(new Pizza(name,0,baseForPizza,ingredientsForPizza));
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
                    stop = true;
                    break;
            }
            if(stop){
                break;
            }
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

    void countPizzasPrice(){
        int price;
        for(Pizza pizza: pizzas){
            price = 0;
            for(Ingredient ingredient : pizza.ingredients){
                price+=ingredient.getPrice();
            }
            price+=pizza.base.getPrice();
            pizza.setPrice(price);
        }
    }
}

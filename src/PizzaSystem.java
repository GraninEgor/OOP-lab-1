import java.util.ArrayList;
import java.util.Scanner;

public class PizzaSystem {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private ArrayList<Base> bases = new ArrayList<Base>();
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    int command;
    Controller controller = new Controller();
    Manager pizzaManager = new PizzaManager();
    Manager baseManager = new BaseManager();
    Manager ingredientManager = new IngredientManager();
    Manager selectedManager;

    void start(){
        while(true){
            countPizzasPrice();
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
    void create(){
        String name;
        int price;
        int chosenBase;
        boolean stop = false;
        Base baseForPizza;
        ArrayList<Ingredient> ingredientsForPizza = new ArrayList<Ingredient>();
        while(true){
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
    void edit(){
        boolean stop = false;
        String name;
        int price;
        int pizzaId;
        while (true){
            System.out.println("Редактировать пиццу - 1\nРедактировать основу - 2\nРедактировать ингредиент - 3\n");
            command = scanner.nextInt();
            switch (command){
                case 1:
                    while (true){
                        System.out.println("Введите номер пиццы");
                        showPizzas();
                        command = scanner.nextInt();
                        if(command <0 || command>pizzas.size()){
                            System.out.println("Некорректный ввод");
                            scanner.nextLine();
                        }
                        else{
                            pizzaId = command;
                            System.out.println("Изменить название - 0\nИзменить основу - 1\nИзменить ингедиенты - 2");
                            command = scanner.nextInt();
                            switch (command){
                                case 0:
                                    System.out.println("Введи новое название:");
                                    scanner.nextLine();
                                    name = scanner.nextLine();
                                    pizzas.get(pizzaId).setName(name);
                                    break;
                                case 1:
                                    while(true){
                                        System.out.println("Выберите новую основу:");
                                        scanner.nextLine();
                                        showBases();
                                        command = scanner.nextInt();
                                        if(command<0 || command>bases.size()){
                                            System.out.println("Некорректный ввод");
                                        }
                                        else {
                                            pizzas.get(pizzaId).base = bases.get(command);
                                            break;
                                        }
                                    }
                                    break;
                                case 2:
                                    while (true){
                                        System.out.println("Добавить - 0\nУдалить - 1\nДля выода введите -1");
                                        scanner.nextLine();
                                        command = scanner.nextInt();
                                        if(command == -1){
                                            break;
                                        }
                                        else{
                                            if(command == 0){
                                                while(true){
                                                    System.out.println("Выбери номер ингредиента:");
                                                    scanner.nextLine();
                                                    showIngredients();
                                                    command = scanner.nextInt();
                                                    if(command<0 || command> ingredients.size()){
                                                        System.out.println("Некорректный ввод");
                                                    }
                                                    else{
                                                        pizzas.get(pizzaId).ingredients.add(ingredients.get(command));
                                                        break;
                                                    }
                                                }
                                            }
                                            else if(command == 1){
                                                while(true){
                                                    System.out.println("Выбери номер ингредиента:");
                                                    scanner.nextLine();
                                                    showIngredients();
                                                    command = scanner.nextInt();
                                                    if(command<0 || command> ingredients.size()){
                                                        System.out.println("Некорректный ввод");
                                                    }
                                                    else{
                                                        pizzas.get(pizzaId).ingredients.remove(ingredients.get(command));
                                                        break;
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Некорректный ввод");
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    stop = true;
                            }
                            break;
                        }
                        if(stop){
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Введите номер основы");
                    scanner.nextLine();
                    showBases();
                    command = scanner.nextInt();
                    while (true){
                        if(command <0 || command>bases.size()-1){
                            System.out.println("Некорректный ввод");
                            scanner.nextLine();
                        }
                        else{
                            while (true){
                                System.out.println("Для изменения названия - 0\nДля изменения цены - 1\n");
                                scanner.nextLine();
                                command = scanner.nextInt();
                                if(command == 0){
                                    System.out.println("Введите новое название:");
                                    scanner.nextLine();
                                    name = scanner.nextLine();
                                    scanner.nextLine();
                                    bases.get(command).setName(name);
                                    break;
                                }
                                else if(command == 1){
                                    System.out.println("Введите новую цену:");
                                    scanner.nextLine();
                                    price = scanner.nextInt();
                                    scanner.nextLine();
                                    bases.get(command).setPrice(price);
                                    break;
                                }
                                else{
                                    System.out.println("Некорректный ввод");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Введите номер ингредиента");
                    scanner.nextLine();
                    showIngredients();
                    command = scanner.nextInt();
                    while (true){
                        if(command <0 || command>ingredients.size()-1){
                            System.out.println("Некорректный ввод");
                            scanner.nextLine();
                        }
                        else{
                            while (true){
                                System.out.println("Для изменения названия - 0\nДля изменения цены - 1\n");
                                scanner.nextLine();
                                command = scanner.nextInt();
                                if(command == 0){
                                    System.out.println("Введите новое название:");
                                    scanner.nextLine();
                                    name = scanner.nextLine();
                                    scanner.nextLine();
                                    ingredients.get(command).setName(name);
                                    break;
                                }
                                else if(command == 1){
                                    System.out.println("Введите новую цену:");
                                    scanner.nextLine();
                                    price = scanner.nextInt();
                                    scanner.nextLine();
                                    ingredients.get(command).setPrice(price);
                                    break;
                                }
                                else{
                                    System.out.println("Некорректный ввод");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Выход");
                    stop = true;
            }
            if(stop){
                break;
            }
        }
    }
    void delete(){
        boolean stop = false;
        System.out.println("Удалить пиццу - 1\nУдалить основу - 2\nУдалить ингредиент - 3\n");
        command = scanner.nextInt();
        while(true){
            switch (command){
                case 1:
                    deletePizza();
                    break;
                case 2:
                    deleteBase();
                    break;
                case 3:
                    deleteIngredient();
                    break;
                default:
                    System.out.println("Некорректный ввод");
                    stop = true;
            }
            if(stop){
                break;
            }
        }
    }
    void show(){
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

    void deleteBase(){
        System.out.println("Введи номер основы");
        showBases();
        command = scanner.nextInt();
        while (true){
            if(command <0 || command>bases.size()){
                System.out.println("Некорректный ввод");
            }
            else{
                bases.remove(command);
                break;
            }
        }
    }

    void deleteIngredient(){
        System.out.println("Введи номер ингредиента");
        showIngredients();
        command = scanner.nextInt();
        while (true){
            if(command <0 || command>ingredients.size()){
                System.out.println("Некорректный ввод");
            }
            else{
                ingredients.remove(command);
                break;
            }
        }
    }

    void deletePizza(){
        System.out.println("Введи номер пиццы");
        showPizzas();
        command = scanner.nextInt();
        while (true){
            if(command <0 || command>pizzas.size()){
                System.out.println("Некорректный ввод");
            }
            else{
                pizzas.remove(command);
                break;
            }
        }
    }

    void createController(){
        command = scanner.nextInt();
        boolean createDialog = true;
        while (createDialog){
            System.out.println("Создать пиццу - 1\nСоздать основу - 2\nСоздать ингредиент - 3\nЧтобы вернуться нажмите любое число\n");
            command = scanner.nextInt();
            switch (command){
                case  1:

            }
        }
    }
    void editController(){

    }
    void deleteController(){

    }
    void showController(){

    }

    Manager selectManager(){
        Manager manager = null;
        int selectedItemCommand;
        System.out.println("Пиццу - 1\nОснову - 2\nИнгредиент - 3\nЧтобы вернутся нажмите 0");
        selectedItemCommand = scanner.nextInt();
        switch (selectedItemCommand){
            case 1:
                manager = new PizzaManager();
                break;
            case 2:
                manager = new BaseManager();
                break;
            case 3:
                manager = new IngredientManager();
                break;
            default:
                break;
        }
        return manager;
    }
}



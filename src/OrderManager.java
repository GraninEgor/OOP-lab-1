import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class OrderManager  extends Manager<Order>{

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
        Order order = new Order(getComment(),0);
        boolean dialogState = true;
        while (dialogState){
            System.out.println("Создать с уже сделанными - 1\nСоздать новую - 2\nСоздать комбинированную - 3\nЧтобы подтвердить введите -1\n");
            selectedAction = scanner.nextInt();
            scanner.nextLine();
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
                dialogState = false;
            }
        }
        int isDeferred;
        System.out.println("Сделать отложенным?\nНет - 0\nДа - 1");
        isDeferred = scanner.nextInt();
        scanner.nextLine();
        if (isDeferred == 1){
            LocalDate userDate = getValidatedDateFromUser();
            LocalTime userTime = getValidatedTimeFromUser();
            order.setDate(userDate);
            order.setTime(userTime);
        }
        order.setPrice(countPrice(order.pizzas));
        storage.add(order);
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
            scanner.nextLine();

            if(selectedPizza == -1){
                dialogState = false;
            }

            if(selectedPizza >= 0 && selectedPizza < pizzaManager.storageSize()){
                pizza = pizzaManager.storageGet(selectedPizza);

                Size selectedSize = selectSizeFromUser();
                pizza.setSize(selectedSize);

                System.out.println("Чтобы не удваивать количество ингердиентов - 0\nЧтобы удвоить - 1\nДля выхода введите -1");
                doubleCommand = scanner.nextInt();
                scanner.nextLine();

                if(doubleCommand == 0){
                    order.pizzas.add(pizza);
                }
                else if(doubleCommand == 1){
                    Pizza doubledPizza = new Pizza(
                            pizza.getName(),
                            pizza.getPrice(),
                            pizza.base,
                            new ArrayList<>(pizza.ingredients),
                            pizza.side
                    );
                    doubledPizza.doubleIngredients(); // удваиваем ингредиенты
                    doubledPizza.setSize(selectedSize); // сохраняем тот же размер
                    order.pizzas.add(doubledPizza);
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

    private void createNewPizzaToOrder(Order order){
        Pizza pizza = createPizza();
        if (pizza == null){
            return;
        }
        order.pizzas.add(pizza);
    }

    private void createCombinedPizzaToOrder(Order order){
        Pizza halfA;
        Pizza halfB;
        int selected;
        System.out.println("Выберите первую половину:\nЧтобы выйти введите -1");
        pizzaManager.print();
        selected = scanner.nextInt();
        if(selected>=0 && selected<pizzaManager.storageSize()){
            halfA = pizzaManager.storageGet(selected);
        }
        else{
            return;
        }
        System.out.println("Выберите вторую половину:\nЧтобы выйти введите -1");
        pizzaManager.print();
        selected = scanner.nextInt();
        if(selected>=0 && selected<pizzaManager.storageSize()){
            halfB = pizzaManager.storageGet(selected);
        }
        else{
            return;
        }
        String name = halfA.getName() + " + " + halfB.getName();
        int price = halfA.getPrice()/2 + halfB.getPrice()/2;
        Base selectedBase = selectBaseFromUser();
        if (selectedBase == null) return;
        int selectAction;
        ArrayList<Ingredient> ingredients = new ArrayList<>(halfA.ingredients);
        ingredients.addAll(halfB.ingredients);
        System.out.println("Оставить разные бортики?\nНет - 0\nДа - 1");
        selectAction = scanner.nextInt();
        if(selectAction == 0){
            Side selectedSide = selectSideFromUser();
            Pizza pizza= new Pizza(name,price,selectedBase,ingredients,selectedSide);
            order.pizzas.add(pizza);
        }
        else if( selectAction == 1){
            Pizza pizza = new Pizza(name,price,selectedBase,ingredients,halfA,halfB,halfA.side,halfB.side);
            pizza.setSize(selectSizeFromUser());
            order.pizzas.add(pizza);
        }

    }

    private String getComment(){
        System.out.println("Введите комментарий к заказу");
        String comment = scanner.nextLine();
        return comment;
    }

    private Pizza createPizza(){
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
        Pizza pizza = new Pizza(pizzaName, 0, selectedBase, ingredients, selectedSide);
        pizza.setSize(selectSizeFromUser());

        return pizza;
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

    @Override
    public void print(){
        for(Order order: storage){
            System.out.println(order.getName() + " " + order.getPrice() + " " + order.date + " " + order.time);
            for(Pizza pizza: order.pizzas){
                System.out.println("   " + pizza.getName());
            }
        }

    }

    private int countPrice(ArrayList<Pizza> pizzas){
        int price = 0;
        for(Pizza pizza: pizzas){
            price+=pizza.getPrice();
        }
        return price;
    }

    public LocalDate getValidatedDateFromUser() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("Введите дату в формате yyyy-MM-dd:");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Попробуйте снова.");
            }
        }
    }

    public LocalTime getValidatedTimeFromUser() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        while (true) {
            System.out.println("Введите время в формате HH:mm:");
            String input = scanner.nextLine();
            try {
                return LocalTime.parse(input, timeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат времени. Попробуйте снова.");
            }
        }
    }

    private Size selectSizeFromUser() {
        System.out.println("Выберите размер пиццы:");
        System.out.println("1 - Маленькая (×0.7)");
        System.out.println("2 - Средняя (×1.0)");
        System.out.println("3 - Большая (×1.3)");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: return Size.SMALL;
            case 2: return Size.MEDIUM;
            case 3: return Size.LARGE;
            default:
                System.out.println("Неверный выбор, установлено значение по умолчанию: Средний размер");
                return Size.MEDIUM;
        }
    }
    @Override
    public void filter() {
        System.out.println("Фильтрация заказов:");
        System.out.println("1 - По комментарию");
        System.out.println("2 - По стоимости");
        System.out.println("3 - По дате");
        System.out.println("4 - По времени");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 3) {
            LocalDate date = getValidatedDateFromUser();
            for (Order order : storage) {
                if (order.getDate().isEqual(date)) {
                    System.out.println(order.getName() + " - " + order.getPrice() + " - " + order.getDate());
                }
            }
        } else if (choice == 4) {
            LocalTime time = getValidatedTimeFromUser();
            for (Order order : storage) {
                if (order.getTime().equals(time)) {
                    System.out.println(order.getName() + " - " + order.getPrice() + " - " + order.getTime());
                }
            }
        } else {
            super.filter(); // базовая фильтрация
        }
    }
}

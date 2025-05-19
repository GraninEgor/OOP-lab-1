import java.util.ArrayList;
import java.util.Scanner;

public class IngredientManager extends Manager<Ingredient> implements ManagerFunctionality<Ingredient> {

    @Override
    public void create(){
        String name;
        int price;
        System.out.println("Имя ингедиента");
        name = scanner.nextLine();
        System.out.println("Цена");
        price = scanner.nextInt();
        scanner.nextLine();
        storageAdd(new Ingredient(name,price));
    }
    @Override
    public void update(){

    }
}

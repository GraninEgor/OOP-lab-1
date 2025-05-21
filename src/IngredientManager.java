import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IngredientManager extends Manager<Ingredient> {

    @Override
    public void create() {
        String name;
        int price;
        try {
            System.out.println("Имя ингредиента");
            name = scanner.nextLine();
            System.out.println("Цена");
            price = scanner.nextInt();
            scanner.nextLine();
            storageAdd(new Ingredient(name, price));
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: некорректный ввод. Ожидается число.");
            scanner.nextLine();
        }
    }
}

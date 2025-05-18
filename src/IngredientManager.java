import java.util.ArrayList;
import java.util.Scanner;

public class IngredientManager implements Manager {
    private final ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public void storageAdd(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    Scanner scanner;
    @Override
    public void create(){
        String name;
        int price;
        System.out.println("Имя ингедиента");
        scanner.nextLine();
        name = scanner.nextLine();
        System.out.println("Цена");
        price = scanner.nextInt();
        scanner.nextLine();
        storageAdd(new Ingredient(name,price));
    }
    @Override
    public void update(){

    }
    @Override
    public void delete(){
//        System.out.println("Введи номер ингредиента");
//        showIngredients();
//        command = scanner.nextInt();
//        while (true){
//            if(command <0 || command>ingredients.size()){
//                System.out.println("Некорректный ввод");
//            }
//            else{
//                ingredients.remove(command);
//                break;
//            }
//        }
    }
    @Override
    public void print(){

    }
}

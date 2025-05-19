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
}

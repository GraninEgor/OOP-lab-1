import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class IngredientManager implements Manager<Ingredient> {
    private final ArrayList<Ingredient> ingredients = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
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
    @Override
    public void print(){
        for(int i = 0;i<ingredients.size();i++){
            System.out.println(i + " - " + ingredients.get(i).getName() + " - " + ingredients.get(i).getPrice());
        }
    }

    @Override
    public void storageAdd(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}

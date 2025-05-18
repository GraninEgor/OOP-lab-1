import java.util.ArrayList;

public class IngredientManager extends Manager {
    private final ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    @Override
    public void create(){

    }
    @Override
    public void update(){

    }
    @Override
    public void delete(){
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
    @Override
    public void print(){

    }
}

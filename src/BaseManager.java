import java.util.ArrayList;
import java.util.Scanner;

public class BaseManager extends Manager<Base> implements ManagerFunctionality<Base> {

    Base classic;

    @Override
    public void create(){
        String name;
        int price;
        System.out.println("Имя основы");
        name = scanner.nextLine();
        System.out.println("Цена");
        price = scanner.nextInt();
        scanner.nextLine();
        if(price > classic.getPrice()*1.2 || price < classic.getPrice()*0.8){
            System.out.println("Цена отличается от классической на 20%");
            return;
        }
        storage.add(new Base(name,price));
    }

    @Override
    public void update(){
        boolean updateDialog = true;
        int selectedItem;
        int selectedChange;
        String name;
        int price;
        while (updateDialog){
            System.out.println("Введи номер:\nДля выхода введи -1");
            print();
            selectedItem = scanner.nextInt();
            if(selectedItem>=0 && selectedItem<storage.size()){
                System.out.println("Изменить название - 1\nИзменить цену - 2\nДля выхода введи -1");
                selectedChange = scanner.nextInt();
                if(selectedChange == 1){
                    System.out.println("Введи новое название");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    storage.get(selectedItem).setName(name);
                }
                else if(selectedChange == 2){
                    System.out.println("Введи новую цену");
                    price = scanner.nextInt();
                    if(storage.get(selectedItem) != classic){
                        if(price > classic.getPrice()*1.2 || price < classic.getPrice()*0.8){
                            System.out.println("Цена отличается от классической на 20%");
                            return;
                        }
                    }
                    storage.get(selectedItem).setPrice(price);
                    updateDialog = false;
                }
                else{
                    updateDialog = false;
                }
            }
            else{
                if(selectedItem == -1){
                    updateDialog = false;
                }
                else{
                    System.out.println("Некорректный ввод");
                }
            }
        }
    }

    public BaseManager() {
        classic = new Base("Классическая", 50);
        storage.add(classic);
    }
}

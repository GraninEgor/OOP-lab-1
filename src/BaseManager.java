import java.util.ArrayList;
import java.util.Scanner;

public class BaseManager extends Manager<Base> implements ManagerFunctionality<Base> {

    @Override
    public void create(){
        String name;
        int price;
        System.out.println("Имя основы");
        name = scanner.nextLine();
        System.out.println("Цена");
        price = scanner.nextInt();
        scanner.nextLine();
        storage.add(new Base(name,price));
    }

}

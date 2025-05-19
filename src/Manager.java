import java.util.ArrayList;
import java.util.Scanner;

public abstract class Manager<T extends Component> {
    protected Scanner scanner = new Scanner(System.in);
    protected ArrayList<T> storage = new ArrayList<>();

    public void print(){
        for(int i = 0;i<storage.size();i++){
            System.out.println(i + " - " + storage.get(i).getName() + " - " + storage.get(i).getPrice());
        }
    }

    public void storageAdd(T item) {
        storage.add(item);
    }

    public int storageSize() {
        return storage.size();
    }

    public T storageGet(int index) {
        return storage.get(index);
    }

    public void delete(){
        boolean deleteDialog = true;
        int selected;
        while (deleteDialog){
            System.out.println("Введи номер:\nДля выхода введи -1");
            print();
            selected = scanner.nextInt();
            if(selected>0 && selected<storage.size()){
                storage.remove(selected);
            }
            else{
                deleteDialog = false;
            }
        }
    }

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
                    storage.get(selectedItem).setPrice(price);
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



}

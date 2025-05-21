import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public abstract class Manager<T extends Component> implements ManagerFunctionality<T>{
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
                    updateDialog = false;
                }
                else if(selectedChange == 2){
                    System.out.println("Введи новую цену");
                    price = scanner.nextInt();
                    storage.get(selectedItem).setPrice(price);
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
    public void filter() {
        if (storage.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }

        System.out.println("Выберите тип фильтрации:");
        System.out.println("1 - По названию (по алфавиту)");
        System.out.println("2 - По цене (возрастание)");
        System.out.println("3 - По цене (убывание)");
        int choice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<T> filteredList = new ArrayList<>(storage);

        switch (choice) {
            case 1:
                filteredList.sort(Comparator.comparing(T::getName));
                break;
            case 2:
                filteredList.sort(Comparator.comparingInt(T::getPrice));
                break;
            case 3:
                filteredList.sort(Comparator.comparingInt(T::getPrice).reversed());
                break;
            default:
                System.out.println("Неверный выбор");
                return;
        }

        for (T item : filteredList) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
    }


}

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


}

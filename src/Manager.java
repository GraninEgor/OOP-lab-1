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
}

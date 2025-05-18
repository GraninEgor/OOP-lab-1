import java.util.ArrayList;
import java.util.Scanner;

public abstract class Manager<T> {
    protected Scanner scanner = new Scanner(System.in);
    protected ArrayList<T> storage = new ArrayList<>();
}

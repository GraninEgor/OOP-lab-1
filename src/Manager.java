import java.util.ArrayList;

public interface Manager<T> {

    void create();
    void update();
    void delete();
    void print();
    void storageAdd(Ingredient ingredient);
}

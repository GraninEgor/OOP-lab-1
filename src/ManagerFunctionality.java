public interface ManagerFunctionality<T> {
    void create();
    void update();
    void delete();
    void print();
    void storageAdd(T component);
    int storageSize();
    T storageGet(int index);
}
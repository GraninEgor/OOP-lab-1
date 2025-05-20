public class SideManager extends Manager<Side>{
    @Override
    public void create(){
        String name;
        int price;
        System.out.println("Название бортика");
        name = scanner.nextLine();
        System.out.println("Цена");
        price = scanner.nextInt();
        scanner.nextLine();
        storage.add(new Side(name,price));
    }
}

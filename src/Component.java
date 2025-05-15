public abstract class Component {
    private String name;
    private int price;

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(String name){
        this.name = name;
    }

    public Component(String name, int price){
        this.name = name;
        this.price = price;
    }
}

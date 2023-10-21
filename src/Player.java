public class Player {
    String name;
    int color;

    public Player(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return name;
    }
    public String getStringColor(){
        return color == 0 ? "white" : "Black";
    }
}

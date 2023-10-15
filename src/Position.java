public class Position{
    Character column;
    int row;
    public Position(Character Col,int Row){
        this.column=Col;
        this.row=Row;
    }

    public String toString() {
        return (String.format("%c%d",column,row));
    }
}

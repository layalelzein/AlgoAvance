package Stack_LIFO;


public class Couche {
    
    String data;
    Couche bottom;

    public Couche(String data, Couche bottom) {
        this.data=data;
        this.bottom=bottom;
    }

    public void setBottom(Couche n) {
        this.bottom = n;
    }

    public Couche getBottom() {
        return this.bottom;
    }

    public void setData(String s) {
        this.data = s;
    }

    public String getData() {
        return this.data;
    }
}
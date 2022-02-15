public class Balloon {
    private String color;
    private double psi;

    public Balloon(String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public double getPsi() {
        return psi > 16.0 ? Double.POSITIVE_INFINITY : psi;
    }

    public void inflate (){
        this.psi += Math.random() * 5.0;
    }

    public boolean isExploded() {
        return psi > 16.0;
    }
}

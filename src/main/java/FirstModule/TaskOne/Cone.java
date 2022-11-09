package FirstModule.TaskOne;

class Cone implements Figures {

    protected int baseArea;
    protected int height;
    protected int radius;

    Cone(int baseArea, int height, int radius) {
        this.baseArea = baseArea;
        this.height = height;
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "Cone";
    }

    @Override
    public double volume() {
        if (baseArea != 0)
            return this.baseArea * this.height / 3.0;
        else
            return Math.PI * this.radius * this.radius * this.height / 3.0;
    }
}

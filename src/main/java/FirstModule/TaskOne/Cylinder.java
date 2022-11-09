package FirstModule.TaskOne;

class Cylinder implements Figures {

    protected int baseArea;
    protected int height;
    protected int radius;

    Cylinder(int baseArea, int height, int radius) {
        this.baseArea = baseArea;
        this.height = height;
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "Cylinder";
    }

    @Override
    public double volume() {
        if (baseArea != 0)
            return this.baseArea * this.height;
        else
            return Math.PI * this.radius * this.radius * this.height;
    }
}

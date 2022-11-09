package FirstModule.TaskOne;

class Sphere implements Figures {
    protected int radius;

    Sphere(int radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "Sphere";
    }

    @Override
    public double volume() {
        return Math.PI * this.radius * this.radius * this.radius * 4 / 3.0;
    }
}

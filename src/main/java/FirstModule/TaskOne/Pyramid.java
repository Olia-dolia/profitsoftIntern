package FirstModule.TaskOne;

class Pyramid implements Figures {

    protected int baseArea;
    protected int height;

    Pyramid(int baseArea, int height) {
        this.baseArea = baseArea;
        this.height = height;
    }

    @Override
    public String getName() {
        return "Pyramid";
    }

    @Override
    public double volume() {
        return this.baseArea * this.height / 3.0;
    }
}

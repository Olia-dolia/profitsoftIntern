package FirstModule.TaskOne;

class Prism implements Figures {

    protected int baseArea;
    protected int height;

    Prism(int baseArea, int height) {
        this.baseArea = baseArea;
        this.height = height;
    }

    @Override
    public String getName() {
        return "Prism";
    }

    @Override
    public double volume() {
        return this.baseArea * this.height;
    }
}

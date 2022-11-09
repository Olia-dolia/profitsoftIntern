package FirstModule.TaskOne;

class Cube implements Figures {
    protected int facet;

    Cube(int facet) {
        this.facet = facet;
    }

    @Override
    public String getName() {
        return "Cube";
    }

    @Override
    public double volume() {
        return this.facet * this.facet * this.facet;
    }
}

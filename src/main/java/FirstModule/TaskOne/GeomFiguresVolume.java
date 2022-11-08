package FirstModule.TaskOne;

import java.util.*;

interface Figures{
    double volume();
}

class Cube implements Figures{
    private int facet;

    Cube(int facet) {
        this.facet = facet;
    }

    public void setFacet(int facet) {
        this.facet = facet;
    }

    @Override
    public double volume() {
        return this.facet*this.facet*this.facet;
    }
}

class Sphere implements Figures{
    private int radius;

    Sphere(int radius) {
        this.radius = radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double volume() {
        return Math.PI*this.radius*this.radius*this.radius*4/3.0;
    }
}

class Prism implements Figures{

    private int baseArea;
    private int height;

    Prism(int baseArea, int height){
        this.baseArea = baseArea;
        this.height = height;
    }

    public void setBaseArea(int baseArea) {
        this.baseArea = baseArea;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double volume() {
        return this.baseArea*this.height;
    }
}

class Pyramid implements Figures{

    private int baseArea;
    private int height;

    Pyramid(int baseArea, int height){
        this.baseArea = baseArea;
        this.height = height;
    }

    public void setBaseArea(int baseArea) {
        this.baseArea = baseArea;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double volume() {
        return this.baseArea*this.height/3.0;
    }
}

class Cylinder implements Figures{

    private int baseArea;
    private int height;
    private int radius;

    Cylinder(int baseArea, int height, int radius){
        this.baseArea = baseArea;
        this.height = height;
        this.radius = radius;
    }

    public void setBaseArea(int baseArea) {
        this.baseArea = baseArea;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double volume() {
        if (baseArea != 0)
            return this.baseArea*this.height;
        else
            return Math.PI*this.radius*this.radius*this.height;
    }
}

class Cone implements Figures{

    private int baseArea;
    private int height;
    private int radius;

    Cone(int baseArea, int height, int radius){
        this.baseArea = baseArea;
        this.height = height;
        this.radius = radius;
    }

    public void setBaseArea(int baseArea) {
        this.baseArea = baseArea;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double volume() {
        if (baseArea != 0)
            return this.baseArea*this.height/3.0;
        else
            return Math.PI*this.radius*this.radius*this.height/3.0;
    }
}

public class GeomFiguresVolume {
        public static List<Double> geomFiguresVolume(List<Figures> figuresList){
            List<Double> volumesList = new ArrayList<>();
            int listSize = 0;
            while (listSize < figuresList.size()){
                volumesList.add(figuresList.get(listSize).volume());
                listSize++;
            }
            volumesList.sort(Collections.reverseOrder());
        return volumesList;
    }
    public static void main(String[] args) {
        Figures cube = new Cube(12);
        Figures prism = new Prism(12, 3);
        Figures pyramid = new Pyramid(13, 4);
        Figures cylinder = new Cylinder(0, 8, 4);
        Figures cone = new Cone(12, 6,3);
        Figures sphere = new Sphere(12);
        List<Figures> figuresList= new ArrayList<>();
        figuresList.add(cube);
        figuresList.add(prism);
        figuresList.add(pyramid);
        figuresList.add(cylinder);
        figuresList.add(cone);
        figuresList.add(sphere);
        List<Double> volumesList = geomFiguresVolume(figuresList);
        for (double x: volumesList) {
            System.out.println(x);
        }
    }
}

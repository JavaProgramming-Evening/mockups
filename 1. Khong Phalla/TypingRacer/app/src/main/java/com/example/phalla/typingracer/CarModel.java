package com.example.phalla.typingracer;

/**
 * Created by Sony on 27/06/2016.
 */
public class CarModel {
    private int carImage;
    private boolean isSelected;

    public CarModel(int carImageUrl, boolean carSelected){
        carImage = carImageUrl;
        isSelected = carSelected;
    }

    public int getCar() {
        return carImage;
    }

    public void setCar(int carImageUrl) {
        this.carImage = carImageUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

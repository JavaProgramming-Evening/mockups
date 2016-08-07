package com.example.phalla.typingracer;

/**
 * Created by Sony on 27/06/2016.
 */
public class CarModel {
    private int carImage;

    public CarModel(int carImageUrl){
        carImage = carImageUrl;
    }

    public int getCar() {
        return carImage;
    }

    public void setCar(int carImageUrl) {
        this.carImage = carImageUrl;
    }

}

package com.mamdani;


public interface AccessoryFunc {
    double getValue(double x);

    AccessoryFunc copyFunc();

    void setActivatedValue(double x);

    double getActivatedValue(double x);
}

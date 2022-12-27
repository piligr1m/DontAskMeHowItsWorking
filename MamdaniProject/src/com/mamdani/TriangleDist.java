package com.mamdani;

import com.mamdani.exceptions.ArgumentOutOfBoundsException;

public class TriangleDist implements AccessoryFunc{
    private double a;
    private double b;
    private double c;
    private double activatedValue;

    public TriangleDist(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getValue(double x) {
        if (x <= a) {
            return 0;
        }
        else if (x >= a && x < b) {
            return (x - a) / (b - a);
        }
        else if (x > b && x <= c) {
            return (c - x) / (c - b);
        }
        else if (c < x) {
            return 0;
        }
        throw new ArgumentOutOfBoundsException("ArgumentOutOfBoundsException");
    }

    @Override
    public AccessoryFunc copyFunc() {
        return new TriangleDist(this.a, this.b, this.c);
    }

    @Override
    public void setActivatedValue(double activatedValue) {
        this.activatedValue = activatedValue;
    }

    @Override
    public double getActivatedValue(double x) {
        return Math.min(getValue(x), activatedValue);
    }
}

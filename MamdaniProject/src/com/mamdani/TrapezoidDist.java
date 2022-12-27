package com.mamdani;

import com.mamdani.exceptions.ArgumentOutOfBoundsException;

public class TrapezoidDist implements AccessoryFunc {
    private double a;
    private double b;
    private double c;
    private double d;
    private double activatedValue;

    public TrapezoidDist(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getValue(double x) {
        if (x <= a) {
            return 0;
        }
        else if (x >= a && x < b) {
            return (x - a) / (b - a);
        }
        else if (x >= b && x <= c) {
            return 1;
        }
        else if (x > c && x <= d) {
            return (d - x) / (d - c);
        }
        else if (d < x) {
            return 0;
        }
        throw new ArgumentOutOfBoundsException("ArgumentOutOfBoundsException");
    }

    public void setActivatedValue(double activatedValue) {
        this.activatedValue = activatedValue;
    }

    public double getActivatedValue(double x) {
        return Math.min(getValue(x), activatedValue);
    }


    public AccessoryFunc copyFunc() {
        TrapezoidDist func = new TrapezoidDist(this.a, this.b, this.c, this.d);
        return func;
    }

}

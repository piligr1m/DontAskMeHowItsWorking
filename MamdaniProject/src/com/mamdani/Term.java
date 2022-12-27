package com.mamdani;

public class Term {
    private String name;
    private AccessoryFunc accessoryFunc;

    public Term(String name, AccessoryFunc func) {
        this.name = name;
        this.accessoryFunc = func;
    }

    public String getName() {
        return name;
    }

    public AccessoryFunc getAccessoryFunc() {
        return accessoryFunc;
    }

    public Term copyTerm(){
        Term term = new Term(this.name, accessoryFunc.copyFunc());
        return term;
    }

    public void setActivatedValue(double x) {
        accessoryFunc.setActivatedValue(x);
    }

    public double getActivatedValue(double x) {
        return accessoryFunc.getActivatedValue(x);
    }

}

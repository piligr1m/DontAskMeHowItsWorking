package com.mamdani;

import java.util.ArrayList;
import java.util.List;

public class UnionOfTermSet {
    List<Term> unionOfTermSet;

    public UnionOfTermSet(int i) {
        unionOfTermSet = new ArrayList<Term>(i);
    }

    public double getMaxValue(double x) {
        double y = 0.0;
        for (Term term : unionOfTermSet) {
            y = Math.max(y, term.getActivatedValue(x));
        }
        return y;
    }

    public void add(Term term) {
        unionOfTermSet.add(term);
    }
}

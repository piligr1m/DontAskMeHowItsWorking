package com.mamdani;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TermSet {
    private Set<Term> terms;

    public TermSet() {
        terms = new HashSet<Term>();
    }
    public void setTerm(String name, AccessoryFunc func) {
        terms.add(new Term(name, func));
    }

    public boolean containsTerm(String termName) {
        Iterator<Term> iterator = terms.iterator();
        while (iterator.hasNext()) {
            Term term = iterator.next();
            if (term.getName() == termName) {
                return true;
            }
        }
        return false;
    }

    public Term getTerm(String termName) {
        Iterator<Term> iterator = terms.iterator();
        while (iterator.hasNext()) {
            Term term = iterator.next();
            if (term.getName() == termName) {
                return term;
            }
        }
        return null;
    }

    public AccessoryFunc getFuncForTerm(Term x) {
        Iterator<Term> iterator = terms.iterator();
        while (iterator.hasNext()) {
            Term term = iterator.next();
            if (term.equals(x)) {
                return term.getAccessoryFunc();
            }
        }
        return null;
    }

}

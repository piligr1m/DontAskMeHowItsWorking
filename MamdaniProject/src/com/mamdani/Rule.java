package com.mamdani;

import java.util.ArrayList;
import java.util.List;


public class Rule {
    private static int numberOfConditions;
    private static int numberOfConclusions;
    List<Condition> conditions;
    Conclusion conclusion;

    public void setConditions(Condition... conditions) {
        this.conditions = new ArrayList<Condition>();
        for (Condition cond : conditions) {
            this.conditions.add(cond);
        }
        Rule.numberOfConditions += conditions.length;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
        Rule.numberOfConclusions++;
    }

    public static int getNumberOfConditions() {
        return numberOfConditions;
    }

    public static int getNumberOfConclusions() {
        return numberOfConclusions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Conclusion getConclusion() {
        return conclusion;
    }
}

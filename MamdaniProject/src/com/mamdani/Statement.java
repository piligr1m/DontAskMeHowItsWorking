package com.mamdani;

import com.mamdani.exceptions.HasNoThatTermException;
import com.mamdani.exceptions.HasNoThatVariableException;

import java.util.Map;

public class Statement {
    private static Map<String, LinguisticVariable> variables;
    private LinguisticVariable linguisticVariable;
    private Term term;

    public static void setLingVariables(Map<String, LinguisticVariable> variables) {
        Statement.variables = variables;
    }

    public Statement(String variableName, String termName) {
        if (!variables.containsKey(variableName)) {
            throw new HasNoThatVariableException("HasNoThatVariableException");
        }
        if (!variables.get(variableName).hasTerm(termName)) {
            throw new HasNoThatTermException(variableName + ":" + termName);
        }
        this.linguisticVariable = variables.get(variableName);
        this.term = variables.get(variableName).getTerm(termName);
    }

    public int getVariableId() {
        return linguisticVariable.getId();
    }

    public LinguisticVariable getLingVariable() {
        return linguisticVariable;
    }

    public Term getTerm() {
        return term;
    }
}

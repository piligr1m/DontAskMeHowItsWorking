package com.mamdani.alg;

import com.mamdani.*;
import com.mamdani.exceptions.ArgumentOutOfBoundsException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MamdaniAlg {
    private Map<String, LinguisticVariable> variables;
    private ArrayList<Rule> rules;
    UnionOfTermSet unionOfTerms;

    public static void main(String[] args) {
        MamdaniAlg controller = new MamdaniAlg();
        controller.loadVariables();
        controller.loadRules();
        double b[] = controller.fuzzification(new double[]{0.8, 0.68});

        double c[] = controller.aggregation(b);
        System.out.println();
        System.out.println();
        controller.unionOfTerms = controller.composition(c);

        for (double x = 0.0; x <= 1.0; x += 0.01) {
            System.out.println(x + " : " + controller.unionOfTerms.getMaxValue(x) + " ");
        }
        double[] ad;
        System.out.println("Result");
        ad = controller.defuzzification(controller.unionOfTerms);
        for (int i = 0; i < 100; i++){
            System.out.println(ad[i]);
        }
    }


    private void loadVariables() {
        variables = new HashMap<String, LinguisticVariable>();

        TermSet ts1 = new TermSet();
        ts1.setTerm("низкий", new TrapezoidDist(0, 0, 0.3, 0.4));
        ts1.setTerm("средний", new TrapezoidDist(0.3, 0.4, 0.6, 0.7));
        ts1.setTerm("высокий", new TrapezoidDist(0.6, 0.7, 1, 1));
        LinguisticVariable x1 = new LinguisticVariable("input1", ts1);
        variables.put("X1", x1);

        TermSet ts2 = new TermSet();
        ts2.setTerm("блондин", new TrapezoidDist(0, 0, 0.4, 0.5));
        ts2.setTerm("брюнет", new TriangleDist(0.4, 0.6, 0.7));
        ts2.setTerm("шатен", new TriangleDist(0.6, 0.8, 0.9));
        ts2.setTerm("руссый", new TriangleDist(0.8, 1, 1));
        LinguisticVariable x2 = new LinguisticVariable("input2", ts2);
        variables.put("X2", x2);

        TermSet ts3 = new TermSet();
        ts3.setTerm("свой", new TrapezoidDist(0, 0, 0.2, 0.3));
        ts3.setTerm("чужой", new TrapezoidDist(0.2, 0.3, 0.4, 0.5));
        LinguisticVariable y = new LinguisticVariable("output", ts3);
        variables.put("Y", y);

    }

    private void loadRules() {
        Statement.setLingVariables(variables);
        rules = new ArrayList<Rule>();


        Rule r1 = new Rule();
        r1.setConditions(new Condition("X1", "высокий"), new Condition("X2", "блондин"));
        r1.setConclusion(new Conclusion("Y", "свой"));
        rules.add(r1);

        Rule r2 = new Rule();
        r2.setConditions(new Condition("X1", "высокий"), new Condition("X2", "брюнет"));
        r2.setConclusion(new Conclusion("Y", "свой"));
        rules.add(r2);

        Rule r3 = new Rule();
        r3.setConditions(new Condition("X1", "высокий"), new Condition("X2", "шатен"));
        r3.setConclusion(new Conclusion("Y", "чужой"));
        rules.add(r3);

        Rule r4 = new Rule();
        r4.setConditions(new Condition("X1", "высокий"), new Condition("X2", "руссый"));
        r4.setConclusion(new Conclusion("Y", "свой"));
        rules.add(r4);

        Rule r5 = new Rule();
        r5.setConditions(new Condition("X1", "средний"), new Condition("X2", "блондин"));
        r5.setConclusion(new Conclusion("Y", "чужой"));
        rules.add(r5);

        Rule r6 = new Rule();
        r6.setConditions(new Condition("X1", "средний"), new Condition("X2", "брюнет"));
        r6.setConclusion(new Conclusion("Y", "чужой"));
        rules.add(r6);

        Rule r7 = new Rule();
        r7.setConditions(new Condition("X1", "средний"), new Condition("X2", "шатен"));
        r7.setConclusion(new Conclusion("Y", "чужой"));
        rules.add(r7);

        Rule r8 = new Rule();
        r8.setConditions(new Condition("X1", "средний"), new Condition("X2", "руссый"));
        r8.setConclusion(new Conclusion("Y", "свой"));
        rules.add(r8);

        Rule r9 = new Rule();
        r9.setConditions(new Condition("X1", "низкий"), new Condition("X2", "блондин"));
        r9.setConclusion(new Conclusion("Y", "свой"));
        rules.add(r9);

        Rule r10 = new Rule();
        r10.setConditions(new Condition("X1", "низкий"), new Condition("X2", "брюнет"));
        r10.setConclusion(new Conclusion("Y", "чужой"));
        rules.add(r10);

        Rule r11 = new Rule();
        r11.setConditions(new Condition("X1", "низкий"), new Condition("X2", "шатен"));
        r11.setConclusion(new Conclusion("Y", "свой"));
        rules.add(r11);

        Rule r12 = new Rule();
        r12.setConditions(new Condition("X1", "низкий"), new Condition("X2", "руссый"));
        r12.setConclusion(new Conclusion("Y", "чужой"));
        rules.add(r12);

    }

    public double[] fuzzification(double[] inputData) {
        if (inputData.length != variables.size() - 1) {
            throw new ArgumentOutOfBoundsException("ArgumentOutOfBoundsException");
        }

        int i = 0;
        double[] b = new double[Rule.getNumberOfConditions()];
        for (Rule rule : rules) {
            for (Statement condition : rule.getConditions()) {
                int id = condition.getVariableId();
                LinguisticVariable variable = condition.getLingVariable();
                b[i] = variable.getValueForTerm(condition.getTerm(), inputData[id]);
                System.out.print(variable.getName() + " : " + b[i] + " ");
                i++;
            }
            System.out.println();
        }
        return b;
    }

    private double[] aggregation(double[] b) {
        int i = 0;
        int j = 0;
        double[] c = new double[Rule.getNumberOfConclusions()];
        for (Rule rule : rules) {
            double truthOfConditions = 1.0;
            for (Statement condition : rule.getConditions()) {
                truthOfConditions = Math.min(truthOfConditions, b[i]);
                i++;
            }
            c[j] = truthOfConditions;
            System.out.println(c[j]);
            j++;
        }
        return c;
    }


    private UnionOfTermSet composition(double c[]) {
        UnionOfTermSet unionOfTerms = new UnionOfTermSet(Rule.getNumberOfConclusions());
        int i = 0;
        for (Rule rule : rules) {
            Term term = rule.getConclusion().getTerm().copyTerm();
            term.setActivatedValue(c[i]);
            unionOfTerms.add(term);
            i++;
        }
        return unionOfTerms;
    }

    private double[] defuzzification(UnionOfTermSet unionOfTerms) {
        double[] y = new double[100];
        double x, y1 = 0.0, y2 = 0.0, step = 0.01;
        int i = 0;
        for (x = 0.0; x <= 1.0; x += step) {
            y1 += x * unionOfTerms.getMaxValue(x);
            y2 += unionOfTerms.getMaxValue(x);
            y[i] = y1/y2;
            i++;
        }
        return y;
    }

}

package demo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

// Evaluate a given mathematical expression and return its value
public class ExpressionEvaluator{
    public static void main(String[] args) {
        String expression = "3 * (4 + 2) / 2"; // Example expression
        double result = expressionEvaluator(expression);
        System.out.println("Result of expression '" + expression + "' is: " + result);
    }
    public static double expressionEvaluator(String expression){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            Object result = engine.eval(expression);
            if (result instanceof Integer) {
                return (Integer) result;
            } else if (result instanceof Double) {
                return (Double) result;
            } else {
                throw new IllegalStateException("Unsupported result type: " + result.getClass());
            }
        } catch (ScriptException e) {
            System.err.println("Error evaluating expression: " + e.getMessage());
            return Double.NaN; // Return NaN if evaluation fails
        }
    }

}
// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math;

import com.otnip.math.algebraicscripting.operators.OperatorNode_Subtract;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Add;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Divide;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Multiply;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Log;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Floor;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Ceil;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Sqrt;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Tan;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Cos;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Sin;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Abs;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Pow;
import java.util.StringTokenizer;
import java.lang.reflect.Constructor;
import com.otnip.math.algebraicscripting.LeafNode;
import com.otnip.math.algebraicscripting.operators.OperatorNode_Constant;
import com.otnip.math.algebraicscripting.OperatorNode;
import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import com.otnip.math.algebraicscripting.ScriptingNode;
import java.util.HashMap;
import java.util.ArrayList;

public class AlgebraicScripting
{
    private static final ArrayList<String> operatorStrings;
    private static final ArrayList<Class> operatorClasses;
    private static final HashMap<String, double[]> constants;
    private HashMap<String, ScriptingNode> operatorTable;
    private HashMap<String, double[]> dataTable;
    private int operatorNodeId;
    
    public static void insertOperator(final String operator, final Class operatorClass, int insertionIndex) {
        if (insertionIndex == -1 || insertionIndex > AlgebraicScripting.operatorStrings.size()) {
            insertionIndex = AlgebraicScripting.operatorStrings.size();
        }
        AlgebraicScripting.operatorStrings.add(insertionIndex, operator);
        AlgebraicScripting.operatorClasses.add(insertionIndex, operatorClass);
    }
    
    public static void removeOperator(final String operator) {
        final int index = AlgebraicScripting.operatorStrings.indexOf(operator);
        if (index != -1) {
            AlgebraicScripting.operatorStrings.remove(index);
            AlgebraicScripting.operatorClasses.remove(index);
        }
    }
    
    public static ArrayList<String> getOperators() {
        return new ArrayList<String>(AlgebraicScripting.operatorStrings);
    }
    
    public static void addConstant(final String name, final double value) {
        AlgebraicScripting.constants.put(name, new double[] { value });
    }
    
    public static void addConstant(final String name, final double[] value) {
        AlgebraicScripting.constants.put(name, value);
    }
    
    public static void removeConstant(final String name) {
        AlgebraicScripting.constants.remove(name);
    }
    
    public static HashMap<String, double[]> getConstants() {
        return new HashMap<String, double[]>(AlgebraicScripting.constants);
    }
    
    public AlgebraicScripting(final Object... args) {
        this.operatorTable = new HashMap<String, ScriptingNode>();
        this.operatorNodeId = 0;
        this.dataTable = new HashMap<String, double[]>(AlgebraicScripting.constants);
        for (int i = 0; i < args.length; ++i) {
            final String name = (String)args[i++];
            final double[] data = (double[])args[i];
            this.dataTable.put(name, data);
        }
    }
    
    private boolean containsOperator(final String line) {
        boolean result = false;
        for (final String operator : AlgebraicScripting.operatorStrings) {
            result |= line.contains(operator);
        }
        return result;
    }
    
    private OperatorNode parseNextOperator(final String line) throws Exception {
        try {
            return new OperatorNode_Constant(Double.parseDouble(line));
        }
        catch (Exception ex) {
            if (this.dataTable.containsKey(line)) {
                return new OperatorNode_Constant(this.dataTable.get(line)[0]);
            }
            final int beginIndex = line.lastIndexOf(40);
            int endIndex = -1;
            if (beginIndex != -1) {
                endIndex = line.indexOf(41, beginIndex);
                final String inside = line.substring(beginIndex + 1, endIndex);
                final OperatorNode node = this.parseNextOperator(inside);
                final String nodeName = "##NODE" + Integer.toString(this.operatorNodeId++) + "##";
                this.operatorTable.put(nodeName, node);
                String s = line.substring(0, beginIndex) + nodeName;
                if (endIndex + 1 < line.length()) {
                    s += line.substring(endIndex + 1, line.length());
                }
                if (!this.containsOperator(s)) {
                    return node;
                }
                return this.parseNextOperator(s);
            }
            else {
                String targetOperator = null;
                for (final String operator : AlgebraicScripting.operatorStrings) {
                    if (targetOperator == null && line.contains(operator)) {
                        targetOperator = operator;
                        break;
                    }
                }
                final int centerIndex = line.indexOf(targetOperator);
                String operandA = "";
                String operandB = "";
                int leftIndex = centerIndex;
                if (isBinaryOperator(targetOperator)) {
                    if (centerIndex != 0) {
                        for (leftIndex = centerIndex - 1; leftIndex >= 0 && !AlgebraicScripting.operatorStrings.contains(Character.toString(line.charAt(leftIndex))); --leftIndex) {}
                        operandA = line.substring(leftIndex + 1, centerIndex);
                    }
                    else if (targetOperator.equals("-")) {
                        operandA = "0";
                    }
                }
                else if (centerIndex != 0) {
                    leftIndex = centerIndex - 1;
                }
                int rightIndex;
                for (rightIndex = centerIndex + targetOperator.length(); rightIndex < line.length() && !AlgebraicScripting.operatorStrings.contains(Character.toString(line.charAt(rightIndex))); ++rightIndex) {
                    operandB += line.charAt(rightIndex);
                }
                final OperatorNode operatorNode = this.createNode(operandA.trim(), targetOperator, operandB.trim());
                final String nodeName2 = "##NODE" + Integer.toString(this.operatorNodeId++) + "##";
                this.operatorTable.put(nodeName2, operatorNode);
                int newLeftIndex = leftIndex + 1;
                if (leftIndex == 0) {
                    newLeftIndex = leftIndex;
                }
                String s2 = line.substring(0, newLeftIndex) + nodeName2 + line.substring(rightIndex, line.length());
                if (line.charAt(0) == '-' && !targetOperator.equals("-")) {
                    s2 = "-" + s2;
                }
                if (this.containsOperator(s2)) {
                    return this.parseNextOperator(s2);
                }
                return operatorNode;
            }
        }
    }
    
    private static final boolean isBinaryOperator(final String operator) {
        boolean result = false;
        try {
            AlgebraicScripting.operatorClasses.get(AlgebraicScripting.operatorStrings.indexOf(operator)).getConstructor(ScriptingNode.class, ScriptingNode.class);
            result = true;
        }
        catch (Exception ex) {}
        return result;
    }
    
    private OperatorNode createNode(final String operandA, final String operator, final String operandB) throws Exception {
        ScriptingNode a = null;
        ScriptingNode b = null;
        OperatorNode operatorNode = null;
        if (isBinaryOperator(operator)) {
            if (this.operatorTable.get(operandA) != null) {
                a = this.operatorTable.get(operandA);
            }
            else if (this.dataTable.get(operandA) != null) {
                a = new LeafNode(this.dataTable.get(operandA));
            }
            else if (AlgebraicScripting.operatorStrings.contains(operandA)) {
                a = AlgebraicScripting.operatorClasses.get(AlgebraicScripting.operatorStrings.indexOf(operandA)).newInstance();
            }
            else {
                a = new LeafNode(Double.parseDouble(operandA));
            }
        }
        if (operandB == null) {
            b = new LeafNode();
        }
        else if (this.operatorTable.get(operandB) != null) {
            b = this.operatorTable.get(operandB);
        }
        else if (this.dataTable.get(operandB) != null) {
            b = new LeafNode(this.dataTable.get(operandB));
        }
        else if (AlgebraicScripting.operatorStrings.contains(operandB)) {
            b = AlgebraicScripting.operatorClasses.get(AlgebraicScripting.operatorStrings.indexOf(operandB)).newInstance();
        }
        else {
            b = new LeafNode(Double.parseDouble(operandB));
        }
        final Class<OperatorNode> operatorNodeClass = AlgebraicScripting.operatorClasses.get(AlgebraicScripting.operatorStrings.indexOf(operator));
        if (operatorNodeClass != null) {
            if (isBinaryOperator(operator)) {
                final Constructor<OperatorNode> constructor = operatorNodeClass.getConstructor(ScriptingNode.class, ScriptingNode.class);
                operatorNode = constructor.newInstance(a, b);
            }
            else {
                final Constructor<OperatorNode> constructor = operatorNodeClass.getConstructor(ScriptingNode.class);
                operatorNode = constructor.newInstance(b);
            }
            return operatorNode;
        }
        throw new Exception("Don't know how to handle operator:  " + operator);
    }
    
    private void evaluateLine(final String line) throws Exception {
        final StringTokenizer str = new StringTokenizer(line, "=");
        final String leftSide = str.nextToken().replaceAll(" ", "");
        String rightSide = str.nextToken().replaceAll(" ", "");
        rightSide = preParse(rightSide, 0);
        final OperatorNode node = this.parseNextOperator(rightSide);
        final LeafNode leafNode = node.getLeafNode();
        double[] result = null;
        switch (leafNode.getNodeType()) {
            case SCALAR: {
                result = new double[] { leafNode.getScalar() };
                break;
            }
            case VECTOR: {
                result = leafNode.getVector();
                break;
            }
            case NULL: {
                result = new double[0];
                break;
            }
        }
        this.dataTable.put(leftSide, result);
    }
    
    private static final String preParse(String text, final int minIndex) {
        final int index = text.indexOf("e-", minIndex);
        if (index != -1) {
            int rightIndex;
            for (rightIndex = index + 2; rightIndex < text.length() && Character.isDigit(text.charAt(rightIndex)); ++rightIndex) {}
            int leftIndex;
            for (leftIndex = index - 1; leftIndex >= 0 && (Character.isDigit(text.charAt(leftIndex)) || text.charAt(leftIndex) == '.'); --leftIndex) {}
            if (++leftIndex != index && rightIndex - 2 != index) {
                text = text.substring(0, leftIndex) + "(" + text.substring(leftIndex, rightIndex) + ")" + text.substring(rightIndex, text.length());
            }
            text = preParse(text, rightIndex);
        }
        return text;
    }
    
    public void evaluate(String script) throws Exception {
        script = script.trim();
        final StringTokenizer str = new StringTokenizer(script, "\n");
        while (str.hasMoreTokens()) {
            this.evaluateLine(str.nextToken());
        }
    }
    
    public double[] get(final String data) {
        return this.dataTable.get(data);
    }
    
    public String toString() {
        final String s = "";
        for (final String key : this.operatorTable.keySet()) {
            final ScriptingNode node = this.operatorTable.get(key);
        }
        for (final String key : this.dataTable.keySet()) {
            final double[] node2 = this.dataTable.get(key);
        }
        return s;
    }
    
    public static void main(final String[] args) {
        try {
            final String text = "PI = 3.14159265358979323846\nPI_2 = PI / 2\nSPD_LIGHT = 2.99792458e8\nMU_0 = 4e-7*PI\nEPSILON_0 = 1/( MU_0 * SPD_LIGHT**2)";
            addConstant("math.pi", 3.141592653589793);
            final AlgebraicScripting script = new AlgebraicScripting(new Object[0]);
            script.evaluate(text);
            System.out.println(script.get("SPD_LIGHT")[0]);
            System.out.println(script.get("MU_0")[0]);
            System.out.println(script.get("EPSILON_0")[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        operatorStrings = new ArrayList<String>();
        operatorClasses = new ArrayList<Class>();
        constants = new HashMap<String, double[]>();
        insertOperator("^", OperatorNode_Pow.class, -1);
        insertOperator("**", OperatorNode_Pow.class, -1);
        insertOperator("abs", OperatorNode_Abs.class, -1);
        insertOperator("sin", OperatorNode_Sin.class, -1);
        insertOperator("cos", OperatorNode_Cos.class, -1);
        insertOperator("tan", OperatorNode_Tan.class, -1);
        insertOperator("sqrt", OperatorNode_Sqrt.class, -1);
        insertOperator("ceil", OperatorNode_Ceil.class, -1);
        insertOperator("floor", OperatorNode_Floor.class, -1);
        insertOperator("ln", OperatorNode_Log.class, -1);
        insertOperator("*", OperatorNode_Multiply.class, -1);
        insertOperator("/", OperatorNode_Divide.class, -1);
        insertOperator("+", OperatorNode_Add.class, -1);
        insertOperator("-", OperatorNode_Subtract.class, -1);
    }
}

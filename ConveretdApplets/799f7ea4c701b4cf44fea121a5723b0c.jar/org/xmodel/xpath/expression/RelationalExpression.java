// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.function.BooleanFunction;
import org.xmodel.xpath.function.NumberFunction;
import org.xmodel.xpath.function.StringFunction;
import org.xmodel.IModelObject;
import java.util.List;

public class RelationalExpression extends M
{
    Operator \u00d8;
    private static /* synthetic */ int[] \u00d9;
    
    public RelationalExpression(final Operator \u00f8) {
        this.\u00d8 = \u00f8;
    }
    
    public RelationalExpression(final Operator \u00f8, final IExpression expression, final IExpression expression2) {
        this.\u00d8 = \u00f8;
        this.addArgument(expression);
        this.addArgument(expression2);
    }
    
    @Override
    public String getName() {
        return "relational";
    }
    
    public boolean B(final IContext context, final IExpression expression, final IExpression expression2) throws ExpressionException {
        final IExpression.ResultType type = expression.getType(context);
        final IExpression.ResultType type2 = expression2.getType(context);
        if (type == IExpression.ResultType.NODES) {
            if (type2 == IExpression.ResultType.BOOLEAN) {
                return this.A(expression.evaluateNodes(context), expression2.evaluateBoolean(context));
            }
            if (type2 == IExpression.ResultType.NODES) {
                return this.B(expression.evaluateNodes(context), expression2.evaluateNodes(context));
            }
            if (type2 == IExpression.ResultType.NUMBER) {
                return this.B(expression.evaluateNodes(context), expression2.evaluateNumber(context));
            }
            return this.C(expression.evaluateNodes(context), expression2.evaluateString(context));
        }
        else if (type2 == IExpression.ResultType.NODES) {
            if (type == IExpression.ResultType.BOOLEAN) {
                return this.A(expression2.evaluateNodes(context), expression.evaluateBoolean(context));
            }
            if (type == IExpression.ResultType.NODES) {
                return this.B(expression.evaluateNodes(context), expression2.evaluateNodes(context));
            }
            if (type == IExpression.ResultType.NUMBER) {
                return this.A(expression.evaluateNumber(context), expression2.evaluateNodes(context));
            }
            return this.A(expression.evaluateString(context), expression2.evaluateNodes(context));
        }
        else {
            if (type == IExpression.ResultType.BOOLEAN || type2 == IExpression.ResultType.BOOLEAN) {
                return this.B(expression.evaluateBoolean(context), expression2.evaluateBoolean(context));
            }
            if (type == IExpression.ResultType.NUMBER || type2 == IExpression.ResultType.NUMBER) {
                return this.B(expression.evaluateNumber(context), expression2.evaluateNumber(context));
            }
            return this.D(expression.evaluateString(context), expression2.evaluateString(context));
        }
    }
    
    private boolean B(final List<IModelObject> list, final List<IModelObject> list2) {
        for (int i = 0; i < list.size(); ++i) {
            final String stringValue = StringFunction.stringValue(list.get(i));
            for (int j = 0; j < list2.size(); ++j) {
                final String stringValue2 = StringFunction.stringValue(list2.get(i));
                switch (O()[this.\u00d8.ordinal()]) {
                    case 1: {
                        if (stringValue.compareTo(stringValue2) > 0) {
                            return true;
                        }
                        break;
                    }
                    case 2: {
                        if (stringValue.compareTo(stringValue2) >= 0) {
                            return true;
                        }
                        break;
                    }
                    case 3: {
                        if (stringValue.compareTo(stringValue2) < 0) {
                            return true;
                        }
                        break;
                    }
                    case 4: {
                        if (stringValue.compareTo(stringValue2) <= 0) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean B(final List<IModelObject> list, final double n) {
        for (int i = 0; i < list.size(); ++i) {
            final double numericValue = NumberFunction.numericValue(StringFunction.stringValue(list.get(i)));
            switch (O()[this.\u00d8.ordinal()]) {
                case 1: {
                    if (numericValue > n) {
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (numericValue >= n) {
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (numericValue < n) {
                        return true;
                    }
                    break;
                }
                case 4: {
                    if (numericValue <= n) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean C(final List<IModelObject> list, final String s) {
        for (int i = 0; i < list.size(); ++i) {
            final String stringValue = StringFunction.stringValue(list.get(i));
            switch (O()[this.\u00d8.ordinal()]) {
                case 1: {
                    if (stringValue.compareTo(s) > 0) {
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (stringValue.compareTo(s) >= 0) {
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (stringValue.compareTo(s) < 0) {
                        return true;
                    }
                    break;
                }
                case 4: {
                    if (stringValue.compareTo(s) <= 0) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean A(final List<IModelObject> list, final boolean b) {
        final boolean booleanValue = BooleanFunction.booleanValue(list);
        switch (O()[this.\u00d8.ordinal()]) {
            case 1: {
                return booleanValue && !b;
            }
            case 2: {
                return (booleanValue && !b) || booleanValue == b;
            }
            case 3: {
                return !booleanValue && b;
            }
            case 4: {
                return (!booleanValue && b) || booleanValue == b;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean A(final double n, final List<IModelObject> list) {
        for (int i = 0; i < list.size(); ++i) {
            final double numericValue = NumberFunction.numericValue(StringFunction.stringValue(list.get(i)));
            switch (O()[this.\u00d8.ordinal()]) {
                case 1: {
                    if (n > numericValue) {
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (n >= numericValue) {
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (n < numericValue) {
                        return true;
                    }
                    break;
                }
                case 4: {
                    if (n <= numericValue) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean A(final String s, final List<IModelObject> list) {
        for (int i = 0; i < list.size(); ++i) {
            final String stringValue = StringFunction.stringValue(list.get(i));
            switch (O()[this.\u00d8.ordinal()]) {
                case 1: {
                    if (s.compareTo(stringValue) > 0) {
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (s.compareTo(stringValue) >= 0) {
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (s.compareTo(stringValue) < 0) {
                        return true;
                    }
                    break;
                }
                case 4: {
                    if (s.compareTo(stringValue) <= 0) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean B(final boolean b, final boolean b2) {
        switch (O()[this.\u00d8.ordinal()]) {
            case 1: {
                return b && !b2;
            }
            case 2: {
                return (b && !b2) || b == b2;
            }
            case 3: {
                return !b && b2;
            }
            case 4: {
                return (!b && b2) || b == b2;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean B(final double n, final double n2) {
        switch (O()[this.\u00d8.ordinal()]) {
            case 1: {
                return n > n2;
            }
            case 2: {
                return n >= n2;
            }
            case 3: {
                return n < n2;
            }
            case 4: {
                return n <= n2;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean D(final String s, final String s2) {
        final double numericValue = NumberFunction.numericValue(s);
        final double numericValue2 = NumberFunction.numericValue(s2);
        switch (O()[this.\u00d8.ordinal()]) {
            case 1: {
                return numericValue > numericValue2;
            }
            case 2: {
                return numericValue >= numericValue2;
            }
            case 3: {
                return numericValue < numericValue2;
            }
            case 4: {
                return numericValue <= numericValue2;
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    protected IExpression cloneOne() {
        return new RelationalExpression(this.\u00d8);
    }
    
    @Override
    public String toString() {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        switch (O()[this.\u00d8.ordinal()]) {
            case 1: {
                return String.valueOf(argument.toString()) + " > " + argument2.toString();
            }
            case 2: {
                return String.valueOf(argument.toString()) + " >= " + argument2.toString();
            }
            case 3: {
                return String.valueOf(argument.toString()) + " < " + argument2.toString();
            }
            case 4: {
                return String.valueOf(argument.toString()) + " <= " + argument2.toString();
            }
            default: {
                return null;
            }
        }
    }
    
    static /* synthetic */ int[] O() {
        final int[] \u00f9 = RelationalExpression.\u00d9;
        if (\u00f9 != null) {
            return \u00f9;
        }
        final int[] \u00f92 = new int[Operator.values().length];
        try {
            \u00f92[Operator.GE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00f92[Operator.GT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00f92[Operator.LE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00f92[Operator.LT.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        return RelationalExpression.\u00d9 = \u00f92;
    }
    
    public enum Operator
    {
        GT("GT", 0), 
        GE("GE", 1), 
        LT("LT", 2), 
        LE("LE", 3);
        
        static {
            A = new Operator[] { Operator.GT, Operator.GE, Operator.LT, Operator.LE };
        }
        
        private Operator(final String s, final int n) {
        }
    }
}

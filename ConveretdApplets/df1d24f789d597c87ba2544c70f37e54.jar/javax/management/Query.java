// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class Query
{
    public static final int DIV = 3;
    public static final int EQ = 4;
    public static final int GE = 2;
    public static final int GT = 0;
    public static final int LE = 3;
    public static final int LT = 1;
    public static final int MINUS = 1;
    public static final int PLUS = 0;
    public static final int TIMES = 2;
    
    public static QueryExp and(final QueryExp first, final QueryExp second) {
        return new AndQueryExp(first, second);
    }
    
    public static QueryExp anySubString(final AttributeValueExp attr, final StringValueExp string) {
        return new MatchQueryExp(attr, "*" + string.getValue() + "*");
    }
    
    public static AttributeValueExp attr(final String value) {
        return new AttributeValueExp(value);
    }
    
    public static AttributeValueExp attr(final String className, final String value) {
        return new QualifiedAttributeValueExp(className, value);
    }
    
    public static QueryExp between(final ValueExp test, final ValueExp lower, final ValueExp higher) {
        return new BetweenQueryExp(test, lower, higher);
    }
    
    public static AttributeValueExp classattr() {
        return new ClassAttributeValueExp();
    }
    
    public static ValueExp div(final ValueExp first, final ValueExp second) {
        return new BinaryOpValueExp(3, first, second);
    }
    
    public static QueryExp eq(final ValueExp first, final ValueExp second) {
        return new BinaryRelQueryExp(4, first, second);
    }
    
    public static QueryExp finalSubString(final AttributeValueExp attr, final StringValueExp string) {
        return new MatchQueryExp(attr, "*" + string.getValue());
    }
    
    public static QueryExp geq(final ValueExp first, final ValueExp second) {
        return new BinaryRelQueryExp(2, first, second);
    }
    
    public static QueryExp gt(final ValueExp first, final ValueExp second) {
        return new BinaryRelQueryExp(0, first, second);
    }
    
    public static QueryExp in(final ValueExp test, final ValueExp[] list) {
        return new InQueryExp(test, list);
    }
    
    public static QueryExp initialSubString(final AttributeValueExp attr, final StringValueExp string) {
        return new MatchQueryExp(attr, string.getValue() + "*");
    }
    
    public static QueryExp leq(final ValueExp first, final ValueExp second) {
        return new BinaryRelQueryExp(3, first, second);
    }
    
    public static QueryExp lt(final ValueExp first, final ValueExp second) {
        return new BinaryRelQueryExp(1, first, second);
    }
    
    public static QueryExp match(final AttributeValueExp attr, final StringValueExp string) {
        return new MatchQueryExp(attr, string.getValue());
    }
    
    public static ValueExp minus(final ValueExp first, final ValueExp second) {
        return new BinaryOpValueExp(1, first, second);
    }
    
    public static QueryExp not(final QueryExp expression) {
        return new NotQueryExp(expression);
    }
    
    public static QueryExp or(final QueryExp first, final QueryExp second) {
        return new OrQueryExp(first, second);
    }
    
    public static ValueExp plus(final ValueExp first, final ValueExp second) {
        return new BinaryOpValueExp(0, first, second);
    }
    
    public static ValueExp times(final ValueExp first, final ValueExp second) {
        return new BinaryOpValueExp(2, first, second);
    }
    
    public static ValueExp value(final boolean value) {
        return new BooleanValueExp(new Boolean(value));
    }
    
    public static ValueExp value(final double value) {
        return new NumericValueExp(new Double(value));
    }
    
    public static ValueExp value(final float value) {
        return new NumericValueExp(new Double(value));
    }
    
    public static ValueExp value(final int value) {
        return new NumericValueExp(new Long(value));
    }
    
    public static ValueExp value(final long value) {
        return new NumericValueExp(new Long(value));
    }
    
    public static ValueExp value(final Number value) {
        return new NumericValueExp(value);
    }
    
    public static StringValueExp value(final String value) {
        return new StringValueExp(value);
    }
}

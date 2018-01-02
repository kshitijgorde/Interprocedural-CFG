// 
// Decompiled by Procyon v0.5.30
// 

public class ExprToXxx
{
    private static final String CANT_CONVERT = "Can't convert '";
    private static final String TO_NUMBER = "' to a number";
    private static final String TO_POINT = "' to a two-number sentence";
    private static final String TO_WORD = "' to a word/text";
    private static final String TO_TRUE_FALSE = "' to true or false";
    
    public static boolean exprToBoolean(final Object o) throws TTRuntimeError {
        if (o instanceof Boolean) {
            return (boolean)o;
        }
        if (o instanceof Expression) {
            return exprToBoolean(((Expression)o).getValue());
        }
        if (o instanceof Word) {
            final String string = ((Word)o).toString();
            if (string.equalsIgnoreCase("true")) {
                return true;
            }
            if (string.equalsIgnoreCase("false")) {
                return false;
            }
        }
        throw new TTRuntimeError("Can't convert '" + o + "' to true or false");
    }
    
    public static Collection exprToCollection(final Object o) throws TTRuntimeError {
        if (o instanceof Collection) {
            return (Collection)o;
        }
        if (o instanceof Expression) {
            return exprToCollection(((Expression)o).getValue());
        }
        return exprToWord(o);
    }
    
    public static Number exprToNumber(final Object o) throws TTRuntimeError {
        if (o instanceof Number) {
            return (Number)o;
        }
        if (o instanceof Expression) {
            return exprToNumber(((Expression)o).getValue());
        }
        if (o instanceof Word) {
            final String string = ((Word)o).toString();
            Integer n;
            try {
                n = new Integer(string);
            }
            catch (NumberFormatException ex) {
                Float n2;
                try {
                    n2 = new Float(string);
                }
                catch (NumberFormatException ex2) {
                    throw new TTRuntimeError("Can't convert '" + o + "' to a number");
                }
                return n2;
            }
            return n;
        }
        throw new TTRuntimeError("Can't convert '" + o + "' to a number");
    }
    
    public static Object exprToPrimitive(final Object o) throws TTRuntimeError {
        if (o instanceof Expression) {
            return exprToPrimitive(((Expression)o).getValue());
        }
        return o;
    }
    
    public static TGPoint exprToPoint(final Object o) throws TTRuntimeError {
        final Collection exprToCollection = exprToCollection(o);
        if (!(exprToCollection instanceof List)) {
            throw new TTRuntimeError("Can't convert '" + o + "' to a two-number sentence");
        }
        if (exprToCollection.count() != 2) {
            throw new TTRuntimeError("Can't convert '" + o + "' to a two-number sentence");
        }
        return new TGPoint(exprToNumber(exprToCollection.first()).floatValue(), exprToNumber(exprToCollection.last()).floatValue());
    }
    
    public static Word exprToWord(final Object o) throws TTRuntimeError {
        if (o instanceof Word) {
            return (Word)o;
        }
        if (o instanceof Expression) {
            return exprToWord(((Expression)o).getValue());
        }
        if (o instanceof Boolean) {
            if (o) {
                return new Word("true");
            }
            return new Word("false");
        }
        else {
            if (o instanceof Float) {
                return new Word(String.valueOf((float)o));
            }
            if (o instanceof Integer) {
                return new Word(String.valueOf((int)o));
            }
            throw new TTRuntimeError("Can't convert '" + o + "' to a word/text");
        }
    }
}

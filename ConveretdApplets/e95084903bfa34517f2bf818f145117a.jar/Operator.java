// 
// Decompiled by Procyon v0.5.30
// 

class Operator implements Expression
{
    public static final int NOT_OPERATOR = 0;
    private static final int OPR_ABS = 1;
    private static final int OPR_AND = 2;
    private static final int OPR_ARRAY = 3;
    private static final int OPR_ARRAY2LIST = 4;
    private static final int OPR_ARRAYP = 5;
    private static final int OPR_ASCII = 6;
    private static final int OPR_BUTFIRST = 7;
    private static final int OPR_BUTLAST = 8;
    private static final int OPR_CANVASHEIGHT = 9;
    private static final int OPR_CANVASWIDTH = 10;
    private static final int OPR_CEIL = 11;
    private static final int OPR_CHAR = 12;
    private static final int OPR_COLORUNDER = 13;
    private static final int OPR_COS = 14;
    private static final int OPR_COUNT = 15;
    private static final int OPR_DIFFERENCE = 16;
    private static final int OPR_EMPTYP = 17;
    private static final int OPR_EQUALP = 18;
    private static final int OPR_FIRST = 19;
    private static final int OPR_FLOOR = 20;
    private static final int OPR_FPUT = 21;
    private static final int OPR_GREATERP = 22;
    private static final int OPR_HEADING = 23;
    private static final int OPR_INT = 24;
    private static final int OPR_ITEM = 25;
    private static final int OPR_LAST = 26;
    private static final int OPR_LIST = 27;
    private static final int OPR_LESSP = 28;
    private static final int OPR_LIST2ARRAY = 29;
    private static final int OPR_LISTP = 30;
    private static final int OPR_LPUT = 31;
    private static final int OPR_MEMBERP = 32;
    private static final int OPR_MINUS = 33;
    private static final int OPR_MOUSEX = 34;
    private static final int OPR_MOUSEY = 35;
    private static final int OPR_NOT = 36;
    private static final int OPR_NUMBERP = 37;
    private static final int OPR_OR = 38;
    private static final int OPR_PENCOLOR = 39;
    private static final int OPR_PENDOWNP = 40;
    private static final int OPR_PENSIZE = 41;
    private static final int OPR_POS = 42;
    private static final int OPR_POWER = 43;
    private static final int OPR_PRODUCT = 44;
    private static final int OPR_QUOTIENT = 45;
    private static final int OPR_RANDOM = 46;
    private static final int OPR_REMAINDER = 47;
    private static final int OPR_REPCOUNT = 48;
    private static final int OPR_ROUND = 49;
    private static final int OPR_SENTENCE = 50;
    private static final int OPR_SIN = 51;
    private static final int OPR_SQRT = 52;
    private static final int OPR_SUM = 53;
    private static final int OPR_TAN = 54;
    private static final int OPR_THING = 55;
    private static final int OPR_TOWARDS = 56;
    private static final int OPR_WHO = 57;
    private static final int OPR_WORD = 58;
    private static final int OPR_WORDP = 59;
    private static final int OPR_XCOR = 60;
    private static final int OPR_YCOR = 61;
    private static final String[] itemTypes;
    private static final String[] memberTypes;
    private static final String[] oneBoolean;
    private static final String[] oneCollection;
    private static final String[] oneList;
    private static final String[] oneNumber;
    private static final String[] oneObject;
    private static final String[] oneWord;
    private static final String[] twoBooleans;
    private static final String[] twoCollections;
    private static final String[] twoNumbers;
    private static final String[] twoObjects;
    private static final String CLASS_NAME = "Operator";
    private static final String AS_INPUT = " as input";
    private static final String BAD_OPR = " bad oprIdNum (";
    private static final String DOESNT_LIKE = " doesn't like ";
    private static final String ELEM = " elements";
    private static final String EXPECT_BOOL = "Expecting boolean value, not ";
    private static final String MORE_THAN = "An array must have more than ";
    private int operation;
    private Object args;
    
    public Operator(final int operation, final Object args) {
        this.operation = operation;
        this.args = args;
    }
    
    private Number doFloatMath(final float n, final float n2) {
        float n3 = 0.0f;
        switch (this.operation) {
            case 16: {
                n3 = n - n2;
                break;
            }
            case 44: {
                n3 = n * n2;
                break;
            }
            case 45: {
                n3 = n / n2;
                break;
            }
            case 47: {
                return new Integer((int)n % (int)n2);
            }
            case 53: {
                n3 = n + n2;
                break;
            }
            default: {
                System.err.print("Operator.doFloatMath: bad oprIdNum (");
                System.err.println(this.operation + ")");
                return new Float(0.0f);
            }
        }
        if (n3 % 1.0f == 0.0f) {
            return new Integer((int)n3);
        }
        return new Float(n3);
    }
    
    private Integer doIntMath(final int n, final int n2) {
        switch (this.operation) {
            case 16: {
                return new Integer(n - n2);
            }
            case 44: {
                return new Integer(n * n2);
            }
            case 45: {
                return new Integer(n / n2);
            }
            case 47: {
                return new Integer(n % n2);
            }
            case 53: {
                return new Integer(n + n2);
            }
            default: {
                System.err.println("Operator.doIntMath: bad oprIdNum (" + this.operation + ")");
                return new Integer(0);
            }
        }
    }
    
    private Object getBooleanValue() throws TTRuntimeError {
        switch (this.operation) {
            case 2:
            case 38: {
                final boolean exprToBoolean = ExprToXxx.exprToBoolean(((Object[])this.args)[0]);
                final boolean exprToBoolean2 = ExprToXxx.exprToBoolean(((Object[])this.args)[1]);
                if (this.operation == 2) {
                    return new Boolean(exprToBoolean & exprToBoolean2);
                }
                return new Boolean(exprToBoolean | exprToBoolean2);
            }
            case 5: {
                if (ExprToXxx.exprToPrimitive(this.args) instanceof Array) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            case 17: {
                return ExprToXxx.exprToCollection(this.args).emptyp();
            }
            case 22:
            case 28: {
                final Number exprToNumber = ExprToXxx.exprToNumber(((Object[])this.args)[0]);
                final Number exprToNumber2 = ExprToXxx.exprToNumber(((Object[])this.args)[1]);
                final float floatValue = exprToNumber.floatValue();
                final float floatValue2 = exprToNumber2.floatValue();
                if (this.operation == 22) {
                    return new Boolean(floatValue > floatValue2);
                }
                return new Boolean(floatValue < floatValue2);
            }
            case 18: {
                final Object exprToPrimitive = ExprToXxx.exprToPrimitive(((Object[])this.args)[0]);
                if (exprToPrimitive instanceof Array) {
                    throw new TTRuntimeError("equal? doesn't like " + exprToPrimitive + " as input");
                }
                final Object exprToPrimitive2 = ExprToXxx.exprToPrimitive(((Object[])this.args)[1]);
                if (exprToPrimitive2 instanceof Array) {
                    throw new TTRuntimeError("equal? doesn't like " + exprToPrimitive2 + " as input");
                }
                if (exprToPrimitive instanceof Number && exprToPrimitive2 instanceof Number) {
                    return new Boolean(((Number)exprToPrimitive).floatValue() == ((Number)exprToPrimitive2).floatValue());
                }
                if (!(exprToPrimitive instanceof List)) {
                    String s;
                    if (exprToPrimitive instanceof Word) {
                        s = ((Word)exprToPrimitive).toString();
                    }
                    else {
                        s = exprToPrimitive.toString();
                    }
                    String s2;
                    if (exprToPrimitive2 instanceof Word) {
                        s2 = ((Word)exprToPrimitive2).toString();
                    }
                    else {
                        s2 = exprToPrimitive2.toString();
                    }
                    return new Boolean(s.equalsIgnoreCase(s2));
                }
                if (!(exprToPrimitive2 instanceof List)) {
                    return new Boolean(false);
                }
                return ((List)exprToPrimitive).equalp((List)exprToPrimitive2);
            }
            case 30: {
                if (ExprToXxx.exprToPrimitive(this.args) instanceof List) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            case 32: {
                return ExprToXxx.exprToCollection(((Object[])this.args)[1]).memberp(ExprToXxx.exprToWord(((Object[])this.args)[0]));
            }
            case 36: {
                return new Boolean(!ExprToXxx.exprToBoolean(this.args));
            }
            case 37: {
                try {
                    ExprToXxx.exprToNumber(this.args);
                }
                catch (TTRuntimeError ttRuntimeError) {
                    return new Boolean(false);
                }
                return new Boolean(true);
            }
            case 40: {
                final Turtle turtle = TGDriver.getTurtle();
                if (turtle == null) {
                    return new Boolean(false);
                }
                return new Boolean(turtle.ispendown());
            }
            case 59: {
                final Object exprToPrimitive3 = ExprToXxx.exprToPrimitive(this.args);
                if (exprToPrimitive3 instanceof Boolean || exprToPrimitive3 instanceof Number || exprToPrimitive3 instanceof Word) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            default: {
                System.err.println("Operator.getBooleanValue: bad oprIdNum (" + this.operation + ")");
                return null;
            }
        }
    }
    
    private Object getCollectionValue() throws TTRuntimeError {
        switch (this.operation) {
            case 3: {
                final int intValue = ExprToXxx.exprToNumber(this.args).intValue();
                if (intValue <= 0) {
                    final StringBuffer sb = new StringBuffer("An array must have more than ");
                    sb.append(intValue);
                    sb.append(" elements");
                    throw new TTRuntimeError(sb.toString());
                }
                return new Array(intValue);
            }
            case 7: {
                return ExprToXxx.exprToCollection(this.args).butFirst();
            }
            case 8: {
                return ExprToXxx.exprToCollection(this.args).butLast();
            }
            case 19: {
                return ExprToXxx.exprToCollection(this.args).first();
            }
            case 25: {
                final int intValue2 = ExprToXxx.exprToNumber(((Object[])this.args)[0]).intValue();
                if (intValue2 <= 0) {
                    throw new TTRuntimeError("item doesn't like " + intValue2 + " as input");
                }
                final Collection exprToCollection = ExprToXxx.exprToCollection(((Object[])this.args)[1]);
                final Object item = exprToCollection.item(intValue2);
                if (item == null) {
                    throw new TTRuntimeError("item " + intValue2 + " not in " + exprToCollection);
                }
                return item;
            }
            case 26: {
                return ExprToXxx.exprToCollection(this.args).last();
            }
            case 29: {
                final Collection exprToCollection2 = ExprToXxx.exprToCollection(this.args);
                if (exprToCollection2 instanceof List) {
                    return ((List)exprToCollection2).toArray();
                }
                throw new TTRuntimeError("LISTTOARRAY doesn't like " + this.args + " as input");
            }
            case 42: {
                float xcor;
                float ycor;
                if (TG.getTGC() == null) {
                    xcor = 0.0f;
                    ycor = 0.0f;
                }
                else {
                    final Turtle turtle = TGDriver.getTurtle();
                    xcor = turtle.xcor();
                    ycor = turtle.ycor();
                }
                Word word;
                if (xcor % 1.0f == 0.0f) {
                    word = new Word(String.valueOf((int)xcor));
                }
                else {
                    word = new Word(String.valueOf(xcor));
                }
                final List list = new List(word);
                Word word2;
                if (ycor % 1.0f == 0.0f) {
                    word2 = new Word(String.valueOf((int)ycor));
                }
                else {
                    word2 = new Word(String.valueOf(ycor));
                }
                list.append(word2);
                return list;
            }
            case 50: {
                return List.sentence(new Object[] { ExprToXxx.exprToCollection(((Object[])this.args)[0]), ExprToXxx.exprToCollection(((Object[])this.args)[1]) });
            }
            default: {
                System.err.print("Operator.getCollectionValue: bad oprIdNum (");
                System.err.println(this.operation + ")");
                return null;
            }
        }
    }
    
    private Number getDegreesTwds(final float n, final float n2, final float n3, final float n4) {
        float n5 = 0.0f;
        if (n != n3 || n2 != n4) {
            float n6;
            if (n == n3) {
                n6 = ((n2 < n4) ? 90.0f : 270.0f);
            }
            else {
                n6 = (float)(Math.atan((n4 - n2) / (n3 - n)) / 0.017453292519943295);
                if (n > n3) {
                    n6 = (n6 + 180.0f) % 360.0f;
                }
            }
            n5 = -(n6 - 90.0f);
            if (n5 < 0.0f) {
                n5 += 360.0f;
            }
        }
        if (n5 % 1.0f == 0.0f) {
            return new Integer((int)n5);
        }
        return new Float(n5);
    }
    
    private Number getNumberValue() throws TTRuntimeError {
        Label_0573: {
            switch (this.operation) {
                case 9:
                case 10:
                case 13:
                case 23:
                case 34:
                case 35:
                case 39:
                case 41:
                case 60:
                case 61: {
                    final TGCanvas tgc = TG.getTGC();
                    if (tgc == null) {
                        return new Integer(0);
                    }
                    final Turtle turtle = TGDriver.getTurtle();
                    if (turtle == null) {
                        return new Integer(0);
                    }
                    switch (this.operation) {
                        case 9: {
                            return new Integer(tgc.canvasHeight());
                        }
                        case 10: {
                            return new Integer(tgc.canvasWidth());
                        }
                        case 13: {
                            return new Integer(turtle.colorunder());
                        }
                        case 23: {
                            final float n = turtle.heading();
                            if (n % 1.0f == 0.0f) {
                                return new Integer((int)n);
                            }
                            return new Float(n);
                        }
                        case 34: {
                            return new Integer(tgc.mousex());
                        }
                        case 35: {
                            return new Integer(tgc.mousey());
                        }
                        case 39: {
                            return new Integer(turtle.pencolor());
                        }
                        case 41: {
                            return new Integer(turtle.pensize());
                        }
                        case 60: {
                            final float xcor = turtle.xcor();
                            if (xcor % 1.0f == 0.0f) {
                                return new Integer((int)xcor);
                            }
                            return new Float(xcor);
                        }
                        case 61: {
                            final float ycor = turtle.ycor();
                            if (ycor % 1.0f == 0.0f) {
                                return new Integer((int)ycor);
                            }
                            return new Float(ycor);
                        }
                        default: {
                            break Label_0573;
                        }
                    }
                    break;
                }
                case 1: {
                    final Number exprToNumber = ExprToXxx.exprToNumber(this.args);
                    if (exprToNumber instanceof Float) {
                        return new Float(Math.abs(exprToNumber.floatValue()));
                    }
                    return new Integer(Math.abs(exprToNumber.intValue()));
                }
                case 6: {
                    final Word exprToWord = ExprToXxx.exprToWord(this.args);
                    if (exprToWord instanceof Word && exprToWord.count() == 1) {
                        return new Integer(exprToWord.toString().charAt(0));
                    }
                    throw new TTRuntimeError("ascii doesn't like " + exprToWord + " as input");
                }
                case 11: {
                    return new Integer((int)Math.ceil(ExprToXxx.exprToNumber(this.args).doubleValue()));
                }
                case 15: {
                    return ExprToXxx.exprToCollection(this.args).count();
                }
                case 14: {
                    return new Float(Math.cos(0.017453292519943295 * ExprToXxx.exprToNumber(this.args).doubleValue()));
                }
                case 20: {
                    return new Integer((int)Math.floor(ExprToXxx.exprToNumber(this.args).doubleValue()));
                }
                case 24: {
                    return new Integer((int)ExprToXxx.exprToNumber(this.args).doubleValue());
                }
                case 33: {
                    final Number exprToNumber2 = ExprToXxx.exprToNumber(this.args);
                    if (exprToNumber2 instanceof Float) {
                        return new Float(-exprToNumber2.floatValue());
                    }
                    return new Integer(-exprToNumber2.intValue());
                }
                case 46: {
                    return new Integer((int)Math.floor(ExprToXxx.exprToNumber(this.args).doubleValue() * Math.random()));
                }
                case 48: {
                    return new Integer(((Command)this.args).getRepeatCounter());
                }
                case 49: {
                    return new Integer(Math.round(ExprToXxx.exprToNumber(this.args).floatValue()));
                }
                case 51: {
                    return new Float(Math.sin(0.017453292519943295 * ExprToXxx.exprToNumber(this.args).doubleValue()));
                }
                case 52: {
                    return new Float(Math.sqrt(ExprToXxx.exprToNumber(this.args).doubleValue()));
                }
                case 54: {
                    return new Float(Math.tan(0.017453292519943295 * ExprToXxx.exprToNumber(this.args).doubleValue()));
                }
                case 56: {
                    if (TG.getTGC() == null) {
                        throw new TTRuntimeError("Towards: No current canvas");
                    }
                    final Turtle turtle2 = TGDriver.getTurtle();
                    if (turtle2 == null) {
                        throw new TTRuntimeError("Towards: No current turtle");
                    }
                    TGPoint exprToPoint;
                    try {
                        exprToPoint = ExprToXxx.exprToPoint(this.args);
                    }
                    catch (TTRuntimeError ttRuntimeError) {
                        throw new TTRuntimeError("Towards:" + ttRuntimeError.errMsg);
                    }
                    return this.getDegreesTwds(turtle2.xcor(), turtle2.ycor(), exprToPoint.xFloatValue(), exprToPoint.yFloatValue());
                }
                case 16:
                case 44:
                case 45:
                case 47:
                case 53: {
                    final Number exprToNumber3 = ExprToXxx.exprToNumber(((Object[])this.args)[0]);
                    final Number exprToNumber4 = ExprToXxx.exprToNumber(((Object[])this.args)[1]);
                    if (exprToNumber3 instanceof Float || exprToNumber4 instanceof Float || this.opIsFloat()) {
                        return this.doFloatMath(exprToNumber3.floatValue(), exprToNumber4.floatValue());
                    }
                    return this.doIntMath(exprToNumber3.intValue(), exprToNumber4.intValue());
                }
                default: {
                    System.err.print("Operator.getNumberValue: bad oprIdNum (");
                    System.err.println(this.operation + ")");
                    return null;
                }
            }
        }
    }
    
    private Object getWordValue() throws TTRuntimeError {
        switch (this.operation) {
            case 12: {
                final int intValue = ExprToXxx.exprToNumber(this.args).intValue();
                if (intValue > 0 && intValue < 128) {
                    return new Word(new String(new char[] { (char)intValue }));
                }
                throw new TTRuntimeError("char doesn't like " + intValue + " as input");
            }
            case 57: {
                return new Word(TGDriver.getCurThreadTTI().getName());
            }
            case 58: {
                return new Word(ExprToXxx.exprToWord(((Object[])this.args)[0]), ExprToXxx.exprToWord(((Object[])this.args)[1]));
            }
            default: {
                System.err.print("Operator.getWordValue: bad oprIdNum (");
                System.err.println(this.operation + ")");
                return null;
            }
        }
    }
    
    private boolean opIsFloat() {
        switch (this.operation) {
            case 14:
            case 45:
            case 51:
            case 52:
            case 54: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private String oprIdToString() {
        switch (this.operation) {
            case 3: {
                return "array";
            }
            case 5: {
                return "array?";
            }
            case 1: {
                return "abs";
            }
            case 6: {
                return "ascii";
            }
            case 2: {
                return "and";
            }
            case 7: {
                return "bf";
            }
            case 8: {
                return "bl";
            }
            case 9: {
                return "canvasheight";
            }
            case 10: {
                return "canvaswidht";
            }
            case 11: {
                return "ceil";
            }
            case 12: {
                return "char";
            }
            case 13: {
                return "colorunder";
            }
            case 14: {
                return "cos";
            }
            case 15: {
                return "count";
            }
            case 16: {
                return "difference";
            }
            case 17: {
                return "empty?";
            }
            case 18: {
                return "equal?";
            }
            case 19: {
                return "first";
            }
            case 20: {
                return "floor";
            }
            case 22: {
                return "greater?";
            }
            case 23: {
                return "heading";
            }
            case 24: {
                return "int";
            }
            case 25: {
                return "item";
            }
            case 26: {
                return "last";
            }
            case 28: {
                return "less?";
            }
            case 29: {
                return "listtoarray";
            }
            case 30: {
                return "list?";
            }
            case 32: {
                return "member?";
            }
            case 33: {
                return "minus";
            }
            case 34: {
                return "mousex";
            }
            case 35: {
                return "mousey";
            }
            case 36: {
                return "not";
            }
            case 37: {
                return "number?";
            }
            case 38: {
                return "or";
            }
            case 39: {
                return "pencolor";
            }
            case 40: {
                return "pendown?";
            }
            case 41: {
                return "pensize";
            }
            case 42: {
                return "pos";
            }
            case 44: {
                return "product";
            }
            case 45: {
                return "quotient";
            }
            case 46: {
                return "random";
            }
            case 47: {
                return "remainder";
            }
            case 48: {
                return "repcount";
            }
            case 49: {
                return "round";
            }
            case 50: {
                return "se";
            }
            case 51: {
                return "sin";
            }
            case 52: {
                return "sqrt";
            }
            case 53: {
                return "sum";
            }
            case 54: {
                return "tan";
            }
            case 55: {
                return "thing";
            }
            case 57: {
                return "who";
            }
            case 58: {
                return "word";
            }
            case 59: {
                return "word?";
            }
            case 60: {
                return "xcor";
            }
            case 61: {
                return "ycor";
            }
            default: {
                System.err.println("Operator.oprIdToString: bad oprIdNum (" + this.operation + ")");
                return "";
            }
        }
    }
    
    public static String[] getInputTypes(final int n) {
        switch (n) {
            case 9:
            case 10:
            case 13:
            case 23:
            case 34:
            case 35:
            case 39:
            case 40:
            case 41:
            case 42:
            case 48:
            case 57:
            case 60:
            case 61: {
                return null;
            }
            case 36: {
                return Operator.oneBoolean;
            }
            case 7:
            case 8:
            case 15:
            case 17:
            case 19:
            case 26: {
                return Operator.oneCollection;
            }
            case 1:
            case 3:
            case 11:
            case 12:
            case 14:
            case 20:
            case 24:
            case 33:
            case 46:
            case 49:
            case 51:
            case 52:
            case 54: {
                return Operator.oneNumber;
            }
            case 5:
            case 30:
            case 37:
            case 55:
            case 59: {
                return Operator.oneObject;
            }
            case 29:
            case 56: {
                return Operator.oneList;
            }
            case 6: {
                return Operator.oneWord;
            }
            case 2:
            case 38: {
                return Operator.twoBooleans;
            }
            case 50:
            case 58: {
                return Operator.twoCollections;
            }
            case 16:
            case 22:
            case 28:
            case 43:
            case 44:
            case 45:
            case 47:
            case 53: {
                return Operator.twoNumbers;
            }
            case 18: {
                return Operator.twoObjects;
            }
            case 25: {
                return Operator.itemTypes;
            }
            case 32: {
                return Operator.memberTypes;
            }
            default: {
                System.err.print("Operator.getInputTypes: bad oprIdNum (" + n + ")");
                return null;
            }
        }
    }
    
    public static int getNumInputs(final int n) {
        switch (n) {
            case 9:
            case 10:
            case 13:
            case 23:
            case 34:
            case 35:
            case 39:
            case 40:
            case 41:
            case 42:
            case 48:
            case 57:
            case 60:
            case 61: {
                return 0;
            }
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 14:
            case 15:
            case 17:
            case 19:
            case 20:
            case 24:
            case 26:
            case 29:
            case 30:
            case 33:
            case 36:
            case 37:
            case 46:
            case 49:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 59: {
                return 1;
            }
            case 2:
            case 16:
            case 18:
            case 22:
            case 25:
            case 28:
            case 32:
            case 38:
            case 43:
            case 44:
            case 45:
            case 47:
            case 50:
            case 53:
            case 58: {
                return 2;
            }
            default: {
                System.err.print("Operator.getNumInputs: bad oprIdNum (" + n + ")");
                return 0;
            }
        }
    }
    
    public static int getOperatorId(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.equals("abs")) {
            return 1;
        }
        if (lowerCase.equals("and")) {
            return 2;
        }
        if (lowerCase.equals("array")) {
            return 3;
        }
        if (lowerCase.equals("arrayp") || lowerCase.equals("array?")) {
            return 5;
        }
        if (lowerCase.equals("ascii")) {
            return 6;
        }
        if (lowerCase.equals("butfirst") || lowerCase.equals("bf")) {
            return 7;
        }
        if (lowerCase.equals("butlast") || lowerCase.equals("bl")) {
            return 8;
        }
        if (lowerCase.equals("canvasheight")) {
            return 9;
        }
        if (lowerCase.equals("canvaswidth")) {
            return 10;
        }
        if (lowerCase.equals("ceil")) {
            return 11;
        }
        if (lowerCase.equals("char")) {
            return 12;
        }
        if (lowerCase.equals("colorunder")) {
            return 13;
        }
        if (lowerCase.equals("cos")) {
            return 14;
        }
        if (lowerCase.equals("count")) {
            return 15;
        }
        if (lowerCase.equals("difference")) {
            return 16;
        }
        if (lowerCase.equals("emptyp") || lowerCase.equals("empty?")) {
            return 17;
        }
        if (lowerCase.equals("equalp") || lowerCase.equals("equal?")) {
            return 18;
        }
        if (lowerCase.equals("first")) {
            return 19;
        }
        if (lowerCase.equals("floor")) {
            return 20;
        }
        if (lowerCase.equals("greaterp") || lowerCase.equals("greater?")) {
            return 22;
        }
        if (lowerCase.equals("heading")) {
            return 23;
        }
        if (lowerCase.equals("int")) {
            return 24;
        }
        if (lowerCase.equals("item")) {
            return 25;
        }
        if (lowerCase.equals("last")) {
            return 26;
        }
        if (lowerCase.equals("lessp") || lowerCase.equals("less?")) {
            return 28;
        }
        if (lowerCase.equals("listp") || lowerCase.equals("list?")) {
            return 30;
        }
        if (lowerCase.equals("listtoarray")) {
            return 29;
        }
        if (lowerCase.equals("memberp") || lowerCase.equals("member?")) {
            return 32;
        }
        if (lowerCase.equals("minus")) {
            return 33;
        }
        if (lowerCase.equals("mousex")) {
            return 34;
        }
        if (lowerCase.equals("mousey")) {
            return 35;
        }
        if (lowerCase.equals("not")) {
            return 36;
        }
        if (lowerCase.equals("numberp") || lowerCase.equals("number?")) {
            return 37;
        }
        if (lowerCase.equals("or")) {
            return 38;
        }
        if (lowerCase.equals("pencolor")) {
            return 39;
        }
        if (lowerCase.equals("pendownp") || lowerCase.equals("pendown?")) {
            return 40;
        }
        if (lowerCase.equals("pensize")) {
            return 41;
        }
        if (lowerCase.equals("pos")) {
            return 42;
        }
        if (lowerCase.equals("pow")) {
            return 43;
        }
        if (lowerCase.equals("product")) {
            return 44;
        }
        if (lowerCase.equals("quotient")) {
            return 45;
        }
        if (lowerCase.equals("random")) {
            return 46;
        }
        if (lowerCase.equals("remainder")) {
            return 47;
        }
        if (lowerCase.equals("repcount")) {
            return 48;
        }
        if (lowerCase.equals("round")) {
            return 49;
        }
        if (lowerCase.equals("sentence") || lowerCase.equals("se")) {
            return 50;
        }
        if (lowerCase.equals("sin")) {
            return 51;
        }
        if (lowerCase.equals("sqrt")) {
            return 52;
        }
        if (lowerCase.equals("sum")) {
            return 53;
        }
        if (lowerCase.equals("tan")) {
            return 54;
        }
        if (lowerCase.equals("thing")) {
            return 55;
        }
        if (lowerCase.equals("towards")) {
            return 56;
        }
        if (lowerCase.equals("who")) {
            return 57;
        }
        if (lowerCase.equals("word")) {
            return 58;
        }
        if (lowerCase.equals("wordp") || lowerCase.equals("word?")) {
            return 59;
        }
        if (lowerCase.equals("xcor")) {
            return 60;
        }
        if (lowerCase.equals("ycor")) {
            return 61;
        }
        return 0;
    }
    
    public Object getValue() throws TTRuntimeError {
        switch (this.operation) {
            case 2:
            case 5:
            case 17:
            case 18:
            case 22:
            case 28:
            case 30:
            case 32:
            case 36:
            case 37:
            case 38:
            case 40:
            case 59: {
                return this.getBooleanValue();
            }
            case 1:
            case 6:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 20:
            case 23:
            case 24:
            case 33:
            case 34:
            case 35:
            case 39:
            case 41:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 51:
            case 52:
            case 53:
            case 54:
            case 56:
            case 60:
            case 61: {
                return this.getNumberValue();
            }
            case 12:
            case 57:
            case 58: {
                return this.getWordValue();
            }
            case 3:
            case 7:
            case 8:
            case 19:
            case 25:
            case 26:
            case 29:
            case 42:
            case 50: {
                return this.getCollectionValue();
            }
            case 55: {
                final Word exprToWord = ExprToXxx.exprToWord(this.args);
                final GlobalVar globalVar = Workspace.getGlobalVar(exprToWord.toString());
                if (globalVar == null) {
                    throw new TTRuntimeError("thing doesn't like " + exprToWord + " as input");
                }
                return globalVar.thing();
            }
            default: {
                System.err.println("Operator.getValue: bad oprIdNum (" + this.operation + ")");
                return null;
            }
        }
    }
    
    public boolean isBoolean() {
        switch (this.operation) {
            case 2:
            case 17:
            case 18:
            case 22:
            case 28:
            case 32:
            case 36:
            case 38:
            case 40: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean isNumber() {
        switch (this.operation) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 19:
            case 20:
            case 23:
            case 24:
            case 25:
            case 26:
            case 33:
            case 34:
            case 35:
            case 39:
            case 41:
            case 44:
            case 45:
            case 46:
            case 47:
            case 49:
            case 51:
            case 52:
            case 53:
            case 55:
            case 58:
            case 60:
            case 61: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isRepcount(final int n) {
        return n == 48;
    }
    
    public String toString() {
        switch (this.operation) {
            case 9:
            case 10:
            case 13:
            case 23:
            case 34:
            case 35:
            case 39:
            case 40:
            case 41:
            case 42:
            case 48:
            case 57:
            case 60:
            case 61: {
                return this.oprIdToString();
            }
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 14:
            case 15:
            case 17:
            case 19:
            case 20:
            case 24:
            case 26:
            case 29:
            case 30:
            case 33:
            case 36:
            case 37:
            case 46:
            case 49:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 59: {
                return this.oprIdToString() + " " + this.args.toString();
            }
            case 2:
            case 16:
            case 18:
            case 22:
            case 25:
            case 28:
            case 32:
            case 38:
            case 44:
            case 45:
            case 47:
            case 50:
            case 53:
            case 58: {
                final StringBuffer sb = new StringBuffer(this.oprIdToString());
                sb.append(" ");
                sb.append(((Object[])this.args)[0].toString());
                sb.append(" ");
                sb.append(((Object[])this.args)[1].toString());
                return sb.toString();
            }
            default: {
                System.err.println("Operator.toString: bad oprIdNum (" + this.operation + ")");
                return "";
            }
        }
    }
    
    static {
        itemTypes = new String[] { "java.lang.Number", "Collection" };
        memberTypes = new String[] { "Word", "Collection" };
        oneBoolean = new String[] { "java.lang.Boolean" };
        oneCollection = new String[] { "Collection" };
        oneList = new String[] { "List" };
        oneNumber = new String[] { "java.lang.Number" };
        oneObject = new String[] { "java.lang.Object" };
        oneWord = new String[] { "Word" };
        twoBooleans = new String[] { "java.lang.Boolean", "java.lang.Boolean" };
        twoCollections = new String[] { "Collection", "Collection" };
        twoNumbers = new String[] { "java.lang.Number", "java.lang.Number" };
        twoObjects = new String[] { "java.lang.Object", "java.lang.Object" };
    }
}

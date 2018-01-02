// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression.parser;

public interface ExpressionParserConstants
{
    public static final int EOF = 0;
    public static final int TRUE = 6;
    public static final int FALSE = 7;
    public static final int NULL = 8;
    public static final int IF = 9;
    public static final int THEN = 10;
    public static final int ELSE = 11;
    public static final int AND = 12;
    public static final int OR = 13;
    public static final int NOT = 14;
    public static final int XOR = 15;
    public static final int INT = 16;
    public static final int DECIMAL_LITERAL = 17;
    public static final int HEX_LITERAL = 18;
    public static final int OCTAL_LITERAL = 19;
    public static final int LONG = 20;
    public static final int DOUBLE = 21;
    public static final int FLOAT = 22;
    public static final int EXPONENT = 23;
    public static final int STRING = 24;
    public static final int QUOTED = 25;
    public static final int IDENTIFIER = 26;
    public static final int LETTER = 27;
    public static final int DIGIT = 28;
    public static final int LPAREN = 29;
    public static final int RPAREN = 30;
    public static final int EQ = 31;
    public static final int GT = 32;
    public static final int LT = 33;
    public static final int LE = 34;
    public static final int GE = 35;
    public static final int NE = 36;
    public static final int ADD = 37;
    public static final int SUB = 38;
    public static final int MUL = 39;
    public static final int DIV = 40;
    public static final int POW = 41;
    public static final int MOD = 42;
    public static final int DEFAULT = 0;
    public static final String[] tokenImage = { "<EOF>", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"\\f\"", "<TRUE>", "<FALSE>", "<NULL>", "<IF>", "<THEN>", "<ELSE>", "<AND>", "<OR>", "<NOT>", "<XOR>", "<INT>", "<DECIMAL_LITERAL>", "<HEX_LITERAL>", "<OCTAL_LITERAL>", "<LONG>", "<DOUBLE>", "<FLOAT>", "<EXPONENT>", "<STRING>", "<QUOTED>", "<IDENTIFIER>", "<LETTER>", "<DIGIT>", "\"(\"", "\")\"", "<EQ>", "\">\"", "\"<\"", "\"<=\"", "\">=\"", "<NE>", "\"+\"", "\"-\"", "\"*\"", "\"/\"", "\"^\"", "\"%\"", "\",\"" };
}

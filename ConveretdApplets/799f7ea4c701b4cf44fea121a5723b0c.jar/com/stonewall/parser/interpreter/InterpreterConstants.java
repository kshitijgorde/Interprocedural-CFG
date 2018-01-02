// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter;

public interface InterpreterConstants
{
    public static final int EOF = 0;
    public static final int LBRACE = 8;
    public static final int RBRACE = 9;
    public static final int LPAREN = 10;
    public static final int RPAREN = 11;
    public static final int COMMA = 12;
    public static final int LBRACKET = 13;
    public static final int RBRACKET = 14;
    public static final int COLON = 15;
    public static final int SEMICOLON = 16;
    public static final int EQUALS = 17;
    public static final int NODEQUALS = 18;
    public static final int QMARK = 19;
    public static final int AT = 20;
    public static final int PLUS = 21;
    public static final int PCT = 22;
    public static final int OR = 23;
    public static final int DEFINED = 24;
    public static final int ESCAPED = 25;
    public static final int NUMBER = 26;
    public static final int IDENTIFIER = 27;
    public static final int STRING = 28;
    public static final int LETTER = 29;
    public static final int DIGIT = 30;
    public static final int DQS = 31;
    public static final int SQS = 32;
    public static final int OTHER = 33;
    public static final int TEXT = 34;
    public static final int ENDCOMMENT = 35;
    public static final int DEFAULT = 0;
    public static final int COMMENT = 1;
    public static final String[] tokenImage = { "<EOF>", "\" \"", "\"\\r\"", "\"\\t\"", "\"\\n\"", "\"$\"", "\"#\"", "\"//\"", "\"{\"", "\"}\"", "\"(\"", "\")\"", "\",\"", "\"[\"", "\"]\"", "\":\"", "\";\"", "\"=\"", "\":=\"", "\"?\"", "\"@\"", "\"+\"", "\"%\"", "\"|\"", "\"defined\"", "<ESCAPED>", "<NUMBER>", "<IDENTIFIER>", "<STRING>", "<LETTER>", "<DIGIT>", "<DQS>", "<SQS>", "<OTHER>", "<TEXT>", "<ENDCOMMENT>" };
}

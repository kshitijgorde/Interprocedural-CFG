// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.ast.java_signature.ParameterNode;
import org.jruby.ast.java_signature.Modifier;
import java.util.List;
import java.util.ArrayList;
import org.jruby.ast.java_signature.ArrayTypeNode;
import org.jruby.ast.java_signature.ReferenceTypeNode;
import org.jruby.ast.java_signature.PrimitiveTypeNode;
import org.jruby.ast.java_signature.TypeNode;
import org.jruby.ast.java_signature.ConstructorSignatureNode;
import org.jruby.ast.java_signature.MethodSignatureNode;
import java.io.IOException;
import org.jruby.lexer.JavaSignatureLexer;
import org.jruby.ast.java_signature.SignatureNode;
import java.io.InputStream;

public class JavaSignatureParser
{
    private static JavaSignatureParser parser;
    public static final int BOOLEAN = 257;
    public static final int BYTE = 258;
    public static final int SHORT = 259;
    public static final int INT = 260;
    public static final int LONG = 261;
    public static final int CHAR = 262;
    public static final int FLOAT = 263;
    public static final int DOUBLE = 264;
    public static final int VOID = 265;
    public static final int PUBLIC = 266;
    public static final int PROTECTED = 267;
    public static final int PRIVATE = 268;
    public static final int STATIC = 269;
    public static final int ABSTRACT = 270;
    public static final int FINAL = 271;
    public static final int NATIVE = 272;
    public static final int SYNCHRONIZED = 273;
    public static final int TRANSIENT = 274;
    public static final int VOLATILE = 275;
    public static final int STRICTFP = 276;
    public static final int IDENTIFIER = 277;
    public static final int AND = 278;
    public static final int DOT = 279;
    public static final int COMMA = 280;
    public static final int ELLIPSIS = 281;
    public static final int LPAREN = 282;
    public static final int RPAREN = 283;
    public static final int LBRACK = 284;
    public static final int RBRACK = 285;
    public static final int QUESTION = 286;
    public static final int LT = 287;
    public static final int GT = 288;
    public static final int THROWS = 289;
    public static final int EXTENDS = 290;
    public static final int SUPER = 291;
    public static final int RSHIFT = 292;
    public static final int URSHIFT = 293;
    public static final int yyErrorCode = 256;
    protected static final int yyFinal = 12;
    protected static final short[] yyLhs;
    protected static final short[] yyLen;
    protected static final short[] yyDefRed;
    protected static final short[] yyDgoto;
    protected static final short[] yySindex;
    protected static final short[] yyRindex;
    protected static final short[] yyGindex;
    protected static final short[] yyTable;
    protected static final short[] yyCheck;
    protected static final String[] yyNames;
    protected int yyMax;
    
    public static SignatureNode parse(final InputStream in) throws IOException, ParserSyntaxException {
        return (SignatureNode)JavaSignatureParser.parser.yyparse(JavaSignatureLexer.create(in));
    }
    
    public void yyerror(final String message) throws ParserSyntaxException {
        throw new ParserSyntaxException(message, new String[0]);
    }
    
    public void yyerror(final String message, final String[] expected, final String found) throws ParserSyntaxException {
        final String text = message + ", unexpected " + found + "\n";
        throw new ParserSyntaxException(text, new String[0]);
    }
    
    protected String[] yyExpecting(final int state) {
        int len = 0;
        final boolean[] ok = new boolean[JavaSignatureParser.yyNames.length];
        int n;
        if ((n = JavaSignatureParser.yySindex[state]) != 0) {
            for (int token = (n < 0) ? (-n) : 0; token < JavaSignatureParser.yyNames.length && n + token < JavaSignatureParser.yyTable.length; ++token) {
                if (JavaSignatureParser.yyCheck[n + token] == token && !ok[token] && JavaSignatureParser.yyNames[token] != null) {
                    ++len;
                    ok[token] = true;
                }
            }
        }
        if ((n = JavaSignatureParser.yyRindex[state]) != 0) {
            for (int token = (n < 0) ? (-n) : 0; token < JavaSignatureParser.yyNames.length && n + token < JavaSignatureParser.yyTable.length; ++token) {
                if (JavaSignatureParser.yyCheck[n + token] == token && !ok[token] && JavaSignatureParser.yyNames[token] != null) {
                    ++len;
                    ok[token] = true;
                }
            }
        }
        final String[] result = new String[len];
        int token = n = 0;
        while (n < len) {
            if (ok[token]) {
                result[n++] = JavaSignatureParser.yyNames[token];
            }
            ++token;
        }
        return result;
    }
    
    public Object yyparse(final JavaSignatureLexer yyLex, final Object ayydebug) throws IOException, ParserSyntaxException {
        return this.yyparse(yyLex);
    }
    
    protected Object yyDefault(final Object first) {
        return first;
    }
    
    public Object yyparse(final JavaSignatureLexer yyLex) throws IOException, ParserSyntaxException {
        if (this.yyMax <= 0) {
            this.yyMax = 256;
        }
        int yyState = 0;
        int[] yyStates = new int[this.yyMax];
        Object yyVal = null;
        Object[] yyVals = new Object[this.yyMax];
        int yyToken = -1;
        int yyErrorFlag = 0;
        int yyTop = 0;
    Label_4224_Outer:
        while (true) {
            if (yyTop >= yyStates.length) {
                final int[] i = new int[yyStates.length + this.yyMax];
                System.arraycopy(yyStates, 0, i, 0, yyStates.length);
                yyStates = i;
                final Object[] o = new Object[yyVals.length + this.yyMax];
                System.arraycopy(yyVals, 0, o, 0, yyVals.length);
                yyVals = o;
            }
            yyStates[yyTop] = yyState;
            yyVals[yyTop] = yyVal;
        Label_4224:
            while (true) {
                int yyN = 0;
            Label_0437:
                while ((yyN = JavaSignatureParser.yyDefRed[yyState]) == 0) {
                    if (yyToken < 0) {
                        final int a1 = yyLex.yylex();
                        yyToken = ((a1 == -1) ? 0 : a1);
                    }
                    if ((yyN = JavaSignatureParser.yySindex[yyState]) != 0 && (yyN += yyToken) >= 0 && yyN < JavaSignatureParser.yyTable.length && JavaSignatureParser.yyCheck[yyN] == yyToken) {
                        yyState = JavaSignatureParser.yyTable[yyN];
                        yyVal = yyLex.value();
                        yyToken = -1;
                        if (yyErrorFlag > 0) {
                            --yyErrorFlag;
                        }
                    }
                    else {
                        if ((yyN = JavaSignatureParser.yyRindex[yyState]) != 0 && (yyN += yyToken) >= 0 && yyN < JavaSignatureParser.yyTable.length && JavaSignatureParser.yyCheck[yyN] == yyToken) {
                            yyN = JavaSignatureParser.yyTable[yyN];
                            break;
                        }
                        switch (yyErrorFlag) {
                            case 0: {
                                this.yyerror("syntax error", this.yyExpecting(yyState), JavaSignatureParser.yyNames[yyToken]);
                            }
                            case 1:
                            case 2: {
                                yyErrorFlag = 3;
                                do {
                                    if ((yyN = JavaSignatureParser.yySindex[yyStates[yyTop]]) != 0) {
                                        yyN += 256;
                                        if (yyN >= 0 && yyN < JavaSignatureParser.yyTable.length && JavaSignatureParser.yyCheck[yyN] == 256) {
                                            yyState = JavaSignatureParser.yyTable[yyN];
                                            yyVal = yyLex.value();
                                            break Label_4224;
                                        }
                                        continue Label_4224_Outer;
                                    }
                                } while (--yyTop >= 0);
                                throw new ParserSyntaxException("irrecoverable syntax error", new String[0]);
                            }
                            case 3: {
                                if (yyToken == 0) {
                                    throw new ParserSyntaxException("irrecoverable syntax error at end-of-file", new String[0]);
                                }
                                yyToken = -1;
                                continue Label_4224_Outer;
                            }
                            default: {
                                break Label_0437;
                            }
                        }
                    }
                    ++yyTop;
                    continue Label_4224_Outer;
                }
                final int yyV = yyTop + 1 - JavaSignatureParser.yyLen[yyN];
                yyVal = this.yyDefault((yyV > yyTop) ? null : yyVals[yyV]);
                switch (yyN) {
                    case 1: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 2: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 4: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 5: {
                        yyVal = PrimitiveTypeNode.BYTE;
                        break;
                    }
                    case 6: {
                        yyVal = PrimitiveTypeNode.SHORT;
                        break;
                    }
                    case 7: {
                        yyVal = PrimitiveTypeNode.INT;
                        break;
                    }
                    case 8: {
                        yyVal = PrimitiveTypeNode.LONG;
                        break;
                    }
                    case 9: {
                        yyVal = PrimitiveTypeNode.CHAR;
                        break;
                    }
                    case 10: {
                        yyVal = PrimitiveTypeNode.BOOLEAN;
                        break;
                    }
                    case 11: {
                        yyVal = PrimitiveTypeNode.FLOAT;
                        break;
                    }
                    case 12: {
                        yyVal = PrimitiveTypeNode.DOUBLE;
                        break;
                    }
                    case 13: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 14: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 15: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 16: {
                        yyVal = new ReferenceTypeNode((String)yyVals[0 + yyTop]);
                        break;
                    }
                    case 17: {
                        final String genericTyping = "<" + (String)yyVals[-2 + yyTop] + "." + (String)yyVals[0 + yyTop];
                        yyVal = yyVals[-4 + yyTop];
                        ((ReferenceTypeNode)yyVals[-4 + yyTop]).setGenericsTyping(genericTyping);
                        break;
                    }
                    case 19: {
                        final String genericTyping = "<" + (String)yyVals[0 + yyTop];
                        yyVal = yyVals[-2 + yyTop];
                        ((ReferenceTypeNode)yyVals[-2 + yyTop]).setGenericsTyping(genericTyping);
                        break;
                    }
                    case 22: {
                        ((ArrayTypeNode)yyVals[0 + yyTop]).setTypeForArray((TypeNode)yyVals[-1 + yyTop]);
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 23: {
                        ((ArrayTypeNode)yyVals[0 + yyTop]).setTypeForArray(new ReferenceTypeNode((String)yyVals[-1 + yyTop]));
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 24: {
                        ((ReferenceTypeNode)yyVals[-5 + yyTop]).setGenericsTyping("<" + (String)yyVals[-3 + yyTop] + "." + (String)yyVals[-1 + yyTop]);
                        ((ArrayTypeNode)yyVals[0 + yyTop]).setTypeForArray((TypeNode)yyVals[-5 + yyTop]);
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 25: {
                        ((ReferenceTypeNode)yyVals[-3 + yyTop]).setGenericsTyping("<" + (String)yyVals[-1 + yyTop]);
                        ((ArrayTypeNode)yyVals[0 + yyTop]).setTypeForArray((TypeNode)yyVals[-3 + yyTop]);
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 26: {
                        yyVal = "?";
                        break;
                    }
                    case 27: {
                        yyVal = "? extends " + ((ReferenceTypeNode)yyVals[0 + yyTop]).getFullyTypedName();
                        break;
                    }
                    case 28: {
                        yyVal = "? super " + ((ReferenceTypeNode)yyVals[0 + yyTop]).getFullyTypedName();
                        break;
                    }
                    case 29: {
                        yyVal = "?>";
                        break;
                    }
                    case 30: {
                        yyVal = "? extends " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 31: {
                        yyVal = "? super " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 32: {
                        yyVal = "?>>";
                        break;
                    }
                    case 33: {
                        yyVal = "? extends " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 34: {
                        yyVal = "? super " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 35: {
                        yyVal = "?>>";
                        break;
                    }
                    case 36: {
                        yyVal = "? extends " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 37: {
                        yyVal = "? super " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 38: {
                        yyVal = ((ReferenceTypeNode)yyVals[-1 + yyTop]).getFullyTypedName() + ">";
                        break;
                    }
                    case 39: {
                        yyVal = ((ReferenceTypeNode)yyVals[-2 + yyTop]).getFullyTypedName() + "<" + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 40: {
                        yyVal = ((ReferenceTypeNode)yyVals[-1 + yyTop]).getFullyTypedName() + ">>";
                        break;
                    }
                    case 41: {
                        yyVal = ((ReferenceTypeNode)yyVals[-2 + yyTop]).getFullyTypedName() + "<" + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 42: {
                        yyVal = ((ReferenceTypeNode)yyVals[-1 + yyTop]).getFullyTypedName() + ">>>";
                        break;
                    }
                    case 43: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 44: {
                        yyVal = (String)yyVals[-2 + yyTop] + ", " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 46: {
                        yyVal = (String)yyVals[-2 + yyTop] + ", " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 48: {
                        yyVal = (String)yyVals[-2 + yyTop] + ", " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 50: {
                        yyVal = (String)yyVals[-2 + yyTop] + ", " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 51: {
                        yyVal = ((ReferenceTypeNode)yyVals[0 + yyTop]).getFullyTypedName();
                        break;
                    }
                    case 61: {
                        yyVal = new ArrayList();
                        ((List)yyVal).add(yyVals[0 + yyTop]);
                        break;
                    }
                    case 62: {
                        ((List)yyVals[-1 + yyTop]).add(yyVals[0 + yyTop]);
                        break;
                    }
                    case 63: {
                        yyVal = new ArrayList();
                        break;
                    }
                    case 64: {
                        yyVal = Modifier.PUBLIC;
                        break;
                    }
                    case 65: {
                        yyVal = Modifier.PROTECTED;
                        break;
                    }
                    case 66: {
                        yyVal = Modifier.PRIVATE;
                        break;
                    }
                    case 67: {
                        yyVal = Modifier.STATIC;
                        break;
                    }
                    case 68: {
                        yyVal = Modifier.ABSTRACT;
                        break;
                    }
                    case 69: {
                        yyVal = Modifier.FINAL;
                        break;
                    }
                    case 70: {
                        yyVal = Modifier.NATIVE;
                        break;
                    }
                    case 71: {
                        yyVal = Modifier.SYNCHRONIZED;
                        break;
                    }
                    case 72: {
                        yyVal = Modifier.TRANSIENT;
                        break;
                    }
                    case 73: {
                        yyVal = Modifier.VOLATILE;
                        break;
                    }
                    case 74: {
                        yyVal = Modifier.STRICTFP;
                        break;
                    }
                    case 75: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 76: {
                        yyVal = (String)yyVals[-2 + yyTop] + "." + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 77: {
                        yyVal = new ArrayTypeNode();
                        break;
                    }
                    case 78: {
                        yyVal = new ArrayTypeNode((TypeNode)yyVals[-2 + yyTop]);
                        break;
                    }
                    case 79: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 80: {
                        yyVal = new ArrayList();
                        break;
                    }
                    case 81: {
                        yyVal = new ArrayList();
                        ((List)yyVal).add(yyVals[0 + yyTop]);
                        break;
                    }
                    case 82: {
                        ((List)yyVals[-2 + yyTop]).add(yyVals[0 + yyTop]);
                        break;
                    }
                    case 83: {
                        yyVal = new MethodSignatureNode((String)yyVals[-3 + yyTop], (List<ParameterNode>)yyVals[-1 + yyTop]);
                        break;
                    }
                    case 85: {
                        yyVal = new ArrayList();
                        break;
                    }
                    case 86: {
                        final List<ParameterNode> list = new ArrayList<ParameterNode>();
                        list.add((ParameterNode)yyVals[0 + yyTop]);
                        yyVal = list;
                        break;
                    }
                    case 87: {
                        ((List)yyVals[-2 + yyTop]).add(yyVals[0 + yyTop]);
                        break;
                    }
                    case 88: {
                        yyVal = new ParameterNode((TypeNode)yyVals[-1 + yyTop], (String)yyVals[0 + yyTop]);
                        break;
                    }
                    case 89: {
                        yyVal = new ParameterNode((TypeNode)yyVals[0 + yyTop], null);
                        break;
                    }
                    case 90: {
                        yyVal = new ParameterNode((TypeNode)yyVals[-1 + yyTop], (String)yyVals[0 + yyTop], true);
                        break;
                    }
                    case 91: {
                        yyVal = new ParameterNode((TypeNode)yyVals[0 + yyTop], null, true);
                        break;
                    }
                    case 92: {
                        yyVal = new ParameterNode((TypeNode)yyVals[-2 + yyTop], (String)yyVals[0 + yyTop], false, true);
                        break;
                    }
                    case 93: {
                        yyVal = new ParameterNode((TypeNode)yyVals[-1 + yyTop], null, false, true);
                        break;
                    }
                    case 94: {
                        yyVal = new ParameterNode((TypeNode)yyVals[-2 + yyTop], (String)yyVals[0 + yyTop], true, true);
                        break;
                    }
                    case 95: {
                        yyVal = new ParameterNode((TypeNode)yyVals[-1 + yyTop], null, true, true);
                        break;
                    }
                    case 96: {
                        yyVal = yyVals[0 + yyTop];
                        break;
                    }
                    case 97: {
                        yyVals[-3 + yyTop] = new ArrayTypeNode((TypeNode)yyVals[-3 + yyTop]);
                        yyVal = yyVals[-2 + yyTop];
                        break;
                    }
                    case 98: {
                        yyVal = (String)yyVals[-2 + yyTop] + ", " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 101: {
                        yyVal = (String)yyVals[-2 + yyTop] + ", " + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 102: {
                        yyVal = (String)yyVals[-1 + yyTop] + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 103: {
                        yyVal = (String)yyVals[-1 + yyTop] + ">";
                        break;
                    }
                    case 104: {
                        yyVal = (String)yyVals[-1 + yyTop] + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 105: {
                        yyVal = " extends " + (String)yyVals[-1 + yyTop];
                        break;
                    }
                    case 106: {
                        yyVal = " extends " + ((ReferenceTypeNode)yyVals[-1 + yyTop]).getFullyTypedName() + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 108: {
                        yyVal = "";
                        break;
                    }
                    case 109: {
                        yyVal = "extends " + ((ReferenceTypeNode)yyVals[-1 + yyTop]).getFullyTypedName() + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 111: {
                        yyVal = "";
                        break;
                    }
                    case 112: {
                        yyVal = (String)yyVals[-1 + yyTop] + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 114: {
                        yyVal = (String)yyVals[-1 + yyTop] + (String)yyVals[0 + yyTop];
                        break;
                    }
                    case 115: {
                        yyVal = " & " + (String)yyVals[-1 + yyTop];
                        break;
                    }
                    case 116: {
                        yyVal = " & " + ((ReferenceTypeNode)yyVals[0 + yyTop]).getFullyTypedName();
                        break;
                    }
                    case 117: {
                        yyVal = null;
                        break;
                    }
                    case 118: {
                        yyVal = yyVals[-1 + yyTop];
                        ((ConstructorSignatureNode)yyVal).setModifiers((List<Modifier>)yyVals[-2 + yyTop]);
                        ((ConstructorSignatureNode)yyVal).setThrows((List<TypeNode>)yyVals[0 + yyTop]);
                        break;
                    }
                    case 119: {
                        yyVal = yyVals[-1 + yyTop];
                        ((ConstructorSignatureNode)yyVal).setModifiers((List<Modifier>)yyVals[-4 + yyTop]);
                        ((ConstructorSignatureNode)yyVal).setExtraTypeInfo("<" + (String)yyVals[-2 + yyTop]);
                        ((ConstructorSignatureNode)yyVal).setThrows((List<TypeNode>)yyVals[0 + yyTop]);
                        break;
                    }
                    case 120: {
                        yyVal = new ConstructorSignatureNode((String)yyVals[-3 + yyTop], (List<ParameterNode>)yyVals[-1 + yyTop]);
                        break;
                    }
                    case 121: {
                        yyVal = yyVals[-1 + yyTop];
                        ((MethodSignatureNode)yyVal).setModifiers((List<Modifier>)yyVals[-3 + yyTop]);
                        ((MethodSignatureNode)yyVal).setReturnType((TypeNode)yyVals[-2 + yyTop]);
                        ((MethodSignatureNode)yyVal).setThrows((List<TypeNode>)yyVals[0 + yyTop]);
                        break;
                    }
                    case 122: {
                        yyVal = yyVals[-1 + yyTop];
                        ((MethodSignatureNode)yyVal).setModifiers((List<Modifier>)yyVals[-5 + yyTop]);
                        ((MethodSignatureNode)yyVal).setExtraTypeInfo("<" + (String)yyVals[-3 + yyTop]);
                        ((MethodSignatureNode)yyVal).setReturnType((TypeNode)yyVals[-2 + yyTop]);
                        ((MethodSignatureNode)yyVal).setThrows((List<TypeNode>)yyVals[0 + yyTop]);
                        break;
                    }
                    case 123: {
                        yyVal = yyVals[-1 + yyTop];
                        ((MethodSignatureNode)yyVal).setModifiers((List<Modifier>)yyVals[-3 + yyTop]);
                        ((MethodSignatureNode)yyVal).setReturnType(PrimitiveTypeNode.VOID);
                        ((MethodSignatureNode)yyVal).setThrows((List<TypeNode>)yyVals[0 + yyTop]);
                        break;
                    }
                    case 124: {
                        yyVal = yyVals[-1 + yyTop];
                        ((MethodSignatureNode)yyVal).setModifiers((List<Modifier>)yyVals[-5 + yyTop]);
                        ((MethodSignatureNode)yyVal).setExtraTypeInfo("<" + (String)yyVals[-3 + yyTop]);
                        ((MethodSignatureNode)yyVal).setReturnType(PrimitiveTypeNode.VOID);
                        ((MethodSignatureNode)yyVal).setThrows((List<TypeNode>)yyVals[0 + yyTop]);
                        break;
                    }
                }
                yyTop -= JavaSignatureParser.yyLen[yyN];
                yyState = yyStates[yyTop];
                final int yyM = JavaSignatureParser.yyLhs[yyN];
                if (yyState == 0 && yyM == 0) {
                    yyState = 12;
                    if (yyToken < 0) {
                        final int a2 = yyLex.yylex();
                        yyToken = ((a2 == -1) ? 0 : a2);
                    }
                    if (yyToken == 0) {
                        return yyVal;
                    }
                    continue Label_4224;
                }
                else {
                    if ((yyN = JavaSignatureParser.yyGindex[yyM]) != 0 && (yyN += yyState) >= 0 && yyN < JavaSignatureParser.yyTable.length && JavaSignatureParser.yyCheck[yyN] == yyState) {
                        yyState = JavaSignatureParser.yyTable[yyN];
                        continue Label_4224;
                    }
                    yyState = JavaSignatureParser.yyDgoto[yyM];
                    continue Label_4224;
                }
                break;
            }
        }
    }
    
    static {
        JavaSignatureParser.parser = new JavaSignatureParser();
        yyLhs = new short[] { -1, 0, 0, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 20, 20, 22, 15, 15, 16, 16, 19, 18, 17, 17, 17, 17, 27, 27, 27, 33, 33, 33, 34, 34, 34, 35, 35, 35, 36, 36, 37, 37, 38, 29, 29, 39, 39, 40, 40, 41, 41, 28, 28, 30, 30, 31, 31, 32, 32, 7, 7, 8, 8, 9, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 21, 21, 51, 51, 10, 10, 11, 11, 1, 5, 5, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 23, 23, 44, 44, 45, 45, 42, 43, 43, 24, 24, 46, 46, 47, 49, 49, 48, 48, 26, 26, 25, 52, 4, 4, 3, 2, 2, 2, 2 };
        yyLen = new short[] { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 3, 1, 1, 2, 2, 6, 4, 1, 3, 3, 2, 3, 3, 2, 3, 3, 2, 3, 3, 2, 3, 2, 3, 2, 1, 3, 1, 3, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 3, 2, 0, 1, 3, 4, 1, 0, 1, 3, 2, 1, 3, 2, 3, 2, 4, 3, 1, 3, 3, 1, 1, 3, 2, 2, 2, 2, 3, 1, 1, 3, 1, 1, 2, 1, 2, 2, 2, 0, 3, 5, 4, 4, 6, 4, 6 };
        yyDefRed = new short[] { 0, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 0, 1, 2, 0, 0, 60, 61, 10, 5, 6, 7, 8, 9, 11, 12, 0, 75, 0, 0, 0, 0, 0, 13, 14, 4, 0, 62, 0, 0, 15, 0, 99, 100, 0, 0, 0, 118, 0, 0, 0, 0, 0, 0, 0, 0, 123, 103, 0, 104, 102, 107, 108, 0, 0, 0, 0, 0, 0, 20, 81, 0, 77, 0, 121, 0, 0, 0, 0, 0, 52, 43, 0, 45, 54, 53, 0, 76, 0, 0, 0, 86, 0, 0, 0, 105, 98, 101, 0, 119, 0, 0, 0, 78, 29, 0, 0, 0, 38, 0, 0, 0, 0, 120, 0, 96, 0, 0, 83, 0, 0, 106, 110, 109, 111, 124, 122, 82, 0, 0, 30, 0, 31, 0, 0, 0, 0, 47, 56, 55, 39, 44, 46, 0, 0, 0, 87, 92, 0, 0, 116, 0, 115, 114, 112, 0, 0, 0, 32, 0, 40, 0, 0, 94, 97, 0, 0, 33, 0, 34, 0, 0, 0, 49, 58, 57, 41, 48, 0, 0, 35, 42, 0, 0, 36, 0, 37, 50 };
        yyDgoto = new short[] { 12, 40, 13, 30, 14, 90, 91, 15, 16, 17, 48, 68, 92, 77, 93, 135, 34, 35, 151, 71, 36, 80, 42, 118, 60, 121, 122, 81, 82, 83, 84, 138, 174, 85, 139, 175, 86, 140, 176, 87, 141, 177, 43, 44, 45, 46, 61, 62, 123, 124, 18, 50, 63 };
        yySindex = new short[] { 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -104, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -268, 0, -255, -240, -218, -268, -253, 0, 0, 0, -246, 0, -202, -240, 0, -145, 0, 0, -185, -12, -169, 0, -168, -160, -240, -55, -154, -1, -160, -1, 0, 0, 33, 0, 0, 0, 0, -255, -268, -240, -268, -150, -116, 0, 0, -107, 0, -109, 0, -190, -218, -108, -102, -239, 0, 0, -99, 0, 0, 0, -214, 0, 33, -96, -70, 0, -256, -72, -260, 0, 0, 0, -240, 0, -240, -169, -55, 0, 0, 33, 33, -43, 0, -55, -169, -160, -213, 0, -1, 0, -65, -58, 0, 33, -50, 0, 0, 0, 0, 0, 0, 0, -46, -102, 0, -102, 0, -17, -33, -211, -48, 0, 0, 0, 0, 0, 0, -239, -11, -58, 0, 0, -41, 0, 0, -102, 0, 0, 0, -169, 33, 33, 0, -22, 0, -43, -160, 0, 0, -107, -211, 0, -211, 0, -142, -63, -13, 0, 0, 0, 0, 0, 33, 33, 0, 0, -22, -63, 0, -63, 0, 0 };
        yyRindex = new short[] { -95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 268, -161, 0, -152, 0, 0, 0, -250, 0, 0, 268, 0, -8, 0, 0, 0, 0, 0, 0, 0, 38, 268, 0, 0, -14, 55, -14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 268, 0, 277, 3, 0, 0, 1, 0, 0, 0, -2, 0, -186, 5, -103, 0, 0, 0, 0, 0, 0, 72, 0, 0, 0, -4, 0, -225, 0, -8, 0, 0, 0, 268, 0, 268, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 89, -177, 0, 0, 0, -171, -143, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 4, 7, 0, 20, 0, -2, -269, 5, 0, 0, 0, 0, 0, 0, 0, 21, -92, -56, 0, 0, 0, -181, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 106, 0, 0, 2, 7, 0, 20, 0, -2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 20, 0, 0 };
        yyGindex = new short[] { 0, -19, 0, 234, 0, 247, 0, 0, 0, 0, -25, 0, 190, 29, -7, 32, -31, 0, 0, 204, -47, -15, 0, 194, 0, 0, 191, 0, -93, -98, -90, -148, 128, 0, 0, 0, -53, -128, -137, 214, 0, 0, 256, 258, 0, 0, 0, 0, 202, 0, 308, -30, 230 };
        yyTable = new short[] { 37, 16, 17, 18, 19, 79, 96, 55, 32, 39, 137, 18, 95, 51, 178, 57, 70, 142, 120, 18, 143, 116, 41, 18, 18, 117, 75, 16, 109, 168, 170, 37, 72, 53, 52, 178, 54, 16, 49, 67, 53, 100, 185, 187, 31, 49, 99, 33, 101, 47, 55, 168, 170, 131, 133, 89, 79, 112, 89, 130, 132, 136, 173, 79, 116, 111, 49, 153, 145, 142, 49, 70, 143, 152, 126, 31, 127, 109, 33, 69, 56, 161, 113, 31, 78, 31, 33, 72, 33, 150, 142, 78, 18, 143, 18, 64, 144, 21, 105, 21, 106, 107, 18, 91, 131, 133, 91, 13, 28, 93, 167, 169, 93, 172, 163, 136, 3, 73, 31, 3, 3, 33, 3, 88, 74, 18, 131, 133, 18, 18, 102, 18, 184, 186, 69, 78, 172, 88, 78, 78, 88, 166, 78, 58, 31, 59, 105, 33, 179, 180, 159, 181, 78, 19, 20, 21, 22, 23, 24, 25, 26, 27, 63, 63, 63, 63, 63, 63, 63, 63, 63, 103, 53, 28, 16, 16, 104, 16, 16, 108, 16, 110, 63, 29, 16, 16, 109, 114, 95, 16, 16, 95, 63, 59, 59, 59, 59, 59, 59, 59, 59, 59, 19, 20, 21, 22, 23, 24, 25, 26, 115, 119, 148, 59, 19, 20, 21, 22, 23, 24, 25, 26, 28, 59, 90, 109, 149, 90, 120, 161, 182, 76, 162, 156, 28, 19, 20, 21, 22, 23, 24, 25, 26, 134, 165, 19, 20, 21, 22, 23, 24, 25, 26, 65, 160, 28, 19, 20, 21, 22, 23, 24, 25, 26, 171, 28, 164, 183, 80, 85, 89, 105, 117, 157, 158, 159, 28, 79, 26, 84, 66, 16, 17, 18, 19, 51, 113, 27, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 17, 17, 28, 17, 17, 94, 17, 147, 128, 146, 17, 17, 28, 188, 154, 17, 17, 22, 22, 129, 22, 22, 97, 22, 98, 155, 38, 125, 22, 0, 0, 0, 22, 22, 23, 23, 0, 23, 23, 0, 23, 0, 0, 0, 0, 23, 0, 0, 0, 23, 23, 19, 19, 0, 19, 19, 0, 19, 0, 0, 0, 0, 19, 0, 0, 0, 19, 19, 25, 25, 0, 25, 25, 0, 25, 0, 0, 0, 0, 25, 0, 0, 0, 25, 25, 24, 24, 0, 24, 24, 0, 24, 0, 0, 0, 0, 24, 0, 0, 0, 24, 24, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        yyCheck = new short[] { 15, 0, 0, 0, 0, 52, 59, 37, 15, 277, 108, 280, 59, 32, 162, 40, 47, 110, 278, 288, 110, 277, 277, 292, 293, 281, 51, 277, 288, 157, 158, 46, 47, 279, 287, 183, 282, 287, 284, 46, 279, 66, 179, 180, 15, 284, 65, 15, 67, 289, 80, 179, 180, 106, 107, 280, 103, 87, 283, 106, 107, 108, 160, 110, 277, 279, 284, 120, 281, 162, 284, 102, 162, 120, 99, 46, 101, 288, 46, 47, 282, 292, 89, 54, 52, 56, 54, 102, 56, 120, 183, 59, 278, 183, 280, 280, 111, 278, 288, 280, 290, 291, 288, 280, 157, 158, 283, 288, 277, 280, 157, 158, 283, 160, 144, 162, 277, 285, 89, 280, 281, 89, 283, 277, 284, 277, 179, 180, 280, 281, 280, 283, 179, 180, 102, 103, 183, 280, 106, 107, 283, 156, 110, 288, 115, 290, 288, 115, 290, 291, 292, 293, 120, 257, 258, 259, 260, 261, 262, 263, 264, 265, 257, 258, 259, 260, 261, 262, 263, 264, 265, 287, 279, 277, 277, 278, 285, 280, 281, 287, 283, 280, 277, 287, 287, 288, 288, 283, 280, 292, 293, 283, 287, 257, 258, 259, 260, 261, 262, 263, 264, 265, 257, 258, 259, 260, 261, 262, 263, 264, 280, 283, 277, 277, 257, 258, 259, 260, 261, 262, 263, 264, 277, 287, 280, 288, 284, 283, 278, 292, 293, 286, 280, 279, 277, 257, 258, 259, 260, 261, 262, 263, 264, 286, 285, 257, 258, 259, 260, 261, 262, 263, 264, 265, 287, 277, 257, 258, 259, 260, 261, 262, 263, 264, 286, 277, 277, 280, 0, 283, 271, 288, 280, 290, 291, 292, 277, 0, 280, 283, 46, 280, 280, 280, 280, 280, 280, 280, 287, 287, 257, 258, 259, 260, 261, 262, 263, 264, 277, 278, 280, 280, 281, 56, 283, 115, 102, 113, 287, 288, 277, 183, 121, 292, 293, 277, 278, 103, 280, 281, 64, 283, 64, 121, 16, 95, 288, -1, -1, -1, 292, 293, 277, 278, -1, 280, 281, -1, 283, -1, -1, -1, -1, 288, -1, -1, -1, 292, 293, 277, 278, -1, 280, 281, -1, 283, -1, -1, -1, -1, 288, -1, -1, -1, 292, 293, 277, 278, -1, 280, 281, -1, 283, -1, -1, -1, -1, 288, -1, -1, -1, 292, 293, 277, 278, -1, 280, 281, -1, 283, -1, -1, -1, -1, 288, -1, -1, -1, 292, 293, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276 };
        yyNames = new String[] { "end-of-file", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "BOOLEAN", "BYTE", "SHORT", "INT", "LONG", "CHAR", "FLOAT", "DOUBLE", "VOID", "PUBLIC", "PROTECTED", "PRIVATE", "STATIC", "ABSTRACT", "FINAL", "NATIVE", "SYNCHRONIZED", "TRANSIENT", "VOLATILE", "STRICTFP", "IDENTIFIER", "AND", "DOT", "COMMA", "ELLIPSIS", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "QUESTION", "LT", "GT", "THROWS", "EXTENDS", "SUPER", "RSHIFT", "URSHIFT" };
    }
}

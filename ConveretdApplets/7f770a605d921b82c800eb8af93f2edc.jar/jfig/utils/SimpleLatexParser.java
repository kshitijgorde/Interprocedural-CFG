// 
// Decompiled by Procyon v0.5.30
// 

package jfig.utils;

import java.io.OutputStream;
import java.io.DataOutputStream;
import jfig.objects.FigObject;
import jfig.objects.FigObjectList;
import jfig.objects.FigWriter;
import jfig.canvas.FigTrafo2D;
import java.io.FileOutputStream;
import jfig.objects.FigCompound;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.awt.Point;
import java.util.Stack;
import java.util.Vector;
import jfig.gui.FontCache;
import jfig.gui.ColorCache;

public class SimpleLatexParser
{
    static final int INVALID = 0;
    static final int TEXT = 1;
    static final int COMMAND = 2;
    static final int MATH = 3;
    static final int BEGINGROUP = 4;
    static final int ENDGROUP = 5;
    static final int CONTROL = 6;
    static final int BEGINMATH = 7;
    static final int ENDMATH = 8;
    static final int SUPERSCRIPT = 9;
    static final int SUBSCRIPT = 10;
    static final int MATHSYMBOL = 11;
    static final String[] _mode_names;
    static int count;
    boolean debug;
    boolean dump_tokens;
    boolean display_boxes;
    ColorCache colorCache;
    FontCache fontCache;
    int mathFontIndex;
    int mathGreekFontIndex;
    int mathSymbolFontIndex;
    StringBuffer token;
    Vector tokens;
    Vector types;
    Stack stack;
    SimpleLatexParser.Box master;
    SimpleLatexParser.Box current;
    int fontIndex;
    int ptSize;
    int colorIndex;
    int italic_correction;
    public final int[][] subscriptPtSizes;
    
    void tokenize(final String s) {
        this.tokens = new Vector();
        this.types = new Vector();
        this.token = new StringBuffer("");
        int n = 1;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            final char shortCommand = this.getShortCommand(i, s);
            if (shortCommand != '\0') {
                ++i;
            }
            if (n == 1) {
                if (shortCommand != '\0') {
                    this.token.append(shortCommand);
                }
                else if (char1 == '$') {
                    this.checkAddToken(this.token, 1);
                    this.checkAddToken("$", 7);
                    this.token = new StringBuffer("");
                    n = 3;
                }
                else if (char1 == '{') {
                    this.checkAddToken(this.token, 1);
                    this.checkAddToken("{", 4);
                    this.token = new StringBuffer("");
                }
                else if (char1 == '}') {
                    this.checkAddToken(this.token, 1);
                    this.checkAddToken("}", 5);
                    this.token = new StringBuffer("");
                }
                else if (char1 == '\\') {
                    this.checkAddToken(this.token, 1);
                    this.token = new StringBuffer("\\");
                    n2 = 1;
                    n = 2;
                }
                else {
                    if (char1 == '%') {
                        break;
                    }
                    if (this.isTextToken(char1)) {
                        this.token.append(char1);
                    }
                    else {
                        this.errorInTokenizer(i, n, s);
                    }
                }
            }
            else if (n == 3) {
                if (shortCommand != '\0') {
                    this.token.append(shortCommand);
                }
                else if (this.isDigit(char1)) {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("" + char1, 11);
                    this.token = new StringBuffer("");
                }
                else if (this.isTextToken(char1)) {
                    this.token.append(char1);
                }
                else if (this.isWhitespace(char1) || this.isPunctuation(char1)) {
                    this.token.append(char1);
                }
                else if (char1 == '$') {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("$", 8);
                    this.token = new StringBuffer("");
                    n = 1;
                }
                else if (char1 == '^') {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("^", 9);
                    this.token = new StringBuffer("");
                }
                else if (char1 == '_') {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("_", 10);
                    this.token = new StringBuffer("");
                }
                else if (char1 == '{') {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("{", 4);
                    this.token = new StringBuffer("");
                }
                else if (char1 == '}') {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("}", 5);
                    this.token = new StringBuffer("");
                }
                else if (char1 == '\\') {
                    this.checkAddToken(this.token, 3);
                    this.token = new StringBuffer("\\");
                    n2 = 3;
                    n = 2;
                }
                else if (this.isMathSymbol(char1)) {
                    this.checkAddToken(this.token, 3);
                    this.checkAddToken("" + char1, 11);
                    this.token = new StringBuffer("");
                    n = 3;
                }
                else {
                    if (char1 == '%') {
                        break;
                    }
                    this.errorInTokenizer(i, n, s);
                }
            }
            else if (n == 2) {
                if (shortCommand != '\0') {
                    this.checkAddToken(this.token, 2);
                    this.token = new StringBuffer("" + shortCommand);
                    n = n2;
                }
                else if (this.isAscii(char1)) {
                    this.token.append(char1);
                }
                else if (char1 == '\\') {
                    this.checkAddToken(this.token, 2);
                    this.token = new StringBuffer("\\");
                    n = 2;
                }
                else if (char1 == '$') {
                    this.checkAddToken(this.token, 2);
                    if (n2 == 3) {
                        this.checkAddToken("$", 8);
                        n = 1;
                    }
                    else {
                        this.checkAddToken("$", 7);
                        n = 3;
                    }
                    this.token = new StringBuffer("");
                }
                else {
                    if (char1 == '%') {
                        break;
                    }
                    if (char1 == '^') {
                        if (n2 != 3) {
                            this.errorInTokenizer(i, n, s);
                        }
                        else {
                            this.checkAddToken(this.token, 2);
                            this.checkAddToken("^", 9);
                            this.token = new StringBuffer("");
                            n = n2;
                        }
                    }
                    else if (char1 == '_') {
                        if (n2 != 3) {
                            this.errorInTokenizer(i, n, s);
                        }
                        else {
                            this.checkAddToken(this.token, 2);
                            this.checkAddToken("_", 10);
                            this.token = new StringBuffer("");
                            n = n2;
                        }
                    }
                    else if (char1 == '{') {
                        this.checkAddToken(this.token, 2);
                        this.checkAddToken("{", 4);
                        this.token = new StringBuffer("");
                        n = n2;
                    }
                    else if (char1 == '}') {
                        this.checkAddToken(this.token, 2);
                        this.checkAddToken("}", 5);
                        this.token = new StringBuffer("");
                        n = n2;
                    }
                    else if (char1 == ' ') {
                        this.checkAddToken(this.token, 2);
                        this.token = new StringBuffer("");
                        n = n2;
                    }
                    else if (this.isDigit(char1) || this.isPunctuation(char1) || this.isWhitespace(char1)) {
                        this.checkAddToken(this.token, 2);
                        this.token = new StringBuffer("" + char1);
                        n = n2;
                    }
                    else if (n2 == 3 && this.isMathSymbol(char1)) {
                        this.checkAddToken(this.token, 2);
                        this.checkAddToken("" + char1, 11);
                        this.token = new StringBuffer("");
                        n = n2;
                    }
                    else {
                        this.errorInTokenizer(i, n, s);
                    }
                }
            }
        }
        if (this.token != null && this.token.length() > 0) {
            this.checkAddToken(this.token, n);
        }
    }
    
    private char getShortCommand(final int n, final String s) {
        try {
            final char char1 = s.charAt(n);
            if (char1 != '\\' && char1 != '\"') {
                return '\0';
            }
            final char char2 = s.charAt(n + 1);
            if (char1 == '\\') {
                switch (char2) {
                    case 32: {
                        return ' ';
                    }
                    case 36: {
                        return '$';
                    }
                    case 37: {
                        return '%';
                    }
                    case 38: {
                        return '&';
                    }
                    case 123: {
                        return '{';
                    }
                    case 125: {
                        return '}';
                    }
                    case 124: {
                        return '|';
                    }
                    case 35: {
                        return '#';
                    }
                    case 95: {
                        return '_';
                    }
                    case 34: {
                        return '\"';
                    }
                    default: {
                        return '\0';
                    }
                }
            }
            else {
                if (char1 != '\"') {
                    return '\0';
                }
                switch (char2) {
                    case 97: {
                        return '\u00e4';
                    }
                    case 111: {
                        return '\u00f6';
                    }
                    case 117: {
                        return '\u00fc';
                    }
                    case 65: {
                        return '\u00c4';
                    }
                    case 79: {
                        return '\u00d6';
                    }
                    case 85: {
                        return '\u00dc';
                    }
                    case 115: {
                        return '\u00df';
                    }
                    default: {
                        return '\0';
                    }
                }
            }
        }
        catch (Exception ex) {
            return '\0';
        }
    }
    
    private void checkAddToken(final Object o, final int n) {
        if (o == null) {
            return;
        }
        if (o.toString().length() > 0) {
            this.tokens.add(o);
            this.types.add(new Integer(n));
        }
    }
    
    private void errorInTokenizer(final int n, final int n2, final String s) {
        System.out.println("Error in tokenize, mode= " + SimpleLatexParser._mode_names[n2]);
        System.out.println(s);
        for (int i = 0; i < n; ++i) {
            System.out.print(" ");
        }
        System.out.println("^");
    }
    
    public boolean isTextToken(final char c) {
        return this.isAscii(c) || this.isUmlaut(c) || this.isDigit(c) || this.isWhitespace(c) || this.isPunctuation(c);
    }
    
    public boolean isAscii(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    public boolean isUmlaut(final char c) {
        return c == '\u00e4' || c == '\u00c4' || c == '\u00f6' || c == '\u00d6' || c == '\u00fc' || c == '\u00dc' || c == '\u00df';
    }
    
    public boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public boolean isWhitespace(final char c) {
        return c == ' ' || c == '\t';
    }
    
    public boolean isPunctuation(final char c) {
        return c == '.' || c == ',' || c == ';' || c == ':' || c == '!' || c == '?' || c == '\'' || c == '-' || c == '+' || c == '=' || c == '(' || c == ')' || c == '[' || c == ']';
    }
    
    public boolean isMathSymbol(final char c) {
        return c == '=' || c == '>' || c == '<' || c == '+' || c == '-' || c == '|' || c == '*' || c == '(' || c == ')' || c == '/';
    }
    
    public void dumpTokens() {
        int n = 0;
        for (int i = 0; i < this.tokens.size(); ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(" ");
            }
            final String string = this.tokens.elementAt(i).toString();
            System.out.println(string + "   " + SimpleLatexParser._mode_names[this.types.elementAt(i)]);
            n += string.length();
        }
    }
    
    public Object getState() {
        return new Object[] { new Integer(this.fontIndex), new Integer(this.ptSize), new Integer(this.colorIndex) };
    }
    
    public void setState(final Object o) {
        try {
            int n = 0;
            final Object[] array = (Object[])o;
            this.fontIndex = (int)array[n++];
            this.ptSize = (int)array[n++];
            this.colorIndex = (int)array[n++];
        }
        catch (Throwable t) {
            msg("-E- internal in setState: tmp= " + o);
            t.printStackTrace();
        }
    }
    
    public void append(final SimpleLatexParser.Box current) {
        this.current.append(current);
        this.current = current;
    }
    
    public SimpleLatexParser.Box buildBox() {
        if (this == null) {
            throw null;
        }
        this.master = (SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, "", this.fontIndex, this.ptSize, this.colorIndex);
        this.current = this.master;
        for (int i = 0; i < this.tokens.size(); ++i) {
            final String string = this.tokens.elementAt(i).toString();
            final int intValue = this.types.elementAt(i);
            switch (intValue) {
                case 0: {
                    msg("-E-internal: mode=INVALID, token=" + string);
                    break;
                }
                case 1: {
                    for (int j = 0; j < string.length(); ++j) {
                        final String string2 = "" + string.charAt(j);
                        if (this == null) {
                            throw null;
                        }
                        this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, string2, this.fontIndex, this.ptSize, this.colorIndex));
                    }
                    break;
                }
                case 2: {
                    this.handleCommand(string.substring(1));
                    break;
                }
                case 3: {
                    for (int k = 0; k < string.length(); ++k) {
                        final String string3 = "" + string.charAt(k);
                        if (this == null) {
                            throw null;
                        }
                        this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, string3, this.fontIndex, this.ptSize, this.colorIndex));
                    }
                    break;
                }
                case 4: {
                    this.stack.push(this.getState());
                    break;
                }
                case 5: {
                    try {
                        this.setState(this.stack.pop());
                    }
                    catch (Exception ex) {
                        msg("-E- empty stack: unmatched '}' brace");
                        break;
                    }
                    if (!this.stack.isEmpty() && this.stack.peek() instanceof SimpleLatexParser.SubSuperScriptBox) {
                        this.current = (SimpleLatexParser.Box)this.stack.pop();
                        this.setState(this.stack.pop());
                    }
                    break;
                }
                case 6: {
                    msg("-W- control character, should not happen: " + string);
                    break;
                }
                case 7: {
                    this.stack.push(this.getState());
                    this.fontIndex = this.mathFontIndex;
                    break;
                }
                case 8: {
                    this.setState(this.stack.pop());
                    break;
                }
                case 9: {
                    if (this.debug) {
                        System.out.println("\n\n\n");
                    }
                    SimpleLatexParser.SubSuperScriptBox subSuperScriptBox;
                    if (this.current instanceof SimpleLatexParser.SubSuperScriptBox) {
                        subSuperScriptBox = (SimpleLatexParser.SubSuperScriptBox)this.current;
                        if (this.debug) {
                            System.out.println("SUPERSCRIPT: '" + string + "' " + subSuperScriptBox);
                        }
                        if (subSuperScriptBox.getSuperscriptBox() != null) {
                            if (this == null) {
                                throw null;
                            }
                            subSuperScriptBox = new SimpleLatexParser.SubSuperScriptBox(this, this.fontIndex, this.ptSize);
                            this.append((SimpleLatexParser.Box)subSuperScriptBox);
                        }
                    }
                    else {
                        if (this == null) {
                            throw null;
                        }
                        subSuperScriptBox = new SimpleLatexParser.SubSuperScriptBox(this, this.fontIndex, this.ptSize);
                        this.append((SimpleLatexParser.Box)subSuperScriptBox);
                    }
                    this.stack.push(this.getState());
                    this.stack.push(subSuperScriptBox);
                    this.ptSize = subSuperScriptBox.getSubscriptPtSize();
                    final Point superscriptBasePoint = subSuperScriptBox.getSuperscriptBasePoint();
                    if (this == null) {
                        throw null;
                    }
                    final SimpleLatexParser.CharBox charBox = new SimpleLatexParser.CharBox(this, "", this.fontIndex, this.ptSize, this.colorIndex);
                    ((SimpleLatexParser.Box)charBox).setOrigin(superscriptBasePoint);
                    subSuperScriptBox.setSuperscriptBox((SimpleLatexParser.Box)charBox);
                    if (this.debug) {
                        System.out.println("MASTER: base=" + superscriptBasePoint + "\nbox= " + subSuperScriptBox);
                    }
                    if (this.debug) {
                        System.out.println("new superscriptbox=" + charBox + " " + this.ptSize);
                    }
                    this.current = (SimpleLatexParser.Box)charBox;
                    break;
                }
                case 10: {
                    if (this.debug) {
                        System.out.println("\n\n\n");
                    }
                    SimpleLatexParser.SubSuperScriptBox subSuperScriptBox2;
                    if (this.current instanceof SimpleLatexParser.SubSuperScriptBox) {
                        subSuperScriptBox2 = (SimpleLatexParser.SubSuperScriptBox)this.current;
                        if (this.debug) {
                            System.out.println("SUBSCRIPT: '" + string + "' " + subSuperScriptBox2);
                        }
                        if (subSuperScriptBox2.getSubscriptBox() != null) {
                            if (this == null) {
                                throw null;
                            }
                            subSuperScriptBox2 = new SimpleLatexParser.SubSuperScriptBox(this, this.fontIndex, this.ptSize);
                            this.append((SimpleLatexParser.Box)subSuperScriptBox2);
                        }
                    }
                    else {
                        if (this == null) {
                            throw null;
                        }
                        subSuperScriptBox2 = new SimpleLatexParser.SubSuperScriptBox(this, this.fontIndex, this.ptSize);
                        this.append((SimpleLatexParser.Box)subSuperScriptBox2);
                    }
                    this.stack.push(this.getState());
                    this.stack.push(subSuperScriptBox2);
                    this.ptSize = subSuperScriptBox2.getSubscriptPtSize();
                    final Point subscriptBasePoint = subSuperScriptBox2.getSubscriptBasePoint();
                    if (this == null) {
                        throw null;
                    }
                    final SimpleLatexParser.CharBox charBox2 = new SimpleLatexParser.CharBox(this, "", this.fontIndex, this.ptSize, this.colorIndex);
                    ((SimpleLatexParser.Box)charBox2).setOrigin(subscriptBasePoint);
                    subSuperScriptBox2.setSubscriptBox((SimpleLatexParser.Box)charBox2);
                    if (this.debug) {
                        System.out.println("MASTER: base=" + subscriptBasePoint + "\nbox= " + subSuperScriptBox2);
                    }
                    if (this.debug) {
                        System.out.println("new subscriptbox=" + charBox2 + " " + this.ptSize);
                    }
                    this.current = (SimpleLatexParser.Box)charBox2;
                    break;
                }
                case 11: {
                    if (this == null) {
                        throw null;
                    }
                    this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, string, this.mathSymbolFontIndex, this.ptSize, this.colorIndex));
                    break;
                }
                default: {
                    System.out.println("UNKNOWN MODE: " + intValue + " " + string);
                    break;
                }
            }
        }
        return this.master;
    }
    
    public void handleCommand(final String s) {
        final String string = "_" + s;
        try {
            this.getClass().getMethod(string, (Class<?>[])new Class[0]).invoke(this, new Object[0]);
        }
        catch (InvocationTargetException ex) {
            msg("-E- handleCommand: " + ex.getTargetException());
        }
        catch (NoSuchMethodException ex2) {
            msg("-W- Command undefined: " + s + " " + ex2);
            ex2.printStackTrace();
            if (this == null) {
                throw null;
            }
            this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, s, this.fontIndex, this.ptSize, this.colorIndex));
        }
        catch (Exception ex3) {
            msg("-E- exception: " + ex3 + " for command: " + s);
            ex3.printStackTrace();
        }
    }
    
    public void FIXME(final String s) {
        System.out.println("-W- function not (yet) supported: " + s);
    }
    
    public void _bf() {
        this.fontIndex |= 0x2;
    }
    
    public void _em() {
        this.fontIndex ^= 0x1;
    }
    
    public void _rm() {
        this.fontIndex &= 0xFC;
    }
    
    public void _sl() {
        throw new Error("Not yet implemented: _sl");
    }
    
    public void _sc() {
        throw new Error("Not yet implemented: _sc");
    }
    
    public void _mathrm() {
        this.fontIndex &= 0xFC;
    }
    
    public void _tt() {
        this.fontIndex = 12;
    }
    
    public void _btt() {
        this.fontIndex = 14;
    }
    
    public void _itt() {
        this.fontIndex = 13;
    }
    
    public void _black() {
        this.colorIndex = ColorCache.BLACK;
    }
    
    public void _red() {
        this.colorIndex = ColorCache.RED;
    }
    
    public void _green() {
        this.colorIndex = ColorCache.GREEN;
    }
    
    public void _blue() {
        this.colorIndex = ColorCache.BLUE;
    }
    
    public void _cyan() {
        this.colorIndex = ColorCache.CYAN;
    }
    
    public void _magenta() {
        this.colorIndex = ColorCache.MAGENTA;
    }
    
    public void _yellow() {
        this.colorIndex = ColorCache.YELLOW;
    }
    
    public void _white() {
        this.colorIndex = ColorCache.WHITE;
    }
    
    public void _darkblue() {
        this.colorIndex = ColorCache.BLUE4;
    }
    
    public void _lightblue() {
        this.colorIndex = ColorCache.LTBLUE;
    }
    
    public void _darkgreen() {
        this.colorIndex = ColorCache.GREEN4;
    }
    
    public void _darkred() {
        this.colorIndex = ColorCache.RED4;
    }
    
    public void _darkpink() {
        this.colorIndex = ColorCache.PINK4;
    }
    
    public void _gold() {
        this.colorIndex = ColorCache.GOLD;
    }
    
    public void _fivept() {
        this.ptSize = 5;
    }
    
    public void _sixpt() {
        this.ptSize = 6;
    }
    
    public void _sevenpt() {
        this.ptSize = 7;
    }
    
    public void _eightpt() {
        this.ptSize = 8;
    }
    
    public void _ninept() {
        this.ptSize = 9;
    }
    
    public void _tenpt() {
        this.ptSize = 10;
    }
    
    public void _elevenpt() {
        this.ptSize = 11;
    }
    
    public void _twelvept() {
        this.ptSize = 12;
    }
    
    public void _thirteenpt() {
        this.ptSize = 13;
    }
    
    public void _fourteenpt() {
        this.ptSize = 14;
    }
    
    public void _fifteenpt() {
        this.ptSize = 15;
    }
    
    public void _sixteenpt() {
        this.ptSize = 16;
    }
    
    public void _seventeenpt() {
        this.ptSize = 17;
    }
    
    public void _eightteenpt() {
        this.ptSize = 18;
    }
    
    public void _nineteenpt() {
        this.ptSize = 19;
    }
    
    public void _twentypt() {
        this.ptSize = 20;
    }
    
    public void _twentyonept() {
        this.ptSize = 21;
    }
    
    public void _twentytwopt() {
        this.ptSize = 22;
    }
    
    public void _twentythreept() {
        this.ptSize = 23;
    }
    
    public void _twentyfourpt() {
        this.ptSize = 24;
    }
    
    public void _twentyfivept() {
        this.ptSize = 25;
    }
    
    public void _twentysixpt() {
        this.ptSize = 26;
    }
    
    public void _twentysevenpt() {
        this.ptSize = 27;
    }
    
    public void _twentyeightpt() {
        this.ptSize = 28;
    }
    
    public void _twentyninept() {
        this.ptSize = 29;
    }
    
    public void _thirtypt() {
        this.ptSize = 30;
    }
    
    public void _thirtyfivept() {
        this.ptSize = 35;
    }
    
    public void _fourtypt() {
        this.ptSize = 40;
    }
    
    public void _fourtyfivept() {
        this.ptSize = 45;
    }
    
    public void _fiftypt() {
        this.ptSize = 50;
    }
    
    public void _fiftyfivept() {
        this.ptSize = 55;
    }
    
    public void _sixtypt() {
        this.ptSize = 60;
    }
    
    public void _seventypt() {
        this.ptSize = 70;
    }
    
    public void _eightypt() {
        this.ptSize = 80;
    }
    
    public void _ninetypt() {
        this.ptSize = 90;
    }
    
    public void _hundredpt() {
        this.ptSize = 100;
    }
    
    public void _xpt() {
        this.ptSize = 10;
    }
    
    public void _xipt() {
        this.ptSize = 11;
    }
    
    public void _xiipt() {
        this.ptSize = 12;
    }
    
    public void _xiiipt() {
        this.ptSize = 13;
    }
    
    public void _xivpt() {
        this.ptSize = 14;
    }
    
    public void _xvpt() {
        this.ptSize = 15;
    }
    
    public void _xvipt() {
        this.ptSize = 16;
    }
    
    public void _xviipt() {
        this.ptSize = 17;
    }
    
    public void _xviiipt() {
        this.ptSize = 18;
    }
    
    public void _xixpt() {
        this.ptSize = 19;
    }
    
    public void _xxpt() {
        this.ptSize = 20;
    }
    
    public void _xxipt() {
        this.ptSize = 21;
    }
    
    public void _xxiipt() {
        this.ptSize = 22;
    }
    
    public void _xxiiipt() {
        this.ptSize = 23;
    }
    
    public void _xxivpt() {
        this.ptSize = 24;
    }
    
    public void _xxvpt() {
        this.ptSize = 25;
    }
    
    public void _xxvipt() {
        this.ptSize = 26;
    }
    
    public void _xxviipt() {
        this.ptSize = 27;
    }
    
    public void _xxxpt() {
        this.ptSize = 30;
    }
    
    public void _xxxvpt() {
        this.ptSize = 35;
    }
    
    public void _xlpt() {
        this.ptSize = 40;
    }
    
    public void _footnotesize() {
        this.FIXME("footnotesize");
    }
    
    public void _tiny() {
        this.FIXME("tiny");
    }
    
    public void _small() {
        this.FIXME("small");
    }
    
    public void _normalsize() {
        this.FIXME("normalsize");
    }
    
    public void _large() {
        this.FIXME("large");
    }
    
    public void _Large() {
        this.FIXME("Large");
    }
    
    public void _LARGE() {
        this.FIXME("LARGE");
    }
    
    public void _huge() {
        this.FIXME("huge");
    }
    
    public void _hashsign() {
        this.unicode("#");
    }
    
    public void _atsign() {
        this.unicode("@");
    }
    
    public void _lbracket() {
        this.unicode("[");
    }
    
    public void _backslash() {
        this.unicode("\\");
    }
    
    public void _rbracket() {
        this.unicode("]");
    }
    
    public void _lessthan() {
        this.unicode("<");
    }
    
    public void _equals() {
        this.unicode("=");
    }
    
    public void _greaterthan() {
        this.unicode(">");
    }
    
    public void _proportional() {
        this.unicode("\u2243");
    }
    
    public void _approxeq() {
        this.unicode("\u2245");
    }
    
    public void _lbrace() {
        this.unicode("{");
    }
    
    public void _verticalbar() {
        this.unicode("|");
    }
    
    public void _rbrace() {
        this.unicode("}");
    }
    
    public void _tildechar() {
        this.unicode("~");
    }
    
    public void _not() {
        this.FIXME("\\noti#1");
    }
    
    public void _pmb() {
        this.FIXME("\\pmb#1");
    }
    
    public void _frac() {
        this.FIXME("\\frac#1#2");
    }
    
    public void _hat() {
        this.FIXME("\\hat#1");
    }
    
    public void _check() {
        this.FIXME("\\check#1");
    }
    
    public void _acute() {
        this.FIXME("\\acute#1");
    }
    
    public void _grave() {
        this.FIXME("\\grave#1");
    }
    
    public void _brave() {
        this.FIXME("\\brave#1");
    }
    
    public void _bar() {
        this.FIXME("\\bar#1");
    }
    
    public void _vec() {
        this.FIXME("\\vec#1");
    }
    
    public void _dot() {
        this.FIXME("\\dot#1");
    }
    
    public void _ddot() {
        this.FIXME("\\ddot#1");
    }
    
    public void _tilde() {
        this.FIXME("\\tilde#1");
    }
    
    public void _alpha() {
        this.greek("\u03b1");
    }
    
    public void _beta() {
        this.greek("\u03b2");
    }
    
    public void _gamma() {
        this.greek("\u03b3");
    }
    
    public void _delta() {
        this.greek("\u03b4");
    }
    
    public void _epsilon() {
        this.greek("\u03b5");
    }
    
    public void _varepsilon() {
        this.greek("\u02b5");
    }
    
    public void _zeta() {
        this.greek("\u03b6");
    }
    
    public void _eta() {
        this.greek("\u03b7");
    }
    
    public void _theta() {
        this.greek("\u03b8");
    }
    
    public void _vartheta() {
        this.greek("\u03d1");
    }
    
    public void _iota() {
        this.greek("\u03b9");
    }
    
    public void _kappa() {
        this.greek("\u03ba");
    }
    
    public void _lambda() {
        this.greek("\u03bb");
    }
    
    public void _mu() {
        this.greek("\u03bc");
    }
    
    public void _nu() {
        this.greek("\u03bd");
    }
    
    public void _xi() {
        this.greek("\u03be");
    }
    
    public void _greeko() {
        this.greek("\u03bf");
    }
    
    public void _pi() {
        this.greek("\u03c0");
    }
    
    public void _varpi() {
        this.greek("\u03d6");
    }
    
    public void _rho() {
        this.greek("\u03c1");
    }
    
    public void _varrho() {
        this.greek("\u03f1");
    }
    
    public void _sigma() {
        this.greek("\u03c3");
    }
    
    public void _varsigma() {
        this.greek("\u03da");
    }
    
    public void _tau() {
        this.greek("\u03c4");
    }
    
    public void _upsilon() {
        this.greek("\u03c5");
    }
    
    public void _phi() {
        this.greek("\u03d5");
    }
    
    public void _varphi() {
        this.greek("\u03c6");
    }
    
    public void _chi() {
        this.greek("\u03c7");
    }
    
    public void _psi() {
        this.greek("\u03c8");
    }
    
    public void _omega() {
        this.greek("\u03c9");
    }
    
    public void _Alpha() {
        this.greek("\u0391");
    }
    
    public void _Beta() {
        this.greek("\u0392");
    }
    
    public void _Gamma() {
        this.greek("\u0393");
    }
    
    public void _Delta() {
        this.greek("\u0394");
    }
    
    public void _Epsilon() {
        this.greek("\u0395");
    }
    
    public void _Zeta() {
        this.greek("\u0396");
    }
    
    public void _Eta() {
        this.greek("\u0397");
    }
    
    public void _Theta() {
        this.greek("\u0398");
    }
    
    public void _Iota() {
        this.greek("\u0399");
    }
    
    public void _Kappa() {
        this.greek("\u039a");
    }
    
    public void _Lambda() {
        this.greek("\u039b");
    }
    
    public void _Mu() {
        this.greek("\u039c");
    }
    
    public void _Nu() {
        this.greek("\u039d");
    }
    
    public void _Xi() {
        this.greek("\u039e");
    }
    
    public void _Greeko() {
        this.greek("\u039f");
    }
    
    public void _Pi() {
        this.greek("\u03a0");
    }
    
    public void _Rho() {
        this.greek("\u03a1");
    }
    
    public void _Sigma() {
        this.greek("\u03a3");
    }
    
    public void _Tau() {
        this.greek("\u03a4");
    }
    
    public void _Upsilon() {
        this.greek("\u03a5");
    }
    
    public void _Phi() {
        this.greek("\u03a6");
    }
    
    public void _Chi() {
        this.greek("\u03a7");
    }
    
    public void _Psi() {
        this.greek("\u03a8");
    }
    
    public void _Omega() {
        this.greek("\u03a9");
    }
    
    public void _pm() {
        this.unicode("±");
    }
    
    public void _cap() {
        this.unicode("\u2229");
    }
    
    public void _diamond() {
        this.unicode("\u22c4");
    }
    
    public void _oplus() {
        this.unicode("\u2295");
    }
    
    public void _mp() {
        this.unicode("\u2213");
    }
    
    public void _cup() {
        this.unicode("\u222a");
    }
    
    public void _bigtriangleup() {
        this.unicode("\u2206");
    }
    
    public void _ominus() {
        this.unicode("\u2296");
    }
    
    public void _times() {
        this.unicode("\u00d7");
    }
    
    public void _uplus() {
        this.unicode("\u228e");
    }
    
    public void _bigtriangledown() {
        this.unicode("\u2207");
    }
    
    public void _otimes() {
        this.unicode("\u2297");
    }
    
    public void _div() {
        this.unicode("\u00f7");
    }
    
    public void _sqcap() {
        this.unicode("\u2293");
    }
    
    public void _triangleleft() {
        this.unicode("\u22b2");
    }
    
    public void _oslash() {
        this.unicode("\u2298");
    }
    
    public void _ast() {
        this.unicode("\u2217");
    }
    
    public void _sqcup() {
        this.unicode("\u2294");
    }
    
    public void _triangleright() {
        this.unicode("\u22b3");
    }
    
    public void _odot() {
        this.unicode("\u2299");
    }
    
    public void _star() {
        this.unicode("\u22c6");
    }
    
    public void _vee() {
        this.unicode("\u2228");
    }
    
    public void _lhd() {
        this.unicode("\u22b2");
    }
    
    public void _bigcirc() {
        this.unicode("\u25cb");
    }
    
    public void _circ() {
        this.unicode("\u2218");
    }
    
    public void _wedge() {
        this.unicode("\u2227");
    }
    
    public void _rhd() {
        this.unicode("\u22b3");
    }
    
    public void _dagger() {
        this.unicode("\u2020");
    }
    
    public void _bullet() {
        this.unicode("\u2219");
    }
    
    public void _setminus() {
        this.unicode("\u2216");
    }
    
    public void _unlhd() {
        this.unicode("\u22b4");
    }
    
    public void _ddagger() {
        this.unicode("\u2021");
    }
    
    public void _cdot() {
        this.unicode("\u22c5");
    }
    
    public void _wr() {
        this.unicode("\u2240");
    }
    
    public void _unrhd() {
        this.unicode("\u22b5");
    }
    
    public void _amalg() {
        this.unicode("\u2210");
    }
    
    public void _leq() {
        this.unicode("\u2264");
    }
    
    public void _le() {
        this.unicode("\u2264");
    }
    
    public void _geq() {
        this.unicode("\u2265");
    }
    
    public void _equiv() {
        this.unicode("\u2261");
    }
    
    public void _models() {
        this.unicode("\u22a8");
    }
    
    public void _prec() {
        this.unicode("\u227a");
    }
    
    public void _succ() {
        this.unicode("\u227b");
    }
    
    public void _sim() {
        this.unicode("\u223c");
    }
    
    public void _perp() {
        this.unicode("\u22a5");
    }
    
    public void _preceq() {
        this.unicode("\u227c");
    }
    
    public void _succeq() {
        this.unicode("\u227d");
    }
    
    public void _simeq() {
        this.unicode("\u22d6");
    }
    
    public void _mid() {
        this.unicode("\u2223");
    }
    
    public void _ll() {
        this.unicode("«");
    }
    
    public void _gg() {
        this.unicode("»");
    }
    
    public void _asymp() {
        this.unicode("\u224d");
    }
    
    public void _parallel() {
        this.unicode("\u2225");
    }
    
    public void _subset() {
        this.unicode("\u2282");
    }
    
    public void _supset() {
        this.unicode("\u2283");
    }
    
    public void _approx() {
        this.unicode("\u2248");
    }
    
    public void _bowtie() {
        this.unicode("\u22c8");
    }
    
    public void _subseteq() {
        this.unicode("\u2286");
    }
    
    public void _supseteq() {
        this.unicode("\u2287");
    }
    
    public void _cong() {
        this.unicode("\u2245");
    }
    
    public void _Join() {
        this.unicode("\u22c8");
    }
    
    public void _sqsubset() {
        this.unicode("\u228f");
    }
    
    public void _sqsupset() {
        this.unicode("\u2290");
    }
    
    public void _neq() {
        this.unicode("\u2260");
    }
    
    public void _smile() {
        this.unicode("\u203f");
    }
    
    public void _sqsubseteq() {
        this.unicode("\u2291");
    }
    
    public void _sqsupseteq() {
        this.unicode("\u2292");
    }
    
    public void _doteq() {
        this.unicode("\u2250");
    }
    
    public void _frown() {
        this.unicode("\u2040");
    }
    
    public void _in() {
        this.unicode("\u2208");
    }
    
    public void _ni() {
        this.unicode("\u220b");
    }
    
    public void _propto() {
        this.unicode("\u221d");
    }
    
    public void _vdash() {
        this.unicode("\u22a2");
    }
    
    public void _dashv() {
        this.unicode("\u22a3");
    }
    
    public void _leftarrow() {
        this.unicode("\u2190");
    }
    
    public void _longleftarrow() {
        this.unicode("\u2190");
    }
    
    public void _uparrow() {
        this.unicode("\u2191");
    }
    
    public void _Leftarrow() {
        this.unicode("\u21d0");
    }
    
    public void _LongLeftarrow() {
        this.unicode("\u21d0");
    }
    
    public void _Uparrow() {
        this.unicode("\u21d1");
    }
    
    public void _rightarrow() {
        this.unicode("\u2192");
    }
    
    public void _longrightarrow() {
        this.unicode("\u2192");
    }
    
    public void _downarrow() {
        this.unicode("\u2193");
    }
    
    public void _Rightarrow() {
        this.unicode("\u21d2");
    }
    
    public void _Longrightarrow() {
        this.unicode("\u21d2");
    }
    
    public void _Downarrow() {
        this.unicode("\u21d3");
    }
    
    public void _leftrightarrow() {
        this.unicode("\u2194");
    }
    
    public void _longleftrightarrow() {
        this.unicode("\u2194");
    }
    
    public void _updownarrow() {
        this.unicode("\u2195");
    }
    
    public void _Leftrightarrow() {
        this.unicode("\u21d4");
    }
    
    public void _Longleftrightarrow() {
        this.unicode("\u21d4");
    }
    
    public void _Updownarrow() {
        this.unicode("\u21d5");
    }
    
    public void _mapsto() {
        this.unicode("\u21a6");
    }
    
    public void _longmapsto() {
        this.unicode("\u21a6");
    }
    
    public void _nearrow() {
        this.unicode("\u2197");
    }
    
    public void _hookleftarrow() {
        this.unicode("\u21a9");
    }
    
    public void _hookrightarrow() {
        this.unicode("\u21aa");
    }
    
    public void _searrow() {
        this.unicode("\u2198");
    }
    
    public void _leftharpoonup() {
        this.unicode("\u21bc");
    }
    
    public void _rightharpoonup() {
        this.unicode("\u21c0");
    }
    
    public void _swarrow() {
        this.unicode("\u2199");
    }
    
    public void _leftharpoondown() {
        this.unicode("\u21bd");
    }
    
    public void _rightharpoondown() {
        this.unicode("\u21c1");
    }
    
    public void _nwarrow() {
        this.unicode("\u2196");
    }
    
    public void _ldots() {
        this.unicode("\u2026");
    }
    
    public void _cdots() {
        this.unicode("\u22ef");
    }
    
    public void _vdots() {
        this.unicode("\u22ee");
    }
    
    public void _ddots() {
        this.unicode("\u22f1");
    }
    
    public void _aleph() {
        this.unicode("\u2135");
    }
    
    public void _prime() {
        this.unicode("\u2032");
    }
    
    public void _forall() {
        this.unicode("\u2200");
    }
    
    public void _infty() {
        this.unicode("\u221e");
    }
    
    public void _hbar() {
        this.unicode("\u210f");
    }
    
    public void _emptyset() {
        this.unicode("\u2205");
    }
    
    public void _exists() {
        this.unicode("\u2204");
    }
    
    public void _nabla() {
        this.unicode("\u2207");
    }
    
    public void _surd() {
        this.unicode("\u221a");
    }
    
    public void _Box() {
        this.unicode("\u25a1");
    }
    
    public void _triangle() {
        this.unicode("\u2206");
    }
    
    public void _Diamond() {
        this.unicode("\u25ca");
    }
    
    public void _imath() {
        this.unicode("i");
    }
    
    public void _jmath() {
        this.unicode("j");
    }
    
    public void _ell() {
        this.unicode("\u2113");
    }
    
    public void _neg() {
        this.unicode("¬");
    }
    
    public void _top() {
        this.unicode("\u22a4");
    }
    
    public void _flat() {
        this.unicode("FIXME");
    }
    
    public void _natural() {
        this.unicode("FIXME");
    }
    
    public void _sharp() {
        this.unicode("FIXME");
    }
    
    public void _wp() {
        this.unicode("\u2118");
    }
    
    public void _bot() {
        this.unicode("\u22a5");
    }
    
    public void _clubsuit() {
        this.unicode("\u2663");
    }
    
    public void _diamondsuit() {
        this.unicode("\u2666");
    }
    
    public void _heartsuit() {
        this.unicode("\u2665");
    }
    
    public void _spadesuit() {
        this.unicode("\u2660");
    }
    
    public void _mho() {
        this.unicode("\u2127");
    }
    
    public void _Re() {
        this.unicode("\u211c");
    }
    
    public void _Im() {
        this.unicode("\u2111");
    }
    
    public void _angle() {
        this.unicode("\u2220");
    }
    
    public void _partial() {
        this.unicode("\u2202");
    }
    
    public void _sum() {
        this.unicode("\u2211");
    }
    
    public void _prod() {
        this.unicode("\u220f");
    }
    
    public void _coprod() {
        this.unicode("\u2210");
    }
    
    public void _int() {
        this.unicode("\u222b");
    }
    
    public void _oint() {
        this.unicode("\u222e");
    }
    
    public void _bigcap() {
        this.unicode("\u2229");
    }
    
    public void _bigcup() {
        this.unicode("\u222a");
    }
    
    public void _bigsqcup() {
        this.unicode("\u2294");
    }
    
    public void _bigvee() {
        this.unicode("\u2228");
    }
    
    public void _bigwedge() {
        this.unicode("\u2227");
    }
    
    public void _bigodot() {
        this.unicode("\u2299");
    }
    
    public void _bigotimes() {
        this.unicode("\u2297");
    }
    
    public void _bigoplus() {
        this.unicode("\u2295");
    }
    
    public void _biguplus() {
        this.unicode("\u228e");
    }
    
    public void _arccos() {
        this.mathfunction("arccos");
    }
    
    public void _arcsin() {
        this.mathfunction("arcsin");
    }
    
    public void _arctan() {
        this.mathfunction("arctan");
    }
    
    public void _arg() {
        this.mathfunction("arg");
    }
    
    public void _atan() {
        this.mathfunction("atan");
    }
    
    public void _cos() {
        this.mathfunction("cos");
    }
    
    public void _cosh() {
        this.mathfunction("cosh");
    }
    
    public void _cot() {
        this.mathfunction("cot");
    }
    
    public void _coth() {
        this.mathfunction("coth");
    }
    
    public void _csc() {
        this.mathfunction("csc");
    }
    
    public void _deg() {
        this.mathfunction("deg");
    }
    
    public void _det() {
        this.mathfunction("det");
    }
    
    public void _dim() {
        this.mathfunction("dim");
    }
    
    public void _exp() {
        this.mathfunction("exp");
    }
    
    public void _gcd() {
        this.mathfunction("gcd");
    }
    
    public void _hom() {
        this.mathfunction("hom");
    }
    
    public void _inf() {
        this.mathfunction("inf");
    }
    
    public void _ker() {
        this.mathfunction("ker");
    }
    
    public void _lg() {
        this.mathfunction("lg");
    }
    
    public void _lim() {
        this.mathfunction("lim");
    }
    
    public void _liminf() {
        this.mathfunction("liminf");
    }
    
    public void _limsup() {
        this.mathfunction("limsup");
    }
    
    public void _ln() {
        this.mathfunction("ln");
    }
    
    public void _log() {
        this.mathfunction("log");
    }
    
    public void _max() {
        this.mathfunction("max");
    }
    
    public void _min() {
        this.mathfunction("min");
    }
    
    public void _Pr() {
        this.mathfunction("Pr");
    }
    
    public void _sec() {
        this.mathfunction("sec");
    }
    
    public void _sin() {
        this.mathfunction("sin");
    }
    
    public void _sinh() {
        this.mathfunction("sinh");
    }
    
    public void _sup() {
        this.mathfunction("sup");
    }
    
    public void _tan() {
        this.mathfunction("tan");
    }
    
    public void _tanh() {
        this.mathfunction("tanh");
    }
    
    public void _lfloor() {
        this.unicode("FIXME");
    }
    
    public void _rfloor() {
        this.unicode("FIXME");
    }
    
    public void _lceil() {
        this.unicode("\u02e5");
    }
    
    public void _rceil() {
        this.unicode("\u02e9");
    }
    
    public void _langle() {
        this.unicode("FIXME");
    }
    
    public void _rangle() {
        this.unicode("FIXME");
    }
    
    public void _slash() {
        this.unicode("/");
    }
    
    public void _dverticalbar() {
        this.unicode("\u2225");
    }
    
    public void _rmoustache() {
        this.unicode("FIXME");
    }
    
    public void _lmoustache() {
        this.unicode("FIXME");
    }
    
    public void _rgroup() {
        this.unicode("FIXME");
    }
    
    public void _lgroup() {
        this.unicode("FIXME");
    }
    
    public void _arrowvert() {
        this.unicode("FIXME");
    }
    
    public void _Arrowvert() {
        this.unicode("FIXME");
    }
    
    public void _bracevert() {
        this.unicode("FIXME");
    }
    
    public void _widetilde() {
        this.FIXME("widetilde#1");
    }
    
    public void _widehat() {
        this.FIXME("widehat#1");
    }
    
    public void _overleftarrow() {
        this.FIXME("overleftarrow#1");
    }
    
    public void _overrightarrow() {
        this.FIXME("overrightarrow#1");
    }
    
    public void _overline() {
        this.FIXME("overline#1");
    }
    
    public void _underline() {
        this.FIXME("underline#1");
    }
    
    public void _overbrace() {
        this.FIXME("overbrace#1");
    }
    
    public void _underbrace() {
        this.FIXME("underbrace#1");
    }
    
    public void _sqrt() {
        this.FIXME("sqrt#1");
    }
    
    public void _sqrtn() {
        this.FIXME("sqrtn#1");
    }
    
    public void _digamma() {
        this.unicode("F");
    }
    
    public void _beth() {
        this.unicode("\u2136");
    }
    
    public void _daleth() {
        this.unicode("\u2138");
    }
    
    public void _gimel() {
        this.unicode("\u2137");
    }
    
    public void _looparrowleft() {
        this.unicode("\u21ab");
    }
    
    public void _looparrowright() {
        this.unicode("\u21ac");
    }
    
    public void _leftrightsquigarrow() {
        this.unicode("\u21ad");
    }
    
    public void _circlearrowleft() {
        this.unicode("\u21ba");
    }
    
    public void _circlearrowright() {
        this.unicode("\u21bb");
    }
    
    public void _nleftarrow() {
        this.unicode("\u219a");
    }
    
    public void _nrightarrow() {
        this.unicode("\u219b");
    }
    
    public void _nleftrightarrow() {
        this.unicode("\u21ae");
    }
    
    public void _nLeftarrow() {
        this.unicode("\u21cd");
    }
    
    public void _nLeftrightarrow() {
        this.unicode("\u21ce");
    }
    
    public void _nRightarrow() {
        this.unicode("\u21cf");
    }
    
    public void _define() {
        this.unicode("\u225d");
    }
    
    public void _lll() {
        this.unicode("\u22d8");
    }
    
    public void _ggg() {
        this.unicode("\u22d9");
    }
    
    public void _cdotvar() {
        this.unicode("·");
    }
    
    public void _minus() {
        this.unicode("\u2212");
    }
    
    public void _euro() {
        this.unicode("\u20ac");
    }
    
    public void _pound() {
        this.unicode("\u20a4");
    }
    
    public void _pts() {
        this.unicode("\u20a7");
    }
    
    public void _percent() {
        this.unicode("%");
    }
    
    public void _promille() {
        this.unicode("\u2030");
    }
    
    public void _copyright() {
        this.unicode("©");
    }
    
    public void _paragraph() {
        this.unicode("¶");
    }
    
    public void _degrees() {
        this.unicode("°");
    }
    
    public void _setZ() {
        this.unicode("\u2124");
    }
    
    public void _setN() {
        this.unicode("\u2115");
    }
    
    public void _setR() {
        this.unicode("\u211d");
    }
    
    public void _celsius() {
        this.unicode("\u2103");
    }
    
    public void _fahrenheit() {
        this.unicode("\u2109");
    }
    
    public void _complement() {
        this.unicode("\u2201");
    }
    
    public void _nexists() {
        this.unicode("\u2204");
    }
    
    public void _varnothing() {
        this.unicode("\u2205");
    }
    
    public void _notElementIn() {
        this.unicode("\u2209");
    }
    
    public void _mathEpsilon() {
        this.unicode("\u220a");
    }
    
    public void _square() {
        this.unicode("\u22a1");
    }
    
    public void _mathslash() {
        this.unicode("\u2215");
    }
    
    public void _root() {
        this.unicode("\u221a");
    }
    
    public void _cubicroot() {
        this.unicode("\u221b");
    }
    
    public void _fourthroot() {
        this.unicode("\u221c");
    }
    
    public void _varpropto() {
        this.unicode("\u221d");
    }
    
    public void _iint() {
        this.unicode("\u222c");
    }
    
    public void _iiint() {
        this.unicode("\u222d");
    }
    
    public void _telephone() {
        this.unicode("\u260e");
    }
    
    public void _smiley() {
        this.unicode("\u263a");
    }
    
    public void _smileyblack() {
        this.unicode("\u263b");
    }
    
    public void _male() {
        this.unicode("\u2642");
    }
    
    public void _female() {
        this.unicode("\u2640");
    }
    
    public void _thinspace() {
        if (this == null) {
            throw null;
        }
        final SimpleLatexParser.Box box = new SimpleLatexParser.Box(this);
        box.setW(5);
        this.append(box);
    }
    
    public void _qquad() {
    }
    
    public void _emspace() {
        if (this == null) {
            throw null;
        }
        final SimpleLatexParser.CharBox charBox = new SimpleLatexParser.CharBox(this, "M", this.fontIndex, this.ptSize, this.colorIndex);
        charBox.measure();
        if (this == null) {
            throw null;
        }
        final SimpleLatexParser.Box box = new SimpleLatexParser.Box(this);
        box.setW(charBox.getW());
        this.append(box);
    }
    
    void greek(final String s) {
        msg("XXX: greek: " + s + " " + this.mathSymbolFontIndex);
        if (this == null) {
            throw null;
        }
        this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, s, this.mathGreekFontIndex, this.ptSize, this.colorIndex));
    }
    
    void unicode(final String s) {
        msg("XXX: unicode: " + s + " " + this.mathSymbolFontIndex);
        if (this == null) {
            throw null;
        }
        this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, s, this.mathSymbolFontIndex, this.ptSize, this.colorIndex));
    }
    
    void mathfunction(final String s) {
        final int n = this.fontIndex & 0xFC;
        if (this == null) {
            throw null;
        }
        this.append((SimpleLatexParser.Box)new SimpleLatexParser.CharBox(this, s, n, this.ptSize, this.colorIndex));
    }
    
    void space(final int w) {
        if (this == null) {
            throw null;
        }
        final SimpleLatexParser.Box box = new SimpleLatexParser.Box(this);
        box.setW(w);
        this.append(box);
    }
    
    void writeFigHeader(final PrintStream printStream) {
        printStream.println("#FIG 3.2");
        printStream.println("Portrait");
        printStream.println("Center");
        printStream.println("Metric");
        printStream.println("A4");
        printStream.println("100.0");
        printStream.println("Single");
        printStream.println("-2");
        printStream.println("1200 2");
    }
    
    public void setFontIndex(final int fontIndex) {
        this.fontIndex = fontIndex;
    }
    
    public void setFontPtSize(final int ptSize) {
        this.ptSize = ptSize;
    }
    
    public void setColorIndex(final int colorIndex) {
        this.colorIndex = colorIndex;
    }
    
    public void parse(final String s) {
        this.tokenize(s);
        if (this.dump_tokens) {
            System.out.println("tokens for input:\n" + s);
            this.dumpTokens();
            System.out.println();
            System.out.println();
        }
    }
    
    public void convertToFig(final FigCompound figCompound) {
        this.buildBox().convertToFig(figCompound);
    }
    
    public void selftest(final String s) {
        this.parse(s);
        final SimpleLatexParser.Box buildBox = this.buildBox();
        if (this.debug) {
            buildBox.dump(System.out);
        }
        try {
            ++SimpleLatexParser.count;
            final FileOutputStream fileOutputStream = new FileOutputStream("hugo" + SimpleLatexParser.count + ".fig");
            final FigCompound figCompound = new FigCompound();
            figCompound.setTrafo(new FigTrafo2D());
            buildBox.convertToFig(figCompound);
            figCompound.update_bbox();
            final FigWriter figWriter = new FigWriter();
            final FigObjectList list = new FigObjectList();
            list.insert(figCompound);
            figWriter.write_fig31_file(new DataOutputStream(fileOutputStream), true, list);
            fileOutputStream.close();
        }
        catch (Throwable t) {}
    }
    
    public void setEnableDisplayBoxes(final boolean display_boxes) {
        this.display_boxes = display_boxes;
    }
    
    public void setEnableDumpTokens(final boolean dump_tokens) {
        this.dump_tokens = dump_tokens;
    }
    
    public static void msg(final String s) {
        System.out.println(s);
    }
    
    public static void usage() {
        msg("java jfig.utils.SimpleLatexParser [options]");
        msg("where options include: ");
        msg("-help               - this message ");
        msg("-debug              - lots of diagnostic output");
        msg("-tokens             - dump tokens to stdout");
        msg("-boxes              - display the box outlines");
        msg("-italic <value>     - add <value> pts after italic chars");
        System.exit(0);
    }
    
    public static void main(final String[] array) {
        final SimpleLatexParser simpleLatexParser = new SimpleLatexParser();
        for (int i = 0; i < array.length; ++i) {
            if ("-tokens".equals(array[i])) {
                simpleLatexParser.dump_tokens = true;
            }
            if ("-boxes".equals(array[i])) {
                simpleLatexParser.display_boxes = true;
            }
            if ("-debug".equals(array[i])) {
                simpleLatexParser.debug = true;
            }
            if ("-italic".equals(array[i])) {
                simpleLatexParser.italic_correction = Integer.parseInt(array[i + 1]);
            }
            if ("-help".equals(array[i])) {
                usage();
            }
        }
        simpleLatexParser.setFontIndex(0);
        simpleLatexParser.setFontPtSize(27);
        simpleLatexParser.selftest("Und es begab sich zu der Zeit, da Cyrhenius Landpfleger...");
        simpleLatexParser.selftest("Black, {\\red red}, {\\gold gold}, {\\green green}, {\\blue blue {\\black black inside} blue}!");
        simpleLatexParser.selftest("Short Commands, \\$ \\% \\\" aou\"a\"o\"uAOU\"A\"O\"Us\"s!");
        simpleLatexParser.selftest("Text und {\\tt Code mit {\\itt kursivem} Argument.}");
        simpleLatexParser.selftest("Das {\\bf Theorem} von {\\em Archimedes}.");
        simpleLatexParser.selftest("{\\em\\red hendrich\\thinspace}{\\black \\atsign informatik.uni-hamburg.de}");
        simpleLatexParser.selftest("Normaler Text, {\\blue $w = \\sin ( \\alpha \\cdot \\pi /4 )$}");
        simpleLatexParser.selftest("{\\twentypt 20pt {\\fifteenpt 15pt }{\\twelvept 12pt }{\\tenpt 10pt }{\\eightpt 8pt }{\\fivept 5pt } and back to 20pt}");
        simpleLatexParser.selftest("Text mit Formel, {\\blue$w = \\sin( \\alpha\\cdot\\pi/4 )$}");
        simpleLatexParser.setFontIndex(16);
        simpleLatexParser.setFontPtSize(20);
        simpleLatexParser.selftest("Boole'sche Algebra: $a = b \\oplus c$ ");
        simpleLatexParser.selftest("\\lessthan X\\greaterthan \\equals\\backslash\\$\\lbrace\\lbracket X\\rbracket\\rbrace X");
        simpleLatexParser.selftest("M\\emspace M $\\black\\clubsuit\\spadesuit {\\red\\heartsuit{}\\diamondsuit}$ \\telephone\\male\\ \\ \\female 42.59\\ \\euro, 15\\thinspace\\pound, 22.000\\thinspace\\pts");
        simpleLatexParser.setFontIndex(0);
        simpleLatexParser.setFontPtSize(30);
        simpleLatexParser.selftest("{\\darkgreen Pythagoras:} $a^{2} + b^{2} = c^{2}$");
        simpleLatexParser.selftest("$\\cos^{2}(x) + \\sin^{2}(x) = 1$");
        simpleLatexParser.selftest("{\\darkblue Euler formula:  $\\exp (i \\pi ) + 1 = 0$}");
        simpleLatexParser.selftest("{\\darkblue Euler formula:  $e^{\\thinspace i \\pi} + 1 = 0$}");
        simpleLatexParser.selftest("AbCdE: \\int $X_{j=3}^{2} \\cdot dx = 42.0$");
        simpleLatexParser.selftest("Formel1: $X_{i} = \\sum_{i=1}^{N_{0}} \\phi  \\xi  \\cdot 3$");
        simpleLatexParser.selftest("$\\prod_{i} \\sum_{j} \\Gamma_{\\Xi_{\\mu}} \\Rightarrow \\setZ \\bullet (TeX \\mp Word)$");
        simpleLatexParser.selftest("Hopfield-Dynamik: $S_{i} (t+1) = (1/N) \\ {\\fourtypt\\sum}_{j}\\  J_{ij}\\cdot S_{j} (t)$");
        simpleLatexParser.selftest("Hopfield-Lernen: $J_{ij} = (1/N) \\sum_{\\mu} \\xi_{i}^{\\mu} \\xi_{j}^{\\mu}$ ");
        simpleLatexParser.selftest("${\\fourtypt\\int}^{5}_{0}\\ (x^{2}) {\\rm d}x = 1/3 x^{3}{\\fourtypt |}_{0}^{5} = (125/3) $");
        simpleLatexParser.selftest("$\\int^{3}_{x=0} a$");
        simpleLatexParser.selftest("IEEE 754: $value = (-1)^{s} \\cdot\\ 2^{exp-127} \\cdot\\ (1 + mantisse^{-23})$ ");
        simpleLatexParser.selftest("Text (in) [Klammern] \\{und\\} mit\\_unterstrich");
        simpleLatexParser.selftest("For all $x: f(x) = g(x+1)$");
        simpleLatexParser.selftest("Preparing an input file: ");
        simpleLatexParser.selftest("abcde fghij klmno pqrst uvwxy z");
        simpleLatexParser.selftest("ABCDE FGHIJ KLMNO PQRST UVWXY Z");
        simpleLatexParser.selftest("01234 56789 .:;,?! '`()[] -/*+=@");
        simpleLatexParser.selftest("The Romans wrote I + I = II. Really!");
        simpleLatexParser.selftest("Beans (lima, etc.)\\ have vitamin B\\@.");
        simpleLatexParser.selftest("\\$ \\& \\% \\# \\_ \\{ \\} are easy to produce.");
        simpleLatexParser.selftest("This page was produced on \\today. (\\backslash today)");
        simpleLatexParser.selftest("The formula \\( x-3y = 7 \\) is easy to type.");
        simpleLatexParser.selftest("Does \\( x + y \\) always equals \\(x+y\\)?");
        simpleLatexParser.selftest("\\( a_{1} > x^{2n} / y^{2n} \\)");
        simpleLatexParser.selftest("This proves that \\( x' < x'' - y'_{3} < 10 x''' z \\).");
        simpleLatexParser.selftest("The formula \\( a<7 \\) is a noun in this sentence.");
        simpleLatexParser.selftest("Let $x$ be a prime such that $y>2x$.");
        simpleLatexParser.selftest("displayed equation: \\[ x' + y^{2} = z_{i}^{2} \\]");
        simpleLatexParser.selftest("{\\bf This is a bold type style.}");
        simpleLatexParser.selftest("{\\sf This is a sans serif type style.}");
        simpleLatexParser.selftest("{\\sl This is a slanted type style.}");
        simpleLatexParser.selftest("{\\sc This is a Small Caps type style.}");
        simpleLatexParser.selftest("{\\tt This is a typewriter type style.}");
        simpleLatexParser.selftest("\\`{o} \\'{o} \\^{o} \\\"{o} \\~{o} \\={o} \\.{o} \\v{o} \\H{o} \\t{oo} \\c{o} \\d{o} \\b{o}");
        simpleLatexParser.selftest("El se\\~{n}or est\\'{a} bien, gar\\c{c}on.");
        simpleLatexParser.selftest("\\'{E}l est\\'{a} aqu\\'{\\i}.");
        simpleLatexParser.selftest("\\dag \\ddag \\S \\P \\copyright \\pounds.");
        simpleLatexParser.selftest("\\oe \\OE \\ae \\AE \\aa \\AA \\o \\O \\l \\L \\ss ?' !'");
        simpleLatexParser.selftest("$x^{2y} x_{2y} x^{y^{2}} x^{y_{1}} x^{y}_{1} x_{1}^{y}$");
        simpleLatexParser.selftest("Multiplyling by $n/2$ gives \\( (m+n)/n \\).");
        simpleLatexParser.selftest("\\[ x = \\frac{y+z/2}{y^{2}+1} \\]");
        simpleLatexParser.selftest("\\[\\frac{x+y}{1 + \\frac{y}{z+1}}\\]");
        simpleLatexParser.selftest("in-text formula to produce a fraction like $\\frac{1}{2}$.");
        simpleLatexParser.selftest("Square root $\\sqrt{x+y}$ and an $n$th root \\sqrt[n]{2}$.");
        simpleLatexParser.selftest("A low ellipsis: $x_{1}, \\ldots ,x_{n}$.");
        simpleLatexParser.selftest("A centered ellipsis: $a + \\cdots + z$.");
        simpleLatexParser.selftest("Making Greek letters is as easy as $\\pi$ (or $\\Pi$).");
        System.exit(0);
    }
    
    public SimpleLatexParser() {
        this.debug = false;
        this.dump_tokens = false;
        this.display_boxes = false;
        this.colorCache = ColorCache.getColorCache();
        this.fontCache = FontCache.getFontCache();
        this.mathFontIndex = 44;
        this.mathGreekFontIndex = 42;
        this.mathSymbolFontIndex = 42;
        this.stack = new Stack();
        this.italic_correction = 0;
        final int[][] subscriptPtSizes = new int[10][];
        subscriptPtSizes[0] = new int[] { 9, 6 };
        subscriptPtSizes[1] = new int[] { 10, 6 };
        subscriptPtSizes[2] = new int[] { 11, 7 };
        subscriptPtSizes[3] = new int[] { 12, 9 };
        subscriptPtSizes[4] = new int[] { 15, 10 };
        subscriptPtSizes[5] = new int[] { 17, 12 };
        subscriptPtSizes[6] = new int[] { 20, 12 };
        subscriptPtSizes[7] = new int[] { 24, 15 };
        subscriptPtSizes[8] = new int[] { 30, 20 };
        subscriptPtSizes[9] = new int[] { 40, 27 };
        this.subscriptPtSizes = subscriptPtSizes;
    }
    
    static {
        _mode_names = new String[] { "invalid", "text", "command", "math", "begingroup", "endgroup", "control", "beginmath", "endmath", "superscript", "subscript", "mathsymbol" };
        SimpleLatexParser.count = 100;
    }
}

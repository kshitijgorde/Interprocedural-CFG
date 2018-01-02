// 
// Decompiled by Procyon v0.5.30
// 

package jfig.utils;

import jfig.objects.FigPolyline;
import java.awt.font.FontRenderContext;
import jfig.objects.FigAttribs;
import jfig.objects.FigRectangle;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.DataOutputStream;
import jfig.objects.FigObject;
import jfig.objects.FigObjectList;
import jfig.objects.FigWriter;
import jfig.objects.FigText;
import jfig.canvas.FigTrafo2D;
import java.io.FileOutputStream;
import jfig.objects.FigCompound;
import java.io.PrintStream;
import java.awt.FontMetrics;
import java.util.Date;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.Vector;
import jfig.gui.FontCache;
import jfig.gui.ColorCache;
import java.awt.Graphics2D;

public class LP2
{
    static boolean debug;
    static final int THINSPACE = 5;
    static final int USE_LUCIDA_FONTS = 1;
    static final int USE_CM_FONTS = 2;
    static final int USE_SYMBOL_FONT = 3;
    static final int CMERR = -13;
    static final int CMANY = -12;
    static final int CMR = 35;
    static final int CMMI = 42;
    static final int CMSS = 38;
    static final int CMSL = 39;
    static final int CMSY = 44;
    static final int CMTT = 40;
    static final int CMEX = 46;
    static final int MSAM = 47;
    static final int MSBM = 48;
    static Graphics2D imageGraphics2D;
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
    static final int MATHDIGIT = 12;
    static final String[] _mode_names;
    static int count;
    boolean dump_tokens;
    boolean display_boxes;
    boolean use_word_boxes;
    int fontMode;
    int mathFontIndex;
    int mathGreekFontIndex;
    int mathSymbolFontIndex;
    int mathBackupFontIndex;
    ColorCache colorCache;
    FontCache fontCache;
    StringBuffer token;
    Vector tokens;
    Vector types;
    String line;
    int pos;
    int mode;
    Stack stack;
    Stack delayedCommandStack;
    Box master;
    Box current;
    int fontIndex;
    int ptBaseSize;
    int ptSize;
    int colorIndex;
    int italic_correction;
    Object pdfMapper;
    public final int[][] subscriptPtSizes;
    private static /* synthetic */ Class class$Ljava$lang$String;
    
    public void setFontMode(final int n) {
        if (n == 1) {
            this.fontMode = 1;
            this.mathFontIndex = 1;
            this.mathGreekFontIndex = 1;
            this.mathSymbolFontIndex = 0;
            this.mathBackupFontIndex = 16;
        }
        else if (n == 2) {
            this.fontMode = 2;
            this.mathFontIndex = 42;
            this.mathGreekFontIndex = 1;
            this.mathSymbolFontIndex = 44;
            this.mathBackupFontIndex = 16;
        }
        else {
            if (n == 3) {
                this.fontMode = 3;
                throw new IllegalArgumentException("Use Symbol font mode not yet!");
            }
            throw new IllegalArgumentException("Wrong font mode: " + n);
        }
    }
    
    public void setPDFMapper(final Object pdfMapper) {
        this.pdfMapper = pdfMapper;
    }
    
    public int getStringWidthPoints(final int n, final int n2, final String s) {
        if (this.pdfMapper == null) {
            return this.fontCache.getFontMetrics(n, n2).stringWidth(s);
        }
        try {
            return (int)this.pdfMapper.getClass().getMethod("getStringWidthPoints", Integer.TYPE, Integer.TYPE, (LP2.class$Ljava$lang$String != null) ? LP2.class$Ljava$lang$String : (LP2.class$Ljava$lang$String = class$("java.lang.String"))).invoke(this.pdfMapper, new Integer(n), new Integer(n2), s);
        }
        catch (Throwable t) {
            msg("-E- internal error in getStringWidthPoints: " + t);
            msg("-I- falling back to AWT font metrics...");
            return this.fontCache.getFontMetrics(n, n2).stringWidth(s);
        }
    }
    
    String tokenize_command() {
        final StringBuffer sb = new StringBuffer();
        final char char1 = this.line.charAt(this.pos);
        if (char1 != '\\') {
            throw new RuntimeException("-E- not a double quote: " + char1 + " " + this.pos);
        }
        final char char2 = this.line.charAt(this.pos + 1);
        if (this.mode == 3) {
            switch (char2) {
                case ',': {
                    this.checkAddToken("\\mskip_thin", 2);
                    ++this.pos;
                    return "\\mskip_thin";
                }
                case '>': {
                    this.checkAddToken("\\mskip_med", 2);
                    ++this.pos;
                    return "\\mskip_med";
                }
                case ';': {
                    this.checkAddToken("\\mskip_thick", 2);
                    ++this.pos;
                    return "\\mskip_thick";
                }
                case '!': {
                    this.checkAddToken("\\mskip_back", 2);
                    ++this.pos;
                    return "\\mskip_back";
                }
            }
        }
        switch (char2) {
            case ' ': {
                if (this.mode == 3) {
                    this.addMathSpaceToken();
                }
                else {
                    this.addShortCommandToken(" ");
                }
                return null;
            }
            case '\"': {
                this.addShortCommandToken("\"");
                return null;
            }
            case '$': {
                this.addShortCommandToken("$");
                return null;
            }
            case '&': {
                this.addShortCommandToken("&");
                return null;
            }
            case '%': {
                this.addShortCommandToken("%");
                return null;
            }
            case '#': {
                this.addShortCommandToken("#");
                return null;
            }
            case '_': {
                this.addShortCommandToken("_");
                return null;
            }
            case '\\': {
                this.addShortCommandToken("\\");
                return null;
            }
            case '{': {
                this.addShortCommandToken("{");
                return null;
            }
            case '}': {
                this.addShortCommandToken("}");
                return null;
            }
            case '|': {
                this.addShortCommandToken("|");
                return null;
            }
            case '@': {
                this.addShortCommandToken("");
                return null;
            }
            case '(': {
                this.add_begin_math_token();
                return null;
            }
            case '[': {
                this.add_begin_math_token();
                return null;
            }
            case ')': {
                this.add_end_math_token();
                return null;
            }
            case ']': {
                this.add_end_math_token();
                return null;
            }
            default: {
                if (!this.isAscii(char2)) {
                    throw new RuntimeException("-E- Invalid command: '" + char1 + char2 + "'");
                }
                sb.append(char1);
                while (this.pos < this.line.length() - 1) {
                    final char char3 = this.line.charAt(++this.pos);
                    if (this.isAscii(char3)) {
                        sb.append(char3);
                    }
                    else {
                        if (char3 == ' ') {
                            break;
                        }
                        --this.pos;
                        break;
                    }
                }
                final String string = sb.toString();
                msg("-#- tokenize_command: " + string);
                this.checkAddToken(string, 2);
                return string;
            }
        }
    }
    
    String handle_double_quote() {
        final char char1 = this.line.charAt(this.pos);
        if (char1 != '\"') {
            throw new RuntimeException("-E- not a double quote: " + char1 + " " + this.pos);
        }
        ++this.pos;
        switch (this.line.charAt(this.pos + 1)) {
            case 'a': {
                this.addShortCommandToken("\u00e4");
                return null;
            }
            case 'A': {
                this.addShortCommandToken("\u00c4");
                return null;
            }
            case 'o': {
                this.addShortCommandToken("\u00f6");
                return null;
            }
            case 'O': {
                this.addShortCommandToken("\u00d6");
                return null;
            }
            case 'u': {
                this.addShortCommandToken("\u00fc");
                return null;
            }
            case 'U': {
                this.addShortCommandToken("\u00dc");
                return null;
            }
            case 's': {
                this.addShortCommandToken("\u00df");
                return null;
            }
            default: {
                ++this.pos;
                throw new RuntimeException("illegal double quote");
            }
        }
    }
    
    void addShortCommandToken(final String s) {
        this.tokens.add(s);
        this.types.add(new Integer(this.mode));
        ++this.pos;
    }
    
    void addMathSpaceToken() {
        this.tokens.add(" ");
        this.types.add(new Integer(11));
        ++this.pos;
    }
    
    private void begin_group() {
        this.checkAddToken("{", 4);
    }
    
    private void end_group() {
        this.checkAddToken("}", 5);
    }
    
    private void toggle_math_mode() {
        if (this.mode == 1) {
            this.checkAddToken("$", 7);
            this.mode = 3;
        }
        else if (this.mode == 3) {
            this.checkAddToken("$", 8);
            this.mode = 1;
        }
        else {
            msg("-E- toggle_math_mode: neither text nor math mode?? ignored.");
        }
    }
    
    private void add_begin_math_token() {
        if (this.mode == 1) {
            this.checkAddToken("$", 7);
            this.mode = 3;
        }
        else {
            msg("-E- add_begin_math_token: already in math mode, ignored.");
        }
        ++this.pos;
    }
    
    private void add_end_math_token() {
        if (this.mode == 3) {
            this.checkAddToken("$", 8);
            this.mode = 1;
        }
        else {
            msg("-E- add_end_math_token: already in text mode, ignored.");
        }
        ++this.pos;
    }
    
    private void eat_trailing_spaces(final int n) {
        try {
            this.pos = n;
            while (this.pos < this.line.length() && this.line.charAt(this.pos) == ' ') {
                ++this.pos;
            }
            --this.pos;
        }
        catch (StringIndexOutOfBoundsException ex) {
            this.pos = n;
        }
    }
    
    void tokenize(final String line) {
        this.line = line;
        this.tokens = new Vector();
        this.types = new Vector();
        this.token = new StringBuffer("");
        this.pos = 0;
        this.mode = 1;
        this.pos = 0;
        while (this.pos < this.line.length()) {
            final char char1 = this.line.charAt(this.pos);
            if (this.mode == 1) {
                switch (char1) {
                    case 34: {
                        this.handle_double_quote();
                        break;
                    }
                    case 92: {
                        this.tokenize_command();
                        break;
                    }
                    case 123: {
                        this.begin_group();
                        break;
                    }
                    case 125: {
                        this.end_group();
                        break;
                    }
                    case 36: {
                        this.toggle_math_mode();
                        break;
                    }
                    case 64: {
                        this.checkAddToken("@", 1);
                        break;
                    }
                    case 39: {
                        this.checkAddToken("'", 1);
                        break;
                    }
                    case 96: {
                        this.checkAddToken("`", 1);
                        break;
                    }
                    case 37: {
                        return;
                    }
                    default: {
                        if (this.isTextToken(char1)) {
                            this.checkAddToken("" + char1, 1);
                        }
                        else {
                            this.error_in_tokenizer(this.pos, this.mode, this.line);
                        }
                        break;
                    }
                }
            }
            else if (this.mode == 3) {
                switch (char1) {
                    case '%': {
                        return;
                    }
                    case '\"': {
                        this.handle_double_quote();
                        break;
                    }
                    case '\\': {
                        this.tokenize_command();
                        break;
                    }
                    case '{': {
                        this.begin_group();
                        break;
                    }
                    case '}': {
                        this.end_group();
                        break;
                    }
                    case '$': {
                        this.toggle_math_mode();
                        break;
                    }
                    case '^': {
                        this.checkAddToken("^", 9);
                        break;
                    }
                    case '_': {
                        this.checkAddToken("_", 10);
                        break;
                    }
                    default: {
                        if (this.isDigit(char1)) {
                            this.checkAddToken("" + char1, 12);
                        }
                        else if (this.isMathSymbol(char1)) {
                            this.checkAddToken("" + char1, 11);
                        }
                        else if (this.isWhitespace(char1)) {
                            this.checkAddToken("" + char1, 11);
                        }
                        else if (this.isPunctuation(char1)) {
                            this.checkAddToken("" + char1, 3);
                        }
                        else if (this.isTextToken(char1)) {
                            this.checkAddToken("" + char1, 3);
                        }
                        else {
                            this.error_in_tokenizer(this.pos, this.mode, this.line);
                        }
                        break;
                    }
                }
            }
            else {
                this.error_in_tokenizer(this.pos, this.mode, this.line);
            }
            ++this.pos;
        }
        if (this.token != null && this.token.length() > 0) {
            this.checkAddToken(this.token, this.mode);
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
    
    private void handleMathDigit(final char c) {
        if (this.fontMode == 2) {
            this.tokens.add("" + c);
            this.types.add(new Integer(3));
        }
        else {
            this.tokens.add("" + c);
            this.types.add(new Integer(3));
        }
    }
    
    private void error_in_tokenizer(final int n, final int n2, final String s) {
        System.err.println("Error in tokenize, mode= " + LP2._mode_names[n2]);
        System.err.println(s);
        for (int i = 0; i < n; ++i) {
            System.err.print(" ");
        }
        System.err.println("^");
        Thread.dumpStack();
        System.err.println("\n\n\n");
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
        return c == '.' || c == ',' || c == ';' || c == ':' || c == '!' || c == '?' || c == '\'' || c == '-' || c == '+' || c == '*' || c == '/' || c == '=' || c == '(' || c == ')' || c == '[' || c == ']';
    }
    
    public boolean isMathSymbol(final char c) {
        return c == '=' || c == '>' || c == '<' || c == '+' || c == '-' || c == '|' || c == '*' || c == '(' || c == ')' || c == '[' || c == ']' || c == ':' || c == ',' || c == '.' || c == '\'' || c == '/';
    }
    
    public void dumpTokens() {
        int n = 0;
        for (int i = 0; i < this.tokens.size(); ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(" ");
            }
            final String string = this.tokens.elementAt(i).toString();
            System.out.println(string + "   " + LP2._mode_names[this.types.elementAt(i)]);
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
    
    public void append(final Box current) {
        if (this.current instanceof GroupBox) {
            ((GroupBox)this.current).appendMember(current);
            current.setParent(this.current);
        }
        else {
            msg("-E- internal: current not a GroupBox in append..." + this.current);
            Thread.dumpStack();
            this.current.append(current);
            this.current = current;
        }
    }
    
    public Box buildBox() {
        if (this == null) {
            throw null;
        }
        this.master = (Box)new GroupBox();
        this.current = this.master;
        for (int i = 0; i < this.tokens.size(); ++i) {
            final String string = this.tokens.elementAt(i).toString();
            final int intValue = this.types.elementAt(i);
            if (!this.delayedCommandStack.isEmpty()) {
                msg("**** dCS: " + this.delayedCommandStack.pop() + " " + string);
            }
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
                        this.append((Box)new CharBox(string2, this.fontIndex, this.ptSize, this.colorIndex));
                    }
                    break;
                }
                case 2: {
                    final String substring = string.substring(1);
                    final int handleCommand = this.handleCommand(substring);
                    if (handleCommand > 0) {
                        this.buildBoxHandleBeginCommand(substring, handleCommand);
                    }
                    break;
                }
                case 3: {
                    for (int k = 0; k < string.length(); ++k) {
                        final String string3 = "" + string.charAt(k);
                        if (this.fontMode == 2) {
                            if (string3.startsWith("{")) {
                                this.unicode("{", 44, 102);
                            }
                            else if (string3.startsWith("}")) {
                                this.unicode("}", 44, 103);
                            }
                            else if (string3.startsWith(",")) {
                                this.unicode(",", 35, 44);
                            }
                            else if (string3.startsWith(";")) {
                                this.unicode(";", 35, 59);
                            }
                            else {
                                if (this == null) {
                                    throw null;
                                }
                                this.append((Box)new CharBox(string3, this.fontIndex, this.ptSize, this.colorIndex));
                            }
                        }
                        else {
                            if (this == null) {
                                throw null;
                            }
                            this.append((Box)new CharBox(string3, this.fontIndex, this.ptSize, this.colorIndex));
                        }
                    }
                    break;
                }
                case 4: {
                    this.buildBoxHandleBeginGroup();
                    break;
                }
                case 5: {
                    final GroupBox groupBox = (GroupBox)this.current;
                    this.buildBoxHandleEndGroup();
                    if (this.current instanceof CommandBox) {
                        final CommandBox commandBox = (CommandBox)this.current;
                        commandBox.addArgument(groupBox);
                        if (LP2.debug) {
                            msg("\nCMD: " + commandBox.getCommand());
                        }
                        if (commandBox.hasAllArguments()) {
                            commandBox.executeCommand();
                            this.buildBoxHandleEndGroup();
                        }
                    }
                    else if (this.current instanceof SubSuperScriptBox) {
                        final SubSuperScriptBox subSuperScriptBox = (SubSuperScriptBox)this.current;
                        subSuperScriptBox.setMode("none");
                        subSuperScriptBox.measure();
                        if (LP2.debug) {
                            msg("§§§ ENDGROUP:end of SubSuperScript:" + subSuperScriptBox);
                        }
                        this.buildBoxHandleEndGroup();
                    }
                    break;
                }
                case 6: {
                    msg("-W- control character, should not happen: " + string);
                    break;
                }
                case 7: {
                    this.buildBoxHandleBeginGroup();
                    if (this.fontMode == 2) {
                        this.fontIndex = 42;
                    }
                    else {
                        this.fontIndex = this.mathFontIndex;
                    }
                    break;
                }
                case 8: {
                    this.buildBoxHandleEndGroup();
                    break;
                }
                case 9: {
                    if (LP2.debug) {
                        msg("\n\n\n>>>SUPERSCRIPT>>>");
                    }
                    final GroupBox groupBox2 = (GroupBox)this.current;
                    if (groupBox2 == null) {
                        msg("-E- Internal: SUPERSCRIPT master not a group: " + this.current);
                        if (LP2.debug) {
                            Thread.dumpStack();
                        }
                        break;
                    }
                    final Box lastMember = groupBox2.getLastMember();
                    SubSuperScriptBox current;
                    if (lastMember instanceof SubSuperScriptBox) {
                        if (((SubSuperScriptBox)lastMember).getSuperscriptBox() == null) {
                            current = (SubSuperScriptBox)lastMember;
                            current.setMode("superscript");
                        }
                        else {
                            if (this == null) {
                                throw null;
                            }
                            current = new SubSuperScriptBox(this.fontIndex, this.ptSize);
                            current.setMode("superscript");
                            ((Box)current).setParent((Box)groupBox2);
                            groupBox2.appendMember(current);
                        }
                    }
                    else {
                        if (this == null) {
                            throw null;
                        }
                        current = new SubSuperScriptBox(this.fontIndex, this.ptSize);
                        current.setMode("superscript");
                        ((Box)current).setParent((Box)groupBox2);
                        groupBox2.appendMember(current);
                    }
                    this.current = (Box)current;
                    this.stack.push(this.getState());
                    this.ptSize = current.getSubscriptPtSize();
                    current.getSuperscriptBasePoint();
                    break;
                }
                case 10: {
                    if (LP2.debug) {
                        msg("\n\n\n>>>SUBSCRIPT>>>");
                    }
                    final GroupBox groupBox3 = (GroupBox)this.current;
                    if (groupBox3 == null) {
                        msg("-E- Internal: SUBSCRIPT master not a group: " + this.current);
                        if (LP2.debug) {
                            Thread.dumpStack();
                        }
                        break;
                    }
                    final Box lastMember2 = groupBox3.getLastMember();
                    SubSuperScriptBox current2;
                    if (lastMember2 instanceof SubSuperScriptBox) {
                        if (((SubSuperScriptBox)lastMember2).getSubscriptBox() == null) {
                            current2 = (SubSuperScriptBox)lastMember2;
                            current2.setMode("subscript");
                        }
                        else {
                            if (this == null) {
                                throw null;
                            }
                            current2 = new SubSuperScriptBox(this.fontIndex, this.ptSize);
                            current2.setMode("subscript");
                            ((Box)current2).setParent((Box)groupBox3);
                            groupBox3.appendMember(current2);
                        }
                    }
                    else {
                        if (this == null) {
                            throw null;
                        }
                        current2 = new SubSuperScriptBox(this.fontIndex, this.ptSize);
                        current2.setMode("subscript");
                        ((Box)current2).setParent((Box)groupBox3);
                        groupBox3.appendMember(current2);
                    }
                    this.current = (Box)current2;
                    this.stack.push(this.getState());
                    this.ptSize = current2.getSubscriptPtSize();
                    current2.getSubscriptBasePoint();
                    break;
                }
                case 11: {
                    this.handleMathSymbol(string);
                    break;
                }
                case 12: {
                    this.handleMathDigit(string);
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
    
    public void buildBoxHandleBeginGroup() {
        if (this == null) {
            throw null;
        }
        final GroupBox current = new GroupBox();
        this.append((Box)current);
        this.current = (Box)current;
        this.stack.push(this.getState());
    }
    
    public void buildBoxHandleBeginCommand(final String s, final int n) {
        if (this == null) {
            throw null;
        }
        final CommandBox current = new CommandBox(s, n);
        this.append((Box)current);
        this.current = (Box)current;
        this.stack.push(this.getState());
        try {
            this.getClass().getMethod("_" + s + "_BEGIN", (Class<?>[])new Class[0]).invoke(this, new Object[0]);
        }
        catch (Exception ex) {}
    }
    
    public void buildBoxHandleEndGroup() {
        if (this.current instanceof GroupBox) {
            this.current.measure();
            this.current.getBounds();
            if (LP2.debug) {
                msg(">>> buildBoxHandleEndGroup: " + this.current.getBounds());
            }
            final Box parent = this.current.getParent();
            if (parent != null) {
                this.current = parent;
            }
            else {
                msg("-E- internal: ENDGROUP: enclosing group is null");
                msg("token is: " + (Object)this.token);
                Thread.dumpStack();
            }
        }
        else {
            msg("-E- internal in ENDGROUP: current not a GroupBox!");
            Thread.dumpStack();
        }
        if (!this.stack.isEmpty()) {
            this.setState(this.stack.pop());
        }
        else {
            msg("-E- Internal error in buildBoxHandleEndGroup: empty stack");
        }
    }
    
    public void handleMathSymbol(final String s) {
        if (this.fontMode == 2) {
            if (s.length() != 1) {
                msg("-E- handleMathSymbol: invalid token: '" + s + "'");
                return;
            }
            switch (s.charAt(0)) {
                case ' ': {
                    this.cm_math(35, 32);
                    break;
                }
                case '\t': {
                    this.cm_math(35, 32);
                    break;
                }
                case '(': {
                    this.cm_math(35, 40);
                    break;
                }
                case ')': {
                    this.cm_math(35, 41);
                    break;
                }
                case '[': {
                    this.cm_math(35, 91);
                    break;
                }
                case ']': {
                    this.cm_math(35, 93);
                    break;
                }
                case ':': {
                    this.cm_math(35, 58);
                    break;
                }
                case '+': {
                    this.cm_math(35, 43);
                    break;
                }
                case '=': {
                    this.cm_math(35, 61);
                    break;
                }
                case '-': {
                    this.cm_math(35, 123);
                    break;
                }
                case '*': {
                    this.cm_math(44, 164);
                    break;
                }
                case '|': {
                    this.cm_math(44, 106);
                    break;
                }
                case '\'': {
                    this.cm_math(38, 39);
                    break;
                }
                case '.': {
                    this.cm_math(42, 58);
                    break;
                }
                case ',': {
                    this.cm_math(42, 59);
                    break;
                }
                case '/': {
                    this.cm_math(42, 61);
                    break;
                }
                case '<': {
                    this._thinspace();
                    this.cm_math(42, 60);
                    this._thinspace();
                    break;
                }
                case '>': {
                    this._thinspace();
                    this.cm_math(42, 62);
                    this._thinspace();
                    break;
                }
                default: {
                    msg("-E- handleMathSymbol: unhandled token: '" + s + "'");
                    break;
                }
            }
        }
        else {
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(s, this.mathSymbolFontIndex, this.ptSize, this.colorIndex));
        }
    }
    
    public void handleMathDigit(final String s) {
        if (this.fontMode == 2) {
            this.cm_math(35, s.charAt(0));
        }
        else {
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(s, this.mathSymbolFontIndex, this.ptSize, this.colorIndex));
        }
    }
    
    private void cm_math(final int n, final int n2) {
        final String string = "" + (char)n2;
        if (this == null) {
            throw null;
        }
        this.append((Box)new CharBox(string, n, this.ptSize, this.colorIndex));
    }
    
    public int handleCommand(final String s) {
        final String string = "_" + s;
        try {
            final Class[] array = new Class[0];
            final Object[] array2 = new Object[0];
            try {
                final Object invoke = this.getClass().getMethod(string, (Class<?>[])array).invoke(this, array2);
                if (invoke != null) {
                    return (int)invoke;
                }
            }
            catch (NoSuchMethodException ex4) {
                final Object invoke2 = this.getClass().getMethod(string, array2.getClass()).invoke(this, null);
                if (invoke2 != null) {
                    return (int)invoke2;
                }
            }
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
            this.append((Box)new CharBox(s, this.fontIndex, this.ptSize, this.colorIndex));
        }
        catch (Exception ex3) {
            msg("-E- exception: " + ex3 + " for command: " + s);
            ex3.printStackTrace();
        }
        return 0;
    }
    
    public int handleCommand(final String s, final Object[] array) {
        final String string = "_" + s;
        try {
            final Object invoke = this.getClass().getMethod(string, array.getClass()).invoke(this, array);
            if (invoke != null) {
                return (int)invoke;
            }
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
            this.append((Box)new CharBox(s, this.fontIndex, this.ptSize, this.colorIndex));
        }
        catch (Exception ex3) {
            msg("-E- exception: " + ex3 + " for command: " + s);
            ex3.printStackTrace();
        }
        return 0;
    }
    
    public void FIXME(final String s) {
        System.out.println("-W- function not (yet) supported: " + s);
    }
    
    public void _today() {
        final String string = new Date().toString();
        if (this == null) {
            throw null;
        }
        this.append((Box)new CharBox(string, this.fontIndex, this.ptSize, this.colorIndex));
    }
    
    public void _bf() {
        this.fontIndex |= 0x2;
    }
    
    public void _it() {
        if (this.fontMode == 2) {
            this.fontIndex |= 0x1;
        }
        else {
            this.fontIndex |= 0x1;
        }
    }
    
    public void _em() {
        this.fontIndex ^= 0x1;
    }
    
    public void _rm() {
        if (this.fontMode == 2) {
            this.fontIndex = 35;
        }
        else {
            this.fontIndex &= 0xFC;
        }
    }
    
    public void _sf() {
        if (this.fontMode == 2) {
            this.fontIndex = 38;
        }
        else {
            this.fontIndex = 16;
        }
    }
    
    public void _sl() {
        if (this.fontMode == 2) {
            this.fontIndex = 39;
        }
        else {
            this.fontIndex = 17;
        }
    }
    
    public void _sc() {
        this.FIXME("_sc: caps/small caps font not available yet.");
    }
    
    public void _mathrm() {
        if (this.fontMode == 2) {
            this.fontIndex = 35;
        }
        else {
            this.fontIndex &= 0xFC;
        }
    }
    
    public void _tt() {
        if (this.fontMode == 2) {
            this.fontIndex = 40;
        }
        else {
            this.fontIndex = 12;
        }
    }
    
    public void _btt() {
        if (this.fontMode == 2) {
            msg("-W- \\btt: no bold typewriter font in CM fonts.");
            this.fontIndex = 40;
        }
        else {
            this.fontIndex = 14;
        }
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
    
    private int __round(final double n) {
        return (int)Math.round(n);
    }
    
    public void _tiny() {
        this.ptSize = this.__round(0.5 * this.ptBaseSize);
    }
    
    public void _scriptsize() {
        this.ptSize = this.__round(0.6666666666666666 * this.ptBaseSize);
    }
    
    public void _footnotesize() {
        this.ptSize = this.__round(0.8333333333333334 * this.ptBaseSize);
    }
    
    public void _small() {
        this.ptSize = this.__round(0.9166666666666666 * this.ptBaseSize);
    }
    
    public void _normalsize() {
        this.ptSize = this.ptBaseSize;
    }
    
    public void _large() {
        this.ptSize = this.__round(1.1666666666666667 * this.ptBaseSize);
    }
    
    public void _Large() {
        this.ptSize = this.__round(1.4166666666666667 * this.ptBaseSize);
    }
    
    public void _LARGE() {
        this.ptSize = this.__round(1.6666666666666667 * this.ptBaseSize);
    }
    
    public void _huge() {
        this.ptSize = this.__round(2.0833333333333335 * this.ptBaseSize);
    }
    
    public void _Huge() {
        this.ptSize = this.__round(2.0833333333333335 * this.ptBaseSize);
    }
    
    public void _hashsign() {
        this.unicode("#", -12, 35);
    }
    
    public void _atsign() {
        this.unicode("@", -12, 64);
    }
    
    public void _lbracket() {
        this.unicode("[", -12, 91);
    }
    
    public void _backslash() {
        this.unicode("\\", 40, 92);
    }
    
    public void _rbracket() {
        this.unicode("]", -12, 93);
    }
    
    public void _lbrace() {
        this.unicode("{", 44, 102);
    }
    
    public void _verticalbar() {
        this.unicode("|", 44, 106);
    }
    
    public void _rbrace() {
        this.unicode("}", 44, 103);
    }
    
    public void _tildechar() {
        this.unicode("~", -12, 126);
    }
    
    public void _lesschar() {
        this.unicode("<", 42, 60);
    }
    
    public void _equalchar() {
        this.unicode("=", -12, 61);
    }
    
    public void _greaterchar() {
        this.unicode(">", 42, 62);
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
    
    public void _sgn() {
        this.mathfunction("sgn");
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
    
    public void _alpha() {
        this.greek("\u03b1", 42, 174);
    }
    
    public void _beta() {
        this.greek("\u03b2", 42, 175);
    }
    
    public void _gamma() {
        this.greek("\u03b3", 42, 176);
    }
    
    public void _delta() {
        this.greek("\u03b4", 42, 177);
    }
    
    public void _epsilon() {
        this.greek("\u03b5", 42, 178);
    }
    
    public void _zeta() {
        this.greek("\u03b6", 42, 179);
    }
    
    public void _eta() {
        this.greek("\u03b7", 42, 180);
    }
    
    public void _theta() {
        this.greek("\u03b8", 42, 181);
    }
    
    public void _iota() {
        this.greek("\u03b9", 42, 182);
    }
    
    public void _kappa() {
        this.greek("\u03ba", 42, 183);
    }
    
    public void _lambda() {
        this.greek("\u03bb", 42, 184);
    }
    
    public void _mu() {
        this.greek("\u03bc", 42, 185);
    }
    
    public void _nu() {
        this.greek("\u03bd", 42, 186);
    }
    
    public void _xi() {
        this.greek("\u03be", 42, 187);
    }
    
    public void _greeko() {
        this.greek("\u03bf", 42, 111);
    }
    
    public void _pi() {
        this.greek("\u03c0", 42, 188);
    }
    
    public void _rho() {
        this.greek("\u03c1", 42, 189);
    }
    
    public void _sigma() {
        this.greek("\u03c3", 42, 190);
    }
    
    public void _tau() {
        this.greek("\u03c4", 42, 191);
    }
    
    public void _upsilon() {
        this.greek("\u03c5", 42, 192);
    }
    
    public void _phi() {
        this.greek("\u03d5", 42, 193);
    }
    
    public void _chi() {
        this.greek("\u03c7", 42, 194);
    }
    
    public void _psi() {
        this.greek("\u03c8", 42, 195);
    }
    
    public void _omega() {
        this.greek("\u03c9", 42, 33);
    }
    
    public void _varepsilon() {
        this.greek("\u02b5", 42, 34);
    }
    
    public void _vartheta() {
        this.greek("\u03d1", 42, 35);
    }
    
    public void _varpi() {
        this.greek("\u03d6", 42, 36);
    }
    
    public void _varrho() {
        this.greek("\u03f1", 42, 37);
    }
    
    public void _varsigma() {
        this.greek("\u03da", 42, 38);
    }
    
    public void _varphi() {
        this.greek("\u03c6", 42, 39);
    }
    
    public void _Alpha() {
        this.greek("\u0391", 35, 65);
    }
    
    public void _Beta() {
        this.greek("\u0392", 35, 66);
    }
    
    public void _Gamma() {
        this.greek("\u0393", 35, 161);
    }
    
    public void _Delta() {
        this.greek("\u0394", 35, 162);
    }
    
    public void _Epsilon() {
        this.greek("\u0395", 35, 69);
    }
    
    public void _Zeta() {
        this.greek("\u0396", 35, 90);
    }
    
    public void _Eta() {
        this.greek("\u0397", 35, 78);
    }
    
    public void _Theta() {
        this.greek("\u0398", 35, 163);
    }
    
    public void _Iota() {
        this.greek("\u0399", 35, 73);
    }
    
    public void _Kappa() {
        this.greek("\u039a", 35, 75);
    }
    
    public void _Lambda() {
        this.greek("\u039b", 35, 164);
    }
    
    public void _Mu() {
        this.greek("\u039c", 35, 77);
    }
    
    public void _Nu() {
        this.greek("\u039d", 35, 78);
    }
    
    public void _Xi() {
        this.greek("\u039e", 35, 165);
    }
    
    public void _Greeko() {
        this.greek("\u039f", 35, 79);
    }
    
    public void _Pi() {
        this.greek("\u03a0", 35, 166);
    }
    
    public void _Rho() {
        this.greek("\u03a1", 35, 82);
    }
    
    public void _Sigma() {
        this.greek("\u03a3", 35, 167);
    }
    
    public void _Tau() {
        this.greek("\u03a4", 35, 84);
    }
    
    public void _Upsilon() {
        this.greek("\u03a5", 35, 168);
    }
    
    public void _Phi() {
        this.greek("\u03a6", 35, 169);
    }
    
    public void _Chi() {
        this.greek("\u03a7", 35, 88);
    }
    
    public void _Psi() {
        this.greek("\u03a8", 35, 170);
    }
    
    public void _Omega() {
        this.greek("\u03a9", 35, 173);
    }
    
    public void _calA() {
        this.calli("A", 44, 65);
    }
    
    public void _calB() {
        this.calli("B", 44, 66);
    }
    
    public void _calC() {
        this.calli("C", 44, 67);
    }
    
    public void _calD() {
        this.calli("D", 44, 68);
    }
    
    public void _calE() {
        this.calli("E", 44, 69);
    }
    
    public void _calF() {
        this.calli("F", 44, 70);
    }
    
    public void _calG() {
        this.calli("G", 44, 71);
    }
    
    public void _calH() {
        this.calli("H", 44, 72);
    }
    
    public void _calI() {
        this.calli("I", 44, 73);
    }
    
    public void _calJ() {
        this.calli("J", 44, 74);
    }
    
    public void _calK() {
        this.calli("K", 44, 75);
    }
    
    public void _calL() {
        this.calli("L", 44, 76);
    }
    
    public void _calM() {
        this.calli("M", 44, 77);
    }
    
    public void _calN() {
        this.calli("N", 44, 78);
    }
    
    public void _calO() {
        this.calli("O", 44, 79);
    }
    
    public void _calP() {
        this.calli("P", 44, 80);
    }
    
    public void _calQ() {
        this.calli("Q", 44, 81);
    }
    
    public void _calR() {
        this.calli("R", 44, 82);
    }
    
    public void _calS() {
        this.calli("S", 44, 83);
    }
    
    public void _calT() {
        this.calli("T", 44, 84);
    }
    
    public void _calU() {
        this.calli("U", 44, 85);
    }
    
    public void _calV() {
        this.calli("V", 44, 86);
    }
    
    public void _calW() {
        this.calli("W", 44, 87);
    }
    
    public void _calX() {
        this.calli("X", 44, 88);
    }
    
    public void _calY() {
        this.calli("Y", 44, 89);
    }
    
    public void _calZ() {
        this.calli("Z", 44, 90);
    }
    
    public void _aleph() {
        this.unicode("\u2135", 44, 64);
    }
    
    public void _hbar() {
        this.unicode("\u210f", 48, 126);
    }
    
    public void _imath() {
        this.unicode("FIXME", 42, 123);
    }
    
    public void _jmath() {
        this.unicode("FIXME", 42, 124);
    }
    
    public void _ell() {
        this.unicode("\u2113", 42, 96);
    }
    
    public void _wp() {
        this.unicode("\u2118", 42, 125);
    }
    
    public void _Re() {
        this.unicode("\u211c", 44, 60);
    }
    
    public void _Im() {
        this.unicode("\u2111", 44, 61);
    }
    
    public void _partial() {
        this.unicode("\u2202", 42, 64);
    }
    
    public void _infty() {
        this.unicode("\u221e", 44, 49);
    }
    
    public void _prime() {
        this.unicode("\u2032", 44, 48);
    }
    
    public void _emptyset() {
        this.unicode("\u2205", 44, 59);
    }
    
    public void _nabla() {
        this.unicode("\u2207", 44, 114);
    }
    
    public void _surd() {
        this.unicode("\u221a", 44, 112);
    }
    
    public void _top() {
        this.unicode("\u22a4", 44, 62);
    }
    
    public void _bot() {
        this.unicode("\u22a5", 44, 63);
    }
    
    public void _doublebar() {
        this.unicode("FIXME", 44, 107);
    }
    
    public void _angle() {
        this.unicode("\u2220", 47, 92);
    }
    
    public void _triangle() {
        this.unicode("\u2206", 44, 52);
    }
    
    public void _mbackslash() {
        this.unicode("\\", 44, 110);
    }
    
    public void _forall() {
        this.unicode("\u2200", 44, 56);
    }
    
    public void _exists() {
        this.unicode("\u2203", 44, 57);
    }
    
    public void _neg() {
        this.unicode("¬", 44, 58);
    }
    
    public void _flat() {
        this.unicode("FIXME", 42, 91);
    }
    
    public void _natural() {
        this.unicode("FIXME", 42, 92);
    }
    
    public void _sharp() {
        this.unicode("FIXME", 42, 93);
    }
    
    public void _clubsuit() {
        this.unicode("\u2663", 44, 124);
    }
    
    public void _diamondsuit() {
        this.unicode("\u2666", 44, 125);
    }
    
    public void _heartsuit() {
        this.unicode("\u2665", 44, 126);
    }
    
    public void _spadesuit() {
        this.unicode("\u2660", 44, 196);
    }
    
    public void _oldstyle() {
        this.FIXME("oldstyle");
    }
    
    public void _sum() {
        this.unicode("\u2211", -12, 167);
    }
    
    public void _prod() {
        this.unicode("\u220f", -12, 166);
    }
    
    public void _coprod() {
        this.unicode("\u2210", 44, 113);
    }
    
    public void _int() {
        this.unicode("\u222b", 44, 115);
    }
    
    public void _oint() {
        this.unicode("\u222e", 46, 72);
    }
    
    public void _bigcap() {
        this.big_opr("\u2229", 44, 92);
    }
    
    public void _bigcup() {
        this.big_opr("\u222a", 44, 91);
    }
    
    public void _bigsqcup() {
        this.big_opr("\u2294", 44, 116);
    }
    
    public void _bigvee() {
        this.big_opr("\u2228", 44, 95);
    }
    
    public void _bigwedge() {
        this.big_opr("\u2227", 44, 94);
    }
    
    public void _bigodot() {
        this.big_opr("\u2299", 44, 175);
    }
    
    public void _bigotimes() {
        this.big_opr("\u2297", 44, 173);
    }
    
    public void _bigoplus() {
        this.big_opr("\u2295", 44, 169);
    }
    
    public void _biguplus() {
        this.big_opr("\u228e", 44, 93);
    }
    
    public int _Sum(final Object[] array) {
        if (array == null) {
            return 2;
        }
        this.setState(this.stack.pop());
        if (this == null) {
            throw null;
        }
        final CharBox charBox = new CharBox("§", 35, 2 * this.ptSize, this.colorIndex);
        charBox.setYOffset((int)(-0.3 * this.ptSize));
        return this.__big_operator(array, (Box)charBox);
    }
    
    public void _Sum_BEGIN() {
        this.__big_operator_begin();
    }
    
    public int _Prod(final Object[] array) {
        if (array == null) {
            return 2;
        }
        this.setState(this.stack.pop());
        if (this == null) {
            throw null;
        }
        final CharBox charBox = new CharBox("¦", 35, 2 * this.ptSize, this.colorIndex);
        charBox.setYOffset((int)(-0.3 * this.ptSize));
        return this.__big_operator(array, (Box)charBox);
    }
    
    public void _Prod_BEGIN() {
        this.__big_operator_begin();
    }
    
    public int _Int(final Object[] array) {
        if (array == null) {
            return 2;
        }
        this.setState(this.stack.pop());
        if (this == null) {
            throw null;
        }
        return this.__big_operator(array, (Box)new CharBox("s", 44, 2 * this.ptSize, this.colorIndex));
    }
    
    public void _Int_BEGIN() {
        this.__big_operator_begin();
    }
    
    private void __big_operator_begin() {
        this.stack.push(this.getState());
        if (this == null) {
            throw null;
        }
        this.ptSize = new SubSuperScriptBox(this.fontIndex, this.ptSize).getSubscriptPtSize();
    }
    
    private int __big_operator(final Object[] array, final Box centerBox) {
        final GroupBox bottomBox = (GroupBox)array[0];
        final GroupBox topBox = (GroupBox)array[1];
        final GroupBox groupBox = (GroupBox)((Box)bottomBox).getParent();
        groupBox.deleteAllMembers();
        centerBox.moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        if (this == null) {
            throw null;
        }
        final StackBox stackBox = new StackBox();
        ((Box)stackBox).setParent(((Box)groupBox).getParent());
        ((Box)stackBox).moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        stackBox.setCenterBox(centerBox);
        stackBox.setTopBox(topBox);
        stackBox.setBottomBox(bottomBox);
        stackBox.measure();
        groupBox.appendMember(stackBox);
        groupBox.measure();
        return 0;
    }
    
    public int _lstack(final Object[] array) {
        return this.__stack(array, 1);
    }
    
    public int _cstack(final Object[] array) {
        return this.__stack(array, 2);
    }
    
    public int _rstack(final Object[] array) {
        return this.__stack(array, 3);
    }
    
    private int __stack(final Object[] array, final int alignment) {
        if (array == null) {
            return 3;
        }
        final GroupBox topBox = (GroupBox)array[0];
        final GroupBox centerBox = (GroupBox)array[1];
        final GroupBox bottomBox = (GroupBox)array[2];
        final GroupBox groupBox = (GroupBox)((Box)centerBox).getParent();
        groupBox.deleteAllMembers();
        ((Box)centerBox).moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        topBox.measure();
        centerBox.measure();
        bottomBox.measure();
        ((Box)topBox).getW();
        ((Box)centerBox).getW();
        ((Box)bottomBox).getW();
        if (this == null) {
            throw null;
        }
        final StackBox stackBox = new StackBox();
        ((Box)stackBox).moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        ((Box)stackBox).setParent(((Box)groupBox).getParent());
        stackBox.setTopBox(topBox);
        stackBox.setBottomBox(bottomBox);
        stackBox.setCenterBox(centerBox);
        stackBox.setAlignment(alignment);
        stackBox.measure();
        groupBox.appendMember(stackBox);
        groupBox.measure();
        return 0;
    }
    
    public int _frac(final Object[] array) {
        if (array == null) {
            return 2;
        }
        final GroupBox topBox = (GroupBox)array[0];
        final GroupBox bottomBox = (GroupBox)array[1];
        final GroupBox groupBox = (GroupBox)((Box)bottomBox).getParent();
        groupBox.deleteAllMembers();
        bottomBox.measure();
        topBox.measure();
        final Box _make_line = this.__make_line(((Box)groupBox).getX(), ((Box)groupBox).getY(), Math.max(((Box)bottomBox).getW(), ((Box)topBox).getW()));
        if (this == null) {
            throw null;
        }
        final StackBox stackBox = new StackBox();
        ((Box)stackBox).moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        ((Box)stackBox).setParent(((Box)groupBox).getParent());
        stackBox.setTopBox(topBox);
        stackBox.setBottomBox(bottomBox);
        stackBox.setCenterBox(_make_line);
        stackBox.measure();
        groupBox.appendMember(stackBox);
        groupBox.measure();
        return 0;
    }
    
    public void _minus() {
        this.binaryop("\u2212", 44, 161);
    }
    
    public void _slash() {
        this.binaryop("/", 44, 61);
    }
    
    public void _pm() {
        this.binaryop("²", 44, 167);
    }
    
    public void _mp() {
        this.binaryop("\u2213", 44, 168);
    }
    
    public void _setminus() {
        this.binaryop("\u2216", 44, 110);
    }
    
    public void _cdot() {
        this.binaryop("\u22c5", 44, 162);
    }
    
    public void _cdotvar() {
        this.binaryop("·", 44, 162);
    }
    
    public void _times() {
        this.binaryop("\u00d7", 44, 163);
    }
    
    public void _ast() {
        this.binaryop("\u2217", 44, 164);
    }
    
    public void _star() {
        this.binaryop("\u22c6", 42, 63);
    }
    
    public void _diamond() {
        this.binaryop("\u22c4", 44, 166);
    }
    
    public void _circ() {
        this.binaryop("\u2218", 44, 177);
    }
    
    public void _bullet() {
        this.binaryop("\u2219", 44, 178);
    }
    
    public void _div() {
        this.binaryop("\u00f7", 44, 165);
    }
    
    public void _cap() {
        this.binaryop("\u2229", 44, 92);
    }
    
    public void _cup() {
        this.binaryop("\u222a", 44, 91);
    }
    
    public void _uplus() {
        this.binaryop("\u228e", 44, 93);
    }
    
    public void _sqcap() {
        this.binaryop("\u2293", 44, 117);
    }
    
    public void _sqcup() {
        this.binaryop("\u2294", 44, 116);
    }
    
    public void _triangleleft() {
        this.binaryop("\u22b2", 42, 47);
    }
    
    public void _triangleright() {
        this.binaryop("\u22b3", 42, 46);
    }
    
    public void _wr() {
        this.binaryop("\u2240", 44, 111);
    }
    
    public void _bigcirc() {
        this.binaryop("\u25cb", 44, 176);
    }
    
    public void _bigtriangleup() {
        this.binaryop("\u2206", 44, 52);
    }
    
    public void _bigtriangledown() {
        this.binaryop("\u2207", 44, 53);
    }
    
    public void _vee() {
        this.binaryop("\u2228", 44, 95);
    }
    
    public void _wedge() {
        this.binaryop("\u2227", 44, 94);
    }
    
    public void _oplus() {
        this.binaryop("\u2295", 44, 169);
    }
    
    public void _ominus() {
        this.binaryop("\u2296", 44, 170);
    }
    
    public void _otimes() {
        this.binaryop("\u2297", 44, 173);
    }
    
    public void _oslash() {
        this.binaryop("\u2298", 44, 174);
    }
    
    public void _odot() {
        this.binaryop("\u2299", 44, 175);
    }
    
    public void _dagger() {
        this.binaryop("\u2020", 44, 121);
    }
    
    public void _ddagger() {
        this.binaryop("\u2021", 44, 122);
    }
    
    public void _amalg() {
        this.binaryop("\u2210", 44, 113);
    }
    
    public void _lessthan() {
        this.relation("<", 42, 60);
    }
    
    public void _greaterthan() {
        this.relation(">", 42, 62);
    }
    
    public void _equals() {
        this.relation("=", 35, 61);
    }
    
    public void _leq() {
        this.relation("\u2264", 44, 183);
    }
    
    public void _prec() {
        this.relation("\u227a", 44, 193);
    }
    
    public void _preceq() {
        this.relation("\u227c", 44, 185);
    }
    
    public void _ll() {
        this.relation("\u226a", 44, 191);
    }
    
    public void _subset() {
        this.relation("\u2282", 44, 189);
    }
    
    public void _subseteq() {
        this.relation("\u2286", 44, 181);
    }
    
    public void _sqsubseteq() {
        this.relation("\u2291", 44, 118);
    }
    
    public void _in() {
        this.relation("\u2208", 44, 50);
    }
    
    public void _vdash() {
        this.relation("\u22a2", 44, 96);
    }
    
    public void _smile() {
        this.relation("\u203f", 42, 94);
    }
    
    public void _frown() {
        this.relation("\u2040", 42, 95);
    }
    
    public void _geq() {
        this.relation("\u2265", 44, 184);
    }
    
    public void _succ() {
        this.relation("\u227b", 44, 194);
    }
    
    public void _succeq() {
        this.relation("\u227d", 44, 186);
    }
    
    public void _gg() {
        this.relation("\u226b", 44, 192);
    }
    
    public void _supset() {
        this.relation("\u2283", 44, 190);
    }
    
    public void _supseteq() {
        this.relation("\u2287", 44, 182);
    }
    
    public void _sqsupseteq() {
        this.relation("\u2292", 44, 119);
    }
    
    public void _ni() {
        this.relation("\u220b", 44, 51);
    }
    
    public void _dashv() {
        this.relation("\u22a3", 44, 97);
    }
    
    public void _mid() {
        this.relation("\u2223", 44, 106);
    }
    
    public void _parallel() {
        this.relation("\u2225", 44, 107);
    }
    
    public void _equiv() {
        this.relation("\u2261", 44, 180);
    }
    
    public void _sim() {
        this.relation("\u223c", 44, 187);
    }
    
    public void _simeq() {
        this.relation("\u22d6", 44, 39);
    }
    
    public void _asymp() {
        this.relation("\u224d", 44, 179);
    }
    
    public void _approx() {
        this.relation("\u2248", 44, 188);
    }
    
    public void _cong() {
        this.relation("\u2245", -13, 0);
    }
    
    public void _bowtie() {
        this.relation("\u22c8", -13, 0);
    }
    
    public void _propto() {
        this.relation("\u221d", 44, 47);
    }
    
    public void _models() {
        this.relation("\u22a8", 47, 178);
    }
    
    public void _doteq() {
        this.relation("\u2250", 47, 36);
    }
    
    public void _perp() {
        this.relation("\u22a5", 44, 63);
    }
    
    public void _not() {
        this._not_internal();
    }
    
    public void _notin() {
        this._not();
        this._in();
    }
    
    public void _leftarrow() {
        this.relation("\u2190", 44, 195);
    }
    
    public void _Leftarrow() {
        this.relation("\u21d0", 44, 40);
    }
    
    public void _rightarrow() {
        this.relation("\u2192", 44, 33);
    }
    
    public void _Rightarrow() {
        this.relation("\u21d2", 44, 41);
    }
    
    public void _leftrightarrow() {
        this.relation("\u2194", 44, 36);
    }
    
    public void _Leftrightarrow() {
        this.relation("\u21d4", 44, 44);
    }
    
    public void _mapsto() {
        this.relation("\u21a6", 44, 55, 44, 33, 0);
    }
    
    public void _hookleftarrow() {
        this.relation("\u21a9", 44, 195, 42, 45, -4);
    }
    
    public void _leftharpoonup() {
        this.relation("\u21bc", 42, 40);
    }
    
    public void _leftharpoondown() {
        this.relation("\u21bd", 42, 41);
    }
    
    public void _rightleftharpoons() {
        this.FIXME("_rightleftharpoons");
    }
    
    public void _longleftarrow() {
        this.relation("\u2190", 44, 195, 44, 161, -4);
    }
    
    public void _Longleftarrow() {
        this.relation("\u21d0", 44, 40, 35, 61, -4);
    }
    
    public void _longrightarrow() {
        this.relation("\u2192", 44, 161, 44, 33, -4);
    }
    
    public void _Longrightarrow() {
        this.relation("\u21d2", 35, 61, 44, 41, -4);
    }
    
    public void _longleftrightarrow() {
        this.relation("\u2194", 44, 195, 44, 33, -4);
    }
    
    public void _Longleftrightarrow() {
        this.relation("\u21d4", 44, 40, 44, 41, -4);
    }
    
    public void _longmapsto() {
        this.relation("\u21a6", 44, 55, 44, 33, 0);
    }
    
    public void _hookrightarrow() {
        this.relation("\u21aa", 42, 44, 44, 33, -4);
    }
    
    public void _rightharpoonup() {
        this.relation("\u21c0", 42, 42);
    }
    
    public void _rightharpoondown() {
        this.relation("\u21c1", 42, 43);
    }
    
    public void _uparrow() {
        this.relation("\u2191", 44, 34);
    }
    
    public void _Uparrow() {
        this.relation("\u21d1", 44, 42);
    }
    
    public void _downarrow() {
        this.relation("\u2193", 44, 35);
    }
    
    public void _Downarrow() {
        this.relation("\u21d3", 44, 43);
    }
    
    public void _updownarrow() {
        this.relation("\u2195", 44, 108);
    }
    
    public void _Updownarrow() {
        this.relation("\u21d5", 44, 109);
    }
    
    public void _nearrow() {
        this.relation("\u2197", 44, 45);
    }
    
    public void _searrow() {
        this.relation("\u2198", 44, 46);
    }
    
    public void _swarrow() {
        this.relation("\u2199", 44, 38);
    }
    
    public void _nwarrow() {
        this.relation("\u2196", 44, 37);
    }
    
    public void _buildrel() {
        this.FIXME("buildrel not supported.");
    }
    
    public void _looparrowleft() {
        this.unicode("\u21ab", 47, 34);
    }
    
    public void _looparrowright() {
        this.unicode("\u21ac", 47, 35);
    }
    
    public void _leftrightsquigarrow() {
        this.unicode("\u21ad", 47, 33);
    }
    
    public void _circlearrowleft() {
        this.unicode("\u21ba", 47, 169);
    }
    
    public void _circlearrowright() {
        this.unicode("\u21bb", 47, 168);
    }
    
    public void _nleftarrow() {
        this.unicode("\u219a", 48, 56);
    }
    
    public void _nrightarrow() {
        this.unicode("\u219b", 48, 57);
    }
    
    public void _nleftrightarrow() {
        this.unicode("\u21ae", 48, 61);
    }
    
    public void _nLeftarrow() {
        this.unicode("\u21cd", 48, 58);
    }
    
    public void _nLeftrightarrow() {
        this.unicode("\u21ce", 48, 60);
    }
    
    public void _nRightarrow() {
        this.unicode("\u21cf", 48, 59);
    }
    
    public void _lbrack() {
        this.unicode("[", 35, 91);
    }
    
    public void _lfloor() {
        this.unicode("FIXME", 44, 98);
    }
    
    public void _lceil() {
        this.unicode("\u02e5", 44, 100);
    }
    
    public void _langle() {
        this.unicode("FIXME", 44, 104);
    }
    
    public void _lmoustache() {
        this.FIXME("lmoustache");
    }
    
    public void _lgroup() {
        this.FIXME("lgroup");
    }
    
    public void _rbrack() {
        this.unicode("]", 35, 93);
    }
    
    public void _rfloor() {
        this.unicode("FIXME", 44, 99);
    }
    
    public void _rceil() {
        this.unicode("\u02e9", 44, 101);
    }
    
    public void _rangle() {
        this.unicode("FIXME", 44, 105);
    }
    
    public void _rmoustache() {
        this.FIXME("rmoustache");
    }
    
    public void _rgroup() {
        this.FIXME("rgroup");
    }
    
    public void _arrowvert() {
        this.FIXME("arrowvert");
    }
    
    public void _Arrowvert() {
        this.FIXME("Arrowvert");
    }
    
    public void _bracevert() {
        this.FIXME("bracevert");
    }
    
    public void _colon() {
        this.unicode(":", 35, 58);
    }
    
    public void _ldots() {
        this._ldots_internal();
    }
    
    public void _cdots() {
        this._cdots_internal();
    }
    
    public void _ne() {
        this._neq();
    }
    
    public void _neq() {
        this.relation("\u2260", 35, 61, 42, 61, -10);
    }
    
    public void _le() {
        this._leq();
    }
    
    public void _ge() {
        this._geq();
    }
    
    public void _to() {
        this._rightarrow();
    }
    
    public void _gets() {
        this._leftarrow();
    }
    
    public void _owns() {
        this._ni();
    }
    
    public void _land() {
        this._wedge();
    }
    
    public void _lor() {
        this._vee();
    }
    
    public void _lnot() {
        this._neg();
    }
    
    public void _vert() {
        this._verticalbar();
    }
    
    public void _Vert() {
        this._parallel();
    }
    
    public void _iff() {
        this._thinspace();
        this._Longleftrightarrow();
        this._thinspace();
    }
    
    public void _S() {
        this.unicode("§", 44, 120);
    }
    
    public void _P() {
        this.unicode("¶", 44, 123);
    }
    
    public void _dag() {
        this.unicode("\u2020", 44, 121);
    }
    
    public void _ddag() {
        this.unicode("\u2021", 44, 122);
    }
    
    public void _sqsubset() {
        this.unicode("\u228f");
    }
    
    public void _define() {
        this.unicode("\u225d", -13, 0);
    }
    
    public void _Join() {
        this.unicode("\u22c8");
    }
    
    public void _sqsupset() {
        this.unicode("\u2290");
    }
    
    public void _vdots() {
        this.unicode("\u22ee");
    }
    
    public void _ddots() {
        this.unicode("\u22f1");
    }
    
    public void _Box() {
        this.unicode("\u25a1");
    }
    
    public void _Diamond() {
        this.unicode("\u25ca");
    }
    
    public void _mho() {
        this.unicode("\u2127");
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
    
    public void _setZ() {
        this.unicode("\u2124", 48, 90);
    }
    
    public void _setN() {
        this.unicode("\u2115", 48, 78);
    }
    
    public void _setR() {
        this.unicode("\u211d", 48, 82);
    }
    
    public void _celsius() {
        this.unicode("\u2103", 35, 186, 35, 67, 3);
    }
    
    public void _fahrenheit() {
        this.unicode("\u2109", 35, 186, 35, 70, 3);
    }
    
    public void _complement() {
        this.unicode("\u2201", 47, 123);
    }
    
    public void _nexists() {
        this.unicode("\u2204", 48, 64);
    }
    
    public void _varnothing() {
        this.unicode("\u2205", 44, 59);
    }
    
    public void _mathslash() {
        this.unicode("\u2215", 42, 60);
    }
    
    public void _root() {
        this.unicode("\u221a", 44, 112);
    }
    
    public void _cubicroot() {
        this.unicode("\u221b", -13, 0);
    }
    
    public void _fourthroot() {
        this.unicode("\u221c", -13, 0);
    }
    
    public void _varpropto() {
        this.unicode("\u221d", 44, 47);
    }
    
    public void _lhd() {
        this.unicode("\u22b2");
    }
    
    public void _rhd() {
        this.unicode("\u22b3");
    }
    
    public void _unlhd() {
        this.unicode("\u22b4");
    }
    
    public void _unrhd() {
        this.unicode("\u22b5");
    }
    
    public void _iint() {
        this.unicode("\u222c", -13, 0);
    }
    
    public void _iiint() {
        this.unicode("\u222d", -13, 0);
    }
    
    public void _telephone() {
        this.unicode("\u260e", -13, 0);
    }
    
    public void _smiley() {
        this.unicode("\u263a", -13, 0);
    }
    
    public void _smileyblack() {
        this.unicode("\u263b", -13, 0);
    }
    
    public void _male() {
        this.unicode("\u2642", -13, 0);
    }
    
    public void _female() {
        this.unicode("\u2640", -13, 0);
    }
    
    public void _proportional() {
        this.unicode("\u2243");
    }
    
    public void _approxeq() {
        this.unicode("\u2245");
    }
    
    public int _pmb() {
        this.FIXME("\\pmb#1");
        return 1;
    }
    
    public int _hat(final Object[] array) {
        return this.accent("\\hat", array);
    }
    
    public int _check(final Object[] array) {
        return this.accent("\\check", array);
    }
    
    public int _acute(final Object[] array) {
        return this.accent("\\acute", array);
    }
    
    public int _grave(final Object[] array) {
        return this.accent("\\grave", array);
    }
    
    public int _bar(final Object[] array) {
        return this.accent("\\bar", array);
    }
    
    public int _vec(final Object[] array) {
        return this.accent("\\vec", array);
    }
    
    public int _dot(final Object[] array) {
        return this.accent("\\dot", array);
    }
    
    public int _ddot(final Object[] array) {
        return this.accent("\\ddot", array);
    }
    
    public int _tilde(final Object[] array) {
        return this.accent("\\tilde", array);
    }
    
    public int _dq(final Object[] array) {
        return this.accent("\\dq", array);
    }
    
    public int accent(final String s, final Object[] array) {
        if (array == null) {
            return 1;
        }
        CharBox topBox;
        if (this.fontMode == 2) {
            if ("\\hat".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("^", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\dot".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("_", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\tilde".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("~", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\acute".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("µ", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\check".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("¶", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\grave".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("¸", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\bar".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("¹", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\ddot".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("\u00c4", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\dq".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("\"", 35, this.ptSize, this.colorIndex);
            }
            else if ("\\vec".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("~", 42, this.ptSize, this.colorIndex);
            }
            else if ("\\tinyinversegrave".equals(s)) {
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("\u00c4", 42, this.ptSize, this.colorIndex);
            }
            else {
                msg("-E- accent: unknown command: " + s);
                if (this == null) {
                    throw null;
                }
                topBox = new CharBox("#", 35, this.ptSize, this.colorIndex);
            }
        }
        else {
            msg("-E- Internal: accent command '" + s + "' only supported " + "when using the Computer Modern fonts, sorry.");
            if (this == null) {
                throw null;
            }
            topBox = new CharBox(s, this.fontIndex, this.ptSize, this.colorIndex);
        }
        final GroupBox centerBox = (GroupBox)array[0];
        final GroupBox groupBox = (GroupBox)((Box)centerBox).getParent();
        if (this == null) {
            throw null;
        }
        final StackBox stackBox = new StackBox();
        stackBox.setAlignment(2);
        ((Box)stackBox).setParent(((Box)centerBox).getParent());
        ((Box)stackBox).moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        stackBox.setCenterBox(centerBox);
        stackBox.setTopBox(topBox);
        stackBox.measure();
        groupBox.deleteMember(centerBox);
        groupBox.appendMember(stackBox);
        groupBox.measure();
        return 0;
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
    
    public int _overbrace() {
        this.FIXME("overbrace#1");
        return 1;
    }
    
    public int _underbrace() {
        this.FIXME("underbrace#1");
        return 1;
    }
    
    public void _over() {
        this.FIXME("#1\\over#2: Please use \\frac{#1}{#2} instead");
    }
    
    public int _sqrtn() {
        this.FIXME("sqrt#1#2");
        return 2;
    }
    
    public int _underline(final Object[] array) {
        if (array == null) {
            return 1;
        }
        final GroupBox groupBox = (GroupBox)array[0];
        groupBox.measure();
        final GroupBox groupBox2 = (GroupBox)this.__make_line(0.0, 0.0, ((Box)groupBox).getW());
        if (this == null) {
            throw null;
        }
        return this.__stack(new Object[] { new GroupBox(), array[0], groupBox2 }, 2);
    }
    
    public int _overline(final Object[] array) {
        if (array == null) {
            return 1;
        }
        final GroupBox groupBox = (GroupBox)array[0];
        groupBox.measure();
        final GroupBox groupBox2 = (GroupBox)this.__make_line(0.0, 0.0, ((Box)groupBox).getW());
        if (this == null) {
            throw null;
        }
        return this.__stack(new Object[] { groupBox2, array[0], new GroupBox() }, 2);
    }
    
    public int _sqrt(final Object[] array) {
        if (array == null) {
            return 1;
        }
        final GroupBox groupBox = (GroupBox)array[0];
        final GroupBox parent = (GroupBox)((Box)groupBox).getParent();
        if (this == null) {
            throw null;
        }
        final SqrtBox sqrtBox = new SqrtBox(groupBox);
        ((Box)sqrtBox).setParent((Box)parent);
        ((Box)sqrtBox).moveTo(((Box)groupBox).getX(), ((Box)groupBox).getY());
        parent.deleteMember(groupBox);
        parent.appendMember(sqrtBox);
        return 0;
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
    
    public void _lll() {
        this.unicode("\u22d8");
    }
    
    public void _ggg() {
        this.unicode("\u22d9");
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
        this.unicode("¶", 44, 123);
    }
    
    public void _degrees() {
        this.unicode("°", 35, 186);
    }
    
    public void _square() {
        this.unicode("\u22a1");
    }
    
    public void _not_internal() {
        this._thinspace();
        this._thinspace();
        CharBox charBox;
        if (this.fontMode == 2) {
            if (this == null) {
                throw null;
            }
            charBox = new CharBox("=", 42, this.ptSize, this.colorIndex);
        }
        else {
            if (this == null) {
                throw null;
            }
            charBox = new CharBox("/", this.fontIndex, this.ptSize, this.colorIndex);
        }
        ((Box)charBox).measure();
        this.append((Box)charBox);
        this.__space(-10 - ((Box)charBox).getW());
    }
    
    public void _ldots_internal() {
        if (this.fontMode == 2) {
            this.cm_math(35, 46);
            this.cm_math(35, 46);
            this.cm_math(35, 46);
        }
        else {
            this.unicode("\u2026");
        }
    }
    
    public void _cdots_internal() {
        if (this.fontMode == 2) {
            this.cm_math(44, 162);
            this.cm_math(44, 162);
            this.cm_math(44, 162);
        }
        else {
            this.unicode("\u22ef");
        }
    }
    
    public void _strut() {
        final FontMetrics fontMetrics = this.fontCache.getFontMetrics(this.fontIndex, this.ptSize);
        if (this == null) {
            throw null;
        }
        final Box box = new Box();
        box.setA(fontMetrics.getMaxAscent());
        box.setD(fontMetrics.getMaxDescent());
        box.setW(0);
        this.append(box);
    }
    
    public void __space(final int w) {
        if (this == null) {
            throw null;
        }
        final Box box = new Box();
        box.setW(w);
        this.append(box);
    }
    
    public void _thinspace() {
        this.__space(5);
    }
    
    public void _mskip_thin() {
        this.__space(5);
    }
    
    public void _mskip_med() {
        this.__space(10);
    }
    
    public void _mskip_thick() {
        this.__space(20);
    }
    
    public void _mskip_back() {
        this.__space(-5);
    }
    
    public void _qquad() {
        this.__space(40);
    }
    
    public void _emspace() {
        if (this == null) {
            throw null;
        }
        final CharBox charBox = new CharBox("M", this.fontIndex, this.ptSize, this.colorIndex);
        charBox.measure();
        if (this == null) {
            throw null;
        }
        final Box box = new Box();
        box.setW(((Box)charBox).getW());
        this.append(box);
    }
    
    public Box __make_line(final double n, final double n2, final double n3) {
        if (this == null) {
            throw null;
        }
        final GroupBox groupBox = new GroupBox();
        ((Box)groupBox).moveTo((int)n, (int)n2);
        if (this.fontMode != 2) {
            System.err.println("__make_line Times not yet");
            Thread.dumpStack();
            return (Box)groupBox;
        }
        final String s = "|";
        if (this == null) {
            throw null;
        }
        final CharBox charBox = new CharBox(s, 35, this.ptSize, this.colorIndex);
        charBox.measure();
        final double n4 = ((Box)charBox).getW();
        final int w = (int)(-Math.max(1.0, n4 / 10.0));
        groupBox.appendMember(charBox);
        Box box;
        CharBox charBox2;
        for (double n5 = n4; n5 < n3; n5 += box.getW() + ((Box)charBox2).getW()) {
            if (this == null) {
                throw null;
            }
            box = new Box();
            if (this == null) {
                throw null;
            }
            charBox2 = new CharBox(s, 35, this.ptSize, this.colorIndex);
            if (n5 + n4 >= n3) {
                box.setW((int)(n3 - n5 - n4));
            }
            else {
                box.setW(w);
            }
            groupBox.appendMember(box);
            groupBox.appendMember(charBox2);
        }
        groupBox.measure();
        return (Box)groupBox;
    }
    
    void greek(final String s) {
        if (this == null) {
            throw null;
        }
        this.append((Box)new CharBox(s, this.mathGreekFontIndex, this.ptSize, this.colorIndex));
    }
    
    void greek(final String s, final int n, final int n2) {
        if (this.fontMode == 2) {
            final String string = "" + (char)n2;
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(string, n, this.ptSize, this.colorIndex));
        }
        else {
            this.greek(s);
        }
    }
    
    void calli(final String s, final int n, final int n2) {
        if (this.fontMode == 2) {
            final String string = "" + (char)n2;
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(string, n, this.ptSize, this.colorIndex));
        }
        else {
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(s, 33, this.ptSize, this.colorIndex));
        }
    }
    
    void unicode(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.fontMode == 2) {
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox("" + (char)n2, n, this.ptSize, this.colorIndex));
            this.__space(n5);
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox("" + (char)n4, n3, this.ptSize, this.colorIndex));
        }
        else {
            this.unicode(s);
        }
    }
    
    void unicode(final String s, int n, final int n2) {
        if (this.fontMode == 2) {
            if (n == -13) {
                msg("-W- Symbol not supported in CM_FONTS mode: '" + s + "'" + " " + Integer.toHexString(s.charAt(0)));
                this.unicode(s);
            }
            else {
                if (n == -12) {
                    n = 35;
                }
                final String string = "" + (char)n2;
                if (this == null) {
                    throw null;
                }
                this.append((Box)new CharBox(string, n, this.ptSize, this.colorIndex));
            }
        }
        else {
            this.unicode(s);
        }
    }
    
    void unicode(final String s) {
        if (this == null) {
            throw null;
        }
        this.append((Box)new CharBox(s, this.mathSymbolFontIndex, this.ptSize, this.colorIndex));
    }
    
    void big_opr(final String s, final int n, final int n2) {
        final int ptSize = this.ptSize;
        this.ptSize *= (int)1.4;
        this.unicode(s, n, n2);
        this.ptSize = ptSize;
    }
    
    void binaryop(final String s, int n, final int n2) {
        this._thinspace();
        if (this.fontMode == 2 && n != -13) {
            if (n == -12) {
                n = 35;
            }
            final String string = "" + (char)n2;
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(string, n, this.ptSize, this.colorIndex));
        }
        else {
            this.unicode(s);
        }
        this._thinspace();
    }
    
    void relation(final String s, final int n, final int n2) {
        this.binaryop(s, n, n2);
    }
    
    void relation(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.fontMode == 2) {
            this._thinspace();
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox("" + (char)n2, n, this.ptSize, this.colorIndex));
            if (n5 != 0) {
                this.__space(n5);
            }
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox("" + (char)n4, n3, this.ptSize, this.colorIndex));
            this._thinspace();
        }
        else {
            this.binaryop(s, -13, 0);
        }
    }
    
    void mathfunction(final String s) {
        if (this.fontMode == 2) {
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(s, 35, this.ptSize, this.colorIndex));
        }
        else {
            final boolean b = false;
            if (this == null) {
                throw null;
            }
            this.append((Box)new CharBox(s, (int)(b ? 1 : 0), this.ptSize, this.colorIndex));
        }
    }
    
    void space(final int w) {
        if (this == null) {
            throw null;
        }
        final Box box = new Box();
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
    
    public void setFontPtSize(final int n) {
        this.ptSize = n;
        this.ptBaseSize = n;
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
    
    public void selftest(final String text) {
        this.fontIndex = 35;
        this.ptSize = 24;
        this.parse(text);
        final Box buildBox = this.buildBox();
        if (LP2.debug) {
            buildBox.dump(System.out);
        }
        try {
            ++LP2.count;
            final FileOutputStream fileOutputStream = new FileOutputStream("hugo" + LP2.count + ".fig");
            final FigCompound figCompound = new FigCompound();
            figCompound.setTrafo(new FigTrafo2D());
            buildBox.convertToFig(figCompound);
            figCompound.update_bbox();
            final FigText figText = new FigText();
            figText.setTrafo(new FigTrafo2D());
            figText.setText(text);
            figText.move(0, 2400);
            final FigWriter figWriter = new FigWriter();
            final FigObjectList list = new FigObjectList();
            list.insert(figCompound);
            list.insert(figText);
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
    
    public void setUseWords(final boolean use_word_boxes) {
        this.use_word_boxes = use_word_boxes;
    }
    
    public static void msg(final String s) {
        if (LP2.debug) {
            System.out.println(s);
        }
        else if (!s.startsWith("-#-")) {
            System.out.println(s);
        }
    }
    
    public static void usage() {
        msg("java jfig.utils.LP2 [options]");
        msg("where options include: ");
        msg("-help               - this message ");
        msg("-debug              - lots of diagnostic output");
        msg("-tokens             - dump tokens to stdout");
        msg("-boxes              - display the box outlines");
        msg("-italic <value>     - add <value> pts after italic chars");
        msg("-cmfonts            - use Computer Modern fonts");
        msg("-symbolfont         - use Symbol font for math");
        System.exit(0);
    }
    
    public static void main(final String[] array) {
        final LP2 lp2 = new LP2();
        for (int i = 0; i < array.length; ++i) {
            if ("-tokens".equals(array[i])) {
                lp2.dump_tokens = true;
            }
            if ("-boxes".equals(array[i])) {
                lp2.display_boxes = true;
            }
            if ("-debug".equals(array[i])) {
                LP2.debug = true;
            }
            if ("-italic".equals(array[i])) {
                lp2.italic_correction = Integer.parseInt(array[i + 1]);
            }
            if ("-cmfonts".equals(array[i])) {
                lp2.setFontMode(2);
            }
            if ("-symbol".equals(array[i])) {
                lp2.setFontMode(3);
            }
            if ("-help".equals(array[i])) {
                usage();
            }
        }
        lp2.setFontIndex(35);
        lp2.setFontPtSize(27);
        lp2.selftest("TimesRoman $Times \\cos^{2}(x) {\\mathrm{Times}} Times \\sin^{2}(times)$, Times");
        lp2.selftest("$\\frac{n^{2} (n+1)}{n-\\beta} \\cdot\\sum_{j} \\xi_{j}$");
        lp2.selftest("$x^{2y}_{3z}  x^{4^{5^{6}}_{7}}_{3z} $");
        lp2.selftest("$x^{2y} x_{2y} x^{y^{2}} x^{y_{1}} x^{y}_{1} x_{1}^{y}$");
        lp2.selftest("$ a^{2^{5}} + b^{3} = c^{4}$");
        lp2.selftest("Hopfield-Lernen: $J_{ij} = (1/N) \\sum_{\\mu} \\xi_{i}^{\\mu} \\xi_{j}^{\\mu}$ ");
        lp2.selftest("Hopfield-Dynamik: $S_{i} (t+1) = (1/N) \\ {\\fourtypt\\sum}_{j}\\  J_{ij}\\cdot S_{j} (t)$");
        lp2.selftest("X$ \\sqrt{a+b-c\\cdot d} + \\sqrt{\\strut a+b-c\\cdot d} + \\frac{1}{3}\\cdot \\alpha$.");
        lp2.selftest("X$ \\frac{\\sqrt{a+b+c}}{1 + \\sqrt{\\cos(\\alpha)}} = \\omega$");
        lp2.selftest("Square root $\\sqrt{x+y}$ and an $n$th root \\sqrt[n]{2}$.");
        lp2.selftest("Underline: $\\underline{\\alpha} + \\underline{x+y\\cdot\\pi}$");
        lp2.selftest("Aber \\lstack{Franziskus}{Gabi}{Ottokar} das \\cstack{a}{bcde}{f} ist \\rstack{\\red red}{\\green green}{\\magenta magenta} nicht \\lstack{\\strut\\red red}{\\strut\\green green}{\\strut\\magenta magenta} alles.");
        lp2.selftest("X \\rstack{jolanda}{irina}{olga} X  \\cstack{jolanda}{olga}{irina} X");
        lp2.selftest("{\\darkgreen Pythagoras:} $a^{2} + b^{2} = c^{2}$");
        lp2.selftest("\\[ x = \\frac{y+z/2}{y^{2}+1} \\]");
        lp2.selftest("\\[\\frac{x+y}{1 + \\frac{y}{z+1}}\\]");
        lp2.selftest("in-text formula to produce a fraction like $\\frac{1}{2}$.");
        lp2.selftest("A low ellipsis: $x_{1}, \\ldots\\ , x_{n}$.");
        lp2.selftest("IEEE 754: $value = (-1)^{s} \\cdot\\  2^{exp-127} \\cdot\\ (1 + mantisse\\,\\cdot\\,2^{-23})$ ");
        lp2.selftest("Akzente: $\\hat{\\alpha} \\hat{\\Sigma} \\check{\\beta} \\acute{\\gamma} \\grave{\\delta} \\bar{\\epsilon} \\vec{x} \\vec{vmax} \\vec{v}_{max} \\dot{y} \\ddot{y} \\tilde{W} ");
        lp2.selftest("Summe: $\\qquad \\Sum{\\xvpt i=0}{\\xivpt N} i = N (N+1)/2$");
        lp2.selftest("$\\Sum{\\xvpt i}{} \\Sum{\\xvpt j}{\\xviiipt N} x^{i,j} = \\gamma\\cdot\\int f(x)dx$");
        lp2.selftest("$\\Sum{\\xvpt i,j,k}{\\xvpt N} \\Sum{\\xvpt l}{\\xviiipt M} x^{i,j} = \\gamma\\cdot\\int f(x)dx$");
        lp2.selftest("$\\Sum{\\xvpt i,j,k}{\\xvpt N} \\Prod{\\xvpt l,m}{\\xvpt M} x_{ijk}^{lm} = \\Int{\\xvpt x=0}{\\xvpt 2\\pi} f(x)dx$");
        lp2.selftest("Black, {\\red red}, {\\gold gold}, {\\green green}, {\\blue blue {\\black black inside} blue}!");
        lp2.selftest("{\\twentypt 20pt {\\fifteenpt 15pt }{\\twelvept 12pt }{\\tenpt 10pt }{\\eightpt 8pt }{\\fivept 5pt } and back to 20pt}");
        lp2.selftest("Und es begab sich zu der Zeit, da Cyrhenius Landpfleger...");
        lp2.selftest("Text und {\\tt Code mit {\\itt kursivem} Argument.}");
        lp2.selftest("Das {\\bf Theorem} von {\\em Archimedes}.");
        lp2.selftest("{\\em\\red hendrich\\thinspace}{\\black \\atsign informatik.uni-hamburg.de}");
        lp2.selftest("Normaler Text, {\\blue $w = \\sin ( \\alpha \\cdot \\pi /4 )$}");
        lp2.selftest("Text mit Formel, {\\blue$w = \\sin( \\alpha\\cdot\\pi/4 )$}");
        lp2.setFontPtSize(20);
        lp2.selftest("Boole'sche Algebra: $a = b \\oplus  c$ ");
        lp2.selftest("\\lessthan X\\greaterthan \\equals\\backslash \\$ \\lbrace\\lbracket X\\rbracket\\rbrace X");
        lp2.selftest("\\lessthan X\\greaterthan \\equals\\backslash\\$\\lbrace\\lbracket X\\rbracket\\rbrace X");
        lp2.selftest("M\\emspace M $\\black\\clubsuit\\spadesuit {\\red\\heartsuit{}\\diamondsuit}$ \\telephone\\male\\ \\ \\female 42.59\\ \\euro, 15\\thinspace\\pound, 22.000\\thinspace\\pts");
        lp2.setFontIndex(0);
        lp2.setFontPtSize(30);
        lp2.selftest("{\\darkgreen Pythagoras:} $a^{2} + b^{2} = c^{2}$");
        lp2.selftest("$\\cos^{2}(x) + \\sin^{2}(x) = 1$");
        lp2.selftest("{\\darkblue Euler formula:  $\\exp (i \\pi ) + 1 = 0$}");
        lp2.selftest("{\\darkblue Euler formula:  $e^{\\thinspace i \\pi} + 1 = 0$}");
        lp2.selftest("AbCdE: \\int $X_{j=3}^{2} \\cdot dx = 42.0$");
        lp2.selftest("Formel1: $X_{i} = \\sum_{i=1}^{N_{0}} \\phi  \\xi  \\cdot 3$");
        lp2.selftest("$\\prod_{i} \\sum_{j} \\Gamma_{\\Xi_{\\mu}} \\Rightarrow \\setZ \\bullet (TeX \\mp Word)$");
        lp2.selftest("Hopfield-Dynamik: $S_{i} (t+1) = (1/N) \\ {\\fourtypt\\sum}_{j}\\  J_{ij}\\cdot S_{j} (t)$");
        lp2.selftest("Hopfield-Lernen: $J_{ij} = (1/N) \\sum_{\\mu} \\xi_{i}^{\\mu} \\xi_{j}^{\\mu}$ ");
        lp2.selftest("${\\fourtypt\\int^{5}_{0}}\\ (x^{2}) {\\rm d}x = 1/3 x^{3}{\\fourtypt |_{0}^{5}} = (125/3) $");
        lp2.selftest("$\\int^{3}_{x=0} a$");
        lp2.selftest("IEEE 754: $value = (-1)^{s} \\cdot\\  2^{exp-127} \\cdot\\ (1 + mantisse\\,\\cdot\\,2^{-23})$ ");
        lp2.selftest("Text (in) [Klammern] \\{und\\} mit\\_unterstrich");
        lp2.selftest("For all $x: f(x) = g(x+1)$");
        lp2.selftest("Preparing an input file: ");
        lp2.selftest("abcde fghij klmno pqrst uvwxy z");
        lp2.selftest("ABCDE FGHIJ KLMNO PQRST UVWXY Z");
        lp2.selftest("01234 56789 .:;,?! '`()[] -/*+=@");
        lp2.selftest("The Romans wrote I + I = II. Really!");
        lp2.selftest("Beans (lima, etc.)\\ have vitamin B\\@.");
        lp2.selftest("\\$ \\& \\% \\# \\_ \\{ \\} are easy to produce.");
        lp2.selftest("This page was produced on \\today. (\\backslash today)");
        lp2.selftest("The formula \\( x-3y = 7 \\) is easy to type.");
        lp2.selftest("Does \\( x + y \\) always equal \\(x+y\\)?");
        lp2.selftest("\\( a_{1} > x^{2n} / y^{2n} \\)");
        lp2.selftest("This proves that \\( x' < x'' - y'_{3} < 10 x''' z \\).");
        lp2.selftest("The formula \\( a<7 \\) is a noun in this sentence.");
        lp2.selftest("Let $x$ be a prime such that $y>2x$.");
        lp2.selftest("displayed equation: \\[ x' + y^{2} = z_{i}^{2} \\]");
        lp2.selftest("{\\bf This is a bold type style.}");
        lp2.selftest("{\\sf This is a sans serif type style.}");
        lp2.selftest("{\\sl This is a slanted type style.}");
        lp2.selftest("{\\sc This is a Small Caps type style.}");
        lp2.selftest("{\\tt This is a typewriter type style.}");
        lp2.selftest("\\dag \\ddag \\S \\P \\copyright.");
        lp2.selftest("$x^{2y} x_{2y} x^{y^{2}} x^{y_{1}} x^{y}_{1} x_{1}^{y}$");
        lp2.selftest("Multiplying by $n/2$ gives \\( (m+n)/n \\).");
        lp2.selftest("\\[ x = \\frac{y+z/2}{y^{2}+1} \\]");
        lp2.selftest("\\[\\frac{x+y}{1 + \\frac{y}{z+1}}\\]");
        lp2.selftest("in-text formula to produce a fraction like $\\frac{1}{2}$.");
        lp2.selftest("Square root $\\sqrt{x+y}$ and an $n$th root \\sqrt[n]{2}$.");
        lp2.selftest("A low ellipsis: $x_{1}, \\ldots\\ , x_{n}$.");
        lp2.selftest("A centered ellipsis: $a + \\cdots\\ + z$.");
        lp2.selftest("Making Greek letters is as easy as $\\pi$ (or $\\Pi$).");
        lp2.selftest("A\\ B\\ \\ \\ C$\\pi\\ $D");
        lp2.selftest("Boole'sche Algebra: $a = b \\oplus  c$ ");
        lp2.selftest("$\\bigcup_{n=1}^{m}(x_{n}\\cup y_{n})$");
        lp2.selftest("F.1: $\\alpha,\\beta,\\gamma,\\delta,\\epsilon,\\varepsilon,\\zeta,\\eta,\\theta,\\vartheta,\\iota,\\kappa,\\lambda,\\mu,\\nu,\\xi,o,\\pi,\\varpi,\\rho,\\varrho,\\sigma,\\varsigma,\\tau,\\upsilon,\\phi,\\varphi,\\chi,\\psi,\\omega");
        lp2.selftest("F.2: $\\Gamma,\\Delta,\\Theta,\\Lambda,\\Xi,\\Pi,\\Sigma,\\Upsilon,\\Phi,\\Psi,\\Omega$.");
        lp2.selftest("F.3: calligraphic: $\\calA\\calB\\calC\\calD\\ldots\\calZ$");
        lp2.selftest("F.4: dotless i and j: $\\hat\\imath, \\vec\\jmath$");
        lp2.selftest("F.4: $\\aleph,\\hbar,\\imath,\\jmath,\\ell,\\wp,\\Re,\\Im,\\partial,\\infty$ ");
        lp2.selftest("F.4: $\\prime,\\emptyset,\\nabla,\\surd,\\top,\\bot,\\doublebar,\\angle,\\triangle,\\mbackslash$");
        lp2.selftest("F.4: $\\forall,\\exists,\\neg,\\flat,\\natural,\\sharp\\clubsuit,\\diamondsuit,\\heartsuit,\\spadesuit$");
        lp2.selftest("F.5: 01234 56789 {\\bf 01234 56789} {\\it 01234 56789}");
        lp2.selftest("F.6: $\\sum,\\prod,\\coprod,\\int,\\oint,\\bigcap,\\bigcup,\\bigsqcup,\\bigvee,\\bigwedge,\\bigodot,\\bigotimes,\\bigoplus,\\biguplus");
        lp2.selftest("F.7: $\\pm,\\mp,\\setminus,\\cdot,\\times,\\ast,\\star,\\diamond,\\circ,\\bullet,\\div$");
        lp2.selftest("F.7: $\\cap,\\cup,\\uplus,\\sqcap,\\sqcup,\\triangleleft,\\triangleright,\\wr,\\bigcirc,\\bigtriangleup,\\bigtriangledown$");
        lp2.selftest("F.7: $\\vee,\\wedge,\\oplus,\\ominus,\\otimes,\\oslash,\\odot,\\dagger,\\ddagger,\\amalg.$");
        lp2.selftest("F.8: $\\leq,\\prec,\\preceq,\\ll,\\subset,\\subseteq,\\sqsubseteq,\\in,\\vdash,\\smile,\\frown$");
        lp2.selftest("F.8: $\\geq,\\succ,\\succeq,\\gg,\\supset,\\supseteq,\\sqsupseteq,\\ni,\\dashv,\\mid,\\parallel$");
        lp2.selftest("F.8: $\\equiv,\\sim,\\simeq,\\asymp,\\approx,\\cong,\\bowtie,\\propto,\\models,\\doteq,\\perp$");
        lp2.selftest("F.9: $\\not=,\\not\\lessthan,\\not\\geq,\\not\\prec,\\not\\subset,\\not\\asymp$");
        lp2.selftest("$\\vec{a} \\vec{X} \\vec{d} \\hat{b} \\tilde{\\alpha}$");
        System.out.println("OK.");
        System.exit(0);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public LP2() {
        this.dump_tokens = false;
        this.display_boxes = false;
        this.use_word_boxes = true;
        this.fontMode = 2;
        this.mathFontIndex = 1;
        this.mathGreekFontIndex = 1;
        this.mathSymbolFontIndex = 0;
        this.mathBackupFontIndex = 16;
        this.colorCache = ColorCache.getColorCache();
        this.fontCache = FontCache.getFontCache();
        this.stack = new Stack();
        this.delayedCommandStack = new Stack();
        this.italic_correction = 0;
        this.pdfMapper = null;
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
        LP2.debug = false;
        LP2.imageGraphics2D = null;
        LP2.imageGraphics2D = (Graphics2D)new BufferedImage(20, 20, 2).getGraphics();
        msg("-#- imageGraphics2D=" + LP2.imageGraphics2D);
        _mode_names = new String[] { "invalid", "text", "command", "math", "begingroup", "endgroup", "control", "beginmath", "endmath", "superscript", "subscript", "mathsymbol", "mathdigit" };
        LP2.count = 100;
    }
    
    public class Box
    {
        protected int x;
        protected int y;
        protected int w;
        protected int a;
        protected int d;
        protected Box next;
        protected Box prev;
        protected Box parent;
        protected String comment;
        
        public void setComment(final String comment) {
            this.comment = comment;
        }
        
        public String getComment() {
            return this.comment;
        }
        
        public void move(final int n, final int n2) {
            this.x += n;
            this.y += n2;
        }
        
        public void moveTo(final int n, final int n2) {
            this.move(n - this.getX(), n2 - this.getY());
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
        
        public int getW() {
            return this.w;
        }
        
        public int getA() {
            return this.a;
        }
        
        public int getD() {
            return this.d;
        }
        
        public Point getOrigin() {
            return new Point(this.getX(), this.getY());
        }
        
        public void setX(final int x) {
            this.x = x;
        }
        
        public void setY(final int y) {
            this.y = y;
        }
        
        public void setW(final int w) {
            this.w = w;
        }
        
        public void setA(final int a) {
            this.a = a;
        }
        
        public void setD(final int d) {
            this.d = d;
        }
        
        public Box getNextBox() {
            return this.next;
        }
        
        public Box getPrevBox() {
            return this.prev;
        }
        
        public void setNextBox(final Box next) {
            this.next = next;
        }
        
        public void setPrevBox(final Box prev) {
            this.prev = prev;
        }
        
        public void append(final Box next) {
            this.measure();
            this.next = next;
            next.prev = this;
            next.setParent(this.getParent());
            next.setOrigin(this.getAbutmentPoint());
        }
        
        public void measure() {
        }
        
        public Rectangle getBounds() {
            final int min = Math.min(this.x, this.x + this.w);
            return new Rectangle(min, this.y - this.a, Math.max(this.x, this.x + this.w) - min, this.d + this.a);
        }
        
        public Point getAbutmentPoint() {
            return new Point(this.x + this.w, this.y);
        }
        
        public void setOrigin(final Point point) {
            this.x = point.x;
            this.y = point.y;
            this.measure();
        }
        
        public Box getParent() {
            return this.parent;
        }
        
        public void setParent(final Box parent) {
            this.parent = parent;
        }
        
        public String toString() {
            return this.getClass().getName() + "[(" + this.x + "," + this.y + ") " + this.w + " (" + this.a + "," + this.d + ")]";
        }
        
        public void dump(final PrintStream printStream) {
            printStream.println(this.toString());
            if (this.getNextBox() != null) {
                this.getNextBox().dump(printStream);
            }
        }
        
        public void convertToFig(final FigCompound figCompound) {
            if (LP2.this.display_boxes) {
                figCompound.fastAddMember(this.getFigRectangle(figCompound, ColorCache.RED, 190));
            }
            if (this.next != null) {
                this.next.convertToFig(figCompound);
            }
        }
        
        public FigRectangle getFigRectangle(final FigCompound figCompound, final int n, final int currentLayer) {
            final Rectangle bounds = this.getBounds();
            final Point point = new Point(32 * bounds.x, 32 * bounds.y);
            final Point point2 = new Point(32 * (bounds.x + bounds.width), 32 * (bounds.y + bounds.height));
            final FigRectangle figRectangle = new FigRectangle();
            figRectangle.setPoints(new Point[] { point, point2 });
            figRectangle.setTrafo(figCompound.getTrafo());
            figRectangle.setAttributes(new FigAttribs());
            figRectangle.setLineColor(LP2.this.colorCache.get(n));
            figRectangle.setFillColor(LP2.this.colorCache.get(n));
            figRectangle.getAttributes().currentLayer = currentLayer;
            return figRectangle;
        }
    }
    
    public class CharBox extends Box
    {
        protected int y_offset;
        protected int fontIndex;
        protected int ptSize;
        protected int colorIndex;
        protected String s;
        
        public int getColorIndex() {
            return this.colorIndex;
        }
        
        public int getFontIndex() {
            return this.fontIndex;
        }
        
        public int getPtSize() {
            return this.ptSize;
        }
        
        public String getText() {
            return this.s;
        }
        
        public void setText(final String s) {
            this.s = s;
            this.measure();
        }
        
        public void setYOffset(final int y_offset) {
            this.y_offset = y_offset;
        }
        
        public void measure() {
            final FontRenderContext fontRenderContext = LP2.imageGraphics2D.getFontRenderContext();
            final Rectangle pixelBounds = LP2.this.fontCache.getFont(this.fontIndex, this.ptSize).createGlyphVector(fontRenderContext, this.s).getPixelBounds(fontRenderContext, 0.0f, 0.0f);
            this.a = -pixelBounds.y + this.y_offset;
            this.d = pixelBounds.height - this.a;
            this.w = LP2.this.getStringWidthPoints(this.fontIndex, this.ptSize, this.s);
        }
        
        public void append(final Box next) {
            boolean b = false;
            if (LP2.this.use_word_boxes && next instanceof CharBox) {
                final CharBox charBox = (CharBox)next;
                if (charBox.fontIndex == this.fontIndex && charBox.ptSize == this.ptSize && charBox.colorIndex == this.colorIndex) {
                    b = true;
                    System.out.println("CharBox.append: makeWordBox: " + this.s + " " + charBox.s);
                }
            }
            if (b) {
                this.s += ((CharBox)next).s;
                this.measure();
                ((Box)this).getAbutmentPoint();
            }
            else {
                this.measure();
                this.next = next;
                final Point abutmentPoint = ((Box)this).getAbutmentPoint();
                final boolean b2 = (this.fontIndex & 0x1) != 0x0;
                final boolean b3 = next instanceof CharBox && (((CharBox)next).fontIndex & 0x1) != 0x0;
                if (b2 && !b3) {
                    final Point point = abutmentPoint;
                    point.x += LP2.this.italic_correction;
                }
                next.setOrigin(abutmentPoint);
                next.setParent(((Box)this).getParent());
            }
        }
        
        public String toString() {
            return this.getClass().getName() + "[(" + this.x + "," + this.y + ") " + this.w + " (" + this.a + "," + this.d + ")]" + "'" + this.s + "'";
        }
        
        public void convertToFig(final FigCompound figCompound) {
            final FigText figText = new FigText(new Point(32 * this.x, 32 * (this.y - this.y_offset)), this.s, new FigAttribs(), figCompound.getTrafo());
            figText.setFont(this.fontIndex);
            figText.setFontSize(this.ptSize);
            figText.setColor(LP2.this.colorCache.get(this.colorIndex));
            figCompound.fastAddMember(figText);
            if (LP2.this.display_boxes) {
                figCompound.fastAddMember(((Box)this).getFigRectangle(figCompound, ColorCache.BLUE, 200));
            }
            if (this.next != null) {
                this.next.convertToFig(figCompound);
            }
        }
        
        public CharBox(final String s, final int fontIndex, final int ptSize, final int colorIndex) {
            this.y_offset = 0;
            final boolean b = false;
            this.y = (b ? 1 : 0);
            this.x = (b ? 1 : 0);
            this.s = s;
            this.fontIndex = fontIndex;
            this.colorIndex = colorIndex;
            this.ptSize = ptSize;
            this.measure();
        }
    }
    
    public class GroupBox extends Box
    {
        protected Vector members;
        
        public void deleteAllMembers() {
            this.members = new Vector();
            this.measure();
        }
        
        public void deleteMember(final Box box) {
            this.members.remove(box);
        }
        
        public Vector getMembers() {
            return this.members;
        }
        
        public void appendMemberOrig(final Box box) {
            final int size = this.members.size();
            this.members.addElement(box);
            box.setParent((Box)this);
            if (size == 0) {
                box.setOrigin(((Box)this).getOrigin());
            }
            else {
                box.setOrigin(((Box)this.members.elementAt(size - 1)).getAbutmentPoint());
            }
        }
        
        public void appendMember(final Box box) {
            final int size = this.members.size();
            box.setParent((Box)this);
            if (size == 0) {
                this.members.addElement(box);
                box.setOrigin(((Box)this).getOrigin());
            }
            else {
                final Box box2 = this.members.elementAt(size - 1);
                boolean b = false;
                if (LP2.this.use_word_boxes && box2 instanceof CharBox && box instanceof CharBox) {
                    final CharBox charBox = (CharBox)box2;
                    final CharBox charBox2 = (CharBox)box;
                    if (charBox.getFontIndex() == charBox2.getFontIndex() && charBox.getPtSize() == charBox2.getPtSize() && charBox.getColorIndex() == charBox2.getColorIndex()) {
                        b = true;
                        charBox.setText(charBox.getText() + charBox2.getText());
                    }
                }
                if (!b) {
                    this.members.addElement(box);
                    box.setOrigin(box2.getAbutmentPoint());
                }
            }
        }
        
        public Box getLastMember() {
            final int size = this.members.size();
            if (size == 0) {
                return null;
            }
            return (Box)this.members.elementAt(size - 1);
        }
        
        public void append(final Box box) {
            super.append(box);
            box.setParent((Box)this);
        }
        
        public Point getAbutmentPoint() {
            return new Point(this.x + this.getBounds().width, this.y);
        }
        
        public Rectangle getBounds() {
            Rectangle rectangle = super.getBounds();
            for (int size = this.members.size(), i = 0; i < size; ++i) {
                rectangle = rectangle.union(((Box)this.members.elementAt(i)).getBounds());
            }
            LP2.msg("-#- getBounds: " + rectangle);
            return rectangle;
        }
        
        public void dumpGroupMembers() {
            LP2.msg("-I- GroupBox.dumpGroupMembers...");
            for (int size = this.members.size(), i = 0; i < size; ++i) {
                System.out.println("      " + this.members.elementAt(i));
            }
            LP2.msg("<<< GroupBox.END");
            LP2.msg("");
        }
        
        public void move(final int n, final int n2) {
            for (int size = this.members.size(), i = 0; i < size; ++i) {
                ((Box)this.members.elementAt(i)).move(n, n2);
            }
            super.move(n, n2);
        }
        
        public void measure() {
            super.measure();
            Rectangle union = new Rectangle(((Box)this).getX(), ((Box)this).getY(), 0, 0);
            for (int size = this.members.size(), i = 0; i < size; ++i) {
                final Box box = this.members.elementAt(i);
                box.measure();
                union = union.union(box.getBounds());
            }
            ((Box)this).setW(union.width);
            ((Box)this).setA(((Box)this).getY() - union.y);
            ((Box)this).setD(-((Box)this).getY() + union.y + union.height);
        }
        
        public void convertToFig(final FigCompound figCompound) {
            if (LP2.this.display_boxes) {
                figCompound.fastAddMember(((Box)this).getFigRectangle(figCompound, ColorCache.BROWN2, 193));
            }
            for (int size = this.members.size(), i = 0; i < size; ++i) {
                ((Box)this.members.elementAt(i)).convertToFig(figCompound);
            }
            if (this.next != null) {
                this.next.convertToFig(figCompound);
            }
        }
        
        public GroupBox() {
            this.members = new Vector();
        }
    }
    
    public class SubSuperScriptBox extends GroupBox
    {
        protected Box superscriptBox;
        protected Box subscriptBox;
        protected String mode;
        protected int aa;
        protected int dd;
        protected int sa;
        protected int sd;
        protected int ptSize;
        protected int fontIndex;
        protected int italicCorrection;
        
        public void setMode(final String mode) {
            this.mode = mode;
        }
        
        public String getMode() {
            return this.mode;
        }
        
        public void appendMember(final Box box) {
            if (this.mode == "superscript") {
                this.superscriptBox = box;
                final Point superscriptBasePoint = this.getSuperscriptBasePoint();
                box.moveTo(superscriptBasePoint.x, superscriptBasePoint.y);
            }
            else if (this.mode == "subscript") {
                this.subscriptBox = box;
                final Point subscriptBasePoint = this.getSubscriptBasePoint();
                box.moveTo(subscriptBasePoint.x, subscriptBasePoint.y);
            }
            else {
                LP2.msg("-E- SubSuperScriptBox.appendMember: unknown mode " + this.mode);
                Thread.dumpStack();
            }
        }
        
        public void deleteMember(final Box box) {
            if (box == this.superscriptBox) {
                this.superscriptBox = null;
            }
            if (box == this.subscriptBox) {
                this.subscriptBox = null;
            }
            this.measure();
        }
        
        public void deleteAllMembers() {
            this.superscriptBox = null;
            this.subscriptBox = null;
            this.measure();
        }
        
        public void move(final int n, final int n2) {
            if (this.superscriptBox != null) {
                this.superscriptBox.move(n, n2);
            }
            if (this.subscriptBox != null) {
                this.subscriptBox.move(n, n2);
            }
            super.move(n, n2);
        }
        
        public void measure() {
            int w = 0;
            int w2 = 0;
            int y = ((Box)this).getY();
            int y2 = ((Box)this).getY();
            if (this.superscriptBox != null) {
                this.superscriptBox.measure();
                w = this.superscriptBox.getW();
                y = this.superscriptBox.getY() - this.superscriptBox.getA();
            }
            if (this.subscriptBox != null) {
                this.subscriptBox.measure();
                w2 = this.subscriptBox.getW();
                y2 = this.subscriptBox.getY() + this.subscriptBox.getD();
            }
            ((Box)this).setA(((Box)this).getY() - y);
            ((Box)this).setD(y2 - ((Box)this).getY());
            ((Box)this).setW(Math.max(w, w2));
        }
        
        public Point getAbutmentPoint() {
            Object o = this.subscriptBox;
            Point abutmentPoint = new Point(0, 0);
            while (o != null) {
                abutmentPoint = ((Box)o).getAbutmentPoint();
                o = ((Box)o).getNextBox();
            }
            Object o2 = this.superscriptBox;
            Point abutmentPoint2 = new Point(0, 0);
            while (o2 != null) {
                abutmentPoint2 = ((Box)o2).getAbutmentPoint();
                o2 = ((Box)o2).getNextBox();
            }
            return new Point(Math.max(abutmentPoint.x, abutmentPoint2.x), this.y);
        }
        
        public int getSubscriptPtSize() {
            for (int i = 0; i < LP2.this.subscriptPtSizes.length; ++i) {
                if (LP2.this.subscriptPtSizes[i][0] >= this.ptSize) {
                    return LP2.this.subscriptPtSizes[i][1];
                }
            }
            return LP2.this.subscriptPtSizes[LP2.this.subscriptPtSizes.length - 1][1];
        }
        
        public Point getSuperscriptBasePoint() {
            return new Point(this.x, this.y - this.aa + (this.aa + this.dd) / 2 - (2 * this.sa + 2 * this.sd + this.sd) / 2 + this.sa);
        }
        
        public Point getSubscriptBasePoint() {
            return new Point(this.x, this.y - this.aa + (this.aa + this.dd) / 2 + (2 * this.sa + 2 * this.sd + this.sd) / 2 - this.sd);
        }
        
        public Box getSubscriptBox() {
            return this.subscriptBox;
        }
        
        public Box getSuperscriptBox() {
            return this.superscriptBox;
        }
        
        public void setSubscriptBox(final Box subscriptBox) {
            this.subscriptBox = subscriptBox;
        }
        
        public void setSuperscriptBox(final Box superscriptBox) {
            this.superscriptBox = superscriptBox;
        }
        
        public String toString() {
            return this.getClass().getName() + "[(" + this.x + "," + this.y + ") " + this.w + " (" + this.aa + "," + this.dd + ")]" + "\nsuperscript box:\n" + ((this.superscriptBox != null) ? this.superscriptBox.toString() : "") + "\nsubscript box:" + ((this.subscriptBox != null) ? this.subscriptBox.toString() : "") + "\n";
        }
        
        public void convertToFig(final FigCompound figCompound) {
            if (LP2.this.display_boxes) {
                figCompound.fastAddMember(((Box)this).getFigRectangle(figCompound, ColorCache.GREEN, 195));
            }
            if (this.next != null) {
                this.next.convertToFig(figCompound);
            }
            if (this.superscriptBox != null) {
                this.superscriptBox.convertToFig(figCompound);
            }
            if (this.subscriptBox != null) {
                this.subscriptBox.convertToFig(figCompound);
            }
        }
        
        public SubSuperScriptBox(final int fontIndex, final int ptSize) {
            this.mode = "none";
            this.italicCorrection = 0;
            final boolean b = false;
            this.y = (b ? 1 : 0);
            this.x = (b ? 1 : 0);
            this.fontIndex = fontIndex;
            this.ptSize = ptSize;
            final FontMetrics fontMetrics = LP2.this.fontCache.getFontMetrics(fontIndex, ptSize);
            this.aa = fontMetrics.getAscent();
            this.dd = fontMetrics.getDescent();
            final FontMetrics fontMetrics2 = LP2.this.fontCache.getFontMetrics(fontIndex, this.getSubscriptPtSize());
            this.sa = fontMetrics2.getAscent();
            this.sd = fontMetrics2.getDescent();
            final int n = 2 * this.sa + 2 * this.sd + this.sd;
            this.measure();
        }
    }
    
    public class CommandBox extends GroupBox
    {
        protected String command;
        protected int n_args;
        protected Vector args;
        
        public String getCommand() {
            return this.command;
        }
        
        public void addArgument(final Box box) {
            this.args.add(box);
        }
        
        public boolean hasAllArguments() {
            return this.args.size() == this.n_args;
        }
        
        public void executeCommand() {
            LP2.this.handleCommand(this.command, this.args.toArray());
        }
        
        public CommandBox(final String command, final int n_args) {
            this.n_args = 1;
            this.args = null;
            this.command = command;
            this.n_args = n_args;
            this.args = new Vector();
        }
    }
    
    public class StackBox extends Box
    {
        protected Box topBox;
        protected Box centerBox;
        protected Box bottomBox;
        protected int align;
        protected int Y_SPACING;
        
        public Box getTopBox() {
            return this.topBox;
        }
        
        public Box getCenterbox() {
            return this.centerBox;
        }
        
        public Box getBottomBox() {
            return this.bottomBox;
        }
        
        public void setTopBox(final Box topBox) {
            this.topBox = topBox;
        }
        
        public void setCenterBox(final Box centerBox) {
            this.centerBox = centerBox;
        }
        
        public void setBottomBox(final Box bottomBox) {
            this.bottomBox = bottomBox;
        }
        
        public void setAlignment(final int align) {
            this.align = align;
        }
        
        public int getAlignment() {
            return this.align;
        }
        
        public void move(final int n, final int n2) {
            if (this.topBox != null) {
                this.topBox.move(n, n2);
            }
            if (this.centerBox != null) {
                this.centerBox.move(n, n2);
            }
            if (this.bottomBox != null) {
                this.bottomBox.move(n, n2);
            }
            super.move(n, n2);
        }
        
        public void measure() {
            int a = 0;
            int d = 0;
            int w = 0;
            int w2 = 0;
            int w3 = 0;
            int a2 = 0;
            int d2 = 0;
            if (this.centerBox != null) {
                this.centerBox.measure();
                a = this.centerBox.getA();
                d = this.centerBox.getD();
                w2 = this.centerBox.getW();
                this.Y_SPACING = Math.max(2, LP2.this.ptSize / 5);
                a2 += this.centerBox.getA();
                d2 += this.centerBox.getD();
            }
            if (this.topBox != null) {
                this.topBox.measure();
                w = this.topBox.getW();
                a2 = a2 + this.topBox.getBounds().height + this.Y_SPACING;
            }
            if (this.bottomBox != null) {
                this.bottomBox.measure();
                w3 = this.bottomBox.getW();
                d2 = d2 + this.bottomBox.getBounds().height + this.Y_SPACING;
            }
            final int max = Math.max(w2, Math.max(w, w3));
            if (this.align == 1) {
                if (this.centerBox != null) {
                    this.centerBox.moveTo(((Box)this).getX(), ((Box)this).getY());
                }
                if (this.topBox != null) {
                    this.topBox.moveTo(((Box)this).getX(), ((Box)this).getY() - a - this.topBox.getD() - this.Y_SPACING);
                }
                if (this.bottomBox != null) {
                    this.bottomBox.moveTo(((Box)this).getX(), ((Box)this).getY() + d + this.bottomBox.getA() + this.Y_SPACING);
                }
            }
            else if (this.align == 2) {
                if (this.centerBox != null) {
                    this.centerBox.moveTo(((Box)this).getX() + (max - w2) / 2, ((Box)this).getY());
                }
                if (this.topBox != null) {
                    this.topBox.moveTo(((Box)this).getX() + (max - w) / 2, ((Box)this).getY() - a - this.topBox.getD() - this.Y_SPACING);
                }
                if (this.bottomBox != null) {
                    this.bottomBox.moveTo(((Box)this).getX() + (max - w3) / 2, ((Box)this).getY() + d + this.bottomBox.getA() + this.Y_SPACING);
                }
            }
            else {
                if (this.align != 3) {
                    throw new IllegalArgumentException("Unknown align" + this.align);
                }
                if (this.centerBox != null) {
                    this.centerBox.moveTo(((Box)this).getX() + (max - w2), ((Box)this).getY());
                }
                if (this.topBox != null) {
                    this.topBox.moveTo(((Box)this).getX() + (max - w), ((Box)this).getY() - a - this.topBox.getD() - this.Y_SPACING);
                }
                if (this.bottomBox != null) {
                    this.bottomBox.moveTo(((Box)this).getX() + (max - w3), ((Box)this).getY() + d + this.bottomBox.getA() + this.Y_SPACING);
                }
            }
            ((Box)this).setW(max);
            ((Box)this).setA(a2);
            ((Box)this).setD(d2);
        }
        
        public void convertToFig(final FigCompound figCompound) {
            if (LP2.this.display_boxes) {
                figCompound.fastAddMember(((Box)this).getFigRectangle(figCompound, ColorCache.MAGENTA, 191));
            }
            if (this.topBox != null) {
                this.topBox.convertToFig(figCompound);
            }
            if (this.centerBox != null) {
                this.centerBox.convertToFig(figCompound);
            }
            if (this.bottomBox != null) {
                this.bottomBox.convertToFig(figCompound);
            }
            if (this.next != null) {
                this.next.convertToFig(figCompound);
            }
        }
        
        public StackBox() {
            this.align = 2;
            this.setAlignment(this.Y_SPACING = 2);
        }
    }
    
    public class SqrtBox extends Box
    {
        protected GroupBox group;
        protected int Y_SPACING;
        protected int X_OFFSET;
        
        public void move(final int n, final int n2) {
            if (this.group != null) {
                this.group.move(n, n2);
            }
            super.move(n, n2);
        }
        
        public void measure() {
            if (this.group != null) {
                this.group.measure();
                ((Box)this).setA(((Box)this.group).getA() + this.Y_SPACING);
                ((Box)this).setD(((Box)this.group).getD());
                this.X_OFFSET = (((Box)this).getA() + ((Box)this).getD()) / 2;
                ((Box)this).setW(((Box)this.group).getW() + this.X_OFFSET);
                ((Box)this.group).moveTo(((Box)this).getX() + this.X_OFFSET, ((Box)this).getY());
            }
        }
        
        private Point gp(final int n, final int n2) {
            return new Point(32 * n, 32 * n2);
        }
        
        public void convertToFig(final FigCompound figCompound) {
            if (LP2.this.display_boxes) {
                figCompound.fastAddMember(((Box)this).getFigRectangle(figCompound, ColorCache.BLUE3, 192));
            }
            if (this.group != null) {
                this.group.convertToFig(figCompound);
            }
            final int x = ((Box)this.group).getX();
            final int w = ((Box)this.group).getW();
            final int y = ((Box)this.group).getY();
            final int a = ((Box)this.group).getA();
            final int d = ((Box)this.group).getD();
            final Point gp = this.gp(x + w, y - a - 2);
            final Point gp2 = this.gp(x, y - a - 2);
            final Point gp3 = this.gp(x - (a + d) / 3, y + d);
            final Point gp4 = this.gp(x - (a + d) / 2, y - a / 3);
            final FigPolyline figPolyline = new FigPolyline();
            figPolyline.setPoints(new Point[] { gp4, gp3, gp2, gp });
            figPolyline.setTrafo(figCompound.getTrafo());
            figPolyline.setAttributes(new FigAttribs());
            figPolyline.setLineColor(LP2.this.colorCache.get(LP2.this.colorIndex));
            figPolyline.setFillColor(LP2.this.colorCache.get(LP2.this.colorIndex));
            figPolyline.getAttributes().currentLayer = 192;
            figPolyline.getAttributes().parse("linewidth=2");
            figCompound.fastAddMember(figPolyline);
        }
        
        public SqrtBox(final GroupBox group) {
            this.Y_SPACING = 3;
            this.X_OFFSET = 0;
            this.group = group;
        }
    }
}

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ConsolePanel extends Panel
{
    private ConsoleCanvas canvas;
    private String buffer;
    private int pos;
    
    public ConsolePanel() {
        this.buffer = null;
        this.pos = 0;
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout(0, 0));
        this.add("Center", this.canvas = new ConsoleCanvas());
    }
    
    public void clear() {
        this.canvas.clear();
    }
    
    public void put(final int x) {
        this.put(x, 0);
    }
    
    public void put(final long x) {
        this.put(x, 0);
    }
    
    public void put(final double x) {
        this.put(x, 0);
    }
    
    public void put(final char x) {
        this.put(x, 0);
    }
    
    public void put(final boolean x) {
        this.put(x, 0);
    }
    
    public void put(final String x) {
        this.put(x, 0);
    }
    
    public void putln(final int x) {
        this.put(x, 0);
        this.newLine();
    }
    
    public void putln(final long x) {
        this.put(x, 0);
        this.newLine();
    }
    
    public void putln(final double x) {
        this.put(x, 0);
        this.newLine();
    }
    
    public void putln(final char x) {
        this.put(x, 0);
        this.newLine();
    }
    
    public void putln(final boolean x) {
        this.put(x, 0);
        this.newLine();
    }
    
    public void putln(final String x) {
        this.put(x, 0);
        this.newLine();
    }
    
    public void putln(final int x, final int w) {
        this.put(x, w);
        this.newLine();
    }
    
    public void putln(final long x, final int w) {
        this.put(x, w);
        this.newLine();
    }
    
    public void putln(final double x, final int w) {
        this.put(x, w);
        this.newLine();
    }
    
    public void putln(final char x, final int w) {
        this.put(x, w);
        this.newLine();
    }
    
    public void putln(final boolean x, final int w) {
        this.put(x, w);
        this.newLine();
    }
    
    public void putln(final String x, final int w) {
        this.put(x, w);
        this.newLine();
    }
    
    public void putln() {
        this.newLine();
    }
    
    public void put(final int x, final int w) {
        this.dumpString(String.valueOf(x), w);
    }
    
    public void put(final long x, final int w) {
        this.dumpString(String.valueOf(x), w);
    }
    
    public void put(final double x, final int w) {
        this.dumpString(realToString(x), w);
    }
    
    public void put(final char x, final int w) {
        this.dumpString(String.valueOf(x), w);
    }
    
    public void put(final boolean x, final int w) {
        this.dumpString(String.valueOf(x), w);
    }
    
    public void put(final String x, final int w) {
        this.dumpString(x, w);
    }
    
    public byte getlnByte() {
        final byte x = this.getByte();
        this.emptyBuffer();
        return x;
    }
    
    public short getlnShort() {
        final short x = this.getShort();
        this.emptyBuffer();
        return x;
    }
    
    public int getlnInt() {
        final int x = this.getInt();
        this.emptyBuffer();
        return x;
    }
    
    public long getlnLong() {
        final long x = this.getLong();
        this.emptyBuffer();
        return x;
    }
    
    public float getlnFloat() {
        final float x = this.getFloat();
        this.emptyBuffer();
        return x;
    }
    
    public double getlnDouble() {
        final double x = this.getDouble();
        this.emptyBuffer();
        return x;
    }
    
    public char getlnChar() {
        final char x = this.getChar();
        this.emptyBuffer();
        return x;
    }
    
    public boolean getlnBoolean() {
        final boolean x = this.getBoolean();
        this.emptyBuffer();
        return x;
    }
    
    public String getlnWord() {
        final String x = this.getWord();
        this.emptyBuffer();
        return x;
    }
    
    public String getlnString() {
        return this.getln();
    }
    
    public String getln() {
        final StringBuffer s = new StringBuffer(100);
        for (char ch = this.readChar(); ch != '\n'; ch = this.readChar()) {
            s.append(ch);
        }
        return s.toString();
    }
    
    public byte getByte() {
        return (byte)this.readInteger(-128L, 127L);
    }
    
    public short getShort() {
        return (short)this.readInteger(-32768L, 32767L);
    }
    
    public int getInt() {
        return (int)this.readInteger(-2147483648L, 2147483647L);
    }
    
    public long getLong() {
        return this.readInteger(Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public char getChar() {
        return this.readChar();
    }
    
    public char peek() {
        return this.lookChar();
    }
    
    public float getFloat() {
        float x = 0.0f;
        Float f;
        while (true) {
            final String str = this.readRealString();
            if (str.equals("")) {
                this.errorMessage("Illegal floating point input.", "Real number in the range 1.4E-45 to 3.4028235E38");
            }
            else {
                f = null;
                try {
                    f = Float.valueOf(str);
                }
                catch (NumberFormatException e) {
                    this.errorMessage("Illegal floating point input.", "Real number in the range 1.4E-45 to 3.4028235E38");
                    continue;
                }
                if (!f.isInfinite()) {
                    break;
                }
                this.errorMessage("Floating point input outside of legal range.", "Real number in the range 1.4E-45 to 3.4028235E38");
            }
        }
        x = f;
        return x;
    }
    
    public double getDouble() {
        double x = 0.0;
        Double f;
        while (true) {
            final String str = this.readRealString();
            if (str.equals("")) {
                this.errorMessage("Illegal floating point input", "Real number in the range 4.9E-324 to 1.7976931348623157E308");
            }
            else {
                f = null;
                try {
                    f = Double.valueOf(str);
                }
                catch (NumberFormatException e) {
                    this.errorMessage("Illegal floating point input", "Real number in the range 4.9E-324 to 1.7976931348623157E308");
                    continue;
                }
                if (!f.isInfinite()) {
                    break;
                }
                this.errorMessage("Floating point input outside of legal range.", "Real number in the range 4.9E-324 to 1.7976931348623157E308");
            }
        }
        x = f;
        return x;
    }
    
    public String getWord() {
        char ch;
        for (ch = this.lookChar(); ch == ' ' || ch == '\n'; ch = this.lookChar()) {
            this.readChar();
            if (ch == '\n') {
                this.dumpString("? ", 0);
            }
        }
        final StringBuffer str = new StringBuffer(50);
        while (ch != ' ' && ch != '\n') {
            str.append(this.readChar());
            ch = this.lookChar();
        }
        return str.toString();
    }
    
    public boolean getBoolean() {
        boolean ans = false;
        while (true) {
            final String s = this.getWord();
            if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y") || s.equals("1")) {
                ans = true;
                break;
            }
            if (s.equalsIgnoreCase("false") || s.equalsIgnoreCase("f") || s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n") || s.equals("0")) {
                ans = false;
                break;
            }
            this.errorMessage("Illegal boolean input value.", "one of:  true, false, t, f, yes, no, y, n, 0, or 1");
        }
        return ans;
    }
    
    private String readRealString() {
        final StringBuffer s = new StringBuffer(50);
        char ch;
        for (ch = this.lookChar(); ch == ' ' || ch == '\n'; ch = this.lookChar()) {
            this.readChar();
            if (ch == '\n') {
                this.dumpString("? ", 0);
            }
        }
        if (ch == '-' || ch == '+') {
            s.append(this.readChar());
            for (ch = this.lookChar(); ch == ' '; ch = this.lookChar()) {
                this.readChar();
            }
        }
        while (ch >= '0' && ch <= '9') {
            s.append(this.readChar());
            ch = this.lookChar();
        }
        if (ch == '.') {
            s.append(this.readChar());
            for (ch = this.lookChar(); ch >= '0' && ch <= '9'; ch = this.lookChar()) {
                s.append(this.readChar());
            }
        }
        if (ch == 'E' || ch == 'e') {
            s.append(this.readChar());
            ch = this.lookChar();
            if (ch == '-' || ch == '+') {
                s.append(this.readChar());
                ch = this.lookChar();
            }
            while (ch >= '0' && ch <= '9') {
                s.append(this.readChar());
                ch = this.lookChar();
            }
        }
        return s.toString();
    }
    
    private long readInteger(final long min, final long max) {
        long x = 0L;
        while (true) {
            final StringBuffer s = new StringBuffer(34);
            char ch;
            for (ch = this.lookChar(); ch == ' ' || ch == '\n'; ch = this.lookChar()) {
                this.readChar();
                if (ch == '\n') {}
                this.dumpString("? ", 0);
            }
            if (ch == '-' || ch == '+') {
                s.append(this.readChar());
                for (ch = this.lookChar(); ch == ' '; ch = this.lookChar()) {
                    this.readChar();
                }
            }
            while (ch >= '0' && ch <= '9') {
                s.append(this.readChar());
                ch = this.lookChar();
            }
            if (s.equals("")) {
                this.errorMessage("Illegal integer input.", "Integer in the range " + min + " to " + max);
            }
            else {
                final String str = s.toString();
                try {
                    x = Long.parseLong(str);
                }
                catch (NumberFormatException e) {
                    this.errorMessage("Illegal integer input.", "Integer in the range " + min + " to " + max);
                    continue;
                }
                if (x >= min && x <= max) {
                    break;
                }
                this.errorMessage("Integer input outside of legal range.", "Integer in the range " + min + " to " + max);
            }
        }
        return x;
    }
    
    private static String realToString(double x) {
        if (Double.isNaN(x)) {
            return "undefined";
        }
        if (Double.isInfinite(x)) {
            if (x < 0.0) {
                return "-INF";
            }
            return "INF";
        }
        else {
            if (Math.abs(x) <= 5.0E9 && Math.rint(x) == x) {
                return String.valueOf((long)x);
            }
            String s = String.valueOf(x);
            if (s.length() <= 10) {
                return s;
            }
            boolean neg = false;
            if (x < 0.0) {
                neg = true;
                x = -x;
                s = String.valueOf(x);
            }
            if (x >= 5.0E-5 && x <= 5.0E7 && s.indexOf(69) == -1 && s.indexOf(101) == -1) {
                s = round(s, 10);
                s = trimZeros(s);
            }
            else if (x > 1.0) {
                final long power = (long)Math.floor(Math.log(x) / Math.log(10.0));
                final String exp = "E" + power;
                final int numlength = 10 - exp.length();
                x /= Math.pow(10.0, power);
                s = String.valueOf(x);
                s = round(s, numlength);
                s = trimZeros(s);
                s += exp;
            }
            else {
                final long power = (long)Math.ceil(-Math.log(x) / Math.log(10.0));
                final String exp = "E-" + power;
                final int numlength = 10 - exp.length();
                x *= Math.pow(10.0, power);
                s = String.valueOf(x);
                s = round(s, numlength);
                s = trimZeros(s);
                s += exp;
            }
            if (neg) {
                return "-" + s;
            }
            return s;
        }
    }
    
    private static String trimZeros(String num) {
        if (num.indexOf(46) >= 0 && num.charAt(num.length() - 1) == '0') {
            int i;
            for (i = num.length() - 1; num.charAt(i) == '0'; --i) {}
            if (num.charAt(i) == '.') {
                num = num.substring(0, i);
            }
            else {
                num = num.substring(0, i + 1);
            }
        }
        return num;
    }
    
    private static String round(final String num, final int length) {
        if (num.indexOf(46) < 0) {
            return num;
        }
        if (num.length() <= length) {
            return num;
        }
        if (num.charAt(length) >= '5' && num.charAt(length) != '.') {
            final char[] temp = new char[length + 1];
            int ct = length;
            boolean rounding = true;
            for (int i = length - 1; i >= 0; --i) {
                temp[ct] = num.charAt(i);
                if (rounding && temp[ct] != '.') {
                    if (temp[ct] < '9') {
                        final char[] array = temp;
                        final int n = ct;
                        ++array[n];
                        rounding = false;
                    }
                    else {
                        temp[ct] = '0';
                    }
                }
                --ct;
            }
            if (rounding) {
                temp[ct] = '1';
                --ct;
            }
            return new String(temp, ct + 1, length - ct);
        }
        return num.substring(0, length);
    }
    
    private void dumpString(final String str, final int w) {
        for (int i = str.length(); i < w; ++i) {
            this.canvas.addChar(' ');
        }
        for (int j = 0; j < str.length(); ++j) {
            if (str.charAt(j) >= ' ' && str.charAt(j) != '\u007f') {
                this.canvas.addChar(str.charAt(j));
            }
            else if (str.charAt(j) == '\n' || str.charAt(j) == '\r') {
                this.newLine();
            }
        }
    }
    
    private void errorMessage(final String message, final String expecting) {
        this.newLine();
        this.dumpString("  *** Error in input: " + message + "\n", 0);
        this.dumpString("  *** Expecting: " + expecting + "\n", 0);
        this.dumpString("  *** Discarding Input: ", 0);
        if (this.lookChar() == '\n') {
            this.dumpString("(end-of-line)\n\n", 0);
        }
        else {
            while (this.lookChar() != '\n') {
                this.canvas.addChar(this.readChar());
            }
            this.dumpString("\n\n", 0);
        }
        this.dumpString("Please re-enter: ", 0);
        this.readChar();
    }
    
    private char lookChar() {
        if (this.buffer == null || this.pos > this.buffer.length()) {
            this.fillBuffer();
        }
        if (this.pos == this.buffer.length()) {
            return '\n';
        }
        return this.buffer.charAt(this.pos);
    }
    
    private char readChar() {
        final char ch = this.lookChar();
        ++this.pos;
        return ch;
    }
    
    private void newLine() {
        this.canvas.addCR();
    }
    
    private void fillBuffer() {
        this.buffer = this.canvas.readLine();
        this.pos = 0;
    }
    
    private void emptyBuffer() {
        this.buffer = null;
    }
    
    public void clearBuffers() {
        this.buffer = null;
        this.canvas.clearTypeAhead();
    }
}

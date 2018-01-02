// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Event;
import java.applet.Applet;
import java.awt.Color;
import java.awt.TextField;

public class Nbr extends TextField
{
    public static String CL;
    public static String INPUT_ERROR_MSG;
    public static String RANGE_LOW_ERROR_MSG;
    public static String RANGE_HIGH_ERROR_MSG;
    public static final int INTEGER = 1;
    public static final int DOUBLE = 2;
    public static final int FMT_DOLLARS = 3;
    public static final int FMT_PERCENT = 4;
    public static final int FMT_NUMBER = 5;
    public static final int FMT_INPUT = 6;
    protected String _sName;
    protected double _dDoubleRangeLow;
    protected double _dDoubleRangeHigh;
    protected int _nDecimals;
    protected int _nFormat;
    protected CalculatorApplet myApplet;
    protected String sChange;
    
    static {
        Nbr.CL = ":";
        Nbr.INPUT_ERROR_MSG = "Please enter a valid ";
        Nbr.RANGE_LOW_ERROR_MSG = " must be greater than or equal to ";
        Nbr.RANGE_HIGH_ERROR_MSG = " must be less than or equal to ";
    }
    
    public Nbr() {
        this._nDecimals = 0;
        this._nFormat = 5;
        this.myApplet = null;
        this.sChange = "";
    }
    
    public Nbr(final double n, final String s, final double n2, final double n3, final int n4) {
        this(n, s, n2, n3, n4, 5);
    }
    
    public Nbr(final double n, final String sName, final double dDoubleRangeLow, final double dDoubleRangeHigh, final int nDecimals, final int nFormat) {
        this._nDecimals = 0;
        this._nFormat = 5;
        this.myApplet = null;
        this.sChange = "";
        this._sName = sName;
        this._dDoubleRangeLow = dDoubleRangeLow;
        this._dDoubleRangeHigh = dDoubleRangeHigh;
        this._nDecimals = nDecimals;
        this._nFormat = nFormat;
        this.setText(Fmt.number(n, this._nDecimals));
        this.setBackground(Color.white);
    }
    
    public Nbr(final double n, final String s, final double n2, final double n3, final int n4, final int n5, final Applet applet) {
        this(n, s, n2, n3, n4, n5);
        if (applet instanceof CalculatorApplet) {
            this.myApplet = (CalculatorApplet)applet;
        }
    }
    
    public Nbr(final String s, final String s2, final double n, final double n2, final int n3) {
        this(s, s2, n, n2, n3, 5);
    }
    
    public Nbr(final String text, final String sName, final double dDoubleRangeLow, final double dDoubleRangeHigh, final int nDecimals, final int nFormat) {
        this._nDecimals = 0;
        this._nFormat = 5;
        this.myApplet = null;
        this.sChange = "";
        this._sName = sName;
        this._dDoubleRangeLow = dDoubleRangeLow;
        this._dDoubleRangeHigh = dDoubleRangeHigh;
        this._nDecimals = nDecimals;
        this._nFormat = nFormat;
        this.setText(text);
        this.setBackground(Color.white);
    }
    
    public Nbr(String parameter, String parameter2, final double dDoubleRangeLow, final double dDoubleRangeHigh, final int nDecimals, final int nFormat, final Applet applet) {
        this._nDecimals = 0;
        this._nFormat = 5;
        this.myApplet = null;
        this.sChange = "";
        if (applet instanceof CalculatorApplet) {
            this.myApplet = (CalculatorApplet)applet;
            parameter2 = this.myApplet.getParameter("MSG_" + parameter, parameter2);
        }
        if (applet.getParameter(parameter) == null) {
            parameter = "0";
        }
        else {
            parameter = applet.getParameter(parameter);
        }
        this._sName = parameter2;
        this._dDoubleRangeLow = dDoubleRangeLow;
        this._dDoubleRangeHigh = dDoubleRangeHigh;
        this._nDecimals = nDecimals;
        this._nFormat = nFormat;
        this.setText(parameter);
        this.setBackground(Color.white);
    }
    
    public static String Strip(final String s) {
        final char[] array = new char[s.length()];
        int n = 0;
        if (s == null) {
            return "";
        }
        if (s.equals("")) {
            return "";
        }
        for (int i = 0; i < array.length; ++i) {
            final char char1 = s.charAt(i);
            if ((char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z')) {
                switch (char1) {
                    case 44: {
                        if (Fmt.bEuropeDecimals) {
                            array[n] = '.';
                            ++n;
                            break;
                        }
                        break;
                    }
                    case 46: {
                        if (!Fmt.bEuropeDecimals || (Fmt.bEuropeDecimals && Fmt.sPC.equals(" ") && Fmt.sCC.equals(" "))) {
                            array[n] = '.';
                            ++n;
                            break;
                        }
                        break;
                    }
                    default: {
                        array[n] = s.charAt(i);
                        ++n;
                        break;
                    }
                    case 9:
                    case 32:
                    case 36:
                    case 37:
                    case 41:
                    case 47:
                    case 60:
                    case 62:
                    case 163: {
                        break;
                    }
                    case 40: {
                        array[n] = '-';
                        ++n;
                        break;
                    }
                }
            }
        }
        return String.valueOf(array, 0, n);
    }
    
    public boolean bManualRecalc() {
        return this.myApplet == null;
    }
    
    public synchronized void disable() {
        this.setBackground(Color.lightGray);
        this.setEditable(false);
    }
    
    public synchronized void enable() {
        this.setBackground(Color.white);
        this.setEditable(true);
    }
    
    public String fmt(final double n) {
        switch (this._nFormat) {
            case 3: {
                return Fmt.dollars(n, this._nDecimals);
            }
            case 4: {
                return Fmt.percent(n / 100.0, this._nDecimals);
            }
            case 6: {
                return Fmt.input(n, this._nDecimals);
            }
            default: {
                return Fmt.number(n, this._nDecimals);
            }
        }
    }
    
    public String getLabel() {
        return String.valueOf(this._sName) + Nbr.CL;
    }
    
    public String getName() {
        return this._sName;
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.sChange = this.getText();
        this.selectAll();
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        return (event.id == 401 && !this.keypressOk((char)event.key)) || super.handleEvent(event);
    }
    
    public boolean keypressOk(final char c) {
        return (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && c != '!' && c != '@' && c != '#' && c != '^' && c != '&' && c != '*' && c != '_' && c != '+' && c != '=' && c != '{' && c != '}' && c != '[' && c != ']' && c != '|' && c != '\\' && c != ';' && c != ':' && c != '\'' && c != '\"' && c != '<' && c != '>' && c != '?' && c != '~' && c != '/' && c != '`' && c != '~' && (c != '.' || this._nDecimals > 0) && (c != '-' || this._dDoubleRangeLow < 0.0);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        if (this.myApplet != null && !this.sChange.equals(this.getText())) {
            this.myApplet.calculate();
        }
        this.select(0, 0);
        return true;
    }
    
    public double toDouble() throws NumberFormatException {
        final String strip = Strip(this.getText());
        double round;
        try {
            round = Fmt.round(toDouble(strip), this._nDecimals);
        }
        catch (NumberFormatException ex) {
            throw new NumberFormatException(String.valueOf(Nbr.INPUT_ERROR_MSG) + this._sName + ".");
        }
        if (round < this._dDoubleRangeLow) {
            throw new NumberFormatException(String.valueOf(this._sName) + Nbr.RANGE_LOW_ERROR_MSG + this.fmt(this._dDoubleRangeLow) + ".");
        }
        if (round > this._dDoubleRangeHigh) {
            throw new NumberFormatException(String.valueOf(this._sName) + Nbr.RANGE_HIGH_ERROR_MSG + this.fmt(this._dDoubleRangeHigh) + ".");
        }
        this.setText(this.fmt(round));
        return round;
    }
    
    public static double toDouble(final String s) throws NumberFormatException {
        double doubleValue;
        if (s == null) {
            doubleValue = 0.0;
        }
        else if (s.trim().equals("")) {
            doubleValue = 0.0;
        }
        else {
            doubleValue = new Double(s);
        }
        return doubleValue;
    }
}

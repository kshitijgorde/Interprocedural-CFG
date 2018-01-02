// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.applet.Applet;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class Nbr extends JTextField implements KeyListener, FocusListener
{
    public static String CL;
    public static String INPUT_ERROR_MSG;
    public static String RANGE_LOW_ERROR_MSG;
    public static String RANGE_HIGH_ERROR_MSG;
    protected String _sName;
    protected String _sHover;
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
    
    public Nbr(String parameter, String parameter2, final double dDoubleRangeLow, final double dDoubleRangeHigh, final int nDecimals, final int nFormat, final Applet applet) {
        this._nDecimals = 0;
        this._nFormat = 5;
        this.myApplet = null;
        this.sChange = "";
        if (applet instanceof CalculatorApplet) {
            this.myApplet = (CalculatorApplet)applet;
            parameter2 = this.myApplet.getParameter("MSG_" + parameter, parameter2);
            this.addActionListener(this.myApplet);
            this.addKeyListener(this);
            this.addFocusListener(this);
        }
        this._sHover = applet.getParameter("HOVER_" + parameter);
        if (parameter == null) {
            parameter = "0";
        }
        else {
            parameter = applet.getParameter(parameter);
            if (parameter == null) {
                parameter = "0";
            }
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
            switch (s.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    array[n] = s.charAt(i);
                    ++n;
                    break;
                }
                case ',': {
                    if (Fmt.bEuropeDecimals) {
                        array[n] = '.';
                        ++n;
                        break;
                    }
                    break;
                }
                case '.': {
                    if (!Fmt.bEuropeDecimals || (Fmt.bEuropeDecimals && Fmt.sPC.equals(" ") && Fmt.sCC.equals(" "))) {
                        array[n] = '.';
                        ++n;
                        break;
                    }
                    break;
                }
                case '-': {
                    array[n] = '-';
                    ++n;
                    break;
                }
                case '(': {
                    array[n] = '-';
                    ++n;
                    break;
                }
            }
        }
        return String.valueOf(array, 0, n);
    }
    
    public static String Strip2(final String s) {
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
                    default: {
                        array[n] = s.charAt(i);
                        ++n;
                        break;
                    }
                    case 9:
                    case 32:
                    case 47:
                    case 60:
                    case 62: {
                        break;
                    }
                }
            }
        }
        return String.valueOf(array, 0, n);
    }
    
    public synchronized void disable() {
        this.setBackground(Color.lightGray);
        this.setEditable(false);
    }
    
    public synchronized void enable() {
        this.setBackground(Color.white);
        this.setEditable(true);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.sChange = this.getText();
        this.selectAll();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (!focusEvent.isTemporary()) {
            if (this.myApplet != null && !this.sChange.equals(this.getText())) {
                this.myApplet.actionPerformed(new ActionEvent(this, 1001, ""));
            }
            this.select(0, 0);
        }
    }
    
    public String getHover() {
        return this._sHover;
    }
    
    public String getLabel() {
        return String.valueOf(this._sName) + Nbr.CL;
    }
    
    public String getName() {
        return this._sName;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 16) {
            this.replaceSelection("");
        }
        if (!this.keypressOk(keyEvent.getKeyChar())) {
            int caretPosition = this.getCaretPosition();
            this.setText(Strip2(this.getText()));
            if (--caretPosition < 0) {
                caretPosition = 0;
            }
            try {
                this.setCaretPosition(caretPosition);
            }
            catch (IllegalArgumentException ex) {
                this.setCaretPosition(0);
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == '\n' || keyEvent.getKeyChar() == '\t') {
            this.myApplet.calculate();
        }
    }
    
    public boolean keypressOk(final char c) {
        return (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && c != '!' && c != '@' && c != '#' && c != '^' && c != '&' && c != '*' && c != '_' && c != '+' && c != '=' && c != '{' && c != '}' && c != '[' && c != ']' && c != '|' && c != '\\' && c != ';' && c != ':' && c != '\'' && c != '\"' && c != '<' && c != '>' && c != '?' && c != '~' && c != '/' && c != '`' && c != '~' && (c != '.' || this._nDecimals > 0) && (c != '-' || this._dDoubleRangeLow < 0.0);
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
            throw new NumberFormatException(String.valueOf(this._sName) + Nbr.RANGE_LOW_ERROR_MSG + Fmt.fmt(this._nFormat, this._dDoubleRangeLow, this._nDecimals) + ".");
        }
        if (round > this._dDoubleRangeHigh) {
            throw new NumberFormatException(String.valueOf(this._sName) + Nbr.RANGE_HIGH_ERROR_MSG + Fmt.fmt(this._nFormat, this._dDoubleRangeHigh, this._nDecimals) + ".");
        }
        this.setText(Fmt.fmt(this._nFormat, round, this._nDecimals));
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

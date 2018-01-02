// 
// Decompiled by Procyon v0.5.30
// 

package name.zentner.adrian.easySlider;

import java.awt.event.AdjustmentEvent;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Color;
import netscape.javascript.JSObject;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

public class EasySlider extends Applet implements AdjustmentListener
{
    protected String _fldlong;
    protected String _fldint;
    protected String _fldif;
    protected String b;
    protected boolean _fldfor;
    private Scrollbar a;
    protected String _fldnull;
    private double _fldgoto;
    private double _fldchar;
    private double c;
    private int _fldvoid;
    private boolean _flddo;
    private boolean d;
    private int _fldbyte;
    private String _fldtry;
    protected JSObject _fldelse;
    protected String _fldnew;
    protected String[] _fldcase;
    
    public void init() {
        if (this.getParameter("output") != null) {
            this._fldfor = Boolean.valueOf(this.getParameter("output"));
        }
        this.a("==================================================\n");
        this.a(this.getAppletInfo());
        this.a("==================================================\n");
        if (this.getParameter("boxmessage") == null) {
            this.getAppletContext().showStatus(String.valueOf(this._fldlong) + "V" + this._fldint);
        }
        else {
            this.getAppletContext().showStatus(this.getParameter("boxmessage"));
        }
        if (this.getParameter("boxfgcolor") == null) {
            this.setForeground(Color.black);
        }
        else {
            this.setForeground(Color.decode(this.getParameter("boxfgcolor")));
        }
        if (this.getParameter("boxbgcolor") == null) {
            this.setBackground(Color.white);
        }
        else {
            this.setBackground(Color.decode(this.getParameter("boxbgcolor")));
        }
        if (this.getParameter("variable") != null) {
            this._fldnull = this.getParameter("variable");
        }
        if (this.getParameter("digits") != null) {
            this.setDigits(this.getParameter("digits"));
        }
        if (this.getParameter("updateOnDrag") != null) {
            this.d = Boolean.valueOf(this.getParameter("updateOnDrag"));
        }
        if (this.getParameter("rangeException") != null) {
            this.setRangeException(this.getParameter("rangeException"));
        }
        if (this.getParameter("discrete") != null) {
            this._flddo = Boolean.valueOf(this.getParameter("discrete"));
        }
        if (this.getParameter("steps") != null) {
            this._fldvoid = Math.max(Math.abs(this.a(this.getParameter("steps"), this._fldvoid)), 1);
        }
        if (this.getParameter("orientation") == null) {
            this.a = new Scrollbar(0, (int)(this._fldvoid * (this._fldgoto - this._fldchar) / (this.c - this._fldchar)), 1, 0, this._fldvoid + 1);
        }
        else if (this.getParameter("orientation").equalsIgnoreCase("vertical")) {
            this.a = new Scrollbar(1, (int)(this._fldvoid * (this.c - this._fldgoto) / (this.c - this._fldchar)), 1, 0, this._fldvoid + 1);
        }
        else {
            this.a = new Scrollbar(0, (int)(this._fldvoid * (this._fldgoto - this._fldchar) / (this.c - this._fldchar)), 1, 0, this._fldvoid + 1);
        }
        if (this.getParameter("enabled") != null) {
            this.a.setEnabled(Boolean.valueOf(this.getParameter("enabled")));
        }
        this.a.setBlockIncrement((int)Math.max(Math.round(this._fldvoid / 10.0), 1L));
        this.a.setForeground(this.getForeground());
        this.a.setBackground(this.getBackground());
        this.add(this.a);
        this.setLayout(new GridLayout(1, 1));
        this._fldelse = JSObject.getWindow((Applet)this);
        if (this.getParameter("jsCallback") != null) {
            this._fldnew = this.getParameter("jsCallback");
        }
        else {
            this.a("warning @ init(): parameter \"jsCallback\" is undefined");
        }
        double n = this._fldchar;
        if (this.getParameter("minValue") != null) {
            n = this.a(this.getParameter("minValue"), n);
        }
        double n2 = this.c;
        if (this.getParameter("maxValue") != null) {
            n2 = this.a(this.getParameter("maxValue"), n2);
        }
        this._fldchar = Math.min(n, n2);
        this.c = Math.max(n, n2);
        this._fldgoto = this._fldchar + (this.c - this._fldchar) / 2.0;
        if (this.getParameter("value") != null) {
            this.setValue(this.getParameter("value"));
        }
        this.a("Applet initialized");
    }
    
    public void start() {
        if (this.getParameter("copyright") != null) {
            if (this.getParameter("copyright").equalsIgnoreCase(String.valueOf(this._fldif) + " (" + this.b + ")")) {
                this.a.addAdjustmentListener(this);
            }
            else {
                this.a("error @ start(): parameter \"copyright\" must be \"" + this._fldif + " (" + this.b + ")" + "\"");
            }
        }
        else {
            this.a("error @ start(): parameter \"copyright\" is undefined");
        }
        this.a("Applet started");
    }
    
    public void stop() {
        this.a("Applet stopped");
    }
    
    public void destroy() {
        this.a("Applet destroyed");
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public String getAppletInfo() {
        return String.valueOf(this._fldlong) + " " + "Version " + this._fldint + " \n" + "Author: " + this._fldif + " \n" + "Homepage: " + this.b + " \n";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "image", "java.net.URL", "splash screen to display during applet loading" }, { "boxmessage", "java.lang.String", "message to display in the status bar" }, { "boxfgcolor", "java.awt.Color", "applet foreground color" }, { "boxbgcolor", "java.awt.Color", "applet background color" }, { "output", "java.lang.Boolean", "applet system output enabled state: (default: true)" }, { "variable", "java.lang.String", "name of the variable: (default: slider)" }, { "value", "java.lang.Double", "initial slider value: (default: 0.000)" }, { "minValue", "java.lang.Double", "minimum slider value: (default: 0.000)" }, { "maxValue", "java.lang.Double", "maximum slider value: (default: 1.000)" }, { "digits", "java.lang.Integer", "number of decimal places: (default: 3)" }, { "updateOnDrag", "java.lang.Boolean", "update value during dragging: (default: true)" }, { "rangeException", "java.lang.String", "handling of range exception: ignore|limit|adjust (default: ignore)" }, { "discrete", "java.lang.Boolean", "round value to slider step width: (default: true)" }, { "steps", "java.lang.Integer", "number of discrete slider steps: (default: 100)" }, { "orientation", "java.lang.String", "slider orientation: horizontal|vertical (default: horizontal)" }, { "enabled", "java.lang.Boolean", "initial slider enabled state: (default: true)" }, { "jsCallback", "java.lang.String", "javascript callback function: (default: System.out)" }, { "copyright", "java.lang.String", String.valueOf(this._fldif) + " (" + this.b + ")" } };
    }
    
    private void a(final String s) {
        if (!this._fldfor) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(String.valueOf(this._fldlong) + ": " + stringTokenizer.nextToken());
        }
    }
    
    private int a(final String s, int round) {
        try {
            round = Math.round(Float.valueOf(s));
        }
        catch (NumberFormatException ex) {
            this.a("error @ getInteger(): \"" + s + "\" is not a number");
        }
        return round;
    }
    
    private double a(final String s, double doubleValue) {
        try {
            doubleValue = Double.valueOf(s);
        }
        catch (NumberFormatException ex) {
            this.a("error @ getDouble(): \"" + s + "\" is not a number");
        }
        return doubleValue;
    }
    
    public String getEnabled() {
        return String.valueOf(this.a.isEnabled());
    }
    
    public String setEnabled(final String s) {
        this.a.setEnabled(Boolean.valueOf(s));
        return this.getEnabled();
    }
    
    public String getVariable() {
        return this._fldnull;
    }
    
    public String setVariable(final String fldnull) {
        this._fldnull = fldnull;
        return this.getVariable();
    }
    
    public String getUpdateOnDrag() {
        return String.valueOf(this.d);
    }
    
    public String setUpdateOnDrag(final String s) {
        this.d = Boolean.valueOf(s);
        return this.getUpdateOnDrag();
    }
    
    public String getDiscrete() {
        return String.valueOf(this._flddo);
    }
    
    public String setDiscrete(final String s) {
        this._flddo = Boolean.valueOf(s);
        return this.getDiscrete();
    }
    
    public String getDigits() {
        return String.valueOf(this._fldbyte);
    }
    
    public String setDigits(final String s) {
        this._fldbyte = Math.min(Math.abs(this.a(s, this._fldbyte)), 12);
        return this.getDigits();
    }
    
    public String getRangeException() {
        return this._fldtry;
    }
    
    public String setRangeException(final String s) {
        if (s.equalsIgnoreCase("limit")) {
            this._fldtry = "limit";
        }
        else if (s.equalsIgnoreCase("ignore")) {
            this._fldtry = "ignore";
        }
        else if (s.equalsIgnoreCase("adjust")) {
            this._fldtry = "adjust";
        }
        else {
            this.a("warning @ setRangeException(): argument rangeException=\"" + s + "\" is invalid");
        }
        return this.getRangeException();
    }
    
    public String getValue() {
        if (this._fldbyte == 0) {
            return String.valueOf(Math.round(this._fldgoto));
        }
        return String.valueOf(this.a(this._fldgoto));
    }
    
    public String setValue(final String s) {
        this._mthif(this.a(s, this._fldgoto));
        return this.getValue();
    }
    
    private void _mthif(final double n) {
        this._fldgoto = this._mthdo(n);
        this.a();
        this.a(false);
    }
    
    public String getValues() {
        return String.valueOf(this._fldgoto) + " " + this._fldchar + " " + this.c;
    }
    
    public String setValues(final String value, final String s, final String s2) {
        final double a = this.a(s, this._fldchar);
        final double a2 = this.a(s2, this.c);
        this._fldgoto = a + (a2 - a) / 2.0;
        this.a(a, a2);
        this.setValue(value);
        this.a(false);
        return this.getValues();
    }
    
    private double _mthdo(final double n) {
        double n2 = n;
        if (n > this.c) {
            if (this._fldtry.equalsIgnoreCase("limit")) {
                this.a("warning @ checkValue(): \"" + n + "\" is higher than maxValue; limiting value to \"" + this.c + "\"");
                n2 = this.c;
            }
            else if (this._fldtry.equalsIgnoreCase("ignore")) {
                this.a("warning @ checkValue(): \"" + n + "\" is higher than maxValue; ignoring value");
                n2 = this._fldgoto;
            }
            else if (this._fldtry.equalsIgnoreCase("adjust")) {
                this.a("info @ checkValue(): \"" + n + "\" is higher than maxValue; adjusting new max range to \"" + this.c + "\"");
                this.c = n2;
            }
            else {
                this.a("warning @ setRangeException(): argument rangeException=\"" + this._fldtry + "\" is invalid");
            }
        }
        if (n < this._fldchar) {
            if (this._fldtry.equalsIgnoreCase("limit")) {
                this.a("warning @ checkValue(): \"" + n + "\" is lower than minValue; limiting value to \"" + this._fldchar + "\"");
                n2 = this.c;
            }
            else if (this._fldtry.equalsIgnoreCase("ignore")) {
                this.a("warning @ checkValue(): \"" + n + "\" is lower than minValue; ignoring value");
                n2 = this._fldgoto;
            }
            else if (this._fldtry.equalsIgnoreCase("adjust")) {
                this.a("info @ checkValue(): \"" + n + "\" is lower than minValue; adjusting new max range to \"" + this._fldchar + "\"");
                this._fldchar = n2;
            }
            else {
                this.a("error @ checkValue(): argument rangeException=\"" + this._fldtry + "\" is invalid");
            }
        }
        if (this._flddo) {
            final double n3 = (this.c - this._fldchar) / this._fldvoid;
            n2 = this._fldchar + Math.round((n2 - this._fldchar) / n3) * n3;
        }
        return n2;
    }
    
    private void a() {
        if (this.a.getOrientation() == 0) {
            this.a.setValue((int)Math.round(this._fldvoid * (this._fldgoto - this._fldchar) / (this.c - this._fldchar)));
            return;
        }
        this.a.setValue((int)Math.round(this._fldvoid * (this.c - this._fldgoto) / (this.c - this._fldchar)));
    }
    
    private void a(final boolean b) {
        if (this.a.getOrientation() == 0) {
            this.a.setValue((int)Math.round(this._fldvoid * (this._fldgoto - this._fldchar) / (this.c - this._fldchar)));
        }
        else {
            this.a.setValue((int)Math.round(this._fldvoid * (this.c - this._fldgoto) / (this.c - this._fldchar)));
        }
        if (this.d || !b) {
            if (!this._fldnew.equals("noCallback")) {
                this._fldcase[0] = this.getValue();
                this._fldcase[1] = this._fldnull;
                this._fldcase[2] = String.valueOf(b);
                this._fldelse.call(this._fldnew, (Object[])this._fldcase);
                return;
            }
            this.a(String.valueOf(this._fldnull) + "Update" + "(" + this._fldgoto + ", " + this._fldnull + ", " + b + ")");
        }
    }
    
    public String getMinValue() {
        return String.valueOf(this._fldchar);
    }
    
    public String setMinValue(final String s) {
        this.setRange(s, String.valueOf(this.c));
        return this.getMinValue();
    }
    
    public String getMaxValue() {
        return String.valueOf(this.c);
    }
    
    public String setMaxValue(final String s) {
        this.setRange(String.valueOf(this._fldchar), s);
        return this.getMaxValue();
    }
    
    public String getRange() {
        return String.valueOf(this._fldchar) + " " + this.c;
    }
    
    public String setRange(final String s, final String s2) {
        this.a(this.a(s, this._fldchar), this.a(s2, this.c));
        return this.getRange();
    }
    
    private void a(final double n, final double n2) {
        if (n > n2) {
            this.a("warning @ setRange(): min=\"" + n + "\" is higher than max=\"" + n2 + "\"; switching min and max");
            this._fldchar = n2;
            this.c = n;
        }
        else {
            this._fldchar = n;
            this.c = n2;
        }
        if (this._fldgoto > this.c) {
            if (this._fldtry.equals("limit")) {
                this.a("warning @ setRange(): current value=\"" + this._fldgoto + "\" is higher than new maxValue; limiting value to \"" + this.c + "\"");
                this._fldgoto = this.c;
            }
            else if (this._fldtry.equals("ignore")) {
                this.a("warning @ setRange(): current value=\"" + this._fldgoto + "\" is higher than new maxValue; setting value to mean value of the range \"" + (this.c - this._fldchar) / 2.0 + "\"");
                this._fldgoto = this._fldchar + (this.c - this._fldchar) / 2.0;
            }
            else if (this._fldtry.equals("adjust")) {
                this.a("warning @ setRange(): current value=\"" + this._fldgoto + "\" is higher than new maxValue; re-adjusting maxValue to \"" + this._fldgoto + "\"");
                this.c = this._fldgoto;
            }
            else {
                this.a("error @ checkValue(): argument rangeException=\"" + this._fldtry + "\" is invalid");
            }
        }
        if (this._fldgoto < this._fldchar) {
            if (this._fldtry.equals("limit")) {
                this.a("warning @ setRange(): current value=\"" + this._fldgoto + "\" is lower than new minValue; limiting value to \"" + this._fldchar + "\"");
                this._fldgoto = this.c;
            }
            else if (this._fldtry.equals("ignore")) {
                this.a("warning @ setRange(): current value=\"" + this._fldgoto + "\" is lower than new minValue; setting value to mean value of the range \"" + (this.c - this._fldchar) / 2.0 + "\"");
                this._fldgoto = this._fldchar + (this.c - this._fldchar) / 2.0;
            }
            else if (this._fldtry.equals("adjust")) {
                this.a("warning @ setRange(): current value=\"" + this._fldgoto + "\" is lower than new minValue; re-adjusting minValue to \"" + this._fldgoto + "\"");
                this.c = this._fldgoto;
            }
            else {
                this.a("error @ checkValue(): argument rangeException=\"" + this._fldtry + "\" is invalid");
            }
        }
        this._mthif(this._fldgoto);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (this.a.getOrientation() == 0) {
            this._fldgoto = adjustmentEvent.getValue() / this._fldvoid * (this.c - this._fldchar) + this._fldchar;
        }
        else {
            this._fldgoto = this.c - adjustmentEvent.getValue() / this._fldvoid * (this.c - this._fldchar);
        }
        if (adjustmentEvent.paramString().indexOf("isAdjusting") == -1) {
            this.a(false);
            return;
        }
        if (adjustmentEvent.paramString().endsWith("isAdjusting=false")) {
            this.a(false);
            return;
        }
        this.a(true);
    }
    
    private double a(final double n) {
        return Math.round(Math.pow(10.0, Math.round(Math.abs(this._fldbyte))) * n) / Math.pow(10.0, Math.round(Math.abs(this._fldbyte)));
    }
    
    public EasySlider() {
        this._fldlong = "Easy Slider";
        this._fldint = "0.3 (build 1)";
        this._fldif = "Adrian Zentner";
        this.b = "www.adrian.zentner.name";
        this._fldfor = true;
        this._fldnull = "slider";
        this.c = 1.0;
        this._fldvoid = 100;
        this._flddo = true;
        this.d = true;
        this._fldbyte = 3;
        this._fldtry = "ignore";
        this._fldnew = "noCallback";
        this._fldcase = new String[3];
    }
}

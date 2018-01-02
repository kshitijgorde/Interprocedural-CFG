import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Canvas;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class precession13 extends Applet
{
    Panel p;
    Canvas c;
    Label L11;
    Label L12;
    Label L13;
    Label L21;
    Label L31;
    Label L41;
    Label L51;
    Label L61;
    Label L71;
    Label L81;
    Label L91;
    Label L101;
    Label L111;
    Label L112;
    Label L1;
    Label L2;
    Label L3;
    Label L_1;
    Label L_2;
    Label L_3;
    Label L_4;
    Label L_5;
    Label L_6;
    Label L_7;
    Label L_8;
    Checkbox box;
    Button run;
    Checkbox negDec;
    String str1;
    String str2;
    String str3;
    String str4;
    String str5;
    String str6;
    String str7;
    String str8;
    String str9;
    String str10;
    String str11;
    Compute comp;
    TextField initalEpoqueField;
    TextField finalEpoqueField;
    TextField initialJDField;
    TextField finalJDField;
    TextField raHinitial;
    TextField raHfinal;
    TextField raMininitial;
    TextField raMinFinal;
    TextField raSecInitial;
    TextField raSecFinal;
    TextField raDecimInitial;
    TextField raDecimFinal;
    TextField decDegInitial;
    TextField decDegFinal;
    TextField decMinInitial;
    TextField decMinFinal;
    TextField decSecInitial;
    TextField decSecFinal;
    TextField decDecimalInitial;
    TextField decDecimalFinal;
    final double K = 0.017453292519943295;
    double epoqueInitial;
    double epoqueFinal;
    double JDInitial;
    double JDFinal;
    double raInitial;
    double raFinal;
    double decInitial;
    double decFinal;
    int raH_Final;
    int raMin_Final;
    double raSec_Final;
    int decDeg_Final;
    int decMin_Final;
    double decSec_Final;
    boolean smallDecOK;
    boolean demo;
    
    public void init() {
        final URL url = this.getDocumentBase();
        String myStr = url.toString();
        myStr = String.valueOf(myStr) + "1234567890123456789012345";
        final String wwwStr = myStr.substring(0, 27);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.initialJDField.disable();
        this.finalJDField.disable();
        this.raHfinal.disable();
        this.raMinFinal.disable();
        this.raSecFinal.disable();
        this.raDecimFinal.disable();
        this.decDegFinal.disable();
        this.decMinFinal.disable();
        this.decSecFinal.disable();
        this.decDecimalFinal.disable();
        this.raDecimInitial.disable();
        this.decDecimalInitial.disable();
        (this.p = new Panel()).setLayout(new GridLayout(17, 3));
        this.p.add(this.L11);
        this.p.add(this.L12);
        this.p.add(this.L13);
        this.p.add(this.L21);
        this.initalEpoqueField.setBackground(new Color(220, 255, 220));
        this.p.add(this.initalEpoqueField);
        this.finalEpoqueField.setBackground(new Color(220, 255, 220));
        this.p.add(this.finalEpoqueField);
        this.p.add(this.L31);
        this.initialJDField.setBackground(new Color(255, 220, 220));
        this.p.add(this.initialJDField);
        this.finalJDField.setBackground(new Color(255, 220, 220));
        this.p.add(this.finalJDField);
        this.p.add(this.L41);
        this.raHinitial.setBackground(new Color(220, 255, 220));
        this.p.add(this.raHinitial);
        this.raHfinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.raHfinal);
        this.p.add(this.L51);
        this.p.add(this.raMininitial);
        this.raMininitial.setBackground(new Color(220, 255, 220));
        this.raMinFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.raMinFinal);
        this.p.add(this.L61);
        this.p.add(this.raSecInitial);
        this.raSecInitial.setBackground(new Color(220, 255, 220));
        this.raSecFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.raSecFinal);
        this.p.add(this.L71);
        this.p.add(this.raDecimInitial);
        this.raDecimInitial.setBackground(new Color(255, 220, 220));
        this.raDecimFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.raDecimFinal);
        this.p.add(this.L81);
        this.p.add(this.decDegInitial);
        this.decDegInitial.setBackground(new Color(220, 255, 220));
        this.decDegFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.decDegFinal);
        this.p.add(this.L91);
        this.p.add(this.decMinInitial);
        this.decMinInitial.setBackground(new Color(220, 255, 220));
        this.decMinFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.decMinFinal);
        this.p.add(this.L101);
        this.decSecInitial.setBackground(new Color(220, 255, 220));
        this.p.add(this.decSecInitial);
        this.decSecFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.decSecFinal);
        this.negDec.setState(false);
        this.p.add(this.negDec);
        this.p.add(new Label(""));
        this.p.add(new Label(""));
        this.p.add(this.L111);
        this.decDecimalInitial.setBackground(new Color(255, 220, 220));
        this.p.add(this.decDecimalInitial);
        this.decDecimalFinal.setBackground(new Color(255, 220, 220));
        this.p.add(this.decDecimalFinal);
        this.p.add(this.L_1);
        this.p.add(this.L_2);
        this.p.add(this.L_3);
        this.box.setState(false);
        this.p.add(this.box);
        this.p.add(this.run);
        this.p.add(this.L112);
        this.p.add(this.L_4);
        this.p.add(this.L_5);
        this.p.add(this.L_6);
        this.p.add(this.L1);
        this.p.add(this.L2);
        this.p.add(this.L_7);
        this.p.add(this.L3);
        this.p.add(this.L_8);
        this.add("Center", this.p);
        this.initalEpoqueField.selectAll();
        boolean ok = true;
        final String email = this.getParameter("email");
        final String param = this.getParameter("password");
        if (this.formula(wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
            ok = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (email.length() == 0 || Integer.parseInt(param) != this.formula(email, email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.L112.setText("    D  E  M  O");
        }
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 4644;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public void getInput() {
        double raH = 0.0;
        double raMin = 0.0;
        double decDeg = 0.0;
        double decMin = 0.0;
        double decSec = 0.0;
        this.L112.setText("");
        this.str1 = this.initalEpoqueField.getText();
        this.str2 = this.finalEpoqueField.getText();
        this.str3 = this.raHinitial.getText();
        this.str4 = this.raMininitial.getText();
        this.str5 = this.raSecInitial.getText();
        this.str6 = this.decDegInitial.getText();
        this.str7 = this.decMinInitial.getText();
        this.str8 = this.decSecInitial.getText();
        this.str9 = this.raHfinal.getText();
        this.str10 = this.raMinFinal.getText();
        this.str11 = this.raSecFinal.getText();
        boolean OK = true;
        try {
            System.out.println("Initial J " + this.str1);
            final Double myDouble = Double.valueOf(this.str1);
            this.epoqueInitial = myDouble;
        }
        catch (NumberFormatException ex) {
            OK = false;
        }
        try {
            System.out.println("Final   J " + this.str2);
            final Double myDouble = Double.valueOf(this.str2);
            this.epoqueFinal = myDouble;
        }
        catch (NumberFormatException ex2) {
            OK = false;
        }
        try {
            System.out.println("initial RA = " + this.str3 + " h");
            final Double myDouble = Double.valueOf(this.str3);
            raH = myDouble;
            this.raInitial = raH;
        }
        catch (NumberFormatException ex3) {
            OK = false;
        }
        try {
            System.out.println("initial RA = " + this.str4 + " min");
            final Double myDouble = Double.valueOf(this.str4);
            raMin = myDouble;
            this.raInitial = raH + raMin / 60.0;
        }
        catch (NumberFormatException ex4) {
            OK = false;
        }
        try {
            System.out.println("initial RA = " + this.str5 + " s");
            final Double myDouble = Double.valueOf(this.str5);
            final double raSec = myDouble;
            this.raInitial = raH + raMin / 60.0 + raSec / 3600.0;
        }
        catch (NumberFormatException ex5) {}
        try {
            System.out.println("initial Dec = " + this.str6 + " " + '°');
            final Double myDouble = Double.valueOf(this.str6);
            decDeg = myDouble;
            this.decInitial = decDeg;
        }
        catch (NumberFormatException ex6) {
            OK = false;
        }
        try {
            System.out.println("initial Dec = " + this.str7 + " '");
            final Double myDouble = Double.valueOf(this.str7);
            decMin = myDouble;
            this.decInitial = decDeg + decMin / 60.0;
        }
        catch (NumberFormatException ex7) {
            OK = false;
        }
        try {
            System.out.println("initial Dec = " + this.str8 + " ''");
            final Double myDouble = Double.valueOf(this.str8);
            decSec = myDouble;
            this.decInitial = decDeg + decMin / 60.0 + decSec / 3600.0;
            if (this.negDec.getState()) {
                this.decInitial = -(decDeg + decMin / 60.0 + decSec / 3600.0);
            }
        }
        catch (NumberFormatException ex8) {}
        if (!OK) {
            this.L112.setText("  missing input");
        }
    }
    
    public void putOutput() {
        if (this.str1.length() > 0) {
            this.initialJDField.setText(String.valueOf(this.JDInitial));
        }
        if (this.str2.length() > 0) {
            this.finalJDField.setText(String.valueOf(this.JDFinal));
        }
        this.decDecimalInitial.setText("");
        this.decDecimalFinal.setText("");
        this.decDegFinal.setText("");
        this.decMinFinal.setText("");
        this.decSecFinal.setText("");
        this.raHfinal.setText("");
        this.raMinFinal.setText("");
        this.raSecFinal.setText("");
        this.raDecimFinal.setText("");
        this.decDecimalInitial.setText(String.valueOf(Math.round(1.0E7 * this.decInitial) / 1.0E7));
        this.raDecimInitial.setText(String.valueOf(Math.round(1.0E7 * this.raInitial) / 1.0E7));
        if (this.str1.length() > 0 && this.str2.length() > 0 && this.str3.length() > 0 && this.str4.length() > 0 && this.str6.length() > 0 && this.str7.length() > 0) {
            this.raDecimFinal.setText(String.valueOf(Math.round(1.0E7 * this.raFinal) / 1.0E7));
            this.raHfinal.setText(String.valueOf(this.raH_Final));
            System.out.println("final RA  = " + this.raH_Final + " h");
            this.raMinFinal.setText(String.valueOf(this.raMin_Final));
            System.out.println("final RA  = " + this.raMin_Final + " min");
            this.raSecFinal.setText(String.valueOf(Math.round(1000.0 * this.raSec_Final) / 1000.0));
            System.out.println("final RA  = " + this.raSec_Final + " s");
            System.out.println("final RA  = " + this.raFinal + " h");
            this.raDecimFinal.setText(String.valueOf(Math.round(1.0E7 * this.raFinal) / 1.0E7));
            if (this.decInitial >= 0.0) {
                this.decDecimalFinal.setText(String.valueOf(Math.round(1.0E7 * this.decFinal) / 1.0E7));
            }
            else {
                this.decDecimalFinal.setText(String.valueOf(Math.round(-1.0E7 * this.decFinal) / 1.0E7));
            }
            System.out.println("final Dec = " + this.decDeg_Final + " " + '°');
            System.out.println("final Dec = " + this.decMin_Final + " '");
            System.out.println("final Dec = " + this.decSec_Final + " ''");
            System.out.println("final Dec = " + this.decFinal + " " + '°');
            this.decDegFinal.setText(String.valueOf(this.decDeg_Final));
            this.decMinFinal.setText(String.valueOf(this.decMin_Final));
            this.decSecFinal.setText(String.valueOf(Math.round(1000.0 * this.decSec_Final) / 1000.0));
        }
    }
    
    public void HMS() {
        this.raH_Final = (int)this.raFinal;
        this.raMin_Final = (int)(this.frac(this.raFinal) * 60.0);
        this.raSec_Final = this.frac(this.raFinal) * 60.0 - this.raMin_Final;
        this.raSec_Final *= 60.0;
        this.decDeg_Final = (int)this.decFinal;
        this.decMin_Final = (int)(this.frac(Math.abs(this.decFinal)) * 60.0);
        this.decSec_Final = this.frac(Math.abs(this.decFinal)) * 60.0 - this.decMin_Final;
        this.decSec_Final *= 60.0;
    }
    
    public void calculate() {
        this.comp = new Compute();
        this.JDInitial = this.comp.JD(this.epoqueInitial);
        this.JDFinal = this.comp.JD(this.epoqueFinal);
        final double T = (this.JDInitial - 2451545.0) / 36525.0;
        final double t = (this.JDFinal - this.JDInitial) / 36525.0;
        double zeta = (2306.2181 + 1.39656 * T - 1.39E-4 * T * T) * t + (0.30188 - 3.44E-4 * T) * t * t + 0.017998 * t * t * t;
        double z = (2306.2181 + 1.39656 * T - 1.39E-4 * T * T) * t + (1.09468 + 6.6E-5 * T) * t * t + 0.018203 * t * t * t;
        double theta = (2004.3109 - 0.8533 * T - 2.17E-4 * T * T) * t - (0.42665 + 2.17E-4 * T) * t * t - 0.041833 * t * t * t;
        zeta /= 3600.0;
        z /= 3600.0;
        theta /= 3600.0;
        System.out.println("t= " + t);
        System.out.println("zeta= " + zeta);
        System.out.println("z= " + z);
        System.out.println("theta= " + theta);
        final double A = Math.cos(0.017453292519943295 * this.decInitial) * Math.sin(0.017453292519943295 * (15.0 * this.raInitial + zeta));
        final double B = Math.cos(0.017453292519943295 * theta) * Math.cos(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * (15.0 * this.raInitial + zeta)) - Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * this.decInitial);
        final double C = Math.sin(0.017453292519943295 * theta) * Math.cos(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * (15.0 * this.raInitial + zeta)) + Math.cos(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * this.decInitial);
        System.out.println("A= " + A);
        System.out.println("z= " + B);
        System.out.println("C= " + C);
        final double tan = A / B;
        double alpha = Math.atan2(A, B) / 0.017453292519943295 + z;
        alpha /= 15.0;
        this.raFinal = alpha;
        if (this.raFinal < 0.0) {
            this.raFinal += 24.0;
        }
        if (this.smallDecOK) {
            this.decFinal = Math.asin(C) / 0.017453292519943295;
        }
        else {
            final double cosD = Math.sqrt(A * A + B * B);
            this.decFinal = Math.acos(cosD) / 0.017453292519943295;
        }
        this.HMS();
    }
    
    public boolean handleEvent(final Event evt) {
        if (this.demo) {
            return true;
        }
        if (evt.target instanceof Button && evt.target == this.run) {
            this.getInput();
            this.calculate();
            this.putOutput();
        }
        if (evt.target instanceof Checkbox && evt.target == this.box) {
            this.smallDecOK ^= true;
            this.box.setState(this.smallDecOK);
        }
        final Object target = evt.target;
        return super.handleEvent(evt);
    }
    
    public precession13() {
        this.L11 = new Label("");
        this.L12 = new Label("   Initial");
        this.L13 = new Label("   Final");
        this.L21 = new Label("   J. Epoque");
        this.L31 = new Label("   JD");
        this.L41 = new Label("   RA (h)");
        this.L51 = new Label("   RA (min)");
        this.L61 = new Label("   RA (sec)");
        this.L71 = new Label("   RA (decimal)");
        this.L81 = new Label("   Dec (deg)");
        this.L91 = new Label("   Dec (min)");
        this.L101 = new Label("   Dec (sec)");
        this.L111 = new Label("   Dec (decimal)");
        this.L112 = new Label("");
        this.L1 = new Label("Precession 1.1");
        this.L2 = new Label("© 2003-2011   J. Giesen");
        this.L3 = new Label(" www.GeoAstro.de");
        this.L_1 = new Label("");
        this.L_2 = new Label("");
        this.L_3 = new Label("");
        this.L_4 = new Label("");
        this.L_5 = new Label("");
        this.L_6 = new Label("");
        this.L_7 = new Label("");
        this.L_8 = new Label("");
        this.box = new Checkbox("small declin.");
        this.run = new Button("Calculate");
        this.negDec = new Checkbox("Dec < 0");
        this.str1 = "";
        this.str2 = "";
        this.str3 = "";
        this.str4 = "";
        this.str5 = "";
        this.str6 = "";
        this.str7 = "";
        this.str8 = "";
        this.str9 = "";
        this.str10 = "";
        this.str11 = "";
        this.initalEpoqueField = new TextField("", 10);
        this.finalEpoqueField = new TextField("", 10);
        this.initialJDField = new TextField("", 10);
        this.finalJDField = new TextField("", 10);
        this.raHinitial = new TextField("", 10);
        this.raHfinal = new TextField("", 10);
        this.raMininitial = new TextField("", 10);
        this.raMinFinal = new TextField("", 10);
        this.raSecInitial = new TextField("", 10);
        this.raSecFinal = new TextField("", 10);
        this.raDecimInitial = new TextField("", 10);
        this.raDecimFinal = new TextField("", 10);
        this.decDegInitial = new TextField("", 10);
        this.decDegFinal = new TextField("", 10);
        this.decMinInitial = new TextField("", 10);
        this.decMinFinal = new TextField("", 10);
        this.decSecInitial = new TextField("", 10);
        this.decSecFinal = new TextField("", 10);
        this.decDecimalInitial = new TextField("", 10);
        this.decDecimalFinal = new TextField("", 10);
        this.smallDecOK = false;
        this.demo = true;
    }
}

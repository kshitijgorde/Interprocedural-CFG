import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PrecessionMotion071 extends Applet
{
    char deg;
    final double K = 0.017453292519943295;
    String vers1;
    String vers2;
    String vers3;
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
    Label L121;
    Label L131;
    Label L123;
    Label L133;
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
    Label L_9;
    Label L_10;
    Label L_11;
    Label dec1;
    Label dec2;
    Button run;
    Checkbox pmBox;
    Checkbox PlusMinusDec;
    Checkbox precessionBox;
    Button clearButton;
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
    String str12;
    String str13;
    Compute comp;
    TextField initialEpoqueField;
    TextField finalEpoqueField;
    Label initialJDField;
    Label finalJDField;
    TextField raHinitial;
    Label raHfinal;
    TextField raMinInitial;
    Label raMinFinal;
    TextField raSecInitial;
    Label raSecFinal;
    Label raDecimInitial;
    Label raDecimFinal;
    TextField decDegInitial;
    Label decDegFinal;
    TextField decMinInitial;
    Label decMinFinal;
    TextField decSecInitial;
    Label decSecFinal;
    Label decDecimalInitial;
    Label decDecimalFinal;
    TextField motionRA;
    TextField motionDec;
    Label pmRAField;
    Label pmDecField;
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
    boolean demo;
    double pmRA;
    double pmDec;
    double pmRA1;
    double pmDec1;
    int H;
    int MIN;
    double SEC;
    boolean pmOK;
    double raH;
    double raMin;
    double raSec;
    double decDeg;
    double decMin;
    double decSec;
    double xx;
    double yy;
    double zz;
    double THETA;
    double PHI;
    double LInitial;
    double LFinal;
    double BInitial;
    double BFinal;
    boolean decPos;
    boolean precessionOK;
    
    public void init() {
        final URL url = this.getDocumentBase();
        String myStr = url.toString();
        myStr = String.valueOf(myStr) + "1234567890123456789012345";
        final String wwwStr = myStr.substring(0, 27);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(24, 3));
        this.add(this.L11);
        this.add(this.L12);
        this.add(this.L13);
        this.add(this.L21);
        this.initialEpoqueField.setBackground(new Color(220, 255, 220));
        this.add(this.initialEpoqueField);
        this.initialEpoqueField.selectAll();
        this.finalEpoqueField.setBackground(new Color(220, 255, 220));
        this.add(this.finalEpoqueField);
        this.add(this.L31);
        this.initialJDField.setBackground(new Color(255, 220, 220));
        this.add(this.initialJDField);
        this.finalJDField.setBackground(new Color(255, 220, 220));
        this.add(this.finalJDField);
        this.add(this.L41);
        this.raHinitial.setBackground(new Color(220, 255, 220));
        this.add(this.raHinitial);
        this.raHfinal.setBackground(new Color(255, 220, 220));
        this.add(this.raHfinal);
        this.add(this.L51);
        this.add(this.raMinInitial);
        this.raMinInitial.setBackground(new Color(220, 255, 220));
        this.raMinFinal.setBackground(new Color(255, 220, 220));
        this.add(this.raMinFinal);
        this.add(this.L61);
        this.raSecInitial.setBackground(new Color(220, 255, 220));
        this.add(this.raSecInitial);
        this.raSecFinal.setBackground(new Color(255, 220, 220));
        this.add(this.raSecFinal);
        this.add(this.L71);
        this.add(this.raDecimInitial);
        this.raDecimInitial.setBackground(new Color(255, 220, 220));
        this.raDecimFinal.setBackground(new Color(255, 220, 220));
        this.add(this.raDecimFinal);
        this.add(this.dec1);
        this.add(this.PlusMinusDec);
        this.PlusMinusDec.setBackground(new Color(255, 220, 220));
        this.dec2.setBackground(new Color(255, 220, 220));
        this.PlusMinusDec.setState(true);
        this.add(this.dec2);
        this.add(this.L81);
        this.add(this.decDegInitial);
        this.decDegInitial.setBackground(new Color(220, 255, 220));
        this.decDegFinal.setBackground(new Color(255, 220, 220));
        this.add(this.decDegFinal);
        this.add(this.L91);
        this.add(this.decMinInitial);
        this.decMinInitial.setBackground(new Color(220, 255, 220));
        this.decMinFinal.setBackground(new Color(255, 220, 220));
        this.add(this.decMinFinal);
        this.add(this.L101);
        this.decSecInitial.setBackground(new Color(220, 255, 220));
        this.add(this.decSecInitial);
        this.decSecFinal.setBackground(new Color(255, 220, 220));
        this.add(this.decSecFinal);
        this.add(this.L111);
        this.decDecimalInitial.setBackground(new Color(255, 220, 220));
        this.add(this.decDecimalInitial);
        this.decDecimalFinal.setBackground(new Color(255, 220, 220));
        this.add(this.decDecimalFinal);
        this.add(this.L121);
        this.motionRA.setBackground(new Color(220, 255, 220));
        this.add(this.motionRA);
        this.pmRAField.setBackground(new Color(255, 220, 220));
        this.add(this.pmRAField);
        this.add(this.L131);
        this.motionDec.setBackground(new Color(220, 255, 220));
        this.add(this.motionDec);
        this.pmDecField.setBackground(new Color(255, 220, 220));
        this.add(this.pmDecField);
        this.add(this.L_1);
        this.add(this.L112);
        this.add(this.L_3);
        this.add(new Label("   Precession"));
        this.add(this.precessionBox);
        this.precessionBox.setState(true);
        this.add(new Label(""));
        this.add(new Label("   Proper Motion"));
        this.add(this.pmBox);
        this.pmBox.setState(this.pmOK);
        this.add(this.L_9);
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(this.run);
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(this.clearButton);
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(new Label(""));
        this.add(this.L1);
        this.add(this.L2);
        this.add(this.L_10);
        this.add(this.L3);
        this.add(this.L_11);
        this.initialEpoqueField.selectAll();
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
        this.repaint();
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 7444;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public String checkInput(String str, final int n) {
        String s = "";
        String s2 = "";
        for (int i = 0; i < str.length(); ++i) {
            final char c = str.charAt(i);
            if (c == ',') {
                s = String.valueOf(s) + ".";
            }
            else {
                s = String.valueOf(s) + c;
            }
        }
        str = s;
        s2 = "";
        if (n == 1) {
            for (int j = 0; j < str.length(); ++j) {
                final char c = str.charAt(j);
                if (c == '-') {
                    s = String.valueOf(s);
                }
                else {
                    s = String.valueOf(s) + c;
                }
            }
            for (int k = 0; k < str.length(); ++k) {
                final char c = str.charAt(k);
                if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.') {
                    s2 = String.valueOf(s2) + c;
                }
            }
        }
        else {
            for (int j = 0; j < str.length(); ++j) {
                final char c = str.charAt(j);
                if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.' || c == '-') {
                    s2 = String.valueOf(s2) + c;
                }
            }
        }
        return s2;
    }
    
    public void getInput() {
        this.L112.setText("");
        this.str1 = this.initialEpoqueField.getText();
        this.initialEpoqueField.setText(this.str1);
        this.str2 = this.finalEpoqueField.getText();
        this.finalEpoqueField.setText(this.str2);
        this.str3 = this.raHinitial.getText();
        this.str3 = this.checkInput(this.str3, 1);
        this.raHinitial.setText(this.str3);
        this.str4 = this.raMinInitial.getText();
        this.str4 = this.checkInput(this.str4, 1);
        this.raMinInitial.setText(this.str4);
        this.str5 = this.raSecInitial.getText();
        this.str5 = this.checkInput(this.str5, 1);
        this.raSecInitial.setText(this.str5);
        this.str6 = this.decDegInitial.getText();
        this.str6 = this.checkInput(this.str6, 1);
        this.decDegInitial.setText(this.str6);
        this.str7 = this.decMinInitial.getText();
        this.str7 = this.checkInput(this.str7, 1);
        this.decMinInitial.setText(this.str7);
        this.str8 = this.decSecInitial.getText();
        this.str8 = this.checkInput(this.str8, 1);
        this.decSecInitial.setText(this.str8);
        this.str9 = this.raHfinal.getText();
        this.str10 = this.raMinFinal.getText();
        this.str11 = this.raSecFinal.getText();
        this.str12 = this.motionRA.getText();
        this.str12 = this.checkInput(this.str12, 0);
        this.motionRA.setText(this.str12);
        this.str13 = this.motionDec.getText();
        this.str13 = this.checkInput(this.str13, 0);
        this.motionDec.setText(this.str13);
        boolean OK = true;
        System.out.println("");
        System.out.println(String.valueOf(this.vers1) + "  " + this.vers2 + "  " + this.vers3);
        if (this.pmOK) {
            System.out.println("Calculate proper motion");
        }
        if (!this.pmOK) {
            System.out.println("Calculate proper motion and precession");
        }
        try {
            System.out.println("Initial J " + this.str1);
            final Double myDouble = Double.valueOf(this.str1);
            this.epoqueInitial = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("  Epoque missing");
        }
        try {
            System.out.println("Final   J " + this.str2);
            final Double myDouble = Double.valueOf(this.str2);
            this.epoqueFinal = myDouble;
        }
        catch (NumberFormatException ex2) {
            this.L112.setText("  Epoque missing");
        }
        try {
            final Double myDouble = Double.valueOf(this.str3);
            this.raH = myDouble;
            if (this.raH > 24.0) {
                this.L112.setText("  RA > 24 ?");
            }
            this.raInitial = this.raH;
        }
        catch (NumberFormatException ex3) {
            OK = false;
        }
        try {
            final Double myDouble = Double.valueOf(this.str4);
            this.raMin = myDouble;
            this.raInitial = this.raH + this.raMin / 60.0;
        }
        catch (NumberFormatException ex4) {
            OK = false;
        }
        try {
            final Double myDouble = Double.valueOf(this.str5);
            this.raSec = myDouble;
            this.raInitial = this.raH + this.raMin / 60.0 + this.raSec / 3600.0;
            System.out.println("initial RA = " + this.raInitial + " h");
            System.out.println("initial RA = " + this.raInitial * 15.0 + " " + this.deg);
        }
        catch (NumberFormatException ex5) {
            OK = false;
        }
        try {
            final Double myDouble = Double.valueOf(this.str6);
            this.decDeg = myDouble;
            this.decInitial = this.decDeg;
        }
        catch (NumberFormatException ex6) {
            OK = false;
        }
        try {
            final Double myDouble = Double.valueOf(this.str7);
            this.decMin = myDouble;
            this.decInitial = this.decDeg + this.decMin / 60.0;
        }
        catch (NumberFormatException ex7) {
            OK = false;
        }
        try {
            final Double myDouble = Double.valueOf(this.str8);
            this.decSec = myDouble;
            this.decInitial = this.decDeg + this.decMin / 60.0 + this.decSec / 3600.0;
            if (!this.PlusMinusDec.getState()) {
                this.decInitial = -Math.abs(this.decInitial);
            }
            this.decDecimalInitial.setText(String.valueOf(Math.round(1.0E7 * this.decInitial) / 1.0E7));
            System.out.println("initial Dec = " + this.decInitial + this.deg);
        }
        catch (NumberFormatException ex8) {
            OK = false;
        }
        if (this.pmOK && this.str1.length() > 0 && this.str2.length() > 0 && (this.str12.length() > 0 || this.str13.length() > 0)) {
            OK = true;
        }
        try {
            System.out.println("Proper Motion RA  = " + this.str12 + " mas/yr");
            final Double myDouble = Double.valueOf(this.str12);
            this.pmRA1 = myDouble;
        }
        catch (NumberFormatException ex9) {
            if (this.pmOK) {
                OK = false;
            }
        }
        try {
            System.out.println("Proper Motion Dec = " + this.str13 + " mas/yr");
            final Double myDouble = Double.valueOf(this.str13);
            this.pmDec1 = myDouble;
        }
        catch (NumberFormatException ex10) {
            if (this.pmOK) {
                OK = false;
            }
        }
        if (!OK) {
            this.L112.setText("  Check input !");
        }
    }
    
    public void putOutput() {
        if (this.str1.length() > 0) {
            this.initialJDField.setText(String.valueOf(Math.round(10000.0 * this.JDInitial) / 10000.0));
        }
        if (this.str2.length() > 0) {
            this.finalJDField.setText(String.valueOf(Math.round(10000.0 * this.JDFinal) / 10000.0));
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
        this.pmRAField.setText(Math.round(1000.0 * this.pmRA1) / 1000.0 + " mas");
        this.pmDecField.setText(Math.round(1000.0 * this.pmDec1) / 1000.0 + " mas");
        if (this.pmOK) {
            this.decDecimalInitial.setText(String.valueOf(Math.round(1.0E7 * this.decInitial) / 1.0E7));
            this.raDecimInitial.setText(String.valueOf(Math.round(1.0E7 * this.raInitial) / 1.0E7));
            if (this.str12.length() > 0) {
                this.raDecimFinal.setText(String.valueOf(Math.round(1000000.0 * this.raFinal) / 1000000.0));
                this.raHfinal.setText(String.valueOf(this.raH_Final));
                this.raMinFinal.setText(String.valueOf(this.raMin_Final));
                this.raSecFinal.setText(String.valueOf(Math.round(1000.0 * this.raSec_Final) / 1000.0));
            }
            if (this.str13.length() > 0) {
                this.decDecimalFinal.setText(String.valueOf(Math.round(100000.0 * this.decFinal) / 100000.0));
                this.decDegFinal.setText(String.valueOf(Math.abs(this.decDeg_Final)));
                this.decMinFinal.setText(String.valueOf(this.decMin_Final));
                this.decSecFinal.setText(String.valueOf(Math.round(1000.0 * this.decSec_Final) / 1000.0));
            }
        }
        else {
            this.decDecimalInitial.setText(String.valueOf(Math.round(1.0E7 * this.decInitial) / 1.0E7));
            this.raDecimInitial.setText(String.valueOf(Math.round(1.0E7 * this.raInitial) / 1.0E7));
            if (this.str1.length() > 0 && this.str2.length() > 0 && this.str3.length() > 0 && this.str4.length() > 0 && this.str5.length() > 0) {
                this.raDecimInitial.setText(String.valueOf(Math.round(1.0E7 * this.raInitial) / 1.0E7));
                this.raDecimFinal.setText(String.valueOf(Math.round(1.0E7 * this.raFinal) / 1.0E7));
                this.HMS(this.raFinal);
                this.raHfinal.setText(String.valueOf(this.raH_Final));
                if (this.raFinal < 0.0) {
                    this.raHfinal.setText("-" + this.raH_Final);
                }
                this.raMinFinal.setText(String.valueOf(this.raMin_Final));
                this.raSecFinal.setText(String.valueOf(Math.round(1000.0 * this.raSec_Final) / 1000.0));
                System.out.println("final RA  = " + this.raH_Final + " h  " + this.raMin_Final + " min  " + Math.round(this.raSec_Final * 10000.0) / 10000.0 + " s");
                System.out.println("final RA  = " + this.raFinal + " h");
                System.out.println("final RA  = " + this.raFinal * 15.0 + " " + this.deg);
                this.HMS(15.0 * this.raFinal);
                if (15.0 * this.raFinal >= 0.0) {
                    System.out.println("final RA  = " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                else {
                    System.out.println("final RA  = -" + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                System.out.println("diff. RA  = " + (this.raFinal - this.raInitial) + " h");
                this.HMS(this.raFinal - this.raInitial);
                if (this.raFinal - this.raInitial >= 0.0) {
                    System.out.println("diff. RA  = " + this.H + " h  " + this.MIN + " min  " + Math.round(10000.0 * this.SEC) / 10000.0 + " s");
                }
                else {
                    System.out.println("diff. RA  = -" + this.H + " h  " + this.MIN + " min  " + Math.round(10000.0 * this.SEC) / 10000.0 + " s");
                }
                System.out.println("diff. RA  = " + (this.raFinal - this.raInitial) * 15.0 + " " + this.deg);
            }
            if (this.str1.length() > 0 && this.str2.length() > 0 && this.str6.length() > 0 && this.str7.length() > 0 && this.str8.length() > 0) {
                this.decDecimalInitial.setText(String.valueOf(Math.round(1.0E7 * this.decInitial) / 1.0E7));
                this.decDecimalFinal.setText(String.valueOf(Math.round(1.0E7 * this.decFinal) / 1.0E7));
                this.HMS(this.decFinal);
                this.decDegFinal.setText(String.valueOf(Math.abs(this.decDeg_Final)));
                this.decMinFinal.setText(String.valueOf(this.decMin_Final));
                this.decSecFinal.setText(String.valueOf(Math.round(1000.0 * this.decSec_Final) / 1000.0));
                if (this.decFinal >= 0.0) {
                    System.out.println("final Dec = " + this.decDeg_Final + this.deg + " " + this.decMin_Final + "' " + Math.round(10000.0 * this.decSec_Final) / 10000.0 + "''");
                }
                else {
                    System.out.println("final Dec = -" + this.decDeg_Final + this.deg + " " + this.decMin_Final + "' " + Math.round(10000.0 * this.decSec_Final) / 10000.0 + "''");
                }
                System.out.println("final Dec = " + this.decFinal + " " + this.deg);
                System.out.println("diff. Dec = " + (this.decFinal - this.decInitial) + this.deg);
                this.HMS(this.decFinal - this.decInitial);
                if (this.decFinal - this.decInitial >= 0.0) {
                    System.out.println("diff. Dec = " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                else {
                    System.out.println("diff. Dec = -" + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                final double diffL = this.LFinal - this.LInitial;
                this.HMS(diffL);
                System.out.println("diff. L   = " + diffL + this.deg);
                if (diffL >= 0.0) {
                    System.out.println("diff. L   = " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                else {
                    System.out.println("diff. L   = -" + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                final double diffB = this.BFinal - this.BInitial;
                System.out.println("diff. B   = " + diffB + this.deg);
                this.HMS(diffB);
                if (diffB >= 0.0) {
                    System.out.println("diff. B   = " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                else {
                    System.out.println("diff. B   = -" + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
                }
                System.out.println("");
            }
        }
        if (this.str6.length() == 0 || this.str7.length() == 0 || this.str8.length() == 0) {
            this.decDegFinal.setText("");
            this.decMinFinal.setText("");
            this.decSecFinal.setText("");
            this.decDecimalFinal.setText("");
            this.decDecimalInitial.setText("");
        }
        if (this.str3.length() == 0 || this.str4.length() == 0 || this.str5.length() == 0) {
            this.raHfinal.setText("");
            this.raMinFinal.setText("");
            this.raSecFinal.setText("");
            this.raDecimFinal.setText("");
            this.raDecimInitial.setText("");
        }
        if (!this.precessionOK && !this.pmOK) {
            this.raHfinal.setText("");
            this.raMinFinal.setText("");
            this.raSecFinal.setText("");
            this.raDecimFinal.setText("");
            this.decDegFinal.setText("");
            this.decMinFinal.setText("");
            this.decSecFinal.setText("");
            this.decDecimalFinal.setText("");
        }
        if (this.motionRA.getText().length() == 0) {
            this.pmRAField.setText("");
        }
        if (this.motionDec.getText().length() == 0) {
            this.pmDecField.setText("");
        }
    }
    
    public void HMS(double x) {
        final int F = 1;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        this.H = F * (int)x;
        this.MIN = F * (int)(this.frac(x) * 60.0);
        this.SEC = F * 60 * (this.frac(x) * 60.0 - this.MIN);
    }
    
    public String DMS(final double x) {
        String str = "";
        double X;
        if (x < 0.0) {
            X = Math.abs(x);
        }
        else {
            X = x;
        }
        final int DEG = (int)X;
        final int MIN = (int)(this.frac(X) * 60.0);
        final double SEC = 60.0 * (this.frac(X) * 60.0 - MIN);
        if (x < 0.0) {
            str = "-";
        }
        else {
            str = "+";
        }
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + this.deg + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + Math.round(10.0 * SEC) / 10.0 + "''";
        return str;
    }
    
    public void cart(final double theta, final double phi) {
        final double R = 1.0;
        final double RCST = R * Math.cos(0.017453292519943295 * theta);
        this.xx = RCST * Math.cos(0.017453292519943295 * phi);
        this.yy = RCST * Math.sin(0.017453292519943295 * phi);
        this.zz = R * Math.sin(0.017453292519943295 * theta);
    }
    
    public void calculate() {
        final double K = 0.017453292519943295;
        this.comp = new Compute();
        this.JDInitial = this.comp.JD(this.epoqueInitial);
        this.JDFinal = this.comp.JD(this.epoqueFinal);
        System.out.println("initial JD  = " + Math.round(this.JDInitial * 10000.0) / 10000.0);
        System.out.println("final JD    = " + Math.round(this.JDFinal * 10000.0) / 10000.0);
        System.out.println("initial RA  =  " + (int)this.raH + " h  " + (int)this.raMin + " min  " + this.raSec + " s");
        this.HMS(this.raInitial * 15.0);
        System.out.println("initial RA  =  " + this.H + this.deg + " " + this.MIN + "' " + this.SEC + "''");
        System.out.println("initial Dec = " + (int)this.decDeg + this.deg + " " + (int)this.decMin + "' " + this.decSec + "''");
        final double T = (this.JDInitial - 2451545.0) / 36525.0;
        final double t = (this.JDFinal - this.JDInitial) / 36525.0;
        double zeta = (2306.2181 + 1.39656 * T - 1.39E-4 * T * T) * t + (0.30188 - 3.44E-4 * T) * t * t + 0.017998 * t * t * t;
        double z = (2306.2181 + 1.39656 * T - 1.39E-4 * T * T) * t + (1.09468 + 6.6E-5 * T) * t * t + 0.018203 * t * t * t;
        double theta = (2004.3109 - 0.8533 * T - 2.17E-4 * T * T) * t - (0.42665 + 2.17E-4 * T) * t * t - 0.041833 * t * t * t;
        zeta /= 3600.0;
        z /= 3600.0;
        theta /= 3600.0;
        final double delta = this.epoqueFinal - this.epoqueInitial;
        this.pmRA1 *= delta;
        this.pmRA = this.pmRA1 / 1000.0;
        this.pmRA /= 15.0;
        this.pmDec1 *= delta;
        this.pmDec = this.pmDec1 / 1000.0;
        System.out.println("Proper Motion RA  = " + this.pmRA * 15.0 + " as");
        System.out.println("Proper Motion RA  = " + this.pmRA + " s");
        System.out.println("Proper Motion Dec = " + this.pmDec + " " + "''");
        this.HMS(this.pmRA / 3600.0);
        this.raH_Final = this.H;
        this.raMin_Final = this.MIN;
        this.raSec_Final = this.SEC;
        this.HMS(this.pmDec / 3600.0);
        this.decDeg_Final = this.H;
        this.decMin_Final = this.MIN;
        this.decSec_Final = this.SEC;
        if (this.pmDec < 0.0) {
            this.H = -Math.abs(this.H);
            this.MIN = -Math.abs(this.MIN);
            this.SEC = -Math.abs(this.SEC);
        }
        if (this.pmOK && !this.precessionOK && this.str1.length() > 0 && this.str2.length() > 0 && this.str3.length() > 0 && this.str4.length() > 0 && this.str5.length() > 0) {
            this.raFinal = this.raInitial + this.pmRA / 3600.0;
            if (this.raFinal < 0.0) {
                this.raFinal += 24.0;
            }
            if (this.raFinal > 24.0) {
                this.raFinal -= 24.0;
            }
            this.HMS(this.raFinal);
            this.raH_Final = this.H;
            this.raMin_Final = this.MIN;
            this.raSec_Final = this.SEC;
        }
        if (this.pmOK && !this.precessionOK && this.str1.length() > 0 && this.str2.length() > 0 && this.str6.length() > 0 && this.str7.length() > 0 && this.str8.length() > 0) {
            this.HMS(this.decFinal = this.decInitial + this.pmDec / 3600.0);
            this.decDeg_Final = this.H;
            this.decMin_Final = this.MIN;
            this.decSec_Final = this.SEC;
        }
        if (this.precessionOK) {
            this.raInitial = this.raH + this.raMin / 60.0 + this.raSec / 3600.0;
            if (this.pmOK) {
                this.raInitial += this.pmRA / 3600.0;
            }
            this.decInitial = this.decDeg + this.decMin / 60.0 + this.decSec / 3600.0;
            if (this.pmOK) {
                this.decInitial += this.pmDec / 3600.0;
            }
            if (!this.PlusMinusDec.getState()) {
                this.decInitial = -Math.abs(this.decInitial);
            }
            final double tt = (this.JDInitial - 2451545.0) / 36525.0;
            final double epsInitial = 23.43929111111111 - 46.815 * tt / 3600.0 - 5.9E-4 * tt * tt / 3600.0 + 0.001813 * tt * tt * tt / 3600.0;
            System.out.println("initial eps = " + Math.round(100000.0 * epsInitial) / 100000.0 + this.deg);
            System.out.println("initial eps = " + this.DMS(epsInitial));
            double TT = (this.JDFinal - 2451545.0) / 36525.0;
            double eps = 23.43929111111111 - 46.815 * TT / 3600.0 - 5.9E-4 * TT * TT / 3600.0 + 0.001813 * TT * TT * TT / 3600.0;
            System.out.println("final eps   = " + Math.round(100000.0 * eps) / 100000.0 + this.deg);
            System.out.println("final eps   = " + this.DMS(eps));
            double Z = Math.sin(0.017453292519943295 * this.raInitial * 15.0) * Math.cos(0.017453292519943295 * eps) + Math.tan(0.017453292519943295 * this.decInitial) * Math.sin(0.017453292519943295 * eps);
            double N = Math.cos(0.017453292519943295 * this.raInitial * 15.0);
            double L = Math.atan2(Z, N) / 0.017453292519943295;
            if (L < 0.0) {
                L += 360.0;
            }
            double sinB = Math.sin(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * eps) - Math.cos(0.017453292519943295 * this.decInitial) * Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * this.raInitial * 15.0);
            double BB = Math.asin(sinB) / 0.017453292519943295;
            this.LInitial = L;
            this.BInitial = BB;
            System.out.println("initial L = " + L + this.deg + "    initial B = " + BB + this.deg);
            this.HMS(L);
            System.out.println("initial L = " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
            this.HMS(BB);
            if (BB >= 0.0) {
                System.out.println("initial B = " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
            }
            else {
                System.out.println("initial B = -" + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
            }
            final double A = Math.cos(0.017453292519943295 * this.decInitial) * Math.sin(0.017453292519943295 * (15.0 * this.raInitial + zeta));
            final double B = Math.cos(0.017453292519943295 * theta) * Math.cos(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * (15.0 * this.raInitial + zeta)) - Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * this.decInitial);
            final double C = Math.sin(0.017453292519943295 * theta) * Math.cos(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * (15.0 * this.raInitial + zeta)) + Math.cos(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * this.decInitial);
            final double tan = A / B;
            double alpha = Math.atan2(A, B) / 0.017453292519943295 + z;
            alpha /= 15.0;
            this.raFinal = alpha;
            if (this.raFinal < 0.0) {
                this.raFinal += 24.0;
            }
            if (this.raFinal > 24.0) {
                this.raFinal -= 24.0;
            }
            if (this.decInitial < 70.0) {
                this.decFinal = Math.asin(C) / 0.017453292519943295;
            }
            else {
                final double cosD = Math.sqrt(A * A + B * B);
                this.decFinal = Math.acos(cosD) / 0.017453292519943295;
            }
            this.HMS(this.raFinal);
            this.raH_Final = this.H;
            this.raMin_Final = this.MIN;
            this.raSec_Final = this.SEC;
            this.HMS(this.decFinal);
            this.decDeg_Final = this.H;
            this.decMin_Final = this.MIN;
            this.decSec_Final = this.SEC;
            TT = (this.JDFinal - 2451545.0) / 36525.0;
            eps = 23.43929111111111 - 46.815 * TT / 3600.0 - 5.9E-4 * TT * TT / 3600.0 + 0.001813 * TT * TT * TT / 3600.0;
            Z = Math.sin(0.017453292519943295 * this.raFinal * 15.0) * Math.cos(0.017453292519943295 * eps) + Math.tan(0.017453292519943295 * this.decFinal) * Math.sin(0.017453292519943295 * eps);
            N = Math.cos(0.017453292519943295 * this.raFinal * 15.0);
            L = Math.atan2(Z, N) / 0.017453292519943295;
            if (L < 0.0) {
                L += 360.0;
            }
            sinB = Math.sin(0.017453292519943295 * this.decFinal) * Math.cos(0.017453292519943295 * eps) - Math.cos(0.017453292519943295 * this.decFinal) * Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * this.raFinal * 15.0);
            BB = Math.asin(sinB) / 0.017453292519943295;
            this.LFinal = L;
            this.BFinal = BB;
            System.out.println("final   L = " + L + this.deg + "   final  B = " + BB + this.deg);
            this.HMS(L);
            System.out.println("final   L =  " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
            this.HMS(BB);
            if (BB >= 0.0) {
                System.out.println("final   B =  " + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
            }
            else {
                System.out.println("final   B =  -" + this.H + this.deg + " " + this.MIN + "' " + Math.round(10000.0 * this.SEC) / 10000.0 + "''");
            }
        }
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target instanceof Button) {
            if (evt.target == this.run) {
                this.getInput();
                if (!this.PlusMinusDec.getState()) {
                    this.decInitial = -Math.abs(this.decInitial);
                }
                this.calculate();
                this.putOutput();
                return true;
            }
            if (evt.target == this.clearButton) {
                this.initialEpoqueField.setText("");
                this.finalEpoqueField.setText("");
                this.initialJDField.setText("");
                this.finalJDField.setText("");
                this.raHinitial.setText("");
                this.raHfinal.setText("");
                this.raMinInitial.setText("");
                this.raMinFinal.setText("");
                this.raSecInitial.setText("");
                this.raSecFinal.setText("");
                this.raDecimInitial.setText("");
                this.raDecimFinal.setText("");
                this.decDegInitial.setText("");
                this.decDegFinal.setText("");
                this.decMinInitial.setText("");
                this.decMinFinal.setText("");
                this.decSecInitial.setText("");
                this.decSecFinal.setText("");
                this.decDecimalInitial.setText("");
                this.decDecimalFinal.setText("");
                this.motionRA.setText("");
                this.motionDec.setText("");
                this.pmRAField.setText("");
                this.pmDecField.setText("");
                this.L112.setText("");
                this.decInitial = 0.0;
                this.raInitial = 0.0;
                this.decInitial = 0.0;
                return true;
            }
        }
        if (evt.target instanceof Checkbox) {
            if (evt.target == this.pmBox) {
                this.pmOK ^= true;
                return true;
            }
            if (evt.target == this.PlusMinusDec) {
                this.decPos ^= true;
                this.getInput();
                if (!this.PlusMinusDec.getState()) {
                    this.decInitial = -Math.abs(this.decInitial);
                }
                this.decDecimalInitial.setText(String.valueOf(Math.round(1.0E7 * this.decInitial) / 1.0E7));
                if (this.str6.length() == 0 || this.str7.length() == 0 || this.str8.length() == 0) {
                    this.decDecimalInitial.setText("");
                }
                this.repaint();
                return true;
            }
            if (evt.target == this.precessionBox) {
                this.precessionOK ^= true;
            }
        }
        final Object target = evt.target;
        return super.handleEvent(evt);
    }
    
    public void paint(final Graphics g) {
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("DEMO", 20, 150);
            g.setFont(f);
        }
    }
    
    public boolean keyDown(final Event event, final int code) {
        final int n = 0;
        final int cmd = 0;
        if (code == 10 || code == 3 || code == 9) {
            if (event.target == this.initialEpoqueField) {
                this.initialEpoqueField.nextFocus();
            }
            if (event.target == this.finalEpoqueField) {
                this.finalEpoqueField.nextFocus();
            }
            if (event.target == this.raHinitial) {
                this.raHinitial.nextFocus();
            }
            if (event.target == this.raMinInitial) {
                this.raMinInitial.nextFocus();
            }
            if (event.target == this.raSecInitial) {
                this.raSecInitial.nextFocus();
            }
            if (event.target == this.decDegInitial) {
                this.decDegInitial.nextFocus();
            }
            if (event.target == this.decMinInitial) {
                this.decMinInitial.nextFocus();
            }
            if (event.target == this.decSecInitial) {
                this.decSecInitial.nextFocus();
            }
            if (event.target == this.decSecInitial) {
                this.decSecInitial.nextFocus();
            }
            if (event.target == this.motionRA) {
                this.motionRA.nextFocus();
            }
        }
        return false;
    }
    
    public PrecessionMotion071() {
        this.deg = '°';
        this.vers1 = "  Precession 0.71";
        this.vers2 = "  © 2004-2007  J. Giesen";
        this.vers3 = "  www.GeoAstro.de";
        this.L11 = new Label("");
        this.L12 = new Label("   Initial");
        this.L13 = new Label("   Final");
        this.L21 = new Label("   J. Epoque");
        this.L31 = new Label("   JD");
        this.L41 = new Label("   RA (h)");
        this.L51 = new Label("   RA (min)");
        this.L61 = new Label("   RA (s)");
        this.L71 = new Label("   RA (h decimal)");
        this.L81 = new Label("   Dec (" + this.deg + ")");
        this.L91 = new Label("   Dec (')");
        this.L101 = new Label("   Dec ('')");
        this.L111 = new Label("   Dec (" + this.deg + " decimal)");
        this.L112 = new Label("");
        this.L121 = new Label("   PM RA (mas/yr)");
        this.L131 = new Label("   PM Dec (mas/yr)");
        this.L123 = new Label("");
        this.L133 = new Label("");
        this.L1 = new Label(this.vers1);
        this.L2 = new Label(this.vers2);
        this.L3 = new Label(this.vers3);
        this.L_1 = new Label("");
        this.L_2 = new Label("");
        this.L_3 = new Label("");
        this.L_4 = new Label("");
        this.L_5 = new Label("");
        this.L_6 = new Label("");
        this.L_7 = new Label("");
        this.L_8 = new Label("");
        this.L_9 = new Label("");
        this.L_10 = new Label("");
        this.L_11 = new Label("");
        this.dec1 = new Label("   Dec>=0");
        this.dec2 = new Label("");
        this.run = new Button("Calculate");
        this.pmBox = new Checkbox();
        this.PlusMinusDec = new Checkbox();
        this.precessionBox = new Checkbox();
        this.clearButton = new Button("Clear Input");
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
        this.str12 = "";
        this.str13 = "";
        this.initialEpoqueField = new TextField("", 10);
        this.finalEpoqueField = new TextField("", 10);
        this.initialJDField = new Label("");
        this.finalJDField = new Label("");
        this.raHinitial = new TextField("", 10);
        this.raHfinal = new Label("");
        this.raMinInitial = new TextField("", 10);
        this.raMinFinal = new Label("");
        this.raSecInitial = new TextField("", 10);
        this.raSecFinal = new Label("");
        this.raDecimInitial = new Label("");
        this.raDecimFinal = new Label("");
        this.decDegInitial = new TextField("", 10);
        this.decDegFinal = new Label("");
        this.decMinInitial = new TextField("", 10);
        this.decMinFinal = new Label("");
        this.decSecInitial = new TextField("", 10);
        this.decSecFinal = new Label("");
        this.decDecimalInitial = new Label("");
        this.decDecimalFinal = new Label("");
        this.motionRA = new TextField("", 10);
        this.motionDec = new TextField("", 10);
        this.pmRAField = new Label("");
        this.pmDecField = new Label("");
        this.demo = true;
        this.pmOK = false;
        this.raH = 0.0;
        this.raMin = 0.0;
        this.decDeg = 0.0;
        this.decMin = 0.0;
        this.decSec = 0.0;
        this.decPos = true;
        this.precessionOK = true;
    }
}

import java.net.MalformedURLException;
import java.io.DataOutputStream;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.awt.Graphics;
import java.awt.Event;
import java.util.Date;
import java.net.URLEncoder;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Container;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.awt.Component;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Label;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.List;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class z1 extends Applet
{
    static z7 z0;
    private Button z1;
    private boolean z2;
    public static boolean alt;
    boolean z3;
    public String aol_cache;
    private boolean z4;
    private Button z5;
    private z8 z6;
    private boolean z7;
    private Button z8;
    private Color z9;
    private int z10;
    private Color z11;
    private TextField z12;
    private String z13;
    static int z14;
    static Choice z15;
    static List z16;
    static int z17;
    private TextField z18;
    static Choice z19;
    static String z20;
    private Button z21;
    private Button z22;
    static String z23;
    static Choice z24;
    static boolean z25;
    private double z26;
    private Font z27;
    private boolean z28;
    static String z29;
    static int z30;
    private TextArea z31;
    private TextField z32;
    private TextField z33;
    private TextField z34;
    private boolean z35;
    private String z36;
    private boolean z37;
    public boolean enlarge;
    public int enlarge_1;
    private String z38;
    private boolean z39;
    private TextArea z40;
    private Panel z41;
    private TextField z42;
    private TextField z43;
    private int z44;
    static String[] z45;
    static int z46;
    static int z47;
    static boolean z48;
    private int z49;
    private String z50;
    private boolean z51;
    public String global_item_total;
    public double global_tax;
    private double z52;
    static String z53;
    static Font z54;
    static Font z55;
    static boolean z56;
    static Color z57;
    static Image z58;
    private String z59;
    static int z60;
    static List z61;
    public static String image;
    static Image z62;
    static Color z63;
    static Color z64;
    static Color z65;
    static Color z66;
    private boolean z67;
    static String z68;
    private String z69;
    public boolean javascript;
    public int javascript_1;
    static int z70;
    static int z71;
    private CardLayout z72;
    static int z73;
    static int z74;
    static z4 z75;
    static List z76;
    static List z77;
    private String z78;
    static List z79;
    private String z80;
    private boolean z81;
    static int z82;
    private boolean z83;
    private Button z84;
    private Button z85;
    private TextField z86;
    private String z87;
    private TextArea z88;
    private TextField z89;
    private TextField z90;
    private Label z91;
    private Label z92;
    private TextField z93;
    private TextField z94;
    z5 z95;
    private Button z96;
    private Button z97;
    private Button z98;
    private String z99;
    private Panel z100;
    private List z101;
    Panel z102;
    private TextField z103;
    static Color z104;
    static Color z105;
    private boolean z106;
    private String z107;
    private String z108;
    private String z109;
    private String z110;
    private String z111;
    static z2 z112;
    static int z113;
    static int z114;
    static int z115;
    static List z116;
    private boolean z117;
    private Choice z118;
    static int z119;
    static List z120;
    private String z121;
    static List z122;
    private boolean z123;
    private Button z124;
    private Button z125;
    private String z126;
    static int z127;
    static int z128;
    private String z129;
    static Label z130;
    private boolean z131;
    private Button z132;
    private Button z133;
    private boolean z134;
    private String z135;
    private Panel z136;
    public boolean send_order;
    public int sent_order_1;
    static List z137;
    private String z138;
    private CheckboxGroup z139;
    private Choice z140;
    public String shop_pl_url;
    static boolean z141;
    private boolean z142;
    static Choice z143;
    private Choice z144;
    private int z145;
    private int z146;
    private boolean z147;
    private Button z148;
    private String z149;
    private String z150;
    private z6 z151;
    static boolean z152;
    static Color z153;
    private String z154;
    private Checkbox[] z155;
    private Choice z156;
    private int z157;
    private String[] z158;
    private String z159;
    private double z160;
    private CheckboxGroup z161;
    private boolean z162;
    static Scrollbar z163;
    private boolean z164;
    static int z165;
    static int z166;
    static int z167;
    static z3 z168;
    public static TextField tf;
    static int z169;
    private String z170;
    static String[] z171;
    private Button z172;
    private Button z173;
    static double z174;
    static double z175;
    private Button z176;
    static String z177;
    private URLConnection z178;
    private TextArea z179;
    private boolean z180;
    static Scrollbar z181;
    private String z182;
    static int z183;
    static List z184;
    static List z185;
    private String z186;
    private boolean z187;
    private Button z188;
    private boolean z189;
    private String z190;
    static int z191;
    static int z192;
    static int z193;
    
    static {
        z1.z152 = false;
        z1.z17 = 0;
        z1.alt = true;
        z1.z47 = 26;
        z1.z48 = true;
        z1.z76 = new List();
        z1.z77 = new List();
        z1.z25 = false;
        z1.z167 = 200;
        z1.z165 = 999;
        z1.z57 = Color.red;
        z1.z153 = Color.blue;
        z1.z23 = "";
        z1.z115 = 15;
        z1.z70 = 400;
        z1.z71 = 400;
        z1.z166 = 100;
        z1.z116 = new List();
        z1.z75 = new z4();
        z1.z127 = 16;
        z1.z30 = 1;
        z1.z181 = new Scrollbar(1);
        z1.z163 = new Scrollbar(1);
        z1.z56 = false;
        z1.z141 = false;
        z1.z169 = 200;
        z1.z191 = 380;
        z1.z192 = 165;
        z1.z193 = 173;
        z1.image = "";
        z1.z14 = 0;
        z1.z24 = new Choice();
        z1.z143 = new Choice();
        z1.z19 = new Choice();
        z1.z168 = new z3();
        z1.z65 = Color.black;
        z1.z104 = new Color(255, 255, 227);
        z1.z66 = Color.black;
        z1.z105 = Color.white;
        z1.z64 = Color.black;
        z1.z63 = Color.white;
        z1.z54 = new Font("Helvetica", 1, 12);
        z1.z55 = new Font("Helvetica", 0, 12);
        z1.z46 = 8;
        z1.z119 = 2;
        z1.z113 = 4;
        z1.z183 = 3;
        z1.z128 = 5;
        z1.z60 = 1;
        z1.z68 = "";
        z1.z82 = 8;
        z1.z20 = "";
        z1.z174 = 0.0;
        z1.z175 = 0.0;
        z1.z185 = new List();
        z1.z137 = new List();
        z1.z122 = new List();
        z1.z61 = new List();
        z1.z79 = new List();
        z1.z120 = new List();
        z1.z184 = new List();
        z1.z15 = new Choice();
        z1.z16 = new List();
        z1.tf = new TextField("", 5);
        z1.z53 = "";
        z1.z29 = "";
        z1.z58 = null;
        z1.z74 = 68;
        z1.z73 = 32;
        z1.z177 = "";
    }
    
    public void buy_item() {
        final String s = "                                                     ";
        if (z1.z24.getSelectedIndex() == 0 && !z0(14).trim().equals("") && !z1.z45[6].trim().equals("")) {
            z1.z24.setForeground(Color.white);
            z1.z24.setBackground(Color.red);
            return;
        }
        if (z1.z143.getSelectedIndex() == 0 && !z0(15).trim().equals("") && !z1.z45[7].trim().equals("")) {
            z1.z143.setForeground(Color.white);
            z1.z143.setBackground(Color.red);
            return;
        }
        if (z1.z19.getSelectedIndex() == 0 && !z0(16).trim().equals("") && !z1.z45[8].trim().equals("")) {
            z1.z19.setForeground(Color.white);
            z1.z19.setBackground(Color.red);
            return;
        }
        z1.z143.setForeground(z1.z66);
        z1.z143.setBackground(z1.z105);
        z1.z24.setForeground(z1.z66);
        z1.z24.setBackground(z1.z105);
        z1.z19.setForeground(z1.z66);
        z1.z19.setBackground(z1.z105);
        String s2;
        if (!this.z106) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(z1.z143.getSelectedItem()).concat(String.valueOf(" "))).concat(String.valueOf(z1.z24.getSelectedItem().trim()))).concat(String.valueOf(" "))).concat(String.valueOf(z1.z19.getSelectedItem())).trim();
        }
        else {
            s2 = z1.z19.getSelectedItem().trim();
        }
        String text = this.z86.getText();
        final String concat = String.valueOf(String.valueOf(z1.z45[z1.z60]).concat(String.valueOf(" "))).concat(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(" "))).concat(String.valueOf(z1.z45[0])).trim()));
        String s3 = z1.z45[z1.z119];
        if (s3.trim().equals("")) {
            s3 = "0";
        }
        double doubleValue = 0.0;
        if (z1.z24.getSelectedIndex() != 0) {
            final String trim = this.z12(z1.z24.getSelectedItem()).trim();
            if (!trim.equals("")) {
                try {
                    doubleValue = new Double(trim);
                }
                catch (Exception ex) {
                    doubleValue = 0.0;
                }
            }
        }
        double doubleValue2 = 0.0;
        if (z1.z143.getSelectedIndex() != 0) {
            final String trim2 = this.z12(z1.z143.getSelectedItem()).trim();
            if (!trim2.equals("")) {
                try {
                    doubleValue2 = new Double(trim2);
                }
                catch (Exception ex2) {
                    doubleValue2 = 0.0;
                }
            }
        }
        double doubleValue3 = 0.0;
        if (z1.z19.getSelectedIndex() != 0) {
            final String trim3 = this.z12(z1.z19.getSelectedItem()).trim();
            if (!trim3.equals("")) {
                try {
                    doubleValue3 = new Double(trim3);
                }
                catch (Exception ex3) {
                    doubleValue3 = 0.0;
                }
            }
        }
        String z0 = this.z0(doubleValue + doubleValue2 + doubleValue3 + new Double(s3.replace('$', ' ').replace('£', ' ').trim()));
        if (this.z106) {
            if (this.z111.equals("")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), "no_user_name.html"), "_blank");
                    return;
                }
                catch (Exception ex4) {
                    return;
                }
            }
            this.z0(concat, z0);
            return;
        }
        String s4 = "";
        double doubleValue4 = 0.0;
        String s5 = "";
        for (int i = 0; i < this.z157; ++i) {
            if (this.z155[i].getState()) {
                s5 = this.z158[i];
            }
        }
        try {
            if (z1.z183 != -1) {
                s4 = z1.z45[z1.z183];
                doubleValue4 = new Double(s4.trim());
            }
        }
        catch (NumberFormatException ex5) {
            doubleValue4 = 0.0;
        }
        double doubleValue5;
        try {
            doubleValue5 = new Double(s5.trim());
        }
        catch (NumberFormatException ex6) {
            doubleValue5 = 0.0;
        }
        double doubleValue6;
        try {
            doubleValue6 = new Double(text.trim());
        }
        catch (NumberFormatException ex7) {
            doubleValue6 = 1.0;
            text = "1";
        }
        double doubleValue7;
        try {
            doubleValue7 = new Double(z0.trim());
        }
        catch (NumberFormatException ex8) {
            doubleValue7 = 0.0;
            z0 = "error";
            this.getAppletContext().showStatus("Not a valid price");
        }
        z1.z122.addItem(text.trim());
        z1.z79.addItem(String.valueOf(String.valueOf(s2).concat(String.valueOf(" "))).concat(String.valueOf(z1.z45[0])));
        z1.z120.addItem(z0.trim());
        z1.z61.addItem(z1.z45[z1.z60]);
        z1.z184.addItem(String.valueOf("").concat(String.valueOf(doubleValue4)));
        final double n = doubleValue6 * doubleValue7;
        z1.z174 += doubleValue6 * doubleValue7;
        z1.z175 += doubleValue6 * doubleValue4;
        final double n2 = doubleValue5 * 10;
        final double n3 = z1.z174 * n2 / 1000;
        final double n4 = z1.z174 * (1000 + n2) / 1000;
        this.global_item_total = this.z0(n);
        final String z2 = this.z0(n);
        String s6;
        if (text.length() < 5) {
            s6 = String.valueOf(text).concat(String.valueOf(s.substring(0, 4 - text.length())));
        }
        else {
            s6 = String.valueOf(text).concat(String.valueOf(" "));
        }
        String s7;
        if (concat.length() > 32 + this.z44) {
            s7 = String.valueOf(concat).concat(String.valueOf(" "));
        }
        else {
            s7 = String.valueOf(concat).concat(String.valueOf(s.substring(0, 33 + this.z44 - concat.length())));
        }
        final String concat2 = String.valueOf(s.substring(0, 11 - z0.length())).concat(String.valueOf(z0));
        String.valueOf(s.substring(0, 11 - s4.length())).concat(String.valueOf(s4));
        z1.z20 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(z1.z20).concat(String.valueOf(this.z38))).concat(String.valueOf(s6))).concat(String.valueOf(s7))).concat(String.valueOf(concat2))).concat(String.valueOf(String.valueOf(s.substring(0, 12 - z2.length())).concat(String.valueOf(z2))));
        this.calc();
    }
    
    public void calc() {
        boolean b = false;
        double doubleValue = 0.0;
        this.z26 = 0.0;
        double doubleValue2 = 0.0;
        double doubleValue3 = 0.0;
        for (int i = 0; i < this.z118.countItems(); ++i) {
            final String item = this.z118.getItem(i);
            if (this.z140.getSelectedIndex() == 0) {
                break;
            }
            try {
                if (!z1(item, 4).trim().equals("")) {
                    b = true;
                    doubleValue = new Double(z1(item, 4).replace('$', ' ').replace('£', ' ').trim());
                }
            }
            catch (Exception ex) {
                System.out.println(String.valueOf("Error: ").concat(String.valueOf(z1(item, 4))));
            }
            final String z1 = z1(item, 1);
            String s = z1(item, 2).toUpperCase().replace('£', '$');
            boolean b2 = false;
            if (s.indexOf(43) > -1) {
                b2 = true;
                s = s.replace('+', ' ');
            }
            String s2;
            String s3;
            if (s.indexOf(36) == -1) {
                s2 = "W";
                s3 = s.replace('W', ' ').replace('w', ' ');
            }
            else {
                s2 = "$";
                s3 = s.replace('$', ' ').replace('£', ' ');
            }
            final String trim = s3.trim();
            try {
                doubleValue2 = new Double(trim);
            }
            catch (Exception ex2) {
                doubleValue2 = 0.0;
                this.z91.setText("Error in Shipping Rates");
            }
            final String z2 = z1(item, 3);
            z2.replace('£', '$');
            String s4;
            String s5;
            if (z2.indexOf(37) != -1) {
                s4 = "%";
                s5 = z2.replace('%', ' ');
            }
            else {
                s4 = "$";
                s5 = z2.replace('$', ' ').replace('£', ' ');
            }
            final String trim2 = s5.trim();
            try {
                doubleValue3 = new Double(trim2);
            }
            catch (Exception ex3) {
                doubleValue3 = 0.0;
                this.z91.setText("Error in Shipping Rates");
            }
            if (this.z140.getSelectedItem().equals(z1)) {
                double n;
                if (s2.equals("$")) {
                    n = z1.z174;
                }
                else {
                    n = z1.z175;
                }
                if (n < doubleValue2 || (n >= doubleValue2 && b2)) {
                    double z3;
                    if (s4.equals("%")) {
                        z3 = n * doubleValue3 / 100;
                    }
                    else {
                        z3 = doubleValue3;
                    }
                    i = this.z118.countItems();
                    this.z26 = z3;
                }
            }
        }
        if (b && z1.z175 >= doubleValue2) {
            this.z26 = doubleValue + doubleValue3 * (z1.z175 - doubleValue2);
        }
        final double z4 = 0.0;
        String s6 = "0";
        try {
            if (this.z156.getSelectedIndex() > 0) {
                s6 = this.z158[this.z156.getSelectedIndex() - 1];
            }
        }
        catch (Exception ex4) {
            s6 = "0";
        }
        this.getAppletContext().showStatus("");
        double doubleValue4;
        try {
            doubleValue4 = new Double(s6);
        }
        catch (NumberFormatException ex5) {
            doubleValue4 = 0.0;
        }
        if (!this.z117) {
            this.z26 = z4;
        }
        if (z1.z174 == 0) {
            doubleValue4 = 0.0;
        }
        final double n2 = doubleValue4 * 10;
        double z5;
        if (this.z180) {
            z5 = (z1.z174 + this.z26) * n2 / 1000;
        }
        else {
            z5 = z1.z174 * n2 / 1000;
        }
        this.z160 = z5;
        double z6;
        if (this.z180) {
            z6 = (z1.z174 + this.z26) * (1000 + n2) / 1000;
        }
        else {
            z6 = z1.z174 * (1000 + n2) / 1000 + this.z26;
        }
        final String s7 = "                                              ";
        final String substring = s7.substring(0, 10 - this.z0(this.z26).length());
        final String substring2 = s7.substring(0, 10 - this.z0(z5).length());
        final String substring3 = s7.substring(0, 10 - this.z0(z6).length());
        this.global_item_total = String.valueOf("").concat(String.valueOf(this.z0(z6)));
        String s8 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(z1(this.z87, 5)).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf("      "))).concat(String.valueOf(z1(this.z87, 6)))).concat(String.valueOf(z1.z20))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38));
        if (this.z26 == z6) {
            this.z26 = 0.0;
            z6 = 0.0;
        }
        String s9;
        if (z1(this.z182, 11).trim().equals("")) {
            s9 = z1(this.z182, 12);
        }
        else {
            s9 = z1(this.z182, 11);
        }
        String s10;
        if (this.z180) {
            s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                   ".substring(0, 50 - z1(this.z182, 12).length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(s9))).concat(String.valueOf(substring))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf(this.z38));
            if (this.z162) {
                s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s10).concat(String.valueOf("                                                       ".substring(0, 50 - this.z159.length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(this.z159))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38));
            }
            if (!this.z162 && this.z161.getCurrent() != null) {
                s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s10).concat(String.valueOf("                                                       ".substring(0, 50 - this.z159.length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(this.z159))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38));
            }
            if (!this.z162) {
                s10 = String.valueOf(s10).concat(String.valueOf(this.z38));
            }
        }
        else {
            if (this.z162) {
                s8 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                       ".substring(0, 50 - this.z159.length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(this.z159))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z38));
            }
            if (!this.z162 && this.z161.getCurrent() != null) {
                s8 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                       ".substring(0, 50 - this.z159.length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(this.z159))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38));
            }
            if (!this.z162) {
                s8 = String.valueOf(s8).concat(String.valueOf(this.z38));
            }
            s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                   ".substring(0, 50 - s9.length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(s9))).concat(String.valueOf(substring))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38));
        }
        String s11 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s10).concat(String.valueOf("                                                   ".substring(0, 50 - z1(this.z182, 17).length())))).concat(String.valueOf(s7.substring(0, this.z44)))).concat(String.valueOf(z1(this.z182, 17)))).concat(String.valueOf(substring3))).concat(String.valueOf(this.z0(z6)))).concat(String.valueOf("\r\n\r\n"));
        if (this.z117) {
            s11 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s11).concat(String.valueOf(z1(this.z182, 12)))).concat(String.valueOf(" "))).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(this.z38));
        }
        if (z1.z175 > 0) {
            s11 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s11).concat(String.valueOf(z1.z171[3]))).concat(String.valueOf(": "))).concat(String.valueOf(this.z0(z1.z175)))).concat(String.valueOf("  "));
        }
        this.z52 = z6;
        if (this.z162) {
            s11 = String.valueOf(String.valueOf(s11).concat(String.valueOf(this.z156.getSelectedItem()))).concat(String.valueOf(" "));
        }
        final String concat = String.valueOf(String.valueOf(s11).concat(String.valueOf(" "))).concat(String.valueOf(z1(z1.z29, 1)));
        this.z99 = concat;
        if (this.z101.getParent() == this.z100) {
            this.z100.remove(this.z101);
        }
        (this.z101 = new List()).setMultipleSelections(true);
        try {
            String line;
            while ((line = new DataInputStream(new StringBufferInputStream(concat)).readLine()) != null) {
                if (line == null) {
                    line = "";
                }
                this.z101.addItem(line);
            }
        }
        catch (Exception ex6) {}
        this.z100.add(this.z101);
        this.z101.setFont(this.z27);
        this.z101.setForeground(z1.z65);
        this.z101.setBackground(z1.z104);
        this.z101.reshape(5, 8 + this.z10 * 2, this.size().width - 10, this.size().height - (8 + this.z10 * 2) - 65);
        this.z72.show(this, "order_form");
    }
    
    public void checkout() {
        final String concat = String.valueOf(z1(this.z182, 24)).concat(String.valueOf(": "));
        this.z91.setText("                                                       ");
        if (this.z162 && this.z156.getSelectedIndex() < 1) {
            this.z91.setText(this.z156.getItem(0));
            this.z156.setBackground(Color.red);
            this.z156.setForeground(Color.white);
            return;
        }
        this.z156.setBackground(z1.z105);
        this.z156.setForeground(z1.z66);
        if (this.z117 && this.z140.getSelectedIndex() < 1) {
            this.z140.setBackground(Color.red);
            this.z140.setForeground(Color.white);
            this.z91.setText(this.z140.getItem(0));
            return;
        }
        if (z1.z174 == 0) {
            this.z91.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z36, 2))));
            return;
        }
        if (z1.z174 == 0) {
            this.z91.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z36, 2))));
            return;
        }
        String s = this.z99;
        this.getAppletContext().showStatus(z1(this.z87, 3));
        this.z37 = false;
        boolean b = false;
        if (this.z50.equals("post_html") || this.z50.equals("post_barclay")) {
            this.z186 = this.z129;
            if (this.z50.equals("post_html") && !this.getParameter("cheque").equals("")) {
                this.z186 = this.getParameter("cheque");
            }
            s = this.z8(s);
            this.z78 = "secure";
        }
        if (this.z50.equals("get_worldpay")) {
            this.z186 = this.getParameter("staging");
            if (this.z2(s)) {
                return;
            }
        }
        else if (this.z50.equals("get_new_worldpay")) {
            if (this.z3(s)) {
                return;
            }
        }
        else if (this.z50.equals("get_nochex")) {
            if (this.z4(s)) {
                return;
            }
        }
        else if (this.z50.equals("get_secpay")) {
            if (this.z5(s)) {
                return;
            }
        }
        else if (this.z50.equals("get_secure")) {
            if (this.z6(s)) {
                return;
            }
        }
        else if (this.z50.equals("get_authorize")) {
            if (this.z1(s)) {
                return;
            }
        }
        else {
            if (!this.z50.equals("get_html") && !this.z50.equals("get_barclay")) {
                if (this.z50.equals("post_worldpay")) {
                    this.z186 = this.getParameter("staging");
                    s = this.z8(s);
                    this.z50 = "post_worldpay";
                    this.z78 = "secure";
                }
                if (this.z50.equals("post_new_worldpay")) {
                    this.z186 = this.getParameter("staging");
                    s = this.z10(s);
                    this.z78 = "secure";
                }
                if (this.z50.equals("post_authorize")) {
                    s = this.z9(s);
                    this.z78 = "secure";
                }
                final String z50 = this.z50;
                if (z50.equals("post_html") || z50.equals("post_worldpay") || z50.equals("post_barclay") || z50.equals("post_authorize") || z50.equals("post_new_worldpay")) {
                    if (System.getProperty("os.name").equals("16-bit Windows")) {
                        if (this.z0(s) && this.z138.indexOf("OK") != -1) {
                            b = true;
                        }
                    }
                    else if (this.z14(s) && this.z7() && this.z138.indexOf("OK") != -1) {
                        b = true;
                    }
                }
                if (b && !z50.equals("java")) {
                    try {
                        this.z121 = this.z138.substring(0, this.z138.indexOf("O"));
                        this.shop_pl_url = String.valueOf(String.valueOf(this.z129).concat(String.valueOf("?Action=get_file&process_id="))).concat(String.valueOf(this.z121));
                        final URL url = new URL(this.shop_pl_url);
                        if (!this.javascript) {
                            this.getAppletContext().showDocument(url, "_blank");
                        }
                        else {
                            this.send_order = true;
                            this.sent_order_1 = 1;
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(String.valueOf("Error: ").concat(String.valueOf(ex)));
                    }
                }
                return;
            }
            this.z186 = this.z129;
            if (this.z2(s)) {
                return;
            }
        }
    }
    
    public void clear() {
        z1.z122.clear();
        z1.z79.clear();
        z1.z120.clear();
        z1.z61.clear();
        z1.z184.clear();
        z1.z20 = "";
        z1.z174 = 0.0;
        z1.z175 = 0.0;
        this.z26 = 0.0;
    }
    
    public void destroy() {
        if (this.z6.isAlive()) {
            z1.z152 = true;
            try {
                this.z6.join(1000L);
            }
            catch (Exception ex) {
                System.out.println(String.valueOf("Thread: ").concat(String.valueOf(ex)));
            }
        }
    }
    
    static synchronized void z0() {
        z1.z75.setText1();
        for (int i = 0; i < 11; ++i) {
            z1.z45[i] = z0(i + 1);
        }
        z1.z171[9] = "";
        final String replace = z0(12).replace('¶', '\r').replace('§', '\n');
        z1.z181.setValue(0);
        String s = "";
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(z1.z54);
        int n = 0;
        try {
            String s2;
            while ((s2 = new DataInputStream(new StringBufferInputStream(replace)).readLine()) != null) {
                if (s2 == null) {
                    s2 = "";
                }
                if (s2.equals("")) {
                    s = String.valueOf(s).concat(String.valueOf("\n"));
                    ++n;
                }
                while (!s2.equals("")) {
                    String substring = "";
                    final String s3 = s2;
                    while (fontMetrics.stringWidth(s2) > z1.z167 - 21) {
                        final int lastIndex = s2.lastIndexOf(" ");
                        if (lastIndex == -1) {
                            break;
                        }
                        if (s2.length() > 0) {
                            substring = s3.substring(lastIndex);
                        }
                        s2 = s2.substring(0, lastIndex);
                    }
                    s = String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("\n"));
                    s2 = substring.trim();
                }
            }
        }
        catch (Exception ex) {
            s = replace;
        }
        z10();
        z1.z168.setText(s, z1.z191 - 20, z1.z192);
        int n2 = 30 + z1.z17;
        final int n3 = 32;
        final String s4 = z1.z45[6];
        z1.z0.remove(z1.z24);
        (z1.z24 = new Choice()).setForeground(z1.z66);
        z1.z24.setBackground(z1.z105);
        int n4 = 1;
        if (!z1(s4, 0).trim().equals("")) {
            z1.z24.addItem(String.valueOf("Select ").concat(String.valueOf(z0(14))));
            while (!z1(s4, n4).equals("")) {
                z1.z24.addItem(z1(s4, n4));
                ++n4;
            }
        }
        if (n4 == 1) {
            z1.z24.addItem("");
        }
        else {
            z1.z0.add(z1.z24);
            final int n5 = fontMetrics.stringWidth(z1.z24.getItem(0)) + 30;
            z1.z24.reshape(n2, n3, n5, 30);
            n2 = n2 + n5 + 20;
        }
        z1.z24.select(0);
        final String s5 = z1.z45[7];
        z1.z0.remove(z1.z143);
        (z1.z143 = new Choice()).setForeground(z1.z66);
        z1.z143.setBackground(z1.z105);
        int n6 = 1;
        if (!z1(s5, 0).trim().equals("")) {
            z1.z0.add(z1.z143);
            z1.z143.addItem(String.valueOf("Select ").concat(String.valueOf(z0(15))));
            while (!z1(s5, n6).equals("")) {
                z1.z143.addItem(z1(s5, n6));
                ++n6;
            }
            final int n7 = fontMetrics.stringWidth(z1.z143.getItem(0)) + 30;
            z1.z143.reshape(n2, n3, n7, 30);
            n2 = n2 + n7 + 20;
        }
        if (n6 == 1) {
            z1.z143.addItem("");
        }
        final String s6 = z1.z45[8];
        z1.z0.remove(z1.z19);
        (z1.z19 = new Choice()).setForeground(z1.z66);
        z1.z19.setBackground(z1.z105);
        int n8 = 1;
        if (!z1(s6, 0).trim().equals("")) {
            z1.z0.add(z1.z19);
            z1.z19.addItem(String.valueOf("Select ").concat(String.valueOf(z0(16))));
            while (!z1(s6, n8).equals("")) {
                z1.z19.addItem(z1(s6, n8));
                ++n8;
            }
            z1.z19.reshape(n2, n3, fontMetrics.stringWidth(z1.z19.getItem(0)) + 30, 30);
        }
        if (n8 == 1) {
            z1.z19.addItem("");
        }
        z1.z112.show();
        if (z1.z45[10].trim().equals("")) {
            z1.z112.button_show = false;
        }
        else {
            z1.z112.button_show = true;
        }
        z1.z0.setBackground(z1.z104);
    }
    
    private void z1() {
        (z1.z0 = new z7()).setLayout(null);
        z1.z0.setBackground(z1.z104);
        if (z1.z15.countItems() > 2) {
            z1.z0.add(z1.z16);
            final FontMetrics fontMetrics = z1.z168.getFontMetrics(z1.z54);
            z1.z17 = fontMetrics.stringWidth(z1.z16.getItem(0)) + 30;
            for (int i = 0; i < z1.z16.countItems(); ++i) {
                if (z1.z17 < fontMetrics.stringWidth(z1.z16.getItem(i)) + 30) {
                    z1.z17 = fontMetrics.stringWidth(z1.z16.getItem(i)) + 30;
                }
            }
            z1.z16.reshape(10, 0, z1.z17, 59);
            this.z102.reshape(z1.z17 + 20, 0, 800, 32);
        }
        this.z102.setBackground(z1.z104);
        this.z102.setForeground(z1.z65);
        z1.z0.add(this.z102);
        this.z102.setLayout(new FlowLayout(0));
        z1.z130.setFont(z1.z54);
        z1.z130.setForeground(z1.z65);
        final Label label = new Label(z1(this.z182, 1));
        label.setAlignment(2);
        if (this.z142) {
            this.z102.add(this.z144);
        }
        if (z1.z25) {
            this.z102.add(z1.z130);
            this.z102.add(z1.tf);
        }
        this.z102.add(label);
        this.z102.add(this.z86);
        label.setFont(z1.z54);
        this.z102.add(this.z1);
        this.z102.add(this.z96);
        this.z1.setFont(z1.z54);
        this.z96.setFont(z1.z54);
        z1.z0.add(z1.z75);
        (z1.z45 = new String[z1.z82])[0] = "";
        for (int j = 0; j < z1.z82; ++j) {
            z1.z45[j] = "";
        }
        z1.z112 = new z2();
        z1.z0.add(z1.z181);
        z1.z0.add(z1.z168);
        z1.z0.add(z1.z163);
        z1.z0.add(z1.z112);
        z1.z168.setFont(z1.z54);
    }
    
    private void z2() {
        this.z44 = 22;
        this.z100.setLayout(null);
        final Panel panel = new Panel();
        this.z100.add(panel);
        panel.add(this.z172);
        panel.add(this.z21);
        panel.add(this.z22);
        panel.reshape(z1.z17 + 10, 32, this.size().width - 15 - z1.z17, 32);
        panel.setLayout(new FlowLayout(0));
        this.z101.reshape(5, 8 + this.z10 * 2, this.size().width - 10, this.size().height - (8 + this.z10 * 2) - 65);
        this.z100.add(this.z101);
        this.z101.setForeground(z1.z65);
        this.z101.setFont(this.z27);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(0));
        this.z97.setLabel(z1(this.z36, 3));
        this.z97.setForeground(this.z11);
        this.z97.setBackground(this.z9);
        this.z97.setFont(z1.z54);
        this.z21.setLabel(z1(this.z182, 14));
        if (z1(this.z182, 13).trim().equals("")) {
            this.z22.setLabel("Clear Selected Items");
        }
        else {
            this.z22.setLabel(z1(this.z182, 13));
        }
        this.z21.setForeground(this.z11);
        this.z21.setBackground(this.z9);
        this.z22.setForeground(this.z11);
        this.z22.setBackground(this.z9);
        this.z22.setFont(z1.z54);
        this.z21.setFont(z1.z54);
        this.z124.setLabel(z1(this.z182, 15));
        this.z124.setForeground(this.z11);
        this.z124.setBackground(this.z9);
        if (this.z162) {
            panel2.add(this.z156);
        }
        if (this.z117) {
            panel2.add(this.z140);
        }
        if (this.z187 || this.z81) {
            panel2.add(this.z188);
        }
        if (this.z83) {
            panel2.add(this.z85);
        }
        if (this.z131) {
            panel2.add(this.z133);
        }
        if (this.z4) {
            panel2.add(this.z5);
        }
        if (this.z147) {
            panel2.add(this.z148);
        }
        if (this.z7) {
            panel2.add(this.z8);
        }
        if (this.z3) {
            panel2.add(this.z97);
        }
        this.z188.setForeground(this.z11);
        this.z188.setBackground(this.z9);
        this.z188.setFont(z1.z54);
        this.z84.setForeground(this.z11);
        this.z84.setBackground(this.z9);
        this.z84.setFont(z1.z54);
        this.z85.setForeground(this.z11);
        this.z85.setBackground(this.z9);
        this.z85.setFont(z1.z54);
        this.z132.setForeground(this.z11);
        this.z132.setBackground(this.z9);
        this.z132.setFont(z1.z54);
        this.z133.setForeground(this.z11);
        this.z133.setBackground(this.z9);
        this.z133.setFont(z1.z54);
        this.z5.setForeground(this.z11);
        this.z5.setBackground(this.z9);
        this.z5.setFont(z1.z54);
        this.z8.setForeground(this.z11);
        this.z8.setBackground(this.z9);
        this.z8.setFont(z1.z54);
        this.z124.setFont(z1.z54);
        panel2.add(this.z91);
        this.z91.setForeground(z1.z65);
        this.z91.setBackground(z1.z104);
        this.z100.add(panel2);
        panel2.reshape(0, this.size().height - 64, this.size().width, 64);
    }
    
    private void z3() {
        final int n = 140;
        final int n2 = 287;
        final int n3 = 21;
        final int n4 = 21;
        final int n5 = 310;
        final int n6 = 400;
        final int n7 = 759;
        final int n8 = 100;
        final int n9 = 270;
        final int n10 = 0;
        final int n11 = 190 - n9;
        final int n12 = n4;
        final int n13 = n11 + 10;
        final int n14 = n13 + n12;
        final int n15 = n13 + n12 * 2;
        final int n16 = n13 + n12 * 3;
        final int n17 = n13 + n12 * 4;
        final int n18 = 10;
        this.z136.setLayout(null);
        final Panel panel = new Panel();
        this.z136.add(panel);
        panel.add(this.z173);
        panel.reshape(n8, n10, this.size().width - n8, n4 + 8);
        panel.reshape(0, n10, this.size().width - n8, n4 + 8);
        panel.setLayout(new FlowLayout(0));
        final Label label = new Label("Name: ", 2);
        label.setFont(z1.z54);
        this.z136.add(label);
        label.reshape(0, n11 + n18 + n, n8, n4);
        final Label label2 = new Label("Name: ", 2);
        label2.setFont(z1.z54);
        if (this.z2) {
            this.z136.add(label2);
        }
        label2.reshape(n5, n11 + n18 + n, n6 - n5, n4);
        final Panel panel2 = new Panel();
        this.z136.add(panel2);
        panel2.reshape(n8, n11 + n18 + n, n5 - n8, n4);
        panel2.setLayout(new FlowLayout(0, 0, 0));
        panel2.add(this.z94);
        this.z94.setForeground(Color.black);
        this.z94.setBackground(Color.white);
        final Panel panel3 = new Panel();
        if (this.z2) {
            this.z136.add(panel3);
        }
        final Label label3 = new Label("Delivery Details");
        final Label label4 = new Label("Card Holder Details");
        if (!this.z2) {
            label4.setText("Delivery Details");
        }
        if (this.z2) {
            this.z136.add(label3);
        }
        this.z136.add(label4);
        label4.setFont(z1.z54);
        label3.setFont(z1.z54);
        label3.reshape(n6, n11 + n18 + n - n4, 759 - n6, n4);
        label4.reshape(n8, n11 + n18 + n - n4, n5 - n8, n4);
        this.z136.reshape(n6, n11 + n18 + n, 759 - n6, n4);
        panel3.reshape(n6, n11 + n18 + n, n5 - n8, n4);
        panel3.setLayout(new FlowLayout(0, 0, 0));
        if (this.z2) {
            panel3.add(this.z33);
        }
        this.z33.setForeground(Color.black);
        this.z33.setBackground(Color.white);
        final Label label5 = new Label("Address: ", 2);
        label5.setFont(z1.z54);
        this.z136.add(label5);
        label5.reshape(0, n14 + n, n8, n4);
        final Label label6 = new Label("Address: ", 2);
        label6.setFont(z1.z54);
        if (this.z2) {
            this.z136.add(label6);
        }
        label6.reshape(n5, n14 + n, n6 - n5, n4);
        this.z136.add(this.z88);
        this.z88.reshape(n8, n14 + n, n5 - n8, n4 * 5);
        this.z88.setForeground(Color.black);
        this.z88.setBackground(Color.white);
        if (this.z2) {
            this.z136.add(this.z31);
        }
        this.z31.reshape(n6, n14 + n, n5 - n8, n4 * 5);
        this.z31.setForeground(Color.black);
        this.z31.setBackground(Color.white);
        final Label label7 = new Label("PostCode/Zip: ", 2);
        label7.setFont(z1.z54);
        this.z136.add(label7);
        label7.reshape(0, n14 + 5 * n4 + n, n8, n4);
        final Panel panel4 = new Panel();
        this.z136.add(panel4);
        panel4.add(this.z89);
        panel4.reshape(n8, n14 + n4 * 5 + n, n5 - n8 - 20, n4);
        panel4.setLayout(new FlowLayout(0, 0, 0));
        this.z89.setForeground(Color.black);
        this.z89.setBackground(Color.white);
        final Label label8 = new Label("PostCode/Zip: ", 2);
        label8.setFont(z1.z54);
        if (this.z2) {
            this.z136.add(label8);
        }
        label8.reshape(n5, n14 + 5 * n4 + n, n6 - n5, n4);
        final Panel panel5 = new Panel();
        if (this.z2) {
            this.z136.add(panel5);
        }
        if (this.z2) {
            panel5.add(this.z32);
        }
        panel5.reshape(n6, n14 + n4 * 5 + n, 759 - n6, n4);
        panel5.setLayout(new FlowLayout(0, 0, 0));
        this.z32.setForeground(Color.black);
        this.z32.setBackground(Color.white);
        final Label label9 = new Label("Phone Number: ");
        this.z136.add(label9);
        label9.setFont(z1.z54);
        label9.reshape(0, n13 + n2, n8, n4);
        label9.setAlignment(2);
        final Panel panel6 = new Panel();
        this.z136.add(panel6);
        panel6.reshape(n8, n13 + n2, n5 - n8, n4);
        panel6.setLayout(new FlowLayout(0, 0, 0));
        panel6.add(this.z103);
        this.z103.setForeground(Color.black);
        this.z103.setBackground(Color.white);
        final Label label10 = new Label("Email: ");
        label10.setAlignment(2);
        this.z136.add(label10);
        label10.setFont(z1.z54);
        label10.reshape(0, n14 + n2, n8, n4);
        final Panel panel7 = new Panel();
        this.z136.add(panel7);
        panel7.reshape(n8, n14 + n2, n5 - n8, n4);
        panel7.setLayout(new FlowLayout(0, 0, 0));
        panel7.add(this.z90);
        this.z90.setForeground(Color.black);
        this.z90.setBackground(Color.white);
        final Label label11 = new Label("Purchase Order: ");
        label11.setAlignment(2);
        if (!label11.getText().trim().equals(":") && this.z28) {
            this.z136.add(label11);
            label11.reshape(0, n17 + n2, n8, n4);
            label11.setFont(z1.z54);
            this.z93.setBackground(z1.z105);
            this.z93.setForeground(z1.z66);
            final Panel panel8 = new Panel();
            this.z136.add(panel8);
            panel8.reshape(n8, n17 + n2, n5 - n8, n4);
            panel8.setLayout(new FlowLayout(0, 0, 0));
            panel8.add(this.z93);
        }
        final Label label12 = new Label("Comments: ");
        label12.setAlignment(2);
        this.z136.add(label12);
        label12.setFont(z1.z54);
        this.z136.add(this.z179);
        this.z179.setBackground(z1.z105);
        this.z179.setForeground(z1.z66);
        if (this.z28) {
            final Label label13 = new Label("Card Number: ");
            final Label label14 = new Label("Expriy Date: ");
            label13.setFont(z1.z54);
            label14.setFont(z1.z54);
            this.z136.add(label13);
            label13.reshape(0, n16 + n2, n8, n3);
            label13.setAlignment(2);
            this.z12.setBackground(z1.z105);
            this.z12.setForeground(z1.z66);
            final Panel panel9 = new Panel();
            this.z136.add(panel9);
            panel9.reshape(n8, n16 + n2, n5 - n8, n4);
            panel9.setLayout(new FlowLayout(0, 0, 0));
            panel9.add(this.z12);
            this.z136.add(label14);
            label14.reshape(0, n15 + n2, n8, n4);
            label14.setAlignment(2);
            final Panel panel10 = new Panel();
            this.z136.add(panel10);
            panel10.reshape(n8, n15 + n2, n5 - n8, n4);
            panel10.setLayout(new FlowLayout(0, 0, 0));
            final Label label15 = new Label("/", 1);
            label15.setFont(z1.z54);
            panel10.add(this.z42);
            panel10.add(label15);
            panel10.add(this.z43);
            this.z42.setForeground(Color.black);
            this.z42.setBackground(Color.white);
            this.z43.setForeground(Color.black);
            this.z43.setBackground(Color.white);
        }
        final Panel panel11 = new Panel();
        panel11.setLayout(new FlowLayout(0));
        this.z98.setLabel("Order Button 2");
        this.z98.setForeground(this.z11);
        this.z98.setBackground(this.z9);
        this.z97.setFont(z1.z54);
        this.z125.setLabel("Return to Shop");
        this.z125.setForeground(this.z11);
        this.z125.setBackground(this.z9);
        this.z125.setFont(z1.z54);
        this.z173.setForeground(this.z11);
        this.z173.setBackground(this.z9);
        this.z173.setFont(z1.z54);
        panel11.add(this.z125);
        if (this.z83) {
            panel11.add(this.z84);
        }
        if (this.z131) {
            panel11.add(this.z132);
        }
        panel11.add(this.z92);
        this.z92.setForeground(Color.red);
        this.z136.add(panel11);
        panel11.reshape(0, this.size().height - n4 - 12, n7, n4 + 8);
    }
    
    static String z0(String s, final int n) {
        int n2 = 0;
        s = String.valueOf(s).concat(String.valueOf("|||||||||||||||||"));
        for (int i = 1; i < n; ++i) {
            n2 = s.indexOf(124, n2) + 1;
        }
        s = s.substring(n2, s.indexOf(124, n2));
        return s;
    }
    
    public static void find(String replace) {
        z1.z130.setText("Searching");
        replace = replace.trim().replace(' ', '_');
        for (int countItems = z1.z137.countItems(), i = 0; i < countItems; ++i) {
            int lastIndex = z0(z1.z137.getItem(i), 1).toUpperCase().lastIndexOf(replace.toUpperCase(), 0);
            if (z0(z1.z137.getItem(i), 13).toUpperCase().lastIndexOf(replace.toUpperCase(), 0) > -1) {
                lastIndex = 1;
            }
            if (lastIndex != -1) {
                z1.z185.select(i);
                z1.z185.makeVisible(i);
                z1.z185.requestFocus();
                if (countItems > z1.z116.countItems()) {
                    z0();
                }
                try {
                    if (i == 0) {
                        z1.z75.translate(0, Integer.parseInt(z1.z116.getItem(i)) - z1.z115 - z1.z115);
                        z1.z163.setValue(0);
                    }
                    else {
                        z1.z75.translate(0, Integer.parseInt(z1.z116.getItem(i - 1)));
                        z1.z163.setValue(Integer.parseInt(z1.z116.getItem(i - 1)) / z1.z115);
                    }
                    z0();
                }
                catch (Exception ex) {
                    System.out.println(String.valueOf("Exception ").concat(String.valueOf(ex)));
                    System.out.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Item count: ").concat(String.valueOf(countItems))).concat(String.valueOf("\nCount Items: "))).concat(String.valueOf(z1.z137.countItems()))).concat(String.valueOf("\nPos count: "))).concat(String.valueOf(z1.z116.countItems()))).concat(String.valueOf("\nFound:"))).concat(String.valueOf(lastIndex)));
                    break;
                }
                z1.z130.setText("Search Index");
                return;
            }
        }
    }
    
    static String z1(String s, final int n) {
        int n2 = 0;
        s = String.valueOf(s).concat(String.valueOf(",,,,,,,,,"));
        for (int i = 1; i < n; ++i) {
            n2 = s.indexOf(44, n2) + 1;
        }
        s = s.substring(n2, s.indexOf(44, n2));
        return s;
    }
    
    private boolean z0(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.z129).concat(String.valueOf("?"))).concat(String.valueOf("Action="))).concat(String.valueOf(URLEncoder.encode(this.z78)))).concat(String.valueOf("&info="))).concat(String.valueOf(URLEncoder.encode(s)));
        try {
            this.z138 = "";
            final URLConnection openConnection = new URL(concat).openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String protocol = this.getCodeBase().getProtocol();
            String line;
            while ((line = dataInputStream.readLine()) != null && line.compareTo("end") != 0) {
                protocol = line;
            }
            dataInputStream.close();
            this.z91.setText(protocol);
            this.z138 = protocol;
            return true;
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
            return false;
        }
    }
    
    private boolean z1(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("x_Version=3.1&x_Login=").concat(String.valueOf(URLEncoder.encode(this.getParameter("Authorize"))))).concat(String.valueOf("&x_Show_Form=PAYMENT_FORM"))).concat(String.valueOf("&x_Amount="))).concat(String.valueOf(this.z0(this.z52)))).concat(String.valueOf("&x_Invoice_Num="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&x_Method=ECHECK"));
        String s2;
        if (this.javascript) {
            s2 = String.valueOf(concat).concat(String.valueOf("&javascript=true"));
        }
        else {
            s2 = String.valueOf(concat).concat(String.valueOf("&javascript=false"));
        }
        if (this.z80.indexOf("verify") != -1) {
            s2 = String.valueOf(s2).concat(String.valueOf("&verify=1"));
        }
        String s3 = "";
        for (int i = 0; i < z1.z122.countItems(); ++i) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z7(z1.z61.getItem(i))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z7(z1.z79.getItem(i))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z122.getItem(i)))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z120.getItem(i)))).concat(String.valueOf(")"))).concat(String.valueOf("\n<br>"));
        }
        String s4 = "";
        for (int j = 0; j < z1.z122.countItems(); ++j) {
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z7(z1.z61.getItem(j))))).concat(String.valueOf(" "))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z7(z1.z122.getItem(j))))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z120.getItem(j)))).concat(String.valueOf(")"))).concat(String.valueOf("\n<br>"));
        }
        int n = 0;
        String s5;
        if (this.z117) {
            ++n;
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("\n<br>"));
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("\n<br>"));
            s5 = String.valueOf(String.valueOf(s2).concat(String.valueOf("&x_Freight="))).concat(String.valueOf(this.z0(this.z26)));
        }
        else {
            s5 = String.valueOf(s2).concat(String.valueOf("&x_Freight=$0"));
        }
        String s6;
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z159))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("\n<br>"));
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z159))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("\n<br>"));
            s6 = String.valueOf(String.valueOf(s5).concat(String.valueOf("&x_Tax="))).concat(String.valueOf(this.z0(this.z160)));
        }
        else {
            s6 = String.valueOf(s5).concat(String.valueOf("&x_Tax=$0"));
        }
        String trim;
        int index;
        String concat2;
        for (trim = s3; trim.indexOf("<br>", 0) != -1; trim = String.valueOf(concat2.substring(0, index)).concat(String.valueOf(concat2.substring(index + 4))).trim()) {
            index = trim.indexOf("<br>", 0);
            concat2 = String.valueOf(trim).concat(String.valueOf("       "));
        }
        final String concat3 = String.valueOf(String.valueOf(s6).concat(String.valueOf("&Description="))).concat(String.valueOf(URLEncoder.encode(trim)));
        String s7;
        if (s3.length() < 256) {
            s7 = String.valueOf(String.valueOf(concat3).concat(String.valueOf("&x_Description="))).concat(String.valueOf(URLEncoder.encode(s3)));
        }
        else if (s4.length() < 256) {
            s7 = String.valueOf(String.valueOf(concat3).concat(String.valueOf("&x_Description="))).concat(String.valueOf(URLEncoder.encode(s4)));
        }
        else {
            s7 = String.valueOf(concat3).concat(String.valueOf("&x_Description=Internet+Order"));
        }
        final String concat4 = String.valueOf(String.valueOf(this.z129).concat(String.valueOf("?"))).concat(String.valueOf(s7));
        try {
            this.z138 = "";
            final URL url = new URL(concat4);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = concat4;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    static void z4() {
        z1.z185 = new List();
        z1.z137 = new List();
        z1.z116 = new List();
        StringBuffer sb = new StringBuffer("");
        boolean b = false;
        int selectedIndex = z1.z15.getSelectedIndex();
        if (selectedIndex < 1) {
            selectedIndex = 1;
        }
        for (int i = selectedIndex; i < z1.z15.countItems(); ++i) {
            String s;
            String s2;
            if (z1.alt) {
                s = String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')).concat(String.valueOf(".html"));
                s2 = String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')).concat(String.valueOf(".txt"));
                if (i == 1) {
                    System.out.println(String.valueOf("alt ").concat(String.valueOf(s2)));
                }
            }
            else {
                s2 = String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')).concat(String.valueOf(".html"));
                s = String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')).concat(String.valueOf(".txt"));
            }
            DataInputStream dataInputStream;
            try {
                final String replace = z1.z15.getItem(i).trim().replace(' ', '_');
                int j;
                for (j = 0; j < z1.z77.countItems(); ++j) {
                    if (z1.z77.getItem(j).trim().replace(' ', '_').equals(replace)) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    dataInputStream = new DataInputStream(new StringBufferInputStream(z1.z76.getItem(j)));
                }
                else {
                    dataInputStream = new DataInputStream(new URL(new URL(z1.z177), s2).openConnection().getInputStream());
                }
            }
            catch (Exception ex2) {
                try {
                    final URLConnection openConnection = new URL(new URL(z1.z177), s).openConnection();
                    openConnection.setUseCaches(false);
                    dataInputStream = new DataInputStream(openConnection.getInputStream());
                }
                catch (Exception ex3) {
                    try {
                        final URLConnection openConnection2 = new URL(new URL(z1.z177), s2).openConnection();
                        openConnection2.setUseCaches(false);
                        dataInputStream = new DataInputStream(openConnection2.getInputStream());
                    }
                    catch (Exception ex4) {
                        try {
                            final URLConnection openConnection3 = new URL(new URL(z1.z177), s).openConnection();
                            openConnection3.setUseCaches(false);
                            dataInputStream = new DataInputStream(openConnection3.getInputStream());
                        }
                        catch (Exception ex5) {
                            return;
                        }
                    }
                }
            }
            try {
                z1.z53 = dataInputStream.readLine();
                if (z1.z53.toLowerCase().trim().equals("<html>")) {
                    z1.z53 = dataInputStream.readLine();
                }
                if (z1.z53.trim().equals("<!--")) {
                    z1.z53 = dataInputStream.readLine();
                }
                for (int k = 0; k < z1.z82; ++k) {
                    z1.z171[k] = z0(z1.z53, k + 1);
                }
                sb = new StringBuffer("");
                sb.append(String.valueOf(z1.z53.trim()).concat(String.valueOf("\n")));
                String s3;
                while ((s3 = dataInputStream.readLine()) != null && s3.compareTo("-->") != 0) {
                    if (z1.z152) {
                        return;
                    }
                    sb.append(String.valueOf(s3.trim()).concat(String.valueOf("\n")));
                    int n = 12;
                    if (z1.z53.indexOf("|v3-ssc") != -1) {
                        int n2 = 0;
                        final String concat = String.valueOf(s3).concat(String.valueOf("||||||||||||||"));
                        if (!z0(concat, 14).trim().equals("")) {
                            n = 16;
                        }
                        for (int l = 1; l < n; ++l) {
                            n2 = concat.indexOf(124, n2) + 1;
                        }
                        s3 = String.valueOf(concat.substring(0, concat.indexOf(124, n2))).concat(String.valueOf("|"));
                    }
                    if (z0(s3, 13).trim().equals("") && n == 12) {
                        s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')))).concat(String.valueOf("|"))).concat(String.valueOf(z0(z1.z53, 7)))).concat(String.valueOf("|"))).concat(String.valueOf(z0(z1.z53, 8)))).concat(String.valueOf("|"))).concat(String.valueOf(z0(z1.z53, 9)))).concat(String.valueOf("|"));
                    }
                    if (s3.indexOf(124) > 0) {
                        z1.z137.addItem(s3);
                        z1.z185.addItem(z0(s3, 1));
                    }
                    if (z1.z137.countItems() != 1 && z1.z137.countItems() != 20) {
                        continue;
                    }
                    System.out.println(z1.z137.countItems());
                    z1.z185.select(0);
                    z0();
                }
                dataInputStream.close();
            }
            catch (Exception ex) {
                System.out.println(String.valueOf(String.valueOf(String.valueOf("get data ").concat(String.valueOf(i))).concat(String.valueOf(" "))).concat(String.valueOf(ex)));
            }
            if (z1.z137.countItems() < 1) {
                final String s4 = "New_Record||||||||||||||||||||";
                z1.z185.addItem("New_Record", 0);
                z1.z137.addItem(s4, 0);
            }
            else if (!b) {
                z1.z77.addItem(z1.z15.getItem(i).trim().replace(' ', '_'));
                z1.z76.addItem(sb.toString());
            }
            if (!z1.z25) {
                break;
            }
        }
        z0();
        if (!z1.tf.getText().trim().equals("")) {
            find(z1.tf.getText().trim());
            z1.z130.setText("Search Index");
        }
    }
    
    private boolean z2(final String s) {
        String s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("store=").concat(String.valueOf(this.z190))).concat(String.valueOf("&cname="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z29, 2))))).concat(String.valueOf("&mail="))).concat(String.valueOf(this.getParameter("E_Mail_Address")));
        if (this.z50.equals("get_barclay")) {
            s2 = String.valueOf(s2).concat(String.valueOf("&barclay=true"));
        }
        if (this.z162) {
            final String s3 = this.z158[this.z156.getSelectedIndex()];
        }
        int n = 0;
        for (int i = 0; i < z1.z122.countItems(); ++i) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&code"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z61.getItem(i))))).concat(String.valueOf("&desc"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z79.getItem(i).trim())))).concat(String.valueOf("&cost"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z120.getItem(i))))).concat(String.valueOf("&qty"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z122.getItem(i))));
            if (this.z187) {
                s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&cur"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z29, 2))));
            }
        }
        if (this.z117) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&code"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode("~WP_PNP")))).concat(String.valueOf("&desc"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(this.z140.getSelectedItem())))).concat(String.valueOf("&cost"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(this.z0(this.z26))))).concat(String.valueOf("&qty"))).concat(String.valueOf(n))).concat(String.valueOf("=1"))).concat(String.valueOf("&cur"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z29, 2))));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&code"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode("~WP_TAX")))).concat(String.valueOf("&desc"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(this.z156.getSelectedItem())))).concat(String.valueOf("&cost"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(this.z160)))))).concat(String.valueOf("&qty"))).concat(String.valueOf(n))).concat(String.valueOf("=1"))).concat(String.valueOf("&cur"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z29, 2))));
        }
        final String concat = String.valueOf(String.valueOf(String.valueOf(this.z186).concat(String.valueOf("?"))).concat(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&max="))).concat(String.valueOf(n))))).concat(String.valueOf("&Action=processform"));
        if (this.z186.length() < 8) {
            this.z91.setText("Please set the program up before making an order");
        }
        try {
            this.z138 = "";
            final URL url = new URL(concat);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = concat;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    static String z0(final int n) {
        int z5 = z5();
        if (z5 == -1) {
            z5 = 0;
        }
        return z0(z1.z137.getItem(z5), n);
    }
    
    private boolean z3(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("instId=").concat(String.valueOf(URLEncoder.encode(this.z190)))).concat(String.valueOf("&cartId="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&currency="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z29, 2))))).concat(String.valueOf("&amount="))).concat(String.valueOf(this.z0(this.z52)))).concat(String.valueOf("&testMode="))).concat(String.valueOf(this.getParameter("test_mode")))).concat(String.valueOf("&M_recipient="))).concat(String.valueOf(this.getParameter("E_Mail_Address")))).concat(String.valueOf("&M_subject="))).concat(String.valueOf(URLEncoder.encode("Worldpay Order")));
        String s2 = "";
        for (int i = 0; i < z1.z122.countItems(); ++i) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z7(z1.z61.getItem(i))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z7(z1.z79.getItem(i))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z122.getItem(i)))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z120.getItem(i)))).concat(String.valueOf("\n<br>"));
        }
        String s3 = "";
        for (int j = 0; j < z1.z122.countItems(); ++j) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z7(z1.z61.getItem(j))))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z7(z1.z122.getItem(j))))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z120.getItem(j)))).concat(String.valueOf("\n<br>"));
        }
        int n = 0;
        if (this.z117) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("<BR>"));
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("<BR>"));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("<BR>"));
            s3 = String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("<BR>"));
        }
        final String concat2 = String.valueOf(String.valueOf(concat).concat(String.valueOf("&MC_description="))).concat(String.valueOf(URLEncoder.encode(s2)));
        String s4;
        if (s2.length() < 256) {
            s4 = String.valueOf(String.valueOf(concat2).concat(String.valueOf("&desc="))).concat(String.valueOf(URLEncoder.encode(s2)));
        }
        else if (s3.length() < 256) {
            s4 = String.valueOf(String.valueOf(concat2).concat(String.valueOf("&desc="))).concat(String.valueOf(URLEncoder.encode(s3)));
        }
        else {
            s4 = String.valueOf(concat2).concat(String.valueOf("&desc=Details+on+email+receipt"));
        }
        String shop_pl_url;
        if (this.z189) {
            shop_pl_url = String.valueOf("https://select.worldpay.com/wcc/transaction?withDelivery=&").concat(String.valueOf(s4));
        }
        else {
            shop_pl_url = String.valueOf("https://select.worldpay.com/wcc/purchase?").concat(String.valueOf(s4));
        }
        try {
            this.z138 = "";
            final URL url = new URL(shop_pl_url);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = shop_pl_url;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    private boolean z4(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("email=").concat(String.valueOf(URLEncoder.encode(this.getParameter("E_Mail_Address"))))).concat(String.valueOf("&ordernumber="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&amount="))).concat(String.valueOf(this.z0(this.z52)));
        final String concat2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("\n").concat(String.valueOf(this.z94.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z88.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z89.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z103.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z90.getText()))).concat(String.valueOf("\n"));
        String s2 = "&description=";
        int n = 0;
        for (int i = 0; i < z1.z122.countItems(); ++i) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(URLEncoder.encode(String.valueOf(this.z7(z1.z61.getItem(i))).concat(String.valueOf(" ")))))).concat(String.valueOf(URLEncoder.encode(String.valueOf(this.z7(z1.z79.getItem(i).trim())).concat(String.valueOf(" ")))))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf("(").concat(String.valueOf(z1.z122.getItem(i)))).concat(String.valueOf("x) £")))))).concat(String.valueOf(URLEncoder.encode(String.valueOf(z1.z120.getItem(i)).concat(String.valueOf("\n\n")))));
        }
        if (this.z117) {
            ++n;
            s2 = String.valueOf(s2).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.z140.getSelectedItem()).concat(String.valueOf(" £"))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("\n")))));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            s2 = String.valueOf(s2).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.z159).concat(String.valueOf(" £"))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("\n")))));
        }
        final String concat3 = String.valueOf("https://www.nochex.com/nochex.dll/checkout?").concat(String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf(s2))).concat(String.valueOf(URLEncoder.encode(concat2)))));
        try {
            this.z138 = "";
            final URL url = new URL(concat3);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = concat3;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    private boolean z0(final String s, final String s2) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("add=1&business=").concat(String.valueOf(URLEncoder.encode(this.z111)))).concat(String.valueOf("&item_name="))).concat(String.valueOf(URLEncoder.encode(s)))).concat(String.valueOf("&quantity="))).concat(String.valueOf(this.z86.getText()))).concat(String.valueOf("&currency_code="))).concat(String.valueOf(z1(z1.z29, 2)))).concat(String.valueOf("&item_number="))).concat(String.valueOf(URLEncoder.encode(z1.z45[z1.z60])))).concat(String.valueOf("&amount="))).concat(String.valueOf(URLEncoder.encode(s2)));
        String s3;
        if (!z1.z143.getSelectedItem().trim().equals("")) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("&on0="))).concat(String.valueOf(URLEncoder.encode(z1.z171[7])))).concat(String.valueOf("&os0="))).concat(String.valueOf(URLEncoder.encode(z1.z143.getSelectedItem())))).concat(String.valueOf("&on1="))).concat(String.valueOf(URLEncoder.encode(z1.z171[6])))).concat(String.valueOf("&os1="))).concat(String.valueOf(URLEncoder.encode(z1.z24.getSelectedItem())));
        }
        else {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("&on0="))).concat(String.valueOf(URLEncoder.encode(z1.z171[6])))).concat(String.valueOf("&os0="))).concat(String.valueOf(URLEncoder.encode(z1.z24.getSelectedItem())));
        }
        String s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("&image_url="))).concat(String.valueOf(URLEncoder.encode(this.z107)))).concat(String.valueOf("&return"))).concat(String.valueOf(URLEncoder.encode(this.z154)))).concat(String.valueOf("&cancel_payment"))).concat(String.valueOf(URLEncoder.encode(this.z13)));
        if (this.z108.equals("No")) {
            s4 = String.valueOf(s4).concat(String.valueOf("&no_note=1"));
        }
        if (this.z109.equals("No")) {
            s4 = String.valueOf(s4).concat(String.valueOf("&no_shipping=1"));
        }
        if (System.getProperty("java.vendor").toLowerCase().indexOf("apple computer") != -1) {
            this.z110 = "http://www.paypal.com/cart";
        }
        final String concat2 = String.valueOf(String.valueOf(this.z110).concat(String.valueOf("?"))).concat(String.valueOf(s4));
        try {
            this.z138 = "";
            final URL url = new URL(concat2);
            if (!this.javascript) {
                if (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") != -1) {
                    this.getAppletContext().showDocument(url, "win");
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = concat2;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    private boolean z5(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("merchant=").concat(String.valueOf(this.z135))).concat(String.valueOf("&trans_id="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&amount="))).concat(String.valueOf(this.z0(this.z52)))).concat(String.valueOf("&options="))).concat(String.valueOf(URLEncoder.encode("test_status=")))).concat(String.valueOf("true"))).concat(String.valueOf("&callback="))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.getCodeBase().getProtocol()).concat(String.valueOf("://"))).concat(String.valueOf(this.getCodeBase().getHost()))).concat(String.valueOf("/received_order.html")))))).concat(String.valueOf(";"))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.getCodeBase().getProtocol()).concat(String.valueOf("://"))).concat(String.valueOf(this.getCodeBase().getHost()))).concat(String.valueOf("/failure.html")))));
        String s2 = "&order=";
        int n = 0;
        for (int i = 0; i < z1.z122.countItems(); ++i) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("prod="))).concat(String.valueOf(URLEncoder.encode(String.valueOf(this.z13(this.z7(z1.z61.getItem(i)))).concat(String.valueOf(" ")))))).concat(String.valueOf(URLEncoder.encode(this.z13(this.z7(z1.z79.getItem(i).trim())))))).concat(String.valueOf(URLEncoder.encode(",item_amount=")))).concat(String.valueOf(URLEncoder.encode(z1.z120.getItem(i))))).concat(String.valueOf("x"))).concat(String.valueOf(URLEncoder.encode(z1.z122.getItem(i))));
            if (i < z1.z122.countItems()) {
                s2 = String.valueOf(s2).concat(String.valueOf(";"));
            }
        }
        if (this.z117) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("prod="))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(this.z13(this.z140.getSelectedItem())).concat(String.valueOf(",amount="))).concat(String.valueOf(this.z0(this.z26))))))).concat(String.valueOf(";"));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("prod="))).concat(String.valueOf(URLEncoder.encode(this.z13(this.z159))))).concat(String.valueOf(",amount="))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf(";"));
        }
        final String concat2 = String.valueOf("https://www.secpay.com/java-bin/ValCard?").concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf(s2))).concat(String.valueOf("&ship_name="))).concat(String.valueOf(URLEncoder.encode(this.z94.getText())))).concat(String.valueOf("&ship_company="))).concat(String.valueOf(URLEncoder.encode(this.z94.getText())))).concat(String.valueOf("&ship_addr_1="))).concat(String.valueOf(URLEncoder.encode(this.z88.getText())))).concat(String.valueOf("&ship_post_code="))).concat(String.valueOf(URLEncoder.encode(this.z89.getText())))).concat(String.valueOf("&ship_tel="))).concat(String.valueOf(URLEncoder.encode(this.z103.getText())))).concat(String.valueOf("&ship_email="))).concat(String.valueOf(URLEncoder.encode(this.z90.getText())))));
        try {
            this.z138 = "";
            final URL url = new URL(concat2);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = concat2;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    static int z5() {
        int selectedIndex = z1.z185.getSelectedIndex();
        if (selectedIndex == -1) {
            selectedIndex = 0;
            z1.z185.select(0);
        }
        return selectedIndex;
    }
    
    private String z1(final int n) {
        return z1.z137.getItem(n);
    }
    
    private boolean z6(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf("merchant=").concat(String.valueOf(URLEncoder.encode("phsupplies2323")))).concat(String.valueOf("&requiredfields="))).concat(String.valueOf(URLEncoder.encode("name,telephone,email")));
        for (int i = 0; i < this.z157; ++i) {
            if (this.z155[i].getState()) {
                final String s2 = this.z158[i];
            }
        }
        final String concat2 = String.valueOf(concat).concat(String.valueOf("&orderinfo="));
        String s3 = "";
        for (int j = 0; j < z1.z122.countItems(); ++j) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(z1.z122.getItem(j)))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z61.getItem(j)))).concat(String.valueOf(" "))).concat(String.valueOf(z1.z79.getItem(j)))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z120.getItem(j)))).concat(String.valueOf(")"))).concat(String.valueOf("\n<br>"));
        }
        if (this.z161.getCurrent() != null && this.z162) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("1 x "))).concat(String.valueOf(this.z159))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf(")<br>"))).concat(String.valueOf("\n"));
        }
        if (this.z117) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("1 x "))).concat(String.valueOf("shipping"))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf(")\n<br>"));
        }
        final String concat3 = String.valueOf("https://securetrading.net/authorize/form.cgi?").concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat2).concat(String.valueOf(URLEncoder.encode(s3)))).concat(String.valueOf("&amount="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(Math.round(this.z52 * 100))))))));
        try {
            this.z138 = "";
            final URL url = new URL(concat3);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.sent_order_1 = 1;
                this.shop_pl_url = concat3;
            }
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
            return false;
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (this.getParameter("expand") != null && this.getParameter("expand").equals("Yes")) {
            if (event.target != this.z172 && event.target != z1.z24 && event.x < z1.z17 + 22 && event.y < 70) {
                int n = z1.z15.countItems() * z1.z115;
                if (z1.z16.size().height < 64) {
                    if (n <= z1.z115 * 4) {
                        n = 59;
                    }
                    z1.z16.reshape(10, 0, z1.z17, n);
                }
            }
            if (((event.target == z1.z16 && event.id == 701) || (event.target != z1.z16 && event.target != z1.z0 && (event.x >= z1.z17 + 22 || event.y > z1.z15.countItems() * z1.z115))) && z1.z16.size().height != 59) {
                z1.z16.reshape(10, 0, z1.z17, 59);
                z1.z16.makeVisible(z1.z16.getSelectedIndex());
            }
        }
        if (event.id == 1001 && (event.target == this.z125 || event.target == this.z173)) {
            this.z72.show(this, "order_form");
            return super.handleEvent(event);
        }
        if (event.target == z1.z112 && event.id == 503 && !this.z67) {
            this.z67 = true;
            z1.z141 = true;
            final Graphics graphics = z1.z112.getGraphics();
            z1.z112.paint_enlarge_only = true;
            z1.z112.paint(graphics);
            z1.z112.paint_enlarge_only = false;
            return super.handleEvent(event);
        }
        if (event.target == z1.z112 && event.id == 505 && this.z67) {
            this.z67 = false;
            z1.z141 = false;
            z1.z112.paint(z1.z112.getGraphics());
            return super.handleEvent(event);
        }
        if (event.target == this.z101 && event.id == 701) {
            try {
                if (Integer.parseInt(String.valueOf("").concat(String.valueOf(event.arg))) < 1 || Integer.parseInt(String.valueOf("").concat(String.valueOf(event.arg))) > z1.z122.countItems()) {
                    this.z101.deselect(Integer.parseInt(String.valueOf("").concat(String.valueOf(event.arg))));
                }
                this.z101.select(0);
                this.z101.deselect(0);
            }
            catch (Exception ex) {}
        }
        if (event.target == z1.z75 && event.id == 501) {
            final int n2 = event.y - z1.z74 + z1.z163.getValue() * z1.z75.point_height;
            int n3 = 0;
            for (int i = z1.z116.countItems(); i > 1; --i) {
                if (n2 >= Integer.parseInt(z1.z116.getItem(i - 2))) {
                    n3 = i - 1;
                    break;
                }
            }
            z1.z185.select(n3);
            z0();
        }
        if (event.id == 501 && event.target == z1.z112 && !z1.z45[10].trim().equals("")) {
            z1.z112.button_show = true;
            if ((this.javascript || this.javascript_1 == 1) && System.getProperty("java.vendor").indexOf("Netscape") == -1) {
                this.enlarge = true;
                this.enlarge_1 = 1;
                z1.image = z1.z45[10].trim();
            }
            else {
                if (this.z151 != null) {
                    this.z151.dispose();
                }
                String s = "";
                if (this.getCodeBase().getProtocol().toLowerCase().equals("http")) {
                    s = "?";
                }
                try {
                    this.z151 = new z6(Toolkit.getDefaultToolkit().getImage(new URL(this.getCodeBase(), String.valueOf(z1.z45[10]).concat(String.valueOf(s)))));
                }
                catch (Exception ex2) {}
                this.z151.resize(200, 200);
                this.z151.show();
            }
        }
        if (event.target == z1.z181 && (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603)) {
            z1.z168.translate(0, z1.z181.getValue() * Toolkit.getDefaultToolkit().getFontMetrics(z1.z54).getHeight());
            return true;
        }
        if (event.target == z1.z163 && (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603)) {
            z1.z75.translate(0, z1.z163.getValue() * z1.z115);
            return true;
        }
        if (event.id == 701 && event.target == z1.z16 && z1.z16.getSelectedIndex() > 0) {
            z1.tf.setText("");
            z1.z15.select(z1.z16.getSelectedIndex());
            this.show_category("");
        }
        if (event.id == 1001) {
            z1.tf.setText("");
            if (event.target == z1.z15 && z1.z15.getSelectedIndex() > 0) {
                this.show_category("");
            }
            if (event.target == this.z22) {
                final int countItems = z1.z122.countItems();
                boolean b = false;
                for (int j = countItems; j > 0; --j) {
                    if (this.z101.isSelected(j)) {
                        this.z101.delItem(j);
                        z1.z174 -= Integer.parseInt(z1.z122.getItem(j - 1)) * new Double(z1.z120.getItem(j - 1));
                        z1.z175 -= new Double(z1.z184.getItem(j - 1));
                        z1.z122.delItem(j - 1);
                        z1.z79.delItem(j - 1);
                        z1.z120.delItem(j - 1);
                        z1.z61.delItem(j - 1);
                        z1.z184.delItem(j - 1);
                        this.z26 = 0.0;
                        b = true;
                    }
                }
                if (b) {
                    z1.z20 = "\n";
                    if (z1.z122.countItems() != 0) {
                        for (int k = 0; k < z1.z122.countItems(); ++k) {
                            z1.z20 = String.valueOf(z1.z20).concat(String.valueOf(this.z101.getItem(k + 1)));
                            if (k != z1.z122.countItems() - 1) {
                                z1.z20 = String.valueOf(z1.z20).concat(String.valueOf("\n"));
                            }
                        }
                        this.calc();
                    }
                    else {
                        this.clear();
                    }
                }
            }
            if (event.target == this.z21) {
                this.clear();
            }
        }
        if (event.id == 1001 && (event.target == this.z133 || event.target == this.z85)) {
            if (this.precheckout()) {
                this.z72.show(this, "secure_form");
            }
            return super.handleEvent(event);
        }
        if (event.id == 1001 && (event.target == this.z97 || event.target == this.z84 || event.target == this.z132 || event.target == this.z188 || event.target == this.z8 || event.target == this.z5 || event.target == this.z148)) {
            if ((event.target == this.z84 || event.target == this.z132) && !this.z6()) {
                return super.handleEvent(event);
            }
            if (this.z187 && event.target == this.z188) {
                if (this.z164) {
                    this.z50 = "post_worldpay";
                }
                else {
                    this.z50 = "get_worldpay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z81 && event.target == this.z188) {
                if (this.z164) {
                    this.z50 = "post_new_worldpay";
                }
                else {
                    this.z50 = "get_new_worldpay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z83 && event.target == this.z84) {
                if (this.z164) {
                    this.z50 = "get_nochex";
                }
                else {
                    this.z50 = "get_nochex";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z131 && event.target == this.z132) {
                if (this.z164) {
                    this.z50 = "get_secpay";
                }
                else {
                    this.z50 = "get_secpay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z147 && event.target == this.z148) {
                this.z50 = "get_secure";
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z7 && event.target == this.z8) {
                if (this.z164) {
                    this.z50 = "post_barclay";
                }
                else {
                    this.z50 = "get_barclay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z4 && event.target == this.z5) {
                if (this.z164) {
                    this.z50 = "post_authorize";
                }
                else {
                    this.z50 = "get_authorize";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (event.target == this.z97 && this.z164) {
                this.z186 = this.z129;
                this.z50 = "post_html";
                this.checkout();
                return super.handleEvent(event);
            }
            if (event.target == this.z97 && !this.z164) {
                this.z186 = this.z129;
                this.z50 = "get_html";
                this.checkout();
                return super.handleEvent(event);
            }
            return super.handleEvent(event);
        }
        else {
            if (event.id == 1001 && event.target == this.z1) {
                this.buy_item();
                return super.handleEvent(event);
            }
            if (event.id == 1001 && event.target == this.z96 && this.z106) {
                final String concat = String.valueOf(String.valueOf(this.z110).concat(String.valueOf("?display=1&business="))).concat(String.valueOf(URLEncoder.encode(this.z111)));
                try {
                    final URL url = new URL(concat);
                    if (!this.javascript) {
                        if (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") != -1) {
                            this.getAppletContext().showDocument(url, "win");
                        }
                        else {
                            this.getAppletContext().showDocument(url);
                        }
                    }
                    else {
                        this.send_order = true;
                        this.sent_order_1 = 1;
                        this.shop_pl_url = concat;
                    }
                }
                catch (Exception ex3) {}
                return super.handleEvent(event);
            }
            if ((event.id == 402 || event.id == 1001) && (event.target == this.z96 || event.target == this.z1 || event.target == this.z156 || event.target == this.z140 || event.target == this.z21)) {
                this.calc();
            }
            if (event.id == 1001 && (event.target == this.z124 || event.target == this.z172)) {
                this.z91.setText("                                                       ");
                this.z8();
            }
            if (event.id == 1001 && event.target == this.z144) {
                if (this.z144.getSelectedIndex() == 0) {
                    this.z3(this.z146 = 1);
                    z1.z75.reshape(10, z1.z74, z1.z71, z1.z192);
                    this.z145 = 1;
                    z1.z130.setText(z1(this.z87, 7));
                }
                if (this.z144.getSelectedIndex() == 1) {
                    this.z146 = 2;
                    this.z3(3);
                    z1.z75.reshape(10, z1.z74, z1.z71, z1.z192);
                    this.z145 = 3;
                    z1.z130.setText(z1(this.z87, 8));
                }
                if (this.z144.getSelectedIndex() == 2) {
                    this.z146 = 3;
                    this.z3(2);
                    z1.z75.reshape(10, z1.z74, z1.z71, z1.z192);
                    this.z145 = 2;
                    z1.z130.setText(z1(this.z87, 9));
                }
            }
            if (event.id == 402 && event.target == z1.tf && !z1.tf.getText().equals("")) {
                if (z1.z16.getSelectedIndex() > 1 && !this.z6.isAlive()) {
                    z1.z16.select(0);
                    z1.z15.select(0);
                    this.show_category(z1.z16.getSelectedItem());
                    super.handleEvent(event);
                }
                find(z1.tf.getText().trim());
                if (!this.z6.isAlive()) {
                    z1.z130.setText("Search Index");
                }
            }
            return super.handleEvent(event);
        }
    }
    
    private String z7(String s) {
        int index;
        String substring;
        for (int n = 0; s.indexOf(38, n) != -1; s = String.valueOf(s).concat(String.valueOf(" ")), substring = s.substring(index + 1), s = s.substring(0, index), s = String.valueOf(String.valueOf(s).concat(String.valueOf("&amp;"))).concat(String.valueOf(substring)).trim(), n = index + 6) {
            index = s.indexOf(38, n);
        }
        while (s.indexOf(60, 0) != -1) {
            final int index2 = s.indexOf(60, 0);
            s = String.valueOf(s).concat(String.valueOf(" "));
            final String substring2 = s.substring(index2 + 1);
            s = s.substring(0, index2);
            s = String.valueOf(String.valueOf(s).concat(String.valueOf("&lt;"))).concat(String.valueOf(substring2)).trim();
        }
        while (s.indexOf(62, 0) != -1) {
            final int index3 = s.indexOf(62, 0);
            s = String.valueOf(s).concat(String.valueOf(" "));
            final String substring3 = s.substring(index3 + 1);
            s = s.substring(0, index3);
            s = String.valueOf(String.valueOf(s).concat(String.valueOf("&rt;"))).concat(String.valueOf(substring3)).trim();
        }
        while (s.indexOf(34, 0) != -1) {
            final int index4 = s.indexOf(34, 0);
            s = String.valueOf(s).concat(String.valueOf(" "));
            final String substring4 = s.substring(index4 + 1);
            s = s.substring(0, index4);
            s = String.valueOf(String.valueOf(s).concat(String.valueOf("&quot;"))).concat(String.valueOf(substring4)).trim();
        }
        return s;
    }
    
    public void init() {
        System.out.println("Online Store 3.60 Copyright Web Design 2002");
        z1.z152 = false;
        z1.z77 = new List();
        z1.z76 = new List();
        z1.tf.setText("");
        this.z91.setFont(new Font("Courier", 1, 12));
        z1.z177 = String.valueOf(String.valueOf(String.valueOf(this.getCodeBase().getProtocol()).concat(String.valueOf("://"))).concat(String.valueOf(this.getCodeBase().getHost()))).concat(String.valueOf(this.getCodeBase().getFile()));
        this.z102.reshape(5, 0, 800, 32);
        z1.z15 = new Choice();
        z1.z16 = new List();
        z1.z116 = new List();
        z1.z185 = new List();
        z1.z137 = new List();
        z1.z75 = new z4();
        z1.z181 = new Scrollbar(1);
        z1.z163 = new Scrollbar(1);
        z1.z112 = null;
        z1.z48 = true;
        if (this.getCodeBase().getProtocol().toLowerCase().equals("http")) {
            this.aol_cache = "?";
        }
        this.z38 = System.getProperty("line.separator");
        final Date date = new Date();
        z1.z57 = new Color(Integer.parseInt(this.getParameter("highlight")));
        if (this.getParameter("thumbnail_height") != null) {
            z1.z169 = Integer.parseInt(this.getParameter("thumbnail_height"));
        }
        if (this.getParameter("secpay_user_name") != null) {
            this.z135 = this.getParameter("secpay_user_name");
        }
        if (this.getParameter("net_url") != null) {
            this.z80 = this.getParameter("net_url");
        }
        if (this.getParameter("paypal") != null && this.getParameter("method").indexOf("PayPal") != -1) {
            this.z106 = true;
        }
        this.z78 = this.getParameter("method");
        if (this.z78.indexOf("ePDQ") != -1) {
            this.z7 = true;
        }
        if (this.getParameter("business_name") != null) {
            this.z111 = this.getParameter("business_name");
        }
        if (this.getParameter("shipping_address") != null) {
            this.z109 = this.getParameter("shipping_address");
        }
        if (this.getParameter("logo") != null) {
            this.z107 = this.getParameter("logo");
        }
        if (this.getParameter("successful") != null) {
            this.z154 = this.getParameter("successful");
        }
        if (this.getParameter("cancel_payment") != null) {
            this.z13 = this.getParameter("cancel_payment");
        }
        if (this.getParameter("note") != null) {
            this.z108 = this.getParameter("note");
        }
        z1.z153 = new Color(Integer.parseInt(this.getParameter("subcat")));
        if (this.getParameter("form_html") != null && this.getParameter("form_html").equals("Yes")) {
            this.z51 = true;
        }
        z1.z115 = Toolkit.getDefaultToolkit().getFontMetrics(new Font("Helvetica", 0, this.z49)).getHeight();
        z1.z75.point_height = z1.z115;
        if (this.getParameter("temp_files").equals("Yes")) {
            this.z164 = true;
        }
        else {
            this.z164 = false;
        }
        this.z50 = this.getParameter("form");
        if (this.getParameter("vat_post").equals("Yes")) {
            this.z180 = true;
        }
        else {
            this.z180 = false;
        }
        this.z182 = this.getParameter("buttons");
        this.z36 = this.getParameter("error");
        this.z126 = this.getParameter("status");
        this.z87 = this.getParameter("other");
        z1.z29 = this.getParameter("currency");
        this.z188.setLabel(z1(this.z182, 5));
        z1.z130 = new Label(String.valueOf("  ").concat(String.valueOf(z1(this.z87, 7))), 0);
        z1.z23 = z1(this.z36, 4);
        this.setLayout(this.z72 = new CardLayout());
        final String parameter = this.getParameter("background");
        if (this.getParameter("cat_type") != null) {
            if (this.getParameter("cat_type").equals("Show")) {
                this.z142 = true;
            }
            if (this.getParameter("cat_type").indexOf("Sort") != -1) {
                this.z142 = true;
            }
            if (this.getParameter("cat_type").indexOf("Search") != -1) {
                z1.z25 = true;
                this.z142 = false;
            }
        }
        this.z159 = this.getParameter("tax_string");
        z1.z65 = Color.black;
        if (parameter.equals("Black")) {
            z1.z65 = Color.white;
        }
        if (parameter.equals("Blue")) {
            z1.z65 = Color.white;
        }
        if (parameter.equals("Red")) {
            z1.z65 = Color.white;
        }
        z1.z104 = new Color(Integer.parseInt(this.getParameter("color")));
        z1.z191 = this.size().width / 2 - 10;
        z1.z192 = this.size().height - z1.z74 - 10;
        z1.z70 = z1.z192;
        z1.z71 = z1.z191;
        z1.z193 = this.size().height - z1.z192 - 5;
        z1.z114 = Math.abs(this.size().width - z1.z191 - 30);
        z1.z14 = z1.z191 + 20;
        z1.z167 = this.size().width - z1.z14 - 10;
        z1.z105 = new Color(Integer.parseInt(this.getParameter("item_details")));
        z1.z66 = new Color(Integer.parseInt(this.getParameter("item_text")));
        z1.z168.setBackground(z1.z104);
        z1.z63 = new Color(Integer.parseInt(this.getParameter("index")));
        z1.z75.setBackground(z1.z63);
        z1.z168.setForeground(z1.z65);
        z1.z64 = new Color(Integer.parseInt(this.getParameter("index_text")));
        z1.z75.setForeground(z1.z64);
        this.z9 = new Color(Integer.parseInt(this.getParameter("button_back")));
        this.z11 = new Color(Integer.parseInt(this.getParameter("button_text")));
        this.z150 = this.getParameter("cheque");
        this.z186 = this.getParameter("staging");
        if (this.getParameter("store_name") != null) {
            this.z190 = this.getParameter("store_name").toUpperCase();
        }
        if (this.z78.indexOf("Payment") == -1) {
            this.z3 = true;
        }
        if (this.z78.indexOf("Worldpay") != -1 && this.z78.indexOf("Junior") == -1) {
            this.z187 = true;
        }
        if (this.z78.indexOf("Authorize") != -1) {
            this.z4 = true;
        }
        if (this.z78.indexOf("Junior") != -1) {
            this.z81 = true;
        }
        if (this.z78.indexOf("NOCHEX") != -1) {
            this.z83 = true;
        }
        if (this.z78.indexOf("SecPay") != -1) {
            this.z131 = true;
        }
        if (this.z78.indexOf("SecPay Test") != -1) {
            this.z134 = true;
        }
        if (this.z78.indexOf("Secure Trading") != -1) {
            this.z147 = true;
        }
        this.z149 = this.getParameter("st_no");
        final Color white = Color.white;
        z1.tf.setBackground(white);
        this.z86.setBackground(white);
        z1.z15.setBackground(white);
        z1.z16.setBackground(white);
        final Color black = Color.black;
        z1.tf.setForeground(black);
        this.z86.setForeground(black);
        z1.z16.setForeground(black);
        if (this.getParameter("script_dir") != null) {
            this.z129 = this.getParameter("script_dir");
        }
        this.z78 = this.getParameter("method");
        this.z41.add(this.z40);
        this.add("error", this.z41);
        this.z40.reshape(0, 0, 90, 108);
        if (this.getParameter("CAT_1") != null) {
            final String z1 = z1(this.z36, 5);
            z1.z15.addItem(z1);
            z1.z16.addItem(z1);
            String s;
            for (int n = 1; (s = this.getParameter(String.valueOf("CAT_").concat(String.valueOf(n)))) != null; ++n) {
                final int index = s.indexOf(46);
                if (index != -1) {
                    s = s.substring(0, index);
                }
                z1.z15.addItem(s.replace('_', ' '));
                z1.z16.addItem(s.replace('_', ' '));
            }
            z1.z15.select(1);
            z1.z16.select(1);
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(z1.z55);
            this.getAppletContext().showStatus(z1(this.z87, 1));
            z1.z137.addItem("Loading Data, Please Wait.|||||||||0||||");
            z1.z185.addItem("a");
            z1.z185.select(0);
            z1.z137.select(0);
            this.z96.setLabel(z1(this.z182, 3));
            this.z96.setForeground(this.z11);
            this.z96.setBackground(this.z9);
            this.z1.setLabel(z1(this.z182, 2));
            this.z1.setForeground(this.z11);
            this.z1.setBackground(this.z9);
            this.z172.setLabel(z1(this.z182, 4));
            this.z172.setForeground(this.z11);
            this.z172.setBackground(this.z9);
            if (this.getParameter("POST_1") != null) {
                this.z117 = true;
                int n2 = 1;
                int n3 = 0;
                String s2 = "";
                this.z140.addItem(String.valueOf(String.valueOf(z1(this.z87, 4)).concat(String.valueOf(" "))).concat(String.valueOf(z1(this.z182, 12))));
                String parameter2;
                while ((parameter2 = this.getParameter(String.valueOf("POST_").concat(String.valueOf(n2)))) != null) {
                    final String z2 = z1(parameter2, 1);
                    if (!s2.equals(z2)) {
                        this.z140.addItem(z2);
                        ++n3;
                        s2 = z2;
                    }
                    this.z118.addItem(parameter2);
                    ++n2;
                }
            }
            else {
                this.z117 = false;
                this.z140.addItem("Standard");
                this.z118.addItem("Standard,$0+,$0");
            }
            if (this.z140.countItems() == 2) {
                this.z140.select(1);
            }
            if (this.z140.countItems() > 2) {
                this.z140.select(0);
            }
            this.z156.setBackground(z1.z105);
            this.z156.setForeground(z1.z66);
            this.z155 = new Checkbox[10];
            this.z156.addItem(String.valueOf(String.valueOf(z1(this.z87, 4)).concat(String.valueOf(" "))).concat(String.valueOf(this.z159)));
            while (this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z157 * 2 + 1))) != null && this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z157 * 2 + 2))) != null) {
                this.z155[this.z157] = new Checkbox(this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z157 * 2 + 1))), this.z161, false);
                this.z158[this.z157] = this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z157 * 2 + 2)));
                this.z156.addItem(this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z157 * 2 + 1))));
                ++this.z157;
            }
            if (this.z156.countItems() == 2) {
                this.z156.select(1);
            }
            if (this.z156.countItems() > 2) {
                this.z156.select(0);
            }
            if (this.z157 > 0) {
                this.z162 = true;
                if (this.z157 == 1) {
                    this.z155[0].setState(true);
                }
            }
            else {
                for (int i = 0; i < 10; ++i) {
                    this.z155[i] = new Checkbox("error", this.z161, false);
                    this.z158[i] = "0";
                }
                this.z162 = false;
            }
            z1.z53 = "||GBP £||||||||||v3-ssc";
            final String concat = String.valueOf(z1.z53).concat(String.valueOf("|end|"));
            int j = 1;
            int n4 = -1;
            while (j != 0) {
                ++n4;
                if (z0(concat, n4 + 1).equals("end")) {
                    j = 0;
                }
            }
            z1.z82 = n4;
            z1.z46 = n4;
            z1.z171 = new String[z1.z82];
            for (int k = 0; k < z1.z82; ++k) {
                z1.z171[k] = z0(z1.z53, k + 1);
            }
            (this.z144 = new Choice()).setForeground(Color.black);
            this.z144.setBackground(Color.white);
            this.z144.addItem(String.valueOf(z1(this.z126, 3)).concat(String.valueOf("Items")));
            this.z144.addItem(String.valueOf(z1(this.z126, 3)).concat(String.valueOf("Prices")));
            this.z172.setFont(z1.z54);
            this.setBackground(z1.z104);
            this.setForeground(z1.z65);
            this.z100 = new Panel();
            this.z136 = new Panel();
            this.z1();
            this.z2();
            final Panel panel = new Panel();
            panel.setLayout(null);
            final TextArea textArea = new TextArea();
            this.z176 = new Button("Please re-order to continue");
            textArea.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.z38).concat(String.valueOf(this.z38))).concat(String.valueOf("The evaluation period for the shopping cart has expired"))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38))).concat(String.valueOf("The full version can be ordered"))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38))).concat(String.valueOf(this.z38))).concat(String.valueOf("from http://www.online-store.co.uk")));
            panel.add(textArea);
            textArea.reshape(100, 20, 400, 200);
            this.z176.reshape(190, 240, 220, 28);
            this.z176.setFont(z1.z54);
            this.add("aa", z1.z0);
            this.add("order_form", this.z100);
            this.add("secure_form", this.z136);
            this.add("start", panel);
            final Date date2 = new Date();
            this.z72.show(this, "aa");
            this.z35 = false;
            if (date2.getYear() > 103) {
                this.z35 = true;
            }
            if (date2.getYear() == 103) {
                if (!this.z35 && date2.getMonth() > 2) {
                    this.z35 = true;
                }
                if (!this.z35 && date2.getMonth() == 2 && date2.getDate() > 3) {
                    this.z35 = true;
                }
            }
            this.z35 = false;
            if (this.z35) {
                this.z72.show(this, "start");
            }
            this.z3();
            (this.z6 = new z8(z1.z15.getSelectedItem())).start();
            z1.z165 = z1.z74 + z1.z169 + 10;
            z1.z166 = this.size().height - z1.z165 - 10;
            z1.z181.reshape(z1.z14 + z1.z167 - z1.z127, z1.z165, z1.z127, z1.z166);
            z1.z168.reshape(z1.z14, z1.z165, z1.z167 - z1.z127, z1.z166);
            z1.z181.setValues(0, z1.z192 / fontMetrics.getHeight(), 0, (z1.z192 + fontMetrics.getHeight()) / fontMetrics.getHeight());
            z1.z75.reshape(10, z1.z74, z1.z71, z1.z192);
            z1.z163.setValues(0, 1, 0, z1.z185.countItems());
            z1.z163.reshape(10 + z1.z191 - z1.z127, z1.z74, z1.z127, z1.z192);
            z1.z112.reshape(z1.z14, z1.z74, z1.z167, z1.z169);
            z1.z112.setBackground(z1.z104);
            z1.z15.select(0);
            z1.z16.select(0);
            if (z1.alt) {
                try {
                    this.z59 = new Socket(this.getDocumentBase().getHost(), 80).getLocalAddress().getHostAddress();
                }
                catch (Exception ex) {
                    this.z69 = ex.toString();
                }
            }
            return;
        }
        this.z40.setText("shop.html error ,no data files");
    }
    
    public boolean keyDown(final Event event, final int n) {
        return event.id == 401 && event.target == this.z86 && (event.target != this.z86 || ((n <= 47 || n >= 59) && n != 8));
    }
    
    private String z2(String s, final int n) {
        int n2 = 0;
        s = String.valueOf(s).concat(String.valueOf("\n\n\n\n\n\n\n\n\n"));
        for (int i = 1; i < n; ++i) {
            n2 = s.indexOf(10, n2) + 1;
        }
        s = s.substring(n2, s.indexOf(10, n2));
        return s;
    }
    
    private String z8(final String s) {
        final String s2 = "<HTML>\n<HEAD>\n<TITLE>Shopping Cart</TITLE>\n</HEAD>\n<BODY onLoad = \"focus();document.postform.submit();\" ><p align=\"center\"><font color=\"#0000FF\"><strong><big><big><big>Connecting to the Server: Please Wait</big></big></big></strong></font><br><br>";
        for (int i = 0; i < this.z157; ++i) {
            if (this.z155[i].getState()) {
                final String s3 = this.z158[i];
            }
        }
        String s4 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z7(this.z186)))).concat(String.valueOf("\" method=\"post\">"));
        if (!this.javascript) {
            s4 = String.valueOf(s4).concat(String.valueOf("<INPUT type=\"submit\" name=\"submit_button\" value=\"Send Order to Server\"><br><br>"));
        }
        if (this.z50.equals("post_barclay")) {
            s4 = String.valueOf(s4).concat(String.valueOf("\r\n<input type=\"hidden\" name = \"barclay\" value = \"true\">\r\n"));
        }
        String s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf("<input type=\"hidden\" name = \"cname\" value = \""))).concat(String.valueOf(z1(z1.z29, 2)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"store\" value = \""))).concat(String.valueOf(this.z190))).concat(String.valueOf("\">\r\n"));
        int n = 0;
        for (int j = 0; j < z1.z122.countItems(); ++j) {
            ++n;
            String item;
            if (z1.z61.getItem(j).trim().equals("")) {
                item = "";
            }
            else {
                item = z1.z61.getItem(j);
            }
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"code"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(item)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"desc"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(z1.z79.getItem(j))))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cur"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(z1(z1.z29, 2)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cost"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(z1.z120.getItem(j))))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"qty"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(z1.z122.getItem(j))))).concat(String.valueOf("\">\r\n"));
        }
        if (this.z117) {
            ++n;
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"code"))).concat(String.valueOf(n))).concat(String.valueOf("\" value =  \"~WP_PNP\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"desc"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cost"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(this.z0(this.z26))))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"qty"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \"1\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cur"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(z1(z1.z29, 2))))).concat(String.valueOf("\">\r\n"));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            ++n;
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"code"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \"~WP_TAX\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"desc"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z156.getSelectedItem()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cost"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z160))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"qty"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \"1\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cur"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z7(z1(z1.z29, 2))))).concat(String.valueOf("\">\r\n"));
        }
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"max\"   value = \""))).concat(String.valueOf(n))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"Action\"   value = \"processform\">\r\n"))).concat(String.valueOf("</p></FORM>"))).concat(String.valueOf("</BODY></HTML>"));
    }
    
    private String z9(final String s) {
        String s2 = "";
        if (this.z51) {
            try {
                final DataInputStream dataInputStream = new DataInputStream(new URL(this.getCodeBase(), "authorize_form.html").openConnection().getInputStream());
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    s2 = String.valueOf(String.valueOf(s2).concat(String.valueOf(line))).concat(String.valueOf("\n"));
                }
                dataInputStream.close();
            }
            catch (Exception ex) {
                System.out.println(ex);
                s2 = "";
            }
            if (s2.length() > 10) {
                final int index = s2.indexOf("</form>");
                s2.substring(index + 7);
                s2 = s2.substring(0, index);
            }
        }
        if (!this.z51) {
            s2 = String.valueOf(String.valueOf(String.valueOf("<HTML>\n<HEAD>\n<TITLE>Shopping Cart</TITLE>\n</HEAD>\n<BODY onLoad = \"focus();document.postform.submit();\" ><p align=\"center\"><font color=\"#0000FF\"><strong><big><big><big>Connecting to the Server: Please Wait</big></big></big></strong></font><br><br>").concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z7(this.z129)))).concat(String.valueOf("\" method=\"post\">"));
        }
        for (int i = 0; i < this.z157; ++i) {
            if (this.z155[i].getState()) {
                final String s3 = this.z158[i];
            }
        }
        final Date date = new Date();
        if (!this.javascript) {
            s2 = String.valueOf(s2).concat(String.valueOf("<INPUT type=\"submit\" name=\"submit_button\" value=\"Send Order to Server\"><br><br>"));
        }
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<input type=\"hidden\" name = \"x_Version\" value = \"3.1\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Amount\" value = \""))).concat(String.valueOf(this.z0(this.z52)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Invoice_Num\" value = \""))).concat(String.valueOf(date.getTime()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Show_Form\" value = \""))).concat(String.valueOf("PAYMENT_FORM"))).concat(String.valueOf("\">\r\n"));
        String s4 = "";
        for (int j = 0; j < z1.z122.countItems(); ++j) {
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z7(z1.z61.getItem(j))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z7(z1.z79.getItem(j))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z122.getItem(j)))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z120.getItem(j)))).concat(String.valueOf(")"))).concat(String.valueOf("<br>\n"));
        }
        String s5 = "";
        for (int k = 0; k < z1.z122.countItems(); ++k) {
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z7(z1.z61.getItem(k))))).concat(String.valueOf(" "))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z7(z1.z122.getItem(k))))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z120.getItem(k)))).concat(String.valueOf(")"))).concat(String.valueOf("<br>\n"));
        }
        int n = 0;
        if (this.z117) {
            ++n;
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("<br>\n"));
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("<br>\n"));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z159))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("<br>\n"));
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z159))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("<br>\n"));
        }
        String trim;
        int index2;
        String concat2;
        for (trim = s4; trim.indexOf("<br>", 0) != -1; trim = String.valueOf(concat2.substring(0, index2)).concat(String.valueOf(concat2.substring(index2 + 4))).trim()) {
            index2 = trim.indexOf("<br>", 0);
            concat2 = String.valueOf(trim).concat(String.valueOf("       "));
        }
        final String concat3 = String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("<input type=\"hidden\" name = \"Description\" value = \""))).concat(String.valueOf(trim))).concat(String.valueOf("\">\n"));
        String s6;
        if (s4.length() < 256) {
            s6 = String.valueOf(String.valueOf(String.valueOf(concat3).concat(String.valueOf("<input type=\"hidden\" name = \"x_Description\" value = \""))).concat(String.valueOf(s4))).concat(String.valueOf("\">\n"));
        }
        else if (s5.length() < 256) {
            s6 = String.valueOf(String.valueOf(String.valueOf(concat3).concat(String.valueOf("<input type=\"hidden\" name = \"x_Description\" value = \""))).concat(String.valueOf(s5))).concat(String.valueOf("\">\n"));
        }
        else {
            s6 = String.valueOf(String.valueOf(String.valueOf(concat3).concat(String.valueOf("<input type=\"hidden\" name = \"x_Description\" value = \""))).concat(String.valueOf("Internet Order"))).concat(String.valueOf("\">\n"));
        }
        String s7 = String.valueOf(s6).concat(String.valueOf("<input type=\"hidden\" name = \"Action\"   value = \"processform\">\r\n"));
        if (!this.z51) {
            s7 = String.valueOf(String.valueOf(s7).concat(String.valueOf("</p></FORM>"))).concat(String.valueOf("</BODY></HTML>"));
        }
        return s7;
    }
    
    private String z10(final String s) {
        final String s2 = "<HTML>\n<HEAD>\n<TITLE>Shopping Cart</TITLE>\n</HEAD>\n<BODY onLoad = \"focus();document.postform.submit();\" ><p align=\"center\"><font color=\"#0000FF\"><strong><big><big><big>Connecting to the Server: Please Wait</big></big></big></strong></font><br><br>";
        String s3;
        if (this.z189) {
            s3 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z7("https://select.worldpay.com/wcc/transaction")))).concat(String.valueOf("\" method=\"post\">"));
        }
        else {
            s3 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z7("https://select.worldpay.com/wcc/purchase")))).concat(String.valueOf("\" method=\"post\">"));
        }
        for (int i = 0; i < this.z157; ++i) {
            if (this.z155[i].getState()) {
                final String s4 = this.z158[i];
            }
        }
        final Date date = new Date();
        if (!this.javascript) {
            s3 = String.valueOf(s3).concat(String.valueOf("<INPUT type=\"submit\" name=\"submit_button\" value=\"Send Order to Server\"><br><br>"));
        }
        if (this.z189) {
            s3 = String.valueOf(s3).concat(String.valueOf("<input type=\"hidden\" name = \"withDelivery\" value = \"\">\r\n"));
        }
        String s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("<input type=\"hidden\" name = \"instId\" value = \""))).concat(String.valueOf(this.z190))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"amount\" value = \""))).concat(String.valueOf(this.z0(this.z52)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cartId\" value = \""))).concat(String.valueOf(date.getTime()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"currency\" value = \""))).concat(String.valueOf(z1(z1.z29, 2)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"testMode\" value = \""))).concat(String.valueOf(this.getParameter("test_mode")))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"M_recipient\" value = \""))).concat(String.valueOf(this.getParameter("E_Mail_Address")))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"M_subject\" value = \""))).concat(String.valueOf("Worlday Order"))).concat(String.valueOf("\">\r\n"));
        if (this.getParameter("auth") != null) {
            s5 = String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"authMode\" value = \""))).concat(String.valueOf(this.getParameter("auth")))).concat(String.valueOf("\">\r\n"));
        }
        if (this.getParameter("acc_id") != null && !this.getParameter("acc_id").trim().equals("")) {
            s5 = String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"accId1\" value = \""))).concat(String.valueOf(this.getParameter("acc_id")))).concat(String.valueOf("\">\r\n"));
        }
        String s6 = "";
        for (int j = 0; j < z1.z122.countItems(); ++j) {
            s6 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s6).concat(String.valueOf(this.z7(z1.z61.getItem(j))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z7(z1.z79.getItem(j))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z122.getItem(j)))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z120.getItem(j)))).concat(String.valueOf("<br>\n"));
        }
        String s7 = "";
        for (int k = 0; k < z1.z122.countItems(); ++k) {
            s7 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s7).concat(String.valueOf(this.z7(z1.z61.getItem(k))))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z7(z1.z122.getItem(k))))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z120.getItem(k)))).concat(String.valueOf("<br>\n"));
        }
        int n = 0;
        if (this.z117) {
            ++n;
            s6 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s6).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("\n"));
            s7 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s7).concat(String.valueOf(this.z140.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z26)))).concat(String.valueOf("\n"));
        }
        if (this.z162 && this.z156.getSelectedIndex() > 0) {
            s6 = String.valueOf(String.valueOf(String.valueOf(s6).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("<br>\n"));
            s7 = String.valueOf(String.valueOf(String.valueOf(s7).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z160)))).concat(String.valueOf("<br>\n"));
        }
        String s8;
        if (s6.length() < 256) {
            s8 = String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"desc\" value = \""))).concat(String.valueOf(s6))).concat(String.valueOf("\">\n"));
        }
        else if (s7.length() < 256) {
            s8 = String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"desc\" value = \""))).concat(String.valueOf(s7))).concat(String.valueOf("\">\n"));
        }
        else {
            s8 = String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"desc\" value = \"Details on Email Receipt\">\n"));
        }
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("<input type=\"hidden\" name = \"MC_description\" value = \""))).concat(String.valueOf(s6))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"Action\"   value = \"processform\">\r\n"))).concat(String.valueOf("</p></FORM>"))).concat(String.valueOf("</BODY></HTML>"));
    }
    
    private String z0(double n) {
        n = Math.round(n * 100);
        n /= 100;
        final String string = Double.toString(n);
        String s;
        if (string.indexOf(".", 0) == -1) {
            s = String.valueOf(string).concat(String.valueOf(".00"));
        }
        else {
            final String concat = String.valueOf(string).concat(String.valueOf("000"));
            s = concat.substring(0, concat.indexOf(".", 0) + 3);
        }
        return s;
    }
    
    private boolean z6() {
        final String s = "Error: ";
        this.z94.setForeground(z1.z66);
        this.z94.setBackground(z1.z105);
        this.z94.setFont(z1.z55);
        this.z88.setForeground(z1.z66);
        this.z88.setBackground(z1.z105);
        this.z88.setFont(z1.z55);
        this.z89.setForeground(z1.z66);
        this.z89.setBackground(z1.z105);
        this.z89.setFont(z1.z55);
        this.z103.setForeground(z1.z66);
        this.z103.setBackground(z1.z105);
        this.z103.setFont(z1.z55);
        this.z90.setForeground(z1.z66);
        this.z90.setBackground(z1.z105);
        this.z90.setFont(z1.z55);
        if (this.z94.getText().equals("")) {
            this.z92.setText(String.valueOf(s).concat(String.valueOf("No Name")));
            this.z94.setForeground(Color.white);
            this.z94.setBackground(Color.red);
            this.z94.setFont(z1.z54);
            return false;
        }
        if (this.z88.getText().equals("")) {
            this.z92.setText(String.valueOf(s).concat(String.valueOf("No Address")));
            this.z88.setForeground(Color.white);
            this.z88.setBackground(Color.red);
            this.z88.setFont(z1.z54);
            return false;
        }
        if (this.z103.getText().equals("")) {
            this.z92.setText(String.valueOf(s).concat(String.valueOf("No Telephone Number")));
            this.z103.setForeground(Color.white);
            this.z103.setBackground(Color.red);
            this.z103.setFont(z1.z54);
            return false;
        }
        if (this.z90.getText().indexOf("@") == -1 && this.z90.getText().indexOf(".") == -1) {
            this.z92.setText(String.valueOf(s).concat(String.valueOf("in Email Address")));
            this.z90.setForeground(Color.white);
            this.z90.setBackground(Color.red);
            this.z90.setFont(z1.z54);
            return false;
        }
        return true;
    }
    
    public boolean precheckout() {
        final String concat = String.valueOf(z1(this.z182, 24)).concat(String.valueOf(": "));
        this.z91.setText("                                                       ");
        if (this.z162 && this.z156.getSelectedIndex() < 1) {
            this.z91.setText(this.z156.getItem(0));
            this.z156.setBackground(Color.red);
            this.z156.setForeground(Color.white);
            return false;
        }
        this.z156.setBackground(z1.z105);
        this.z156.setForeground(z1.z66);
        if (this.z117 && this.z140.getSelectedIndex() < 1) {
            this.z140.setBackground(Color.red);
            this.z140.setForeground(Color.white);
            this.z91.setText(this.z140.getItem(0));
            return false;
        }
        if (z1.z174 == 0) {
            this.z91.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z36, 2))));
            return false;
        }
        if (z1.z174 == 0) {
            this.z91.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z36, 2))));
            return false;
        }
        return true;
    }
    
    private boolean z7() {
        try {
            this.z138 = "";
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.z178.getInputStream(), 2048));
            String protocol = this.getCodeBase().getProtocol();
            String line;
            while ((line = dataInputStream.readLine()) != null && line.compareTo("end") != 0) {
                protocol = line;
            }
            dataInputStream.close();
            this.z91.setText(protocol);
            this.z138 = protocol;
            return true;
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
            return false;
        }
    }
    
    private void z11(final String s) {
        if (this.z6.isAlive()) {
            z1.z152 = true;
            try {
                this.z6.join(2000L);
                z1.z152 = false;
            }
            catch (Exception ex) {
                System.out.println(String.valueOf("Thread: ").concat(String.valueOf(ex)));
            }
        }
        (this.z6 = new z8(s)).start();
    }
    
    private void z8() {
        this.getAppletContext().showStatus("");
        this.z72.show(this, "aa");
        if (System.getProperty("java.version").indexOf("1.0") == -1) {
            this.z9();
        }
    }
    
    private String z12(final String s) {
        String concat = "";
        final int length = s.trim().length();
        if (s.indexOf("$") != -1 || s.indexOf("£") != -1) {
            int n = s.indexOf("$") + 1;
            if (n == 0) {
                n = s.indexOf("£") + 1;
            }
            for (int i = n; i < length; ++i) {
                final String substring = s.substring(i, i + 1);
                if (substring.equals("0") || substring.equals("1") || substring.equals("2") || substring.equals("3") || substring.equals("4") || substring.equals("5") || substring.equals("6") || substring.equals("7") || substring.equals("8") || substring.equals("9") || substring.equals(".")) {
                    concat = String.valueOf(concat).concat(String.valueOf(substring));
                }
                else {
                    i = length;
                }
            }
        }
        return concat;
    }
    
    private String z13(String s) {
        s = s.replace('\'', ' ');
        s = s.replace(';', ' ');
        s = s.replace('=', ' ');
        return s;
    }
    
    private boolean z14(final String s) {
        final Date date = new Date();
        date.toLocaleString();
        final Dimension dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        try {
            final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Action=").concat(String.valueOf(URLEncoder.encode(this.z78)))).concat(String.valueOf("&info="))).concat(String.valueOf(URLEncoder.encode(s)))).concat(String.valueOf("&java+version="))).concat(String.valueOf(URLEncoder.encode(System.getProperty("java.version"))))).concat(String.valueOf("&java+vendor="))).concat(String.valueOf(URLEncoder.encode(System.getProperty("java.vendor"))))).concat(String.valueOf("&java+class+version="))).concat(String.valueOf(URLEncoder.encode(System.getProperty("java.class.version"))))).concat(String.valueOf("&os+name="))).concat(String.valueOf(URLEncoder.encode(System.getProperty("os.name"))))).concat(String.valueOf("&os+arch="))).concat(String.valueOf(URLEncoder.encode(System.getProperty("os.arch"))))).concat(String.valueOf("&os+version="))).concat(String.valueOf(URLEncoder.encode(System.getProperty("os.version"))))).concat(String.valueOf("&time+zone="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(date.getTimezoneOffset())))))).concat(String.valueOf("&local+time="))).concat(String.valueOf(URLEncoder.encode(date.toLocaleString())))).concat(String.valueOf("&epoch="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(date.getTime())))))).concat(String.valueOf("&screen+width="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(dimension.width)))))).concat(String.valueOf("&screen+height="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(dimension.height)))))).concat(String.valueOf("&screen+resolution="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(Toolkit.getDefaultToolkit().getScreenResolution())))))).concat(String.valueOf("&local+ip+address="))).concat(String.valueOf(URLEncoder.encode(this.z59)));
            (this.z178 = new URL(this.z129).openConnection()).setUseCaches(false);
            this.z178.setDoOutput(true);
            this.z178.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            this.z178.setRequestProperty("content-length", String.valueOf(concat.length()));
            final DataOutputStream dataOutputStream = new DataOutputStream(this.z178.getOutputStream());
            dataOutputStream.writeBytes(concat);
            dataOutputStream.close();
            return true;
        }
        catch (Exception ex) {
            this.z138 = String.valueOf("URL Connection error: ").concat(String.valueOf(ex.toString()));
            return false;
        }
    }
    
    private void z2(final int n) {
        z1.z185.select(n);
    }
    
    private void z9() {
        this.z86.setText("1");
    }
    
    public void show_category(final String s) {
        if (!s.equals("")) {
            z1.z15.select(s);
        }
        z1.z75.dy = 0;
        z1.z163.setValue(0);
        final String z0 = z0(z1.z137.getItem(0), 13);
        for (int n = 0; n < z1.z77.countItems() && !z1.z77.getItem(n).trim().replace(' ', '_').equals(z0); ++n) {}
        this.z123 = false;
        this.z11(z1.z15.getSelectedItem().trim().replace(' ', '_'));
        if (this.z123) {
            this.z11(z1.z15.getSelectedItem().trim().replace(' ', '_'));
        }
        this.getAppletContext().showStatus("");
        this.z72.show(this, "aa");
    }
    
    private void z3(int z145) {
        z145 = 1;
        if (this.z146 == 2) {
            z145 = 3;
        }
        else if (this.z146 == 3) {
            z145 = 2;
        }
        final int countItems = z1.z137.countItems();
        this.z145 = z145;
        final String s = new String();
        String item = "";
        final int z146 = z5();
        if (z146 >= 0) {
            item = z1.z137.getItem(z146);
        }
        final List list = new List();
        list.addItem("");
        if (countItems > 2) {
            for (int i = 0; i < countItems; ++i) {
                final String concat = String.valueOf(z1.z137.getItem(i)).concat(String.valueOf("|"));
                int n = 0;
                for (int j = 1; j < z145; ++j) {
                    n = concat.indexOf(124, n) + 1;
                }
                list.addItem(concat.substring(n, concat.indexOf(124, n)).toUpperCase().trim(), i);
            }
            this.getAppletContext().showStatus(z1(this.z87, 2));
            for (int k = 0; k < countItems - 1; ++k) {
                int n2 = k;
                String item2 = list.getItem(k);
                for (int l = k + 1; l < countItems; ++l) {
                    final String item3 = list.getItem(l);
                    try {
                        if (new Double(item2) > new Double(item3)) {
                            n2 = l;
                            item2 = item3;
                        }
                    }
                    catch (NumberFormatException ex) {
                        if (item2.compareTo(item3) > 0) {
                            n2 = l;
                            item2 = item3;
                        }
                    }
                }
                if (k != n2) {
                    final String item4 = z1.z137.getItem(k);
                    z1.z137.replaceItem(z1.z137.getItem(n2), k);
                    z1.z137.replaceItem(item4, n2);
                    list.replaceItem(list.getItem(k), n2);
                }
            }
        }
        if (countItems > 1) {
            z1.z185.select(countItems - 1);
        }
        if (countItems == 1) {
            z1.z185.select(0);
        }
        z1.z185.addItem("", 0);
        z1.z185.delItem(0);
        z1.z185.setMultipleSelections(true);
        z1.z185.hide();
        if (z145 == 1) {
            for (int n3 = 0; n3 < countItems; ++n3) {
                z1.z185.replaceItem(z0(z1.z137.getItem(n3), z145), n3);
            }
        }
        else {
            for (int n4 = 0; n4 < countItems; ++n4) {
                z1.z185.replaceItem(z0(z1.z137.getItem(n4), z145), n4);
            }
        }
        z1.z185.setMultipleSelections(false);
        z1.z185.show();
        z1.z185.addItem("", 0);
        z1.z185.delItem(0);
        this.getAppletContext().showStatus("");
        if (z1.z185.getSelectedIndex() != -1) {
            z1.z185.deselect(z1.z185.getSelectedIndex());
        }
        if (z146 >= 0) {
            for (int n5 = 0; n5 < countItems; ++n5) {
                if (item.equals(z1.z137.getItem(n5))) {
                    this.z2(n5);
                }
            }
        }
        z0();
        z1.z185.makeVisible(z1.z185.getSelectedIndex());
    }
    
    private String z15(String concat) {
        String concat2 = "";
        int index = 0;
        concat = String.valueOf(concat).concat(String.valueOf(",jajwuthtfapow,,"));
        z1.z82 = 0;
        while (true) {
            final int n = index;
            String s;
            if (concat.indexOf(34, n) == n) {
                int i = 1;
                while (i != 0) {
                    index = concat.indexOf("\"", index + 1);
                    if (concat.substring(index + 1, index + 2).equals("\"")) {
                        ++index;
                    }
                    else {
                        i = 0;
                    }
                }
                s = concat.substring(n + 1, index);
                index += 2;
            }
            else {
                final int index2 = concat.indexOf(",", index);
                s = concat.substring(n, index2);
                index = index2 + 1;
            }
            if (s.equals("jajwuthtfapow")) {
                break;
            }
            concat2 = String.valueOf(String.valueOf(concat2).concat(String.valueOf(s))).concat(String.valueOf("|"));
            ++z1.z82;
        }
        String concat3 = concat2;
        int index3;
        String substring;
        for (int n2 = 0; concat3.indexOf("\"\"", n2) != -1; concat3 = String.valueOf(substring).concat(String.valueOf(concat3.substring(index3 + 1))), n2 = index3 + 1) {
            index3 = concat3.indexOf("\"\"", n2);
            if (index3 == 0) {
                substring = "";
            }
            else {
                substring = concat3.substring(0, index3);
            }
        }
        return concat3;
    }
    
    static void z10() {
        z1.z112.original = false;
        z1.z112.original = true;
        z1.z112.xx = z1.z114;
        z1.z112.yy = z1.z169;
        z1.image = z1(z1.z45[z1.z113], z1.z30);
        try {
            final URL url = new URL(String.valueOf(z1.z177).concat(String.valueOf("")));
            if (!z1.z45[z1.z113].trim().equals("") && !z1.z45[z1.z113].trim().equals("_small.jpg")) {
                z1.z62 = Toolkit.getDefaultToolkit().getImage(new URL(url, z1(z1.z45[z1.z113], z1.z30)));
                z1.z112.draw_picture(z1.z62, z1(z1.z45[z1.z113], z1.z30));
            }
        }
        catch (MalformedURLException ex) {}
        try {
            if (z1.z45[z1.z113].trim().equals("")) {
                if (z1.z165 != z1.z74) {
                    z1.z112.resize(0, 0);
                    z1.z165 = z1.z74;
                    z1.z166 = z1.z0.size().height - z1.z74 - 10;
                    z1.z168.reshape(z1.z14, z1.z74, z1.z167, z1.z166);
                }
            }
            else if (z1.z165 != z1.z74 + z1.z169 + 10) {
                z1.z165 = z1.z74 + z1.z169 + 10;
                z1.z166 = z1.z0.size().height - z1.z165 - 10;
                z1.z168.reshape(z1.z14, z1.z165, z1.z167, z1.z166);
                z1.z112.resize(z1.z167, z1.z169);
            }
        }
        catch (Exception ex2) {}
    }
    
    public z1() {
        this.z69 = "";
        this.z59 = "";
        this.z189 = false;
        this.z170 = "";
        this.z2 = false;
        this.z134 = false;
        this.z51 = false;
        this.z3 = false;
        this.z106 = false;
        this.z4 = false;
        this.z7 = false;
        this.z67 = false;
        this.z49 = 12;
        this.z101 = new List();
        this.z186 = "";
        this.z187 = false;
        this.z81 = false;
        this.z83 = false;
        this.z131 = false;
        this.z147 = false;
        this.z164 = true;
        this.z149 = "";
        this.z156 = new Choice();
        this.z52 = 0.0;
        this.z50 = "";
        this.z188 = new Button("");
        this.z84 = new Button("NOCHEX Secure Server");
        this.z85 = new Button("Pay using NOCHEX");
        this.z132 = new Button("SecPay Secure Order Form");
        this.z133 = new Button("Pay using Secpay");
        this.z8 = new Button("Pay with Credit Card");
        this.z5 = new Button("Pay by Credit Card via Authorize Net Secure Order Form");
        this.z148 = new Button("Pay by Credit Card via SecureTrading");
        this.z80 = "https://secure.authorize.net/gateway/transact.dll";
        this.z135 = "";
        this.z10 = 28;
        this.z35 = false;
        this.shop_pl_url = "";
        this.z121 = "";
        this.z180 = false;
        this.z150 = "";
        this.z190 = "";
        this.z110 = "https://www.paypal.com/cart";
        this.z111 = "";
        this.z109 = "";
        this.z108 = "";
        this.z107 = "";
        this.z154 = "";
        this.z13 = "";
        this.z160 = 0.0;
        this.z142 = false;
        this.enlarge = false;
        this.enlarge_1 = 0;
        this.javascript = false;
        this.javascript_1 = 0;
        this.send_order = false;
        this.sent_order_1 = 0;
        this.global_item_total = "";
        this.global_tax = 0.0;
        this.z157 = 0;
        this.z26 = 0.0;
        this.z182 = "";
        this.z146 = 1;
        this.z44 = 0;
        this.z176 = new Button("Click Here");
        this.z123 = false;
        this.z102 = new Panel();
        this.z39 = false;
        this.z37 = true;
        this.z27 = new Font("Courier", 0, 12);
        this.z138 = "";
        this.z129 = "http://no-script-dir/cgi-script.pl";
        this.z86 = new TextField("1", 3);
        this.z18 = new TextField("ww", z1.z47);
        this.z94 = new TextField("", z1.z47);
        this.z33 = new TextField("", z1.z47);
        this.z88 = new TextArea("");
        this.z31 = new TextArea("");
        this.z89 = new TextField("", 10);
        this.z32 = new TextField("", 10);
        this.z103 = new TextField("", z1.z47);
        this.z34 = new TextField("", z1.z47);
        this.z90 = new TextField("", z1.z47);
        this.z93 = new TextField("", 10);
        this.z179 = new TextArea("");
        this.z12 = new TextField("", z1.z47);
        this.z42 = new TextField("", 10);
        this.z43 = new TextField("", 10);
        this.z98 = new Button("order_button_2");
        this.z125 = new Button("Return to Shop");
        this.z173 = new Button("Return to Shop");
        this.z92 = new Label("                                                            ");
        this.z28 = false;
        this.z162 = false;
        this.z117 = false;
        this.z91 = new Label("                                                       ");
        this.z172 = new Button("");
        this.z1 = new Button("");
        this.z21 = new Button("");
        this.z22 = new Button("Clear Selected Items");
        this.z96 = new Button("");
        this.z124 = new Button("");
        this.z97 = new Button("");
        this.z145 = 1;
        this.z140 = new Choice();
        this.z118 = new Choice();
        this.z99 = "";
        this.z78 = "";
        this.z41 = new Panel();
        this.z40 = new TextArea();
        this.z161 = new CheckboxGroup();
        this.z139 = new CheckboxGroup();
        this.z158 = new String[10];
        this.aol_cache = "";
    }
}

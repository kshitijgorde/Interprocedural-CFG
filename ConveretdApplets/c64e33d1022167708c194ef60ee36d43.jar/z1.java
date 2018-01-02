import java.net.MalformedURLException;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
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
import java.awt.List;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Font;
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
    private TextField z16;
    static Choice z17;
    static String z18;
    private Button z19;
    private Button z20;
    static String z21;
    static Choice z22;
    static boolean z23;
    private double z24;
    private Font z25;
    private boolean z26;
    static String z27;
    static int z28;
    private TextArea z29;
    private TextField z30;
    private TextField z31;
    private TextField z32;
    private boolean z33;
    private String z34;
    private boolean z35;
    public boolean enlarge;
    private String z36;
    private boolean z37;
    private TextArea z38;
    private Panel z39;
    private TextField z40;
    private TextField z41;
    private int z42;
    static String[] z43;
    static int z44;
    static int z45;
    static boolean z46;
    private int z47;
    private String z48;
    private boolean z49;
    public String global_item_total;
    public double global_tax;
    private double z50;
    static String z51;
    static Font z52;
    static Font z53;
    static boolean z54;
    static Color z55;
    static Image z56;
    static int z57;
    static List z58;
    public static String image;
    static Image z59;
    static Color z60;
    static Color z61;
    static Color z62;
    static Color z63;
    private boolean z64;
    public boolean javascript;
    static int z65;
    static int z66;
    private CardLayout z67;
    static int z68;
    static int z69;
    static z3 z70;
    static List z71;
    static List z72;
    private String z73;
    static List z74;
    private String z75;
    private boolean z76;
    static int z77;
    private boolean z78;
    private Button z79;
    private Button z80;
    private TextField z81;
    private String z82;
    private TextArea z83;
    private TextField z84;
    private TextField z85;
    private Label z86;
    private Label z87;
    private TextField z88;
    private TextField z89;
    z5 z90;
    private Button z91;
    private Button z92;
    private Button z93;
    private String z94;
    private Panel z95;
    private List z96;
    Panel z97;
    private TextField z98;
    static Color z99;
    static Color z100;
    private boolean z101;
    private String z102;
    private String z103;
    private String z104;
    private String z105;
    private String z106;
    private String z107;
    private String z108;
    static z2 z109;
    static int z110;
    static int z111;
    private int z112;
    static List z113;
    private boolean z114;
    private Choice z115;
    static int z116;
    static List z117;
    private String z118;
    static List z119;
    private boolean z120;
    private Button z121;
    private Button z122;
    private String z123;
    static int z124;
    static int z125;
    private String z126;
    private Label z127;
    private boolean z128;
    private Button z129;
    private Button z130;
    private boolean z131;
    private String z132;
    private Panel z133;
    public boolean send_order;
    static List z134;
    private String z135;
    private CheckboxGroup z136;
    private Choice z137;
    public String shop_pl_url;
    static boolean z138;
    private boolean z139;
    static Choice z140;
    private Choice z141;
    private int z142;
    private int z143;
    private boolean z144;
    private Button z145;
    private String z146;
    private String z147;
    private z6 z148;
    static Color z149;
    private String z150;
    private Checkbox[] z151;
    private Choice z152;
    private int z153;
    private String[] z154;
    private String z155;
    private double z156;
    private CheckboxGroup z157;
    private boolean z158;
    static Scrollbar z159;
    private boolean z160;
    static int z161;
    static int z162;
    static int z163;
    static z4 z164;
    public TextField tf;
    static int z165;
    static String[] z166;
    private Button z167;
    private Button z168;
    static double z169;
    static double z170;
    private Button z171;
    static String z172;
    private URLConnection z173;
    private TextArea z174;
    private boolean z175;
    static Scrollbar z176;
    private String z177;
    static int z178;
    static List z179;
    static List z180;
    private String z181;
    private boolean z182;
    private Button z183;
    private String z184;
    static int z185;
    static int z186;
    static int z187;
    
    static {
        z1.z45 = 26;
        z1.z46 = true;
        z1.z71 = new List();
        z1.z72 = new List();
        z1.z23 = false;
        z1.z163 = 200;
        z1.z161 = 999;
        z1.z55 = Color.red;
        z1.z149 = Color.blue;
        z1.z21 = "";
        z1.z65 = 400;
        z1.z66 = 400;
        z1.z162 = 100;
        z1.z113 = new List();
        z1.z70 = new z3();
        z1.z124 = 16;
        z1.z28 = 1;
        z1.z176 = new Scrollbar(1);
        z1.z159 = new Scrollbar(1);
        z1.z54 = false;
        z1.z138 = false;
        z1.z165 = 200;
        z1.z185 = 380;
        z1.z186 = 165;
        z1.z187 = 173;
        z1.image = "";
        z1.z14 = 0;
        z1.z22 = new Choice();
        z1.z140 = new Choice();
        z1.z17 = new Choice();
        z1.z164 = new z4();
        z1.z62 = Color.black;
        z1.z99 = new Color(255, 255, 227);
        z1.z63 = Color.black;
        z1.z100 = Color.white;
        z1.z61 = Color.black;
        z1.z60 = Color.white;
        z1.z52 = new Font("Helvetica", 1, 12);
        z1.z53 = new Font("Helvetica", 0, 12);
        z1.z44 = 8;
        z1.z116 = 2;
        z1.z110 = 4;
        z1.z178 = 3;
        z1.z125 = 5;
        z1.z57 = 1;
        z1.z77 = 8;
        z1.z18 = "";
        z1.z169 = 0.0;
        z1.z170 = 0.0;
        z1.z180 = new List();
        z1.z134 = new List();
        z1.z119 = new List();
        z1.z58 = new List();
        z1.z74 = new List();
        z1.z117 = new List();
        z1.z179 = new List();
        z1.z15 = new Choice();
        z1.z51 = "";
        z1.z27 = "";
        z1.z56 = null;
        z1.z69 = 68;
        z1.z68 = 32;
        z1.z172 = "";
    }
    
    public void buy_item() {
        if (z1.z22.getSelectedIndex() == 0 && !z1.z166[6].trim().equals("") && !z1.z43[6].trim().equals("")) {
            z1.z22.setForeground(Color.white);
            z1.z22.setBackground(Color.red);
            return;
        }
        if (z1.z140.getSelectedIndex() == 0 && !z1.z166[7].trim().equals("") && !z1.z43[7].trim().equals("")) {
            z1.z140.setForeground(Color.white);
            z1.z140.setBackground(Color.red);
            return;
        }
        if (z1.z17.getSelectedIndex() == 0 && !z1.z166[8].trim().equals("") && !z1.z43[8].trim().equals("")) {
            z1.z17.setForeground(Color.white);
            z1.z17.setBackground(Color.red);
            return;
        }
        z1.z140.setForeground(z1.z63);
        z1.z140.setBackground(z1.z100);
        z1.z22.setForeground(z1.z63);
        z1.z22.setBackground(z1.z100);
        z1.z17.setForeground(z1.z63);
        z1.z17.setBackground(z1.z100);
        final String s = "                                                     ";
        final String trim = String.valueOf(String.valueOf(String.valueOf(String.valueOf(z1.z140.getSelectedItem()).concat(String.valueOf(" "))).concat(String.valueOf(z1.z22.getSelectedItem().trim()))).concat(String.valueOf(" "))).concat(String.valueOf(z1.z17.getSelectedItem())).trim();
        String text = this.z81.getText();
        final String concat = String.valueOf(String.valueOf(z1.z43[z1.z57]).concat(String.valueOf(" "))).concat(String.valueOf(String.valueOf(String.valueOf(trim).concat(String.valueOf(" "))).concat(String.valueOf(z1.z43[0])).trim()));
        if (this.z101) {
            if (this.z108.equals("")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), "no_user_name.html"), "_blank");
                    return;
                }
                catch (Exception ex) {
                    return;
                }
            }
            this.z6(concat);
            return;
        }
        String trim2 = z1.z43[z1.z116].replace('$', ' ').replace('£', ' ').trim();
        String s2 = "";
        double doubleValue = 0.0;
        String s3 = "";
        for (int i = 0; i < this.z153; ++i) {
            if (this.z151[i].getState()) {
                s3 = this.z154[i];
            }
        }
        try {
            if (z1.z178 != -1) {
                s2 = z1.z43[z1.z178];
                doubleValue = new Double(s2.trim());
            }
        }
        catch (NumberFormatException ex2) {
            doubleValue = 0.0;
        }
        double doubleValue2;
        try {
            doubleValue2 = new Double(s3.trim());
        }
        catch (NumberFormatException ex3) {
            doubleValue2 = 0.0;
        }
        double doubleValue3;
        try {
            doubleValue3 = new Double(text.trim());
        }
        catch (NumberFormatException ex4) {
            doubleValue3 = 1.0;
            text = "1";
        }
        double doubleValue4;
        try {
            doubleValue4 = new Double(trim2.trim());
        }
        catch (NumberFormatException ex5) {
            doubleValue4 = 0.0;
            trim2 = "error";
            this.getAppletContext().showStatus("Not a valid price");
        }
        z1.z119.addItem(text.trim());
        z1.z74.addItem(String.valueOf(String.valueOf(trim).concat(String.valueOf(" "))).concat(String.valueOf(z1.z43[0])));
        z1.z117.addItem(trim2.trim());
        z1.z58.addItem(z1.z43[z1.z57]);
        z1.z179.addItem(String.valueOf("").concat(String.valueOf(doubleValue)));
        final double n = doubleValue3 * doubleValue4;
        z1.z169 += doubleValue3 * doubleValue4;
        z1.z170 += doubleValue3 * doubleValue;
        final double n2 = doubleValue2 * 10;
        final double n3 = z1.z169 * n2 / 1000;
        final double n4 = z1.z169 * (1000 + n2) / 1000;
        this.global_item_total = this.z0(n);
        final String z0 = this.z0(n);
        String s4;
        if (text.length() < 5) {
            s4 = String.valueOf(text).concat(String.valueOf(s.substring(0, 4 - text.length())));
        }
        else {
            s4 = String.valueOf(text).concat(String.valueOf(" "));
        }
        String s5;
        if (concat.length() > 32 + this.z42) {
            s5 = String.valueOf(concat).concat(String.valueOf(" "));
        }
        else {
            s5 = String.valueOf(concat).concat(String.valueOf(s.substring(0, 33 + this.z42 - concat.length())));
        }
        final String concat2 = String.valueOf(s.substring(0, 11 - trim2.length())).concat(String.valueOf(trim2));
        String.valueOf(s.substring(0, 11 - s2.length())).concat(String.valueOf(s2));
        z1.z18 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(z1.z18).concat(String.valueOf(this.z36))).concat(String.valueOf(s4))).concat(String.valueOf(s5))).concat(String.valueOf(concat2))).concat(String.valueOf(String.valueOf(s.substring(0, 12 - z0.length())).concat(String.valueOf(z0))));
        this.calc();
    }
    
    public void calc() {
        boolean b = false;
        double doubleValue = 0.0;
        this.z24 = 0.0;
        double doubleValue2 = 0.0;
        double doubleValue3 = 0.0;
        for (int i = 0; i < this.z115.countItems(); ++i) {
            final String item = this.z115.getItem(i);
            if (this.z137.getSelectedIndex() == 0) {
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
                this.z86.setText("Error in Shipping Rates");
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
                this.z86.setText("Error in Shipping Rates");
            }
            if (this.z137.getSelectedItem().equals(z1)) {
                double n;
                if (s2.equals("$")) {
                    n = z1.z169;
                }
                else {
                    n = z1.z170;
                }
                if (n < doubleValue2 || (n >= doubleValue2 && b2)) {
                    double z3;
                    if (s4.equals("%")) {
                        z3 = n * doubleValue3 / 100;
                    }
                    else {
                        z3 = doubleValue3;
                    }
                    i = this.z115.countItems();
                    this.z24 = z3;
                }
            }
        }
        if (b && z1.z170 >= doubleValue2) {
            this.z24 = doubleValue + doubleValue3 * (z1.z170 - doubleValue2);
        }
        final double z4 = 0.0;
        String s6 = "0";
        try {
            if (this.z152.getSelectedIndex() > 0) {
                s6 = this.z154[this.z152.getSelectedIndex() - 1];
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
        if (!this.z114) {
            this.z24 = z4;
        }
        if (z1.z169 == 0) {
            doubleValue4 = 0.0;
        }
        final double n2 = doubleValue4 * 10;
        double z5;
        if (this.z175) {
            z5 = (z1.z169 + this.z24) * n2 / 1000;
        }
        else {
            z5 = z1.z169 * n2 / 1000;
        }
        this.z156 = z5;
        double z6;
        if (this.z175) {
            z6 = (z1.z169 + this.z24) * (1000 + n2) / 1000;
        }
        else {
            z6 = z1.z169 * (1000 + n2) / 1000 + this.z24;
        }
        final String s7 = "                                              ";
        final String substring = s7.substring(0, 10 - this.z0(this.z24).length());
        final String substring2 = s7.substring(0, 10 - this.z0(z5).length());
        final String substring3 = s7.substring(0, 10 - this.z0(z6).length());
        this.global_item_total = String.valueOf("").concat(String.valueOf(this.z0(z6)));
        String s8 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(z1(this.z82, 5)).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf("      "))).concat(String.valueOf(z1(this.z82, 6)))).concat(String.valueOf(z1.z18))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36));
        if (this.z24 == z6) {
            this.z24 = 0.0;
            z6 = 0.0;
        }
        String s9;
        if (this.z175) {
            s9 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                   ".substring(0, 50 - z1(this.z177, 12).length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(z1(this.z177, 12)))).concat(String.valueOf(substring))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf(this.z36));
            if (this.z158) {
                s9 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s9).concat(String.valueOf("                                                       ".substring(0, 50 - this.z155.length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(this.z155))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36));
            }
            if (!this.z158 && this.z157.getCurrent() != null) {
                s9 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s9).concat(String.valueOf("                                                       ".substring(0, 50 - this.z155.length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(this.z155))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36));
            }
            if (!this.z158) {
                s9 = String.valueOf(s9).concat(String.valueOf(this.z36));
            }
        }
        else {
            if (this.z158) {
                s8 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                       ".substring(0, 50 - this.z155.length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(this.z155))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z36));
            }
            if (!this.z158 && this.z157.getCurrent() != null) {
                s8 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                       ".substring(0, 50 - this.z155.length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(this.z155))).concat(String.valueOf(substring2))).concat(String.valueOf(this.z0(z5)))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36));
            }
            if (!this.z158) {
                s8 = String.valueOf(s8).concat(String.valueOf(this.z36));
            }
            s9 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s8).concat(String.valueOf("                                                   ".substring(0, 50 - z1(this.z177, 12).length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(z1(this.z177, 12)))).concat(String.valueOf(substring))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36));
        }
        String s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s9).concat(String.valueOf("                                                   ".substring(0, 50 - z1(this.z177, 17).length())))).concat(String.valueOf(s7.substring(0, this.z42)))).concat(String.valueOf(z1(this.z177, 17)))).concat(String.valueOf(substring3))).concat(String.valueOf(this.z0(z6)))).concat(String.valueOf("\r\n\r\n"));
        if (this.z114) {
            s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s10).concat(String.valueOf(z1(this.z177, 12)))).concat(String.valueOf(" "))).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(this.z36));
        }
        if (z1.z170 > 0) {
            s10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s10).concat(String.valueOf(z1.z166[3]))).concat(String.valueOf(": "))).concat(String.valueOf(this.z0(z1.z170)))).concat(String.valueOf("  "));
        }
        this.z50 = z6;
        if (this.z158) {
            s10 = String.valueOf(String.valueOf(s10).concat(String.valueOf(this.z152.getSelectedItem()))).concat(String.valueOf(" "));
        }
        final String concat = String.valueOf(String.valueOf(s10).concat(String.valueOf(" "))).concat(String.valueOf(z1(z1.z27, 1)));
        this.z94 = concat;
        if (this.z96.getParent() == this.z95) {
            this.z95.remove(this.z96);
        }
        (this.z96 = new List()).setMultipleSelections(true);
        try {
            String line;
            while ((line = new DataInputStream(new StringBufferInputStream(concat)).readLine()) != null) {
                if (line == null) {
                    line = "";
                }
                this.z96.addItem(line);
            }
        }
        catch (Exception ex6) {}
        this.z95.add(this.z96);
        this.z96.setFont(this.z25);
        this.z96.setForeground(z1.z62);
        this.z96.setBackground(z1.z99);
        this.z96.reshape(5, 8 + this.z10 * 2, this.size().width - 10, this.size().height - (8 + this.z10 * 2) - 65);
        this.z67.show(this, "order_form");
    }
    
    public void checkout() {
        final String concat = String.valueOf(z1(this.z177, 24)).concat(String.valueOf(": "));
        this.z86.setText("                                                       ");
        if (this.z158 && this.z152.getSelectedIndex() < 1) {
            this.z86.setText(this.z152.getItem(0));
            this.z152.setBackground(Color.red);
            this.z152.setForeground(Color.white);
            return;
        }
        this.z152.setBackground(z1.z100);
        this.z152.setForeground(z1.z63);
        if (this.z114 && this.z137.getSelectedIndex() < 1) {
            this.z137.setBackground(Color.red);
            this.z137.setForeground(Color.white);
            this.z86.setText(this.z137.getItem(0));
            return;
        }
        if (z1.z169 == 0) {
            this.z86.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z34, 2))));
            return;
        }
        if (z1.z169 == 0) {
            this.z86.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z34, 2))));
            return;
        }
        String s = this.z94;
        this.getAppletContext().showStatus(z1(this.z82, 3));
        this.z35 = false;
        boolean b = false;
        if (this.z48.equals("post_html") || this.z48.equals("post_barclay")) {
            this.z181 = this.z126;
            if (this.z48.equals("post_html") && !this.getParameter("cheque").equals("")) {
                this.z181 = this.getParameter("cheque");
            }
            s = this.z10(s);
            this.z73 = "secure";
        }
        if (this.z48.equals("get_worldpay")) {
            this.z181 = this.getParameter("staging");
            if (this.z3(s)) {
                return;
            }
        }
        else if (this.z48.equals("get_new_worldpay")) {
            if (this.z4(s)) {
                return;
            }
        }
        else if (this.z48.equals("get_nochex")) {
            if (this.z5(s)) {
                return;
            }
        }
        else if (this.z48.equals("get_secpay")) {
            if (this.z7(s)) {
                return;
            }
        }
        else if (this.z48.equals("get_secure")) {
            if (this.z8(s)) {
                return;
            }
        }
        else if (this.z48.equals("get_authorize")) {
            if (this.z1(s)) {
                return;
            }
        }
        else {
            if (!this.z48.equals("get_html") && !this.z48.equals("get_barclay")) {
                if (this.z48.equals("post_worldpay")) {
                    this.z181 = this.getParameter("staging");
                    s = this.z10(s);
                    this.z48 = "post_worldpay";
                    this.z73 = "secure";
                }
                if (this.z48.equals("post_new_worldpay")) {
                    this.z181 = this.getParameter("staging");
                    s = this.z12(s);
                    this.z73 = "secure";
                }
                if (this.z48.equals("post_authorize")) {
                    s = this.z11(s);
                    this.z73 = "secure";
                }
                final String z48 = this.z48;
                if (z48.equals("post_html") || z48.equals("post_worldpay") || z48.equals("post_barclay") || z48.equals("post_authorize") || z48.equals("post_new_worldpay")) {
                    if (System.getProperty("os.name").equals("16-bit Windows")) {
                        if (this.z0(s) && this.z135.indexOf("OK") != -1) {
                            b = true;
                        }
                    }
                    else if (this.z15(s) && this.z6() && this.z135.indexOf("OK") != -1) {
                        b = true;
                    }
                }
                if (b && !z48.equals("java")) {
                    try {
                        this.z118 = this.z135.substring(0, this.z135.indexOf("O"));
                        this.shop_pl_url = String.valueOf(String.valueOf(this.z126).concat(String.valueOf("?Action=get_file&process_id="))).concat(String.valueOf(this.z118));
                        final URL url = new URL(this.shop_pl_url);
                        if (!this.javascript) {
                            this.getAppletContext().showDocument(url, "_blank");
                        }
                        else {
                            this.send_order = true;
                        }
                    }
                    catch (Exception ex) {}
                }
                return;
            }
            this.z181 = this.z126;
            if (this.z3(s)) {
                return;
            }
        }
    }
    
    public void clear() {
        z1.z119.clear();
        z1.z74.clear();
        z1.z117.clear();
        z1.z58.clear();
        z1.z179.clear();
        z1.z18 = "";
        z1.z169 = 0.0;
        z1.z170 = 0.0;
        this.z24 = 0.0;
    }
    
    static synchronized void z0() {
        z1.z70.setText1();
        for (int i = 0; i < 11; ++i) {
            z1.z43[i] = z0(i + 1);
        }
        z1.z166[9] = "";
        final String replace = z0(12).replace('¶', '\r').replace('§', '\n');
        z1.z176.setValue(0);
        String s = "";
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(z1.z52);
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
                    while (fontMetrics.stringWidth(s2) > z1.z163 - 21) {
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
        z9();
        z1.z164.setText(s, z1.z185 - 20, z1.z186);
        int n2 = 10;
        final int n3 = 32;
        final String s4 = z1.z43[6];
        z1.z0.remove(z1.z22);
        (z1.z22 = new Choice()).setForeground(z1.z63);
        z1.z22.setBackground(z1.z100);
        int n4 = 1;
        if (!z1(s4, 0).trim().equals("")) {
            z1.z22.addItem(String.valueOf("Select ").concat(String.valueOf(z1.z166[6])));
            while (!z1(s4, n4).equals("")) {
                z1.z22.addItem(z1(s4, n4));
                ++n4;
            }
        }
        if (n4 == 1) {
            z1.z22.addItem("");
        }
        else {
            z1.z0.add(z1.z22);
            final int n5 = fontMetrics.stringWidth(z1.z22.getItem(0)) + 30;
            z1.z22.reshape(n2, n3, n5, 30);
            n2 = n2 + n5 + 20;
        }
        z1.z22.select(0);
        final String s5 = z1.z43[7];
        z1.z0.remove(z1.z140);
        (z1.z140 = new Choice()).setForeground(z1.z63);
        z1.z140.setBackground(z1.z100);
        int n6 = 1;
        if (!z1(s5, 0).trim().equals("")) {
            z1.z0.add(z1.z140);
            z1.z140.addItem(String.valueOf("Select ").concat(String.valueOf(z1.z166[7])));
            while (!z1(s5, n6).equals("")) {
                z1.z140.addItem(z1(s5, n6));
                ++n6;
            }
            final int n7 = fontMetrics.stringWidth(z1.z140.getItem(0)) + 30;
            z1.z140.reshape(n2, n3, n7, 30);
            n2 = n2 + n7 + 20;
        }
        if (n6 == 1) {
            z1.z140.addItem("");
        }
        final String s6 = z1.z43[8];
        z1.z0.remove(z1.z17);
        (z1.z17 = new Choice()).setForeground(z1.z63);
        z1.z17.setBackground(z1.z100);
        int n8 = 1;
        if (!z1(s6, 0).trim().equals("")) {
            z1.z0.add(z1.z17);
            z1.z17.addItem(String.valueOf("Select ").concat(String.valueOf(z1.z166[8])));
            while (!z1(s6, n8).equals("")) {
                z1.z17.addItem(z1(s6, n8));
                ++n8;
            }
            z1.z17.reshape(n2, n3, fontMetrics.stringWidth(z1.z17.getItem(0)) + 30, 30);
        }
        if (n8 == 1) {
            z1.z17.addItem("");
        }
        z1.z109.show();
        if (z1.z43[10].trim().equals("")) {
            z1.z109.button_show = false;
        }
        else {
            z1.z109.button_show = true;
        }
        z1.z0.setBackground(z1.z99);
    }
    
    private void z1() {
        (z1.z0 = new z7()).setLayout(null);
        z1.z0.setBackground(z1.z99);
        if (z1.z15.countItems() > 2) {
            this.z97.add(z1.z15);
        }
        this.z97.setBackground(z1.z99);
        this.z97.setForeground(z1.z62);
        z1.z0.add(this.z97);
        this.z97.setLayout(new FlowLayout(0));
        this.z127.setFont(z1.z52);
        this.z127.setForeground(z1.z62);
        final Label label = new Label(z1(this.z177, 1));
        label.setAlignment(2);
        if (this.z139) {
            this.z97.add(this.z141);
        }
        if (!this.z101) {
            this.z97.add(label);
            this.z97.add(this.z81);
        }
        label.setFont(z1.z52);
        this.z97.add(this.z1);
        this.z97.add(this.z91);
        this.z1.setFont(z1.z52);
        this.z91.setFont(z1.z52);
        z1.z0.add(z1.z70);
        (z1.z43 = new String[z1.z77])[0] = "";
        for (int i = 0; i < z1.z77; ++i) {
            z1.z43[i] = "";
        }
        z1.z109 = new z2();
        z1.z0.add(z1.z176);
        z1.z0.add(z1.z164);
        z1.z0.add(z1.z159);
        z1.z0.add(z1.z109);
        z1.z164.setFont(z1.z52);
    }
    
    private void z2() {
        this.z42 = 22;
        this.z95.setLayout(null);
        final Panel panel = new Panel();
        this.z95.add(panel);
        panel.add(this.z167);
        panel.add(this.z19);
        panel.add(this.z20);
        panel.reshape(0, 32, this.size().width - 5, 32);
        panel.setLayout(new FlowLayout(0));
        this.z96.reshape(5, 8 + this.z10 * 2, this.size().width - 10, this.size().height - (8 + this.z10 * 2) - 65);
        this.z95.add(this.z96);
        this.z96.setForeground(z1.z62);
        this.z96.setFont(this.z25);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(0));
        this.z92.setLabel(z1(this.z34, 3));
        this.z92.setForeground(this.z11);
        this.z92.setBackground(this.z9);
        this.z92.setFont(z1.z52);
        this.z19.setLabel(z1(this.z177, 14));
        this.z19.setForeground(this.z11);
        this.z19.setBackground(this.z9);
        this.z20.setForeground(this.z11);
        this.z20.setBackground(this.z9);
        this.z20.setFont(z1.z52);
        this.z19.setFont(z1.z52);
        this.z121.setLabel(z1(this.z177, 15));
        this.z121.setForeground(this.z11);
        this.z121.setBackground(this.z9);
        if (this.z158) {
            panel2.add(this.z152);
        }
        if (this.z114) {
            panel2.add(this.z137);
        }
        if (this.z182 || this.z76) {
            panel2.add(this.z183);
        }
        if (this.z78) {
            panel2.add(this.z80);
        }
        if (this.z128) {
            panel2.add(this.z130);
        }
        if (this.z4) {
            panel2.add(this.z5);
        }
        if (this.z144) {
            panel2.add(this.z145);
        }
        if (this.z7) {
            panel2.add(this.z8);
        }
        if (this.z3) {
            panel2.add(this.z92);
        }
        this.z183.setForeground(this.z11);
        this.z183.setBackground(this.z9);
        this.z183.setFont(z1.z52);
        this.z79.setForeground(this.z11);
        this.z79.setBackground(this.z9);
        this.z79.setFont(z1.z52);
        this.z80.setForeground(this.z11);
        this.z80.setBackground(this.z9);
        this.z80.setFont(z1.z52);
        this.z129.setForeground(this.z11);
        this.z129.setBackground(this.z9);
        this.z129.setFont(z1.z52);
        this.z130.setForeground(this.z11);
        this.z130.setBackground(this.z9);
        this.z130.setFont(z1.z52);
        this.z5.setForeground(this.z11);
        this.z5.setBackground(this.z9);
        this.z5.setFont(z1.z52);
        this.z8.setForeground(this.z11);
        this.z8.setBackground(this.z9);
        this.z8.setFont(z1.z52);
        this.z121.setFont(z1.z52);
        panel2.add(this.z86);
        this.z86.setForeground(z1.z62);
        this.z86.setBackground(z1.z99);
        this.z95.add(panel2);
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
        this.z133.setLayout(null);
        final Panel panel = new Panel();
        this.z133.add(panel);
        panel.add(this.z168);
        panel.reshape(n8, n10, this.size().width - n8, n4 + 8);
        panel.reshape(0, n10, this.size().width - n8, n4 + 8);
        panel.setLayout(new FlowLayout(0));
        final Label label = new Label("Name: ", 2);
        label.setFont(z1.z52);
        this.z133.add(label);
        label.reshape(0, n11 + n18 + n, n8, n4);
        final Label label2 = new Label("Name: ", 2);
        label2.setFont(z1.z52);
        if (this.z2) {
            this.z133.add(label2);
        }
        label2.reshape(n5, n11 + n18 + n, n6 - n5, n4);
        final Panel panel2 = new Panel();
        this.z133.add(panel2);
        panel2.reshape(n8, n11 + n18 + n, n5 - n8, n4);
        panel2.setLayout(new FlowLayout(0, 0, 0));
        panel2.add(this.z89);
        this.z89.setForeground(Color.black);
        this.z89.setBackground(Color.white);
        final Panel panel3 = new Panel();
        if (this.z2) {
            this.z133.add(panel3);
        }
        final Label label3 = new Label("Delivery Details");
        final Label label4 = new Label("Card Holder Details");
        if (!this.z2) {
            label4.setText("Delivery Details");
        }
        if (this.z2) {
            this.z133.add(label3);
        }
        this.z133.add(label4);
        label4.setFont(z1.z52);
        label3.setFont(z1.z52);
        label3.reshape(n6, n11 + n18 + n - n4, 759 - n6, n4);
        label4.reshape(n8, n11 + n18 + n - n4, n5 - n8, n4);
        this.z133.reshape(n6, n11 + n18 + n, 759 - n6, n4);
        panel3.reshape(n6, n11 + n18 + n, n5 - n8, n4);
        panel3.setLayout(new FlowLayout(0, 0, 0));
        if (this.z2) {
            panel3.add(this.z31);
        }
        this.z31.setForeground(Color.black);
        this.z31.setBackground(Color.white);
        final Label label5 = new Label("Address: ", 2);
        label5.setFont(z1.z52);
        this.z133.add(label5);
        label5.reshape(0, n14 + n, n8, n4);
        final Label label6 = new Label("Address: ", 2);
        label6.setFont(z1.z52);
        if (this.z2) {
            this.z133.add(label6);
        }
        label6.reshape(n5, n14 + n, n6 - n5, n4);
        this.z133.add(this.z83);
        this.z83.reshape(n8, n14 + n, n5 - n8, n4 * 5);
        this.z83.setForeground(Color.black);
        this.z83.setBackground(Color.white);
        if (this.z2) {
            this.z133.add(this.z29);
        }
        this.z29.reshape(n6, n14 + n, n5 - n8, n4 * 5);
        this.z29.setForeground(Color.black);
        this.z29.setBackground(Color.white);
        final Label label7 = new Label("PostCode/Zip: ", 2);
        label7.setFont(z1.z52);
        this.z133.add(label7);
        label7.reshape(0, n14 + 5 * n4 + n, n8, n4);
        final Panel panel4 = new Panel();
        this.z133.add(panel4);
        panel4.add(this.z84);
        panel4.reshape(n8, n14 + n4 * 5 + n, n5 - n8 - 20, n4);
        panel4.setLayout(new FlowLayout(0, 0, 0));
        this.z84.setForeground(Color.black);
        this.z84.setBackground(Color.white);
        final Label label8 = new Label("PostCode/Zip: ", 2);
        label8.setFont(z1.z52);
        if (this.z2) {
            this.z133.add(label8);
        }
        label8.reshape(n5, n14 + 5 * n4 + n, n6 - n5, n4);
        final Panel panel5 = new Panel();
        if (this.z2) {
            this.z133.add(panel5);
        }
        if (this.z2) {
            panel5.add(this.z30);
        }
        panel5.reshape(n6, n14 + n4 * 5 + n, 759 - n6, n4);
        panel5.setLayout(new FlowLayout(0, 0, 0));
        this.z30.setForeground(Color.black);
        this.z30.setBackground(Color.white);
        final Label label9 = new Label("Phone Number: ");
        this.z133.add(label9);
        label9.setFont(z1.z52);
        label9.reshape(0, n13 + n2, n8, n4);
        label9.setAlignment(2);
        final Panel panel6 = new Panel();
        this.z133.add(panel6);
        panel6.reshape(n8, n13 + n2, n5 - n8, n4);
        panel6.setLayout(new FlowLayout(0, 0, 0));
        panel6.add(this.z98);
        this.z98.setForeground(Color.black);
        this.z98.setBackground(Color.white);
        final Label label10 = new Label("Email: ");
        label10.setAlignment(2);
        this.z133.add(label10);
        label10.setFont(z1.z52);
        label10.reshape(0, n14 + n2, n8, n4);
        final Panel panel7 = new Panel();
        this.z133.add(panel7);
        panel7.reshape(n8, n14 + n2, n5 - n8, n4);
        panel7.setLayout(new FlowLayout(0, 0, 0));
        panel7.add(this.z85);
        this.z85.setForeground(Color.black);
        this.z85.setBackground(Color.white);
        final Label label11 = new Label("Purchase Order: ");
        label11.setAlignment(2);
        if (!label11.getText().trim().equals(":") && this.z26) {
            this.z133.add(label11);
            label11.reshape(0, n17 + n2, n8, n4);
            label11.setFont(z1.z52);
            this.z88.setBackground(z1.z100);
            this.z88.setForeground(z1.z63);
            final Panel panel8 = new Panel();
            this.z133.add(panel8);
            panel8.reshape(n8, n17 + n2, n5 - n8, n4);
            panel8.setLayout(new FlowLayout(0, 0, 0));
            panel8.add(this.z88);
        }
        final Label label12 = new Label("Comments: ");
        label12.setAlignment(2);
        this.z133.add(label12);
        label12.setFont(z1.z52);
        this.z133.add(this.z174);
        this.z174.setBackground(z1.z100);
        this.z174.setForeground(z1.z63);
        if (this.z26) {
            final Label label13 = new Label("Card Number: ");
            final Label label14 = new Label("Expriy Date: ");
            label13.setFont(z1.z52);
            label14.setFont(z1.z52);
            this.z133.add(label13);
            label13.reshape(0, n16 + n2, n8, n3);
            label13.setAlignment(2);
            this.z12.setBackground(z1.z100);
            this.z12.setForeground(z1.z63);
            final Panel panel9 = new Panel();
            this.z133.add(panel9);
            panel9.reshape(n8, n16 + n2, n5 - n8, n4);
            panel9.setLayout(new FlowLayout(0, 0, 0));
            panel9.add(this.z12);
            this.z133.add(label14);
            label14.reshape(0, n15 + n2, n8, n4);
            label14.setAlignment(2);
            final Panel panel10 = new Panel();
            this.z133.add(panel10);
            panel10.reshape(n8, n15 + n2, n5 - n8, n4);
            panel10.setLayout(new FlowLayout(0, 0, 0));
            final Label label15 = new Label("/", 1);
            label15.setFont(z1.z52);
            panel10.add(this.z40);
            panel10.add(label15);
            panel10.add(this.z41);
            this.z40.setForeground(Color.black);
            this.z40.setBackground(Color.white);
            this.z41.setForeground(Color.black);
            this.z41.setBackground(Color.white);
        }
        final Panel panel11 = new Panel();
        panel11.setLayout(new FlowLayout(0));
        this.z93.setLabel("Order Button 2");
        this.z93.setForeground(this.z11);
        this.z93.setBackground(this.z9);
        this.z92.setFont(z1.z52);
        this.z122.setLabel("Return to Shop");
        this.z122.setForeground(this.z11);
        this.z122.setBackground(this.z9);
        this.z122.setFont(z1.z52);
        this.z168.setForeground(this.z11);
        this.z168.setBackground(this.z9);
        this.z168.setFont(z1.z52);
        panel11.add(this.z122);
        if (this.z78) {
            panel11.add(this.z79);
        }
        if (this.z128) {
            panel11.add(this.z129);
        }
        panel11.add(this.z87);
        this.z87.setForeground(Color.red);
        this.z133.add(panel11);
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
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.z126).concat(String.valueOf("?"))).concat(String.valueOf("Action="))).concat(String.valueOf(URLEncoder.encode(this.z73)))).concat(String.valueOf("&info="))).concat(String.valueOf(URLEncoder.encode(s)));
        try {
            this.z135 = "";
            final URLConnection openConnection = new URL(concat).openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String protocol = this.getCodeBase().getProtocol();
            String line;
            while ((line = dataInputStream.readLine()) != null && line.compareTo("end") != 0) {
                protocol = line;
            }
            dataInputStream.close();
            this.z86.setText(protocol);
            this.z135 = protocol;
            return true;
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
            return false;
        }
    }
    
    private boolean z1(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("x_Version=3.1&x_Login=").concat(String.valueOf(URLEncoder.encode(this.getParameter("Authorize"))))).concat(String.valueOf("&x_Show_Form=PAYMENT_FORM"))).concat(String.valueOf("&x_Amount="))).concat(String.valueOf(this.z0(this.z50)))).concat(String.valueOf("&x_Invoice_Num="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&x_Method=ECHECK"));
        String s2 = "";
        for (int i = 0; i < z1.z119.countItems(); ++i) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z9(z1.z58.getItem(i))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z9(z1.z74.getItem(i))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z119.getItem(i)))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z117.getItem(i)))).concat(String.valueOf(")"))).concat(String.valueOf("\n<br>"));
        }
        String s3 = "";
        for (int j = 0; j < z1.z119.countItems(); ++j) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z9(z1.z58.getItem(j))))).concat(String.valueOf(" "))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z9(z1.z119.getItem(j))))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z117.getItem(j)))).concat(String.valueOf(")"))).concat(String.valueOf("\n<br>"));
        }
        int n = 0;
        String s4;
        if (this.z114) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("\n<br>"));
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("\n<br>"));
            s4 = String.valueOf(String.valueOf(concat).concat(String.valueOf("&x_Freight="))).concat(String.valueOf(this.z0(this.z24)));
        }
        else {
            s4 = String.valueOf(concat).concat(String.valueOf("&x_Freight=$0"));
        }
        String s5;
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z155))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("\n<br>"));
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z155))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("\n<br>"));
            s5 = String.valueOf(String.valueOf(s4).concat(String.valueOf("&x_Tax="))).concat(String.valueOf(this.z0(this.z156)));
        }
        else {
            s5 = String.valueOf(s4).concat(String.valueOf("&x_Tax=$0"));
        }
        String trim;
        int index;
        String concat2;
        for (trim = s2; trim.indexOf("<br>", 0) != -1; trim = String.valueOf(concat2.substring(0, index)).concat(String.valueOf(concat2.substring(index + 4))).trim()) {
            index = trim.indexOf("<br>", 0);
            concat2 = String.valueOf(trim).concat(String.valueOf("       "));
        }
        final String concat3 = String.valueOf(String.valueOf(s5).concat(String.valueOf("&Description="))).concat(String.valueOf(URLEncoder.encode(trim)));
        String s6;
        if (s2.length() < 256) {
            s6 = String.valueOf(String.valueOf(concat3).concat(String.valueOf("&x_Description="))).concat(String.valueOf(URLEncoder.encode(s2)));
        }
        else if (s3.length() < 256) {
            s6 = String.valueOf(String.valueOf(concat3).concat(String.valueOf("&x_Description="))).concat(String.valueOf(URLEncoder.encode(s3)));
        }
        else {
            s6 = String.valueOf(concat3).concat(String.valueOf("&x_Description=Internet+Order"));
        }
        final String concat4 = String.valueOf(String.valueOf(this.z75).concat(String.valueOf("?"))).concat(String.valueOf(s6));
        try {
            this.z135 = "";
            final URL url = new URL(concat4);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.shop_pl_url = concat4;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    static void z2(String s) {
        final String s2 = s;
        s = String.valueOf(s).concat(String.valueOf(".txt"));
        final StringBuffer sb = new StringBuffer("");
        boolean b = false;
        String replace = "";
        for (int i = 1; i < z1.z15.countItems(); ++i) {
            if (z1.z23) {
                s = String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')).concat(String.valueOf(".txt"));
            }
            DataInputStream dataInputStream;
            try {
                if (z1.z23) {
                    replace = z1.z15.getItem(i).trim().replace(' ', '_');
                }
                else {
                    replace = s2;
                }
                int j;
                for (j = 0; j < z1.z72.countItems(); ++j) {
                    if (z1.z72.getItem(j).trim().replace(' ', '_').equals(replace)) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    dataInputStream = new DataInputStream(new StringBufferInputStream(z1.z71.getItem(j)));
                }
                else {
                    dataInputStream = new DataInputStream(new URL(new URL(z1.z172), s).openConnection().getInputStream());
                }
            }
            catch (Exception ex) {
                try {
                    final URLConnection openConnection = new URL(new URL(z1.z172), s).openConnection();
                    openConnection.setUseCaches(false);
                    dataInputStream = new DataInputStream(openConnection.getInputStream());
                }
                catch (Exception ex2) {
                    return;
                }
            }
            try {
                z1.z51 = dataInputStream.readLine();
                for (i = 0; i < z1.z77; ++i) {
                    z1.z166[i] = z0(z1.z51, i + 1);
                }
                sb.append(String.valueOf(z1.z51.trim()).concat(String.valueOf("\n")));
                String s3;
                while ((s3 = dataInputStream.readLine()) != null && s3.compareTo("end") != 0) {
                    sb.append(String.valueOf(s3.trim()).concat(String.valueOf("\n")));
                    int n = 12;
                    if (z1.z51.indexOf("|v3-ssc") != -1) {
                        int n2 = 0;
                        final String concat = String.valueOf(s3).concat(String.valueOf("||||||||||||||"));
                        if (!z0(concat, 14).trim().equals("")) {
                            n = 16;
                        }
                        for (int k = 1; k < n; ++k) {
                            n2 = concat.indexOf(124, n2) + 1;
                        }
                        s3 = String.valueOf(concat.substring(0, concat.indexOf(124, n2))).concat(String.valueOf("|"));
                    }
                    if (z1.z23) {
                        s3 = String.valueOf(String.valueOf(s3).concat(String.valueOf(z1.z15.getItem(i).trim().replace(' ', '_')))).concat(String.valueOf("|"));
                    }
                    else if (z0(s3, 13).trim().equals("") && n == 12) {
                        s3 = String.valueOf(String.valueOf(s3).concat(String.valueOf(s2.trim().replace(' ', '_')))).concat(String.valueOf("|"));
                    }
                    if (s3.indexOf(124) > 0) {
                        z1.z134.addItem(s3);
                        z1.z180.addItem(z0(s3, 1));
                    }
                    if (z1.z134.countItems() == 1 || z1.z134.countItems() == 20) {
                        z1.z180.select(0);
                        z0();
                    }
                }
                String line;
                while ((line = dataInputStream.readLine()) != null) {}
                dataInputStream.close();
            }
            catch (Exception ex3) {}
            if (z1.z134.countItems() < 1) {
                final String s4 = "New_Record||||||||||||||||||||";
                z1.z180.addItem("New_Record", 0);
                z1.z134.addItem(s4, 0);
            }
            else if (!b) {
                if (z1.z23) {
                    z1.z72.addItem(z1.z15.getItem(i).trim().replace(' ', '_'));
                    z1.z71.addItem(sb.toString());
                }
                else {
                    z1.z72.addItem(replace.replace(' ', '_'));
                    z1.z71.addItem(sb.toString());
                }
            }
            if (!z1.z23) {
                break;
            }
        }
        z0();
    }
    
    private boolean z3(final String s) {
        String s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("store=").concat(String.valueOf(this.z184))).concat(String.valueOf("&cname="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z27, 2))))).concat(String.valueOf("&mail="))).concat(String.valueOf(this.getParameter("E_Mail_Address")));
        if (this.z48.equals("get_barclay")) {
            s2 = String.valueOf(s2).concat(String.valueOf("&barclay=true"));
        }
        if (this.z158) {
            final String s3 = this.z154[this.z152.getSelectedIndex()];
        }
        int n = 0;
        for (int i = 0; i < z1.z119.countItems(); ++i) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&code"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z58.getItem(i))))).concat(String.valueOf("&desc"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z74.getItem(i).trim())))).concat(String.valueOf("&cost"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z117.getItem(i))))).concat(String.valueOf("&qty"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1.z119.getItem(i))));
            if (this.z182) {
                s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&cur"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z27, 2))));
            }
        }
        if (this.z114) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&code"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode("~WP_PNP")))).concat(String.valueOf("&desc"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(this.z137.getSelectedItem())))).concat(String.valueOf("&cost"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(this.z0(this.z24))))).concat(String.valueOf("&qty"))).concat(String.valueOf(n))).concat(String.valueOf("=1"))).concat(String.valueOf("&cur"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z27, 2))));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&code"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode("~WP_TAX")))).concat(String.valueOf("&desc"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(this.z152.getSelectedItem())))).concat(String.valueOf("&cost"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(this.z156)))))).concat(String.valueOf("&qty"))).concat(String.valueOf(n))).concat(String.valueOf("=1"))).concat(String.valueOf("&cur"))).concat(String.valueOf(n))).concat(String.valueOf("="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z27, 2))));
        }
        final String concat = String.valueOf(String.valueOf(String.valueOf(this.z181).concat(String.valueOf("?"))).concat(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("&max="))).concat(String.valueOf(n))))).concat(String.valueOf("&Action=processform"));
        if (this.z181.length() < 8) {
            this.z86.setText("Please set the program up before making an order");
        }
        try {
            this.z135 = "";
            final URL url = new URL(concat);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.shop_pl_url = concat;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    static String z0(final int n) {
        int z4 = z4();
        if (z4 == -1) {
            z4 = 0;
        }
        return z0(z1.z134.getItem(z4), n);
    }
    
    private boolean z4(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("instId=").concat(String.valueOf(URLEncoder.encode(this.z184)))).concat(String.valueOf("&cartId="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&currency="))).concat(String.valueOf(URLEncoder.encode(z1(z1.z27, 2))))).concat(String.valueOf("&amount="))).concat(String.valueOf(this.z0(this.z50)))).concat(String.valueOf("&testMode="))).concat(String.valueOf(this.getParameter("test_mode")))).concat(String.valueOf("&M_recipient="))).concat(String.valueOf(this.getParameter("E_Mail_Address")))).concat(String.valueOf("&M_subject="))).concat(String.valueOf(URLEncoder.encode("Worldpay Order")));
        String s2 = "";
        for (int i = 0; i < z1.z119.countItems(); ++i) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z9(z1.z58.getItem(i))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z9(z1.z74.getItem(i))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z119.getItem(i)))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z117.getItem(i)))).concat(String.valueOf("\n<br>"));
        }
        String s3 = "";
        for (int j = 0; j < z1.z119.countItems(); ++j) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z9(z1.z58.getItem(j))))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z9(z1.z119.getItem(j))))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z117.getItem(j)))).concat(String.valueOf("\n<br>"));
        }
        int n = 0;
        if (this.z114) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("<BR>"));
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("<BR>"));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("<BR>"));
            s3 = String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("<BR>"));
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
        final String concat3 = String.valueOf("https://select.worldpay.com/wcc/purchase?").concat(String.valueOf(s4));
        try {
            this.z135 = "";
            final URL url = new URL(concat3);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.shop_pl_url = concat3;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    private boolean z5(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("email=").concat(String.valueOf(URLEncoder.encode(this.getParameter("E_Mail_Address"))))).concat(String.valueOf("&ordernumber="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&amount="))).concat(String.valueOf(this.z0(this.z50)));
        final String concat2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("\n").concat(String.valueOf(this.z89.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z83.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z84.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z98.getText()))).concat(String.valueOf("\n"))).concat(String.valueOf(this.z85.getText()))).concat(String.valueOf("\n"));
        String s2 = "&description=";
        int n = 0;
        for (int i = 0; i < z1.z119.countItems(); ++i) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf(URLEncoder.encode(String.valueOf(this.z9(z1.z58.getItem(i))).concat(String.valueOf(" ")))))).concat(String.valueOf(URLEncoder.encode(String.valueOf(this.z9(z1.z74.getItem(i).trim())).concat(String.valueOf(" ")))))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf("(").concat(String.valueOf(z1.z119.getItem(i)))).concat(String.valueOf("x) £")))))).concat(String.valueOf(URLEncoder.encode(String.valueOf(z1.z117.getItem(i)).concat(String.valueOf("\n\n")))));
        }
        if (this.z114) {
            ++n;
            s2 = String.valueOf(s2).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.z137.getSelectedItem()).concat(String.valueOf(" £"))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("\n")))));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            s2 = String.valueOf(s2).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.z155).concat(String.valueOf(" £"))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("\n")))));
        }
        final String concat3 = String.valueOf("https://www.nochex.com/nochex.dll/checkout?").concat(String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf(s2))).concat(String.valueOf(URLEncoder.encode(concat2)))));
        try {
            this.z135 = "";
            final URL url = new URL(concat3);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.shop_pl_url = concat3;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    private boolean z6(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("add=").concat(String.valueOf(URLEncoder.encode(this.z81.getText())))).concat(String.valueOf("&business="))).concat(String.valueOf(URLEncoder.encode(this.z108)))).concat(String.valueOf("&item_name="))).concat(String.valueOf(URLEncoder.encode(s)))).concat(String.valueOf("&item_number="))).concat(String.valueOf(URLEncoder.encode(z1.z43[z1.z57])))).concat(String.valueOf("&amount="))).concat(String.valueOf(URLEncoder.encode(z1.z43[z1.z116])))).concat(String.valueOf("&shipping="))).concat(String.valueOf(URLEncoder.encode(this.z102)))).concat(String.valueOf("&shipping2="))).concat(String.valueOf(URLEncoder.encode(this.z103)))).concat(String.valueOf("&handling="))).concat(String.valueOf(URLEncoder.encode(this.z104)))).concat(String.valueOf("&note="))).concat(String.valueOf(URLEncoder.encode(this.z106)))).concat(String.valueOf("&image_url="))).concat(String.valueOf(URLEncoder.encode(this.z105)))).concat(String.valueOf("&return"))).concat(String.valueOf(URLEncoder.encode(this.z150)))).concat(String.valueOf("&cancel_payment"))).concat(String.valueOf(URLEncoder.encode(this.z13)));
        if (System.getProperty("java.vendor").toLowerCase().indexOf("apple computer") != -1) {
            this.z107 = "http://www.paypal.com/cart";
        }
        final String concat2 = String.valueOf(String.valueOf(this.z107).concat(String.valueOf("?"))).concat(String.valueOf(concat));
        try {
            this.z135 = "";
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
                this.shop_pl_url = concat2;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    private boolean z7(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("merchant=").concat(String.valueOf(this.z132))).concat(String.valueOf("&trans_id="))).concat(String.valueOf(new Date().getTime()))).concat(String.valueOf("&amount="))).concat(String.valueOf(this.z0(this.z50)))).concat(String.valueOf("&options="))).concat(String.valueOf(URLEncoder.encode("test_status=")))).concat(String.valueOf("true"))).concat(String.valueOf("&callback="))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.getCodeBase().getProtocol()).concat(String.valueOf("://"))).concat(String.valueOf(this.getCodeBase().getHost()))).concat(String.valueOf("/received_order.html")))))).concat(String.valueOf(";"))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(String.valueOf(this.getCodeBase().getProtocol()).concat(String.valueOf("://"))).concat(String.valueOf(this.getCodeBase().getHost()))).concat(String.valueOf("/failure.html")))));
        String s2 = "&order=";
        int n = 0;
        for (int i = 0; i < z1.z119.countItems(); ++i) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("prod="))).concat(String.valueOf(URLEncoder.encode(String.valueOf(this.z14(this.z9(z1.z58.getItem(i)))).concat(String.valueOf(" ")))))).concat(String.valueOf(URLEncoder.encode(this.z14(this.z9(z1.z74.getItem(i).trim())))))).concat(String.valueOf(URLEncoder.encode(",item_amount=")))).concat(String.valueOf(URLEncoder.encode(z1.z117.getItem(i))))).concat(String.valueOf("x"))).concat(String.valueOf(URLEncoder.encode(z1.z119.getItem(i))));
            if (i < z1.z119.countItems()) {
                s2 = String.valueOf(s2).concat(String.valueOf(";"));
            }
        }
        if (this.z114) {
            ++n;
            s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("prod="))).concat(String.valueOf(URLEncoder.encode(String.valueOf(String.valueOf(this.z14(this.z137.getSelectedItem())).concat(String.valueOf(",amount="))).concat(String.valueOf(this.z0(this.z24))))))).concat(String.valueOf(";"));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("prod="))).concat(String.valueOf(URLEncoder.encode(this.z14(this.z155))))).concat(String.valueOf(",amount="))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf(";"));
        }
        final String concat2 = String.valueOf("https://www.secpay.com/java-bin/ValCard?").concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf(s2))).concat(String.valueOf("&ship_name="))).concat(String.valueOf(URLEncoder.encode(this.z89.getText())))).concat(String.valueOf("&ship_company="))).concat(String.valueOf(URLEncoder.encode(this.z89.getText())))).concat(String.valueOf("&ship_addr_1="))).concat(String.valueOf(URLEncoder.encode(this.z83.getText())))).concat(String.valueOf("&ship_post_code="))).concat(String.valueOf(URLEncoder.encode(this.z84.getText())))).concat(String.valueOf("&ship_tel="))).concat(String.valueOf(URLEncoder.encode(this.z98.getText())))).concat(String.valueOf("&ship_email="))).concat(String.valueOf(URLEncoder.encode(this.z85.getText())))));
        try {
            this.z135 = "";
            final URL url = new URL(concat2);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.shop_pl_url = concat2;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
        }
        return true;
    }
    
    static int z4() {
        int selectedIndex = z1.z180.getSelectedIndex();
        if (selectedIndex == -1) {
            selectedIndex = 0;
            z1.z180.select(0);
        }
        return selectedIndex;
    }
    
    private String z1(final int n) {
        return z1.z134.getItem(n);
    }
    
    private boolean z8(final String s) {
        final String concat = String.valueOf(String.valueOf(String.valueOf("merchant=").concat(String.valueOf(URLEncoder.encode("phsupplies2323")))).concat(String.valueOf("&requiredfields="))).concat(String.valueOf(URLEncoder.encode("name,telephone,email")));
        for (int i = 0; i < this.z153; ++i) {
            if (this.z151[i].getState()) {
                final String s2 = this.z154[i];
            }
        }
        final String concat2 = String.valueOf(concat).concat(String.valueOf("&orderinfo="));
        String s3 = "";
        for (int j = 0; j < z1.z119.countItems(); ++j) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf(z1.z119.getItem(j)))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z58.getItem(j)))).concat(String.valueOf(" "))).concat(String.valueOf(z1.z74.getItem(j)))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z117.getItem(j)))).concat(String.valueOf(")"))).concat(String.valueOf("\n<br>"));
        }
        if (this.z157.getCurrent() != null && this.z158) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("1 x "))).concat(String.valueOf(this.z155))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf(")<br>"))).concat(String.valueOf("\n"));
        }
        if (this.z114) {
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("1 x "))).concat(String.valueOf("shipping"))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf(")\n<br>"));
        }
        final String concat3 = String.valueOf("https://securetrading.net/authorize/form.cgi?").concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat2).concat(String.valueOf(URLEncoder.encode(s3)))).concat(String.valueOf("&amount="))).concat(String.valueOf(URLEncoder.encode(String.valueOf("").concat(String.valueOf(Math.round(this.z50 * 100))))))));
        try {
            this.z135 = "";
            final URL url = new URL(concat3);
            if (!this.javascript) {
                this.getAppletContext().showDocument(url, "_blank");
            }
            else {
                this.send_order = true;
                this.shop_pl_url = concat3;
            }
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
            return false;
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && (event.target == this.z122 || event.target == this.z168)) {
            this.z67.show(this, "order_form");
            return super.handleEvent(event);
        }
        if (event.target == z1.z109 && event.id == 503 && !this.z64) {
            this.z64 = true;
            z1.z138 = true;
            final Graphics graphics = z1.z109.getGraphics();
            z1.z109.paint_enlarge_only = true;
            z1.z109.paint(graphics);
            z1.z109.paint_enlarge_only = false;
            return super.handleEvent(event);
        }
        if (event.target == z1.z109 && event.id == 505 && this.z64) {
            this.z64 = false;
            z1.z138 = false;
            z1.z109.paint(z1.z109.getGraphics());
            return super.handleEvent(event);
        }
        if (event.target == this.z96 && event.id == 701) {
            try {
                if (Integer.parseInt(String.valueOf("").concat(String.valueOf(event.arg))) < 1 || Integer.parseInt(String.valueOf("").concat(String.valueOf(event.arg))) > z1.z119.countItems()) {
                    this.z96.deselect(Integer.parseInt(String.valueOf("").concat(String.valueOf(event.arg))));
                }
                this.z96.select(0);
                this.z96.deselect(0);
            }
            catch (Exception ex) {}
        }
        if (event.target == z1.z70 && event.id == 501) {
            final int n = event.y - z1.z69 + z1.z159.getValue() * z1.z70.point_height;
            int n2 = 0;
            for (int i = z1.z113.countItems(); i > 1; --i) {
                if (n >= Integer.parseInt(z1.z113.getItem(i - 2))) {
                    n2 = i - 1;
                    break;
                }
            }
            z1.z180.select(n2);
            z0();
        }
        if (event.id == 501 && event.target == z1.z109 && !z1.z43[10].trim().equals("")) {
            z1.z109.button_show = true;
            if (this.javascript && System.getProperty("java.vendor").indexOf("Netscape") == -1) {
                this.enlarge = true;
                z1.image = z1.z43[10].trim();
            }
            else {
                if (this.z148 != null) {
                    this.z148.dispose();
                }
                String s = "";
                if (this.getCodeBase().getProtocol().toLowerCase().equals("http")) {
                    s = "?";
                }
                try {
                    this.z148 = new z6(Toolkit.getDefaultToolkit().getImage(new URL(this.getCodeBase(), String.valueOf(z1.z43[10]).concat(String.valueOf(s)))));
                }
                catch (Exception ex2) {}
                this.z148.resize(200, 200);
                this.z148.show();
            }
        }
        if (event.target == z1.z176 && (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603)) {
            z1.z164.translate(0, z1.z176.getValue() * Toolkit.getDefaultToolkit().getFontMetrics(z1.z52).getHeight());
            return true;
        }
        if (event.target == z1.z159 && (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603)) {
            z1.z70.translate(0, z1.z159.getValue() * this.z112);
            return true;
        }
        if (event.id == 1001) {
            if (event.target == z1.z15 && z1.z15.getSelectedIndex() > 0) {
                this.show_category("");
            }
            if (event.target == this.z20) {
                final int countItems = z1.z119.countItems();
                boolean b = false;
                for (int j = countItems; j > 0; --j) {
                    if (this.z96.isSelected(j)) {
                        this.z96.delItem(j);
                        z1.z169 -= Integer.parseInt(z1.z119.getItem(j - 1)) * new Double(z1.z117.getItem(j - 1));
                        z1.z170 -= new Double(z1.z179.getItem(j - 1));
                        z1.z119.delItem(j - 1);
                        z1.z74.delItem(j - 1);
                        z1.z117.delItem(j - 1);
                        z1.z58.delItem(j - 1);
                        z1.z179.delItem(j - 1);
                        this.z24 = 0.0;
                        b = true;
                    }
                }
                if (b) {
                    z1.z18 = "\n";
                    if (z1.z119.countItems() != 0) {
                        for (int k = 0; k < z1.z119.countItems(); ++k) {
                            z1.z18 = String.valueOf(z1.z18).concat(String.valueOf(this.z96.getItem(k + 1)));
                            if (k != z1.z119.countItems() - 1) {
                                z1.z18 = String.valueOf(z1.z18).concat(String.valueOf("\n"));
                            }
                        }
                        this.calc();
                    }
                    else {
                        this.clear();
                    }
                }
            }
            if (event.target == this.z19) {
                this.clear();
            }
        }
        if (event.id == 1001 && (event.target == this.z130 || event.target == this.z80)) {
            if (this.precheckout()) {
                this.z67.show(this, "secure_form");
            }
            return super.handleEvent(event);
        }
        if (event.id == 1001 && (event.target == this.z92 || event.target == this.z79 || event.target == this.z129 || event.target == this.z183 || event.target == this.z8 || event.target == this.z5 || event.target == this.z145)) {
            if ((event.target == this.z79 || event.target == this.z129) && !this.z5()) {
                return super.handleEvent(event);
            }
            if (this.z182 && event.target == this.z183) {
                if (this.z160) {
                    this.z48 = "post_worldpay";
                }
                else {
                    this.z48 = "get_worldpay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z76 && event.target == this.z183) {
                if (this.z160) {
                    this.z48 = "post_new_worldpay";
                }
                else {
                    this.z48 = "get_new_worldpay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z78 && event.target == this.z79) {
                if (this.z160) {
                    this.z48 = "get_nochex";
                }
                else {
                    this.z48 = "get_nochex";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z128 && event.target == this.z129) {
                if (this.z160) {
                    this.z48 = "get_secpay";
                }
                else {
                    this.z48 = "get_secpay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z144 && event.target == this.z145) {
                this.z48 = "get_secure";
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z7 && event.target == this.z8) {
                if (this.z160) {
                    this.z48 = "post_barclay";
                }
                else {
                    this.z48 = "get_barclay";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (this.z4 && event.target == this.z5) {
                if (this.z160) {
                    this.z48 = "post_authorize";
                }
                else {
                    this.z48 = "get_authorize";
                }
                this.checkout();
                return super.handleEvent(event);
            }
            if (event.target == this.z92 && this.z160) {
                this.z181 = this.z126;
                this.z48 = "post_html";
                this.checkout();
                return super.handleEvent(event);
            }
            if (event.target == this.z92 && !this.z160) {
                this.z181 = this.z126;
                this.z48 = "get_html";
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
            if (event.id == 1001 && event.target == this.z91 && this.z101) {
                final String concat = String.valueOf(String.valueOf(this.z107).concat(String.valueOf("?display=1&business="))).concat(String.valueOf(URLEncoder.encode(this.z108)));
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
                        this.shop_pl_url = concat;
                    }
                }
                catch (Exception ex3) {}
                return super.handleEvent(event);
            }
            if ((event.id == 402 || event.id == 1001) && (event.target == this.z91 || event.target == this.z1 || event.target == this.z152 || event.target == this.z137 || event.target == this.z19)) {
                this.calc();
            }
            if (event.id == 1001 && (event.target == this.z121 || event.target == this.z167)) {
                this.z86.setText("                                                       ");
                this.z7();
            }
            if (event.id == 1001 && event.target == this.z141) {
                if (this.z141.getSelectedIndex() == 0) {
                    this.z3(this.z143 = 1);
                    z1.z70.reshape(10, z1.z69, z1.z66, z1.z186);
                    this.z142 = 1;
                    this.z127.setText(z1(this.z82, 7));
                }
                if (this.z141.getSelectedIndex() == 1) {
                    this.z143 = 2;
                    this.z3(3);
                    z1.z70.reshape(10, z1.z69, z1.z66, z1.z186);
                    this.z142 = 3;
                    this.z127.setText(z1(this.z82, 8));
                }
                if (this.z141.getSelectedIndex() == 2) {
                    this.z143 = 3;
                    this.z3(2);
                    z1.z70.reshape(10, z1.z69, z1.z66, z1.z186);
                    this.z142 = 2;
                    this.z127.setText(z1(this.z82, 9));
                }
            }
            if (event.id == 402 && event.target == this.tf) {
                z0();
            }
            return super.handleEvent(event);
        }
    }
    
    private String z9(String s) {
        while (s.indexOf(38, 0) != -1) {
            final int index = s.indexOf(38, 0);
            s = String.valueOf(s).concat(String.valueOf(" "));
            final String substring = s.substring(index + 1);
            s = s.substring(0, index);
            s = String.valueOf(String.valueOf(s).concat(String.valueOf("&amp;"))).concat(String.valueOf(substring)).trim();
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
        System.out.println("Online Store 3.30 Copyright Web Design 2002");
        this.z86.setFont(new Font("Courier", 1, 12));
        z1.z172 = String.valueOf(String.valueOf(String.valueOf(this.getCodeBase().getProtocol()).concat(String.valueOf("://"))).concat(String.valueOf(this.getCodeBase().getHost()))).concat(String.valueOf(this.getCodeBase().getFile()));
        this.z97.reshape(5, 0, 800, 32);
        z1.z15 = new Choice();
        z1.z113 = new List();
        z1.z180 = new List();
        z1.z134 = new List();
        z1.z70 = new z3();
        z1.z176 = new Scrollbar(1);
        z1.z159 = new Scrollbar(1);
        z1.z109 = null;
        z1.z46 = true;
        if (this.getCodeBase().getProtocol().toLowerCase().equals("http")) {
            this.aol_cache = "?";
        }
        this.z36 = System.getProperty("line.separator");
        z1.z55 = new Color(Integer.parseInt(this.getParameter("highlight")));
        if (this.getParameter("thumbnail_height") != null) {
            z1.z165 = Integer.parseInt(this.getParameter("thumbnail_height"));
        }
        if (this.getParameter("secpay_user_name") != null) {
            this.z132 = this.getParameter("secpay_user_name");
        }
        if (this.getParameter("net_url") != null) {
            this.z75 = this.getParameter("net_url");
        }
        if (this.getParameter("paypal") != null && this.getParameter("method").indexOf("PayPal") != -1) {
            this.z101 = true;
        }
        this.z73 = this.getParameter("method");
        if (this.z73.indexOf("ePDQ") != -1) {
            this.z7 = true;
        }
        if (this.getParameter("business_name") != null) {
            this.z108 = this.getParameter("business_name");
        }
        if (this.getParameter("base_shipping") != null) {
            this.z102 = this.getParameter("base_shipping");
        }
        if (this.getParameter("extra_shipping") != null) {
            this.z103 = this.getParameter("extra_shipping");
        }
        if (this.getParameter("handling") != null) {
            this.z104 = this.getParameter("handling");
        }
        if (this.getParameter("logo") != null) {
            this.z105 = this.getParameter("logo");
        }
        if (this.getParameter("successful") != null) {
            this.z150 = this.getParameter("successful");
        }
        if (this.getParameter("cancel_payment") != null) {
            this.z13 = this.getParameter("cancel_payment");
        }
        if (this.getParameter("note") != null) {
            this.z106 = this.getParameter("note");
        }
        z1.z149 = new Color(Integer.parseInt(this.getParameter("subcat")));
        if (this.getParameter("form_html") != null && this.getParameter("form_html").equals("Yes")) {
            this.z49 = true;
        }
        this.z112 = Toolkit.getDefaultToolkit().getFontMetrics(new Font("Helvetica", 0, this.z47)).getHeight();
        z1.z70.point_height = this.z112;
        if (this.getParameter("temp_files").equals("No")) {
            this.z160 = false;
        }
        this.z48 = this.getParameter("form");
        if (this.getParameter("vat_post").equals("Yes")) {
            this.z175 = true;
        }
        else {
            this.z175 = false;
        }
        this.z177 = this.getParameter("buttons");
        this.z34 = this.getParameter("error");
        this.z123 = this.getParameter("status");
        this.z82 = this.getParameter("other");
        z1.z27 = this.getParameter("currency");
        this.z183.setLabel(z1(this.z177, 5));
        this.z127 = new Label(String.valueOf("  ").concat(String.valueOf(z1(this.z82, 7))), 0);
        z1.z21 = z1(this.z34, 4);
        this.setLayout(this.z67 = new CardLayout());
        final String parameter = this.getParameter("background");
        if (this.getParameter("cat_type").equals("Show")) {
            this.z139 = true;
        }
        else {
            this.z139 = false;
        }
        this.z155 = this.getParameter("tax_string");
        z1.z62 = Color.black;
        if (parameter.equals("Black")) {
            z1.z62 = Color.white;
        }
        if (parameter.equals("Blue")) {
            z1.z62 = Color.white;
        }
        if (parameter.equals("Red")) {
            z1.z62 = Color.white;
        }
        z1.z99 = new Color(Integer.parseInt(this.getParameter("color")));
        z1.z185 = this.size().width / 2 - 10;
        z1.z186 = this.size().height - z1.z69 - 10;
        z1.z65 = z1.z186;
        z1.z66 = z1.z185;
        z1.z187 = this.size().height - z1.z186 - 5;
        z1.z111 = Math.abs(this.size().width - z1.z185 - 30);
        z1.z14 = z1.z185 + 20;
        z1.z163 = this.size().width - z1.z14 - 10;
        z1.z100 = new Color(Integer.parseInt(this.getParameter("item_details")));
        z1.z63 = new Color(Integer.parseInt(this.getParameter("item_text")));
        z1.z164.setBackground(z1.z99);
        z1.z60 = new Color(Integer.parseInt(this.getParameter("index")));
        z1.z70.setBackground(z1.z60);
        z1.z164.setForeground(z1.z62);
        z1.z61 = new Color(Integer.parseInt(this.getParameter("index_text")));
        z1.z70.setForeground(z1.z61);
        this.z9 = new Color(Integer.parseInt(this.getParameter("button_back")));
        this.z11 = new Color(Integer.parseInt(this.getParameter("button_text")));
        this.z147 = this.getParameter("cheque");
        this.z181 = this.getParameter("staging");
        if (this.getParameter("store_name") != null) {
            this.z184 = this.getParameter("store_name").toUpperCase();
        }
        if (this.z73.indexOf("Payment") == -1) {
            this.z3 = true;
        }
        if (this.z73.indexOf("Worldpay") != -1 && this.z73.indexOf("Junior") == -1) {
            this.z182 = true;
        }
        if (this.z73.indexOf("Authorize") != -1) {
            this.z4 = true;
        }
        if (this.z73.indexOf("Junior") != -1) {
            this.z76 = true;
        }
        if (this.z73.indexOf("NOCHEX") != -1) {
            this.z78 = true;
        }
        if (this.z73.indexOf("SecPay") != -1) {
            this.z128 = true;
        }
        if (this.z73.indexOf("SecPay Test") != -1) {
            this.z131 = true;
        }
        if (this.z73.indexOf("Secure Trading") != -1) {
            this.z144 = true;
        }
        this.z146 = this.getParameter("st_no");
        final Color white = Color.white;
        this.z81.setBackground(white);
        z1.z15.setBackground(white);
        final Color black = Color.black;
        this.z81.setForeground(black);
        z1.z15.setForeground(black);
        if (this.getParameter("script_dir") != null) {
            this.z126 = this.getParameter("script_dir");
        }
        this.z73 = this.getParameter("method");
        this.z39.add(this.z38);
        this.add("error", this.z39);
        this.z38.reshape(0, 0, 90, 108);
        if (this.getParameter("CAT_1") != null) {
            z1.z15.addItem(z1(this.z34, 5));
            String s;
            for (int n = 1; (s = this.getParameter(String.valueOf("CAT_").concat(String.valueOf(n)))) != null; ++n) {
                final int index = s.indexOf(46);
                if (index != -1) {
                    s = s.substring(0, index);
                }
                z1.z15.addItem(s.replace('_', ' '));
            }
            z1.z15.select(1);
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(z1.z53);
            this.getAppletContext().showStatus(z1(this.z82, 1));
            z1.z134.addItem("Loading Data, Please Wait.|||||||||0||||");
            z1.z180.addItem("a");
            z1.z180.select(0);
            z1.z134.select(0);
            this.z91.setLabel(z1(this.z177, 3));
            this.z91.setForeground(this.z11);
            this.z91.setBackground(this.z9);
            this.z1.setLabel(z1(this.z177, 2));
            this.z1.setForeground(this.z11);
            this.z1.setBackground(this.z9);
            this.z167.setLabel(z1(this.z177, 4));
            this.z167.setForeground(this.z11);
            this.z167.setBackground(this.z9);
            if (this.getParameter("POST_1") != null) {
                this.z114 = true;
                int n2 = 1;
                int n3 = 0;
                String s2 = "";
                this.z137.addItem("Please select shipping");
                String parameter2;
                while ((parameter2 = this.getParameter(String.valueOf("POST_").concat(String.valueOf(n2)))) != null) {
                    final String z1 = z1(parameter2, 1);
                    if (!s2.equals(z1)) {
                        this.z137.addItem(z1);
                        ++n3;
                        s2 = z1;
                    }
                    this.z115.addItem(parameter2);
                    ++n2;
                }
            }
            else {
                this.z114 = false;
                this.z137.addItem("Standard");
                this.z115.addItem("Standard,$0+,$0");
            }
            if (this.z137.countItems() == 2) {
                this.z137.select(1);
            }
            if (this.z137.countItems() > 2) {
                this.z137.select(0);
            }
            this.z152.setBackground(z1.z100);
            this.z152.setForeground(z1.z63);
            this.z151 = new Checkbox[10];
            this.z152.addItem(String.valueOf("Please select ").concat(String.valueOf(this.z155)));
            while (this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z153 * 2 + 1))) != null && this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z153 * 2 + 2))) != null) {
                this.z151[this.z153] = new Checkbox(this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z153 * 2 + 1))), this.z157, false);
                this.z154[this.z153] = this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z153 * 2 + 2)));
                this.z152.addItem(this.getParameter(String.valueOf("TAX_").concat(String.valueOf(this.z153 * 2 + 1))));
                ++this.z153;
            }
            if (this.z152.countItems() == 2) {
                this.z152.select(1);
            }
            if (this.z152.countItems() > 2) {
                this.z152.select(0);
            }
            if (this.z153 > 0) {
                this.z158 = true;
                if (this.z153 == 1) {
                    this.z151[0].setState(true);
                }
            }
            else {
                for (int i = 0; i < 10; ++i) {
                    this.z151[i] = new Checkbox("error", this.z157, false);
                    this.z154[i] = "0";
                }
                this.z158 = false;
            }
            z1.z51 = "||GBP £||||||||||v3-ssc";
            final String concat = String.valueOf(z1.z51).concat(String.valueOf("|end|"));
            int j = 1;
            int n4 = -1;
            while (j != 0) {
                ++n4;
                if (z0(concat, n4 + 1).equals("end")) {
                    j = 0;
                }
            }
            z1.z77 = n4;
            z1.z44 = n4;
            z1.z166 = new String[z1.z77];
            for (int k = 0; k < z1.z77; ++k) {
                z1.z166[k] = z0(z1.z51, k + 1);
            }
            (this.z141 = new Choice()).setForeground(Color.black);
            this.z141.setBackground(Color.white);
            this.z141.addItem(String.valueOf(z1(this.z123, 3)).concat(String.valueOf("Items")));
            this.z141.addItem(String.valueOf(z1(this.z123, 3)).concat(String.valueOf("Prices")));
            this.z167.setFont(z1.z52);
            this.setBackground(z1.z99);
            this.setForeground(z1.z62);
            this.z95 = new Panel();
            this.z133 = new Panel();
            this.z1();
            this.z2();
            final Panel panel = new Panel();
            panel.setLayout(null);
            final TextArea textArea = new TextArea();
            this.z171 = new Button("Please re-order to continue");
            textArea.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.z36).concat(String.valueOf(this.z36))).concat(String.valueOf("The evaluation period for the shopping cart has expired"))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36))).concat(String.valueOf("The full version can be ordered"))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36))).concat(String.valueOf(this.z36))).concat(String.valueOf("from http://www.online-store.co.uk")));
            panel.add(textArea);
            textArea.reshape(100, 20, 400, 200);
            this.z171.reshape(190, 240, 220, 28);
            this.z171.setFont(z1.z52);
            this.add("aa", z1.z0);
            this.add("order_form", this.z95);
            this.add("secure_form", this.z133);
            this.add("start", panel);
            final Date date = new Date();
            this.z67.show(this, "aa");
            this.z33 = false;
            if (date.getYear() > 102) {
                this.z33 = true;
            }
            if (!this.z33 && date.getMonth() > 5) {
                this.z33 = true;
            }
            if (!this.z33 && date.getMonth() == 5 && date.getDate() > 6) {
                this.z33 = true;
            }
            if (this.z33) {
                this.z67.show(this, "start");
            }
            this.z3();
            (this.z6 = new z8(z1.z15.getSelectedItem())).start();
            z1.z161 = z1.z69 + z1.z165 + 10;
            z1.z162 = this.size().height - z1.z161 - 10;
            z1.z176.reshape(z1.z14 + z1.z163 - z1.z124, z1.z161, z1.z124, z1.z162);
            z1.z164.reshape(z1.z14, z1.z161, z1.z163 - z1.z124, z1.z162);
            z1.z176.setValues(0, z1.z186 / fontMetrics.getHeight(), 0, (z1.z186 + fontMetrics.getHeight()) / fontMetrics.getHeight());
            z1.z70.reshape(10, z1.z69, z1.z66, z1.z186);
            z1.z159.setValues(0, 1, 0, z1.z180.countItems());
            z1.z159.reshape(10 + z1.z185 - z1.z124, z1.z69, z1.z124, z1.z186);
            z1.z109.reshape(z1.z14, z1.z69, z1.z163, z1.z165);
            z1.z109.setBackground(z1.z99);
            z1.z15.select(0);
            return;
        }
        this.z38.setText("shop.html error ,no data files");
    }
    
    public boolean keyDown(final Event event, final int n) {
        return event.id == 401 && event.target == this.z81 && (event.target != this.z81 || ((n <= 47 || n >= 59) && n != 8));
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
    
    private String z10(final String s) {
        final String s2 = "<HTML>\n<HEAD>\n<TITLE>Shopping Cart</TITLE>\n</HEAD>\n<BODY onLoad = \"focus();document.postform.submit();\" ><p align=\"center\"><font color=\"#0000FF\"><strong><big><big><big>Connecting to the Server: Please Wait</big></big></big></strong></font><br><br>";
        for (int i = 0; i < this.z153; ++i) {
            if (this.z151[i].getState()) {
                final String s3 = this.z154[i];
            }
        }
        String s4 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z9(this.z181)))).concat(String.valueOf("\" method=\"post\">"));
        if (!this.javascript) {
            s4 = String.valueOf(s4).concat(String.valueOf("<INPUT type=\"submit\" name=\"submit_button\" value=\"Send Order to Server\"><br><br>"));
        }
        if (this.z48.equals("post_barclay")) {
            s4 = String.valueOf(s4).concat(String.valueOf("\r\n<input type=\"hidden\" name = \"barclay\" value = \"true\">\r\n"));
        }
        String s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf("<input type=\"hidden\" name = \"cname\" value = \""))).concat(String.valueOf(z1(z1.z27, 2)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"store\" value = \""))).concat(String.valueOf(this.z184))).concat(String.valueOf("\">\r\n"));
        int n = 0;
        for (int j = 0; j < z1.z119.countItems(); ++j) {
            ++n;
            String item;
            if (z1.z58.getItem(j).trim().equals("")) {
                item = "";
            }
            else {
                item = z1.z58.getItem(j);
            }
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"code"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(item)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"desc"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(z1.z74.getItem(j))))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cur"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(z1(z1.z27, 2)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cost"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(z1.z117.getItem(j))))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"qty"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(z1.z119.getItem(j))))).concat(String.valueOf("\">\r\n"));
        }
        if (this.z114) {
            ++n;
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"code"))).concat(String.valueOf(n))).concat(String.valueOf("\" value =  \"~WP_PNP\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"desc"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cost"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(this.z0(this.z24))))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"qty"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \"1\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cur"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(z1(z1.z27, 2))))).concat(String.valueOf("\">\r\n"));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            ++n;
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"code"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \"~WP_TAX\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"desc"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z152.getSelectedItem()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cost"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z156))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"qty"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \"1\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cur"))).concat(String.valueOf(n))).concat(String.valueOf("\" value = \""))).concat(String.valueOf(this.z9(z1(z1.z27, 2))))).concat(String.valueOf("\">\r\n"));
        }
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("<input type=\"hidden\" name = \"max\"   value = \""))).concat(String.valueOf(n))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"Action\"   value = \"processform\">\r\n"))).concat(String.valueOf("</p></FORM>"))).concat(String.valueOf("</BODY></HTML>"));
    }
    
    private String z11(final String s) {
        String s2 = "";
        if (this.z49) {
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
        if (!this.z49) {
            s2 = String.valueOf(String.valueOf(String.valueOf("<HTML>\n<HEAD>\n<TITLE>Shopping Cart</TITLE>\n</HEAD>\n<BODY onLoad = \"focus();document.postform.submit();\" ><p align=\"center\"><font color=\"#0000FF\"><strong><big><big><big>Connecting to the Server: Please Wait</big></big></big></strong></font><br><br>").concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z9(this.z75)))).concat(String.valueOf("\" method=\"post\">"));
        }
        for (int i = 0; i < this.z153; ++i) {
            if (this.z151[i].getState()) {
                final String s3 = this.z154[i];
            }
        }
        final Date date = new Date();
        if (!this.javascript) {
            s2 = String.valueOf(s2).concat(String.valueOf("<INPUT type=\"submit\" name=\"submit_button\" value=\"Send Order to Server\"><br><br>"));
        }
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<input type=\"hidden\" name = \"x_Login\" value = \""))).concat(String.valueOf(this.getParameter("Authorize")))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Version\" value = \"3.0\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Amount\" value = \""))).concat(String.valueOf(this.z0(this.z50)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Invoice_Num\" value = \""))).concat(String.valueOf(date.getTime()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"x_Show_Form\" value = \""))).concat(String.valueOf("PAYMENT_FORM"))).concat(String.valueOf("\">\r\n"));
        String s4 = "";
        for (int j = 0; j < z1.z119.countItems(); ++j) {
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z9(z1.z58.getItem(j))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z9(z1.z74.getItem(j))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z119.getItem(j)))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z117.getItem(j)))).concat(String.valueOf(")"))).concat(String.valueOf("<br>\n"));
        }
        String s5 = "";
        for (int k = 0; k < z1.z119.countItems(); ++k) {
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z9(z1.z58.getItem(k))))).concat(String.valueOf(" "))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z9(z1.z119.getItem(k))))).concat(String.valueOf(" x "))).concat(String.valueOf(z1.z117.getItem(k)))).concat(String.valueOf(")"))).concat(String.valueOf("<br>\n"));
        }
        int n = 0;
        if (this.z114) {
            ++n;
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("<br>\n"));
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("<br>\n"));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z155))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("<br>\n"));
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z155))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("<br>\n"));
        }
        String trim;
        int index2;
        String concat2;
        for (trim = s4; trim.indexOf("<br>", 0) != -1; trim = String.valueOf(concat2.substring(0, index2)).concat(String.valueOf(concat2.substring(index2 + 4))).trim()) {
            index2 = trim.indexOf("<br>", 0);
            concat2 = String.valueOf(trim).concat(String.valueOf("       "));
        }
        String s6;
        if (s4.length() < 256) {
            s6 = String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("<input type=\"hidden\" name = \"x_Description\" value = \""))).concat(String.valueOf(s4))).concat(String.valueOf("\">\n"));
        }
        else {
            final String concat3 = String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("<input type=\"hidden\" name = \"Description\" value = \""))).concat(String.valueOf(trim))).concat(String.valueOf("\">\n"));
            if (s5.length() < 256) {
                s6 = String.valueOf(String.valueOf(String.valueOf(concat3).concat(String.valueOf("<input type=\"hidden\" name = \"x_Description\" value = \""))).concat(String.valueOf(s5))).concat(String.valueOf("\">\n"));
            }
            else {
                s6 = String.valueOf(String.valueOf(String.valueOf(concat3).concat(String.valueOf("<input type=\"hidden\" name = \"x_Description\" value = \""))).concat(String.valueOf("Internet Order"))).concat(String.valueOf("\">\n"));
            }
        }
        String s7 = String.valueOf(s6).concat(String.valueOf("<input type=\"hidden\" name = \"Action\"   value = \"processform\">\r\n"));
        if (!this.z49) {
            s7 = String.valueOf(String.valueOf(s7).concat(String.valueOf("</p></FORM>"))).concat(String.valueOf("</BODY></HTML>"));
        }
        return s7;
    }
    
    private String z12(final String s) {
        String s2 = String.valueOf(String.valueOf(String.valueOf("<HTML>\n<HEAD>\n<TITLE>Shopping Cart</TITLE>\n</HEAD>\n<BODY onLoad = \"focus();document.postform.submit();\" ><p align=\"center\"><font color=\"#0000FF\"><strong><big><big><big>Connecting to the Server: Please Wait</big></big></big></strong></font><br><br>").concat(String.valueOf("<FORM name=\"postform\" action=\""))).concat(String.valueOf(this.z9("https://select.worldpay.com/wcc/purchase")))).concat(String.valueOf("\" method=\"post\">"));
        for (int i = 0; i < this.z153; ++i) {
            if (this.z151[i].getState()) {
                final String s3 = this.z154[i];
            }
        }
        final Date date = new Date();
        if (!this.javascript) {
            s2 = String.valueOf(s2).concat(String.valueOf("<INPUT type=\"submit\" name=\"submit_button\" value=\"Send Order to Server\"><br><br>"));
        }
        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("<input type=\"hidden\" name = \"instId\" value = \""))).concat(String.valueOf(this.z184))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"amount\" value = \""))).concat(String.valueOf(this.z0(this.z50)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"cartId\" value = \""))).concat(String.valueOf(date.getTime()))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"currency\" value = \""))).concat(String.valueOf(z1(z1.z27, 2)))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"testMode\" value = \""))).concat(String.valueOf(this.getParameter("test_mode")))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"M_recipient\" value = \""))).concat(String.valueOf(this.getParameter("E_Mail_Address")))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"M_subject\" value = \""))).concat(String.valueOf("Worlday Order"))).concat(String.valueOf("\">\r\n"));
        String s4 = "";
        for (int j = 0; j < z1.z119.countItems(); ++j) {
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z9(z1.z58.getItem(j))))).concat(String.valueOf(" "))).concat(String.valueOf(this.z9(z1.z74.getItem(j))))).concat(String.valueOf(" ("))).concat(String.valueOf(z1.z119.getItem(j)))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z117.getItem(j)))).concat(String.valueOf("<br>\n"));
        }
        String s5 = "";
        for (int k = 0; k < z1.z119.countItems(); ++k) {
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z9(z1.z58.getItem(k))))).concat(String.valueOf(" ("))).concat(String.valueOf(this.z9(z1.z119.getItem(k))))).concat(String.valueOf("x) "))).concat(String.valueOf(z1.z117.getItem(k)))).concat(String.valueOf("<br>\n"));
        }
        int n = 0;
        if (this.z114) {
            ++n;
            s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("\n"));
            s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf(this.z137.getSelectedItem()))).concat(String.valueOf(" "))).concat(String.valueOf(this.z0(this.z24)))).concat(String.valueOf("\n"));
        }
        if (this.z158 && this.z152.getSelectedIndex() > 0) {
            s4 = String.valueOf(String.valueOf(String.valueOf(s4).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("<br>\n"));
            s5 = String.valueOf(String.valueOf(String.valueOf(s5).concat(String.valueOf("VAT "))).concat(String.valueOf(this.z0(this.z156)))).concat(String.valueOf("<br>\n"));
        }
        String s6;
        if (s4.length() < 256) {
            s6 = String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("<input type=\"hidden\" name = \"desc\" value = \""))).concat(String.valueOf(s4))).concat(String.valueOf("\">\n"));
        }
        else if (s5.length() < 256) {
            s6 = String.valueOf(String.valueOf(String.valueOf(concat).concat(String.valueOf("<input type=\"hidden\" name = \"desc\" value = \""))).concat(String.valueOf(s5))).concat(String.valueOf("\">\n"));
        }
        else {
            s6 = String.valueOf(concat).concat(String.valueOf("<input type=\"hidden\" name = \"desc\" value = \"Details on Email Receipt\">\n"));
        }
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s6).concat(String.valueOf("<input type=\"hidden\" name = \"MC_description\" value = \""))).concat(String.valueOf(s4))).concat(String.valueOf("\">\r\n"))).concat(String.valueOf("<input type=\"hidden\" name = \"Action\"   value = \"processform\">\r\n"))).concat(String.valueOf("</p></FORM>"))).concat(String.valueOf("</BODY></HTML>"));
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
    
    private boolean z5() {
        final String s = "Error: ";
        this.z89.setForeground(z1.z63);
        this.z89.setBackground(z1.z100);
        this.z89.setFont(z1.z53);
        this.z83.setForeground(z1.z63);
        this.z83.setBackground(z1.z100);
        this.z83.setFont(z1.z53);
        this.z84.setForeground(z1.z63);
        this.z84.setBackground(z1.z100);
        this.z84.setFont(z1.z53);
        this.z98.setForeground(z1.z63);
        this.z98.setBackground(z1.z100);
        this.z98.setFont(z1.z53);
        this.z85.setForeground(z1.z63);
        this.z85.setBackground(z1.z100);
        this.z85.setFont(z1.z53);
        if (this.z89.getText().equals("")) {
            this.z87.setText(String.valueOf(s).concat(String.valueOf("No Name")));
            this.z89.setForeground(Color.white);
            this.z89.setBackground(Color.red);
            this.z89.setFont(z1.z52);
            return false;
        }
        if (this.z83.getText().equals("")) {
            this.z87.setText(String.valueOf(s).concat(String.valueOf("No Address")));
            this.z83.setForeground(Color.white);
            this.z83.setBackground(Color.red);
            this.z83.setFont(z1.z52);
            return false;
        }
        if (this.z98.getText().equals("")) {
            this.z87.setText(String.valueOf(s).concat(String.valueOf("No Telephone Number")));
            this.z98.setForeground(Color.white);
            this.z98.setBackground(Color.red);
            this.z98.setFont(z1.z52);
            return false;
        }
        if (this.z85.getText().indexOf("@") == -1 && this.z85.getText().indexOf(".") == -1) {
            this.z87.setText(String.valueOf(s).concat(String.valueOf("in Email Address")));
            this.z85.setForeground(Color.white);
            this.z85.setBackground(Color.red);
            this.z85.setFont(z1.z52);
            return false;
        }
        return true;
    }
    
    public boolean precheckout() {
        final String concat = String.valueOf(z1(this.z177, 24)).concat(String.valueOf(": "));
        this.z86.setText("                                                       ");
        if (this.z158 && this.z152.getSelectedIndex() < 1) {
            this.z86.setText(this.z152.getItem(0));
            this.z152.setBackground(Color.red);
            this.z152.setForeground(Color.white);
            return false;
        }
        this.z152.setBackground(z1.z100);
        this.z152.setForeground(z1.z63);
        if (this.z114 && this.z137.getSelectedIndex() < 1) {
            this.z137.setBackground(Color.red);
            this.z137.setForeground(Color.white);
            this.z86.setText(this.z137.getItem(0));
            return false;
        }
        if (z1.z169 == 0) {
            this.z86.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z34, 2))));
            return false;
        }
        if (z1.z169 == 0) {
            this.z86.setText(String.valueOf(concat).concat(String.valueOf(z1(this.z34, 2))));
            return false;
        }
        return true;
    }
    
    private boolean z6() {
        try {
            this.z135 = "";
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.z173.getInputStream(), 2048));
            String protocol = this.getCodeBase().getProtocol();
            String line;
            while ((line = dataInputStream.readLine()) != null && line.compareTo("end") != 0) {
                protocol = line;
            }
            dataInputStream.close();
            this.z86.setText(protocol);
            this.z135 = protocol;
            return true;
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("Error reading from input: ").concat(String.valueOf(ex));
            return false;
        }
    }
    
    private void z13(final String s) {
        if (this.z6.isAlive()) {
            return;
        }
        z1.z180 = new List();
        z1.z134 = new List();
        z1.z113 = new List();
        z1.z70.dy = 0;
        z1.z159.setValue(0);
        (this.z6 = new z8(s)).start();
    }
    
    private void z7() {
        this.getAppletContext().showStatus("");
        this.z67.show(this, "aa");
        if (System.getProperty("java.version").indexOf("1.0") == -1) {
            this.z8();
        }
    }
    
    private String z14(String s) {
        s = s.replace('\'', ' ');
        s = s.replace(';', ' ');
        s = s.replace('=', ' ');
        return s;
    }
    
    private boolean z15(final String s) {
        try {
            final String concat = String.valueOf(String.valueOf(String.valueOf("Action=").concat(String.valueOf(URLEncoder.encode(this.z73)))).concat(String.valueOf("&info="))).concat(String.valueOf(URLEncoder.encode(s)));
            (this.z173 = new URL(this.z126).openConnection()).setUseCaches(false);
            this.z173.setDoOutput(true);
            this.z173.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            this.z173.setRequestProperty("content-length", String.valueOf(concat.length()));
            final DataOutputStream dataOutputStream = new DataOutputStream(this.z173.getOutputStream());
            dataOutputStream.writeBytes(concat);
            dataOutputStream.close();
            return true;
        }
        catch (Exception ex) {
            this.z135 = String.valueOf("URL Connection error: ").concat(String.valueOf(ex.toString()));
            return false;
        }
    }
    
    private void z2(final int n) {
        z1.z180.select(n);
    }
    
    private void z8() {
        this.z81.setText("1");
    }
    
    public void show_category(final String s) {
        if (!s.equals("")) {
            z1.z15.select(s);
        }
        if (!z1.z23) {
            final String z0 = z0(z1.z134.getItem(0), 13);
            for (int i = 0; i < z1.z72.countItems(); ++i) {
                if (z1.z72.getItem(i).trim().replace(' ', '_').equals(z0)) {
                    break;
                }
            }
        }
        this.z120 = false;
        this.z13(z1.z15.getSelectedItem().trim().replace(' ', '_'));
        if (this.z120) {
            this.z13(z1.z15.getSelectedItem().trim().replace(' ', '_'));
        }
        this.getAppletContext().showStatus("");
        this.z67.show(this, "aa");
    }
    
    private void z3(int z142) {
        z142 = 1;
        if (this.z143 == 2) {
            z142 = 3;
        }
        else if (this.z143 == 3) {
            z142 = 2;
        }
        final int countItems = z1.z134.countItems();
        this.z142 = z142;
        final String s = new String();
        String item = "";
        final int z143 = z4();
        if (z143 >= 0) {
            item = z1.z134.getItem(z143);
        }
        final List list = new List();
        list.addItem("");
        if (countItems > 2) {
            for (int i = 0; i < countItems; ++i) {
                final String concat = String.valueOf(z1.z134.getItem(i)).concat(String.valueOf("|"));
                int n = 0;
                for (int j = 1; j < z142; ++j) {
                    n = concat.indexOf(124, n) + 1;
                }
                list.addItem(concat.substring(n, concat.indexOf(124, n)).toUpperCase().trim(), i);
            }
            this.getAppletContext().showStatus(z1(this.z82, 2));
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
                    final String item4 = z1.z134.getItem(k);
                    z1.z134.replaceItem(z1.z134.getItem(n2), k);
                    z1.z134.replaceItem(item4, n2);
                    list.replaceItem(list.getItem(k), n2);
                }
            }
        }
        if (countItems > 1) {
            z1.z180.select(countItems - 1);
        }
        if (countItems == 1) {
            z1.z180.select(0);
        }
        z1.z180.addItem("", 0);
        z1.z180.delItem(0);
        z1.z180.setMultipleSelections(true);
        z1.z180.hide();
        if (z142 == 1) {
            for (int n3 = 0; n3 < countItems; ++n3) {
                z1.z180.replaceItem(z0(z1.z134.getItem(n3), z142), n3);
            }
        }
        else {
            for (int n4 = 0; n4 < countItems; ++n4) {
                z1.z180.replaceItem(z0(z1.z134.getItem(n4), z142), n4);
            }
        }
        z1.z180.setMultipleSelections(false);
        z1.z180.show();
        z1.z180.addItem("", 0);
        z1.z180.delItem(0);
        this.getAppletContext().showStatus("");
        if (z1.z180.getSelectedIndex() != -1) {
            z1.z180.deselect(z1.z180.getSelectedIndex());
        }
        if (z143 >= 0) {
            for (int n5 = 0; n5 < countItems; ++n5) {
                if (item.equals(z1.z134.getItem(n5))) {
                    this.z2(n5);
                }
            }
        }
        z0();
        z1.z180.makeVisible(z1.z180.getSelectedIndex());
    }
    
    private String z16(String concat) {
        String concat2 = "";
        int index = 0;
        concat = String.valueOf(concat).concat(String.valueOf(",jajwuthtfapow,,"));
        z1.z77 = 0;
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
            ++z1.z77;
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
    
    static void z9() {
        z1.z109.original = false;
        z1.z109.original = true;
        z1.z109.xx = z1.z111;
        z1.z109.yy = z1.z165;
        z1.image = z1(z1.z43[z1.z110], z1.z28);
        try {
            z1.z59 = Toolkit.getDefaultToolkit().getImage(new URL(new URL(String.valueOf(z1.z172).concat(String.valueOf(""))), z1(z1.z43[z1.z110], z1.z28)));
            z1.z109.draw_picture(z1.z59, z1(z1.z43[z1.z110], z1.z28));
        }
        catch (MalformedURLException ex) {}
        try {
            if (z1.z43[z1.z110].trim().equals("")) {
                if (z1.z161 != z1.z69) {
                    z1.z109.resize(0, 0);
                    z1.z161 = z1.z69;
                    z1.z162 = z1.z0.size().height - z1.z69 - 10;
                    z1.z164.reshape(z1.z14, z1.z69, z1.z163, z1.z162);
                }
            }
            else if (z1.z161 != z1.z69 + z1.z165 + 10) {
                z1.z161 = z1.z69 + z1.z165 + 10;
                z1.z162 = z1.z0.size().height - z1.z161 - 10;
                z1.z164.reshape(z1.z14, z1.z161, z1.z163, z1.z162);
                z1.z109.resize(z1.z163, z1.z165);
            }
        }
        catch (Exception ex2) {}
    }
    
    public z1() {
        this.z2 = false;
        this.z131 = false;
        this.z49 = false;
        this.z3 = false;
        this.z101 = false;
        this.z4 = false;
        this.z7 = false;
        this.z64 = false;
        this.z47 = 12;
        this.z112 = 15;
        this.z96 = new List();
        this.z181 = "";
        this.z182 = false;
        this.z76 = false;
        this.z78 = false;
        this.z128 = false;
        this.z144 = false;
        this.z160 = true;
        this.z146 = "";
        this.z152 = new Choice();
        this.z50 = 0.0;
        this.z48 = "";
        this.z183 = new Button("");
        this.z79 = new Button("NOCHEX Secure Server");
        this.z80 = new Button("Pay using NOCHEX");
        this.z129 = new Button("SecPay Secure Order Form");
        this.z130 = new Button("Pay using Secpay");
        this.z8 = new Button("Pay via Barclays ePDQ");
        this.z5 = new Button("Pay by Credit Card via Authorize Net Secure Order Form");
        this.z145 = new Button("Pay by Credit Card via SecureTrading");
        this.z75 = "https://secure.authorize.net/gateway/transact.dll";
        this.z132 = "";
        this.z10 = 28;
        this.z33 = false;
        this.shop_pl_url = "";
        this.z118 = "";
        this.z175 = false;
        this.z147 = "";
        this.z184 = "";
        this.z107 = "https://www.paypal.com/cart";
        this.z108 = "";
        this.z102 = "";
        this.z103 = "";
        this.z104 = "";
        this.z106 = "";
        this.z105 = "";
        this.z150 = "";
        this.z13 = "";
        this.z156 = 0.0;
        this.z139 = false;
        this.enlarge = false;
        this.javascript = false;
        this.send_order = false;
        this.global_item_total = "";
        this.global_tax = 0.0;
        this.z153 = 0;
        this.z24 = 0.0;
        this.z177 = "";
        this.z143 = 1;
        this.z42 = 0;
        this.z171 = new Button("Click Here");
        this.z120 = false;
        this.z97 = new Panel();
        this.z37 = false;
        this.z35 = true;
        this.z25 = new Font("Courier", 0, 12);
        this.z135 = "";
        this.z126 = "http://no-script-dir/cgi-script.pl";
        this.z81 = new TextField("1", 3);
        this.z16 = new TextField("ww", z1.z45);
        this.z89 = new TextField("", z1.z45);
        this.z31 = new TextField("", z1.z45);
        this.z83 = new TextArea("");
        this.z29 = new TextArea("");
        this.z84 = new TextField("", 10);
        this.z30 = new TextField("", 10);
        this.z98 = new TextField("", z1.z45);
        this.z32 = new TextField("", z1.z45);
        this.z85 = new TextField("", z1.z45);
        this.z88 = new TextField("", 10);
        this.z174 = new TextArea("");
        this.z12 = new TextField("", z1.z45);
        this.z40 = new TextField("", 10);
        this.z41 = new TextField("", 10);
        this.z93 = new Button("order_button_2");
        this.z122 = new Button("Return to Shop");
        this.z168 = new Button("Return to Shop");
        this.z87 = new Label("                                                            ");
        this.z26 = false;
        this.z158 = false;
        this.z114 = false;
        this.z86 = new Label("                                                       ");
        this.z167 = new Button("");
        this.z1 = new Button("");
        this.z19 = new Button("");
        this.z20 = new Button("Clear Selected Items");
        this.z91 = new Button("");
        this.z121 = new Button("");
        this.z92 = new Button("");
        this.z142 = 1;
        this.z137 = new Choice();
        this.z115 = new Choice();
        this.z94 = "";
        this.tf = new TextField("", 5);
        this.z73 = "";
        this.z39 = new Panel();
        this.z38 = new TextArea();
        this.z157 = new CheckboxGroup();
        this.z136 = new CheckboxGroup();
        this.z154 = new String[10];
        this.aol_cache = "";
    }
}

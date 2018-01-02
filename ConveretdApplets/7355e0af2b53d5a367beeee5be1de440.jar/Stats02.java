import java.awt.Graphics;
import java.io.InputStream;
import java.awt.Toolkit;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Event;
import java.awt.TextField;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Stats02 extends Applet
{
    String \u00c7;
    Panel \u00c8;
    Panel \u00c9;
    Panel \u00ca;
    ImagePanel \u00cb;
    ImagePanel \u00cc;
    ImagePanel \u00cd;
    ImagePanel \u00ce;
    Label[][] \u00cf;
    Label[][] \u00d0;
    Label[][] \u00d1;
    plaatjeKnop \u00d2;
    plaatjeKnop \u00d3;
    plaatjeKnop \u00d4;
    plaatjeKnop \u00d5;
    plaatjeKnop \u00d6;
    plaatjeKnop \u00d8;
    plaatjeKnop \u00d9;
    plaatjeKnop \u00da;
    plaatjeKnop \u00db;
    plaatjeKnop \u00dc;
    plaatjeKnop \u00dd;
    plaatjeKnop \u00de;
    plaatjeKnop \u00df;
    plaatjeKnop \u00e0;
    plaatjeKnop \u00e1;
    plaatjeKnop \u00e2;
    plaatjeKnop \u00e3;
    Color \u00e4;
    Color \u00e5;
    Color \u00e6;
    final int \u00e7 = 1;
    final int \u00e8 = 2;
    final int \u00e9 = 3;
    final int \u00ea = 4;
    final int \u00eb = 5;
    final int \u00ec = 6;
    final int \u00ed = 7;
    int \u00ee;
    int \u00ef;
    int \u00f0;
    int \u00f1;
    final int \u00f2 = 3;
    final int \u00f3 = 13;
    final int \u00f4 = 10;
    int[] \u00f5;
    int[] \u00f6;
    int[] \u00f8;
    int[] \u00f9;
    int \u00fa;
    String[] \u00fb;
    String[] \u00fc;
    Font \u00fd;
    Font \u00fe;
    Font \u00ff;
    Font \u0100;
    Font \u0101;
    Color \u0102;
    String \u0103;
    String \u0104;
    String \u0105;
    String \u0106;
    String \u0107;
    Image \u0108;
    MediaTracker \u0109;
    String \u010a;
    String \u010b;
    String \u010c;
    String \u010d;
    HyperlinkComponent \u010e;
    HyperlinkComponent \u010f;
    HyperlinkComponent \u0110;
    Panel \u0111;
    Panel \u0112;
    Panel \u0113;
    Image \u0114;
    Image \u0115;
    Image \u0116;
    Image \u0117;
    Image \u0118;
    Image \u0119;
    Image \u011a;
    Image \u011b;
    Image \u011c;
    Image \u011d;
    Image \u011e;
    Image \u011f;
    Image \u0120;
    Image \u0121;
    Image \u0122;
    Image \u0123;
    Image \u0124;
    Image \u0125;
    Image \u0126;
    Image \u0127;
    plaatjeKnop \u0128;
    plaatjeKnop \u0129;
    plaatjeKnop \u012a;
    plaatjeKnop \u012b;
    plaatjeKnop \u012c;
    plaatjeKnop \u012d;
    int \u012e;
    int \u012f;
    int \u0130;
    int \u0131;
    int \u0132;
    TextField \u0133;
    TextField \u0134;
    TextField \u0135;
    
    public Stats02() {
        this.\u00c7 = "2.0.2";
        this.\u00e4 = new Color(149, 3, 3);
        this.\u00e5 = new Color(236, 240, 249);
        this.\u00e6 = new Color(236, 240, 249);
        this.\u00ee = 180;
        this.\u00ef = 333;
        this.\u00f0 = 50;
        this.\u00f1 = 5;
        this.\u00f5 = new int[] { 1, 36, 121 };
        this.\u00f6 = new int[] { 35, 90, 57 };
        this.\u00f8 = new int[] { 1, 60, 120 };
        this.\u00f9 = new int[] { 59, 60, 59 };
        this.\u00fa = 20;
        this.\u00fb = new String[] { "RANK", "NAME", "TIME" };
        this.\u00fc = new String[] { "RANK", "NAME", "POINTS" };
        this.\u00fd = new Font("Roman", 1, 11);
        this.\u00fe = new Font("Roman", 0, 11);
        this.\u00ff = new Font("Roman", 0, 10);
        this.\u0100 = new Font("Roman", 0, 11);
        this.\u0101 = new Font("Roman", 1, 11);
        this.\u0102 = new Color(204, 204, 204);
        this.\u0103 = "software © PZZL.com";
        this.\u010a = "How is this calculated?";
        this.\u010b = "http://www.nytimes.com/ref/membercenter/help/xwordtopten.html#c2";
        this.\u010c = "http://www.nytimes.com/ref/membercenter/help/xwordtopten.html#c2";
        this.\u010d = "http://www.nytimes.com/ref/membercenter/help/xwordtopten.html#c2";
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.\u00db) {
            if (this.\u0130 == 1) {
                this.\u0130 = -1;
            }
            else {
                this.\u0130 -= 10;
            }
            this.\u00c8(5);
            this.\u00d2(5);
        }
        if (event.target == this.\u00dc) {
            this.\u0130 += 10;
            this.\u00c8(5);
            this.\u00d2(5);
        }
        if (event.target == this.\u00dd) {
            if (this.\u0131 == 1) {
                this.\u0131 = -1;
            }
            else {
                this.\u0131 -= 10;
            }
            this.\u00c8(6);
            this.\u00d2(6);
        }
        if (event.target == this.\u00de) {
            this.\u0131 += 10;
            this.\u00c8(6);
            this.\u00d2(6);
        }
        if (event.target == this.\u00df) {
            if (this.\u0132 == 1) {
                this.\u0132 = -1;
            }
            else {
                this.\u0132 -= 10;
            }
            this.\u00c8(7);
            this.\u00d2(7);
        }
        if (event.target == this.\u00e0) {
            this.\u0132 += 10;
            this.\u00c8(7);
            this.\u00d2(7);
        }
        if (event.target == this.\u00d2) {
            this.\u012f = 1;
            this.doSearch2();
            this.\u00c7(1);
            this.\u00c8(this.\u012e);
            this.\u00d1(1);
            this.\u00d6.show(true);
            this.\u00d8.show(false);
            this.\u00d9.show(false);
            this.\u00da.show(false);
            this.\u00d8(1);
        }
        if (event.target == this.\u00d3) {
            this.\u012f = 2;
            this.doSearch2();
            this.\u00c7(2);
            this.\u00c8(this.\u012e);
            this.\u00d1(2);
            this.\u00d6.show(false);
            this.\u00d8.show(true);
            this.\u00d9.show(false);
            this.\u00da.show(false);
            this.\u00d8(2);
        }
        if (event.target == this.\u00d4) {
            this.\u012f = 3;
            this.doSearch2();
            this.\u00c7(2);
            this.\u00c8(this.\u012e);
            this.\u00d1(3);
            this.\u00d6.show(false);
            this.\u00d8.show(false);
            this.\u00d9.show(true);
            this.\u00da.show(false);
            this.\u00d8(3);
        }
        if (event.target == this.\u00d5) {
            this.\u012f = 4;
            this.doSearch2();
            this.\u00c7(4);
            this.\u00c8(this.\u012e);
            this.\u00d1(4);
            this.\u00d6.show(false);
            this.\u00d8.show(false);
            this.\u00d9.show(false);
            this.\u00da.show(true);
            this.\u00d8(4);
        }
        if (event.target == this.\u0128) {
            this.\u012e = 5;
            this.doSearch2();
            this.\u00cb.show(true);
            this.\u00cd.show(false);
            this.\u00cc.show(false);
            this.\u00d3.show(true);
            this.\u00d4.show(true);
            this.\u012b.show(true);
            this.\u012c.show(false);
            this.\u012d.show(false);
            this.\u00c8(5);
            this.\u00d2(5);
            this.\u00c8.show(true);
            this.\u00ca.show(false);
            this.\u00c9.show(false);
        }
        if (event.target == this.\u012a) {
            this.\u012e = 6;
            this.doSearch2();
            this.\u00cb.show(false);
            this.\u00cd.show(true);
            this.\u00cc.show(false);
            this.\u00d3.show(true);
            this.\u00d4.show(true);
            this.\u012b.show(false);
            this.\u012c.show(false);
            this.\u012d.show(true);
            this.\u00c8(6);
            this.\u00d2(6);
            this.\u00c8.show(false);
            this.\u00ca.show(true);
            this.\u00c9.show(false);
        }
        if (event.target == this.\u0129) {
            this.\u012e = 7;
            this.doSearch2();
            this.\u00cb.show(false);
            this.\u00cd.show(false);
            this.\u00cc.show(true);
            this.\u00c7(this.\u012f = 1);
            this.\u00d3.show(false);
            this.\u00d4.show(false);
            this.\u00d8.show(false);
            this.\u00d9.show(false);
            this.\u00da.show(false);
            this.\u00d6.show(true);
            this.\u012b.show(false);
            this.\u012c.show(true);
            this.\u012d.show(false);
            this.\u00c8(7);
            this.\u00d2(7);
            this.\u00c8.show(false);
            this.\u00ca.show(false);
            this.\u00c9.show(true);
        }
        if (event.target == this.\u00d6) {
            this.doSearch2();
            this.\u00c8(this.\u012e);
            this.\u00d1(1);
            this.\u00d8(1);
        }
        if (event.target == this.\u00d8) {
            this.doSearch2();
            this.\u00c8(this.\u012e);
            this.\u00d1(2);
            this.\u00d8(2);
        }
        if (event.target == this.\u00d9) {
            this.doSearch2();
            this.\u00c8(this.\u012e);
            this.\u00d1(3);
            this.\u00d8(3);
        }
        if (event.target == this.\u00da) {
            this.doSearch2();
            this.\u00c8(this.\u012e);
            this.\u00d1(4);
            this.\u00d8(4);
        }
        if (event.target == this.\u012b) {
            this.doSearch2();
            this.\u00c8(5);
            this.\u00d2(5);
        }
        if (event.target == this.\u012d) {
            this.doSearch2();
            this.\u00c8(6);
            this.\u00d2(6);
        }
        if (event.target == this.\u012c) {
            this.doSearch2();
            this.\u00c8(7);
            this.\u00d2(7);
        }
        if (event.target == this.\u00e1 || event.target == this.\u00e2 || event.target == this.\u00e3) {
            this.doSearchName();
        }
        return true;
    }
    
    void \u00c7(final int n) {
        if (n == 1) {
            this.\u00cf[2][0].setText(this.\u00fb[2]);
            this.\u00d0[2][0].setText(this.\u00fb[2]);
            this.\u00d1[2][0].setText(this.\u00fb[2]);
        }
        else {
            this.\u00cf[2][0].setText(this.\u00fc[2]);
            this.\u00d0[2][0].setText(this.\u00fc[2]);
            this.\u00d1[2][0].setText(this.\u00fc[2]);
        }
    }
    
    void \u00c8(final int n) {
        switch (n) {
            case 5: {
                for (int i = 0; i < 3; ++i) {
                    for (int j = 1; j < 11; ++j) {
                        if (i == 0) {
                            this.\u00cf[i][j].setText(".");
                        }
                        if (i == 1) {
                            this.\u00cf[i][j].setText("-");
                        }
                        if (i == 2) {
                            this.\u00cf[i][j].setText("0");
                        }
                        this.\u00cf[i][12].setText("");
                    }
                }
                break;
            }
            case 6: {
                for (int k = 0; k < 3; ++k) {
                    for (int l = 1; l < 11; ++l) {
                        if (k == 0) {
                            this.\u00d1[k][l].setText(".");
                        }
                        if (k == 1) {
                            this.\u00d1[k][l].setText("-");
                        }
                        if (k == 2) {
                            this.\u00d1[k][l].setText("0");
                        }
                        this.\u00d1[k][12].setText("");
                    }
                }
                break;
            }
            case 7: {
                for (int n2 = 0; n2 < 3; ++n2) {
                    for (int n3 = 1; n3 < 11; ++n3) {
                        if (n2 == 0) {
                            this.\u00d0[n2][n3].setText(".");
                        }
                        if (n2 == 1) {
                            this.\u00d0[n2][n3].setText("-");
                        }
                        if (n2 == 2) {
                            this.\u00d0[n2][n3].setText("0");
                        }
                        this.\u00d0[n2][12].setText("");
                    }
                }
                break;
            }
        }
    }
    
    String \u00c9(final int n) {
        int n2 = n / 60000;
        final int n3 = n2 / 60;
        final int n4 = n / 1000 - n2 * 60;
        String string;
        if (n3 > 0) {
            string = n3 + ":";
            n2 -= n3 * 60;
        }
        else {
            string = "";
        }
        String s;
        if (n2 < 10) {
            s = " " + n2;
        }
        else {
            s = String.valueOf(n2);
        }
        String s2;
        if (n4 < 10) {
            s2 = "0" + n4;
        }
        else {
            s2 = String.valueOf(n4);
        }
        return String.valueOf(string) + s + ":" + s2;
    }
    
    void \u00ca() {
        (this.\u00ce = new ImagePanel(this.\u0108, 156, 15)).reshape(24, 351, 156, 15);
        this.add(this.\u00ce);
    }
    
    void \u00cb() {
        (this.\u00c8 = new Panel()).setLayout(null);
        this.\u00c8.reshape(1, 73, 178, 260);
        this.\u00c8.setBackground(Color.white);
        this.\u00c8.show(false);
        this.add(this.\u00c8);
        (this.\u00cb = new ImagePanel(this.\u0123, 170, 40)).setLayout(null);
        this.\u00cb.reshape(4, 1, 178, 40);
        this.\u00cb.setBackground(Color.white);
        this.\u00cb.show(false);
        this.add(this.\u00cb);
        (this.\u00db = new plaatjeKnop(Color.white, null, this.\u0126, this.\u0126, this.\u0126)).reshape(3, this.\u00ef - 112, 18, 18);
        this.\u00c8.add(this.\u00db);
        (this.\u00dc = new plaatjeKnop(Color.white, null, this.\u0127, this.\u0127, this.\u0127)).reshape(159, this.\u00ef - 111, 18, 18);
        this.\u00c8.add(this.\u00dc);
        this.\u00cf = new Label[3][13];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 13; ++j) {
                (this.\u00cf[i][j] = new Label()).setFont(this.\u00fe);
                if (j == 0) {
                    this.\u00cf[i][j].reshape(this.\u00f8[i], j * this.\u00fa, this.\u00f9[i], this.\u00fa);
                }
                else {
                    this.\u00cf[i][j].reshape(this.\u00f5[i], j * this.\u00fa, this.\u00f6[i], this.\u00fa);
                }
                if (j == 0) {
                    this.\u00cf[i][j].setBackground(Color.white);
                    this.\u00cf[i][j].setForeground(Color.black);
                    if (i == 0) {
                        this.\u00cf[i][j].setAlignment(0);
                    }
                    if (i == 1) {
                        this.\u00cf[i][j].setAlignment(1);
                    }
                    if (i == 2) {
                        this.\u00cf[i][j].setAlignment(2);
                    }
                    this.\u00cf[i][j].setFont(this.\u0101);
                    this.\u00cf[i][j].setText(this.\u00fb[i]);
                }
                else if (j == 1) {
                    this.\u00cf[i][j].setBackground(this.\u00e4);
                    this.\u00cf[i][j].setForeground(Color.white);
                }
                else {
                    if (j % 2 == 0) {
                        this.\u00cf[i][j].setBackground(this.\u00e5);
                    }
                    else {
                        this.\u00cf[i][j].setBackground(Color.white);
                    }
                    this.\u00cf[i][j].setForeground(Color.black);
                }
                if (j > 0 && j < 11) {
                    if (i == 0) {
                        this.\u00cf[i][j].setText(".");
                        this.\u00cf[i][j].setAlignment(1);
                    }
                    if (i == 1) {
                        this.\u00cf[i][j].setText("-");
                        this.\u00cf[i][j].setAlignment(0);
                    }
                    if (i == 2) {
                        this.\u00cf[i][j].setText("0");
                        this.\u00cf[i][j].setAlignment(2);
                    }
                }
                if (j == 11) {
                    this.\u00cf[i][j].hide();
                }
                if (j > 11) {
                    if (i == 0) {
                        this.\u00cf[i][j].setAlignment(1);
                    }
                    if (i == 1) {
                        this.\u00cf[i][j].setAlignment(0);
                    }
                    if (i == 2) {
                        this.\u00cf[i][j].setAlignment(2);
                    }
                }
                this.\u00c8.add(this.\u00cf[i][j]);
            }
        }
    }
    
    public void declareInterface() {
        final int \u00ef = this.\u00ef;
        final int n = 55;
        final int n2 = 18;
        (this.\u00e1 = new plaatjeKnop(Color.white, null, this.\u0114, this.\u0114, this.\u0114)).reshape(79, this.\u00ef - 110, 21, 15);
        this.\u00c8.add(this.\u00e1);
        (this.\u00e3 = new plaatjeKnop(Color.white, null, this.\u0114, this.\u0114, this.\u0114)).reshape(79, this.\u00ef - 110, 21, 15);
        this.\u00c9.add(this.\u00e3);
        (this.\u00e2 = new plaatjeKnop(Color.white, null, this.\u0114, this.\u0114, this.\u0114)).reshape(79, this.\u00ef - 110, 21, 15);
        this.\u00ca.add(this.\u00e2);
        (this.\u00d6 = new plaatjeKnop(Color.white, null, this.\u0116, this.\u0116, this.\u0116)).reshape(0, \u00ef, 39, n2);
        this.add(this.\u00d6);
        (this.\u00d8 = new plaatjeKnop(Color.white, null, this.\u011a, this.\u011a, this.\u011a)).show(false);
        this.\u00d8.reshape(40, \u00ef, 46, n2);
        this.add(this.\u00d8);
        (this.\u00d9 = new plaatjeKnop(Color.white, null, this.\u0118, this.\u0118, this.\u0118)).show(false);
        this.\u00d9.reshape(87, \u00ef, 51, n2);
        this.add(this.\u00d9);
        (this.\u00da = new plaatjeKnop(Color.white, null, this.\u011c, this.\u011c, this.\u011c)).show(false);
        this.\u00da.reshape(138, \u00ef, 42, n2);
        this.add(this.\u00da);
        (this.\u012b = new plaatjeKnop(Color.white, null, this.\u0120, this.\u0120, this.\u0120)).show(false);
        this.\u012b.reshape(60, n, 59, n2);
        this.add(this.\u012b);
        (this.\u012d = new plaatjeKnop(Color.white, null, this.\u0122, this.\u0122, this.\u0122)).show(false);
        this.\u012d.reshape(120, n, 59, n2);
        this.add(this.\u012d);
        (this.\u012c = new plaatjeKnop(Color.white, null, this.\u011e, this.\u011e, this.\u011e)).reshape(0, n, 59, n2);
        this.add(this.\u012c);
        (this.\u00d2 = new plaatjeKnop(Color.white, null, this.\u0115, this.\u0116, this.\u0115)).reshape(0, \u00ef, 39, n2);
        this.add(this.\u00d2);
        (this.\u00d3 = new plaatjeKnop(Color.white, null, this.\u0119, this.\u011a, this.\u0119)).reshape(40, \u00ef, 46, n2);
        this.\u00d3.show(false);
        this.add(this.\u00d3);
        (this.\u00d4 = new plaatjeKnop(Color.white, null, this.\u0117, this.\u0118, this.\u0117)).reshape(87, \u00ef, 51, n2);
        this.\u00d4.show(false);
        this.add(this.\u00d4);
        (this.\u00d5 = new plaatjeKnop(Color.white, null, this.\u011b, this.\u011c, this.\u011b)).reshape(138, \u00ef, 42, n2);
        this.\u00d5.show(false);
        this.add(this.\u00d5);
        (this.\u0128 = new plaatjeKnop(Color.white, null, this.\u011f, this.\u0120, this.\u011f)).reshape(60, n, 59, n2);
        this.add(this.\u0128);
        (this.\u012a = new plaatjeKnop(Color.white, null, this.\u0121, this.\u0122, this.\u0121)).reshape(120, n, 59, n2);
        this.add(this.\u012a);
        (this.\u0129 = new plaatjeKnop(Color.white, null, this.\u011d, this.\u011e, this.\u011d)).reshape(0, n, 59, n2);
        this.add(this.\u0129);
        (this.\u0111 = new Panel()).setLayout(null);
        this.\u0111.reshape(5, 40, 170, 15);
        this.\u0111.show(false);
        this.\u0111.setBackground(Color.white);
        this.add(this.\u0111);
        (this.\u010e = new HyperlinkComponent(this.\u010a, this.\u010b, this, 10)).reshape(20, 0, 130, 15);
        this.\u0111.add(this.\u010e);
        (this.\u0112 = new Panel()).setLayout(null);
        this.\u0112.reshape(5, 40, 170, 15);
        this.\u0112.show(false);
        this.\u0112.setBackground(Color.white);
        this.add(this.\u0112);
        (this.\u010f = new HyperlinkComponent(this.\u010a, this.\u010c, this, 10)).reshape(20, 0, 130, 15);
        this.\u0112.add(this.\u010f);
        (this.\u0113 = new Panel()).setLayout(null);
        this.\u0113.reshape(5, 40, 170, 15);
        this.\u0113.show(false);
        this.\u0113.setBackground(Color.white);
        this.add(this.\u0113);
        (this.\u0110 = new HyperlinkComponent(this.\u010a, this.\u010d, this, 10)).reshape(20, 0, 130, 15);
        this.\u0113.add(this.\u0110);
    }
    
    void \u00cc() {
        (this.\u00ca = new Panel()).setLayout(null);
        this.\u00ca.reshape(1, 73, 178, 260);
        this.\u00ca.setBackground(Color.white);
        this.\u00ca.show(false);
        this.add(this.\u00ca);
        (this.\u00cd = new ImagePanel(this.\u0124, 170, 40)).setLayout(null);
        this.\u00cd.reshape(4, 1, 178, 40);
        this.\u00cd.setBackground(Color.white);
        this.\u00cd.show(false);
        this.add(this.\u00cd);
        (this.\u00dd = new plaatjeKnop(Color.white, null, this.\u0126, this.\u0126, this.\u0126)).reshape(3, this.\u00ef - 112, 18, 18);
        this.\u00ca.add(this.\u00dd);
        (this.\u00de = new plaatjeKnop(Color.white, null, this.\u0127, this.\u0127, this.\u0127)).reshape(159, this.\u00ef - 111, 18, 18);
        this.\u00ca.add(this.\u00de);
        this.\u00d1 = new Label[3][13];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 13; ++j) {
                (this.\u00d1[i][j] = new Label()).setFont(this.\u00fe);
                if (j == 0) {
                    this.\u00d1[i][j].reshape(this.\u00f8[i], j * this.\u00fa, this.\u00f9[i], this.\u00fa);
                }
                else {
                    this.\u00d1[i][j].reshape(this.\u00f5[i], j * this.\u00fa, this.\u00f6[i], this.\u00fa);
                }
                if (j == 0) {
                    this.\u00d1[i][j].setBackground(Color.white);
                    this.\u00d1[i][j].setForeground(Color.black);
                    if (i == 0) {
                        this.\u00d1[i][j].setAlignment(0);
                    }
                    if (i == 1) {
                        this.\u00d1[i][j].setAlignment(1);
                    }
                    if (i == 2) {
                        this.\u00d1[i][j].setAlignment(2);
                    }
                    this.\u00d1[i][j].setFont(this.\u0101);
                    this.\u00d1[i][j].setText(this.\u00fb[i]);
                }
                else if (j == 1) {
                    this.\u00d1[i][j].setBackground(this.\u00e4);
                    this.\u00d1[i][j].setForeground(Color.white);
                }
                else {
                    if (j % 2 == 0) {
                        this.\u00d1[i][j].setBackground(this.\u00e5);
                    }
                    else {
                        this.\u00d1[i][j].setBackground(Color.white);
                    }
                    this.\u00d1[i][j].setForeground(Color.black);
                }
                if (j > 0 && j < 11) {
                    if (i == 0) {
                        this.\u00d1[i][j].setText(".");
                        this.\u00d1[i][j].setAlignment(1);
                    }
                    if (i == 1) {
                        this.\u00d1[i][j].setText("-");
                        this.\u00d1[i][j].setAlignment(0);
                    }
                    if (i == 2) {
                        this.\u00d1[i][j].setText("0");
                        this.\u00d1[i][j].setAlignment(2);
                    }
                }
                if (j == 11) {
                    this.\u00d1[i][j].hide();
                }
                if (j > 11) {
                    if (i == 0) {
                        this.\u00d1[i][j].setAlignment(1);
                    }
                    if (i == 1) {
                        this.\u00d1[i][j].setAlignment(0);
                    }
                    if (i == 2) {
                        this.\u00d1[i][j].setAlignment(2);
                    }
                }
                this.\u00ca.add(this.\u00d1[i][j]);
            }
        }
    }
    
    void \u00cd() {
        (this.\u00c9 = new Panel()).setLayout(null);
        this.\u00c9.reshape(1, 73, 178, 260);
        this.\u00c9.setBackground(Color.white);
        this.\u00c9.show(true);
        this.add(this.\u00c9);
        (this.\u00cc = new ImagePanel(this.\u0125, 170, 40)).setLayout(null);
        this.\u00cc.reshape(4, 1, 178, 40);
        this.\u00cc.setBackground(Color.white);
        this.\u00cc.show(true);
        this.add(this.\u00cc);
        (this.\u00df = new plaatjeKnop(Color.white, null, this.\u0126, this.\u0126, this.\u0126)).reshape(3, this.\u00ef - 112, 18, 18);
        this.\u00c9.add(this.\u00df);
        (this.\u00e0 = new plaatjeKnop(Color.white, null, this.\u0127, this.\u0127, this.\u0127)).reshape(159, this.\u00ef - 111, 18, 18);
        this.\u00c9.add(this.\u00e0);
        this.\u00d0 = new Label[3][13];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 13; ++j) {
                (this.\u00d0[i][j] = new Label()).setFont(this.\u00fe);
                if (j == 0) {
                    this.\u00d0[i][j].reshape(this.\u00f8[i], j * this.\u00fa, this.\u00f9[i], this.\u00fa);
                }
                else {
                    this.\u00d0[i][j].reshape(this.\u00f5[i], j * this.\u00fa, this.\u00f6[i], this.\u00fa);
                }
                if (j == 0) {
                    this.\u00d0[i][j].setBackground(Color.white);
                    this.\u00d0[i][j].setForeground(Color.black);
                    if (i == 0) {
                        this.\u00d0[i][j].setAlignment(0);
                    }
                    if (i == 1) {
                        this.\u00d0[i][j].setAlignment(1);
                    }
                    if (i == 2) {
                        this.\u00d0[i][j].setAlignment(2);
                    }
                    this.\u00d0[i][j].setFont(this.\u0101);
                    this.\u00d0[i][j].setText(this.\u00fb[i]);
                }
                else if (j == 1) {
                    this.\u00d0[i][j].setBackground(this.\u00e4);
                    this.\u00d0[i][j].setForeground(Color.white);
                }
                else {
                    if (j % 2 == 0) {
                        this.\u00d0[i][j].setBackground(this.\u00e5);
                    }
                    else {
                        this.\u00d0[i][j].setBackground(Color.white);
                    }
                    this.\u00d0[i][j].setForeground(Color.black);
                }
                if (j > 0 && j < 11) {
                    if (i == 0) {
                        this.\u00d0[i][j].setText(".");
                        this.\u00d0[i][j].setAlignment(1);
                    }
                    if (i == 1) {
                        this.\u00d0[i][j].setText("-");
                        this.\u00d0[i][j].setAlignment(0);
                    }
                    if (i == 2) {
                        this.\u00d0[i][j].setText("0");
                        this.\u00d0[i][j].setAlignment(2);
                    }
                }
                if (j == 11) {
                    this.\u00d0[i][j].hide();
                }
                if (j > 11) {
                    if (i == 0) {
                        this.\u00d0[i][j].setAlignment(1);
                    }
                    if (i == 1) {
                        this.\u00d0[i][j].setAlignment(0);
                    }
                    if (i == 2) {
                        this.\u00d0[i][j].setAlignment(2);
                    }
                }
                this.\u00c9.add(this.\u00d0[i][j]);
            }
        }
    }
    
    public void doSearch2() {
        if (this.\u0133.isShowing() | this.\u0135.isShowing() | this.\u0134.isShowing()) {
            if (this.\u0133.isShowing()) {
                this.\u0133.validate();
                this.\u0106 = this.\u0133.getText();
                this.\u0135.setText(this.\u0106);
                this.\u0134.setText(this.\u0106);
                this.\u0133.hide();
                this.\u0134.hide();
                this.\u0135.hide();
                this.\u00cf[1][12].show();
                this.\u00d1[1][12].show();
                this.\u00d0[1][12].show();
            }
            if (this.\u0135.isShowing()) {
                this.\u0135.validate();
                this.\u0106 = this.\u0135.getText();
                this.\u0133.setText(this.\u0106);
                this.\u0134.setText(this.\u0106);
                this.\u0133.hide();
                this.\u0134.hide();
                this.\u0135.hide();
                this.\u00cf[1][12].show();
                this.\u00d1[1][12].show();
                this.\u00d0[1][12].show();
            }
            if (this.\u0134.isShowing()) {
                this.\u0134.validate();
                this.\u0106 = this.\u0134.getText();
                this.\u0135.setText(this.\u0106);
                this.\u0133.setText(this.\u0106);
                this.\u0133.hide();
                this.\u0134.hide();
                this.\u0135.hide();
                this.\u00cf[1][12].show();
                this.\u00d1[1][12].show();
                this.\u00d0[1][12].show();
            }
        }
    }
    
    public void doSearchName() {
        if (this.\u0133.isShowing() | this.\u0135.isShowing() | this.\u0134.isShowing()) {
            if (this.\u0133.isShowing()) {
                this.\u0133.validate();
                this.\u0106 = this.\u0133.getText();
                this.\u0135.setText(this.\u0106);
                this.\u0134.setText(this.\u0106);
                this.\u0133.hide();
                this.\u0134.hide();
                this.\u0135.hide();
                this.\u00cf[1][12].show();
                this.\u00d1[1][12].show();
                this.\u00d0[1][12].show();
                this.showScreen(this.\u012f, this.\u012e);
            }
            if (this.\u0135.isShowing()) {
                this.\u0135.validate();
                this.\u0106 = this.\u0135.getText();
                this.\u0133.setText(this.\u0106);
                this.\u0134.setText(this.\u0106);
                this.\u0133.hide();
                this.\u0134.hide();
                this.\u0135.hide();
                this.\u00cf[1][12].show();
                this.\u00d1[1][12].show();
                this.\u00d0[1][12].show();
                this.showScreen(this.\u012f, this.\u012e);
            }
            if (this.\u0134.isShowing()) {
                this.\u0134.validate();
                this.\u0106 = this.\u0134.getText();
                this.\u0135.setText(this.\u0106);
                this.\u0133.setText(this.\u0106);
                this.\u0133.hide();
                this.\u0134.hide();
                this.\u0135.hide();
                this.\u00cf[1][12].show();
                this.\u00d1[1][12].show();
                this.\u00d0[1][12].show();
                this.showScreen(this.\u012f, this.\u012e);
            }
        }
        else {
            this.\u0133.show();
            this.\u0134.show();
            this.\u0135.show();
            this.\u00cf[1][12].hide();
            this.\u00d1[1][12].hide();
            this.\u00d0[1][12].hide();
            this.\u0133.requestFocus();
            this.\u0134.requestFocus();
            this.\u0135.requestFocus();
        }
    }
    
    void \u00ce(final int n) {
        URL url = null;
        String s = null;
        switch (n) {
            case 1: {
                s = "DAY";
                break;
            }
            case 2: {
                s = "WEEK";
                break;
            }
            case 3: {
                s = "MONTH";
                break;
            }
            case 4: {
                s = "YEAR";
                break;
            }
            default: {
                s = "DAY";
                break;
            }
        }
        this.\u0106 = URLEncoder.encode(this.\u0106);
        final String string = "/servlets/EarlySolvers?loginname=" + this.\u0106 + "&mode=" + s + "&vanaf=" + this.\u0130;
        try {
            url = new URL("http", this.\u0105, 80, string);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String s2 = dataInputStream.readLine();
            for (int n2 = 1; s2 != null; s2 = dataInputStream.readLine(), ++n2) {
                this.\u00d4(s, s2, n2);
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {}
    }
    
    public Image getImage(final String s) {
        Image image;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            if (resourceAsStream == null) {
                System.err.println("Image not found.");
                return null;
            }
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (IOException ex) {
            System.err.println("Unable to read image.");
            ex.printStackTrace();
            return null;
        }
        return image;
    }
    
    public int getIntegerValue(final String s, final String s2) {
        final int n = s.indexOf("<" + s2 + ">", 0) + s2.length() + 2;
        final int index = s.indexOf("</" + s2 + ">", 0);
        if (n > 0 & index > 0) {
            return Integer.parseInt(s.substring(n, index));
        }
        return 0;
    }
    
    public void getPuzzleName() {
        URL url = null;
        final String s = "/servlets/PuzzleDatesNY";
        try {
            url = new URL("http", this.\u0105, 80, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            this.\u0104 = dataInputStream.readLine().trim();
            dataInputStream.readLine().trim();
            dataInputStream.close();
        }
        catch (IOException ex2) {}
    }
    
    void \u00cf(final int n) {
        URL url = null;
        String s = null;
        switch (n) {
            case 1: {
                s = "DAY";
                break;
            }
            case 2: {
                s = "WEEK";
                break;
            }
            case 3: {
                s = "MONTH";
                break;
            }
            case 4: {
                s = "YEAR";
                break;
            }
            default: {
                s = "DAY";
                break;
            }
        }
        this.\u0106 = URLEncoder.encode(this.\u0106);
        final String string = "/servlets/QuickSolvers?loginname=" + this.\u0106 + "&mode=" + s + "&vanaf=" + this.\u0131;
        try {
            url = new URL("http", this.\u0105, 80, string);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String s2 = dataInputStream.readLine();
            for (int n2 = 1; s2 != null; s2 = dataInputStream.readLine(), ++n2) {
                this.\u00d5(s, s2, n2);
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {}
    }
    
    void \u00d0(final int n) {
        URL url = null;
        String s = null;
        switch (n) {
            case 1: {
                s = "DAY";
                break;
            }
            case 2: {
                s = "WEEK";
                break;
            }
            case 3: {
                s = "MONTH";
                break;
            }
            case 4: {
                s = "YEAR";
                break;
            }
            default: {
                s = "DAY";
                break;
            }
        }
        this.\u0106 = URLEncoder.encode(this.\u0106);
        final String string = "/servlets/LatestSolvers?loginname=" + this.\u0106 + "&mode=" + s + "&vanaf=" + this.\u0132;
        try {
            url = new URL("http", this.\u0105, 80, string);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String s2 = dataInputStream.readLine();
            for (int n2 = 1; s2 != null; s2 = dataInputStream.readLine(), ++n2) {
                this.\u00d6(s, s2, n2);
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {}
    }
    
    public String getStringValue(final String s, final String s2) {
        final int n = s.indexOf("<" + s2 + ">", 0) + s2.length() + 2;
        final int index = s.indexOf("</" + s2 + ">", 0);
        if (n > 0 & index > 0) {
            return s.substring(n, index);
        }
        return "";
    }
    
    void \u00d1(final int n) {
        switch (this.\u012e) {
            case 5: {
                this.\u00ce(n);
                break;
            }
            case 6: {
                this.\u00cf(n);
                break;
            }
            case 7: {
                this.\u00d0(n);
                break;
            }
        }
    }
    
    void \u00d2(final int n) {
        switch (n) {
            case 5: {
                this.\u00ce(this.\u012f);
                break;
            }
            case 6: {
                this.\u00cf(this.\u012f);
                break;
            }
            case 7: {
                this.\u00d0(this.\u012f);
                break;
            }
        }
    }
    
    public void init() {
        this.setLayout(null);
        this.setBackground(Color.red);
        this.\u0130 = 1;
        this.\u0131 = 1;
        this.\u0132 = 1;
        this.\u0109 = new MediaTracker(this);
        this.\u0108 = this.getImage(this.getCodeBase(), "graphics/credit.gif");
        this.\u0109.addImage(this.\u0108, 1);
        this.\u00d3();
        this.\u012f = 1;
        this.\u012e = 7;
        this.\u0105 = this.getParameter("host");
        this.\u0106 = this.getParameter("loginname");
        (this.\u0133 = new TextField(this.\u0106)).reshape(35, this.\u00ef - 92, 85, 17);
        this.\u0133.setFont(this.\u00fe);
        this.\u0133.hide();
        (this.\u0134 = new TextField(this.\u0106)).reshape(35, this.\u00ef - 92, 85, 18);
        this.\u0134.setFont(this.\u00fe);
        this.\u0134.hide();
        (this.\u0135 = new TextField(this.\u0106)).reshape(35, this.\u00ef - 92, 85, 19);
        this.\u0135.setFont(this.\u00fe);
        this.\u0135.hide();
        this.\u0107 = this.\u0106;
        this.setBackground(Color.white);
        this.getPuzzleName();
        this.\u00ca();
        this.\u00cb();
        this.\u00cd();
        this.\u00cc();
        this.declareInterface();
        this.\u00c9.add(this.\u0135);
        this.\u00ca.add(this.\u0134);
        this.\u00c8.add(this.\u0133);
        this.\u00d0(1);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch ((char)n) {
            case '\n': {
                this.doSearchName();
                break;
            }
        }
        return false;
    }
    
    void \u00d3() {
        this.\u0114 = this.getImage(this.getCodeBase(), "graphics/search.gif");
        this.\u0109.addImage(this.\u0114, 1);
        this.\u0108 = this.getImage(this.getCodeBase(), "graphics/credit.gif");
        this.\u0109.addImage(this.\u0108, 1);
        this.\u0115 = this.getImage(this.getCodeBase(), "graphics/day.gif");
        this.\u0109.addImage(this.\u0115, 1);
        this.\u0116 = this.getImage(this.getCodeBase(), "graphics/daymd.gif");
        this.\u0109.addImage(this.\u0116, 1);
        this.\u0119 = this.getImage(this.getCodeBase(), "graphics/week.gif");
        this.\u0109.addImage(this.\u0119, 1);
        this.\u011a = this.getImage(this.getCodeBase(), "graphics/weekmd.gif");
        this.\u0109.addImage(this.\u011a, 1);
        this.\u0117 = this.getImage(this.getCodeBase(), "graphics/month.gif");
        this.\u0109.addImage(this.\u0117, 1);
        this.\u0118 = this.getImage(this.getCodeBase(), "graphics/monthmd.gif");
        this.\u0109.addImage(this.\u0118, 1);
        this.\u011b = this.getImage(this.getCodeBase(), "graphics/year.gif");
        this.\u0109.addImage(this.\u011b, 1);
        this.\u011c = this.getImage(this.getCodeBase(), "graphics/yearmd.gif");
        this.\u0109.addImage(this.\u011c, 1);
        this.\u011f = this.getImage(this.getCodeBase(), "graphics/first.gif");
        this.\u0109.addImage(this.\u011f, 1);
        this.\u0120 = this.getImage(this.getCodeBase(), "graphics/firstmd.gif");
        this.\u0109.addImage(this.\u0120, 1);
        this.\u0121 = this.getImage(this.getCodeBase(), "graphics/fastest.gif");
        this.\u0109.addImage(this.\u0121, 1);
        this.\u0122 = this.getImage(this.getCodeBase(), "graphics/fastestmd.gif");
        this.\u0109.addImage(this.\u0122, 1);
        this.\u011d = this.getImage(this.getCodeBase(), "graphics/recent.gif");
        this.\u0109.addImage(this.\u011d, 1);
        this.\u011e = this.getImage(this.getCodeBase(), "graphics/recentmd.gif");
        this.\u0109.addImage(this.\u011e, 1);
        this.\u0123 = this.getImage(this.getCodeBase(), "graphics/headerearly.gif");
        this.\u0109.addImage(this.\u0123, 1);
        this.\u0125 = this.getImage(this.getCodeBase(), "graphics/headerrecent.gif");
        this.\u0109.addImage(this.\u0125, 1);
        this.\u0124 = this.getImage(this.getCodeBase(), "graphics/headerquick.gif");
        this.\u0109.addImage(this.\u0124, 1);
        this.\u0126 = this.getImage(this.getCodeBase(), "graphics/left.gif");
        this.\u0109.addImage(this.\u0126, 1);
        this.\u0127 = this.getImage(this.getCodeBase(), "graphics/right.gif");
        this.\u0109.addImage(this.\u0127, 1);
        try {
            this.\u0109.waitForID(1);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.\u00e6);
        graphics.drawRect(0, 0, 179, this.\u00ef);
    }
    
    void \u00d4(final String s, final String s2, final int n) {
        final String stringValue = this.getStringValue(s2, "LOGINNAMEREQ");
        final String stringValue2 = this.getStringValue(s2, "RANK");
        final String stringValue3 = this.getStringValue(s2, "NAME");
        final int integerValue = this.getIntegerValue(s2, "SOLVINGTIME");
        final String stringValue4 = this.getStringValue(s2, "SUBMITTIME");
        int n2 = 0;
        final int int1 = Integer.parseInt(stringValue2);
        if (n == 1) {
            this.\u0130 = int1;
        }
        if (s.equals("WEEK")) {
            n2 = this.getIntegerValue(s2, "POINTSWEEK");
        }
        if (s.equals("MONTH")) {
            n2 = this.getIntegerValue(s2, "POINTSMONTH");
        }
        if (s.equals("YEAR")) {
            n2 = this.getIntegerValue(s2, "POINTSYEAR");
        }
        this.\u00c9(integerValue);
        if (n <= 10 && !stringValue2.equals("0") && (!stringValue.equals("yes") || (int1 >= this.\u0130 && int1 < this.\u0130 + 10))) {
            this.\u00cf[0][n].setText(String.valueOf(stringValue2) + ".");
            this.\u00cf[1][n].setText(stringValue3);
            if (s.equals("DAY")) {
                this.\u00cf[2][n].setText(stringValue4);
            }
            else {
                this.\u00cf[2][n].setText(String.valueOf(n2));
            }
        }
        if (stringValue.equals("yes")) {
            this.\u00cf[0][12].setText(String.valueOf(stringValue2) + ".");
            this.\u00cf[1][12].setText(stringValue3);
            if (s.equals("DAY")) {
                this.\u00cf[2][12].setText(stringValue4);
            }
            else {
                this.\u00cf[2][12].setText(String.valueOf(n2));
            }
        }
    }
    
    void \u00d5(final String s, final String s2, final int n) {
        final String stringValue = this.getStringValue(s2, "LOGINNAMEREQ");
        final String stringValue2 = this.getStringValue(s2, "RANK");
        final String stringValue3 = this.getStringValue(s2, "NAME");
        final int integerValue = this.getIntegerValue(s2, "SOLVINGTIME");
        this.getStringValue(s2, "SUBMITTIME");
        int n2 = 0;
        final int int1 = Integer.parseInt(stringValue2);
        if (n == 1) {
            this.\u0131 = int1;
        }
        if (s.equals("WEEK")) {
            n2 = this.getIntegerValue(s2, "POINTSWEEK");
        }
        if (s.equals("MONTH")) {
            n2 = this.getIntegerValue(s2, "POINTSMONTH");
        }
        if (s.equals("YEAR")) {
            n2 = this.getIntegerValue(s2, "POINTSYEAR");
        }
        final String \u00e9 = this.\u00c9(integerValue);
        if (n <= 10 && !stringValue2.equals("0") && (!stringValue.equals("yes") || (int1 >= this.\u0131 && int1 < this.\u0131 + 10))) {
            this.\u00d1[0][n].setText(String.valueOf(stringValue2) + ".");
            this.\u00d1[1][n].setText(stringValue3);
            if (s.equals("DAY")) {
                this.\u00d1[2][n].setText(\u00e9);
            }
            else {
                this.\u00d1[2][n].setText(String.valueOf(n2));
            }
        }
        if (stringValue.equals("yes")) {
            this.\u00d1[0][12].setText(String.valueOf(stringValue2) + ".");
            this.\u00d1[1][12].setText(stringValue3);
            if (s.equals("DAY")) {
                this.\u00d1[2][12].setText(\u00e9);
            }
            else {
                this.\u00d1[2][12].setText(String.valueOf(n2));
            }
        }
    }
    
    void \u00d6(final String s, final String s2, final int n) {
        final String stringValue = this.getStringValue(s2, "LOGINNAMEREQ");
        final String stringValue2 = this.getStringValue(s2, "RANK");
        final String stringValue3 = this.getStringValue(s2, "NAME");
        final int integerValue = this.getIntegerValue(s2, "SOLVINGTIME");
        final String stringValue4 = this.getStringValue(s2, "SUBMITTIME");
        int n2 = 0;
        final int int1 = Integer.parseInt(stringValue2);
        if (n == 1) {
            this.\u0132 = int1;
        }
        if (s.equals("WEEK")) {
            n2 = this.getIntegerValue(s2, "POINTSWEEK");
        }
        if (s.equals("MONTH")) {
            n2 = this.getIntegerValue(s2, "POINTSMONTH");
        }
        if (s.equals("YEAR")) {
            n2 = this.getIntegerValue(s2, "POINTSYEAR");
        }
        this.\u00c9(integerValue);
        if (n <= 10 && !stringValue2.equals("0") && (!stringValue.equals("yes") || (int1 >= this.\u0132 && int1 < this.\u0132 + 10))) {
            this.\u00d0[0][n].setText(String.valueOf(stringValue2) + ".");
            this.\u00d0[1][n].setText(stringValue3);
            if (s.equals("DAY")) {
                this.\u00d0[2][n].setText(stringValue4);
            }
            else {
                this.\u00d0[2][n].setText(String.valueOf(n2));
            }
        }
        if (stringValue.equals("yes")) {
            this.\u00d0[0][12].setText(String.valueOf(stringValue2) + ".");
            this.\u00d0[1][12].setText(stringValue3);
            if (s.equals("DAY")) {
                this.\u00d0[2][12].setText(stringValue4);
            }
            else {
                this.\u00d0[2][12].setText(String.valueOf(n2));
            }
        }
    }
    
    void \u00d8(final int \u012f) {
        this.\u012f = \u012f;
        this.\u0111.show(false);
        this.\u0112.show(false);
        this.\u0113.show(false);
        switch (\u012f) {
            case 2: {
                this.\u0111.show(true);
                break;
            }
            case 3: {
                this.\u0112.show(true);
                break;
            }
            case 4: {
                this.\u0113.show(true);
                break;
            }
        }
    }
    
    public void showScreen(final int n, final int n2) {
        if (n2 == 5) {
            this.\u00c8(5);
            this.\u00d2(5);
        }
        if (n2 == 6) {
            this.\u00c8(6);
            this.\u00d2(6);
        }
        if (n2 == 7) {
            this.\u00c8(7);
            this.\u00d2(7);
        }
        if (n == 1) {
            this.\u00c8(this.\u012e);
            this.\u00d1(1);
        }
        if (n == 2) {
            this.\u00c8(this.\u012e);
            this.\u00d1(2);
        }
        if (n == 3) {
            this.\u00c8(this.\u012e);
            this.\u00d1(3);
        }
        if (n == 4) {
            this.\u00c8(this.\u012e);
            this.\u00d1(4);
        }
    }
}

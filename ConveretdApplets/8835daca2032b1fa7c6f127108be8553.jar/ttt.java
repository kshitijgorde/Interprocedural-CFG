import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ttt extends Applet
{
    Button newgame;
    Image backgrd;
    int[] boxes;
    int winner1;
    int winner2;
    boolean found;
    boolean full;
    boolean validresponse;
    String xwins;
    String ywins;
    GridBagLayout thisLayout;
    GridBagConstraints c;
    
    public void init() {
        this.backgrd = this.getImage(this.getCodeBase(), "backgrd.gif");
        this.c.fill = 1;
        this.c.gridx = 12;
        this.c.gridy = 1;
        this.c.gridwidth = 1;
        this.c.gridheight = 1;
        final Button button = new Button("New game");
        this.thisLayout.setConstraints(button, this.c);
        this.add(button);
        this.xwins = "No of wins for X:";
        this.ywins = "No of wins for O:";
        this.reset();
    }
    
    public boolean action(final Event event, final Object o) {
        if ("New game".equals(o)) {
            this.reset();
        }
        return true;
    }
    
    public void reset() {
        this.xwins = "No of wins for X:";
        this.ywins = "No of wins for O:";
        for (int i = 0; i < 30; ++i) {
            this.boxes[i] = 0;
        }
        this.winner1 = 0;
        this.winner2 = 0;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.backgrd, 0, 0, this);
        for (int i = 0; i < 27; ++i) {
            if (this.boxes[i] == 1) {
                this.drawX(graphics, i);
            }
        }
        for (int j = 0; j < 27; ++j) {
            if (this.boxes[j] == 2) {
                this.drawO(graphics, j);
            }
        }
        graphics.setColor(Color.black);
        graphics.drawString(this.xwins, 280, 40);
        graphics.drawString(this.ywins, 280, 60);
        graphics.drawString("3D tic tac toe", 280, 180);
        graphics.drawString("The object of the game is to get", 280, 220);
        graphics.drawString("as many 3 in a rows as possible", 280, 240);
        graphics.drawString("You win just like in tic tac toe", 280, 260);
        graphics.drawString("except you can also win by getting", 280, 280);
        graphics.drawString("3 in a row down each board", 280, 300);
    }
    
    public void drawX(final Graphics graphics, final int n) {
        final int n2 = 50;
        final int n3 = 9;
        final int n4 = 98;
        final int n5 = 17;
        graphics.setColor(Color.white);
        final int n6 = (n - n / 3 * 3) * 50;
        final int n7 = n / 3 * 50;
        if (n < 9) {
            graphics.drawLine(n6, n7, n6 + 50, n7 + 50);
            graphics.drawLine(n6 + 50, n7, n6, n7 + 50);
        }
        if (n > 8 && n < 18) {
            graphics.drawLine(n6 + n2, n7 + n3, n6 + 50 + n2, n7 + 50 + n3);
            graphics.drawLine(n6 + 50 + n2, n7 + n3, n6 + n2, n7 + 50 + n3);
        }
        if (n > 17 && n < 27) {
            graphics.drawLine(n6 + n4, n7 + n5, n6 + 50 + n4, n7 + 50 + n5);
            graphics.drawLine(n6 + 50 + n4, n7 + n5, n6 + n4, n7 + 50 + n5);
        }
    }
    
    public void drawO(final Graphics graphics, final int n) {
        final int n2 = 50;
        final int n3 = 9;
        final int n4 = 98;
        final int n5 = 17;
        graphics.setColor(Color.white);
        final int n6 = (n - n / 3 * 3) * 50;
        final int n7 = n / 3 * 50;
        if (n < 9) {
            graphics.drawOval(n6, n7, 50, 50);
        }
        if (n > 8 && n < 18) {
            graphics.drawOval(n6 + n2, n7 + n3, 50, 50);
        }
        if (n > 17 && n < 27) {
            graphics.drawOval(n6 + n4, n7 + n5, 50, 50);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = 50;
        final int n4 = 9;
        final int n5 = 98;
        final int n6 = 17;
        this.validresponse = false;
        for (int i = 0; i < 9; ++i) {
            final int n7 = i - i / 3 * 3;
            final int n8 = i / 3;
            if (n > n7 * 50 && n < n7 * 50 + 50 && n2 > n8 * 50 && n2 < n8 * 50 + 50 && this.boxes[i] == 0) {
                this.boxes[i] = 1;
                this.validresponse = true;
            }
        }
        for (int j = 9; j < 18; ++j) {
            final int n9 = j - j / 3 * 3;
            final int n10 = j / 3;
            if (n > n9 * 50 + n3 && n < n9 * 50 + 50 + n3 && n2 > n10 * 50 + n4 && n2 < n10 * 50 + 50 + n4 && this.boxes[j] == 0) {
                this.validresponse = true;
                this.boxes[j] = 1;
            }
        }
        for (int k = 18; k < 27; ++k) {
            final int n11 = k - k / 3 * 3;
            final int n12 = k / 3;
            if (n > n11 * 50 + n5 && n < n11 * 50 + 50 + n5 && n2 > n12 * 50 + n6 && n2 < n12 * 50 + 50 + n6 && this.boxes[k] == 0) {
                this.validresponse = true;
                this.boxes[k] = 1;
            }
        }
        if (this.validresponse) {
            this.computermove();
        }
        this.checkwin();
        this.repaint();
        return true;
    }
    
    public void checkfull() {
        this.full = true;
        for (int i = 0; i < 27; ++i) {
            if (this.boxes[i] == 0) {
                this.full = false;
            }
        }
    }
    
    public void computermove() {
        if (!(this.found = false)) {
            this.goforcenter();
        }
        if (!this.found) {
            this.goforwin();
        }
        if (!this.found) {
            this.goforblock();
        }
        if (!this.found) {
            this.putrandom();
        }
    }
    
    public void goforcenter() {
        if (this.boxes[13] == 0) {
            this.boxes[13] = 2;
            this.found = true;
        }
    }
    
    public void goforwin() {
        int n = 0;
        final int n2 = 2;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < 3; ++i) {
            if (this.boxes[i] == n2) {
                ++n4;
            }
        }
        if (n4 == 2) {
            for (int j = 0; j < 3; ++j) {
                if (this.boxes[j] == 0) {
                    n3 = j;
                    n = 1;
                }
            }
        }
        int n5 = 0;
        for (int k = 3; k < 6; ++k) {
            if (this.boxes[k] == n2) {
                ++n5;
            }
        }
        if (n5 == 2) {
            for (int l = 3; l < 6; ++l) {
                if (this.boxes[l] == 0) {
                    n3 = l;
                    n = 1;
                }
            }
        }
        int n6 = 0;
        for (int n7 = 6; n7 < 9; ++n7) {
            if (this.boxes[n7] == n2) {
                ++n6;
            }
        }
        if (n6 == 2) {
            for (int n8 = 6; n8 < 9; ++n8) {
                if (this.boxes[n8] == 0) {
                    n3 = n8;
                    n = 1;
                }
            }
        }
        int n9 = 0;
        for (int n10 = 0; n10 < 7; n10 += 3) {
            if (this.boxes[n10] == n2) {
                ++n9;
            }
        }
        if (n9 == 2) {
            for (int n11 = 0; n11 < 7; n11 += 3) {
                if (this.boxes[n11] == 0) {
                    n3 = n11;
                    n = 1;
                }
            }
        }
        int n12 = 0;
        for (int n13 = 1; n13 < 8; n13 += 3) {
            if (this.boxes[n13] == n2) {
                ++n12;
            }
        }
        if (n12 == 2) {
            for (int n14 = 1; n14 < 8; n14 += 3) {
                if (this.boxes[n14] == 0) {
                    n3 = n14;
                    n = 1;
                }
            }
        }
        int n15 = 0;
        for (int n16 = 2; n16 < 9; n16 += 3) {
            if (this.boxes[n16] == n2) {
                ++n15;
            }
        }
        if (n15 == 2) {
            for (int n17 = 2; n17 < 9; n17 += 3) {
                if (this.boxes[n17] == 0) {
                    n3 = n17;
                    n = 1;
                }
            }
        }
        int n18 = 0;
        for (int n19 = 0; n19 < 9; n19 += 4) {
            if (this.boxes[n19] == n2) {
                ++n18;
            }
        }
        if (n18 == 2) {
            for (int n20 = 0; n20 < 9; n20 += 4) {
                if (this.boxes[n20] == 0) {
                    n3 = n20;
                    n = 1;
                }
            }
        }
        int n21 = 0;
        for (int n22 = 2; n22 < 7; n22 += 2) {
            if (this.boxes[n22] == n2) {
                ++n21;
            }
        }
        if (n21 == 3) {
            for (int n23 = 2; n23 < 7; n23 += 2) {
                if (this.boxes[n23] == 0) {
                    n3 = n23;
                    n = 1;
                }
            }
        }
        int n24 = 0;
        for (int n25 = 9; n25 < 12; ++n25) {
            if (this.boxes[n25] == n2) {
                ++n24;
            }
        }
        if (n24 == 2) {
            for (int n26 = 9; n26 < 12; ++n26) {
                if (this.boxes[n26] == 0) {
                    n3 = n26;
                    n = 1;
                }
            }
        }
        int n27 = 0;
        for (int n28 = 12; n28 < 15; ++n28) {
            if (this.boxes[n28] == n2) {
                ++n27;
            }
        }
        if (n27 == 2) {
            for (int n29 = 12; n29 < 15; ++n29) {
                if (this.boxes[n29] == 0) {
                    n3 = n29;
                    n = 1;
                }
            }
        }
        int n30 = 0;
        for (int n31 = 15; n31 < 18; ++n31) {
            if (this.boxes[n31] == n2) {
                ++n30;
            }
        }
        if (n30 == 2) {
            for (int n32 = 15; n32 < 18; ++n32) {
                if (this.boxes[n32] == 0) {
                    n3 = n32;
                    n = 1;
                }
            }
        }
        int n33 = 0;
        for (int n34 = 9; n34 < 16; n34 += 3) {
            if (this.boxes[n34] == n2) {
                ++n33;
            }
        }
        if (n33 == 2) {
            for (int n35 = 9; n35 < 16; n35 += 3) {
                if (this.boxes[n35] == 0) {
                    n3 = n35;
                    n = 1;
                }
            }
        }
        int n36 = 0;
        for (int n37 = 10; n37 < 17; n37 += 3) {
            if (this.boxes[n37] == n2) {
                ++n36;
            }
        }
        if (n36 == 2) {
            for (int n38 = 10; n38 < 17; n38 += 3) {
                if (this.boxes[n38] == 0) {
                    n3 = n38;
                    n = 1;
                }
            }
        }
        int n39 = 0;
        for (int n40 = 11; n40 < 18; n40 += 3) {
            if (this.boxes[n40] == n2) {
                ++n39;
            }
        }
        if (n39 == 2) {
            for (int n41 = 11; n41 < 18; n41 += 3) {
                if (this.boxes[n41] == 0) {
                    n3 = n41;
                    n = 1;
                }
            }
        }
        int n42 = 0;
        for (int n43 = 9; n43 < 18; n43 += 4) {
            if (this.boxes[n43] == n2) {
                ++n42;
            }
        }
        if (n42 == 2) {
            for (int n44 = 9; n44 < 18; n44 += 4) {
                if (this.boxes[n44] == 0) {
                    n3 = n44;
                    n = 1;
                }
            }
        }
        int n45 = 0;
        for (int n46 = 11; n46 < 16; n46 += 2) {
            if (this.boxes[n46] == n2) {
                ++n45;
            }
        }
        if (n45 == 3) {
            for (int n47 = 11; n47 < 16; n47 += 2) {
                if (this.boxes[n47] == 0) {
                    n3 = n47;
                    n = 1;
                }
            }
        }
        int n48 = 0;
        for (int n49 = 18; n49 < 21; ++n49) {
            if (this.boxes[n49] == n2) {
                ++n48;
            }
        }
        if (n48 == 2) {
            for (int n50 = 18; n50 < 21; ++n50) {
                if (this.boxes[n50] == 0) {
                    n3 = n50;
                    n = 1;
                }
            }
        }
        int n51 = 0;
        for (int n52 = 21; n52 < 24; ++n52) {
            if (this.boxes[n52] == n2) {
                ++n51;
            }
        }
        if (n51 == 2) {
            for (int n53 = 21; n53 < 24; ++n53) {
                if (this.boxes[n53] == 0) {
                    n3 = n53;
                    n = 1;
                }
            }
        }
        int n54 = 0;
        for (int n55 = 24; n55 < 27; ++n55) {
            if (this.boxes[n55] == n2) {
                ++n54;
            }
        }
        if (n54 == 2) {
            for (int n56 = 24; n56 < 27; ++n56) {
                if (this.boxes[n56] == 0) {
                    n3 = n56;
                    n = 1;
                }
            }
        }
        int n57 = 0;
        for (int n58 = 18; n58 < 25; n58 += 3) {
            if (this.boxes[n58] == n2) {
                ++n57;
            }
        }
        if (n57 == 2) {
            for (int n59 = 18; n59 < 25; n59 += 3) {
                if (this.boxes[n59] == 0) {
                    n3 = n59;
                    n = 1;
                }
            }
        }
        int n60 = 0;
        for (int n61 = 19; n61 < 26; n61 += 3) {
            if (this.boxes[n61] == n2) {
                ++n60;
            }
        }
        if (n60 == 2) {
            for (int n62 = 19; n62 < 26; n62 += 3) {
                if (this.boxes[n62] == 0) {
                    n3 = n62;
                    n = 1;
                }
            }
        }
        int n63 = 0;
        for (int n64 = 20; n64 < 27; n64 += 3) {
            if (this.boxes[n64] == n2) {
                ++n63;
            }
        }
        if (n63 == 2) {
            for (int n65 = 20; n65 < 27; n65 += 3) {
                if (this.boxes[n65] == 0) {
                    n3 = n65;
                    n = 1;
                }
            }
        }
        int n66 = 0;
        for (int n67 = 18; n67 < 27; n67 += 4) {
            if (this.boxes[n67] == n2) {
                ++n66;
            }
        }
        if (n66 == 2) {
            for (int n68 = 18; n68 < 27; n68 += 4) {
                if (this.boxes[n68] == 0) {
                    n3 = n68;
                    n = 1;
                }
            }
        }
        int n69 = 0;
        for (int n70 = 20; n70 < 25; n70 += 2) {
            if (this.boxes[n70] == n2) {
                ++n69;
            }
        }
        if (n69 == 2) {
            for (int n71 = 20; n71 < 25; n71 += 2) {
                if (this.boxes[n71] == 0) {
                    n3 = n71;
                    n = 1;
                }
            }
        }
        int n72 = 0;
        for (int n73 = 0; n73 < 19; n73 += 9) {
            if (this.boxes[n73] == n2) {
                ++n72;
            }
        }
        if (n72 == 2) {
            for (int n74 = 0; n74 < 19; n74 += 9) {
                if (this.boxes[n74] == 0) {
                    n3 = n74;
                    n = 1;
                }
            }
        }
        int n75 = 0;
        for (int n76 = 1; n76 < 20; n76 += 9) {
            if (this.boxes[n76] == n2) {
                ++n75;
            }
        }
        if (n75 == 2) {
            for (int n77 = 1; n77 < 20; n77 += 9) {
                if (this.boxes[n77] == 0) {
                    n3 = n77;
                    n = 1;
                }
            }
        }
        int n78 = 0;
        for (int n79 = 2; n79 < 21; n79 += 9) {
            if (this.boxes[n79] == n2) {
                ++n78;
            }
        }
        if (n78 == 2) {
            for (int n80 = 2; n80 < 21; n80 += 9) {
                if (this.boxes[n80] == 0) {
                    n3 = n80;
                    n = 1;
                }
            }
        }
        int n81 = 0;
        for (int n82 = 3; n82 < 22; n82 += 9) {
            if (this.boxes[n82] == n2) {
                ++n81;
            }
        }
        if (n81 == 2) {
            for (int n83 = 3; n83 < 22; n83 += 9) {
                if (this.boxes[n83] == 0) {
                    n3 = n83;
                    n = 1;
                }
            }
        }
        int n84 = 0;
        for (int n85 = 4; n85 < 23; n85 += 9) {
            if (this.boxes[n85] == n2) {
                ++n84;
            }
        }
        if (n84 == 2) {
            for (int n86 = 4; n86 < 23; n86 += 9) {
                if (this.boxes[n86] == 0) {
                    n3 = n86;
                    n = 1;
                }
            }
        }
        int n87 = 0;
        for (int n88 = 5; n88 < 24; n88 += 9) {
            if (this.boxes[n88] == n2) {
                ++n87;
            }
        }
        if (n87 == 2) {
            for (int n89 = 5; n89 < 24; n89 += 9) {
                if (this.boxes[n89] == 0) {
                    n3 = n89;
                    n = 1;
                }
            }
        }
        int n90 = 0;
        for (int n91 = 6; n91 < 25; n91 += 9) {
            if (this.boxes[n91] == n2) {
                ++n90;
            }
        }
        if (n90 == 2) {
            for (int n92 = 6; n92 < 25; n92 += 9) {
                if (this.boxes[n92] == 0) {
                    n3 = n92;
                    n = 1;
                }
            }
        }
        int n93 = 0;
        for (int n94 = 7; n94 < 26; n94 += 9) {
            if (this.boxes[n94] == n2) {
                ++n93;
            }
        }
        if (n93 == 2) {
            for (int n95 = 7; n95 < 26; n95 += 9) {
                if (this.boxes[n95] == 0) {
                    n3 = n95;
                    n = 1;
                }
            }
        }
        int n96 = 0;
        for (int n97 = 8; n97 < 27; n97 += 9) {
            if (this.boxes[n97] == n2) {
                ++n96;
            }
        }
        if (n96 == 2) {
            for (int n98 = 8; n98 < 27; n98 += 9) {
                if (this.boxes[n98] == 0) {
                    n3 = n98;
                    n = 1;
                }
            }
        }
        int n99 = 0;
        for (int n100 = 0; n100 < 25; n100 += 12) {
            if (this.boxes[n100] == n2) {
                ++n99;
            }
        }
        if (n99 == 2) {
            for (int n101 = 0; n101 < 25; n101 += 12) {
                if (this.boxes[n101] == 0) {
                    n3 = n101;
                    n = 1;
                }
            }
        }
        int n102 = 0;
        for (int n103 = 1; n103 < 26; n103 += 12) {
            if (this.boxes[n103] == n2) {
                ++n102;
            }
        }
        if (n102 == 2) {
            for (int n104 = 1; n104 < 26; n104 += 12) {
                if (this.boxes[n104] == 0) {
                    n3 = n104;
                    n = 1;
                }
            }
        }
        int n105 = 0;
        for (int n106 = 2; n106 < 27; n106 += 12) {
            if (this.boxes[n106] == n2) {
                ++n105;
            }
        }
        if (n105 == 2) {
            for (int n107 = 2; n107 < 27; n107 += 12) {
                if (this.boxes[n107] == 0) {
                    n3 = n107;
                    n = 1;
                }
            }
        }
        int n108 = 0;
        for (int n109 = 6; n109 < 19; n109 += 6) {
            if (this.boxes[n109] == n2) {
                ++n108;
            }
        }
        if (n108 == 2) {
            for (int n110 = 6; n110 < 19; n110 += 6) {
                if (this.boxes[n110] == 0) {
                    n3 = n110;
                    n = 1;
                }
            }
        }
        int n111 = 0;
        for (int n112 = 7; n112 < 20; n112 += 6) {
            if (this.boxes[n112] == n2) {
                ++n111;
            }
        }
        if (n111 == 2) {
            for (int n113 = 7; n113 < 20; n113 += 6) {
                if (this.boxes[n113] == 0) {
                    n3 = n113;
                    n = 1;
                }
            }
        }
        int n114 = 0;
        for (int n115 = 8; n115 < 21; n115 += 6) {
            if (this.boxes[n115] == n2) {
                ++n114;
            }
        }
        if (n114 == 2) {
            for (int n116 = 8; n116 < 21; n116 += 6) {
                if (this.boxes[n116] == 0) {
                    n3 = n116;
                    n = 1;
                }
            }
        }
        int n117 = 0;
        for (int n118 = 0; n118 < 21; n118 += 10) {
            if (this.boxes[n118] == n2) {
                ++n117;
            }
        }
        if (n117 == 2) {
            for (int n119 = 0; n119 < 21; n119 += 10) {
                if (this.boxes[n119] == 0) {
                    n3 = n119;
                    n = 1;
                }
            }
        }
        int n120 = 0;
        for (int n121 = 3; n121 < 24; n121 += 10) {
            if (this.boxes[n121] == n2) {
                ++n120;
            }
        }
        if (n120 == 2) {
            for (int n122 = 3; n122 < 24; n122 += 10) {
                if (this.boxes[n122] == 0) {
                    n3 = n122;
                    n = 1;
                }
            }
        }
        int n123 = 0;
        for (int n124 = 6; n124 < 27; n124 += 10) {
            if (this.boxes[n124] == n2) {
                ++n123;
            }
        }
        if (n123 == 2) {
            for (int n125 = 6; n125 < 27; n125 += 10) {
                if (this.boxes[n125] == 0) {
                    n3 = n125;
                    n = 1;
                }
            }
        }
        int n126 = 0;
        for (int n127 = 2; n127 < 19; n127 += 8) {
            if (this.boxes[n127] == n2) {
                ++n126;
            }
        }
        if (n126 == 2) {
            for (int n128 = 2; n128 < 19; n128 += 8) {
                if (this.boxes[n128] == 0) {
                    n3 = n128;
                    n = 1;
                }
            }
        }
        int n129 = 0;
        for (int n130 = 5; n130 < 22; n130 += 8) {
            if (this.boxes[n130] == n2) {
                ++n129;
            }
        }
        if (n129 == 2) {
            for (int n131 = 5; n131 < 22; n131 += 8) {
                if (this.boxes[n131] == 0) {
                    n3 = n131;
                    n = 1;
                }
            }
        }
        int n132 = 0;
        for (int n133 = 8; n133 < 25; n133 += 8) {
            if (this.boxes[n133] == n2) {
                ++n132;
            }
        }
        if (n132 == 2) {
            for (int n134 = 8; n134 < 25; n134 += 8) {
                if (this.boxes[n134] == 0) {
                    n3 = n134;
                    n = 1;
                }
            }
        }
        int n135 = 0;
        for (int n136 = 0; n136 < 27; n136 += 13) {
            if (this.boxes[n136] == n2) {
                ++n135;
            }
        }
        if (n135 == 2) {
            for (int n137 = 0; n137 < 27; n137 += 13) {
                if (this.boxes[n137] == 0) {
                    n3 = n137;
                    n = 1;
                }
            }
        }
        int n138 = 0;
        for (int n139 = 2; n139 < 25; n139 += 11) {
            if (this.boxes[n139] == n2) {
                ++n138;
            }
        }
        if (n138 == 2) {
            for (int n140 = 2; n140 < 25; n140 += 11) {
                if (this.boxes[n140] == 0) {
                    n3 = n140;
                    n = 1;
                }
            }
        }
        int n141 = 0;
        for (int n142 = 6; n142 < 21; n142 += 7) {
            if (this.boxes[n142] == n2) {
                ++n141;
            }
        }
        if (n141 == 2) {
            for (int n143 = 6; n143 < 21; n143 += 7) {
                if (this.boxes[n143] == 0) {
                    n3 = n143;
                    n = 1;
                }
            }
        }
        int n144 = 0;
        for (int n145 = 8; n145 < 19; n145 += 5) {
            if (this.boxes[n145] == n2) {
                ++n144;
            }
        }
        if (n144 == 2) {
            for (int n146 = 8; n146 < 19; n146 += 5) {
                if (this.boxes[n146] == 0) {
                    n3 = n146;
                    n = 1;
                }
            }
        }
        if (n == 1) {
            this.found = true;
            this.boxes[n3] = 2;
        }
    }
    
    public void goforblock() {
        final boolean b = true;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < 3; ++i) {
            if (this.boxes[i] == (b ? 1 : 0)) {
                ++n3;
            }
        }
        if (n3 == 2) {
            for (int j = 0; j < 3; ++j) {
                if (this.boxes[j] == 0) {
                    n = j;
                    n2 = 1;
                }
            }
        }
        int n4 = 0;
        for (int k = 3; k < 6; ++k) {
            if (this.boxes[k] == (b ? 1 : 0)) {
                ++n4;
            }
        }
        if (n4 == 2) {
            for (int l = 3; l < 6; ++l) {
                if (this.boxes[l] == 0) {
                    n = l;
                    n2 = 1;
                }
            }
        }
        int n5 = 0;
        for (int n6 = 6; n6 < 9; ++n6) {
            if (this.boxes[n6] == (b ? 1 : 0)) {
                ++n5;
            }
        }
        if (n5 == 2) {
            for (int n7 = 6; n7 < 9; ++n7) {
                if (this.boxes[n7] == 0) {
                    n = n7;
                    n2 = 1;
                }
            }
        }
        int n8 = 0;
        for (int n9 = 0; n9 < 7; n9 += 3) {
            if (this.boxes[n9] == (b ? 1 : 0)) {
                ++n8;
            }
        }
        if (n8 == 2) {
            for (int n10 = 0; n10 < 7; n10 += 3) {
                if (this.boxes[n10] == 0) {
                    n = n10;
                    n2 = 1;
                }
            }
        }
        int n11 = 0;
        for (int n12 = 1; n12 < 8; n12 += 3) {
            if (this.boxes[n12] == (b ? 1 : 0)) {
                ++n11;
            }
        }
        if (n11 == 2) {
            for (int n13 = 1; n13 < 8; n13 += 3) {
                if (this.boxes[n13] == 0) {
                    n = n13;
                    n2 = 1;
                }
            }
        }
        int n14 = 0;
        for (int n15 = 2; n15 < 9; n15 += 3) {
            if (this.boxes[n15] == (b ? 1 : 0)) {
                ++n14;
            }
        }
        if (n14 == 2) {
            for (int n16 = 2; n16 < 9; n16 += 3) {
                if (this.boxes[n16] == 0) {
                    n = n16;
                    n2 = 1;
                }
            }
        }
        int n17 = 0;
        for (int n18 = 0; n18 < 9; n18 += 4) {
            if (this.boxes[n18] == (b ? 1 : 0)) {
                ++n17;
            }
        }
        if (n17 == 2) {
            for (int n19 = 0; n19 < 9; n19 += 4) {
                if (this.boxes[n19] == 0) {
                    n = n19;
                    n2 = 1;
                }
            }
        }
        int n20 = 0;
        for (int n21 = 2; n21 < 7; n21 += 2) {
            if (this.boxes[n21] == (b ? 1 : 0)) {
                ++n20;
            }
        }
        if (n20 == 3) {
            for (int n22 = 2; n22 < 7; n22 += 2) {
                if (this.boxes[n22] == 0) {
                    n = n22;
                    n2 = 1;
                }
            }
        }
        int n23 = 0;
        for (int n24 = 9; n24 < 12; ++n24) {
            if (this.boxes[n24] == (b ? 1 : 0)) {
                ++n23;
            }
        }
        if (n23 == 2) {
            for (int n25 = 9; n25 < 12; ++n25) {
                if (this.boxes[n25] == 0) {
                    n = n25;
                    n2 = 1;
                }
            }
        }
        int n26 = 0;
        for (int n27 = 12; n27 < 15; ++n27) {
            if (this.boxes[n27] == (b ? 1 : 0)) {
                ++n26;
            }
        }
        if (n26 == 2) {
            for (int n28 = 12; n28 < 15; ++n28) {
                if (this.boxes[n28] == 0) {
                    n = n28;
                    n2 = 1;
                }
            }
        }
        int n29 = 0;
        for (int n30 = 15; n30 < 18; ++n30) {
            if (this.boxes[n30] == (b ? 1 : 0)) {
                ++n29;
            }
        }
        if (n29 == 2) {
            for (int n31 = 15; n31 < 18; ++n31) {
                if (this.boxes[n31] == 0) {
                    n = n31;
                    n2 = 1;
                }
            }
        }
        int n32 = 0;
        for (int n33 = 9; n33 < 16; n33 += 3) {
            if (this.boxes[n33] == (b ? 1 : 0)) {
                ++n32;
            }
        }
        if (n32 == 2) {
            for (int n34 = 9; n34 < 16; n34 += 3) {
                if (this.boxes[n34] == 0) {
                    n = n34;
                    n2 = 1;
                }
            }
        }
        int n35 = 0;
        for (int n36 = 10; n36 < 17; n36 += 3) {
            if (this.boxes[n36] == (b ? 1 : 0)) {
                ++n35;
            }
        }
        if (n35 == 2) {
            for (int n37 = 10; n37 < 17; n37 += 3) {
                if (this.boxes[n37] == 0) {
                    n = n37;
                    n2 = 1;
                }
            }
        }
        int n38 = 0;
        for (int n39 = 11; n39 < 18; n39 += 3) {
            if (this.boxes[n39] == (b ? 1 : 0)) {
                ++n38;
            }
        }
        if (n38 == 2) {
            for (int n40 = 11; n40 < 18; n40 += 3) {
                if (this.boxes[n40] == 0) {
                    n = n40;
                    n2 = 1;
                }
            }
        }
        int n41 = 0;
        for (int n42 = 9; n42 < 18; n42 += 4) {
            if (this.boxes[n42] == (b ? 1 : 0)) {
                ++n41;
            }
        }
        if (n41 == 2) {
            for (int n43 = 9; n43 < 18; n43 += 4) {
                if (this.boxes[n43] == 0) {
                    n = n43;
                    n2 = 1;
                }
            }
        }
        int n44 = 0;
        for (int n45 = 11; n45 < 16; n45 += 2) {
            if (this.boxes[n45] == (b ? 1 : 0)) {
                ++n44;
            }
        }
        if (n44 == 3) {
            for (int n46 = 11; n46 < 16; n46 += 2) {
                if (this.boxes[n46] == 0) {
                    n = n46;
                    n2 = 1;
                }
            }
        }
        int n47 = 0;
        for (int n48 = 18; n48 < 21; ++n48) {
            if (this.boxes[n48] == (b ? 1 : 0)) {
                ++n47;
            }
        }
        if (n47 == 2) {
            for (int n49 = 18; n49 < 21; ++n49) {
                if (this.boxes[n49] == 0) {
                    n = n49;
                    n2 = 1;
                }
            }
        }
        int n50 = 0;
        for (int n51 = 21; n51 < 24; ++n51) {
            if (this.boxes[n51] == (b ? 1 : 0)) {
                ++n50;
            }
        }
        if (n50 == 2) {
            for (int n52 = 21; n52 < 24; ++n52) {
                if (this.boxes[n52] == 0) {
                    n = n52;
                    n2 = 1;
                }
            }
        }
        int n53 = 0;
        for (int n54 = 24; n54 < 27; ++n54) {
            if (this.boxes[n54] == (b ? 1 : 0)) {
                ++n53;
            }
        }
        if (n53 == 2) {
            for (int n55 = 24; n55 < 27; ++n55) {
                if (this.boxes[n55] == 0) {
                    n = n55;
                    n2 = 1;
                }
            }
        }
        int n56 = 0;
        for (int n57 = 18; n57 < 25; n57 += 3) {
            if (this.boxes[n57] == (b ? 1 : 0)) {
                ++n56;
            }
        }
        if (n56 == 2) {
            for (int n58 = 18; n58 < 25; n58 += 3) {
                if (this.boxes[n58] == 0) {
                    n = n58;
                    n2 = 1;
                }
            }
        }
        int n59 = 0;
        for (int n60 = 19; n60 < 26; n60 += 3) {
            if (this.boxes[n60] == (b ? 1 : 0)) {
                ++n59;
            }
        }
        if (n59 == 2) {
            for (int n61 = 19; n61 < 26; n61 += 3) {
                if (this.boxes[n61] == 0) {
                    n = n61;
                    n2 = 1;
                }
            }
        }
        int n62 = 0;
        for (int n63 = 20; n63 < 27; n63 += 3) {
            if (this.boxes[n63] == (b ? 1 : 0)) {
                ++n62;
            }
        }
        if (n62 == 2) {
            for (int n64 = 20; n64 < 27; n64 += 3) {
                if (this.boxes[n64] == 0) {
                    n = n64;
                    n2 = 1;
                }
            }
        }
        int n65 = 0;
        for (int n66 = 18; n66 < 27; n66 += 4) {
            if (this.boxes[n66] == (b ? 1 : 0)) {
                ++n65;
            }
        }
        if (n65 == 2) {
            for (int n67 = 18; n67 < 27; n67 += 4) {
                if (this.boxes[n67] == 0) {
                    n = n67;
                    n2 = 1;
                }
            }
        }
        int n68 = 0;
        for (int n69 = 20; n69 < 25; n69 += 2) {
            if (this.boxes[n69] == (b ? 1 : 0)) {
                ++n68;
            }
        }
        if (n68 == 2) {
            for (int n70 = 20; n70 < 25; n70 += 2) {
                if (this.boxes[n70] == 0) {
                    n = n70;
                    n2 = 1;
                }
            }
        }
        if (n2 == 1) {
            this.found = true;
            this.boxes[n] = 2;
        }
    }
    
    public void putrandom() {
        while (!this.found) {
            final int n = (int)(26.0 * Math.random());
            if (this.boxes[n] == 0) {
                this.found = true;
                this.boxes[n] = 2;
            }
            this.checkfull();
            if (this.full) {
                this.found = true;
            }
        }
    }
    
    void checkwin() {
        this.winner1 = 0;
        this.winner2 = 0;
        int n = 0;
        for (int i = 0; i < 3; ++i) {
            if (this.boxes[i] == 1) {
                ++n;
            }
        }
        if (n == 3) {
            ++this.winner1;
        }
        int n2 = 0;
        for (int j = 3; j < 6; ++j) {
            if (this.boxes[j] == 1) {
                ++n2;
            }
        }
        if (n2 == 3) {
            ++this.winner1;
        }
        int n3 = 0;
        for (int k = 6; k < 9; ++k) {
            if (this.boxes[k] == 1) {
                ++n3;
            }
        }
        if (n3 == 3) {
            ++this.winner1;
        }
        int n4 = 0;
        for (int l = 0; l < 7; l += 3) {
            if (this.boxes[l] == 1) {
                ++n4;
            }
        }
        if (n4 == 3) {
            ++this.winner1;
        }
        int n5 = 0;
        for (int n6 = 1; n6 < 8; n6 += 3) {
            if (this.boxes[n6] == 1) {
                ++n5;
            }
        }
        if (n5 == 3) {
            ++this.winner1;
        }
        int n7 = 0;
        for (int n8 = 2; n8 < 9; n8 += 3) {
            if (this.boxes[n8] == 1) {
                ++n7;
            }
        }
        if (n7 == 3) {
            ++this.winner1;
        }
        int n9 = 0;
        for (int n10 = 0; n10 < 9; n10 += 4) {
            if (this.boxes[n10] == 1) {
                ++n9;
            }
        }
        if (n9 == 3) {
            ++this.winner1;
        }
        int n11 = 0;
        for (int n12 = 2; n12 < 7; n12 += 2) {
            if (this.boxes[n12] == 1) {
                ++n11;
            }
        }
        if (n11 == 3) {
            ++this.winner1;
        }
        int n13 = 0;
        for (int n14 = 0; n14 < 3; ++n14) {
            if (this.boxes[n14] == 2) {
                ++n13;
            }
        }
        if (n13 == 3) {
            ++this.winner2;
        }
        int n15 = 0;
        for (int n16 = 3; n16 < 6; ++n16) {
            if (this.boxes[n16] == 2) {
                ++n15;
            }
        }
        if (n15 == 3) {
            ++this.winner2;
        }
        int n17 = 0;
        for (int n18 = 6; n18 < 9; ++n18) {
            if (this.boxes[n18] == 2) {
                ++n17;
            }
        }
        if (n17 == 3) {
            ++this.winner2;
        }
        int n19 = 0;
        for (int n20 = 0; n20 < 7; n20 += 3) {
            if (this.boxes[n20] == 2) {
                ++n19;
            }
        }
        if (n19 == 3) {
            ++this.winner2;
        }
        int n21 = 0;
        for (int n22 = 1; n22 < 8; n22 += 3) {
            if (this.boxes[n22] == 2) {
                ++n21;
            }
        }
        if (n21 == 3) {
            ++this.winner2;
        }
        int n23 = 0;
        for (int n24 = 2; n24 < 9; n24 += 3) {
            if (this.boxes[n24] == 2) {
                ++n23;
            }
        }
        if (n23 == 3) {
            ++this.winner2;
        }
        int n25 = 0;
        for (int n26 = 0; n26 < 9; n26 += 4) {
            if (this.boxes[n26] == 2) {
                ++n25;
            }
        }
        if (n25 == 3) {
            ++this.winner2;
        }
        int n27 = 0;
        for (int n28 = 2; n28 < 7; n28 += 2) {
            if (this.boxes[n28] == 2) {
                ++n27;
            }
        }
        if (n27 == 3) {
            ++this.winner2;
        }
        int n29 = 0;
        for (int n30 = 9; n30 < 12; ++n30) {
            if (this.boxes[n30] == 1) {
                ++n29;
            }
        }
        if (n29 == 3) {
            ++this.winner1;
        }
        int n31 = 0;
        for (int n32 = 12; n32 < 15; ++n32) {
            if (this.boxes[n32] == 1) {
                ++n31;
            }
        }
        if (n31 == 3) {
            ++this.winner1;
        }
        int n33 = 0;
        for (int n34 = 15; n34 < 18; ++n34) {
            if (this.boxes[n34] == 1) {
                ++n33;
            }
        }
        if (n33 == 3) {
            ++this.winner1;
        }
        int n35 = 0;
        for (int n36 = 9; n36 < 16; n36 += 3) {
            if (this.boxes[n36] == 1) {
                ++n35;
            }
        }
        if (n35 == 3) {
            ++this.winner1;
        }
        int n37 = 0;
        for (int n38 = 10; n38 < 17; n38 += 3) {
            if (this.boxes[n38] == 1) {
                ++n37;
            }
        }
        if (n37 == 3) {
            ++this.winner1;
        }
        int n39 = 0;
        for (int n40 = 11; n40 < 18; n40 += 3) {
            if (this.boxes[n40] == 1) {
                ++n39;
            }
        }
        if (n39 == 3) {
            ++this.winner1;
        }
        int n41 = 0;
        for (int n42 = 9; n42 < 18; n42 += 4) {
            if (this.boxes[n42] == 1) {
                ++n41;
            }
        }
        if (n41 == 3) {
            ++this.winner1;
        }
        int n43 = 0;
        for (int n44 = 11; n44 < 16; n44 += 2) {
            if (this.boxes[n44] == 1) {
                ++n43;
            }
        }
        if (n43 == 3) {
            ++this.winner1;
        }
        int n45 = 0;
        for (int n46 = 9; n46 < 12; ++n46) {
            if (this.boxes[n46] == 2) {
                ++n45;
            }
        }
        if (n45 == 3) {
            ++this.winner2;
        }
        int n47 = 0;
        for (int n48 = 12; n48 < 15; ++n48) {
            if (this.boxes[n48] == 2) {
                ++n47;
            }
        }
        if (n47 == 3) {
            ++this.winner2;
        }
        int n49 = 0;
        for (int n50 = 15; n50 < 18; ++n50) {
            if (this.boxes[n50] == 2) {
                ++n49;
            }
        }
        if (n49 == 3) {
            ++this.winner2;
        }
        int n51 = 0;
        for (int n52 = 9; n52 < 16; n52 += 3) {
            if (this.boxes[n52] == 2) {
                ++n51;
            }
        }
        if (n51 == 3) {
            ++this.winner2;
        }
        int n53 = 0;
        for (int n54 = 10; n54 < 17; n54 += 3) {
            if (this.boxes[n54] == 2) {
                ++n53;
            }
        }
        if (n53 == 3) {
            ++this.winner2;
        }
        int n55 = 0;
        for (int n56 = 11; n56 < 18; n56 += 3) {
            if (this.boxes[n56] == 2) {
                ++n55;
            }
        }
        if (n55 == 3) {
            ++this.winner2;
        }
        int n57 = 0;
        for (int n58 = 9; n58 < 18; n58 += 4) {
            if (this.boxes[n58] == 2) {
                ++n57;
            }
        }
        if (n57 == 3) {
            ++this.winner2;
        }
        int n59 = 0;
        for (int n60 = 11; n60 < 16; n60 += 2) {
            if (this.boxes[n60] == 2) {
                ++n59;
            }
        }
        if (n59 == 3) {
            ++this.winner2;
        }
        int n61 = 0;
        for (int n62 = 18; n62 < 21; ++n62) {
            if (this.boxes[n62] == 1) {
                ++n61;
            }
        }
        if (n61 == 3) {
            ++this.winner1;
        }
        int n63 = 0;
        for (int n64 = 21; n64 < 24; ++n64) {
            if (this.boxes[n64] == 1) {
                ++n63;
            }
        }
        if (n63 == 3) {
            ++this.winner1;
        }
        int n65 = 0;
        for (int n66 = 24; n66 < 27; ++n66) {
            if (this.boxes[n66] == 1) {
                ++n65;
            }
        }
        if (n65 == 3) {
            ++this.winner1;
        }
        int n67 = 0;
        for (int n68 = 18; n68 < 25; n68 += 3) {
            if (this.boxes[n68] == 1) {
                ++n67;
            }
        }
        if (n67 == 3) {
            ++this.winner1;
        }
        int n69 = 0;
        for (int n70 = 19; n70 < 26; n70 += 3) {
            if (this.boxes[n70] == 1) {
                ++n69;
            }
        }
        if (n69 == 3) {
            ++this.winner1;
        }
        int n71 = 0;
        for (int n72 = 20; n72 < 27; n72 += 3) {
            if (this.boxes[n72] == 1) {
                ++n71;
            }
        }
        if (n71 == 3) {
            ++this.winner1;
        }
        int n73 = 0;
        for (int n74 = 18; n74 < 27; n74 += 4) {
            if (this.boxes[n74] == 1) {
                ++n73;
            }
        }
        if (n73 == 3) {
            ++this.winner1;
        }
        int n75 = 0;
        for (int n76 = 20; n76 < 25; n76 += 2) {
            if (this.boxes[n76] == 1) {
                ++n75;
            }
        }
        if (n75 == 3) {
            ++this.winner1;
        }
        int n77 = 0;
        for (int n78 = 18; n78 < 21; ++n78) {
            if (this.boxes[n78] == 2) {
                ++n77;
            }
        }
        if (n77 == 3) {
            ++this.winner2;
        }
        int n79 = 0;
        for (int n80 = 21; n80 < 24; ++n80) {
            if (this.boxes[n80] == 2) {
                ++n79;
            }
        }
        if (n79 == 3) {
            ++this.winner2;
        }
        int n81 = 0;
        for (int n82 = 24; n82 < 27; ++n82) {
            if (this.boxes[n82] == 2) {
                ++n81;
            }
        }
        if (n81 == 3) {
            ++this.winner2;
        }
        int n83 = 0;
        for (int n84 = 18; n84 < 25; n84 += 3) {
            if (this.boxes[n84] == 2) {
                ++n83;
            }
        }
        if (n83 == 3) {
            ++this.winner2;
        }
        int n85 = 0;
        for (int n86 = 19; n86 < 26; n86 += 3) {
            if (this.boxes[n86] == 2) {
                ++n85;
            }
        }
        if (n85 == 3) {
            ++this.winner2;
        }
        int n87 = 0;
        for (int n88 = 20; n88 < 27; n88 += 3) {
            if (this.boxes[n88] == 2) {
                ++n87;
            }
        }
        if (n87 == 3) {
            ++this.winner2;
        }
        int n89 = 0;
        for (int n90 = 18; n90 < 27; n90 += 4) {
            if (this.boxes[n90] == 2) {
                ++n89;
            }
        }
        if (n89 == 3) {
            ++this.winner2;
        }
        int n91 = 0;
        for (int n92 = 20; n92 < 25; n92 += 2) {
            if (this.boxes[n92] == 2) {
                ++n91;
            }
        }
        if (n91 == 3) {
            ++this.winner2;
        }
        int n93 = 0;
        for (int n94 = 0; n94 < 19; n94 += 9) {
            if (this.boxes[n94] == 1) {
                ++n93;
            }
        }
        if (n93 == 3) {
            ++this.winner1;
        }
        int n95 = 0;
        for (int n96 = 1; n96 < 20; n96 += 9) {
            if (this.boxes[n96] == 1) {
                ++n95;
            }
        }
        if (n95 == 3) {
            ++this.winner1;
        }
        int n97 = 0;
        for (int n98 = 2; n98 < 21; n98 += 9) {
            if (this.boxes[n98] == 1) {
                ++n97;
            }
        }
        if (n97 == 3) {
            ++this.winner1;
        }
        int n99 = 0;
        for (int n100 = 3; n100 < 22; n100 += 9) {
            if (this.boxes[n100] == 1) {
                ++n99;
            }
        }
        if (n99 == 3) {
            ++this.winner1;
        }
        int n101 = 0;
        for (int n102 = 4; n102 < 23; n102 += 9) {
            if (this.boxes[n102] == 1) {
                ++n101;
            }
        }
        if (n101 == 3) {
            ++this.winner1;
        }
        int n103 = 0;
        for (int n104 = 5; n104 < 24; n104 += 9) {
            if (this.boxes[n104] == 1) {
                ++n103;
            }
        }
        if (n103 == 3) {
            ++this.winner1;
        }
        int n105 = 0;
        for (int n106 = 6; n106 < 25; n106 += 9) {
            if (this.boxes[n106] == 1) {
                ++n105;
            }
        }
        if (n105 == 3) {
            ++this.winner1;
        }
        int n107 = 0;
        for (int n108 = 7; n108 < 26; n108 += 9) {
            if (this.boxes[n108] == 1) {
                ++n107;
            }
        }
        if (n107 == 3) {
            ++this.winner1;
        }
        int n109 = 0;
        for (int n110 = 8; n110 < 27; n110 += 9) {
            if (this.boxes[n110] == 1) {
                ++n109;
            }
        }
        if (n109 == 3) {
            ++this.winner1;
        }
        int n111 = 0;
        for (int n112 = 0; n112 < 25; n112 += 12) {
            if (this.boxes[n112] == 1) {
                ++n111;
            }
        }
        if (n111 == 3) {
            ++this.winner1;
        }
        int n113 = 0;
        for (int n114 = 1; n114 < 26; n114 += 12) {
            if (this.boxes[n114] == 1) {
                ++n113;
            }
        }
        if (n113 == 3) {
            ++this.winner1;
        }
        int n115 = 0;
        for (int n116 = 2; n116 < 27; n116 += 12) {
            if (this.boxes[n116] == 1) {
                ++n115;
            }
        }
        if (n115 == 3) {
            ++this.winner1;
        }
        int n117 = 0;
        for (int n118 = 6; n118 < 19; n118 += 6) {
            if (this.boxes[n118] == 1) {
                ++n117;
            }
        }
        if (n117 == 3) {
            ++this.winner1;
        }
        int n119 = 0;
        for (int n120 = 7; n120 < 20; n120 += 6) {
            if (this.boxes[n120] == 1) {
                ++n119;
            }
        }
        if (n119 == 3) {
            ++this.winner1;
        }
        int n121 = 0;
        for (int n122 = 8; n122 < 21; n122 += 6) {
            if (this.boxes[n122] == 1) {
                ++n121;
            }
        }
        if (n121 == 3) {
            ++this.winner1;
        }
        int n123 = 0;
        for (int n124 = 0; n124 < 21; n124 += 10) {
            if (this.boxes[n124] == 1) {
                ++n123;
            }
        }
        if (n123 == 3) {
            ++this.winner1;
        }
        int n125 = 0;
        for (int n126 = 3; n126 < 24; n126 += 10) {
            if (this.boxes[n126] == 1) {
                ++n125;
            }
        }
        if (n125 == 3) {
            ++this.winner1;
        }
        int n127 = 0;
        for (int n128 = 6; n128 < 27; n128 += 10) {
            if (this.boxes[n128] == 1) {
                ++n127;
            }
        }
        if (n127 == 3) {
            ++this.winner1;
        }
        int n129 = 0;
        for (int n130 = 2; n130 < 19; n130 += 8) {
            if (this.boxes[n130] == 1) {
                ++n129;
            }
        }
        if (n129 == 3) {
            ++this.winner1;
        }
        int n131 = 0;
        for (int n132 = 5; n132 < 22; n132 += 8) {
            if (this.boxes[n132] == 1) {
                ++n131;
            }
        }
        if (n131 == 3) {
            ++this.winner1;
        }
        int n133 = 0;
        for (int n134 = 8; n134 < 25; n134 += 8) {
            if (this.boxes[n134] == 1) {
                ++n133;
            }
        }
        if (n133 == 3) {
            ++this.winner1;
        }
        int n135 = 0;
        for (int n136 = 0; n136 < 27; n136 += 13) {
            if (this.boxes[n136] == 1) {
                ++n135;
            }
        }
        if (n135 == 3) {
            ++this.winner1;
        }
        int n137 = 0;
        for (int n138 = 2; n138 < 25; n138 += 11) {
            if (this.boxes[n138] == 1) {
                ++n137;
            }
        }
        if (n137 == 3) {
            ++this.winner1;
        }
        int n139 = 0;
        for (int n140 = 6; n140 < 21; n140 += 7) {
            if (this.boxes[n140] == 1) {
                ++n139;
            }
        }
        if (n139 == 3) {
            ++this.winner1;
        }
        int n141 = 0;
        for (int n142 = 8; n142 < 19; n142 += 5) {
            if (this.boxes[n142] == 1) {
                ++n141;
            }
        }
        if (n141 == 3) {
            ++this.winner1;
        }
        int n143 = 0;
        for (int n144 = 0; n144 < 19; n144 += 9) {
            if (this.boxes[n144] == 2) {
                ++n143;
            }
        }
        if (n143 == 3) {
            ++this.winner2;
        }
        int n145 = 0;
        for (int n146 = 1; n146 < 20; n146 += 9) {
            if (this.boxes[n146] == 2) {
                ++n145;
            }
        }
        if (n145 == 3) {
            ++this.winner2;
        }
        int n147 = 0;
        for (int n148 = 2; n148 < 21; n148 += 9) {
            if (this.boxes[n148] == 2) {
                ++n147;
            }
        }
        if (n147 == 3) {
            ++this.winner2;
        }
        int n149 = 0;
        for (int n150 = 3; n150 < 22; n150 += 9) {
            if (this.boxes[n150] == 2) {
                ++n149;
            }
        }
        if (n149 == 3) {
            ++this.winner2;
        }
        int n151 = 0;
        for (int n152 = 4; n152 < 23; n152 += 9) {
            if (this.boxes[n152] == 2) {
                ++n151;
            }
        }
        if (n151 == 3) {
            ++this.winner2;
        }
        int n153 = 0;
        for (int n154 = 5; n154 < 24; n154 += 9) {
            if (this.boxes[n154] == 2) {
                ++n153;
            }
        }
        if (n153 == 3) {
            ++this.winner2;
        }
        int n155 = 0;
        for (int n156 = 6; n156 < 25; n156 += 9) {
            if (this.boxes[n156] == 2) {
                ++n155;
            }
        }
        if (n155 == 3) {
            ++this.winner2;
        }
        int n157 = 0;
        for (int n158 = 7; n158 < 26; n158 += 9) {
            if (this.boxes[n158] == 2) {
                ++n157;
            }
        }
        if (n157 == 3) {
            ++this.winner2;
        }
        int n159 = 0;
        for (int n160 = 8; n160 < 27; n160 += 9) {
            if (this.boxes[n160] == 2) {
                ++n159;
            }
        }
        if (n159 == 3) {
            ++this.winner2;
        }
        int n161 = 0;
        for (int n162 = 0; n162 < 25; n162 += 12) {
            if (this.boxes[n162] == 2) {
                ++n161;
            }
        }
        if (n161 == 3) {
            ++this.winner2;
        }
        int n163 = 0;
        for (int n164 = 1; n164 < 26; n164 += 12) {
            if (this.boxes[n164] == 2) {
                ++n163;
            }
        }
        if (n163 == 3) {
            ++this.winner2;
        }
        int n165 = 0;
        for (int n166 = 2; n166 < 27; n166 += 12) {
            if (this.boxes[n166] == 2) {
                ++n165;
            }
        }
        if (n165 == 3) {
            ++this.winner2;
        }
        int n167 = 0;
        for (int n168 = 6; n168 < 19; n168 += 6) {
            if (this.boxes[n168] == 2) {
                ++n167;
            }
        }
        if (n167 == 3) {
            ++this.winner2;
        }
        int n169 = 0;
        for (int n170 = 7; n170 < 20; n170 += 6) {
            if (this.boxes[n170] == 2) {
                ++n169;
            }
        }
        if (n169 == 3) {
            ++this.winner2;
        }
        int n171 = 0;
        for (int n172 = 8; n172 < 21; n172 += 6) {
            if (this.boxes[n172] == 2) {
                ++n171;
            }
        }
        if (n171 == 3) {
            ++this.winner2;
        }
        int n173 = 0;
        for (int n174 = 0; n174 < 21; n174 += 10) {
            if (this.boxes[n174] == 2) {
                ++n173;
            }
        }
        if (n173 == 3) {
            ++this.winner2;
        }
        int n175 = 0;
        for (int n176 = 3; n176 < 24; n176 += 10) {
            if (this.boxes[n176] == 2) {
                ++n175;
            }
        }
        if (n175 == 3) {
            ++this.winner2;
        }
        int n177 = 0;
        for (int n178 = 6; n178 < 27; n178 += 10) {
            if (this.boxes[n178] == 2) {
                ++n177;
            }
        }
        if (n177 == 3) {
            ++this.winner2;
        }
        int n179 = 0;
        for (int n180 = 2; n180 < 19; n180 += 8) {
            if (this.boxes[n180] == 2) {
                ++n179;
            }
        }
        if (n179 == 3) {
            ++this.winner2;
        }
        int n181 = 0;
        for (int n182 = 5; n182 < 22; n182 += 8) {
            if (this.boxes[n182] == 2) {
                ++n181;
            }
        }
        if (n181 == 3) {
            ++this.winner2;
        }
        int n183 = 0;
        for (int n184 = 8; n184 < 25; n184 += 8) {
            if (this.boxes[n184] == 2) {
                ++n183;
            }
        }
        if (n183 == 3) {
            ++this.winner2;
        }
        int n185 = 0;
        for (int n186 = 0; n186 < 27; n186 += 13) {
            if (this.boxes[n186] == 2) {
                ++n185;
            }
        }
        if (n185 == 3) {
            ++this.winner2;
        }
        int n187 = 0;
        for (int n188 = 2; n188 < 25; n188 += 11) {
            if (this.boxes[n188] == 2) {
                ++n187;
            }
        }
        if (n187 == 3) {
            ++this.winner2;
        }
        int n189 = 0;
        for (int n190 = 6; n190 < 21; n190 += 7) {
            if (this.boxes[n190] == 2) {
                ++n189;
            }
        }
        if (n189 == 3) {
            ++this.winner2;
        }
        int n191 = 0;
        for (int n192 = 8; n192 < 19; n192 += 5) {
            if (this.boxes[n192] == 2) {
                ++n191;
            }
        }
        if (n191 == 3) {
            ++this.winner2;
        }
        this.xwins = "No of wins for X: " + this.winner1;
        this.ywins = "No of wins for O: " + this.winner2;
    }
    
    public ttt() {
        this.boxes = new int[30];
        this.thisLayout = new GridBagLayout();
        this.c = new GridBagConstraints();
    }
}

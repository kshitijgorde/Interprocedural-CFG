import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.AudioClip;
import java.awt.Color;
import java.util.Vector;
import java.awt.image.BufferedImage;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jigsaw extends Applet
{
    BufferedImage bmain;
    BufferedImage templedge;
    BufferedImage bprevclip;
    piece[][] p;
    Vector clusarr;
    options o;
    mresp mr;
    int xprev;
    int yprev;
    boolean ifclip;
    boolean somethingtodraw;
    boolean stopclip;
    boolean ready;
    Color bgcol;
    AudioClip click;
    String folder;
    String newpage;
    
    public jigsaw() {
        this.bmain = null;
        this.bprevclip = null;
        this.xprev = 0;
        this.yprev = 0;
        this.ifclip = false;
        this.somethingtodraw = false;
        this.stopclip = false;
        this.ready = true;
    }
    
    public void init() {
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = true;
        final String[] array = { this.getParameter("npieceshor"), this.getParameter("npiecesver"), this.getParameter("picture"), this.getParameter("faded"), this.getParameter("popup"), this.getParameter("rotations"), this.getParameter("resize"), this.getParameter("percent"), this.getParameter("bgcol"), this.getParameter("optionswindow"), this.getParameter("folder"), this.getParameter("newpage"), this.getParameter("picturelist") };
        final String getint = this.getint(array[0], "4");
        final String getint2 = this.getint(array[1], "3");
        final String getint3 = this.getint(array[7], "30");
        if (array[3] != null && (array[3].equals("yes") || array[3].equals("Yes") || array[3].equals("YES"))) {
            b = true;
        }
        if (array[4] != null && (array[4].equals("yes") || array[4].equals("Yes") || array[4].equals("YES"))) {
            b2 = true;
        }
        if (array[5] != null && (array[5].equals("yes") || array[5].equals("Yes") || array[5].equals("YES"))) {
            b3 = true;
        }
        if (array[6] != null && (array[6].equals("yes") || array[6].equals("Yes") || array[6].equals("YES"))) {
            b4 = true;
        }
        if (array[9] != null && (array[9].equals("no") || array[9].equals("No") || array[9].equals("NO"))) {
            b5 = false;
        }
        if (array[8] != null && array[8].length() == 6) {
            this.bgcol = new Color(16 * this.hex2d(array[8].substring(0, 1)) + this.hex2d(array[8].substring(1, 2)), 16 * this.hex2d(array[8].substring(2, 3)) + this.hex2d(array[8].substring(3, 4)), 16 * this.hex2d(array[8].substring(4, 5)) + this.hex2d(array[8].substring(5, 6)));
        }
        else {
            this.bgcol = Color.white;
        }
        String s;
        if (array[2] != null) {
            s = array[2];
        }
        else {
            s = "";
        }
        if (array[10] != null) {
            this.folder = array[10];
        }
        else {
            this.folder = "pictures";
        }
        if (array[11] != null) {
            this.newpage = array[11];
        }
        else {
            this.newpage = "";
        }
        String s2;
        if (array[12] != null) {
            s2 = array[12];
        }
        else {
            s2 = "";
        }
        this.click = this.getAudioClip(this.getCodeBase(), "click.wav");
        this.addMouseListener(this.mr = new mresp(this));
        this.addMouseMotionListener(this.mr);
        cluster.context = this;
        this.o = new options(this, b3, b4, b2, b, this.bgcol, getint, getint2, getint3, this.folder, s, s2);
        this.templedge = this.openimage("templedge.png");
        this.o.pack();
        if (b5) {
            this.o.setVisible(true);
        }
        else {
            this.o.scramble();
        }
    }
    
    public void stop() {
        this.o.setVisible(false);
        this.o.dispose();
    }
    
    BufferedImage openimage(final String s) {
        BufferedImage bufferedImage = null;
        boolean b = true;
        final Image image = this.getImage(this.getCodeBase(), s);
        if (image == null) {
            b = false;
        }
        else {
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                b = false;
            }
        }
        if (image.getWidth(this) < 0) {
            b = false;
        }
        if (b) {
            bufferedImage = new BufferedImage(image.getWidth(this), image.getHeight(this), 2);
            bufferedImage.createGraphics().drawImage(image, 0, 0, this);
        }
        return bufferedImage;
    }
    
    void transbmain() {
        final int n = 1610612736;
        if (this.bmain != null) {
            for (int i = 0; i < this.bmain.getWidth(); ++i) {
                for (int j = 0; j < this.bmain.getHeight(); ++j) {
                    this.bmain.setRGB(i, j, n + (0xFFFFFF & this.bmain.getRGB(i, j)));
                }
            }
        }
    }
    
    boolean setim(final String s) {
        this.bmain = this.openimage(this.folder + "/" + s);
        if (this.bmain == null) {
            return false;
        }
        this.transbmain();
        return true;
    }
    
    void setinitpos(final piece[][] array) {
        final int n = this.getSize().width - piece.pw - 2 * piece.wover;
        final int n2 = this.getSize().height - piece.ph - 2 * piece.hover;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                array[i][j].ashift = i * piece.pw;
                array[i][j].bshift = j * piece.ph;
            }
        }
        for (int k = 0; k < array.length; ++k) {
            for (int l = 0; l < array[k].length; ++l) {
                final int n3 = (int)(Math.random() * array.length);
                final int n4 = (int)(Math.random() * array[k].length);
                final piece piece = array[k][l];
                array[k][l] = array[n3][n4];
                array[n3][n4] = piece;
            }
        }
        for (int n5 = 0; n5 < array.length; ++n5) {
            for (int n6 = 0; n6 < array[n5].length; ++n6) {
                array[n5][n6].posx = ((array.length == 0) ? 0 : (n5 * n / (array.length - 1)));
                array[n5][n6].posy = ((array[n5].length == 0) ? 0 : (n6 * n2 / (array[n5].length - 1)));
            }
        }
    }
    
    void scramble(final int n, final int n2) {
        piece.pw = this.bmain.getWidth() / n;
        piece.wover = piece.pw / 4;
        if (cluster.ifrot) {
            piece.ph = piece.pw;
        }
        else {
            piece.ph = this.bmain.getHeight() / n2;
        }
        piece.hover = piece.ph / 4;
        this.bmain = this.bmain.getSubimage((this.bmain.getWidth() - piece.pw * n) / 2, (this.bmain.getHeight() - piece.ph * n2) / 2, piece.pw * n, piece.ph * n2);
        int n3 = n;
        int n4 = n2;
        if (cluster.ifrot) {
            if (n3 > n4) {
                n4 = n3;
            }
            else {
                n3 = n4;
            }
        }
        cluster.cliparr = new BufferedImage[n3][];
        for (int i = 0; i < n3; ++i) {
            cluster.cliparr[i] = new BufferedImage[n4];
        }
        cluster.cliparr[0][0] = new BufferedImage(piece.pw + 3 * piece.wover, piece.ph + 3 * piece.hover, 2);
        this.somethingtodraw = true;
        this.clusarr = new Vector();
        this.mr.setclus(this.clusarr);
        this.p = new piece[n][];
        for (int j = 0; j < n; ++j) {
            this.p[j] = new piece[n2];
        }
        for (int k = 0; k < n; ++k) {
            for (int l = 0; l < n2; ++l) {
                this.p[k][l] = new piece(k, l);
                this.clusarr.addElement(this.p[k][l]);
            }
        }
        for (int n5 = 0; n5 < n; ++n5) {
            this.p[n5][0].inn = 0;
        }
        for (int n6 = 0; n6 < n; ++n6) {
            this.p[n6][n2 - 1].ins = 0;
        }
        for (int n7 = 0; n7 < n2; ++n7) {
            this.p[0][n7].inw = 0;
        }
        for (int n8 = 0; n8 < n2; ++n8) {
            this.p[n - 1][n8].ine = 0;
        }
        for (int n9 = 1; n9 < n; ++n9) {
            for (int n10 = 0; n10 < n2; ++n10) {
                if (Math.random() < 0.5) {
                    this.p[n9 - 1][n10].ine = 1;
                    this.p[n9][n10].inw = -1;
                }
                else {
                    this.p[n9 - 1][n10].ine = -1;
                    this.p[n9][n10].inw = 1;
                }
            }
        }
        for (int n11 = 0; n11 < n; ++n11) {
            for (int n12 = 1; n12 < n2; ++n12) {
                if (Math.random() < 0.5) {
                    this.p[n11][n12 - 1].ins = 1;
                    this.p[n11][n12].inn = -1;
                }
                else {
                    this.p[n11][n12 - 1].ins = -1;
                    this.p[n11][n12].inn = 1;
                }
            }
        }
        final boolean[][] array = new boolean[this.templedge.getWidth()][];
        for (int n13 = 0; n13 < array.length; ++n13) {
            array[n13] = new boolean[2 * this.templedge.getHeight()];
            for (int n14 = 0; n14 < this.templedge.getHeight(); ++n14) {
                array[n13][n14] = ((this.templedge.getRGB(n13, this.templedge.getHeight() - n14 - 1) & 0x1) == 0x1);
                array[n13][n14 + this.templedge.getHeight()] = ((0xFFFFFF & this.templedge.getRGB(n13, n14)) != 0x0);
            }
        }
        final boolean[][] array2 = new boolean[2 * this.templedge.getWidth()][];
        for (int n15 = 0; n15 < this.templedge.getWidth(); ++n15) {
            array2[n15] = new boolean[this.templedge.getHeight()];
            for (int n16 = 0; n16 < array2[n15].length; ++n16) {
                array2[n15][n16] = ((0xFFFFFF & this.templedge.getRGB(n16, this.templedge.getWidth() - n15 - 1)) != 0x0);
            }
        }
        for (int width = this.templedge.getWidth(); width < 2 * this.templedge.getWidth(); ++width) {
            array2[width] = new boolean[this.templedge.getHeight()];
            for (int n17 = 0; n17 < array2[width].length; ++n17) {
                array2[width][n17] = ((0xFFFFFF & this.templedge.getRGB(n17, width - this.templedge.getWidth())) != 0x0);
            }
        }
        final int[][] array3 = new int[piece.pw][];
        for (int n18 = 0; n18 < array3.length; ++n18) {
            array3[n18] = new int[2 * piece.hover];
        }
        final int[][] array4 = new int[2 * piece.wover][];
        for (int n19 = 0; n19 < array4.length; ++n19) {
            array4[n19] = new int[piece.ph];
        }
        for (int n20 = 0; n20 < array2.length; ++n20) {
            for (int n21 = 0; n21 < array2[n20].length; ++n21) {
                final int n22 = n20 * piece.pw / array2.length;
                final int n23 = n21 * 2 * piece.hover / array2[n20].length;
                final int[] array5 = array3[n22];
                final int n24 = n23;
                array5[n24] += (array2[n20][n21] ? 65281 : 1);
            }
        }
        for (int n25 = 0; n25 < array3.length; ++n25) {
            for (int n26 = 0; n26 < array3[n25].length; ++n26) {
                final int n27 = array3[n25][n26];
                if (n27 > 0) {
                    array3[n25][n26] = (n27 >> 8) / (n27 & 0xFF);
                }
            }
        }
        for (int n28 = 0; n28 < array.length; ++n28) {
            for (int n29 = 0; n29 < array[n28].length; ++n29) {
                final int n30 = n28 * 2 * piece.wover / array.length;
                final int n31 = n29 * piece.ph / array[n28].length;
                final int[] array6 = array4[n30];
                final int n32 = n31;
                array6[n32] += (array[n28][n29] ? 65281 : 1);
            }
        }
        for (int n33 = 0; n33 < array4.length; ++n33) {
            for (int n34 = 0; n34 < array4[n33].length; ++n34) {
                final int n35 = array4[n33][n34];
                if (n35 > 0) {
                    array4[n33][n34] = (n35 >> 8) / (n35 & 0xFF);
                }
            }
        }
        this.setinitpos(this.p);
        for (int n36 = 0; n36 < n; ++n36) {
            for (int n37 = 0; n37 < n2; ++n37) {
                final int n38 = (this.p[n36][n37].inw == 0) ? 0 : (-piece.wover);
                final int n39 = (this.p[n36][n37].ine == 0) ? piece.pw : (piece.pw + piece.wover);
                final int n40 = (this.p[n36][n37].inn == 0) ? 0 : (-piece.hover);
                final int n41 = (this.p[n36][n37].ins == 0) ? piece.ph : (piece.ph + piece.hover);
                for (int n42 = n38; n42 < n39; ++n42) {
                    for (int n43 = n40; n43 < n41; ++n43) {
                        this.p[n36][n37].b.setRGB(n42 + piece.wover, n43 + piece.hover, this.bmain.getRGB(this.p[n36][n37].ashift + n42, this.p[n36][n37].bshift + n43));
                    }
                }
                for (int n44 = 0; n44 < piece.wover; ++n44) {
                    for (int n45 = 0; n45 < piece.hover; ++n45) {
                        this.p[n36][n37].b.setRGB(n44, n45, this.p[n36][n37].b.getRGB(n44, n45) & 0xFFFFFF);
                    }
                }
                for (int n46 = 0; n46 < piece.wover; ++n46) {
                    for (int n47 = piece.ph + piece.hover; n47 < piece.ph + 2 * piece.hover; ++n47) {
                        this.p[n36][n37].b.setRGB(n46, n47, this.p[n36][n37].b.getRGB(n46, n47) & 0xFFFFFF);
                    }
                }
                for (int n48 = piece.pw + piece.wover; n48 < piece.pw + 2 * piece.wover; ++n48) {
                    for (int n49 = 0; n49 < piece.hover; ++n49) {
                        this.p[n36][n37].b.setRGB(n48, n49, this.p[n36][n37].b.getRGB(n48, n49) & 0xFFFFFF);
                    }
                }
                for (int n50 = piece.pw + piece.wover; n50 < piece.pw + 2 * piece.wover; ++n50) {
                    for (int n51 = piece.ph + piece.hover; n51 < piece.ph + 2 * piece.hover; ++n51) {
                        this.p[n36][n37].b.setRGB(n50, n51, this.p[n36][n37].b.getRGB(n50, n51) & 0xFFFFFF);
                    }
                }
                for (int n52 = 2 * piece.wover; n52 < piece.pw; ++n52) {
                    for (int n53 = 2 * piece.hover; n53 < piece.ph; ++n53) {
                        this.p[n36][n37].b.setRGB(n52, n53, (this.p[n36][n37].b.getRGB(n52, n53) & 0xFFFFFF) - 16777216);
                    }
                }
                for (int wover = piece.wover; wover < piece.pw + piece.wover; ++wover) {
                    for (int n54 = 0; n54 < 2 * piece.hover; ++n54) {
                        if (this.p[n36][n37].inn == 1) {
                            this.p[n36][n37].b.setRGB(wover, n54, (this.p[n36][n37].b.getRGB(wover, n54) & 0xFFFFFF) + (array3[wover - piece.wover][n54] << 24));
                        }
                        if (this.p[n36][n37].inn == -1) {
                            this.p[n36][n37].b.setRGB(wover, n54, (this.p[n36][n37].b.getRGB(wover, n54) & 0xFFFFFF) + (255 - array3[wover - piece.wover][2 * piece.hover - 1 - n54] << 24));
                        }
                        if (this.p[n36][n37].inn == 0) {
                            if (n54 < piece.hover) {
                                this.p[n36][n37].b.setRGB(wover, n54, this.p[n36][n37].b.getRGB(wover, n54) & 0xFFFFFF);
                            }
                            else {
                                this.p[n36][n37].b.setRGB(wover, n54, (this.p[n36][n37].b.getRGB(wover, n54) & 0xFFFFFF) - 16777216);
                            }
                        }
                    }
                }
                for (int wover2 = piece.wover; wover2 < piece.pw + piece.wover; ++wover2) {
                    for (int ph = piece.ph; ph < piece.ph + 2 * piece.hover; ++ph) {
                        if (this.p[n36][n37].ins == 1) {
                            this.p[n36][n37].b.setRGB(wover2, ph, (this.p[n36][n37].b.getRGB(wover2, ph) & 0xFFFFFF) + (array3[wover2 - piece.wover][piece.ph + 2 * piece.hover - 1 - ph] << 24));
                        }
                        if (this.p[n36][n37].ins == -1) {
                            this.p[n36][n37].b.setRGB(wover2, ph, (this.p[n36][n37].b.getRGB(wover2, ph) & 0xFFFFFF) + (255 - array3[wover2 - piece.wover][ph - piece.ph] << 24));
                        }
                        if (this.p[n36][n37].ins == 0) {
                            if (ph < piece.ph + piece.hover) {
                                this.p[n36][n37].b.setRGB(wover2, ph, (this.p[n36][n37].b.getRGB(wover2, ph) & 0xFFFFFF) - 16777216);
                            }
                            else {
                                this.p[n36][n37].b.setRGB(wover2, ph, this.p[n36][n37].b.getRGB(wover2, ph) & 0xFFFFFF);
                            }
                        }
                    }
                }
                for (int n55 = 0; n55 < piece.wover; ++n55) {
                    for (int hover = piece.hover; hover < piece.ph + piece.hover; ++hover) {
                        if (this.p[n36][n37].inw == 1) {
                            this.p[n36][n37].b.setRGB(n55, hover, (this.p[n36][n37].b.getRGB(n55, hover) & 0xFFFFFF) + (array4[n55][hover - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].inw == -1) {
                            this.p[n36][n37].b.setRGB(n55, hover, (this.p[n36][n37].b.getRGB(n55, hover) & 0xFFFFFF) + (255 - array4[2 * piece.wover - 1 - n55][hover - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].inw == 0) {
                            if (n55 < piece.wover) {
                                this.p[n36][n37].b.setRGB(n55, hover, this.p[n36][n37].b.getRGB(n55, hover) & 0xFFFFFF);
                            }
                            else {
                                this.p[n36][n37].b.setRGB(n55, hover, (this.p[n36][n37].b.getRGB(n55, hover) & 0xFFFFFF) - 16777216);
                            }
                        }
                    }
                }
                for (int wover3 = piece.wover; wover3 < 2 * piece.wover; ++wover3) {
                    for (int n56 = 2 * piece.hover; n56 < piece.ph; ++n56) {
                        if (this.p[n36][n37].inw == 1) {
                            this.p[n36][n37].b.setRGB(wover3, n56, (this.p[n36][n37].b.getRGB(wover3, n56) & 0xFFFFFF) + (array4[wover3][n56 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].inw == -1) {
                            this.p[n36][n37].b.setRGB(wover3, n56, (this.p[n36][n37].b.getRGB(wover3, n56) & 0xFFFFFF) + (255 - array4[2 * piece.wover - 1 - wover3][n56 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].inw == 0) {
                            if (wover3 < piece.wover) {
                                this.p[n36][n37].b.setRGB(wover3, n56, this.p[n36][n37].b.getRGB(wover3, n56) & 0xFFFFFF);
                            }
                            else {
                                this.p[n36][n37].b.setRGB(wover3, n56, (this.p[n36][n37].b.getRGB(wover3, n56) & 0xFFFFFF) - 16777216);
                            }
                        }
                    }
                }
                for (int wover4 = piece.wover; wover4 < 2 * piece.wover; ++wover4) {
                    for (int hover2 = piece.hover; hover2 < 2 * piece.hover; ++hover2) {
                        final int n57 = this.p[n36][n37].b.getRGB(wover4, hover2) >>> 24;
                        if (this.p[n36][n37].inw == 1) {
                            this.p[n36][n37].b.setRGB(wover4, hover2, (this.p[n36][n37].b.getRGB(wover4, hover2) & 0xFFFFFF) + (n57 - 255 + array4[wover4][hover2 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].inw == -1) {
                            this.p[n36][n37].b.setRGB(wover4, hover2, (this.p[n36][n37].b.getRGB(wover4, hover2) & 0xFFFFFF) + (n57 - array4[2 * piece.wover - 1 - wover4][hover2 - piece.hover] << 24));
                        }
                    }
                }
                for (int wover5 = piece.wover; wover5 < 2 * piece.wover; ++wover5) {
                    for (int ph2 = piece.ph; ph2 < piece.ph + piece.hover; ++ph2) {
                        final int n58 = this.p[n36][n37].b.getRGB(wover5, ph2) >>> 24;
                        if (this.p[n36][n37].inw == 1) {
                            this.p[n36][n37].b.setRGB(wover5, ph2, (this.p[n36][n37].b.getRGB(wover5, ph2) & 0xFFFFFF) + (n58 - 255 + array4[wover5][ph2 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].inw == -1) {
                            this.p[n36][n37].b.setRGB(wover5, ph2, (this.p[n36][n37].b.getRGB(wover5, ph2) & 0xFFFFFF) + (n58 - array4[2 * piece.wover - 1 - wover5][ph2 - piece.hover] << 24));
                        }
                    }
                }
                for (int n59 = piece.pw + piece.wover; n59 < piece.pw + 2 * piece.wover; ++n59) {
                    for (int hover3 = piece.hover; hover3 < piece.ph + piece.hover; ++hover3) {
                        if (this.p[n36][n37].ine == 1) {
                            this.p[n36][n37].b.setRGB(n59, hover3, (this.p[n36][n37].b.getRGB(n59, hover3) & 0xFFFFFF) + (array4[piece.pw + 2 * piece.wover - 1 - n59][hover3 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].ine == -1) {
                            this.p[n36][n37].b.setRGB(n59, hover3, (this.p[n36][n37].b.getRGB(n59, hover3) & 0xFFFFFF) + (255 - array4[n59 - piece.pw][hover3 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].ine == 0) {
                            if (n59 < piece.pw + piece.wover) {
                                this.p[n36][n37].b.setRGB(n59, hover3, (this.p[n36][n37].b.getRGB(n59, hover3) & 0xFFFFFF) - 16777216);
                            }
                            else {
                                this.p[n36][n37].b.setRGB(n59, hover3, this.p[n36][n37].b.getRGB(n59, hover3) & 0xFFFFFF);
                            }
                        }
                    }
                }
                for (int pw = piece.pw; pw < piece.pw + piece.wover; ++pw) {
                    for (int n60 = 2 * piece.hover; n60 < piece.ph; ++n60) {
                        if (this.p[n36][n37].ine == 1) {
                            this.p[n36][n37].b.setRGB(pw, n60, (this.p[n36][n37].b.getRGB(pw, n60) & 0xFFFFFF) + (array4[piece.pw + 2 * piece.wover - 1 - pw][n60 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].ine == -1) {
                            this.p[n36][n37].b.setRGB(pw, n60, (this.p[n36][n37].b.getRGB(pw, n60) & 0xFFFFFF) + (255 - array4[pw - piece.pw][n60 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].ine == 0) {
                            if (pw < piece.pw + piece.wover) {
                                this.p[n36][n37].b.setRGB(pw, n60, (this.p[n36][n37].b.getRGB(pw, n60) & 0xFFFFFF) - 16777216);
                            }
                            else {
                                this.p[n36][n37].b.setRGB(pw, n60, this.p[n36][n37].b.getRGB(pw, n60) & 0xFFFFFF);
                            }
                        }
                    }
                }
                for (int pw2 = piece.pw; pw2 < piece.pw + piece.wover; ++pw2) {
                    for (int hover4 = piece.hover; hover4 < 2 * piece.hover; ++hover4) {
                        final int n61 = this.p[n36][n37].b.getRGB(pw2, hover4) >>> 24;
                        if (this.p[n36][n37].ine == 1) {
                            this.p[n36][n37].b.setRGB(pw2, hover4, (this.p[n36][n37].b.getRGB(pw2, hover4) & 0xFFFFFF) + (n61 - 255 + array4[piece.pw + 2 * piece.wover - 1 - pw2][hover4 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].ine == -1) {
                            this.p[n36][n37].b.setRGB(pw2, hover4, (this.p[n36][n37].b.getRGB(pw2, hover4) & 0xFFFFFF) + (n61 - array4[pw2 - piece.pw][hover4 - piece.hover] << 24));
                        }
                    }
                }
                for (int pw3 = piece.pw; pw3 < piece.pw + piece.wover; ++pw3) {
                    for (int ph3 = piece.ph; ph3 < piece.ph + piece.hover; ++ph3) {
                        final int n62 = this.p[n36][n37].b.getRGB(pw3, ph3) >>> 24;
                        if (this.p[n36][n37].ine == 1) {
                            this.p[n36][n37].b.setRGB(pw3, ph3, (this.p[n36][n37].b.getRGB(pw3, ph3) & 0xFFFFFF) + (n62 - 255 + array4[piece.pw + 2 * piece.wover - 1 - pw3][ph3 - piece.hover] << 24));
                        }
                        if (this.p[n36][n37].ine == -1) {
                            this.p[n36][n37].b.setRGB(pw3, ph3, (this.p[n36][n37].b.getRGB(pw3, ph3) & 0xFFFFFF) + (n62 - array4[pw3 - piece.pw][ph3 - piece.hover] << 24));
                        }
                    }
                }
                if (piece.ifrot) {
                    final int n63 = (int)(Math.random() * 4.0);
                    if (n63 == 1) {
                        this.p[n36][n37].rotate(true);
                    }
                    if (n63 == 2) {
                        this.p[n36][n37].rotate(true);
                        this.p[n36][n37].rotate(true);
                    }
                    if (n63 == 3) {
                        this.p[n36][n37].rotate(false);
                    }
                }
                this.paint(this.getGraphics());
            }
        }
        System.out.println("" + (this.p[0][0].b.getRGB(75, 75) >>> 24));
        System.out.println("" + (this.p[1][0].b.getRGB(15, 75) >>> 24));
        System.out.println("" + (this.p[0][1].b.getRGB(75, 15) >>> 24));
        System.out.println("" + (this.p[1][1].b.getRGB(15, 15) >>> 24));
    }
    
    void setprev(final int xprev, final int yprev) {
        this.xprev = xprev;
        this.yprev = yprev;
    }
    
    void paintclip(final boolean b) {
        if (b) {
            this.ifclip = true;
        }
        else if (this.clusarr != null) {
            this.stopclip = true;
            this.bprevclip = this.clusarr.lastElement().clip;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.ifclip) {
            final int n = (this.xprev < this.clusarr.lastElement().posx) ? this.xprev : this.clusarr.lastElement().posx;
            final int n2 = (this.yprev < this.clusarr.lastElement().posy) ? this.yprev : this.clusarr.lastElement().posy;
            if (this.stopclip) {
                this.drawclip(graphics, this.bprevclip, this.xprev, this.yprev);
            }
            else if (this.xprev - n > piece.wover || this.yprev - n2 > piece.hover || this.clusarr.lastElement().posx - n > piece.wover || this.clusarr.lastElement().posy - n2 > piece.hover) {
                this.drawclip(graphics, this.clusarr.lastElement().clip, this.xprev, this.yprev);
                this.drawclip(graphics, this.clusarr.lastElement().clip, this.clusarr.lastElement().posx, this.clusarr.lastElement().posy);
            }
            else {
                this.drawclip(graphics, this.clusarr.lastElement().clip, n, n2);
            }
            this.setprev(this.clusarr.lastElement().posx, this.clusarr.lastElement().posy);
        }
        else if (this.somethingtodraw) {
            final BufferedImage bufferedImage = cluster.cliparr[0][0];
            for (int i = 0; i < this.getSize().width; i += bufferedImage.getWidth()) {
                for (int j = 0; j < this.getSize().height; j += bufferedImage.getHeight()) {
                    this.drawclip(graphics, bufferedImage, i, j);
                }
            }
        }
        if (this.stopclip) {
            this.stopclip = false;
            this.ifclip = false;
        }
        this.ready = true;
    }
    
    void drawclip(final Graphics graphics, final BufferedImage bufferedImage, final int n, final int n2) {
        final Graphics2D graphics2 = bufferedImage.createGraphics();
        graphics2.setColor(this.bgcol);
        graphics2.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        if (this.o.cb1.getState()) {
            graphics2.drawImage(this.bmain, (this.getSize().width - this.bmain.getWidth()) / 2 - n, (this.getSize().height - this.bmain.getHeight()) / 2 - n2, this);
        }
        for (int i = 0; i < this.clusarr.size(); ++i) {
            ((cluster)this.clusarr.elementAt(i)).paint(graphics2, n, n2);
        }
        graphics.drawImage(bufferedImage, n, n2, this);
    }
    
    String getint(final String s, final String s2) {
        if (s == null) {
            return s2;
        }
        try {
            new Integer(s);
        }
        catch (NumberFormatException ex) {
            return s2;
        }
        return s;
    }
    
    int hex2d(final String s) {
        if (s.equals("1")) {
            return 1;
        }
        if (s.equals("2")) {
            return 2;
        }
        if (s.equals("3")) {
            return 3;
        }
        if (s.equals("4")) {
            return 4;
        }
        if (s.equals("5")) {
            return 5;
        }
        if (s.equals("6")) {
            return 6;
        }
        if (s.equals("7")) {
            return 7;
        }
        if (s.equals("8")) {
            return 8;
        }
        if (s.equals("9")) {
            return 9;
        }
        if (s.equals("a")) {
            return 10;
        }
        if (s.equals("b")) {
            return 11;
        }
        if (s.equals("c")) {
            return 12;
        }
        if (s.equals("d")) {
            return 13;
        }
        if (s.equals("e")) {
            return 14;
        }
        if (s.equals("f")) {
            return 15;
        }
        if (s.equals("A")) {
            return 10;
        }
        if (s.equals("B")) {
            return 11;
        }
        if (s.equals("C")) {
            return 12;
        }
        if (s.equals("D")) {
            return 13;
        }
        if (s.equals("E")) {
            return 14;
        }
        if (s.equals("F")) {
            return 15;
        }
        return 0;
    }
}

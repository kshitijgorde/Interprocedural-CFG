import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Skin
{
    NewsPro npro;
    int skinType;
    Color textcolor;
    Color groupcolor;
    Color titlecolor;
    Color bgcolor;
    Color ilinkcolor;
    Color alinkcolor;
    Font textfont;
    Font groupfont;
    Font titlefont;
    String image;
    Image bgimage;
    private int pixX;
    int pixY;
    int textOffset;
    int groupOffset;
    int btOffset;
    int[] bgxco;
    int[] bgyco;
    int[][][] btxco;
    int[][][] btyco;
    Image iconImage;
    
    public Skin(final NewsPro npro) {
        this.pixY = 300;
        this.bgxco = new int[] { 0, 200, 0, 200 };
        this.bgyco = new int[] { 0, 250, 0, 250 };
        this.btxco = new int[4][3][4];
        this.btyco = new int[4][3][4];
        this.npro = npro;
        this.loadSkin(npro.getParameter("Skin"));
    }
    
    private void fillArrays(final int pixY, final int n, final int n2) {
        for (int i = 0; i < 4; ++i) {
            if (i > 1) {
                this.pixY = pixY;
                if (i == 2) {
                    this.pixX = 0;
                }
            }
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (k == 0) {
                        this.btxco[i][j][k] = 0;
                        this.btyco[i][j][k] = 0;
                    }
                    if (k == 1) {
                        this.btxco[i][j][k] = n;
                        this.btyco[i][j][k] = n2;
                    }
                    if (k == 2) {
                        this.btxco[i][j][k] = this.pixX;
                        this.btyco[i][j][k] = this.pixY;
                    }
                    if (k == 3) {
                        this.btxco[i][j][k] = this.pixX + n;
                        this.btyco[i][j][k] = this.pixY + n2;
                    }
                }
                this.pixX += n;
            }
        }
    }
    
    public Image getBgimage() {
        return this.bgimage;
    }
    
    public Color getColor(final String s) {
        if (s.equals("bgcolor")) {
            return this.bgcolor;
        }
        if (s.equals("textcolor")) {
            return this.textcolor;
        }
        if (s.equals("titlecolor")) {
            return this.titlecolor;
        }
        if (s.equals("groupcolor")) {
            return this.groupcolor;
        }
        if (s.equals("alinkcolor")) {
            return this.alinkcolor;
        }
        if (s.equals("ilinkcolor")) {
            return this.ilinkcolor;
        }
        return Color.yellow;
    }
    
    public Font getFont(final String s) {
        if (s.equals("textfont")) {
            return this.textfont;
        }
        if (s.equals("groupfont")) {
            return this.groupfont;
        }
        if (s.equals("titlefont")) {
            return this.titlefont;
        }
        return new Font("Verdana", 2, 12);
    }
    
    public Image getIconImage() {
        return this.iconImage;
    }
    
    public int getOffset(final String s) {
        if (s.equals("groupOffset")) {
            return this.groupOffset;
        }
        if (s.equals("textOffset")) {
            return this.textOffset;
        }
        return this.btOffset;
    }
    
    private void getProperties() {
        double n = 1.0;
        switch (this.skinType) {
            case 5: {
                this.image = "skin5.gif";
                this.textcolor = new Color(0, 255, 0);
                this.bgcolor = new Color(0, 0, 0);
                this.ilinkcolor = new Color(255, 255, 0);
                this.alinkcolor = new Color(255, 0, 255);
                this.titlecolor = new Color(255, 255, 0);
                this.groupcolor = new Color(255, 255, 255);
                this.textfont = new Font("Arial", 0, 10);
                this.titlefont = new Font("Arial", 1, 10);
                this.groupfont = new Font("Arial", 1, 12);
                this.textOffset = 40;
                this.btOffset = 10;
                this.groupOffset = 185;
                this.fillArrays(320, 25, 15);
                this.bgyco[3] = 200;
                this.bgxco[3] = 150;
                n = this.npro.dim.height / this.bgyco[3];
                this.textOffset = (int)(40.0 * n);
                this.groupOffset = (int)(185.0 * n);
                break;
            }
            case 4: {
                this.image = "skin4.gif";
                this.textcolor = new Color(255, 255, 255);
                this.bgcolor = new Color(0, 0, 0);
                this.ilinkcolor = new Color(0, 0, 255);
                this.alinkcolor = new Color(0, 0, 255);
                this.titlecolor = new Color(255, 255, 255);
                this.groupcolor = new Color(255, 255, 0);
                this.textfont = new Font("Arial", 0, 12);
                this.titlefont = new Font("Arial", 1, 12);
                this.groupfont = new Font("Arial", 1, 14);
                this.textOffset = 40;
                this.groupOffset = 20;
                this.bgyco[1] = 300;
                this.bgyco[3] = 300;
                this.bgxco[1] = 300;
                this.bgxco[3] = 300;
                this.btOffset = this.npro.dim.height - 30;
                n = this.npro.dim.height / this.bgyco[3];
                this.textOffset = (int)(40.0 * n);
                this.groupOffset = (int)(20.0 * n);
                this.fillArrays(320, 25, 15);
                break;
            }
            case 1: {
                this.image = "skin1.gif";
                this.textcolor = new Color(0, 0, 0);
                this.bgcolor = new Color(255, 255, 255);
                this.ilinkcolor = new Color(0, 0, 122);
                this.alinkcolor = new Color(0, 0, 255);
                this.titlecolor = new Color(0, 0, 122);
                this.groupcolor = new Color(255, 255, 255);
                this.textfont = new Font("Arial", 0, 10);
                this.titlefont = new Font("Arial", 1, 12);
                this.groupfont = new Font("Arial", 1, 14);
                this.textOffset = 40;
                this.groupOffset = 20;
                this.btOffset = this.npro.dim.height - 30;
                n = this.npro.dim.height / this.bgyco[3];
                this.textOffset = (int)(40.0 * n);
                this.groupOffset = (int)(20.0 * n);
                this.fillArrays(320, 25, 15);
                break;
            }
            case 2: {
                this.image = "skin2.gif";
                this.textcolor = new Color(0, 0, 0);
                this.bgcolor = new Color(255, 255, 255);
                this.ilinkcolor = new Color(0, 0, 122);
                this.alinkcolor = new Color(0, 0, 255);
                this.titlecolor = new Color(0, 0, 122);
                this.groupcolor = new Color(255, 255, 255);
                this.textfont = new Font("Arial", 0, 10);
                this.titlefont = new Font("Arial", 1, 12);
                this.groupfont = new Font("Arial", 1, 14);
                this.textOffset = 40;
                this.groupOffset = 20;
                this.btOffset = this.npro.dim.height - 30;
                this.bgyco[1] = 200;
                this.bgyco[3] = 200;
                n = this.npro.dim.height / this.bgyco[3];
                this.textOffset = (int)(40.0 * n);
                this.groupOffset = (int)(20.0 * n);
                this.fillArrays(320, 25, 15);
                break;
            }
            case 3: {
                this.image = "skin3.gif";
                this.textcolor = new Color(0, 0, 50);
                this.bgcolor = new Color(184, 191, 254);
                this.ilinkcolor = new Color(0, 0, 122);
                this.alinkcolor = new Color(0, 0, 255);
                this.titlecolor = new Color(0, 0, 122);
                this.groupcolor = new Color(255, 255, 255);
                this.textfont = new Font("Arial", 0, 10);
                this.titlefont = new Font("Arial", 1, 12);
                this.groupfont = new Font("Comic Sans MS", 1, 12);
                this.btOffset = this.npro.dim.height - 30;
                this.bgyco[1] = 200;
                this.bgyco[3] = 200;
                this.bgxco[3] = 150;
                n = this.npro.dim.height / this.bgyco[3];
                this.textOffset = (int)(40.0 * n);
                this.groupOffset = (int)(25.0 * n);
                this.fillArrays(320, 25, 15);
                break;
            }
            default: {
                this.image = this.npro.getParameter("bgimage");
                this.textcolor = new Color(255, 200, 200);
                this.bgcolor = new Color(0, 0, 0);
                this.ilinkcolor = new Color(200, 100, 100);
                this.alinkcolor = new Color(100, 100, 100);
                this.titlecolor = new Color(255, 255, 255);
                this.groupcolor = new Color(100, 50, 30);
                this.textfont = new Font("Arial", 0, 12);
                this.titlefont = new Font("Arial", 1, 12);
                this.groupfont = new Font("Arial", 1, 14);
                this.textOffset = 40;
                this.groupOffset = 20;
                break;
            }
        }
        System.out.println("Var: " + n);
    }
    
    public void load() {
        this.bgimage = this.npro.loadMedia(this.image);
        this.iconImage = this.npro.loadMedia("smilies.gif");
    }
    
    private void loadSkin(final String s) {
        if (s.toUpperCase().equals("SKIN1")) {
            this.skinType = 1;
        }
        else if (s.toUpperCase().equals("SKIN2")) {
            this.skinType = 2;
        }
        else if (s.toUpperCase().equals("SKIN3")) {
            this.skinType = 3;
        }
        else if (s.toUpperCase().equals("SKIN4")) {
            this.skinType = 4;
        }
        else if (s.toUpperCase().equals("SKIN5")) {
            this.skinType = 5;
        }
        else {
            this.skinType = 0;
        }
        this.getProperties();
    }
}

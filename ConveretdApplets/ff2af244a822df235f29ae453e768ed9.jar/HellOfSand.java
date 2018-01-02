// 
// Decompiled by Procyon v0.5.30
// 

public class HellOfSand extends BApplet
{
    int counter;
    int SAND_COLOR;
    int WATER_COLOR;
    int FIRE_COLOR;
    int BACK_COLOR;
    int WALL_COLOR;
    int PLANT_COLOR;
    int SMOKE_COLOR;
    int SPOUT_COLOR;
    int CERA_COLOR;
    int CERA2_COLOR;
    int UNIDENT_COLOR;
    int OIL_COLOR;
    int SOLT_WATER_COLOR;
    int TREE_COLOR;
    int TREE2_COLOR;
    int SALT_COLOR;
    int SNIG_COLOR;
    int SNIG2_COLOR;
    int SNIG3_COLOR;
    int HUMAN_COLOR;
    int HUMAN2_COLOR;
    int WIDTH;
    int HEIGHT;
    int WH;
    int SHEIGHT;
    int RESOURCE_N;
    String[] resource_text;
    int[] resource_color;
    int pen_num;
    int MENU_N;
    int MENU_PEN;
    int MENU_TIME;
    int MENU_SAND;
    int MENU_WATER;
    int MENU_OIL;
    int MENU_SALT;
    int MENU_SNIG;
    int MENU_HUMAN;
    String[] menu_text;
    int[] menu_count;
    String[][] menu_level_text;
    int[] menu_level_max;
    int[] pen_size_list;
    Ball ball;
    int killed;
    Particles par;
    Human[] humans;
    MassPoint[] holds;
    MassPointLink[] snig2human;
    int meat_eater_stock;
    int plant_eater_stock;
    Tree tree;
    boolean pmousePressed;
    boolean nmousePressed;
    Vec mouse_pos;
    int PAR_MAX;
    
    void fillScreen(final int n) {
        for (int i = 0; i < this.WH; ++i) {
            this.pixels[i] = n;
        }
    }
    
    boolean overRect(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return n5 >= n && n5 <= n + n3 && n6 >= n2 && n6 <= n2 + n4;
    }
    
    void setup() {
        this.size(400, 480);
        this.WIDTH = this.width;
        this.HEIGHT = this.height - this.SHEIGHT;
        this.WH = this.WIDTH * this.HEIGHT;
        this.colorMode(1, 255.0f, 255.0f, 255.0f);
        this.fillScreen(this.BACK_COLOR);
        this.textFont(this.loadFont("RotisSanSer-Bold.vlw.gz"), 20.0f);
        this.framerate(50.0f);
        (this.ball = new Ball(32, 10.0f)).trans(new Vec(32.0f, 200.0f));
        this.par = new Particles();
        this.humans = new Human[50];
        for (int i = 0; i < this.humans.length; ++i) {
            this.humans[i] = new Human();
        }
        this.holds = new MassPoint[20];
        this.snig2human = new MassPointLink[200];
    }
    
    void pset(final int n, final int n2, final int n3) {
        switch (this.pixels[n + n2]) {
            case -1127296: {
                int n4;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n4 = n + this.WIDTH + n2] == -16777216 || this.pixels[n4 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n4 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n4 = n + n2 - 1] == -16777216 || this.pixels[n4 = n + n2 + 1] == -16777216)) {
                    this.pixels[n4] = -1127296;
                    this.pixels[n + n2] = -16777216;
                }
                else if (this.random(0.0f, 1.0f) < 0.25f && this.pixels[n + this.WIDTH + n2] == -14671617) {
                    this.pixels[n + this.WIDTH + n2] = -1127296;
                    this.pixels[n + n2] = -14671617;
                }
                break;
            }
            case -14671617: {
                int n5;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n5 = n + this.WIDTH + n2] == -16777216 || this.pixels[n5 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n5 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n5 = n + n2 + 1] == -16777216 || this.pixels[n5 = n + n2 - 1] == -16777216)) {
                    this.pixels[n5] = -14671617;
                    this.pixels[n + n2] = -16777216;
                }
                int n6;
                if (this.pixels[n6 = n + this.WIDTH + n2] == -4464 || this.pixels[n6 = n + n2 + 1] == -4464 || this.pixels[n6 = n + n2 - 1] == -4464) {
                    this.pixels[n6] = -16777216;
                    this.ball.water(n2, n3);
                }
                else {
                    int n7;
                    if (this.pixels[n7 = n + this.WIDTH + n2] == -4463 || this.pixels[n7 = n + n2 + 1] == -4463 || this.pixels[n7 = n + n2 - 1] == -4463) {
                        this.pixels[n7] = -16777216;
                        this.ball.water(n2, n3);
                    }
                }
                break;
            }
            case -12549889: {
                int n8;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n8 = n + this.WIDTH + n2] == -16777216 || this.pixels[n8 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n8 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n8 = n + n2 + 1] == -16777216 || this.pixels[n8 = n + n2 - 1] == -16777216)) {
                    this.pixels[n8] = -12549889;
                    this.pixels[n + n2] = -16777216;
                }
                else if (this.random(0.0f, 1.0f) < 0.5f && this.pixels[n + this.WIDTH + n2] == -14671617) {
                    this.pixels[n + this.WIDTH + n2] = -12549889;
                    this.pixels[n + n2] = -14671617;
                }
                int n9;
                if (this.pixels[n9 = n + this.WIDTH + n2] == -4464 || this.pixels[n9 = n + n2 + 1] == -4464 || this.pixels[n9 = n + n2 - 1] == -4464) {
                    this.pixels[n9] = -16777216;
                    this.ball.salt_water(n2, n3);
                }
                else {
                    int n10;
                    if (this.pixels[n10 = n + this.WIDTH + n2] == -4463 || this.pixels[n10 = n + n2 + 1] == -4463 || this.pixels[n10 = n + n2 - 1] == -4463) {
                        this.pixels[n10] = -16777216;
                        this.ball.salt_water(n2, n3);
                    }
                }
                break;
            }
            case -65281: {
                if (this.random(0.0f, 1.0f) < 0.65f) {
                    final int n11;
                    if (this.pixels[n11 = n + this.WIDTH + n2] != -65536 && this.pixels[n11] != -14627808) {
                        this.pixels[n11] = -65281;
                    }
                    final int n12;
                    if (this.pixels[n12 = n - this.WIDTH + n2] != -65536 && this.pixels[n12] != -14627808) {
                        this.pixels[n12] = -65281;
                    }
                    final int n13;
                    if (this.pixels[n13 = n + 1 + n2] != -65536 && this.pixels[n13] != -14627808) {
                        this.pixels[n13] = -65281;
                    }
                    final int n14;
                    if (this.pixels[n14 = n - 1 + n2] != -65536 && this.pixels[n14] != -14627808) {
                        this.pixels[n14] = -65281;
                    }
                }
                else {
                    this.pixels[n + n2] = -65536;
                }
                break;
            }
            case -65536: {
                if (this.random(0.0f, 1.0f) < 0.02f) {
                    this.pixels[n + n2] = -16777216;
                }
                break;
            }
            case -1122868: {
                if (this.random(0.0f, 1.0f) < 0.01f && (this.pixels[n - this.WIDTH + n2] == -49088 || this.pixels[n + n2 + 1] == -49088 || this.pixels[n + n2 - 1] == -49088 || this.pixels[n + n2 + this.WIDTH] == -49088)) {
                    this.pixels[n + n2] = -49088;
                    int n15;
                    if (this.pixels[n15 = n + this.WIDTH + n2] == -16777216 || this.pixels[n15 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n15 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n15 = n + n2 + 1] == -16777216 || this.pixels[n15 = n + n2 - 1] == -16777216) {
                        this.pixels[n15] = -1122867;
                    }
                }
                break;
            }
            case -1122867: {
                if (this.random(0.0f, 1.0f) < 0.8f) {
                    int n16;
                    if (this.pixels[n16 = n + this.WIDTH + n2] == -16777216 || this.pixels[n16 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n16 = n + n2 + this.WIDTH + 1] == -16777216) {
                        this.pixels[n16] = -1122867;
                        this.pixels[n + n2] = -16777216;
                    }
                    else {
                        this.pixels[n + n2] = -1122868;
                    }
                }
                break;
            }
            case -49088: {
                final int n17 = (int)this.random(-1.0f, 1.001f);
                if (this.random(0.0f, 1.0f) < 0.5f && this.pixels[n + n2 - this.WIDTH + n17] == -16777216) {
                    this.pixels[n + n2 - this.WIDTH + n17] = -49088;
                }
                if (this.random(0.0f, 1.0f) < 0.4f && this.pixels[n + n2 - this.WIDTH] != -14627808 && this.pixels[n + n2 + this.WIDTH] != -14627808 && this.pixels[n + n2 - 1] != -14627808 && this.pixels[n + n2 + 1] != -14627808 && this.pixels[n + n2 - this.WIDTH] != -1122868 && this.pixels[n + n2 + this.WIDTH] != -1122868 && this.pixels[n + n2 - 1] != -1122868 && this.pixels[n + n2 + 1] != -1122868) {
                    this.pixels[n + n2] = -16777216;
                }
                int n18;
                if (this.random(0.0f, 1.0f) < 0.9f && (this.pixels[n18 = n + n2 + 1] == -14671617 || this.pixels[n18 = n + n2 - 1] == -14671617 || this.pixels[n18 = n + n2 + this.WIDTH] == -14671617)) {
                    this.pixels[n18] = -16777216;
                    this.pixels[n + n2] = -16777216;
                }
                break;
            }
            case -14627808: {
                if (this.random(0.0f, 1.0f) < 0.2f && (this.pixels[n - this.WIDTH + n2] == -49088 || this.pixels[n + n2 + 1] == -49088 || this.pixels[n + n2 - 1] == -49088 || this.pixels[n + n2 + this.WIDTH] == -49088)) {
                    this.pixels[n + n2] = -49088;
                }
                if (this.random(0.0f, 1.0f) < 0.1f) {
                    final int n19;
                    if (this.pixels[n19 = n - this.WIDTH + n2] == -14671617) {
                        this.pixels[n19] = -14627808;
                    }
                    final int n20;
                    if (this.pixels[n20 = n + this.WIDTH + n2] == -14671617) {
                        this.pixels[n20] = -14627808;
                    }
                    final int n21;
                    if (this.pixels[n21 = n + 1 + n2] == -14671617) {
                        this.pixels[n21] = -14627808;
                    }
                    final int n22;
                    if (this.pixels[n22 = n - 1 + n2] == -14671617) {
                        this.pixels[n22] = -14627808;
                    }
                }
                break;
            }
            case -9408400: {
                if (this.random(0.0f, 1.0f) < 0.01f) {
                    this.pixels[n + n2] = -16777216;
                }
                int n23;
                if (this.random(0.0f, 1.0f) < 0.5f && (this.pixels[n23 = n - this.WIDTH + n2] == -16777216 || this.pixels[n23 = n + n2 - this.WIDTH - 1] == -16777216 || this.pixels[n23 = n + n2 - this.WIDTH + 1] == -16777216 || this.pixels[n23 = n + n2 + 1] == -16777216 || this.pixels[n23 = n + n2 - 1] == -16777216)) {
                    this.pixels[n23] = -9408400;
                    this.pixels[n + n2] = -16777216;
                }
                break;
            }
            case -9395969: {
                if (this.random(0.0f, 1.0f) < 0.05f) {
                    final int n24;
                    if (this.pixels[n24 = n - this.WIDTH + n2] == -16777216) {
                        this.pixels[n24] = -14671617;
                    }
                    final int n25;
                    if (this.pixels[n25 = n + this.WIDTH + n2] == -16777216) {
                        this.pixels[n25] = -14671617;
                    }
                    final int n26;
                    if (this.pixels[n26 = n + 1 + n2] == -16777216) {
                        this.pixels[n26] = -14671617;
                    }
                    final int n27;
                    if (this.pixels[n27 = n - 1 + n2] == -16777216) {
                        this.pixels[n27] = -14671617;
                    }
                }
                if (this.random(0.0f, 1.0f) < 0.1f) {
                    if (this.pixels[n - this.WIDTH + n2] == -1127296) {
                        this.pixels[n + n2] = -1127296;
                    }
                    if (this.pixels[n + this.WIDTH + n2] == -1127296) {
                        this.pixels[n + n2] = -1127296;
                    }
                    if (this.pixels[n + 1 + n2] == -1127296) {
                        this.pixels[n + n2] = -1127296;
                    }
                    if (this.pixels[n - 1 + n2] == -1127296) {
                        this.pixels[n + n2] = -1127296;
                    }
                }
                break;
            }
            case -8372160: {
                if (this.random(0.0f, 1.0f) < 0.25f && (this.pixels[n - this.WIDTH + n2] == -49088 || this.pixels[n + n2 + 1] == -49088 || this.pixels[n + n2 - 1] == -49088 || this.pixels[n + n2 + this.WIDTH] == -49088)) {
                    this.pixels[n + n2 + 1] = -49088;
                    this.pixels[n + n2 - 1] = -49088;
                    this.pixels[n + n2 + this.WIDTH] = -49088;
                    this.pixels[n + n2 - this.WIDTH] = -49088;
                    this.pixels[n + n2] = -49088;
                }
                int n28;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n28 = n + this.WIDTH + n2] == -16777216 || this.pixels[n28 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n28 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n28 = n + n2 + 1] == -16777216 || this.pixels[n28 = n + n2 - 1] == -16777216)) {
                    this.pixels[n28] = -8372160;
                    this.pixels[n + n2] = -16777216;
                }
                else if (this.random(0.0f, 1.0f) < 0.25f && this.pixels[n - this.WIDTH + n2] == -14671617) {
                    this.pixels[n - this.WIDTH + n2] = -8372160;
                    this.pixels[n + n2] = -14671617;
                }
                break;
            }
            case -1: {
                int n29;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n29 = n + this.WIDTH + n2] == -16777216 || this.pixels[n29 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n29 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n29 = n + n2 + 1] == -16777216 || this.pixels[n29 = n + n2 - 1] == -16777216)) {
                    this.pixels[n29] = -1;
                    this.pixels[n + n2] = -16777216;
                }
                else {
                    int n30;
                    if (this.random(0.0f, 1.0f) < 0.9f && (this.pixels[n30 = n - this.WIDTH + n2] == -14671617 || this.pixels[n30 = n + this.WIDTH + n2] == -14671617 || this.pixels[n30 = n + n2 + 1] == -14671617 || this.pixels[n30 = n + n2 - 1] == -14671617)) {
                        this.pixels[n30] = -12549889;
                        this.pixels[n + n2] = -16777216;
                    }
                }
                int n31;
                if (this.pixels[n31 = n + this.WIDTH + n2] == -4464 || this.pixels[n31 = n + n2 + 1] == -4464 || this.pixels[n31 = n + n2 - 1] == -4464) {
                    this.pixels[n31] = -16777216;
                    this.ball.salt(n2, n3);
                }
                else {
                    int n32;
                    if (this.pixels[n32 = n + this.WIDTH + n2] == -4463 || this.pixels[n32 = n + n2 + 1] == -4463 || this.pixels[n32 = n + n2 - 1] == -4463) {
                        this.pixels[n32] = -16777216;
                        this.ball.salt(n2, n3);
                    }
                }
                break;
            }
            case -4462: {
                this.pixels[n + n2] = -16777216;
                break;
            }
            case -4463: {
                this.pixels[n + n2] = -16777216;
                break;
            }
            case -4464: {
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n + n2 - this.WIDTH] == -49088 || this.pixels[n + n2 + 1] == -49088 || this.pixels[n + n2 - 1] == -49088 || this.pixels[n + n2 + this.WIDTH] == -49088)) {
                    this.pixels[n + n2 - this.WIDTH] = -49088;
                    this.pixels[n + n2] = -49088;
                }
                else {
                    this.pixels[n + n2] = -4463;
                }
                break;
            }
            case -3355445: {
                this.pixels[n + n2] = -16777216;
                break;
            }
            case -3355444: {
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n + n2 - this.WIDTH] == -49088 || this.pixels[n + n2 + 1] == -49088 || this.pixels[n + n2 - 1] == -49088 || this.pixels[n + n2 + this.WIDTH] == -49088)) {
                    this.pixels[n + n2 - this.WIDTH] = -49088;
                    this.pixels[n + n2] = -49088;
                }
                else {
                    this.pixels[n + n2] = -3355445;
                }
                break;
            }
        }
    }
    
    float drawLine(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.ellipseMode(3);
        final float n7 = n3 - n;
        final float n8 = n4 - n2;
        final float sqrt = this.sqrt(n7 * n7 + n8 * n8);
        if (sqrt == 0.0f) {
            if (n6 >= n5) {
                this.ellipse(n, n2, n5, n5);
            }
            return n5;
        }
        final float n9 = n7 / sqrt;
        final float n10 = n8 / sqrt;
        float n11;
        for (n11 = 0.0f; n11 < sqrt && n11 * n5 < n6; n11 += n5 / 2.0f) {
            this.ellipse(n + n9 * n11, n2 + n10 * n11, n5, n5);
        }
        return n11 * n5;
    }
    
    void loop() {
        this.pmousePressed = this.nmousePressed;
        this.nmousePressed = this.mousePressed;
        this.mouse_pos = new Vec(this.mouseX, this.mouseY);
        final int n = this.menu_count[this.MENU_HUMAN] * 10;
        for (int i = 0; i < this.menu_count[this.MENU_TIME]; ++i) {
            final int n2 = this.WIDTH / 5;
            if (this.menu_count[this.MENU_SAND] != 0) {
                for (int n3 = this.menu_count[this.MENU_SAND] * 5, j = 1; j <= n3; ++j) {
                    final int n4 = j * this.WIDTH + n2;
                    for (int k = -n3; k <= n3; ++k) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n4 + k] = this.SAND_COLOR;
                        }
                    }
                }
            }
            if (this.menu_count[this.MENU_WATER] != 0) {
                for (int n5 = this.menu_count[this.MENU_WATER] * 5, l = 1; l <= n5; ++l) {
                    final int n6 = l * this.WIDTH + n2 * 2;
                    for (int n7 = -n5; n7 < n5; ++n7) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n6 + n7] = this.WATER_COLOR;
                        }
                    }
                }
            }
            if (this.menu_count[this.MENU_SALT] != 0) {
                for (int n8 = this.menu_count[this.MENU_SALT] * 5, n9 = 1; n9 <= n8; ++n9) {
                    final int n10 = n9 * this.WIDTH + n2 * 3;
                    for (int n11 = -n8; n11 <= n8; ++n11) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n10 + n11] = this.SALT_COLOR;
                        }
                    }
                }
            }
            if (this.menu_count[this.MENU_OIL] != 0) {
                for (int n12 = this.menu_count[this.MENU_OIL] * 5, n13 = 1; n13 <= n12; ++n13) {
                    final int n14 = n13 * this.WIDTH + n2 * 4;
                    for (int n15 = -n12; n15 <= n12; ++n15) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n14 + n15] = this.OIL_COLOR;
                        }
                    }
                }
            }
            ++this.counter;
            if ((this.counter & 0x1) == 0x0) {
                for (int n16 = this.HEIGHT - 2; n16 > 0; --n16) {
                    final int n17 = n16 * this.WIDTH;
                    for (int n18 = 1; n18 < this.WIDTH - 1; ++n18) {
                        this.pset(n17, n18, n16);
                    }
                }
            }
            else {
                for (int n19 = this.HEIGHT - 2; n19 > 0; --n19) {
                    final int n20 = n19 * this.WIDTH;
                    for (int n21 = this.WIDTH - 2; n21 > 0; --n21) {
                        this.pset(n20, n21, n19);
                    }
                }
            }
            for (int n22 = 1; n22 < this.WIDTH - 1; ++n22) {
                this.pixels[n22] = this.BACK_COLOR;
                this.pixels[(this.HEIGHT - 2) * this.WIDTH + n22] = this.BACK_COLOR;
            }
            for (int n23 = 1; n23 < this.HEIGHT - 1; ++n23) {
                this.pixels[this.WIDTH * n23] = this.WALL_COLOR;
                this.pixels[this.WIDTH * n23 + this.WIDTH - 1] = this.WALL_COLOR;
            }
            if (this.menu_count[this.MENU_SNIG] != 0) {
                if (this.menu_count[this.MENU_SNIG] == 1) {
                    this.ball.addForce(new Vec(0.0f, 0.1f));
                    this.ball.update();
                    this.par.update();
                    this.par.draw();
                }
                else if (this.menu_count[this.MENU_SNIG] == 2) {
                    this.ball.draw();
                    this.par.draw();
                }
            }
            final float n24 = this.ball.scale * (10.0f + this.killed * 0.01f);
            if (n24 < 9.0f) {
                this.par.add(this.ball.pts[0].pos.x, this.ball.pts[0].pos.y, 10.0f);
                this.ball = new Ball(32, n24);
                if (n24 > 100.0f) {
                    this.ball.trans(new Vec(this.WIDTH / 2, this.HEIGHT / 2));
                }
                else {
                    this.ball.trans(new Vec(this.random(n24, this.WIDTH - n24), this.random(n24, this.HEIGHT - n24)));
                }
                for (int n25 = 0; n25 < this.snig2human.length && this.snig2human[n25] != null; ++n25) {
                    this.snig2human[n25] = null;
                }
                for (int n26 = 0; n26 < n; ++n26) {
                    this.humans[n26].snig_hold = false;
                }
                ++this.killed;
            }
            for (int n27 = 0; n27 < n; ++n27) {
                for (int n28 = n27 + 1; n28 < n; ++n28) {
                    this.humans[n27].collide(this.humans[n28]);
                }
            }
            for (int n29 = 0; n29 < this.snig2human.length && this.snig2human[n29] != null; ++n29) {
                this.snig2human[n29].first.pos.x = this.snig2human[n29].second.pos.x;
                this.snig2human[n29].first.pos.y = this.snig2human[n29].second.pos.y;
                this.snig2human[n29].first.prev.x = this.snig2human[n29].second.prev.x;
                this.snig2human[n29].first.prev.y = this.snig2human[n29].second.prev.y;
            }
            for (int n30 = 0; n30 < n; ++n30) {
                this.humans[n30].update();
            }
        }
        if (this.pen_num == 11 && !this.nmousePressed && this.pmousePressed) {
            for (int n31 = 0; n31 < this.holds.length; ++n31) {
                this.holds[n31] = null;
            }
        }
        if (this.nmousePressed && this.mouseY < this.HEIGHT) {
            if (this.pen_num == 11) {
                if (!this.pmousePressed) {
                    for (int n32 = 0; n32 < this.ball.pts.length; ++n32) {
                        if (this.ball.pts[n32].pos.distance(this.mouse_pos) < 8.0f) {
                            for (int n33 = 0; n33 < this.holds.length; ++n33) {
                                if (this.holds[n33] == null) {
                                    this.holds[n33] = this.ball.pts[n32];
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    for (int n34 = 0; n34 < n; ++n34) {
                        for (int n35 = 0; n35 < this.humans[n34].pts.length; ++n35) {
                            if (this.humans[n34].pts[n35].pos.distance(this.mouse_pos) < 8.0f) {
                                for (int n36 = 0; n36 < this.holds.length; ++n36) {
                                    if (this.holds[n36] == null) {
                                        this.holds[n36] = this.humans[n34].pts[n35];
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                for (int n37 = 0; n37 < this.holds.length && this.holds[n37] != null; ++n37) {
                    this.holds[n37].pos.x = this.mouse_pos.x;
                    this.holds[n37].pos.y = this.mouse_pos.y;
                }
            }
            else {
                this.noStroke();
                this.fill(this.resource_color[this.pen_num]);
                this.drawLine(this.pmouseX, this.pmouseY, this.mouseX, this.mouseY, this.pen_size_list[this.menu_count[this.MENU_PEN]], 10000.0f);
                this.strokeWeight(1.0f);
            }
        }
        if (this.menu_count[this.MENU_SNIG] == 1) {
            for (int n38 = 0; n38 < n; ++n38) {
                if (!this.humans[n38].snig_hold && this.humans[n38].pts[0].pos.distance(this.ball.pts[0].pos) < this.ball.scale * 5) {
                    boolean b = false;
                    for (int n39 = 1; n39 < this.ball.pts.length && !b; ++n39) {
                        for (int n40 = 6; n40 < this.humans[n38].pts.length; ++n40) {
                            if (this.humans[n38].pts[n40].pos.distance(this.ball.pts[n39].pos) < 8.0f) {
                                for (int n41 = 0; n41 < this.snig2human.length; ++n41) {
                                    if (this.snig2human[n41] == null) {
                                        this.snig2human[n41] = new MassPointLink(this.humans[n38].pts[n40], this.ball.pts[n39]);
                                        b = true;
                                        this.humans[n38].snig_hold = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        else {
            for (int n42 = 0; n42 < this.snig2human.length && this.snig2human[n42] != null; ++n42) {
                this.snig2human[n42] = null;
            }
            for (int n43 = 0; n43 < n; ++n43) {
                this.humans[n43].snig_hold = false;
            }
        }
        this.fill(60.0f, 60.0f, 60.0f);
        this.stroke(60.0f, 120.0f, 200.0f);
        this.rect(0.0f, this.HEIGHT, this.WIDTH, this.SHEIGHT);
        final int n44 = 6;
        for (int pen_num = 0; pen_num < this.RESOURCE_N; ++pen_num) {
            final int n45 = pen_num % n44;
            final int n46 = pen_num / n44;
            if (this.overRect(this.WIDTH / 4 * n46, this.HEIGHT + this.SHEIGHT / n44 * n45, this.WIDTH / 4, this.SHEIGHT / n44 - 1.0f, this.mouseX, this.mouseY)) {
                this.noStroke();
                this.fill(-16777216);
                this.rect(this.WIDTH / 4 * n46, this.HEIGHT + this.SHEIGHT / n44 * n45 + 2.0f, this.WIDTH / 4, this.SHEIGHT / n44);
                if (this.nmousePressed && !this.pmousePressed) {
                    this.pen_num = pen_num;
                }
            }
            if (this.pen_num == pen_num) {
                this.stroke(-1);
                this.fill(-11184811);
                this.rect(this.WIDTH / 4 * n46, this.HEIGHT + this.SHEIGHT / n44 * n45 + 2.0f, this.WIDTH / 4, this.SHEIGHT / n44);
            }
            else {
                this.stroke(this.resource_color[pen_num]);
            }
            this.fill(this.resource_color[pen_num]);
            this.textSize(16.0f);
            this.text(this.resource_text[pen_num], this.WIDTH / 4 * n46 + 5, this.HEIGHT + this.SHEIGHT / n44 * n45 + 12.0f);
        }
        for (int n47 = 0; n47 < this.MENU_N; ++n47) {
            if (this.overRect(this.WIDTH / 2, this.HEIGHT + this.SHEIGHT / this.MENU_N * n47, this.WIDTH / 2, this.SHEIGHT / this.MENU_N - 1.0f, this.mouseX, this.mouseY)) {
                this.noStroke();
                this.fill(-16777216);
                this.rect(this.WIDTH / 2 - 2, this.HEIGHT + this.SHEIGHT / this.MENU_N * n47 + 4, this.WIDTH / 2, 8.0f);
                if (this.nmousePressed && !this.pmousePressed) {
                    final int[] menu_count = this.menu_count;
                    final int n48 = n47;
                    ++menu_count[n48];
                    if (this.menu_count[n47] > this.menu_level_max[n47]) {
                        this.menu_count[n47] = 0;
                    }
                }
            }
            this.textSize(16.0f);
            this.fill(-128);
            this.text(this.menu_text[n47] + this.menu_level_text[n47][this.menu_count[n47]], this.WIDTH / 2 + 3, this.HEIGHT + this.SHEIGHT / this.MENU_N * n47 + 11.0f);
        }
    }
    
    private final /* synthetic */ void this() {
        this.counter = 0;
        this.SAND_COLOR = -1127296;
        this.WATER_COLOR = -14671617;
        this.FIRE_COLOR = -49088;
        this.BACK_COLOR = -16777216;
        this.WALL_COLOR = -8355712;
        this.PLANT_COLOR = -14627808;
        this.SMOKE_COLOR = -9408400;
        this.SPOUT_COLOR = -9395969;
        this.CERA_COLOR = -1122868;
        this.CERA2_COLOR = -1122867;
        this.UNIDENT_COLOR = -65281;
        this.OIL_COLOR = -8372160;
        this.SOLT_WATER_COLOR = -12549889;
        this.TREE_COLOR = -2264992;
        this.TREE2_COLOR = -2264991;
        this.SALT_COLOR = -1;
        this.SNIG_COLOR = -4464;
        this.SNIG2_COLOR = -4463;
        this.SNIG3_COLOR = -4462;
        this.HUMAN_COLOR = -3355444;
        this.HUMAN2_COLOR = -3355445;
        this.WIDTH = 0;
        this.HEIGHT = 0;
        this.WH = this.WIDTH * this.HEIGHT;
        this.SHEIGHT = 80;
        this.RESOURCE_N = 12;
        this.resource_text = new String[] { "WALL", "FIRE", "WATER", "PLANT", "SAND", "SPOUT", "WAX", "???", "OIL", "ERASER", "SALT", "HAND" };
        this.resource_color = new int[] { this.WALL_COLOR, this.FIRE_COLOR, this.WATER_COLOR, this.PLANT_COLOR, this.SAND_COLOR, this.SPOUT_COLOR, this.CERA_COLOR, this.UNIDENT_COLOR, this.OIL_COLOR, this.BACK_COLOR, this.SALT_COLOR, this.HUMAN_COLOR };
        this.pen_num = 0;
        this.MENU_N = 8;
        this.MENU_PEN = 0;
        this.MENU_TIME = 1;
        this.MENU_SAND = 2;
        this.MENU_WATER = 3;
        this.MENU_OIL = 5;
        this.MENU_SALT = 4;
        this.MENU_SNIG = 6;
        this.MENU_HUMAN = 7;
        this.menu_text = new String[] { "PEN-size:   ", "TIME-speed: ", "SAND:    ", "WATER: ", "SALT:     ", "OIL:       ", "SNIG:     ", "ZOMBIE:   " };
        this.menu_count = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };
        this.menu_level_text = new String[][] { { " x 1", " x 2", " x 4", " x 8", " x 16", " x 32" }, { " x 0", " x 1", " x 2", " x 3", " x 4", " x 5" }, { " x 0", " x 1", " x 2", " x 3" }, { " x 0", " x 1", " x 2", " x 3" }, { " x 0", " x 1", " x 2", " x 3" }, { " x 0", " x 1", " x 2", " x 3" }, { " VANISH", " MOVE", " STOP" }, { " x 0", " x 10", " x 20", " x 30", " x 40", " x 50" } };
        this.menu_level_max = new int[] { 5, 5, 3, 3, 3, 3, 2, 5 };
        this.pen_size_list = new int[] { 2, 4, 8, 16, 32, 64 };
        this.killed = 0;
        this.meat_eater_stock = 0;
        this.plant_eater_stock = 0;
        this.pmousePressed = false;
        this.nmousePressed = false;
        this.PAR_MAX = 40;
    }
    
    public HellOfSand() {
        this.this();
    }
    
    class Vec
    {
        float x;
        float y;
        
        Vec set(final float x, final float y) {
            this.x = x;
            this.y = y;
            return this;
        }
        
        float length() {
            return HellOfSand.this.sqrt(this.x * this.x + this.y * this.y);
        }
        
        float distance(final Vec vec) {
            final float n = this.x - vec.x;
            final float n2 = this.y - vec.y;
            return HellOfSand.this.sqrt(n * n + n2 * n2);
        }
        
        void normalize() {
            final float length = this.length();
            this.x /= length;
            this.y /= length;
        }
        
        Vec add(final Vec vec, final Vec vec2) {
            this.x = vec.x + vec2.x;
            this.y = vec.y + vec2.y;
            return this;
        }
        
        Vec add(final Vec vec) {
            this.x += vec.x;
            this.y += vec.y;
            return this;
        }
        
        Vec sub(final Vec vec, final Vec vec2) {
            this.x = vec.x - vec2.x;
            this.y = vec.y - vec2.y;
            return this;
        }
        
        Vec sub(final Vec vec) {
            this.x -= vec.x;
            this.y -= vec.y;
            return this;
        }
        
        Vec mul(final Vec vec, final float n) {
            this.x = vec.x * n;
            this.y = vec.y * n;
            return this;
        }
        
        Vec mul(final float n) {
            this.x *= n;
            this.y *= n;
            return this;
        }
        
        Vec div(final Vec vec, final float n) {
            this.x = vec.x / n;
            this.y = vec.y / n;
            return this;
        }
        
        Vec div(final float n) {
            this.x /= n;
            this.y /= n;
            return this;
        }
        
        Vec dup() {
            return new Vec(this.x, this.y);
        }
        
        Vec assign(final Vec vec) {
            this.x = vec.x;
            this.y = vec.y;
            return this;
        }
        
        void adjust(final Vec vec, final float n) {
            final Vec sub = new Vec().sub(vec, this);
            final float length = sub.length();
            final float n2 = n - length;
            if (length == 0.0f) {
                return;
            }
            sub.div(length);
            sub.mul(n2 * 0.5f);
            this.sub(sub);
            vec.add(sub);
        }
        
        void adjust(final Vec vec, final float n, final float n2, final float n3) {
            final Vec sub = new Vec().sub(vec, this);
            final float length = sub.length();
            final float n4 = n - length;
            if (length == 0.0f) {
                return;
            }
            sub.div(length);
            final Vec dup = sub.dup();
            dup.mul(n4 * n3);
            sub.mul(n4 * n2);
            this.sub(sub);
            vec.add(dup);
        }
        
        void interp(final Vec vec, final Vec vec2, final float n) {
            final float n2 = 1.0f - n;
            this.x = vec.x * n2 + vec2.x * n;
            this.y = vec.y * n2 + vec2.y * n;
        }
        
        void interp(final Vec vec, final float n) {
            final float n2 = 1.0f - n;
            this.x = this.x * n2 + vec.x * n;
            this.y = this.y * n2 + vec.y * n;
        }
        
        Vec() {
            this.x = 0.0f;
            this.y = 0.0f;
        }
        
        Vec(final float x, final float y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class MassPoint
    {
        Vec pos;
        Vec prev;
        Vec vel;
        float weight;
        
        void trans(final Vec vec) {
            this.pos.add(vec);
            this.prev.add(vec);
        }
        
        void update(final float n) {
            this.vel.sub(this.pos, this.prev);
            this.prev.assign(this.pos);
            this.vel.mul(n);
            this.pos.add(this.vel);
        }
        
        void update() {
            this.vel.sub(this.pos, this.prev);
            this.prev.assign(this.pos);
            this.vel.mul(0.99f);
            this.pos.add(this.vel);
        }
        
        void adjust(final MassPoint massPoint, final float n) {
            this.pos.adjust(massPoint.pos, n);
        }
        
        void adjust(final MassPoint massPoint, final float n, final float n2, final float n3) {
            this.pos.adjust(massPoint.pos, n, n2, n3);
        }
        
        MassPoint() {
        }
        
        MassPoint(final Vec vec) {
            this.pos = new Vec();
            this.prev = new Vec();
            this.vel = new Vec();
            this.pos.assign(vec);
            this.prev.assign(vec);
            this.vel.set(0.0f, 0.0f);
            this.weight = 1.0f;
        }
        
        MassPoint(final Vec vec, final Vec vec2) {
            this.pos = new Vec();
            this.prev = new Vec();
            this.pos.assign(vec);
            this.prev.sub(this.pos, vec2);
            this.vel.sub(this.pos, this.prev);
            this.weight = 1.0f;
        }
    }
    
    class MassPointLink
    {
        MassPoint first;
        MassPoint second;
        float distance;
        
        void adjust() {
            final float n = this.first.weight + this.second.weight;
            this.first.adjust(this.second, this.distance, this.second.weight / n, this.first.weight / n);
        }
        
        void adjust(final float n) {
            final float n2 = this.first.weight + this.second.weight;
            this.first.adjust(this.second, this.distance * n, this.second.weight / n2, this.first.weight / n2);
        }
        
        MassPointLink() {
        }
        
        MassPointLink(final MassPoint first, final MassPoint second) {
            this.first = first;
            this.second = second;
            this.distance = first.pos.distance(second.pos);
        }
        
        MassPointLink(final MassPoint first, final MassPoint second, final float distance) {
            this.first = first;
            this.second = second;
            this.distance = distance;
        }
    }
    
    class Line
    {
        MassPoint[] pts;
        MassPointLink[] links;
        float[] air;
        
        void align(final Vec vec, final Vec vec2) {
            final float n = (vec2.x - vec.x) / (this.pts.length - 1);
            final float n2 = (vec2.y - vec.y) / (this.pts.length - 1);
            for (int i = 0; i < this.pts.length; ++i) {
                final Vec prev = this.pts[i].prev;
                final Vec pos = this.pts[i].pos;
                final float n3 = vec.x + n * i;
                pos.x = n3;
                prev.x = n3;
                final Vec prev2 = this.pts[i].prev;
                final Vec pos2 = this.pts[i].pos;
                final float n4 = vec.y + n2 * i;
                pos2.y = n4;
                prev2.y = n4;
            }
        }
        
        void trans(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].trans(vec);
            }
        }
        
        void addForce(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].pos.add(vec);
            }
        }
        
        void draw() {
            HellOfSand.this.fill(HellOfSand.this.WALL_COLOR);
            HellOfSand.this.noStroke();
            HellOfSand.this.beginShape(65);
            float n = 0.0f;
            float n2 = 0.0f;
            float sqrt = 0.0f;
            for (int i = 0; i < this.pts.length - 1; ++i) {
                n = -(this.pts[i + 1].pos.y - this.pts[i].pos.y);
                n2 = this.pts[i + 1].pos.x - this.pts[i].pos.x;
                sqrt = HellOfSand.this.sqrt(n * n + n2 * n2);
                if (sqrt != 0.0f) {
                    final float n3 = n / sqrt;
                    final float n4 = n2 / sqrt;
                    n = n3 * this.air[i];
                    n2 = n4 * this.air[i];
                    HellOfSand.this.vertex(this.pts[i].pos.x + n, this.pts[i].pos.y + n2);
                    HellOfSand.this.vertex(this.pts[i].pos.x - n, this.pts[i].pos.y - n2);
                }
            }
            final int n5 = this.pts.length - 1;
            if (sqrt != 0.0f) {
                HellOfSand.this.vertex(this.pts[n5].pos.x + n, this.pts[n5].pos.y + n2);
                HellOfSand.this.vertex(this.pts[n5].pos.x - n, this.pts[n5].pos.y - n2);
            }
            HellOfSand.this.endShape();
            HellOfSand.this.stroke(0.0f, 0.0f, 0.0f);
        }
        
        void update() {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].update(0.95f);
            }
            for (int j = this.pts.length - 2; j > 0; --j) {
                this.air[j] = (this.air[j] + this.air[j + 1]) / 2.0f;
            }
            this.air[this.pts.length - 1] = 1.0f;
            this.adjust();
            this.adjust();
        }
        
        void adjust() {
            for (int i = 0; i < this.links.length; ++i) {
                this.links[i].adjust();
            }
        }
        
        Line(final Vec vec, final Vec vec2) {
            final int n = 8;
            this.pts = new MassPoint[n];
            final float n2 = (vec2.x - vec.x) / n;
            final float n3 = (vec2.y - vec.y) / n;
            this.air = new float[n];
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i] = new MassPoint(new Vec(vec.x + n2 * i, vec.y + n3 * i));
                this.air[i] = 1.0f;
            }
            this.links = new MassPointLink[n - 1];
            for (int j = 0; j < this.pts.length - 1; ++j) {
                this.links[j] = new MassPointLink(this.pts[j], this.pts[j + 1], 10.0f);
            }
            for (int k = 0; k < this.links.length; ++k) {
                final MassPointLink massPointLink = this.links[k];
                final int n4 = (int)HellOfSand.this.random(k, this.links.length);
                this.links[k] = this.links[n4];
                this.links[n4] = massPointLink;
            }
            this.pts[this.pts.length - 1].weight = 10.0f;
        }
    }
    
    class Ball
    {
        MassPoint[] pts;
        MassPointLink[] links;
        float scale;
        float limit_scale;
        boolean right_tern;
        float power;
        int wall_on;
        
        void trans(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].trans(vec);
            }
        }
        
        Vec eval(final float n, final float n2, final float n3, final float n4, final float n5) {
            final float pow = HellOfSand.this.pow(HellOfSand.this.pow(HellOfSand.this.abs(HellOfSand.this.cos(n * n5 / 4) / 1.0f), n3) + HellOfSand.this.pow(HellOfSand.this.abs(HellOfSand.this.sin(n * n5 / 4) / 1.0f), n4), 1.0f / n2);
            if (HellOfSand.this.abs(pow) == 0.0f) {
                return new Vec(0.0f, 0.0f);
            }
            final float n6 = 1.0f / pow;
            return new Vec(n6 * HellOfSand.this.cos(n5), n6 * HellOfSand.this.sin(n5));
        }
        
        void addForce(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].pos.add(vec);
            }
        }
        
        void draw() {
            HellOfSand.this.stroke(HellOfSand.this.SNIG_COLOR);
            HellOfSand.this.noFill();
            HellOfSand.this.strokeWeight(1.0f);
            HellOfSand.this.beginShape(32);
            for (int i = 0; i < this.links.length; ++i) {
                HellOfSand.this.vertex(this.links[i].first.pos.x, this.links[i].first.pos.y);
                HellOfSand.this.vertex(this.links[i].second.pos.x, this.links[i].second.pos.y);
            }
            HellOfSand.this.endShape();
            HellOfSand.this.strokeWeight(1.0f);
        }
        
        void salt_water(final int n, final int n2) {
            if ((this.wall_on & 0x8) == 0x0) {
                if (this.pts[0].pos.x < n) {
                    this.right_tern = true;
                }
                else {
                    this.right_tern = false;
                }
            }
            this.scale -= 0.005f;
            this.power += 0.01f;
        }
        
        void salt(final int n, final int n2) {
            if ((this.wall_on & 0x8) == 0x0) {
                if (this.pts[0].pos.x < n) {
                    this.right_tern = false;
                }
                else {
                    this.right_tern = true;
                }
            }
            this.scale -= 0.005f;
            this.power += 0.01f;
        }
        
        void water(final int n, final int n2) {
            if ((this.wall_on & 0x8) == 0x0) {
                if (this.pts[0].pos.x < n) {
                    this.right_tern = true;
                }
                else {
                    this.right_tern = false;
                }
            }
            this.scale += 0.002f;
            this.power += 0.002f;
        }
        
        void update() {
            if (this.scale < 0.01f) {
                this.scale = 0.1f;
            }
            if (this.power > 0.2f) {
                this.power -= 1.0E-4f;
            }
            if (this.power < 0.0f) {
                this.power = 0.0f;
            }
            if (this.power > 1.0f) {
                this.power = 1.0f;
            }
            if (this.right_tern) {
                this.ternR(this.power);
            }
            else {
                this.ternL(this.power);
            }
            this.adjust();
            this.wall_on = 0;
            for (int i = 0; i < this.pts.length; ++i) {
                if (this.pts[i].pos.x < 3) {
                    this.pts[i].pos.x = 0.0f;
                    this.pts[i].pos.y = this.pts[i].prev.y * 0.9f + this.pts[i].pos.y * 0.1f;
                    this.wall_on |= 0x2;
                }
                else if (this.pts[i].pos.x > HellOfSand.this.WIDTH - 4) {
                    this.pts[i].pos.x = HellOfSand.this.WIDTH - 1;
                    this.pts[i].pos.y = this.pts[i].prev.y * 0.9f + this.pts[i].pos.y * 0.1f;
                    this.wall_on |= 0x4;
                }
                else if (this.pts[i].pos.y < 4) {
                    this.pts[i].pos.y = 0.0f;
                    this.pts[i].pos.x = this.pts[i].prev.x * 0.9f + this.pts[i].pos.x * 0.1f;
                    this.wall_on |= 0x8;
                }
                else if (this.pts[i].pos.y > HellOfSand.this.HEIGHT - 4) {
                    this.pts[i].pos.y = HellOfSand.this.HEIGHT - 1;
                    this.pts[i].pos.x = this.pts[i].prev.x * 0.5f + this.pts[i].pos.x * 0.5f;
                    this.wall_on |= 0x10;
                }
                else {
                    final int n = HellOfSand.this.pixels[(int)this.pts[i].pos.y * HellOfSand.this.WIDTH + (int)this.pts[i].pos.x];
                    if (n != HellOfSand.this.BACK_COLOR && n != HellOfSand.this.SNIG_COLOR && n != HellOfSand.this.SNIG2_COLOR && n != HellOfSand.this.HUMAN2_COLOR) {
                        this.pts[i].pos.assign(this.pts[i].prev);
                    }
                }
            }
            this.draw();
            for (int j = 0; j < this.pts.length; ++j) {
                this.pts[j].update(0.95f);
            }
        }
        
        void ternR(float n) {
            n *= 0.2f;
            this.pts[this.pts.length - 1].pos.interp(this.pts[3].pos, n);
            for (int i = 3; i < this.pts.length - 1; ++i) {
                this.pts[i].pos.interp(this.pts[i + 1].pos, n);
            }
            this.adjust();
        }
        
        void ternL(float n) {
            n *= 0.2f;
            this.pts[3].pos.interp(this.pts[this.pts.length - 1].pos, n);
            for (int i = this.pts.length - 1; i > 3; --i) {
                this.pts[i].pos.interp(this.pts[i - 1].pos, n);
            }
            this.adjust();
        }
        
        void adjust() {
            for (int i = 0; i < this.links.length; ++i) {
                this.links[i].adjust(this.scale);
            }
        }
        
        Ball(final int n, final float n2) {
            (this.pts = new MassPoint[n + 1])[0] = new MassPoint(new Vec(0.0f, 0.0f));
            final float n3 = (int)HellOfSand.this.random(0.0f, 256.0f);
            final float n4 = 1.0f;
            final float n5 = 1.0f;
            final float n6 = 1.0f;
            for (int i = 1; i < this.pts.length; ++i) {
                final Vec eval = this.eval(n3, n4, n5, n6, (i + 2) * 6.2831855f / n);
                eval.mul(n2);
                this.pts[i] = new MassPoint(eval);
            }
            this.scale = 2.0f;
            this.limit_scale = 0.5f;
            this.right_tern = true;
            this.power = 0.2f;
            this.wall_on = 0;
            this.links = new MassPointLink[this.pts.length - 1 + (this.pts.length - 1) * (n / 4)];
            int n7 = 0;
            for (int j = 1; j < this.pts.length; ++j) {
                for (int k = 1; k <= n / 4; ++k) {
                    int n8 = (j + k) % this.pts.length;
                    if (n8 == 0) {
                        ++n8;
                    }
                    this.links[n7] = new MassPointLink(this.pts[j], this.pts[n8]);
                    ++n7;
                }
            }
            for (int l = 1; l < this.pts.length; ++l) {
                this.links[n7] = new MassPointLink(this.pts[0], this.pts[l]);
                ++n7;
            }
            for (int n9 = 0; n9 < this.links.length; ++n9) {
                final MassPointLink massPointLink = this.links[n9];
                final int n10 = (int)HellOfSand.this.random(n9, this.links.length);
                this.links[n9] = this.links[n10];
                this.links[n10] = massPointLink;
            }
        }
    }
    
    class Tree
    {
        MassPoint[] pts;
        float[] grows;
        float[] limits;
        int[] rels;
        int pts_count;
        MassPointLink[] links;
        int links_count;
        
        void draw() {
            HellOfSand.this.stroke(HellOfSand.this.TREE_COLOR);
            HellOfSand.this.noFill();
            HellOfSand.this.strokeWeight(1.0f);
            for (int i = 0; i < this.pts_count; ++i) {
                final int n = this.rels[i] >> 16;
                final int n2 = this.rels[i] & (char)(-1);
                HellOfSand.this.line(this.pts[n].pos.x, this.pts[n].pos.y, this.pts[n2].pos.x, this.pts[n2].pos.y);
            }
            HellOfSand.this.strokeWeight(1.0f);
        }
        
        void update() {
            this.pts[0].pos.x = HellOfSand.this.WIDTH / 2;
            this.pts[0].pos.y = HellOfSand.this.HEIGHT;
            this.adjust();
            for (int i = 1; i < this.pts_count; ++i) {
                if (this.pts[i].pos.x < 3) {
                    this.pts[i].pos.x = 0.0f;
                    this.pts[i].pos.y = this.pts[i].prev.y * 0.9f + this.pts[i].pos.y * 0.1f;
                }
                else if (this.pts[i].pos.x > HellOfSand.this.WIDTH - 4) {
                    this.pts[i].pos.x = HellOfSand.this.WIDTH - 1;
                    this.pts[i].pos.y = this.pts[i].prev.y * 0.9f + this.pts[i].pos.y * 0.1f;
                }
                else if (this.pts[i].pos.y < 4) {
                    this.pts[i].pos.y = 0.0f;
                    this.pts[i].pos.x = this.pts[i].prev.x * 0.9f + this.pts[i].pos.x * 0.1f;
                }
                else if (this.pts[i].pos.y > HellOfSand.this.HEIGHT - 4) {
                    this.pts[i].pos.y = HellOfSand.this.HEIGHT - 1;
                    this.pts[i].pos.x = this.pts[i].prev.x * 0.5f + this.pts[i].pos.x * 0.5f;
                }
                else {
                    final int n = HellOfSand.this.pixels[(int)this.pts[i].pos.y * HellOfSand.this.WIDTH + (int)this.pts[i].pos.x];
                    if (n != HellOfSand.this.BACK_COLOR && n != HellOfSand.this.TREE_COLOR && n != HellOfSand.this.TREE2_COLOR) {
                        this.pts[i].pos.assign(this.pts[i].prev);
                    }
                }
                if (this.grows[i] > this.limits[i]) {
                    this.pts[i].update(0.9f);
                }
                else {
                    final float[] grows = this.grows;
                    final int n2 = i;
                    grows[n2] += 0.1f;
                    if (this.grows[i] > this.limits[i]) {
                        this.grow(i, this.limits[i] * 0.9f);
                        this.grow(i, this.limits[i] * 0.9f);
                        this.links[this.links_count++] = new MassPointLink(this.pts[i], this.pts[(int)HellOfSand.this.random(0.0f, this.pts_count)]);
                        this.links[this.links_count++] = new MassPointLink(this.pts[i], this.pts[(int)HellOfSand.this.random(0.0f, this.pts_count)]);
                    }
                    this.pts[i].update(1.0f);
                }
            }
            this.pts[0].pos.x = HellOfSand.this.WIDTH / 2;
            this.pts[0].pos.y = HellOfSand.this.HEIGHT;
        }
        
        void grow(final int n, final float n2) {
            if (this.pts_count == this.pts.length) {
                return;
            }
            this.pts[this.pts_count] = new MassPoint(this.pts[n].pos.dup());
            final Vec pos = this.pts[this.pts_count].pos;
            pos.x += HellOfSand.this.random(-1.0f, 1.0f);
            final Vec pos2 = this.pts[this.pts_count].pos;
            pos2.y += HellOfSand.this.random(-1.0f, -0.5f);
            this.rels[this.pts_count] = (n << 16 | this.pts_count);
            this.limits[this.pts_count] = n2;
            ++this.pts_count;
        }
        
        void adjust() {
            for (int i = 0; i < this.links_count; ++i) {
                this.links[i].adjust();
            }
        }
        
        Tree() {
            this.pts = new MassPoint[500];
            this.grows = new float[this.pts.length];
            this.limits = new float[this.pts.length];
            this.links = new MassPointLink[this.pts.length * 4];
            this.rels = new int[this.pts.length];
            this.pts_count = 0;
            this.links_count = 0;
            for (int i = 0; i < this.grows.length; ++i) {
                this.grows[i] = 0.0f;
                this.limits[i] = 0.0f;
                this.rels[i] = 0;
            }
            this.limits[1] = 10.0f;
            this.pts[this.pts_count++] = new MassPoint(new Vec(HellOfSand.this.WIDTH / 2, HellOfSand.this.HEIGHT));
            this.pts[this.pts_count++] = new MassPoint(new Vec(HellOfSand.this.WIDTH / 2, HellOfSand.this.HEIGHT - 5));
            final Vec pos = this.pts[1].pos;
            --pos.y;
            this.rels[1] = 65536;
        }
    }
    
    class Particles
    {
        float[] xs;
        float[] ys;
        float[] dxs;
        float[] dys;
        float[] sizes;
        int[] cols;
        boolean[] alives;
        
        void add(final float n, final float n2, final float n3, final float n4, final int n5, final float n6) {
            for (int i = 0; i < HellOfSand.this.PAR_MAX; ++i) {
                if (!this.alives[i]) {
                    this.alives[i] = true;
                    this.xs[i] = n;
                    this.ys[i] = n2;
                    this.dxs[i] = n3;
                    this.dys[i] = n4;
                    this.cols[i] = n5;
                    this.sizes[i] = n6;
                    break;
                }
            }
        }
        
        void add(final float n, final float n2, final float n3) {
            for (int i = 0; i < 20; ++i) {
                this.add(n, n2, HellOfSand.this.random(-n3 * 0.5f, n3 * 0.5f), HellOfSand.this.random(-n3 * 0.5f, n3 * 0.5f), HellOfSand.this.SNIG3_COLOR, HellOfSand.this.random(1.0f, n3));
            }
        }
        
        void update() {
            for (int i = 0; i < HellOfSand.this.PAR_MAX; ++i) {
                if (this.alives[i]) {
                    final float[] xs = this.xs;
                    final int n = i;
                    xs[n] += this.dxs[i];
                    final float[] ys = this.ys;
                    final int n2 = i;
                    ys[n2] += this.dys[i];
                    final float[] dys = this.dys;
                    final int n3 = i;
                    dys[n3] += 0.1f;
                    if (this.ys[i] > HellOfSand.this.HEIGHT) {
                        this.alives[i] = false;
                    }
                }
            }
        }
        
        void draw() {
            HellOfSand.this.noStroke();
            for (int i = 0; i < HellOfSand.this.PAR_MAX; ++i) {
                if (this.alives[i]) {
                    HellOfSand.this.fill(this.cols[i]);
                    HellOfSand.this.ellipse(this.xs[i], this.ys[i], this.sizes[i], this.sizes[i]);
                }
            }
        }
        
        void reset() {
            for (int i = 0; i < HellOfSand.this.PAR_MAX; ++i) {
                this.alives[i] = false;
            }
        }
        
        Particles() {
            this.xs = new float[HellOfSand.this.PAR_MAX];
            this.ys = new float[HellOfSand.this.PAR_MAX];
            this.dxs = new float[HellOfSand.this.PAR_MAX];
            this.dys = new float[HellOfSand.this.PAR_MAX];
            this.cols = new int[HellOfSand.this.PAR_MAX];
            this.sizes = new float[HellOfSand.this.PAR_MAX];
            this.alives = new boolean[HellOfSand.this.PAR_MAX];
            for (int i = 0; i < HellOfSand.this.PAR_MAX; ++i) {
                this.alives[i] = false;
            }
        }
    }
    
    class Human
    {
        MassPoint[] pts;
        MassPointLink[] links;
        int walk_state;
        float size;
        int hold;
        float scale;
        boolean on;
        boolean snig_hold;
        
        void init() {
            this.pts = new MassPoint[11];
            this.size = HellOfSand.this.random(2.0f, 2.0f);
            final float random = HellOfSand.this.random(2.0f, 3);
            final float random2 = HellOfSand.this.random(4, 5);
            this.pts[0] = new MassPoint(new Vec(0.0f, -this.size * 2.0f * this.size));
            this.pts[1] = new MassPoint(new Vec(0.0f, -this.size));
            this.pts[2] = new MassPoint(new Vec(0.0f, this.size * 2.0f));
            this.pts[3] = new MassPoint(new Vec(-random * this.size, 0.0f));
            this.pts[4] = new MassPoint(new Vec(random * this.size, 0.0f));
            this.pts[5] = new MassPoint(new Vec(-this.size, random2 * this.size));
            this.pts[6] = new MassPoint(new Vec(this.size, random2 * this.size));
            this.pts[7] = new MassPoint(new Vec(-random * 2.0f * this.size, 0.0f));
            this.pts[8] = new MassPoint(new Vec(random * 2.0f * this.size, 0.0f));
            this.pts[9] = new MassPoint(new Vec(-this.size, random2 * 2.0f * this.size));
            this.pts[10] = new MassPoint(new Vec(this.size, random2 * 2.0f * this.size));
            (this.links = new MassPointLink[10])[0] = new MassPointLink(this.pts[0], this.pts[1]);
            this.links[1] = new MassPointLink(this.pts[1], this.pts[2]);
            this.links[2] = new MassPointLink(this.pts[1], this.pts[3]);
            this.links[3] = new MassPointLink(this.pts[1], this.pts[4]);
            this.links[4] = new MassPointLink(this.pts[2], this.pts[5]);
            this.links[5] = new MassPointLink(this.pts[2], this.pts[6]);
            this.links[6] = new MassPointLink(this.pts[3], this.pts[7]);
            this.links[7] = new MassPointLink(this.pts[4], this.pts[8]);
            this.links[8] = new MassPointLink(this.pts[5], this.pts[9]);
            this.links[9] = new MassPointLink(this.pts[6], this.pts[10]);
            this.walk_state = 0;
            this.hold = 0;
            this.scale = 0.001f;
            this.on = false;
            this.snig_hold = false;
            this.trans(new Vec(HellOfSand.this.random(10.0f, HellOfSand.this.WIDTH - 10), HellOfSand.this.random(10.0f, HellOfSand.this.HEIGHT - 10)));
        }
        
        void trans(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].trans(vec);
            }
        }
        
        void addForce(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].pos.add(vec);
            }
        }
        
        void draw() {
            if (this.scale < 0.0f) {
                return;
            }
            HellOfSand.this.stroke(HellOfSand.this.HUMAN_COLOR);
            HellOfSand.this.noFill();
            HellOfSand.this.strokeWeight(1.0f);
            HellOfSand.this.beginShape(32);
            for (int i = 0; i < this.links.length; ++i) {
                HellOfSand.this.vertex(this.links[i].first.pos.x, this.links[i].first.pos.y);
                HellOfSand.this.vertex(this.links[i].second.pos.x, this.links[i].second.pos.y);
            }
            HellOfSand.this.endShape();
            HellOfSand.this.strokeWeight(1.0f);
            HellOfSand.this.ellipseMode(3);
            HellOfSand.this.ellipse(this.pts[0].pos.x, this.pts[0].pos.y, 2.0f * this.size * this.scale, 2.0f * this.size * this.scale);
        }
        
        void update() {
            this.scale += 0.03f;
            if (this.scale > 1.0f) {
                this.scale = 1.0f;
            }
            if (this.scale < 0.0f) {
                this.init();
            }
            final float n = 5 * this.size;
            if (this.on) {
                final float n2 = (this.pts[0].pos.x < HellOfSand.this.ball.pts[0].pos.x) ? 0.8f : -0.8f;
                if (this.walk_state < 50) {
                    final Vec pos = this.pts[5].pos;
                    pos.y -= 0.01f * n * n2;
                    final Vec pos2 = this.pts[5].pos;
                    pos2.x -= 0.005f * n * n2;
                    final Vec pos3 = this.pts[9].pos;
                    pos3.y -= 0.01f * n * n2;
                    final Vec pos4 = this.pts[9].pos;
                    pos4.x -= 0.005f * n * n2;
                    final Vec pos5 = this.pts[1].pos;
                    pos5.y -= 0.02f * n * n2;
                    final Vec pos6 = this.pts[2].pos;
                    pos6.y += 0.02f * n * n2;
                }
                else if (this.walk_state >= 100) {
                    if (this.walk_state < 150) {
                        final Vec pos7 = this.pts[6].pos;
                        pos7.y -= 0.01f * n * n2;
                        final Vec pos8 = this.pts[6].pos;
                        pos8.x -= 0.005f * n * n2;
                        final Vec pos9 = this.pts[10].pos;
                        pos9.y -= 0.01f * n * n2;
                        final Vec pos10 = this.pts[10].pos;
                        pos10.x -= 0.005f * n * n2;
                        final Vec pos11 = this.pts[1].pos;
                        pos11.y -= 0.02f * n * n2;
                        final Vec pos12 = this.pts[2].pos;
                        pos12.y += 0.02f * n * n2;
                    }
                    else if (this.walk_state >= 200) {
                        this.walk_state = 0;
                    }
                }
                final Vec pos13 = this.pts[3].pos;
                pos13.x -= 0.001f * n * n2;
                final Vec pos14 = this.pts[4].pos;
                pos14.x -= 0.001f * n * n2;
                final Vec pos15 = this.pts[7].pos;
                pos15.x -= 0.001f * n * n2;
                final Vec pos16 = this.pts[8].pos;
                pos16.x -= 0.001f * n * n2;
                final Vec pos17 = this.pts[0].pos;
                pos17.x += 0.001f * n * n2;
                final Vec pos18 = this.pts[1].pos;
                pos18.x += 0.001f * n * n2;
                final Vec pos19 = this.pts[2].pos;
                pos19.x += 0.001f * n * n2;
                ++this.walk_state;
            }
            if (this.on) {
                final Vec pos20 = this.pts[0].pos;
                pos20.y -= 0.001f * n;
                final Vec pos21 = this.pts[1].pos;
                pos21.y -= 0.001f * n;
                final Vec pos22 = this.pts[5].pos;
                pos22.x -= 0.001f * n;
                final Vec pos23 = this.pts[6].pos;
                pos23.x += 0.001f * n;
                final Vec pos24 = this.pts[9].pos;
                pos24.y += 0.02f * n;
                final Vec pos25 = this.pts[10].pos;
                pos25.y += 0.02f * n;
                final Vec pos26 = this.pts[3].pos;
                pos26.y += 0.001f * n;
                final Vec pos27 = this.pts[4].pos;
                pos27.y += 0.001f * n;
                final Vec pos28 = this.pts[3].pos;
                pos28.x -= 1.0E-4f * n;
                final Vec pos29 = this.pts[4].pos;
                pos29.x += 1.0E-4f * n;
                final Vec pos30 = this.pts[7].pos;
                pos30.y -= 0.001f * n;
                final Vec pos31 = this.pts[8].pos;
                pos31.y -= 0.001f * n;
                final float n3 = this.pts[6].pos.x - this.pts[5].pos.x;
                final float n4 = this.pts[6].pos.y - this.pts[5].pos.y;
                final float sqrt = HellOfSand.this.sqrt(n3 * n3 + n4 * n4);
                final float n5 = n3 / sqrt;
                final float n6 = n4 / sqrt;
                final Vec pos32 = this.pts[6].pos;
                pos32.y += n6 * 0.001f * n;
                final Vec pos33 = this.pts[6].pos;
                pos33.x += n5 * 0.001f * n;
                final Vec pos34 = this.pts[5].pos;
                pos34.y -= n6 * 0.001f * n;
                final Vec pos35 = this.pts[5].pos;
                pos35.x -= n5 * 0.001f * n;
                final Vec pos36 = this.pts[9].pos;
                pos36.y -= n6 * 0.001f * n;
                final Vec pos37 = this.pts[9].pos;
                pos37.x -= n5 * 0.001f * n;
                final Vec pos38 = this.pts[10].pos;
                pos38.y += n6 * 0.001f * n;
                final Vec pos39 = this.pts[10].pos;
                pos39.x += n5 * 0.001f * n;
            }
            else {
                for (int i = 0; i < this.pts.length; ++i) {
                    final Vec pos40 = this.pts[i].pos;
                    pos40.y += 0.001f * n;
                }
                final float n7 = this.pts[6].pos.x - this.pts[5].pos.x;
                final float n8 = this.pts[6].pos.y - this.pts[5].pos.y;
                final float sqrt2 = HellOfSand.this.sqrt(n7 * n7 + n8 * n8);
                final float n9 = n7 / sqrt2;
                final float n10 = n8 / sqrt2;
                final Vec pos41 = this.pts[6].pos;
                pos41.y -= n10 * 0.001f * n;
                final Vec pos42 = this.pts[6].pos;
                pos42.x -= n9 * 0.001f * n;
                final Vec pos43 = this.pts[5].pos;
                pos43.y += n10 * 0.001f * n;
                final Vec pos44 = this.pts[5].pos;
                pos44.x += n9 * 0.001f * n;
                final Vec pos45 = this.pts[9].pos;
                pos45.y += n10 * 0.001f * n;
                final Vec pos46 = this.pts[9].pos;
                pos46.x += n9 * 0.001f * n;
                final Vec pos47 = this.pts[10].pos;
                pos47.y -= n10 * 0.001f * n;
                final Vec pos48 = this.pts[10].pos;
                pos48.x -= n9 * 0.001f * n;
            }
            final float n11 = this.pts[2].pos.x - this.pts[5].pos.x + this.pts[2].pos.x - this.pts[6].pos.x;
            final float n12 = this.pts[2].pos.y - this.pts[5].pos.y + this.pts[2].pos.y - this.pts[6].pos.y;
            final float sqrt3 = HellOfSand.this.sqrt(n11 * n11 + n12 * n12);
            final float n13 = n11 / sqrt3;
            final float n14 = n12 / sqrt3;
            final Vec pos49 = this.pts[1].pos;
            pos49.y += n14 * 0.005f * n;
            final Vec pos50 = this.pts[1].pos;
            pos50.x += n13 * 0.005f * n;
            this.adjust();
            this.on = false;
            for (int j = 0; j < this.pts.length; ++j) {
                if (this.pts[j].pos.x < 3) {
                    this.pts[j].pos.x = 3;
                    this.pts[j].pos.y = this.pts[j].prev.y * 0.9f + this.pts[j].pos.y * 0.1f;
                    this.on = true;
                }
                else if (this.pts[j].pos.x > HellOfSand.this.WIDTH - 4) {
                    this.pts[j].pos.x = HellOfSand.this.WIDTH - 4;
                    this.pts[j].pos.y = this.pts[j].prev.y * 0.9f + this.pts[j].pos.y * 0.1f;
                    this.on = true;
                }
                else if (this.pts[j].pos.y < 4) {
                    this.pts[j].pos.y = 4;
                    this.pts[j].pos.x = this.pts[j].prev.x * 0.9f + this.pts[j].pos.x * 0.1f;
                    this.on = true;
                }
                else if (this.pts[j].pos.y > HellOfSand.this.HEIGHT - 4) {
                    this.pts[j].pos.y = HellOfSand.this.HEIGHT - 4;
                    this.pts[j].pos.x = this.pts[j].prev.x * 0.5f + this.pts[j].pos.x * 0.5f;
                    this.on = true;
                }
                else {
                    final int n15 = (int)this.pts[j].pos.x;
                    final int n16 = (int)(this.pts[j].pos.y + 1.0f);
                    final int n17 = HellOfSand.this.pixels[n16 * HellOfSand.this.WIDTH + n15];
                    if (n17 != HellOfSand.this.BACK_COLOR && n17 != HellOfSand.this.HUMAN_COLOR && n17 != HellOfSand.this.HUMAN2_COLOR) {
                        this.on = true;
                        this.pts[j].pos.assign(this.pts[j].prev);
                        if (n17 == HellOfSand.this.FIRE_COLOR) {
                            HellOfSand.this.pixels[n16 * HellOfSand.this.WIDTH + n15] = HellOfSand.this.FIRE_COLOR;
                            HellOfSand.this.pixels[n16 * HellOfSand.this.WIDTH + n15 - 1] = HellOfSand.this.FIRE_COLOR;
                            HellOfSand.this.pixels[n16 * HellOfSand.this.WIDTH + n15 + 1] = HellOfSand.this.FIRE_COLOR;
                            HellOfSand.this.pixels[n16 * HellOfSand.this.WIDTH + n15 + HellOfSand.this.WIDTH] = HellOfSand.this.FIRE_COLOR;
                            HellOfSand.this.pixels[n16 * HellOfSand.this.WIDTH + n15 - HellOfSand.this.WIDTH] = HellOfSand.this.FIRE_COLOR;
                            this.scale -= 0.009f;
                        }
                    }
                }
            }
            this.draw();
            for (int k = 0; k < this.pts.length; ++k) {
                this.pts[k].update(0.999f);
            }
            --this.hold;
            if (this.hold < 0) {
                this.hold = 0;
            }
        }
        
        void adjust() {
            for (int i = 0; i < this.links.length; ++i) {
                this.links[i].adjust(this.scale);
            }
        }
        
        void collide(final Human human) {
            for (int i = 0; i < 4; ++i) {
                for (int j = 4; j < 8; ++j) {
                    final float distance = this.pts[i].pos.distance(human.pts[j].pos);
                    if (distance < 16.0f) {
                        final float n = human.pts[j].pos.x - human.pts[j].prev.x;
                        final float n2 = human.pts[j].pos.y - human.pts[j].prev.y;
                        this.pts[i].adjust(human.pts[j], HellOfSand.this.sqrt(n * n + n2 * n2) * HellOfSand.this.random(1.25f) + distance);
                    }
                }
            }
        }
        
        void collide(final Ball ball) {
            int n = 0;
            for (int i = 3; i < 8; ++i) {
                for (int j = 0; j < ball.pts.length; ++j) {
                    if (this.pts[i].pos.distance(ball.pts[j].pos) < 5) {
                        this.pts[i].adjust(ball.pts[j], 0.0f);
                        ++n;
                        this.hold = 5;
                        if (n > 2) {
                            return;
                        }
                    }
                }
            }
        }
        
        Human() {
            this.init();
        }
    }
}

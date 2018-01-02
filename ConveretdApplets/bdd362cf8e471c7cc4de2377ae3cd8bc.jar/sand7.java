// 
// Decompiled by Procyon v0.5.30
// 

public class sand7 extends BApplet
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
    int SALT_COLOR;
    int SNIG_COLOR;
    int SNIG2_COLOR;
    int SNIG3_COLOR;
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
    String[] menu_text;
    int[] menu_count;
    String[][] menu_level_text;
    int[] menu_level_max;
    int[] pen_size_list;
    Ball ball;
    int killed;
    Particles par;
    boolean pmousePressed;
    boolean nmousePressed;
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
        this.size(960, 640);
        this.WIDTH = this.width;
        this.HEIGHT = this.height - this.SHEIGHT;
        this.WH = this.WIDTH * this.HEIGHT;
        this.colorMode(1, 255.0f, 255.0f, 255.0f);
        this.fillScreen(this.BACK_COLOR);
        this.textFont(this.loadFont("RotisSanSer-Bold.vlw.gz"), 20.0f);
        this.framerate(50.0f);
        (this.ball = new Ball(32, 10.0f)).trans(new Vec(32.0f, 200.0f));
        this.par = new Particles();
    }
    
    void pset(final int n, final int n2, final int n3) {
        switch (this.pixels[n + n2]) {
            case -1127296: {
                int n4;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n4 = n + this.WIDTH + n2] == -16777216 || this.pixels[n4 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n4 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n4 = n + n2 - 1] == -16777216 || this.pixels[n4 = n + n2 + 1] == -16777216)) {
                    this.pixels[n4] = -1127296;
                    this.pixels[n + n2] = -16777216;
                    break;
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
                    break;
                }
                int n7;
                if (this.pixels[n7 = n + this.WIDTH + n2] == -4463 || this.pixels[n7 = n + n2 + 1] == -4463 || this.pixels[n7 = n + n2 - 1] == -4463) {
                    this.pixels[n7] = -16777216;
                    this.ball.water(n2, n3);
                    break;
                }
                break;
            }
            case -65281: {
                if (this.random(0.0f, 1.0f) >= 0.65f) {
                    this.pixels[n + n2] = -65536;
                    break;
                }
                final int n8;
                if (this.pixels[n8 = n + this.WIDTH + n2] != -65536 && this.pixels[n8] != -14627808) {
                    this.pixels[n8] = -65281;
                }
                final int n9;
                if (this.pixels[n9 = n - this.WIDTH + n2] != -65536 && this.pixels[n9] != -14627808) {
                    this.pixels[n9] = -65281;
                }
                final int n10;
                if (this.pixels[n10 = n + 1 + n2] != -65536 && this.pixels[n10] != -14627808) {
                    this.pixels[n10] = -65281;
                }
                final int n11;
                if (this.pixels[n11 = n - 1 + n2] != -65536 && this.pixels[n11] != -14627808) {
                    this.pixels[n11] = -65281;
                    break;
                }
                break;
            }
            case -65536: {
                if (this.random(0.0f, 1.0f) < 0.02f) {
                    this.pixels[n + n2] = -16777216;
                    break;
                }
                break;
            }
            case -1122868: {
                if (this.random(0.0f, 1.0f) >= 0.01f) {
                    break;
                }
                if (this.pixels[n - this.WIDTH + n2] != -49088 && this.pixels[n + n2 + 1] != -49088 && this.pixels[n + n2 - 1] != -49088 && this.pixels[n + n2 + this.WIDTH] != -49088) {
                    break;
                }
                this.pixels[n + n2] = -49088;
                int n12;
                if (this.pixels[n12 = n + this.WIDTH + n2] == -16777216 || this.pixels[n12 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n12 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n12 = n + n2 + 1] == -16777216 || this.pixels[n12 = n + n2 - 1] == -16777216) {
                    this.pixels[n12] = -1122867;
                    break;
                }
                break;
            }
            case -1122867: {
                if (this.random(0.0f, 1.0f) >= 0.8f) {
                    break;
                }
                int n13;
                if (this.pixels[n13 = n + this.WIDTH + n2] == -16777216 || this.pixels[n13 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n13 = n + n2 + this.WIDTH + 1] == -16777216) {
                    this.pixels[n13] = -1122867;
                    this.pixels[n + n2] = -16777216;
                    break;
                }
                this.pixels[n + n2] = -1122868;
                break;
            }
            case -49088: {
                final int n14 = (int)this.random(-1.0f, 1.001f);
                if (this.random(0.0f, 1.0f) < 0.5f && this.pixels[n + n2 - this.WIDTH + n14] == -16777216) {
                    this.pixels[n + n2 - this.WIDTH + n14] = -49088;
                }
                if (this.random(0.0f, 1.0f) < 0.4f && this.pixels[n + n2 - this.WIDTH] != -14627808 && this.pixels[n + n2 + this.WIDTH] != -14627808 && this.pixels[n + n2 - 1] != -14627808 && this.pixels[n + n2 + 1] != -14627808 && this.pixels[n + n2 - this.WIDTH] != -1122868 && this.pixels[n + n2 + this.WIDTH] != -1122868 && this.pixels[n + n2 - 1] != -1122868 && this.pixels[n + n2 + 1] != -1122868) {
                    this.pixels[n + n2] = -16777216;
                }
                int n15;
                if (this.random(0.0f, 1.0f) < 0.9f && (this.pixels[n15 = n + n2 + 1] == -14671617 || this.pixels[n15 = n + n2 - 1] == -14671617 || this.pixels[n15 = n + n2 + this.WIDTH] == -14671617)) {
                    this.pixels[n15] = -16777216;
                    this.pixels[n + n2] = -16777216;
                    break;
                }
                break;
            }
            case -14627808: {
                if (this.random(0.0f, 1.0f) < 0.2f && (this.pixels[n - this.WIDTH + n2] == -49088 || this.pixels[n + n2 + 1] == -49088 || this.pixels[n + n2 - 1] == -49088 || this.pixels[n + n2 + this.WIDTH] == -49088)) {
                    this.pixels[n + n2] = -49088;
                }
                if (this.random(0.0f, 1.0f) >= 0.1f) {
                    break;
                }
                final int n16;
                if (this.pixels[n16 = n - this.WIDTH + n2] == -14671617) {
                    this.pixels[n16] = -14627808;
                }
                final int n17;
                if (this.pixels[n17 = n + this.WIDTH + n2] == -14671617) {
                    this.pixels[n17] = -14627808;
                }
                final int n18;
                if (this.pixels[n18 = n + 1 + n2] == -14671617) {
                    this.pixels[n18] = -14627808;
                }
                final int n19;
                if (this.pixels[n19 = n - 1 + n2] == -14671617) {
                    this.pixels[n19] = -14627808;
                    break;
                }
                break;
            }
            case -9408400: {
                if (this.random(0.0f, 1.0f) < 0.01f) {
                    this.pixels[n + n2] = -1127296;
                    break;
                }
                break;
            }
            case -12549889: {
                if (this.random(0.0f, 1.0f) < 0.05f) {
                    final int n20;
                    if (this.pixels[n20 = n - this.WIDTH + n2] == -16777216) {
                        this.pixels[n20] = -14671617;
                    }
                    final int n21;
                    if (this.pixels[n21 = n + this.WIDTH + n2] == -16777216) {
                        this.pixels[n21] = -14671617;
                    }
                    final int n22;
                    if (this.pixels[n22 = n + 1 + n2] == -16777216) {
                        this.pixels[n22] = -14671617;
                    }
                    final int n23;
                    if (this.pixels[n23 = n - 1 + n2] == -16777216) {
                        this.pixels[n23] = -14671617;
                    }
                }
                if (this.random(0.0f, 1.0f) >= 0.1f) {
                    break;
                }
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
                    break;
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
                int n24;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n24 = n + this.WIDTH + n2] == -16777216 || this.pixels[n24 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n24 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n24 = n + n2 + 1] == -16777216 || this.pixels[n24 = n + n2 - 1] == -16777216)) {
                    this.pixels[n24] = -8372160;
                    this.pixels[n + n2] = -16777216;
                    break;
                }
                break;
            }
            case -1: {
                int n25;
                if (this.random(0.0f, 1.0f) < 0.95f && (this.pixels[n25 = n + this.WIDTH + n2] == -16777216 || this.pixels[n25 = n + n2 + this.WIDTH + 1] == -16777216 || this.pixels[n25 = n + n2 + this.WIDTH - 1] == -16777216 || this.pixels[n25 = n + n2 + 1] == -16777216 || this.pixels[n25 = n + n2 - 1] == -16777216)) {
                    this.pixels[n25] = -1;
                    this.pixels[n + n2] = -16777216;
                }
                int n26;
                if (this.pixels[n26 = n + this.WIDTH + n2] == -4464 || this.pixels[n26 = n + n2 + 1] == -4464 || this.pixels[n26 = n + n2 - 1] == -4464) {
                    this.pixels[n26] = -16777216;
                    this.ball.salt(n2, n3);
                    break;
                }
                int n27;
                if (this.pixels[n27 = n + this.WIDTH + n2] == -4463 || this.pixels[n27 = n + n2 + 1] == -4463 || this.pixels[n27 = n + n2 - 1] == -4463) {
                    this.pixels[n27] = -16777216;
                    this.ball.salt(n2, n3);
                    break;
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
                    break;
                }
                this.pixels[n + n2] = -4463;
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
        for (int i = 0; i < this.menu_count[this.MENU_TIME]; ++i) {
            final int n = this.WIDTH / 5;
            if (this.menu_count[this.MENU_SAND] != 0) {
                for (int n2 = this.menu_count[this.MENU_SAND] * 3, j = 1; j <= n2; ++j) {
                    final int n3 = j * this.WIDTH + n;
                    for (int k = -n2; k <= n2; ++k) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n3 + k] = this.SAND_COLOR;
                        }
                    }
                }
            }
            if (this.menu_count[this.MENU_WATER] != 0) {
                for (int n4 = this.menu_count[this.MENU_WATER] * 3, l = 1; l <= n4; ++l) {
                    final int n5 = l * this.WIDTH + n * 2;
                    for (int n6 = -n4; n6 < n4; ++n6) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n5 + n6] = this.WATER_COLOR;
                        }
                    }
                }
            }
            if (this.menu_count[this.MENU_SALT] != 0) {
                for (int n7 = this.menu_count[this.MENU_SALT] * 3, n8 = 1; n8 <= n7; ++n8) {
                    final int n9 = n8 * this.WIDTH + n * 3;
                    for (int n10 = -n7; n10 <= n7; ++n10) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n9 + n10] = this.SALT_COLOR;
                        }
                    }
                }
            }
            if (this.menu_count[this.MENU_OIL] != 0) {
                for (int n11 = this.menu_count[this.MENU_OIL] * 3, n12 = 1; n12 <= n11; ++n12) {
                    final int n13 = n12 * this.WIDTH + n * 4;
                    for (int n14 = -n11; n14 <= n11; ++n14) {
                        if (this.random(0.0f, 1.0f) < 0.1f) {
                            this.pixels[n13 + n14] = this.OIL_COLOR;
                        }
                    }
                }
            }
            ++this.counter;
            if ((this.counter & 0x1) == 0x0) {
                for (int n15 = this.HEIGHT - 2; n15 > 0; --n15) {
                    final int n16 = n15 * this.WIDTH;
                    for (int n17 = 1; n17 < this.WIDTH - 1; ++n17) {
                        this.pset(n16, n17, n15);
                    }
                }
            }
            else {
                for (int n18 = this.HEIGHT - 2; n18 > 0; --n18) {
                    final int n19 = n18 * this.WIDTH;
                    for (int n20 = this.WIDTH - 2; n20 > 0; --n20) {
                        this.pset(n19, n20, n18);
                    }
                }
            }
            for (int n21 = 1; n21 < this.WIDTH - 1; ++n21) {
                this.pixels[n21] = this.BACK_COLOR;
                this.pixels[(this.HEIGHT - 2) * this.WIDTH + n21] = this.BACK_COLOR;
            }
            for (int n22 = 1; n22 < this.HEIGHT - 1; ++n22) {
                this.pixels[this.WIDTH * n22] = this.WALL_COLOR;
                this.pixels[this.WIDTH * n22 + this.WIDTH - 1] = this.WALL_COLOR;
            }
            if (this.menu_count[this.MENU_SNIG] != 0) {
                if (this.menu_count[this.MENU_SNIG] == 1) {
                    this.ball.addForce(new Vec(0.0f, 0.1f));
                    this.ball.update();
                    this.ball.draw();
                    this.par.update();
                    this.par.draw();
                }
                else if (this.menu_count[this.MENU_SNIG] == 2) {
                    this.ball.draw();
                    this.par.draw();
                }
            }
            final float n23 = this.ball.scale * (10.0f + this.killed * 0.01f);
            if (n23 < 9.0f) {
                this.par.add(this.ball.pts[0].pos.x, this.ball.pts[0].pos.y, 10.0f);
                this.ball = new Ball(32, n23);
                if (n23 > 100.0f) {
                    this.ball.trans(new Vec(this.WIDTH / 2, this.HEIGHT / 2));
                }
                else {
                    this.ball.trans(new Vec(this.random(n23, this.WIDTH - n23), this.random(n23, this.HEIGHT - n23)));
                }
                ++this.killed;
            }
        }
        if (this.nmousePressed && this.mouseY < this.HEIGHT) {
            this.noStroke();
            this.fill(this.resource_color[this.pen_num]);
            this.drawLine(this.pmouseX, this.pmouseY, this.mouseX, this.mouseY, this.pen_size_list[this.menu_count[this.MENU_PEN]], 10000.0f);
            this.strokeWeight(1.0f);
        }
        this.fill(60.0f, 60.0f, 60.0f);
        this.stroke(60.0f, 120.0f, 200.0f);
        this.rect(0.0f, this.HEIGHT, this.WIDTH, this.SHEIGHT);
        final int n24 = 6;
        for (int pen_num = 0; pen_num < this.RESOURCE_N; ++pen_num) {
            final int n25 = pen_num % n24;
            final int n26 = pen_num / n24;
            if (this.overRect(this.WIDTH / 4 * n26, this.HEIGHT + this.SHEIGHT / n24 * n25, this.WIDTH / 4, this.SHEIGHT / n24 - 1.0f, this.mouseX, this.mouseY)) {
                this.noStroke();
                this.fill(-16777216);
                this.rect(this.WIDTH / 4 * n26, this.HEIGHT + this.SHEIGHT / n24 * n25 + 2.0f, this.WIDTH / 4, this.SHEIGHT / n24);
                if (this.nmousePressed && !this.pmousePressed) {
                    this.pen_num = pen_num;
                }
            }
            if (this.pen_num == pen_num) {
                this.stroke(-1);
                this.fill(-11184811);
                this.rect(this.WIDTH / 4 * n26, this.HEIGHT + this.SHEIGHT / n24 * n25 + 2.0f, this.WIDTH / 4, this.SHEIGHT / n24);
            }
            else {
                this.stroke(this.resource_color[pen_num]);
            }
            this.fill(this.resource_color[pen_num]);
            this.textSize(16.0f);
            this.text(this.resource_text[pen_num], this.WIDTH / 4 * n26 + 5, this.HEIGHT + this.SHEIGHT / n24 * n25 + 12.0f);
        }
        for (int n27 = 0; n27 < this.MENU_N; ++n27) {
            if (this.overRect(this.WIDTH / 2, this.HEIGHT + this.SHEIGHT / this.MENU_N * n27, this.WIDTH / 2, this.SHEIGHT / this.MENU_N - 1.0f, this.mouseX, this.mouseY)) {
                this.noStroke();
                this.fill(-16777216);
                this.rect(this.WIDTH / 2 - 2, this.HEIGHT + this.SHEIGHT / this.MENU_N * n27 + 4.0f, this.WIDTH / 2, 12.0f);
                if (this.nmousePressed && !this.pmousePressed) {
                    final int[] menu_count = this.menu_count;
                    final int n28 = n27;
                    ++menu_count[n28];
                    if (this.menu_count[n27] > this.menu_level_max[n27]) {
                        this.menu_count[n27] = 0;
                    }
                }
            }
            this.textSize(16.0f);
            this.fill(-128);
            this.text(this.menu_text[n27] + this.menu_level_text[n27][this.menu_count[n27]], this.WIDTH / 2 + 3, this.HEIGHT + this.SHEIGHT / this.MENU_N * n27 + 11.0f);
        }
    }
    
    private final void _mththis() {
        this.counter = 0;
        this.SAND_COLOR = -1127296;
        this.WATER_COLOR = -14671617;
        this.FIRE_COLOR = -49088;
        this.BACK_COLOR = -16777216;
        this.WALL_COLOR = -8355712;
        this.PLANT_COLOR = -14627808;
        this.SMOKE_COLOR = -9408400;
        this.SPOUT_COLOR = -12549889;
        this.CERA_COLOR = -1122868;
        this.CERA2_COLOR = -1122867;
        this.UNIDENT_COLOR = -65281;
        this.OIL_COLOR = -8372160;
        this.SALT_COLOR = -1;
        this.SNIG_COLOR = -4464;
        this.SNIG2_COLOR = -4463;
        this.SNIG3_COLOR = -4462;
        this.WIDTH = 0;
        this.HEIGHT = 0;
        this.WH = this.WIDTH * this.HEIGHT;
        this.SHEIGHT = 80;
        this.RESOURCE_N = 11;
        this.resource_text = new String[] { "WALL", "FIRE", "WATER", "PLANT", "SAND", "SPOUT", "CERA", "???", "OIL", "ERASER", "SALT" };
        this.resource_color = new int[] { this.WALL_COLOR, this.FIRE_COLOR, this.WATER_COLOR, this.PLANT_COLOR, this.SAND_COLOR, this.SPOUT_COLOR, this.CERA_COLOR, this.UNIDENT_COLOR, this.OIL_COLOR, this.BACK_COLOR, this.SALT_COLOR };
        this.pen_num = 0;
        this.MENU_N = 7;
        this.MENU_PEN = 0;
        this.MENU_TIME = 1;
        this.MENU_SAND = 2;
        this.MENU_WATER = 3;
        this.MENU_OIL = 5;
        this.MENU_SALT = 4;
        this.MENU_SNIG = 6;
        this.menu_text = new String[] { "PEN-size:   ", "TIME-speed: ", "SAND:    ", "WATER: ", "SALT:     ", "OIL:       ", "NAMEKUJI:" };
        this.menu_count = new int[] { 1, 1, 1, 1, 1, 1, 1 };
        this.menu_level_text = new String[][] { { " x 1", " x 2", " x 4", " x 8", " x 16", " x 32" }, { " x 0", " x 1", " x 2", " x 3", " x 4", " x 5" }, { " x 0", " x 1", " x 2", " x 3", " x 4", " x 5" }, { " x 0", " x 1", " x 2", " x 3", " x 4", " x 5" }, { " x 0", " x 1", " x 2", " x 3", " x 4", " x 5" }, { " x 0", " x 1", " x 2", " x 3", " x 4", " x 5" }, { " VANISH", " MOVE", " STOP" } };
        this.menu_level_max = new int[] { 5, 5, 5, 5, 5, 5, 2 };
        this.pen_size_list = new int[] { 2, 4, 8, 16, 32, 64 };
        this.killed = 0;
        this.pmousePressed = false;
        this.nmousePressed = false;
        this.PAR_MAX = 40;
    }
    
    public sand7() {
        this._mththis();
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
            return sand7.this.sqrt(this.x * this.x + this.y * this.y);
        }
        
        float distance(final Vec vec) {
            final float n = this.x - vec.x;
            final float n2 = this.y - vec.y;
            return sand7.this.sqrt(n * n + n2 * n2);
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
            sand7.this.fill(sand7.this.WALL_COLOR);
            sand7.this.noStroke();
            sand7.this.beginShape(65);
            float n = 0.0f;
            float n2 = 0.0f;
            float sqrt = 0.0f;
            for (int i = 0; i < this.pts.length - 1; ++i) {
                n = -(this.pts[i + 1].pos.y - this.pts[i].pos.y);
                n2 = this.pts[i + 1].pos.x - this.pts[i].pos.x;
                sqrt = sand7.this.sqrt(n * n + n2 * n2);
                if (sqrt != 0.0f) {
                    final float n3 = n / sqrt;
                    final float n4 = n2 / sqrt;
                    n = n3 * this.air[i];
                    n2 = n4 * this.air[i];
                    sand7.this.vertex(this.pts[i].pos.x + n, this.pts[i].pos.y + n2);
                    sand7.this.vertex(this.pts[i].pos.x - n, this.pts[i].pos.y - n2);
                }
            }
            final int n5 = this.pts.length - 1;
            if (sqrt != 0.0f) {
                sand7.this.vertex(this.pts[n5].pos.x + n, this.pts[n5].pos.y + n2);
                sand7.this.vertex(this.pts[n5].pos.x - n, this.pts[n5].pos.y - n2);
            }
            sand7.this.endShape();
            sand7.this.stroke(0.0f, 0.0f, 0.0f);
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
                final int n4 = (int)sand7.this.random(k, this.links.length);
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
            final float pow = sand7.this.pow(sand7.this.pow(sand7.this.abs(sand7.this.cos(n * n5 / 4.0f) / 1.0f), n3) + sand7.this.pow(sand7.this.abs(sand7.this.sin(n * n5 / 4.0f) / 1.0f), n4), 1.0f / n2);
            if (sand7.this.abs(pow) == 0.0f) {
                return new Vec(0.0f, 0.0f);
            }
            final float n6 = 1.0f / pow;
            return new Vec(n6 * sand7.this.cos(n5), n6 * sand7.this.sin(n5));
        }
        
        void addForce(final Vec vec) {
            for (int i = 0; i < this.pts.length; ++i) {
                this.pts[i].pos.add(vec);
            }
        }
        
        void draw() {
            sand7.this.fill(sand7.this.SNIG_COLOR);
            sand7.this.noStroke();
            sand7.this.beginShape(256);
            for (int i = 1; i < this.pts.length; ++i) {
                sand7.this.vertex(this.pts[i].pos.x, this.pts[i].pos.y);
            }
            sand7.this.endShape();
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
                if (this.pts[i].pos.x < 3.0f) {
                    this.pts[i].pos.x = 0.0f;
                    this.pts[i].pos.y = this.pts[i].prev.y * 0.9f + this.pts[i].pos.y * 0.1f;
                    this.wall_on |= 0x2;
                }
                else if (this.pts[i].pos.x > sand7.this.WIDTH - 4) {
                    this.pts[i].pos.x = sand7.this.WIDTH - 1;
                    this.pts[i].pos.y = this.pts[i].prev.y * 0.9f + this.pts[i].pos.y * 0.1f;
                    this.wall_on |= 0x4;
                }
                else if (this.pts[i].pos.y < 4.0f) {
                    this.pts[i].pos.y = 0.0f;
                    this.pts[i].pos.x = this.pts[i].prev.x * 0.9f + this.pts[i].pos.x * 0.1f;
                    this.wall_on |= 0x8;
                }
                else if (this.pts[i].pos.y > sand7.this.HEIGHT - 4) {
                    this.pts[i].pos.y = sand7.this.HEIGHT - 1;
                    this.pts[i].pos.x = this.pts[i].prev.x * 0.5f + this.pts[i].pos.x * 0.5f;
                    this.wall_on |= 0x10;
                }
                else {
                    final int n = sand7.this.pixels[(int)this.pts[i].pos.y * sand7.this.WIDTH + (int)this.pts[i].pos.x];
                    if (n != sand7.this.BACK_COLOR && n != sand7.this.SNIG_COLOR && n != sand7.this.SNIG2_COLOR) {
                        this.pts[i].pos.assign(this.pts[i].prev);
                    }
                }
            }
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
            final float n3 = (int)sand7.this.random(0.0f, 256.0f);
            final float n4 = 1.0f;
            final float n5 = 1.0f;
            final float n6 = 1.0f;
            for (int i = 1; i < this.pts.length; ++i) {
                final Vec eval = this.eval(n3, n4, n5, n6, (i + 2) * 6.283185f / n);
                eval.mul(n2);
                this.pts[i] = new MassPoint(eval);
            }
            this.scale = 2.0f;
            this.limit_scale = 0.5f;
            this.right_tern = true;
            this.power = 0.2f;
            this.wall_on = 0;
            this.links = new MassPointLink[this.pts.length * (n / 4)];
            int n7 = 0;
            for (int j = 0; j < this.pts.length; ++j) {
                for (int k = 1; k <= n / 4; ++k) {
                    this.links[n7] = new MassPointLink(this.pts[j], this.pts[(j + k) % this.pts.length]);
                    ++n7;
                }
            }
            for (int l = 0; l < this.links.length; ++l) {
                final MassPointLink massPointLink = this.links[l];
                final int n8 = (int)sand7.this.random(l, this.links.length);
                this.links[l] = this.links[n8];
                this.links[n8] = massPointLink;
            }
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
            for (int i = 0; i < sand7.this.PAR_MAX; ++i) {
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
                this.add(n, n2, sand7.this.random(-n3 * 0.5f, n3 * 0.5f), sand7.this.random(-n3 * 0.5f, n3 * 0.5f), sand7.this.SNIG3_COLOR, sand7.this.random(1.0f, n3));
            }
        }
        
        void update() {
            for (int i = 0; i < sand7.this.PAR_MAX; ++i) {
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
                    if (this.ys[i] > sand7.this.HEIGHT) {
                        this.alives[i] = false;
                    }
                }
            }
        }
        
        void draw() {
            sand7.this.noStroke();
            for (int i = 0; i < sand7.this.PAR_MAX; ++i) {
                if (this.alives[i]) {
                    sand7.this.fill(this.cols[i]);
                    sand7.this.ellipse(this.xs[i], this.ys[i], this.sizes[i], this.sizes[i]);
                }
            }
        }
        
        void reset() {
            for (int i = 0; i < sand7.this.PAR_MAX; ++i) {
                this.alives[i] = false;
            }
        }
        
        Particles() {
            this.xs = new float[sand7.this.PAR_MAX];
            this.ys = new float[sand7.this.PAR_MAX];
            this.dxs = new float[sand7.this.PAR_MAX];
            this.dys = new float[sand7.this.PAR_MAX];
            this.cols = new int[sand7.this.PAR_MAX];
            this.sizes = new float[sand7.this.PAR_MAX];
            this.alives = new boolean[sand7.this.PAR_MAX];
            for (int i = 0; i < sand7.this.PAR_MAX; ++i) {
                this.alives[i] = false;
            }
        }
    }
}

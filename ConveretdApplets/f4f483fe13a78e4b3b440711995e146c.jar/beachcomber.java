// 
// Decompiled by Procyon v0.5.30
// 

public class beachcomber extends BApplet
{
    int direction;
    int stone;
    int scatter;
    int wipenext;
    BImage beachpic;
    
    void setup() {
        this.size(200, 200);
        this.beachpic = this.loadImage("god.jpg");
        this.noBackground();
    }
    
    void loop() {
        for (int i = 0; i < 10000; ++i) {
            final int n = (int)this.random(super.width - 2) + 1;
            final int n2 = (int)this.random(super.height - 2) + 1;
            int n3 = 0;
            int n4 = 0;
            if (this.direction == 0) {
                n3 = n;
                n4 = n2 - 1;
            }
            if (this.direction == 1) {
                n3 = n + 1;
                n4 = n2 - 1;
            }
            if (this.direction == 2) {
                n3 = n + 1;
                n4 = n2;
            }
            if (this.direction == 3) {
                n3 = n + 1;
                n4 = n2 + 1;
            }
            if (this.direction == 4) {
                n3 = n;
                n4 = n2 + 1;
            }
            if (this.direction == 5) {
                n3 = n - 1;
                n4 = n2 + 1;
            }
            if (this.direction == 6) {
                n3 = n - 1;
                n4 = n2;
            }
            if (this.direction == 7) {
                n3 = n - 1;
                n4 = n2 - 1;
            }
            if (this.scatter == 1) {
                n3 = n3 + (int)this.random(3.0f) - 1;
                n4 = n4 + (int)this.random(3.0f) - 1;
            }
            if (n3 < super.width && n3 > 0 && n4 < super.height && n4 > 0) {
                final int pixel = this.getPixel(n, n2);
                final int pixel2 = this.getPixel(n3, n4);
                if (this.red(pixel) + this.green(pixel) + this.blue(pixel) > this.red(pixel2) + this.green(pixel2) + this.blue(pixel2)) {
                    this.setPixel(n, n2, pixel2);
                    this.setPixel(n3, n4, pixel);
                }
            }
        }
        if (this.wipenext == 1) {
            this.wipenext = 0;
            if (this.stone == 3) {
                this.image(this.beachpic, 0.0f, 0.0f);
                return;
            }
            for (int j = super.width - 1; j >= 0; --j) {
                for (int k = 0; k < super.height; ++k) {
                    this.setPixel(j, k, this.pebble());
                }
            }
        }
    }
    
    void keyPressed() {
        if (super.key == 122 || super.key == 90) {
            this.size(100, 100);
            ++this.direction;
            if (this.direction == 8) {
                this.direction = 0;
            }
        }
        if (super.key == 32) {
            this.wipenext = 1;
        }
        if (super.key == 120 || super.key == 88) {
            ++this.stone;
            if (this.stone == 4) {
                this.stone = 0;
            }
            this.wipenext = 1;
        }
        if (super.key == 99 || super.key == 67) {
            this.scatter = 1 - this.scatter;
        }
    }
    
    void mousePressed() {
        for (int i = -8; i < 8; ++i) {
            for (int j = -8; j < 8; ++j) {
                if (this.sqrt(j * j + i * i) < 8.0f) {
                    final int n = (int)this.random(255.0f);
                    if (super.mouseY + i > 0 && super.mouseY + i < super.height && super.mouseX + j > 0 && super.mouseX + j < super.width) {
                        this.setPixel(super.mouseX + j, super.mouseY + i, this.pebble());
                    }
                }
            }
        }
    }
    
    int pebble() {
        if (this.stone == 0) {
            final int n = (int)this.random(155.0f) + 100;
            return this.color(n, n / 1.5f + this.random(50.0f), n / 3 + this.random(50.0f));
        }
        if (this.stone != 2) {
            final int n2 = (int)this.random(255.0f);
            return this.color(n2, n2, n2);
        }
        if ((int)this.random(2.0f) == 1) {
            return this.color(200.0f, 0.0f, 0.0f);
        }
        return this.color(100.0f, 0.0f, 0.0f);
    }
    
    public beachcomber() {
        this.Block$();
    }
    
    private void Block$() {
        this.direction = 0;
        this.stone = 0;
        this.scatter = 0;
        this.wipenext = 1;
    }
}

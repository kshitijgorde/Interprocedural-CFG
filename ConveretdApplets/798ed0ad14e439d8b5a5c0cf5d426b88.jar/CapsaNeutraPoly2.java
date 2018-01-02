import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraPoly2 extends NeuterBox
{
    Poly2 C;
    float create;
    
    public CapsaNeutraPoly2(final int n) {
        String s = null;
        this.create = 0.05f;
        switch (n) {
            case 0: {
                s = "m 0.8 1 0.75 0.15 d 16 20 ox 2 oy 9 inici 0 -9 g 90 t 4 z 8.5 0 -65 7 t 4 z 5.5 0 90 5 g -90 l 2 g -90 t 6 z -9.5 0 115 7 t 4 z -4.5 0 -90 3 inici 0 -9 clock g -90 t 4 z 8.5 0 65 7 t 4 z 5.5 0 -90 5 g 90 l 2 g 90 t 6 z -9.5 0 -115 7 t 4 z -4.5 0 90 3 end";
                break;
            }
            case 1: {
                s = "m 0.602 1 0.75 0.15 d 12 20 ox 6.01 oy 9.0 inici 0 -3 l 2 g -90 l 6 t 5.5 z 8.02 0 90 5.5 l 6 g -90 l 2 g -90 l 5.5 t 8.5 z -12.02 0 90 8.5 end";
                this.create = 0.1f;
                break;
            }
            case 13: {
                s = "m 0.602 1 0.75 0.15 d 12 20 ox 6.01 oy 9.0 i 0 -15 clock l 2 g 90 l 6 t 5.5 z 8.02 0 -90 5.5 l 6 g 90 l 2 g 90 l 5.5 t 8.5z -12.02 0 -90 8.5 end";
                this.create = 0.1f;
                break;
            }
            case 2: {
                s = "m 0.55 1 0.75 0.15 d 11 20 ox 2.5 oy 9 i 0 -3 l 1.6 g -90 l 12 g -90 l 1.6 i 3 -3 l 1.6 g -90 l 12 g -90 l 1.6 i 9 -3 l 2 g -90 l 12 g -90 l 2 i 3.6 -3 s 1 0 s 4.4 -8 s 1 -4 s -1 0 s -4.4 8 end";
                break;
            }
            case 3: {
                s = "m 0.5 1 0.75 0.15 d 10 20 oy 9 i 0 -3 l 10 s 0 -2 s -4 -8 s -2 0 s 4 8 s -8 0 i 10 -15 s -10 0 s 0 2 s 4 8 s 2 0 s -4 -8 s 8 0 end";
                break;
            }
            case 4: {
                s = "m 0.60 1 0.75 0.15 d 12 20 oy 9 ox 6 inici 0 -9 g 90 t 3.5 z 6 6 0 3.5 z 6 -6 -90 3.5 g -90  l 2 g -90 t 6 z -8 0 -90 6 inici 12 -9 g -90 t 3.5  z -6 -6 180 3.5 z -6 6 90 3.5 g -90 l 2 g -90 t 6 z 8 0 90 6 i 3.4 -4 l 1.6  g -90 l 10 g -90 l 1.6 i 7 -10 g -65 l 7 g -90 l 1 g -90 l 7 end";
                break;
            }
            case 5: {
                s = "m 0.60 1 0.75 0.15 d 12 20 ox 2.5 oy 6.5 i 4.6 -3 l 4 t 4 z 0.8 -6.5 180 3 s 3 -5.5 l 2.5 s -3 5 l 2.5 g -90 l 2 g -90 l 3.5 t 2 z 0 3 180 2 l 3.5 i 3 -3 l 1.6 g -90 l 12 g -90 l 1.6 i 0 -3 l 1.6 g -90 l 12 g -90 l 1.6 end";
                break;
            }
            case 6: {
                s = "m 0.50 1 0.75 0.15 d 10 20 oy 9 ox 6 inici 0 -9 g 90 t 3.5 z 6 6 0 3.5 z 4 -2 -65 0 g -90 l 2 g -90 t 4 z -6.3 -3.2 -90 5 inici 10 -13 g -115 t 0 z -4 -2 180 3.5 z -6 6 90 3.5 g -90 l 2 g -90 t 5 z 6.2 -3.2 65 4 i 3.4 -4 l 1.6 g -90 l 10 g -90 l 1.6 end";
                break;
            }
            case 7: {
                s = "v 1 m 1 3 1.8 1.2 d 8.4 20.5 inici 6.05 -1.0g 70a 1.2 1.2 -140a .5 .5 -290g 130a .9 .9 30a .5 .5 60a .8 .8 20a 1 1 10a 10 10 5a 20 20 5a 170 170 -5a 20 20 -5a 1.2 1.2 -140a .5 .5 -290g 130a .9 .9 30a .5 .5 60a .8 .8 20a 1 1 10a 10 10 5a 20 20 5a 170 170 -5a 20 20 -5 end ";
                break;
            }
            case 8: {
                s = "v 1 m 0.70 1 0.75 0.15 d 100 154 inici 90 -49 g 180 l 58 a 50 50 10  a 10 10 35 a 40 40 30 g 105 l 2.5 g 55 a 35 35 -20 a 15 15 -35 l 5 g -92 a 100 100 -13 a 50 50 -10 a 100 100 -10 a 9 8 185 a 30 30 17 a 100 177 15 g -92 l 25 g -100 a 200 350 5 a 200 140 5 a 15 15 160 a 50 50 10 g 100 l 2 g 80 a 9 9 -170 a 200 360 -6 g -84 l 17.2 end";
                break;
            }
            case 10: {
                s = "v 1 m 0.6 0.5 0.5 0.15 d 106 50  inici  98 -50 g 135 l 60 g 90 l 60 g 135 l 10 g 45 l 46 g -90 l 46 g 45l 10 end";
                this.create = 0.1f;
                break;
            }
            case 9: {
                s = "v 1 m 0.6 0.5 0.42 0.15 d 106 50 inici  11    0 g -45 l 60 g 90 l 60 g 135 l 10 g 45 l 46 g -90 l 46 g 45l 10 end";
                this.create = 0.1f;
                break;
            }
            case 11: {
                s = "v 1 m 1.4 1.8 1.2 0.15 d 100 100 inici 90 0 g 180 l 90 g 135 l 70.711 g -90 l 70.711 g 135 l 90 g 70 l 20 g 155 l 15 g -45 l 70 g -135 l 65 g 90 l 58 g -135 l 70.2 g -55 l 10 g 155 l 14 end";
                break;
            }
            case 12: {
                s = "v 1 m 1.4 1.8 1.2 0.15 d 100 100 inici 100 0 g 180 l 100 g 90 l 2 g 90 l 5 a 5 5 -90 l 86 a 5 5 -90 l 5 g 90 l 2 g 90 l 32 g 90 l 2 g 90 l 5 a 5 5 -90 l 88 g -90 l 56 g -90 l 88 a 5 5 -90 l 5 g 90 l 2 g 90 l 32 g 90 l 2 g 90 l 5 a 5 5 -90 l 86 a 5 5 -90 l 5 g 90 l 2 end";
                break;
            }
            case 14: {
                s = "v 1 m 1.4 1.8 1.2 0.15 d 100 100 inici 100 -100 g 90 l 60 a 50 40 180 l 60 g 90 l 12 g 90 l 60 a 38 30 -180 l 60 g 90 l 12 end";
                this.create = 0.1f;
                break;
            }
            case 15: {
                s = "v 1 m 1.4 1.8 1.2 0.15 d 100 100 inici   0   0 g 270 l 60 a 50 40 180 l 60 g 90 l 12 g 90 l 60 a 38 30 -180 l 60 g 90 l 12 end";
                this.create = 0.1f;
                break;
            }
            case 16: {
                s = "m 0.866 1 1 0.2 d 13 15 ox 9 oy 7 inici 6 -15 s -4.5 6.7 s -1.5 -0.5 s 2 2 s 4 -6 s 0.5 -1 inici 12.5 0 s -6 -14 l -1 0 s 6 14 end";
                break;
            }
            case 17: {
                s = "m 0.866 1 1 0.2 d 13 15 ox 9 oy 7 inici 6 -15 s -5 7.3333 s 1.3333 0.5 s 3.6666 -5.66666 s 0.5 -1 inici 12.5 0 s -6 -14 l -1 0 s 6 14 end";
                break;
            }
            case 18: {
                s = "m 0.60 1 0.75 0.15 d 12 20 oy 9 ox 6 inici 0 -9 g 90 t 3.5 z 6 6 0 3.5 z 6 -6 -90 3.5 g -90  l 2 g -90 t 6 z -8 0 -90 6 inici 12 -9 g -90 t 3.5  z -6 -6 180 3.5 z -6 6 90 3.5 g -90 l 2 g -90 t 6 z 8 0 90 6 i 10.8 -1.8 al 2.8 -16.8 al 1.2 -16.2 al 9.2 -1.2 end";
                break;
            }
        }
        this.C = Poly2.create(s, 24.0f);
    }
    
    public final void calculRect(final BoxComponent boxComponent) {
        if (this.C.versio == 2) {
            super.width = this.C.getWidth(super.D) + 2 * Math.max(this.em(this.create), 1);
            super.height = this.C.getHeight(super.D);
            super.baseline = this.C.getBaseline(super.D);
        }
        else {
            super.width = this.em(this.C.width);
            if (super.width == 17) {
                super.width = 16;
            }
            super.height = this.em(this.C.height);
            super.baseline = this.em(this.C.baseline);
        }
    }
    
    public final void onPaint(final Graphics graphics) {
        if (this.C.versio == 2) {
            this.C.draw(Math.max(this.em(this.create), 1), super.baseline, super.D, true, true, graphics);
        }
        if (this.C.versio == 1) {
            this.C.draw(0, 0, super.width, super.height, true, true, graphics);
        }
    }
    
    static final void I(final String s, final int n) {
        NeuterBox.S.put(s, new Integer(n));
    }
}

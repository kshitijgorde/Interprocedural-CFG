// 
// Decompiled by Procyon v0.5.30
// 

final class Class72
{
    int anInt536;
    int anInt537;
    int anInt538;
    private int anInt539;
    int anInt540;
    int anInt541;
    int anInt542;
    boolean aBoolean543;
    boolean aBoolean544;
    
    private final void method716(final Class98_Sub22 class98_Sub22, final boolean b, final int n) {
        try {
            if (~n != 0xFFFFFFFE) {
                if (n == 2) {
                    this.anInt537 = class98_Sub22.readShort((byte)127);
                    if (~this.anInt537 == 0xFFFF0000) {
                        this.anInt537 = -1;
                    }
                }
                else if (n == 3) {
                    this.anInt536 = class98_Sub22.readShort((byte)127) << -380251870;
                }
                else if (n != 4) {
                    if (n == 5) {
                        this.aBoolean543 = false;
                    }
                }
                else {
                    this.aBoolean544 = false;
                }
            }
            else {
                this.method718(0, this.anInt539 = class98_Sub22.method1186(-126));
            }
            if (!b) {
                this.anInt536 = -118;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "el.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    final void method717(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)40);
                if (unsignedByte == 0) {
                    break;
                }
                this.method716(class98_Sub22, true, unsignedByte);
            }
            if (n > -25) {
                this.anInt539 = 39;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "el.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method718(final int n, final int n2) {
        try {
            final double n3 = (n2 >> -356542736 & 0xFF) / 256.0;
            final double n4 = ((n2 & 0xFF10) >> -1757323064) / 256.0;
            final double n5 = (0xFF & n2) / 256.0;
            double n6 = n3;
            if (n4 < n6) {
                n6 = n4;
            }
            if (n5 < n6) {
                n6 = n5;
            }
            double n7 = n3;
            if (n4 > n7) {
                n7 = n4;
            }
            if (n7 < n5) {
                n7 = n5;
            }
            double n8 = 0.0;
            double n9 = 0.0;
            final double n10 = (n7 + n6) / 2.0;
            if (n7 != n6) {
                if (n10 < 0.5) {
                    n9 = (n7 - n6) / (n7 + n6);
                }
                if (n3 == n7) {
                    n8 = (-n5 + n4) / (n7 - n6);
                }
                else if (n4 != n7) {
                    if (n7 == n5) {
                        n8 = 4.0 + (n3 - n4) / (n7 - n6);
                    }
                }
                else {
                    n8 = (-n3 + n5) / (n7 - n6) + 2.0;
                }
                if (n10 >= 0.5) {
                    n9 = (-n6 + n7) / (-n7 + 2.0 - n6);
                }
            }
            final double n11 = n8 / 6.0;
            this.anInt542 = (int)(n10 * 256.0);
            this.anInt541 = (int)(n9 * 256.0);
            if (~this.anInt542 <= -1) {
                if (this.anInt542 > 255) {
                    this.anInt542 = 255;
                }
            }
            else {
                this.anInt542 = 0;
            }
            if (n > this.anInt541) {
                this.anInt541 = 0;
            }
            else if (~this.anInt541 < -256) {
                this.anInt541 = 255;
            }
            if (n10 <= 0.5) {
                this.anInt540 = (int)(512.0 * (n10 * n9));
            }
            else {
                this.anInt540 = (int)(n9 * (1.0 - n10) * 512.0);
            }
            if (this.anInt540 < 1) {
                this.anInt540 = 1;
            }
            this.anInt538 = (int)(n11 * this.anInt540);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "el.B(" + n + ',' + n2 + ')');
        }
    }
    
    public Class72() {
        this.anInt536 = 512;
        this.anInt537 = -1;
        this.anInt539 = 0;
        this.aBoolean543 = true;
        this.aBoolean544 = true;
    }
}

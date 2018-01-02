// 
// Decompiled by Procyon v0.5.30
// 

public final class Flo
{
    public static String loc;
    public static Flo[] cache;
    public int anInt390;
    public int anInt391;
    public boolean aBoolean393;
    public int anInt394;
    public int anInt395;
    public int anInt396;
    public int anInt397;
    public int anInt398;
    public int anInt399;
    
    public static byte[] getData(final String s) {
        return FileOperations.ReadFile(s);
    }
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        final Stream stream = new Stream(getData(Flo.loc + "flo.dat"));
        final int unsignedWord = stream.readUnsignedWord();
        if (Flo.cache == null) {
            Flo.cache = new Flo[unsignedWord];
        }
        for (int i = 0; i < unsignedWord; ++i) {
            if (Flo.cache[i] == null) {
                Flo.cache[i] = new Flo();
            }
            Flo.cache[i].readValues(stream);
        }
    }
    
    private void readValues(final Stream stream) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                this.method262(this.anInt390 = stream.read3Bytes());
            }
            else if (unsignedByte == 2) {
                this.anInt391 = stream.readUnsignedByte();
            }
            else {
                if (unsignedByte == 3) {
                    continue;
                }
                if (unsignedByte == 5) {
                    this.aBoolean393 = false;
                }
                else if (unsignedByte == 6) {
                    stream.readString();
                }
                else if (unsignedByte == 7) {
                    final int anInt394 = this.anInt394;
                    final int anInt395 = this.anInt395;
                    final int anInt396 = this.anInt396;
                    final int anInt397 = this.anInt397;
                    this.method262(stream.read3Bytes());
                    this.anInt394 = anInt394;
                    this.anInt395 = anInt395;
                    this.anInt396 = anInt396;
                    this.anInt397 = anInt397;
                    this.anInt398 = anInt397;
                }
                else {
                    System.out.println("Error unrecognised config code: " + unsignedByte);
                }
            }
        }
    }
    
    private void method262(final int n) {
        final double n2 = (n >> 16 & 0xFF) / 256.0;
        final double n3 = (n >> 8 & 0xFF) / 256.0;
        final double n4 = (n & 0xFF) / 256.0;
        double n5 = n2;
        if (n3 < n5) {
            n5 = n3;
        }
        if (n4 < n5) {
            n5 = n4;
        }
        double n6 = n2;
        if (n3 > n6) {
            n6 = n3;
        }
        if (n4 > n6) {
            n6 = n4;
        }
        double n7 = 0.0;
        double n8 = 0.0;
        final double n9 = (n5 + n6) / 2.0;
        if (n5 != n6) {
            if (n9 < 0.5) {
                n8 = (n6 - n5) / (n6 + n5);
            }
            if (n9 >= 0.5) {
                n8 = (n6 - n5) / (2.0 - n6 - n5);
            }
            if (n2 == n6) {
                n7 = (n3 - n4) / (n6 - n5);
            }
            else if (n3 == n6) {
                n7 = 2.0 + (n4 - n2) / (n6 - n5);
            }
            else if (n4 == n6) {
                n7 = 4.0 + (n2 - n3) / (n6 - n5);
            }
        }
        final double n10 = n7 / 6.0;
        this.anInt394 = (int)(n10 * 256.0);
        this.anInt395 = (int)(n8 * 256.0);
        this.anInt396 = (int)(n9 * 256.0);
        if (this.anInt395 < 0) {
            this.anInt395 = 0;
        }
        else if (this.anInt395 > 255) {
            this.anInt395 = 255;
        }
        if (this.anInt396 < 0) {
            this.anInt396 = 0;
        }
        else if (this.anInt396 > 255) {
            this.anInt396 = 255;
        }
        if (n9 > 0.5) {
            this.anInt398 = (int)((1.0 - n9) * n8 * 512.0);
        }
        else {
            this.anInt398 = (int)(n9 * n8 * 512.0);
        }
        if (this.anInt398 < 1) {
            this.anInt398 = 1;
        }
        this.anInt397 = (int)(n10 * this.anInt398);
        int n11 = this.anInt394 + (int)(Math.random() * 16.0) - 8;
        if (n11 < 0) {
            n11 = 0;
        }
        else if (n11 > 255) {
            n11 = 255;
        }
        int n12 = this.anInt395 + (int)(Math.random() * 48.0) - 24;
        if (n12 < 0) {
            n12 = 0;
        }
        else if (n12 > 255) {
            n12 = 255;
        }
        int n13 = this.anInt396 + (int)(Math.random() * 48.0) - 24;
        if (n13 < 0) {
            n13 = 0;
        }
        else if (n13 > 255) {
            n13 = 255;
        }
        this.anInt399 = this.method263(n11, n12, n13);
    }
    
    private int method263(final int n, int n2, final int n3) {
        if (n3 > 179) {
            n2 /= 2;
        }
        if (n3 > 192) {
            n2 /= 2;
        }
        if (n3 > 217) {
            n2 /= 2;
        }
        if (n3 > 243) {
            n2 /= 2;
        }
        return (n / 4 << 10) + (n2 / 32 << 7) + n3 / 2;
    }
    
    private Flo() {
        this.anInt391 = -1;
        this.aBoolean393 = true;
    }
    
    static {
        Flo.loc = SignLink.findcachedir() + "Data/Animation/";
    }
}

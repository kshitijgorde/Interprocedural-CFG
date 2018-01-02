// 
// Decompiled by Procyon v0.5.30
// 

public final class Varp
{
    public static Varp[] cache;
    private static int anInt702;
    private static int[] anIntArray703;
    public int anInt709;
    public boolean aBoolean713;
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        final Stream stream = new Stream(streamLoader.getDataForName("varp.dat"));
        Varp.anInt702 = 0;
        final int unsignedWord = stream.readUnsignedWord();
        if (Varp.cache == null) {
            Varp.cache = new Varp[unsignedWord];
        }
        if (Varp.anIntArray703 == null) {
            Varp.anIntArray703 = new int[unsignedWord];
        }
        for (int i = 0; i < unsignedWord; ++i) {
            if (Varp.cache[i] == null) {
                Varp.cache[i] = new Varp();
            }
            Varp.cache[i].readValues(stream, i);
        }
        if (stream.currentOffset != stream.buffer.length) {
            System.out.println("varptype load mismatch");
        }
    }
    
    private void readValues(final Stream stream, final int n) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                stream.readUnsignedByte();
            }
            else if (unsignedByte == 2) {
                stream.readUnsignedByte();
            }
            else if (unsignedByte == 3) {
                Varp.anIntArray703[Varp.anInt702++] = n;
            }
            else {
                if (unsignedByte == 4) {
                    continue;
                }
                if (unsignedByte == 5) {
                    this.anInt709 = stream.readUnsignedWord();
                }
                else {
                    if (unsignedByte == 6) {
                        continue;
                    }
                    if (unsignedByte == 7) {
                        stream.readDWord();
                    }
                    else if (unsignedByte == 8) {
                        this.aBoolean713 = true;
                    }
                    else if (unsignedByte == 10) {
                        stream.readString();
                    }
                    else if (unsignedByte == 11) {
                        this.aBoolean713 = true;
                    }
                    else if (unsignedByte == 12) {
                        stream.readDWord();
                    }
                    else {
                        if (unsignedByte == 13) {
                            continue;
                        }
                        System.out.println("Error unrecognised config code: " + unsignedByte);
                    }
                }
            }
        }
    }
    
    private Varp() {
        this.aBoolean713 = false;
    }
}

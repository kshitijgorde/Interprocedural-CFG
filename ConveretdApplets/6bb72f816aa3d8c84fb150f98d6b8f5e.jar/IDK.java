// 
// Decompiled by Procyon v0.5.30
// 

public final class IDK
{
    public static int length;
    public static IDK[] cache;
    public int anInt657;
    private int[] anIntArray658;
    private final int[] anIntArray659;
    private final int[] anIntArray660;
    private final int[] anIntArray661;
    public boolean aBoolean662;
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        final Stream stream = new Stream(streamLoader.getDataForName("idk.dat"));
        IDK.length = stream.readUnsignedWord();
        if (IDK.cache == null) {
            IDK.cache = new IDK[IDK.length];
        }
        for (int i = 0; i < IDK.length; ++i) {
            if (IDK.cache[i] == null) {
                IDK.cache[i] = new IDK();
            }
            IDK.cache[i].readValues(stream);
        }
    }
    
    private void readValues(final Stream stream) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                this.anInt657 = stream.readUnsignedByte();
            }
            else if (unsignedByte == 2) {
                final int unsignedByte2 = stream.readUnsignedByte();
                this.anIntArray658 = new int[unsignedByte2];
                for (int i = 0; i < unsignedByte2; ++i) {
                    this.anIntArray658[i] = stream.readUnsignedWord();
                }
            }
            else if (unsignedByte == 3) {
                this.aBoolean662 = true;
            }
            else if (unsignedByte >= 40 && unsignedByte < 50) {
                this.anIntArray659[unsignedByte - 40] = stream.readUnsignedWord();
            }
            else if (unsignedByte >= 50 && unsignedByte < 60) {
                this.anIntArray660[unsignedByte - 50] = stream.readUnsignedWord();
            }
            else if (unsignedByte >= 60 && unsignedByte < 70) {
                this.anIntArray661[unsignedByte - 60] = stream.readUnsignedWord();
            }
            else {
                System.out.println("Error unrecognised config code: " + unsignedByte);
            }
        }
    }
    
    public boolean method537() {
        if (this.anIntArray658 == null) {
            return true;
        }
        boolean b = true;
        for (int i = 0; i < this.anIntArray658.length; ++i) {
            if (!Model.method463(this.anIntArray658[i])) {
                b = false;
            }
        }
        return b;
    }
    
    public Model method538() {
        if (this.anIntArray658 == null) {
            return null;
        }
        final Model[] array = new Model[this.anIntArray658.length];
        for (int i = 0; i < this.anIntArray658.length; ++i) {
            array[i] = Model.method462(this.anIntArray658[i]);
        }
        Model model;
        if (array.length == 1) {
            model = array[0];
        }
        else {
            model = new Model(array.length, array);
        }
        for (int n = 0; n < 6 && this.anIntArray659[n] != 0; ++n) {
            model.method476(this.anIntArray659[n], this.anIntArray660[n]);
        }
        return model;
    }
    
    public boolean method539() {
        boolean b = true;
        for (int i = 0; i < 5; ++i) {
            if (this.anIntArray661[i] != -1 && !Model.method463(this.anIntArray661[i])) {
                b = false;
            }
        }
        return b;
    }
    
    public Model method540() {
        final Model[] array = new Model[5];
        int n = 0;
        for (int i = 0; i < 5; ++i) {
            if (this.anIntArray661[i] != -1) {
                array[n++] = Model.method462(this.anIntArray661[i]);
            }
        }
        final Model model = new Model(n, array);
        for (int n2 = 0; n2 < 6 && this.anIntArray659[n2] != 0; ++n2) {
            model.method476(this.anIntArray659[n2], this.anIntArray660[n2]);
        }
        return model;
    }
    
    private IDK() {
        this.anIntArray661 = new int[] { -1, -1, -1, -1, -1 };
        this.anInt657 = -1;
        this.anIntArray659 = new int[6];
        this.anIntArray660 = new int[6];
        this.aBoolean662 = false;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

public final class VarBit
{
    public static VarBit[] cache;
    public int anInt648;
    public int anInt649;
    public int anInt650;
    private boolean aBoolean651;
    
    public static void unpackConfig(final StreamLoader streamLoader) {
        final Stream stream = new Stream(streamLoader.getDataForName("varbit.dat"));
        final int unsignedWord = stream.readUnsignedWord();
        if (VarBit.cache == null) {
            VarBit.cache = new VarBit[unsignedWord];
        }
        for (int i = 0; i < unsignedWord; ++i) {
            if (VarBit.cache[i] == null) {
                VarBit.cache[i] = new VarBit();
            }
            VarBit.cache[i].readValues(stream);
            if (VarBit.cache[i].aBoolean651) {
                Varp.cache[VarBit.cache[i].anInt648].aBoolean713 = true;
            }
        }
        if (stream.currentOffset != stream.buffer.length) {
            System.out.println("varbit load mismatch");
        }
    }
    
    private void readValues(final Stream stream) {
        while (true) {
            final int unsignedByte = stream.readUnsignedByte();
            if (unsignedByte == 0) {
                break;
            }
            if (unsignedByte == 1) {
                this.anInt648 = stream.readUnsignedWord();
                this.anInt649 = stream.readUnsignedByte();
                this.anInt650 = stream.readUnsignedByte();
            }
            else if (unsignedByte == 10) {
                stream.readString();
            }
            else if (unsignedByte == 2) {
                this.aBoolean651 = true;
            }
            else if (unsignedByte == 3) {
                stream.readDWord();
            }
            else if (unsignedByte == 4) {
                stream.readDWord();
            }
            else {
                System.out.println("Error unrecognised config code: " + unsignedByte);
            }
        }
    }
    
    private VarBit() {
        this.aBoolean651 = false;
    }
}

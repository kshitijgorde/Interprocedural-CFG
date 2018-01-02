import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ObjectDef
{
    public boolean aBoolean736;
    private byte aByte737;
    private int anInt738;
    public String name;
    private int anInt740;
    private static final Model[] aModelArray741s;
    private byte aByte742;
    public int anInt744;
    private int anInt745;
    public int anInt746;
    private int[] originalModelColors;
    private int anInt748;
    public int anInt749;
    private boolean aBoolean751;
    public static boolean lowMem;
    private static Stream stream;
    public int type;
    private static int[] streamIndices;
    public boolean aBoolean757;
    public int anInt758;
    public int[] childrenIDs;
    private int anInt760;
    public int anInt761;
    public boolean aBoolean762;
    public boolean aBoolean764;
    public static client clientInstance;
    private boolean aBoolean766;
    public boolean aBoolean767;
    public int anInt768;
    private boolean aBoolean769;
    private static int cacheIndex;
    private int anInt772;
    private int[] anIntArray773;
    public int anInt774;
    public int anInt775;
    private int[] anIntArray776;
    public byte[] description;
    public boolean hasActions;
    public boolean aBoolean779;
    public static MRUNodes mruNodes2;
    public int anInt781;
    private static ObjectDef[] cache;
    private int anInt783;
    private int[] modifiedModelColors;
    public static MRUNodes mruNodes1;
    public String[] actions;
    
    public static ObjectDef forID(int type) {
        if (type > ObjectDef.streamIndices.length) {
            type = ObjectDef.streamIndices.length - 1;
        }
        for (int i = 0; i < 20; ++i) {
            if (ObjectDef.cache[i].type == type) {
                return ObjectDef.cache[i];
            }
        }
        ObjectDef.cacheIndex = (ObjectDef.cacheIndex + 1) % 20;
        final ObjectDef objectDef = ObjectDef.cache[ObjectDef.cacheIndex];
        ObjectDef.stream.currentOffset = ObjectDef.streamIndices[type];
        objectDef.type = type;
        objectDef.setDefaults();
        objectDef.readValues(ObjectDef.stream);
        return objectDef;
    }
    
    private void setDefaults() {
        this.anIntArray773 = null;
        this.anIntArray776 = null;
        this.name = null;
        this.description = null;
        this.modifiedModelColors = null;
        this.originalModelColors = null;
        this.anInt744 = 1;
        this.anInt761 = 1;
        this.aBoolean767 = true;
        this.aBoolean757 = true;
        this.hasActions = false;
        this.aBoolean762 = false;
        this.aBoolean769 = false;
        this.aBoolean764 = false;
        this.anInt781 = -1;
        this.anInt775 = 16;
        this.aByte737 = 0;
        this.aByte742 = 0;
        this.actions = null;
        this.anInt746 = -1;
        this.anInt758 = -1;
        this.aBoolean751 = false;
        this.aBoolean779 = true;
        this.anInt748 = 128;
        this.anInt772 = 128;
        this.anInt740 = 128;
        this.anInt768 = 0;
        this.anInt738 = 0;
        this.anInt745 = 0;
        this.anInt783 = 0;
        this.aBoolean736 = false;
        this.aBoolean766 = false;
        this.anInt760 = -1;
        this.anInt774 = -1;
        this.anInt749 = -1;
        this.childrenIDs = null;
    }
    
    public void method574(final OnDemandFetcher onDemandFetcher) {
        if (this.anIntArray773 == null) {
            return;
        }
        for (int i = 0; i < this.anIntArray773.length; ++i) {
            onDemandFetcher.method560(this.anIntArray773[i] & 0xFFFF, 0);
        }
    }
    
    public static void nullLoader() {
        ObjectDef.mruNodes1 = null;
        ObjectDef.mruNodes2 = null;
        ObjectDef.streamIndices = null;
        ObjectDef.cache = null;
        ObjectDef.stream = null;
    }
    
    public static void unpackConfig(final StreamLoader streamLoader) throws IOException {
        ObjectDef.stream = new Stream(streamLoader.getDataForName("loc.dat"));
        final Stream stream = new Stream(streamLoader.getDataForName("loc.idx"));
        final int unsignedWord = stream.readUnsignedWord();
        ObjectDef.streamIndices = new int[unsignedWord];
        int n = 2;
        for (int i = 0; i < unsignedWord; ++i) {
            ObjectDef.streamIndices[i] = n;
            n += stream.readUnsignedWord();
        }
        ObjectDef.cache = new ObjectDef[20];
        for (int j = 0; j < 20; ++j) {
            ObjectDef.cache[j] = new ObjectDef();
        }
    }
    
    public static void dumpObjects(final int n) {
        Writer writer = null;
        try {
            writer = new FileWriter(new File("objectdump.txt"));
        }
        catch (IOException ex) {}
        for (int i = 0; i < n; ++i) {
            try {
                final ObjectDef forID = forID(i);
                if (forID != null) {
                    writer.write(forID.type + "\t" + forID.name + "\t" + forID.actions[0] + "\r\n");
                }
            }
            catch (IOException ex2) {}
        }
        try {
            ((OutputStreamWriter)writer).close();
        }
        catch (IOException ex3) {}
    }
    
    public boolean method577(final int n) {
        if (this.anIntArray776 != null) {
            for (int i = 0; i < this.anIntArray776.length; ++i) {
                if (this.anIntArray776[i] == n) {
                    return Model.method463(this.anIntArray773[i] & 0xFFFF);
                }
            }
            return true;
        }
        if (this.anIntArray773 == null) {
            return true;
        }
        if (n != 10) {
            return true;
        }
        boolean b = true;
        for (int j = 0; j < this.anIntArray773.length; ++j) {
            b &= Model.method463(this.anIntArray773[j] & 0xFFFF);
        }
        return b;
    }
    
    public Model method578(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        Model method581 = this.method581(n, n7, n2);
        if (method581 == null) {
            return null;
        }
        if (this.aBoolean762 || this.aBoolean769) {
            method581 = new Model(this.aBoolean762, this.aBoolean769, method581);
        }
        if (this.aBoolean762) {
            final int n8 = (n3 + n4 + n5 + n6) / 4;
            for (int i = 0; i < method581.anInt1626; ++i) {
                final int n9 = method581.anIntArray1627[i];
                final int n10 = method581.anIntArray1629[i];
                final int n11 = n3 + (n4 - n3) * (n9 + 64) / 128;
                final int n12 = n11 + (n6 + (n5 - n6) * (n9 + 64) / 128 - n11) * (n10 + 64) / 128;
                final int[] anIntArray1628 = method581.anIntArray1628;
                final int n13 = i;
                anIntArray1628[n13] += n12 - n8;
            }
            method581.method467();
        }
        return method581;
    }
    
    public boolean method579() {
        if (this.anIntArray773 == null) {
            return true;
        }
        boolean b = true;
        for (int i = 0; i < this.anIntArray773.length; ++i) {
            b &= Model.method463(this.anIntArray773[i] & 0xFFFF);
        }
        return b;
    }
    
    public ObjectDef method580() {
        int n = -1;
        if (this.anInt774 != -1) {
            final VarBit varBit = VarBit.cache[this.anInt774];
            final int anInt648 = varBit.anInt648;
            final int anInt649 = varBit.anInt649;
            n = (ObjectDef.clientInstance.variousSettings[anInt648] >> anInt649 & client.anIntArray1232[varBit.anInt650 - anInt649]);
        }
        else if (this.anInt749 != -1) {
            n = ObjectDef.clientInstance.variousSettings[this.anInt749];
        }
        if (n < 0 || n >= this.childrenIDs.length || this.childrenIDs[n] == -1) {
            return null;
        }
        return forID(this.childrenIDs[n]);
    }
    
    private Model method581(final int n, final int n2, int n3) {
        Model model = null;
        long n4;
        if (this.anIntArray776 == null) {
            if (n != 10) {
                return null;
            }
            n4 = (this.type << 6) + n3 + (n2 + 1 << 32);
            final Model model2 = (Model)ObjectDef.mruNodes2.insertFromCache(n4);
            if (model2 != null) {
                return model2;
            }
            if (this.anIntArray773 == null) {
                return null;
            }
            final boolean b = this.aBoolean751 ^ n3 > 3;
            final int length = this.anIntArray773.length;
            for (int i = 0; i < length; ++i) {
                int n5 = this.anIntArray773[i];
                if (b) {
                    n5 += 65536;
                }
                model = (Model)ObjectDef.mruNodes1.insertFromCache(n5);
                if (model == null) {
                    model = Model.method462(n5 & 0xFFFF);
                    if (model == null) {
                        return null;
                    }
                    if (b) {
                        model.method477();
                    }
                    ObjectDef.mruNodes1.removeFromCache(model, n5);
                }
                if (length > 1) {
                    ObjectDef.aModelArray741s[i] = model;
                }
            }
            if (length > 1) {
                model = new Model(length, ObjectDef.aModelArray741s);
            }
        }
        else {
            int n6 = -1;
            for (int j = 0; j < this.anIntArray776.length; ++j) {
                if (this.anIntArray776[j] == n) {
                    n6 = j;
                    break;
                }
            }
            if (n6 == -1) {
                return null;
            }
            n4 = (this.type << 6) + (n6 << 3) + n3 + (n2 + 1 << 32);
            final Model model3 = (Model)ObjectDef.mruNodes2.insertFromCache(n4);
            if (model3 != null) {
                return model3;
            }
            int n7 = this.anIntArray773[n6];
            final boolean b2 = this.aBoolean751 ^ n3 > 3;
            if (b2) {
                n7 += 65536;
            }
            model = (Model)ObjectDef.mruNodes1.insertFromCache(n7);
            if (model == null) {
                model = Model.method462(n7 & 0xFFFF);
                if (model == null) {
                    return null;
                }
                if (b2) {
                    model.method477();
                }
                ObjectDef.mruNodes1.removeFromCache(model, n7);
            }
        }
        final boolean b3 = this.anInt748 != 128 || this.anInt772 != 128 || this.anInt740 != 128;
        final boolean b4 = this.anInt738 != 0 || this.anInt745 != 0 || this.anInt783 != 0;
        final Model model4 = new Model(this.modifiedModelColors == null, Class36.method532(n2), n3 == 0 && n2 == -1 && !b3 && !b4, model);
        if (n2 != -1) {
            model4.method469();
            model4.method470(n2);
            model4.anIntArrayArray1658 = null;
            model4.anIntArrayArray1657 = null;
        }
        while (n3-- > 0) {
            model4.method473();
        }
        if (this.modifiedModelColors != null) {
            for (int k = 0; k < this.modifiedModelColors.length; ++k) {
                model4.method476(this.modifiedModelColors[k], this.originalModelColors[k]);
            }
        }
        if (b3) {
            model4.method478(this.anInt748, this.anInt740, this.anInt772);
        }
        if (b4) {
            model4.method475(this.anInt738, this.anInt745, this.anInt783);
        }
        model4.method479(74 + this.aByte737, 948 + this.aByte742 * 5, -30, -50, -30, !this.aBoolean769);
        if (this.anInt760 == 1) {
            model4.anInt1654 = model4.modelHeight;
        }
        ObjectDef.mruNodes2.removeFromCache(model4, n4);
        return model4;
    }
    
    private void readValues(final Stream stream) {
        final ObjectDef objectDef = ObjectDef.cache[ObjectDef.cacheIndex];
        int unsignedByte = -1;
        while (true) {
            final int unsignedByte2 = stream.readUnsignedByte();
            if (unsignedByte2 == 0) {
                break;
            }
            if (unsignedByte2 == 1) {
                final int unsignedByte3 = stream.readUnsignedByte();
                if (unsignedByte3 <= 0) {
                    continue;
                }
                if (this.anIntArray773 == null || ObjectDef.lowMem) {
                    this.anIntArray776 = new int[unsignedByte3];
                    this.anIntArray773 = new int[unsignedByte3];
                    for (int i = 0; i < unsignedByte3; ++i) {
                        this.anIntArray773[i] = stream.readUnsignedWord();
                        this.anIntArray776[i] = stream.readUnsignedByte();
                    }
                }
                else {
                    stream.currentOffset += unsignedByte3 * 3;
                }
            }
            else if (unsignedByte2 == 2) {
                this.name = stream.readNewString();
            }
            else if (unsignedByte2 == 3) {
                this.description = stream.readBytes();
            }
            else if (unsignedByte2 == 5) {
                final int unsignedByte4 = stream.readUnsignedByte();
                if (unsignedByte4 <= 0) {
                    continue;
                }
                if (this.anIntArray773 == null || ObjectDef.lowMem) {
                    this.anIntArray776 = null;
                    this.anIntArray773 = new int[unsignedByte4];
                    for (int j = 0; j < unsignedByte4; ++j) {
                        this.anIntArray773[j] = stream.readUnsignedWord();
                    }
                }
                else {
                    stream.currentOffset += unsignedByte4 * 2;
                }
            }
            else if (unsignedByte2 == 14) {
                this.anInt744 = stream.readUnsignedByte();
            }
            else if (unsignedByte2 == 15) {
                this.anInt761 = stream.readUnsignedByte();
            }
            else if (unsignedByte2 == 17) {
                this.aBoolean767 = false;
            }
            else if (unsignedByte2 == 18) {
                this.aBoolean757 = false;
            }
            else if (unsignedByte2 == 19) {
                unsignedByte = stream.readUnsignedByte();
                if (unsignedByte != 1) {
                    continue;
                }
                this.hasActions = true;
            }
            else if (unsignedByte2 == 21) {
                this.aBoolean762 = true;
            }
            else if (unsignedByte2 == 22) {
                this.aBoolean769 = true;
            }
            else if (unsignedByte2 == 23) {
                this.aBoolean764 = true;
            }
            else if (unsignedByte2 == 24) {
                this.anInt781 = stream.readUnsignedWord();
                if (this.anInt781 != 65535) {
                    continue;
                }
                this.anInt781 = -1;
            }
            else if (unsignedByte2 == 28) {
                this.anInt775 = stream.readUnsignedByte();
            }
            else if (unsignedByte2 == 29) {
                this.aByte737 = stream.readSignedByte();
            }
            else if (unsignedByte2 == 39) {
                this.aByte742 = stream.readSignedByte();
            }
            else if (unsignedByte2 >= 30 && unsignedByte2 < 39) {
                if (this.actions == null) {
                    this.actions = new String[10];
                }
                this.actions[unsignedByte2 - 30] = stream.readNewString();
                if (!this.actions[unsignedByte2 - 30].equalsIgnoreCase("hidden")) {
                    continue;
                }
                this.actions[unsignedByte2 - 30] = null;
            }
            else if (unsignedByte2 == 40) {
                final int unsignedByte5 = stream.readUnsignedByte();
                this.modifiedModelColors = new int[unsignedByte5];
                this.originalModelColors = new int[unsignedByte5];
                for (int k = 0; k < unsignedByte5; ++k) {
                    this.modifiedModelColors[k] = stream.readUnsignedWord();
                    this.originalModelColors[k] = stream.readUnsignedWord();
                }
            }
            else if (unsignedByte2 == 60) {
                this.anInt746 = stream.readUnsignedWord();
            }
            else if (unsignedByte2 == 62) {
                this.aBoolean751 = true;
            }
            else if (unsignedByte2 == 64) {
                this.aBoolean779 = false;
            }
            else if (unsignedByte2 == 65) {
                this.anInt748 = stream.readUnsignedWord();
            }
            else if (unsignedByte2 == 66) {
                this.anInt772 = stream.readUnsignedWord();
            }
            else if (unsignedByte2 == 67) {
                this.anInt740 = stream.readUnsignedWord();
            }
            else if (unsignedByte2 == 68) {
                this.anInt758 = stream.readUnsignedWord();
            }
            else if (unsignedByte2 == 69) {
                this.anInt768 = stream.readUnsignedByte();
            }
            else if (unsignedByte2 == 70) {
                this.anInt738 = stream.readSignedWord();
            }
            else if (unsignedByte2 == 71) {
                this.anInt745 = stream.readSignedWord();
            }
            else if (unsignedByte2 == 72) {
                this.anInt783 = stream.readSignedWord();
            }
            else if (unsignedByte2 == 73) {
                this.aBoolean736 = true;
            }
            else if (unsignedByte2 == 74) {
                this.aBoolean766 = true;
            }
            else if (unsignedByte2 == 75) {
                this.anInt760 = stream.readUnsignedByte();
            }
            else {
                if (unsignedByte2 != 77) {
                    continue;
                }
                this.anInt774 = stream.readUnsignedWord();
                if (this.anInt774 == 65535) {
                    this.anInt774 = -1;
                }
                this.anInt749 = stream.readUnsignedWord();
                if (this.anInt749 == 65535) {
                    this.anInt749 = -1;
                }
                final int unsignedByte6 = stream.readUnsignedByte();
                this.childrenIDs = new int[unsignedByte6 + 1];
                for (int l = 0; l <= unsignedByte6; ++l) {
                    this.childrenIDs[l] = stream.readUnsignedWord();
                    if (this.childrenIDs[l] == 65535) {
                        this.childrenIDs[l] = -1;
                    }
                }
            }
        }
        if (unsignedByte == -1) {
            this.hasActions = (this.anIntArray773 != null && (this.anIntArray776 == null || this.anIntArray776[0] == 10));
            if (this.actions != null) {
                this.hasActions = true;
            }
        }
        if (this.aBoolean766) {
            this.aBoolean767 = false;
            this.aBoolean757 = false;
        }
        if (this.anInt760 == -1) {
            this.anInt760 = (this.aBoolean767 ? 1 : 0);
        }
    }
    
    private ObjectDef() {
        this.type = -1;
    }
    
    static {
        aModelArray741s = new Model[4];
        ObjectDef.mruNodes2 = new MRUNodes(30);
        ObjectDef.mruNodes1 = new MRUNodes(500);
    }
}

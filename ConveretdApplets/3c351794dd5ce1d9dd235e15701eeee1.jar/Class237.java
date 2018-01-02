import jagtheora.ogg.OggStreamState;
import java.io.IOException;
import jagtheora.ogg.OggSyncState;
import jagtheora.ogg.OggPage;
import jagtheora.ogg.OggPacket;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class237
{
    private Class98_Sub43_Sub4 aClass98_Sub43_Sub4_1801;
    private OggPacket anOggPacket1802;
    static boolean aBoolean1803;
    private boolean aBoolean1804;
    private boolean aBoolean1805;
    private String aString1806;
    private Class98_Sub43_Sub1 aClass98_Sub43_Sub1_1807;
    private OggPage anOggPage1808;
    static int[] anIntArray1809;
    private OggSyncState anOggSyncState1810;
    private Class377 aClass377_1811;
    private boolean aBoolean1812;
    private Class98_Sub43_Sub3 aClass98_Sub43_Sub3_1813;
    private byte[] aByteArray1814;
    private boolean aBoolean1815;
    
    static final void method2898(final int anInt77, final byte b, final int anInt78, final int anInt79, final int anInt80) {
        try {
            Class218.anInt1635 = anInt78;
            if (b <= -47) {
                Class3.anInt77 = anInt77;
                Class98_Sub10_Sub38.anInt5753 = anInt79;
                Class76_Sub8.anInt3778 = anInt80;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.F(" + anInt77 + ',' + b + ',' + anInt78 + ',' + anInt79 + ',' + anInt80 + ')');
        }
    }
    
    abstract int method2899(final int p0, final byte[] p1) throws IOException;
    
    final Class98_Sub43_Sub1 method2900(final byte b) {
        try {
            if (b != -79) {
                return null;
            }
            return this.aClass98_Sub43_Sub1_1807;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.Q(" + b + ')');
        }
    }
    
    final double method2901(final byte b) {
        try {
            if (this.aClass98_Sub43_Sub1_1807 != null) {
                return this.aClass98_Sub43_Sub1_1807.method1489(0);
            }
            if (this.aClass98_Sub43_Sub3_1813 != null) {
                return this.aClass98_Sub43_Sub3_1813.method1502(0);
            }
            if (b > -80) {
                method2906(108);
            }
            return 0.0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.G(" + b + ')');
        }
    }
    
    final boolean method2902(final boolean b) {
        try {
            if (!b) {
                Class237.aBoolean1803 = true;
            }
            return (this.aBoolean1815 || this.aBoolean1805) && (this.aClass98_Sub43_Sub1_1807 == null || this.aClass98_Sub43_Sub1_1807.method1492((byte)(-110)) <= 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.M(" + b + ')');
        }
    }
    
    final void method2903(final int n) {
        try {
            if (!this.aBoolean1815 && n == 8) {
                for (Class98_Sub43 class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3998(108); class98_Sub43 != null; class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3995(-1)) {
                    class98_Sub43.method1487(-1128);
                    class98_Sub43.anOggStreamState4241.a();
                }
                this.anOggPacket1802.a();
                this.anOggPage1808.a();
                this.anOggSyncState1810.a();
                this.aBoolean1815 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.L(" + n + ')');
        }
    }
    
    final void method2904(final boolean b) throws IOException {
        try {
            if (!b && !this.aBoolean1812) {
                while (!this.aBoolean1815) {
                    Class98_Sub43 method2914;
                    if (!this.aBoolean1804) {
                        method2914 = this.method2914(1);
                        if (method2914 == null) {
                            if (this.aBoolean1805) {
                                this.method2907(-2);
                                break;
                            }
                            break;
                        }
                        else {
                            if (method2914 == null) {
                                throw new IllegalStateException();
                            }
                            this.aBoolean1804 = true;
                        }
                    }
                    else {
                        method2914 = (Class98_Sub43)this.aClass377_1811.method3990(this.anOggPage1808.getSerialNumber(), -1);
                    }
                    if (this.aClass98_Sub43_Sub1_1807 == method2914) {
                        if (~this.aClass98_Sub43_Sub1_1807.method1492((byte)(-18)) <= -51) {
                            break;
                        }
                        while (~this.aClass98_Sub43_Sub1_1807.anOggStreamState4241.packetOut(this.anOggPacket1802) == 0xFFFFFFFE) {
                            this.aClass98_Sub43_Sub1_1807.method1486(this.anOggPacket1802, 21000);
                            this.method2910((byte)119);
                            if (this.aClass98_Sub43_Sub3_1813 != null) {
                                final double method2915 = this.aClass98_Sub43_Sub3_1813.method1502(0);
                                for (int n = 0; n < 10 && this.method2912((byte)(-59)); ++n) {
                                    this.method2905(-88);
                                    if (this.aBoolean1815) {
                                        return;
                                    }
                                }
                                if (this.aClass98_Sub43_Sub3_1813.method1502(0) > method2915) {
                                    return;
                                }
                            }
                            if (~this.aClass98_Sub43_Sub1_1807.method1492((byte)(-63)) <= -51) {
                                return;
                            }
                        }
                    }
                    else if (!(method2914 instanceof Class98_Sub43_Sub4)) {
                        if (this.aClass98_Sub43_Sub3_1813 != method2914) {
                            while (~method2914.anOggStreamState4241.packetOut(this.anOggPacket1802) == 0xFFFFFFFE) {
                                if (~method2914.anInt4240 == 0xFFFFFFFE && method2914 instanceof Class98_Sub43_Sub4) {
                                    this.method2909(!b, this.aString1806);
                                }
                                method2914.method1486(this.anOggPacket1802, 21000);
                            }
                        }
                        else if (this.aClass98_Sub43_Sub1_1807 == null && !this.aBoolean1812) {
                            for (int n2 = 0; ~n2 > -11 && this.method2912((byte)(-59)); ++n2) {
                                this.method2905(-95);
                                if (this.aBoolean1815) {
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    else {
                        this.method2910((byte)127);
                    }
                    this.aBoolean1804 = false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.E(" + b + ')');
        }
    }
    
    private final void method2905(final int n) throws IOException {
        try {
            while (this.aClass98_Sub43_Sub3_1813.anOggStreamState4241.packetOut(this.anOggPacket1802) != 1) {
                final Class98_Sub43 method2914 = this.method2914(1);
                if (method2914 == null) {
                    if (this.aBoolean1805) {
                        this.method2907(-2);
                    }
                    return;
                }
                if (method2914 != this.aClass98_Sub43_Sub4_1801) {
                    continue;
                }
                this.method2910((byte)109);
            }
            this.aClass98_Sub43_Sub3_1813.method1486(this.anOggPacket1802, 21000);
            if (n >= -83) {
                this.method2908(40);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.C(" + n + ')');
        }
    }
    
    public static void method2906(final int n) {
        try {
            Class237.anIntArray1809 = null;
            if (n != 10) {
                Class237.aBoolean1803 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.N(" + n + ')');
        }
    }
    
    private final void method2907(final int n) {
        try {
            for (Class98_Sub43 class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3998(97); class98_Sub43 != null; class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3995(-1)) {
                if (class98_Sub43 != this.aClass98_Sub43_Sub3_1813) {
                    while (class98_Sub43.anOggStreamState4241.packetOut() == 1) {
                        class98_Sub43.method1486(this.anOggPacket1802, n + 21002);
                    }
                }
            }
            if (n != -2) {
                method2898(-96, (byte)32, 48, 89, 40);
            }
            if (this.aClass98_Sub43_Sub3_1813 != null) {
                for (int i = 0; i < 10; ++i) {
                    if (!this.method2912((byte)(-59))) {
                        break;
                    }
                    if (~this.aClass98_Sub43_Sub3_1813.anOggStreamState4241.packetOut() != 0xFFFFFFFE) {
                        this.method2903(8);
                        break;
                    }
                    this.aClass98_Sub43_Sub3_1813.method1486(this.anOggPacket1802, 21000);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.J(" + n + ')');
        }
    }
    
    final Class98_Sub43_Sub3 method2908(final int n) {
        try {
            if (n != -32675) {
                this.anOggPage1808 = null;
            }
            return this.aClass98_Sub43_Sub3_1813;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.B(" + n + ')');
        }
    }
    
    final void method2909(final boolean b, final String aString1806) {
        try {
            if (!b) {
                this.aString1806 = null;
            }
            this.aString1806 = aString1806;
            if (this.aString1806 == null) {
                this.aClass98_Sub43_Sub4_1801 = null;
            }
            else {
                if (this.aClass98_Sub43_Sub4_1801 != null && !this.aString1806.equals(this.aClass98_Sub43_Sub4_1801.method1507(b))) {
                    this.aClass98_Sub43_Sub4_1801 = null;
                }
                if (this.aClass98_Sub43_Sub4_1801 == null) {
                    for (Class98_Sub43 class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3998(111); class98_Sub43 != null; class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3995(-1)) {
                        if (class98_Sub43 instanceof Class98_Sub43_Sub4) {
                            final Class98_Sub43_Sub4 aClass98_Sub43_Sub4_1801 = (Class98_Sub43_Sub4)class98_Sub43;
                            if (this.aString1806.equals(aClass98_Sub43_Sub4_1801.method1507(true))) {
                                this.aClass98_Sub43_Sub4_1801 = aClass98_Sub43_Sub4_1801;
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.A(" + b + ',' + ((aString1806 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method2910(final byte b) {
        try {
            for (Class98_Sub43 class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3998(101); class98_Sub43 != null; class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3995(-1)) {
                if (class98_Sub43 instanceof Class98_Sub43_Sub4) {
                    final Class98_Sub43_Sub4 class98_Sub43_Sub4 = (Class98_Sub43_Sub4)class98_Sub43;
                    while (class98_Sub43_Sub4.anInt4240 <= 8 || this.method2901((byte)(-112)) > class98_Sub43_Sub4.method1509(-6085)) {
                        if (~class98_Sub43_Sub4.anOggStreamState4241.packetOut(this.anOggPacket1802) != 0xFFFFFFFE) {
                            break;
                        }
                        class98_Sub43_Sub4.method1486(this.anOggPacket1802, 21000);
                    }
                }
            }
            if (b < 102) {
                this.aBoolean1815 = true;
            }
            this.method2909(true, this.aString1806);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.I(" + b + ')');
        }
    }
    
    final void method2911(final boolean b, final byte b2) {
        try {
            if (this.aClass98_Sub43_Sub1_1807 != null) {
                final Class98_Sub31_Sub4 method1488 = this.aClass98_Sub43_Sub1_1807.method1488((byte)88);
                if (method1488 != null) {
                    method1488.method1391(b, b2 ^ 0xFC7F699F);
                }
            }
            if (b2 != 22) {
                this.anOggSyncState1810 = null;
            }
            this.aBoolean1812 = !this.aBoolean1812;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.D(" + b + ',' + b2 + ')');
        }
    }
    
    private final boolean method2912(final byte b) {
        try {
            if (b != -59) {
                this.method2909(true, null);
            }
            if (this.aClass98_Sub43_Sub1_1807 == null) {
                final double n = this.aClass98_Sub43_Sub3_1813.method1496(-1);
                return n == 0.0 || Class343.method3819(-47) >= this.aClass98_Sub43_Sub3_1813.method1500(false) + 1000.0 / n;
            }
            return !this.aClass98_Sub43_Sub3_1813.method1498(1024) || this.method2901((byte)(-117)) > this.aClass98_Sub43_Sub3_1813.method1502(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.P(" + b + ')');
        }
    }
    
    final Class98_Sub43_Sub4 method2913(final int n) {
        try {
            if (n != 1) {
                this.method2902(false);
            }
            return this.aClass98_Sub43_Sub4_1801;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.K(" + n + ')');
        }
    }
    
    private final Class98_Sub43 method2914(final int n) throws IOException {
        try {
            if (n != 1) {
                this.method2908(110);
            }
            if (this.aBoolean1815) {
                throw new IllegalStateException();
            }
            if (this.aBoolean1805) {
                return null;
            }
            while (~this.anOggSyncState1810.pageOut(this.anOggPage1808) >= -1) {
                final int method2899 = this.method2899(n + 48, this.aByteArray1814);
                if (method2899 == -1) {
                    this.aBoolean1805 = true;
                    return null;
                }
                if (~method2899 == -1) {
                    return null;
                }
                if (!this.anOggSyncState1810.write(this.aByteArray1814, method2899)) {
                    throw new RuntimeException("");
                }
            }
            final int serialNumber = this.anOggPage1808.getSerialNumber();
            if (!this.anOggPage1808.isBOS()) {
                final Class98_Sub43 class98_Sub43 = (Class98_Sub43)this.aClass377_1811.method3990(serialNumber, -1);
                if (!class98_Sub43.anOggStreamState4241.pageIn(this.anOggPage1808)) {
                    throw new IllegalStateException();
                }
                return class98_Sub43;
            }
            else {
                final OggStreamState oggStreamState = new OggStreamState(serialNumber);
                if (!oggStreamState.pageIn(this.anOggPage1808)) {
                    throw new IllegalStateException();
                }
                if (~oggStreamState.packetPeek(this.anOggPacket1802) != 0xFFFFFFFE) {
                    throw new IllegalStateException();
                }
                Class98_Sub43 class98_Sub44;
                if (this.aClass98_Sub43_Sub3_1813 != null || !this.anOggPacket1802.isTheora()) {
                    if (this.aClass98_Sub43_Sub1_1807 != null || !this.anOggPacket1802.isVorbis()) {
                        final byte[] data = this.anOggPacket1802.getData();
                        final StringBuffer sb = new StringBuffer();
                        for (int n2 = 1; ~n2 > ~data.length && Character.isLetterOrDigit((char)data[n2]); ++n2) {
                            sb.append((char)data[n2]);
                        }
                        if (sb.toString().equals("kate")) {
                            class98_Sub44 = new Class98_Sub43_Sub4(oggStreamState);
                        }
                        else {
                            class98_Sub44 = new Class98_Sub43_Sub2(oggStreamState);
                        }
                    }
                    else {
                        this.aClass98_Sub43_Sub1_1807 = new Class98_Sub43_Sub1(oggStreamState);
                        class98_Sub44 = this.aClass98_Sub43_Sub1_1807;
                    }
                }
                else {
                    this.aClass98_Sub43_Sub3_1813 = new Class98_Sub43_Sub3(oggStreamState);
                    class98_Sub44 = this.aClass98_Sub43_Sub3_1813;
                }
                this.aClass377_1811.method3996(class98_Sub44, serialNumber, -1);
                return class98_Sub44;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.H(" + n + ')');
        }
    }
    
    Class237(final int n) {
        try {
            if (!Class134_Sub1.method2246("jagtheora", (byte)(-36))) {
                throw new RuntimeException("Failed to load jagtheora library");
            }
            this.aByteArray1814 = new byte[n];
            this.anOggSyncState1810 = new OggSyncState();
            this.anOggPage1808 = new OggPage();
            this.anOggPacket1802 = new OggPacket();
            this.aClass377_1811 = new Class377(8);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pb.<init>(" + n + ')');
        }
    }
    
    static {
        Class237.aBoolean1803 = false;
        Class237.anIntArray1809 = new int[3];
    }
}

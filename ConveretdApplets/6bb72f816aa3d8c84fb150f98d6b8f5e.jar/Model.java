// 
// Decompiled by Procyon v0.5.30
// 

public class Model extends Animable
{
    public static boolean[] newmodel;
    public static String ccString;
    public static String xxString;
    public static String vvString;
    public static String aString9_9;
    private int anInt1614;
    private boolean aBoolean1615;
    private int anInt1616;
    private int anInt1617;
    private boolean aBoolean1618;
    private static int anInt1619;
    public static int anInt1620;
    public static Model aModel_1621;
    private static int[] anIntArray1622;
    private static int[] anIntArray1623;
    private static int[] anIntArray1624;
    private static int[] anIntArray1625;
    public int anInt1626;
    public int[] anIntArray1627;
    public int[] anIntArray1628;
    public int[] anIntArray1629;
    public int anInt1630;
    public int[] anIntArray1631;
    public int[] anIntArray1632;
    public int[] anIntArray1633;
    public int[] anIntArray1634;
    public int[] anIntArray1635;
    public int[] anIntArray1636;
    public int[] anIntArray1637;
    public int[] anIntArray1638;
    public int[] anIntArray1639;
    public int[] anIntArray1640;
    public int anInt1641;
    public int anInt1642;
    public int[] anIntArray1643;
    public int[] anIntArray1644;
    public int[] anIntArray1645;
    public int anInt1646;
    public int anInt1647;
    public int anInt1648;
    public int anInt1649;
    public int anInt1650;
    public int anInt1651;
    public int anInt1652;
    public int anInt1653;
    public int anInt1654;
    public int[] anIntArray1655;
    public int[] anIntArray1656;
    public int[][] anIntArrayArray1657;
    public int[][] anIntArrayArray1658;
    public boolean aBoolean1659;
    Class33[] aClass33Array1660;
    static Class21[] aClass21Array1661;
    static OnDemandFetcherParent aOnDemandFetcherParent_1662;
    static boolean[] aBooleanArray1663;
    static boolean[] aBooleanArray1664;
    static int[] anIntArray1665;
    static int[] anIntArray1666;
    static int[] anIntArray1667;
    static int[] anIntArray1668;
    static int[] anIntArray1669;
    static int[] anIntArray1670;
    static int[] anIntArray1671;
    static int[][] anIntArrayArray1672;
    static int[] anIntArray1673;
    static int[][] anIntArrayArray1674;
    static int[] anIntArray1675;
    static int[] anIntArray1676;
    static int[] anIntArray1677;
    static int[] anIntArray1678;
    static int[] anIntArray1679;
    static int[] anIntArray1680;
    static int anInt1681;
    static int anInt1682;
    static int anInt1683;
    public static boolean aBoolean1684;
    public static int anInt1685;
    public static int anInt1686;
    public static int anInt1687;
    public static int[] anIntArray1688;
    public static int[] modelIntArray1;
    public static int[] modelIntArray2;
    static int[] modelIntArray3;
    static int[] modelIntArray4;
    
    public static void nullLoader() {
        Model.aClass21Array1661 = null;
        Model.aBooleanArray1663 = null;
        Model.aBooleanArray1664 = null;
        Model.anIntArray1666 = null;
        Model.anIntArray1667 = null;
        Model.anIntArray1668 = null;
        Model.anIntArray1669 = null;
        Model.anIntArray1670 = null;
        Model.anIntArray1671 = null;
        Model.anIntArrayArray1672 = null;
        Model.anIntArray1673 = null;
        Model.anIntArrayArray1674 = null;
        Model.anIntArray1675 = null;
        Model.anIntArray1676 = null;
        Model.anIntArray1677 = null;
        Model.modelIntArray1 = null;
        Model.modelIntArray2 = null;
        Model.modelIntArray3 = null;
        Model.modelIntArray4 = null;
    }
    
    public void read525Model(final byte[] aByteArray368, final int n) {
        final Stream stream = new Stream(aByteArray368);
        final Stream stream2 = new Stream(aByteArray368);
        final Stream stream3 = new Stream(aByteArray368);
        final Stream stream4 = new Stream(aByteArray368);
        final Stream stream5 = new Stream(aByteArray368);
        final Stream stream6 = new Stream(aByteArray368);
        final Stream stream7 = new Stream(aByteArray368);
        stream.currentOffset = aByteArray368.length - 23;
        final int unsignedWord = stream.readUnsignedWord();
        final int unsignedWord2 = stream.readUnsignedWord();
        final int unsignedByte = stream.readUnsignedByte();
        final Class21[] aClass21Array1661 = Model.aClass21Array1661;
        final Class21 class21 = new Class21();
        aClass21Array1661[n] = class21;
        final Class21 class22 = class21;
        class22.aByteArray368 = aByteArray368;
        class22.anInt369 = unsignedWord;
        class22.anInt370 = unsignedWord2;
        class22.anInt371 = unsignedByte;
        final int unsignedByte2 = stream.readUnsignedByte();
        final boolean b = ~(0x1 & unsignedByte2) == 0xFFFFFFFE;
        final boolean b2 = ~(unsignedByte2 & 0x2) == 0xFFFFFFFD;
        final int unsignedByte3 = stream.readUnsignedByte();
        final int unsignedByte4 = stream.readUnsignedByte();
        final int unsignedByte5 = stream.readUnsignedByte();
        final int unsignedByte6 = stream.readUnsignedByte();
        final int unsignedByte7 = stream.readUnsignedByte();
        final int unsignedWord3 = stream.readUnsignedWord();
        final int unsignedWord4 = stream.readUnsignedWord();
        final int unsignedWord5 = stream.readUnsignedWord();
        final int unsignedWord6 = stream.readUnsignedWord();
        final int unsignedWord7 = stream.readUnsignedWord();
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        byte[] array = null;
        byte[] array2 = null;
        byte[] array3 = null;
        byte[] array4 = null;
        byte[] array5 = null;
        byte[] array6 = null;
        byte[] array7 = null;
        int[] array8 = null;
        int[] array9 = null;
        int[] array10 = null;
        short[] array11 = null;
        final int[] array12 = new int[unsignedWord2];
        if (unsignedByte > 0) {
            array2 = new byte[unsignedByte];
            stream.currentOffset = 0;
            for (int i = 0; i < unsignedByte; ++i) {
                final byte[] array13 = array2;
                final int n5 = i;
                final byte signedByte = stream.readSignedByte();
                array13[n5] = signedByte;
                final byte b3 = signedByte;
                if (b3 == 0) {
                    ++n2;
                }
                if (b3 >= 1 && b3 <= 3) {
                    ++n3;
                }
                if (b3 == 2) {
                    ++n4;
                }
            }
        }
        final int currentOffset2;
        final int currentOffset;
        int n6 = currentOffset = (currentOffset2 = unsignedByte) + unsignedWord;
        if (unsignedByte2 != 0) {
            n6 += unsignedWord2;
        }
        final int currentOffset3 = n6;
        final int currentOffset4;
        int n7 = currentOffset4 = n6 + unsignedWord2;
        if (unsignedByte3 == 255) {
            n7 += unsignedWord2;
        }
        final int currentOffset5 = n7;
        if (unsignedByte5 == 1) {
            n7 += unsignedWord2;
        }
        final int currentOffset6 = n7;
        if (unsignedByte7 == 1) {
            n7 += unsignedWord;
        }
        final int currentOffset7 = n7;
        if (unsignedByte4 == 1) {
            n7 += unsignedWord2;
        }
        final int currentOffset8 = n7;
        final int currentOffset9;
        int n8 = currentOffset9 = n7 + unsignedWord6;
        if (unsignedByte6 == 1) {
            n8 += unsignedWord2 * 2;
        }
        final int currentOffset10 = n8;
        final int currentOffset20;
        final int currentOffset19;
        final int currentOffset18;
        final int currentOffset17;
        final int currentOffset16;
        final int currentOffset15;
        final int currentOffset14;
        final int currentOffset13;
        final int currentOffset12;
        final int currentOffset11;
        final int n9 = (currentOffset11 = (currentOffset12 = (currentOffset13 = (currentOffset14 = (currentOffset15 = (currentOffset16 = (currentOffset17 = (currentOffset18 = (currentOffset19 = (currentOffset20 = n8 + unsignedWord7) + unsignedWord2 * 2) + unsignedWord3) + unsignedWord4) + unsignedWord5) + n2 * 6) + n3 * 6) + n3 * 6) + n3) + n3) + (n3 + n4 * 2);
        final int[] anIntArray1627 = new int[unsignedWord];
        final int[] anIntArray1628 = new int[unsignedWord];
        final int[] anIntArray1629 = new int[unsignedWord];
        final int[] anIntArray1630 = new int[unsignedWord2];
        final int[] anIntArray1631 = new int[unsignedWord2];
        final int[] anIntArray1632 = new int[unsignedWord2];
        this.anIntArray1655 = new int[unsignedWord];
        this.anIntArray1637 = new int[unsignedWord2];
        this.anIntArray1638 = new int[unsignedWord2];
        this.anIntArray1639 = new int[unsignedWord2];
        this.anIntArray1656 = new int[unsignedWord2];
        if (unsignedByte7 == 1) {
            this.anIntArray1655 = new int[unsignedWord];
        }
        if (b) {
            this.anIntArray1637 = new int[unsignedWord2];
        }
        if (unsignedByte3 == 255) {
            this.anIntArray1638 = new int[unsignedWord2];
        }
        else {
            final byte b4 = (byte)unsignedByte3;
        }
        if (unsignedByte4 == 1) {
            this.anIntArray1639 = new int[unsignedWord2];
        }
        if (unsignedByte5 == 1) {
            this.anIntArray1656 = new int[unsignedWord2];
        }
        if (unsignedByte6 == 1) {
            array11 = new short[unsignedWord2];
        }
        if (unsignedByte6 == 1 && unsignedByte > 0) {
            array = new byte[unsignedWord2];
        }
        final int[] anIntArray1633 = new int[unsignedWord2];
        int[] array14 = null;
        int[] array15 = null;
        int[] array16 = null;
        if (unsignedByte > 0) {
            array14 = new int[unsignedByte];
            array15 = new int[unsignedByte];
            array16 = new int[unsignedByte];
            if (n3 > 0) {
                array8 = new int[n3];
                array10 = new int[n3];
                array9 = new int[n3];
                array6 = new byte[n3];
                array7 = new byte[n3];
                array4 = new byte[n3];
            }
            if (n4 > 0) {
                array5 = new byte[n4];
                array3 = new byte[n4];
            }
        }
        stream.currentOffset = currentOffset2;
        stream2.currentOffset = currentOffset19;
        stream3.currentOffset = currentOffset18;
        stream4.currentOffset = currentOffset17;
        stream5.currentOffset = currentOffset6;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        for (int j = 0; j < unsignedWord; ++j) {
            final int unsignedByte8 = stream.readUnsignedByte();
            int method421 = 0;
            if ((unsignedByte8 & 0x1) != 0x0) {
                method421 = stream2.method421();
            }
            int method422 = 0;
            if ((unsignedByte8 & 0x2) != 0x0) {
                method422 = stream3.method421();
            }
            int method423 = 0;
            if ((unsignedByte8 & 0x4) != 0x0) {
                method423 = stream4.method421();
            }
            anIntArray1627[j] = n10 + method421;
            anIntArray1628[j] = n11 + method422;
            anIntArray1629[j] = n12 + method423;
            n10 = anIntArray1627[j];
            n11 = anIntArray1628[j];
            n12 = anIntArray1629[j];
            if (this.anIntArray1655 != null) {
                this.anIntArray1655[j] = stream5.readUnsignedByte();
            }
        }
        stream.currentOffset = currentOffset20;
        stream2.currentOffset = currentOffset;
        stream3.currentOffset = currentOffset4;
        stream4.currentOffset = currentOffset7;
        stream5.currentOffset = currentOffset5;
        stream6.currentOffset = currentOffset9;
        stream7.currentOffset = currentOffset10;
        for (int k = 0; k < unsignedWord2; ++k) {
            anIntArray1633[k] = stream.readUnsignedWord();
            if (unsignedByte2 != 0) {
                this.anIntArray1637[k] = stream2.readSignedByte();
                if (this.anIntArray1637[k] == 2) {
                    anIntArray1633[k] = 65535;
                }
                this.anIntArray1637[k] = 0;
            }
            if (unsignedByte3 == 255) {
                this.anIntArray1638[k] = stream3.readSignedByte();
            }
            if (unsignedByte4 == 1) {
                this.anIntArray1639[k] = stream4.readSignedByte();
                if (this.anIntArray1639[k] < 0) {
                    this.anIntArray1639[k] += 256;
                }
            }
            if (unsignedByte5 == 1) {
                this.anIntArray1656[k] = stream5.readUnsignedByte();
            }
            if (unsignedByte6 == 1) {
                array11[k] = (short)(stream6.readUnsignedWord() - 1);
            }
            if (array != null) {
                if (array11[k] != -1) {
                    array[k] = (byte)(stream7.readUnsignedByte() - 1);
                }
                else {
                    array[k] = -1;
                }
            }
        }
        stream.currentOffset = currentOffset8;
        stream2.currentOffset = currentOffset3;
        int n13 = 0;
        int n14 = 0;
        int n15 = 0;
        int n16 = 0;
        for (int l = 0; l < unsignedWord2; ++l) {
            final int unsignedByte9 = stream2.readUnsignedByte();
            if (unsignedByte9 == 1) {
                n13 = stream.method421() + n16;
                n14 = stream.method421() + n13;
                n15 = (n16 = stream.method421() + n14);
                anIntArray1630[l] = n13;
                anIntArray1631[l] = n14;
                anIntArray1632[l] = n15;
            }
            if (unsignedByte9 == 2) {
                n14 = n15;
                n15 = (n16 += stream.method421());
                anIntArray1630[l] = n13;
                anIntArray1631[l] = n14;
                anIntArray1632[l] = n15;
            }
            if (unsignedByte9 == 3) {
                n13 = n15;
                n15 = (n16 += stream.method421());
                anIntArray1630[l] = n13;
                anIntArray1631[l] = n14;
                anIntArray1632[l] = n15;
            }
            if (unsignedByte9 == 4) {
                final int n17 = n13;
                n13 = n14;
                n14 = n17;
                n15 = (n16 += stream.method421());
                anIntArray1630[l] = n13;
                anIntArray1631[l] = n14;
                anIntArray1632[l] = n15;
            }
        }
        stream.currentOffset = currentOffset16;
        stream2.currentOffset = currentOffset15;
        stream3.currentOffset = currentOffset14;
        stream4.currentOffset = currentOffset13;
        stream5.currentOffset = currentOffset12;
        stream6.currentOffset = currentOffset11;
        for (int n18 = 0; n18 < unsignedByte; ++n18) {
            final int n19 = array2[n18] & 0xFF;
            if (n19 == 0) {
                array14[n18] = stream.readUnsignedWord();
                array15[n18] = stream.readUnsignedWord();
                array16[n18] = stream.readUnsignedWord();
            }
            if (n19 == 1) {
                array14[n18] = stream2.readUnsignedWord();
                array15[n18] = stream2.readUnsignedWord();
                array16[n18] = stream2.readUnsignedWord();
                array8[n18] = stream3.readUnsignedWord();
                array10[n18] = stream3.readUnsignedWord();
                array9[n18] = stream3.readUnsignedWord();
                array6[n18] = stream4.readSignedByte();
                array7[n18] = stream5.readSignedByte();
                array4[n18] = stream6.readSignedByte();
            }
            if (n19 == 2) {
                array14[n18] = stream2.readUnsignedWord();
                array15[n18] = stream2.readUnsignedWord();
                array16[n18] = stream2.readUnsignedWord();
                array8[n18] = stream3.readUnsignedWord();
                array10[n18] = stream3.readUnsignedWord();
                array9[n18] = stream3.readUnsignedWord();
                array6[n18] = stream4.readSignedByte();
                array7[n18] = stream5.readSignedByte();
                array4[n18] = stream6.readSignedByte();
                array5[n18] = stream6.readSignedByte();
                array3[n18] = stream6.readSignedByte();
            }
            if (n19 == 3) {
                array14[n18] = stream2.readUnsignedWord();
                array15[n18] = stream2.readUnsignedWord();
                array16[n18] = stream2.readUnsignedWord();
                array8[n18] = stream3.readUnsignedWord();
                array10[n18] = stream3.readUnsignedWord();
                array9[n18] = stream3.readUnsignedWord();
                array6[n18] = stream4.readSignedByte();
                array7[n18] = stream5.readSignedByte();
                array4[n18] = stream6.readSignedByte();
            }
        }
        if (unsignedByte3 != 255) {
            for (int n20 = 0; n20 < unsignedWord2; ++n20) {
                this.anIntArray1638[n20] = unsignedByte3;
            }
        }
        this.anIntArray1640 = anIntArray1633;
        this.anInt1626 = unsignedWord;
        this.anInt1630 = unsignedWord2;
        this.anIntArray1627 = anIntArray1627;
        this.anIntArray1628 = anIntArray1628;
        this.anIntArray1629 = anIntArray1629;
        this.anIntArray1631 = anIntArray1630;
        this.anIntArray1632 = anIntArray1631;
        this.anIntArray1633 = anIntArray1632;
    }
    
    public Model(final int n) {
        if (this.anIntArray1638 != null) {
            for (int i = 0; i < this.anIntArray1638.length; ++i) {
                this.anIntArray1638[i] = 10;
            }
        }
        final byte[] aByteArray368 = Model.aClass21Array1661[n].aByteArray368;
        if (aByteArray368[aByteArray368.length - 1] == -1 && aByteArray368[aByteArray368.length - 2] == -1) {
            this.read622Model(aByteArray368, n);
        }
        else {
            this.readOldModel(n);
        }
        if (Model.newmodel[n]) {
            this.scale2(4);
        }
    }
    
    public void scale2(final int n) {
        for (int i = 0; i < this.anInt1626; ++i) {
            this.anIntArray1627[i] /= n;
            this.anIntArray1628[i] /= n;
            this.anIntArray1629[i] /= n;
        }
    }
    
    public void read622Model(final byte[] aByteArray368, final int n) {
        final Stream stream = new Stream(aByteArray368);
        final Stream stream2 = new Stream(aByteArray368);
        final Stream stream3 = new Stream(aByteArray368);
        final Stream stream4 = new Stream(aByteArray368);
        final Stream stream5 = new Stream(aByteArray368);
        final Stream stream6 = new Stream(aByteArray368);
        final Stream stream7 = new Stream(aByteArray368);
        stream.currentOffset = aByteArray368.length - 23;
        final int unsignedWord = stream.readUnsignedWord();
        final int unsignedWord2 = stream.readUnsignedWord();
        final int unsignedByte = stream.readUnsignedByte();
        final Class21[] aClass21Array1661 = Model.aClass21Array1661;
        final Class21 class21 = new Class21();
        aClass21Array1661[n] = class21;
        final Class21 class22 = class21;
        class22.aByteArray368 = aByteArray368;
        class22.anInt369 = unsignedWord;
        class22.anInt370 = unsignedWord2;
        class22.anInt371 = unsignedByte;
        final int unsignedByte2 = stream.readUnsignedByte();
        final boolean b = ~(0x1 & unsignedByte2) == 0xFFFFFFFE;
        final boolean b2 = ~(unsignedByte2 & 0x2) == 0xFFFFFFFD;
        final boolean b3 = (0x4 & unsignedByte2) == 0x4;
        final boolean b4 = (0x8 & unsignedByte2) == 0x8;
        if (!b4) {
            this.read525Model(aByteArray368, n);
            return;
        }
        int unsignedByte3 = 0;
        if (b4) {
            final Stream stream8 = stream;
            stream8.currentOffset -= 7;
            unsignedByte3 = stream.readUnsignedByte();
            final Stream stream9 = stream;
            stream9.currentOffset += 6;
        }
        if (unsignedByte3 == 15) {
            Model.newmodel[n] = true;
        }
        final int unsignedByte4 = stream.readUnsignedByte();
        final int unsignedByte5 = stream.readUnsignedByte();
        final int unsignedByte6 = stream.readUnsignedByte();
        final int unsignedByte7 = stream.readUnsignedByte();
        final int unsignedByte8 = stream.readUnsignedByte();
        final int unsignedWord3 = stream.readUnsignedWord();
        final int unsignedWord4 = stream.readUnsignedWord();
        final int unsignedWord5 = stream.readUnsignedWord();
        final int unsignedWord6 = stream.readUnsignedWord();
        final int unsignedWord7 = stream.readUnsignedWord();
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        byte[] array = null;
        byte[] array2 = null;
        byte[] array3 = null;
        byte[] array4 = null;
        byte[] array5 = null;
        byte[] array6 = null;
        byte[] array7 = null;
        int[] array8 = null;
        int[] array9 = null;
        int[] array10 = null;
        short[] array11 = null;
        final int[] array12 = new int[unsignedWord2];
        if (unsignedByte > 0) {
            array2 = new byte[unsignedByte];
            stream.currentOffset = 0;
            for (int i = 0; i < unsignedByte; ++i) {
                final byte[] array13 = array2;
                final int n5 = i;
                final byte signedByte = stream.readSignedByte();
                array13[n5] = signedByte;
                final byte b5 = signedByte;
                if (b5 == 0) {
                    ++n2;
                }
                if (b5 >= 1 && b5 <= 3) {
                    ++n3;
                }
                if (b5 == 2) {
                    ++n4;
                }
            }
        }
        final int currentOffset2;
        final int currentOffset;
        int n6 = currentOffset = (currentOffset2 = unsignedByte) + unsignedWord;
        if (b) {
            n6 += unsignedWord2;
        }
        if (unsignedByte2 != 0) {
            n6 += unsignedWord2;
        }
        final int currentOffset3 = n6;
        final int currentOffset4;
        int n7 = currentOffset4 = n6 + unsignedWord2;
        if (unsignedByte4 == 255) {
            n7 += unsignedWord2;
        }
        final int currentOffset5 = n7;
        if (unsignedByte6 == 1) {
            n7 += unsignedWord2;
        }
        final int currentOffset6 = n7;
        if (unsignedByte8 == 1) {
            n7 += unsignedWord;
        }
        final int currentOffset7 = n7;
        if (unsignedByte5 == 1) {
            n7 += unsignedWord2;
        }
        final int currentOffset8 = n7;
        final int currentOffset9;
        int n8 = currentOffset9 = n7 + unsignedWord6;
        if (unsignedByte7 == 1) {
            n8 += unsignedWord2 * 2;
        }
        final int currentOffset10 = n8;
        final int currentOffset16;
        final int currentOffset15;
        final int currentOffset14;
        final int currentOffset13;
        final int currentOffset12;
        final int currentOffset11;
        final int n9 = (currentOffset11 = (currentOffset12 = (currentOffset13 = (currentOffset14 = (currentOffset15 = (currentOffset16 = n8 + unsignedWord7) + unsignedWord2 * 2) + unsignedWord3) + unsignedWord4) + unsignedWord5) + n2 * 6) + n3 * 6;
        int n10 = 6;
        if (unsignedByte3 != 14) {
            if (unsignedByte3 >= 15) {
                n10 = 9;
            }
        }
        else {
            n10 = 7;
        }
        final int currentOffset17 = n9;
        final int currentOffset20;
        final int currentOffset19;
        final int currentOffset18;
        final int n11 = (currentOffset18 = (currentOffset19 = (currentOffset20 = n9 + n10 * n3) + n3) + n3) + (n3 + n4 * 2);
        final int[] anIntArray1627 = new int[unsignedWord];
        final int[] anIntArray1628 = new int[unsignedWord];
        final int[] anIntArray1629 = new int[unsignedWord];
        final int[] anIntArray1630 = new int[unsignedWord2];
        final int[] anIntArray1631 = new int[unsignedWord2];
        final int[] anIntArray1632 = new int[unsignedWord2];
        this.anIntArray1655 = new int[unsignedWord];
        this.anIntArray1637 = new int[unsignedWord2];
        this.anIntArray1638 = new int[unsignedWord2];
        this.anIntArray1639 = new int[unsignedWord2];
        this.anIntArray1656 = new int[unsignedWord2];
        if (unsignedByte8 == 1) {
            this.anIntArray1655 = new int[unsignedWord];
        }
        if (b) {
            this.anIntArray1637 = new int[unsignedWord2];
        }
        if (unsignedByte4 == 255) {
            this.anIntArray1638 = new int[unsignedWord2];
        }
        else {
            final byte b6 = (byte)unsignedByte4;
        }
        if (unsignedByte5 == 1) {
            this.anIntArray1639 = new int[unsignedWord2];
        }
        if (unsignedByte6 == 1) {
            this.anIntArray1656 = new int[unsignedWord2];
        }
        if (unsignedByte7 == 1) {
            array11 = new short[unsignedWord2];
        }
        if (unsignedByte7 == 1 && unsignedByte > 0) {
            array = new byte[unsignedWord2];
        }
        final int[] anIntArray1633 = new int[unsignedWord2];
        int[] array14 = null;
        int[] array15 = null;
        int[] array16 = null;
        if (unsignedByte > 0) {
            array14 = new int[unsignedByte];
            array15 = new int[unsignedByte];
            array16 = new int[unsignedByte];
            if (n3 > 0) {
                array8 = new int[n3];
                array10 = new int[n3];
                array9 = new int[n3];
                array6 = new byte[n3];
                array7 = new byte[n3];
                array4 = new byte[n3];
            }
            if (n4 > 0) {
                array5 = new byte[n4];
                array3 = new byte[n4];
            }
        }
        stream.currentOffset = currentOffset2;
        stream2.currentOffset = currentOffset15;
        stream3.currentOffset = currentOffset14;
        stream4.currentOffset = currentOffset13;
        stream5.currentOffset = currentOffset6;
        int n12 = 0;
        int n13 = 0;
        int n14 = 0;
        for (int j = 0; j < unsignedWord; ++j) {
            final int unsignedByte9 = stream.readUnsignedByte();
            int method421 = 0;
            if ((unsignedByte9 & 0x1) != 0x0) {
                method421 = stream2.method421();
            }
            int method422 = 0;
            if ((unsignedByte9 & 0x2) != 0x0) {
                method422 = stream3.method421();
            }
            int method423 = 0;
            if ((unsignedByte9 & 0x4) != 0x0) {
                method423 = stream4.method421();
            }
            anIntArray1627[j] = n12 + method421;
            anIntArray1628[j] = n13 + method422;
            anIntArray1629[j] = n14 + method423;
            n12 = anIntArray1627[j];
            n13 = anIntArray1628[j];
            n14 = anIntArray1629[j];
            if (this.anIntArray1655 != null) {
                this.anIntArray1655[j] = stream5.readUnsignedByte();
            }
        }
        stream.currentOffset = currentOffset16;
        stream2.currentOffset = currentOffset;
        stream3.currentOffset = currentOffset4;
        stream4.currentOffset = currentOffset7;
        stream5.currentOffset = currentOffset5;
        stream6.currentOffset = currentOffset9;
        stream7.currentOffset = currentOffset10;
        for (int k = 0; k < unsignedWord2; ++k) {
            anIntArray1633[k] = stream.readUnsignedWord();
            if (unsignedByte2 != 0) {
                this.anIntArray1637[k] = stream2.readSignedByte();
                if (this.anIntArray1637[k] == 2) {
                    anIntArray1633[k] = 65535;
                }
                this.anIntArray1637[k] = 0;
            }
            if (unsignedByte4 == 255) {
                this.anIntArray1638[k] = stream3.readSignedByte();
            }
            if (unsignedByte5 == 1) {
                this.anIntArray1639[k] = stream4.readSignedByte();
                if (this.anIntArray1639[k] < 0) {
                    this.anIntArray1639[k] += 256;
                }
            }
            if (unsignedByte6 == 1) {
                this.anIntArray1656[k] = stream5.readUnsignedByte();
            }
            if (unsignedByte7 == 1) {
                array11[k] = (short)(stream6.readUnsignedWord() - 1);
            }
            if (array != null) {
                if (array11[k] != -1) {
                    array[k] = (byte)(stream7.readUnsignedByte() - 1);
                }
                else {
                    array[k] = -1;
                }
            }
        }
        stream.currentOffset = currentOffset8;
        stream2.currentOffset = currentOffset3;
        int n15 = 0;
        int n16 = 0;
        int n17 = 0;
        int n18 = 0;
        for (int l = 0; l < unsignedWord2; ++l) {
            final int unsignedByte10 = stream2.readUnsignedByte();
            if (unsignedByte10 == 1) {
                n15 = stream.method421() + n18;
                n16 = stream.method421() + n15;
                n17 = (n18 = stream.method421() + n16);
                anIntArray1630[l] = n15;
                anIntArray1631[l] = n16;
                anIntArray1632[l] = n17;
            }
            if (unsignedByte10 == 2) {
                n16 = n17;
                n17 = (n18 += stream.method421());
                anIntArray1630[l] = n15;
                anIntArray1631[l] = n16;
                anIntArray1632[l] = n17;
            }
            if (unsignedByte10 == 3) {
                n15 = n17;
                n17 = (n18 += stream.method421());
                anIntArray1630[l] = n15;
                anIntArray1631[l] = n16;
                anIntArray1632[l] = n17;
            }
            if (unsignedByte10 == 4) {
                final int n19 = n15;
                n15 = n16;
                n16 = n19;
                n17 = (n18 += stream.method421());
                anIntArray1630[l] = n15;
                anIntArray1631[l] = n16;
                anIntArray1632[l] = n17;
            }
        }
        stream.currentOffset = currentOffset12;
        stream2.currentOffset = currentOffset11;
        stream3.currentOffset = currentOffset17;
        stream4.currentOffset = currentOffset20;
        stream5.currentOffset = currentOffset19;
        stream6.currentOffset = currentOffset18;
        for (int n20 = 0; n20 < unsignedByte; ++n20) {
            final int n21 = array2[n20] & 0xFF;
            if (n21 == 0) {
                array14[n20] = stream.readUnsignedWord();
                array15[n20] = stream.readUnsignedWord();
                array16[n20] = stream.readUnsignedWord();
            }
            if (n21 == 1) {
                array14[n20] = stream2.readUnsignedWord();
                array15[n20] = stream2.readUnsignedWord();
                array16[n20] = stream2.readUnsignedWord();
                if (unsignedByte3 < 15) {
                    array8[n20] = stream3.readUnsignedWord();
                    if (unsignedByte3 >= 14) {
                        array10[n20] = stream3.v(-1);
                    }
                    else {
                        array10[n20] = stream3.readUnsignedWord();
                    }
                    array9[n20] = stream3.readUnsignedWord();
                }
                else {
                    array8[n20] = stream3.v(-1);
                    array10[n20] = stream3.v(-1);
                    array9[n20] = stream3.v(-1);
                }
                array6[n20] = stream4.readSignedByte();
                array7[n20] = stream5.readSignedByte();
                array4[n20] = stream6.readSignedByte();
            }
            if (n21 == 2) {
                array14[n20] = stream2.readUnsignedWord();
                array15[n20] = stream2.readUnsignedWord();
                array16[n20] = stream2.readUnsignedWord();
                if (unsignedByte3 >= 15) {
                    array8[n20] = stream3.v(-1);
                    array10[n20] = stream3.v(-1);
                    array9[n20] = stream3.v(-1);
                }
                else {
                    array8[n20] = stream3.readUnsignedWord();
                    if (unsignedByte3 < 14) {
                        array10[n20] = stream3.readUnsignedWord();
                    }
                    else {
                        array10[n20] = stream3.v(-1);
                    }
                    array9[n20] = stream3.readUnsignedWord();
                }
                array6[n20] = stream4.readSignedByte();
                array7[n20] = stream5.readSignedByte();
                array4[n20] = stream6.readSignedByte();
                array5[n20] = stream6.readSignedByte();
                array3[n20] = stream6.readSignedByte();
            }
            if (n21 == 3) {
                array14[n20] = stream2.readUnsignedWord();
                array15[n20] = stream2.readUnsignedWord();
                array16[n20] = stream2.readUnsignedWord();
                if (unsignedByte3 < 15) {
                    array8[n20] = stream3.readUnsignedWord();
                    if (unsignedByte3 < 14) {
                        array10[n20] = stream3.readUnsignedWord();
                    }
                    else {
                        array10[n20] = stream3.v(-1);
                    }
                    array9[n20] = stream3.readUnsignedWord();
                }
                else {
                    array8[n20] = stream3.v(-1);
                    array10[n20] = stream3.v(-1);
                    array9[n20] = stream3.v(-1);
                }
                array6[n20] = stream4.readSignedByte();
                array7[n20] = stream5.readSignedByte();
                array4[n20] = stream6.readSignedByte();
            }
        }
        if (unsignedByte4 != 255) {
            for (int n22 = 0; n22 < unsignedWord2; ++n22) {
                this.anIntArray1638[n22] = unsignedByte4;
            }
        }
        this.anIntArray1640 = anIntArray1633;
        this.anInt1626 = unsignedWord;
        this.anInt1630 = unsignedWord2;
        this.anIntArray1627 = anIntArray1627;
        this.anIntArray1628 = anIntArray1628;
        this.anIntArray1629 = anIntArray1629;
        this.anIntArray1631 = anIntArray1630;
        this.anIntArray1632 = anIntArray1631;
        this.anIntArray1633 = anIntArray1632;
    }
    
    private void readOldModel(final int n) {
        final int i = -870;
        this.anInt1614 = 9;
        this.aBoolean1615 = false;
        this.anInt1616 = 360;
        this.anInt1617 = 1;
        this.aBoolean1618 = true;
        this.aBoolean1659 = false;
        ++Model.anInt1620;
        final Class21 class21 = Model.aClass21Array1661[n];
        this.anInt1626 = class21.anInt369;
        this.anInt1630 = class21.anInt370;
        this.anInt1642 = class21.anInt371;
        this.anIntArray1627 = new int[this.anInt1626];
        this.anIntArray1628 = new int[this.anInt1626];
        this.anIntArray1629 = new int[this.anInt1626];
        this.anIntArray1631 = new int[this.anInt1630];
        this.anIntArray1632 = new int[this.anInt1630];
        while (i >= 0) {
            this.aBoolean1618 = !this.aBoolean1618;
        }
        this.anIntArray1633 = new int[this.anInt1630];
        this.anIntArray1643 = new int[this.anInt1642];
        this.anIntArray1644 = new int[this.anInt1642];
        this.anIntArray1645 = new int[this.anInt1642];
        if (class21.anInt376 >= 0) {
            this.anIntArray1655 = new int[this.anInt1626];
        }
        if (class21.anInt380 >= 0) {
            this.anIntArray1637 = new int[this.anInt1630];
        }
        if (class21.anInt381 >= 0) {
            this.anIntArray1638 = new int[this.anInt1630];
        }
        else {
            this.anInt1641 = -class21.anInt381 - 1;
        }
        if (class21.anInt382 >= 0) {
            this.anIntArray1639 = new int[this.anInt1630];
        }
        if (class21.anInt383 >= 0) {
            this.anIntArray1656 = new int[this.anInt1630];
        }
        this.anIntArray1640 = new int[this.anInt1630];
        final Stream stream = new Stream(class21.aByteArray368);
        stream.currentOffset = class21.anInt372;
        final Stream stream2 = new Stream(class21.aByteArray368);
        stream2.currentOffset = class21.anInt373;
        final Stream stream3 = new Stream(class21.aByteArray368);
        stream3.currentOffset = class21.anInt374;
        final Stream stream4 = new Stream(class21.aByteArray368);
        stream4.currentOffset = class21.anInt375;
        final Stream stream5 = new Stream(class21.aByteArray368);
        stream5.currentOffset = class21.anInt376;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int j = 0; j < this.anInt1626; ++j) {
            final int unsignedByte = stream.readUnsignedByte();
            int method421 = 0;
            if ((unsignedByte & 0x1) != 0x0) {
                method421 = stream2.method421();
            }
            int method422 = 0;
            if ((unsignedByte & 0x2) != 0x0) {
                method422 = stream3.method421();
            }
            int method423 = 0;
            if ((unsignedByte & 0x4) != 0x0) {
                method423 = stream4.method421();
            }
            this.anIntArray1627[j] = n2 + method421;
            this.anIntArray1628[j] = n3 + method422;
            this.anIntArray1629[j] = n4 + method423;
            n2 = this.anIntArray1627[j];
            n3 = this.anIntArray1628[j];
            n4 = this.anIntArray1629[j];
            if (this.anIntArray1655 != null) {
                this.anIntArray1655[j] = stream5.readUnsignedByte();
            }
        }
        stream.currentOffset = class21.anInt379;
        stream2.currentOffset = class21.anInt380;
        stream3.currentOffset = class21.anInt381;
        stream4.currentOffset = class21.anInt382;
        stream5.currentOffset = class21.anInt383;
        for (int k = 0; k < this.anInt1630; ++k) {
            this.anIntArray1640[k] = stream.readUnsignedWord();
            if (this.anIntArray1637 != null) {
                this.anIntArray1637[k] = stream2.readUnsignedByte();
            }
            if (this.anIntArray1638 != null) {
                this.anIntArray1638[k] = stream3.readUnsignedByte();
            }
            if (this.anIntArray1639 != null) {
                this.anIntArray1639[k] = stream4.readUnsignedByte();
            }
            if (this.anIntArray1656 != null) {
                this.anIntArray1656[k] = stream5.readUnsignedByte();
            }
        }
        stream.currentOffset = class21.anInt377;
        stream2.currentOffset = class21.anInt378;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        for (int l = 0; l < this.anInt1630; ++l) {
            final int unsignedByte2 = stream2.readUnsignedByte();
            if (unsignedByte2 == 1) {
                n5 = stream.method421() + n8;
                n6 = stream.method421() + n5;
                n7 = (n8 = stream.method421() + n6);
                this.anIntArray1631[l] = n5;
                this.anIntArray1632[l] = n6;
                this.anIntArray1633[l] = n7;
            }
            if (unsignedByte2 == 2) {
                n5 = n5;
                n6 = n7;
                n7 = (n8 += stream.method421());
                this.anIntArray1631[l] = n5;
                this.anIntArray1632[l] = n6;
                this.anIntArray1633[l] = n7;
            }
            if (unsignedByte2 == 3) {
                n5 = n7;
                n6 = n6;
                n7 = (n8 += stream.method421());
                this.anIntArray1631[l] = n5;
                this.anIntArray1632[l] = n6;
                this.anIntArray1633[l] = n7;
            }
            if (unsignedByte2 == 4) {
                final int n9 = n5;
                n5 = n6;
                n6 = n9;
                n7 = (n8 += stream.method421());
                this.anIntArray1631[l] = n5;
                this.anIntArray1632[l] = n6;
                this.anIntArray1633[l] = n7;
            }
        }
        stream.currentOffset = class21.anInt384;
        for (int n10 = 0; n10 < this.anInt1642; ++n10) {
            this.anIntArray1643[n10] = stream.readUnsignedWord();
            this.anIntArray1644[n10] = stream.readUnsignedWord();
            this.anIntArray1645[n10] = stream.readUnsignedWord();
        }
    }
    
    public static void method460(final byte[] aByteArray368, final int n) {
        try {
            if (aByteArray368 == null) {
                final Class21[] aClass21Array1661 = Model.aClass21Array1661;
                final Class21 class21 = new Class21();
                aClass21Array1661[n] = class21;
                final Class21 class22 = class21;
                class22.anInt369 = 0;
                class22.anInt370 = 0;
                class22.anInt371 = 0;
                return;
            }
            final Stream stream = new Stream(aByteArray368);
            stream.currentOffset = aByteArray368.length - 18;
            final Class21[] aClass21Array1662 = Model.aClass21Array1661;
            final Class21 class23 = new Class21();
            aClass21Array1662[n] = class23;
            final Class21 class24 = class23;
            class24.aByteArray368 = aByteArray368;
            class24.anInt369 = stream.readUnsignedWord();
            class24.anInt370 = stream.readUnsignedWord();
            class24.anInt371 = stream.readUnsignedByte();
            final int unsignedByte = stream.readUnsignedByte();
            final int unsignedByte2 = stream.readUnsignedByte();
            final int unsignedByte3 = stream.readUnsignedByte();
            final int unsignedByte4 = stream.readUnsignedByte();
            final int unsignedByte5 = stream.readUnsignedByte();
            final int unsignedWord = stream.readUnsignedWord();
            final int unsignedWord2 = stream.readUnsignedWord();
            stream.readUnsignedWord();
            final int unsignedWord3 = stream.readUnsignedWord();
            final int anInt372 = 0;
            class24.anInt372 = anInt372;
            final int anInt373 = anInt372 + class24.anInt369;
            class24.anInt378 = anInt373;
            int n2 = anInt373 + class24.anInt370;
            class24.anInt381 = n2;
            if (unsignedByte2 == 255) {
                n2 += class24.anInt370;
            }
            else {
                class24.anInt381 = -unsignedByte2 - 1;
            }
            class24.anInt383 = n2;
            if (unsignedByte4 == 1) {
                n2 += class24.anInt370;
            }
            else {
                class24.anInt383 = -1;
            }
            class24.anInt380 = n2;
            if (unsignedByte == 1) {
                n2 += class24.anInt370;
            }
            else {
                class24.anInt380 = -1;
            }
            class24.anInt376 = n2;
            if (unsignedByte5 == 1) {
                n2 += class24.anInt369;
            }
            else {
                class24.anInt376 = -1;
            }
            class24.anInt382 = n2;
            if (unsignedByte3 == 1) {
                n2 += class24.anInt370;
            }
            else {
                class24.anInt382 = -1;
            }
            class24.anInt377 = n2;
            final int anInt374 = n2 + unsignedWord3;
            class24.anInt379 = anInt374;
            final int anInt375 = anInt374 + class24.anInt370 * 2;
            class24.anInt384 = anInt375;
            final int anInt376 = anInt375 + class24.anInt371 * 6;
            class24.anInt373 = anInt376;
            final int anInt377 = anInt376 + unsignedWord;
            class24.anInt374 = anInt377;
            class24.anInt375 = anInt377 + unsignedWord2;
        }
        catch (Exception ex) {}
    }
    
    public static void method459(final int n, final OnDemandFetcherParent aOnDemandFetcherParent_1662) {
        Model.aClass21Array1661 = new Class21[80000];
        Model.newmodel = new boolean[100000];
        Model.aOnDemandFetcherParent_1662 = aOnDemandFetcherParent_1662;
    }
    
    public static void method461(final int n) {
        Model.aClass21Array1661[n] = null;
    }
    
    public static Model method462(final int n) {
        if (Model.aClass21Array1661 == null) {
            return null;
        }
        if (Model.aClass21Array1661[n] == null) {
            Model.aOnDemandFetcherParent_1662.method548(n);
            return null;
        }
        return new Model(n);
    }
    
    public static boolean method463(final int n) {
        if (Model.aClass21Array1661 == null) {
            return false;
        }
        if (Model.aClass21Array1661[n] == null) {
            Model.aOnDemandFetcherParent_1662.method548(n);
            return false;
        }
        return true;
    }
    
    private Model(final boolean b) {
        this.anInt1614 = 9;
        this.aBoolean1615 = false;
        this.anInt1616 = 360;
        this.anInt1617 = 1;
        this.aBoolean1618 = true;
        this.aBoolean1659 = false;
        if (!b) {
            this.aBoolean1618 = !this.aBoolean1618;
        }
    }
    
    public Model(final int n, final Model[] array) {
        this.anInt1614 = 9;
        this.aBoolean1615 = false;
        this.anInt1616 = 360;
        this.anInt1617 = 1;
        this.aBoolean1618 = true;
        this.aBoolean1659 = false;
        ++Model.anInt1620;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        this.anInt1626 = 0;
        this.anInt1630 = 0;
        this.anInt1642 = 0;
        this.anInt1641 = -1;
        for (final Model model : array) {
            if (model != null) {
                this.anInt1626 += model.anInt1626;
                this.anInt1630 += model.anInt1630;
                this.anInt1642 += model.anInt1642;
                b |= (model.anIntArray1637 != null);
                if (model.anIntArray1638 != null) {
                    b2 = true;
                }
                else {
                    if (this.anInt1641 == -1) {
                        this.anInt1641 = model.anInt1641;
                    }
                    if (this.anInt1641 != model.anInt1641) {
                        b2 = true;
                    }
                }
                b3 |= (model.anIntArray1639 != null);
                b4 |= (model.anIntArray1656 != null);
            }
        }
        this.anIntArray1627 = new int[this.anInt1626];
        this.anIntArray1628 = new int[this.anInt1626];
        this.anIntArray1629 = new int[this.anInt1626];
        this.anIntArray1655 = new int[this.anInt1626];
        this.anIntArray1631 = new int[this.anInt1630];
        this.anIntArray1632 = new int[this.anInt1630];
        this.anIntArray1633 = new int[this.anInt1630];
        this.anIntArray1643 = new int[this.anInt1642];
        this.anIntArray1644 = new int[this.anInt1642];
        this.anIntArray1645 = new int[this.anInt1642];
        if (b) {
            this.anIntArray1637 = new int[this.anInt1630];
        }
        if (b2) {
            this.anIntArray1638 = new int[this.anInt1630];
        }
        if (b3) {
            this.anIntArray1639 = new int[this.anInt1630];
        }
        if (b4) {
            this.anIntArray1656 = new int[this.anInt1630];
        }
        this.anIntArray1640 = new int[this.anInt1630];
        this.anInt1626 = 0;
        this.anInt1630 = 0;
        this.anInt1642 = 0;
        int n2 = 0;
        for (final Model model2 : array) {
            if (model2 != null) {
                for (int k = 0; k < model2.anInt1630; ++k) {
                    if (b) {
                        if (model2.anIntArray1637 == null) {
                            this.anIntArray1637[this.anInt1630] = 0;
                        }
                        else {
                            int n3 = model2.anIntArray1637[k];
                            if ((n3 & 0x2) == 0x2) {
                                n3 += n2 << 2;
                            }
                            this.anIntArray1637[this.anInt1630] = n3;
                        }
                    }
                    if (b2) {
                        if (model2.anIntArray1638 == null) {
                            this.anIntArray1638[this.anInt1630] = model2.anInt1641;
                        }
                        else {
                            this.anIntArray1638[this.anInt1630] = model2.anIntArray1638[k];
                        }
                    }
                    if (b3) {
                        if (model2.anIntArray1639 == null) {
                            this.anIntArray1639[this.anInt1630] = 0;
                        }
                        else {
                            this.anIntArray1639[this.anInt1630] = model2.anIntArray1639[k];
                        }
                    }
                    if (b4 && model2.anIntArray1656 != null) {
                        this.anIntArray1656[this.anInt1630] = model2.anIntArray1656[k];
                    }
                    this.anIntArray1640[this.anInt1630] = model2.anIntArray1640[k];
                    this.anIntArray1631[this.anInt1630] = this.method465(model2, model2.anIntArray1631[k]);
                    this.anIntArray1632[this.anInt1630] = this.method465(model2, model2.anIntArray1632[k]);
                    this.anIntArray1633[this.anInt1630] = this.method465(model2, model2.anIntArray1633[k]);
                    ++this.anInt1630;
                }
                for (int l = 0; l < model2.anInt1642; ++l) {
                    this.anIntArray1643[this.anInt1642] = this.method465(model2, model2.anIntArray1643[l]);
                    this.anIntArray1644[this.anInt1642] = this.method465(model2, model2.anIntArray1644[l]);
                    this.anIntArray1645[this.anInt1642] = this.method465(model2, model2.anIntArray1645[l]);
                    ++this.anInt1642;
                }
                n2 += model2.anInt1642;
            }
        }
    }
    
    public Model(final Model[] array) {
        final int n = 2;
        this.anInt1614 = 9;
        this.aBoolean1615 = false;
        this.anInt1616 = 360;
        this.anInt1617 = 1;
        this.aBoolean1618 = true;
        this.aBoolean1659 = false;
        ++Model.anInt1620;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        this.anInt1626 = 0;
        this.anInt1630 = 0;
        this.anInt1642 = 0;
        this.anInt1641 = -1;
        for (final Model model : array) {
            if (model != null) {
                this.anInt1626 += model.anInt1626;
                this.anInt1630 += model.anInt1630;
                this.anInt1642 += model.anInt1642;
                b |= (model.anIntArray1637 != null);
                if (model.anIntArray1638 != null) {
                    b2 = true;
                }
                else {
                    if (this.anInt1641 == -1) {
                        this.anInt1641 = model.anInt1641;
                    }
                    if (this.anInt1641 != model.anInt1641) {
                        b2 = true;
                    }
                }
                b3 |= (model.anIntArray1639 != null);
                b4 |= (model.anIntArray1640 != null);
            }
        }
        this.anIntArray1627 = new int[this.anInt1626];
        this.anIntArray1628 = new int[this.anInt1626];
        this.anIntArray1629 = new int[this.anInt1626];
        this.anIntArray1631 = new int[this.anInt1630];
        this.anIntArray1632 = new int[this.anInt1630];
        this.anIntArray1633 = new int[this.anInt1630];
        this.anIntArray1634 = new int[this.anInt1630];
        this.anIntArray1635 = new int[this.anInt1630];
        this.anIntArray1636 = new int[this.anInt1630];
        this.anIntArray1643 = new int[this.anInt1642];
        this.anIntArray1644 = new int[this.anInt1642];
        this.anIntArray1645 = new int[this.anInt1642];
        if (b) {
            this.anIntArray1637 = new int[this.anInt1630];
        }
        if (b2) {
            this.anIntArray1638 = new int[this.anInt1630];
        }
        if (b3) {
            this.anIntArray1639 = new int[this.anInt1630];
        }
        if (b4) {
            this.anIntArray1640 = new int[this.anInt1630];
        }
        this.anInt1626 = 0;
        this.anInt1630 = 0;
        this.anInt1642 = 0;
        int n2 = 0;
        for (final Model model2 : array) {
            if (model2 != null) {
                final int anInt1626 = this.anInt1626;
                for (int k = 0; k < model2.anInt1626; ++k) {
                    this.anIntArray1627[this.anInt1626] = model2.anIntArray1627[k];
                    this.anIntArray1628[this.anInt1626] = model2.anIntArray1628[k];
                    this.anIntArray1629[this.anInt1626] = model2.anIntArray1629[k];
                    ++this.anInt1626;
                }
                for (int l = 0; l < model2.anInt1630; ++l) {
                    this.anIntArray1631[this.anInt1630] = model2.anIntArray1631[l] + anInt1626;
                    this.anIntArray1632[this.anInt1630] = model2.anIntArray1632[l] + anInt1626;
                    this.anIntArray1633[this.anInt1630] = model2.anIntArray1633[l] + anInt1626;
                    this.anIntArray1634[this.anInt1630] = model2.anIntArray1634[l];
                    this.anIntArray1635[this.anInt1630] = model2.anIntArray1635[l];
                    this.anIntArray1636[this.anInt1630] = model2.anIntArray1636[l];
                    if (b) {
                        if (model2.anIntArray1637 == null) {
                            this.anIntArray1637[this.anInt1630] = 0;
                        }
                        else {
                            int n3 = model2.anIntArray1637[l];
                            if ((n3 & 0x2) == 0x2) {
                                n3 += n2 << 2;
                            }
                            this.anIntArray1637[this.anInt1630] = n3;
                        }
                    }
                    if (b2) {
                        if (model2.anIntArray1638 == null) {
                            this.anIntArray1638[this.anInt1630] = model2.anInt1641;
                        }
                        else {
                            this.anIntArray1638[this.anInt1630] = model2.anIntArray1638[l];
                        }
                    }
                    if (b3) {
                        if (model2.anIntArray1639 == null) {
                            this.anIntArray1639[this.anInt1630] = 0;
                        }
                        else {
                            this.anIntArray1639[this.anInt1630] = model2.anIntArray1639[l];
                        }
                    }
                    if (b4 && model2.anIntArray1640 != null) {
                        this.anIntArray1640[this.anInt1630] = model2.anIntArray1640[l];
                    }
                    ++this.anInt1630;
                }
                for (int n4 = 0; n4 < model2.anInt1642; ++n4) {
                    this.anIntArray1643[this.anInt1642] = model2.anIntArray1643[n4] + anInt1626;
                    this.anIntArray1644[this.anInt1642] = model2.anIntArray1644[n4] + anInt1626;
                    this.anIntArray1645[this.anInt1642] = model2.anIntArray1645[n4] + anInt1626;
                    ++this.anInt1642;
                }
                n2 += model2.anInt1642;
            }
        }
        this.method466();
    }
    
    public Model(final boolean b, final boolean b2, final boolean b3, final Model model) {
        this.anInt1614 = 9;
        this.aBoolean1615 = false;
        this.anInt1616 = 360;
        this.anInt1617 = 1;
        this.aBoolean1618 = true;
        this.aBoolean1659 = false;
        ++Model.anInt1620;
        this.anInt1626 = model.anInt1626;
        this.anInt1630 = model.anInt1630;
        this.anInt1642 = model.anInt1642;
        if (b3) {
            this.anIntArray1627 = model.anIntArray1627;
            this.anIntArray1628 = model.anIntArray1628;
            this.anIntArray1629 = model.anIntArray1629;
        }
        else {
            this.anIntArray1627 = new int[this.anInt1626];
            this.anIntArray1628 = new int[this.anInt1626];
            this.anIntArray1629 = new int[this.anInt1626];
            for (int i = 0; i < this.anInt1626; ++i) {
                this.anIntArray1627[i] = model.anIntArray1627[i];
                this.anIntArray1628[i] = model.anIntArray1628[i];
                this.anIntArray1629[i] = model.anIntArray1629[i];
            }
        }
        if (b) {
            this.anIntArray1640 = model.anIntArray1640;
        }
        else {
            this.anIntArray1640 = new int[this.anInt1630];
            for (int j = 0; j < this.anInt1630; ++j) {
                this.anIntArray1640[j] = model.anIntArray1640[j];
            }
        }
        if (b2) {
            this.anIntArray1639 = model.anIntArray1639;
        }
        else {
            this.anIntArray1639 = new int[this.anInt1630];
            if (model.anIntArray1639 == null) {
                for (int k = 0; k < this.anInt1630; ++k) {
                    this.anIntArray1639[k] = 0;
                }
            }
            else {
                for (int l = 0; l < this.anInt1630; ++l) {
                    this.anIntArray1639[l] = model.anIntArray1639[l];
                }
            }
        }
        this.anIntArray1655 = model.anIntArray1655;
        this.anIntArray1656 = model.anIntArray1656;
        this.anIntArray1637 = model.anIntArray1637;
        this.anIntArray1631 = model.anIntArray1631;
        this.anIntArray1632 = model.anIntArray1632;
        this.anIntArray1633 = model.anIntArray1633;
        this.anIntArray1638 = model.anIntArray1638;
        this.anInt1641 = model.anInt1641;
        this.anIntArray1643 = model.anIntArray1643;
        this.anIntArray1644 = model.anIntArray1644;
        this.anIntArray1645 = model.anIntArray1645;
    }
    
    public Model(final boolean b, final boolean b2, final Model model) {
        this.anInt1614 = 9;
        this.aBoolean1615 = false;
        this.anInt1616 = 360;
        this.anInt1617 = 1;
        this.aBoolean1618 = true;
        this.aBoolean1659 = false;
        ++Model.anInt1620;
        this.anInt1626 = model.anInt1626;
        this.anInt1630 = model.anInt1630;
        this.anInt1642 = model.anInt1642;
        if (b) {
            this.anIntArray1628 = new int[this.anInt1626];
            for (int i = 0; i < this.anInt1626; ++i) {
                this.anIntArray1628[i] = model.anIntArray1628[i];
            }
        }
        else {
            this.anIntArray1628 = model.anIntArray1628;
        }
        if (b2) {
            this.anIntArray1634 = new int[this.anInt1630];
            this.anIntArray1635 = new int[this.anInt1630];
            this.anIntArray1636 = new int[this.anInt1630];
            for (int j = 0; j < this.anInt1630; ++j) {
                this.anIntArray1634[j] = model.anIntArray1634[j];
                this.anIntArray1635[j] = model.anIntArray1635[j];
                this.anIntArray1636[j] = model.anIntArray1636[j];
            }
            this.anIntArray1637 = new int[this.anInt1630];
            if (model.anIntArray1637 == null) {
                for (int k = 0; k < this.anInt1630; ++k) {
                    this.anIntArray1637[k] = 0;
                }
            }
            else {
                for (int l = 0; l < this.anInt1630; ++l) {
                    this.anIntArray1637[l] = model.anIntArray1637[l];
                }
            }
            super.aClass33Array1425 = new Class33[this.anInt1626];
            for (int n = 0; n < this.anInt1626; ++n) {
                final Class33[] aClass33Array1425 = super.aClass33Array1425;
                final int n2 = n;
                final Class33 class33 = new Class33();
                aClass33Array1425[n2] = class33;
                final Class33 class34 = class33;
                final Class33 class35 = model.aClass33Array1425[n];
                class34.anInt602 = class35.anInt602;
                class34.anInt603 = class35.anInt603;
                class34.anInt604 = class35.anInt604;
                class34.anInt605 = class35.anInt605;
            }
            this.aClass33Array1660 = model.aClass33Array1660;
        }
        else {
            this.anIntArray1634 = model.anIntArray1634;
            this.anIntArray1635 = model.anIntArray1635;
            this.anIntArray1636 = model.anIntArray1636;
            this.anIntArray1637 = model.anIntArray1637;
        }
        this.anIntArray1627 = model.anIntArray1627;
        this.anIntArray1629 = model.anIntArray1629;
        this.anIntArray1640 = model.anIntArray1640;
        this.anIntArray1639 = model.anIntArray1639;
        this.anIntArray1638 = model.anIntArray1638;
        this.anInt1641 = model.anInt1641;
        this.anIntArray1631 = model.anIntArray1631;
        this.anIntArray1632 = model.anIntArray1632;
        this.anIntArray1633 = model.anIntArray1633;
        this.anIntArray1643 = model.anIntArray1643;
        this.anIntArray1644 = model.anIntArray1644;
        this.anIntArray1645 = model.anIntArray1645;
        super.modelHeight = model.modelHeight;
        this.anInt1650 = model.anInt1650;
        this.anInt1653 = model.anInt1653;
        this.anInt1652 = model.anInt1652;
        this.anInt1646 = model.anInt1646;
        this.anInt1648 = model.anInt1648;
        this.anInt1649 = model.anInt1649;
        this.anInt1647 = model.anInt1647;
    }
    
    public void method464(final Model model, final boolean b) {
        this.anInt1626 = model.anInt1626;
        this.anInt1630 = model.anInt1630;
        this.anInt1642 = model.anInt1642;
        if (Model.anIntArray1622.length < this.anInt1626) {
            Model.anIntArray1622 = new int[this.anInt1626 + 10000];
            Model.anIntArray1623 = new int[this.anInt1626 + 10000];
            Model.anIntArray1624 = new int[this.anInt1626 + 10000];
        }
        this.anIntArray1627 = Model.anIntArray1622;
        this.anIntArray1628 = Model.anIntArray1623;
        this.anIntArray1629 = Model.anIntArray1624;
        for (int i = 0; i < this.anInt1626; ++i) {
            this.anIntArray1627[i] = model.anIntArray1627[i];
            this.anIntArray1628[i] = model.anIntArray1628[i];
            this.anIntArray1629[i] = model.anIntArray1629[i];
        }
        if (b) {
            this.anIntArray1639 = model.anIntArray1639;
        }
        else {
            if (Model.anIntArray1625.length < this.anInt1630) {
                Model.anIntArray1625 = new int[this.anInt1630 + 100];
            }
            this.anIntArray1639 = Model.anIntArray1625;
            if (model.anIntArray1639 == null) {
                for (int j = 0; j < this.anInt1630; ++j) {
                    this.anIntArray1639[j] = 0;
                }
            }
            else {
                for (int k = 0; k < this.anInt1630; ++k) {
                    this.anIntArray1639[k] = model.anIntArray1639[k];
                }
            }
        }
        this.anIntArray1637 = model.anIntArray1637;
        this.anIntArray1640 = model.anIntArray1640;
        this.anIntArray1638 = model.anIntArray1638;
        this.anInt1641 = model.anInt1641;
        this.anIntArrayArray1658 = model.anIntArrayArray1658;
        this.anIntArrayArray1657 = model.anIntArrayArray1657;
        this.anIntArray1631 = model.anIntArray1631;
        this.anIntArray1632 = model.anIntArray1632;
        this.anIntArray1633 = model.anIntArray1633;
        this.anIntArray1634 = model.anIntArray1634;
        this.anIntArray1635 = model.anIntArray1635;
        this.anIntArray1636 = model.anIntArray1636;
        this.anIntArray1643 = model.anIntArray1643;
        this.anIntArray1644 = model.anIntArray1644;
        this.anIntArray1645 = model.anIntArray1645;
    }
    
    private final int method465(final Model model, final int n) {
        int n2 = -1;
        final int n3 = model.anIntArray1627[n];
        final int n4 = model.anIntArray1628[n];
        final int n5 = model.anIntArray1629[n];
        for (int i = 0; i < this.anInt1626; ++i) {
            if (n3 == this.anIntArray1627[i] && n4 == this.anIntArray1628[i] && n5 == this.anIntArray1629[i]) {
                n2 = i;
                break;
            }
        }
        if (n2 == -1) {
            this.anIntArray1627[this.anInt1626] = n3;
            this.anIntArray1628[this.anInt1626] = n4;
            this.anIntArray1629[this.anInt1626] = n5;
            if (model.anIntArray1655 != null) {
                this.anIntArray1655[this.anInt1626] = model.anIntArray1655[n];
            }
            n2 = this.anInt1626++;
        }
        return n2;
    }
    
    public void method466() {
        super.modelHeight = 0;
        this.anInt1650 = 0;
        this.anInt1651 = 0;
        for (int i = 0; i < this.anInt1626; ++i) {
            final int n = this.anIntArray1627[i];
            final int anInt1651 = this.anIntArray1628[i];
            final int n2 = this.anIntArray1629[i];
            if (-anInt1651 > super.modelHeight) {
                super.modelHeight = -anInt1651;
            }
            if (anInt1651 > this.anInt1651) {
                this.anInt1651 = anInt1651;
            }
            final int anInt1652 = n * n + n2 * n2;
            if (anInt1652 > this.anInt1650) {
                this.anInt1650 = anInt1652;
            }
        }
        this.anInt1650 = (int)(Math.sqrt(this.anInt1650) + 0.99);
        this.anInt1653 = (int)(Math.sqrt(this.anInt1650 * this.anInt1650 + super.modelHeight * super.modelHeight) + 0.99);
        this.anInt1652 = this.anInt1653 + (int)(Math.sqrt(this.anInt1650 * this.anInt1650 + this.anInt1651 * this.anInt1651) + 0.99);
    }
    
    public void method467() {
        super.modelHeight = 0;
        this.anInt1651 = 0;
        for (int i = 0; i < this.anInt1626; ++i) {
            final int anInt1651 = this.anIntArray1628[i];
            if (-anInt1651 > super.modelHeight) {
                super.modelHeight = -anInt1651;
            }
            if (anInt1651 > this.anInt1651) {
                this.anInt1651 = anInt1651;
            }
        }
        this.anInt1653 = (int)(Math.sqrt(this.anInt1650 * this.anInt1650 + super.modelHeight * super.modelHeight) + 0.99);
        this.anInt1652 = this.anInt1653 + (int)(Math.sqrt(this.anInt1650 * this.anInt1650 + this.anInt1651 * this.anInt1651) + 0.99);
    }
    
    public void method468(final int n) {
        super.modelHeight = 0;
        this.anInt1650 = 0;
        this.anInt1651 = 0;
        this.anInt1646 = 999999;
        this.anInt1647 = -999999;
        this.anInt1648 = -99999;
        this.anInt1649 = 99999;
        for (int i = 0; i < this.anInt1626; ++i) {
            final int n2 = this.anIntArray1627[i];
            final int anInt1651 = this.anIntArray1628[i];
            final int n3 = this.anIntArray1629[i];
            if (n2 < this.anInt1646) {
                this.anInt1646 = n2;
            }
            if (n2 > this.anInt1647) {
                this.anInt1647 = n2;
            }
            if (n3 < this.anInt1649) {
                this.anInt1649 = n3;
            }
            if (n3 > this.anInt1648) {
                this.anInt1648 = n3;
            }
            if (-anInt1651 > super.modelHeight) {
                super.modelHeight = -anInt1651;
            }
            if (anInt1651 > this.anInt1651) {
                this.anInt1651 = anInt1651;
            }
            final int anInt1652 = n2 * n2 + n3 * n3;
            if (anInt1652 > this.anInt1650) {
                this.anInt1650 = anInt1652;
            }
        }
        this.anInt1650 = (int)Math.sqrt(this.anInt1650);
        this.anInt1653 = (int)Math.sqrt(this.anInt1650 * this.anInt1650 + super.modelHeight * super.modelHeight);
        if (n != 21073) {
            return;
        }
        this.anInt1652 = this.anInt1653 + (int)Math.sqrt(this.anInt1650 * this.anInt1650 + this.anInt1651 * this.anInt1651);
    }
    
    public void method469() {
        if (this.anIntArray1655 != null) {
            final int[] array = new int[256];
            int n = 0;
            for (int i = 0; i < this.anInt1626; ++i) {
                final int n2 = this.anIntArray1655[i];
                final int[] array2 = array;
                final int n3 = n2;
                ++array2[n3];
                if (n2 > n) {
                    n = n2;
                }
            }
            this.anIntArrayArray1657 = new int[n + 1][];
            for (int j = 0; j <= n; ++j) {
                this.anIntArrayArray1657[j] = new int[array[j]];
                array[j] = 0;
            }
            for (int k = 0; k < this.anInt1626; ++k) {
                final int n4 = this.anIntArray1655[k];
                this.anIntArrayArray1657[n4][array[n4]++] = k;
            }
            this.anIntArray1655 = null;
        }
        if (this.anIntArray1656 != null) {
            final int[] array3 = new int[256];
            int n5 = 0;
            for (int l = 0; l < this.anInt1630; ++l) {
                final int n6 = this.anIntArray1656[l];
                final int[] array4 = array3;
                final int n7 = n6;
                ++array4[n7];
                if (n6 > n5) {
                    n5 = n6;
                }
            }
            this.anIntArrayArray1658 = new int[n5 + 1][];
            for (int n8 = 0; n8 <= n5; ++n8) {
                this.anIntArrayArray1658[n8] = new int[array3[n8]];
                array3[n8] = 0;
            }
            for (int n9 = 0; n9 < this.anInt1630; ++n9) {
                final int n10 = this.anIntArray1656[n9];
                this.anIntArrayArray1658[n10][array3[n10]++] = n9;
            }
            this.anIntArray1656 = null;
        }
    }
    
    public void method470(final int n) {
        if (this.anIntArrayArray1657 == null) {
            return;
        }
        if (n == -1) {
            return;
        }
        final Class36 method531 = Class36.method531(n);
        if (method531 == null) {
            return;
        }
        final Class18 aClass18_637 = method531.aClass18_637;
        Model.anInt1681 = 0;
        Model.anInt1682 = 0;
        Model.anInt1683 = 0;
        for (int i = 0; i < method531.anInt638; ++i) {
            final int n2 = method531.anIntArray639[i];
            this.method472(aClass18_637.anIntArray342[n2], aClass18_637.anIntArrayArray343[n2], method531.anIntArray640[i], method531.anIntArray641[i], method531.anIntArray642[i]);
        }
    }
    
    public void method471(final int[] array, final int n, final int n2) {
        if (n2 == -1) {
            return;
        }
        if (array == null || n == -1) {
            this.method470(n2);
            return;
        }
        final Class36 method531 = Class36.method531(n2);
        if (method531 == null) {
            return;
        }
        final Class36 method532 = Class36.method531(n);
        if (method532 == null) {
            this.method470(n2);
            return;
        }
        final Class18 aClass18_637 = method531.aClass18_637;
        Model.anInt1681 = 0;
        Model.anInt1682 = 0;
        Model.anInt1683 = 0;
        int n3 = 0;
        int n4 = array[n3++];
        for (int i = 0; i < method531.anInt638; ++i) {
            int j;
            for (j = method531.anIntArray639[i]; j > n4; n4 = array[n3++]) {}
            if (j != n4 || aClass18_637.anIntArray342[j] == 0) {
                this.method472(aClass18_637.anIntArray342[j], aClass18_637.anIntArrayArray343[j], method531.anIntArray640[i], method531.anIntArray641[i], method531.anIntArray642[i]);
            }
        }
        Model.anInt1681 = 0;
        Model.anInt1682 = 0;
        Model.anInt1683 = 0;
        int n5 = 0;
        int n6 = array[n5++];
        for (int k = 0; k < method532.anInt638; ++k) {
            int l;
            for (l = method532.anIntArray639[k]; l > n6; n6 = array[n5++]) {}
            if (l == n6 || aClass18_637.anIntArray342[l] == 0) {
                this.method472(aClass18_637.anIntArray342[l], aClass18_637.anIntArrayArray343[l], method532.anIntArray640[k], method532.anIntArray641[k], method532.anIntArray642[k]);
            }
        }
    }
    
    private void method472(final int n, final int[] array, final int anInt1681, final int anInt1682, final int anInt1683) {
        final int length = array.length;
        if (n == 0) {
            int n2 = 0;
            Model.anInt1681 = 0;
            Model.anInt1682 = 0;
            Model.anInt1683 = 0;
            for (final int n3 : array) {
                if (n3 < this.anIntArrayArray1657.length) {
                    final int[] array2 = this.anIntArrayArray1657[n3];
                    for (int j = 0; j < array2.length; ++j) {
                        final int n4 = array2[j];
                        Model.anInt1681 += this.anIntArray1627[n4];
                        Model.anInt1682 += this.anIntArray1628[n4];
                        Model.anInt1683 += this.anIntArray1629[n4];
                        ++n2;
                    }
                }
            }
            if (n2 > 0) {
                Model.anInt1681 = Model.anInt1681 / n2 + anInt1681;
                Model.anInt1682 = Model.anInt1682 / n2 + anInt1682;
                Model.anInt1683 = Model.anInt1683 / n2 + anInt1683;
                return;
            }
            Model.anInt1681 = anInt1681;
            Model.anInt1682 = anInt1682;
            Model.anInt1683 = anInt1683;
        }
        else {
            if (n == 1) {
                for (final int n5 : array) {
                    if (n5 < this.anIntArrayArray1657.length) {
                        final int[] array3 = this.anIntArrayArray1657[n5];
                        for (int l = 0; l < array3.length; ++l) {
                            final int n6 = array3[l];
                            final int[] anIntArray1627 = this.anIntArray1627;
                            final int n7 = n6;
                            anIntArray1627[n7] += anInt1681;
                            final int[] anIntArray1628 = this.anIntArray1628;
                            final int n8 = n6;
                            anIntArray1628[n8] += anInt1682;
                            final int[] anIntArray1629 = this.anIntArray1629;
                            final int n9 = n6;
                            anIntArray1629[n9] += anInt1683;
                        }
                    }
                }
                return;
            }
            if (n == 2) {
                for (final int n11 : array) {
                    if (n11 < this.anIntArrayArray1657.length) {
                        final int[] array4 = this.anIntArrayArray1657[n11];
                        for (int n12 = 0; n12 < array4.length; ++n12) {
                            final int n13 = array4[n12];
                            final int[] anIntArray1630 = this.anIntArray1627;
                            final int n14 = n13;
                            anIntArray1630[n14] -= Model.anInt1681;
                            final int[] anIntArray1631 = this.anIntArray1628;
                            final int n15 = n13;
                            anIntArray1631[n15] -= Model.anInt1682;
                            final int[] anIntArray1632 = this.anIntArray1629;
                            final int n16 = n13;
                            anIntArray1632[n16] -= Model.anInt1683;
                            final int n17 = (anInt1681 & 0xFF) * 8;
                            final int n18 = (anInt1682 & 0xFF) * 8;
                            final int n19 = (anInt1683 & 0xFF) * 8;
                            if (n19 != 0) {
                                final int n20 = Model.modelIntArray1[n19];
                                final int n21 = Model.modelIntArray2[n19];
                                final int n22 = this.anIntArray1628[n13] * n20 + this.anIntArray1627[n13] * n21 >> 16;
                                this.anIntArray1628[n13] = this.anIntArray1628[n13] * n21 - this.anIntArray1627[n13] * n20 >> 16;
                                this.anIntArray1627[n13] = n22;
                            }
                            if (n17 != 0) {
                                final int n23 = Model.modelIntArray1[n17];
                                final int n24 = Model.modelIntArray2[n17];
                                final int n25 = this.anIntArray1628[n13] * n24 - this.anIntArray1629[n13] * n23 >> 16;
                                this.anIntArray1629[n13] = this.anIntArray1628[n13] * n23 + this.anIntArray1629[n13] * n24 >> 16;
                                this.anIntArray1628[n13] = n25;
                            }
                            if (n18 != 0) {
                                final int n26 = Model.modelIntArray1[n18];
                                final int n27 = Model.modelIntArray2[n18];
                                final int n28 = this.anIntArray1629[n13] * n26 + this.anIntArray1627[n13] * n27 >> 16;
                                this.anIntArray1629[n13] = this.anIntArray1629[n13] * n27 - this.anIntArray1627[n13] * n26 >> 16;
                                this.anIntArray1627[n13] = n28;
                            }
                            final int[] anIntArray1633 = this.anIntArray1627;
                            final int n29 = n13;
                            anIntArray1633[n29] += Model.anInt1681;
                            final int[] anIntArray1634 = this.anIntArray1628;
                            final int n30 = n13;
                            anIntArray1634[n30] += Model.anInt1682;
                            final int[] anIntArray1635 = this.anIntArray1629;
                            final int n31 = n13;
                            anIntArray1635[n31] += Model.anInt1683;
                        }
                    }
                }
                return;
            }
            if (n == 3) {
                for (final int n33 : array) {
                    if (n33 < this.anIntArrayArray1657.length) {
                        final int[] array5 = this.anIntArrayArray1657[n33];
                        for (int n34 = 0; n34 < array5.length; ++n34) {
                            final int n35 = array5[n34];
                            final int[] anIntArray1636 = this.anIntArray1627;
                            final int n36 = n35;
                            anIntArray1636[n36] -= Model.anInt1681;
                            final int[] anIntArray1637 = this.anIntArray1628;
                            final int n37 = n35;
                            anIntArray1637[n37] -= Model.anInt1682;
                            final int[] anIntArray1638 = this.anIntArray1629;
                            final int n38 = n35;
                            anIntArray1638[n38] -= Model.anInt1683;
                            this.anIntArray1627[n35] = this.anIntArray1627[n35] * anInt1681 / 128;
                            this.anIntArray1628[n35] = this.anIntArray1628[n35] * anInt1682 / 128;
                            this.anIntArray1629[n35] = this.anIntArray1629[n35] * anInt1683 / 128;
                            final int[] anIntArray1639 = this.anIntArray1627;
                            final int n39 = n35;
                            anIntArray1639[n39] += Model.anInt1681;
                            final int[] anIntArray1640 = this.anIntArray1628;
                            final int n40 = n35;
                            anIntArray1640[n40] += Model.anInt1682;
                            final int[] anIntArray1641 = this.anIntArray1629;
                            final int n41 = n35;
                            anIntArray1641[n41] += Model.anInt1683;
                        }
                    }
                }
                return;
            }
            if (n == 5 && this.anIntArrayArray1658 != null && this.anIntArray1639 != null) {
                for (final int n43 : array) {
                    if (n43 < this.anIntArrayArray1658.length) {
                        final int[] array6 = this.anIntArrayArray1658[n43];
                        for (int n44 = 0; n44 < array6.length; ++n44) {
                            final int n45 = array6[n44];
                            final int[] anIntArray1642 = this.anIntArray1639;
                            final int n46 = n45;
                            anIntArray1642[n46] += anInt1681 * 8;
                            if (this.anIntArray1639[n45] < 0) {
                                this.anIntArray1639[n45] = 0;
                            }
                            if (this.anIntArray1639[n45] > 255) {
                                this.anIntArray1639[n45] = 255;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void method473() {
        for (int i = 0; i < this.anInt1626; ++i) {
            final int n = this.anIntArray1627[i];
            this.anIntArray1627[i] = this.anIntArray1629[i];
            this.anIntArray1629[i] = -n;
        }
    }
    
    public void method474(final int n) {
        final int n2 = Model.modelIntArray1[n];
        final int n3 = Model.modelIntArray2[n];
        for (int i = 0; i < this.anInt1626; ++i) {
            final int n4 = this.anIntArray1628[i] * n3 - this.anIntArray1629[i] * n2 >> 16;
            this.anIntArray1629[i] = this.anIntArray1628[i] * n2 + this.anIntArray1629[i] * n3 >> 16;
            this.anIntArray1628[i] = n4;
        }
    }
    
    public void method475(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.anInt1626; ++i) {
            final int[] anIntArray1627 = this.anIntArray1627;
            final int n4 = i;
            anIntArray1627[n4] += n;
            final int[] anIntArray1628 = this.anIntArray1628;
            final int n5 = i;
            anIntArray1628[n5] += n2;
            final int[] anIntArray1629 = this.anIntArray1629;
            final int n6 = i;
            anIntArray1629[n6] += n3;
        }
    }
    
    public void method476(final int n, final int n2) {
        for (int i = 0; i < this.anInt1630; ++i) {
            if (this.anIntArray1640[i] == n) {
                this.anIntArray1640[i] = n2;
            }
        }
    }
    
    public void method477() {
        for (int i = 0; i < this.anInt1626; ++i) {
            this.anIntArray1629[i] = -this.anIntArray1629[i];
        }
        for (int j = 0; j < this.anInt1630; ++j) {
            final int n = this.anIntArray1631[j];
            this.anIntArray1631[j] = this.anIntArray1633[j];
            this.anIntArray1633[j] = n;
        }
    }
    
    public void method478(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.anInt1626; ++i) {
            this.anIntArray1627[i] = this.anIntArray1627[i] * n / 128;
            this.anIntArray1628[i] = this.anIntArray1628[i] * n3 / 128;
            this.anIntArray1629[i] = this.anIntArray1629[i] * n2 / 128;
        }
    }
    
    public final void method479(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        final int n6 = n2 * (int)Math.sqrt(n3 * n3 + n4 * n4 + n5 * n5) >> 8;
        if (this.anIntArray1634 == null) {
            this.anIntArray1634 = new int[this.anInt1630];
            this.anIntArray1635 = new int[this.anInt1630];
            this.anIntArray1636 = new int[this.anInt1630];
        }
        if (super.aClass33Array1425 == null) {
            super.aClass33Array1425 = new Class33[this.anInt1626];
            for (int i = 0; i < this.anInt1626; ++i) {
                super.aClass33Array1425[i] = new Class33();
            }
        }
        for (int j = 0; j < this.anInt1630; ++j) {
            if (this.anIntArray1640 != null && this.anIntArray1639 != null && (this.anIntArray1640[j] == 65535 || this.anIntArray1640[j] == 0 || this.anIntArray1640[j] == 16705)) {
                this.anIntArray1639[j] = 255;
            }
            final int n7 = this.anIntArray1631[j];
            final int n8 = this.anIntArray1632[j];
            final int n9 = this.anIntArray1633[j];
            final int n10 = this.anIntArray1627[n8] - this.anIntArray1627[n7];
            final int n11 = this.anIntArray1628[n8] - this.anIntArray1628[n7];
            final int n12 = this.anIntArray1629[n8] - this.anIntArray1629[n7];
            final int n13 = this.anIntArray1627[n9] - this.anIntArray1627[n7];
            final int n14 = this.anIntArray1628[n9] - this.anIntArray1628[n7];
            final int n15 = this.anIntArray1629[n9] - this.anIntArray1629[n7];
            int n16;
            int n17;
            int n18;
            for (n16 = n11 * n15 - n14 * n12, n17 = n12 * n13 - n15 * n10, n18 = n10 * n14 - n13 * n11; n16 > 8192 || n17 > 8192 || n18 > 8192 || n16 < -8192 || n17 < -8192 || n18 < -8192; n16 >>= 1, n17 >>= 1, n18 >>= 1) {}
            int n19 = (int)Math.sqrt(n16 * n16 + n17 * n17 + n18 * n18);
            if (n19 <= 0) {
                n19 = 1;
            }
            final int n20 = n16 * 256 / n19;
            final int n21 = n17 * 256 / n19;
            final int n22 = n18 * 256 / n19;
            if (this.anIntArray1637 == null || (this.anIntArray1637[j] & 0x1) == 0x0) {
                final Class33 class34;
                final Class33 class33 = class34 = super.aClass33Array1425[n7];
                class34.anInt602 += n20;
                final Class33 class35 = class33;
                class35.anInt603 += n21;
                final Class33 class36 = class33;
                class36.anInt604 += n22;
                final Class33 class37 = class33;
                ++class37.anInt605;
                final Class33 class39;
                final Class33 class38 = class39 = super.aClass33Array1425[n8];
                class39.anInt602 += n20;
                final Class33 class40 = class38;
                class40.anInt603 += n21;
                final Class33 class41 = class38;
                class41.anInt604 += n22;
                final Class33 class42 = class38;
                ++class42.anInt605;
                final Class33 class44;
                final Class33 class43 = class44 = super.aClass33Array1425[n9];
                class44.anInt602 += n20;
                final Class33 class45 = class43;
                class45.anInt603 += n21;
                final Class33 class46 = class43;
                class46.anInt604 += n22;
                final Class33 class47 = class43;
                ++class47.anInt605;
            }
            else {
                this.anIntArray1634[j] = method481(this.anIntArray1640[j], n + (n3 * n20 + n4 * n21 + n5 * n22) / (n6 + n6 / 2), this.anIntArray1637[j]);
            }
        }
        if (b) {
            this.method480(n, n6, n3, n4, n5);
        }
        else {
            this.aClass33Array1660 = new Class33[this.anInt1626];
            for (int k = 0; k < this.anInt1626; ++k) {
                final Class33 class48 = super.aClass33Array1425[k];
                final Class33[] aClass33Array1660 = this.aClass33Array1660;
                final int n23 = k;
                final Class33 class49 = new Class33();
                aClass33Array1660[n23] = class49;
                final Class33 class50 = class49;
                class50.anInt602 = class48.anInt602;
                class50.anInt603 = class48.anInt603;
                class50.anInt604 = class48.anInt604;
                class50.anInt605 = class48.anInt605;
            }
        }
        if (b) {
            this.method466();
            return;
        }
        this.method468(21073);
    }
    
    public final void method480(final int n, final int n2, final int n3, final int n4, final int n5) {
        for (int i = 0; i < this.anInt1630; ++i) {
            final int n6 = this.anIntArray1631[i];
            final int n7 = this.anIntArray1632[i];
            final int n8 = this.anIntArray1633[i];
            if (this.anIntArray1637 == null) {
                final int n9 = this.anIntArray1640[i];
                final Class33 class33 = super.aClass33Array1425[n6];
                this.anIntArray1634[i] = method481(n9, n + (n3 * class33.anInt602 + n4 * class33.anInt603 + n5 * class33.anInt604) / (n2 * class33.anInt605), 0);
                final Class33 class34 = super.aClass33Array1425[n7];
                this.anIntArray1635[i] = method481(n9, n + (n3 * class34.anInt602 + n4 * class34.anInt603 + n5 * class34.anInt604) / (n2 * class34.anInt605), 0);
                final Class33 class35 = super.aClass33Array1425[n8];
                this.anIntArray1636[i] = method481(n9, n + (n3 * class35.anInt602 + n4 * class35.anInt603 + n5 * class35.anInt604) / (n2 * class35.anInt605), 0);
            }
            else if ((this.anIntArray1637[i] & 0x1) == 0x0) {
                final int n10 = this.anIntArray1640[i];
                final int n11 = this.anIntArray1637[i];
                final Class33 class36 = super.aClass33Array1425[n6];
                this.anIntArray1634[i] = method481(n10, n + (n3 * class36.anInt602 + n4 * class36.anInt603 + n5 * class36.anInt604) / (n2 * class36.anInt605), n11);
                final Class33 class37 = super.aClass33Array1425[n7];
                this.anIntArray1635[i] = method481(n10, n + (n3 * class37.anInt602 + n4 * class37.anInt603 + n5 * class37.anInt604) / (n2 * class37.anInt605), n11);
                final Class33 class38 = super.aClass33Array1425[n8];
                this.anIntArray1636[i] = method481(n10, n + (n3 * class38.anInt602 + n4 * class38.anInt603 + n5 * class38.anInt604) / (n2 * class38.anInt605), n11);
            }
        }
        super.aClass33Array1425 = null;
        this.aClass33Array1660 = null;
        this.anIntArray1655 = null;
        this.anIntArray1656 = null;
        if (this.anIntArray1637 != null) {
            for (int j = 0; j < this.anInt1630; ++j) {
                if ((this.anIntArray1637[j] & 0x2) == 0x2) {
                    return;
                }
            }
        }
        this.anIntArray1640 = null;
    }
    
    public static final int method481(final int n, int n2, final int n3) {
        if (n == 65535) {
            return 0;
        }
        if ((n3 & 0x2) == 0x2) {
            if (n2 < 0) {
                n2 = 0;
            }
            else if (n2 > 127) {
                n2 = 127;
            }
            n2 = 127 - n2;
            return n2;
        }
        n2 = n2 * (n & 0x7F) >> 7;
        if (n2 < 2) {
            n2 = 2;
        }
        else if (n2 > 126) {
            n2 = 126;
        }
        return (n & 0xFF80) + n2;
    }
    
    public final void method482(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = 0;
        final int textureInt1 = Texture.textureInt1;
        final int textureInt2 = Texture.textureInt2;
        final int n8 = Model.modelIntArray1[n7];
        final int n9 = Model.modelIntArray2[n7];
        final int n10 = Model.modelIntArray1[n];
        final int n11 = Model.modelIntArray2[n];
        final int n12 = Model.modelIntArray1[n2];
        final int n13 = Model.modelIntArray2[n2];
        final int n14 = Model.modelIntArray1[n3];
        final int n15 = Model.modelIntArray2[n3];
        final int n16 = n5 * n14 + n6 * n15 >> 16;
        for (int i = 0; i < this.anInt1626; ++i) {
            int n17 = this.anIntArray1627[i];
            int n18 = this.anIntArray1628[i];
            int n19 = this.anIntArray1629[i];
            if (n2 != 0) {
                final int n20 = n18 * n12 + n17 * n13 >> 16;
                n18 = n18 * n13 - n17 * n12 >> 16;
                n17 = n20;
            }
            if (n7 != 0) {
                final int n21 = n18 * n9 - n19 * n8 >> 16;
                n19 = n18 * n8 + n19 * n9 >> 16;
                n18 = n21;
            }
            if (n != 0) {
                final int n22 = n19 * n10 + n17 * n11 >> 16;
                n19 = n19 * n11 - n17 * n10 >> 16;
                n17 = n22;
            }
            final int n23 = n17 + n4;
            final int n24 = n18 + n5;
            final int n25 = n19 + n6;
            final int n26 = n24 * n15 - n25 * n14 >> 16;
            final int n27 = n24 * n14 + n25 * n15 >> 16;
            final int n28 = n26;
            Model.anIntArray1667[i] = n27 - n16;
            Model.anIntArray1665[i] = textureInt1 + (n23 << 9) / n27;
            Model.anIntArray1666[i] = textureInt2 + (n28 << 9) / n27;
            if (this.anInt1642 > 0) {
                Model.anIntArray1668[i] = n23;
                Model.anIntArray1669[i] = n28;
                Model.anIntArray1670[i] = n27;
            }
        }
        try {
            this.method483(false, false, 0);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public final void method443(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final int n10 = n8 * n5 - n6 * n4 >> 16;
        final int n11 = n7 * n2 + n10 * n3 >> 16;
        final int n12 = this.anInt1650 * n3 >> 16;
        final int n13 = n11 + n12;
        if (n13 <= 50 || n11 >= 3500) {
            return;
        }
        final int n14 = n8 * n4 + n6 * n5 >> 16;
        final int n15 = n14 - this.anInt1650 << 9;
        if (n15 / n13 >= DrawingArea.centerY) {
            return;
        }
        final int n16 = n14 + this.anInt1650 << 9;
        if (n16 / n13 <= -DrawingArea.centerY) {
            return;
        }
        final int n17 = n7 * n3 - n10 * n2 >> 16;
        final int n18 = this.anInt1650 * n2 >> 16;
        final int n19 = n17 + n18 << 9;
        if (n19 / n13 <= -DrawingArea.anInt1387) {
            return;
        }
        final int n20 = n17 - (n18 + (super.modelHeight * n3 >> 16)) << 9;
        if (n20 / n13 >= DrawingArea.anInt1387) {
            return;
        }
        final int n21 = n12 + (super.modelHeight * n2 >> 16);
        boolean b = false;
        if (n11 - n21 <= 50) {
            b = true;
        }
        boolean b2 = false;
        if (n9 > 0 && Model.aBoolean1684) {
            int n22 = n11 - n12;
            if (n22 <= 50) {
                n22 = 50;
            }
            int n23;
            int n24;
            if (n14 > 0) {
                n23 = n15 / n13;
                n24 = n16 / n22;
            }
            else {
                n24 = n16 / n13;
                n23 = n15 / n22;
            }
            int n25;
            int n26;
            if (n17 > 0) {
                n25 = n20 / n13;
                n26 = n19 / n22;
            }
            else {
                n26 = n19 / n13;
                n25 = n20 / n22;
            }
            final int n27 = Model.anInt1685 - Texture.textureInt1;
            final int n28 = Model.anInt1686 - Texture.textureInt2;
            if (n27 > n23 && n27 < n24 && n28 > n25 && n28 < n26) {
                if (this.aBoolean1659) {
                    Model.anIntArray1688[Model.anInt1687++] = n9;
                }
                else {
                    b2 = true;
                }
            }
        }
        final int textureInt1 = Texture.textureInt1;
        final int textureInt2 = Texture.textureInt2;
        int n29 = 0;
        int n30 = 0;
        if (n != 0) {
            n29 = Model.modelIntArray1[n];
            n30 = Model.modelIntArray2[n];
        }
        for (int i = 0; i < this.anInt1626; ++i) {
            int n31 = this.anIntArray1627[i];
            final int n32 = this.anIntArray1628[i];
            int n33 = this.anIntArray1629[i];
            if (n != 0) {
                final int n34 = n33 * n29 + n31 * n30 >> 16;
                n33 = n33 * n30 - n31 * n29 >> 16;
                n31 = n34;
            }
            final int n35 = n31 + n6;
            final int n36 = n32 + n7;
            final int n37 = n33 + n8;
            final int n38 = n37 * n4 + n35 * n5 >> 16;
            final int n39 = n37 * n5 - n35 * n4 >> 16;
            final int n40 = n38;
            final int n41 = n36 * n3 - n39 * n2 >> 16;
            final int n42 = n36 * n2 + n39 * n3 >> 16;
            final int n43 = n41;
            Model.anIntArray1667[i] = n42 - n11;
            if (n42 >= 50) {
                Model.anIntArray1665[i] = textureInt1 + (n40 << 9) / n42;
                Model.anIntArray1666[i] = textureInt2 + (n43 << 9) / n42;
            }
            else {
                Model.anIntArray1665[i] = -5000;
                b = true;
            }
            if (b || this.anInt1642 > 0) {
                Model.anIntArray1668[i] = n40;
                Model.anIntArray1669[i] = n43;
                Model.anIntArray1670[i] = n42;
            }
        }
        try {
            this.method483(b, b2, n9);
        }
        catch (Exception ex) {}
    }
    
    private final void method483(final boolean b, boolean b2, final int n) {
        for (int i = 0; i < this.anInt1652; ++i) {
            Model.anIntArray1671[i] = 0;
        }
        for (int j = 0; j < this.anInt1630; ++j) {
            if (this.anIntArray1637 == null || this.anIntArray1637[j] != -1) {
                final int n2 = this.anIntArray1631[j];
                final int n3 = this.anIntArray1632[j];
                final int n4 = this.anIntArray1633[j];
                final int n5 = Model.anIntArray1665[n2];
                final int n6 = Model.anIntArray1665[n3];
                final int n7 = Model.anIntArray1665[n4];
                if (b && (n5 == -5000 || n6 == -5000 || n7 == -5000)) {
                    Model.aBooleanArray1664[j] = true;
                    final int n8 = (Model.anIntArray1667[n2] + Model.anIntArray1667[n3] + Model.anIntArray1667[n4]) / 3 + this.anInt1653;
                    Model.anIntArrayArray1672[n8][Model.anIntArray1671[n8]++] = j;
                }
                else {
                    if (b2 && this.method486(Model.anInt1685, Model.anInt1686, Model.anIntArray1666[n2], Model.anIntArray1666[n3], Model.anIntArray1666[n4], n5, n6, n7)) {
                        Model.anIntArray1688[Model.anInt1687++] = n;
                        b2 = false;
                    }
                    if ((n5 - n6) * (Model.anIntArray1666[n4] - Model.anIntArray1666[n3]) - (Model.anIntArray1666[n2] - Model.anIntArray1666[n3]) * (n7 - n6) > 0) {
                        Model.aBooleanArray1664[j] = false;
                        if (n5 < 0 || n6 < 0 || n7 < 0 || n5 > DrawingArea.centerX || n6 > DrawingArea.centerX || n7 > DrawingArea.centerX) {
                            Model.aBooleanArray1663[j] = true;
                        }
                        else {
                            Model.aBooleanArray1663[j] = false;
                        }
                        final int n9 = (Model.anIntArray1667[n2] + Model.anIntArray1667[n3] + Model.anIntArray1667[n4]) / 3 + this.anInt1653;
                        Model.anIntArrayArray1672[n9][Model.anIntArray1671[n9]++] = j;
                    }
                }
            }
        }
        if (this.anIntArray1638 == null) {
            for (int k = this.anInt1652 - 1; k >= 0; --k) {
                final int n10 = Model.anIntArray1671[k];
                if (n10 > 0) {
                    final int[] array = Model.anIntArrayArray1672[k];
                    for (int l = 0; l < n10; ++l) {
                        this.method484(array[l]);
                    }
                }
            }
            return;
        }
        for (int n11 = 0; n11 < 12; ++n11) {
            Model.anIntArray1673[n11] = 0;
            Model.anIntArray1677[n11] = 0;
        }
        for (int n12 = this.anInt1652 - 1; n12 >= 0; --n12) {
            final int n13 = Model.anIntArray1671[n12];
            if (n13 > 0) {
                for (final int n15 : Model.anIntArrayArray1672[n12]) {
                    final int n16 = this.anIntArray1638[n15];
                    final int n17 = Model.anIntArray1673[n16]++;
                    Model.anIntArrayArray1674[n16][n17] = n15;
                    if (n16 < 10) {
                        final int[] anIntArray1677 = Model.anIntArray1677;
                        final int n18 = n16;
                        anIntArray1677[n18] += n12;
                    }
                    else if (n16 == 10) {
                        Model.anIntArray1675[n17] = n12;
                    }
                    else {
                        Model.anIntArray1676[n17] = n12;
                    }
                }
            }
        }
        int n19 = 0;
        if (Model.anIntArray1673[1] > 0 || Model.anIntArray1673[2] > 0) {
            n19 = (Model.anIntArray1677[1] + Model.anIntArray1677[2]) / (Model.anIntArray1673[1] + Model.anIntArray1673[2]);
        }
        int n20 = 0;
        if (Model.anIntArray1673[3] > 0 || Model.anIntArray1673[4] > 0) {
            n20 = (Model.anIntArray1677[3] + Model.anIntArray1677[4]) / (Model.anIntArray1673[3] + Model.anIntArray1673[4]);
        }
        int n21 = 0;
        if (Model.anIntArray1673[6] > 0 || Model.anIntArray1673[8] > 0) {
            n21 = (Model.anIntArray1677[6] + Model.anIntArray1677[8]) / (Model.anIntArray1673[6] + Model.anIntArray1673[8]);
        }
        int n22 = 0;
        int n23 = Model.anIntArray1673[10];
        int[] array3 = Model.anIntArrayArray1674[10];
        int[] array4 = Model.anIntArray1675;
        if (n22 == n23) {
            n22 = 0;
            n23 = Model.anIntArray1673[11];
            array3 = Model.anIntArrayArray1674[11];
            array4 = Model.anIntArray1676;
        }
        int n24;
        if (n22 < n23) {
            n24 = array4[n22];
        }
        else {
            n24 = -1000;
        }
        for (int n25 = 0; n25 < 10; ++n25) {
            while (n25 == 0 && n24 > n19) {
                this.method484(array3[n22++]);
                if (n22 == n23 && array3 != Model.anIntArrayArray1674[11]) {
                    n22 = 0;
                    n23 = Model.anIntArray1673[11];
                    array3 = Model.anIntArrayArray1674[11];
                    array4 = Model.anIntArray1676;
                }
                if (n22 < n23) {
                    n24 = array4[n22];
                }
                else {
                    n24 = -1000;
                }
            }
            while (n25 == 3 && n24 > n20) {
                this.method484(array3[n22++]);
                if (n22 == n23 && array3 != Model.anIntArrayArray1674[11]) {
                    n22 = 0;
                    n23 = Model.anIntArray1673[11];
                    array3 = Model.anIntArrayArray1674[11];
                    array4 = Model.anIntArray1676;
                }
                if (n22 < n23) {
                    n24 = array4[n22];
                }
                else {
                    n24 = -1000;
                }
            }
            while (n25 == 5 && n24 > n21) {
                this.method484(array3[n22++]);
                if (n22 == n23 && array3 != Model.anIntArrayArray1674[11]) {
                    n22 = 0;
                    n23 = Model.anIntArray1673[11];
                    array3 = Model.anIntArrayArray1674[11];
                    array4 = Model.anIntArray1676;
                }
                if (n22 < n23) {
                    n24 = array4[n22];
                }
                else {
                    n24 = -1000;
                }
            }
            final int n26 = Model.anIntArray1673[n25];
            final int[] array5 = Model.anIntArrayArray1674[n25];
            for (int n27 = 0; n27 < n26; ++n27) {
                this.method484(array5[n27]);
            }
        }
        while (n24 != -1000) {
            this.method484(array3[n22++]);
            if (n22 == n23 && array3 != Model.anIntArrayArray1674[11]) {
                n22 = 0;
                array3 = Model.anIntArrayArray1674[11];
                n23 = Model.anIntArray1673[11];
                array4 = Model.anIntArray1676;
            }
            if (n22 < n23) {
                n24 = array4[n22];
            }
            else {
                n24 = -1000;
            }
        }
    }
    
    private final void method484(final int n) {
        if (Model.aBooleanArray1664[n]) {
            this.method485(n);
            return;
        }
        final int n2 = this.anIntArray1631[n];
        final int n3 = this.anIntArray1632[n];
        final int n4 = this.anIntArray1633[n];
        Texture.aBoolean1462 = Model.aBooleanArray1663[n];
        if (this.anIntArray1639 == null) {
            Texture.anInt1465 = 0;
        }
        else {
            Texture.anInt1465 = this.anIntArray1639[n];
        }
        int n5;
        if (this.anIntArray1637 == null) {
            n5 = 0;
        }
        else {
            n5 = (this.anIntArray1637[n] & 0x3);
        }
        if (n5 == 0) {
            Texture.method374(Model.anIntArray1666[n2], Model.anIntArray1666[n3], Model.anIntArray1666[n4], Model.anIntArray1665[n2], Model.anIntArray1665[n3], Model.anIntArray1665[n4], this.anIntArray1634[n], this.anIntArray1635[n], this.anIntArray1636[n]);
            return;
        }
        if (n5 == 1) {
            Texture.method376(Model.anIntArray1666[n2], Model.anIntArray1666[n3], Model.anIntArray1666[n4], Model.anIntArray1665[n2], Model.anIntArray1665[n3], Model.anIntArray1665[n4], Model.modelIntArray3[this.anIntArray1634[n]]);
            return;
        }
        if (n5 == 2) {
            final int n6 = this.anIntArray1637[n] >> 2;
            final int n7 = this.anIntArray1643[n6];
            final int n8 = this.anIntArray1644[n6];
            final int n9 = this.anIntArray1645[n6];
            Texture.method378(Model.anIntArray1666[n2], Model.anIntArray1666[n3], Model.anIntArray1666[n4], Model.anIntArray1665[n2], Model.anIntArray1665[n3], Model.anIntArray1665[n4], this.anIntArray1634[n], this.anIntArray1635[n], this.anIntArray1636[n], Model.anIntArray1668[n7], Model.anIntArray1668[n8], Model.anIntArray1668[n9], Model.anIntArray1669[n7], Model.anIntArray1669[n8], Model.anIntArray1669[n9], Model.anIntArray1670[n7], Model.anIntArray1670[n8], Model.anIntArray1670[n9], this.anIntArray1640[n]);
            return;
        }
        if (n5 == 3) {
            final int n10 = this.anIntArray1637[n] >> 2;
            final int n11 = this.anIntArray1643[n10];
            final int n12 = this.anIntArray1644[n10];
            final int n13 = this.anIntArray1645[n10];
            Texture.method378(Model.anIntArray1666[n2], Model.anIntArray1666[n3], Model.anIntArray1666[n4], Model.anIntArray1665[n2], Model.anIntArray1665[n3], Model.anIntArray1665[n4], this.anIntArray1634[n], this.anIntArray1634[n], this.anIntArray1634[n], Model.anIntArray1668[n11], Model.anIntArray1668[n12], Model.anIntArray1668[n13], Model.anIntArray1669[n11], Model.anIntArray1669[n12], Model.anIntArray1669[n13], Model.anIntArray1670[n11], Model.anIntArray1670[n12], Model.anIntArray1670[n13], this.anIntArray1640[n]);
        }
    }
    
    private final void method485(final int n) {
        if (this.anIntArray1640 != null && this.anIntArray1640[n] == 65535) {
            return;
        }
        final int textureInt1 = Texture.textureInt1;
        final int textureInt2 = Texture.textureInt2;
        int n2 = 0;
        final int n3 = this.anIntArray1631[n];
        final int n4 = this.anIntArray1632[n];
        final int n5 = this.anIntArray1633[n];
        final int n6 = Model.anIntArray1670[n3];
        final int n7 = Model.anIntArray1670[n4];
        final int n8 = Model.anIntArray1670[n5];
        if (n6 >= 50) {
            Model.anIntArray1678[n2] = Model.anIntArray1665[n3];
            Model.anIntArray1679[n2] = Model.anIntArray1666[n3];
            Model.anIntArray1680[n2++] = this.anIntArray1634[n];
        }
        else {
            final int n9 = Model.anIntArray1668[n3];
            final int n10 = Model.anIntArray1669[n3];
            final int n11 = this.anIntArray1634[n];
            if (n8 >= 50) {
                final int n12 = (50 - n6) * Model.modelIntArray4[n8 - n6];
                Model.anIntArray1678[n2] = textureInt1 + (n9 + ((Model.anIntArray1668[n5] - n9) * n12 >> 16) << 9) / 50;
                Model.anIntArray1679[n2] = textureInt2 + (n10 + ((Model.anIntArray1669[n5] - n10) * n12 >> 16) << 9) / 50;
                Model.anIntArray1680[n2++] = n11 + ((this.anIntArray1636[n] - n11) * n12 >> 16);
            }
            if (n7 >= 50) {
                final int n13 = (50 - n6) * Model.modelIntArray4[n7 - n6];
                Model.anIntArray1678[n2] = textureInt1 + (n9 + ((Model.anIntArray1668[n4] - n9) * n13 >> 16) << 9) / 50;
                Model.anIntArray1679[n2] = textureInt2 + (n10 + ((Model.anIntArray1669[n4] - n10) * n13 >> 16) << 9) / 50;
                Model.anIntArray1680[n2++] = n11 + ((this.anIntArray1635[n] - n11) * n13 >> 16);
            }
        }
        if (n7 >= 50) {
            Model.anIntArray1678[n2] = Model.anIntArray1665[n4];
            Model.anIntArray1679[n2] = Model.anIntArray1666[n4];
            Model.anIntArray1680[n2++] = this.anIntArray1635[n];
        }
        else {
            final int n14 = Model.anIntArray1668[n4];
            final int n15 = Model.anIntArray1669[n4];
            final int n16 = this.anIntArray1635[n];
            if (n6 >= 50) {
                final int n17 = (50 - n7) * Model.modelIntArray4[n6 - n7];
                Model.anIntArray1678[n2] = textureInt1 + (n14 + ((Model.anIntArray1668[n3] - n14) * n17 >> 16) << 9) / 50;
                Model.anIntArray1679[n2] = textureInt2 + (n15 + ((Model.anIntArray1669[n3] - n15) * n17 >> 16) << 9) / 50;
                Model.anIntArray1680[n2++] = n16 + ((this.anIntArray1634[n] - n16) * n17 >> 16);
            }
            if (n8 >= 50) {
                final int n18 = (50 - n7) * Model.modelIntArray4[n8 - n7];
                Model.anIntArray1678[n2] = textureInt1 + (n14 + ((Model.anIntArray1668[n5] - n14) * n18 >> 16) << 9) / 50;
                Model.anIntArray1679[n2] = textureInt2 + (n15 + ((Model.anIntArray1669[n5] - n15) * n18 >> 16) << 9) / 50;
                Model.anIntArray1680[n2++] = n16 + ((this.anIntArray1636[n] - n16) * n18 >> 16);
            }
        }
        if (n8 >= 50) {
            Model.anIntArray1678[n2] = Model.anIntArray1665[n5];
            Model.anIntArray1679[n2] = Model.anIntArray1666[n5];
            Model.anIntArray1680[n2++] = this.anIntArray1636[n];
        }
        else {
            final int n19 = Model.anIntArray1668[n5];
            final int n20 = Model.anIntArray1669[n5];
            final int n21 = this.anIntArray1636[n];
            if (n7 >= 50) {
                final int n22 = (50 - n8) * Model.modelIntArray4[n7 - n8];
                Model.anIntArray1678[n2] = textureInt1 + (n19 + ((Model.anIntArray1668[n4] - n19) * n22 >> 16) << 9) / 50;
                Model.anIntArray1679[n2] = textureInt2 + (n20 + ((Model.anIntArray1669[n4] - n20) * n22 >> 16) << 9) / 50;
                Model.anIntArray1680[n2++] = n21 + ((this.anIntArray1635[n] - n21) * n22 >> 16);
            }
            if (n6 >= 50) {
                final int n23 = (50 - n8) * Model.modelIntArray4[n6 - n8];
                Model.anIntArray1678[n2] = textureInt1 + (n19 + ((Model.anIntArray1668[n3] - n19) * n23 >> 16) << 9) / 50;
                Model.anIntArray1679[n2] = textureInt2 + (n20 + ((Model.anIntArray1669[n3] - n20) * n23 >> 16) << 9) / 50;
                Model.anIntArray1680[n2++] = n21 + ((this.anIntArray1634[n] - n21) * n23 >> 16);
            }
        }
        final int n24 = Model.anIntArray1678[0];
        final int n25 = Model.anIntArray1678[1];
        final int n26 = Model.anIntArray1678[2];
        final int n27 = Model.anIntArray1679[0];
        final int n28 = Model.anIntArray1679[1];
        final int n29 = Model.anIntArray1679[2];
        if ((n24 - n25) * (n29 - n28) - (n27 - n28) * (n26 - n25) > 0) {
            Texture.aBoolean1462 = false;
            if (n2 == 3) {
                if (n24 < 0 || n25 < 0 || n26 < 0 || n24 > DrawingArea.centerX || n25 > DrawingArea.centerX || n26 > DrawingArea.centerX) {
                    Texture.aBoolean1462 = true;
                }
                int n30;
                if (this.anIntArray1637 == null) {
                    n30 = 0;
                }
                else {
                    n30 = (this.anIntArray1637[n] & 0x3);
                }
                if (n30 == 0) {
                    Texture.method374(n27, n28, n29, n24, n25, n26, Model.anIntArray1680[0], Model.anIntArray1680[1], Model.anIntArray1680[2]);
                }
                else if (n30 == 1) {
                    Texture.method376(n27, n28, n29, n24, n25, n26, Model.modelIntArray3[this.anIntArray1634[n]]);
                }
                else if (n30 == 2) {
                    final int n31 = this.anIntArray1637[n] >> 2;
                    final int n32 = this.anIntArray1643[n31];
                    final int n33 = this.anIntArray1644[n31];
                    final int n34 = this.anIntArray1645[n31];
                    Texture.method378(n27, n28, n29, n24, n25, n26, Model.anIntArray1680[0], Model.anIntArray1680[1], Model.anIntArray1680[2], Model.anIntArray1668[n32], Model.anIntArray1668[n33], Model.anIntArray1668[n34], Model.anIntArray1669[n32], Model.anIntArray1669[n33], Model.anIntArray1669[n34], Model.anIntArray1670[n32], Model.anIntArray1670[n33], Model.anIntArray1670[n34], this.anIntArray1640[n]);
                }
                else if (n30 == 3) {
                    final int n35 = this.anIntArray1637[n] >> 2;
                    final int n36 = this.anIntArray1643[n35];
                    final int n37 = this.anIntArray1644[n35];
                    final int n38 = this.anIntArray1645[n35];
                    Texture.method378(n27, n28, n29, n24, n25, n26, this.anIntArray1634[n], this.anIntArray1634[n], this.anIntArray1634[n], Model.anIntArray1668[n36], Model.anIntArray1668[n37], Model.anIntArray1668[n38], Model.anIntArray1669[n36], Model.anIntArray1669[n37], Model.anIntArray1669[n38], Model.anIntArray1670[n36], Model.anIntArray1670[n37], Model.anIntArray1670[n38], this.anIntArray1640[n]);
                }
            }
            if (n2 == 4) {
                if (n24 < 0 || n25 < 0 || n26 < 0 || n24 > DrawingArea.centerX || n25 > DrawingArea.centerX || n26 > DrawingArea.centerX || Model.anIntArray1678[3] < 0 || Model.anIntArray1678[3] > DrawingArea.centerX) {
                    Texture.aBoolean1462 = true;
                }
                int n39;
                if (this.anIntArray1637 == null) {
                    n39 = 0;
                }
                else {
                    n39 = (this.anIntArray1637[n] & 0x3);
                }
                if (n39 == 0) {
                    Texture.method374(n27, n28, n29, n24, n25, n26, Model.anIntArray1680[0], Model.anIntArray1680[1], Model.anIntArray1680[2]);
                    Texture.method374(n27, n29, Model.anIntArray1679[3], n24, n26, Model.anIntArray1678[3], Model.anIntArray1680[0], Model.anIntArray1680[2], Model.anIntArray1680[3]);
                    return;
                }
                if (n39 == 1) {
                    final int n40 = Model.modelIntArray3[this.anIntArray1634[n]];
                    Texture.method376(n27, n28, n29, n24, n25, n26, n40);
                    Texture.method376(n27, n29, Model.anIntArray1679[3], n24, n26, Model.anIntArray1678[3], n40);
                    return;
                }
                if (n39 == 2) {
                    final int n41 = this.anIntArray1637[n] >> 2;
                    final int n42 = this.anIntArray1643[n41];
                    final int n43 = this.anIntArray1644[n41];
                    final int n44 = this.anIntArray1645[n41];
                    Texture.method378(n27, n28, n29, n24, n25, n26, Model.anIntArray1680[0], Model.anIntArray1680[1], Model.anIntArray1680[2], Model.anIntArray1668[n42], Model.anIntArray1668[n43], Model.anIntArray1668[n44], Model.anIntArray1669[n42], Model.anIntArray1669[n43], Model.anIntArray1669[n44], Model.anIntArray1670[n42], Model.anIntArray1670[n43], Model.anIntArray1670[n44], this.anIntArray1640[n]);
                    Texture.method378(n27, n29, Model.anIntArray1679[3], n24, n26, Model.anIntArray1678[3], Model.anIntArray1680[0], Model.anIntArray1680[2], Model.anIntArray1680[3], Model.anIntArray1668[n42], Model.anIntArray1668[n43], Model.anIntArray1668[n44], Model.anIntArray1669[n42], Model.anIntArray1669[n43], Model.anIntArray1669[n44], Model.anIntArray1670[n42], Model.anIntArray1670[n43], Model.anIntArray1670[n44], this.anIntArray1640[n]);
                    return;
                }
                if (n39 == 3) {
                    final int n45 = this.anIntArray1637[n] >> 2;
                    final int n46 = this.anIntArray1643[n45];
                    final int n47 = this.anIntArray1644[n45];
                    final int n48 = this.anIntArray1645[n45];
                    Texture.method378(n27, n28, n29, n24, n25, n26, this.anIntArray1634[n], this.anIntArray1634[n], this.anIntArray1634[n], Model.anIntArray1668[n46], Model.anIntArray1668[n47], Model.anIntArray1668[n48], Model.anIntArray1669[n46], Model.anIntArray1669[n47], Model.anIntArray1669[n48], Model.anIntArray1670[n46], Model.anIntArray1670[n47], Model.anIntArray1670[n48], this.anIntArray1640[n]);
                    Texture.method378(n27, n29, Model.anIntArray1679[3], n24, n26, Model.anIntArray1678[3], this.anIntArray1634[n], this.anIntArray1634[n], this.anIntArray1634[n], Model.anIntArray1668[n46], Model.anIntArray1668[n47], Model.anIntArray1668[n48], Model.anIntArray1669[n46], Model.anIntArray1669[n47], Model.anIntArray1669[n48], Model.anIntArray1670[n46], Model.anIntArray1670[n47], Model.anIntArray1670[n48], this.anIntArray1640[n]);
                }
            }
        }
    }
    
    private final boolean method486(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return (n2 >= n3 || n2 >= n4 || n2 >= n5) && (n2 <= n3 || n2 <= n4 || n2 <= n5) && (n >= n6 || n >= n7 || n >= n8) && (n <= n6 || n <= n7 || n <= n8);
    }
    
    static {
        Model.ccString = "Cla";
        Model.xxString = "at Cl";
        Model.vvString = "nt";
        Model.aString9_9 = "" + Model.ccString + "n Ch" + Model.xxString + "ie" + Model.vvString + " ";
        Model.anInt1619 = -192;
        Model.aModel_1621 = new Model(true);
        Model.anIntArray1622 = new int[2000];
        Model.anIntArray1623 = new int[2000];
        Model.anIntArray1624 = new int[2000];
        Model.anIntArray1625 = new int[2000];
        Model.aBooleanArray1663 = new boolean[8000];
        Model.aBooleanArray1664 = new boolean[8000];
        Model.anIntArray1665 = new int[8000];
        Model.anIntArray1666 = new int[8000];
        Model.anIntArray1667 = new int[8000];
        Model.anIntArray1668 = new int[8000];
        Model.anIntArray1669 = new int[8000];
        Model.anIntArray1670 = new int[8000];
        Model.anIntArray1671 = new int[1500];
        Model.anIntArrayArray1672 = new int[1500][512];
        Model.anIntArray1673 = new int[12];
        Model.anIntArrayArray1674 = new int[12][2000];
        Model.anIntArray1675 = new int[2000];
        Model.anIntArray1676 = new int[2000];
        Model.anIntArray1677 = new int[12];
        Model.anIntArray1678 = new int[10];
        Model.anIntArray1679 = new int[10];
        Model.anIntArray1680 = new int[10];
        Model.anIntArray1688 = new int[1000];
        Model.modelIntArray1 = Texture.anIntArray1470;
        Model.modelIntArray2 = Texture.anIntArray1471;
        Model.modelIntArray3 = Texture.anIntArray1482;
        Model.modelIntArray4 = Texture.anIntArray1469;
    }
}

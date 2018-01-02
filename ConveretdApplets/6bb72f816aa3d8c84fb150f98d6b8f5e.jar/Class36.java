// 
// Decompiled by Procyon v0.5.30
// 

public final class Class36
{
    private static Class36[][] animationlist;
    public int anInt636;
    public Class18 aClass18_637;
    public int anInt638;
    public int[] anIntArray639;
    public int[] anIntArray640;
    public int[] anIntArray641;
    public int[] anIntArray642;
    private static boolean[] aBooleanArray643;
    
    public static byte[] getData(final int n, final int n2) {
        if (n == 0) {
            return FileOperations.ReadFile(SignLink.findcachedir() + "/Data/Animation/ef/" + n2 + ".dat");
        }
        return FileOperations.ReadFile(SignLink.findcachedir() + "/Data/Animation/es/" + n2 + ".dat");
    }
    
    public static void method528(final int n) {
        Class36.animationlist = new Class36[4000][0];
    }
    
    public static void load2(final int n) {
        try {
            final Stream stream = new Stream(getData(0, n));
            final Class18 aClass18_637 = new Class18(new Stream(getData(1, n)), 0);
            final int unsignedWord = stream.readUnsignedWord();
            Class36.animationlist[n] = new Class36[(int)(unsignedWord * 3.0)];
            final int[] array = new int[500];
            final int[] array2 = new int[500];
            final int[] array3 = new int[500];
            final int[] array4 = new int[500];
            for (int i = 0; i < unsignedWord; ++i) {
                final int unsignedWord2 = stream.readUnsignedWord();
                final Class36[] array5 = Class36.animationlist[n];
                final int n2 = unsignedWord2;
                final Class36 class36 = new Class36();
                array5[n2] = class36;
                final Class36 class37 = class36;
                class37.aClass18_637 = aClass18_637;
                final int unsignedByte = stream.readUnsignedByte();
                int anInt638 = 0;
                int n3 = -1;
                for (int j = 0; j < unsignedByte; ++j) {
                    final int unsignedByte2 = stream.readUnsignedByte();
                    if (unsignedByte2 > 0) {
                        if (aClass18_637.anIntArray342[j] != 0) {
                            for (int k = j - 1; k > n3; --k) {
                                if (aClass18_637.anIntArray342[k] == 0) {
                                    array[anInt638] = k;
                                    array2[anInt638] = 0;
                                    array4[anInt638] = (array3[anInt638] = 0);
                                    ++anInt638;
                                    break;
                                }
                            }
                        }
                        array[anInt638] = j;
                        int n4 = 0;
                        if (aClass18_637.anIntArray342[j] == 3) {
                            n4 = 128;
                        }
                        if ((unsignedByte2 & 0x1) != 0x0) {
                            array2[anInt638] = (short)stream.readShort2();
                        }
                        else {
                            array2[anInt638] = n4;
                        }
                        if ((unsignedByte2 & 0x2) != 0x0) {
                            array3[anInt638] = stream.readShort2();
                        }
                        else {
                            array3[anInt638] = n4;
                        }
                        if ((unsignedByte2 & 0x4) != 0x0) {
                            array4[anInt638] = stream.readShort2();
                        }
                        else {
                            array4[anInt638] = n4;
                        }
                        n3 = j;
                        ++anInt638;
                    }
                }
                class37.anInt638 = anInt638;
                class37.anIntArray639 = new int[anInt638];
                class37.anIntArray640 = new int[anInt638];
                class37.anIntArray641 = new int[anInt638];
                class37.anIntArray642 = new int[anInt638];
                for (int l = 0; l < anInt638; ++l) {
                    class37.anIntArray639[l] = array[l];
                    class37.anIntArray640[l] = array2[l];
                    class37.anIntArray641[l] = array3[l];
                    class37.anIntArray642[l] = array4[l];
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static void load(final int n) {
        try {
            final Stream stream = new Stream(DataBase.allFrames[n]);
            final Class18 aClass18_637 = new Class18(new Stream(DataBase.allSkinlist[n]), 0);
            final int unsignedWord = stream.readUnsignedWord();
            Class36.animationlist[n] = new Class36[(int)(unsignedWord * 1.5)];
            final int[] array = new int[500];
            final int[] array2 = new int[500];
            final int[] array3 = new int[500];
            final int[] array4 = new int[500];
            for (int i = 0; i < unsignedWord; ++i) {
                final int unsignedWord2 = stream.readUnsignedWord();
                final Class36[] array5 = Class36.animationlist[n];
                final int n2 = unsignedWord2;
                final Class36 class36 = new Class36();
                array5[n2] = class36;
                final Class36 class37 = class36;
                class37.aClass18_637 = aClass18_637;
                final int unsignedByte = stream.readUnsignedByte();
                int anInt638 = 0;
                int n3 = -1;
                for (int j = 0; j < unsignedByte; ++j) {
                    final int unsignedByte2 = stream.readUnsignedByte();
                    if (unsignedByte2 > 0) {
                        if (aClass18_637.anIntArray342[j] != 0) {
                            for (int k = j - 1; k > n3; --k) {
                                if (aClass18_637.anIntArray342[k] == 0) {
                                    array[anInt638] = k;
                                    array2[anInt638] = 0;
                                    array4[anInt638] = (array3[anInt638] = 0);
                                    ++anInt638;
                                    break;
                                }
                            }
                        }
                        array[anInt638] = j;
                        int n4 = 0;
                        if (aClass18_637.anIntArray342[j] == 3) {
                            n4 = 128;
                        }
                        if ((unsignedByte2 & 0x1) != 0x0) {
                            array2[anInt638] = (short)stream.readShort2();
                        }
                        else {
                            array2[anInt638] = n4;
                        }
                        if ((unsignedByte2 & 0x2) != 0x0) {
                            array3[anInt638] = stream.readShort2();
                        }
                        else {
                            array3[anInt638] = n4;
                        }
                        if ((unsignedByte2 & 0x4) != 0x0) {
                            array4[anInt638] = stream.readShort2();
                        }
                        else {
                            array4[anInt638] = n4;
                        }
                        n3 = j;
                        ++anInt638;
                    }
                }
                class37.anInt638 = anInt638;
                class37.anIntArray639 = new int[anInt638];
                class37.anIntArray640 = new int[anInt638];
                class37.anIntArray641 = new int[anInt638];
                class37.anIntArray642 = new int[anInt638];
                for (int l = 0; l < anInt638; ++l) {
                    class37.anIntArray639[l] = array[l];
                    class37.anIntArray640[l] = array2[l];
                    class37.anIntArray641[l] = array3[l];
                    class37.anIntArray642[l] = array4[l];
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static Class36 method531(final int n) {
        if (Class36.animationlist == null) {
            return null;
        }
        final String hexString = Integer.toHexString(n);
        final int int1 = Integer.parseInt(hexString.substring(0, hexString.length() - 4), 16);
        final int int2 = Integer.parseInt(hexString.substring(hexString.length() - 4), 16);
        if (Class36.animationlist[int1].length == 0) {
            load(int1);
            load2(int1);
        }
        return Class36.animationlist[int1][int2];
    }
    
    public static void nullLoader() {
        Class36.animationlist = null;
    }
    
    public static boolean method532(final int n) {
        return n == -1;
    }
}

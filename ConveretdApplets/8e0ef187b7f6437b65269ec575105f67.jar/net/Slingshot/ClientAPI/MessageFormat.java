// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

class MessageFormat
{
    public static final int shortFieldSize = 2;
    public static final int longFieldSize = 4;
    public static final int doubleFieldSize = 8;
    public static final int dateFieldSize = 4;
    public static final int timeFieldSize = 4;
    public static final int minSizeEnvelope = 5;
    public static byte noValueBit;
    public static byte arrayBit;
    public static byte partialBit;
    public static byte updateBit;
    public static byte typeOnly;
    public static final byte shortType = 1;
    public static final byte ushortType = 2;
    public static final byte longType = 3;
    public static final byte ulongType = 4;
    public static final byte doubleType = 5;
    public static final byte dateType = 6;
    public static final byte timeType = 7;
    public static final byte priceType = 8;
    public static final byte stringType = 9;
    public static final byte binaryType = 10;
    public static final byte envelopeType = 11;
    public static final short monitorRecord = 1000;
    public static final short snapRecord = 1001;
    public static final short RefreshRecord = 1002;
    public static final short unmonitorRecord = 1003;
    public static final short update = 1004;
    public static final short requestStatus = 1005;
    public static final short insertStatus = 1006;
    public static final short snap = 1007;
    public static final short publishRecordFull = -16;
    public static final short publishPageFull = -17;
    public static final short publishRecordUpdate = -18;
    public static final short publishPageUpdate = -19;
    public static final short requestRecord = -20;
    public static final short serviceString = -21;
    public static final short keyObjectString = -22;
    public static final short requestType = -25;
    public static final short requestFids = -68;
    public static final short dataEnvelope = -24;
    public static final short connectMessage = -69;
    public static final short connectionSignature = -70;
    public static final short connectionId = -71;
    public static final short pingMessage = -66;
    public static final short pingString = -67;
    public static final short requestRecordStatus = -45;
    public static final short bufferedProxy = -72;
    public static final short requestStatusId = -46;
    public static final short dropRequest = -73;
    public static final short insertSequence = -74;
    public static final short insertFid = -75;
    public static final short insertResponse = -76;
    public static final short insertResponseTxt = -77;
    public static final short dropStatusMsg = -56;
    public static final short helloMessage = -78;
    public static final short helloString = -79;
    public static final short pingTime = -80;
    public static final short changeKeyObjectString = -81;
    public static final short requestFlags = -82;
    public static final short connectionFlags = -83;
    
    public static boolean isaArray(final byte b) {
        return (b & MessageFormat.arrayBit) == MessageFormat.arrayBit;
    }
    
    public static byte getTypeOnly(final byte b) {
        return (byte)(b & 0xF);
    }
    
    public static boolean isNoValue(final byte b) {
        return (b & MessageFormat.noValueBit) == MessageFormat.noValueBit;
    }
    
    public static boolean isPartial(final byte b) {
        return (b & MessageFormat.partialBit) == MessageFormat.partialBit;
    }
    
    public static boolean isMissed(final byte b) {
        return (b & MessageFormat.updateBit) == MessageFormat.updateBit;
    }
    
    public static int setEnvelope(final short n, final byte b, final short n2, final short n3, final byte[] array, int n4) {
        n4 = nativeToShort(n, array, n4);
        array[n4++] = b;
        if (isaArray(b)) {
            n4 = nativeToShort(n3, array, n4);
        }
        return nativeToShort(n2, array, n4);
    }
    
    public static int nativeToLong(final long n, final byte[] array, int n2) {
        array[n2++] = (byte)(n & 0xFFL);
        array[n2++] = (byte)(n >> 8 & 0xFFL);
        array[n2++] = (byte)(n >> 16 & 0xFFL);
        array[n2++] = (byte)(n >> 24 & 0xFFL);
        return n2;
    }
    
    public static int nativeToShort(final short n, final byte[] array, int n2) {
        array[n2++] = (byte)(n & 0xFF);
        array[n2++] = (byte)(n >> 8);
        return n2;
    }
    
    public static short shortToNative(final byte[] array, int n) {
        return (short)((array[n++] & 0xFF) + (array[n++] << 8));
    }
    
    public static long longToNative(final byte[] array, int n) {
        return (array[n++] & 0xFF) + (array[n++] << 8 & 0xFF00) + (array[n++] << 16 & 0xFF0000) + (array[n++] << 24 & 0xFF000000);
    }
    
    public static int packAny(final short n, final byte b, short n2, final short n3, final short n4, final byte[] array, final byte[] array2, int n5) {
        n5 = nativeToShort(n, array2, n5);
        array2[n5++] = b;
        if (isaArray(b)) {
            n5 = nativeToShort(n4, array2, n5);
        }
        switch (getTypeOnly(b)) {
            case 1:
            case 2: {
                n2 = 2;
                break;
            }
            case 3:
            case 4: {
                n2 = 4;
                break;
            }
            case 5: {
                n2 = 8;
                break;
            }
            case 6: {
                n2 = 4;
                break;
            }
            case 7: {
                n2 = 4;
                break;
            }
            default: {
                if (isPartial(b)) {
                    n5 = nativeToShort((short)(n2 + 2), array2, n5);
                    n5 = nativeToShort(n2, array2, n5);
                    break;
                }
                n5 = nativeToShort(n2, array2, n5);
                break;
            }
        }
        if (!isNoValue(b)) {
            System.arraycopy(array, 0, array2, n5, n2);
            n5 += n2;
        }
        return n5;
    }
    
    public static int packShort(final short n, final short n2, final short n3, final byte[] array, final int n4) {
        byte b = 1;
        if (n2 > 0) {
            b &= MessageFormat.arrayBit;
        }
        int n5 = nativeToShort(n, array, n4);
        array[n5++] = b;
        if (n2 > 0) {
            n5 = nativeToShort(n2, array, n5);
        }
        return nativeToShort(n3, array, n5);
    }
    
    public static int packLong(final short n, final short n2, final long n3, final byte[] array, final int n4) {
        byte b = 3;
        if (n2 > 0) {
            b &= MessageFormat.arrayBit;
        }
        int n5 = nativeToShort(n, array, n4);
        array[n5++] = b;
        if (n2 > 0) {
            n5 = nativeToShort(n2, array, n5);
        }
        return nativeToLong(n3, array, n5);
    }
    
    static {
        MessageFormat.noValueBit = 64;
        MessageFormat.arrayBit = -128;
        MessageFormat.partialBit = 32;
        MessageFormat.updateBit = 16;
        MessageFormat.typeOnly = 15;
    }
}

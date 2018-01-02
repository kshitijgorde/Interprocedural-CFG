// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.PageAccess;
import java.util.Random;
import jstella.core.JSException;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import jstella.core.JSConsole;
import jstella.core.JSSystem;
import java.io.Serializable;
import jstella.core.IfcDevice;

public abstract class Cartridge implements IfcDevice, Serializable
{
    private static final long serialVersionUID = 7979700597113264401L;
    public static final String TYPE_2K = "2K";
    public static final String TYPE_4K = "4K";
    public static final String TYPE_F8 = "F8";
    public static final String TYPE_F8SWAPPED = "F8 swapped";
    public static final String TYPE_F8SC = "F8SC";
    public static final String TYPE_FASC = "FASC";
    public static final String TYPE_F6 = "F6";
    public static final String TYPE_F6SC = "F6SC";
    public static final String TYPE_F4 = "F4";
    public static final String TYPE_FE = "FE";
    public static final String TYPE_DPC = "DPC";
    public static final String TYPE_E0 = "E0";
    public static final String TYPE_E7 = "E7";
    public static final String TYPE_3F = "3F";
    public static final String TYPE_F4SC = "F4SC";
    protected JSSystem mySystem;
    protected boolean myBankLocked;
    protected String myMD5;
    protected JSConsole myConsole;
    
    protected Cartridge() {
        this.mySystem = null;
        this.myBankLocked = false;
        this.myMD5 = "";
        this.myConsole = null;
        this.unlockBank();
    }
    
    public void lockBank() {
        this.myBankLocked = true;
    }
    
    public void unlockBank() {
        this.myBankLocked = false;
    }
    
    public String getMD5() {
        return this.myMD5;
    }
    
    public void setMD5(final String aMD5) {
        this.myMD5 = aMD5;
    }
    
    protected abstract int[] getImage();
    
    protected abstract void setCurrentBank(final int p0);
    
    protected abstract int getCurrentBank();
    
    protected abstract int bankCount();
    
    public abstract boolean patch(final int p0, final int p1);
    
    public void setConsole(final JSConsole aConsole) {
        this.myConsole = aConsole;
    }
    
    public void systemCyclesReset() {
    }
    
    public static int[] toIntArray(final byte[] aByteArray) {
        final int[] zReturn = new int[aByteArray.length];
        for (int i = 0; i < zReturn.length; ++i) {
            zReturn[i] = (aByteArray[i] & 0xFF);
        }
        return zReturn;
    }
    
    public static byte[] toByteArray(final int[] aIntArray) {
        final byte[] zReturn = new byte[aIntArray.length];
        for (int i = 0; i < zReturn.length; ++i) {
            zReturn[i] = (byte)aIntArray[i];
        }
        return zReturn;
    }
    
    public static String calculateMD5(final byte[] aData) {
        String zReturn = "";
        try {
            final MessageDigest zMD = MessageDigest.getInstance("MD5");
            zMD.update(aData);
            final byte[] zSum = zMD.digest();
            final BigInteger zBig = new BigInteger(1, zSum);
            zReturn = zBig.toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return zReturn;
    }
    
    public static Cartridge create(final byte[] image) throws JSException {
        final String zMD5 = calculateMD5(image);
        System.out.println("LOADING ROM : " + zMD5);
        Cartridge cartridge = null;
        String zType = detectTypeByMD5(zMD5);
        if (zType == null) {
            zType = detectTypeByImage(image);
        }
        System.out.println("JStella - detected cartridge type: " + zType);
        cartridge = create(image, zType);
        return cartridge;
    }
    
    public static Cartridge create(final byte[] image, final String aType) throws JSException {
        Cartridge zCart = null;
        final String zUCType = aType.toUpperCase();
        final int[] zIntImage = toIntArray(image);
        if (zUCType.equals("2K".toUpperCase())) {
            zCart = new Cartridge2K(zIntImage);
        }
        else if (zUCType.equals("4K".toUpperCase())) {
            zCart = new Cartridge4K(zIntImage);
        }
        else if (zUCType.equals("F8".toUpperCase())) {
            zCart = new CartridgeF8(zIntImage, false);
        }
        else if (zUCType.equals("F8 swapped".toUpperCase())) {
            zCart = new CartridgeF8(zIntImage, true);
        }
        else if (zUCType.equals("F8SC".toUpperCase())) {
            zCart = new CartridgeF8SC(zIntImage);
        }
        else if (zUCType.equals("F6".toUpperCase())) {
            zCart = new CartridgeF6(zIntImage);
        }
        else if (zUCType.equals("F6SC".toUpperCase())) {
            zCart = new CartridgeF6SC(zIntImage);
        }
        else if (zUCType.equals("F4SC".toUpperCase())) {
            zCart = new CartridgeF4SC(zIntImage);
        }
        else if (zUCType.equals("FE".toUpperCase())) {
            zCart = new CartridgeFE(zIntImage);
        }
        else if (zUCType.equals("DPC".toUpperCase())) {
            zCart = new CartridgeDPC(zIntImage);
        }
        else if (zUCType.equals("E0".toUpperCase())) {
            zCart = new CartridgeE0(zIntImage);
        }
        else if (zUCType.equals("E7".toUpperCase())) {
            zCart = new CartridgeE7(zIntImage);
        }
        else if (zUCType.equals("3F".toUpperCase())) {
            zCart = new Cartridge3F(zIntImage);
        }
        else if (zUCType.equals("F4".toUpperCase())) {
            zCart = new CartridgeF4(zIntImage);
        }
        else {
            if (!zUCType.equals("FASC".toUpperCase())) {
                final String zMsg = "JStella does not yet support Cartridge Type " + aType + ".";
                throw new JSException(JSException.ExceptionType.CART_NOT_SUPPORTED, zMsg);
            }
            zCart = new CartridgeFASC(zIntImage);
        }
        final String zMD5 = calculateMD5(image);
        zCart.setMD5(zMD5);
        return zCart;
    }
    
    private static boolean arrayCompare(final byte[] aArray, final int aIndexA, final int aIndexB, final int aCompCount) {
        boolean zReturn = true;
        for (int i = 0; i < aCompCount; ++i) {
            if (aArray[aIndexA + i] != aArray[aIndexB + i]) {
                zReturn = false;
                break;
            }
        }
        return zReturn;
    }
    
    private static String detectTypeByMD5(String aMD5) {
        String zReturn = null;
        aMD5 = aMD5.toLowerCase().trim();
        if (aMD5.equals("bc24440b59092559a1ec26055fd1270e")) {
            zReturn = "F8 swapped";
        }
        else if (aMD5.equals("75ee371ccfc4f43e7d9b8f24e1266b55")) {
            zReturn = "F8 swapped";
        }
        else if (aMD5.equals("6dda84fb8e442ecf34241ac0d1d91d69")) {
            zReturn = "F6SC";
        }
        return zReturn;
    }
    
    private static String detectTypeByImage(final byte[] image) {
        String type = "";
        final int size = image.length;
        if (size % 8448 == 0) {
            type = "AR";
        }
        else if (size == 2048 || (size == 4096 && arrayCompare(image, 0, 2048, 2048))) {
            if (isProbablyCV(image, size)) {
                type = "CV";
            }
            else {
                type = "2K";
            }
        }
        else if (size == 4096) {
            if (isProbablyCV(image, size)) {
                type = "CV";
            }
            else {
                type = "4K";
            }
        }
        else if (size == 8192) {
            if (isProbablySC(image, size)) {
                type = "F8SC";
            }
            else if (arrayCompare(image, 0, 4096, 4096)) {
                type = "4K";
            }
            else if (isProbablyE0(image, size)) {
                type = "E0";
            }
            else if (isProbably3E(image, size)) {
                type = "3E";
            }
            else if (isProbably3F(image, size)) {
                type = "3F";
            }
            else if (isProbablyUA(image, size)) {
                type = "UA";
            }
            else if (isProbablyFE(image, size)) {
                type = "FE";
            }
            else {
                type = "F8";
            }
        }
        else if (size == 10495 || size == 10496 || size == 10240) {
            type = "DPC";
        }
        else if (size == 12288) {
            type = "FASC";
        }
        else if (size == 16384) {
            if (isProbablySC(image, size)) {
                type = "F6SC";
            }
            else if (isProbablyE7(image, size)) {
                type = "E7";
            }
            else if (isProbably3E(image, size)) {
                type = "3E";
            }
            else if (isProbably3F(image, size)) {
                type = "3F";
            }
            else {
                type = "F6";
            }
        }
        else if (size == 32768) {
            if (isProbablySC(image, size)) {
                type = "F4SC";
            }
            else if (isProbably3E(image, size)) {
                type = "3E";
            }
            else if (isProbably3F(image, size)) {
                type = "3F";
            }
            else {
                type = "F4";
            }
        }
        else if (size == 65536) {
            if (isProbably3E(image, size)) {
                type = "3E";
            }
            else if (isProbably3F(image, size)) {
                type = "3F";
            }
            else {
                type = "MB";
            }
        }
        else if (size == 131072) {
            if (isProbably3E(image, size)) {
                type = "3E";
            }
            else if (isProbably3F(image, size)) {
                type = "3F";
            }
            else {
                type = "MC";
            }
        }
        else if (isProbably3E(image, size)) {
            type = "3E";
        }
        else if (isProbably3F(image, size)) {
            type = "3F";
        }
        else {
            type = "4K";
        }
        return type;
    }
    
    static boolean searchForBytes(final byte[] image, final byte[] signature, final int sigsize, final int minhits) {
        int count = 0;
        for (int imagesize = image.length, i = 0; i < imagesize - sigsize; ++i) {
            int matches = 0;
            for (int j = 0; j < sigsize && image[i + j] == signature[j]; ++j) {
                ++matches;
            }
            if (matches == sigsize) {
                ++count;
                i += sigsize;
            }
            if (count >= minhits) {
                break;
            }
        }
        return count >= minhits;
    }
    
    static boolean isProbablySC(final byte[] image, final int size) {
        for (int banks = (int)(size / 4096.0), i = 0; i < banks; ++i) {
            final byte first = image[i * 4096];
            for (int j = 0; j < 256; ++j) {
                if (image[i * 4096 + j] != first) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static boolean isProbably3F(final byte[] image, final int size) {
        final int[] intsignature = { 133, 63 };
        final byte[] signature = toDataUByte(intsignature);
        return searchForBytes(image, signature, 2, 2);
    }
    
    static boolean isProbably3E(final byte[] image, final int size) {
        final int[] intsignature = { 133, 62, 169, 0 };
        final byte[] signature = toDataUByte(intsignature);
        return searchForBytes(image, signature, 4, 1);
    }
    
    static boolean isProbablyE0(final byte[] image, final int size) {
        final int[][] intsignature = { { 141, 224, 31 }, { 141, 224, 95 }, { 141, 233, 255 }, { 173, 233, 255 }, { 173, 237, 255 }, { 173, 243, 191 } };
        final byte[][] signature = toDataUByte(intsignature);
        for (int i = 0; i < 6; ++i) {
            if (searchForBytes(image, signature[i], 3, 1)) {
                return true;
            }
        }
        return false;
    }
    
    static boolean isProbablyE7(final byte[] image, final int size) {
        final byte first = image[14336];
        for (int i = 14336; i < 14848; ++i) {
            if (first != image[i]) {
                return false;
            }
        }
        int count1 = 0;
        int count2 = 0;
        for (int j = 14304; j < 14336; ++j) {
            if (first != image[j]) {
                ++count1;
            }
        }
        for (int j = 14848; j < 14880; ++j) {
            if (first != image[j]) {
                ++count2;
            }
        }
        return count1 > 0 || count2 > 0;
    }
    
    static boolean isProbablyUA(final byte[] image, final int size) {
        final int[] intsignature = { 141, 64, 2 };
        final byte[] signature = toDataUByte(intsignature);
        return searchForBytes(image, signature, 3, 1);
    }
    
    static boolean isProbablyCV(final byte[] image, final int size) {
        final int[][] intsignature = { { 157, 255, 243 }, { 153, 0, 244 } };
        final byte[][] signature = toDataUByte(intsignature);
        return searchForBytes(image, signature[0], 3, 1) || searchForBytes(image, signature[1], 3, 1);
    }
    
    static boolean isProbablyFE(final byte[] image, final int size) {
        final int[][] intsignature = { { 32, 0, 208, 198, 197 }, { 32, 195, 248, 165, 130 }, { 208, 251, 32, 115, 254 }, { 32, 0, 240, 132, 214 } };
        final byte[][] signature = toDataUByte(intsignature);
        for (int i = 0; i < 4; ++i) {
            if (searchForBytes(image, signature[i], 5, 1)) {
                return true;
            }
        }
        return false;
    }
    
    protected static int[] copyImage(final int[] aSourceImage) {
        final int[] zReturn = new int[aSourceImage.length];
        for (int i = 0; i < aSourceImage.length; ++i) {
            zReturn[i] = aSourceImage[i];
        }
        return zReturn;
    }
    
    protected static void randomizeRAM(final int[] aRAM) {
        final Random zRandom = new Random();
        for (int i = 0; i < aRAM.length; ++i) {
            aRAM[i] = (zRandom.nextInt() & 0xFF);
        }
    }
    
    protected final void addIndirectAccess(final int aStartAddress, final int aEndAddress) {
        for (int zAddress = aStartAddress & 0xFFFFFFC0; zAddress < (aEndAddress & 0xFFFFFFC0); zAddress += 64) {
            this.mySystem.setPageAccess(zAddress >> 6, PageAccess.createIndirectAccess(this));
        }
    }
    
    protected final void addDirectPeekAccess(final int aStartAddress, final int aEndAddress, final int[] aMemory, final int aBaseAddressMask, final int aBaseAddressOffset) {
        for (int zAddress = aStartAddress & 0xFFFFFFC0; zAddress < (aEndAddress & 0xFFFFFFC0); zAddress += 64) {
            this.mySystem.setPageAccess(zAddress >> 6, PageAccess.createDirectPeekAccess(this, aMemory, aBaseAddressOffset + (zAddress & aBaseAddressMask)));
        }
    }
    
    protected final void addDirectPeekAccess(final int aStartAddress, final int aEndAddress, final int[] aMemory, final int aBaseAddressMask) {
        this.addDirectPeekAccess(aStartAddress, aEndAddress, aMemory, aBaseAddressMask, 0);
    }
    
    protected final void addDirectPokeAccess(final int aStartAddress, final int aEndAddress, final int[] aMemory, final int aBaseAddressMask, final int aBaseAddressOffset) {
        for (int zAddress = aStartAddress & 0xFFFFFFC0; zAddress < (aEndAddress & 0xFFFFFFC0); zAddress += 64) {
            this.mySystem.setPageAccess(zAddress >> 6, PageAccess.createDirectPokeAccess(this, aMemory, aBaseAddressOffset + (zAddress & aBaseAddressMask)));
        }
    }
    
    protected final void addDirectPokeAccess(final int aStartAddress, final int aEndAddress, final int[] aMemory, final int aBaseAddressMask) {
        this.addDirectPokeAccess(aStartAddress, aEndAddress, aMemory, aBaseAddressMask, 0);
    }
    
    public static byte toDataUByte(final int aByteValue) {
        return (byte)(aByteValue & 0xFF);
    }
    
    public static byte[] toDataUByte(final int[] aArray) {
        final byte[] zReturn = new byte[aArray.length];
        for (int i = 0; i < aArray.length; ++i) {
            zReturn[i] = toDataUByte(aArray[i]);
        }
        return zReturn;
    }
    
    public static byte[][] toDataUByte(final int[][] aArray) {
        final byte[][] zReturn = new byte[aArray.length][];
        for (int i = 0; i < aArray.length; ++i) {
            zReturn[i] = new byte[aArray[i].length];
            for (int j = 0; j < aArray[i].length; ++j) {
                zReturn[i][j] = toDataUByte(aArray[i][j]);
            }
        }
        return zReturn;
    }
    
    public String toString() {
        return "Cartridge : " + this.name() + "; md5=" + this.myMD5;
    }
}

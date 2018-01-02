// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIVariant extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IVARIANT_IID_STR = "6c9eb060-8c6a-11d5-90f3-0010a4e73d9a";
    public static final nsID NS_IVARIANT_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 26;
        NS_IVARIANT_IID = new nsID("6c9eb060-8c6a-11d5-90f3-0010a4e73d9a");
    }
    
    public nsIVariant(final int n) {
        super(n);
    }
    
    public int GetDataType(final short[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetAsInt8(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetAsInt16(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetAsInt32(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetAsInt64(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetAsUint8(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetAsUint16(final short[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetAsUint32(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetAsUint64(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int GetAsFloat(final float[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int GetAsDouble(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int GetAsBool(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array);
    }
    
    public int GetAsChar(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), array);
    }
    
    public int GetAsWChar(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), array);
    }
    
    public int GetAsID(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int GetAsAString(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), n);
    }
    
    public int GetAsDOMString(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), n);
    }
    
    public int GetAsACString(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), n);
    }
    
    public int GetAsAUTF8String(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, this.getAddress(), n);
    }
    
    public int GetAsString(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, this.getAddress(), array);
    }
    
    public int GetAsWString(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, this.getAddress(), array);
    }
    
    public int GetAsISupports(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, this.getAddress(), array);
    }
    
    public int GetAsInterface(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, this.getAddress(), array, array2);
    }
    
    public int GetAsArray(final short[] array, final int n, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, this.getAddress(), array, n, array2, array3);
    }
    
    public int GetAsStringWithSize(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, this.getAddress(), array, array2);
    }
    
    public int GetAsWStringWithSize(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, this.getAddress(), array, array2);
    }
}

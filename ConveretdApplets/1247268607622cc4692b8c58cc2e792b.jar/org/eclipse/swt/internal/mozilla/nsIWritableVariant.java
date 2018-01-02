// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWritableVariant extends nsIVariant
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWRITABLEVARIANT_IID_STR = "5586a590-8c82-11d5-90f3-0010a4e73d9a";
    public static final nsID NS_IWRITABLEVARIANT_IID;
    
    static {
        LAST_METHOD_ID = nsIVariant.LAST_METHOD_ID + 31;
        NS_IWRITABLEVARIANT_IID = new nsID("5586a590-8c82-11d5-90f3-0010a4e73d9a");
    }
    
    public nsIWritableVariant(final int n) {
        super(n);
    }
    
    public int GetWritable(final int[] array) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int SetWritable(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int SetAsInt32(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int SetAsInt64(final long n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int SetAsUint16(final short n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int SetAsUint32(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int SetAsFloat(final float n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int SetAsDouble(final double n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 12, this.getAddress(), n);
    }
    
    public int SetAsBool(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
    
    public int SetAsID(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 16, this.getAddress(), n);
    }
    
    public int SetAsAString(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 17, this.getAddress(), n);
    }
    
    public int SetAsDOMString(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 18, this.getAddress(), n);
    }
    
    public int SetAsACString(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 19, this.getAddress(), n);
    }
    
    public int SetAsAUTF8String(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 20, this.getAddress(), n);
    }
    
    public int SetAsString(final byte[] array) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 21, this.getAddress(), array);
    }
    
    public int SetAsWString(final char[] array) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 22, this.getAddress(), array);
    }
    
    public int SetAsISupports(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 23, this.getAddress(), n);
    }
    
    public int SetAsInterface(final nsID nsID, final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 24, this.getAddress(), nsID, n);
    }
    
    public int SetAsArray(final short n, final int n2, final int n3, final int n4) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 25, this.getAddress(), n, n2, n3, n4);
    }
    
    public int SetAsStringWithSize(final int n, final byte[] array) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 26, this.getAddress(), n, array);
    }
    
    public int SetAsWStringWithSize(final int n, final char[] array) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 27, this.getAddress(), n, array);
    }
    
    public int SetAsVoid() {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 28, this.getAddress());
    }
    
    public int SetAsEmpty() {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 29, this.getAddress());
    }
    
    public int SetAsEmptyArray() {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 30, this.getAddress());
    }
    
    public int SetFromVariant(final int n) {
        return XPCOM.VtblCall(nsIVariant.LAST_METHOD_ID + 31, this.getAddress(), n);
    }
}

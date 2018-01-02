// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsISerializable extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISERIALIZABLE_IID_STR = "91cca981-c26d-44a8-bebe-d9ed4891503a";
    public static final nsID NS_ISERIALIZABLE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_ISERIALIZABLE_IID = new nsID("91cca981-c26d-44a8-bebe-d9ed4891503a");
    }
    
    public nsISerializable(final int n) {
        super(n);
    }
    
    public int Read(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int Write(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
}

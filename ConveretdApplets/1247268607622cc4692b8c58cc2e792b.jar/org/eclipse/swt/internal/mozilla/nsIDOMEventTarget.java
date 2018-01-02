// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMEventTarget extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMEVENTTARGET_IID_STR = "1c773b30-d1cf-11d2-bd95-00805f8ae3f4";
    public static final nsID NS_IDOMEVENTTARGET_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 3;
        NS_IDOMEVENTTARGET_IID = new nsID("1c773b30-d1cf-11d2-bd95-00805f8ae3f4");
    }
    
    public nsIDOMEventTarget(final int n) {
        super(n);
    }
    
    public int AddEventListener(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3);
    }
    
    public int RemoveEventListener(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3);
    }
    
    public int DispatchEvent(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, array);
    }
}

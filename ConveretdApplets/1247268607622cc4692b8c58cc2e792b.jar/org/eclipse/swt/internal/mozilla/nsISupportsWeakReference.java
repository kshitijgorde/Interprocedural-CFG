// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsISupportsWeakReference extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISUPPORTSWEAKREFERENCE_IID_STR = "9188bc86-f92e-11d2-81ef-0060083a0bcf";
    public static final nsID NS_ISUPPORTSWEAKREFERENCE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_ISUPPORTSWEAKREFERENCE_IID = new nsID("9188bc86-f92e-11d2-81ef-0060083a0bcf");
    }
    
    public nsISupportsWeakReference(final int n) {
        super(n);
    }
    
    public int GetWeakReference(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIMemory extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IMEMORY_IID_STR = "59e7e77a-38e4-11d4-8cf5-0060b0fc14a3";
    public static final nsID NS_IMEMORY_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 5;
        NS_IMEMORY_IID = new nsID("59e7e77a-38e4-11d4-8cf5-0060b0fc14a3");
    }
    
    public nsIMemory(final int n) {
        super(n);
    }
    
    public int Alloc(final int n) {
        return XPCOM.nsIMemory_Alloc(this.getAddress(), n);
    }
    
    public int Realloc(final int n, final int n2) {
        return XPCOM.nsIMemory_Realloc(this.getAddress(), n, n2);
    }
    
    public int Free(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int HeapMinimize(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int IsLowMemory(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
}

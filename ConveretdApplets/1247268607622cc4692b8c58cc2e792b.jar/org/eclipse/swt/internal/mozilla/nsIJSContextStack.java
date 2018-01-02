// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIJSContextStack extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IJSCONTEXTSTACK_IID_STR = "c67d8270-3189-11d3-9885-006008962422";
    public static final nsID NS_IJSCONTEXTSTACK_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_IJSCONTEXTSTACK_IID = new nsID("c67d8270-3189-11d3-9885-006008962422");
    }
    
    public nsIJSContextStack(final int n) {
        super(n);
    }
    
    public int GetCount(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int Peek(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int Pop(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int Push(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
}

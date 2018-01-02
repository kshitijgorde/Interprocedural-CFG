// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIClassInfo extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICLASSINFO_IID_STR = "986c11d0-f340-11d4-9075-0010a4e73d9a";
    public static final nsID NS_ICLASSINFO_IID;
    public static final int SINGLETON = 1;
    public static final int THREADSAFE = 2;
    public static final int MAIN_THREAD_ONLY = 4;
    public static final int DOM_OBJECT = 8;
    public static final int PLUGIN_OBJECT = 16;
    public static final int EAGER_CLASSINFO = 32;
    public static final int CONTENT_NODE = 64;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 8;
        NS_ICLASSINFO_IID = new nsID("986c11d0-f340-11d4-9075-0010a4e73d9a");
    }
    
    public nsIClassInfo(final int n) {
        super(n);
    }
    
    public int GetInterfaces(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array, array2);
    }
    
    public int GetHelperForLanguage(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array);
    }
    
    public int GetContractID(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetClassDescription(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetClassID(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetImplementationLanguage(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int GetFlags(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetClassIDNoAlloc(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
}

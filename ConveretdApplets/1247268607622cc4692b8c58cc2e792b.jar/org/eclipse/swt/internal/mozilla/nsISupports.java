// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsISupports
{
    static final boolean IsSolaris;
    static final int FIRST_METHOD_ID;
    static final int LAST_METHOD_ID;
    public static final String NS_ISUPPORTS_IID_STR = "00000000-0000-0000-c000-000000000046";
    public static final nsID NS_ISUPPORTS_IID;
    int address;
    
    static {
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        IsSolaris = (lowerCase.startsWith("sunos") || lowerCase.startsWith("solaris"));
        FIRST_METHOD_ID = (nsISupports.IsSolaris ? 2 : 0);
        LAST_METHOD_ID = nsISupports.FIRST_METHOD_ID + 2;
        NS_ISUPPORTS_IID = new nsID("00000000-0000-0000-c000-000000000046");
    }
    
    public nsISupports(final int address) {
        this.address = address;
    }
    
    public int getAddress() {
        return this.address;
    }
    
    public int QueryInterface(final nsID nsID, final int[] array) {
        return XPCOM.VtblCall(nsISupports.FIRST_METHOD_ID, this.getAddress(), nsID, array);
    }
    
    public int AddRef() {
        return XPCOM.VtblCall(nsISupports.FIRST_METHOD_ID + 1, this.getAddress());
    }
    
    public int Release() {
        return XPCOM.VtblCall(nsISupports.FIRST_METHOD_ID + 2, this.getAddress());
    }
}

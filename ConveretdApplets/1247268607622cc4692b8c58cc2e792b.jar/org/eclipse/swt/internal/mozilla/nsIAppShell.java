// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIAppShell extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IAPPSHELL_IID_STR = "a0757c31-eeac-11d1-9ec1-00aa002fb821";
    public static final nsID NS_IAPPSHELL_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 8;
        NS_IAPPSHELL_IID = new nsID("a0757c31-eeac-11d1-9ec1-00aa002fb821");
    }
    
    public nsIAppShell(final int n) {
        super(n);
    }
    
    public int Create(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array);
    }
    
    public int Run() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress());
    }
    
    public int Spinup() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int Spindown() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress());
    }
    
    public int ListenToEventQueue(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, n2);
    }
    
    public int GetNativeEvent(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, array);
    }
    
    public int DispatchNativeEvent(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n, n2);
    }
    
    public int Exit() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress());
    }
}

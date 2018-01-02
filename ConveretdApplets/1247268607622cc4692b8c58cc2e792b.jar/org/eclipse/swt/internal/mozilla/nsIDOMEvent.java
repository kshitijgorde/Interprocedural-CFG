// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMEvent extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMEVENT_IID_STR = "a66b7b80-ff46-bd97-0080-5f8ae38add32";
    public static final nsID NS_IDOMEVENT_IID;
    public static final int CAPTURING_PHASE = 1;
    public static final int AT_TARGET = 2;
    public static final int BUBBLING_PHASE = 3;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 10;
        NS_IDOMEVENT_IID = new nsID("a66b7b80-ff46-bd97-0080-5f8ae38add32");
    }
    
    public nsIDOMEvent(final int n) {
        super(n);
    }
    
    public int GetType(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int GetTarget(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetCurrentTarget(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetEventPhase(final short[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetBubbles(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetCancelable(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int GetTimeStamp(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int StopPropagation() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress());
    }
    
    public int PreventDefault() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress());
    }
    
    public int InitEvent(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n, n2, n3);
    }
}

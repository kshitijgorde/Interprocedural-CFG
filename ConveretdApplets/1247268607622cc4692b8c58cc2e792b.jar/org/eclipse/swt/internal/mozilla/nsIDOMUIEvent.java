// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMUIEvent extends nsIDOMEvent
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMUIEVENT_IID_STR = "a6cf90c3-15b3-11d2-932e-00805f8add32";
    public static final nsID NS_IDOMUIEVENT_IID;
    
    static {
        LAST_METHOD_ID = nsIDOMEvent.LAST_METHOD_ID + 3;
        NS_IDOMUIEVENT_IID = new nsID("a6cf90c3-15b3-11d2-932e-00805f8add32");
    }
    
    public nsIDOMUIEvent(final int n) {
        super(n);
    }
    
    public int GetView(final int[] array) {
        return XPCOM.VtblCall(nsIDOMEvent.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetDetail(final int[] array) {
        return XPCOM.VtblCall(nsIDOMEvent.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int InitUIEvent(final int n, final int n2, final int n3, final int n4, final int n5) {
        return XPCOM.VtblCall(nsIDOMEvent.LAST_METHOD_ID + 3, this.getAddress(), n, n2, n3, n4, n5);
    }
}

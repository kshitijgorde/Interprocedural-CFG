// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMMouseEvent extends nsIDOMUIEvent
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMMOUSEEVENT_IID_STR = "ff751edc-8b02-aae7-0010-8301838a3123";
    public static final nsID NS_IDOMMOUSEEVENT_IID;
    
    static {
        LAST_METHOD_ID = nsIDOMUIEvent.LAST_METHOD_ID + 11;
        NS_IDOMMOUSEEVENT_IID = new nsID("ff751edc-8b02-aae7-0010-8301838a3123");
    }
    
    public nsIDOMMouseEvent(final int n) {
        super(n);
    }
    
    public int GetScreenX(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetScreenY(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetClientX(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetClientY(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetCtrlKey(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetShiftKey(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int GetAltKey(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetMetaKey(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetButton(final short[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int GetRelatedTarget(final int[] array) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int InitMouseEvent(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final short n14, final int n15) {
        return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 11, this.getAddress(), n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15);
    }
}

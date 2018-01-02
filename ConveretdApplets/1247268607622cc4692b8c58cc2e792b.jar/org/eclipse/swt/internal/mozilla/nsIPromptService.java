// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIPromptService extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPROMPTSERVICE_IID_STR = "1630c61a-325e-49ca-8759-a31b16c47aa5";
    public static final nsID NS_IPROMPTSERVICE_IID;
    public static final int BUTTON_POS_0 = 1;
    public static final int BUTTON_POS_1 = 256;
    public static final int BUTTON_POS_2 = 65536;
    public static final int BUTTON_TITLE_OK = 1;
    public static final int BUTTON_TITLE_CANCEL = 2;
    public static final int BUTTON_TITLE_YES = 3;
    public static final int BUTTON_TITLE_NO = 4;
    public static final int BUTTON_TITLE_SAVE = 5;
    public static final int BUTTON_TITLE_DONT_SAVE = 6;
    public static final int BUTTON_TITLE_REVERT = 7;
    public static final int BUTTON_TITLE_IS_STRING = 127;
    public static final int BUTTON_POS_0_DEFAULT = 0;
    public static final int BUTTON_POS_1_DEFAULT = 16777216;
    public static final int BUTTON_POS_2_DEFAULT = 33554432;
    public static final int BUTTON_DELAY_ENABLE = 67108864;
    public static final int STD_OK_CANCEL_BUTTONS = 513;
    public static final int STD_YES_NO_BUTTONS = 1027;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 9;
        NS_IPROMPTSERVICE_IID = new nsID("1630c61a-325e-49ca-8759-a31b16c47aa5");
    }
    
    public nsIPromptService(final int n) {
        super(n);
    }
    
    public int Alert(final int n, final char[] array, final char[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array, array2);
    }
    
    public int AlertCheck(final int n, final char[] array, final char[] array2, final char[] array3, final int[] array4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array, array2, array3, array4);
    }
    
    public int Confirm(final int n, final char[] array, final char[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, array, array2, array3);
    }
    
    public int ConfirmCheck(final int n, final char[] array, final char[] array2, final char[] array3, final int[] array4, final int[] array5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, array, array2, array3, array4, array5);
    }
    
    public int ConfirmEx(final int n, final char[] array, final char[] array2, final int n2, final char[] array3, final char[] array4, final char[] array5, final char[] array6, final int[] array7, final int[] array8) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, array, array2, n2, array3, array4, array5, array6, array7, array8);
    }
    
    public int Prompt(final int n, final char[] array, final char[] array2, final int[] array3, final char[] array4, final int[] array5, final int[] array6) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, array, array2, array3, array4, array5, array6);
    }
    
    public int PromptUsernameAndPassword(final int n, final char[] array, final char[] array2, final int[] array3, final int[] array4, final char[] array5, final int[] array6, final int[] array7) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n, array, array2, array3, array4, array5, array6, array7);
    }
    
    public int PromptPassword(final int n, final char[] array, final char[] array2, final int[] array3, final char[] array4, final int[] array5, final int[] array6) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n, array, array2, array3, array4, array5, array6);
    }
    
    public int Select(final int n, final char[] array, final char[] array2, final int n2, final int[] array3, final int[] array4, final int[] array5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n, array, array2, n2, array3, array4, array5);
    }
}

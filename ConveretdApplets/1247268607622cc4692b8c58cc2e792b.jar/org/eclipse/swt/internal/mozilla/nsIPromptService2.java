// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIPromptService2 extends nsIPromptService
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPROMPTSERVICE2_IID_STR = "cf86d196-dbee-4482-9dfa-3477aa128319";
    public static final nsID NS_IPROMPTSERVICE2_IID;
    
    static {
        LAST_METHOD_ID = nsIPromptService.LAST_METHOD_ID + 2;
        NS_IPROMPTSERVICE2_IID = new nsID("cf86d196-dbee-4482-9dfa-3477aa128319");
    }
    
    public nsIPromptService2(final int n) {
        super(n);
    }
    
    public int PromptAuth(final int n, final int n2, final int n3, final int n4, final char[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsIPromptService.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, array, array2, array3);
    }
    
    public int AsyncPromptAuth(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final char[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsIPromptService.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3, n4, n5, n6, array, array2, array3);
    }
}

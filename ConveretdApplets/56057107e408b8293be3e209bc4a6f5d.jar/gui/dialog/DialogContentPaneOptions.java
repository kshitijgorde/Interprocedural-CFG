// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import java.awt.Component;
import gui.JAPHelpContext;

public class DialogContentPaneOptions
{
    private int m_optionType;
    private DialogContentPane m_previousContentPane;
    private JAPHelpContext.IHelpContext m_helpContext;
    
    public DialogContentPaneOptions(final int n) {
        this(n, (JAPHelpContext.IHelpContext)null, null);
    }
    
    public DialogContentPaneOptions(final String s) {
        this(Integer.MIN_VALUE, s, null);
    }
    
    public DialogContentPaneOptions(final JAPHelpContext.IHelpContext helpContext) {
        this(Integer.MIN_VALUE, helpContext, null);
    }
    
    public DialogContentPaneOptions(final DialogContentPane dialogContentPane) {
        this(Integer.MIN_VALUE, (JAPHelpContext.IHelpContext)null, dialogContentPane);
    }
    
    public DialogContentPaneOptions(final JAPHelpContext.IHelpContext helpContext, final DialogContentPane dialogContentPane) {
        this(Integer.MIN_VALUE, helpContext, dialogContentPane);
    }
    
    public DialogContentPaneOptions(final int n, final DialogContentPane dialogContentPane) {
        this(n, (JAPHelpContext.IHelpContext)null, dialogContentPane);
    }
    
    public DialogContentPaneOptions(final int n, final JAPHelpContext.IHelpContext helpContext) {
        this(n, helpContext, null);
    }
    
    public DialogContentPaneOptions(final int n, final String s) {
        this(n, s, null);
    }
    
    public DialogContentPaneOptions(final int n, final String s, final DialogContentPane dialogContentPane) {
        this(n, new JAPHelpContext.IHelpContext() {
            private final /* synthetic */ String val$a_strHelpContext = val$a_strHelpContext;
            
            public String getHelpContext() {
                return this.val$a_strHelpContext;
            }
            
            public Component getHelpExtractionDisplayContext() {
                return null;
            }
        }, dialogContentPane);
    }
    
    public DialogContentPaneOptions(final int optionType, final JAPHelpContext.IHelpContext helpContext, final DialogContentPane previousContentPane) {
        this.m_optionType = optionType;
        this.m_helpContext = helpContext;
        this.m_previousContentPane = previousContentPane;
    }
    
    public final int getOptionType() {
        return this.m_optionType;
    }
    
    public final JAPHelpContext.IHelpContext getHelpContext() {
        return this.m_helpContext;
    }
    
    public final DialogContentPane getPreviousContentPane() {
        return this.m_previousContentPane;
    }
    
    public int countExtraButtons() {
        return 0;
    }
    
    public AbstractDialogExtraButton getExtraButtonInternal(final int n) {
        return null;
    }
    
    protected final AbstractDialogExtraButton getExtraButton(final int n) {
        if (n < 0 || n >= this.countExtraButtons()) {
            return null;
        }
        return this.getExtraButtonInternal(n);
    }
}

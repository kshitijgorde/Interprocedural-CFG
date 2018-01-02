import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextSelection
{
    private final int LINE_SHIFT = 1000;
    private boolean active;
    private int firstCh;
    private int lastCh;
    private int start;
    private String copyCutText;
    
    public TextSelection() {
        this.active = false;
        this.copyCutText = null;
    }
    
    public void clearSelection() {
        this.active = false;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public int getFirstCol() {
        return this.firstCh % 1000;
    }
    
    public int getFirstLin() {
        return this.firstCh / 1000;
    }
    
    public int getLastCol() {
        return this.lastCh % 1000;
    }
    
    public int getLastLin() {
        return this.lastCh / 1000;
    }
    
    public String getText() {
        if (this.copyCutText != null) {
            return this.copyCutText;
        }
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            return (String)defaultToolkit.getSystemClipboard().getContents(this).getTransferData(DataFlavor.stringFlavor);
        }
        catch (Exception ex) {
            System.err.println("TextSelection.getText: " + ex);
            Toolkit.getDefaultToolkit().beep();
            return null;
        }
    }
    
    public void setSelectionEnd(final int n, final int n2) {
        final int n3 = n * 1000 + n2;
        if (n3 == this.start) {
            this.active = false;
            return;
        }
        if (n3 > this.start) {
            this.firstCh = this.start;
            this.lastCh = n3;
        }
        else {
            this.firstCh = n3;
            this.lastCh = this.start;
        }
        this.active = true;
    }
    
    public void setSelectionStart(final int n, final int n2) {
        this.start = n * 1000 + n2;
        this.active = false;
    }
    
    public void setText(final String copyCutText) {
        final StringSelection stringSelection = new StringSelection(copyCutText);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            defaultToolkit.getSystemClipboard().setContents(stringSelection, stringSelection);
            this.copyCutText = null;
        }
        catch (Exception ex) {
            System.err.println("TextSelection.setText: " + ex);
            this.copyCutText = copyCutText;
        }
    }
}

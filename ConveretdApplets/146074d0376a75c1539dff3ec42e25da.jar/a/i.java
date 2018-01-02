// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class i extends h
{
    public i() {
        if (ef.w != 2 || !ef.w || ef.e >= 65792) {
            final String[] fontList = this.getToolkit().getFontList();
            for (int i = 0; i < fontList.length; ++i) {
                this.addItem(fontList[i]);
            }
            return;
        }
        this.addItem("Courier");
        this.addItem("Dialog");
        this.addItem("Helvetica");
        this.addItem("TimesRoman");
        this.addItem("Arial");
        this.addItem("Tahoma");
    }
}

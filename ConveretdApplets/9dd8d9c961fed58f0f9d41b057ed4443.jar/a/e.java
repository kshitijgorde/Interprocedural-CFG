// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class e extends cw
{
    public e() {
        if (bF.w != 2 || !bF.w || bF.e >= 65792) {
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
    }
}

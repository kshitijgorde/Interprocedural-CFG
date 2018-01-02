// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class e extends cu
{
    public e() {
        if (bD.w != 2 || !bD.w || bD.e >= 65792) {
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

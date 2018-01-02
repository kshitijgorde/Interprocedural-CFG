// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class h extends dR
{
    public h() {
        if (cK.w != 2 || !cK.w || cK.e >= 65792) {
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

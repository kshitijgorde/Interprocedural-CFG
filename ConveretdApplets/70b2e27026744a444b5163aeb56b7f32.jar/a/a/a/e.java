// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import javax.swing.Icon;

class e implements Runnable
{
    private int a;
    private Object b;
    private final j c;
    
    public e(final j c, final int a, final Object b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        switch (this.a) {
            case 0: {
                j.b(this.c).setIcon(this.b ? j.q() : j.r());
                break;
            }
            case 1: {
                j.c(this.c).setListData(new String[] { (String)this.b });
                break;
            }
            case 2: {
                j.a(this.c, (boolean)this.b);
                break;
            }
        }
    }
}

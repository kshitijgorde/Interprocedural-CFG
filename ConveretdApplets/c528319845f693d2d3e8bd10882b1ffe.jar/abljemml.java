import java.awt.Button;
import java.awt.List;
import java.awt.MenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemml
{
    abljema a;
    int b;
    final int c = 11;
    MenuItem[] d;
    String[] e;
    StringBuffer[] f;
    private MenuItem g;
    
    public abljemml(final abljema abljema) {
        this.d = new MenuItem[11];
        this.e = new String[11];
        this.f = new StringBuffer[11];
        this.g = new MenuItem("-");
        this.a = abljema.j;
    }
    
    public boolean a(final String s, final StringBuffer sb) {
        if (!this.b(s, sb)) {
            return false;
        }
        final StringBuffer sb2 = new StringBuffer(sb.toString());
        sb2.insert(0, abljema.b("XAM" + s, 20));
        this.a.bm.a(sb2);
        return true;
    }
    
    public boolean b(final String s, final StringBuffer sb) {
        int compareTo = 1;
        int i;
        for (i = 0; i < this.b; ++i) {
            compareTo = s.compareTo(this.e[i]);
            if (compareTo <= 0) {
                break;
            }
        }
        if (compareTo != 0) {
            if (this.b >= this.e.length) {
                abljem.d("Macro list full");
                return false;
            }
            for (int j = this.b; j > i; --j) {
                this.e[j] = this.e[j - 1];
                this.f[j] = this.f[j - 1];
            }
            ++this.b;
        }
        this.e[i] = s;
        this.f[i] = sb;
        return true;
    }
    
    public boolean a(final String s) {
        if (s.length() <= 0) {
            return false;
        }
        int i;
        for (i = 0; i < this.b && !this.e[i].equals(s); ++i) {}
        if (i >= this.b) {
            return false;
        }
        final StringBuffer sb = new StringBuffer(" ");
        sb.insert(0, abljema.b("XAM" + this.e[i], 20));
        this.a.bm.a(sb);
        --this.b;
        while (i < this.b) {
            this.e[i] = this.e[i + 1];
            this.f[i] = this.f[i + 1];
            ++i;
        }
        this.e[i] = null;
        this.f[i] = null;
        return true;
    }
    
    public StringBuffer a(final MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        final String label = menuItem.getLabel();
        if (label == null) {
            return null;
        }
        int n;
        for (n = 0; n < this.b && (this.e[n] == null || !label.equals(this.e[n])); ++n) {}
        if (n >= this.b) {
            return null;
        }
        return this.f[n];
    }
    
    public void a(final abljempu abljempu) {
        for (int i = 0; i < this.b; ++i) {
            if (this.e[i] != null) {
                if (abljempu.w == 1) {
                    abljempu.a(this.g);
                }
                abljempu.a(this.d[i] = new MenuItem(this.e[i]));
            }
        }
    }
    
    public List a(final List list, final boolean b, final List list2) {
        List list3 = list;
        if (list3 == null || b) {
            if (this.b <= list2.getRows()) {
                list3 = list2;
            }
            else if (list3 == null || list3.getRows() != this.b) {
                list3 = new List(this.b, false);
            }
        }
        list3.clear();
        for (int i = 0; i < this.b; ++i) {
            if (this.e[i] != null) {
                list3.addItem(this.e[i]);
            }
        }
        return list3;
    }
    
    public void a(final String s, final boolean b, final Button button, final Button button2) {
        int n;
        for (n = 0; n < this.b && (this.e[n] == null || !this.e[n].equals(s)); ++n) {}
        if (n < this.b) {
            if (!button.isEnabled()) {
                button.enable();
            }
        }
        else if (button.isEnabled()) {
            button.disable();
        }
        if (b && (this.b < 11 || n < this.b)) {
            if (!button2.isEnabled()) {
                button2.enable();
            }
        }
        else if (button2.isEnabled()) {
            button2.disable();
        }
    }
}

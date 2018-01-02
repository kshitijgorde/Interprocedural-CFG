import java.awt.TextArea;

// 
// Decompiled by Procyon v0.5.30
// 

class ba extends TextArea
{
    int a;
    int b;
    int c;
    
    ba(final int n, final int a) {
        super(n, a);
        this.b = 10240;
        this.c = 0;
        this.a = a;
    }
    
    void a() {
        if (this.c > this.b) {
            final String text = this.getText();
            int n = this.c / 2;
            while (true) {
                Label_0032: {
                    if (!bm.dX) {
                        break Label_0032;
                    }
                    ++n;
                }
                if (text.charAt(n) != '\n' && n < this.c) {
                    continue;
                }
                break;
            }
            final String substring = text.substring(n + 1);
            this.c = substring.length();
            this.setText(substring);
        }
    }
    
    void a(String s) {
        final boolean dx = bm.dX;
        this.c += s.length();
    Label_0087:
        while (true) {
            if (!dx) {
                break Label_0087;
            }
        Label_0098:
            while (true) {
                String substring = null;
                do {
                    final int index = substring.indexOf(" ", this.a);
                    if (index < 0) {
                        break Label_0098;
                    }
                    this.appendText(s.substring(0, index) + "\n");
                    if (s.length() <= index) {
                        break Label_0087;
                    }
                    substring = s.substring(index);
                } while (dx);
                s = substring;
                if (s.length() > this.a) {
                    continue;
                }
                break;
            }
            this.appendText(s + "\n");
            this.a();
            if (!dx) {
                return;
            }
            continue Label_0087;
        }
    }
    
    void b() {
        this.c = 0;
        this.setText("");
    }
}

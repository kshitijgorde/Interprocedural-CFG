// 
// Decompiled by Procyon v0.5.30
// 

class vj extends Thread
{
    private final Object Oa;
    private final n ta;
    
    vj(final n ta, final Object oa) {
        this.ta = ta;
        this.Oa = oa;
    }
    
    public void run() {
        synchronized (this.ta) {
            try {
                String text = "";
                final String a = n.a(this.ta);
                if (this.Oa == n._(this.ta)) {
                    text = n._(this.ta).getText();
                    if (n.f(this.ta)) {
                        text = text.toUpperCase();
                    }
                    if (text.length() > 0) {
                        if (n.g(this.ta) && "".equals(n.b(this.ta).get(n.b(this.ta).getSelectedItem()))) {
                            n.b(this.ta, text);
                            n._(this.ta).put(text, n.a(this.ta)._()._(text));
                        }
                        if (n.b(this.ta).isMultipleMode()) {
                            if (n.b(this.ta)._(text)) {
                                n.b(this.ta).remove(text);
                                if (n.a(this.ta) != null) {
                                    n.b(this.ta).a(n.a(this.ta));
                                }
                            }
                            else if (n.a(this.ta) != null && !text.equals(n.a(this.ta))) {
                                n.b(this.ta).a(n.a(this.ta));
                            }
                        }
                    }
                    else if (n.b(this.ta).isMultipleMode()) {
                        text = n.b(this.ta).b();
                        if (text == null) {
                            text = "";
                        }
                        n._(this.ta).setText(text);
                    }
                    n.a(this.ta, text);
                    n.a(this.ta)._().a(n.b(this.ta).a());
                    n.a(this.ta)._().a(n.a(this.ta));
                    n._(this.ta).selectAll();
                }
                else if (this.Oa == n.a(this.ta)) {
                    text = n.a(this.ta).getText();
                    if (n.f(this.ta)) {
                        text = text.toUpperCase();
                    }
                    if (n.g(this.ta) && "".equals(n.b(this.ta).get(n.b(this.ta).getSelectedItem()))) {
                        n.b(this.ta, text);
                        n._(this.ta).put(text, n.a(this.ta)._()._(text));
                    }
                    if (n.a(this.ta) != null && n.a(this.ta).length() > 0) {
                        n.a(this.ta, n.a(this.ta));
                    }
                    n._(this.ta, text);
                    n.a(this.ta)._()._(text);
                }
                if (text.length() > 0) {
                    this.ta.b(text + ": " + n.a(this.ta).a().b("msgLoadingData"));
                }
                else {
                    this.ta.b(n.a(this.ta).a().b("msgLoadingData"));
                }
                if (this.Oa == n._(this.ta) && (a == null || !a.equals(n.a(this.ta)))) {
                    n._(this.ta).b();
                }
                n._(this.ta);
                boolean a2 = false;
                if (this.ta._()) {
                    a2 = n.a(this.ta);
                }
                if (!a2) {
                    n._(this.ta)._();
                }
                this.ta.b(n.a(this.ta)._().getMessage());
            }
            finally {
                n._(this.ta).repaint();
                n._(this.ta).a();
                this.ta.b(true);
            }
        }
    }
}

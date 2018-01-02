// 
// Decompiled by Procyon v0.5.30
// 

class Tea extends Thread
{
    private final Object _a;
    private final tea N;
    
    Tea(final tea n, final Object a) {
        this.N = n;
        this._a = a;
    }
    
    public void run() {
        synchronized (tea.b(this.N)) {
            try {
                String text = "";
                final String a = super.a(tea.b(this.N));
                if (this._a == super.a(tea.b(this.N))) {
                    text = super.a(tea.b(this.N)).getText();
                    if (super.b(tea.b(this.N))) {
                        text = text.toUpperCase();
                    }
                    if (text.length() > 0) {
                        if (super._(tea.b(this.N))) {
                            super.b(tea.b(this.N), text);
                        }
                        if (super.b(tea.b(this.N)).isMultipleMode()) {
                            if (super.b(tea.b(this.N)).a(text)) {
                                super.b(tea.b(this.N)).remove(text);
                                if (super.a(tea.b(this.N)) != null) {
                                    super.b(tea.b(this.N)).b(super.a(tea.b(this.N)));
                                }
                            }
                            else if (super.a(tea.b(this.N)) != null && !text.equals(super.a(tea.b(this.N)))) {
                                super.b(tea.b(this.N)).b(super.a(tea.b(this.N)));
                            }
                        }
                    }
                    else if (super.b(tea.b(this.N)).isMultipleMode()) {
                        text = super.b(tea.b(this.N)).b();
                        if (text == null) {
                            text = "";
                        }
                        super.a(tea.b(this.N)).setText(text);
                    }
                    super._(tea.b(this.N), text);
                    super.b(tea.b(this.N))._().a(super.b(tea.b(this.N))._());
                    super.b(tea.b(this.N))._()._(super.a(tea.b(this.N)));
                    super.a(tea.b(this.N)).selectAll();
                }
                else if (this._a == super.b(tea.b(this.N))) {
                    text = super.b(tea.b(this.N)).getText();
                    if (super.b(tea.b(this.N))) {
                        text = text.toUpperCase();
                    }
                    if (super._(tea.b(this.N))) {
                        super.b(tea.b(this.N), text);
                    }
                    if (super.a(tea.b(this.N)) != null && super.a(tea.b(this.N)).length() > 0) {
                        super._(tea.b(this.N), super.a(tea.b(this.N)));
                    }
                    if (!super._(tea.b(this.N), text)) {
                        text = "";
                    }
                    super.a(tea.b(this.N), text);
                    super.b(tea.b(this.N))._().b(text);
                }
                if (text.length() > 0) {
                    tea.b(this.N).a(text + ": " + super.b(tea.b(this.N)).a().a("msgLoadingData"));
                }
                else {
                    tea.b(this.N).a(super.b(tea.b(this.N)).a().a("msgLoadingData"));
                }
                if (this._a == super.a(tea.b(this.N)) && (a == null || !a.equals(super.a(tea.b(this.N))))) {
                    super._(tea.b(this.N)).a();
                }
                boolean a2 = false;
                if (tea.b(this.N).b()) {
                    a2 = super.a(tea.b(this.N));
                }
                if (!a2) {
                    super._(tea.b(this.N)).b();
                }
                tea.b(this.N).a(super.b(tea.b(this.N))._().getMessage());
            }
            finally {
                super._(tea.b(this.N)).repaint();
                super._(tea.b(this.N))._();
                tea.b(this.N).a(true);
            }
        }
    }
}

import java.awt.List;
import java.awt.event.ItemEvent;

// 
// Decompiled by Procyon v0.5.30
// 

class afa extends Thread
{
    private final ItemEvent O;
    private final mea N;
    
    afa(final mea n, final ItemEvent o) {
        this.N = n;
        this.O = o;
    }
    
    public void run() {
        synchronized (mea.a(this.N)) {
            try {
                final String a = super.a(mea.a(this.N));
                final List list = (List)this.O.getItemSelectable();
                if (this.O.getStateChange() == 1) {
                    final int intValue = (int)this.O.getItem();
                    if (!list.isMultipleMode() || !super.b(mea.a(this.N)).a(list.getItem(intValue)) || (super.a(mea.a(this.N)) != null && !super.a(mea.a(this.N)).equals(list.getItem(intValue)))) {
                        if (super.a(mea.a(this.N)) != null && list.isMultipleMode()) {
                            super.b(mea.a(this.N)).b(super.a(mea.a(this.N)));
                        }
                        super._(mea.a(this.N), list.getItem(intValue));
                        super.a(mea.a(this.N)).setText(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N))._()._(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N)).remove(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N))._().a(super.b(mea.a(this.N))._());
                        if (super.a(mea.a(this.N)) != null) {
                            mea.a(this.N).a(super.a(mea.a(this.N)) + ": " + super.b(mea.a(this.N)).a().a("msgLoadingData"));
                        }
                        if (a == null || !a.equals(super.a(mea.a(this.N)))) {
                            super._(mea.a(this.N)).a();
                        }
                        boolean a2 = false;
                        if (mea.a(this.N).b()) {
                            a2 = super.a(mea.a(this.N));
                        }
                        if (!a2) {
                            super._(mea.a(this.N)).b();
                        }
                        if (super.a(mea.a(this.N)) != null) {
                            mea.a(this.N).a(super.b(mea.a(this.N))._().getMessage());
                        }
                    }
                }
                else {
                    final int intValue2 = (int)this.O.getItem();
                    if (super.b(mea.a(this.N)).a(list.getItem(intValue2)) || (super.a(mea.a(this.N)) != null && super.a(mea.a(this.N)).equals(list.getItem(intValue2)))) {
                        if (super.a(mea.a(this.N)) != null && !super.a(mea.a(this.N)).equals(list.getItem(intValue2))) {
                            super.b(mea.a(this.N)).remove(list.getItem(intValue2));
                            super.b(mea.a(this.N)).b(super.a(mea.a(this.N)));
                        }
                        super._(mea.a(this.N), super.b(mea.a(this.N)).b());
                        if (super.a(mea.a(this.N)) != null) {
                            super.a(mea.a(this.N)).setText(super.a(mea.a(this.N)));
                        }
                        else {
                            super.a(mea.a(this.N)).setText("");
                        }
                        super.b(mea.a(this.N))._()._(super.a(mea.a(this.N)));
                        super.b(mea.a(this.N))._().a(super.b(mea.a(this.N))._());
                        if (super.a(mea.a(this.N)) != null) {
                            mea.a(this.N).a(super.a(mea.a(this.N)) + ": " + super.b(mea.a(this.N)).a().a("msgLoadingData"));
                        }
                        if (a == null || !a.equals(super.a(mea.a(this.N)))) {
                            super._(mea.a(this.N)).a();
                        }
                        boolean a3 = false;
                        if (mea.a(this.N).b()) {
                            a3 = super.a(mea.a(this.N));
                        }
                        if (!a3) {
                            super._(mea.a(this.N)).b();
                        }
                        if (super.a(mea.a(this.N)) != null) {
                            mea.a(this.N).a(super.b(mea.a(this.N))._().getMessage());
                        }
                    }
                }
            }
            finally {
                super._(mea.a(this.N)).repaint();
                super._(mea.a(this.N))._();
                mea.a(this.N).a(true);
            }
        }
    }
}

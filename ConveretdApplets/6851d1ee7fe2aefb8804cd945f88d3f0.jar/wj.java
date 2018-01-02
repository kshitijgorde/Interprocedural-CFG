import java.awt.Container;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class wj implements ActionListener
{
    private final n ta;
    
    wj(final n ta) {
        this.ta = ta;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.ta) {
            if (actionEvent.getSource() == this.ta.Na) {
                final Container _ = n._(this.ta);
                if (_ == null) {
                    return;
                }
                if (_ instanceof FnChartsApplet) {
                    n._(this.ta).k();
                }
                else if (_ instanceof p) {
                    n._(this.ta).l();
                }
            }
            else {
                if (actionEvent.getSource() == n.b(this.ta)) {
                    if (n._(this.ta) != null) {
                        n._(this.ta).f(n.a(this.ta)._()._(n.a(this.ta)._()._()));
                    }
                    return;
                }
                if (actionEvent.getSource() == n._(this.ta)) {
                    if (n._(this.ta) != null) {
                        n._(this.ta).g(n.a(this.ta)._()._(n.a(this.ta)._()._()));
                    }
                    return;
                }
                if (actionEvent.getSource() == n.b(this.ta)) {
                    n._(this.ta).n(0);
                    n._(this.ta).repaint();
                    n.g(this.ta);
                    return;
                }
                if (actionEvent.getSource() == n._(this.ta)) {
                    n._(this.ta).n(1);
                    n._(this.ta).repaint();
                    n.g(this.ta);
                    return;
                }
                if (actionEvent.getSource() == n.k(this.ta)) {
                    n._(this.ta).n(2);
                    n._(this.ta).repaint();
                    n.g(this.ta);
                    return;
                }
                if (actionEvent.getSource() == n.l(this.ta)) {
                    n._(this.ta).n(3);
                    n._(this.ta).repaint();
                    n.g(this.ta);
                    return;
                }
                if (actionEvent.getSource() == n.m(this.ta) || actionEvent.getSource() == n.n(this.ta) || actionEvent.getSource() == n.c(this.ta)) {
                    if (actionEvent.getSource() == n.m(this.ta)) {
                        if (((t)actionEvent.getSource()).getState()) {
                            n._(this.ta).c(0);
                        }
                    }
                    else if (actionEvent.getSource() == n.n(this.ta)) {
                        if (((t)actionEvent.getSource()).getState()) {
                            n._(this.ta).c(1);
                        }
                    }
                    else if (actionEvent.getSource() == n.c(this.ta) && ((t)actionEvent.getSource()).getState()) {
                        n._(this.ta).c(2);
                    }
                    n._(this.ta)._();
                    n._(this.ta).repaint();
                    return;
                }
                if (actionEvent.getSource() == n.d(this.ta)) {
                    this.ta.b(false);
                    final Gj gj = new Gj(n.b(this.ta), n.a(this.ta).a().b("strTimeframeSettings"), n.a(this.ta)._().b(), n.a(this.ta)._().g(), n.a(this.ta).a());
                    gj.show();
                    if (gj.a()) {
                        new Hj(this, gj.b(), gj._()).start();
                    }
                    else {
                        this.ta.b(true);
                    }
                    return;
                }
                if (actionEvent.getSource() == n.e(this.ta)) {
                    try {
                        this.ta.b(false);
                        final Ij ij = new Ij();
                        for (int i = 0; i < n.b(this.ta).length; ++i) {
                            final Color[] a = n.a(this.ta).a(n.b(this.ta)[i]);
                            int[] _2 = n.a(this.ta)._(n.b(this.ta)[i]);
                            if (_2 == null) {
                                _2 = new int[] { -1 };
                            }
                            if (a != null && _2 != null) {
                                ij.b(n.b(this.ta)[i], a[0], _2[0]);
                            }
                        }
                        final Color[] a2 = n.a(this.ta).a("Indicator");
                        final int[] _3 = n.a(this.ta)._("Indicator");
                        for (int j = 0; j < 3; ++j) {
                            Color orange = Color.orange;
                            int n = 0;
                            if (a2 != null && j < a2.length) {
                                orange = a2[j];
                            }
                            if (_3 != null && j < _3.length) {
                                n = _3[j];
                            }
                            ij.b("IndicatorLine" + j, orange, n);
                        }
                        final Ij ij2 = new Ij();
                        Color[] a3 = n.a(this.ta).a("ExtraPrice");
                        if (a3 == null) {
                            a3 = new Color[0];
                        }
                        final Color[] array = new Color[10];
                        for (int k = 0; k < array.length; ++k) {
                            if (k < a3.length) {
                                array[k] = a3[k];
                            }
                            else {
                                array[k] = Color.black;
                            }
                        }
                        int[] _4 = n.a(this.ta)._("ExtraPrice");
                        if (_4 == null) {
                            _4 = new int[0];
                        }
                        final int[] array2 = new int[10];
                        for (int l = 0; l < array2.length; ++l) {
                            if (l < _4.length) {
                                array2[l] = _4[l];
                            }
                            else {
                                array2[l] = 0;
                            }
                        }
                        for (int n2 = 0; n2 < array.length; ++n2) {
                            ij2.b("ExtraPrice" + n2, array[n2], array2[n2]);
                        }
                        final String[] array3 = { "PivotPoints", "R1", "S1", "R2", "S2" };
                        for (int n3 = 0; n3 < array3.length; ++n3) {
                            final Color[] a4 = n.a(this.ta).a(array3[n3]);
                            int[] _5 = n.a(this.ta)._(array3[n3]);
                            if (_5 == null) {
                                _5 = new int[] { -1 };
                            }
                            if (a4 != null && _5 != null) {
                                ij2.b(array3[n3], a4[0], _5[0]);
                            }
                        }
                        final Jj jj = new Jj(n.b(this.ta), n.a(this.ta).a().b("strChartProperties"), ij, ij2, n.a(this.ta).a());
                        jj.show();
                        if (jj.a()) {
                            final Ij b = jj.b();
                            final String[] b2 = ij2.b();
                            for (int n4 = 0; n4 < b2.length; ++n4) {
                                if (!b2[n4].startsWith("ExtraPrice")) {
                                    final Color color = b._(b2[n4]).getColor();
                                    final int width = b._(b2[n4]).getWidth();
                                    n.a(this.ta)._(b2[n4], new Color[] { color });
                                    if (width >= 0) {
                                        n.a(this.ta).a(b2[n4], new int[] { width });
                                    }
                                }
                            }
                            final Ij a5 = jj.a();
                            final String[] b3 = a5.b();
                            for (int n5 = 0; n5 < b3.length; ++n5) {
                                if (!b3[n5].startsWith("IndicatorLine")) {
                                    final Color color2 = a5._(b3[n5]).getColor();
                                    final int width2 = a5._(b3[n5]).getWidth();
                                    if (b3[n5].equals("BOL")) {
                                        n.a(this.ta)._(b3[n5], new Color[] { color2, color2 });
                                        if (width2 >= 0) {
                                            n.a(this.ta).a(b3[n5], new int[] { width2, width2 });
                                        }
                                    }
                                    else {
                                        n.a(this.ta)._(b3[n5], new Color[] { color2 });
                                        if (width2 >= 0) {
                                            n.a(this.ta).a(b3[n5], new int[] { width2 });
                                        }
                                    }
                                }
                            }
                            final Color[] array4 = new Color[3];
                            final int[] array5 = new int[3];
                            for (int n6 = 0; n6 < b3.length; ++n6) {
                                if (b3[n6].startsWith("IndicatorLine")) {
                                    for (int n7 = 0; n7 < 3; ++n7) {
                                        if (b3[n6].equals("IndicatorLine" + n7)) {
                                            array4[n7] = a5._(b3[n6]).getColor();
                                            array5[n7] = a5._(b3[n6]).getWidth();
                                            if (array5[n7] < 0) {
                                                array5[n7] = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            for (int n8 = 0; n8 < array4.length; ++n8) {
                                if (array4[n8] == null) {
                                    array4[n8] = Color.pink;
                                }
                            }
                            for (int n9 = 0; n9 < array5.length; ++n9) {
                                if (array5[n9] < 0) {
                                    array5[n9] = 0;
                                }
                            }
                            n.a(this.ta)._("Indicator", array4);
                            n.a(this.ta).a("Indicator", array5);
                            final String[] _6 = n.a(this.ta)._();
                            for (int n10 = 0; n10 < _6.length; ++n10) {
                                n.a(this.ta)._(_6[n10], array4);
                                n.a(this.ta).a(_6[n10], array5);
                            }
                            final Ij b4 = jj.b();
                            final String[] b5 = b4.b();
                            final Color[] array6 = new Color[b5.length];
                            final int[] array7 = new int[b5.length];
                            for (int n11 = 0; n11 < b5.length; ++n11) {
                                final Color color3 = b4._(b5[n11]).getColor();
                                final int width3 = b4._(b5[n11]).getWidth();
                                array6[n11] = color3;
                                array7[n11] = width3;
                            }
                            n.a(this.ta)._("ExtraPrice", array6);
                            n.a(this.ta).a("ExtraPrice", array7);
                            n._(this.ta).m();
                            n._(this.ta)._();
                            n._(this.ta).repaint();
                        }
                    }
                    finally {
                        this.ta.b(true);
                    }
                    return;
                }
                if (actionEvent.getSource() == n.f(this.ta)) {
                    n.a(this.ta, n.f(this.ta).getState());
                    n.a(this.ta).setVisible(n.b(this.ta));
                    this.ta.invalidate();
                    this.ta.validate();
                    return;
                }
                if (actionEvent.getSource() == n.g(this.ta)) {
                    int n12 = 0;
                    if (n.a(this.ta).a() != null) {
                        ++n12;
                    }
                    if (n.a(this.ta).b() != null) {
                        ++n12;
                    }
                    if (n.g(this.ta).getState()) {
                        n._(this.ta).d(1);
                        this.ta._(2 + n12, n._(this.ta));
                    }
                    else {
                        n._(this.ta).d(2);
                        this.ta._(1 + n12, n._(this.ta));
                    }
                    n._(this.ta)._();
                    n._(this.ta).repaint();
                    return;
                }
                if (actionEvent.getSource() == n.a(this.ta)) {
                    n.a(this.ta)._().setUseCache(n.a(this.ta).getState());
                    return;
                }
                if (actionEvent.getSource() == n.h(this.ta)) {
                    n._(this.ta)._(!n.h(this.ta).getState());
                    return;
                }
                if (actionEvent.getSource() == n.i(this.ta)) {
                    n._(this.ta).a(n.i(this.ta).getState());
                    return;
                }
                if (actionEvent.getSource() == n.j(this.ta) || actionEvent.getSource() == n.Q(this.ta) || actionEvent.getSource() == n.R(this.ta) || actionEvent.getSource() == n.S(this.ta) || actionEvent.getSource() == n.T(this.ta)) {
                    this.ta.setCursor(Cursor.getPredefinedCursor(3));
                    n.b(this.ta, n._(this.ta) != 5);
                    n.b(this.ta).setMultipleMode(n._(this.ta) == 5);
                    n.a(this.ta, n.a(this.ta));
                    if (n._(this.ta) != 5) {
                        n.b(this.ta).removeAll();
                    }
                    n.a(this.ta)._().a(n.b(this.ta).a());
                    n._(this.ta).e(n._(this.ta));
                    n._(this.ta);
                    if (n.U(this.ta) != null) {
                        n.U(this.ta).setEnabled(n._(this.ta) != 5);
                    }
                    n._(this.ta).repaint();
                    this.ta.setCursor(Cursor.getPredefinedCursor(0));
                    return;
                }
                if (actionEvent.getSource() == n.V(this.ta)) {
                    this.ta.b(false);
                    new Lj(this).start();
                }
            }
        }
    }
    
    static n _(final wj wj) {
        return wj.ta;
    }
}

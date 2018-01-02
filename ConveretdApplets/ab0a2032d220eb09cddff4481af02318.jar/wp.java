import java.awt.Container;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class wp implements ActionListener
{
    private final var n;
    
    wp(final var n) {
        this.n = n;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.n) {
            if (actionEvent.getSource() == this.n.fEb) {
                final Container b = var.b(this.n);
                if (b == null) {
                    return;
                }
                if (b instanceof FnChartsApplet) {
                    var.a(this.n).Y();
                }
                else if (b instanceof while) {
                    var.a(this.n).Z();
                }
            }
            else {
                if (actionEvent.getSource() == var.b(this.n)) {
                    if (var.a(this.n) != null) {
                        var.a(this.n).u(var._(this.n)._().i());
                    }
                    return;
                }
                if (actionEvent.getSource() == var._(this.n)) {
                    if (var.a(this.n) != null) {
                        var.a(this.n).v(var._(this.n)._().i());
                    }
                    return;
                }
                if (actionEvent.getSource() == var._(this.n)) {
                    var.b(this.n).O(0);
                    var.b(this.n).repaint();
                    return;
                }
                if (actionEvent.getSource() == var.g(this.n)) {
                    var.b(this.n).O(1);
                    var.b(this.n).repaint();
                    return;
                }
                if (actionEvent.getSource() == var.h(this.n)) {
                    var.b(this.n).O(2);
                    var.b(this.n).repaint();
                    return;
                }
                if (actionEvent.getSource() == var.i(this.n)) {
                    var.b(this.n).O(3);
                    var.b(this.n).repaint();
                    return;
                }
                if (actionEvent.getSource() == var.j(this.n) || actionEvent.getSource() == var.k(this.n) || actionEvent.getSource() == var.l(this.n)) {
                    if (actionEvent.getSource() == var.j(this.n)) {
                        if (((c)actionEvent.getSource()).getState()) {}
                        var.b(this.n).P(0);
                    }
                    else if (actionEvent.getSource() == var.k(this.n)) {
                        if (((c)actionEvent.getSource()).getState()) {}
                        var.b(this.n).P(1);
                    }
                    else if (actionEvent.getSource() == var.l(this.n)) {
                        if (((c)actionEvent.getSource()).getState()) {}
                        var.b(this.n).P(2);
                    }
                    var.b(this.n).b();
                    var.b(this.n).repaint();
                    return;
                }
                if (actionEvent.getSource() == var.m(this.n)) {
                    new Ep(this).start();
                    return;
                }
                if (actionEvent.getSource() == var.n(this.n)) {
                    this.n.setEnabled(false);
                    this.n.setCursor(Cursor.getPredefinedCursor(3));
                    final Fp fp = new Fp();
                    for (int i = 0; i < var._(this.n).length; ++i) {
                        final Color[] _ = var._(this.n)._(var._(this.n)[i]);
                        int[] b2 = var._(this.n).b(var._(this.n)[i]);
                        if (b2 == null) {
                            b2 = new int[] { -1 };
                        }
                        if (_ != null && b2 != null) {
                            fp.b(var._(this.n)[i], _[0], b2[0]);
                        }
                    }
                    final Color[] _2 = var._(this.n)._("Indicator");
                    final int[] b3 = var._(this.n).b("Indicator");
                    for (int j = 0; j < 3; ++j) {
                        Color orange = Color.orange;
                        int n = 0;
                        if (_2 != null && j < _2.length) {
                            orange = _2[j];
                        }
                        if (b3 != null && j < b3.length) {
                            n = b3[j];
                        }
                        fp.b("IndicatorLine" + j, orange, n);
                    }
                    final Fp fp2 = new Fp();
                    Color[] _3 = var._(this.n)._("ExtraPrice");
                    if (_3 == null) {
                        _3 = new Color[0];
                    }
                    final Color[] array = new Color[10];
                    for (int k = 0; k < array.length; ++k) {
                        if (k < _3.length) {
                            array[k] = _3[k];
                        }
                        else {
                            array[k] = Color.black;
                        }
                    }
                    int[] b4 = var._(this.n).b("ExtraPrice");
                    if (b4 == null) {
                        b4 = new int[0];
                    }
                    final int[] array2 = new int[10];
                    for (int l = 0; l < array2.length; ++l) {
                        if (l < b4.length) {
                            array2[l] = b4[l];
                        }
                        else {
                            array2[l] = 0;
                        }
                    }
                    for (int n2 = 0; n2 < array.length; ++n2) {
                        fp2.b("ExtraPrice" + n2, array[n2], array2[n2]);
                    }
                    final Gp gp = new Gp(var._(this.n), var._(this.n).b()._("strChartProperties"), fp, fp2, var._(this.n).b());
                    gp.show();
                    if (gp.l()) {
                        final Fp b5 = gp.b();
                        final String[] _4 = b5._();
                        for (int n3 = 0; n3 < _4.length; ++n3) {
                            if (!_4[n3].startsWith("IndicatorLine")) {
                                final Color color = b5._(_4[n3]).getColor();
                                final int width = b5._(_4[n3]).getWidth();
                                if (_4[n3].equals("BOL")) {
                                    var._(this.n).a(_4[n3], new Color[] { color, color });
                                    if (width >= 0) {
                                        var._(this.n).b(_4[n3], new int[] { width, width });
                                    }
                                }
                                else {
                                    var._(this.n).a(_4[n3], new Color[] { color });
                                    if (width >= 0) {
                                        var._(this.n).b(_4[n3], new int[] { width });
                                    }
                                }
                            }
                        }
                        final Color[] array3 = new Color[3];
                        final int[] array4 = new int[3];
                        for (int n4 = 0; n4 < _4.length; ++n4) {
                            if (_4[n4].startsWith("IndicatorLine")) {
                                for (int n5 = 0; n5 < 3; ++n5) {
                                    if (_4[n4].equals("IndicatorLine" + n5)) {
                                        array3[n5] = b5._(_4[n4]).getColor();
                                        array4[n5] = b5._(_4[n4]).getWidth();
                                        if (array4[n5] < 0) {
                                            array4[n5] = 0;
                                        }
                                    }
                                }
                            }
                        }
                        for (int n6 = 0; n6 < array3.length; ++n6) {
                            if (array3[n6] == null) {
                                array3[n6] = Color.pink;
                            }
                        }
                        for (int n7 = 0; n7 < array4.length; ++n7) {
                            if (array4[n7] < 0) {
                                array4[n7] = 0;
                            }
                        }
                        var._(this.n).a("Indicator", array3);
                        var._(this.n).b("Indicator", array4);
                        final String[] a = var._(this.n).a();
                        for (int n8 = 0; n8 < a.length; ++n8) {
                            var._(this.n).a(a[n8], array3);
                            var._(this.n).b(a[n8], array4);
                        }
                        final Fp _5 = gp._();
                        final String[] _6 = _5._();
                        final Color[] array5 = new Color[_6.length];
                        final int[] array6 = new int[_6.length];
                        for (int n9 = 0; n9 < _6.length; ++n9) {
                            final Color color2 = _5._(_6[n9]).getColor();
                            final int width2 = _5._(_6[n9]).getWidth();
                            array5[n9] = color2;
                            array6[n9] = width2;
                        }
                        var._(this.n).a("ExtraPrice", array5);
                        var._(this.n).b("ExtraPrice", array6);
                        var.b(this.n)._a();
                        var.b(this.n).b();
                        var.b(this.n).repaint();
                    }
                    this.n.setCursor(Cursor.getPredefinedCursor(0));
                    this.n.setEnabled(true);
                    return;
                }
                if (actionEvent.getSource() == var.c(this.n)) {
                    var.b(this.n, var.c(this.n).getState());
                    var._(this.n).setVisible(var._(this.n));
                    this.n.invalidate();
                    this.n.validate();
                    return;
                }
                if (actionEvent.getSource() == var.d(this.n)) {
                    int n10 = 0;
                    if (var._(this.n).b() != null) {
                        ++n10;
                    }
                    if (var._(this.n)._() != null) {
                        ++n10;
                    }
                    if (var.d(this.n).getState()) {
                        var.b(this.n).Q(1);
                        this.n.e(2 + n10, var.b(this.n));
                    }
                    else {
                        var.b(this.n).Q(2);
                        this.n.e(1 + n10, var.b(this.n));
                    }
                    var.b(this.n).V();
                    for (int n11 = 1; n11 < var.b(this.n).a().H(); ++n11) {
                        var.b(this.n).a().N(n11);
                    }
                    var.b(this.n).W();
                    var.b(this.n).b();
                    var.b(this.n).repaint();
                    return;
                }
                if (actionEvent.getSource() == var.b(this.n)) {
                    var._(this.n)._().g(var.b(this.n).getState());
                    return;
                }
                if (actionEvent.getSource() == var.a(this.n)) {
                    var._(this.n).a(!var.a(this.n).getState());
                    return;
                }
                if (actionEvent.getSource() == var.e(this.n)) {
                    var._(this.n).h(var.e(this.n).getState());
                    return;
                }
                if (actionEvent.getSource() == var.f(this.n) || actionEvent.getSource() == var.Q(this.n) || actionEvent.getSource() == var.R(this.n) || actionEvent.getSource() == var.S(this.n) || actionEvent.getSource() == var.T(this.n)) {
                    this.n.setCursor(Cursor.getPredefinedCursor(3));
                    var.a(this.n, var.b(this.n) != 5);
                    var.a(this.n).setMultipleMode(var.b(this.n) == 5);
                    var._(this.n, var.a(this.n));
                    if (var.b(this.n) != 5) {
                        var._(this.n).removeAll();
                    }
                    var._(this.n)._().b(var._(this.n).b());
                    var.b(this.n).R(var.b(this.n));
                    var.b(this.n).repaint();
                    this.n.setCursor(Cursor.getPredefinedCursor(0));
                    return;
                }
                if (actionEvent.getSource() == var.U(this.n)) {
                    var._(this.n).a(true);
                    new Ip(this).start();
                }
            }
        }
    }
    
    static var a(final wp wp) {
        return wp.n;
    }
}

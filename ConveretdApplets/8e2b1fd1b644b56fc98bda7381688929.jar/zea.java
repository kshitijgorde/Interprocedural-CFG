import java.awt.Container;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class zea implements ActionListener
{
    private final super da;
    
    zea(final super da) {
        this.da = da;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.da) {
            if (actionEvent.getSource() == this.da.Ia) {
                final Container a = super.a(this.da);
                if (a == null) {
                    return;
                }
                if (a instanceof FnChartsApplet) {
                    super.a(this.da).k();
                }
                else if (a instanceof synchronized) {
                    super.a(this.da).l();
                }
            }
            else {
                if (actionEvent.getSource() == super.b(this.da)) {
                    if (super.a(this.da) != null) {
                        super.a(this.da).e(super.b(this.da)._().b(super.b(this.da)._()._()));
                    }
                    return;
                }
                if (actionEvent.getSource() == super._(this.da)) {
                    if (super.a(this.da) != null) {
                        super.a(this.da).f(super.b(this.da)._().b(super.b(this.da)._()._()));
                    }
                    return;
                }
                if (actionEvent.getSource() == super.b(this.da)) {
                    super._(this.da).d(0);
                    super._(this.da).repaint();
                    super.j(this.da);
                    return;
                }
                if (actionEvent.getSource() == super._(this.da)) {
                    super._(this.da).d(1);
                    super._(this.da).repaint();
                    super.j(this.da);
                    return;
                }
                if (actionEvent.getSource() == super.g(this.da)) {
                    super._(this.da).d(2);
                    super._(this.da).repaint();
                    super.j(this.da);
                    return;
                }
                if (actionEvent.getSource() == super.h(this.da)) {
                    super._(this.da).d(3);
                    super._(this.da).repaint();
                    super.j(this.da);
                    return;
                }
                if (actionEvent.getSource() == super.i(this.da) || actionEvent.getSource() == super.j(this.da) || actionEvent.getSource() == super.k(this.da)) {
                    if (actionEvent.getSource() == super.i(this.da)) {
                        if (((transient)actionEvent.getSource()).getState()) {
                            super._(this.da).e(0);
                        }
                    }
                    else if (actionEvent.getSource() == super.j(this.da)) {
                        if (((transient)actionEvent.getSource()).getState()) {
                            super._(this.da).e(1);
                        }
                    }
                    else if (actionEvent.getSource() == super.k(this.da) && ((transient)actionEvent.getSource()).getState()) {
                        super._(this.da).e(2);
                    }
                    super._(this.da).b();
                    super._(this.da).repaint();
                    return;
                }
                if (actionEvent.getSource() == super.l(this.da)) {
                    this.da.a(false);
                    final Iea iea = new Iea(super.b(this.da), super.b(this.da).a().a("strTimeframeSettings"), super.b(this.da)._().e(), super.b(this.da)._().h(), super.b(this.da).a());
                    iea.show();
                    if (iea._()) {
                        new Jea(this, iea.e(), iea.f()).start();
                    }
                    else {
                        this.da.a(true);
                    }
                    return;
                }
                if (actionEvent.getSource() == super.m(this.da)) {
                    try {
                        this.da.a(false);
                        final Kea kea = new Kea();
                        for (int i = 0; i < super.b(this.da).length; ++i) {
                            final Color[] b = super.b(this.da).b(super.b(this.da)[i]);
                            int[] _ = super.b(this.da)._(super.b(this.da)[i]);
                            if (_ == null) {
                                _ = new int[] { -1 };
                            }
                            if (b != null && _ != null) {
                                kea.b(super.b(this.da)[i], b[0], _[0]);
                            }
                        }
                        final Color[] b2 = super.b(this.da).b("Indicator");
                        final int[] _2 = super.b(this.da)._("Indicator");
                        for (int j = 0; j < 3; ++j) {
                            Color orange = Color.orange;
                            int n = 0;
                            if (b2 != null && j < b2.length) {
                                orange = b2[j];
                            }
                            if (_2 != null && j < _2.length) {
                                n = _2[j];
                            }
                            kea.b("IndicatorLine" + j, orange, n);
                        }
                        final Kea kea2 = new Kea();
                        Color[] b3 = super.b(this.da).b("ExtraPrice");
                        if (b3 == null) {
                            b3 = new Color[0];
                        }
                        final Color[] array = new Color[10];
                        for (int k = 0; k < array.length; ++k) {
                            if (k < b3.length) {
                                array[k] = b3[k];
                            }
                            else {
                                array[k] = Color.black;
                            }
                        }
                        int[] _3 = super.b(this.da)._("ExtraPrice");
                        if (_3 == null) {
                            _3 = new int[0];
                        }
                        final int[] array2 = new int[10];
                        for (int l = 0; l < array2.length; ++l) {
                            if (l < _3.length) {
                                array2[l] = _3[l];
                            }
                            else {
                                array2[l] = 0;
                            }
                        }
                        for (int n2 = 0; n2 < array.length; ++n2) {
                            kea2.b("ExtraPrice" + n2, array[n2], array2[n2]);
                        }
                        final Lea lea = new Lea(super.b(this.da), super.b(this.da).a().a("strChartProperties"), kea, kea2, super.b(this.da).a());
                        lea.show();
                        if (lea._()) {
                            final Kea a2 = lea.a();
                            final String[] a3 = a2.a();
                            for (int n3 = 0; n3 < a3.length; ++n3) {
                                if (!a3[n3].startsWith("IndicatorLine")) {
                                    final Color color = a2._(a3[n3]).getColor();
                                    final int width = a2._(a3[n3]).getWidth();
                                    if (a3[n3].equals("BOL")) {
                                        super.b(this.da)._(a3[n3], new Color[] { color, color });
                                        if (width >= 0) {
                                            super.b(this.da)._(a3[n3], new int[] { width, width });
                                        }
                                    }
                                    else {
                                        super.b(this.da)._(a3[n3], new Color[] { color });
                                        if (width >= 0) {
                                            super.b(this.da)._(a3[n3], new int[] { width });
                                        }
                                    }
                                }
                            }
                            final Color[] array3 = new Color[3];
                            final int[] array4 = new int[3];
                            for (int n4 = 0; n4 < a3.length; ++n4) {
                                if (a3[n4].startsWith("IndicatorLine")) {
                                    for (int n5 = 0; n5 < 3; ++n5) {
                                        if (a3[n4].equals("IndicatorLine" + n5)) {
                                            array3[n5] = a2._(a3[n4]).getColor();
                                            array4[n5] = a2._(a3[n4]).getWidth();
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
                            super.b(this.da)._("Indicator", array3);
                            super.b(this.da)._("Indicator", array4);
                            final String[] b4 = super.b(this.da).b();
                            for (int n8 = 0; n8 < b4.length; ++n8) {
                                super.b(this.da)._(b4[n8], array3);
                                super.b(this.da)._(b4[n8], array4);
                            }
                            final Kea b5 = lea.b();
                            final String[] a4 = b5.a();
                            final Color[] array5 = new Color[a4.length];
                            final int[] array6 = new int[a4.length];
                            for (int n9 = 0; n9 < a4.length; ++n9) {
                                final Color color2 = b5._(a4[n9]).getColor();
                                final int width2 = b5._(a4[n9]).getWidth();
                                array5[n9] = color2;
                                array6[n9] = width2;
                            }
                            super.b(this.da)._("ExtraPrice", array5);
                            super.b(this.da)._("ExtraPrice", array6);
                            super._(this.da).m();
                            super._(this.da).b();
                            super._(this.da).repaint();
                        }
                    }
                    finally {
                        this.da.a(true);
                    }
                    return;
                }
                if (actionEvent.getSource() == super.n(this.da)) {
                    super.a(this.da, super.n(this.da).getState());
                    super.b(this.da).setVisible(super.m(this.da));
                    this.da.invalidate();
                    this.da.validate();
                    return;
                }
                if (actionEvent.getSource() == super.c(this.da)) {
                    int n10 = 0;
                    if (super.b(this.da)._() != null) {
                        ++n10;
                    }
                    if (super.b(this.da).a() != null) {
                        ++n10;
                    }
                    if (super.c(this.da).getState()) {
                        super._(this.da).f(1);
                        this.da._(2 + n10, super._(this.da));
                    }
                    else {
                        super._(this.da).f(2);
                        this.da._(1 + n10, super._(this.da));
                    }
                    super._(this.da).b();
                    super._(this.da).repaint();
                    return;
                }
                if (actionEvent.getSource() == super.a(this.da)) {
                    super.b(this.da)._()._(super.a(this.da).getState());
                    return;
                }
                if (actionEvent.getSource() == super.d(this.da)) {
                    super._(this.da).m(!super.d(this.da).getState());
                    return;
                }
                if (actionEvent.getSource() == super.e(this.da)) {
                    super._(this.da).n(super.e(this.da).getState());
                    return;
                }
                if (actionEvent.getSource() == super.f(this.da) || actionEvent.getSource() == super.Q(this.da) || actionEvent.getSource() == super.R(this.da) || actionEvent.getSource() == super.S(this.da) || actionEvent.getSource() == super.T(this.da)) {
                    this.da.setCursor(Cursor.getPredefinedCursor(3));
                    super.b(this.da, super._(this.da) != 5);
                    super.b(this.da).setMultipleMode(super._(this.da) == 5);
                    super._(this.da, super.a(this.da));
                    if (super._(this.da) != 5) {
                        super.b(this.da).removeAll();
                    }
                    super.b(this.da)._().a(super.b(this.da)._());
                    super._(this.da).g(super._(this.da));
                    if (super.U(this.da) != null) {
                        super.U(this.da).setEnabled(super._(this.da) != 5);
                    }
                    super._(this.da).repaint();
                    this.da.setCursor(Cursor.getPredefinedCursor(0));
                    return;
                }
                if (actionEvent.getSource() == super.V(this.da)) {
                    this.da.a(false);
                    new Nea(this).start();
                }
            }
        }
    }
    
    static super b(final zea zea) {
        return zea.da;
    }
}

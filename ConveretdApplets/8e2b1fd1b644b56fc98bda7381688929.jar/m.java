import java.lang.reflect.InvocationTargetException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class m implements MouseListener
{
    private final d da;
    
    m(final d da) {
        this.da = da;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        d.a(this.da);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.da) {
            d._(this.da);
            d.b(this.da);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        synchronized (this.da) {
            if (mouseEvent.isPopupTrigger()) {
                d.a(this.da, mouseEvent);
                return;
            }
            if (mouseEvent.getModifiers() == 4) {
                return;
            }
            if (mouseEvent.getModifiers() == 8) {
                return;
            }
            if (d.a(this.da)) {
                return;
            }
            if (this.da.q._() instanceof u && d.b(this.da)) {
                return;
            }
            if (this.da.q._() instanceof u && ((u)this.da.q._()).W() < 2) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            int b = d.b(this.da);
            d._(this.da, -1);
            for (int i = 0; i < this.da.n(); ++i) {
                if (y >= d._(this.da)[i].y && y <= d._(this.da)[i].y + d._(this.da)[i].height) {
                    d._(this.da, i);
                }
            }
            if (d.a(this.da) == -1 || this.da.a(d.a(this.da)) == 0) {
                return;
            }
            if (mouseEvent.isShiftDown() && mouseEvent.isControlDown()) {
                b = 5;
            }
            else if (b == 4) {
                if (mouseEvent.isShiftDown()) {
                    b = 2;
                }
                else if (mouseEvent.isControlDown()) {
                    b = 3;
                }
                else {
                    b = 1;
                }
            }
            switch (b) {
                case 1: {
                    try {
                        d._(this.da, d.a(this.da).getConstructor((Class<?>[])null).newInstance((Object[])null));
                        d._(this.da).setColor(d.a(this.da));
                        d._(this.da).I(d._(this.da));
                        if (d._(this.da) instanceof SupportResistance) {
                            ((SupportResistance)d._(this.da)).m(this.da.q.Z() + 1, this.da.q._a() - 1);
                        }
                    }
                    catch (NoSuchMethodException ex) {
                        ex.printStackTrace();
                    }
                    catch (InstantiationException ex2) {
                        ex2.printStackTrace();
                    }
                    catch (IllegalAccessException ex3) {
                        ex3.printStackTrace();
                    }
                    catch (IllegalArgumentException ex4) {
                        ex4.printStackTrace();
                    }
                    catch (InvocationTargetException ex5) {
                        ex5.printStackTrace();
                    }
                    if (d._(this.da) == null) {
                        break;
                    }
                    d._(this.da).d(this.da.a()._(x), this.da._(d.a(this.da))._(y));
                    if (d._(this.da) instanceof TextTool) {
                        final TextTool textTool = (TextTool)d._(this.da);
                        d._(this.da, null);
                        d._(this.da, false);
                        final class class1 = new class(d.a(this.da), d._(this.da).a("strInputTextTitle"), d._(this.da).a("strInputTextMessage"), null, d._(this.da).a("btnOK"), d._(this.da).a("btnCancel"), d._(this.da));
                        class1.show();
                        if (class1._()) {
                            textTool.setText(class1.getText());
                            textTool._(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                            this.da.a(d.a(this.da), textTool);
                        }
                        d._(this.da, true);
                        break;
                    }
                    d._(this.da).a(x, y, this.da.a(), this.da._(d.a(this.da)));
                    d._(this.da)._(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                    this.da.a(d.a(this.da), d._(this.da));
                    break;
                }
                case 2: {
                    for (int j = 0; j < this.da._(d.a(this.da)); ++j) {
                        if (this.da.b(d.a(this.da), j).a(x, y, this.da.a(), this.da._(d.a(this.da)))) {
                            d._(this.da, this.da.b(d.a(this.da), j));
                            break;
                        }
                    }
                    if (d._(this.da) != null) {
                        d._(this.da).a(x, y, this.da.a(), this.da._(d.a(this.da)));
                        break;
                    }
                    break;
                }
                case 3: {
                    for (int k = 0; k < this.da._(d.a(this.da)); ++k) {
                        if (this.da.b(d.a(this.da), k).a(x, y, this.da.a(), this.da._(d.a(this.da)))) {
                            d._(this.da, this.da.b(d.a(this.da), k));
                            break;
                        }
                    }
                    if (d._(this.da) != null) {
                        this.da.b(d.a(this.da), d._(this.da));
                        d._(this.da).a(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                        d._(this.da, null);
                        break;
                    }
                    break;
                }
                case 5: {
                    for (int l = 0; l < this.da._(d.a(this.da)); ++l) {
                        final UserDrawTool b2 = this.da.b(d.a(this.da), l);
                        if (b2.a(x, y, this.da.a(), this.da._(d.a(this.da)))) {
                            d._(this.da, b2);
                            break;
                        }
                    }
                    if (d._(this.da) == null) {
                        break;
                    }
                    if (d._(this.da) instanceof TrendLine) {
                        final TrendLine b3 = ((TrendLine)d._(this.da)).b();
                        b3._(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                        this.da.a(d.a(this.da), b3);
                        d._(this.da).a(x, y, this.da.a(), this.da._(d.a(this.da)));
                        d._(this.da)._(x + 2, y, this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                        break;
                    }
                    if (d._(this.da) instanceof TextTool) {
                        final TextTool textTool2 = (TextTool)d._(this.da);
                        d._(this.da, null);
                        d._(this.da, false);
                        final class class2 = new class(d.a(this.da), d._(this.da).a("strEditTextTitle"), d._(this.da).a("strEditTextMessage"), textTool2.getText(), d._(this.da).a("btnOK"), d._(this.da).a("btnCancel"), d._(this.da));
                        class2.show();
                        if (class2._()) {
                            textTool2.a(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                            textTool2.setText(class2.getText());
                            textTool2._(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                        }
                        d._(this.da, true);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        synchronized (this.da) {
            if (d.a(this.da)) {
                return;
            }
            if (this.da.q._() instanceof u && d.b(this.da)) {
                return;
            }
            if (d._(this.da) != null) {
                d._(this.da).b(this.da.getGraphics(), d.a(this.da, d.a(this.da)), d._(this.da), this.da.a(), this.da._(d.a(this.da)));
                d._(this.da, null);
            }
            if (mouseEvent.isPopupTrigger()) {
                d.a(this.da, mouseEvent);
            }
        }
    }
}

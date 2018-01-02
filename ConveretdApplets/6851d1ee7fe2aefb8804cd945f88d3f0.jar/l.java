import java.lang.reflect.InvocationTargetException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class l implements MouseListener
{
    private final continue ta;
    
    l(final continue ta) {
        this.ta = ta;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        continue.a(this.ta);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.ta) {
            continue._(this.ta);
            continue.b(this.ta);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        synchronized (this.ta) {
            if (mouseEvent.isPopupTrigger()) {
                continue.b(this.ta, mouseEvent);
                return;
            }
            if (mouseEvent.getModifiers() == 4) {
                return;
            }
            if (mouseEvent.getModifiers() == 8) {
                return;
            }
            if (continue.b(this.ta)) {
                return;
            }
            if (this.ta.rka.b() instanceof try && continue._(this.ta)) {
                return;
            }
            if (this.ta.rka.b() instanceof try && ((try)this.ta.rka.b()).da() < 2) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            int a = continue.a(this.ta);
            continue.b(this.ta, -1);
            for (int i = 0; i < this.ta.ea(); ++i) {
                if (y >= continue.a(this.ta)[i].y && y <= continue.a(this.ta)[i].y + continue.a(this.ta)[i].height) {
                    continue.b(this.ta, i);
                }
            }
            if (continue._(this.ta) == -1 || this.ta.a(continue._(this.ta)) == 0) {
                return;
            }
            if (mouseEvent.isControlDown() && mouseEvent.isAltDown()) {
                a = 6;
            }
            else if (mouseEvent.isShiftDown() && mouseEvent.isControlDown()) {
                a = 5;
            }
            else if (a == 4) {
                if (mouseEvent.isShiftDown()) {
                    a = 2;
                }
                else if (mouseEvent.isControlDown()) {
                    a = 3;
                }
                else {
                    a = 1;
                }
            }
            switch (a) {
                case 1: {
                    try {
                        continue.b(this.ta, continue.a(this.ta).getConstructor((Class<?>[])null).newInstance((Object[])null));
                        continue.b(this.ta).setColor(continue.b(this.ta));
                        continue.b(this.ta).h(continue.b(this.ta));
                        if (continue.b(this.ta) instanceof SupportResistance) {
                            ((SupportResistance)continue.b(this.ta)).b(this.ta.rka.fa() + 1, this.ta.rka.ga() - 1);
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
                    if (continue.b(this.ta) == null) {
                        break;
                    }
                    continue.b(this.ta).a(this.ta._().a(x), this.ta._(continue._(this.ta)).a(y));
                    if (continue.b(this.ta) instanceof TextTool) {
                        final TextTool textTool = (TextTool)continue.b(this.ta);
                        continue.b(this.ta, (UserDrawTool)null);
                        continue._(this.ta, false);
                        final Ph ph = new Ph(continue.b(this.ta), continue.b(this.ta).b("strInputTextTitle"), continue.b(this.ta).b("strInputTextMessage"), null, continue.b(this.ta).b("btnOK"), continue.b(this.ta).b("btnCancel"), continue.b(this.ta));
                        ph.show();
                        if (ph.a()) {
                            textTool.setText(ph.getText());
                            textTool.b(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                            this.ta.a(continue._(this.ta), textTool);
                        }
                        continue._(this.ta, true);
                        break;
                    }
                    continue.b(this.ta).a(x, y, this.ta._(), this.ta._(continue._(this.ta)));
                    continue.b(this.ta).b(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                    this.ta.a(continue._(this.ta), continue.b(this.ta));
                    break;
                }
                case 2: {
                    for (int j = 0; j < this.ta.b(continue._(this.ta)); ++j) {
                        if (this.ta.b(continue._(this.ta), j)._(x, y, this.ta._(), this.ta._(continue._(this.ta)))) {
                            continue.b(this.ta, this.ta.b(continue._(this.ta), j));
                            break;
                        }
                    }
                    if (continue.b(this.ta) != null) {
                        continue.b(this.ta).a(x, y, this.ta._(), this.ta._(continue._(this.ta)));
                        break;
                    }
                    break;
                }
                case 3: {
                    for (int k = 0; k < this.ta.b(continue._(this.ta)); ++k) {
                        if (this.ta.b(continue._(this.ta), k)._(x, y, this.ta._(), this.ta._(continue._(this.ta)))) {
                            continue.b(this.ta, this.ta.b(continue._(this.ta), k));
                            break;
                        }
                    }
                    if (continue.b(this.ta) != null) {
                        this.ta.b(continue._(this.ta), continue.b(this.ta));
                        continue.b(this.ta)._(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                        continue.b(this.ta, (UserDrawTool)null);
                        break;
                    }
                    break;
                }
                case 5: {
                    for (int l = 0; l < this.ta.b(continue._(this.ta)); ++l) {
                        final UserDrawTool b = this.ta.b(continue._(this.ta), l);
                        if (b._(x, y, this.ta._(), this.ta._(continue._(this.ta)))) {
                            continue.b(this.ta, b);
                            break;
                        }
                    }
                    if (continue.b(this.ta) == null) {
                        break;
                    }
                    if (continue.b(this.ta) instanceof TrendLine) {
                        final TrendLine a2 = ((TrendLine)continue.b(this.ta)).a();
                        a2.b(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                        this.ta.a(continue._(this.ta), a2);
                        continue.b(this.ta).a(x, y, this.ta._(), this.ta._(continue._(this.ta)));
                        continue.b(this.ta)._(x + 2, y, this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                        break;
                    }
                    if (continue.b(this.ta) instanceof TextTool) {
                        final TextTool textTool2 = (TextTool)continue.b(this.ta);
                        continue.b(this.ta, (UserDrawTool)null);
                        continue._(this.ta, false);
                        final Ph ph2 = new Ph(continue.b(this.ta), continue.b(this.ta).b("strEditTextTitle"), continue.b(this.ta).b("strEditTextMessage"), textTool2.getText(), continue.b(this.ta).b("btnOK"), continue.b(this.ta).b("btnCancel"), continue.b(this.ta));
                        ph2.show();
                        if (ph2.a()) {
                            textTool2._(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                            textTool2.setText(ph2.getText());
                            textTool2.b(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                        }
                        continue._(this.ta, true);
                        break;
                    }
                    break;
                }
                case 6: {
                    for (int n = 0; n < this.ta.b(continue._(this.ta)); ++n) {
                        final UserDrawTool b2 = this.ta.b(continue._(this.ta), n);
                        if (b2._(x, y, this.ta._(), this.ta._(continue._(this.ta)))) {
                            continue.b(this.ta, b2);
                            break;
                        }
                    }
                    if (continue.b(this.ta) != null) {
                        final UserDrawTool b3 = continue.b(this.ta);
                        continue.b(this.ta, (UserDrawTool)null);
                        continue._(this.ta, false);
                        final Qh qh = new Qh(continue.b(this.ta), continue.b(this.ta).b("strSelectColor"), continue.b(this.ta));
                        qh.show();
                        if (qh.getColor() != null) {
                            b3._(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                            b3.setColor(qh.getColor());
                            b3.b(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                        }
                        continue._(this.ta, true);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        synchronized (this.ta) {
            if (continue.b(this.ta)) {
                return;
            }
            if (this.ta.rka.b() instanceof try && continue._(this.ta)) {
                return;
            }
            if (continue.b(this.ta) != null) {
                continue.b(this.ta).a(this.ta.getGraphics(), continue.a(this.ta, continue._(this.ta)), continue.a(this.ta), this.ta._(), this.ta._(continue._(this.ta)));
                continue.b(this.ta, (UserDrawTool)null);
            }
            if (mouseEvent.isPopupTrigger()) {
                continue.b(this.ta, mouseEvent);
            }
        }
    }
}

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends a8
{
    MainChartApplet parent;
    public ak aj;
    
    public i(final MainChartApplet parent) {
        super(false, false, false, false);
        this.parent = parent;
        this.aj = new ak(parent._flddo);
        this.enableEvents(503L);
        this.enableEvents(500L);
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        this.aj.setBounds(0, 0, 105, 440);
        this.add(this.aj);
    }
    
    public void _mthbyte() {
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                boolean b = false;
                if ((mouseEvent.getY() > this.aj.b && mouseEvent.getY() < this.aj._fldfor && mouseEvent.getX() > this.aj.a && mouseEvent.getX() < this.aj._fldcase) || (mouseEvent.getY() > this.aj._flddo && mouseEvent.getY() < this.aj._fldchar && mouseEvent.getX() > this.aj._fldbyte && mouseEvent.getX() < this.aj._fldvoid)) {
                    this.setCursor(new Cursor(12));
                    b = true;
                }
                else {
                    for (int i = 0; i < this.aj._fldif.size(); ++i) {
                        final a5 a5 = this.aj._fldif.elementAt(i);
                        if (mouseEvent.getY() > a5._fldfor && mouseEvent.getY() < a5.a && mouseEvent.getX() > a5._fldtry && mouseEvent.getX() < a5._flddo) {
                            this.setCursor(new Cursor(12));
                            b = true;
                            break;
                        }
                    }
                }
                if (!b) {
                    this.setCursor(new Cursor(0));
                    break;
                }
                break;
            }
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 500:
            case 501:
            case 502: {
                if (mouseEvent.getY() > this.aj.b && mouseEvent.getY() < this.aj._fldfor && mouseEvent.getX() > this.aj.a && mouseEvent.getX() < this.aj._fldcase) {
                    this.parent.e._fldgoto._fldif = 1;
                    this.parent.e._fldgoto.an = 1;
                    this.parent.e._fldgoto.aR = 1;
                    this.parent.e._fldgoto.t = 1;
                    this.parent.e._fldgoto.F = 1;
                    this.parent.e._fldgoto.e = 1;
                    this.parent.e._fldgoto.aF = 1;
                    this.parent.e._fldgoto.A = 1;
                    this.parent.e._fldgoto.aC = 1;
                    this.parent.e._fldgoto.am = 1;
                    this.parent.e._fldgoto.a = 1;
                    this.parent.e._fldgoto.G = 1;
                    this.parent.e._fldgoto.a0 = 1;
                    this.parent.e._fldgoto.d = 1;
                    this.parent.e._fldgoto.a8 = 1;
                    this.parent.e._fldgoto.H = 1;
                    this.parent.e._fldgoto.a3 = 1;
                    this.parent.e._fldgoto.C = 1;
                    this.parent.e._fldgoto.T = 1;
                    this.parent.e._fldgoto.bc = 1;
                    this.parent.e._fldgoto.af = 1;
                    this.parent.e._fldgoto._fldchar = 1;
                    this.parent.e._fldgoto.aA = 1;
                    this.parent.e._fldgoto.ao = 1;
                    this.parent.e._fldgoto.aI = 1;
                    this.parent.e._fldgoto.u = 1;
                    this.parent.e._fldgoto.aw = 1;
                    this.parent.e._fldgoto._fldcase = 1;
                    this.parent.e._fldgoto.ae = 1;
                    this.parent.e._fldgoto.ac = 1;
                    this.parent.e._fldgoto.ai = 1;
                    this.parent.e._fldgoto.aw_ljjd = 1;
                    this.parent.e._fldgoto._fldcase_ljjn = 1;
                    this.parent.e._fldgoto.ae_ljjl = 1;
                    this.parent.e._fldgoto.ac_ljjt = 1;
                    this.parent.e._fldgoto.ai_ljjf = 1;
                    this.parent.e._fldgoto.ai_ljjh = 1;
                    this.parent.e._fldgoto.ai_ljjp = 1;
                    this.parent.e._fldgoto.ai_ljje = 1;
                    for (int i = 0; i < this.aj._fldif.size(); ++i) {
                        ((a5)this.aj._fldif.elementAt(i))._fldnew = true;
                    }
                    this.invalidate();
                    this.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.aj._flddo && mouseEvent.getY() < this.aj._fldchar && mouseEvent.getX() > this.aj._fldbyte && mouseEvent.getX() < this.aj._fldvoid) {
                    this.parent.e._fldgoto._fldif = 0;
                    this.parent.e._fldgoto.an = 0;
                    this.parent.e._fldgoto.aR = 0;
                    this.parent.e._fldgoto.t = 0;
                    this.parent.e._fldgoto.F = 0;
                    this.parent.e._fldgoto.e = 0;
                    this.parent.e._fldgoto.aF = 0;
                    this.parent.e._fldgoto.A = 0;
                    this.parent.e._fldgoto.aC = 0;
                    this.parent.e._fldgoto.am = 0;
                    this.parent.e._fldgoto.a = 0;
                    this.parent.e._fldgoto.G = 0;
                    this.parent.e._fldgoto.a0 = 0;
                    this.parent.e._fldgoto.d = 0;
                    this.parent.e._fldgoto.a8 = 0;
                    this.parent.e._fldgoto.H = 0;
                    this.parent.e._fldgoto.a3 = 0;
                    this.parent.e._fldgoto.C = 0;
                    this.parent.e._fldgoto.T = 0;
                    this.parent.e._fldgoto.bc = 0;
                    this.parent.e._fldgoto.af = 0;
                    this.parent.e._fldgoto._fldchar = 0;
                    this.parent.e._fldgoto.aA = 0;
                    this.parent.e._fldgoto.ao = 0;
                    this.parent.e._fldgoto.aI = 0;
                    this.parent.e._fldgoto.u = 0;
                    this.parent.e._fldgoto.aw = 0;
                    this.parent.e._fldgoto._fldcase = 0;
                    this.parent.e._fldgoto.ae = 0;
                    this.parent.e._fldgoto.ac = 0;
                    this.parent.e._fldgoto.ai = 0;
                    this.parent.e._fldgoto.aw_ljjd = 0;
                    this.parent.e._fldgoto._fldcase_ljjn = 0;
                    this.parent.e._fldgoto.ae_ljjl = 0;
                    this.parent.e._fldgoto.ac_ljjt = 0;
                    this.parent.e._fldgoto.ai_ljjf = 0;
                    this.parent.e._fldgoto.ai_ljjh = 0;
                    this.parent.e._fldgoto.ai_ljjp = 0;
                    this.parent.e._fldgoto.ai_ljje = 0;
                    for (int j = 0; j < this.aj._fldif.size(); ++j) {
                        ((a5)this.aj._fldif.elementAt(j))._fldnew = false;
                    }
                    this.invalidate();
                    this.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                for (int k = 0; k < this.aj._fldif.size(); ++k) {
                    final a5 a5 = this.aj._fldif.elementAt(k);
                    if (mouseEvent.getY() > a5._fldfor && mouseEvent.getY() < a5.a && mouseEvent.getX() > a5._fldtry) {
                        if (mouseEvent.getX() < a5._flddo) {
                            if (a5._fldif.equals("\uad50\ubcf4")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto._fldif = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto._fldif = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc2e0\ud55c")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.an = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.an = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub300\uc2e0")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aR = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aR = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub300\uc6b0")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.t = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.t = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub3d9\uc591")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.F = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.F = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub3d9\uc6d0")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.e = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.e = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub3d9\ubd80")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aF = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aF = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uba54\ub9ac\uce20")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.A = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.A = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ubbf8\ub798")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aC = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aC = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc0bc\uc131")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.am = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.am = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc720\uc9c4")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.a = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.a = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc2e0\uc601")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.G = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.G = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("NH")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.a0 = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.a0 = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ud558\uc774")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.d = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.d = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc6b0\ub9ac")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.a8 = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.a8 = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("SK")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.H = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.H = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc5d8\uc9c0")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.a3 = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.a3 = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ud55c\uad6d")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.e = 1;
                                    this.parent.e._fldgoto.C = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.e = 0;
                                    this.parent.e._fldgoto.C = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ud55c\ud654")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.T = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.T = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ud604\ub300")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.bc = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.bc = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uba54\ub9b4")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.af = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.af = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto._fldchar = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto._fldchar = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("JP")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aA = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aA = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ao = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ao = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("UBS")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aI = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aI = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("CSFB")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.u = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.u = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub9e5\ucffc\ub9ac")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aw = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aw = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("ABN\uc554\ub85c")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto._fldcase = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto._fldcase = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ac = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ac = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("CGM")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ai = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ai = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub2e4\uc774\uc640")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.aw_ljjd = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.aw_ljjd = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub178\ubb34\ub77c")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto._fldcase_ljjn = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto._fldcase_ljjn = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("RBS")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ae_ljjl = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ae_ljjl = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ub3c4\uc774\uce58")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ac_ljjt = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ac_ljjt = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("KB")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ai_ljjf = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ai_ljjf = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("HSBC")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ai_ljjh = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ai_ljjh = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\ud30c\ub9ac\ubc14")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ai_ljjp = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ai_ljjp = 0;
                                    a5._fldnew = false;
                                }
                            }
                            else if (a5._fldif.equals("\uc774\ud2b8\ub808\uc774\ub4dc")) {
                                if (!a5._fldnew) {
                                    this.parent.e._fldgoto.ai_ljje = 1;
                                    a5._fldnew = true;
                                }
                                else {
                                    this.parent.e._fldgoto.ai_ljje = 0;
                                    a5._fldnew = false;
                                }
                            }
                            this.invalidate();
                            this.repaint();
                            this.parent._mthif();
                            this.parent.e.invalidate();
                            this.parent.e.repaint();
                        }
                    }
                }
                break;
            }
        }
    }
}

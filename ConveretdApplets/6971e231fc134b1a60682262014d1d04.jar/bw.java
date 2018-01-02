import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class bw extends a8
{
    MainChartApplet parent;
    public Color _flddo;
    public ad ai;
    u ah;
    
    public bw(final MainChartApplet parent) {
        super(false, false, false, false);
        this.ai = new ad();
        this.ah = new u();
        this.parent = parent;
        this.enableEvents(503L);
        this.enableEvents(500L);
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        this.ai.setBounds(0, 0, 105, 180);
        this.add(this.ai);
        this.ah.setBounds(0, 180, 105, 260);
        this.add(this.ah);
    }
    
    public void _mthtry() {
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                boolean b = false;
                if ((mouseEvent.getY() > this.ai.r && mouseEvent.getY() < this.ai._fldcase && mouseEvent.getX() > this.ai.b && mouseEvent.getX() < this.ai.z) || (mouseEvent.getY() > this.ai.s && mouseEvent.getY() < this.ai._fldchar && mouseEvent.getX() > this.ai.c && mouseEvent.getX() < this.ai.B) || (mouseEvent.getY() > this.ai.s && mouseEvent.getY() < this.ai._fldchar && mouseEvent.getX() > this.ai.c && mouseEvent.getX() < this.ai.B) || (mouseEvent.getY() > this.ai.o && mouseEvent.getY() < this.ai._fldnew && mouseEvent.getX() > this.ai._fldelse && mouseEvent.getX() < this.ai.t) || (mouseEvent.getY() > this.ai._fldtry && mouseEvent.getY() < this.ai.p && mouseEvent.getX() > this.ai.v && mouseEvent.getX() < this.ai._fldlong) || (mouseEvent.getY() > this.ai.q && mouseEvent.getY() < this.ai._fldbyte && mouseEvent.getX() > this.ai._fldnull && mouseEvent.getX() < this.ai.w) || (mouseEvent.getY() > this.ai.e && mouseEvent.getY() < this.ai.C && mouseEvent.getX() > this.ai.G && mouseEvent.getX() < this.ai.j) || (mouseEvent.getY() > this.ai.n && mouseEvent.getY() < this.ai._fldint && mouseEvent.getX() > this.ai._fldgoto && mouseEvent.getX() < this.ai.u) || (mouseEvent.getY() > this.ai.A && mouseEvent.getY() < this.ai.d && mouseEvent.getX() > this.ai.i && mouseEvent.getX() < this.ai.F) || (mouseEvent.getY() > this.ai.h && mouseEvent.getY() < this.ai.E && mouseEvent.getX() > this.ai.a && mouseEvent.getX() < this.ai.k)) {
                    this.setCursor(new Cursor(12));
                    b = true;
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
                if (mouseEvent.getY() > this.ai.r && mouseEvent.getY() < this.ai._fldcase && mouseEvent.getX() > this.ai.b && mouseEvent.getX() < this.ai.z) {
                    if (!this.ai.D) {
                        this.ai.D = true;
                        this.parent.e._fldgoto.az = 1;
                    }
                    else {
                        this.ai.D = false;
                        this.parent.e._fldgoto.az = 0;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.s && mouseEvent.getY() < this.ai._fldchar && mouseEvent.getX() > this.ai.c && mouseEvent.getX() < this.ai.B) {
                    if (!this.ai.f) {
                        this.ai.f = true;
                        this.parent.e._fldgoto.aD = 1;
                    }
                    else {
                        this.ai.f = false;
                        this.parent.e._fldgoto.aD = 0;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.o && mouseEvent.getY() < this.ai._fldnew && mouseEvent.getX() > this.ai._fldelse && mouseEvent.getX() < this.ai.t) {
                    if (!this.ai._flddo) {
                        this.ai._flddo = true;
                        this.parent.e._fldgoto.aZ = 1;
                    }
                    else {
                        this.ai._flddo = false;
                        this.parent.e._fldgoto.aZ = 0;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai._fldtry && mouseEvent.getY() < this.ai.p && mouseEvent.getX() > this.ai.v && mouseEvent.getX() < this.ai._fldlong) {
                    if (!this.ai._fldvoid) {
                        this.ai._fldvoid = true;
                        this.parent.e._fldgoto.bh = 1;
                    }
                    else {
                        this.ai._fldvoid = false;
                        this.parent.e._fldgoto.bh = 0;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.q && mouseEvent.getY() < this.ai._fldbyte && mouseEvent.getX() > this.ai._fldnull && mouseEvent.getX() < this.ai.w) {
                    if (!this.ai.l) {
                        this.ai.l = true;
                        this.parent.e._fldgoto._fldvoid = 1;
                    }
                    else {
                        this.ai.l = false;
                        this.parent.e._fldgoto._fldvoid = 0;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.e && mouseEvent.getY() < this.ai.C && mouseEvent.getX() > this.ai.G && mouseEvent.getX() < this.ai.j) {
                    if (!this.ai.m) {
                        this.ai.m = true;
                        this.ah._fldint.a("\uc8fc\uac00\uc774\ud3c9");
                    }
                    else {
                        this.ai.m = false;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.n && mouseEvent.getY() < this.ai._fldint && mouseEvent.getX() > this.ai._fldgoto && mouseEvent.getX() < this.ai.u) {
                    if (!this.ai._fldif) {
                        this.ai._fldif = true;
                        this.ah._fldint.a("Parabolic");
                    }
                    else {
                        this.ai._fldif = false;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.A && mouseEvent.getY() < this.ai.d && mouseEvent.getX() > this.ai.i && mouseEvent.getX() < this.ai.F) {
                    if (!this.ai._fldfor) {
                        this.ai._fldfor = true;
                        this.ah._fldint.a("Envelope");
                    }
                    else {
                        this.ai._fldfor = false;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                if (mouseEvent.getY() > this.ai.h && mouseEvent.getY() < this.ai.E && mouseEvent.getX() > this.ai.a && mouseEvent.getX() < this.ai.k) {
                    if (!this.ai.g) {
                        this.ai.g = true;
                        this.ah._fldint.a("Band%B Osc");
                    }
                    else {
                        this.ai.g = false;
                    }
                    this.ai.repaint();
                    this.parent._mthif();
                    this.parent.e.invalidate();
                    this.parent.e.repaint();
                    break;
                }
                break;
            }
        }
    }
}

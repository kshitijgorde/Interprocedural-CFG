import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import netscape.javascript.JSObject;
import java.awt.Cursor;
import java.awt.Point;
import java.text.DecimalFormat;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.CheckboxMenuItem;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;

// 
// Decompiled by Procyon v0.5.30
// 

class ab extends a8 implements AdjustmentListener
{
    public static int _fldtry;
    public static int _fldchar;
    public static int _fldnull;
    public static boolean c;
    MainChartApplet parent;
    public w _fldgoto;
    public w _fldcase;
    Scrollbar _fldvoid;
    Button _fldbyte;
    Button _fldelse;
    b2 d;
    public String checkcomp;
    LayoutManager _fldlong;
    CheckboxMenuItem b;
    
    public ab(final MainChartApplet parent) {
        super(false, false, false, false);
        this._fldgoto = new w(this);
        this._fldcase = new w(this);
        this._fldvoid = new Scrollbar(0);
        this._fldbyte = new Button("\ud655 \ub300");
        this._fldelse = new Button("\ucd95 \uc18c");
        this._fldlong = new GridBagLayout();
        this.parent = parent;
        this._fldvoid.addAdjustmentListener(this);
        this.setBackground(Color.white);
        this.enableEvents(503L);
        this._fldgoto.aK = true;
        this._fldgoto._mthif(true);
        this._fldcase._mthif(false);
        this._fldgoto.a(true);
        this._fldcase.a(true);
        this.setLayout(null);
        this._fldgoto.setBounds(0, 0, 630, 282);
        this.add(this._fldgoto);
        this._fldcase.setBounds(0, 282, 630, 105);
        this.add(this._fldcase);
        this._fldvoid.setBounds(60, 387, 390, 16);
        this.add(this._fldvoid);
        this._fldbyte.setBounds(450, 387, 60, 16);
        this.add(this._fldbyte);
        this._fldelse.setBounds(511, 387, 60, 16);
        this.add(this._fldelse);
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
        this._fldgoto.invalidate();
        this._fldcase.invalidate();
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this._fldgoto.s == null) {
            return;
        }
        if (this._fldgoto.s.isEmpty()) {
            return;
        }
        final int blockIncrement = this._mthif() - this._mthint() + 1;
        final int size = this._fldgoto.s.size();
        this._fldvoid.setValues(this._mthint(), blockIncrement, 0, size);
        this._fldvoid.setBlockIncrement(blockIncrement);
        this._fldvoid.setUnitIncrement(1);
        this._fldvoid.setEnabled(blockIncrement < size);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                final int mthif = this._mthif(mouseEvent.getX());
                boolean b = false;
                if (mthif >= 0 && mthif < this._fldgoto.au._fldint.size()) {
                    final at at = this._fldgoto.au._fldint.elementAt(mthif);
                    this.parent._fldgoto.m.setText(new String(new StringBuffer().append(at.a.substring(0, 4)).append("/").append(at.a.substring(4, 6)).append("/").append(at.a.substring(6, 8)).toString()));
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
                    if (this.parent.a.P.compareTo(new String("index")) == 0) {
                        decimalFormat = new DecimalFormat("###,##0.0#");
                    }
                    this.parent._fldgoto.f.setText(decimalFormat.format(at._flddo));
                    this.parent._fldgoto.e.setText(decimalFormat.format(at._fldnew));
                    this.parent._fldgoto.h.setText(decimalFormat.format(at._fldif));
                    this.parent._fldgoto.o.setText(decimalFormat.format(at._fldint));
                    this.parent._fldgoto.z.setText(new DecimalFormat("###,###,##0").format(at._fldfor));
                    if (ab.c) {
                        b = true;
                        if (mthif != ab._fldnull || mouseEvent.getY() < ab._fldtry || mouseEvent.getY() > ab._fldchar) {
                            this._mthdo();
                            this.repaint();
                            ab.c = false;
                            ab._fldtry = -1;
                            ab._fldchar = -1;
                            ab._fldnull = -1;
                            b = false;
                        }
                    }
                    else {
                        String s = null;
                        int fldtry = 0;
                        int fldchar = 0;
                        b = false;
                        if (at._fldcase.size() > 0) {
                            for (int i = 0; i < at._fldcase.size(); ++i) {
                                final au au = at._fldcase.elementAt(i);
                                if ((au.j.compareTo("\uad50\ubcf4\uc99d\uad8c") != 0 || this.parent.e._fldgoto._fldif != 0) && (au.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") != 0 || this.parent.e._fldgoto.an != 0) && (au.j.compareTo("\ub300\uc2e0\uc99d\uad8c") != 0 || this.parent.e._fldgoto.aR != 0) && (au.j.compareTo("\ub300\uc6b0\uc99d\uad8c") != 0 || this.parent.e._fldgoto.t != 0) && (au.j.compareTo("\ub3d9\uc591\uc99d\uad8c") != 0 || this.parent.e._fldgoto.F != 0) && (au.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") != 0 || this.parent.e._fldgoto.e != 0) && (au.j.compareTo("\ub3d9\ubd80\uc99d\uad8c") != 0 || this.parent.e._fldgoto.aF != 0) && (au.j.compareTo("\uba54\ub9ac\uce20\uc99d\uad8c") != 0 || this.parent.e._fldgoto.A != 0) && (au.j.compareTo("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c") != 0 || this.parent.e._fldgoto.aC != 0) && (au.j.compareTo("\uc0bc\uc131\uc99d\uad8c") != 0 || this.parent.e._fldgoto.am != 0) && (au.j.compareTo("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c") != 0 || this.parent.e._fldgoto.a != 0) && (au.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") != 0 || this.parent.e._fldgoto.a != 0) && (au.j.compareTo("\uc2e0\uc601\uc99d\uad8c") != 0 || this.parent.e._fldgoto.G != 0) && (au.j.compareTo("NH\uc99d\uad8c") != 0 || this.parent.e._fldgoto.a0 != 0) && (au.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") != 0 || this.parent.e._fldgoto.d != 0) && (au.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") != 0 || this.parent.e._fldgoto.a8 != 0) && (au.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") != 0 || this.parent.e._fldgoto.H != 0) && (au.j.compareTo("\uc5d8\uc9c0\uc99d\uad8c") != 0 || this.parent.e._fldgoto.a3 != 0) && (au.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") != 0 || this.parent.e._fldgoto.C != 0) && (au.j.compareTo("\ud55c\ud654\uc99d\uad8c") != 0 || this.parent.e._fldgoto.T != 0) && (au.j.compareTo("\ud604\ub300\uc99d\uad8c") != 0 || this.parent.e._fldgoto.bc != 0) && (au.j.compareTo("\uba54\ub9b4\ub9b0\uce58") != 0 || this.parent.e._fldgoto.af != 0) && (au.j.compareTo("\uace8\ub4dc\ub9cc\uc0ad\uc2a4") != 0 || this.parent.e._fldgoto._fldchar != 0) && (au.j.compareTo("JP\ubaa8\uac74") != 0 || this.parent.e._fldgoto.aA != 0) && (au.j.compareTo("\ubaa8\uac74\uc2a4\ud0e0\ub9ac") != 0 || this.parent.e._fldgoto.ao != 0) && (au.j.compareTo("UBS") != 0 || this.parent.e._fldgoto.aI != 0) && (au.j.compareTo("CSFB") != 0 || this.parent.e._fldgoto.u != 0) && (au.j.compareTo("\ub9e5\ucffc\ub9ac") != 0 || this.parent.e._fldgoto.aw != 0) && (au.j.compareTo("ABN\uc554\ub85c") != 0 || this.parent.e._fldgoto._fldcase != 0) && (au.j.compareTo("\ud06c\ub808\ub514\ub9ac\uc694\ub124") != 0 || this.parent.e._fldgoto.ac != 0) && (au.j.compareTo("CGM") != 0 || this.parent.e._fldgoto.ai != 0) && (au.j.compareTo("\ub2e4\uc774\uc640") != 0 || this.parent.e._fldgoto.aw_ljjd != 0) && (au.j.compareTo("\ub178\ubb34\ub77c") != 0 || this.parent.e._fldgoto._fldcase_ljjn != 0) && (au.j.compareTo("RBS") != 0 || this.parent.e._fldgoto.ae_ljjl != 0) && (au.j.compareTo("\ub3c4\uc774\uce58") != 0 || this.parent.e._fldgoto.ac_ljjt != 0) && (au.j.compareTo("KB\uc99d\uad8c") != 0 || this.parent.e._fldgoto.ai_ljjf != 0) && (au.j.compareTo("HSBC") != 0 || this.parent.e._fldgoto.ai_ljjh != 0) && (au.j.compareTo("\ud30c\ub9ac\ubc14") != 0 || this.parent.e._fldgoto.ai_ljjp != 0)) {
                                    if (au.j.compareTo("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c") != 0 || this.parent.e._fldgoto.ai_ljje != 0) {
                                        if (this._fldgoto.aD == 1 && mouseEvent.getY() > au.c && mouseEvent.getY() < au.f) {
                                            this.checkcomp = au.j;
                                            s = au.a(2);
                                            b = true;
                                            fldtry = au.c;
                                            fldchar = au.f;
                                        }
                                        if (this._fldgoto.aZ == 1 && mouseEvent.getY() > au._fldint && mouseEvent.getY() < au._fldtry) {
                                            this.checkcomp = au.j;
                                            s = au.a(3);
                                            b = true;
                                            fldtry = au._fldint;
                                            fldchar = au._fldtry;
                                        }
                                        if (this._fldgoto.bh == 1 && mouseEvent.getY() > au._fldcase && mouseEvent.getY() < au._fldnull) {
                                            this.checkcomp = au.j;
                                            s = au.a(4);
                                            b = true;
                                            fldtry = au._fldcase;
                                            fldchar = au._fldnull;
                                        }
                                        if (this._fldgoto._fldvoid == 1 && mouseEvent.getY() > au._fldvoid && mouseEvent.getY() < au.b) {
                                            this.checkcomp = au.j;
                                            s = au.a(5);
                                            b = true;
                                            fldtry = au._fldvoid;
                                            fldchar = au.b;
                                        }
                                        if (this._fldgoto.az == 1 && mouseEvent.getY() > au._fldbyte) {
                                            if (mouseEvent.getY() < au._fldelse) {
                                                if (this.parent.a.P.compareTo(new String("item")) == 0) {
                                                    this.checkcomp = au.j;
                                                    s = au.a(1);
                                                    b = true;
                                                    fldtry = au._fldbyte;
                                                    fldchar = au._fldelse;
                                                }
                                                else if (this.parent.a.P.compareTo(new String("index")) == 0) {
                                                    this.checkcomp = au.j;
                                                    s = au.a(0);
                                                    b = true;
                                                    fldtry = au._fldbyte;
                                                    fldchar = au._fldelse;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (b) {
                                ab._fldnull = mthif;
                                if (s == null) {
                                    break;
                                }
                                if (ab._fldtry != fldtry && ab._fldchar != fldchar) {
                                    this.a(s, new Point(this._mthint(mouseEvent.getX()), mouseEvent.getY()));
                                    ab._fldtry = fldtry;
                                    ab._fldchar = fldchar;
                                    ab.c = true;
                                }
                            }
                        }
                    }
                }
                if (b) {
                    this.setCursor(new Cursor(12));
                    break;
                }
                this.setCursor(new Cursor(0));
                break;
            }
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 500:
            case 501:
            case 502: {
                final int mthif = this._mthif(mouseEvent.getX());
                if (mthif >= 0 && mthif < this._fldgoto.au._fldint.size()) {
                    final at at = this._fldgoto.au._fldint.elementAt(mthif);
                    for (int i = 0; i < at._fldcase.size(); ++i) {
                        final au au = at._fldcase.elementAt(i);
                        if (this.checkcomp.equals(au.j)) {
                            if (this.parent.a.P.compareTo(new String("item")) == 0) {
                                try {
                                    final String s = new String(this.parent.a._fldtry);
                                    String s2;
                                    if (s.compareTo("u") == 0) {
                                        s2 = new StringBuffer().append("http://consensus.hankyung.com/").append(this.parent._flddo).append("/analysis/foreign/item_board.php?c_code=").append(s).append("&func=view&cur_seq=").append(String.valueOf(au.d)).toString();
                                    }
                                    else {
                                        s2 = new StringBuffer().append("http://consensus.hankyung.com/").append(this.parent._flddo).append("/analysis/item/item_board.php?c_code=").append(s).append("&func=view&cur_seq=").append(String.valueOf(au.d)).toString();
                                    }
                                    ((JSObject)this.parent._fldvoid.call("open", new String[] { s2, "item_board", "scrollbars=yes, optionPanelbar=no" })).call("focus", null);
                                }
                                catch (Exception ex) {
                                    System.out.println("start_win open failed!!");
                                }
                                break;
                            }
                            if (this.parent.a.P.compareTo(new String("index")) == 0) {
                                try {
                                    final String s3 = new String(this.parent.a._fldtry);
                                    if (s3.startsWith("M")) {
                                        ((JSObject)this.parent._fldvoid.call("open", new String[] { new StringBuffer().append("http://consensus.hankyung.com/").append(this.parent._flddo).append("/analysis/market/market_board.php?func=view&cur_seq=").append(String.valueOf(au.d)).toString(), "market_board", "scrollbars=yes, optionPanelbar=no" })).call("focus", null);
                                    }
                                    else if (s3.startsWith("B")) {
                                        ((JSObject)this.parent._fldvoid.call("open", new String[] { new StringBuffer().append("http://consensus.hankyung.com/").append(this.parent._flddo).append("/analysis/industry/industry_board.php?i_code=").append(s3).append("&func=view&cur_seq=").append(String.valueOf(au.d)).toString(), "industry_board", "scrollbars=yes, optionPanelbar=no" })).call("focus", null);
                                    }
                                }
                                catch (Exception ex2) {
                                    System.out.println("start_win open failed!!");
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this._mthfor(adjustmentEvent.getValue() + this._fldvoid.getVisibleAmount() - 1);
        this.invalidate();
        this.repaint();
    }
    
    public void a(final int n, final int n2) {
        final Dimension size = this._fldgoto.getSize();
        this._fldcase.getSize();
        final Graphics graphics = this.getGraphics();
        graphics.setColor(Color.red);
        graphics.drawLine(0, n2, size.width - 1, n2);
    }
    
    public int _mthif(final int n) {
        if (this._fldgoto.aU == null) {
            return -1;
        }
        return this._mthint() + (n - this._fldgoto.aU.x + 1) / this._mthfor();
    }
    
    public int _mthint(final int n) {
        return n + (3 - (n - this._fldgoto.aU.x + 1) % this._mthfor());
    }
    
    public void a(final String s, final Point point) {
        this.d = new b2(this, s, point);
    }
    
    public void _mthdo() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }
    
    public boolean a() {
        return this._fldgoto.z;
    }
    
    void a(final boolean b) {
        this._fldgoto.z = b;
        this._fldcase.z = b;
    }
    
    public int _mthfor() {
        return this._fldgoto.L;
    }
    
    public int _mthint() {
        return this._fldgoto.aN;
    }
    
    public int _mthif() {
        return this._fldgoto.bf;
    }
    
    public void _mthdo(final int n) {
        this._fldgoto.L = n;
        this._fldcase.L = n;
    }
    
    public void a(final int n) {
        this._fldgoto.aN = n;
        this._fldcase.aN = n;
    }
    
    public void _mthfor(final int n) {
        this._fldgoto.bf = n;
        this._fldcase.bf = n;
    }
    
    static {
        ab._fldtry = -1;
        ab._fldchar = -1;
        ab._fldnull = -1;
        ab.c = false;
    }
}

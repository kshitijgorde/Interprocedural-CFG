import java.util.Vector;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Observer;
import java.applet.Applet;
import java.awt.Color;
import netscape.javascript.JSObject;
import java.awt.event.ItemListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class MainChartApplet extends cb implements ActionListener, AdjustmentListener, ItemListener
{
    public static final int _fldtry = 840;
    public static final int f = 440;
    public static final int j = 105;
    public static final int g = 440;
    public static final int _fldbyte = 180;
    public static final int c = 260;
    public static final int _fldcase = 440;
    public static final int _fldnew = 105;
    public static final int _fldchar = 36;
    public static final int b = 630;
    public static final int _fldlong = 630;
    public static final int _fldelse = 404;
    public static final int d = 60;
    public static final int _fldnull = 60;
    public af _fldgoto;
    bw h;
    ab e;
    i i;
    public JSObject _fldvoid;
    
    public MainChartApplet() {
        this._fldgoto = new af(this);
        this.h = new bw(this);
        this.e = new ab(this);
    }
    
    @Override
    public void init() {
        super.init();
        this.i = new i(this);
        this.setBackground(Color.white);
        try {
            this._fldvoid = JSObject.getWindow(this);
        }
        catch (Exception ex) {
            System.out.println("JSObject \uc124\uc815 \uc2e4\ud328");
        }
        this.e._fldgoto.N.addObserver(this);
        this.e._fldcase.N.addObserver(this);
        this.h.ah._fldnew.ad.addItemListener(this);
        this.e._fldbyte.addActionListener(this);
        this.e._fldelse.addActionListener(this);
        this.h.ah._fldint.ab._fldnew.addItemListener(this);
        this.h.ah._fldint.ab._fldint.addItemListener(this);
        this.h.ah._fldint.ab._fldif.addItemListener(this);
        this.h.ah._fldint.ab._fldcase.addItemListener(this);
        this.h.ah._fldint.ab._flddo.addAdjustmentListener(this);
        this.h.ah._fldint.ab._fldelse.addAdjustmentListener(this);
        this.h.ah._fldint.ab._fldbyte.addAdjustmentListener(this);
        this.h.ah._fldint.ab._fldtry.addAdjustmentListener(this);
        this.h.ah._fldint.I._fldnew.addItemListener(this);
        this.h.ah._fldint.I._fldint.addItemListener(this);
        this.h.ah._fldint.I._fldif.addItemListener(this);
        this.h.ah._fldint.I._fldfor.addAdjustmentListener(this);
        this.h.ah._fldint.I._fldcase.addAdjustmentListener(this);
        this.h.ah._fldint.I._fldtry.addAdjustmentListener(this);
        this.h.ah._fldint.X.a.addAdjustmentListener(this);
        this.h.ah._fldint.X._fldfor.addAdjustmentListener(this);
        this.h.ah._fldint.O._flddo.addAdjustmentListener(this);
        this.h.ah._fldint.O._fldtry.addAdjustmentListener(this);
        this.h.ah._fldint.O._fldif.addAdjustmentListener(this);
        this.h.ah._fldint.F._fldbyte.addItemListener(this);
        this.h.ah._fldint.F._fldif.addItemListener(this);
        this.h.ah._fldint.F._fldcase.addItemListener(this);
        this.h.ah._fldint.F._flddo.addAdjustmentListener(this);
        this.h.ah._fldint.Y.a.addAdjustmentListener(this);
        this.h.ah._fldint.L._flddo.addItemListener(this);
        this.h.ah._fldint.L._fldtry.addItemListener(this);
        this.h.ah._fldint.L._fldcase.addItemListener(this);
        this.h.ah._fldint.L.a.addAdjustmentListener(this);
        this.h.ah._fldint.L._fldint.addAdjustmentListener(this);
        this.h.ah._fldint.L._fldnew.addAdjustmentListener(this);
        this.h.ah._fldint.Z.a.addAdjustmentListener(this);
        this.h.ah._fldint.P.a.addAdjustmentListener(this);
        this.h.ah._fldint.W._fldfor.addAdjustmentListener(this);
        this.h.ah._fldint.W._flddo.addAdjustmentListener(this);
        this.h.ah._fldint.W._fldbyte.addAdjustmentListener(this);
        this.h.ah._fldint.K.a.addAdjustmentListener(this);
        this.h.ah._fldint.S.a.addAdjustmentListener(this);
        this.h.ah._fldint.U.a.addAdjustmentListener(this);
        this.h.ah._fldint.G.a.addAdjustmentListener(this);
        this.h.ah._fldint.V.a.addAdjustmentListener(this);
        this.h.ah._fldint.T.a.addAdjustmentListener(this);
        this.h.ah._fldint.J.a.addAdjustmentListener(this);
        this.h.ah._fldint.R.a.addAdjustmentListener(this);
        this.h.ah._fldint.R._fldfor.addAdjustmentListener(this);
        this.h.ah._fldint.E._flddo.addAdjustmentListener(this);
        this.h.ah._fldint.C._fldif.addAdjustmentListener(this);
        this.h.ah._fldint.C.a.addAdjustmentListener(this);
        this.h.ah._fldint.N.a.addAdjustmentListener(this);
        this.h.ah._fldint.N._fldfor.addAdjustmentListener(this);
        this.setLayout(null);
        this._fldgoto.setBounds(105, 0, 630, 36);
        this.add(this._fldgoto);
        this.h.setBounds(0, 0, 105, 440);
        this.add(this.h);
        this.i.setBounds(735, 0, 105, 440);
        this.add(this.i);
        this.e.setBounds(105, 37, 630, 403);
        this.add(this.e);
        if (super.a.P.compareTo(new String("item")) == 0) {
            this.e._fldgoto.h = 1;
        }
        else if (super.a.P.compareTo(new String("index")) == 0) {
            this.e._fldgoto.h = 2;
        }
        else {
            this.e._fldgoto.h = 0;
        }
        this.e._fldgoto.a(new bc(super.a.K));
        this.e._fldgoto.a(2);
        this._mthif();
    }
    
    @Override
    public void start() {
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public void destroy() {
        super.destroy();
        this.e._fldgoto.N.deleteObserver(this);
        this.e._fldcase.N.deleteObserver(this);
        this.remove(this._fldgoto);
        this.remove(this.e);
        this.remove(this.h);
    }
    
    @Override
    public String getAppletInfo() {
        return "MainChartApplet v0.6 Copyright (c) 2002, C-cube Technology";
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.e._fldbyte)) {
            int mthfor = this.e._mthfor();
            if (mthfor == 2) {
                mthfor = 3;
            }
            else if (mthfor == 3) {
                mthfor = 4;
            }
            else if (mthfor == 4) {
                mthfor = 6;
            }
            else if (mthfor == 6) {
                mthfor = 12;
            }
            else if (mthfor == 12) {
                mthfor = 24;
            }
            this.e._mthdo(mthfor);
            this.e.invalidate();
            this.e.repaint();
        }
        else if (actionEvent.getSource().equals(this.e._fldelse)) {
            int mthfor2 = this.e._mthfor();
            if (mthfor2 == 3) {
                mthfor2 = 2;
            }
            else if (mthfor2 == 4) {
                mthfor2 = 3;
            }
            else if (mthfor2 == 6) {
                mthfor2 = 4;
            }
            else if (mthfor2 == 12) {
                mthfor2 = 6;
            }
            else if (mthfor2 == 24) {
                mthfor2 = 12;
            }
            this.e._mthdo(mthfor2);
            this.e.invalidate();
            this.e.repaint();
        }
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource().equals(this.h.ah._fldint.ab._flddo)) {
            this.h.ah._fldint.ab._fldnew.setLabel(new StringBuffer().append("\ub2e8\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.ab._fldelse)) {
            this.h.ah._fldint.ab._fldint.setLabel(new StringBuffer().append("\uc911\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.ab._fldbyte)) {
            this.h.ah._fldint.ab._fldif.setLabel(new StringBuffer().append("\uc7a5\uae301 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.ab._fldtry)) {
            this.h.ah._fldint.ab._fldcase.setLabel(new StringBuffer().append("\uc7a5\uae302 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.I._fldfor)) {
            this.h.ah._fldint.I._fldnew.setLabel(new StringBuffer().append("\ub2e8\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.I._fldcase)) {
            this.h.ah._fldint.I._fldint.setLabel(new StringBuffer().append("\uc911\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.I._fldtry)) {
            this.h.ah._fldint.I._fldif.setLabel(new StringBuffer().append("\uc7a5\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.X.a)) {
            this.h.ah._fldint.X._fldif.setText(new StringBuffer().append("\ub2e8\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.X._fldfor)) {
            this.h.ah._fldint.X._fldint.setText(new StringBuffer().append("\uc7a5\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.O._flddo)) {
            this.h.ah._fldint.O._fldcase.setText(new StringBuffer().append("\ub2e8\uae30 (").append(adjustmentEvent.getValue()).append(super.a.Check_Str).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.O._fldtry)) {
            this.h.ah._fldint.O._fldfor.setText(new StringBuffer().append("\uc7a5\uae30 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.O._fldif)) {
            this.h.ah._fldint.O._fldint.setText(new StringBuffer().append("\uc774\ud3c9 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.F._flddo)) {
            this.h.ah._fldint.F._fldfor.setText(new StringBuffer().append("\uc774\ud3c9 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.Y.a)) {
            this.h.ah._fldint.Y._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.L.a)) {
            this.h.ah._fldint.L._flddo.setLabel(new StringBuffer().append("RawK (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.L._fldint)) {
            this.h.ah._fldint.L._fldtry.setLabel(new StringBuffer().append("%K (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.L._fldnew)) {
            this.h.ah._fldint.L._fldcase.setLabel(new StringBuffer().append("%D (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.Z.a)) {
            this.h.ah._fldint.Z._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.P.a)) {
            this.h.ah._fldint.P._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.W._fldfor)) {
            this.h.ah._fldint.W._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.W._flddo)) {
            this.h.ah._fldint.W._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.W._fldbyte)) {
            this.h.ah._fldint.W.a.setText(new StringBuffer().append("SonSig (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.K.a)) {
            this.h.ah._fldint.K._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.S.a)) {
            this.h.ah._fldint.S._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.U.a)) {
            this.h.ah._fldint.U._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.G.a)) {
            this.h.ah._fldint.G._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.V.a)) {
            this.h.ah._fldint.V._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.T.a)) {
            this.h.ah._fldint.T._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.J.a)) {
            this.h.ah._fldint.J._fldif.setText(new StringBuffer().append("\uae30\uac04 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.R.a)) {
            this.h.ah._fldint.R._fldif.setText(new StringBuffer().append("\uc774\ud3c9 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.R._fldfor)) {
            this.h.ah._fldint.R._fldint.setText(new StringBuffer().append("\uc774\ud3c9 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.E._flddo)) {
            this.h.ah._fldint.E._fldif.setText(new StringBuffer().append("\uac00\uc18d\ub3c4(").append(new DecimalFormat("#,##0.###").format(adjustmentEvent.getValue() / 1000.0)).append(")").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.C._fldif)) {
            this.h.ah._fldint.C._fldint.setText(new StringBuffer().append("\uc774\ud3c9 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.C.a)) {
            this.h.ah._fldint.C._fldnew.setText(new StringBuffer().append("\uc0c1\ud558\ud55c (").append(adjustmentEvent.getValue()).append("%)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.N.a)) {
            this.h.ah._fldint.N._fldint.setText(new StringBuffer().append("\uc774\ud3c9 (").append(adjustmentEvent.getValue()).append("\uc77c)").toString());
        }
        else if (adjustmentEvent.getSource().equals(this.h.ah._fldint.N._fldfor)) {
            this.h.ah._fldint.N._fldtry.setText(new StringBuffer().append("\ud3b8\ucc28 (").append(adjustmentEvent.getValue()).append("\u03c3)").toString());
        }
        this.a();
        this.e.invalidate();
        this.e.repaint();
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (((Component)itemEvent.getSource()).equals(this.h.ah._fldnew.ad)) {
            String string = itemEvent.getItem().toString();
            if (string.equals("OBV")) {
                string = "Empty";
            }
            this.h.ah._fldint.a(string);
            this._mthif();
            this.e.invalidate();
            this.e.doLayout();
            this.e.repaint();
        }
    }
    
    public void _mthif() {
        final Vector<b6> vector = new Vector<b6>();
        final Vector vector2 = new Vector();
        final Vector<bg> vector3 = new Vector<bg>();
        final Vector vector4 = new Vector();
        if (this.h.ai.m) {
            if (this.h.ah._fldint.ab._fldnew.getState()) {
                vector.addElement(new b6(m._fldgoto, 0, super.a.z.elementAt(7)));
            }
            if (this.h.ah._fldint.ab._fldint.getState()) {
                vector.addElement(new b6(m._fldchar, 0, super.a.z.elementAt(8)));
            }
            if (this.h.ah._fldint.ab._fldif.getState()) {
                vector.addElement(new b6(m.a, 0, super.a.z.elementAt(9)));
            }
            if (this.h.ah._fldint.ab._fldcase.getState()) {
                vector.addElement(new b6(m._fldfor, 0, super.a.z.elementAt(10)));
            }
        }
        if (this.h.ai._fldif) {
            vector3.addElement(new bg(br._fldfor, 3, super.a.z.elementAt(14)));
        }
        if (this.h.ai._fldfor) {
            vector.addElement(new b6(r._fldfor, 0, super.a.z.elementAt(15)));
            vector.addElement(new b6(r._flddo, 0, super.a.z.elementAt(16)));
            vector.addElement(new b6(r._fldtry, 0, super.a.z.elementAt(17)));
        }
        if (this.h.ai.g) {
            vector.addElement(new b6(v._flddo, 0, super.a.z.elementAt(18)));
            vector.addElement(new b6(v._fldif, 0, super.a.z.elementAt(19)));
            vector.addElement(new b6(v._fldbyte, 0, super.a.z.elementAt(20)));
        }
        this.e._fldgoto._mthfor(vector.isEmpty() ? null : vector);
        this.e._fldgoto._mthdo(vector2.isEmpty() ? null : vector2);
        this.e._fldgoto.a(vector3.isEmpty() ? null : vector3);
        final Vector<b6> vector5 = new Vector<b6>();
        final Vector<bh> vector6 = new Vector<bh>();
        final Vector vector7 = new Vector();
        final Vector vector8 = new Vector();
        this.e._fldcase.setVisible(true);
        this.e._fldcase.aW = true;
        if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[0])) {
            this.e._fldcase.a(1);
            vector6.addElement(new bh(ca._flddo, super.a.z.elementAt(1)));
            if (this.h.ah._fldint.I._fldnew.getState()) {
                vector5.addElement(new b6(ca._fldchar, 0, super.a.z.elementAt(11)));
            }
            if (this.h.ah._fldint.I._fldint.getState()) {
                vector5.addElement(new b6(ca._fldbyte, 0, super.a.z.elementAt(12)));
            }
            if (this.h.ah._fldint.I._fldif.getState()) {
                vector5.addElement(new b6(ca.a, 0, super.a.z.elementAt(13)));
            }
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[1])) {
            this.e._fldcase.a(2);
            vector6.addElement(new bh(bj._flddo, super.a.z.elementAt(21)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[2])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(bl._fldbyte, 0, super.a.z.elementAt(22)));
            vector5.addElement(new b6(bl._fldnew, 0, super.a.z.elementAt(23)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[3])) {
            this.e._fldcase.a(2);
            vector6.addElement(new bh(bl.a, super.a.z.elementAt(24)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[4])) {
            this.e._fldcase.a(2);
            if (this.h.ah._fldint.F._fldbyte.getState()) {
                vector5.addElement(new b6(l._fldnew, 0, super.a.z.elementAt(25)));
            }
            if (this.h.ah._fldint.F._fldif.getState()) {
                vector5.addElement(new b6(l._fldint, 0, super.a.z.elementAt(26)));
            }
            if (this.h.ah._fldint.F._fldcase.getState()) {
                vector5.addElement(new b6(l._fldtry, 0, super.a.z.elementAt(27)));
            }
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[5])) {
            this.e._fldcase.a(2);
            vector6.addElement(new bh(l.a, super.a.z.elementAt(28)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[6])) {
            this.e._fldcase.a(3);
            vector5.addElement(new b6(ax._flddo, 0, super.a.z.elementAt(29)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[7])) {
            this.e._fldcase.a(3);
            if (this.h.ah._fldint.L._flddo.getState()) {
                vector5.addElement(new b6(bo._fldfor, 0, super.a.z.elementAt(30)));
            }
            if (this.h.ah._fldint.L._fldtry.getState()) {
                vector5.addElement(new b6(bo._fldbyte, 0, super.a.z.elementAt(31)));
            }
            if (this.h.ah._fldint.L._fldcase.getState()) {
                vector5.addElement(new b6(bo._fldif, 0, super.a.z.elementAt(32)));
            }
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[8])) {
            this.e._fldcase.a(4);
            vector5.addElement(new b6(ao._flddo, 0, super.a.z.elementAt(33)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[9])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(z._flddo, 0, super.a.z.elementAt(34)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[10])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(be._fldtry, 0, super.a.z.elementAt(35)));
            vector5.addElement(new b6(be._fldnew, 0, super.a.z.elementAt(36)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[11])) {
            this.e._fldcase.a(3);
            vector5.addElement(new b6(ba._flddo, 0, super.a.z.elementAt(37)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[12])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(t._flddo, 0, super.a.z.elementAt(38)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[13])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(Color.blue, 0, super.a.z.elementAt(39)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[14])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(b0._flddo, 0, super.a.z.elementAt(40)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[15])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(ce._flddo, 0, super.a.z.elementAt(41)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[16])) {
            this.e._fldcase.a(3);
            vector5.addElement(new b6(this.h._flddo, 0, super.a.z.elementAt(42)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[17])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(bn._flddo, 0, super.a.z.elementAt(43)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[18])) {
            this.e._fldcase.a(2);
            vector5.addElement(new b6(by._flddo, 0, super.a.z.elementAt(44)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[19])) {
            this.e._fldcase.a(2);
            vector6.addElement(new bh(av._flddo, super.a.z.elementAt(45)));
        }
        else if (this.h.ah._fldnew.ad.getSelectedItem().equals(this.h.ah._fldnew.ac[20])) {
            this.e._fldcase.a(2);
            vector6.addElement(new bh(v._fldnew, super.a.z.elementAt(46)));
        }
        this.e._fldcase._mthfor(vector5.isEmpty() ? null : vector5);
        this.e._fldcase._mthdo(vector6.isEmpty() ? null : vector6);
        this.e._fldcase.a(vector7.isEmpty() ? null : vector7);
        this.e._fldcase._mthif(vector8.isEmpty() ? null : vector8);
        this.e._fldgoto._mthif(true);
        while (!this.e._fldgoto._mthnew()) {
            this.a();
            this.e.invalidate();
            this.e.doLayout();
            this.e.repaint();
        }
        this.a();
        this.e.invalidate();
        this.e.doLayout();
        this.e.repaint();
    }
    
    public void a() {
        if (super.a.z.elementAt(7).period != this.h.ah._fldint.ab._flddo.getValue()) {
            super.a.z.elementAt(7).a("period", new Integer(this.h.ah._fldint.ab._flddo.getValue()));
        }
        if (super.a.z.elementAt(8).period != this.h.ah._fldint.ab._fldelse.getValue()) {
            super.a.z.elementAt(8).a("period", new Integer(this.h.ah._fldint.ab._fldelse.getValue()));
        }
        if (super.a.z.elementAt(9).period != this.h.ah._fldint.ab._fldbyte.getValue()) {
            super.a.z.elementAt(9).a("period", new Integer(this.h.ah._fldint.ab._fldbyte.getValue()));
        }
        if (super.a.z.elementAt(10).period != this.h.ah._fldint.ab._fldtry.getValue()) {
            super.a.z.elementAt(10).a("period", new Integer(this.h.ah._fldint.ab._fldtry.getValue()));
        }
        if (super.a.z.elementAt(11).period != this.h.ah._fldint.I._fldfor.getValue()) {
            super.a.z.elementAt(11).a("period", new Integer(this.h.ah._fldint.I._fldfor.getValue()));
        }
        if (super.a.z.elementAt(12).period != this.h.ah._fldint.I._fldcase.getValue()) {
            super.a.z.elementAt(12).a("period", new Integer(this.h.ah._fldint.I._fldcase.getValue()));
        }
        if (super.a.z.elementAt(13).period != this.h.ah._fldint.I._fldtry.getValue()) {
            super.a.z.elementAt(13).a("period", new Integer(this.h.ah._fldint.I._fldtry.getValue()));
        }
        if (super.a.z.elementAt(21).speriod != this.h.ah._fldint.X.a.getValue()) {
            super.a.z.elementAt(21).a("speriod", new Integer(this.h.ah._fldint.X.a.getValue()));
        }
        if (super.a.z.elementAt(21).lperiod != this.h.ah._fldint.X._fldfor.getValue()) {
            super.a.z.elementAt(21).a("lperiod", new Integer(this.h.ah._fldint.X._fldfor.getValue()));
        }
        if (super.a.z.elementAt(22).speriod != this.h.ah._fldint.O._flddo.getValue()) {
            super.a.z.elementAt(22).a("speriod", new Integer(this.h.ah._fldint.O._flddo.getValue()));
            super.a.z.elementAt(22).a("sweight", new Double(2.0 / (this.h.ah._fldint.O._flddo.getValue() + 1)));
            super.a.z.elementAt(24).a();
        }
        if (super.a.z.elementAt(22).lperiod != this.h.ah._fldint.O._fldtry.getValue()) {
            super.a.z.elementAt(22).a("lperiod", new Integer(this.h.ah._fldint.O._fldtry.getValue()));
            super.a.z.elementAt(22).a("lweight", new Double(2.0 / (this.h.ah._fldint.O._fldtry.getValue() + 1)));
            super.a.z.elementAt(24).a();
        }
        if (super.a.z.elementAt(23).period != this.h.ah._fldint.O._fldif.getValue()) {
            super.a.z.elementAt(23).a("period", new Integer(this.h.ah._fldint.O._fldif.getValue()));
            super.a.z.elementAt(23).a("weight", new Double(2.0 / (this.h.ah._fldint.O._fldif.getValue() + 1)));
            super.a.z.elementAt(24).a();
        }
        if (super.a.z.elementAt(25).period != this.h.ah._fldint.F._flddo.getValue()) {
            super.a.z.elementAt(25).a("period", new Integer(this.h.ah._fldint.F._flddo.getValue()));
            super.a.z.elementAt(26).a("period", new Integer(this.h.ah._fldint.F._flddo.getValue()));
            super.a.z.elementAt(27).a("period", new Integer(this.h.ah._fldint.F._flddo.getValue()));
            super.a.z.elementAt(28).a();
        }
        if (super.a.z.elementAt(26).period != this.h.ah._fldint.F._flddo.getValue()) {
            super.a.z.elementAt(25).a("period", new Integer(this.h.ah._fldint.F._flddo.getValue()));
            super.a.z.elementAt(26).a("period", new Integer(this.h.ah._fldint.F._flddo.getValue()));
            super.a.z.elementAt(27).a("period", new Integer(this.h.ah._fldint.F._flddo.getValue()));
            super.a.z.elementAt(28).a();
        }
        if (super.a.z.elementAt(29).period != this.h.ah._fldint.Y.a.getValue()) {
            super.a.z.elementAt(29).a("period", new Integer(this.h.ah._fldint.Y.a.getValue()));
        }
        if (super.a.z.elementAt(30).period != this.h.ah._fldint.L.a.getValue()) {
            super.a.z.elementAt(30).a("period", new Integer(this.h.ah._fldint.L.a.getValue()));
            super.a.z.elementAt(31).a();
            super.a.z.elementAt(32).a();
        }
        if (super.a.z.elementAt(31).period != this.h.ah._fldint.L._fldint.getValue()) {
            super.a.z.elementAt(31).a("period", new Integer(this.h.ah._fldint.L._fldint.getValue()));
            super.a.z.elementAt(31).a("weight", new Double(2.0 / (this.h.ah._fldint.L._fldint.getValue() + 1)));
            super.a.z.elementAt(32).a();
        }
        if (super.a.z.elementAt(32).period != this.h.ah._fldint.L._fldnew.getValue()) {
            super.a.z.elementAt(32).a("period", new Integer(this.h.ah._fldint.L._fldnew.getValue()));
            super.a.z.elementAt(32).a("weight", new Double(2.0 / (this.h.ah._fldint.L._fldnew.getValue() + 1)));
        }
        if (super.a.z.elementAt(33).period != this.h.ah._fldint.Z.a.getValue()) {
            super.a.z.elementAt(33).a("period", new Integer(this.h.ah._fldint.Z.a.getValue()));
        }
        if (super.a.z.elementAt(34).period != this.h.ah._fldint.P.a.getValue()) {
            super.a.z.elementAt(34).a("period", new Integer(this.h.ah._fldint.P.a.getValue()));
        }
        if (super.a.z.elementAt(35).term != this.h.ah._fldint.W._fldfor.getValue()) {
            super.a.z.elementAt(35).a("term", new Integer(this.h.ah._fldint.W._fldfor.getValue()));
            super.a.z.elementAt(36).a();
        }
        if (super.a.z.elementAt(35).period != this.h.ah._fldint.W._flddo.getValue()) {
            super.a.z.elementAt(35).a("period", new Integer(this.h.ah._fldint.W._flddo.getValue()));
            super.a.z.elementAt(35).a("weight", new Double(2.0 / (this.h.ah._fldint.W._flddo.getValue() + 1)));
            super.a.z.elementAt(36).a();
        }
        if (super.a.z.elementAt(36).period != this.h.ah._fldint.W._fldbyte.getValue()) {
            super.a.z.elementAt(36).a("period", new Integer(this.h.ah._fldint.W._fldbyte.getValue()));
            super.a.z.elementAt(36).a("weight", new Double(2.0 / (this.h.ah._fldint.W._fldbyte.getValue() + 1)));
        }
        if (super.a.z.elementAt(37).period != this.h.ah._fldint.K.a.getValue()) {
            super.a.z.elementAt(37).a("period", new Integer(this.h.ah._fldint.K.a.getValue()));
        }
        if (super.a.z.elementAt(38).period != this.h.ah._fldint.S.a.getValue()) {
            super.a.z.elementAt(38).a("period", new Integer(this.h.ah._fldint.S.a.getValue()));
        }
        if (super.a.z.elementAt(40).period != this.h.ah._fldint.U.a.getValue()) {
            super.a.z.elementAt(40).a("period", new Integer(this.h.ah._fldint.U.a.getValue()));
        }
        if (super.a.z.elementAt(41).period != this.h.ah._fldint.G.a.getValue()) {
            super.a.z.elementAt(41).a("period", new Integer(this.h.ah._fldint.G.a.getValue()));
        }
        if (super.a.z.elementAt(42).period != this.h.ah._fldint.V.a.getValue()) {
            super.a.z.elementAt(42).a("period", new Integer(this.h.ah._fldint.V.a.getValue()));
        }
        if (super.a.z.elementAt(43).period != this.h.ah._fldint.T.a.getValue()) {
            super.a.z.elementAt(43).a("period", new Integer(this.h.ah._fldint.T.a.getValue()));
        }
        if (super.a.z.elementAt(44).period != this.h.ah._fldint.J.a.getValue()) {
            super.a.z.elementAt(44).a("period", new Integer(this.h.ah._fldint.J.a.getValue()));
        }
        if (super.a.z.elementAt(45).speriod != this.h.ah._fldint.R.a.getValue()) {
            super.a.z.elementAt(45).a("speriod", new Integer(this.h.ah._fldint.R.a.getValue()));
        }
        if (super.a.z.elementAt(45).lperiod != this.h.ah._fldint.R._fldfor.getValue()) {
            super.a.z.elementAt(45).a("lperiod", new Integer(this.h.ah._fldint.R._fldfor.getValue()));
        }
        if (super.a.z.elementAt(14).factor != this.h.ah._fldint.E._flddo.getValue() / 1000.0) {
            super.a.z.elementAt(14).a("factor", new Double(this.h.ah._fldint.E._flddo.getValue() / 1000.0));
        }
        if (super.a.z.elementAt(15).period != this.h.ah._fldint.C._fldif.getValue()) {
            super.a.z.elementAt(15).a("period", new Integer(this.h.ah._fldint.C._fldif.getValue()));
            super.a.z.elementAt(16).a();
            super.a.z.elementAt(17).a();
        }
        if (super.a.z.elementAt(16).percentage != this.h.ah._fldint.C.a.getValue()) {
            super.a.z.elementAt(16).a("percentage", new Integer(this.h.ah._fldint.C.a.getValue()));
            super.a.z.elementAt(17).a("percentage", new Integer(this.h.ah._fldint.C.a.getValue()));
        }
        if (super.a.z.elementAt(17).percentage != this.h.ah._fldint.C.a.getValue()) {
            super.a.z.elementAt(16).a("percentage", new Integer(this.h.ah._fldint.C.a.getValue()));
            super.a.z.elementAt(17).a("percentage", new Integer(this.h.ah._fldint.C.a.getValue()));
        }
        if (super.a.z.elementAt(18).period != this.h.ah._fldint.N.a.getValue()) {
            super.a.z.elementAt(18).a("period", new Integer(this.h.ah._fldint.N.a.getValue()));
            super.a.z.elementAt(19).a();
            super.a.z.elementAt(20).a();
            super.a.z.elementAt(46).a();
        }
        if (super.a.z.elementAt(19).sigma != this.h.ah._fldint.N._fldfor.getValue()) {
            super.a.z.elementAt(19).a("sigma", new Integer(this.h.ah._fldint.N._fldfor.getValue()));
            super.a.z.elementAt(20).a("sigma", new Integer(this.h.ah._fldint.N._fldfor.getValue()));
            super.a.z.elementAt(46).a();
        }
        if (super.a.z.elementAt(20).sigma != this.h.ah._fldint.N._fldfor.getValue()) {
            super.a.z.elementAt(19).a("sigma", new Integer(this.h.ah._fldint.N._fldfor.getValue()));
            super.a.z.elementAt(20).a("sigma", new Integer(this.h.ah._fldint.N._fldfor.getValue()));
            super.a.z.elementAt(46).a();
        }
    }
}

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class ak extends Component
{
    public Vector _fldif;
    public int a;
    public int _fldcase;
    public int b;
    public int _fldfor;
    public int _fldbyte;
    public int _fldvoid;
    public int _flddo;
    public int _fldchar;
    Image _fldint;
    static Color _fldlong;
    static Color _fldnew;
    static Color _fldgoto;
    static Color _fldelse;
    int _fldnull;
    String _fldtry;
    
    public ak(final String fldtry) {
        this._fldtry = fldtry;
        this._fldnull = 0;
        this._fldif = new Vector();
        if (fldtry.compareTo("daewoo") == 0) {
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("samsung") == 0) {
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("lg") == 0) {
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("daishin") == 0) {
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("hyundai") == 0) {
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("dongwon") == 0) {
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("myasset") == 0) {
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("dongbu") == 0) {
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("hanhwa") == 0) {
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("kyobo") == 0) {
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("woori") == 0) {
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("sinyoung") == 0) {
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("cjcyber") == 0) {
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("seoulfn") == 0) {
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("goodi") == 0) {
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("meritz") == 0) {
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("sk") == 0) {
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("KB") == 0) {
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
        }
        else if (fldtry.compareTo("kinvest") == 0) {
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else if (fldtry.compareTo("sejong") == 0) {
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        else {
            this._fldnull = 1;
            this._fldif.addElement(new a5(w.ar, true, new String("\ub300\uc6b0")));
            this._fldif.addElement(new a5(w.S, true, new String("\uc0bc\uc131")));
            this._fldif.addElement(new a5(w.a9, true, new String("\ub300\uc2e0")));
            this._fldif.addElement(new a5(w.aH, true, new String("\ud604\ub300")));
            this._fldif.addElement(new a5(w.o, true, new String("\ub3d9\uc591")));
            this._fldif.addElement(new a5(w.as, true, new String("\ud55c\ud654")));
            this._fldif.addElement(new a5(w.bk, true, new String("\uad50\ubcf4")));
            this._fldif.addElement(new a5(w._fldint, true, new String("\uc6b0\ub9ac")));
            this._fldif.addElement(new a5(w._fldlong, true, new String("\ud558\uc774")));
            this._fldif.addElement(new a5(w._flddo, true, new String("\uc720\uc9c4")));
            this._fldif.addElement(new a5(w.l, true, new String("\ub3d9\ubd80")));
            this._fldif.addElement(new a5(w.bm, true, new String("\uc2e0\ud55c")));
            this._fldif.addElement(new a5(w._fldfor, true, new String("\uba54\ub9ac\uce20")));
            this._fldif.addElement(new a5(w.bl, true, new String("SK")));
            this._fldif.addElement(new a5(w.ab, true, new String("\ud55c\uad6d")));
            this._fldif.addElement(new a5(w.aJ, true, new String("NH")));
            this._fldif.addElement(new a5(w.ljjf, true, new String("KB")));
        }
        this._fldif.addElement(new a5(w._fldelse, true, new String("\ubbf8\ub798")));
        this._fldif.addElement(new a5(w.a5, true, new String("CSFB")));
        this._fldif.addElement(new a5(w.bg, true, new String("JP")));
        this._fldif.addElement(new a5(w.bj, true, new String("\uba54\ub9b4")));
        this._fldif.addElement(new a5(w.aP, true, new String("UBS")));
        this._fldif.addElement(new a5(w.X, true, new String("CGM")));
        this._fldif.addElement(new a5(w.bi, true, new String("ABN\uc554\ub85c")));
        this._fldif.addElement(new a5(w.k, true, new String("\ub9e5\ucffc\ub9ac")));
        this._fldif.addElement(new a5(w.ba, true, new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")));
        this._fldif.addElement(new a5(w.ad, true, new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")));
        this._fldif.addElement(new a5(w.a2, true, new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")));
        this._fldif.addElement(new a5(w.ljjn, true, new String("\ub2e4\uc774\uc640")));
        this._fldif.addElement(new a5(w.ljjn, true, new String("\ub178\ubb34\ub77c")));
        this._fldif.addElement(new a5(w.ljjl, true, new String("RBS")));
        this._fldif.addElement(new a5(w.ljjt, true, new String("\ub3c4\uc774\uce58")));
        this._fldif.addElement(new a5(w.ljjh, true, new String("HSBC")));
        this._fldif.addElement(new a5(w.ljjp, true, new String("\ud30c\ub9ac\ubc14")));
        this._fldif.addElement(new a5(w.ljje, true, new String("\uc774\ud2b8\ub808\uc774\ub4dc")));
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
        this._fldint = null;
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.invalidate();
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this._fldint == null) {
            this._fldint = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this._fldint.getGraphics();
        graphics2.setClip(0, 0, size.width, size.height);
        super.paint(graphics2);
        this.a(graphics2);
        graphics.drawImage(this._fldint, 0, 0, null);
        graphics2.dispose();
    }
    
    void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = 17;
        final int n2 = 12;
        final int n3 = 9;
        final int n4 = 60;
        final int height = fontMetrics.getHeight();
        graphics.setColor(Color.lightGray);
        graphics.drawRect(0, 0, 104, 415);
        if (this._fldnull > 0) {
            for (int i = 0; i < 8; ++i) {
                final a5 a5 = this._fldif.elementAt(i);
                final int n5 = n3;
                final int n6 = n * i + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n5 - 4, n6 - 1, 8, 8);
                if (a5._fldnew) {
                    graphics.setColor(a5._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n5 - 3, n6, 6, 6);
                final int n7 = n3 + 10;
                final int stringWidth = fontMetrics.stringWidth(a5._fldif);
                if (a5._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a5._fldif, n7, n6 + height / 2);
                a5._fldtry = n3 - 4;
                a5._flddo = n3 + 10 + stringWidth;
                a5._fldfor = n6 - height / 2;
                a5.a = n6 + height / 2;
            }
            for (int j = 8; j < 16; ++j) {
                final a5 a6 = this._fldif.elementAt(j);
                final int n8 = n4;
                final int n9 = n * (j - 8) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n8 - 4, n9 - 1, 8, 8);
                if (a6._fldnew) {
                    graphics.setColor(a6._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n8 - 3, n9, 6, 6);
                final int n10 = n4 + 10;
                final int stringWidth2 = fontMetrics.stringWidth(a6._fldif);
                if (a6._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a6._fldif, n10, n9 + height / 2);
                a6._fldtry = n4 - 4;
                a6._flddo = n4 + 10 + stringWidth2;
                a6._fldfor = n9 - height / 2;
                a6.a = n9 + height / 2;
            }
            for (int k = 16; k < 18; ++k) {
                final a5 a7 = this._fldif.elementAt(k);
                final int n11 = n3;
                final int n12 = n * (k - 8) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n11 - 4, n12 - 1, 8, 8);
                if (a7._fldnew) {
                    graphics.setColor(a7._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n11 - 3, n12, 6, 6);
                final int n13 = n3 + 10;
                final int stringWidth3 = fontMetrics.stringWidth(a7._fldif);
                if (a7._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a7._fldif, n13, n12 + height / 2);
                a7._fldtry = n3 - 4;
                a7._flddo = n3 + 10 + stringWidth3;
                a7._fldfor = n12 - height / 2;
                a7.a = n12 + height / 2;
            }
            for (int l = 18; l < 20; ++l) {
                final a5 a8 = this._fldif.elementAt(l);
                final int n14 = n4;
                final int n15 = n * (l - 10) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n14 - 4, n15 - 1, 8, 8);
                if (a8._fldnew) {
                    graphics.setColor(a8._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n14 - 3, n15, 6, 6);
                final int n16 = n4 + 10;
                final int stringWidth4 = fontMetrics.stringWidth(a8._fldif);
                if (a8._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a8._fldif, n16, n15 + height / 2);
                a8._fldtry = n4 - 4;
                a8._flddo = n4 + 10 + stringWidth4;
                a8._fldfor = n15 - height / 2;
                a8.a = n15 + height / 2;
            }
            for (int n17 = 20; n17 < 21; ++n17) {
                final a5 a9 = this._fldif.elementAt(n17);
                final int n18 = n3;
                final int n19 = n * (n17 - 10) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n18 - 4, n19 - 1, 8, 8);
                if (a9._fldnew) {
                    graphics.setColor(a9._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n18 - 3, n19, 6, 6);
                final int n20 = n3 + 10;
                final int stringWidth5 = fontMetrics.stringWidth(a9._fldif);
                if (a9._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a9._fldif, n20, n19 + height / 2);
                a9._fldtry = n3 - 4;
                a9._flddo = n3 + 10 + stringWidth5;
                a9._fldfor = n19 - height / 2;
                a9.a = n19 + height / 2;
            }
            for (int n21 = 21; n21 < 22; ++n21) {
                final a5 a10 = this._fldif.elementAt(n21);
                final int n22 = n4;
                final int n23 = n * (n21 - 11) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n22 - 4, n23 - 1, 8, 8);
                if (a10._fldnew) {
                    graphics.setColor(a10._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n22 - 3, n23, 6, 6);
                final int n24 = n4 + 10;
                final int stringWidth6 = fontMetrics.stringWidth(a10._fldif);
                if (a10._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a10._fldif, n24, n23 + height / 2);
                a10._fldtry = n4 - 4;
                a10._flddo = n4 + 10 + stringWidth6;
                a10._fldfor = n23 - height / 2;
                a10.a = n23 + height / 2;
            }
        }
        else {
            for (int n25 = 0; n25 < 8; ++n25) {
                final a5 a11 = this._fldif.elementAt(n25);
                final int n26 = n3;
                final int n27 = n * n25 + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n26 - 4, n27 - 1, 8, 8);
                if (a11._fldnew) {
                    graphics.setColor(a11._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n26 - 3, n27, 6, 6);
                final int n28 = n3 + 10;
                final int stringWidth7 = fontMetrics.stringWidth(a11._fldif);
                if (a11._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a11._fldif, n28, n27 + height / 2);
                a11._fldtry = n3 - 4;
                a11._flddo = n3 + 10 + stringWidth7;
                a11._fldfor = n27 - height / 2;
                a11.a = n27 + height / 2;
            }
            for (int n29 = 8; n29 < 16; ++n29) {
                final a5 a12 = this._fldif.elementAt(n29);
                final int n30 = n4;
                final int n31 = n * (n29 - 8) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n30 - 4, n31 - 1, 8, 8);
                if (a12._fldnew) {
                    graphics.setColor(a12._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n30 - 3, n31, 6, 6);
                final int n32 = n4 + 10;
                final int stringWidth8 = fontMetrics.stringWidth(a12._fldif);
                if (a12._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a12._fldif, n32, n31 + height / 2);
                a12._fldtry = n4 - 4;
                a12._flddo = n4 + 10 + stringWidth8;
                a12._fldfor = n31 - height / 2;
                a12.a = n31 + height / 2;
            }
            for (int n33 = 16; n33 < 18; ++n33) {
                final a5 a13 = this._fldif.elementAt(n33);
                final int n34 = n3;
                final int n35 = n * (n33 - 8) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n34 - 4, n35 - 1, 8, 8);
                if (a13._fldnew) {
                    graphics.setColor(a13._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n34 - 3, n35, 6, 6);
                final int n36 = n3 + 10;
                final int stringWidth9 = fontMetrics.stringWidth(a13._fldif);
                if (a13._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a13._fldif, n36, n35 + height / 2);
                a13._fldtry = n3 - 4;
                a13._flddo = n3 + 10 + stringWidth9;
                a13._fldfor = n35 - height / 2;
                a13.a = n35 + height / 2;
            }
            for (int n37 = 18; n37 < 20; ++n37) {
                final a5 a14 = this._fldif.elementAt(n37);
                final int n38 = n4;
                final int n39 = n * (n37 - 10) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n38 - 4, n39 - 1, 8, 8);
                if (a14._fldnew) {
                    graphics.setColor(a14._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n38 - 3, n39, 6, 6);
                final int n40 = n4 + 10;
                final int stringWidth10 = fontMetrics.stringWidth(a14._fldif);
                if (a14._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a14._fldif, n40, n39 + height / 2);
                a14._fldtry = n4 - 4;
                a14._flddo = n4 + 10 + stringWidth10;
                a14._fldfor = n39 - height / 2;
                a14.a = n39 + height / 2;
            }
            for (int n41 = 20; n41 < 21; ++n41) {
                final a5 a15 = this._fldif.elementAt(n41);
                final int n42 = n3;
                final int n43 = n * (n41 - 10) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n42 - 4, n43 - 1, 8, 8);
                if (a15._fldnew) {
                    graphics.setColor(a15._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n42 - 3, n43, 6, 6);
                final int n44 = n3 + 10;
                final int stringWidth11 = fontMetrics.stringWidth(a15._fldif);
                if (a15._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a15._fldif, n44, n43 + height / 2);
                a15._fldtry = n3 - 4;
                a15._flddo = n3 + 10 + stringWidth11;
                a15._fldfor = n43 - height / 2;
                a15.a = n43 + height / 2;
            }
            for (int n45 = 21; n45 < 22; ++n45) {
                final a5 a16 = this._fldif.elementAt(n45);
                final int n46 = n4;
                final int n47 = n * (n45 - 11) + n2;
                graphics.setColor(Color.black);
                graphics.fillRect(n46 - 4, n47 - 1, 8, 8);
                if (a16._fldnew) {
                    graphics.setColor(a16._fldint);
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(n46 - 3, n47, 6, 6);
                final int n48 = n4 + 11;
                final int stringWidth12 = fontMetrics.stringWidth(a16._fldif);
                if (a16._fldnew) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.lightGray);
                }
                graphics.drawString(a16._fldif, n48, n47 + height / 2);
                a16._fldtry = n4 - 4;
                a16._flddo = n4 + 10 + stringWidth12;
                a16._fldfor = n47 - height / 2;
                a16.a = n47 + height / 2;
            }
        }
        final int n49 = n * 12 + n2 - 15;
        for (int n50 = 22; n50 < this._fldif.size(); ++n50) {
            final a5 a17 = this._fldif.elementAt(n50);
            final int n51 = n3;
            final int n52 = n * (n50 - 22) + n49;
            graphics.setColor(Color.black);
            graphics.fillRect(n51 - 4, n52 - 1, 8, 8);
            if (a17._fldnew) {
                graphics.setColor(a17._fldint);
            }
            else {
                graphics.setColor(Color.white);
            }
            graphics.fillRect(n51 - 3, n52, 6, 6);
            final int n53 = n3 + 10;
            final int stringWidth13 = fontMetrics.stringWidth(a17._fldif);
            if (a17._fldnew) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.lightGray);
            }
            graphics.drawString(a17._fldif, n53, n52 + height / 2);
            a17._fldtry = n3 - 4;
            a17._flddo = n3 + 10 + stringWidth13;
            a17._fldfor = n52 - height / 2;
            a17.a = n52 + height / 2;
        }
        graphics.setColor(new Color(232, 232, 232));
        graphics.fillRect(1, size.height - 22, 49, 21);
        graphics.setColor(Color.lightGray);
        graphics.drawRect(0, size.height - 23, 51, 22);
        this.a = 1;
        this._fldcase = 50;
        this.b = size.height - 22;
        this._fldfor = size.height - 1;
        final int n54 = size.height - 14;
        final int stringWidth14 = fontMetrics.stringWidth("\uc120\ud0dd");
        graphics.setColor(Color.black);
        graphics.drawString("\uc120\ud0dd", 26 - stringWidth14 / 2, n54 + height / 2);
        graphics.setColor(new Color(245, 245, 245));
        graphics.fillRect(54, size.height - 22, 49, 21);
        graphics.setColor(Color.lightGray);
        graphics.drawRect(53, size.height - 23, 51, 22);
        this._fldbyte = 54;
        this._fldvoid = 104;
        this._flddo = size.height - 22;
        this._fldchar = size.height - 1;
        graphics.setColor(Color.black);
        final int n55 = size.height - 14;
        final int stringWidth15 = fontMetrics.stringWidth("\ud574\uc81c");
        graphics.setColor(Color.black);
        graphics.drawString("\ud574\uc81c", 78 - stringWidth15 / 2, n55 + height / 2);
    }
    
    static {
        ak._fldlong = new Color(102, 102, 102);
        ak._fldnew = new Color(178, 178, 178);
        ak._fldgoto = new Color(229, 229, 221);
        ak._fldelse = new Color(160, 160, 154);
    }
}

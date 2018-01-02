import java.text.DecimalFormat;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class au implements Serializable
{
    public int d;
    public String _fldnew;
    public String j;
    public String h;
    public int i;
    public String _fldgoto;
    public int k;
    public int _fldfor;
    public int _flddo;
    public int _fldif;
    public String m;
    public String _fldchar;
    public int _fldbyte;
    public int _fldelse;
    public int c;
    public int f;
    public int _fldint;
    public int _fldtry;
    public int _fldcase;
    public int _fldnull;
    public int _fldvoid;
    public int b;
    public static final int e = 50;
    public static final int a = 10;
    public static final int _fldlong = 9;
    public static final int g = 10;
    public static final int l = 20;
    
    public au(final int d, final String fldnew, final String j, final String h, final int i, final String fldgoto, final int k, final int fldfor, final int flddo, final int fldif, final String m, final String fldchar) {
        this.d = d;
        this._fldnew = fldnew;
        this.j = j;
        this.h = h;
        this.i = i;
        this._fldgoto = fldgoto;
        this.k = k;
        this._fldfor = fldfor;
        this._flddo = flddo;
        this._fldif = fldif;
        this.m = m;
        this._fldchar = fldchar;
        this._fldbyte = -1;
        this._fldelse = -1;
        this.c = -1;
        this.f = -1;
        this._fldint = -1;
        this._fldtry = -1;
        this._fldcase = -1;
        this._fldnull = -1;
        this._fldvoid = -1;
        this.b = -1;
    }
    
    public String a(final int n) {
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0");
        String format;
        if (this._fldfor != 0) {
            format = decimalFormat.format(this._fldfor);
        }
        else {
            format = new String("");
        }
        String format2;
        if (this._flddo != 0) {
            format2 = decimalFormat.format(this._flddo);
        }
        else {
            format2 = new String("");
        }
        String format3;
        if (this._fldif != 0) {
            format3 = decimalFormat.format(this._fldif);
        }
        else {
            format3 = new String("");
        }
        String format4;
        if (this.k != 0) {
            format4 = decimalFormat.format(this.k);
        }
        else {
            format4 = new String("");
        }
        if (this.i != 2) {
            if (this.i != 3) {
                if (this.i != 4) {
                    if (this.i != 5) {
                        if (this.i == 6) {}
                    }
                }
            }
        }
        if (n == 0) {
            if (this.m.length() > 40) {
                this.m = this.m.substring(0, 40);
            }
            if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : SK\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
            return new String("\uc99d\uad8c\uc0ac     : " + this.j + "\n" + "\uc791\uc131\uc790     : " + this.h + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
        }
        else if (n == 1) {
            if (this._fldnew.indexOf("Z") >= 0) {
                if (this.m.length() > 40) {
                    this.m = this.m.substring(0, 40);
                }
                if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : SK\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
                }
                return new String("\uc99d\uad8c\uc0ac     : " + this.j + "\n" + "\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4);
            }
            else {
                if (this.m.length() > 40) {
                    this.m = this.m.substring(0, 40);
                }
                if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                    return new String("\uc99d\uad8c\uc0ac     : SK\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
                }
                return new String("\uc99d\uad8c\uc0ac     : " + this.j + "\n" + "\uc791\uc131\uc790     : " + this.h + "\n" + "\ud22c\uc790\uc758\uacac  : " + this._fldgoto + "\n" + "EPS2011  : " + format + "\n" + "EPS2012  : " + format2 + "\n" + "EPS2013  : " + format3 + "\n" + "\ubaa9\ud45c\uac00     : " + format4 + "\n" + "\uc81c\ubaa9        : " + this.m + "\n" + "\ucca8\ubd80\ud30c\uc77c  : " + this._fldchar);
            }
        }
        else if (n == 2) {
            if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac : SK\uc99d\uad8c\n\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
            }
            return new String("\uc99d\uad8c\uc0ac : " + this.j + "\n" + "\uc791\uc131\uc790 : " + this.h + "\n" + "\ubaa9\ud45c\uac00 : " + format4);
        }
        else if (n == 3) {
            if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : SK\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
            }
            return new String("\uc99d\uad8c\uc0ac     : " + this.j + "\n" + "\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2011  : " + format);
        }
        else if (n == 4) {
            if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : SK\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
            }
            return new String("\uc99d\uad8c\uc0ac     : " + this.j + "\n" + "\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2012  : " + format2);
        }
        else {
            if (n != 5) {
                return null;
            }
            if (this.j.compareTo("\ub3d9\uc6d0\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\ub3d9\uc591\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ub3d9\uc591\uc885\uae08\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\uad7f\ubaa8\ub2dd\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc2e0\ud55c\uae08\uc735\ud22c\uc790\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud55c\uad6d\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\uc6b0\ub9ac\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc6b0\ub9ac\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \ud558\uc774\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\uc11c\uc6b8\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : \uc720\uc9c4\ud22c\uc790\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            if (this.j.compareTo("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c") == 0) {
                return new String("\uc99d\uad8c\uc0ac     : SK\uc99d\uad8c\n\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
            }
            return new String("\uc99d\uad8c\uc0ac     : " + this.j + "\n" + "\uc791\uc131\uc790     : " + this.h + "\n" + "EPS2013  : " + format3);
        }
    }
}

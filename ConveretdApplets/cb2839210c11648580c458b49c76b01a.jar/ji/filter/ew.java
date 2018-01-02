// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.filter.uv.fc;
import ji.filter.jpeg2k.e8;
import ji.filter.pdf.ci;
import ji.filter.png.e7;
import ji.filter.djvu.e6;
import ji.v1event.a3;
import ji.io.fg;
import java.awt.Component;
import ji.util.cn;
import ji.res.ay;
import ji.v1event.a6;
import ji.io.oe;
import ji.io.gn;
import java.io.IOException;
import java.io.EOFException;
import ji.io.fb;
import ji.res.s;
import ji.util.i;
import ji.v1event.ex;
import java.io.FileNotFoundException;
import ji.io.h;
import ji.util.e;
import ji.util.d;
import ji.image.dx;
import ji.image.ds;
import ji.awt.c;
import ji.image.ev;
import java.awt.Dimension;
import ji.document.ad;
import ji.v1event.af;
import ji.io.ac;

public class ew
{
    static boolean a;
    
    public static final dx a(final Object o, final ac ac, final String s, final String s2, final af af, final boolean b, final ad ad, final String s3, final String s4, final String s5, final boolean b2, final int n, final int n2, final int n3, final boolean b3, final int n4, final Dimension dimension, final boolean b4, final boolean b5, final boolean b6, final ev ev, final c c, final ds ds, final boolean b7, final boolean b8, final boolean b9, final boolean b10) throws Exception {
        if (d.w) {
            return null;
        }
        if (b5) {
            d.mt.o = System.currentTimeMillis();
            d.mt.r = 0L;
            d.mt.t = 0L;
            d.mt.q = 0L;
        }
        final boolean b11 = false;
        e.b(true);
        if (ac.v() <= 0) {
            if (d.cy()) {
                h.d(s5, "FileNotFoundException: #6: ".concat(String.valueOf(String.valueOf(o))));
            }
            e.b(false);
            e.e("FileNotFoundException: #6: ".concat(String.valueOf(String.valueOf(o))));
            throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(286, s5)))).append(": ").append(o))));
        }
        ck a = null;
        d.eb = false;
        dx dx = null;
        ck ck = null;
        final ex ex = new ex(s5, af, 18, 500, true);
        try {
            d.a0(false);
            if (i.c(5)) {
                h.d(s5, "Scanning file:".concat(String.valueOf(String.valueOf(s2))));
            }
            final String c2 = d.c(s2.toLowerCase(), s3, s4);
            if (i.c(5)) {
                h.d(s5, String.valueOf(String.valueOf(new StringBuffer("Scanning file:").append(o).append(", extension: ").append(c2))));
            }
            if (!d.eb) {
                a = a(ad, s5, c2, 0, b11, ac, s4, b10);
                if (a != null) {
                    boolean b12 = true;
                    int a2 = 2;
                    if (!i.c(256)) {
                        a2 = a(a, s4, c2, ac, af, ad, s5, b10, n3, true, 0, s, s2);
                        b12 = (a2 == 1 || a2 == 2);
                        if (!b12) {
                            ck = a;
                        }
                    }
                    if (b12) {
                        d.a0(false);
                        String s6 = "";
                        try {
                            dx = a.a(new fh(o, ac, s, s2, af, n3, b, s3, s4, ad, b2, n, n2, s5, n4, dimension, b4, b6, i.c(164), ev, c, ds, b7, b8, b9), b3);
                            if (dx == null && a2 == 1) {
                                h.c(s5, String.valueOf(String.valueOf(new StringBuffer("Filter ").append(a.getFilterName()).append(" is valid but failed to load."))));
                                throw new fb(s.a(1288, s5));
                            }
                        }
                        catch (EOFException ex2) {
                            if (i.c(5)) {
                                ex2.printStackTrace();
                            }
                            s6 = "Attempted but got exception ".concat(String.valueOf(String.valueOf(ex2)));
                            h.c(s5, s6);
                            if (ad != null) {
                                if (ad.co()) {
                                    h.c(s5, "Aborting filters, shutdown detected");
                                    throw ex2;
                                }
                            }
                            else {
                                h.c(s5, "Can't test for shutdown, parent n.");
                            }
                            dx = null;
                        }
                        catch (IOException ex3) {
                            throw ex3;
                        }
                        catch (Exception fb) {
                            if (i.c(5)) {
                                fb.printStackTrace();
                            }
                            s6 = "Attempted but got exception ".concat(String.valueOf(String.valueOf(fb)));
                            h.c(s5, s6);
                            if (ad != null) {
                                if (ad.co()) {
                                    h.c(s5, "Aborting filters, shutdown detected");
                                    throw fb;
                                }
                            }
                            else {
                                h.c(s5, "Can't test for shutdown, parent n.");
                            }
                            dx = null;
                            if (fb instanceof gn) {
                                throw fb;
                            }
                            if (fb instanceof oe) {
                                throw fb;
                            }
                            if (dx == null && a2 == 1) {
                                h.c(s5, String.valueOf(String.valueOf(new StringBuffer("Filter ").append(a.getFilterName()).append(" is valid but failed to load."))));
                                fb = new fb(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.a(1288, s5)))).append(": ").append(fb.getMessage()))));
                            }
                            if (fb instanceof fb) {
                                e.ag(null);
                                if (af != null) {
                                    af.a(new a6(a, 9, ""));
                                }
                                if (af != null) {
                                    af.a(new a6(a, 4, "1"));
                                }
                                d.a(ay.a(), cn.a(), ad, null, af, s5, false);
                                throw fb;
                            }
                            if (fb instanceof fg && !((fg)fb).a()) {
                                throw fb;
                            }
                        }
                        finally {
                            if (dx == null) {
                                if (d.by(s6)) {
                                    s6 = "Filter not valid for this file type.";
                                }
                            }
                            a(dx != null, ad, n3, a.getFilterName(), s4, c2, b10, true, 0, s6);
                        }
                    }
                    try {
                        if (dx == null) {
                            if (!i.c(256)) {
                                ck = a;
                            }
                            a.close(dx, ad);
                            a = null;
                        }
                    }
                    catch (Exception ex4) {
                        d.a(ex4);
                    }
                }
            }
            if (dx == null && !d.eb) {
                int n5 = 1;
                int n6 = 1;
                ew.a = false;
                while (n6 != 0 && !ew.a) {
                    final ck a3 = a(ad, s5, c2, n5, b11, ac, s4, b10);
                    if (a3 != null) {
                        a = a3;
                        d.a0(false);
                        int n7 = 1;
                        int a4 = 2;
                        if (!i.c(256)) {
                            if (ck != null && ck.getClass().equals(a.getClass())) {
                                h.c(s5, String.valueOf(String.valueOf(ck.c(s5))).concat(": Filter has already been checked based on mime type."));
                                n7 = 0;
                            }
                            if (n7 != 0 && a.a(ad)) {
                                a4 = a(a, s4, c2, ac, af, ad, s5, b10, n3, false, n5, s, s2);
                                n7 = ((a4 == 1 || a4 == 2) ? 1 : 0);
                            }
                        }
                        if (n7 != 0 && ew.a && a instanceof ez && dx == null && d.b()) {
                            n7 = 0;
                        }
                        if (n7 != 0) {
                            String concat = "";
                            try {
                                dx = a.a(new fh(o, ac, s, s2, af, n3, b, s3, s4, ad, b2, n, n2, s5, n4, dimension, b4, b6, i.c(164), ev, c, ds, b7, b8), b3);
                                if (dx == null && a4 == 1) {
                                    h.c(s5, String.valueOf(String.valueOf(new StringBuffer("Filter ").append(a.getFilterName()).append(" is valid but failed to load."))));
                                    throw new fb(s.a(1288, s5));
                                }
                            }
                            catch (Exception ex5) {
                                concat = "Attempted but got exception ".concat(String.valueOf(String.valueOf(ex5)));
                                h.c(s5, concat);
                                if (ad != null && ad.co()) {
                                    h.c(s5, "Aborting filters, shutdown detected");
                                    throw ex5;
                                }
                                dx = null;
                                if (ex5 instanceof gn) {
                                    throw ex5;
                                }
                                if (ex5 instanceof oe) {
                                    throw ex5;
                                }
                                if (ex5 instanceof fb) {
                                    e.ag(null);
                                    if (af != null) {
                                        af.a(new a6(a, 9, ""));
                                    }
                                    if (af != null) {
                                        af.a(new a6(a, 4, "1"));
                                    }
                                    d.a(ay.a(), cn.a(), ad, null, af, s5, false);
                                    throw ex5;
                                }
                                if (ex5 instanceof fg && !((fg)ex5).a()) {
                                    throw ex5;
                                }
                            }
                            finally {
                                if (dx == null) {
                                    if (d.by(concat)) {
                                        concat = "Filter not valid for this file type.";
                                    }
                                }
                                a(dx != null, ad, n3, a.getFilterName(), s4, c2, b10, false, n5, concat);
                            }
                        }
                        try {
                            if (dx != null) {
                                break;
                            }
                            a.close(dx, ad);
                            a = null;
                        }
                        catch (Exception ex6) {
                            d.a(ex6);
                        }
                    }
                    else {
                        n6 = 0;
                    }
                    ++n5;
                }
            }
            d.eb = false;
            if (dx == null) {
                e.b(false);
                if (i.c(5)) {
                    h.d(s5, "*** Unrecognized format 2");
                }
                String s7 = s4;
                if (d.by(s7)) {
                    s7 = c2;
                }
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(322, s5)))).append(" (").append(s).append(" - ").append(s7).append(")")));
                e.e(value);
                throw new fg(value);
            }
            dx.bj = a;
        }
        catch (Exception ex7) {
            if (i.c(5)) {
                h.d(s5, "Exception: ".concat(String.valueOf(String.valueOf(ex7))));
            }
            e.b(false);
            if (i.c(5)) {
                ex7.printStackTrace();
            }
            if (!d.ci()) {
                e.e(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(321, s5)))).append(" (").append(s).append(")"))));
            }
            else {
                e.e("LZW not available");
            }
            if (ex7 instanceof gn) {
                throw ex7;
            }
            if (ex7 instanceof oe) {
                throw ex7;
            }
            if (ex7 instanceof fb) {
                throw ex7;
            }
            if (ex7 instanceof fg) {
                throw ex7;
            }
            d.a(ex7);
            throw new fg(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(321, s5)))).append(" (").append(s).append(")"))));
        }
        finally {
            ex.a();
        }
        if (dx == null) {
            e.b(false);
        }
        else {
            if (d.a9() > 0) {
                dx.ac = d.a9();
            }
            if (d.ba() > 0) {
                dx.ad = d.ba();
            }
        }
        if (i.c(5)) {
            h.d(s5, String.valueOf(String.valueOf(new StringBuffer("Using filter: ").append(dx.bj.getFilterName()).append("(").append(dx.l).append(")"))));
        }
        return dx;
    }
    
    private static int a(final ck ck, final String s, final String s2, final ac ac, final af af, final ad ad, final String s3, final boolean b, final int n, final boolean b2, final int n2, final String s4, final String s5) throws fg {
        boolean b3 = true;
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ck.getFilterName()))).append(": ").append(s).append(": ").append(s2).append(": ")));
        final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ck.c(s3)))).append(": ").append(s).append(": ").append(s2).append(": ")));
        String b4 = null;
        try {
            final int fileType = ck.isFileType(ac, s, s2, ad, b, s4, s5, af, n2 != 0);
            try {
                ac.a(0L);
            }
            catch (Exception ex) {}
            switch (fileType) {
                case 0: {
                    b4 = "Filter not valid for this file type.";
                    b3 = false;
                    break;
                }
                case 1: {
                    if (!ck.b(s3)) {
                        b4 = "This file format is not licensed.";
                        b3 = false;
                        throw new fg(String.valueOf(String.valueOf(value2)).concat(String.valueOf(String.valueOf(s.a(1281, s3)))));
                    }
                    if (!ck.d(s3)) {
                        b4 = "This file format is not enabled.";
                        b3 = false;
                        throw new fg(String.valueOf(String.valueOf(value2)).concat(String.valueOf(String.valueOf(s.a(1282, s3)))));
                    }
                    b4 = "Valid filter for this file";
                    break;
                }
                case 2: {
                    if (!ck.b(s3)) {
                        b4 = "Potential file format that is not licensed.";
                        b3 = false;
                        break;
                    }
                    if (!ck.d(s3)) {
                        b4 = "Potential file format that is not enabled.";
                        b3 = false;
                        break;
                    }
                    b4 = "Potential file format.";
                    break;
                }
                case 4: {
                    b4 = ck.b();
                    b3 = false;
                    throw new fg(String.valueOf(String.valueOf(value2)).concat(String.valueOf(String.valueOf(b4))));
                }
                case 3: {
                    b3 = false;
                    if (d.cz(s) && ck.isDefinitelySupportedMimeType(s, ad)) {
                        b4 = "This filter supports the supplied mime-type, but is not licensed";
                        throw new fg(String.valueOf(String.valueOf(value2)).concat(String.valueOf(String.valueOf(s.a(1281, s3)))));
                    }
                    b4 = "Could not check file type as the filter is not licensed.";
                    break;
                }
                case 5: {
                    b3 = false;
                    h.c(s3, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append("Could not check file type: ").append(ck.b()))));
                    if (d.cz(s)) {
                        if (ck.isDefinitelySupportedMimeType(s, ad)) {
                            b4 = "Filter supports the supplied mime-type, but is not on a supported platform.";
                            throw new fg(String.valueOf(String.valueOf(value2)).concat(String.valueOf(String.valueOf(ck.b()))));
                        }
                        b4 = "Filter does not support the supplied mime-type and cannot check file as filter not supported on this platform.";
                    }
                    else {
                        b4 = "Filter does not support the extension of the supplied file and cannot check file as filter not supported on this platform.";
                    }
                    b3 = false;
                    break;
                }
                case 6: {
                    b3 = false;
                    b4 = "Could not check file type because an error occurred.";
                    break;
                }
            }
            return fileType;
        }
        finally {
            if (!d.by(b4)) {
                h.c(s3, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(b ? "errorfile: " : "").append(b4))));
            }
            if (!b3) {
                a(false, ad, n, ck.getFilterName(), s, s2, b, b2, n2, b4);
            }
        }
    }
    
    private static void a(final boolean b, final ad ad, final int n, final String s, final String s2, final String s3, final boolean b2, final boolean b3, final int n2, final String s4) {
        if (b2) {
            h.c(ad.iu(), "No Javascript filter events when dealing with the error file.");
            return;
        }
        final StringBuffer sb = new StringBuffer(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(": ").append(s2).append(": ").append(s3))));
        if (b3) {
            sb.append(": mimetype");
        }
        else {
            sb.append(": looping-".concat(String.valueOf(String.valueOf(n2))));
        }
        sb.append(": ".concat(String.valueOf(String.valueOf(s4))));
        ad.b(new a3(ad, b ? 55 : 56, n, sb.toString(), d.e0()));
    }
    
    private static ck a(final ad ad, final String s, final String s2, final int n, final boolean b, final ac ac, final String s3, final boolean b2) throws Exception {
        final ck a = a(ad, s, s2, n, b, s3, b2);
        if (a != null) {
            if (ad != null && ad.dm() != null) {
                ad.dm().a(a);
            }
            a.a(s);
        }
        return a;
    }
    
    private static ck a(final ad ad, final String s, String s2, final int n, final boolean b, final String s3, final boolean b2) throws Exception {
        if (d.w) {
            return null;
        }
        if (s2 == null) {
            s2 = "";
        }
        if (i.c(190)) {
            if (i.c(5)) {
                h.d(s, "Attempt2: jiFilterStub ".concat(String.valueOf(String.valueOf(s2))));
            }
            return new ey();
        }
        if (n == 0) {
            if (!i.c(256) && b2) {
                h.c(s, "Attempt1a: jiFilterText - error file");
                return new ez();
            }
            final String[] bv = d.bv();
            final String[] bu = d.bu();
            if (bv != null) {
                for (int i = 0; i < bv.length; ++i) {
                    if (s2.equals(bu[i])) {
                        if (i.c(5)) {
                            h.d(s, "Attempt1:".concat(String.valueOf(String.valueOf(bv[i]))));
                        }
                        return new e0(bv[i]);
                    }
                }
            }
            if (s2.startsWith("tif")) {
                if (i.c(5)) {
                    h.d(s, "Attempt2: jiFilterTIFF ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e1();
            }
            if (s2.startsWith("jpg") || s2.startsWith("jpeg")) {
                if (a(d.a6(), true)) {
                    try {
                        if (i.c(5)) {
                            h.d(s, "Attempt3: jiFilterJPGFast ".concat(String.valueOf(String.valueOf(s2))));
                        }
                        return new e2();
                    }
                    catch (Exception ex) {
                        if (i.c(5)) {
                            h.d(s, "Attempt4: jiFilterJG ".concat(String.valueOf(String.valueOf(s2))));
                        }
                        return new e3();
                    }
                }
                if (i.c(5)) {
                    h.d(s, "Attempt5: jiFilterJG ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e3();
            }
            if (s2.startsWith("gif")) {
                if (i.c(5)) {
                    h.d(s, "Attempt7: jiFilterJG ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e3();
            }
            if (s2.startsWith("bmp")) {
                if (i.c(5)) {
                    h.d(s, "Attempt8: jiFilterBmp ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e4();
            }
            if (s2.startsWith("djvu") && a(i.c(12), e.v())) {
                if (i.c(5)) {
                    h.d(s, "Attempt9: jiFilterDJV ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e6();
            }
            if (s2.startsWith("png") && a(i.c(21), d.aa())) {
                if (i.c(5)) {
                    h.d(s, "Attempt11: jiFilterPng ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e7();
            }
            if (s2.startsWith("pdf") && a(true, e.w())) {
                if (i.c(5)) {
                    h.d(s, "Attempt12: jiFilterPDF ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new ci();
            }
            if (s2.startsWith("jpf") && a(true, e.w())) {
                if (i.c(5)) {
                    h.d(s, "Attempt13: jiFilterPDF ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new ci();
            }
            if ((s2.startsWith("jp2") || s2.startsWith("j2k")) && a(true, d.ap())) {
                if (i.c(5)) {
                    h.d(s, "Attempt14: jiFilterJP2 ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e8();
            }
            if (s2.startsWith("fob")) {
                if (i.c(5)) {
                    h.d(s, "Attempt15: jiFilterFilenet ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e9();
            }
            if (s2.startsWith("dib")) {
                if (i.c(5)) {
                    h.d(s, "Attempt16a: jiFilterWinDib ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e5();
            }
            if (fa.a(s2, s3, ad, b2)) {
                if (i.c(5)) {
                    h.d(s, "Attempt18: jiFilterMSG ".concat(String.valueOf(String.valueOf(s2))));
                }
                return fa.a(s);
            }
            if (fc.a(s2, s3, ad, b2) && a(true, e.x())) {
                if (i.c(5)) {
                    h.d(s, "Attempt16b: jiFilterUV ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new fc(s);
            }
            if (!ad.bi(32) && ez.a(s2, s3, ad, false)) {
                if (i.c(5)) {
                    h.d(s, "Attempt10: jiFilterText ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new ez();
            }
            if (i.c(256)) {
                if (i.c(5)) {
                    h.d(s, "Attempt17: jiFilterFilenet ".concat(String.valueOf(String.valueOf(s2))));
                }
                return new e9();
            }
            return null;
        }
        else {
            switch (n) {
                case 1: {
                    return new e1();
                }
                case 2: {
                    if (d.a6()) {
                        if (i.c(5)) {
                            h.d(s, "Attempt18: jiFilterJPGFast");
                        }
                        try {
                            return new e2();
                        }
                        catch (Exception ex2) {
                            return null;
                        }
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt19: jiFilterJG");
                    }
                    return new e3();
                }
                case 3: {
                    if (i.c(5)) {
                        h.d(s, "Attempt20: jiFilterJG");
                    }
                    return new e3();
                }
                case 4: {
                    if (i.c(5)) {
                        h.d(s, "Attempt21: jiFilterBmp");
                    }
                    return new e4();
                }
                case 11: {
                    if (a(true, e.w())) {
                        if (i.c(5)) {
                            h.d(s, "Attempt22: jiFilterPDF");
                        }
                        return new ci();
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt23: jiDummyFilter");
                    }
                    return new fd();
                }
                case 12: {
                    if (i.c(5)) {
                        h.d(s, "Attempt36: jiFilterMSG");
                    }
                    if (fa.a(s2, ad, b2)) {
                        if (i.c(5)) {
                            h.d(s, "Attempt36a: jiFilterMSG ".concat(String.valueOf(String.valueOf(s2))));
                        }
                        return fa.a(s);
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt36b: jiDummyFilter");
                    }
                    return new fd();
                }
                case 13: {
                    if (a(true, e.x())) {
                        if (i.c(5)) {
                            h.d(s, "Attempt23_a: jiFilterUV");
                        }
                        return new fc(s);
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt23_b: jiDummyFilter");
                    }
                    return new fd();
                }
                case 5: {
                    if (i.c(5)) {
                        h.d(s, "Attempt24: jiFilterFilenet");
                    }
                    return new e9();
                }
                case 6: {
                    if (i.c(263) && i.c(247)) {
                        if (i.c(5)) {
                            h.d(s, "Attempt25A: jiFilterFilenetCold2");
                        }
                        return new fe();
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt25B: jiFilterFilenetCold");
                    }
                    return new ff();
                }
                case 7: {
                    if (a(true, d.ap())) {
                        if (i.c(5)) {
                            h.d(s, "Attempt26: jiFilterJP2");
                        }
                        return new e8();
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt27: jiDummyFilter");
                    }
                    return new fd();
                }
                case 8: {
                    if (a(i.c(12), e.v())) {
                        if (i.c(5)) {
                            h.d(s, "Attempt28: jiFilterDJV");
                        }
                        return new e6();
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt29: jiDummyFilter");
                    }
                    return new fd();
                }
                case 9: {
                    if (i.c(5)) {
                        h.d(s, "Attempt30: jiFilterWinDib");
                    }
                    return new e5();
                }
                case 10: {
                    if (a(i.c(21), d.aa())) {
                        if (i.c(5)) {
                            h.d(s, "Attempt31: jiFilterPng");
                        }
                        return new e7();
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt32: jiDummyFilter");
                    }
                    return new fd();
                }
                default: {
                    final String[] bv2 = d.bv();
                    d.bu();
                    if (bv2 == null) {
                        ew.a = true;
                        if (i.c(5)) {
                            h.d(s, "Attempt35: jiFilterText");
                        }
                        return new ez();
                    }
                    if (n - 13 < bv2.length) {
                        if (i.c(5)) {
                            h.d(s, "Attempt33: ".concat(String.valueOf(String.valueOf(bv2[n - 6]))));
                        }
                        return new e0(bv2[n - 6]);
                    }
                    if (i.c(5)) {
                        h.d(s, "Attempt34: jiFilterText");
                    }
                    return new ez();
                }
            }
        }
    }
    
    private static final boolean a(final boolean b, final boolean b2) {
        if (i.c(256)) {
            return b && b2;
        }
        return b;
    }
    
    public static final void a() {
        fc.c();
        ci.c();
    }
    
    static {
        ew.a = false;
    }
}

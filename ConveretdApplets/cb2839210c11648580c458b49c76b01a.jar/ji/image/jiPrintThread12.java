// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.Component;
import ji.util.e;
import ji.util.i;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.Book;
import java.awt.print.Paper;
import ji.util.d;
import ji.v1event.af;
import ji.document.ad;
import ji.awt.c;
import ji.filter.ck;
import ji.io.ac;
import ji.document.c6;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.Dimension;
import ji.document.cw;

public class jiPrintThread12 implements Runnable, jiJ2Interface
{
    cw document;
    boolean allPages;
    Dimension pSize;
    boolean lastPageFirst;
    PrinterJob pj12;
    PageFormat pf;
    cy printZone;
    cy separatorZone;
    cy fullPage;
    c6 thumbs;
    boolean abortPrint;
    boolean doneSeparator;
    int numSelected;
    boolean[] selectedPages;
    boolean printVisibleOnly;
    boolean printedOK;
    String parentId;
    boolean printTransformed;
    ac sepRas;
    String localSepFile;
    dx sepHeader;
    ck sepFilter;
    c sepProperties;
    String strSepContent;
    String strSepMimeType;
    Object[] separatorPages;
    
    public jiPrintThread12() {
        this.document = null;
        this.allPages = false;
        this.pSize = null;
        this.lastPageFirst = false;
        this.pj12 = null;
        this.pf = null;
        this.printZone = null;
        this.separatorZone = null;
        this.fullPage = null;
        this.thumbs = null;
        this.abortPrint = false;
        this.doneSeparator = false;
        this.numSelected = 0;
        this.selectedPages = null;
        this.printVisibleOnly = false;
        this.printedOK = true;
        this.parentId = null;
        this.printTransformed = false;
        this.sepRas = null;
        this.localSepFile = null;
        this.sepHeader = null;
        this.sepFilter = null;
        this.sepProperties = null;
        this.strSepContent = null;
        this.strSepMimeType = null;
        this.separatorPages = null;
    }
    
    public void initialize(final cy printZone, final cy separatorZone, final Object[] separatorPages, cy fullPage, c6 thumbs, final cw document, final boolean allPages, final boolean b, final int numSelected, final boolean[] selectedPages, final boolean printVisibleOnly, final boolean printTransformed, final ad ad, final af af, final String parentId, final int n, final String s) {
        this.parentId = parentId;
        if (fullPage == null) {
            fullPage = new cy(ad, af, parentId, n, true, false);
        }
        if (thumbs == null) {
            thumbs = new c6(ad, af, parentId);
        }
        this.pj12 = nx.a(allPages, numSelected, s);
        if (this.pj12 != null) {
            this.printZone = printZone;
            this.separatorZone = separatorZone;
            this.fullPage = fullPage;
            this.thumbs = thumbs;
            this.document = document;
            this.allPages = allPages;
            this.numSelected = numSelected;
            this.selectedPages = selectedPages;
            this.printVisibleOnly = printVisibleOnly;
            this.printTransformed = printTransformed;
            this.separatorPages = separatorPages;
            this.pf = new PageFormat();
            this.pf = this.pj12.defaultPage(this.pf);
            final Paper paper = this.pf.getPaper();
            final double ac = d.ac();
            paper.setImageableArea(ac, ac, this.pf.getPaper().getWidth() - ac * 2, this.pf.getPaper().getHeight() - ac * 2);
            this.pf.setPaper(paper);
            if (ac == 0.0) {
                this.pf = this.pj12.validatePage(this.pf);
            }
            this.doneSeparator = false;
        }
    }
    
    public boolean isAccepted() {
        if (this.numSelected > 0) {
            final Book pageable = new Book();
            final PageFormat pf = this.pf;
            for (int i = 0; i < this.document.e(true); ++i) {
                if (this.selectedPages[i]) {
                    pageable.append(new ny(this.document, this.pj12, false, this.document.e(true), this.pSize, this.pf, i + 1, this.printVisibleOnly, this.printTransformed, false, this.parentId), pf);
                }
            }
            this.pj12.setPageable(pageable);
        }
        else if (this.allPages) {
            final Book pageable2 = new Book();
            final PageFormat pf2 = this.pf;
            for (int j = 0; j < this.document.e(true); ++j) {
                pageable2.append(new ny(this.document, this.pj12, false, this.document.e(true), this.pSize, this.pf, j + 1, this.printVisibleOnly, this.printTransformed, false, this.parentId), pf2);
            }
            this.pj12.setPageable(pageable2);
        }
        else {
            final boolean b = true;
            final Book pageable3 = new Book();
            pageable3.append(new ny(this.document, this.pj12, false, (int)(b ? 1 : 0), this.pSize, this.pf, this.document.af(), this.printVisibleOnly, this.printTransformed, false, this.parentId), this.pf);
            this.pj12.setPageable(pageable3);
        }
        boolean printDialog = true;
        if (i.c(49)) {
            final PageFormat pageDialog = this.pj12.pageDialog(this.pf);
            printDialog = (pageDialog != this.pf);
            this.pf = pageDialog;
        }
        if (printDialog) {
            printDialog = this.pj12.printDialog();
        }
        if (printDialog) {
            this.pf = this.pj12.validatePage(this.pf);
            this.pSize = new Dimension((int)this.pf.getImageableWidth(), (int)this.pf.getImageableHeight());
            return true;
        }
        return false;
    }
    
    public final void abortPrinting() {
        this.abortPrint = true;
    }
    
    public void run() {
        boolean b = false;
        if (this.pj12 != null) {
            try {
                if (this.printVisibleOnly) {
                    this.printZone.a(true, String.valueOf(String.valueOf(d.b(376, this.parentId))).concat("..."));
                }
                this.abortPrint = false;
                final dx a = this.fullPage.r().a(true);
                try {
                    this.doneSeparator = false;
                    this.printZone.be();
                    this.fullPage.a1(true);
                    this.thumbs.h(true);
                    if (!this.printVisibleOnly) {
                        this.printZone.aa(this.fullPage.am());
                        this.printZone.a(this.fullPage.c1());
                        this.printZone.a(this.document.e, a.f, a.f, a.h, this.document.s, this.document.s, false, this.document.g, a, this.document.k, this.document.l, null, this.document.c, this.document.dc());
                        if (d.ey() || this.printTransformed) {
                            this.printZone.a(this.fullPage.c7(), -1);
                        }
                        try {
                            if (d.di() == 1) {
                                final boolean[] dv = this.fullPage.dv();
                                b = false;
                                for (int i = 0; i < dv.length; ++i) {
                                    if (dv[i]) {
                                        b = true;
                                    }
                                }
                            }
                        }
                        catch (Exception ex2) {}
                        if (b) {
                            this.printZone.b(this.fullPage.dv());
                        }
                    }
                    if (e.ag()) {
                        this.printZone.a(this.fullPage.cj(), this.fullPage.bq(), this.fullPage.bp(), false);
                    }
                    if (this.numSelected > 0) {
                        final Book pageable = new Book();
                        final PageFormat pf = this.pf;
                        for (int j = 0; j < this.document.e(true); ++j) {
                            if (this.selectedPages[j]) {
                                if (this.separatorZone != null) {
                                    this.printSeparatorPage(pageable, pf, j + 1, this.document.e(true), this.document.a(j));
                                }
                                pageable.append(new ny(this.document, this.pj12, false, this.document.e(true), this.pSize, this.pf, j + 1, this.printVisibleOnly, this.printTransformed, false, this.parentId), pf);
                            }
                        }
                        this.pj12.setPageable(pageable);
                        this.pj12.print();
                    }
                    else if (this.allPages) {
                        final Book pageable2 = new Book();
                        final PageFormat pf2 = this.pf;
                        for (int k = 0; k < this.document.e(true); ++k) {
                            if (this.separatorZone != null) {
                                this.printSeparatorPage(pageable2, pf2, k + 1, this.document.e(true), this.document.a(k));
                            }
                            pageable2.append(new ny(this.document, this.pj12, false, this.document.e(true), this.pSize, this.pf, k + 1, this.printVisibleOnly, this.printTransformed, false, this.parentId), pf2);
                        }
                        this.pj12.setPageable(pageable2);
                        this.pj12.print();
                    }
                    else {
                        int n = 1;
                        final Book pageable3 = new Book();
                        final PageFormat pf3 = this.pf;
                        if (this.separatorZone != null && this.printSeparatorPage(pageable3, pf3, this.document.af(), this.document.e(true), this.document.a(n - 1))) {
                            ++n;
                        }
                        pageable3.append(new ny(this.document, this.pj12, false, n, this.pSize, this.pf, this.document.af(), this.printVisibleOnly, this.printTransformed, false, this.parentId), pf3);
                        this.pj12.setPageable(pageable3);
                        this.pj12.print();
                    }
                    this.document.al(0);
                }
                catch (Exception ex) {
                    this.printedOK = false;
                    d.a(ex, "Print Problem", this.document, 5, null, this.parentId);
                }
                try {
                    if (!this.abortPrint && !this.document.n) {
                        if (this.fullPage != null) {
                            this.fullPage.h(7);
                        }
                        if (this.thumbs != null) {
                            this.thumbs.e(7);
                        }
                    }
                }
                catch (Exception ex3) {}
                d.ew();
                this.document.l("");
            }
            finally {
                try {
                    if (this.pj12 != null) {
                        this.pj12 = null;
                    }
                }
                catch (Exception ex4) {}
                try {
                    if (this.printZone != null) {
                        this.printZone.bj();
                        if (!this.printVisibleOnly) {
                            this.printZone.q();
                            this.printZone.dc();
                            this.printZone.t();
                            this.printZone.releaseResources();
                        }
                        else {
                            this.printZone.a(false, "");
                        }
                        this.printZone = null;
                    }
                }
                catch (Exception ex5) {}
                try {
                    if (this.fullPage != null) {
                        this.fullPage.a1(false);
                    }
                    if (this.thumbs != null) {
                        this.thumbs.h(false);
                    }
                }
                catch (Exception ex6) {}
                int ea = 1;
                try {
                    if (this.printZone != null) {
                        ea = this.printZone.ea();
                    }
                }
                catch (Exception ex7) {}
                this.document.h(ea, this.printedOK && !this.abortPrint);
                this.document = null;
                this.separatorZone = null;
                this.separatorPages = null;
                this.abortPrint = false;
            }
        }
    }
    
    private final boolean printSeparatorPage(final Book book, final PageFormat pageFormat, int n, final int n2, final String s) {
        boolean b = false;
        try {
            int n3 = 0;
            final int n4 = n;
            if (this.separatorZone != null) {
                if (n < this.separatorPages.length && this.separatorPages[n] != null && !this.separatorPages[n].toString().endsWith("-")) {
                    n3 = 1;
                }
                if (n3 == 0 && !this.doneSeparator) {
                    this.doneSeparator = true;
                    if (n > 0 && this.separatorPages != null && n < n2 && this.separatorPages[0] != null && !this.separatorPages[0].toString().endsWith("-")) {
                        n3 = 1;
                        n = 0;
                    }
                }
                if (n < this.separatorPages.length && n3 != 0) {
                    this.separatorZone.q(s);
                    this.separatorZone.r().v = n4 + 1;
                    book.append(new ny(this.document, this.pj12, false, this.document.e(true), this.pSize, this.pf, n, this.printVisibleOnly, this.printTransformed, true, this.parentId), pageFormat);
                    b = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
}

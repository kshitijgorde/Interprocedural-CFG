// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.io.RandomAccessFile;
import java.io.File;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public class bE extends bF
{
    private TextField a;
    private TextField b;
    private aX a;
    private Checkbox I;
    aS c;
    aS d;
    private j e;
    private Choice y;
    private boolean i;
    private int ap;
    private u h;
    
    public cF a() {
        final bi bi = new bi(-999, "");
        bi.a = "";
        return bi;
    }
    
    public void a(final cF cf) {
        final ax ax = (ax)cf;
        this.a.setText(ax.f());
        this.b.setText(ax.a);
        this.a.a(new Color(ax.aN));
        this.I.setState(ax.d(37));
    }
    
    public boolean a(final cF cf) {
        final ax ax = (ax)cf;
        final String text = this.a.getText();
        final String text2 = this.b.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a word to be censored."), super.i).setVisible(true);
            return false;
        }
        ax.a = text2;
        ax.d(text);
        ax.aN = this.a.aA;
        ax.a(37, this.I.getState());
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Replace:"), this.a);
        bk.a(ao.e("With:"), this.b, this.a, 0);
        bk.a("", this.I);
        bk.a("", ao.e("Leave blank to replace with asterisks (***)."));
    }
    
    public void c() {
        final int selectedIndex = this.y.getSelectedIndex();
        if (super.e || selectedIndex != this.ap || ((cf)super.f.b(0)).k()) {
            final cD cd = new cD(67333, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final ax ax = (ax)this.a(i);
                if (ax.aw) {
                    cd.a(n, ax.d());
                    cd.a(n, 0, ax.h());
                    if (!ax.d(63)) {
                        cd.a(n, 0, ax.f());
                        cd.a(n, 1, ax.a);
                        cd.a(n, 1, ax.aN);
                        cd.a(n, 37, ax.d(37));
                    }
                    ++n;
                }
                ++i;
            }
            cd.a(-1, 60, selectedIndex == 1 && !this.i);
            cd.a(-1, 61, selectedIndex == 0 && !this.i);
            cd.a(-1, 62, selectedIndex == 0 && this.i);
            cd.a(-1, 59, this.i);
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void b(final cF cf) {
        super.b(cf);
        super.f.a(cf, new Color(cf.aN), Color.white);
    }
    
    public void d() {
        super.d();
        super.i.h.a(false);
        try {
            for (int i = 0; i < super.i.h.b(); ++i) {
                this.b(new bi((ax)super.i.h.a(i)));
            }
        }
        finally {
            super.i.h.a();
        }
        if (this.i) {
            this.y.enable();
            if (cD.a(super.i.d, 62)) {
                this.y.select(0);
            }
            else {
                this.y.select(1);
            }
        }
        else {
            final long n = super.i.e | super.i.d;
            if (cD.a(n, 62)) {
                this.y.select(0);
                this.y.disable();
            }
            else {
                this.y.enable();
                if (cD.a(n, 61)) {
                    this.y.select(0);
                }
                else if (cD.a(n, 60)) {
                    this.y.select(1);
                }
                else {
                    this.y.select(2);
                }
            }
        }
        this.ap = this.y.getSelectedIndex();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.c) {
                    this.x();
                }
                if (event.target == this.d) {
                    this.e();
                }
                return super.handleEvent(event);
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void x() {
        try {
            this.j();
        }
        catch (IOException ex) {}
    }
    
    public void e() {
        try {
            this.f();
        }
        catch (IOException ex) {}
    }
    
    public void j() {
        final Frame frame = new Frame();
        String string = "";
        ax ax = null;
        final int n = -1;
        final FileDialog fileDialog = new FileDialog(frame, ao.e("Select file to import."), 0);
        fileDialog.show();
        new StringBuffer().append(fileDialog.getDirectory()).append(fileDialog.getFile()).toString();
        final File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
        if (file == null) {
            return;
        }
        if (file.exists()) {
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                randomAccessFile.seek(0L);
                while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                    final char c = (char)randomAccessFile.read();
                    switch (c) {
                        case 9: {
                            continue;
                        }
                        case 59: {
                            ax = new bi(n, string.trim());
                            string = "";
                            continue;
                        }
                        case 10:
                        case 12:
                        case 13: {
                            final String trim = string.trim();
                            if (trim.length() != 0) {
                                ax.a = trim;
                            }
                            string = "";
                            this.b(ax);
                            continue;
                        }
                        default: {
                            string += c;
                            continue;
                        }
                    }
                }
            }
            catch (IOException ex) {}
            finally {
                randomAccessFile.close();
            }
        }
        super.e = true;
    }
    
    public void f() {
        final Frame frame = new Frame();
        final String property = System.getProperty("line.separator");
        final FileDialog fileDialog = new FileDialog(frame, ao.e("Select file to save as."), 1);
        fileDialog.show();
        new StringBuffer().append(fileDialog.getDirectory()).append(fileDialog.getFile()).toString();
        final File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
        if (file == null) {
            return;
        }
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0L);
            for (int i = 0; i < super.x.b(); ++i) {
                final bi bi = (bi)super.x.a(i);
                final String string = bi.a("name") + " ; " + bi.a("replace") + property;
                byte[] array;
                try {
                    array = string.getBytes();
                }
                catch (Exception ex) {
                    array = string.getBytes();
                }
                randomAccessFile.write(array);
            }
        }
        catch (IOException ex2) {}
        finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                }
                catch (IOException ex3) {}
            }
        }
        super.e = true;
    }
    
    public bE(final u h, final boolean i) {
        super(h, ao.e("ChatWatch"), ao.e("ChatWatch"));
        this.a = new TextField(20);
        this.b = new TextField(20);
        (this.a = new aX(h, this.b, true)).a(h.k, false, true);
        (this.I = new Checkbox(ao.e("Use message text color"), true)).setState(true);
        this.c = new aS(80, 20);
        this.d = new aS(80, 20);
        this.y = new Choice();
        this.h = h;
        this.i = i;
        if (i) {
            this.y.addItem(ao.e("On For All Sites"));
            this.y.addItem(ao.e("ChatMaster decides"));
        }
        else {
            this.y.addItem(ao.e("Always On"));
            this.y.addItem(ao.e("Default On"));
            this.y.addItem(ao.e("Default Off"));
        }
        this.a(ao.e("ChatWatch Mode:"), this.y);
        if (!this.h.h) {
            this.c.a(ao.e("Import"));
            this.d.a(ao.e("Export"));
            final FontMetrics fontMetrics = this.c.getFontMetrics(this.c.getFont());
            int stringWidth = fontMetrics.stringWidth(ao.e("Export"));
            final int stringWidth2 = fontMetrics.stringWidth(ao.e("Import"));
            if (stringWidth2 > stringWidth) {
                stringWidth = stringWidth2;
            }
            stringWidth += 20;
            this.c.resize(stringWidth, 20);
            this.d.resize(stringWidth, 20);
            this.a("", this.c, this.d);
        }
        final String e = ao.e("Replace:");
        this.a(this.e = new j(e.substring(0, e.length() - 1), "replace"));
        this.e.c(false);
        super.g.b(135);
        final cf cf = new cf(ao.e("Show To Users"), "showtousers");
        cf.d(true);
        this.a(cf, 0);
        cf.b(90);
        cf.c(false);
    }
}

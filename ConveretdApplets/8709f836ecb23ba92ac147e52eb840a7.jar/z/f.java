// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.AbstractListModel;

public class f extends AbstractListModel implements ComboBoxModel
{
    private at a;
    private at b;
    private ArrayList c;
    private at[] d;
    private static /* synthetic */ boolean e;
    
    public f() {
        this.b = null;
        this.c = new ArrayList();
        this.d = null;
        this.f();
    }
    
    private at[] d() {
        if (this.d == null) {
            this.e();
        }
        return this.d;
    }
    
    private void e() {
        final ArrayList<at> list;
        (list = new ArrayList<at>()).add(this.a);
        for (final at at : this.a.b) {
            list.add(at);
            if (at.c.equals(au.c())) {
                final Iterator iterator2 = at.b.iterator();
                while (iterator2.hasNext()) {
                    final at at2;
                    if ((at2 = iterator2.next()).c.equals(au.d())) {
                        list.add(at2);
                    }
                }
            }
        }
        if (this.b != null) {
            final ArrayList<at> list2 = new ArrayList<at>();
            at at3;
            for (at3 = this.b; !list.contains(at3); at3 = at3.a) {
                list2.add(at3);
            }
            final int n = list.indexOf(at3) + 1;
            final Iterator<at> iterator3 = list2.iterator();
            while (iterator3.hasNext()) {
                list.add(n, iterator3.next());
            }
        }
        if (this.d != null) {
            this.fireIntervalRemoved(this, 0, this.d.length - 1);
        }
        this.d = new at[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            this.d[i] = list.get(i);
        }
        this.fireIntervalAdded(this, 0, this.d.length - 1);
    }
    
    public final String a(final String s) {
        for (final String s2 : this.c) {
            if (s != null && s2.endsWith(s)) {
                return s2;
            }
        }
        return s;
    }
    
    private void f() {
        this.a = new at(null);
        this.a.c = au.f();
        this.a.d = "";
        if (au.b() || au.a()) {
            final String property = System.getProperty("user.home");
            final String string = property + File.separator + au.c();
            final String string2 = property + File.separator + au.d();
            final String string3 = property + File.separator + au.e();
            final File file;
            if ((file = new File(string)).exists()) {
                this.c.add(string);
                this.a.a(file).g = o.d;
            }
            final File file2;
            if ((file2 = new File(string2)).exists()) {
                this.c.add(string2);
                this.a.a(file2).g = o.e;
            }
            final File file3;
            if ((file3 = new File(string3)).exists()) {
                this.c.add(string3);
                this.a.a(file3).g = o.f;
            }
            if (au.b()) {
                final String s = "/Volumes";
                final File file4;
                if ((file4 = new File(s)).exists()) {
                    this.c.add(s);
                    final at a;
                    (a = this.a.a(file4)).g = o.g;
                    a.c = "USB";
                }
            }
        }
        else {
            final String property2 = System.getProperty("user.home");
            final String string4 = property2 + "\\" + au.c();
            final String string5 = string4 + "\\" + au.d();
            final String string6 = property2 + "\\" + au.e();
            final File file5;
            if ((file5 = new File(string4)).exists()) {
                this.c.add(string4);
                final at a2;
                (a2 = this.a.a(file5)).g = o.d;
                final File file6;
                if ((file6 = new File(string5)).exists()) {
                    this.c.add(string5);
                    a2.a(file6).g = o.e;
                }
            }
            final File file7;
            if ((file7 = new File(string6)).exists()) {
                this.c.add(string6);
                this.a.a(file7).g = o.f;
            }
        }
        File[] listRoots;
        for (int length = (listRoots = File.listRoots()).length, i = 0; i < length; ++i) {
            this.a.a(listRoots[i]).g = o.c;
        }
    }
    
    public Object getSelectedItem() {
        return this.b;
    }
    
    public void setSelectedItem(final Object o) {
        this.b = (at)o;
        this.fireContentsChanged(this, 0, this.getSize() - 1);
    }
    
    public final void b(final String s) {
        if (s == null) {
            return;
        }
        final at a;
        if ((a = this.a.a(s, 2)) != null) {
            this.setSelectedItem(a);
            return;
        }
        if (!new File(s).exists()) {
            System.out.println("File not found: " + s);
            this.b(null);
            return;
        }
        final at d;
        if ((d = this.a.d(s)) != null) {
            this.setSelectedItem(d);
            return;
        }
        String s2 = null;
        for (final String s3 : this.c) {
            if (s.startsWith(s3)) {
                s2 = s3 + File.separator;
                break;
            }
        }
        if (s2 == null) {
            s2 = au.f(s);
        }
        at selectedItem;
        if ((selectedItem = this.a.d(s2)) == null) {
            this.setSelectedItem(this.a.a(new File(s)));
            this.e();
            return;
        }
        String string = s2;
        for (int i = 1 + au.g(string); i < 100; ++i) {
            final String a2 = au.a(s, i);
            if (!f.e && a2 == null) {
                throw new AssertionError();
            }
            string = string + a2 + File.separator;
            if (selectedItem.a(a2)) {
                selectedItem = selectedItem.b(a2);
            }
            else if ((selectedItem = selectedItem.a(new File(string))).c(s)) {
                this.setSelectedItem(selectedItem);
                this.e();
                return;
            }
        }
        throw new RuntimeException("Could not find folder: " + s);
    }
    
    public Object getElementAt(final int n) {
        return this.d()[n];
    }
    
    public int getSize() {
        return this.d().length;
    }
    
    public final File a() {
        final Object selectedItem;
        if ((selectedItem = this.getSelectedItem()) == null) {
            return null;
        }
        return ((at)selectedItem).e;
    }
    
    public final ArrayList b() {
        return this.a.a(1);
    }
    
    public final String c() {
        return this.a.c;
    }
    
    static {
        f.e = !f.class.desiredAssertionStatus();
    }
}

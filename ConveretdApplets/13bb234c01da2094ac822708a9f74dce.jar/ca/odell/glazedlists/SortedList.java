// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import java.util.Iterator;
import ca.odell.glazedlists.impl.GlazedListsImpl;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import ca.odell.glazedlists.impl.adt.barcode2.SimpleTreeIterator;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import java.util.Comparator;
import ca.odell.glazedlists.impl.adt.barcode2.SimpleTree;
import ca.odell.glazedlists.impl.adt.barcode2.Element;

public final class SortedList extends TransformedList
{
    private static final Element f;
    private SimpleTree g;
    private SimpleTree h;
    private Comparator i;
    private int j;
    static final /* synthetic */ boolean b;
    
    public SortedList(final EventList list, final Comparator comparator) {
        super(list);
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.a(comparator);
        list.a(this);
    }
    
    public void a(final ListEvent listEvent) {
        if (listEvent.d()) {
            final int[] e = listEvent.e();
            final int[] array = new int[this.h.b()];
            int n = 0;
            final SimpleTreeIterator simpleTreeIterator = new SimpleTreeIterator(this.h);
            while (simpleTreeIterator.a()) {
                simpleTreeIterator.b();
                array[this.g.a((Element)simpleTreeIterator.d(), (byte)1)] = n;
                ++n;
            }
            final int[] array2 = new int[this.h.b()];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = array[e[i]];
            }
            final Element[] array3 = new Element[this.g.b()];
            int n2 = 0;
            final SimpleTreeIterator simpleTreeIterator2 = new SimpleTreeIterator(this.g);
            while (simpleTreeIterator2.a()) {
                simpleTreeIterator2.b();
                array3[n2] = simpleTreeIterator2.e();
                ++n2;
            }
            Arrays.sort(array3, this.h.a());
            final int[] array4 = new int[this.h.b()];
            boolean b = false;
            int n3 = 0;
            final SimpleTreeIterator simpleTreeIterator3 = new SimpleTreeIterator(this.h);
            while (simpleTreeIterator3.a()) {
                simpleTreeIterator3.b();
                final Element e2 = simpleTreeIterator3.e();
                final Element element = array3[n3];
                e2.a(element);
                element.a(e2);
                array4[n3] = array2[this.g.a(element, (byte)1)];
                b = (b || n3 != array4[n3]);
                ++n3;
            }
            if (b) {
                this.c.b();
                this.c.a(array4);
                this.c.c();
            }
            return;
        }
        this.c.b();
        final LinkedList<Element> list = new LinkedList<Element>();
        final ArrayList<Element> list2 = new ArrayList<Element>();
        final ArrayList<Object> list3 = new ArrayList<Object>();
        while (listEvent.b()) {
            final int f = listEvent.f();
            final int j = listEvent.i();
            if (j == 2) {
                list.addLast(this.g.a(f, SortedList.f, 1));
            }
            else if (j == 1) {
                final Element element2 = (Element)this.g.a(f).a();
                element2.a(2);
                list2.add(element2);
                list3.add(listEvent.j());
            }
            else {
                if (j != 0) {
                    continue;
                }
                final Element a = this.g.a(f);
                final Object k = listEvent.j();
                this.g.a(a);
                this.c.b(this.b(a), k);
            }
        }
        for (int l = 0; l < list2.size(); ++l) {
            final Element element3 = list2.get(l);
            if (element3.d() == 2) {
                Element element4 = null;
                Element element5 = null;
                Element element6 = element3;
                for (Element element7 = element3.f(); element7 != null; element7 = element7.f()) {
                    if (element7.d() == 0) {
                        element4 = element7;
                        break;
                    }
                    element6 = element7;
                }
                for (Element element8 = element3.e(); element8 != null; element8 = element8.e()) {
                    if (element8.d() == 0) {
                        element5 = element8;
                        break;
                    }
                }
                final Comparator a2 = this.h.a();
                for (Element e3 = element6; e3 != element5; e3 = e3.e()) {
                    if (element5 != null && a2.compare(e3.a(), element5.a()) > 0) {
                        e3.a(1);
                    }
                    else if (element4 != null && a2.compare(e3.a(), element4.a()) < 0) {
                        e3.a(1);
                    }
                    else {
                        e3.a(0);
                        element4 = e3;
                    }
                }
            }
        }
        for (int n4 = 0; n4 < list2.size(); ++n4) {
            final Object value = list3.get(n4);
            final Element element9 = list2.get(n4);
            if (!SortedList.b && element9.d() == 2) {
                throw new AssertionError();
            }
            final int a3 = this.h.a(element9, (byte)1);
            if (element9.d() == 0) {
                this.c.c(a3, value);
            }
            else if (this.j == 1) {
                this.c.c(a3, value);
            }
            else {
                this.h.a(element9);
                this.c.b(a3, value);
                this.c.a(this.a((Element)element9.a()));
            }
        }
        while (!list.isEmpty()) {
            this.c.a(this.a(list.removeFirst()));
        }
        this.c.c();
    }
    
    private int a(final Element element) {
        final Element a = this.h.a((byte)1, element, 1);
        element.a(a);
        return this.h.a(a, (byte)1);
    }
    
    private int b(final Element element) {
        final int a = this.h.a((Element)element.a(), (byte)1);
        this.h.a(a, 1);
        return a;
    }
    
    protected int a(final int n) {
        return this.g.a((Element)this.h.a(n).a(), (byte)1);
    }
    
    protected boolean a() {
        return true;
    }
    
    public Comparator d() {
        return this.i;
    }
    
    public void a(final Comparator i) {
        this.i = i;
        final SimpleTree h = this.h;
        Comparator comparator;
        if (i != null) {
            comparator = new SortedList$ElementComparator(this, i);
        }
        else {
            comparator = new SortedList$ElementRawOrderComparator(this, null);
        }
        this.h = new SimpleTree(comparator);
        if (h == null && this.g == null) {
            this.g = new SimpleTree();
            for (int j = 0; j < this.a.size(); ++j) {
                this.a(this.g.a(j, SortedList.f, 1));
            }
            return;
        }
        if (this.a.size() == 0) {
            return;
        }
        final SimpleTreeIterator simpleTreeIterator = new SimpleTreeIterator(this.g);
        while (simpleTreeIterator.a()) {
            simpleTreeIterator.b();
            this.a(simpleTreeIterator.e());
        }
        final int[] array = new int[this.size()];
        int n = 0;
        final SimpleTreeIterator simpleTreeIterator2 = new SimpleTreeIterator(h);
        while (simpleTreeIterator2.a()) {
            simpleTreeIterator2.b();
            array[this.h.a((Element)((Element)simpleTreeIterator2.e().a()).a(), (byte)1)] = n;
            ++n;
        }
        this.c.b();
        this.c.a(array);
        this.c.c();
    }
    
    public int indexOf(final Object o) {
        if (this.j != 0 || this.i == null) {
            return this.a.indexOf(o);
        }
        int i = this.h.a(o, true, false, (byte)1);
        if (i == -1) {
            return -1;
        }
        while (i < this.size()) {
            final Object value = this.get(i);
            if (this.i.compare(o, value) != 0) {
                return -1;
            }
            if (GlazedListsImpl.a(o, value)) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public int lastIndexOf(final Object o) {
        if (this.j != 0 || this.i == null) {
            return this.a.lastIndexOf(o);
        }
        int i = this.h.a(o, false, false, (byte)1);
        if (i == -1) {
            return -1;
        }
        while (i > -1) {
            final Object value = this.get(i);
            if (this.i.compare(o, value) != 0) {
                return -1;
            }
            if (GlazedListsImpl.a(o, value)) {
                return i;
            }
            --i;
        }
        return -1;
    }
    
    public boolean contains(final Object o) {
        return this.indexOf(o) != -1;
    }
    
    public Iterator iterator() {
        return new SortedList$SortedListIterator(this, null);
    }
    
    static {
        b = !SortedList.class.desiredAssertionStatus();
        f = null;
    }
}

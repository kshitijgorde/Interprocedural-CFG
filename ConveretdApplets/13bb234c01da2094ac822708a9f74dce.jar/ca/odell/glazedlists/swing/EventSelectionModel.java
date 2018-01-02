// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ca.odell.glazedlists.ListSelection$Listener;
import java.util.ArrayList;
import ca.odell.glazedlists.EventList;
import java.util.List;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.ListSelection;
import javax.swing.ListSelectionModel;

public final class EventSelectionModel implements ListSelectionModel
{
    private ListSelection a;
    private TransformedList b;
    private boolean c;
    private final List d;
    private boolean e;
    private int f;
    private int g;
    
    public EventSelectionModel(final EventList list) {
        this.c = true;
        this.d = new ArrayList();
        this.e = false;
        this.f = -1;
        this.g = -1;
        list.b().b().a();
        try {
            this.b = GlazedListsSwing.a(list);
            (this.a = new ListSelection(this.b)).a(new EventSelectionModel$SwingSelectionListener(this, null));
        }
        finally {
            list.b().b().b();
        }
    }
    
    public EventList a() {
        this.b.b().b().a();
        try {
            return this.a.a();
        }
        finally {
            this.b.b().b().b();
        }
    }
    
    private void a(final int f, final int g) {
        if (this.e) {
            if (this.f == -1 || f < this.f) {
                this.f = f;
            }
            if (this.g == -1 || g > this.g) {
                this.g = g;
            }
        }
        final ListSelectionEvent listSelectionEvent = new ListSelectionEvent(this, f, g, this.e);
        for (int i = 0; i < this.d.size(); ++i) {
            ((ListSelectionListener)this.d.get(i)).valueChanged(listSelectionEvent);
        }
    }
    
    public void setSelectionInterval(final int n, final int n2) {
        if (!this.c) {
            return;
        }
        this.b.b().a().a();
        try {
            this.a.c(n, n2);
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public void addSelectionInterval(final int n, final int n2) {
        if (!this.c) {
            return;
        }
        this.b.b().a().a();
        try {
            this.a.b(n, n2);
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public void removeSelectionInterval(final int n, final int n2) {
        if (!this.c) {
            return;
        }
        if (n == 0 && n2 == 0 && this.b.isEmpty()) {
            return;
        }
        this.b.b().a().a();
        try {
            this.a.a(n, n2);
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public boolean isSelectedIndex(final int n) {
        return this.a.a(n);
    }
    
    public int getAnchorSelectionIndex() {
        return this.a.c();
    }
    
    public void setAnchorSelectionIndex(final int n) {
        if (!this.c) {
            return;
        }
        this.b.b().a().a();
        try {
            this.a.e(n);
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public int getLeadSelectionIndex() {
        return this.a.d();
    }
    
    public void setLeadSelectionIndex(final int n) {
        if (!this.c) {
            return;
        }
        this.b.b().a().a();
        try {
            this.a.f(n);
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public int getMinSelectionIndex() {
        return this.a.f();
    }
    
    public int getMaxSelectionIndex() {
        return this.a.g();
    }
    
    public void clearSelection() {
        if (!this.c) {
            return;
        }
        this.b.b().a().a();
        try {
            this.a.b();
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public boolean isSelectionEmpty() {
        this.b.b().b().a();
        try {
            return this.a.a().isEmpty();
        }
        finally {
            this.b.b().b().b();
        }
    }
    
    public void insertIndexInterval(final int n, final int n2, final boolean b) {
    }
    
    public void removeIndexInterval(final int n, final int n2) {
    }
    
    public void setValueIsAdjusting(final boolean e) {
        this.e = e;
        if (!e && this.f != -1 && this.g != -1) {
            this.b.b().a().a();
            try {
                this.a(this.f, this.g);
                this.f = -1;
                this.g = -1;
            }
            finally {
                this.b.b().a().b();
            }
        }
    }
    
    public boolean getValueIsAdjusting() {
        return this.e;
    }
    
    public void setSelectionMode(final int n) {
        this.b.b().a().a();
        try {
            this.a.g(n);
        }
        finally {
            this.b.b().a().b();
        }
    }
    
    public int getSelectionMode() {
        return this.a.e();
    }
    
    public void addListSelectionListener(final ListSelectionListener listSelectionListener) {
        this.d.add(listSelectionListener);
    }
    
    public void removeListSelectionListener(final ListSelectionListener listSelectionListener) {
        this.d.remove(listSelectionListener);
    }
}

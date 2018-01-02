// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

import java.util.Iterator;
import ca.odell.glazedlists.EventList;
import java.util.Collection;
import ca.odell.glazedlists.impl.adt.IdentityMultimap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

final class SequenceDependenciesEventPublisher implements ListEventPublisher, Serializable
{
    private transient int a;
    private final transient Map b;
    private final transient Map c;
    private transient int d;
    private transient List e;
    private transient List f;
    
    SequenceDependenciesEventPublisher() {
        this.b = new IdentityHashMap();
        this.c = new IdentityHashMap();
        this.e = Collections.EMPTY_LIST;
    }
    
    private List a(final List list) {
        final ArrayList list2 = new ArrayList();
        final IdentityMultimap identityMultimap = new IdentityMultimap();
        final IdentityMultimap identityMultimap2 = new IdentityMultimap();
        final IdentityHashMap<Object, Object> identityHashMap = new IdentityHashMap<Object, Object>();
        final ArrayList<Object> list3 = new ArrayList<Object>();
        for (int i = 0; i < list.size(); ++i) {
            final SequenceDependenciesEventPublisher$SubjectAndListener sequenceDependenciesEventPublisher$SubjectAndListener = list.get(i);
            final Object a = sequenceDependenciesEventPublisher$SubjectAndListener.b;
            final Object a2 = this.a(sequenceDependenciesEventPublisher$SubjectAndListener.c);
            identityMultimap.a(a, sequenceDependenciesEventPublisher$SubjectAndListener);
            identityMultimap2.a(a2, sequenceDependenciesEventPublisher$SubjectAndListener);
            identityHashMap.remove(a2);
            if (identityMultimap2.b(a) == 0) {
                identityHashMap.put(a, Boolean.TRUE);
            }
        }
        list3.addAll(identityHashMap.keySet());
        while (!list3.isEmpty()) {
            final List a3 = identityMultimap.a(list3.remove(0));
        Label_0350:
            for (int j = 0; j < a3.size(); ++j) {
                final Object a4 = this.a(a3.get(j).c);
                final List a5 = identityMultimap2.a(a4);
                if (a5.size() != 0) {
                    for (int k = 0; k < a5.size(); ++k) {
                        if (!identityHashMap.containsKey(a5.get(k).b)) {
                            continue Label_0350;
                        }
                    }
                    list2.addAll(a5);
                    identityMultimap2.remove(a4);
                    list3.add(a4);
                    identityHashMap.put(a4, Boolean.TRUE);
                }
            }
        }
        if (!identityMultimap2.isEmpty()) {
            throw new IllegalStateException("Listener cycle detected, " + identityMultimap2.values());
        }
        return list2;
    }
    
    private Object a(final Object o) {
        final Object value = this.c.get(o);
        if (value == null) {
            return o;
        }
        return value;
    }
    
    public synchronized void a(final Object o, final Object o2, final SequenceDependenciesEventPublisher$EventFormat sequenceDependenciesEventPublisher$EventFormat) {
        this.e = this.a(this.a(o, o2, null, sequenceDependenciesEventPublisher$EventFormat));
    }
    
    public synchronized void a(final Object o, final Object o2) {
        this.e = this.a(o, null, o2, null);
    }
    
    private List a(final Object o, final Object o2, Object o3, final SequenceDependenciesEventPublisher$EventFormat sequenceDependenciesEventPublisher$EventFormat) {
        final ArrayList<SequenceDependenciesEventPublisher$SubjectAndListener> list = new ArrayList<SequenceDependenciesEventPublisher$SubjectAndListener>(this.e.size() + ((o2 == null) ? -1 : 1));
        for (int i = 0; i < this.e.size(); ++i) {
            final SequenceDependenciesEventPublisher$SubjectAndListener sequenceDependenciesEventPublisher$SubjectAndListener = this.e.get(i);
            if (sequenceDependenciesEventPublisher$SubjectAndListener.c == o3 && sequenceDependenciesEventPublisher$SubjectAndListener.b == o) {
                o3 = null;
            }
            else if (!sequenceDependenciesEventPublisher$SubjectAndListener.d.a(sequenceDependenciesEventPublisher$SubjectAndListener.b, sequenceDependenciesEventPublisher$SubjectAndListener.c)) {
                list.add(sequenceDependenciesEventPublisher$SubjectAndListener);
            }
        }
        if (o3 != null) {
            throw new IllegalArgumentException("Cannot remove nonexistent listener " + o3);
        }
        if (o2 != null) {
            list.add(new SequenceDependenciesEventPublisher$SubjectAndListener(o, o2, sequenceDependenciesEventPublisher$EventFormat));
        }
        return list;
    }
    
    public void b(final Object o, final Object o2) {
        this.a(o2, o, SequenceDependenciesEventPublisher$NoOpEventFormat.a);
    }
    
    public void a(final EventList list, final ListEventListener listEventListener) {
    }
    
    public void b(final Object o, final Object o2, final SequenceDependenciesEventPublisher$EventFormat sequenceDependenciesEventPublisher$EventFormat) {
        if (this.a == 0) {
            this.f = this.e;
            this.d = Integer.MAX_VALUE;
        }
        ++this.a;
        try {
            if (this.b.put(o, sequenceDependenciesEventPublisher$EventFormat) != null) {
                throw new IllegalStateException("Reentrant fireEvent() by \"" + o + "\"");
            }
            final int size = this.f.size();
            for (int i = 0; i < size; ++i) {
                final SequenceDependenciesEventPublisher$SubjectAndListener sequenceDependenciesEventPublisher$SubjectAndListener = this.f.get(i);
                if (sequenceDependenciesEventPublisher$SubjectAndListener.b == o) {
                    if (i < this.d) {
                        this.d = i;
                    }
                    sequenceDependenciesEventPublisher$SubjectAndListener.a(o2);
                }
            }
            if (this.a != 1) {
                return;
            }
            RuntimeException ex = null;
            while (true) {
                SequenceDependenciesEventPublisher$SubjectAndListener sequenceDependenciesEventPublisher$SubjectAndListener2 = null;
                for (int j = this.d; j < size; ++j) {
                    final SequenceDependenciesEventPublisher$SubjectAndListener sequenceDependenciesEventPublisher$SubjectAndListener3 = this.f.get(j);
                    if (sequenceDependenciesEventPublisher$SubjectAndListener3.a()) {
                        sequenceDependenciesEventPublisher$SubjectAndListener2 = sequenceDependenciesEventPublisher$SubjectAndListener3;
                        this.d = j + 1;
                        break;
                    }
                }
                if (sequenceDependenciesEventPublisher$SubjectAndListener2 == null) {
                    break;
                }
                try {
                    sequenceDependenciesEventPublisher$SubjectAndListener2.b();
                }
                catch (RuntimeException ex2) {
                    if (ex != null) {
                        continue;
                    }
                    ex = ex2;
                }
            }
            for (final Map.Entry<K, SequenceDependenciesEventPublisher$EventFormat> entry : this.b.entrySet()) {
                try {
                    entry.getValue().a(entry.getKey());
                }
                catch (RuntimeException ex3) {
                    if (ex != null) {
                        continue;
                    }
                    ex = ex3;
                }
            }
            this.b.clear();
            this.f = null;
            if (ex != null) {
                throw ex;
            }
        }
        finally {
            --this.a;
        }
    }
}

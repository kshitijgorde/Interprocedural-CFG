// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import com.eventim.common.transfer.saalplan.GraphDetails;
import com.eventim.common.transfer.saalplan.StehplatzbereichDetails;
import com.eventim.common.transfer.saalplan.SitzDetails;
import com.eventim.common.transfer.saalplan.SeatplanObjects;
import com.eventim.applet.a.f;
import java.util.Vector;
import com.eventim.common.transfer.saalplan.SaalplanRabattstufeDetails;
import java.util.Collection;
import java.util.Iterator;
import com.eventim.common.transfer.saalplan.SaalplanSperrtexteDetails;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import com.eventim.common.transfer.saalplan.SaalplanPromotionCodeDetails;
import com.eventim.common.transfer.saalplan.SaalplanSalesGroupPromotionDetails;
import com.eventim.common.transfer.saalplan.SaalplanPromotionDetails;
import com.eventim.common.transfer.saalplan.SaalplanEventDetails;
import java.awt.Color;
import com.eventim.common.transfer.saalplan.SaalplanPlatzkategorieDetails;
import java.util.Map;
import java.util.Set;

public final class e
{
    private int a;
    private int b;
    private Set c;
    private boolean d;
    private Map e;
    private int f;
    private Map g;
    private SaalplanPlatzkategorieDetails[] h;
    private Set i;
    private Map j;
    private Map k;
    private Map l;
    private Map m;
    private int n;
    private int o;
    private Set p;
    
    static {
        new Color(8, 156, 24);
    }
    
    public e(final SaalplanEventDetails saalplanEventDetails, final SaalplanPromotionDetails[] array, final SaalplanSalesGroupPromotionDetails[] array2, final SaalplanPromotionCodeDetails[] array3, final EventimApplet eventimApplet) {
        this.i = new LinkedHashSet();
        this.e = new LinkedHashMap();
        this.m = new LinkedHashMap();
        this.j = new LinkedHashMap();
        this.l = new LinkedHashMap();
        this.g = new LinkedHashMap();
        this.k = new LinkedHashMap();
        this.p = new LinkedHashSet();
        this.c = new LinkedHashSet();
        for (int i = 0; i < saalplanEventDetails.tdlSeatplanDetails.platzkategorieDetails.length; ++i) {
            final SaalplanPlatzkategorieDetails saalplanPlatzkategorieDetails = saalplanEventDetails.tdlSeatplanDetails.platzkategorieDetails[i];
            for (int j = 0; j < saalplanPlatzkategorieDetails.getRabattstufen().length; ++j) {
                if (saalplanPlatzkategorieDetails.getRabattstufen()[j].getPrId() == 0) {
                    this.i.add(new Long(saalplanPlatzkategorieDetails.getTdlPreisklasseId()));
                }
            }
        }
        for (int k = 0; k < array2.length; ++k) {
            final SaalplanSalesGroupPromotionDetails saalplanSalesGroupPromotionDetails;
            if ((saalplanSalesGroupPromotionDetails = array2[k]).getState() == 1) {
                final Long n = new Long(saalplanSalesGroupPromotionDetails.getTdlNumber());
                final Integer n2 = new Integer(saalplanSalesGroupPromotionDetails.getPrId());
                Set<?> set;
                if (this.e.containsKey(n)) {
                    set = this.e.get(n);
                }
                else {
                    set = new HashSet<Object>();
                    this.e.put(n, set);
                }
                set.add(n2);
            }
        }
        if (array != null && array2 != null) {
            EventimApplet.a("Received " + array.length + " Promotions.", 3);
            EventimApplet.a("Received " + array2.length + " Sales Group Promotions.", 3);
            for (int l = 0; l < array.length; ++l) {
                final SaalplanPromotionDetails saalplanPromotionDetails = array[l];
                this.m.put(saalplanPromotionDetails.getId(), saalplanPromotionDetails);
                this.j.put(saalplanPromotionDetails.getId(), new HashSet());
                for (int n3 = 0; n3 < array2.length; ++n3) {
                    final SaalplanSalesGroupPromotionDetails saalplanSalesGroupPromotionDetails2 = array2[n3];
                    if (saalplanPromotionDetails.getId() == saalplanSalesGroupPromotionDetails2.getPrId()) {
                        this.j.get(saalplanPromotionDetails.getId()).add(new Long(saalplanSalesGroupPromotionDetails2.getTdlNumber()));
                    }
                    this.p.add(new Long(saalplanSalesGroupPromotionDetails2.getTdlNumber()));
                }
            }
        }
        if (saalplanEventDetails.tdlSeatplanDetails.sperrtexteDetais != null) {
            for (int n4 = 0; n4 < saalplanEventDetails.tdlSeatplanDetails.sperrtexteDetais.length; ++n4) {
                final SaalplanSperrtexteDetails saalplanSperrtexteDetails;
                if ((saalplanSperrtexteDetails = saalplanEventDetails.tdlSeatplanDetails.sperrtexteDetais[n4]).getLaufendeNummer() > 99) {
                    this.c.add(new Long(saalplanSperrtexteDetails.getLaufendeNummer() - 99));
                }
            }
        }
        if (array3 != null) {
            for (int n5 = 0; n5 < array3.length; ++n5) {
                Set<SaalplanPromotionCodeDetails> set2;
                if ((set2 = this.l.get(new Integer(array3[n5].getPrId()))) == null) {
                    set2 = new HashSet<SaalplanPromotionCodeDetails>();
                    this.l.put(new Integer(array3[n5].getPrId()), set2);
                }
                set2.add(array3[n5]);
            }
        }
        this.b = eventimApplet.d().n();
        this.a = 0;
        this.n = 0;
        this.d = false;
        int intValue = -1;
        for (final Integer n6 : this.l.keySet()) {
            if (this.m.containsKey(n6)) {
                intValue = n6;
            }
        }
        if (this.f(this.b)) {
            if (this.b == intValue) {
                final int b = this.b;
                this.n = b;
                this.a = b;
            }
            else {
                this.n = this.b;
                this.d = true;
            }
        }
        if (this.n <= 0 && this.h(this.b)) {
            final int b2 = this.b;
            this.n = b2;
            this.a = b2;
        }
        if (this.n <= 0 && intValue > 0) {
            final int n7 = intValue;
            this.n = n7;
            this.a = n7;
        }
        final int f;
        if (this.n <= 0 && (f = eventimApplet.d().F()) > 0 && this.h(f)) {
            final int n8 = f;
            this.n = n8;
            this.a = n8;
        }
        if (this.n <= 0) {
            final int m = eventimApplet.d().j();
            if (this.f(m)) {
                this.n = m;
                this.d = true;
            }
            else if (this.h(m)) {
                final int n9 = m;
                this.n = n9;
                this.a = n9;
            }
        }
        if (this.a > 0) {
            EventimApplet.a("Promotion " + this.a + " activated.", 2);
        }
    }
    
    public final void a(final Integer n, final Long n2) {
        Set<Integer> set;
        if ((set = this.g.get(n2)) == null) {
            set = new HashSet<Integer>();
            this.g.put(n2, set);
        }
        set.add(n);
        Set<Long> set2;
        if ((set2 = this.k.get(n)) == null) {
            set2 = new HashSet<Long>();
            this.k.put(n, set2);
        }
        set2.add(n2);
    }
    
    private boolean o(final int n) {
        return n == 0 || (n == this.n && (this.h(n) || this.q(n)));
    }
    
    private boolean d(final long n, final long n2) {
        final Long n3 = new Long(n);
        final Long n4 = new Long(n2);
        if (this.n == 0) {
            return !this.e.containsKey(n3);
        }
        final boolean p2 = this.p(this.n);
        final boolean j = this.j(this.n);
        if (p2 && !j) {
            return !this.e.containsKey(n3);
        }
        boolean contains = false;
        final Set set;
        if ((set = this.e.get(n3)) != null) {
            contains = set.contains(new Integer(this.n));
        }
        final boolean b = j && p2 && contains;
        final boolean b2 = j && !p2 && this.i.contains(n4) && contains;
        return (b || b2) && (this.h(this.n) || this.q(this.n));
    }
    
    public final boolean a(final long n, final long n2) {
        if (this.n == 0 && this.d(n, n2) && this.d(n2)) {
            return this.o(this.n);
        }
        final SaalplanPromotionDetails saalplanPromotionDetails;
        if ((saalplanPromotionDetails = this.m.get(new Integer(this.n))) != null) {
            final boolean b = (saalplanPromotionDetails.getTdlFlag() & 0x40000000) == 0x40000000;
            final boolean b2 = (saalplanPromotionDetails.getTdlFlag() & Integer.MIN_VALUE) == Integer.MIN_VALUE;
            final boolean d = this.d(n2);
            final boolean d2 = this.d(n, n2);
            final boolean b3 = b && !b2 && d && d2;
            final boolean b4 = !b && b2 && d2;
            final boolean b5 = b && b2 && d && d2;
            if (b3 || b4 || b5) {
                return this.o(this.n);
            }
        }
        return false;
    }
    
    public final boolean a(final int n) {
        return this.b <= 0 || !this.m.containsKey(new Integer(this.b)) || ((this = this).b > 0 && n == this.b && this.m.containsKey(new Integer(n)));
    }
    
    public final boolean a() {
        boolean b = false;
        for (final SaalplanPromotionDetails saalplanPromotionDetails : this.m.values()) {
            if (!this.j(saalplanPromotionDetails.getId())) {
                b = true;
                break;
            }
            if (!this.c(saalplanPromotionDetails.getId()).isEmpty()) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public final int b() {
        return this.a;
    }
    
    public final Set b(final long n, final long n2) {
        final HashSet set = new HashSet(1);
        final Long n3 = new Long(n);
        if (this.e.containsKey(n3)) {
            set.addAll((Collection)this.e.get(n3));
        }
        final Long n4 = new Long(n2);
        if (this.g.containsKey(n4)) {
            set.addAll((Collection)this.g.get(n4));
        }
        return set;
    }
    
    public final int c(final long n, final long n2) {
        final SaalplanRabattstufeDetails e;
        final int n3 = ((e = this.e(n, n2)).getTdlGruppeZu() == 0) ? 1 : e.getTdlGruppeZu();
        final int n4 = (e.getGruppeZu() == 0) ? 1 : e.getGruppeZu();
        final int n5 = n3;
        final int n6 = n4;
        final int n7 = n5;
        if (com.eventim.applet.k.a(n5, n6) == 0) {
            return 0;
        }
        return n7 * n6 / com.eventim.applet.k.a(n7, n6);
    }
    
    public final int a(final long n, final long n2, final int n3) {
        final int f = this.f;
        final SaalplanRabattstufeDetails e;
        int n4;
        if ((n4 = (e = this.e(n, n2)).getMaxmenge()) <= 0 || n4 >= f) {
            n4 = f;
        }
        final Integer n5 = new Integer(n3);
        final int tdlCodeMaxNumberTicketsPerDeal;
        if (n3 > 0 && this.m.containsKey(n5) && (tdlCodeMaxNumberTicketsPerDeal = this.m.get(n5).getTdlCodeMaxNumberTicketsPerDeal()) > 0) {
            n4 = Math.min(n4, tdlCodeMaxNumberTicketsPerDeal);
        }
        final int tdlMaxTickets;
        if ((tdlMaxTickets = e.getTdlMaxTickets()) > 0) {
            n4 = Math.min(n4, tdlMaxTickets);
        }
        if (this.o > 0) {
            n4 = Math.min(n4, this.o);
        }
        return n4;
    }
    
    public final int b(final int n) {
        final SaalplanPromotionDetails saalplanPromotionDetails;
        if ((saalplanPromotionDetails = this.m.get(new Integer(n))) != null) {
            return saalplanPromotionDetails.getTdlCodeMaxNumberOfTickets();
        }
        return -1;
    }
    
    public final int c(int n) {
        final Set<SaalplanPromotionCodeDetails> set;
        if ((set = this.l.get(new Integer(n))) == null) {
            return -1;
        }
        n = 0;
        final Iterator<SaalplanPromotionCodeDetails> iterator = set.iterator();
        while (iterator.hasNext()) {
            n += iterator.next().getTdlTicketsLeft();
        }
        return n;
    }
    
    public final int b(final long n, final long n2, final int n3) {
        int tdlCodeMinNumberTicketsPerDeal = 1;
        final Integer n4 = new Integer(n3);
        if (n3 > 0 && this.m.containsKey(n4)) {
            tdlCodeMinNumberTicketsPerDeal = ((SaalplanPromotionDetails)this.m.get(n4)).getTdlCodeMinNumberTicketsPerDeal();
        }
        final SaalplanRabattstufeDetails e = this.e(n, n2);
        return Math.max(Math.max(tdlCodeMinNumberTicketsPerDeal, e.getTdlMinTickets()), e.getMinmenge());
    }
    
    public final int d(final int n) {
        final SaalplanPromotionDetails saalplanPromotionDetails;
        if ((saalplanPromotionDetails = this.m.get(new Integer(n))) != null) {
            return saalplanPromotionDetails.getTdlCodeMinNumberTicketsPerDeal();
        }
        return -1;
    }
    
    public final String e(final int n) {
        final SaalplanPromotionDetails saalplanPromotionDetails;
        if ((saalplanPromotionDetails = this.m.get(new Integer(n))) != null) {
            return saalplanPromotionDetails.getTdlName();
        }
        return null;
    }
    
    public final SaalplanRabattstufeDetails a(final long n, final int n2) {
        final SaalplanRabattstufeDetails[] b;
        if ((b = this.b(n, n2)) != null && b.length > 0) {
            return b[0];
        }
        return null;
    }
    
    public final SaalplanPlatzkategorieDetails a(final long n) {
        for (int i = 0; i < this.h.length; ++i) {
            if (this.h[i].getTdlPreisklasseId() == n) {
                return this.h[i];
            }
        }
        return null;
    }
    
    public final Set a(final Integer n) {
        if (this.k.containsKey(n)) {
            return this.k.get(n);
        }
        return new HashSet();
    }
    
    public final Set b(final Integer n) {
        if (this.l.containsKey(n)) {
            return this.l.get(n);
        }
        return new HashSet();
    }
    
    public final Set c() {
        return this.m.keySet();
    }
    
    public final Collection d() {
        return this.m.values();
    }
    
    private SaalplanRabattstufeDetails e(final long n, final long n2) {
        this = this;
        int i = 0;
        while (true) {
            while (i < this.h.length) {
                if (this.h[i].getTdlPreisklasseId() == n2) {
                    final SaalplanRabattstufeDetails[] rabattstufen = this.h[i].getRabattstufen();
                    final Vector vector = new Vector<SaalplanRabattstufeDetails>();
                    for (int j = 0; j < rabattstufen.length; ++j) {
                        final SaalplanRabattstufeDetails saalplanRabattstufeDetails = rabattstufen[j];
                        if (this.o(saalplanRabattstufeDetails.getPrId())) {
                            vector.addElement(saalplanRabattstufeDetails);
                        }
                    }
                    if (!vector.isEmpty()) {
                        final SaalplanRabattstufeDetails[] array = (SaalplanRabattstufeDetails[])com.eventim.applet.k.a(vector, new SaalplanRabattstufeDetails[vector.size()]);
                        final SaalplanRabattstufeDetails[] array2 = array;
                        for (int k = 0; k < array2.length; ++k) {
                            if (array2[k].getId() == n) {
                                return array2[k];
                            }
                        }
                        return null;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            final SaalplanRabattstufeDetails[] array = null;
            continue;
        }
    }
    
    public final SaalplanRabattstufeDetails[] b(final long n, final int n2) {
        int i = 0;
        while (i < this.h.length) {
            if (this.h[i].getTdlPreisklasseId() == n) {
                final SaalplanRabattstufeDetails[] rabattstufen = this.h[i].getRabattstufen();
                if (this.p(n2)) {
                    final Vector vector = new Vector<SaalplanRabattstufeDetails>();
                    for (int j = 0; j < rabattstufen.length; ++j) {
                        final SaalplanRabattstufeDetails saalplanRabattstufeDetails;
                        if ((saalplanRabattstufeDetails = rabattstufen[j]).getPrId() == n2) {
                            final int prId = saalplanRabattstufeDetails.getPrId();
                            if (prId == 0 || this.h(prId) || this.q(prId)) {
                                vector.addElement(saalplanRabattstufeDetails);
                            }
                        }
                    }
                    if (!vector.isEmpty()) {
                        return (SaalplanRabattstufeDetails[])com.eventim.applet.k.a(vector, new SaalplanRabattstufeDetails[vector.size()]);
                    }
                    break;
                }
                else {
                    if (!this.j(n2)) {
                        break;
                    }
                    final Vector vector2 = new Vector<SaalplanRabattstufeDetails>();
                    for (int k = 0; k < rabattstufen.length; ++k) {
                        final SaalplanRabattstufeDetails saalplanRabattstufeDetails2;
                        if ((saalplanRabattstufeDetails2 = rabattstufen[k]).getPrId() == 0) {
                            vector2.addElement(saalplanRabattstufeDetails2);
                        }
                    }
                    if (!vector2.isEmpty()) {
                        return (SaalplanRabattstufeDetails[])com.eventim.applet.k.a(vector2, new SaalplanRabattstufeDetails[vector2.size()]);
                    }
                    break;
                }
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public final Set c(final Integer n) {
        if (this.j.containsKey(n)) {
            return this.j.get(n);
        }
        return new HashSet();
    }
    
    public final int e() {
        return this.n;
    }
    
    private static boolean c(final long n) {
        return (n & 0x1L) == 0x1L;
    }
    
    public static boolean b(final long n) {
        return (n & 0xFFFFFFFF80000000L) == 0xFFFFFFFF80000000L;
    }
    
    private boolean p(final int n) {
        final SaalplanPromotionDetails saalplanPromotionDetails;
        return n == 0 || ((saalplanPromotionDetails = this.m.get(new Integer(n))) != null && (saalplanPromotionDetails.getTdlFlag() & 0x40000000L) == 0x40000000L);
    }
    
    private boolean q(final int n) {
        if (n > 0) {
            final Integer n2 = new Integer(n);
            if (this.m.containsKey(n2) && c((long)((SaalplanPromotionDetails)this.m.get(n2)).getTdlFlag()) && this.l.containsKey(n2)) {
                return !((Set)this.l.get(n2)).isEmpty();
            }
        }
        return false;
    }
    
    public final boolean a(final f f) {
        return f.b(this.a);
    }
    
    public final boolean f(final int n) {
        if (n > 0) {
            final Integer n2 = new Integer(n);
            if (this.m.containsKey(n2)) {
                return c((long)((SaalplanPromotionDetails)this.m.get(n2)).getTdlFlag());
            }
        }
        return false;
    }
    
    public final boolean f() {
        return this.d;
    }
    
    public static boolean g(final int n) {
        return n == 0;
    }
    
    public final boolean h(final int n) {
        if (n > 0) {
            final Integer n2 = new Integer(n);
            if (this.m.containsKey(n2)) {
                return !c((long)((SaalplanPromotionDetails)this.m.get(n2)).getTdlFlag());
            }
        }
        return false;
    }
    
    private boolean d(final long n) {
        final Long n2 = new Long(n);
        final Integer n3 = new Integer(this.n);
        return this.g.containsKey(n2) && this.g.get(n2).contains(n3);
    }
    
    public static boolean i(final int n) {
        return n > 0;
    }
    
    public final boolean j(final int n) {
        final SaalplanPromotionDetails saalplanPromotionDetails;
        return n != 0 && (saalplanPromotionDetails = this.m.get(new Integer(n))) != null && b((long)saalplanPromotionDetails.getTdlFlag());
    }
    
    public final boolean a(final int n, final int n2) {
        final int b;
        return (b = this.b(n2)) > 0 && n >= b;
    }
    
    public final boolean b(final int n, final int n2) {
        final int c;
        return (c = this.c(n2)) > 0 && n >= c;
    }
    
    public final void k(final int a) {
        this.a = a;
    }
    
    public final void l(final int f) {
        this.f = f;
    }
    
    public final void a(final SaalplanPlatzkategorieDetails[] h) {
        this.h = h;
    }
    
    public final void a(final SaalplanPromotionCodeDetails[] array) {
        if (array != null) {
            this.l.clear();
            for (int i = 0; i < array.length; ++i) {
                Set<SaalplanPromotionCodeDetails> set;
                if ((set = this.l.get(new Integer(array[i].getPrId()))) == null) {
                    set = new HashSet<SaalplanPromotionCodeDetails>();
                    this.l.put(new Integer(array[i].getPrId()), set);
                }
                set.add(array[i]);
            }
        }
    }
    
    public final void m(final int n) {
        this.n = n;
    }
    
    public final void n(final int o) {
        this.o = o;
    }
    
    public final void a(final SeatplanObjects seatplanObjects) {
        final GraphDetails[] graphDetails = seatplanObjects.graphDetails;
        final Iterator<Map.Entry<Long, V>> iterator = this.e.entrySet().iterator();
        while (iterator.hasNext()) {
            final Object o;
            final Long n = ((Map.Entry<Long, Set<Integer>>)(o = iterator.next())).getKey();
            final Set<Integer> set = ((Map.Entry<K, Set<Integer>>)o).getValue();
            final HashSet<Long> set2 = new HashSet<Long>();
            for (int i = 0; i < graphDetails.length; ++i) {
                final GraphDetails graphDetails2;
                Long n2;
                Long n3;
                if ((graphDetails2 = graphDetails[i]).isSitz()) {
                    final SitzDetails sitzDetails = (SitzDetails)graphDetails2;
                    n2 = new Long(sitzDetails.tdlPkid);
                    n3 = new Long(sitzDetails.tdlVgLfndNr);
                }
                else {
                    if (!graphDetails2.isStehplatzbereich()) {
                        continue;
                    }
                    final StehplatzbereichDetails stehplatzbereichDetails = (StehplatzbereichDetails)graphDetails2;
                    n2 = new Long(stehplatzbereichDetails.tdlPkid);
                    n3 = new Long(stehplatzbereichDetails.tdlVgLfndNr);
                }
                if (n.equals(n3) && this.i.contains(n2)) {
                    set2.add(n2);
                }
            }
            for (final Long n4 : set2) {
                final Iterator<Integer> iterator3 = set.iterator();
                while (iterator3.hasNext()) {
                    this.a(iterator3.next(), n4);
                }
            }
        }
    }
}

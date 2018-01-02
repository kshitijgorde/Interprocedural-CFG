// 
// Decompiled by Procyon v0.5.30
// 

package net.eprevue.easyplan.selection;

import java.util.Enumeration;
import java.util.ArrayList;

public class SelectionTreeView extends rp_X
{
    private int a;
    
    public SelectionTreeView(final int a) {
        this.a = 0;
        this.a = a;
    }
    
    public final boolean a(final rp_eA rp_eA) {
        if (rp_eA == null) {
            return false;
        }
        final rp_D rp_D = new rp_D(this);
        final rp_D rp_D2 = new rp_D(this);
        rp_ao rp_ao = null;
        if (rp_eA.a.equalsIgnoreCase("catalogs")) {
            final String a;
            if ((a = rp_eA.a("type", (String)null)) != null && a.equals("items-included") && this.a != 103) {
                rp_ao = this.a(rp_eA, 0, rp_D, rp_D2);
            }
            else if (this.a == 103) {
                rp_ao = this.a(rp_eA, 0, rp_D, rp_D2, "");
            }
            else {
                rp_C.a(4, "*-*-*-*-*- LoadZipThread missing -*-*-*-*-*");
            }
        }
        else if (rp_eA.a.equalsIgnoreCase("types")) {
            rp_ao = this.a(rp_eA, 0, rp_D, rp_D2, "");
        }
        if (rp_ao != null) {
            this.b(rp_ao);
            if (rp_D.a != null) {
                this.a(rp_D.a);
            }
            else if (rp_D2.a != null) {
                this.a(rp_D2.a);
            }
            return true;
        }
        return false;
    }
    
    private rp_ao a(final rp_eA rp_eA, final int n, final rp_D rp_D, final rp_D rp_D2) {
        rp_ao rp_ao = null;
        if (n > 0) {
            rp_ao = new rp_ao(rp_eA.a("name", "unnamed"), new rp_gk(rp_au.a().a(), null));
        }
        boolean b = false;
        final Enumeration<rp_eA> elements = rp_eA.a.elements();
        rp_ao rp_ao2 = null;
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2 = elements.nextElement();
            rp_ao a = null;
            if (rp_eA2.a.equals("default")) {
                rp_D.a = rp_eA2.a("coll", (String)null);
            }
            else {
                if (rp_eA2.a.equalsIgnoreCase("catalog")) {
                    if ((a = this.a(rp_eA2, n + 1, rp_D, rp_D2)).a) {
                        b = true;
                    }
                }
                else if (rp_eA2.a.equalsIgnoreCase("collection")) {
                    final ArrayList<String> list = new ArrayList<String>();
                    final Enumeration elements2 = rp_eA2.a.elements();
                    while (elements2.hasMoreElements()) {
                        final rp_eA rp_eA3;
                        final String a2;
                        if ((rp_eA3 = elements2.nextElement()).a.equals("item") && (a2 = rp_eA3.a("name", (String)null)) != null && a2.length() > 0) {
                            list.add(a2);
                        }
                    }
                    final String a3 = rp_eA2.a("name", "unnamed");
                    a = new rp_ao(a3, new rp_gk(rp_au.a().a(), list.toArray(new String[list.size()])));
                    if (a3.equals(rp_D.a)) {
                        rp_D.a = a;
                        b = true;
                    }
                    if (rp_D2.a == null && rp_D.a == null) {
                        rp_D2.a = a;
                        b = true;
                    }
                }
                if (rp_ao2 == null) {
                    if (rp_ao == null) {
                        rp_ao = a;
                    }
                    else {
                        rp_ao.b = a;
                    }
                }
                else {
                    rp_ao2.a = a;
                }
                rp_ao2 = a;
            }
        }
        if (b) {
            rp_ao.b();
        }
        return rp_ao;
    }
    
    private rp_ao a(final rp_eA rp_eA, final int n, final rp_D rp_D, final rp_D rp_D2, String a) {
        rp_ao rp_ao = null;
        String s = a;
        if (n > 0) {
            a = rp_eA.a("name", "unnamed");
            final int a2 = rp_eA.a("count", 0);
            final String a3 = rp_eA.a("id", "");
            if (s.length() != 0 && a3.length() != 0) {
                s += ".";
            }
            s += a3;
            rp_ao = new rp_ao(a, new rp_y(this.a + 100, a2, s));
        }
        boolean b = false;
        final Enumeration<rp_eA> elements = rp_eA.a.elements();
        rp_ao rp_ao2 = null;
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2 = elements.nextElement();
            rp_ao a4 = null;
            if (rp_eA2.a.equalsIgnoreCase((this.a == 104) ? "group" : "catalog")) {
                if ((a4 = this.a(rp_eA2, n + 1, rp_D, rp_D2, s)).a) {
                    b = true;
                }
            }
            else if (rp_eA2.a.equalsIgnoreCase((this.a == 104) ? "type" : "collection")) {
                final String a5 = rp_eA2.a("name", "unnamed");
                final int a6 = rp_eA2.a("count", 0);
                final String a7 = rp_eA2.a("id", "");
                String string;
                if ((string = s).length() != 0 && a7.length() != 0) {
                    string += ".";
                }
                a4 = new rp_ao(a5, new rp_y(this.a + 100, a6, string + a7));
                if (rp_D2.a == null && rp_D.a == null) {
                    rp_D2.a = a4;
                    b = true;
                }
            }
            if (rp_ao2 == null) {
                if (rp_ao == null) {
                    rp_ao = a4;
                }
                else {
                    rp_ao.b = a4;
                }
            }
            else {
                rp_ao2.a = a4;
            }
            rp_ao2 = a4;
        }
        if (b) {
            rp_ao.b();
        }
        return rp_ao;
    }
}

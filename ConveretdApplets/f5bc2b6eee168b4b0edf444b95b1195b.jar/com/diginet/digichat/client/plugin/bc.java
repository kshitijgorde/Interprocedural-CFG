// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.plugin;

import com.diginet.digichat.util.k;
import com.diginet.digichat.client.ChatMessage;
import com.esial.util.LanguageSupport;
import java.util.Properties;
import com.diginet.digichat.common.User;
import java.util.Vector;
import com.diginet.digichat.network.t;
import com.diginet.digichat.client.User2;
import com.diginet.digichat.common.a6;
import java.util.Hashtable;
import com.diginet.digichat.client.g;

public class bc implements PluginAPI
{
    private g a;
    private Hashtable b;
    private int c;
    private Hashtable d;
    
    public final void a(final String s, final String s2, final String s3, final boolean b) {
        try {
            final Class<?> forName = Class.forName(s2);
            Plugin plugin;
            if (b) {
                plugin = (Plugin)forName.getConstructor(Class.forName("java.lang.String")).newInstance(s3);
            }
            else {
                plugin = (Plugin)forName.newInstance();
            }
            plugin.registerAPI(this, s);
            this.b.put(s, plugin);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String getCurrentRoom() {
        final a6 a6 = (a6)this.a.al.d(this.a.b);
        String c = null;
        if (a6 != null) {
            c = this.a.c(a6.getName());
        }
        return c;
    }
    
    public final String[] getUsersForCurrentRoom() {
        final String[] array = new String[this.a.p(this.a.b)];
        int n = 0;
        this.a.aj.a(false);
        try {
            synchronized (this.a.aj) {
                for (int i = 0; i < this.a.aj.b(); ++i) {
                    final User2 user2 = (User2)this.a.aj.c(i);
                    if (user2.b == this.a.b && (!user2.u(23) || this.a.u(24))) {
                        array[n++] = user2.getName();
                    }
                }
            }
            // monitorexit(this.a.aj)
        }
        finally {
            this.a.aj.a();
        }
        return array;
    }
    
    public final void requestProfile(final Plugin plugin, final String s) {
        final int x = this.a(s).x();
        final t t = new t(67074, 1);
        t.a(0, 0, this.a.x());
        t.m = x;
        this.a.ap(t);
        Vector<Plugin> vector = this.d.get(s);
        if (vector == null) {
            vector = new Vector<Plugin>();
        }
        vector.addElement(plugin);
        this.d.put(s, vector);
    }
    
    public final boolean a(final t t) {
        final User user = (User)this.a.aj.d(t.a(0, 0));
        if (user == null) {
            return false;
        }
        if (this.d.get(user.getName()) == null) {
            return false;
        }
        final Vector<Plugin> vector = this.d.remove(user.getName());
        final Plugin plugin = vector.elementAt(0);
        vector.removeElementAt(0);
        if (vector.size() > 0) {
            this.d.put(user.getName(), vector);
        }
        final Properties properties = new Properties();
        final a6 a6 = (a6)this.a.al.d(user.b);
        if (a6 != null) {
            ((Hashtable<String, String>)properties).put("roomname", this.a.c(a6.getName()));
        }
        if (this.a.u(41)) {
            ((Hashtable<String, String>)properties).put("ipaddress", user.e);
            ((Hashtable<String, String>)properties).put("hostname", user.f);
        }
        ((Hashtable<String, String>)properties).put("name", this.a.c(user.getName()));
        final String c = this.a.c(t.c(0, 0));
        final String c2 = this.a.c(t.c(0, 1));
        final String c3 = this.a.c(t.c(0, 2));
        final String c4 = this.a.c(t.c(0, 3));
        ((Hashtable<String, String>)properties).put("realname", (c == null) ? "" : c);
        ((Hashtable<String, String>)properties).put("comments", (c2 == null) ? "" : c2);
        ((Hashtable<String, String>)properties).put("url", (c3 == null) ? "" : c3);
        ((Hashtable<String, String>)properties).put("email", (c4 == null) ? "" : c4);
        final int a7 = t.a(0, 1);
        if (a7 != -999) {
            ((Hashtable<String, String>)properties).put("age", String.valueOf(a7));
        }
        else {
            ((Hashtable<String, String>)properties).put("age", "");
        }
        if (t.e(0, 1)) {
            ((Hashtable<String, String>)properties).put("gender", LanguageSupport.translate("Male"));
        }
        else if (t.e(0, 0)) {
            ((Hashtable<String, String>)properties).put("gender", LanguageSupport.translate("Female"));
        }
        else {
            ((Hashtable<String, String>)properties).put("gender", "");
        }
        plugin.receiveProfile(user.getName(), properties);
        return true;
    }
    
    public final void sendPrivateMessage(final String s, final String s2) {
        final User a = this.a(s);
        this.a.a(null, a);
        this.a.b(s2, a.x(), -1);
    }
    
    public final void sendMessage(final String s) {
        this.a.f(s);
        this.a.e(s);
    }
    
    public final void sendPluginStringMessage(final String s, final String s2, final String s3) {
        final t t = new t(50400772, 1);
        t.a(0, 0, s2);
        t.a(0, 1, s3);
        t.m = this.a(s).x();
        t.n = this.a.x();
        this.a.ap(t);
    }
    
    public final void sendPluginInviteMessage(final String s, final String s2, final String s3, final String s4) {
        final t t = new t(50400772, 1);
        t.a(0, 0, s2);
        t.a(0, 1, s3);
        t.m = this.a(s).x();
        t.n = this.a.x();
        t.a(s4.getBytes());
        t.a(0, 50400773, true);
        this.a.ap(t);
    }
    
    public final void sendPluginBytesMessage(final String s, final String s2, final byte[] array) throws IllegalArgumentException {
        final t t = new t(50400772, 1);
        t.a(0, 0, s2);
        t.m = this.a(s).x();
        t.n = this.a.x();
        t.a(array);
        this.a.ap(t);
    }
    
    public final void b(final t t) {
        final String c = t.c(0, 0);
        final String c2 = t.c(0, 1);
        final User user = (User)this.a.aj.d(t.n);
        final String s = (user == null) ? "" : user.getName();
        final Plugin plugin = this.b.get(c);
        if (t.e(0, 50400773)) {
            this.a(c, new String(t.d()), c2, s);
        }
        else if (t.e(0, 50400774)) {
            this.b.get(c).receiveInvitationDeclined(s);
        }
        else {
            if (plugin == null) {
                return;
            }
            if (c2 != null) {
                plugin.receivePluginStringMessage(s, c2);
            }
            else {
                plugin.receivePluginBytesMessage(s, t.d());
            }
        }
    }
    
    public final void removePlugin(final String s) {
        this.b.remove(s);
    }
    
    private final User a(final String s) {
        User user = null;
        final k aj = this.a.aj;
        aj.a(false);
        try {
            synchronized (aj) {
                for (int i = 0; i < aj.b(); ++i) {
                    final User user2 = (User)aj.c(i);
                    if (user2.getName().equals(s)) {
                        user = user2;
                    }
                }
            }
            // monitorexit(aj)
        }
        finally {
            aj.a();
        }
        return user;
    }
    
    public final void a(final String s, final String s2, final String s3, final String s4) {
        if (this == null) {
            throw null;
        }
        new Thread(new b7(this, s3, s, s4, s2)).run();
    }
    
    public bc(final g a) {
        this.d = new Hashtable();
        this.a = a;
        this.c = this.a.x();
        this.b = new Hashtable();
    }
}

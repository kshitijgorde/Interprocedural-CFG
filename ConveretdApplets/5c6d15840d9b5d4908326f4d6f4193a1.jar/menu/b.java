// 
// Decompiled by Procyon v0.5.30
// 

package menu;

import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class b extends Panel implements ActionListener
{
    public static boolean if;
    private MenuBlaster a;
    
    public String[] a(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public b(final MenuBlaster a) {
        this.a = a;
        String s = a.getCodeBase().getHost();
        if (s.startsWith("www.")) {
            s = s.substring(4);
        }
        final String property = a.api.getProperty("regID", "error");
        b.if = new c().a(s, property);
        int n;
        for (n = 0; a.api.getProperty("menu" + (n + 1)) != null; ++n) {}
        final menu.a.b b = new menu.a.b(777, a, this);
        b.a(s, property);
        int n2 = n;
        if (!menu.b.if) {
            ++n2;
        }
        if (a.isVertical) {
            this.setLayout(new GridLayout(n2, 1));
        }
        else {
            this.setLayout(new GridLayout(1, n2));
        }
        for (int i = 1; i <= n; ++i) {
            this.add(new menu.a.b(i, a, this));
        }
        if (!menu.b.if) {
            this.add(b);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        String[] a;
        if (actionCommand == "BuddySoft") {
            a = new String[] { "http://www.buddysoft.net/", "_self" };
        }
        else {
            a = this.a(this.a.api.getProperty(actionCommand, "error"), "*");
        }
        if (a[0].equals("error")) {
            this.a.alert("Missing Value! \\n\\nPlease add a value for the menu item <" + actionCommand + "> in your configuration file!");
            return;
        }
        final String s = (a.length > 1) ? a[1].trim() : "_self";
        a[0].trim();
        try {
            this.a.getAppletContext().showDocument(new URL(this.a.getCodeBase(), a[0].trim()), s);
        }
        catch (Exception ex) {
            this.a.alert("Bad URL for the menu item <" + actionCommand + ">");
        }
    }
}

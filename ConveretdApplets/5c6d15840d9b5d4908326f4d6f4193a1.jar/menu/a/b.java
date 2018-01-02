// 
// Decompiled by Procyon v0.5.30
// 

package menu.a;

import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.awt.Menu;
import menu.MenuBlaster;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

public class b extends c implements ActionListener
{
    private menu.b goto;
    private PopupMenu char;
    private MenuBlaster else;
    
    public b(final int n, final MenuBlaster else1, final menu.b goto1) {
        this.goto = goto1;
        this.else = else1;
        super.new = this.else.rollBG;
        super.if = this.else.rollFG;
        super.for = this.else.rollOver;
        final String[] a = this.goto.a(this.else.api.getProperty("menu" + n, "BuddySoft"), "|");
        this.a(a[0].trim());
        if (a.length > 1) {
            this.char = new PopupMenu(a[0].trim());
            for (int i = 1; i < a.length; ++i) {
                a[i].trim();
                final String[] a2 = this.goto.a(a[i], "*");
                if (a2.length > 1) {
                    final Menu menu = new Menu(a2[0].trim());
                    for (int j = 1; j < a2.length; ++j) {
                        if (a2[j].trim() == "-") {
                            menu.addSeparator();
                        }
                        else {
                            final MenuItem menuItem = new MenuItem(a2[j].trim());
                            menuItem.addActionListener(this.goto);
                            menu.add(menuItem);
                        }
                    }
                    this.char.add(menu);
                }
                else if (a[i].trim() == "-") {
                    this.char.addSeparator();
                }
                else {
                    final MenuItem menuItem2 = new MenuItem(a[i].trim());
                    menuItem2.addActionListener(this.goto);
                    this.char.add(menuItem2);
                }
            }
            this.add(this.char);
            this.a(this);
        }
        else {
            this.a(this.goto);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.else.pop.play();
        if (this.else.isVertical) {
            this.char.show(this, this.getSize().width, 0);
        }
        else {
            this.char.show(this, 0, this.getSize().height);
        }
    }
    
    public void a(final String s, final String s2) {
        if (s.equals("")) {
            menu.b.if = true;
            return;
        }
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, "+");
            while (stringTokenizer.hasMoreTokens()) {
                if (this.if(s).equals(stringTokenizer.nextToken().trim().substring(2))) {
                    menu.b.if = true;
                    return;
                }
            }
        }
        menu.b.if = false;
    }
}

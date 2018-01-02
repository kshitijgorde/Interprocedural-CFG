import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.event.ActionListener;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

class MenuBuilder
{
    Stack s;
    ActionListener l;
    
    public MenuBuilder(final Menu menu, final ActionListener l) {
        (this.s = new Stack()).push(menu);
        this.l = l;
    }
    
    public void addBookmark(final String s, final String actionCommand) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(this.l);
        this.getCurrentMenu().add(menuItem);
    }
    
    public void addSeparator() {
        this.getCurrentMenu().addSeparator();
    }
    
    public void endSubmenu() {
        this.s.pop();
    }
    
    Menu getCurrentMenu() {
        return this.s.peek();
    }
    
    public void startSubmenu(final String s) {
        final Menu menu = new Menu(s);
        this.getCurrentMenu().add(menu);
        this.s.push(menu);
    }
}

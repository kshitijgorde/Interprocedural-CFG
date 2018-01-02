import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Event;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class chat_frame extends Frame implements groupboard_consts
{
    groupboard_gui gui;
    
    chat_frame(final chat_area chat_area, final groupboard_gui gui) {
        this.gui = gui;
        this.setTitle("Chat Window");
        this.create_menus();
        this.add("Center", chat_area);
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.gui.fr_chat = null;
            return true;
        }
        return super.handleEvent(event);
    }
    
    void create_menus() {
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("Chat");
        menu.add(new MenuItem("Ignore User"));
        menu.add(new MenuItem("Chat To All"));
        menu.add(new MenuItem("Save Text..."));
        menu.add(new MenuItem("Clear Text"));
        final CheckboxMenuItem checkboxMenuItem;
        menu.add(checkboxMenuItem = new CheckboxMenuItem("Beep on Whisper if Idle"));
        checkboxMenuItem.setState(this.gui.parent.beep_on_whisper);
        menuBar.add(menu);
        this.setMenuBar(menuBar);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof CheckboxMenuItem) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)event.target;
            if (checkboxMenuItem.getLabel().equals("Beep on Whisper if Idle")) {
                this.gui.parent.beep_on_whisper = checkboxMenuItem.getState();
            }
        }
        else if (event.target instanceof MenuItem) {
            final String s = (String)o;
            if (s.equals("Ignore User")) {
                this.gui.chat.ignore();
            }
            else if (s.equals("Chat To All")) {
                this.gui.chat.users.select(0);
            }
            else if (s.equals("Save Text...")) {
                this.gui.chat.save_text();
            }
            else if (s.equals("Clear Text")) {
                this.gui.chat.clear_text();
            }
        }
        return true;
    }
}

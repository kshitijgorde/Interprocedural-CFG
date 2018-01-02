import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Reader;
import java.awt.event.ActionListener;
import java.awt.Menu;

// 
// Decompiled by Procyon v0.5.30
// 

public class MenuLoader extends Thread
{
    Menu m;
    ActionListener l;
    ActionListener doneListener;
    BookmarkParser p;
    
    public MenuLoader(final Menu m, final Reader reader, final ActionListener l) throws IOException {
        this.m = m;
        this.p = new BookmarkParser(reader);
        this.l = l;
    }
    
    public void addActionListener(final ActionListener doneListener) {
        this.doneListener = doneListener;
    }
    
    void notifyListener(final String s) {
        if (this.doneListener != null) {
            this.doneListener.actionPerformed(new ActionEvent(this, 0, s));
        }
    }
    
    public void run() {
        final MenuBuilder menuBuilder = new MenuBuilder(this.m, this.l);
        try {
            while (this.p.next() != -1) {
                switch (this.p.getType()) {
                    default: {
                        continue;
                    }
                    case 1: {
                        menuBuilder.addBookmark(this.p.getName(), this.p.getLink());
                        continue;
                    }
                    case 2: {
                        menuBuilder.startSubmenu(this.p.getName());
                        continue;
                    }
                    case 3: {
                        menuBuilder.endSubmenu();
                        continue;
                    }
                    case 4: {
                        menuBuilder.addSeparator();
                        continue;
                    }
                }
            }
            this.notifyListener("done");
        }
        catch (IOException ex) {
            this.notifyListener("error");
        }
    }
}

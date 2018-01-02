import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Menu;
import java.awt.MenuBar;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMenu extends MenuBar
{
    Menu mFile;
    Menu mHlp;
    
    public JavaMenu() {
        this.mFile = new Menu("File");
        this.mHlp = new Menu("Help");
        this.mFile.add(new MenuItem("New", new MenuShortcut(110)));
        this.mFile.add(new MenuItem("Open", new MenuShortcut(111)));
        this.mFile.add(new MenuItem("Save", new MenuShortcut(115)));
        this.mFile.add(new MenuItem("-"));
        this.mFile.add(new MenuItem("Exit"));
        this.setHelpMenu(this.mHlp);
        this.mHlp.add(new MenuItem("About"));
        this.mHlp.add(new MenuItem("What is JavaNote?"));
        this.add(this.mFile);
        this.add(this.mHlp);
    }
}

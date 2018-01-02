import javax.swing.JMenu;
import java.util.Collection;
import hanzilookup.ui.HanziLookupUIBuilder;
import javax.swing.JMenuBar;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import kiang.chinese.font.ChineseFontFinder;
import java.applet.Applet;
import hanzilookup.i18n.HanziLookupBundleKeys;
import java.util.ResourceBundle;
import netscape.javascript.JSObject;
import hanzilookup.HanziLookup;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MdbgHWIme extends JApplet implements HanziLookup.CharacterSelectionListener
{
    private static final long serialVersionUID = 5683579235322663261L;
    private static final String STROKE_DATA = "/strokes.dat";
    private JSObject windowJSObject;
    private HanziLookup lookupPanel;
    private String charSelectedJSEventHandler;
    private ResourceBundle bundle;
    
    public MdbgHWIme() {
        this.charSelectedJSEventHandler = null;
        this.bundle = HanziLookupBundleKeys.DEFAULT_ENGLISH_BUNDLE;
    }
    
    public void init() {
        this.windowJSObject = JSObject.getWindow((Applet)this);
        this.charSelectedJSEventHandler = this.getParameter("charSelectedJSEventHandler");
        final Font initialFont = ChineseFontFinder.getChineseFont();
        this.lookupPanel = this.buildLookupPanel(initialFont);
        if (initialFont != null) {
            final boolean isSimplifiedFont = ChineseFontFinder.isSimplifiedFont(initialFont);
            final boolean isTraditionalFont = ChineseFontFinder.isTraditionalFont(initialFont);
            if (isSimplifiedFont && !isTraditionalFont) {
                this.lookupPanel.setSearchType(1);
            }
            else if (isTraditionalFont && !isSimplifiedFont) {
                this.lookupPanel.setSearchType(2);
            }
        }
        this.lookupPanel.setNumResults(20);
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this.lookupPanel, "Center");
        this.setJMenuBar(this.buildMenuBar());
    }
    
    private HanziLookup buildLookupPanel(final Font font) {
        try {
            final HanziLookup lookup = new HanziLookup("/strokes.dat", font);
            lookup.addCharacterReceiver(this);
            return lookup;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading in strokes data!", "Error", 0);
            System.exit(1);
            return null;
        }
    }
    
    private JMenuBar buildMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu optionsMenu = HanziLookupUIBuilder.buildOptionsMenu(this.lookupPanel, null, this.bundle);
        menuBar.add(optionsMenu);
        return menuBar;
    }
    
    public void characterSelected(final HanziLookup.CharacterSelectionEvent e) {
        final Object[] args = { String.valueOf(e.getSelectedCharacter()) };
        this.windowJSObject.call(this.charSelectedJSEventHandler, args);
    }
}

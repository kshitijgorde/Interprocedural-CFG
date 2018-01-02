import java.awt.Container;
import java.io.Serializable;
import java.io.IOException;
import java.text.ParseException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.awt.MenuComponent;
import java.awt.PopupMenu;
import java.awt.Dimension;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.Reader;
import java.awt.Font;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ToolBarFactory
{
    protected final Hashtable D;
    protected final Hashtable add;
    protected final Hashtable C;
    private Vector iconTable;
    public Font defaultFont;
    public boolean showTextWithIcons;
    private int zoom;
    
    public ToolBarFactory() {
        this.defaultFont = new Font("SansSerif", 0, 11);
        this.showTextWithIcons = false;
        this.zoom = 100;
        this.D = new Hashtable();
        this.add = new Hashtable();
        this.C = new Hashtable();
        this.iconTable = new Vector();
    }
    
    public final void defineItem(final String s, final int n, final String s2, final String s3) {
        if (s == null) {
            System.err.println(this.getClass().getName() + "ID null!");
            return;
        }
        if (n >= 0) {
            this.D.put(s, new Integer(n));
        }
        if (s2 != null && !s2.equals("null")) {
            this.add.put(s, s2);
        }
        if (s3 != null && !s3.equals("null")) {
            this.C.put(s, s3);
        }
    }
    
    public final void defineItems(final Reader reader) {
        while (true) {
            String word;
            try {
                word = this.readWord(reader);
            }
            catch (EOFException ex2) {
                break;
            }
            try {
                final String word2 = this.readWord(reader);
                final String word3 = this.readWord(reader);
                final String word4 = this.readWord(reader);
                int int1;
                try {
                    int1 = Integer.parseInt(word2);
                }
                catch (NumberFormatException ex3) {
                    int1 = -1;
                }
                this.defineItem(word, int1, word3, word4);
                continue;
            }
            catch (EOFException ex) {
                ex.printStackTrace();
            }
            break;
        }
    }
    
    private String readWord(final Reader reader) {
        final StringBuffer sb = new StringBuffer();
        int n = 1;
        int read;
        while ((read = reader.read()) != 64) {
            if (read == -1) {
                throw new EOFException();
            }
            if (read <= 32) {
                if (n != 0) {
                    continue;
                }
                n = 1;
                read = 32;
            }
            else {
                n = 0;
            }
            sb.append((char)read);
        }
        final String string = sb.toString();
        if (string.length() == 0) {
            return null;
        }
        return string;
    }
    
    public final void setIconList(final Vector iconTable) {
        if (iconTable == null) {
            this.iconTable = new Vector();
        }
        else {
            this.iconTable = iconTable;
        }
    }
    
    public final synchronized void setZoom(final int zoom) {
        if (zoom < 1) {
            throw new IllegalArgumentException();
        }
        this.zoom = zoom;
    }
    
    public final int getZoom() {
        return this.zoom;
    }
    
    public Component createItem(final String name, final ActionListener actionListener, final int n) {
        final WImage icon = this.getIcon(name);
        String label = this.getLabel(name);
        final String command = this.getCommand(name);
        if (!this.showTextWithIcons && icon != null) {
            label = null;
        }
        final WButton wButton = new WButton(icon, name, label, n);
        wButton.setActionCommand(command);
        if (actionListener != null) {
            wButton.addActionListener(actionListener);
        }
        wButton.setName(name);
        wButton.tooltipText = this.getToolTip(name);
        return wButton;
    }
    
    public final MenuItem createMenuItem(final String name, final ActionListener actionListener, final int n) {
        final String label = this.getLabel(name);
        String command = this.getCommand(name);
        MenuItem menuItem;
        if (n == 2) {
            menuItem = new CheckboxMenuItem(label);
        }
        else {
            menuItem = new MenuItem(label);
        }
        if (command == null) {
            command = "";
        }
        menuItem.setActionCommand(command);
        if (actionListener != null) {
            menuItem.addActionListener(actionListener);
        }
        menuItem.setName(name);
        return menuItem;
    }
    
    public String getCommand(final String s) {
        final String s2 = this.C.get(s);
        if (s2 == null) {
            return s;
        }
        return s2;
    }
    
    public final String getLabel(final String s) {
        final String s2 = this.add.get(s);
        if (s2 == null) {
            return s;
        }
        return s2;
    }
    
    public String getToolTip(final String s) {
        if (this.D.containsKey(s)) {
            return formatToolTip(this.add.get(s));
        }
        return null;
    }
    
    public static final String formatToolTip(String s) {
        if (s != null && s.length() > 0 && Character.isLowerCase(s.charAt(0)) && !s.startsWith("www")) {
            final char[] charArray = s.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            s = new String(charArray);
        }
        return s;
    }
    
    public WImage getIcon(final String s) {
        WImage resize = null;
        final Integer n = this.D.get(s);
        if (n != null) {
            final int intValue = n;
            if (intValue >= 0 && intValue < this.iconTable.size()) {
                resize = (WImage)this.iconTable.elementAt(n);
            }
            if (resize != null && this.zoom != 100) {
                resize = WImage.resize(resize, new Dimension(resize.getWidth() * this.zoom / 100, resize.getHeight() * this.zoom / 100), 2, null);
            }
        }
        return resize;
    }
    
    public final Font getFont() {
        if (this.zoom != 100) {
            return new Font(this.defaultFont.getName(), this.defaultFont.getStyle(), this.defaultFont.getSize() * this.zoom / 100);
        }
        return this.defaultFont;
    }
    
    public Component addNewItem(final WToolPane wToolPane, final String s, final int n, final Vector vector, final ActionListener actionListener) {
        final Component item = this.createItem(s, actionListener, n);
        if (item instanceof WButton && vector != null) {
            ((WButton)item).synchronize(vector, true);
        }
        wToolPane.add(item);
        return item;
    }
    
    public final MenuComponent addNewMenuItem(final PopupMenu popupMenu, final String s, final int n, final Vector vector, final ActionListener actionListener) {
        final MenuItem menuItem = this.createMenuItem(s, actionListener, n);
        popupMenu.add(menuItem);
        return menuItem;
    }
    
    public WToolPane addNewSection(final WToolBar wToolBar, final String s, int n) {
        final String label = this.getLabel(s);
        if (n < 1) {
            n = 2;
        }
        return wToolBar.addSection(s, label, n);
    }
    
    public synchronized WToolBar createToolbar(final Reader reader, WToolBar wToolBar, final ActionListener actionListener) {
        if (wToolBar == null) {
            wToolBar = new WToolBar();
        }
        wToolBar.setFont(this.getFont());
        final StringBuffer sb = new StringBuffer(256);
        final char[] array = new char[256];
        try {
            int read;
            while ((read = reader.read(array)) != -1) {
                sb.append(array, 0, read);
            }
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(sb.toString()));
            streamTokenizer.resetSyntax();
            streamTokenizer.lowerCaseMode(false);
            streamTokenizer.parseNumbers();
            streamTokenizer.whitespaceChars(0, 32);
            streamTokenizer.wordChars(48, 57);
            streamTokenizer.wordChars(65, 90);
            streamTokenizer.wordChars(94, 122);
            streamTokenizer.wordChars(126, 255);
            streamTokenizer.whitespaceChars(44, 44);
            streamTokenizer.whitespaceChars(58, 59);
            streamTokenizer.slashSlashComments(true);
            while (true) {
                streamTokenizer.nextToken();
                if (streamTokenizer.ttype != 123) {
                    break;
                }
                final WToolPane sectionDef = this.readSectionDef(streamTokenizer, wToolBar);
                if (sectionDef == null) {
                    continue;
                }
                this.readCompList(streamTokenizer, wToolBar, sectionDef, null, actionListener);
            }
            if (streamTokenizer.ttype == -1) {
                final StringBuffer sb2 = new StringBuffer();
                final WToolBar wToolBar2 = wToolBar;
                wToolBar2.I = sb2.append(wToolBar2.I).append(sb.toString()).toString();
                return wToolBar;
            }
            throw new ParseException("", -1);
        }
        catch (ParseException ex) {
            System.err.println("Bad syntax for in the toolbar definition");
        }
        catch (IOException ex2) {
            System.err.println("Bad syntax for in the toolbar definition");
        }
        finally {
            wToolBar.getSelector().addGlue();
        }
        return wToolBar;
    }
    
    private WToolPane readSectionDef(final StreamTokenizer streamTokenizer, final WToolBar wToolBar) {
        String sval = null;
        int n = 2;
        int n2 = 0;
        while (true) {
            streamTokenizer.nextToken();
            if (streamTokenizer.ttype == -3) {
                sval = streamTokenizer.sval;
            }
            else if (streamTokenizer.ttype == -2) {
                n = (int)streamTokenizer.nval;
            }
            else {
                if (streamTokenizer.ttype == 125 && sval != null) {
                    final WToolPane addNewSection = this.addNewSection(wToolBar, sval, n);
                    if (n2 != 0) {
                        wToolBar.setSelectedSection(addNewSection.getName());
                    }
                    return addNewSection;
                }
                if (streamTokenizer.ttype != 33) {
                    streamTokenizer.pushBack();
                    return null;
                }
                n2 = 1;
            }
        }
    }
    
    private void readCompList(final StreamTokenizer streamTokenizer, final WToolBar wToolBar, final WToolPane wToolPane, final PopupMenu popupMenu, final ActionListener actionListener) {
        int n = 1;
        Vector vector = null;
        PopupMenu popupMenu2 = null;
        Serializable s = null;
        while (true) {
            streamTokenizer.nextToken();
            if (streamTokenizer.ttype == -3) {
                s = null;
                if (popupMenu != null) {
                    if (popupMenu2 != null) {
                        popupMenu2.setLabel(this.getLabel(streamTokenizer.sval));
                        popupMenu.add(popupMenu2);
                    }
                    else {
                        s = this.addNewMenuItem(popupMenu, streamTokenizer.sval, n, vector, actionListener);
                    }
                }
                else {
                    final Component component = (Component)(s = this.addNewItem(wToolPane, streamTokenizer.sval, n, vector, actionListener));
                    if (popupMenu2 != null) {
                        component.add(popupMenu2);
                    }
                }
                popupMenu2 = null;
            }
            else if (streamTokenizer.ttype == 33) {
                if (!(s instanceof WButton)) {
                    continue;
                }
                ((WButton)s).setSelected(true, false);
            }
            else if (streamTokenizer.ttype == 124) {
                if (popupMenu != null) {
                    popupMenu.addSeparator();
                }
                else {
                    wToolPane.addSeparator();
                }
            }
            else if (streamTokenizer.ttype == 42) {
                if (popupMenu != null) {
                    continue;
                }
                wToolPane.addEmptyItem();
            }
            else if (streamTokenizer.ttype == 64) {
                if (popupMenu != null) {
                    continue;
                }
                wToolPane.addGlue();
            }
            else if (streamTokenizer.ttype == 40) {
                n = 3;
                vector = new Vector();
            }
            else if (streamTokenizer.ttype == 41) {
                n = 1;
                vector = null;
            }
            else if (streamTokenizer.ttype == 60) {
                n = 2;
                vector = new Vector();
            }
            else if (streamTokenizer.ttype == 62) {
                n = 1;
                vector = null;
            }
            else if (streamTokenizer.ttype == 91) {
                popupMenu2 = new PopupMenu();
                this.readCompList(streamTokenizer, null, null, popupMenu2, actionListener);
            }
            else {
                if (streamTokenizer.ttype != 93) {
                    streamTokenizer.pushBack();
                    return;
                }
                if (popupMenu != null) {
                    return;
                }
                continue;
            }
        }
    }
    
    public final void rebuild(final WToolBar wToolBar, final ActionListener actionListener, int z) {
        final String selectedSection = wToolBar.getSelectedSection();
        final StringReader stringReader = new StringReader(wToolBar.I);
        wToolBar.I = "";
        wToolBar.removeContent();
        if (z == 1) {
            z = 1;
        }
        else {
            z = 0;
        }
        if (z != wToolBar.getOrientation()) {
            wToolBar.Z = z;
            wToolBar.getSelector().I = wToolBar.Z;
            final Vector<WImage> imagesList = new Vector<WImage>(wToolBar.C.length);
            for (int n = 0; n < wToolBar.C.length && wToolBar.C[n] != null; ++n) {
                imagesList.addElement(wToolBar.C[n].rotate(wToolBar.C[n]));
            }
            wToolBar.setImagesList(imagesList);
        }
        this.createToolbar(stringReader, wToolBar, actionListener);
        wToolBar.setSelectedSection(selectedSection);
        final Container parent = wToolBar.getParent();
        if (parent != null) {
            parent.invalidate();
            parent.validate();
        }
    }
}

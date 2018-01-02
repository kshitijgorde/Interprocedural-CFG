// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.io.Reader;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.io.IOException;
import geracemenu.parser.TokenExpectedException;
import geracemenu.parser.StructuralInconsistencyException;
import java.awt.Component;
import geracemenu.parser.MenuItemParser;
import geracemenu.parser.MenuItemTokenizer;
import java.io.InputStreamReader;
import geracemenu.parser.MenuBuilder;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URL;
import java.applet.Applet;

public class parseMenuApplet extends Applet
{
    public static URL codeBase;
    public static String URLspec;
    private Applet applet;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.applet = this;
        String s = "menudef.p";
        if (this == null) {
            throw null;
        }
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                System.out.println("requesting focus...");
                parseMenuApplet.this.requestFocus();
            }
            
            {
                this.constructor$0(parseMenuApplet.this);
            }
            
            public void constructor$0(final parseMenuApplet parseMenuApplet) {
            }
        });
        if (this == null) {
            throw null;
        }
        this.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                System.out.println("lost focus.");
                parseMenuApplet.this.cancelSelection();
            }
            
            public void focusGained(final FocusEvent focusEvent) {
                System.out.println("gained focus.");
            }
            
            {
                this.constructor$0(parseMenuApplet.this);
            }
            
            public void constructor$0(final parseMenuApplet parseMenuApplet) {
            }
        });
        try {
            final String parameter;
            if ((parameter = this.getParameter("menu")) != null) {
                s = parameter;
            }
            parseMenuApplet.codeBase = this.getCodeBase();
            parseMenuApplet.URLspec = this.getCodeBase().getProtocol() + ((parseMenuApplet.codeBase.getHost() == null) ? ":" : ("://" + parseMenuApplet.codeBase.getHost())) + this.getCodeBase().getFile();
            final URL url = new URL(parseMenuApplet.URLspec + s);
            System.out.println("Looking for menu definition file at " + url);
            MenuBuilder.setAppletContext(this.getAppletContext());
            try {
                this.add("Center", MenuBuilder.getMenuPanel(new MenuItemParser(new MenuItemTokenizer(this.read(new InputStreamReader(url.openStream())))).parseMenu()));
            }
            catch (StructuralInconsistencyException ex) {
                System.out.println(ex.getMessage());
            }
            catch (TokenExpectedException ex2) {
                System.out.println(ex2.getMessage());
            }
            catch (IOException ex5) {
                System.out.println("No menu definiton file found.");
            }
            catch (RuntimeException ex3) {
                System.out.println(ex3.getMessage());
            }
        }
        catch (MalformedURLException ex4) {
            System.out.println(ex4.getMessage());
        }
    }
    
    public void destroy() {
        this.stop();
        this.removeAll();
    }
    
    public void stop() {
    }
    
    protected void cancelSelection() {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof MenuItemGroup) {
                ((MenuItemGroup)components[i]).clearItemsSelection();
                break;
            }
        }
    }
    
    private String read(final BufferedReader bufferedReader) throws IOException {
        final StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
    
    private String read(final InputStreamReader inputStreamReader) throws IOException {
        final StringBuffer sb = new StringBuffer();
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        final char[] array = new char[1024];
        int read;
        while ((read = bufferedReader.read(array, 0, 1024)) > 0) {
            sb.append(new String(array, 0, read));
        }
        bufferedReader.close();
        return sb.toString();
    }
    
    private boolean isNetscape() {
        final String property = System.getProperty("java.vendor");
        try {
            return property.indexOf("Netscape") != -1;
        }
        catch (SecurityException ex) {
            System.out.println("Unable to get a java vendor to check if it's Netscape.");
            return false;
        }
    }
    
    static {
        parseMenuApplet.codeBase = null;
        parseMenuApplet.URLspec = "";
    }
}

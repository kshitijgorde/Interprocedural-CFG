// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.component;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.ScrollPane;
import edu.wise.correl.Cor_app;
import java.awt.Frame;

public class DataEditorWindow extends Frame
{
    Cor_app ca;
    private static DataEditor de;
    private static ScrollPane sp;
    private static Panel menu;
    public static final boolean DEBUG = false;
    
    public DataEditorWindow(final Cor_app ca, final String s, final String s2) {
        super("Data Editor");
        this.ca = ca;
        this.setLayout(new FlowLayout());
        this.setSize(400, 300);
        (DataEditorWindow.menu = new Panel()).resize(this.getSize().width, this.getSize().height);
        (DataEditorWindow.sp = new ScrollPane()).resize(this.getSize());
        DataEditorWindow.de = new DataEditor(ca, Cor_app.cd.getXName(), Cor_app.cd.getYName());
        this.addWindowListener(new deAdapter());
        this.add(DataEditorWindow.sp);
        DataEditorWindow.sp.add(DataEditorWindow.de);
    }
    
    public static void update() {
        DataEditorWindow.de.update();
    }
    
    class deAdapter extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            DataEditorWindow.this.dispose();
        }
    }
}

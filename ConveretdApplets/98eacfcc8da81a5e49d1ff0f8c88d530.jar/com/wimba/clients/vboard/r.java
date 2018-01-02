// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Point;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.filechooser.FileFilter;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.Checkbox;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public final class r extends JDialog implements ActionListener, PropertyChangeListener
{
    private JFileChooser a;
    private File[] b;
    private JOptionPane c;
    private n d;
    private String e;
    private String f;
    private boolean g;
    private static File h;
    private Checkbox i;
    private WindowAdapter j;
    
    public final Checkbox a() {
        return this.i;
    }
    
    public final boolean b() {
        return this.g;
    }
    
    public final File[] c() {
        return this.b;
    }
    
    public r(final n n) {
        this.g = false;
        this.setModal(true);
        this.d = n;
        this.setTitle(n.e("import_title"));
        this.setLocationRelativeTo(n);
        this.e = n.e("btn_ok");
        this.f = n.e("btn_cancel");
        final String e = n.e("import_select_file");
        (this.a = new JFileChooser()).setDialogType(0);
        this.a.setMultiSelectionEnabled(!n.G());
        this.a.setControlButtonsAreShown(false);
        if (r.h != null) {
            this.a.setCurrentDirectory(r.h);
        }
        final k fileFilter;
        (fileFilter = new k(10485760L)).a(".mp3");
        fileFilter.a(".wav");
        fileFilter.a(".spx");
        if (!n.G()) {
            fileFilter.a(".wvb");
            fileFilter.b(n.e("import_supported_files"));
        }
        else {
            fileFilter.b(n.e("import_supported_files_nowvb"));
        }
        this.a.setAcceptAllFileFilterUsed(false);
        this.a.setFileFilter(fileFilter);
        final Object[] array = { e, this.a, null };
        if (n.D()) {
            array[2] = (this.i = new Checkbox(this.d.a().getString("place.content.in.a.top.level.imported.messages.thread")));
        }
        final Object[] array2 = { this.e, this.f };
        this.setContentPane(this.c = new JOptionPane(array, 3, 0, null, array2, array2[0]));
        this.pack();
        this.setLocation(new Point(n.getLocation().x + n.getLocationOnScreen().x + (n.getSize().width - this.getSize().width) / 2, n.getLocation().y + n.getLocationOnScreen().y + (n.getSize().height - this.getSize().height) / 3));
        this.setDefaultCloseOperation(0);
        this.addWindowListener(this.j = new l(this));
        this.c.addPropertyChangeListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.c.setValue(this.e);
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (this.isVisible() && propertyChangeEvent.getSource() == this.c && ("value".equals(propertyName) || "inputValue".equals(propertyName))) {
            final Object value;
            if ((value = this.c.getValue()) == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            this.c.setValue(JOptionPane.UNINITIALIZED_VALUE);
            if (this.e.equals(value)) {
                if (this.a.getSelectedFile() == null) {
                    this.removeWindowListener(this.j);
                    JOptionPane.showMessageDialog(this, this.d.e("import_empty_selection_msg"), this.d.e("import_empty_selection_title"), 0);
                    this.addWindowListener(this.j);
                    this.g = false;
                    return;
                }
                this.g = true;
                this.setVisible(false);
                if (this.a.isMultiSelectionEnabled()) {
                    this.b = this.a.getSelectedFiles();
                }
                else {
                    (this.b = new File[1])[0] = this.a.getSelectedFile();
                }
                r.h = this.a.getCurrentDirectory();
            }
            else {
                this.b = null;
                this.g = false;
                r.h = this.a.getCurrentDirectory();
                this.setVisible(false);
            }
        }
    }
    
    public final void d() {
        this.pack();
        this.setVisible(true);
    }
    
    static JOptionPane a(final r r) {
        return r.c;
    }
    
    static {
        r.h = null;
    }
}

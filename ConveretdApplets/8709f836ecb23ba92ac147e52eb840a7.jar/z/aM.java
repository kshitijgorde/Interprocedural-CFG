// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Observable;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.EventObject;
import java.awt.event.KeyEvent;
import javax.swing.ListCellRenderer;
import javax.swing.ComboBoxModel;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import java.util.Iterator;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Container;
import java.util.Collections;
import java.util.Collection;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.ArrayList;
import java.awt.Frame;
import com.photochannel.easyUploader.AppletParams;
import com.photochannel.easyUploader.d;
import javax.swing.ButtonGroup;
import java.io.FileFilter;
import javax.swing.ImageIcon;
import java.util.Set;
import java.util.List;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.Observer;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import java.awt.event.ComponentListener;

final class aM implements ComponentListener
{
    private /* synthetic */ JScrollPane a;
    private /* synthetic */ as b;
    private /* synthetic */ aT c;
    
    aM(final aT c, final JScrollPane a, final as b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.b.a(this.a.getViewport().getExtentSize().width);
        this.b.b(this.a.getViewport().getExtentSize().height - 1);
        this.c.g.invalidate();
    }
}

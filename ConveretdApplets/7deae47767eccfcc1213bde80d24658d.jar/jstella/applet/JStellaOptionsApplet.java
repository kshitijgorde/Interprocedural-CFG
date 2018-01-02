// 
// Decompiled by Procyon v0.5.30
// 

package jstella.applet;

import java.util.Enumeration;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JApplet;

public class JStellaOptionsApplet extends JApplet
{
    private static final long serialVersionUID = 3232640609035844558L;
    private JStellaApplet myMainApplet;
    private JButton ButtonHelp;
    private JButton ButtonReset;
    private JButton ButtonSelect;
    private JLabel LabelPause;
    private JMenuItem MITogglePause;
    private JPopupMenu PUDebug;
    private JPanel PanelOptions;
    
    public JStellaOptionsApplet() {
        this.myMainApplet = null;
    }
    
    public void init() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    JStellaOptionsApplet.this.initComponents();
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        this.PUDebug = new JPopupMenu();
        this.MITogglePause = new JMenuItem();
        this.PanelOptions = new JPanel();
        this.ButtonReset = new JButton();
        this.ButtonSelect = new JButton();
        this.ButtonHelp = new JButton();
        this.LabelPause = new JLabel();
        this.MITogglePause.setText("Toggle pause");
        this.MITogglePause.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaOptionsApplet.this.MITogglePauseActionPerformed(evt);
            }
        });
        this.PUDebug.add(this.MITogglePause);
        this.PanelOptions.setFocusable(false);
        this.PanelOptions.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent evt) {
                JStellaOptionsApplet.this.PanelOptionsMousePressed(evt);
            }
        });
        this.PanelOptions.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent evt) {
                JStellaOptionsApplet.this.PanelOptionsMouseMoved(evt);
            }
        });
        this.ButtonReset.setText("Reset");
        this.ButtonReset.setDefaultCapable(false);
        this.ButtonReset.setFocusable(false);
        this.ButtonReset.setRequestFocusEnabled(false);
        this.ButtonReset.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaOptionsApplet.this.ButtonResetActionPerformed(evt);
            }
        });
        this.PanelOptions.add(this.ButtonReset);
        this.ButtonSelect.setText("Select");
        this.ButtonSelect.setDefaultCapable(false);
        this.ButtonSelect.setFocusable(false);
        this.ButtonSelect.setRequestFocusEnabled(false);
        this.ButtonSelect.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaOptionsApplet.this.ButtonSelectActionPerformed(evt);
            }
        });
        this.PanelOptions.add(this.ButtonSelect);
        this.ButtonHelp.setText("Help");
        this.ButtonHelp.setDefaultCapable(false);
        this.ButtonHelp.setFocusable(false);
        this.ButtonHelp.setRequestFocusEnabled(false);
        this.ButtonHelp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaOptionsApplet.this.ButtonHelpActionPerformed(evt);
            }
        });
        this.PanelOptions.add(this.ButtonHelp);
        this.PanelOptions.add(this.LabelPause);
        this.getContentPane().add(this.PanelOptions, "Center");
    }
    
    private void PanelOptionsMousePressed(final MouseEvent evt) {
        if (SwingUtilities.isMiddleMouseButton(evt)) {
            this.PUDebug.show(this.PanelOptions, evt.getX(), evt.getY());
        }
    }
    
    private void PanelOptionsMouseMoved(final MouseEvent evt) {
    }
    
    private void MITogglePauseActionPerformed(final ActionEvent evt) {
        if (this.getMainApplet() != null) {
            this.getMainApplet().toggleManualPause();
        }
    }
    
    private void ButtonHelpActionPerformed(final ActionEvent evt) {
        if (this.getMainApplet() != null) {
            this.getMainApplet().doHelp();
        }
    }
    
    private void ButtonSelectActionPerformed(final ActionEvent evt) {
        if (this.getMainApplet() != null) {
            this.getMainApplet().doSelect();
        }
    }
    
    private void ButtonResetActionPerformed(final ActionEvent evt) {
        if (this.getMainApplet() != null) {
            this.getMainApplet().doReset();
        }
    }
    
    private JStellaApplet getMainApplet() {
        if (this.myMainApplet == null) {
            final Enumeration zApplets = this.getAppletContext().getApplets();
            while (zApplets.hasMoreElements()) {
                final Object zObj = zApplets.nextElement();
                if (zObj instanceof JStellaApplet) {
                    this.myMainApplet = (JStellaApplet)zObj;
                    break;
                }
            }
        }
        return this.myMainApplet;
    }
}

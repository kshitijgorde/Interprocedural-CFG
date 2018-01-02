// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import jmaster.jumploader.model.api.common.IAttribute;
import jmaster.jumploader.model.api.file.IFile;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.accessibility.Accessible;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import jmaster.util.B.A;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import jmaster.jumploader.model.api.B;
import java.awt.Font;
import jmaster.jumploader.model.api.upload.IUploadFile;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import jmaster.jumploader.view.impl.GenericView;

public class MetadataView extends GenericView implements ActionListener, KeyListener, FocusListener
{
    private static final long \u00f0 = 2087644957795028151L;
    protected JLabel \u00ec;
    protected JButton \u00ef;
    protected JButton \u00ed;
    protected Component \u00ee;
    protected IUploadFile \u00ea;
    protected Font \u00eb;
    protected Font \u00e9;
    
    public MetadataView(final B b) {
        super(b);
        this.\u00ec = new JLabel();
        this.\u00ef = new JButton("Ok");
        this.\u00ed = new JButton("Cancel");
        try {
            this.setFocusable(true);
            this.addFocusListener(this);
            final jmaster.jumploader.model.impl.A.A k = b.K();
            this.I.injectProperties(this, "metadataView");
            final JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = this.I.newGBC();
            gbc.anchor = 18;
            gbc.weightx = 1.0;
            gbc.gridx = 1;
            gbc.fill = 2;
            for (int i = 0; i < k.C().size(); ++i) {
                final jmaster.jumploader.model.impl.A.B b2 = k.C().get(i);
                JLabel label = null;
                if (!A.C(b2.F())) {
                    label = new JLabel(b2.F());
                    this.I.injectProperties(label, "metadataView.label");
                    label.setFont(b2.H() ? this.\u00e9 : this.\u00eb);
                }
                boolean b3 = false;
                Accessible \u00ee = null;
                if ("text".equals(b2.D())) {
                    final JTextField textField = new JTextField();
                    textField.addActionListener(this);
                    textField.addKeyListener(this);
                    \u00ee = textField;
                }
                if ("textarea".equals(b2.D())) {
                    final JTextArea textArea = new JTextArea();
                    textArea.setRows((int)(Object)b2.E());
                    textArea.addKeyListener(this);
                    b3 = true;
                    \u00ee = textArea;
                }
                if ("checkbox".equals(b2.D())) {
                    ((JComponent)(\u00ee = new JCheckBox(b2.F()))).setFont(b2.H() ? this.\u00e9 : this.\u00eb);
                    label = null;
                }
                if (this.\u00ee == null) {
                    this.\u00ee = (Component)\u00ee;
                }
                this.I.injectProperties(\u00ee, "metadataView.input");
                if (label != null) {
                    gbc = (GridBagConstraints)gbc.clone();
                    gbc.insets = new Insets(4, 4, 0, 4);
                    final GridBagConstraints gridBagConstraints = gbc;
                    ++gridBagConstraints.gridy;
                    panel.add(label, gbc);
                }
                gbc = (GridBagConstraints)gbc.clone();
                gbc.insets = new Insets(0, 4, 4, 4);
                final GridBagConstraints gridBagConstraints2 = gbc;
                ++gridBagConstraints2.gridy;
                panel.add((Component)(b3 ? new JScrollPane((Component)\u00ee) : \u00ee), gbc);
                b2.A((Component)\u00ee);
                b2.A(label);
            }
            final JPanel panel2 = new JPanel();
            if (!A.C(k.B())) {
                this.\u00ef.setText(k.B());
                this.\u00ef.addActionListener(this);
                panel2.add(this.\u00ef);
            }
            if (!A.C(k.I())) {
                this.\u00ed.setText(k.I());
                this.\u00ed.addActionListener(this);
                panel2.add(this.\u00ed);
            }
            this.setLayout(new BorderLayout());
            if (!A.C(k.H())) {
                this.\u00ec.setText(k.H());
                this.add(this.\u00ec, "North");
                this.I.injectProperties(this.\u00ec, "metadataView.title");
            }
            this.add(panel, "Center");
            this.add(panel2, "South");
            final Dimension dimension = new Dimension((int)(Object)k.F(), (int)(Object)k.D());
            this.setSize(dimension);
            this.setMaximumSize(dimension);
        }
        catch (Exception ex) {
            this.E.E("Failed to create metadata view", ex);
        }
    }
    
    public IUploadFile getFile() {
        return this.\u00ea;
    }
    
    public void setFile(final IUploadFile \u00ea) {
        this.\u00ea = \u00ea;
    }
    
    public Font getDefaultLabelFont() {
        return this.\u00eb;
    }
    
    public void setDefaultLabelFont(final Font \u00eb) {
        this.\u00eb = \u00eb;
    }
    
    public Font getRequiredLabelFont() {
        return this.\u00e9;
    }
    
    public void setRequiredLabelFont(final Font \u00e9) {
        this.\u00e9 = \u00e9;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!actionEvent.getSource().equals(this.\u00ed)) {
            this.storeDataToFile();
        }
        this.setVisible(false);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27) {
            this.setVisible(false);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this.\u00ee != null) {
            this.\u00ee.requestFocusInWindow();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void storeDataToFile() {
        final jmaster.jumploader.model.impl.A.A k = this.F.K();
        for (int i = 0; i < k.C().size(); ++i) {
            String value = null;
            final jmaster.jumploader.model.impl.A.B b = k.C().get(i);
            if ("text".equals(b.D())) {
                value = ((JTextField)b.A()).getText();
            }
            if ("textarea".equals(b.D())) {
                value = ((JTextArea)b.A()).getText();
            }
            if ("checkbox".equals(b.D())) {
                value = "" + ((JCheckBox)b.A()).isSelected();
            }
            IAttribute attribute = this.\u00ea.getAttributeSet().getAttributeByName(b.G());
            if (attribute == null) {
                attribute = this.\u00ea.getAttributeSet().createAttribute(b.G(), value);
            }
            b.D(value);
            attribute.setValue(value);
            attribute.setSendToServer(true);
            if ("fileName".equals(b.G())) {
                ((jmaster.jumploader.model.impl.upload.B)this.\u00ea).A("" + value);
            }
        }
        jmaster.jumploader.model.impl.A.A.A(this.\u00ea, k.G());
        this.F.D().metadataChanged(this.\u00ea);
    }
    
    public void loadDataFromFile() {
        final jmaster.jumploader.model.impl.A.A k = this.F.K();
        for (int i = 0; i < k.C().size(); ++i) {
            final jmaster.jumploader.model.impl.A.B b = k.C().get(i);
            final IAttribute attributeByName = this.\u00ea.getAttributeSet().getAttributeByName(b.G());
            final Object o = (attributeByName == null) ? null : attributeByName.getValue();
            if ("text".equals(b.D())) {
                ((JTextField)b.A()).setText((String)o);
            }
            if ("textarea".equals(b.D())) {
                ((JTextArea)b.A()).setText((String)o);
            }
            if ("checkbox".equals(b.D())) {
                ((JCheckBox)b.A()).setSelected(new Boolean("" + o));
            }
            if ("fileName".equals(b.G())) {
                final jmaster.jumploader.model.impl.upload.B b2 = (jmaster.jumploader.model.impl.upload.B)this.\u00ea;
                if (b.A() instanceof JTextComponent) {
                    ((JTextComponent)b.A()).setText(b2.getName());
                }
            }
        }
    }
}

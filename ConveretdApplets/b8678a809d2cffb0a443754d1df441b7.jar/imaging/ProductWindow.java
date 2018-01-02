// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.HashMap;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ProductWindow extends JFrame implements ActionListener
{
    HashMap<String, String> mfcrs;
    HashMap<String, String> products;
    HashMap<String, String> patterns;
    
    public ProductWindow() {
        this.mfcrs = new HashMap<String, String>();
        this.products = new HashMap<String, String>();
        this.patterns = new HashMap<String, String>();
        this.setSize(100, 300);
        final JComboBox mfcList = new JComboBox();
        mfcList.setEditable(true);
        mfcList.addActionListener(this);
        final JComboBox prodList = new JComboBox();
        prodList.setEditable(true);
        prodList.addActionListener(this);
        final JComboBox patternList = new JComboBox();
        patternList.setEditable(true);
        patternList.addActionListener(this);
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, 3));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createVerticalGlue());
        buttonPane.add(mfcList);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(prodList);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(patternList);
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
    }
}

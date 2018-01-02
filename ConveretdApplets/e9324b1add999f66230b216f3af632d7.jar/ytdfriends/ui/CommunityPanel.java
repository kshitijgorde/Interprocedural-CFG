// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.ui;

import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import java.awt.Component;
import edu.berkeley.guir.prefuse.event.FocusEvent;
import edu.berkeley.guir.prefuse.event.FocusListener;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import java.awt.Dimension;
import ytdfriends.FriendsPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

public class CommunityPanel extends JPanel implements ChangeListener, ActionListener
{
    public static final String ENABLED = "Disable";
    private static final String DISABLED = "Enable";
    private static final String UPDATE = "Update";
    private JLabel commLabel;
    private JSlider commSlider;
    public JButton enableButton;
    private FriendsPanel fPanel;
    
    public CommunityPanel(final FriendsPanel fPanel) {
        this.fPanel = fPanel;
        this.commLabel = new JLabel("Community");
        (this.commSlider = new JSlider()).setValue(0);
        this.commSlider.setPreferredSize(new Dimension(200, 25));
        this.commSlider.setMaximumSize(new Dimension(200, 25));
        this.commSlider.addChangeListener(this);
        this.commSlider.setEnabled(false);
        (this.enableButton = new JButton("Enable")).addActionListener(this);
        final CommunitySet comm = (CommunitySet)fPanel.getItemRegistry().getFocusManager().getFocusSet((Object)"community");
        comm.addFocusListener((FocusListener)new FocusListener() {
            public void focusChanged(final FocusEvent e) {
                CommunityPanel.this.commSlider.setModel(comm.getRange());
            }
        });
        this.add(this.commLabel);
        this.add(this.commSlider);
        this.add(this.enableButton);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
    }
    
    public void setForeground(final Color c) {
        super.setForeground(c);
        if (this.commLabel != null) {
            this.commLabel.setForeground(c);
        }
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.commLabel != null) {
            this.commLabel.setBackground(c);
        }
        if (this.commSlider != null) {
            this.commSlider.setBackground(c);
        }
        if (this.enableButton != null) {
            this.enableButton.setBackground(c);
        }
    }
    
    public void stateChanged(final ChangeEvent arg0) {
        final JSlider slider = (JSlider)arg0.getSource();
        this.fPanel.constructCommunities(slider.getValue());
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.enableButton) {
            final boolean enabled = this.enableButton.getText() == "Disable";
            this.enableButton.setText(enabled ? "Enable" : "Disable");
            this.commSlider.setEnabled(!enabled);
            if (!enabled) {
                this.fPanel.constructCommunities(-1);
            }
            else {
                this.fPanel.constructCommunities(-2);
            }
        }
        this.fPanel.runFilter();
    }
}

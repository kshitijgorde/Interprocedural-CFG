import javax.swing.event.ListDataEvent;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.event.ListSelectionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class HideSceneDialog extends JDialog implements WindowListener, ActionListener, Observer, ListEntryBuilder, ListDataListener
{
    private SceneListList sceneListList;
    private JPanel buttonPanel;
    private JButton hideButton;
    private JButton unhideButton;
    private JButton closeButton;
    private MosaicData md;
    private imgViewer applet;
    
    public HideSceneDialog(final JFrame frame, final imgViewer applet, final MosaicData md) {
        super(frame, "Hidden Scene List", false);
        this.applet = applet;
        this.md = md;
        this.getContentPane().setLayout(new BorderLayout());
        final Sensor[] sensors = applet.getSensors();
        final SceneList[] array = new SceneList[sensors.length];
        for (int i = 0; i < sensors.length; ++i) {
            array[i] = sensors[i].hiddenSceneList;
        }
        this.sceneListList = new SceneListList(applet, this, null, applet.normalFont, 6, this, false, false, "Hidden Scene List", array, false);
        (this.buttonPanel = new JPanel()).setLayout(new GridBagLayout());
        (this.hideButton = new JButton("Hide")).setMnemonic(72);
        this.hideButton.setToolTipText("Hide scene");
        this.hideButton.addActionListener(this);
        (this.unhideButton = new JButton("Unhide")).setMnemonic(85);
        this.unhideButton.setToolTipText("Unhide scene");
        this.unhideButton.addActionListener(this);
        (this.closeButton = new JButton("Close")).setMnemonic(67);
        this.closeButton.setToolTipText("Close hidden scene list");
        this.closeButton.addActionListener(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.fill = 1;
        this.buttonPanel.add(this.hideButton, gridBagConstraints);
        this.buttonPanel.add(this.unhideButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridwidth = 0;
        this.buttonPanel.add(this.closeButton, gridBagConstraints);
        this.getContentPane().add(this.sceneListList, "Center");
        this.getContentPane().add(this.buttonPanel, "South");
        this.setSize(450, 200);
        this.disableButtons();
        this.addWindowListener(this);
        this.setSensor(applet.sensorMenu.getCurrentSensor());
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    private void enableButtons() {
        this.unhideButton.setEnabled(true);
    }
    
    private void disableButtons() {
        this.unhideButton.setEnabled(false);
    }
    
    @Override
    public String getEntry(final Metadata metadata) {
        final Sensor sensor = metadata.getSensor();
        String s;
        if (sensor.hasCloudCover) {
            s = new String(", " + metadata.cloudCover + "% Cloud");
        }
        else {
            s = new String("");
        }
        final int quality = metadata.getQuality();
        String s2;
        if (quality >= 0) {
            s2 = new String(", Quality: " + quality);
        }
        else {
            s2 = new String("");
        }
        String s3;
        if (sensor.hasCloudCover) {
            s3 = new String(", Acquired: " + metadata.getDateString());
        }
        else {
            s3 = new String("");
        }
        return metadata.getEntityIDForDisplay() + s + s2 + s3;
    }
    
    public void hideScene(final Metadata metadata) {
        this.sceneListList.add(metadata);
    }
    
    public void setSensor(final Sensor sensor) {
        this.sceneListList.setSensor(sensor);
        if (this.sceneListList.getSceneCount() > 0) {
            this.enableButtons();
        }
        else {
            this.disableButtons();
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Hide")) {
            this.sceneListList.addActiveScene();
        }
        else if (actionCommand.equals("Unhide")) {
            this.sceneListList.remove();
        }
        else if (actionCommand.equals("Close")) {
            this.setVisible(false);
        }
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (observable == this.applet.md) {
            if (this.applet.md.getCurrentScene() != null) {
                this.hideButton.setEnabled(true);
            }
            else {
                this.hideButton.setEnabled(false);
            }
        }
    }
    
    @Override
    public void intervalAdded(final ListDataEvent listDataEvent) {
        this.applet.searchLimitDialog.applyFilter();
        this.enableButtons();
    }
    
    @Override
    public void intervalRemoved(final ListDataEvent listDataEvent) {
        this.applet.searchLimitDialog.applyFilter();
        if (this.sceneListList.getSceneCount() > 0) {
            this.enableButtons();
        }
        else {
            this.disableButtons();
        }
    }
    
    @Override
    public void contentsChanged(final ListDataEvent listDataEvent) {
    }
}

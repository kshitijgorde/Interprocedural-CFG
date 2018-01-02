import javax.swing.event.ListDataEvent;
import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneListDialog extends JDialog implements WindowListener, ActionListener, Observer, ListEntryBuilder, ListSelectionListener, ListDataListener
{
    public SceneListList sceneListList;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton displayButton;
    private JButton shopButton;
    private JButton closeButton;
    private JButton showBrowseButton;
    private JButton showMetadataButton;
    private JButton restoreListButton;
    private MosaicData md;
    private imgViewer applet;
    
    public SceneListDialog(final JFrame frame, final imgViewer applet, final MosaicData md) {
        super(frame, "Scene List", false);
        this.applet = applet;
        this.md = md;
        this.getContentPane().setLayout(new BorderLayout());
        final Sensor[] sensors = applet.getSensors();
        final SceneList[] array = new SceneList[sensors.length];
        for (int i = 0; i < sensors.length; ++i) {
            array[i] = sensors[i].sceneList;
        }
        this.sceneListList = new SceneListList(applet, this, this, applet.normalFont, 6, this, false, true, "Scene List", array, true);
        (this.buttonPanel = new JPanel()).setLayout(new GridBagLayout());
        (this.addButton = new JButton("Add")).setMnemonic(65);
        this.addButton.setToolTipText("Add scene to scene list");
        this.addButton.addActionListener(this);
        (this.deleteButton = new JButton("Delete")).setMnemonic(69);
        this.deleteButton.setToolTipText("Delete scene from scene list");
        this.deleteButton.addActionListener(this);
        (this.displayButton = new JButton("Display")).setMnemonic(73);
        this.displayButton.setToolTipText("Display scene");
        this.displayButton.addActionListener(this);
        (this.shopButton = new JButton("Send to Cart")).setMnemonic(83);
        this.shopButton.setToolTipText("Send scenes to Shopping Cart");
        this.shopButton.addActionListener(this);
        (this.showMetadataButton = new JButton("Show Metadata")).setMnemonic(77);
        this.showMetadataButton.setToolTipText("Show metadata");
        this.showMetadataButton.addActionListener(this);
        (this.showBrowseButton = new JButton("Show Browse")).setMnemonic(66);
        this.showBrowseButton.setToolTipText("Show browse");
        this.showBrowseButton.addActionListener(this);
        (this.closeButton = new JButton("Close")).setMnemonic(67);
        this.closeButton.setToolTipText("Close scene list");
        this.closeButton.addActionListener(this);
        (this.restoreListButton = new JButton("Restore")).setMnemonic(82);
        this.restoreListButton.setToolTipText("Restore scenes in scene list");
        this.restoreListButton.addActionListener(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.fill = 1;
        this.buttonPanel.add(this.addButton, gridBagConstraints);
        this.buttonPanel.add(this.deleteButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.buttonPanel.add(this.displayButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        this.buttonPanel.add(this.showMetadataButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.buttonPanel.add(this.showBrowseButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        this.buttonPanel.add(this.shopButton, gridBagConstraints);
        this.buttonPanel.add(this.restoreListButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        this.buttonPanel.add(this.closeButton, gridBagConstraints);
        this.getContentPane().add(this.sceneListList, "Center");
        this.getContentPane().add(this.buttonPanel, "South");
        this.setSize(420, 200);
        this.disableButtons();
        this.displayButton.setEnabled(false);
        this.restoreListButton.setEnabled(false);
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
        this.deleteButton.setEnabled(true);
        if (this.applet.sensorMenu.getCurrentSensor().isOrderable || this.applet.sensorMenu.getCurrentSensor().isDownloadable) {
            this.shopButton.setEnabled(true);
        }
        else {
            this.shopButton.setEnabled(false);
        }
        this.showBrowseButton.setEnabled(true);
        this.showMetadataButton.setEnabled(true);
    }
    
    private void disableButtons() {
        this.deleteButton.setEnabled(false);
        this.displayButton.setEnabled(false);
        this.shopButton.setEnabled(false);
        this.showBrowseButton.setEnabled(false);
        this.showMetadataButton.setEnabled(false);
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
    
    public void setSensor(final Sensor sensor) {
        this.sceneListList.setSensor(sensor);
        this.shopButton.setVisible(sensor.isOrderable || sensor.isDownloadable);
        this.restoreListButton.setVisible(sensor.isOrderable || sensor.isDownloadable);
        if (this.sceneListList.getSceneCount() > 0) {
            this.enableButtons();
        }
        else {
            this.disableButtons();
        }
        this.updateDisplayButton();
        this.restoreListButton.setEnabled(this.sceneListList.isRestoreEnabled());
    }
    
    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        this.updateDisplayButton();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Add")) {
            this.sceneListList.addActiveScene();
        }
        else if (actionCommand.equals("Delete")) {
            this.sceneListList.remove();
        }
        else if (actionCommand.equals("Display")) {
            this.sceneListList.showSelectedScene();
        }
        else if (actionCommand.equals("Send to Cart")) {
            this.sceneListList.order();
        }
        else if (actionCommand.equals("Show Browse")) {
            this.sceneListList.showBrowse(true);
        }
        else if (actionCommand.equals("Show Metadata")) {
            this.sceneListList.showBrowse(false);
        }
        else if (actionCommand.equals("Restore")) {
            this.sceneListList.restore();
        }
        else if (actionCommand.equals("Close")) {
            this.setVisible(false);
        }
    }
    
    public void updateForChangedSearchLimits() {
        this.updateDisplayButton();
    }
    
    private void updateDisplayButton() {
        final Metadata selectedScene = this.sceneListList.getSelectedScene();
        if (selectedScene != null) {
            this.applet.searchLimitDialog.applySearchLimits(selectedScene);
            this.displayButton.setEnabled(selectedScene.visible);
        }
        else {
            this.displayButton.setEnabled(false);
        }
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (observable == this.applet.md) {
            if (this.applet.md.getCurrentScene() != null) {
                this.addButton.setEnabled(true);
            }
            else {
                this.addButton.setEnabled(false);
            }
        }
    }
    
    @Override
    public void intervalAdded(final ListDataEvent listDataEvent) {
        this.enableButtons();
        this.restoreListButton.setEnabled(this.sceneListList.isRestoreEnabled());
    }
    
    @Override
    public void intervalRemoved(final ListDataEvent listDataEvent) {
        if (this.sceneListList.getSceneCount() > 0) {
            this.enableButtons();
        }
        else {
            this.disableButtons();
        }
        this.restoreListButton.setEnabled(this.sceneListList.isRestoreEnabled());
    }
    
    @Override
    public void contentsChanged(final ListDataEvent listDataEvent) {
    }
}

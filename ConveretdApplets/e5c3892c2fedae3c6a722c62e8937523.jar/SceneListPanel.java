import javax.swing.event.ListDataEvent;
import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListDataListener;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneListPanel extends JPanel implements ActionListener, Observer, ListEntryBuilder, ListDataListener, ListSelectionListener
{
    private imgViewer applet;
    private SceneListList sceneListList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton shopButton;
    
    SceneListPanel(final imgViewer applet) {
        this.applet = applet;
        this.setLayout(new GridBagLayout());
        final Sensor[] sensors = this.applet.getSensors();
        final SceneList[] array = new SceneList[sensors.length];
        for (int i = 0; i < sensors.length; ++i) {
            array[i] = sensors[i].sceneList;
        }
        this.sceneListList = new SceneListList(this.applet, this, this, this.applet.smallFont, 4, this, true, true, "Scene List", array, true);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        (this.addButton = new JButton("Add")).addActionListener(this);
        this.addButton.setMnemonic(65);
        this.addButton.setToolTipText("Add scene to scene list");
        this.addButton.setFont(applet.normalFont);
        this.addButton.setMargin(new Insets(2, 0, 2, 0));
        (this.deleteButton = new JButton("Delete")).setMnemonic(68);
        this.deleteButton.setToolTipText("Delete scene from scene list");
        this.deleteButton.addActionListener(this);
        this.deleteButton.setEnabled(false);
        this.deleteButton.setFont(applet.normalFont);
        this.deleteButton.setMargin(new Insets(2, 0, 2, 0));
        (this.shopButton = new JButton("Send to Cart")).setMnemonic(83);
        this.shopButton.setToolTipText("Send scenes to Shopping Cart");
        this.shopButton.addActionListener(this);
        this.shopButton.setEnabled(false);
        this.shopButton.setFont(applet.normalFont);
        this.shopButton.setMargin(new Insets(2, 0, 2, 0));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.fill = 1;
        panel.add(this.addButton, gridBagConstraints);
        panel.add(this.deleteButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.shopButton, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.weighty = 100.0;
        gridBagConstraints2.weightx = 100.0;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.gridwidth = 0;
        this.add(this.sceneListList, gridBagConstraints2);
        gridBagConstraints2.weighty = 0.0;
        this.add(panel, gridBagConstraints2);
        this.setSensor(this.applet.sensorMenu.getCurrentSensor());
        this.setMinimumSize(new Dimension(240, 115));
    }
    
    private void enableButtons() {
        this.deleteButton.setEnabled(true);
        if (this.sceneListList.getSelectedScene() != null && (this.applet.sensorMenu.getCurrentSensor().isOrderable || this.applet.sensorMenu.getCurrentSensor().isDownloadable)) {
            this.shopButton.setEnabled(true);
        }
        else {
            this.shopButton.setEnabled(false);
        }
    }
    
    private void disableButtons() {
        this.deleteButton.setEnabled(false);
        this.shopButton.setEnabled(false);
    }
    
    @Override
    public String getEntry(final Metadata metadata) {
        return metadata.getMediumEntityIDForDisplay();
    }
    
    public void setSensor(final Sensor sensor) {
        this.sceneListList.setSensor(sensor);
        if (this.sceneListList.getSceneCount() > 0) {
            this.enableButtons();
        }
        else {
            this.disableButtons();
        }
        this.shopButton.setVisible(sensor.isOrderable || sensor.isDownloadable);
    }
    
    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        this.updateOrderButton();
    }
    
    private void updateOrderButton() {
        if (this.sceneListList.getSelectedScene() != null && (this.applet.sensorMenu.getCurrentSensor().isOrderable || this.applet.sensorMenu.getCurrentSensor().isDownloadable)) {
            this.shopButton.setEnabled(true);
        }
        else {
            this.shopButton.setEnabled(false);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.applet.navDate.update(null, null);
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Add")) {
            this.sceneListList.addActiveScene();
        }
        else if (actionCommand.startsWith("Del")) {
            this.sceneListList.remove();
        }
        else if (actionCommand.equals("Send to Cart")) {
            this.sceneListList.order();
        }
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (observable == this.applet.md) {
            this.updateOrderButton();
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
    }
    
    @Override
    public void intervalRemoved(final ListDataEvent listDataEvent) {
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

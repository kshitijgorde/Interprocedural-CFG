import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridCellEntry extends JPanel implements ActionListener, Observer
{
    private JLabel gridTypeLabel;
    private JLabel gridColRowName;
    private FocusTextField gridColEditField;
    private FocusTextField gridRowEditField;
    private JButton goButton;
    private MosaicData md;
    private imgViewer applet;
    
    GridCellEntry(final imgViewer applet, final MosaicData md) {
        this.md = md;
        this.applet = applet;
        this.setLayout(new BoxLayout(this, 0));
        this.setFont(applet.normalFont);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        final NavigationModel navModel = applet.sensorMenu.getCurrentSensor().navModel;
        (this.gridTypeLabel = new JLabel(navModel.getModelName())).setFont(applet.boldFont);
        panel.add(this.gridTypeLabel);
        (this.gridColRowName = new JLabel(navModel.getColName() + " /" + navModel.getRowName() + ":")).setFont(applet.boldFont);
        panel.add(this.gridColRowName);
        this.add(panel);
        (this.gridColEditField = new FocusTextField(navModel.getColumnDigits())).setToolTipText(navModel.getColName() + " entry");
        this.gridColEditField.addActionListener(this);
        this.add(this.gridColEditField);
        (this.gridRowEditField = new FocusTextField(navModel.getRowDigits())).setToolTipText(navModel.getRowName() + " entry");
        this.gridRowEditField.addActionListener(this);
        this.add(this.gridRowEditField);
        (this.goButton = new JButton("Go")).setToolTipText(" Go to " + this.gridColRowName);
        this.goButton.addActionListener(this);
        this.add(this.goButton);
        final Dimension preferredSize = this.getPreferredSize();
        preferredSize.width = 100;
        this.setMinimumSize(preferredSize);
        preferredSize.width = 240;
        this.setMaximumSize(preferredSize);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.parseInput();
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final NavigationModel navModel = currentSensor.navModel;
        this.gridTypeLabel.setText(navModel.getModelName());
        final String text = new String(navModel.getColName() + " /" + navModel.getRowName() + ":");
        this.gridColRowName.setText(text);
        this.setVisible(!currentSensor.hideGridEntry);
        this.gridColEditField.setToolTipText(navModel.getColName() + " entry");
        this.gridRowEditField.setToolTipText(navModel.getRowName() + " entry");
        this.gridColEditField.setColumns(navModel.getColumnDigits());
        this.gridRowEditField.setColumns(navModel.getRowDigits());
        this.goButton.setToolTipText("Go to " + text);
        final TOC currentCell = this.md.getCurrentCell();
        final String columnString = navModel.getColumnString(currentCell.gridCol);
        final String rowString = navModel.getRowString(currentCell.gridRow);
        this.gridColEditField.setText(columnString);
        this.gridRowEditField.setText(rowString);
    }
    
    private void parseInput() {
        final NavigationModel navModel = this.applet.sensorMenu.getCurrentSensor().navModel;
        try {
            final int columnNumberFromString = navModel.getColumnNumberFromString(this.gridColEditField.getText());
            final int rowNumberFromString = navModel.getRowNumberFromString(this.gridRowEditField.getText());
            final int checkColumnBounds = navModel.checkColumnBounds(columnNumberFromString);
            final int checkRowBounds = navModel.checkRowBounds(rowNumberFromString);
            if (columnNumberFromString != checkColumnBounds) {
                this.applet.statusBar.showStatus(navModel.getColName() + " out of range!");
                this.gridColEditField.setText("");
            }
            else if (rowNumberFromString != checkRowBounds) {
                this.applet.statusBar.showStatus(navModel.getRowName() + " out of range!");
                this.gridRowEditField.setText("");
            }
            else if (!navModel.isValidGridCell(checkColumnBounds, checkRowBounds)) {
                this.applet.statusBar.showStatus(navModel.getColName() + "=" + columnNumberFromString + ", " + navModel.getRowName() + "=" + rowNumberFromString + " does not contain data!");
                this.gridColEditField.setText("");
                this.gridRowEditField.setText("");
            }
            else if (this.md.canMoveToMapArea(columnNumberFromString, rowNumberFromString)) {
                this.md.scrollData(columnNumberFromString, rowNumberFromString, 0, 0, false, true, false);
            }
        }
        catch (NumberFormatException ex) {
            this.applet.statusBar.showStatus("Illegal " + navModel.getColName() + "/" + navModel.getRowName() + " number format!");
        }
    }
}

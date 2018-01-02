import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShapeFileAttributesPanel extends JPanel implements ActionListener
{
    private CheckBoxPanel checkboxPanel;
    private JButton colorButton;
    private JComboBox activeAttribute;
    private AttributeFilterPanel attribFilterPanel;
    private JButton applyButton;
    private ShapeFileMapLayer mapLayer;
    private AttributeTable attributeTable;
    private String[] attributeNames;
    
    public ShapeFileAttributesPanel(final ShapeFileMapLayer mapLayer, final String[] attributeNames, final AttributeTable attributeTable) {
        this.mapLayer = mapLayer;
        this.attributeTable = attributeTable;
        this.setLayout(new BoxLayout(this, 0));
        this.add(this.checkboxPanel = new CheckBoxPanel("Displayed Attributes:"));
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        final JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Shape Color: "));
        (this.colorButton = new JButton("Change")).setActionCommand("SetColor");
        this.colorButton.setBackground(mapLayer.getColor());
        this.colorButton.setMnemonic(72);
        this.colorButton.setToolTipText("Change shape color");
        this.colorButton.addActionListener(this);
        panel2.add(this.colorButton);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(panel2);
        final JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Active Attribute:"));
        (this.activeAttribute = new JComboBox()).setActionCommand("ActiveAttribute");
        this.activeAttribute.addItem("None");
        this.activeAttribute.setToolTipText("Select status bar attribute");
        this.activeAttribute.addActionListener(this);
        panel3.add(this.activeAttribute);
        panel3.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(panel3);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 1));
        final JPanel panel5 = new JPanel();
        panel5.add(new JLabel("Only Display:"));
        panel4.add(panel5);
        panel4.add(this.attribFilterPanel = new AttributeFilterPanel(attributeNames));
        final JPanel panel6 = new JPanel();
        (this.applyButton = new JButton("Apply")).setMnemonic(65);
        this.applyButton.setToolTipText("Apply current filter");
        this.applyButton.addActionListener(this);
        panel6.add(this.applyButton);
        panel4.add(panel6);
        panel4.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(panel4);
        panel.add(new Box.Filler(new Dimension(5, 5), new Dimension(5, 32767), new Dimension(5, 32767)));
        this.add(panel);
        this.setAttributeNames(attributeNames);
        final Dimension minimumSize = this.checkboxPanel.getMinimumSize();
        minimumSize.height = 2000;
        this.checkboxPanel.setMaximumSize(minimumSize);
        this.selectLayerColor();
    }
    
    private void updateComboBox(final JComboBox comboBox, final String[] array, final boolean b) {
        final String selectedItem = (String)comboBox.getSelectedItem();
        for (int i = comboBox.getItemCount() - 1; i > 0; --i) {
            comboBox.removeItemAt(i);
        }
        for (int j = 0; j < array.length; ++j) {
            comboBox.addItem(array[j]);
        }
        if (b) {
            if (!selectedItem.equals("None")) {
                comboBox.setSelectedItem(selectedItem);
            }
            if (comboBox.getSelectedIndex() <= 0) {
                comboBox.setSelectedItem("NAME");
                if (comboBox.getSelectedIndex() <= 0) {
                    comboBox.setSelectedItem("FIRENAME");
                }
                if (comboBox.getSelectedIndex() <= 0) {
                    comboBox.setSelectedItem("None");
                }
            }
        }
        else {
            comboBox.setSelectedItem("None");
        }
    }
    
    private void setAttributeNames(final String[] array) {
        this.attributeNames = array;
        this.checkboxPanel.setCheckBoxes(array);
        this.checkboxPanel.addActionListener(this);
        this.updateComboBox(this.activeAttribute, array, true);
        if (this.attributeTable.getCurrentLayer() == null) {
            this.attributeTable.setAttributeNames(this.mapLayer, this.attributeNames, this.getSelectedArray());
        }
    }
    
    public void setAttributeValues(final String[] array) {
        final boolean[] selectedArray = this.getSelectedArray();
        this.attributeTable.setAttributeNames(this.mapLayer, this.attributeNames, selectedArray);
        this.attributeTable.setAttributeValues(this.mapLayer, array, selectedArray);
    }
    
    private boolean[] getSelectedArray() {
        return this.checkboxPanel.getSelectedArray();
    }
    
    private void selectLayerColor() {
        final Color showDialog = JColorChooser.showDialog(this, "Choose color for drawing shapes", this.mapLayer.getColor());
        if (showDialog != null) {
            this.mapLayer.setColor(showDialog);
            this.colorButton.setBackground(showDialog);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("ActiveAttribute")) {
            this.mapLayer.setActiveAttribute((String)this.activeAttribute.getSelectedItem());
        }
        else if (actionCommand.equals("Apply")) {
            this.attribFilterPanel.applyFilter();
        }
        else if (actionCommand.equals("SetColor")) {
            this.selectLayerColor();
        }
        else if (this.attributeTable.getCurrentLayer() == this.mapLayer) {
            this.attributeTable.updateVisible(this.getSelectedArray());
        }
    }
    
    class AttributeFilterPanel extends JPanel implements ActionListener
    {
        private JComboBox filterAttribute;
        private JTextField filterValue;
        
        AttributeFilterPanel(final String[] array) {
            (this.filterAttribute = new JComboBox()).setActionCommand("FilterAttribute");
            this.filterAttribute.setToolTipText("Select attribute for filtering");
            this.filterAttribute.addItem("None");
            for (int i = 0; i < array.length; ++i) {
                this.filterAttribute.addItem(array[i]);
            }
            this.filterAttribute.addActionListener(this);
            (this.filterValue = new JTextField("", 15)).setEnabled(false);
            this.filterValue.setToolTipText("Enter attribute value");
            this.filterValue.setActionCommand("Apply");
            this.filterValue.addActionListener(this);
            this.add(this.filterAttribute);
            this.add(new JLabel(" = "));
            this.add(this.filterValue);
        }
        
        public void applyFilter() {
            ShapeFileAttributesPanel.this.mapLayer.setFilter((String)this.filterAttribute.getSelectedItem(), this.filterValue.getText().trim());
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals("FilterAttribute")) {
                if (((String)this.filterAttribute.getSelectedItem()).equals("None")) {
                    this.filterValue.setText("");
                    this.filterValue.setEnabled(false);
                }
                else {
                    this.filterValue.setEnabled(true);
                }
                this.applyFilter();
            }
            else if (actionCommand.equals("Apply")) {
                this.applyFilter();
            }
        }
    }
}

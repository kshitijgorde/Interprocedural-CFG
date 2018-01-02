// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.util.JBLogger;
import com.objectbox.runner.beans.ICellRenderer;
import com.objectbox.runner.beans.ICellEditor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import com.objectbox.runner.model.JBProperties;
import com.objectbox.runner.beans.AbstrTableModel;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import com.sun.java.swing.BoxLayout;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Dialog;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import com.objectbox.gui.lwcomp.FlatButton;
import com.objectbox.gui.lwcomp.DoubleBufferPanel;
import com.objectbox.runner.model.PropTableModel;
import com.objectbox.runner.beans.TableView;
import java.awt.event.ActionListener;
import com.objectbox.runner.model.JBPropertyEditor;
import java.awt.Panel;

public class AppletPropertiesEditor extends Panel implements JBPropertyEditor, ActionListener
{
    private TableView ivjTableViewProps;
    private PropTableModel ivjPropTableModel1;
    private int yPos;
    private int xPos;
    private DoubleBufferPanel ivjDoubleBufferPanelMenu;
    private FlatButton ivjFlatButtonDelete;
    private FlatButton ivjFlatButtonNew;
    private CheckboxGroup ivjCheckboxGroup1;
    private Checkbox ivjCheckboxParameter;
    private Checkbox ivjCheckboxProperty;
    private DoubleBufferPanel ivjDoubleBufferPanelMenu1;
    private Panel ivjEditorContentsPane;
    private FlatButton ivjFlatButtonCancel;
    private FlatButton ivjFlatButtonOk;
    private Dialog dialog;
    private boolean dialogSvar;
    private Label ivjLabel1;
    private Label ivjLabelName;
    private Label ivjLabelValue;
    private TextField ivjTextFieldName;
    private TextField ivjTextFieldValue;
    private Panel ivjTableViewPanel;
    private BoxLayout ivjDoubleBufferPanelMenuBoxLayout;
    
    public AppletPropertiesEditor() {
        this.ivjTableViewProps = null;
        this.ivjPropTableModel1 = null;
        this.yPos = 0;
        this.xPos = 1;
        this.ivjDoubleBufferPanelMenu = null;
        this.ivjFlatButtonDelete = null;
        this.ivjFlatButtonNew = null;
        this.ivjCheckboxGroup1 = null;
        this.ivjCheckboxParameter = null;
        this.ivjCheckboxProperty = null;
        this.ivjDoubleBufferPanelMenu1 = null;
        this.ivjEditorContentsPane = null;
        this.ivjFlatButtonCancel = null;
        this.ivjFlatButtonOk = null;
        this.dialog = null;
        this.dialogSvar = false;
        this.ivjLabel1 = null;
        this.ivjLabelName = null;
        this.ivjLabelValue = null;
        this.ivjTextFieldName = null;
        this.ivjTextFieldValue = null;
        this.ivjTableViewPanel = null;
        this.ivjDoubleBufferPanelMenuBoxLayout = null;
        this.initialize();
    }
    
    public AppletPropertiesEditor(final LayoutManager layoutManager) {
        super(layoutManager);
        this.ivjTableViewProps = null;
        this.ivjPropTableModel1 = null;
        this.yPos = 0;
        this.xPos = 1;
        this.ivjDoubleBufferPanelMenu = null;
        this.ivjFlatButtonDelete = null;
        this.ivjFlatButtonNew = null;
        this.ivjCheckboxGroup1 = null;
        this.ivjCheckboxParameter = null;
        this.ivjCheckboxProperty = null;
        this.ivjDoubleBufferPanelMenu1 = null;
        this.ivjEditorContentsPane = null;
        this.ivjFlatButtonCancel = null;
        this.ivjFlatButtonOk = null;
        this.dialog = null;
        this.dialogSvar = false;
        this.ivjLabel1 = null;
        this.ivjLabelName = null;
        this.ivjLabelValue = null;
        this.ivjTextFieldName = null;
        this.ivjTextFieldValue = null;
        this.ivjTableViewPanel = null;
        this.ivjDoubleBufferPanelMenuBoxLayout = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getFlatButtonNew()) {
            this.connEtoC1(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonDelete()) {
            this.connEtoC2(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonCancel()) {
            this.connEtoC3(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonOk()) {
            this.connEtoC4(actionEvent);
        }
    }
    
    private void connEtoC1(final ActionEvent actionEvent) {
        try {
            this.flatButtonNew_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC2(final ActionEvent actionEvent) {
        try {
            this.flatButtonDelete_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC3(final ActionEvent actionEvent) {
        try {
            this.flatButtonCancel_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC4(final ActionEvent actionEvent) {
        try {
            this.flatButtonOk_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP1SetTarget() {
        try {
            this.getTableViewProps().setModel(this.getPropTableModel1());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP2SetTarget() {
        try {
            this.getCheckboxParameter().setCheckboxGroup(this.getCheckboxGroup1());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP3SetTarget() {
        try {
            this.getCheckboxProperty().setCheckboxGroup(this.getCheckboxGroup1());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP4SetTarget() {
        try {
            this.getCheckboxGroup1().setSelectedCheckbox(this.getCheckboxParameter());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void edit(final JBProperties data) {
        this.getPropTableModel1().setData(data);
        this.getTableViewProps().setModel(this.getPropTableModel1());
    }
    
    public void flatButtonCancel_ActionPerformed(final ActionEvent actionEvent) {
        this.dialogSvar = false;
        this.getDialog().setVisible(false);
    }
    
    public void flatButtonClose_ActionPerformed(final ActionEvent actionEvent) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).dispose();
    }
    
    public void flatButtonDelete_ActionPerformed(final ActionEvent actionEvent) {
        this.getPropTableModel1().delete(this.getTableViewProps().getCurrent().y);
        this.getTableViewProps().setModel(this.getPropTableModel1());
    }
    
    public void flatButtonNew_ActionPerformed(final ActionEvent actionEvent) {
        this.getDialog().setSize(300, 200);
        this.getDialog().setLocation(this.getLocationOnScreen().x + 20, this.getLocationOnScreen().y + 100);
        this.getTextFieldName().setText("");
        this.getTextFieldValue().setText("");
        this.getCheckboxGroup1().setSelectedCheckbox(this.getCheckboxParameter());
        this.getDialog().setVisible(true);
        this.getDialog().setVisible(false);
        if (this.dialogSvar) {
            final String text = this.getTextFieldName().getText();
            final String text2 = this.getTextFieldValue().getText();
            if (this.getCheckboxParameter().getState()) {
                this.getPropTableModel1().newParameter(text, text2);
            }
            else {
                this.getPropTableModel1().newProperty(text, text2);
            }
            this.getTableViewProps().setModel(this.getPropTableModel1());
            this.getTableViewProps().autoFitAll();
            this.getTableViewProps().repaint();
        }
    }
    
    public void flatButtonOk_ActionPerformed(final ActionEvent actionEvent) {
        this.dialogSvar = true;
        this.getDialog().setVisible(false);
    }
    
    private CheckboxGroup getCheckboxGroup1() {
        if (this.ivjCheckboxGroup1 == null) {
            try {
                this.ivjCheckboxGroup1 = new CheckboxGroup();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxGroup1;
    }
    
    private Checkbox getCheckboxParameter() {
        if (this.ivjCheckboxParameter == null) {
            try {
                (this.ivjCheckboxParameter = new Checkbox()).setName("CheckboxParameter");
                this.ivjCheckboxParameter.setLabel("Parameter");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxParameter;
    }
    
    private Checkbox getCheckboxProperty() {
        if (this.ivjCheckboxProperty == null) {
            try {
                (this.ivjCheckboxProperty = new Checkbox()).setName("CheckboxProperty");
                this.ivjCheckboxProperty.setLabel("Property");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxProperty;
    }
    
    private Dialog getDialog() {
        if (this.dialog == null) {
            try {
                Container container;
                for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
                final Frame frame = new Frame();
                (this.dialog = new Dialog((Frame)container, true)).setName("Editor");
                this.dialog.setLayout(new BorderLayout());
                this.dialog.setModal(true);
                this.getDialog().add(this.getEditorContentsPane(), "Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.dialog;
    }
    
    private DoubleBufferPanel getDoubleBufferPanelMenu() {
        if (this.ivjDoubleBufferPanelMenu == null) {
            try {
                (this.ivjDoubleBufferPanelMenu = new DoubleBufferPanel()).setName("DoubleBufferPanelMenu");
                this.ivjDoubleBufferPanelMenu.setLayout(this.getDoubleBufferPanelMenuBoxLayout());
                this.ivjDoubleBufferPanelMenu.setBackground(new Color(193, 195, 239));
                this.getDoubleBufferPanelMenu().add(this.getFlatButtonNew(), this.getFlatButtonNew().getName());
                this.getDoubleBufferPanelMenu().add(this.getFlatButtonDelete(), this.getFlatButtonDelete().getName());
                this.ivjDoubleBufferPanelMenu.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjDoubleBufferPanelMenu;
    }
    
    private DoubleBufferPanel getDoubleBufferPanelMenu1() {
        if (this.ivjDoubleBufferPanelMenu1 == null) {
            try {
                (this.ivjDoubleBufferPanelMenu1 = new DoubleBufferPanel()).setName("DoubleBufferPanelMenu1");
                this.ivjDoubleBufferPanelMenu1.setLayout(new FlowLayout());
                this.ivjDoubleBufferPanelMenu1.setBackground(new Color(193, 195, 239));
                this.ivjDoubleBufferPanelMenu1.setHasframe(false);
                this.getDoubleBufferPanelMenu1().add(this.getFlatButtonOk(), this.getFlatButtonOk().getName());
                this.getDoubleBufferPanelMenu1().add(this.getFlatButtonCancel(), this.getFlatButtonCancel().getName());
                this.ivjDoubleBufferPanelMenu1.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjDoubleBufferPanelMenu1;
    }
    
    private BoxLayout getDoubleBufferPanelMenuBoxLayout() {
        BoxLayout boxLayout = null;
        try {
            boxLayout = new BoxLayout(this.getDoubleBufferPanelMenu(), 1);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return boxLayout;
    }
    
    private Panel getEditorContentsPane() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        if (this.ivjEditorContentsPane == null) {
            try {
                (this.ivjEditorContentsPane = new Panel()).setName("EditorContentsPane");
                this.ivjEditorContentsPane.setLayout(new GridBagLayout());
                this.ivjEditorContentsPane.setBackground(new Color(193, 195, 239));
                this.ivjEditorContentsPane.setBounds(377, 50, 309, 199);
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 3;
                gridBagConstraints.gridwidth = 4;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 10;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.insets = new Insets(10, 10, 10, 10);
                this.getEditorContentsPane().add(this.getDoubleBufferPanelMenu1(), gridBagConstraints);
                gridBagConstraints2.gridx = 0;
                gridBagConstraints2.gridy = 1;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.anchor = 17;
                gridBagConstraints2.weightx = 0.0;
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.insets = new Insets(10, 10, 0, 0);
                this.getEditorContentsPane().add(this.getLabelName(), gridBagConstraints2);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 2;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.anchor = 16;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
                this.getEditorContentsPane().add(this.getLabelValue(), gridBagConstraints3);
                gridBagConstraints4.gridx = 2;
                gridBagConstraints4.gridy = 1;
                gridBagConstraints4.gridwidth = 2;
                gridBagConstraints4.gridheight = 1;
                gridBagConstraints4.fill = 1;
                gridBagConstraints4.anchor = 10;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                gridBagConstraints4.insets = new Insets(10, 0, 0, 10);
                this.getEditorContentsPane().add(this.getTextFieldName(), gridBagConstraints4);
                gridBagConstraints5.gridx = 2;
                gridBagConstraints5.gridy = 2;
                gridBagConstraints5.gridwidth = 2;
                gridBagConstraints5.gridheight = 1;
                gridBagConstraints5.fill = 2;
                gridBagConstraints5.anchor = 10;
                gridBagConstraints5.weightx = 0.0;
                gridBagConstraints5.weighty = 0.0;
                gridBagConstraints5.insets = new Insets(0, 0, 0, 10);
                this.getEditorContentsPane().add(this.getTextFieldValue(), gridBagConstraints5);
                gridBagConstraints6.gridx = 0;
                gridBagConstraints6.gridy = 0;
                gridBagConstraints6.gridwidth = 1;
                gridBagConstraints6.gridheight = 1;
                gridBagConstraints6.anchor = 17;
                gridBagConstraints6.weightx = 0.0;
                gridBagConstraints6.weighty = 0.0;
                gridBagConstraints6.insets = new Insets(0, 10, 0, 0);
                this.getEditorContentsPane().add(this.getLabel1(), gridBagConstraints6);
                gridBagConstraints7.gridx = 2;
                gridBagConstraints7.gridy = 0;
                gridBagConstraints7.gridwidth = 1;
                gridBagConstraints7.gridheight = 1;
                gridBagConstraints7.anchor = 10;
                gridBagConstraints7.weightx = 0.0;
                gridBagConstraints7.weighty = 0.0;
                this.getEditorContentsPane().add(this.getCheckboxParameter(), gridBagConstraints7);
                gridBagConstraints8.gridx = 3;
                gridBagConstraints8.gridy = 0;
                gridBagConstraints8.gridwidth = 1;
                gridBagConstraints8.gridheight = 1;
                gridBagConstraints8.anchor = 10;
                gridBagConstraints8.weightx = 0.0;
                gridBagConstraints8.weighty = 0.0;
                this.getEditorContentsPane().add(this.getCheckboxProperty(), gridBagConstraints8);
                this.getEditorContentsPane().setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjEditorContentsPane;
    }
    
    private FlatButton getFlatButtonCancel() {
        if (this.ivjFlatButtonCancel == null) {
            try {
                (this.ivjFlatButtonCancel = new FlatButton()).setName("FlatButtonCancel");
                this.ivjFlatButtonCancel.setFixedsize(new Dimension(70, 35));
                this.ivjFlatButtonCancel.setLabel("Cancel");
                this.ivjFlatButtonCancel.setImageResource("/images/cancel.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonCancel;
    }
    
    private FlatButton getFlatButtonDelete() {
        if (this.ivjFlatButtonDelete == null) {
            try {
                (this.ivjFlatButtonDelete = new FlatButton()).setName("FlatButtonDelete");
                this.ivjFlatButtonDelete.setFixedsize(new Dimension(70, 50));
                this.ivjFlatButtonDelete.setLabel("Delete");
                this.ivjFlatButtonDelete.setImageResource("/images/delete.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonDelete;
    }
    
    private FlatButton getFlatButtonNew() {
        if (this.ivjFlatButtonNew == null) {
            try {
                (this.ivjFlatButtonNew = new FlatButton()).setName("FlatButtonNew");
                this.ivjFlatButtonNew.setFixedsize(new Dimension(70, 50));
                this.ivjFlatButtonNew.setLabel("New");
                this.ivjFlatButtonNew.setImageResource("/images/File.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonNew;
    }
    
    private FlatButton getFlatButtonOk() {
        if (this.ivjFlatButtonOk == null) {
            try {
                (this.ivjFlatButtonOk = new FlatButton()).setName("FlatButtonOk");
                this.ivjFlatButtonOk.setFixedsize(new Dimension(70, 35));
                this.ivjFlatButtonOk.setLabel("Ok");
                this.ivjFlatButtonOk.setImageResource("/images/ok.gif", 3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonOk;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setText("New:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel1;
    }
    
    private Label getLabelName() {
        if (this.ivjLabelName == null) {
            try {
                (this.ivjLabelName = new Label()).setName("LabelName");
                this.ivjLabelName.setText("Name:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelName;
    }
    
    private Label getLabelValue() {
        if (this.ivjLabelValue == null) {
            try {
                (this.ivjLabelValue = new Label()).setName("LabelValue");
                this.ivjLabelValue.setText("Value:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelValue;
    }
    
    private PropTableModel getPropTableModel1() {
        if (this.ivjPropTableModel1 == null) {
            try {
                this.ivjPropTableModel1 = new PropTableModel();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPropTableModel1;
    }
    
    private Panel getTableViewPanel() {
        if (this.ivjTableViewPanel == null) {
            try {
                (this.ivjTableViewPanel = new Panel()).setName("TableViewPanel");
                this.ivjTableViewPanel.setLayout(new BorderLayout());
                this.getTableViewPanel().add(this.getTableViewProps(), "Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTableViewPanel;
    }
    
    private TableView getTableViewProps() {
        if (this.ivjTableViewProps == null) {
            try {
                (this.ivjTableViewProps = new TableView()).setName("TableViewProps");
                this.ivjTableViewProps.setCellheight(28);
                this.ivjTableViewProps.setBackground(Color.white);
                this.ivjTableViewProps.setCellwidth(160);
                this.ivjTableViewProps.setHeaderforeground(Color.white);
                this.ivjTableViewProps.setAutofit(true);
                this.ivjTableViewProps.setHeaderbackground(new Color(46, 55, 216));
                this.ivjTableViewProps.setNumberOfcols(2);
                if (JBee.OS_type == 1) {
                    this.ivjTableViewProps.setCellheight(18);
                }
                this.ivjTableViewProps.setCelleditor(new PropertyEditor());
                this.ivjTableViewProps.setCellrenderer(new CellRenderer());
                this.ivjTableViewProps.autoFitAll();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTableViewProps;
    }
    
    private TextField getTextFieldName() {
        if (this.ivjTextFieldName == null) {
            try {
                (this.ivjTextFieldName = new TextField()).setName("TextFieldName");
                this.ivjTextFieldName.setBackground(Color.white);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldName;
    }
    
    private TextField getTextFieldValue() {
        if (this.ivjTextFieldValue == null) {
            try {
                (this.ivjTextFieldValue = new TextField()).setName("TextFieldValue");
                this.ivjTextFieldValue.setBackground(Color.white);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldValue;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.getFlatButtonNew().addActionListener(this);
        this.getFlatButtonDelete().addActionListener(this);
        this.getFlatButtonCancel().addActionListener(this);
        this.getFlatButtonOk().addActionListener(this);
        this.connPtoP1SetTarget();
        this.connPtoP2SetTarget();
        this.connPtoP3SetTarget();
        this.connPtoP4SetTarget();
    }
    
    private void initialize() {
        this.setName("AppletPropertiesEditor");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.setSize(324, 400);
        this.add(this.getDoubleBufferPanelMenu(), "West");
        this.add(this.getTableViewPanel(), "Center");
        this.initConnections();
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final AppletPropertiesEditor appletPropertiesEditor = new AppletPropertiesEditor();
            frame.add("Center", appletPropertiesEditor);
            frame.setSize(appletPropertiesEditor.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    public void updateSize(final Dimension dimension) {
        JBLogger.log("NewSize: " + dimension);
        JBLogger.log("Parent: " + this.getParent().getSize() + this.getParent().getName());
        JBLogger.log("This: " + this.getSize() + this.getName());
        JBLogger.log("tableview: " + this.getTableViewProps().getSize());
    }
}

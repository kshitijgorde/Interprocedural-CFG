// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import com.objectbox.runner.model.JBProperties;
import com.objectbox.runner.model.SecurityManagerIF;
import com.objectbox.runner.model.JBSecurityModel;
import java.awt.event.ItemEvent;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import com.objectbox.runner.gui.tree.Node;
import com.objectbox.runner.util.JBLogger;
import java.awt.Component;
import java.util.Hashtable;
import com.objectbox.gui.lwcomp.JBPopupMenu;
import java.awt.event.ActionEvent;
import com.objectbox.runner.beans.TableView;
import com.objectbox.runner.model.PropTableModel;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import com.objectbox.runner.beans.ICellEditor;

public class PropertyEditor implements ICellEditor, ItemListener, ActionListener, ComponentListener
{
    Choice security_choice;
    Choice windowtype_choice;
    TextField default_editor;
    TextField name_editor;
    PropTableModel propertymodel;
    TableView tableview;
    
    public PropertyEditor() {
        this.security_choice = new Choice();
        this.windowtype_choice = new Choice();
        this.default_editor = new TextField();
        this.name_editor = new TextField();
        this.propertymodel = null;
        this.tableview = null;
        this.default_editor.addActionListener(this);
        this.default_editor.addComponentListener(this);
        this.name_editor.addActionListener(this);
        this.name_editor.addComponentListener(this);
        this.security_choice.addItemListener(this);
        this.windowtype_choice.addItemListener(this);
        this.security_choice.addItem("High");
        this.security_choice.addItem("Medium");
        this.security_choice.addItem("Low");
        this.windowtype_choice.addItem("Small");
        this.windowtype_choice.addItem("Standard");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.tableview != null && this.propertymodel != null) {
                if (actionEvent.getSource() == this.default_editor) {
                    this.propertymodel.setCell(this.tableview.getCurrentCol(), this.tableview.getCurrentRow(), this.default_editor.getText());
                }
                else if (actionEvent.getSource() == this.name_editor) {
                    this.propertymodel.setCell(this.tableview.getCurrentCol(), this.tableview.getCurrentRow(), this.name_editor.getText());
                    final JBManagerPanel jbManagerPanel = (JBManagerPanel)AppRegistry.getInstance().lookup("Manager");
                    final Node selectedNode = jbManagerPanel.getTreeBasePublic().getSelectedNode();
                    if (selectedNode.getType().equals("App")) {
                        jbManagerPanel.getTreeBasePublic().getSelectedNode().setText(this.name_editor.getText());
                        jbManagerPanel.updateVisual();
                        ((JBPopupMenu)((Hashtable)AppRegistry.getInstance().lookup("nodehash")).get(selectedNode)).setItemText(this.name_editor.getText(), selectedNode);
                    }
                }
            }
            this.tableview.remove((Component)actionEvent.getSource());
        }
        catch (Throwable t) {
            JBLogger.log("Exception i PropertyEditor::actionPerformed: " + t);
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
        if (this.tableview != null && this.propertymodel != null) {
            if (componentEvent.getSource() == this.default_editor) {
                this.propertymodel.setCell(this.tableview.getCurrentCol(), this.tableview.getCurrentRow(), this.default_editor.getText());
            }
            else if (componentEvent.getSource() == this.name_editor) {
                this.propertymodel.setCell(this.tableview.getCurrentCol(), this.tableview.getCurrentRow(), this.name_editor.getText());
                final JBManagerPanel jbManagerPanel = (JBManagerPanel)AppRegistry.getInstance().lookup("Manager");
                if (jbManagerPanel.getTreeBasePublic().getSelectedNode().getType().equals("App")) {
                    jbManagerPanel.getTreeBasePublic().getSelectedNode().setText(this.name_editor.getText());
                    jbManagerPanel.updateVisual();
                }
            }
        }
        this.tableview.remove((Component)componentEvent.getSource());
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public boolean hasEditor(final TableView tableView) {
        return tableView.getCurrentCol() != 0;
    }
    
    public void invokeEditor(final TableView tableview, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (n == 0) {
            return;
        }
        this.tableview = tableview;
        this.propertymodel = (PropTableModel)tableview.getModel();
        final String cell = this.propertymodel.getCell(n, n2);
        if (this.propertymodel.getCell(2, n2).compareTo("jbprops") == 0) {
            final String cell2 = this.propertymodel.getCell(0, n2);
            if (cell2.equals("Security")) {
                this.security_choice.setLocation(n3 - tableview.getTranslate().x, n4 - tableview.getTranslate().y);
                this.security_choice.setSize(new Dimension(n5 - n3, n6 - n4));
                tableview.add(this.security_choice);
                tableview.setActiveEditor(this.security_choice);
                this.security_choice.setVisible(true);
                this.security_choice.select(cell);
                this.security_choice.requestFocus();
                return;
            }
            if (cell2.equals("Window type")) {
                this.windowtype_choice.setLocation(n3 - tableview.getTranslate().x, n4 - tableview.getTranslate().y);
                this.windowtype_choice.setSize(new Dimension(n5 - n3, n6 - n4));
                tableview.add(this.windowtype_choice);
                tableview.setActiveEditor(this.windowtype_choice);
                this.windowtype_choice.setVisible(true);
                this.windowtype_choice.select(cell);
                this.windowtype_choice.requestFocus();
                return;
            }
            if (cell2.equals("Name")) {
                this.name_editor.setText(tableview.getModel().getCell(n, n2));
                this.name_editor.setLocation(n3 - tableview.getTranslate().x, n4 - tableview.getTranslate().y);
                this.name_editor.setSize(new Dimension(n5 - n3, n6 - n4));
                tableview.add(this.name_editor);
                tableview.setActiveEditor(this.name_editor);
                this.name_editor.setVisible(true);
                this.name_editor.selectAll();
                this.name_editor.requestFocus();
                this.name_editor.validate();
            }
            else {
                this.default_editor.setText(tableview.getModel().getCell(n, n2));
                this.default_editor.setLocation(n3 - tableview.getTranslate().x, n4 - tableview.getTranslate().y);
                this.default_editor.setSize(new Dimension(n5 - n3, n6 - n4));
                tableview.add(this.default_editor);
                tableview.setActiveEditor(this.default_editor);
                this.default_editor.setVisible(true);
                this.default_editor.selectAll();
                this.default_editor.requestFocus();
                this.default_editor.validate();
            }
        }
        else {
            this.default_editor.setText(tableview.getModel().getCell(n, n2));
            this.default_editor.setLocation(n3 - tableview.getTranslate().x, n4 - tableview.getTranslate().y);
            this.default_editor.setSize(new Dimension(n5 - n3, n6 - n4));
            tableview.add(this.default_editor);
            tableview.setActiveEditor(this.default_editor);
            this.default_editor.setVisible(true);
            this.default_editor.selectAll();
            this.default_editor.requestFocus();
            this.default_editor.validate();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.tableview != null && this.propertymodel != null) {
            final Object source = itemEvent.getSource();
            if (source == this.security_choice) {
                this.propertymodel.setCell(this.tableview.getCurrentCol(), this.tableview.getCurrentRow(), this.security_choice.getSelectedItem());
                final int selectedIndex = this.security_choice.getSelectedIndex();
                final JBProperties data = ((PropTableModel)this.tableview.getModel()).getData();
                ((JBee)AppRegistry.getInstance().lookup("JBee")).setSecurity("-" + data.getProps().getProperty("codebase").hashCode() + data.getProps().getProperty("documentbase").hashCode() + data.getProps().getProperty("code").hashCode(), JBSecurityModel.getSecurityModel(selectedIndex + 1), (JBManagerPanel)AppRegistry.getInstance().lookup("Manager"));
            }
            else if (source == this.windowtype_choice) {
                this.propertymodel.setCell(this.tableview.getCurrentCol(), this.tableview.getCurrentRow(), this.windowtype_choice.getSelectedItem());
            }
            this.tableview.remove((Component)source);
        }
    }
}

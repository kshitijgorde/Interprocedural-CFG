// 
// Decompiled by Procyon v0.5.30
// 

package org.nis.nutracalc;

import org.nis.nutracalc.nutrasmart.NutritionData;
import java.util.Iterator;
import javax.swing.tree.TreePath;
import javax.swing.tree.MutableTreeNode;
import java.awt.Container;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.SpringLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.util.List;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JApplet;

public class NutraCalc extends JApplet
{
    public static final String VERSION = "2.0.0";
    private JComboBox categoryList;
    private JComboBox itemList;
    private JPanel substitutionList;
    private JCheckBox[] substitutionCheckBoxes;
    private JScrollPane substitutionListView;
    private JButton addButton;
    private JTree mealTree;
    private JScrollPane mealView;
    private JButton removeButton;
    private NutritionLabel itemNutrition;
    private JScrollPane nutritionView;
    private AppData appData;
    private List<MealItem> meal;
    private Item selectedItem;
    
    public void init() {
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception ex) {}
            this.appData = new AppData();
            final JLabel categoryLabel = new JLabel("Category:");
            this.categoryList = new JComboBox((Vector<E>)this.appData.getCategories());
            final JLabel itemLabel = new JLabel("Item:");
            this.itemList = new JComboBox((ComboBoxModel<E>)new DefaultComboBoxModel<Object>((Vector<E>)this.appData.getItems(this.appData.getCategories().get(0))));
            final JLabel substitutionLabel = new JLabel("Item Substitutions:");
            this.substitutionList = new JPanel();
            this.substitutionListView = new JScrollPane(this.substitutionList, 22, 30);
            this.addButton = new JButton("Add item to meal");
            final JLabel mealLabel = new JLabel("Meal Contents:");
            this.mealTree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode("Meal")));
            this.mealTree.getSelectionModel().setSelectionMode(1);
            this.mealTree.setShowsRootHandles(true);
            this.mealView = new JScrollPane(this.mealTree, 22, 30);
            this.removeButton = new JButton("Remove item from meal");
            final JLabel itemDataLabel = new JLabel("Nutrition Facts:");
            this.itemNutrition = new NutritionLabel();
            this.nutritionView = new JScrollPane(this.itemNutrition, 22, 30);
            this.categoryList.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    NutraCalc.this.setSelectedCategory((String)((JComboBox)e.getSource()).getSelectedItem());
                }
            });
            this.itemList.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    NutraCalc.this.setSelectedItem((Item)((JComboBox)e.getSource()).getSelectedItem());
                }
            });
            this.addButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    NutraCalc.this.addItem();
                }
            });
            this.removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    NutraCalc.this.removeItem();
                }
            });
            this.mealTree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(final TreeSelectionEvent e) {
                    NutraCalc.this.updateNutritionData();
                }
            });
            final SpringLayout layout = new SpringLayout();
            final Container contentPane = this.getContentPane();
            this.setLayout(layout);
            this.add(categoryLabel);
            this.add(this.categoryList);
            this.add(itemLabel);
            this.add(this.itemList);
            this.add(substitutionLabel);
            this.add(this.substitutionListView);
            this.add(this.addButton);
            this.add(mealLabel);
            this.add(this.mealView);
            this.add(this.removeButton);
            this.add(itemDataLabel);
            this.add(this.nutritionView);
            this.substitutionListView.setPreferredSize(new Dimension(230, this.nutritionView.getPreferredSize().height - this.categoryList.getPreferredSize().height - this.itemList.getPreferredSize().height - substitutionLabel.getPreferredSize().height - this.addButton.getPreferredSize().height - 30));
            this.mealView.setPreferredSize(new Dimension(250, this.nutritionView.getPreferredSize().height - this.itemList.getPreferredSize().height - mealLabel.getPreferredSize().height - this.removeButton.getPreferredSize().height - 30));
            this.nutritionView.setPreferredSize(new Dimension(250, this.nutritionView.getPreferredSize().height));
            layout.putConstraint("West", categoryLabel, 5, "West", contentPane);
            layout.putConstraint("North", categoryLabel, 5, "North", contentPane);
            layout.putConstraint("West", this.categoryList, 5, "East", categoryLabel);
            layout.putConstraint("North", this.categoryList, 5, "North", contentPane);
            layout.putConstraint("West", itemLabel, 5, "West", contentPane);
            layout.putConstraint("North", itemLabel, 5, "South", this.categoryList);
            layout.putConstraint("West", this.itemList, 5, "East", itemLabel);
            layout.putConstraint("North", this.itemList, 5, "South", this.categoryList);
            layout.putConstraint("West", substitutionLabel, 5, "West", contentPane);
            layout.putConstraint("North", substitutionLabel, 5, "South", this.itemList);
            layout.putConstraint("West", this.substitutionListView, 5, "West", contentPane);
            layout.putConstraint("North", this.substitutionListView, 5, "South", substitutionLabel);
            layout.putConstraint("South", this.substitutionListView, -5, "North", this.addButton);
            layout.putConstraint("West", this.addButton, 5, "West", contentPane);
            layout.putConstraint("South", this.addButton, -5, "South", contentPane);
            layout.putConstraint("West", mealLabel, 5, "East", this.substitutionListView);
            layout.putConstraint("North", mealLabel, 5, "South", this.itemList);
            layout.putConstraint("West", this.mealView, 5, "East", this.substitutionListView);
            layout.putConstraint("North", this.mealView, 5, "South", mealLabel);
            layout.putConstraint("South", this.mealView, -5, "North", this.removeButton);
            layout.putConstraint("West", this.removeButton, 5, "East", this.substitutionListView);
            layout.putConstraint("South", this.removeButton, -5, "South", contentPane);
            layout.putConstraint("West", itemDataLabel, 5, "East", this.mealView);
            layout.putConstraint("North", itemDataLabel, 5, "North", contentPane);
            layout.putConstraint("West", this.nutritionView, 5, "East", this.mealView);
            layout.putConstraint("North", this.nutritionView, 5, "South", itemDataLabel);
            layout.putConstraint("East", contentPane, 5, "East", this.nutritionView);
            layout.putConstraint("South", contentPane, 5, "South", this.nutritionView);
            this.selectedItem = null;
            this.meal = new ArrayList<MealItem>();
            this.setSize(750, 400);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "A critical error occurred: " + e.getMessage(), "Critical Error", 0);
            e.printStackTrace();
        }
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    private void setSelectedCategory(final String category) {
        this.itemList.setModel(new DefaultComboBoxModel<Item>(this.appData.getItems(category)));
        this.setSelectedItem((Item)this.itemList.getModel().getSelectedItem());
    }
    
    private void setSelectedItem(final Item item) {
        this.selectedItem = item;
        this.substitutionList.removeAll();
        final SpringLayout layout = new SpringLayout();
        this.substitutionList.setLayout(layout);
        this.substitutionCheckBoxes = new JCheckBox[item.getSubstitutions().length];
        for (int i = 0; i < item.getSubstitutions().length; ++i) {
            this.substitutionCheckBoxes[i] = new JCheckBox(item.getSubstitutions()[i].toString());
            this.substitutionList.add(this.substitutionCheckBoxes[i]);
            if (i == 0) {
                layout.putConstraint("West", this.substitutionCheckBoxes[i], 5, "West", this.substitutionList);
                layout.putConstraint("North", this.substitutionCheckBoxes[i], 5, "North", this.substitutionList);
            }
            else {
                layout.putConstraint("West", this.substitutionCheckBoxes[i], 5, "West", this.substitutionList);
                layout.putConstraint("North", this.substitutionCheckBoxes[i], 5, "South", this.substitutionCheckBoxes[i - 1]);
            }
            if (i + 1 == item.getSubstitutions().length) {
                layout.putConstraint("South", this.substitutionList, 5, "South", this.substitutionCheckBoxes[i]);
            }
        }
        if (item.getSubstitutions().length == 0) {
            final JLabel none = new JLabel("No substitutions for this item");
            this.substitutionList.add(none);
            layout.putConstraint("West", none, 5, "West", this.substitutionList);
            layout.putConstraint("North", none, 5, "North", this.substitutionList);
        }
        final Dimension size;
        final Dimension d = size = this.substitutionList.getSize();
        ++size.height;
        this.substitutionList.setSize(d);
        final Dimension dimension = d;
        --dimension.height;
        this.substitutionList.setSize(d);
        this.updateNutritionData();
    }
    
    private void addItem() {
        if (this.selectedItem != null) {
            final List<Item> substitutions = new ArrayList<Item>();
            for (int i = 0; i < this.substitutionCheckBoxes.length; ++i) {
                if (this.substitutionCheckBoxes[i].isSelected()) {
                    substitutions.add(this.selectedItem.getSubstitutions()[i]);
                }
            }
            final MealItem mealItem = new MealItem(this.selectedItem, substitutions);
            this.meal.add(mealItem);
            final DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(mealItem);
            for (final Item substitution : substitutions) {
                itemNode.add(new DefaultMutableTreeNode(substitution));
            }
            ((DefaultTreeModel)this.mealTree.getModel()).insertNodeInto(itemNode, (MutableTreeNode)((DefaultTreeModel)this.mealTree.getModel()).getRoot(), ((DefaultMutableTreeNode)((DefaultTreeModel)this.mealTree.getModel()).getRoot()).getChildCount());
            this.mealTree.scrollPathToVisible(new TreePath(itemNode.getPath()));
            this.updateNutritionData();
        }
    }
    
    private void removeItem() {
        final DefaultTreeModel treeModel = (DefaultTreeModel)this.mealTree.getModel();
        final DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)this.mealTree.getLastSelectedPathComponent();
        final Object selectedItem = (selectedNode != null) ? selectedNode.getUserObject() : null;
        if (selectedItem != null) {
            if (selectedItem.equals("Meal")) {
                this.meal.clear();
                while (((DefaultMutableTreeNode)((DefaultTreeModel)this.mealTree.getModel()).getRoot()).getChildCount() > 0) {
                    ((DefaultTreeModel)this.mealTree.getModel()).removeNodeFromParent(((DefaultMutableTreeNode)((DefaultTreeModel)this.mealTree.getModel()).getRoot()).getFirstLeaf());
                }
            }
            else if (selectedItem instanceof MealItem) {
                this.meal.remove(selectedItem);
                treeModel.removeNodeFromParent(selectedNode);
            }
            else if (selectedItem instanceof Item) {
                ((MealItem)((DefaultMutableTreeNode)selectedNode.getParent()).getUserObject()).getSubstitutions().remove(selectedItem);
                treeModel.removeNodeFromParent(selectedNode);
            }
            this.updateNutritionData();
        }
        else {
            JOptionPane.showMessageDialog(this, "To remove an item, select one from the Meal Contents.", "No item selected", 2);
        }
    }
    
    private void updateNutritionData() {
        final DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)this.mealTree.getLastSelectedPathComponent();
        final Object selectedItem = (selectedNode != null) ? selectedNode.getUserObject() : "Meal";
        NutritionData nutritionData = new NutritionData();
        if (selectedItem.equals("Meal")) {
            for (final MealItem mealItem : this.meal) {
                nutritionData = nutritionData.aggregate(mealItem.getItem().getNutritionData());
                for (final Item substitution : mealItem.getSubstitutions()) {
                    nutritionData = nutritionData.aggregate(substitution.getNutritionData());
                }
            }
            this.itemNutrition.setData(nutritionData, "1 Meal");
        }
        else if (selectedItem instanceof MealItem) {
            nutritionData = nutritionData.aggregate(((MealItem)selectedItem).getItem().getNutritionData());
            for (final Item substitution2 : ((MealItem)selectedItem).getSubstitutions()) {
                nutritionData = nutritionData.aggregate(substitution2.getNutritionData());
            }
            this.itemNutrition.setData(nutritionData, ((MealItem)selectedItem).getItem().getServing());
        }
        else {
            nutritionData = nutritionData.aggregate(((Item)selectedItem).getNutritionData());
            this.itemNutrition.setData(nutritionData, ((Item)selectedItem).getServing());
        }
        this.mealTree.expandRow(0);
        this.repaint();
    }
}

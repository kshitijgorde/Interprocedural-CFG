// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import medusa.graph.Node;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellEditor;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import medusa.graph.Graph;
import javax.swing.JDialog;

public class EditGraphDialog extends JDialog
{
    private EdgeTableModel edgeTableModel;
    private NodeTableModel nodeTableModel;
    private NewEdgeDialog newEdgeDialog;
    private Graph g;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel controlPanel;
    private JButton deleteButton;
    private JTable edgeTable;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTabbedPane jTabbedPane1;
    private JTable nodeTable;
    private JButton okButton;
    private JLabel titleLabel;
    
    public EditGraphDialog(final Frame frame, final boolean b) {
        super(frame, b);
    }
    
    private void setModels() {
        this.edgeTableModel = new EdgeTableModel(this.g);
        this.nodeTableModel = new NodeTableModel(this.g);
        this.newEdgeDialog = new NewEdgeDialog((Frame)null, true);
        this.initComponents();
        this.nodeTable.getColumnModel().getColumn(0).setCellEditor(new NodeNameEditor(this.g, this));
    }
    
    private void updateTables() {
        this.edgeTableModel.init();
        this.edgeTable.updateUI();
        this.nodeTableModel.init();
        this.nodeTable.updateUI();
    }
    
    protected void notifyNodeNameChange(final String s, final String s2) {
        this.edgeTableModel.init();
    }
    
    private void initComponents() {
        this.titleLabel = new JLabel();
        this.jTabbedPane1 = new JTabbedPane();
        this.jPanel1 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        final TableSorter model = new TableSorter(this.edgeTableModel);
        (this.edgeTable = new JTable()).setModel(model);
        model.setTableHeader(this.edgeTable.getTableHeader());
        this.jPanel2 = new JPanel();
        this.addButton = new JButton();
        this.deleteButton = new JButton();
        this.jScrollPane2 = new JScrollPane();
        final TableSorter model2 = new TableSorter(this.nodeTableModel);
        (this.nodeTable = new JTable()).setModel(model2);
        model2.setTableHeader(this.nodeTable.getTableHeader());
        this.nodeTable.setDefaultRenderer(Color.class, new NodeColorRenderer(true));
        this.nodeTable.setDefaultEditor(Color.class, new ColorEditor());
        this.controlPanel = new JPanel();
        this.okButton = new JButton();
        this.cancelButton = new JButton();
        this.setDefaultCloseOperation(2);
        this.titleLabel.setHorizontalAlignment(0);
        this.titleLabel.setText("Graph editor");
        this.getContentPane().add(this.titleLabel, "North");
        this.jPanel1.setLayout(new BorderLayout());
        this.jScrollPane1.setViewportView(this.edgeTable);
        this.jPanel1.add(this.jScrollPane1, "Center");
        this.jPanel2.setLayout(new BoxLayout(this.jPanel2, 0));
        this.jPanel2.setBorder(BorderFactory.createBevelBorder(0));
        this.addButton.setText("Add");
        this.addButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EditGraphDialog.this.addButtonActionPerformed(actionEvent);
            }
        });
        this.jPanel2.add(this.addButton);
        this.deleteButton.setText("Delete");
        this.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EditGraphDialog.this.deleteButtonActionPerformed(actionEvent);
            }
        });
        this.jPanel2.add(this.deleteButton);
        this.jPanel1.add(this.jPanel2, "South");
        this.jTabbedPane1.addTab("Edges", this.jPanel1);
        this.jScrollPane2.setViewportView(this.nodeTable);
        this.jTabbedPane1.addTab("Nodes", this.jScrollPane2);
        this.getContentPane().add(this.jTabbedPane1, "Center");
        this.okButton.setText("OK");
        this.okButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EditGraphDialog.this.okButtonActionPerformed(actionEvent);
            }
        });
        this.controlPanel.add(this.okButton);
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EditGraphDialog.this.cancelButtonActionPerformed(actionEvent);
            }
        });
        this.controlPanel.add(this.cancelButton);
        this.getContentPane().add(this.controlPanel, "South");
        this.pack();
    }
    
    private void cancelButtonActionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    private void deleteButtonActionPerformed(final ActionEvent actionEvent) {
        this.edgeTableModel.removeEdges();
        this.edgeTable.updateUI();
        this.nodeTableModel.init();
    }
    
    private void addButtonActionPerformed(final ActionEvent actionEvent) {
        this.newEdgeDialog.setVisible(true);
        this.g.addEdge(this.newEdgeDialog.getEdge());
        this.edgeTableModel.init();
        final TableSorter model = new TableSorter(this.edgeTableModel);
        this.edgeTable.setModel(model);
        model.setTableHeader(this.edgeTable.getTableHeader());
        this.edgeTable.updateUI();
        this.nodeTableModel.init();
        final TableSorter model2 = new TableSorter(this.nodeTableModel);
        this.nodeTable.setModel(model2);
        model2.setTableHeader(this.nodeTable.getTableHeader());
        this.nodeTable.updateUI();
        JOptionPane.showMessageDialog(this, "Edge added. Remember to set X and Y position of\nany new nodes that have resulted from this addition.");
    }
    
    private void okButtonActionPerformed(final ActionEvent actionEvent) {
        this.g = this.getGraph();
        this.setVisible(false);
    }
    
    public static void main(final String[] array) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditGraphDialog(new JFrame(), true).setVisible(true);
            }
        });
    }
    
    public void setGraph(final Graph g) {
        this.g = g;
        this.setModels();
    }
    
    private Graph newGraph() {
        return new Graph();
    }
    
    public Graph getGraph() {
        final Graph graph = this.edgeTableModel.getGraph();
        final Node[] nodes = this.nodeTableModel.getNodes();
        for (int i = 0; i < nodes.length; ++i) {
            graph.setNode(nodes[i]);
        }
        return graph;
    }
}

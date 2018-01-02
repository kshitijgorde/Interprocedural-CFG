// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Enumeration;
import anon.util.JAPMessages;
import javax.swing.tree.MutableTreeNode;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Container;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.Icon;
import gui.GUIUtils;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.util.Hashtable;
import javax.swing.JTree;
import javax.swing.JPanel;
import gui.JAPHelpContext;

public class JAPConfModuleSystem implements JAPHelpContext.IHelpContext
{
    private JPanel m_rootPanel;
    private JPanel m_configurationCardsPanel;
    private JTree m_configurationTree;
    private Hashtable m_registratedModules;
    private Hashtable m_treeNodesToSymbolicNames;
    private Hashtable m_symbolicNamesToTreeNodes;
    private Hashtable m_symbolicNamesToHelpContext;
    private JAPHelpContext.IHelpContext m_currentHelpContext;
    
    public JAPConfModuleSystem() {
        this.m_registratedModules = new Hashtable();
        this.m_treeNodesToSymbolicNames = new Hashtable();
        this.m_symbolicNamesToTreeNodes = new Hashtable();
        this.m_symbolicNamesToHelpContext = new Hashtable();
        this.m_configurationCardsPanel = new JPanel(new CardLayout());
        final DefaultTreeModel defaultTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("root"));
        final DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer();
        cellRenderer.setClosedIcon(GUIUtils.loadImageIcon("arrow.gif", true));
        cellRenderer.setOpenIcon(GUIUtils.loadImageIcon("arrow90.gif", true));
        cellRenderer.setLeafIcon(null);
        final DefaultTreeSelectionModel selectionModel = new DefaultTreeSelectionModel() {
            private static final long serialVersionUID = 1L;
            
            public void setSelectionPath(final TreePath selectionPath) {
                if (JAPConfModuleSystem.this.m_treeNodesToSymbolicNames.get(selectionPath.getLastPathComponent()) != null) {
                    super.setSelectionPath(selectionPath);
                }
            }
        };
        selectionModel.setSelectionMode(1);
        (this.m_configurationTree = new JTree(defaultTreeModel)).setSelectionModel(selectionModel);
        this.m_configurationTree.setRootVisible(false);
        this.m_configurationTree.setEditable(false);
        this.m_configurationTree.setCellRenderer(cellRenderer);
        this.m_configurationTree.setBorder(new CompoundBorder(LineBorder.createBlackLineBorder(), new EmptyBorder(5, 5, 5, 5)));
        this.m_configurationTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            public void treeWillCollapse(final TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
                throw new ExpandVetoException(treeExpansionEvent);
            }
            
            public void treeWillExpand(final TreeExpansionEvent treeExpansionEvent) {
            }
        });
        this.m_configurationTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
                if (treeSelectionEvent.isAddedPath()) {
                    final String s = JAPConfModuleSystem.this.m_treeNodesToSymbolicNames.get(treeSelectionEvent.getPath().getLastPathComponent());
                    if (s != null) {
                        JAPConfModuleSystem.this.m_currentHelpContext = (JAPHelpContext.IHelpContext)JAPConfModuleSystem.this.m_symbolicNamesToHelpContext.get(s);
                        ((CardLayout)JAPConfModuleSystem.this.m_configurationCardsPanel.getLayout()).show(JAPConfModuleSystem.this.m_configurationCardsPanel, s);
                    }
                }
            }
        });
        this.m_rootPanel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        this.m_rootPanel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.m_configurationTree, gridBagConstraints);
        this.m_rootPanel.add(this.m_configurationTree);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.m_configurationCardsPanel, gridBagConstraints);
        this.m_rootPanel.add(this.m_configurationCardsPanel);
    }
    
    public DefaultMutableTreeNode addConfigurationModule(final DefaultMutableTreeNode defaultMutableTreeNode, final AbstractJAPConfModule abstractJAPConfModule, final String s) {
        final DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(abstractJAPConfModule.getTabTitle());
        synchronized (this) {
            defaultMutableTreeNode.add(defaultMutableTreeNode2);
            this.m_configurationCardsPanel.add(abstractJAPConfModule.getRootPanel(), s);
            this.m_registratedModules.put(defaultMutableTreeNode2, abstractJAPConfModule);
            this.m_treeNodesToSymbolicNames.put(defaultMutableTreeNode2, s);
            this.m_symbolicNamesToTreeNodes.put(s, defaultMutableTreeNode2);
            this.m_symbolicNamesToHelpContext.put(s, abstractJAPConfModule);
        }
        return defaultMutableTreeNode2;
    }
    
    public DefaultMutableTreeNode addComponent(final DefaultMutableTreeNode defaultMutableTreeNode, final Component component, final String s, final String s2, final String s3) {
        final DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(JAPMessages.getString(s));
        synchronized (this) {
            defaultMutableTreeNode.add(defaultMutableTreeNode2);
            if (component != null) {
                this.m_configurationCardsPanel.add(component, s2);
                this.m_treeNodesToSymbolicNames.put(defaultMutableTreeNode2, s2);
                this.m_symbolicNamesToTreeNodes.put(s2, defaultMutableTreeNode2);
                this.m_symbolicNamesToHelpContext.put(s2, new JAPHelpContext.IHelpContext() {
                    public String getHelpContext() {
                        return s3;
                    }
                    
                    public Component getHelpExtractionDisplayContext() {
                        return JAPConf.getInstance().getContentPane();
                    }
                });
            }
        }
        return defaultMutableTreeNode2;
    }
    
    public DefaultMutableTreeNode getConfigurationTreeRootNode() {
        return (DefaultMutableTreeNode)this.m_configurationTree.getModel().getRoot();
    }
    
    public JTree getConfigurationTree() {
        return this.m_configurationTree;
    }
    
    public String getHelpContext() {
        return this.m_currentHelpContext.getHelpContext();
    }
    
    public Component getHelpExtractionDisplayContext() {
        return JAPConf.getInstance().getContentPane();
    }
    
    public AbstractJAPConfModule getCurrentModule() {
        return null;
    }
    
    public JPanel getRootPanel() {
        return this.m_rootPanel;
    }
    
    protected void initObservers() {
        synchronized (this) {
            final Enumeration<AbstractJAPConfModule> elements = this.m_registratedModules.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().initObservers();
            }
        }
    }
    
    protected void revalidate() {
        this.m_configurationCardsPanel.revalidate();
        this.m_configurationTree.revalidate();
        this.m_rootPanel.revalidate();
    }
    
    public void selectNode(final String s) {
        synchronized (this) {
            final DefaultMutableTreeNode defaultMutableTreeNode = this.m_symbolicNamesToTreeNodes.get(s);
            if (defaultMutableTreeNode != null) {
                this.m_configurationTree.setSelectionPath(new TreePath(defaultMutableTreeNode.getPath()));
            }
        }
    }
    
    public boolean processOkPressedEvent() {
        boolean b = true;
        synchronized (this) {
            final Enumeration<AbstractJAPConfModule> elements = (Enumeration<AbstractJAPConfModule>)this.m_registratedModules.elements();
            while (elements.hasMoreElements()) {
                if (!elements.nextElement().okPressed()) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public void processCancelPressedEvent() {
        synchronized (this) {
            final Enumeration<AbstractJAPConfModule> elements = this.m_registratedModules.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().cancelPressed();
            }
        }
    }
    
    public void processResetToDefaultsPressedEvent() {
        synchronized (this) {
            final Enumeration<AbstractJAPConfModule> elements = this.m_registratedModules.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().resetToDefaultsPressed();
            }
        }
    }
    
    public void processUpdateValuesEvent(final boolean b) {
        synchronized (this) {
            final Enumeration<AbstractJAPConfModule> elements = this.m_registratedModules.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().updateValues(b);
            }
        }
    }
    
    public void createSavePoints() {
        synchronized (this) {
            final Enumeration<AbstractJAPConfModule> elements = this.m_registratedModules.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().createSavePoint();
            }
        }
    }
}

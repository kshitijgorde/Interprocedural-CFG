// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.io.Serializable;
import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Cursor;
import javax.accessibility.AccessibleValue;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleAction;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.beans.PropertyChangeEvent;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.beans.PropertyChangeListener;
import javax.accessibility.AccessibleSelection;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Component;
import javax.swing.plaf.ComponentUI;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.plaf.ListUI;
import javax.accessibility.AccessibleContext;
import javax.swing.event.ListSelectionEvent;
import java.awt.Rectangle;
import java.util.EventListener;
import java.util.Vector;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import javax.accessibility.Accessible;

public class JList extends JComponent implements Scrollable, Accessible
{
    private static final String uiClassID = "ListUI";
    private int fixedCellWidth;
    private int fixedCellHeight;
    private int horizontalScrollIncrement;
    private Object prototypeCellValue;
    private int visibleRowCount;
    private Color selectionForeground;
    private Color selectionBackground;
    private ListSelectionModel selectionModel;
    private ListModel dataModel;
    private ListCellRenderer cellRenderer;
    private ListSelectionListener selectionListener;
    static /* synthetic */ Class class$javax$swing$event$ListSelectionListener;
    
    public JList() {
        this(new AbstractListModel() {
            public Object getElementAt(final int n) {
                return "No Data Model";
            }
            
            public int getSize() {
                return 0;
            }
        });
    }
    
    public JList(final Vector vector) {
        this(new AbstractListModel() {
            private final /* synthetic */ Vector val$listData = val$listData;
            
            public Object getElementAt(final int n) {
                return this.val$listData.elementAt(n);
            }
            
            public int getSize() {
                return this.val$listData.size();
            }
        });
    }
    
    public JList(final ListModel dataModel) {
        this.fixedCellWidth = -1;
        this.fixedCellHeight = -1;
        this.horizontalScrollIncrement = -1;
        this.visibleRowCount = 8;
        if (dataModel == null) {
            throw new IllegalArgumentException("dataModel must be non null");
        }
        this.dataModel = dataModel;
        this.selectionModel = this.createSelectionModel();
        this.setAutoscrolls(true);
        this.setOpaque(true);
        this.updateUI();
    }
    
    public JList(final Object[] array) {
        this(new AbstractListModel() {
            private final /* synthetic */ Object[] val$listData = val$listData;
            
            public Object getElementAt(final int n) {
                return this.val$listData[n];
            }
            
            public int getSize() {
                return this.val$listData.length;
            }
        });
    }
    
    public void addListSelectionListener(final ListSelectionListener listSelectionListener) {
        if (this.selectionListener == null) {
            this.selectionListener = new ListSelectionHandler();
            this.getSelectionModel().addListSelectionListener(this.selectionListener);
        }
        super.listenerList.add((JList.class$javax$swing$event$ListSelectionListener != null) ? JList.class$javax$swing$event$ListSelectionListener : (JList.class$javax$swing$event$ListSelectionListener = class$("javax.swing.event.ListSelectionListener")), listSelectionListener);
    }
    
    public void addSelectionInterval(final int n, final int n2) {
        this.getSelectionModel().addSelectionInterval(n, n2);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public void clearSelection() {
        this.getSelectionModel().clearSelection();
    }
    
    protected ListSelectionModel createSelectionModel() {
        return new DefaultListSelectionModel();
    }
    
    public void ensureIndexIsVisible(final int n) {
        final Rectangle cellBounds = this.getCellBounds(n, n);
        if (cellBounds != null) {
            this.scrollRectToVisible(cellBounds);
        }
    }
    
    protected void fireSelectionValueChanged(final int n, final int n2, final boolean b) {
        final Object[] listenerList = super.listenerList.getListenerList();
        ListSelectionEvent listSelectionEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JList.class$javax$swing$event$ListSelectionListener != null) ? JList.class$javax$swing$event$ListSelectionListener : (JList.class$javax$swing$event$ListSelectionListener = class$("javax.swing.event.ListSelectionListener")))) {
                if (listSelectionEvent == null) {
                    listSelectionEvent = new ListSelectionEvent(this, n, n2, b);
                }
                ((ListSelectionListener)listenerList[i + 1]).valueChanged(listSelectionEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJList();
        }
        return super.accessibleContext;
    }
    
    public int getAnchorSelectionIndex() {
        return this.getSelectionModel().getAnchorSelectionIndex();
    }
    
    public Rectangle getCellBounds(final int n, final int n2) {
        final ListUI ui = this.getUI();
        return (ui != null) ? ui.getCellBounds(this, n, n2) : null;
    }
    
    public ListCellRenderer getCellRenderer() {
        return this.cellRenderer;
    }
    
    public int getFirstVisibleIndex() {
        return this.locationToIndex(this.getVisibleRect().getLocation());
    }
    
    public int getFixedCellHeight() {
        return this.fixedCellHeight;
    }
    
    public int getFixedCellWidth() {
        return this.fixedCellWidth;
    }
    
    public int getLastVisibleIndex() {
        final Rectangle visibleRect = this.getVisibleRect();
        return this.locationToIndex(new Point(visibleRect.x + visibleRect.width - 1, visibleRect.y + visibleRect.height - 1));
    }
    
    public int getLeadSelectionIndex() {
        return this.getSelectionModel().getLeadSelectionIndex();
    }
    
    public int getMaxSelectionIndex() {
        return this.getSelectionModel().getMaxSelectionIndex();
    }
    
    public int getMinSelectionIndex() {
        return this.getSelectionModel().getMinSelectionIndex();
    }
    
    public ListModel getModel() {
        return this.dataModel;
    }
    
    public Dimension getPreferredScrollableViewportSize() {
        final Insets insets = this.getInsets();
        final int n = insets.left + insets.right;
        final int n2 = insets.top + insets.bottom;
        final int visibleRowCount = this.getVisibleRowCount();
        final int fixedCellWidth = this.getFixedCellWidth();
        final int fixedCellHeight = this.getFixedCellHeight();
        if (fixedCellWidth > 0 && fixedCellHeight > 0) {
            return new Dimension(fixedCellWidth + n, visibleRowCount * fixedCellHeight + n2);
        }
        if (this.getModel().getSize() > 0) {
            return new Dimension(this.getPreferredSize().width, visibleRowCount * this.getCellBounds(0, 0).height + n2);
        }
        return new Dimension((fixedCellWidth > 0) ? fixedCellWidth : 256, ((fixedCellHeight > 0) ? fixedCellHeight : 16) * visibleRowCount);
    }
    
    public Object getPrototypeCellValue() {
        return this.prototypeCellValue;
    }
    
    public int getScrollableBlockIncrement(final Rectangle rectangle, final int n, final int n2) {
        return (n == 1) ? rectangle.height : rectangle.width;
    }
    
    public boolean getScrollableTracksViewportHeight() {
        return this.getParent() instanceof JViewport && ((JViewport)this.getParent()).getHeight() > this.getPreferredSize().height;
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return this.getParent() instanceof JViewport && ((JViewport)this.getParent()).getWidth() > this.getPreferredSize().width;
    }
    
    public int getScrollableUnitIncrement(final Rectangle rectangle, final int n, final int n2) {
        if (n == 0) {
            final Font font = this.getFont();
            return (font != null) ? font.getSize() : 1;
        }
        final int firstVisibleIndex = this.getFirstVisibleIndex();
        if (firstVisibleIndex == -1) {
            return 0;
        }
        if (n2 > 0) {
            final Rectangle cellBounds = this.getCellBounds(firstVisibleIndex, firstVisibleIndex);
            return (cellBounds == null) ? 0 : (cellBounds.height - (rectangle.y - cellBounds.y));
        }
        final Rectangle cellBounds2 = this.getCellBounds(firstVisibleIndex, firstVisibleIndex);
        if (cellBounds2.y == rectangle.y && firstVisibleIndex == 0) {
            return 0;
        }
        if (cellBounds2.y == rectangle.y) {
            final Rectangle cellBounds3 = this.getCellBounds(firstVisibleIndex - 1, firstVisibleIndex - 1);
            return (cellBounds3 == null) ? 0 : cellBounds3.height;
        }
        return rectangle.y - cellBounds2.y;
    }
    
    public int getSelectedIndex() {
        return this.getMinSelectionIndex();
    }
    
    public int[] getSelectedIndices() {
        final ListSelectionModel selectionModel = this.getSelectionModel();
        final int minSelectionIndex = selectionModel.getMinSelectionIndex();
        final int maxSelectionIndex = selectionModel.getMaxSelectionIndex();
        if (minSelectionIndex < 0 || maxSelectionIndex < 0) {
            return new int[0];
        }
        final int[] array = new int[1 + (maxSelectionIndex - minSelectionIndex)];
        int n = 0;
        for (int i = minSelectionIndex; i <= maxSelectionIndex; ++i) {
            if (selectionModel.isSelectedIndex(i)) {
                array[n++] = i;
            }
        }
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public Object getSelectedValue() {
        final int minSelectionIndex = this.getMinSelectionIndex();
        return (minSelectionIndex == -1) ? null : this.getModel().getElementAt(minSelectionIndex);
    }
    
    public Object[] getSelectedValues() {
        final ListSelectionModel selectionModel = this.getSelectionModel();
        final ListModel model = this.getModel();
        final int minSelectionIndex = selectionModel.getMinSelectionIndex();
        final int maxSelectionIndex = selectionModel.getMaxSelectionIndex();
        if (minSelectionIndex < 0 || maxSelectionIndex < 0) {
            return new Object[0];
        }
        final Object[] array = new Object[1 + (maxSelectionIndex - minSelectionIndex)];
        int n = 0;
        for (int i = minSelectionIndex; i <= maxSelectionIndex; ++i) {
            if (selectionModel.isSelectedIndex(i)) {
                array[n++] = model.getElementAt(i);
            }
        }
        final Object[] array2 = new Object[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public Color getSelectionBackground() {
        return this.selectionBackground;
    }
    
    public Color getSelectionForeground() {
        return this.selectionForeground;
    }
    
    public int getSelectionMode() {
        return this.getSelectionModel().getSelectionMode();
    }
    
    public ListSelectionModel getSelectionModel() {
        return this.selectionModel;
    }
    
    public ListUI getUI() {
        return (ListUI)super.ui;
    }
    
    public String getUIClassID() {
        return "ListUI";
    }
    
    public boolean getValueIsAdjusting() {
        return this.getSelectionModel().getValueIsAdjusting();
    }
    
    public int getVisibleRowCount() {
        return this.visibleRowCount;
    }
    
    public Point indexToLocation(final int n) {
        final ListUI ui = this.getUI();
        return (ui != null) ? ui.indexToLocation(this, n) : null;
    }
    
    public boolean isSelectedIndex(final int n) {
        return this.getSelectionModel().isSelectedIndex(n);
    }
    
    public boolean isSelectionEmpty() {
        return this.getSelectionModel().isSelectionEmpty();
    }
    
    public int locationToIndex(final Point point) {
        final ListUI ui = this.getUI();
        return (ui != null) ? ui.locationToIndex(this, point) : -1;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",fixedCellHeight=" + this.fixedCellHeight + ",fixedCellWidth=" + this.fixedCellWidth + ",horizontalScrollIncrement=" + this.horizontalScrollIncrement + ",selectionBackground=" + ((this.selectionBackground != null) ? this.selectionBackground.toString() : "") + ",selectionForeground=" + ((this.selectionForeground != null) ? this.selectionForeground.toString() : "") + ",visibleRowCount=" + this.visibleRowCount;
    }
    
    public void removeListSelectionListener(final ListSelectionListener listSelectionListener) {
        super.listenerList.remove((JList.class$javax$swing$event$ListSelectionListener != null) ? JList.class$javax$swing$event$ListSelectionListener : (JList.class$javax$swing$event$ListSelectionListener = class$("javax.swing.event.ListSelectionListener")), listSelectionListener);
    }
    
    public void removeSelectionInterval(final int n, final int n2) {
        this.getSelectionModel().removeSelectionInterval(n, n2);
    }
    
    public void setCellRenderer(final ListCellRenderer cellRenderer) {
        final ListCellRenderer cellRenderer2 = this.cellRenderer;
        this.cellRenderer = cellRenderer;
        if (cellRenderer != null && !cellRenderer.equals(cellRenderer2)) {
            this.updateFixedCellSize();
        }
        this.firePropertyChange("cellRenderer", cellRenderer2, cellRenderer);
    }
    
    public void setFixedCellHeight(final int fixedCellHeight) {
        this.firePropertyChange("fixedCellHeight", this.fixedCellHeight, this.fixedCellHeight = fixedCellHeight);
    }
    
    public void setFixedCellWidth(final int fixedCellWidth) {
        this.firePropertyChange("fixedCellWidth", this.fixedCellWidth, this.fixedCellWidth = fixedCellWidth);
    }
    
    public void setListData(final Vector vector) {
        this.setModel(new AbstractListModel() {
            private final /* synthetic */ Vector val$listData = val$listData;
            
            public Object getElementAt(final int n) {
                return this.val$listData.elementAt(n);
            }
            
            public int getSize() {
                return this.val$listData.size();
            }
        });
    }
    
    public void setListData(final Object[] array) {
        this.setModel(new AbstractListModel() {
            private final /* synthetic */ Object[] val$listData = val$listData;
            
            public Object getElementAt(final int n) {
                return this.val$listData[n];
            }
            
            public int getSize() {
                return this.val$listData.length;
            }
        });
    }
    
    public void setModel(final ListModel dataModel) {
        if (dataModel == null) {
            throw new IllegalArgumentException("model must be non null");
        }
        this.firePropertyChange("model", this.dataModel, this.dataModel = dataModel);
        this.clearSelection();
    }
    
    public void setPrototypeCellValue(final Object prototypeCellValue) {
        final Object prototypeCellValue2 = this.prototypeCellValue;
        this.prototypeCellValue = prototypeCellValue;
        if (prototypeCellValue != null && !prototypeCellValue.equals(prototypeCellValue2)) {
            this.updateFixedCellSize();
        }
        this.firePropertyChange("prototypeCellValue", prototypeCellValue2, prototypeCellValue);
    }
    
    public void setSelectedIndex(final int n) {
        this.getSelectionModel().setSelectionInterval(n, n);
    }
    
    public void setSelectedIndices(final int[] array) {
        final ListSelectionModel selectionModel = this.getSelectionModel();
        selectionModel.clearSelection();
        for (int i = 0; i < array.length; ++i) {
            selectionModel.addSelectionInterval(array[i], array[i]);
        }
    }
    
    public void setSelectedValue(final Object o, final boolean b) {
        if (o == null) {
            this.setSelectedIndex(-1);
        }
        else if (!o.equals(this.getSelectedValue())) {
            final ListModel model = this.getModel();
            for (int i = 0; i < model.getSize(); ++i) {
                if (o.equals(model.getElementAt(i))) {
                    this.setSelectedIndex(i);
                    if (b) {
                        this.ensureIndexIsVisible(i);
                    }
                    this.repaint();
                    return;
                }
            }
            this.setSelectedIndex(-1);
        }
        this.repaint();
    }
    
    public void setSelectionBackground(final Color selectionBackground) {
        this.firePropertyChange("selectionBackground", this.selectionBackground, this.selectionBackground = selectionBackground);
    }
    
    public void setSelectionForeground(final Color selectionForeground) {
        this.firePropertyChange("selectionForeground", this.selectionForeground, this.selectionForeground = selectionForeground);
    }
    
    public void setSelectionInterval(final int n, final int n2) {
        this.getSelectionModel().setSelectionInterval(n, n2);
    }
    
    public void setSelectionMode(final int selectionMode) {
        this.getSelectionModel().setSelectionMode(selectionMode);
    }
    
    public void setSelectionModel(final ListSelectionModel selectionModel) {
        if (selectionModel == null) {
            throw new IllegalArgumentException("selectionModel must be non null");
        }
        if (this.selectionListener != null) {
            this.selectionModel.removeListSelectionListener(this.selectionListener);
            selectionModel.addListSelectionListener(this.selectionListener);
        }
        this.firePropertyChange("selectionModel", this.selectionModel, this.selectionModel = selectionModel);
    }
    
    public void setUI(final ListUI ui) {
        super.setUI(ui);
    }
    
    public void setValueIsAdjusting(final boolean valueIsAdjusting) {
        this.getSelectionModel().setValueIsAdjusting(valueIsAdjusting);
    }
    
    public void setVisibleRowCount(final int n) {
        final int visibleRowCount = this.visibleRowCount;
        this.visibleRowCount = Math.max(0, n);
        this.firePropertyChange("visibleRowCount", visibleRowCount, n);
    }
    
    private void updateFixedCellSize() {
        final ListCellRenderer cellRenderer = this.getCellRenderer();
        final Object prototypeCellValue = this.getPrototypeCellValue();
        if (cellRenderer != null && prototypeCellValue != null) {
            final Component listCellRendererComponent = cellRenderer.getListCellRendererComponent(this, prototypeCellValue, 0, false, false);
            final Font font = listCellRendererComponent.getFont();
            listCellRendererComponent.setFont(this.getFont());
            final Dimension preferredSize = listCellRendererComponent.getPreferredSize();
            this.fixedCellWidth = preferredSize.width;
            this.fixedCellHeight = preferredSize.height;
            listCellRendererComponent.setFont(font);
        }
    }
    
    public void updateUI() {
        this.setUI((ListUI)UIManager.getUI(this));
        this.invalidate();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ListUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJList extends AccessibleJComponent implements AccessibleSelection, PropertyChangeListener, ListSelectionListener, ListDataListener
    {
        int leadSelectionIndex;
        
        public AccessibleJList() {
            JList.this.addPropertyChangeListener(this);
            JList.this.getSelectionModel().addListSelectionListener(this);
            JList.this.getModel().addListDataListener(this);
            this.leadSelectionIndex = JList.this.getLeadSelectionIndex();
        }
        
        public void addAccessibleSelection(final int n) {
            JList.this.addSelectionInterval(n, n);
        }
        
        public void clearAccessibleSelection() {
            JList.this.clearSelection();
        }
        
        public void contentsChanged(final ListDataEvent listDataEvent) {
            this.firePropertyChange("AccessibleVisibleData", new Boolean(false), new Boolean(true));
        }
        
        public Accessible getAccessibleAt(final Point point) {
            final int locationToIndex = JList.this.locationToIndex(point);
            if (locationToIndex >= 0) {
                return new AccessibleJListChild(JList.this, locationToIndex);
            }
            return null;
        }
        
        public Accessible getAccessibleChild(final int n) {
            if (n >= JList.this.getModel().getSize()) {
                return null;
            }
            return new AccessibleJListChild(JList.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return JList.this.getModel().getSize();
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LIST;
        }
        
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }
        
        public Accessible getAccessibleSelection(final int n) {
            final int accessibleSelectionCount = this.getAccessibleSelectionCount();
            if (n < 0 || n >= accessibleSelectionCount) {
                return null;
            }
            return this.getAccessibleChild(JList.this.getSelectedIndices()[n]);
        }
        
        public int getAccessibleSelectionCount() {
            return JList.this.getSelectedIndices().length;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            if (JList.this.selectionModel.getSelectionMode() != 0) {
                accessibleStateSet.add(AccessibleState.MULTISELECTABLE);
            }
            return accessibleStateSet;
        }
        
        public void intervalAdded(final ListDataEvent listDataEvent) {
            this.firePropertyChange("AccessibleVisibleData", new Boolean(false), new Boolean(true));
        }
        
        public void intervalRemoved(final ListDataEvent listDataEvent) {
            this.firePropertyChange("AccessibleVisibleData", new Boolean(false), new Boolean(true));
        }
        
        public boolean isAccessibleChildSelected(final int n) {
            return JList.this.isSelectedIndex(n);
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            final Object oldValue = propertyChangeEvent.getOldValue();
            final Object newValue = propertyChangeEvent.getNewValue();
            if (propertyName.compareTo("model") == 0) {
                if (oldValue != null && oldValue instanceof ListModel) {
                    ((ListModel)oldValue).removeListDataListener(this);
                }
                if (newValue != null && newValue instanceof ListModel) {
                    ((ListModel)newValue).addListDataListener(this);
                }
            }
            else if (propertyName.compareTo("selectionModel") == 0) {
                if (oldValue != null && oldValue instanceof ListSelectionModel) {
                    ((ListSelectionModel)oldValue).removeListSelectionListener(this);
                }
                if (newValue != null && newValue instanceof ListSelectionModel) {
                    ((ListSelectionModel)newValue).addListSelectionListener(this);
                }
                this.firePropertyChange("AccessibleSelection", new Boolean(false), new Boolean(true));
            }
        }
        
        public void removeAccessibleSelection(final int n) {
            JList.this.removeSelectionInterval(n, n);
        }
        
        public void selectAllAccessibleSelection() {
            JList.this.addSelectionInterval(0, this.getAccessibleChildrenCount() - 1);
        }
        
        public void valueChanged(final ListSelectionEvent listSelectionEvent) {
            final int leadSelectionIndex = this.leadSelectionIndex;
            this.leadSelectionIndex = JList.this.getLeadSelectionIndex();
            if (leadSelectionIndex != this.leadSelectionIndex) {
                this.firePropertyChange("AccessibleActiveDescendant", (leadSelectionIndex >= 0) ? this.getAccessibleChild(leadSelectionIndex) : null, (this.leadSelectionIndex >= 0) ? this.getAccessibleChild(this.leadSelectionIndex) : null);
            }
            this.firePropertyChange("AccessibleVisibleData", new Boolean(false), new Boolean(true));
            this.firePropertyChange("AccessibleSelection", new Boolean(false), new Boolean(true));
            final AccessibleStateSet accessibleStateSet = this.getAccessibleStateSet();
            if (JList.this.getSelectionModel().getSelectionMode() != 0) {
                if (!accessibleStateSet.contains(AccessibleState.MULTISELECTABLE)) {
                    accessibleStateSet.add(AccessibleState.MULTISELECTABLE);
                    this.firePropertyChange("AccessibleState", null, AccessibleState.MULTISELECTABLE);
                }
            }
            else if (accessibleStateSet.contains(AccessibleState.MULTISELECTABLE)) {
                accessibleStateSet.remove(AccessibleState.MULTISELECTABLE);
                this.firePropertyChange("AccessibleState", AccessibleState.MULTISELECTABLE, null);
            }
        }
        
        protected class AccessibleJListChild extends AccessibleContext implements Accessible, AccessibleComponent
        {
            private JList parent;
            private int indexInParent;
            private Component component;
            private AccessibleContext accessibleContext;
            private ListModel listModel;
            private ListCellRenderer cellRenderer;
            
            public AccessibleJListChild(final JList parent, final int indexInParent) {
                this.parent = null;
                this.component = null;
                this.accessibleContext = null;
                this.cellRenderer = null;
                this.setAccessibleParent(this.parent = parent);
                this.indexInParent = indexInParent;
                if (parent != null) {
                    this.listModel = parent.getModel();
                    this.cellRenderer = parent.getCellRenderer();
                }
            }
            
            public void addFocusListener(final FocusListener focusListener) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).addFocusListener(focusListener);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.addFocusListener(focusListener);
                    }
                }
            }
            
            public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    currentAccessibleContext.addPropertyChangeListener(propertyChangeListener);
                }
            }
            
            public boolean contains(final Point point) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getBounds().contains(point);
                }
                final Component currentComponent = this.getCurrentComponent();
                if (currentComponent != null) {
                    return currentComponent.getBounds().contains(point);
                }
                return this.getBounds().contains(point);
            }
            
            public AccessibleAction getAccessibleAction() {
                return this.getCurrentAccessibleContext().getAccessibleAction();
            }
            
            public Accessible getAccessibleAt(final Point point) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getAccessibleAt(point);
                }
                return null;
            }
            
            public Accessible getAccessibleChild(final int n) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    final Accessible accessibleChild = currentAccessibleContext.getAccessibleChild(n);
                    currentAccessibleContext.setAccessibleParent(this);
                    return accessibleChild;
                }
                return null;
            }
            
            public int getAccessibleChildrenCount() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    return currentAccessibleContext.getAccessibleChildrenCount();
                }
                return 0;
            }
            
            public AccessibleComponent getAccessibleComponent() {
                return this;
            }
            
            public AccessibleContext getAccessibleContext() {
                return this;
            }
            
            public String getAccessibleDescription() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    return currentAccessibleContext.getAccessibleDescription();
                }
                return null;
            }
            
            public int getAccessibleIndexInParent() {
                return this.indexInParent;
            }
            
            public String getAccessibleName() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    return currentAccessibleContext.getAccessibleName();
                }
                return null;
            }
            
            public AccessibleRole getAccessibleRole() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    return currentAccessibleContext.getAccessibleRole();
                }
                return null;
            }
            
            public AccessibleSelection getAccessibleSelection() {
                return this.getCurrentAccessibleContext().getAccessibleSelection();
            }
            
            public AccessibleStateSet getAccessibleStateSet() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    currentAccessibleContext.getAccessibleStateSet();
                }
                else {
                    final AccessibleStateSet set = new AccessibleStateSet();
                }
                final AccessibleStateSet accessibleStateSet = currentAccessibleContext.getAccessibleStateSet();
                accessibleStateSet.add(AccessibleState.SELECTABLE);
                if (this.parent.hasFocus() && this.indexInParent == this.parent.getLeadSelectionIndex()) {
                    accessibleStateSet.add(AccessibleState.ACTIVE);
                }
                if (this.parent.isSelectedIndex(this.indexInParent)) {
                    accessibleStateSet.add(AccessibleState.SELECTED);
                }
                if (this.isShowing()) {
                    accessibleStateSet.add(AccessibleState.SHOWING);
                }
                else if (accessibleStateSet.contains(AccessibleState.SHOWING)) {
                    accessibleStateSet.remove(AccessibleState.SHOWING);
                }
                if (this.isVisible()) {
                    accessibleStateSet.add(AccessibleState.VISIBLE);
                }
                else if (accessibleStateSet.contains(AccessibleState.VISIBLE)) {
                    accessibleStateSet.remove(AccessibleState.VISIBLE);
                }
                accessibleStateSet.add(AccessibleState.TRANSIENT);
                return accessibleStateSet;
            }
            
            public AccessibleText getAccessibleText() {
                return this.getCurrentAccessibleContext().getAccessibleText();
            }
            
            public AccessibleValue getAccessibleValue() {
                return this.getCurrentAccessibleContext().getAccessibleValue();
            }
            
            public Color getBackground() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getBackground();
                }
                final Component currentComponent = this.getCurrentComponent();
                if (currentComponent != null) {
                    return currentComponent.getBackground();
                }
                return null;
            }
            
            public Rectangle getBounds() {
                if (this.parent != null) {
                    return this.parent.getCellBounds(this.indexInParent, this.indexInParent);
                }
                return null;
            }
            
            private Component getComponentAtIndex(final int n) {
                if (n < 0 || n >= this.listModel.getSize()) {
                    return null;
                }
                if (this.parent != null && this.listModel != null && this.cellRenderer != null) {
                    return this.cellRenderer.getListCellRendererComponent(this.parent, this.listModel.getElementAt(n), n, this.parent.isSelectedIndex(n), this.parent.hasFocus() && n == this.parent.getLeadSelectionIndex());
                }
                return null;
            }
            
            private AccessibleContext getCurrentAccessibleContext() {
                final Component componentAtIndex = this.getComponentAtIndex(this.indexInParent);
                if (componentAtIndex instanceof Accessible) {
                    return ((Accessible)componentAtIndex).getAccessibleContext();
                }
                return null;
            }
            
            private Component getCurrentComponent() {
                return this.getComponentAtIndex(this.indexInParent);
            }
            
            public Cursor getCursor() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getCursor();
                }
                final Component currentComponent = this.getCurrentComponent();
                if (currentComponent != null) {
                    return currentComponent.getCursor();
                }
                final Accessible accessibleParent = this.getAccessibleParent();
                if (accessibleParent instanceof AccessibleComponent) {
                    return ((AccessibleComponent)accessibleParent).getCursor();
                }
                return null;
            }
            
            public Font getFont() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getFont();
                }
                final Component currentComponent = this.getCurrentComponent();
                if (currentComponent != null) {
                    return currentComponent.getFont();
                }
                return null;
            }
            
            public FontMetrics getFontMetrics(final Font font) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getFontMetrics(font);
                }
                final Component currentComponent = this.getCurrentComponent();
                if (currentComponent != null) {
                    return currentComponent.getFontMetrics(font);
                }
                return null;
            }
            
            public Color getForeground() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).getForeground();
                }
                final Component currentComponent = this.getCurrentComponent();
                if (currentComponent != null) {
                    return currentComponent.getForeground();
                }
                return null;
            }
            
            public Locale getLocale() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    return currentAccessibleContext.getLocale();
                }
                return null;
            }
            
            public Point getLocation() {
                if (this.parent != null) {
                    return this.parent.indexToLocation(this.indexInParent);
                }
                return null;
            }
            
            public Point getLocationOnScreen() {
                if (this.parent != null) {
                    final Point locationOnScreen = this.parent.getLocationOnScreen();
                    final Point indexToLocation = this.parent.indexToLocation(this.indexInParent);
                    indexToLocation.translate(locationOnScreen.x, locationOnScreen.y);
                    return indexToLocation;
                }
                return null;
            }
            
            public Dimension getSize() {
                final Rectangle bounds = this.getBounds();
                if (bounds != null) {
                    return bounds.getSize();
                }
                return null;
            }
            
            public boolean isEnabled() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).isEnabled();
                }
                final Component currentComponent = this.getCurrentComponent();
                return currentComponent != null && currentComponent.isEnabled();
            }
            
            public boolean isFocusTraversable() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    return ((AccessibleComponent)currentAccessibleContext).isFocusTraversable();
                }
                final Component currentComponent = this.getCurrentComponent();
                return currentComponent != null && currentComponent.isFocusTraversable();
            }
            
            public boolean isShowing() {
                return this.parent.isShowing() && this.isVisible();
            }
            
            public boolean isVisible() {
                final int firstVisibleIndex = this.parent.getFirstVisibleIndex();
                int lastVisibleIndex = this.parent.getLastVisibleIndex();
                if (lastVisibleIndex == -1) {
                    lastVisibleIndex = this.parent.getModel().getSize() - 1;
                }
                return this.indexInParent >= firstVisibleIndex && this.indexInParent <= lastVisibleIndex;
            }
            
            public void removeFocusListener(final FocusListener focusListener) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).removeFocusListener(focusListener);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.removeFocusListener(focusListener);
                    }
                }
            }
            
            public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    currentAccessibleContext.removePropertyChangeListener(propertyChangeListener);
                }
            }
            
            public void requestFocus() {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).requestFocus();
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.requestFocus();
                    }
                }
            }
            
            public void setAccessibleDescription(final String accessibleDescription) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    currentAccessibleContext.setAccessibleDescription(accessibleDescription);
                }
            }
            
            public void setAccessibleName(final String accessibleName) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext != null) {
                    currentAccessibleContext.setAccessibleName(accessibleName);
                }
            }
            
            public void setBackground(final Color color) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setBackground(color);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.setBackground(color);
                    }
                }
            }
            
            public void setBounds(final Rectangle bounds) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setBounds(bounds);
                }
            }
            
            public void setCursor(final Cursor cursor) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setCursor(cursor);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.setCursor(cursor);
                    }
                }
            }
            
            public void setEnabled(final boolean b) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setEnabled(b);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.setEnabled(b);
                    }
                }
            }
            
            public void setFont(final Font font) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setFont(font);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.setFont(font);
                    }
                }
            }
            
            public void setForeground(final Color color) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setForeground(color);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.setForeground(color);
                    }
                }
            }
            
            public void setLocation(final Point point) {
                if (this.parent != null && this.parent.contains(point)) {
                    JList.this.ensureIndexIsVisible(this.indexInParent);
                }
            }
            
            public void setSize(final Dimension dimension) {
                final AccessibleContext currentAccessibleContext = this.getCurrentAccessibleContext();
                if (currentAccessibleContext instanceof AccessibleComponent) {
                    ((AccessibleComponent)currentAccessibleContext).setSize(dimension);
                }
                else {
                    final Component currentComponent = this.getCurrentComponent();
                    if (currentComponent != null) {
                        currentComponent.setSize(dimension);
                    }
                }
            }
            
            public void setVisible(final boolean b) {
            }
        }
    }
    
    private class ListSelectionHandler implements ListSelectionListener, Serializable
    {
        public void valueChanged(final ListSelectionEvent listSelectionEvent) {
            JList.this.fireSelectionValueChanged(listSelectionEvent.getFirstIndex(), listSelectionEvent.getLastIndex(), listSelectionEvent.getValueIsAdjusting());
        }
    }
}

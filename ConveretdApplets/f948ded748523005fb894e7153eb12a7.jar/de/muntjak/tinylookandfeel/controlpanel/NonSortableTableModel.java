// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import javax.swing.Icon;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class NonSortableTableModel extends AbstractTableModel
{
    protected Vector data;
    private Record template;
    private Vector columnNames;
    private boolean sortable;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$javax$swing$Icon;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$String;
    
    public NonSortableTableModel() {
        this(false);
    }
    
    protected NonSortableTableModel(final boolean sortable) {
        this.sortable = sortable;
        this.createData();
    }
    
    private void createData() {
        (this.columnNames = new Vector()).add("C1");
        this.columnNames.add("C2");
        this.columnNames.add("C3");
        this.columnNames.add("C4");
        TableColorIcon.reset();
        this.template = null;
        this.data = new Vector();
        for (int i = 0; i < 50; ++i) {
            final Record template = new Record(i + 1);
            this.data.add(template);
            if (this.template == null) {
                this.template = template;
            }
        }
    }
    
    public int getColumnCount() {
        if (this.template == null) {
            return 0;
        }
        return this.template.getColumnCount();
    }
    
    public int getRowCount() {
        return this.data.size();
    }
    
    public Object getValueAt(final int n, final int n2) {
        return this.data.get(n).getValueAt(n2);
    }
    
    public Class getColumnClass(final int n) {
        if (this.template == null) {
            return (NonSortableTableModel.class$java$lang$Object == null) ? (NonSortableTableModel.class$java$lang$Object = class$("java.lang.Object")) : NonSortableTableModel.class$java$lang$Object;
        }
        return this.template.getColumnClass(n);
    }
    
    public String getColumnName(final int n) {
        return this.columnNames.get(n);
    }
    
    public void addColumn(final Class clazz, final int n) {
        this.columnNames.add(n, "N" + (n + 1));
        final Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            iterator.next().addColumn(clazz, n);
        }
        this.fireTableStructureChanged();
    }
    
    public void removeColumn(final int n) {
        this.columnNames.remove(n);
        final Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            iterator.next().removeColumn(n);
        }
        this.fireTableStructureChanged();
    }
    
    public void removeAllRows() {
        this.data.clear();
        this.fireTableDataChanged();
    }
    
    public void createNewData() {
        this.createData();
        this.fireTableStructureChanged();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public static final class TableColorIcon implements Icon
    {
        private Color color;
        private static int hue;
        
        TableColorIcon() {
            this.color = Color.getHSBColor((float)(TableColorIcon.hue / 360.0), 0.5f, 0.9f);
            TableColorIcon.hue += 18;
        }
        
        static void reset() {
            TableColorIcon.hue = 0;
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawRect(n, n2, this.getIconWidth() - 1, this.getIconHeight() - 1);
            graphics.setColor(this.color);
            graphics.fillRect(n + 1, n2 + 1, this.getIconWidth() - 2, this.getIconHeight() - 2);
        }
        
        public int getIconWidth() {
            return 24;
        }
        
        public int getIconHeight() {
            return 12;
        }
        
        public int compareTo(final Object o) {
            if (o == null) {
                return 1;
            }
            if (!(o instanceof TableColorIcon)) {
                return 1;
            }
            final Color color = ((TableColorIcon)o).color;
            if (this.color.getRGB() == color.getRGB()) {
                return 0;
            }
            if (this.color.getRGB() > color.getRGB()) {
                return 1;
            }
            return -1;
        }
        
        static {
            TableColorIcon.hue = 0;
        }
    }
    
    protected class Record
    {
        private Vector values;
        private int oldRow;
        private int newRow;
        
        protected Record(final int n) {
            this.values = new Vector();
            final Integer n2 = new Integer(n);
            final Icon randomIcon = this.createRandomIcon();
            String s;
            if (NonSortableTableModel.this.sortable) {
                if (n == 1) {
                    s = "Sortable";
                }
                else if (n == 2) {
                    s = "Table";
                }
                else if (n == 3) {
                    s = "Data";
                }
                else {
                    s = this.createRandomString();
                }
            }
            else if (n == 1) {
                s = "Non-";
            }
            else if (n == 2) {
                s = "sortable";
            }
            else if (n == 3) {
                s = "Table";
            }
            else if (n == 4) {
                s = "Data";
            }
            else {
                s = this.createRandomString();
            }
            final Integer randomInteger = this.createRandomInteger();
            this.values.add(n2);
            this.values.add(randomIcon);
            this.values.add(s);
            this.values.add(randomInteger);
        }
        
        public Object getValueAt(final int n) {
            if (n < 0 || n >= this.values.size()) {
                return null;
            }
            return this.values.get(n);
        }
        
        public Class getColumnClass(final int n) {
            if (n < 0 || n >= this.values.size()) {
                return (NonSortableTableModel.class$java$lang$Object == null) ? (NonSortableTableModel.class$java$lang$Object = NonSortableTableModel.class$("java.lang.Object")) : NonSortableTableModel.class$java$lang$Object;
            }
            return this.values.get(n).getClass();
        }
        
        public void addColumn(final Class clazz, final int n) {
            if (((NonSortableTableModel.class$java$lang$Double == null) ? (NonSortableTableModel.class$java$lang$Double = NonSortableTableModel.class$("java.lang.Double")) : NonSortableTableModel.class$java$lang$Double).equals(clazz)) {
                this.values.add(n, this.createRandomDouble());
            }
            else if (((NonSortableTableModel.class$javax$swing$Icon == null) ? (NonSortableTableModel.class$javax$swing$Icon = NonSortableTableModel.class$("javax.swing.Icon")) : NonSortableTableModel.class$javax$swing$Icon).equals(clazz)) {
                this.values.add(n, this.createRandomIcon());
            }
            else if (((NonSortableTableModel.class$java$lang$Integer == null) ? (NonSortableTableModel.class$java$lang$Integer = NonSortableTableModel.class$("java.lang.Integer")) : NonSortableTableModel.class$java$lang$Integer).equals(clazz)) {
                this.values.add(n, this.createRandomInteger());
            }
            else if (((NonSortableTableModel.class$java$lang$String == null) ? (NonSortableTableModel.class$java$lang$String = NonSortableTableModel.class$("java.lang.String")) : NonSortableTableModel.class$java$lang$String).equals(clazz)) {
                this.values.add(n, this.createRandomString());
            }
        }
        
        public void removeColumn(final int n) {
            this.values.remove(n);
        }
        
        public int getColumnCount() {
            return this.values.size();
        }
        
        private String createRandomString() {
            final char[] array = new char[3];
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.createRandomChar();
            }
            return new String(array);
        }
        
        private char createRandomChar() {
            return (char)('A' + (char)(Math.random() * 3.0));
        }
        
        private Double createRandomDouble() {
            return new Double((int)(Math.random() * 10000.0) / 100.0);
        }
        
        private Integer createRandomInteger() {
            return new Integer((int)(Math.random() * 20.0) + 1);
        }
        
        private Icon createRandomIcon() {
            return new TableColorIcon();
        }
        
        public int getNewRow() {
            return this.newRow;
        }
        
        public void setNewRow(final int newRow) {
            this.newRow = newRow;
        }
        
        public int getOldRow() {
            return this.oldRow;
        }
        
        public void setOldRow(final int oldRow) {
            this.oldRow = oldRow;
        }
    }
}

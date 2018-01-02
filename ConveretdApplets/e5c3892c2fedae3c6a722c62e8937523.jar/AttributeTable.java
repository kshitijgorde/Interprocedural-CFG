import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class AttributeTable extends JTable
{
    private AttributeModel tableModel;
    private ShapeFileMapLayer layer;
    
    public AttributeTable() {
        this.setModel(this.tableModel = new AttributeModel());
        final TableColumn column = this.getColumnModel().getColumn(0);
        column.setPreferredWidth(150);
        column.setMinWidth(100);
        column.setMaxWidth(150);
        this.getColumnModel().getColumn(1).setPreferredWidth(450);
    }
    
    public void setAttributeNames(final ShapeFileMapLayer layer, final String[] array, final boolean[] array2) {
        this.layer = layer;
        this.tableModel.setAttributeNames(array, array2);
    }
    
    public void setAttributeValues(final ShapeFileMapLayer layer, final String[] array, final boolean[] array2) {
        this.layer = layer;
        this.tableModel.setAttributeValues(array, array2);
    }
    
    public void updateVisible(final boolean[] array) {
        this.tableModel.updateVisible(array);
    }
    
    public ShapeFileMapLayer getCurrentLayer() {
        return this.layer;
    }
    
    class AttributeModel extends AbstractTableModel
    {
        String[] columnNames;
        String[] attributeNames;
        String[] visibleAttributeNames;
        String[] attributeValues;
        String[] visibleAttributeValues;
        
        AttributeModel() {
            this.columnNames = new String[] { "Attribute", "Value" };
        }
        
        @Override
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public void setAttributeNames(final String[] attributeNames, final boolean[] array) {
            this.attributeNames = attributeNames;
            this.attributeValues = new String[attributeNames.length];
            for (int i = 0; i < this.attributeValues.length; ++i) {
                this.attributeValues[i] = "";
            }
            this.updateVisible(array);
        }
        
        public void setAttributeValues(final String[] attributeValues, final boolean[] array) {
            this.attributeValues = attributeValues;
            int n = 0;
            for (int i = 0; i < attributeValues.length; ++i) {
                if (array[i]) {
                    this.visibleAttributeValues[n] = attributeValues[i];
                    ++n;
                }
            }
            this.fireTableDataChanged();
        }
        
        public void updateVisible(final boolean[] array) {
            int n = 0;
            for (int i = 0; i < array.length; ++i) {
                if (array[i]) {
                    ++n;
                }
            }
            this.visibleAttributeNames = new String[n];
            this.visibleAttributeValues = new String[n];
            int n2 = 0;
            for (int j = 0; j < array.length; ++j) {
                if (array[j]) {
                    this.visibleAttributeNames[n2] = this.attributeNames[j];
                    this.visibleAttributeValues[n2] = this.attributeValues[j];
                    ++n2;
                }
            }
            this.fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            if (this.visibleAttributeNames != null) {
                return this.visibleAttributeNames.length;
            }
            return 0;
        }
        
        @Override
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        @Override
        public Object getValueAt(final int n, final int n2) {
            if (n2 == 0) {
                return this.visibleAttributeNames[n];
            }
            return this.visibleAttributeValues[n];
        }
        
        @Override
        public boolean isCellEditable(final int n, final int n2) {
            return true;
        }
    }
}

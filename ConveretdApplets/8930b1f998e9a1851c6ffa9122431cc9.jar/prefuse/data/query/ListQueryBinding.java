// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import javax.swing.event.ListSelectionEvent;
import prefuse.data.expression.Expression;
import prefuse.data.expression.Literal;
import prefuse.data.expression.ColumnExpression;
import prefuse.data.expression.ComparisonPredicate;
import prefuse.util.ui.JToggleGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JComponent;
import prefuse.data.Table;
import javax.swing.event.ListSelectionListener;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.BooleanLiteral;
import prefuse.data.expression.OrPredicate;
import prefuse.util.DataLib;
import prefuse.data.tuple.TupleSet;

public class ListQueryBinding extends DynamicQueryBinding
{
    private static final String ALL = "All";
    private Class m_type;
    private ListModel m_model;
    private Listener m_lstnr;
    private boolean m_includeAll;
    
    public ListQueryBinding(final TupleSet set, final String s) {
        this(set, s, true);
    }
    
    public ListQueryBinding(final TupleSet set, final String s, final boolean includeAll) {
        super(set, s);
        this.m_type = DataLib.inferType(set, s);
        this.m_lstnr = new Listener();
        this.m_includeAll = includeAll;
        this.initPredicate();
        this.initModel();
    }
    
    private void initPredicate() {
        final OrPredicate predicate = new OrPredicate();
        predicate.add(BooleanLiteral.TRUE);
        this.setPredicate(predicate);
    }
    
    private void initModel() {
        if (this.m_model != null) {
            this.m_model.removeListSelectionListener(this.m_lstnr);
        }
        Object[] array;
        if (this.m_tuples instanceof Table) {
            array = ((Table)this.m_tuples).getMetadata(this.m_field).getOrdinalArray();
        }
        else {
            array = DataLib.ordinalArray(this.m_tuples.tuples(), this.m_field);
        }
        (this.m_model = new ListModel(array)).addListSelectionListener(this.m_lstnr);
        if (this.m_includeAll) {
            this.m_model.insertElementAt("All", 0);
            this.m_model.setSelectedItem("All");
        }
    }
    
    public ListModel getListModel() {
        return this.m_model;
    }
    
    public JComponent createComponent() {
        return this.createCheckboxGroup();
    }
    
    public JList createList() {
        final JList list = new JList(this.m_model);
        list.setSelectionModel(this.m_model);
        return list;
    }
    
    public JComboBox createComboBox() {
        return new JComboBox(this.m_model);
    }
    
    public JToggleGroup createCheckboxGroup() {
        return this.createToggleGroup(0);
    }
    
    public JToggleGroup createRadioGroup() {
        return this.createToggleGroup(1);
    }
    
    private JToggleGroup createToggleGroup(final int n) {
        return new JToggleGroup(n, this.m_model, this.m_model);
    }
    
    private ComparisonPredicate getComparison(final Object o) {
        return new ComparisonPredicate(2, new ColumnExpression(this.m_field), Literal.getLiteral(o, this.m_type));
    }
    
    private class Listener implements ListSelectionListener
    {
        public void valueChanged(final ListSelectionEvent listSelectionEvent) {
            final ListModel listModel = (ListModel)listSelectionEvent.getSource();
            final OrPredicate orPredicate = (OrPredicate)ListQueryBinding.this.m_query;
            if (listModel.isSelectionEmpty()) {
                orPredicate.clear();
            }
            else if (ListQueryBinding.this.m_includeAll && listModel.isSelectedIndex(0)) {
                orPredicate.set(BooleanLiteral.TRUE);
            }
            else {
                final int minSelectionIndex = listModel.getMinSelectionIndex();
                final int maxSelectionIndex = listModel.getMaxSelectionIndex();
                int n = 0;
                for (int i = minSelectionIndex; i <= maxSelectionIndex; ++i) {
                    if (listModel.isSelectedIndex(i)) {
                        ++n;
                    }
                }
                if (n == listModel.getSize()) {
                    orPredicate.set(BooleanLiteral.TRUE);
                }
                else if (n == 1) {
                    orPredicate.set(ListQueryBinding.this.getComparison(listModel.getElementAt(minSelectionIndex)));
                }
                else {
                    final Predicate[] array = new Predicate[n];
                    int j = minSelectionIndex;
                    int n2 = 0;
                    while (j <= maxSelectionIndex) {
                        if (listModel.isSelectedIndex(j)) {
                            array[n2++] = ListQueryBinding.this.getComparison(listModel.getElementAt(j));
                        }
                        ++j;
                    }
                    orPredicate.set(array);
                }
            }
        }
    }
}

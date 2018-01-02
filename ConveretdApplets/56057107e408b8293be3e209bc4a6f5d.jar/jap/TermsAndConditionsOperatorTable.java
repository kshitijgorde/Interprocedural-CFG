// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import logging.LogHolder;
import logging.LogType;
import anon.terms.TermsAndConditions;
import anon.infoservice.Database;
import anon.util.JAPMessages;
import java.util.BitSet;
import javax.swing.table.AbstractTableModel;
import anon.infoservice.ServiceOperator;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.util.Vector;
import java.awt.event.MouseListener;
import javax.swing.JTable;

public class TermsAndConditionsOperatorTable extends JTable implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private static final int OPERATOR_COL = 0;
    private static final int DATE_COL = 1;
    private static final int ACCEPTED_COL = 2;
    private static final String OPERATOR_COL_NAMEKEY = "mixOperator";
    private static final String DATE_COL_NAMEKEY = "validFrom";
    private static final String ACCEPTED_COL_NAMEKEY;
    private static final int COLS = 3;
    private TermsAndCondtionsTableController controller;
    static /* synthetic */ Class class$jap$JAPConfTC;
    static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
    
    public TermsAndConditionsOperatorTable() {
        this((Vector)null);
    }
    
    public TermsAndConditionsOperatorTable(final Vector vector) {
        this.setModel((vector != null) ? new OperatorTableModel(vector) : new OperatorTableModel());
        this.setDefaultRenderer((TermsAndConditionsOperatorTable.class$anon$infoservice$ServiceOperator == null) ? (TermsAndConditionsOperatorTable.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : TermsAndConditionsOperatorTable.class$anon$infoservice$ServiceOperator, new OperatorsCellRenderer());
        this.setSelectionMode(0);
        this.getColumnModel().getColumn(0).setMinWidth(200);
        this.getColumnModel().getColumn(0).setPreferredWidth(200);
        this.getColumnModel().getColumn(1).setMinWidth(100);
        this.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.setPreferredSize(new Dimension(450, Math.min(10 + this.getOperators().size() * 12, 100)));
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.controller != null) {
            final ServiceOperator serviceOperator = (ServiceOperator)this.getModel().getValueAt(this.getSelectedRow(), 0);
            final boolean booleanValue = (boolean)this.getModel().getValueAt(this.getSelectedRow(), 2);
            if ((this.getSelectedColumn() == 0 || this.getSelectedColumn() == 1) && mouseEvent.getClickCount() > 1) {
                this.getModel().setValueAt(new Boolean(this.controller.handleOperatorAction(serviceOperator, booleanValue)), this.getSelectedRow(), 2);
            }
            else if (this.getSelectedColumn() != 2) {
                this.controller.handleSelectLineAction(serviceOperator);
            }
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public TermsAndCondtionsTableController getController() {
        return this.controller;
    }
    
    public void setController(final TermsAndCondtionsTableController controller) {
        this.controller = controller;
    }
    
    public void setOperators(final Vector operators) {
        this.checkModel();
        ((OperatorTableModel)this.getModel()).setOperators(operators);
        this.repaint();
    }
    
    public Vector getOperators() {
        this.checkModel();
        return ((OperatorTableModel)this.getModel()).getOperators();
    }
    
    public Vector getTermsAccepted() {
        this.checkModel();
        return ((OperatorTableModel)this.getModel()).getTermsAccepted();
    }
    
    public Vector getTermsRejected() {
        this.checkModel();
        return ((OperatorTableModel)this.getModel()).getTermsRejected();
    }
    
    public boolean areTermsRejected() {
        this.checkModel();
        return ((OperatorTableModel)this.getModel()).areTermsRejected();
    }
    
    private void checkModel() {
        if (this.getModel() == null) {
            throw new IllegalStateException("Current model is null");
        }
        if (!(this.getModel() instanceof OperatorTableModel)) {
            throw new IllegalStateException("Wrong model set " + this.getModel().getClass());
        }
    }
    
    public void setAccepted(final int n, final boolean b) {
        this.setValueAt(new Boolean(b), n, 2);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ACCEPTED_COL_NAMEKEY = ((TermsAndConditionsOperatorTable.class$jap$JAPConfTC == null) ? (TermsAndConditionsOperatorTable.class$jap$JAPConfTC = class$("jap.JAPConfTC")) : TermsAndConditionsOperatorTable.class$jap$JAPConfTC).getName() + "_tncAccepted";
    }
    
    private class OperatorTableModel extends AbstractTableModel
    {
        private Vector m_vecOperators;
        private String[] columnNames;
        private Class[] columnClasses;
        private BitSet accepted;
        static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
        static /* synthetic */ Class class$java$util$Date;
        static /* synthetic */ Class class$java$lang$Boolean;
        
        public OperatorTableModel(final Vector operators) {
            this.m_vecOperators = new Vector();
            this.accepted = new BitSet();
            this.columnClasses = new Class[3];
            this.columnNames = new String[3];
            this.columnClasses[0] = ((OperatorTableModel.class$anon$infoservice$ServiceOperator == null) ? (OperatorTableModel.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : OperatorTableModel.class$anon$infoservice$ServiceOperator);
            this.columnClasses[1] = ((OperatorTableModel.class$java$util$Date == null) ? (OperatorTableModel.class$java$util$Date = class$("java.util.Date")) : OperatorTableModel.class$java$util$Date);
            this.columnClasses[2] = ((OperatorTableModel.class$java$lang$Boolean == null) ? (OperatorTableModel.class$java$lang$Boolean = class$("java.lang.Boolean")) : OperatorTableModel.class$java$lang$Boolean);
            this.columnNames[0] = JAPMessages.getString("mixOperator");
            this.columnNames[1] = JAPMessages.getString("validFrom");
            this.columnNames[2] = JAPMessages.getString(TermsAndConditionsOperatorTable.ACCEPTED_COL_NAMEKEY);
            this.setOperators(operators);
        }
        
        public OperatorTableModel(final TermsAndConditionsOperatorTable termsAndConditionsOperatorTable) {
            this(termsAndConditionsOperatorTable, Database.getInstance((OperatorTableModel.class$anon$infoservice$ServiceOperator == null) ? (OperatorTableModel.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : OperatorTableModel.class$anon$infoservice$ServiceOperator).getEntryList());
        }
        
        public int getRowCount() {
            return this.m_vecOperators.size();
        }
        
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return n2 == 2 || n2 == 0;
        }
        
        public Class getColumnClass(final int n) {
            return this.columnClasses[n];
        }
        
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public Object getValueAt(final int n, final int n2) {
            try {
                switch (n2) {
                    case 0: {
                        return this.m_vecOperators.elementAt(n);
                    }
                    case 1: {
                        final TermsAndConditions termsAndConditions = TermsAndConditions.getTermsAndConditions(this.m_vecOperators.elementAt(n));
                        return (termsAndConditions != null) ? termsAndConditions.getDate() : null;
                    }
                    case 2: {
                        return new Boolean(this.accepted.get(n));
                    }
                    default: {
                        throw new IndexOutOfBoundsException("No definition for column " + n2);
                    }
                }
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.GUI, ex);
                return null;
            }
        }
        
        public void setValueAt(final Object o, final int n, final int n2) {
            switch (n2) {
                case 0: {
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    final boolean booleanValue = (boolean)o;
                    this.setAccepted(n, booleanValue);
                    if (TermsAndConditionsOperatorTable.this.controller != null) {
                        try {
                            TermsAndConditionsOperatorTable.this.controller.handleAcceptAction((ServiceOperator)this.getValueAt(n, 0), booleanValue);
                        }
                        catch (IllegalStateException ex) {
                            this.setAccepted(n, !booleanValue);
                        }
                        break;
                    }
                    break;
                }
                default: {
                    throw new IndexOutOfBoundsException("No definition for column " + n2);
                }
            }
        }
        
        public void setOperators(final Vector vector) {
            this.m_vecOperators.removeAllElements();
            for (int i = 0; i < this.accepted.size(); ++i) {
                this.accepted.clear(i);
            }
            if (vector != null) {
                int n = 0;
                for (int j = 0; j < vector.size(); ++j) {
                    final ServiceOperator element = vector.elementAt(j);
                    if (element instanceof ServiceOperator && element.hasTermsAndConditions()) {
                        this.m_vecOperators.addElement(element);
                        if (TermsAndConditions.getTermsAndConditions(element).isAccepted()) {
                            this.accepted.set(n);
                        }
                        ++n;
                    }
                }
            }
        }
        
        public Vector getOperators() {
            return this.m_vecOperators;
        }
        
        public ServiceOperator getOperator(final int n) {
            return (ServiceOperator)this.getValueAt(n, 0);
        }
        
        public Vector getTermsAccepted() {
            return this.getTermsWithAcceptStatus(true);
        }
        
        public Vector getTermsRejected() {
            return this.getTermsWithAcceptStatus(false);
        }
        
        public boolean areTermsRejected() {
            for (int i = 0; i < this.m_vecOperators.size(); ++i) {
                if (!this.accepted.get(i)) {
                    return true;
                }
            }
            return false;
        }
        
        public void setAccepted(final int n, final boolean b) {
            if (b) {
                this.accepted.set(n);
            }
            else {
                this.accepted.clear(n);
            }
        }
        
        private Vector getTermsWithAcceptStatus(final boolean b) {
            final Vector<TermsAndConditions> vector = new Vector<TermsAndConditions>();
            for (int i = 0; i < this.m_vecOperators.size(); ++i) {
                final ServiceOperator serviceOperator = this.m_vecOperators.elementAt(i);
                if (serviceOperator.hasTermsAndConditions() && this.accepted.get(i) == b) {
                    vector.addElement(TermsAndConditions.getTermsAndConditions(serviceOperator));
                }
            }
            return vector;
        }
        
        static /* synthetic */ Class class$(final String s) {
            try {
                return Class.forName(s);
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
    }
}

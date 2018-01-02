// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableColumnModel;
import abc.parser.TuneChangeEvent;
import abc.parser.TuneBookListenerInterface;
import javax.swing.table.AbstractTableModel;
import abc.notation.Tune;
import abc.parser.TuneBook;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.Vector;
import javax.swing.JTable;

public class TuneBookTable extends JTable
{
    public static final int REFERENCE_NUMBER_COLUMN = 10;
    public static final int TITLE_COLUMN = 1;
    public static final int KEY_COLUMN = 2;
    public static final int COMPOSER_COLUMN = 3;
    public static final int INFORMATION_COLUMN = 4;
    public static final int DISCOGRAPHY_COLUMN = 5;
    public static final int ORIGIN_COLUMN = 6;
    public static final int RHYTHM_COLUMN = 7;
    public static final int BOOK_COLUMN = 8;
    public static final int SOURCE_COLUMN = 9;
    private Vector m_tunes;
    private Vector m_columns;
    private PopupMenu m_popMenu;
    private TuneBookTableModel m_model;
    
    public TuneBookTable() {
        this.m_tunes = null;
        this.m_columns = null;
        this.m_popMenu = null;
        this.m_model = null;
        this.setModel(this.m_model = new TuneBookTableModel());
        this.setColumnModel(new TuneBookTableColumnModel());
        this.setSelectionMode(0);
        this.m_popMenu = new PopupMenu(this);
        this.addMouseListenerToHeaderInTable();
    }
    
    private void addMouseListenerToHeaderInTable() {
        final MouseAdapter listMouseListener = new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (!TuneBookTable.this.m_popMenu.isVisible()) {
                    final int columnIndex = TuneBookTable.this.getColumnModel().getColumnIndexAtX(e.getX());
                    final TuneColumn column = (TuneColumn)TuneBookTable.this.getColumnModel().getColumn(columnIndex);
                    if (!column.isAscendingOrdered() && !column.isDescendingOrdered()) {
                        column.sort(true);
                    }
                    else if (column.isAscendingOrdered()) {
                        column.sort(false);
                    }
                    else if (column.isDescendingOrdered()) {
                        column.sort(true);
                    }
                    for (int i = 0; i < TuneBookTable.this.getColumnModel().getColumnCount(); ++i) {
                        if (!TuneBookTable.this.getColumnModel().getColumn(i).equals(column)) {
                            ((TuneColumn)TuneBookTable.this.getColumnModel().getColumn(i)).setIsAscendingOrdered(false);
                            ((TuneColumn)TuneBookTable.this.getColumnModel().getColumn(i)).setIsDescendingOrdered(false);
                        }
                    }
                }
            }
            
            public void mousePressed(final MouseEvent e) {
            }
            
            public void mouseReleased(final MouseEvent e) {
                if (e.isPopupTrigger()) {
                    TuneBookTable.this.m_popMenu.show(TuneBookTable.this, e.getX(), e.getY());
                }
            }
        };
        this.getTableHeader().addMouseListener(listMouseListener);
    }
    
    public void setTuneBook(final TuneBook book) {
        this.m_model.setTuneBook(book);
        try {
            final int[] numbers = { 0 };
            for (int i = 0; i < numbers.length; ++i) {}
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TuneBook getTuneBook() {
        return this.m_model.m_bookModel;
    }
    
    public Tune getSelectedTune() {
        int selectedIndex = -1;
        int viewColumnNumber = -1;
        selectedIndex = this.getSelectionModel().getLeadSelectionIndex();
        if (selectedIndex != -1) {
            viewColumnNumber = this.convertColumnIndexToView(10);
            final int selectedTuneReferenceNumber = (int)this.getValueAt(selectedIndex, viewColumnNumber);
            return this.m_model.m_bookModel.getTune(selectedTuneReferenceNumber);
        }
        return null;
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    private class TuneBookTableModel extends AbstractTableModel implements TuneBookListenerInterface
    {
        private TuneBook m_bookModel;
        
        public TuneBookTableModel(final TuneBookTable tuneBookTable, final TuneBook tuneBook) {
            this(tuneBookTable);
            this.setTuneBook(tuneBook);
        }
        
        public TuneBookTableModel() {
            this.m_bookModel = null;
            TuneBookTable.this.m_tunes = new Vector();
        }
        
        public void setTuneBook(final TuneBook tuneBook) {
            if (this.m_bookModel != null) {
                this.m_bookModel.removeListener(this);
            }
            (this.m_bookModel = tuneBook).addListener(this);
            TuneBookTable.this.m_tunes = this.m_bookModel.toVector();
            this.fireTableDataChanged();
        }
        
        public int getColumnCount() {
            final int col = TuneBookTable.this.getColumnModel().getColumnCount();
            return col;
        }
        
        public int getRowCount() {
            return TuneBookTable.this.m_tunes.size();
        }
        
        public Object getValueAt(final int row, final int col) {
            try {
                final TuneBookTableColumnModel model = (TuneBookTableColumnModel)TuneBookTable.this.getColumnModel();
                final TuneColumn column = (TuneColumn)model.getColumnFromModelIndex(col);
                final Object obj = column.getValueFor(TuneBookTable.this.m_tunes.elementAt(row));
                return obj;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }
        
        public void tuneChanged(final TuneChangeEvent e) {
            System.out.println(this.getClass().getName() + " tune change detection " + e);
            if (e.getType() == 2) {
                TuneBookTable.this.m_tunes.addElement(e.getTune());
                this.fireTableDataChanged();
            }
            else {
                final int ref = e.getTune().getReferenceNumber();
                for (int i = 0; i < TuneBookTable.this.m_tunes.size(); ++i) {
                    if (TuneBookTable.this.m_tunes.elementAt(i).getReferenceNumber() == ref) {
                        if (e.getType() == 1) {
                            TuneBookTable.this.m_tunes.removeElementAt(i);
                        }
                        else {
                            final Tune tune = this.m_bookModel.getTune(ref);
                            TuneBookTable.this.m_tunes.setElementAt(tune, i);
                        }
                        this.fireTableDataChanged();
                        break;
                    }
                }
            }
        }
    }
    
    private class TuneBookTableColumnModel extends DefaultTableColumnModel
    {
        public TuneBookTableColumnModel() {
            TableColumn col = new ReferenceNumberColumn();
            col.setModelIndex(10);
            this.addColumn(col);
            col = new TitleColumn();
            col.setModelIndex(1);
            this.addColumn(col);
            col = new KeyColumn();
            col.setModelIndex(2);
            this.addColumn(col);
            col = new ComposerColumn();
            col.setModelIndex(3);
            this.addColumn(col);
            col = new RhythmColumn();
            col.setModelIndex(7);
            this.addColumn(col);
        }
        
        private TableColumn getColumnFromModelIndex(final int index) {
            for (int i = 0; i < this.getColumnCount(); ++i) {
                if (this.getColumn(i).getModelIndex() == index) {
                    return this.getColumn(i);
                }
            }
            return null;
        }
    }
    
    public class ReferenceNumberColumn extends TuneColumn
    {
        public ReferenceNumberColumn() {
            this.setHeaderValue("Number");
        }
        
        public Object getValueFor(final Tune tune) {
            return new Integer(tune.getReferenceNumber());
        }
    }
    
    public class AreaColumn extends TuneColumn
    {
        public AreaColumn() {
            this.setHeaderValue("Area");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getArea();
        }
    }
    
    public class BookColumn extends TuneColumn
    {
        public BookColumn() {
            this.setHeaderValue("Book");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getBook();
        }
    }
    
    public class ComposerColumn extends TuneColumn
    {
        public ComposerColumn() {
            this.setHeaderValue("Composer");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getComposer();
        }
    }
    
    public class DiscographyColumn extends TuneColumn
    {
        public DiscographyColumn() {
            this.setHeaderValue("Discography");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getDiscography();
        }
    }
    
    public class TitleColumn extends TuneColumn
    {
        public TitleColumn() {
            this.setHeaderValue("Title");
        }
        
        public Object getValueFor(final Tune tune) {
            if (tune.getTitles() != null) {
                return tune.getTitles()[0];
            }
            return null;
        }
    }
    
    public class KeyColumn extends TuneColumn
    {
        public KeyColumn() {
            this.setHeaderValue("Key");
        }
        
        public Object getValueFor(final Tune tune) {
            if (tune.getKey() != null) {
                return tune.getKey().toLitteralNotation();
            }
            return null;
        }
    }
    
    public class OriginColumn extends TuneColumn
    {
        public OriginColumn() {
            this.setHeaderValue("Origin");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getOrigin();
        }
    }
    
    public class InformationColumn extends TuneColumn
    {
        public InformationColumn() {
            this.setHeaderValue("Information");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getInformation();
        }
    }
    
    public class RhythmColumn extends TuneColumn
    {
        public RhythmColumn() {
            this.setHeaderValue("Rhythm");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getRhythm();
        }
    }
    
    public class SourceColumn extends TuneColumn
    {
        public SourceColumn() {
            this.setHeaderValue("Source");
        }
        
        public Object getValueFor(final Tune tune) {
            return tune.getSource();
        }
    }
    
    public abstract class TuneColumn extends TableColumn
    {
        private boolean isAscendingOrdered;
        private boolean isDescendingOrdered;
        
        public TuneColumn() {
            this.isAscendingOrdered = false;
            this.isDescendingOrdered = false;
        }
        
        public abstract Object getValueFor(final Tune p0);
        
        public void sort(final boolean ascendingOrder) {
            Comparator comp = null;
            if (ascendingOrder) {
                comp = new Comparator() {
                    public int compare(final Object obj1, final Object obj2) {
                        final Comparable o1 = (Comparable)TuneColumn.this.getValueFor((Tune)obj1);
                        final Comparable o2 = (Comparable)TuneColumn.this.getValueFor((Tune)obj2);
                        if (o1 == null && o2 == null) {
                            return 0;
                        }
                        if (o1 == null) {
                            return -1;
                        }
                        if (o2 == null) {
                            return 1;
                        }
                        return o1.compareTo(o2);
                    }
                };
                this.isAscendingOrdered = true;
                this.isDescendingOrdered = false;
            }
            else {
                comp = new Comparator() {
                    public int compare(final Object obj1, final Object obj2) {
                        final Comparable o1 = (Comparable)TuneColumn.this.getValueFor((Tune)obj1);
                        final Comparable o2 = (Comparable)TuneColumn.this.getValueFor((Tune)obj2);
                        if (o1 == null && o2 == null) {
                            return 0;
                        }
                        if (o1 == null) {
                            return 1;
                        }
                        if (o2 == null) {
                            return -1;
                        }
                        return o2.compareTo(o1);
                    }
                };
                this.isAscendingOrdered = false;
                this.isDescendingOrdered = true;
            }
            Collections.sort((List<Object>)TuneBookTable.this.m_tunes, comp);
            TuneBookTable.this.updateUI();
        }
        
        public boolean isAscendingOrdered() {
            return this.isAscendingOrdered;
        }
        
        public boolean isDescendingOrdered() {
            return this.isDescendingOrdered;
        }
        
        void setIsAscendingOrdered(final boolean order) {
            this.isAscendingOrdered = order;
        }
        
        void setIsDescendingOrdered(final boolean order) {
            this.isDescendingOrdered = order;
        }
    }
}

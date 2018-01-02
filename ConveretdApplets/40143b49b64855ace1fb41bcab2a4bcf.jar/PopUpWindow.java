import java.awt.Image;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.List;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class PopUpWindow extends Frame implements Runnable
{
    AppletContext appletContext;
    URL baseURL;
    private Button bFirst;
    private Button bPrevious;
    private Button bNext;
    private Button bLast;
    private Button bShowAll;
    private Button bSelectAll;
    private Button bSearch;
    private Button bAbout;
    private Button bHelp;
    private Button bSearchAll;
    private Label l_SortOrder;
    private Label l_Filter;
    private Label l_Search;
    private Label l_KEYField;
    private ScrollGrid Grid;
    private TextField KEYField;
    private TextField StatusField;
    private TextArea outField;
    private Choice SortOrderChoice;
    private Choice IndexChoice;
    private List filterList;
    private int oldSort;
    private int oldIndex;
    public Catalog Cat;
    public Vector ResultSet;
    public CatalogView Parent;
    private boolean AllSelected;
    public FontMetrics DataMetrics;
    public Font ComponentFont;
    public Font DataFont;
    public Font StatusFont;
    private Vector filterVector;
    private int oldFilterIndex;
    public MyDialog AboutDlg;
    public MyDialog ImageDlg;
    private int CurrentRecNr;
    private int count;
    public Thread LoadImageThread;
    private int REC_NR;
    String IMAGE_BASE;
    String IMAGE_URL;
    
    private void goNext() {
        if (this.Grid.ROW < this.ResultSet.size() - 1) {
            ++this.Grid.ROW;
            if (this.Grid.ROW >= this.Grid.ty + this.Grid.visibleROWS) {
                final ScrollGrid grid = this.Grid;
                ++grid.ty;
                this.Grid.newty = true;
            }
            else {
                this.Grid.newty = false;
            }
            this.Grid.COL = -1;
            this.Grid.renderNext();
            this.Grid.repaint();
            this.goSetText();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (event.target == this.bFirst) {
            this.StatusField.setText("Go to the first Record");
            return true;
        }
        if (event.target == this.bLast) {
            this.StatusField.setText("Go to the last Record");
            return true;
        }
        if (event.target == this.bPrevious) {
            this.StatusField.setText("Go to the previous Record");
            return true;
        }
        if (event.target == this.bNext) {
            this.StatusField.setText("Go to the Next Record");
            return true;
        }
        if (event.target == this.bSearch) {
            this.StatusField.setText("Search selected index by KEYword");
            return true;
        }
        if (event.target == this.bSearchAll) {
            this.StatusField.setText("Search all RECORDs by string comparison");
            return true;
        }
        if (event.target == this.bShowAll) {
            this.StatusField.setText("Show all RECORDs");
            return true;
        }
        if (event.target == this.bHelp) {
            this.StatusField.setText("Open Help Window");
            return true;
        }
        if (event.target == this.bAbout) {
            this.StatusField.setText("Information about this applet");
            return true;
        }
        if (event.target == this.bPrevious) {
            this.StatusField.setText("Go to the previous Record");
            return true;
        }
        if (event.target == this.IndexChoice) {
            this.StatusField.setText("Select a search index");
            return true;
        }
        if (event.target == this.SortOrderChoice) {
            this.StatusField.setText("Select the RECORDs sort order");
            return true;
        }
        if (event.target == this.filterList) {
            this.StatusField.setText("List all RECORDs in the Category");
            return true;
        }
        if (event.target == this.KEYField) {
            this.StatusField.setText("Enter a KEYword to search in the selected index");
            return true;
        }
        return false;
    }
    
    private void goFilter() {
        final String selectedItem = this.filterList.getSelectedItem();
        final Index index = this.Cat.Indexes[this.oldFilterIndex];
        if (this.filterList.getSelectedIndex() == 0) {
            this.ResultSet = this.Cat.RECORDs;
        }
        else {
            this.ResultSet = index.get(selectedItem);
        }
        if (this.ResultSet != null) {
            if (this.Parent.pSORT_CHOICE) {
                this.qsort(this.ResultSet, 0, this.ResultSet.size() - 1, this.SortOrderChoice.getSelectedIndex());
            }
            this.Grid.tx = 0;
            this.Grid.horz.setValue(this.Grid.tx);
            this.goFirst();
        }
    }
    
    private void goPrevious() {
        if (this.Grid.ROW > 0) {
            --this.Grid.ROW;
            if (this.Grid.ROW < this.Grid.ty) {
                final ScrollGrid grid = this.Grid;
                --grid.ty;
                this.Grid.newty = true;
            }
            else {
                this.Grid.newty = false;
            }
            this.Grid.COL = -1;
            this.Grid.renderPrevious();
            this.Grid.repaint();
            this.goSetText();
        }
    }
    
    private void goLast() {
        if (this.ResultSet.size() - this.Grid.visibleROWS > 0) {
            this.Grid.ty = this.ResultSet.size() - this.Grid.visibleROWS;
        }
        else {
            this.Grid.ty = 0;
        }
        this.Grid.COL = -1;
        this.Grid.ROW = this.ResultSet.size() - 1;
        this.Grid.render();
        this.Grid.repaint();
        this.goSetText();
    }
    
    private void goRight() {
        if (this.Grid.COL < this.Cat.FieldsVisible.length - 1) {
            ++this.Grid.COL;
            if (this.Grid.COL > 0) {
                if (this.Grid.AccCOLS[this.Grid.COL] > this.Grid.visibleWIDTH - this.Grid.leftCOLSWIDTH - this.Grid.vert.size().width) {
                    this.Grid.tx = this.Cat.FieldsAcWIDTH[this.Grid.COL - 1];
                }
            }
            else {
                this.Grid.tx = 0;
            }
        }
        this.Grid.horz.setValue(this.Grid.tx);
        this.Grid.render();
        this.Grid.repaint();
        this.getField();
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (event.target instanceof Component) {
            this.StatusField.setText("");
        }
        return true;
    }
    
    public PopUpWindow(final CatalogView parent) {
        this.oldSort = 0;
        this.oldIndex = 0;
        this.AllSelected = false;
        this.oldFilterIndex = -1;
        this.CurrentRecNr = 0;
        this.count = 0;
        this.LoadImageThread = null;
        this.REC_NR = 0;
        this.IMAGE_BASE = null;
        this.IMAGE_URL = null;
        this.Parent = parent;
        this.setBackground(Color.lightGray);
        this.setTitle(this.Parent.pWINDOW_TITLE);
        this.appletContext = this.Parent.getAppletContext();
        this.baseURL = this.Parent.getCodeBase();
        this.Cat = new Catalog(this.Parent.FIELDs);
        this.ComponentFont = new Font("Helvetica", this.Parent.pFONT_TYPE, this.Parent.pFONT_SIZE);
        this.DataFont = new Font("Helvetica", this.Parent.pDATAFONT_TYPE, this.Parent.pDATAFONT_SIZE);
        this.StatusFont = new Font("Helvetica", 1, 11);
        this.DataMetrics = this.getFontMetrics(this.DataFont);
        this.ReadFileURL(this.Parent.pSOURCE_FILE, this.Parent.FIELDs.length);
        if (this.Cat.FieldsVisible.length < 1) {
            this.Parent.ProgressBar.ProgressField.setText("Error: No Visible Fields");
            System.exit(0);
        }
        if (this.Cat.Indexes.length < 1) {
            this.Parent.ProgressBar.ProgressField.setText("Error: No Indexed Fields");
            System.exit(0);
        }
        this.Parent.ProgressBar.ProgressField.setText("Loading Please Wait..");
        this.ResultSet = new Vector(this.Cat.RECORDs.size());
        this.ResultSet = this.Cat.RECORDs;
        this.AboutDlg = new MyDialog(this.Parent, MyDialog.ABOUT_DLG, this.Parent.pFONT_SIZE);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        final BorderPanel borderPanel = new BorderPanel();
        borderPanel.setBackground(Color.lightGray);
        borderPanel.setLayout(new FlowLayout(0, 5, 2));
        final BorderPanel borderPanel2 = new BorderPanel();
        borderPanel2.setBackground(Color.lightGray);
        borderPanel2.setLayout(new FlowLayout(0, 5, 2));
        final Panel panel = new Panel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(0, 4, 0, 2));
        final Panel panel2 = new Panel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new GridLayout(2, 1, 2, 2));
        final BorderPanel borderPanel3 = new BorderPanel();
        borderPanel3.setBackground(Color.lightGray);
        borderPanel3.setLayout(new BorderLayout(2, 2));
        final BorderPanel borderPanel4 = new BorderPanel();
        borderPanel4.setBackground(Color.lightGray);
        final Panel panel3 = new Panel();
        panel3.setBackground(Color.lightGray);
        panel3.setLayout(new FlowLayout(1, 2, 2));
        final Panel panel4 = new Panel();
        panel4.setBackground(Color.lightGray);
        panel4.setLayout(new FlowLayout(1, 2, 2));
        final BorderPanel borderPanel5 = new BorderPanel();
        borderPanel5.setBackground(Color.lightGray);
        borderPanel5.setLayout(new FlowLayout(1, 2, 2));
        final Panel panel5 = new Panel();
        panel5.setBackground(Color.lightGray);
        panel5.setLayout(new FlowLayout(1, 2, 2));
        final Panel panel6 = new Panel();
        panel6.setBackground(Color.lightGray);
        panel6.setLayout(new FlowLayout(1, 2, 2));
        this.bFirst = new Button("<<");
        this.bPrevious = new Button("<");
        this.bNext = new Button(">");
        this.bLast = new Button(">>");
        this.bSearch = new Button("Search Index");
        this.bSearchAll = new Button("Search All");
        this.bShowAll = new Button("Show All");
        this.bSelectAll = new Button(" Select All ");
        this.bHelp = new Button("Help");
        this.bAbout = new Button("About");
        this.l_SortOrder = new Label("Sort", 2);
        this.l_Search = new Label("Index", 2);
        this.l_Filter = new Label("Filter", 2);
        this.l_KEYField = new Label("Search", 2);
        this.SortOrderChoice = new Choice();
        this.IndexChoice = new Choice();
        this.KEYField = new TextField("", 20);
        this.filterList = new List(4, false);
        this.outField = new TextArea("", this.Parent.pTOPVISIBLE_ROWS, 10);
        this.Grid = new ScrollGrid(this);
        this.StatusField = new TextField("Status : ");
        this.ImageDlg = new MyDialog(this, MyDialog.IMAGE_DLG, this.Parent.pFONT_SIZE);
        this.bFirst.setFont(this.ComponentFont);
        this.bPrevious.setFont(this.ComponentFont);
        this.bNext.setFont(this.ComponentFont);
        this.bLast.setFont(this.ComponentFont);
        this.bSearch.setFont(this.ComponentFont);
        this.bSearchAll.setFont(this.ComponentFont);
        this.bShowAll.setFont(this.ComponentFont);
        this.bSelectAll.setFont(this.ComponentFont);
        this.bHelp.setFont(this.ComponentFont);
        this.bAbout.setFont(this.ComponentFont);
        this.l_SortOrder.setFont(this.ComponentFont);
        this.l_Search.setFont(this.ComponentFont);
        this.l_Filter.setFont(this.ComponentFont);
        this.l_KEYField.setFont(this.ComponentFont);
        this.SortOrderChoice.setFont(this.DataFont);
        this.IndexChoice.setFont(this.DataFont);
        this.KEYField.setFont(this.DataFont);
        this.filterList.setFont(this.DataFont);
        this.outField.setFont(this.DataFont);
        this.outField.setBackground(Color.white);
        this.outField.setEditable(false);
        this.StatusField.setFont(this.StatusFont);
        this.StatusField.setEditable(false);
        panel.add(this.bFirst);
        panel.add(this.bPrevious);
        panel.add(this.bNext);
        panel.add(this.bLast);
        borderPanel.add(panel);
        if (this.Parent.pSEARCH_BUTTON) {
            panel3.add(this.bSearch);
        }
        if (this.Parent.pSEARCHALL_BUTTON) {
            panel3.add(this.bSearchAll);
        }
        if (this.Parent.pSHOWALL_BUTTON) {
            panel3.add(this.bShowAll);
        }
        if (this.Parent.pHELP_BUTTON) {
            panel3.add(this.bHelp);
        }
        panel3.add(this.bAbout);
        borderPanel.add(panel3);
        panel2.add(borderPanel);
        for (int i = 0; i < this.Cat.FieldsSORTED.length; ++i) {
            this.SortOrderChoice.addItem(this.Cat.FIELDs[this.Cat.FieldsSORTED[i]].Name);
        }
        this.SortOrderChoice.select(0);
        if (this.Parent.pSORT_CHOICE && this.Cat.FieldsSORTED.length > 0) {
            if (this.Parent.pSORT_ORDER < this.Cat.FIELDs.length) {}
            this.qsort(this.Cat.RECORDs, 0, this.Cat.RECORDs.size() - 1, this.Parent.pSORT_ORDER);
        }
        panel4.add(this.l_SortOrder);
        panel4.add(this.SortOrderChoice);
        if (this.Parent.pSORT_CHOICE && this.Cat.FieldsSORTED.length > 0) {
            borderPanel2.add(panel4);
        }
        for (int j = 0; j < this.Cat.Indexes.length; ++j) {
            if (this.Cat.FIELDs[this.Cat.FieldsIndexed[j]].FILTER) {
                this.IndexChoice.addItem(this.Cat.Indexes[j].Name + "...");
            }
            else {
                this.IndexChoice.addItem(this.Cat.Indexes[j].Name);
            }
        }
        this.IndexChoice.select(0);
        panel5.add(this.l_Search);
        panel5.add(this.IndexChoice);
        if (this.Parent.pINDEX_CHOICE) {
            borderPanel2.add(panel5);
        }
        panel6.add(this.l_KEYField);
        panel6.add(this.KEYField);
        if (this.Parent.pKEY_FIELD) {
            borderPanel2.add(panel6);
        }
        panel2.add(borderPanel2);
        this.validate();
        layout.setConstraints(panel2, gridBagConstraints);
        this.add(panel2);
        this.filterList.setBackground(Color.white);
        borderPanel5.add(this.l_Filter);
        this.filterList.addItem("all");
        this.filterVector = new Vector();
        if (this.Cat.FieldsFiltered.length > 0) {
            final Enumeration<String> keys = this.Cat.Indexes[this.Cat.FieldsFiltered[0]].keys();
            while (keys.hasMoreElements()) {
                this.filterVector.addElement(keys.nextElement());
            }
            this.fsort(this.filterVector, 0, this.filterVector.size() - 1);
            for (int k = 0; k < this.filterVector.size(); ++k) {
                this.filterList.addItem((String)this.filterVector.elementAt(k));
            }
            borderPanel5.add(this.filterList);
            if (this.Parent.pFILTER_CHOICE) {
                layout.setConstraints(borderPanel5, gridBagConstraints);
                this.add(borderPanel5);
            }
            this.oldFilterIndex = this.Cat.FieldsFiltered[0];
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(borderPanel4, gridBagConstraints);
        this.add(borderPanel4);
        borderPanel3.add("Center", this.outField);
        layout.setConstraints(borderPanel3, gridBagConstraints);
        if (borderPanel3.countComponents() > 0) {
            this.add(borderPanel3);
        }
        layout.setConstraints(this.Grid, gridBagConstraints);
        this.add(this.Grid);
        layout.setConstraints(this.StatusField, gridBagConstraints);
        this.add(this.StatusField);
        this.pack();
        this.Grid.resetVisibleWIDTH();
        this.Grid.resize(this.Grid.preferredSize());
        this.Grid.resizeHorz();
        this.Grid.resizeVert();
        this.pack();
        this.validate();
        this.setResizable(false);
        this.goSetText();
    }
    
    private void _fsort(final Vector vector, int n, int i) {
        while (i > n) {
            int j = n;
            int n2 = i;
            final String s = vector.elementAt(n);
            while (j < n2) {
                for (String s2 = vector.elementAt(n2); s2.compareTo(s) > 0; s2 = vector.elementAt(n2)) {
                    --n2;
                }
                vector.setElementAt(vector.elementAt(n2), j);
                for (String s3 = vector.elementAt(j); j < n2 && s3.compareTo(s) <= 0; ++j, s3 = vector.elementAt(j)) {}
                vector.setElementAt(vector.elementAt(j), n2);
            }
            vector.setElementAt(s, j);
            if (j - n < i - j) {
                this._fsort(vector, n, j - 1);
                n = j + 1;
            }
            else {
                this._fsort(vector, j + 1, i);
                i = j - 1;
            }
        }
    }
    
    private void fsort(final Vector vector, final int n, final int n2) {
        if (vector == null) {
            return;
        }
        this._fsort(vector, n, n2);
    }
    
    private void goPageUp() {
        if (this.Grid.ty > this.Grid.visibleROWS - 1) {
            this.Grid.ty = this.Grid.ty - this.Grid.visibleROWS + 1;
        }
        else {
            this.Grid.ty = 0;
        }
        this.Grid.COL = -1;
        this.Grid.ROW = this.Grid.ty;
        this.Grid.render();
        this.Grid.repaint();
        this.goSetText();
    }
    
    private void goLeft() {
        if (this.Grid.COL > -1) {
            --this.Grid.COL;
            if (this.Grid.COL > 0) {
                if (this.Cat.FieldsAcWIDTH[this.Grid.COL - 1] < this.Grid.tx) {
                    this.Grid.tx = this.Cat.FieldsAcWIDTH[this.Grid.COL - 1];
                }
            }
            else {
                this.Grid.tx = 0;
            }
        }
        this.Grid.horz.setValue(this.Grid.tx);
        this.Grid.render();
        this.Grid.repaint();
        this.getField();
    }
    
    private void _nqsort(final Vector vector, int n, int i, final int n2) {
        try {
            this.StatusField.setText("Sorting: " + this.CurrentRecNr);
            if (this.count == 10) {
                this.StatusField.setText("Sorting: " + this.CurrentRecNr);
                this.count = 0;
            }
            while (i > n) {
                int j = n;
                int n3 = i;
                final Record record = vector.elementAt(n);
                while (j < n3) {
                    for (Record record2 = vector.elementAt(n3); Double.valueOf(record2.FIELDvalue[n2]) > Double.valueOf(record.FIELDvalue[n2]); record2 = vector.elementAt(n3)) {
                        --n3;
                    }
                    vector.setElementAt(vector.elementAt(n3), j);
                    for (Record record3 = vector.elementAt(j); j < n3 && Double.valueOf(record3.FIELDvalue[n2]) <= Double.valueOf(record.FIELDvalue[n2]); ++j, record3 = vector.elementAt(j)) {}
                    vector.setElementAt(vector.elementAt(j), n3);
                }
                vector.setElementAt(record, j);
                if (j - n < i - j) {
                    this._nqsort(vector, n, j - 1, n2);
                    n = j + 1;
                }
                else {
                    this._nqsort(vector, j + 1, i, n2);
                    i = j - 1;
                }
            }
            ++this.CurrentRecNr;
            ++this.count;
        }
        catch (NumberFormatException ex) {
            System.out.println("_nqsort A data value did not contain a valid number");
        }
    }
    
    private void goSearch(final String s) {
        this.ResultSet = this.Cat.Indexes[this.IndexChoice.getSelectedIndex()].get(s.toLowerCase());
        if (this.ResultSet != null) {
            if (this.Parent.pSORT_CHOICE) {
                this.qsort(this.ResultSet, 0, this.ResultSet.size() - 1, this.SortOrderChoice.getSelectedIndex());
            }
        }
        else {
            this.ResultSet = new Vector();
        }
        this.Grid.tx = 0;
        this.goFirst();
    }
    
    private void goFirst() {
        this.Grid.ty = 0;
        this.Grid.COL = -1;
        this.Grid.ROW = 0;
        this.Grid.render();
        this.Grid.repaint();
        this.goSetText();
    }
    
    private void goURL(final String s, final String s2, final String s3) {
        URL url = null;
        try {
            url = new URL(s + s2);
        }
        catch (MalformedURLException ex) {
            this.StatusField.setText("Malformed URL: " + s2);
        }
        if (url != null) {
            this.appletContext.showDocument(url, s3);
        }
    }
    
    private void _qsort(final Vector vector, int n, int i, final int n2) {
        this.StatusField.setText("Sorting: " + this.CurrentRecNr);
        if (this.count == 10) {
            this.StatusField.setText("Sorting: " + this.CurrentRecNr);
            this.count = 0;
        }
        while (i > n) {
            int j = n;
            int n3 = i;
            final Record record = vector.elementAt(n);
            while (j < n3) {
                for (Record record2 = vector.elementAt(n3); record2.FIELDvalue[n2].compareTo(record.FIELDvalue[n2]) > 0; record2 = vector.elementAt(n3)) {
                    --n3;
                }
                vector.setElementAt(vector.elementAt(n3), j);
                for (Record record3 = vector.elementAt(j); j < n3 && record3.FIELDvalue[n2].compareTo(record.FIELDvalue[n2]) <= 0; ++j, record3 = vector.elementAt(j)) {}
                vector.setElementAt(vector.elementAt(j), n3);
            }
            vector.setElementAt(record, j);
            if (j - n < i - j) {
                this._qsort(vector, n, j - 1, n2);
                n = j + 1;
            }
            else {
                this._qsort(vector, j + 1, i, n2);
                i = j - 1;
            }
        }
        ++this.CurrentRecNr;
        ++this.count;
    }
    
    private void getField() {
        if (this.Grid.COL < 0) {
            this.goSetText();
            return;
        }
        if (this.Grid.ROW < this.ResultSet.size()) {
            this.outField.setText(this.ResultSet.elementAt(this.Grid.ROW).FIELDvalue[this.Cat.FieldsVisible[this.Grid.COL]]);
        }
    }
    
    private void goSearchAll(final String s) {
        this.StatusField.setText("Searching: ");
        int n = 0;
        int n2 = 0;
        this.ResultSet = new Vector();
        final Enumeration<Record> elements = (Enumeration<Record>)this.Cat.RECORDs.elements();
        while (elements.hasMoreElements()) {
            if (n2 == 10) {
                this.StatusField.setText("Searching: " + n);
                n2 = 0;
            }
            final Record record = elements.nextElement();
            final String record2 = record.getRECORD();
            int n3 = 0;
            for (boolean b = true; n3 < record2.length() - s.length() && b; ++n3) {
                if (record2.regionMatches(true, n3, s, 0, s.length())) {
                    this.ResultSet.addElement(record);
                    b = false;
                }
            }
            ++n;
            ++n2;
        }
        if (this.ResultSet != null && this.Parent.pSORT_CHOICE) {
            this.qsort(this.ResultSet, 0, this.ResultSet.size() - 1, this.SortOrderChoice.getSelectedIndex());
        }
        this.Grid.tx = 0;
        this.goFirst();
    }
    
    private void qsort(final Vector vector, final int n, final int n2, final int n3) {
        if (vector == null) {
            return;
        }
        this.CurrentRecNr = 0;
        this.count = 0;
        if (this.Cat.FIELDs[n3].Type == 4) {
            this._nqsort(vector, n, n2, n3);
        }
        else {
            this._qsort(vector, n, n2, n3);
        }
        this.StatusField.setText("");
    }
    
    public void show() {
        super.show();
        this.Grid.requestFocus();
    }
    
    public boolean action(final Event event, final Object o) {
        final Object target = event.target;
        if (target == this.bFirst) {
            this.goFirst();
            return true;
        }
        if (target == this.bShowAll) {
            this.filterList.select(0);
            this.goFilter();
            return true;
        }
        if (target == this.bPrevious) {
            this.goPrevious();
            return true;
        }
        if (target == this.bNext) {
            this.goNext();
            return true;
        }
        if (target == this.bLast) {
            this.goLast();
            return true;
        }
        if (target == this.bSearch) {
            if (this.KEYField.getText().length() > 1) {
                this.goSearch(this.KEYField.getText());
            }
            return true;
        }
        if (target == this.bSearchAll) {
            if (this.KEYField.getText().length() > 1) {
                this.goSearchAll(this.KEYField.getText());
            }
            return true;
        }
        if (target == this.bHelp) {
            this.goURL(this.Parent.pHELP_BASE, this.Parent.pHELP_DOC, this.Parent.pHELP_TARGET);
            return true;
        }
        if (target == this.bAbout) {
            this.AboutDlg.show();
        }
        if (target == this.KEYField) {
            if (this.KEYField.getText().length() > 1) {
                this.goSearch(this.KEYField.getText());
            }
            return true;
        }
        if (target == this.SortOrderChoice) {
            if (this.SortOrderChoice.getSelectedIndex() != this.oldSort) {
                if (this.Parent.pSORT_CHOICE) {
                    this.qsort(this.ResultSet, 0, this.ResultSet.size() - 1, this.Cat.FieldsSORTED[this.SortOrderChoice.getSelectedIndex()]);
                }
                this.goFirst();
                this.oldSort = this.SortOrderChoice.getSelectedIndex();
            }
            return true;
        }
        if (target == this.IndexChoice) {
            if (this.IndexChoice.getSelectedIndex() != this.oldIndex) {
                this.oldIndex = this.IndexChoice.getSelectedIndex();
                if (this.Cat.FIELDs[this.Cat.FieldsIndexed[this.oldIndex]].FILTER) {
                    this.oldFilterIndex = this.oldIndex;
                    this.filterVector.removeAllElements();
                    final Enumeration<String> keys = this.Cat.Indexes[this.IndexChoice.getSelectedIndex()].keys();
                    while (keys.hasMoreElements()) {
                        this.filterVector.addElement(keys.nextElement());
                    }
                    this.fsort(this.filterVector, 0, this.filterVector.size() - 1);
                    this.filterList.clear();
                    this.filterList.addItem("all");
                    for (int i = 0; i < this.filterVector.size(); ++i) {
                        this.filterList.addItem((String)this.filterVector.elementAt(i));
                    }
                }
            }
            return true;
        }
        if (target == this.filterList) {
            this.goFilter();
            return true;
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target == this.Grid) {
            this.getField();
            if (this.Grid.COL > -1) {
                if (this.Cat.FIELDs[this.Cat.FieldsVisible[this.Grid.COL]].Type == Field.URL) {
                    this.goURL(this.Cat.FIELDs[this.Grid.COL].BASE_URL, this.ResultSet.elementAt(this.Grid.ROW).FIELDvalue[this.Cat.FieldsVisible[this.Grid.COL]], this.Cat.FIELDs[this.Grid.COL].TARGET);
                }
                if (this.Cat.FIELDs[this.Cat.FieldsVisible[this.Grid.COL]].Type == Field.IMAGE) {
                    this.IMAGE_BASE = this.Cat.FIELDs[this.Grid.COL].BASE_IMAGE;
                    this.IMAGE_URL = this.ResultSet.elementAt(this.Grid.ROW).FIELDvalue[this.Cat.FieldsVisible[this.Grid.COL]];
                    this.REC_NR = this.Grid.ROW + 1;
                    this.ImageDlg.show();
                    (this.LoadImageThread = new Thread(this)).start();
                }
            }
        }
        return false;
    }
    
    private void goPageDown() {
        if (this.Grid.ty < this.ResultSet.size() - (2 * this.Grid.visibleROWS - 1)) {
            this.Grid.ty = this.Grid.ty + this.Grid.visibleROWS - 1;
        }
        else {
            this.Grid.ty = this.ResultSet.size() - this.Grid.visibleROWS;
        }
        this.Grid.COL = -1;
        this.Grid.ROW = this.Grid.ty;
        this.Grid.render();
        this.Grid.repaint();
        this.goSetText();
    }
    
    private void ReadFileURL(final String s, final int n) {
        System.out.println("Catalog View Applet\n http://www2.dk-online.dk/users/nkt");
        boolean b = false;
        DataInputStream dataInputStream = null;
        URL url = null;
        final String string = new Character(this.Parent.pDELIMITER).toString();
        try {
            url = new URL(this.baseURL, s);
        }
        catch (MalformedURLException ex) {
            this.Parent.ProgressBar.ProgressField.setText("Error: Could not open Source File: " + ex);
            System.exit(0);
        }
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(url.openStream()));
        }
        catch (Exception ex2) {
            this.Parent.ProgressBar.ProgressField.setText("Read File exception: probably file not found");
            System.exit(0);
        }
        String s2 = " ";
        try {
            s2 = dataInputStream.readLine();
        }
        catch (Exception ex3) {
            this.Parent.ProgressBar.ProgressField.setText("Read File exception: mis-formatted line 1");
            b = true;
            System.exit(0);
        }
        int n2 = 0;
        int n3 = 0;
        while (!b) {
            try {
                ++n2;
                if (++n3 == 5) {
                    this.Parent.ProgressBar.RecField.setText(Integer.toString(n2));
                    n3 = 0;
                }
                final Record record = new Record(n);
                final StringTokenizer stringTokenizer = new StringTokenizer(s2, string);
                for (int n4 = 0; stringTokenizer.hasMoreTokens() && n4 < n; ++n4) {
                    if (this.Cat.FIELDs[n4].Type == 3) {
                        String string2 = "";
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken().trim(), "|");
                        while (stringTokenizer2.hasMoreTokens()) {
                            string2 = string2 + stringTokenizer2.nextToken().trim() + "\n";
                        }
                        record.FIELDvalue[n4] = string2;
                    }
                    else {
                        record.FIELDvalue[n4] = stringTokenizer.nextToken().trim();
                    }
                }
                this.Cat.AddToCatalog(record);
                s2 = dataInputStream.readLine();
            }
            catch (Exception ex4) {
                this.Parent.ProgressBar.ProgressField.setText("Read File exception: mis-formatted line: " + n2);
                b = true;
            }
            if (s2 == null) {
                b = true;
            }
            else {
                if (s2.length() > 1) {
                    continue;
                }
                b = true;
            }
        }
    }
    
    public void run() {
        URL url = null;
        try {
            url = new URL(this.IMAGE_BASE + this.IMAGE_URL);
        }
        catch (MalformedURLException ex3) {
            this.ImageDlg.ProgressField.setText("Malformed URL: " + url);
            this.LoadImageThread = null;
        }
        catch (Exception ex) {
            this.ImageDlg.ProgressField.setText("Image Load Failure: " + ex.getMessage());
        }
        if (url != null) {
            this.ImageDlg.ProgressField.setText("Loading Image: " + this.IMAGE_URL);
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Image image = this.appletContext.getImage(url);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex4) {
                this.ImageDlg.ProgressField.setText("Image Load Failure");
                this.LoadImageThread = null;
            }
            catch (Exception ex2) {
                this.ImageDlg.ProgressField.setText("Image Load Failure: " + ex2.getMessage());
            }
            this.ImageDlg.ImageField.setImage(image);
            this.ImageDlg.ProgressField.setText("");
            this.ImageDlg.StatusLabel.setText("  Record Nr: " + this.REC_NR);
            this.ImageDlg.StatusLabel2.setText("  Image: " + this.IMAGE_URL);
        }
        this.LoadImageThread = null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            if (this.AboutDlg != null) {
                this.AboutDlg.hide();
            }
            if (this.ImageDlg != null) {
                this.ImageDlg.hide();
            }
            this.hide();
        }
        else if (event.id == 403 && event.target == this.Grid) {
            switch (event.key) {
                case 1005: {
                    this.goNext();
                    break;
                }
                case 1001: {
                    this.goLast();
                    break;
                }
                case 1000: {
                    this.goFirst();
                    break;
                }
                case 1006: {
                    this.goLeft();
                    break;
                }
                case 1003: {
                    this.goPageDown();
                    break;
                }
                case 1002: {
                    this.goPageUp();
                    break;
                }
                case 1007: {
                    this.goRight();
                    break;
                }
                case 1004: {
                    this.goPrevious();
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    private void goSetText() {
        String string = "";
        int i;
        for (i = 0; i < this.Cat.FIELDs.length - 1; ++i) {
            string = string + this.ResultSet.elementAt(this.Grid.ROW).FIELDvalue[i] + "\n";
        }
        this.outField.setText(string + this.ResultSet.elementAt(this.Grid.ROW).FIELDvalue[i]);
    }
}

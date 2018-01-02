// 
// Decompiled by Procyon v0.5.30
// 

package risco_table;

import java.awt.MediaTracker;
import java.awt.Image;
import java.util.Enumeration;
import java.awt.Component;
import risco_table.graphics.Border;
import risco_table.table.Titlebar;
import risco_table.graphics.BorderRaised;
import risco_table.table.DataArea;
import java.awt.Container;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import risco_table.graphics.StringFormatter;
import risco_table.TableCell.CAddressTableCell;
import risco_table.TableCell.CDateTableCell;
import risco_table.TableCell.CDecimalTableCell;
import risco_table.TableCell.CMoneyTableCell;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.util.Properties;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import risco_table.table.ColumnSortEvent;
import risco_table.TableCell.CIntegerTableCell;
import risco_table.TableCell.ITableCell;
import risco_table.TableCell.CStringTableCell;
import risco_table.table.SimpleSortableTableRow;
import risco_table.table.ColumnDefinition;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import risco_table.util.ColumnSortableVector;
import java.util.Hashtable;
import java.util.Vector;
import risco_table.table.Table;
import java.applet.Applet;

public class TableApplet extends Applet
{
    private boolean m_bUseOriginalColumnIndex;
    private int m_iCols;
    private int m_iSelectionMode;
    private boolean m_bAllowColAdd;
    private boolean m_bCheckmarks;
    private boolean m_bLineNumbers;
    private boolean m_bShowButtons;
    private boolean m_bSortable;
    private boolean m_bGotMoreData;
    private boolean m_bFiltered;
    private boolean m_bAllowRowDragDrop;
    private boolean m_bAllowColumnDragDrop;
    private boolean m_bSyncChecksWithSelections;
    private boolean m_bHasColumnsInTDC;
    private boolean m_bBasicColors;
    private int m_iDataBackground_oddRow;
    private int m_iDataBackground_evenRow;
    private int m_iDataForeground_oddRow;
    private int m_iDataForeground_evenRow;
    private int m_iColumnTitleBackground;
    private int m_iColumnTitleForeground;
    private int m_iRowTitleBackground;
    private int m_iRowTitleForeground;
    private String m_sDateFormat;
    private String m_sOnRowDblClick;
    private String m_sOnRowClick;
    private String m_sOnLoad;
    private String m_sRowSplit;
    private String m_sCellSplit;
    private String m_sURL;
    private String m_sOnModifyClick;
    private String m_sOnAcceptClick;
    private Table m_oTable;
    private Vector m_oRows;
    private Vector m_oCols;
    private Vector m_oFRows;
    private String m_sOnSort;
    private TableAppletArray m_oArray;
    private boolean m_bHasPainted;
    private Hashtable m_hashColWidths;
    private String m_sAcceptButtonNormalImagePath;
    private String m_sAcceptButtonPressedImagePath;
    private String m_sModifyButtonNormalImagePath;
    private String m_sModifyButtonPressedImagePath;
    
    public TableApplet() {
        this.m_bUseOriginalColumnIndex = true;
        this.m_iCols = 0;
        this.m_iSelectionMode = 0;
        this.m_bAllowColAdd = true;
        this.m_bCheckmarks = false;
        this.m_bLineNumbers = false;
        this.m_bShowButtons = false;
        this.m_bSortable = true;
        this.m_bGotMoreData = true;
        this.m_bFiltered = false;
        this.m_bAllowRowDragDrop = false;
        this.m_bAllowColumnDragDrop = true;
        this.m_bSyncChecksWithSelections = false;
        this.m_bHasColumnsInTDC = true;
        this.m_bBasicColors = false;
        this.m_iDataBackground_oddRow = 16777215;
        this.m_iDataBackground_evenRow = 14474460;
        this.m_iDataForeground_oddRow = 0;
        this.m_iDataForeground_evenRow = 0;
        this.m_iColumnTitleBackground = 3762588;
        this.m_iColumnTitleForeground = 16777215;
        this.m_iRowTitleBackground = 11385558;
        this.m_iRowTitleForeground = 0;
        this.m_sDateFormat = "MM/dd/yyyy";
        this.m_sOnRowDblClick = new String();
        this.m_sOnRowClick = new String();
        this.m_sOnLoad = new String();
        this.m_sRowSplit = new String(":");
        this.m_sCellSplit = new String(",");
        this.m_sURL = new String();
        this.m_sOnModifyClick = new String();
        this.m_sOnAcceptClick = new String();
        this.m_oTable = new Table();
        this.m_oRows = new ColumnSortableVector();
        this.m_oCols = new Vector();
        this.m_oFRows = new ColumnSortableVector();
        this.m_sOnSort = new String();
        this.m_oArray = null;
        this.m_bHasPainted = false;
        this.m_sAcceptButtonNormalImagePath = "";
        this.m_sAcceptButtonPressedImagePath = "";
        this.m_sModifyButtonNormalImagePath = "";
        this.m_sModifyButtonPressedImagePath = "";
    }
    
    public void setAcceptButtonNormalImagePath(final String sPath) {
        this.m_sAcceptButtonNormalImagePath = sPath;
    }
    
    public void setAcceptButtonPressedImagePath(final String sPath) {
        this.m_sAcceptButtonPressedImagePath = sPath;
    }
    
    public void setModifyButtonNormalImagePath(final String sPath) {
        this.m_sModifyButtonNormalImagePath = sPath;
    }
    
    public void setModifyButtonPressedImagePath(final String sPath) {
        this.m_sModifyButtonPressedImagePath = sPath;
    }
    
    public void setArray(final TableAppletArray oArray) {
        this.m_oArray = oArray;
    }
    
    public Table getTable() {
        return this.m_oTable;
    }
    
    public boolean getHasPainted() {
        return this.m_bHasPainted;
    }
    
    public void destroy() {
        this.m_oCols.removeAllElements();
        this.m_oRows.removeAllElements();
        this.m_oFRows.removeAllElements();
        this.m_oTable.removeAll();
    }
    
    public void setDataBackground_oddRow(final int iVal) {
        this.m_iDataBackground_oddRow = iVal;
    }
    
    public void setDataBackground_evenRow(final int iVal) {
        this.m_iDataBackground_evenRow = iVal;
    }
    
    public void setDataForeground_oddRow(final int iVal) {
        this.m_iDataForeground_oddRow = iVal;
    }
    
    public void setDataForeground_evenRow(final int iVal) {
        this.m_iDataForeground_evenRow = iVal;
    }
    
    public void setColumnTitleBackground(final int iVal) {
        this.m_iColumnTitleBackground = iVal;
    }
    
    public void setColumnTitleForeground(final int iVal) {
        this.m_iColumnTitleForeground = iVal;
    }
    
    public void setRowTitleBackground(final int iVal) {
        this.m_iRowTitleBackground = iVal;
    }
    
    public void setRowTitleForeground(final int iVal) {
        this.m_iRowTitleForeground = iVal;
    }
    
    public boolean getSyncChecksWithSelections() {
        return this.m_bSyncChecksWithSelections;
    }
    
    public void setSyncChecksWithSelections(final boolean bValue) {
        this.m_bSyncChecksWithSelections = bValue;
    }
    
    public void init() {
        if (this.m_oArray != null) {
            this.m_oArray.init();
        }
        else {
            this.setLayout(new BorderLayout());
        }
    }
    
    public void UpdateData() {
        synchronized (this.m_oRows) {
            this.m_oRows.removeAllElements();
            this.setTDCData(this.m_sURL, true);
            this.m_oTable.rowsAdded(this.m_oRows.size());
        }
        // monitorexit(this.m_oRows)
    }
    
    public String getAppletInfo() {
        return "RISCO Table Applet. Copyright (C) 2000 RISCO, Inc.";
    }
    
    public void unfilterRows() {
        if (this.m_bFiltered) {
            this.m_oTable.setRows(this.m_oRows);
            this.m_oTable.getTitlebar().setAllowColumnDragDrop(this.m_bAllowColumnDragDrop);
            this.m_bFiltered = false;
        }
    }
    
    public void toggleCells(int iColumnIndex, final String sTrueVal, final String sFalseVal) {
        final int[] oRows = this.m_oTable.getSelectedIndexes();
        if (oRows.length > 0) {
            iColumnIndex = this.m_oTable.getOriginalColumnIndex(iColumnIndex);
            final int iType = this.m_oCols.elementAt(iColumnIndex).getColumnType();
            if (iType == 1 || iType == 2) {
                for (int iCount = 0; iCount < oRows.length; ++iCount) {
                    switch (iType) {
                        case 1: {
                            this.m_oTable.getRows().elementAt(oRows[iCount]).getStrings()[iColumnIndex] = ((this.m_oTable.getRows().elementAt(oRows[iCount]).getStrings()[iColumnIndex].compare(new CStringTableCell(sTrueVal)) == 0) ? new CStringTableCell(sFalseVal) : new CStringTableCell(sTrueVal));
                            break;
                        }
                        case 2: {
                            this.m_oTable.getRows().elementAt(oRows[iCount]).getStrings()[iColumnIndex] = ((this.m_oTable.getRows().elementAt(oRows[iCount]).getStrings()[iColumnIndex].compare(new CIntegerTableCell(sTrueVal)) == 0) ? new CIntegerTableCell(sFalseVal) : new CIntegerTableCell(sTrueVal));
                            break;
                        }
                    }
                }
                this.refreshTable();
            }
        }
    }
    
    public void sortColumns(final String cols, final String asc) {
        int len = 0;
        final int[] columnIndexes = this.splitStringToInt(cols, "|");
        final boolean[] ascending = this.splitStringToBool(asc, "|");
        len = columnIndexes.length;
        final int[] columnTypes = new int[len];
        final String[] sDateFormats = new String[len];
        for (int k = 0; k < len; ++k) {
            columnTypes[k] = this.m_oCols.elementAt(columnIndexes[k]).getColumnType();
            sDateFormats[k] = this.m_oCols.elementAt(columnIndexes[k]).getDateFormat();
        }
        this.m_oTable.sortColumn(new ColumnSortEvent(this.m_oRows, columnIndexes, columnTypes, ascending, sDateFormats));
    }
    
    private final int[] splitStringToInt(final String s, final String delim) {
        final StringTokenizer st = new StringTokenizer(s, delim);
        final int[] ret = new int[st.countTokens()];
        int k = 0;
        while (st.hasMoreTokens()) {
            ret[k] = Integer.parseInt(st.nextToken());
            ++k;
        }
        return ret;
    }
    
    private final boolean[] splitStringToBool(final String s, final String delim) {
        final StringTokenizer st = new StringTokenizer(s, delim);
        final boolean[] ret = new boolean[st.countTokens()];
        int k = 0;
        while (st.hasMoreTokens()) {
            ret[k] = Boolean.valueOf(st.nextToken());
            ++k;
        }
        return ret;
    }
    
    private final String[] splitString(final String s, final String delim) {
        final StringTokenizer st = new StringTokenizer(s, delim);
        final String[] ret = new String[st.countTokens()];
        int k = 0;
        while (st.hasMoreTokens()) {
            ret[k] = st.nextToken();
            ++k;
        }
        return ret;
    }
    
    public String getColumnSortableFlags() {
        final StringBuffer sb = new StringBuffer();
        final boolean[] ret = this.m_oTable.getTitlebar().getColumnSortableFlags();
        if (ret == null) {
            return "";
        }
        for (int k = 0; k < ret.length; ++k) {
            if (k > 0) {
                sb.append("|");
            }
            sb.append(ret[k]);
        }
        return sb.toString();
    }
    
    public String getSortColumnMulti() {
        final StringBuffer sb = new StringBuffer();
        final int[] ret = this.m_oTable.getTitlebar().getSortColumnMulti();
        if (ret == null) {
            return "";
        }
        for (int k = 0; k < ret.length; ++k) {
            if (k > 0) {
                sb.append("|");
            }
            sb.append(ret[k]);
        }
        return sb.toString();
    }
    
    public String getSortAscendingMulti() {
        final StringBuffer sb = new StringBuffer();
        final boolean[] ret = this.m_oTable.getTitlebar().getSortAscendingMulti();
        if (ret == null) {
            return "";
        }
        for (int k = 0; k < ret.length; ++k) {
            if (k > 0) {
                sb.append("|");
            }
            sb.append(ret[k]);
        }
        return sb.toString();
    }
    
    public boolean getShowLineNumbers() {
        return this.m_bLineNumbers;
    }
    
    public boolean getMultisort() {
        return this.m_oTable.getTitlebar().getMultisort();
    }
    
    public int getSortColumn() {
        return this.m_oTable.getTitlebar().getSortColumn();
    }
    
    public boolean getAscending() {
        return this.m_oTable.getTitlebar().getSortAscending();
    }
    
    public void filterRows() {
        final int[] ary = this.m_oTable.getSelectedIndexes();
        if (ary.length > 0) {
            this.m_oTable.uncheckAll();
            this.m_oTable.getTitlebar().setAllowColumnDragDrop(false);
            if (!this.m_bFiltered) {
                synchronized (this.m_oFRows) {
                    this.m_oFRows.removeAllElements();
                    for (int iCount = 0; iCount < ary.length; ++iCount) {
                        this.m_oFRows.addElement(this.m_oRows.elementAt(ary[iCount]));
                        this.m_oTable.select(ary[iCount], false);
                    }
                }
                // monitorexit(this.m_oFRows)
                this.m_oTable.setRows(this.m_oFRows);
            }
            else {
                final int[] aTemp = new int[this.m_oTable.getRows().size() - ary.length];
                int i = 0;
                for (int iCount2 = 0; iCount2 < this.m_oFRows.size(); ++iCount2) {
                    if (!this.m_oTable.isSelected(iCount2)) {
                        aTemp[i++] = iCount2;
                    }
                    else {
                        this.m_oTable.select(iCount2, false);
                    }
                }
                this.m_oTable.removeRowsByIndex(aTemp);
            }
            this.m_bFiltered = true;
        }
    }
    
    public boolean isFiltered() {
        return this.m_bFiltered;
    }
    
    public void enableSorting() {
        this.m_oTable.getTitlebar().setColumnSortableFlags(this.getBoolArray(true, this.m_iCols));
        this.m_oTable.getTitlebar().repaint();
    }
    
    public void setSortable(final boolean bVal) {
        this.m_bSortable = bVal;
    }
    
    public void setUseOriginalColumnIndex(final boolean bVal) {
        this.m_bUseOriginalColumnIndex = bVal;
    }
    
    public void setSelectionMode(final int iSelectionMode) {
        if (iSelectionMode < 4) {
            this.m_iSelectionMode = iSelectionMode;
        }
    }
    
    public void setColumnWidth(final int iIndex, final int iWidth) {
        final int[] aryInts = this.m_oTable.getColumnWidths();
        if (aryInts != null) {
            if (aryInts.length > iIndex) {
                aryInts[iIndex] = iWidth;
                this.m_oTable.setColumnWidths(aryInts);
            }
        }
        else {
            if (this.m_hashColWidths == null) {
                this.m_hashColWidths = new Hashtable();
            }
            this.m_hashColWidths.put(Integer.toString(iIndex), Integer.toString(iWidth));
        }
    }
    
    public int getColumnWidth(final int iIndex) {
        final int[] aryInts = this.m_oTable.getColumnWidths();
        if (aryInts != null && aryInts.length > iIndex) {
            return aryInts[iIndex];
        }
        return -1;
    }
    
    public void printGrid() {
        final Toolkit oToolkit = Toolkit.getDefaultToolkit();
        final PrintJob oPrintJob = oToolkit.getPrintJob(new Frame(), "", new Properties());
        if (oPrintJob != null) {
            final Graphics oGraphics = oPrintJob.getGraphics();
            oGraphics.dispose();
            oPrintJob.end();
        }
    }
    
    public void onRowDblClick(final String sVal) {
        this.m_sOnRowDblClick = sVal;
    }
    
    public void onRowClick(final String sVal) {
        this.m_sOnRowClick = sVal;
    }
    
    public void onSort(final String sVal) {
        this.m_sOnSort = sVal;
    }
    
    public void onModifyClick(final String sVal) {
        this.m_sOnModifyClick = sVal;
    }
    
    public void onAcceptClick(final String sVal) {
        this.m_sOnAcceptClick = sVal;
    }
    
    public void onLoad(final String sVal) {
        this.m_sOnLoad = sVal;
    }
    
    public void setCellDelim(final String sVal) {
        this.m_sCellSplit = sVal;
    }
    
    public void setRowDelim(final String sVal) {
        this.m_sRowSplit = sVal;
    }
    
    public String getRowDelim() {
        return this.m_sRowSplit;
    }
    
    public String getCellDelim() {
        return this.m_sCellSplit;
    }
    
    public void setShowChecks(final boolean bVal) {
        this.m_bCheckmarks = bVal;
    }
    
    public void setShowNumbers(final boolean bVal) {
        this.m_bLineNumbers = bVal;
    }
    
    public void setShowButtons(final boolean bVal) {
        this.m_bShowButtons = bVal;
    }
    
    public void setDateFormat(final String sDateFormat) {
        this.m_sDateFormat = sDateFormat;
    }
    
    public void setAllowRowDragDrop(final boolean bVal) {
        this.m_bAllowRowDragDrop = bVal;
    }
    
    public void setHasColumnsInTDC(final boolean bVal) {
        this.m_bHasColumnsInTDC = bVal;
    }
    
    public void setBasicColors(final boolean bVal) {
        this.m_bBasicColors = bVal;
    }
    
    public void addRow(String sVal) {
        sVal = this.replaceQuoted(sVal, ',', '\u001b');
        final StringTokenizer oSplit = new StringTokenizer(sVal, this.m_sCellSplit);
        final ITableCell[] oRows = new ITableCell[oSplit.countTokens()];
        int iCount = 0;
        int iColumnIndex = 0;
        while (oSplit.hasMoreTokens() && iCount <= this.m_iCols) {
            iColumnIndex = this.m_oTable.getOriginalColumnIndex(iCount);
            final ColumnDefinition oColumn = this.m_oCols.elementAt(iColumnIndex);
            switch (oColumn.getColumnType()) {
                case 5: {
                    oRows[iColumnIndex] = new CMoneyTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')));
                    break;
                }
                case 1: {
                    oRows[iColumnIndex] = new CStringTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')));
                    break;
                }
                case 2: {
                    oRows[iColumnIndex] = new CIntegerTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')));
                    break;
                }
                case 4: {
                    oRows[iColumnIndex] = new CDecimalTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')));
                    break;
                }
                case 3: {
                    oRows[iColumnIndex] = new CDateTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')), oColumn.getDateFormat());
                    break;
                }
                case 6: {
                    oRows[iColumnIndex] = new CAddressTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')), this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')), this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')));
                    break;
                }
                default: {
                    oRows[iColumnIndex] = new CStringTableCell(this.unquoteString(this.replaceQuoted(oSplit.nextToken(), '\u001b', ',')));
                    break;
                }
            }
            ++iCount;
        }
        synchronized (this.m_oRows) {
            this.m_oRows.addElement(new SimpleSortableTableRow(oRows));
        }
        // monitorexit(this.m_oRows)
        this.m_bAllowColAdd = false;
    }
    
    public void addColumn(final String sVal, final String sDataType) {
        if (this.m_bAllowColAdd) {
            final StringFormatter oStrAlignCenter = new StringFormatter(4, 4);
            final ColumnDefinition oColumn = new ColumnDefinition(sVal, 3, oStrAlignCenter, oStrAlignCenter);
            int iType = 1;
            if (sDataType.compareTo("STRING") == 0 || sDataType.compareTo("STR") == 0) {
                iType = 1;
            }
            else if (sDataType.compareTo("INTEGER") == 0 || sDataType.compareTo("INT") == 0) {
                iType = 2;
            }
            else if (sDataType.compareTo("DATE") == 0 || sDataType.compareTo("DTE") == 0) {
                iType = 3;
            }
            else if (sDataType.compareTo("DECIMAL") == 0 || sDataType.compareTo("DEC") == 0) {
                iType = 4;
            }
            else if (sDataType.compareTo("MONEY") == 0 || sDataType.compareTo("MNY") == 0) {
                iType = 5;
            }
            else if (sDataType.compareTo("ADDRESS") == 0 || sDataType.compareTo("ADDR") == 0) {
                iType = 6;
            }
            oColumn.setColumnType(iType);
            oColumn.setDateFormat(this.m_sDateFormat);
            oColumn.setDataFormatter(new StringFormatter(1, 4));
            oColumn.setTitleFont(new Font("Tahoma", 1, 11));
            oColumn.setDataFont(new Font("Tahoma", 0, 11));
            oColumn.setDataForeground(new Color(this.m_iDataForeground_evenRow), new Color(this.m_iDataForeground_oddRow));
            if (!this.m_bBasicColors) {
                oColumn.setTitleBackground(new Color(this.m_iColumnTitleBackground));
                oColumn.setTitleForeground(new Color(this.m_iColumnTitleForeground));
                oColumn.setDataBackground(new Color(this.m_iDataBackground_evenRow), new Color(this.m_iDataBackground_oddRow));
            }
            else {
                oColumn.setTitleBackground(Color.white);
                oColumn.setTitleForeground(Color.black);
                oColumn.setDataBackground(new Color(15921906), Color.white);
            }
            synchronized (this.m_oCols) {
                this.m_oCols.addElement(oColumn);
            }
            // monitorexit(this.m_oCols)
            ++this.m_iCols;
        }
    }
    
    private String replaceQuoted(String sValue, final char bOld, final char bNew) {
        boolean bInQuotes = false;
        for (int i = 0; i < sValue.length(); ++i) {
            if (bInQuotes && sValue.charAt(i) == bOld) {
                sValue = String.valueOf(sValue.substring(0, i)) + bNew + sValue.substring(i + 1);
            }
            else if (sValue.charAt(i) == '\"') {
                bInQuotes = !bInQuotes;
            }
        }
        return sValue;
    }
    
    private String quoteString(String sValue) {
        sValue = String.valueOf('\"') + sValue.replace('\"', ' ');
        sValue = String.valueOf(sValue) + '\"';
        return sValue;
    }
    
    private String unquoteString(String sValue) {
        if (sValue.length() > 1 && sValue.substring(0, 1).compareTo("\"") == 0 && sValue.substring(sValue.length() - 1).compareTo("\"") == 0) {
            sValue = sValue.substring(1, sValue.length() - 1);
        }
        return sValue;
    }
    
    public void setColumns(String sColDefs) {
        sColDefs = this.replaceQuoted(sColDefs, ',', '\u001b');
        final StringTokenizer oSplitCols = new StringTokenizer(sColDefs, this.m_sCellSplit);
        final String[] aColDefs = new String[oSplitCols.countTokens()];
        int iCount = 0;
        while (oSplitCols.hasMoreTokens()) {
            aColDefs[iCount] = oSplitCols.nextToken();
            aColDefs[iCount] = this.unquoteString(this.replaceQuoted(aColDefs[iCount], '\u001b', ','));
            final StringTokenizer oSplitType = new StringTokenizer(aColDefs[iCount], new String(":"));
            String sName = new String();
            String sType = new String("STR");
            if (oSplitType.hasMoreTokens()) {
                sName = oSplitType.nextToken();
                if (oSplitType.hasMoreTokens()) {
                    sType = oSplitType.nextToken();
                }
            }
            this.addColumn(sName, sType);
            ++iCount;
        }
    }
    
    public void setUrl(final String sURL) {
        this.m_sURL = sURL;
    }
    
    public String getUrl() {
        return this.m_sURL;
    }
    
    public boolean gotMoreData() {
        return this.m_bGotMoreData;
    }
    
    public boolean processTDCData(final String sData, final boolean bHasColumns) {
        try {
            final StringTokenizer oSplitRows = new StringTokenizer(sData, this.m_sRowSplit);
            if (oSplitRows.hasMoreTokens()) {
                final int iStartRow = this.m_oRows.size();
                final String sLine = oSplitRows.nextToken();
                if (bHasColumns) {
                    this.setColumns(sLine);
                }
                else {
                    this.addRow(sLine);
                }
                while (oSplitRows.hasMoreTokens()) {
                    final String sRow = oSplitRows.nextToken();
                    this.addRow(sRow);
                }
                if (iStartRow > 0) {
                    this.m_oTable.getDataArea().scrollToRow(iStartRow);
                }
            }
            else {
                this.m_bGotMoreData = false;
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public void scrollToEnd() {
        this.m_oTable.getDataArea().scrollToRow(this.m_oTable.getRows().size());
    }
    
    public void setTDCData(final String sURL, final boolean bHasColumns) {
        if (sURL.length() > 0) {
            try {
                final URL urlData = new URL(sURL);
                final InputStream isData = urlData.openConnection().getInputStream();
                String sAllData = new String();
                while (isData.available() != 0) {
                    final byte[] bData = new byte[isData.available()];
                    isData.read(bData);
                    sAllData = String.valueOf(sAllData) + new String(bData);
                }
                this.processTDCData(sAllData, bHasColumns);
            }
            catch (Exception e) {
                System.out.println("\n" + e.getMessage() + ", URL:" + sURL.toString());
            }
        }
    }
    
    public void refreshTable() {
        if (this.m_oArray != null) {
            this.m_oArray.paintTable(this.m_oTable);
        }
        else {
            this.paintAll(this.getGraphics());
        }
    }
    
    public void paintTable() {
        try {
            this.m_bHasPainted = true;
            if (this.m_oArray != null) {
                this.m_oArray.setBackground(Color.white);
                this.m_oArray.setForeground(Color.black);
            }
            else {
                this.setBackground(Color.white);
                this.setForeground(Color.black);
            }
            this.setTDCData(this.m_sURL, this.m_bHasColumnsInTDC);
            this.m_oTable.setTableData(this.m_oCols, this.m_oRows);
            this.m_oTable.getDataArea().setSyncChecksWithSelections(this.m_bSyncChecksWithSelections);
            this.m_oTable.getDataArea();
            DataArea.setGridLinesColor(Color.black);
            this.m_oTable.getDataArea().setRowClickHandler(this.m_sOnRowDblClick, this.m_sOnRowClick);
            this.m_oTable.getDataArea().setButtonClickHandler(this.m_sOnAcceptClick, this.m_sOnModifyClick);
            this.m_oTable.getDataArea().setAllowRowDragDrop(this.m_bAllowRowDragDrop);
            if (!this.m_bBasicColors) {
                this.m_oTable.getDataArea();
                DataArea.setReferenceColumnBackground(new Color(this.m_iRowTitleBackground));
                this.m_oTable.getDataArea();
                DataArea.setReferenceColumnForeground(new Color(this.m_iRowTitleForeground));
            }
            else {
                this.m_oTable.getDataArea().setShowGrid(false);
                this.m_oTable.getDataArea();
                DataArea.setReferenceColumnBackground(Color.white);
                this.m_oTable.getTitlebar();
                Titlebar.setBorder(new BorderRaised());
            }
            if (this.m_bShowButtons) {
                this.m_oTable.getDataArea().setAcceptButtonNormalImage(this.loadImage(this.m_sAcceptButtonNormalImagePath));
                this.m_oTable.getDataArea().setAcceptButtonPressedImage(this.loadImage(this.m_sAcceptButtonPressedImagePath));
                this.m_oTable.getDataArea().setModifyButtonNormalImage(this.loadImage(this.m_sModifyButtonNormalImagePath));
                this.m_oTable.getDataArea().setModifyButtonPressedImage(this.loadImage(this.m_sModifyButtonPressedImagePath));
                this.m_oTable.getDataArea().setRowHeightFactor(1.5);
            }
            this.m_oTable.getTitlebar().setAllowColumnDragDrop(this.m_bAllowColumnDragDrop);
            this.m_oTable.getTitlebar().setColumnSortableFlags(this.getBoolArray(this.m_bSortable, this.m_iCols));
            this.m_oTable.getTitlebar().setColumnResizeableFlags(this.getBoolArray(true, this.m_iCols));
            this.m_oTable.getTitlebar().setForeground(new Color(this.m_iColumnTitleForeground));
            this.m_oTable.getTitlebar().setOnSort(this.m_sOnSort);
            if (!this.m_bBasicColors) {
                this.m_oTable.getTitlebar().setBackground(new Color(this.m_iColumnTitleBackground));
            }
            else {
                this.m_oTable.getTitlebar().setBackground(Color.white);
            }
            this.m_oTable.getTitlebar().setAdjustToFit(false);
            this.m_oTable.setShowCheckmarks(this.m_bCheckmarks);
            this.m_oTable.setShowLineNumbers(this.m_bLineNumbers);
            this.m_oTable.setShowButtons(this.m_bShowButtons);
            this.m_oTable.setHorizontalScrollMode(1);
            this.m_oTable.setVerticalScrollMode(1);
            this.m_oTable.setSelectionMode(this.m_iSelectionMode);
            if (this.m_oArray != null) {
                this.m_oArray.paintTable(this.m_oTable);
            }
            else {
                this.add("Center", this.m_oTable);
                this.paintAll(this.getGraphics());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (this.m_sOnLoad.length() > 0) {
            this.m_oTable.getDataArea().execJavaScript(this.m_sOnLoad);
        }
        if (this.m_hashColWidths != null) {
            final Enumeration enum1 = this.m_hashColWidths.keys();
            while (enum1.hasMoreElements()) {
                Object obj = enum1.nextElement();
                final int iIndex = Integer.parseInt((String)obj);
                obj = this.m_hashColWidths.get(obj);
                final int iWidth = Integer.parseInt((String)obj);
                this.setColumnWidth(iIndex, iWidth);
            }
        }
    }
    
    private boolean[] getBoolArray(final boolean bItemVal, final int iBoundVal) {
        final boolean[] aryBools = new boolean[iBoundVal];
        for (int iCount = 0; iCount < iBoundVal; ++iCount) {
            aryBools[iCount] = bItemVal;
        }
        return aryBools;
    }
    
    private int[] getIntArray(final int iItemVal, final int iBoundVal) {
        final int[] aryInts = new int[iBoundVal];
        for (int iCount = 0; iCount < iBoundVal; ++iCount) {
            aryInts[iCount] = iItemVal;
        }
        return aryInts;
    }
    
    public String getRowByIndex(final int iRowIndex, final int iColumnIndex) {
        final int[] aryRow = { iRowIndex };
        return this.getRowsString(aryRow, iColumnIndex);
    }
    
    public void setRowByIndex(final int iRowIndex, final int iColumnIndex, final String strValue) {
        final SimpleSortableTableRow oSimpleRows = this.m_oTable.getRows().elementAt(iRowIndex);
        final ITableCell[] oRows = oSimpleRows.getStrings();
        final ColumnDefinition oColumn = this.m_oCols.elementAt(iColumnIndex);
        switch (oColumn.getColumnType()) {
            case 5: {
                oRows[iColumnIndex] = new CMoneyTableCell(strValue);
                break;
            }
            case 1: {
                oRows[iColumnIndex] = new CStringTableCell(strValue);
                break;
            }
            case 2: {
                oRows[iColumnIndex] = new CIntegerTableCell(strValue);
                break;
            }
            case 4: {
                oRows[iColumnIndex] = new CDecimalTableCell(strValue);
                break;
            }
            case 3: {
                oRows[iColumnIndex] = new CDateTableCell(strValue, oColumn.getDateFormat());
                break;
            }
            default: {
                oRows[iColumnIndex] = new CStringTableCell(strValue);
                break;
            }
        }
    }
    
    public String getColumnName(int iColumnIndex) {
        if (this.m_bUseOriginalColumnIndex) {
            iColumnIndex = this.m_oTable.getOriginalColumnIndex(iColumnIndex);
        }
        final ColumnDefinition oCol = this.m_oCols.elementAt(iColumnIndex);
        return oCol.getTitle();
    }
    
    public String getSelected(final int iColumnIndex) {
        return this.getRowsString(this.m_oTable.getSelectedIndexes(), iColumnIndex);
    }
    
    public boolean isRowSelected(final int iRowIndex) {
        return this.m_oTable.isSelected(iRowIndex);
    }
    
    public String getAll(final int iColumnIndex) {
        final int[] aryRows = new int[this.m_oTable.getRows().size()];
        for (int i = 0; i < this.m_oTable.getRows().size(); ++i) {
            aryRows[i] = i;
        }
        return this.getRowsString(aryRows, iColumnIndex);
    }
    
    public String getChecked(final int iColumnIndex) {
        if (this.m_oTable.getShowCheckmarks()) {
            return this.getRowsString(this.m_oTable.getCheckedIndexes(), iColumnIndex);
        }
        return "";
    }
    
    public int getRowCount() {
        try {
            return this.m_oTable.getRows().size();
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public int getColumnCount() {
        return this.m_oCols.size();
    }
    
    public String getColumnValue(final int iColumnIndex) {
        try {
            final int[] aryRow = { this.m_oTable.getDataArea().getFocusRow() };
            return this.getRowsString(aryRow, iColumnIndex);
        }
        catch (Exception e) {
            return "";
        }
    }
    
    public int getFocusRow() {
        try {
            return this.m_oTable.getDataArea().getFocusRow();
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public void moveFocusRow(final int iRow) {
        try {
            this.m_oTable.getDataArea().moveFocus(iRow);
            this.m_oTable.getDataArea().scrollToRow(this.m_oTable.getDataArea().getFocusRow());
        }
        catch (Exception ex) {}
    }
    
    public void moveNextRow() {
        this.moveFocusRow(this.m_oTable.getDataArea().getFocusRow() + 1);
    }
    
    public void movePrevRow() {
        this.moveFocusRow(this.m_oTable.getDataArea().getFocusRow() - 1);
    }
    
    public void setSelected(final int iRow, final boolean bKeepCurrentSel) {
        try {
            if (bKeepCurrentSel) {
                this.m_oTable.getDataArea().clickLineMany(iRow, false, true, false);
            }
            else {
                this.m_oTable.getDataArea().clickLineOne(iRow, false, false, false);
            }
        }
        catch (Exception ex) {}
    }
    
    private String getRowsString(final int[] aryIdx, int iColumnIndex) {
        if (this.m_bUseOriginalColumnIndex) {
            iColumnIndex = this.m_oTable.getOriginalColumnIndex(iColumnIndex);
        }
        String sReturn = new String();
        for (int iCount = 0; iCount < aryIdx.length; ++iCount) {
            try {
                final SimpleSortableTableRow oRow = this.m_oTable.getRows().elementAt(aryIdx[iCount]);
                final ITableCell[] aryStr = oRow.getStrings();
                String sVal = new String();
                if (iColumnIndex == -1) {
                    for (int iIdx = 0; iIdx < aryStr.length; ++iIdx) {
                        sVal = String.valueOf(sVal) + '\"' + aryStr[this.m_oTable.getOriginalColumnIndex(iIdx)].toString() + '\"' + this.m_sCellSplit;
                    }
                    sReturn = String.valueOf(sReturn) + sVal.substring(0, sVal.length() - this.m_sCellSplit.length()) + this.m_sRowSplit;
                }
                else {
                    sReturn = String.valueOf(sReturn) + aryStr[iColumnIndex].toString() + this.m_sRowSplit;
                }
            }
            catch (Exception ex) {}
        }
        if (aryIdx.length > 0) {
            return sReturn.substring(0, sReturn.length() - this.m_sRowSplit.length());
        }
        return "";
    }
    
    public int getColumnType(final int iColumnIndex) {
        return this.m_oCols.elementAt(iColumnIndex).getColumnType();
    }
    
    public void removeRowsByIndex(final String cols) {
        final int[] indexes = this.splitStringToInt(cols, ",");
        this.m_oTable.removeRowsByIndex(indexes);
    }
    
    public void select(final int index) {
        this.m_oTable.select(index, true);
    }
    
    public void deselectAll() {
        this.m_oTable.deselectAll();
    }
    
    public void setButtonColumnToCheck() {
        this.m_oTable.deselectAll();
    }
    
    public void setButtonColumnTextToCheck() {
        this.m_oTable.deselectAll();
    }
    
    public void shiftRows(final int iFrom, final int iTo) {
        this.m_oTable.getDataArea().shiftRows(iFrom, iTo);
    }
    
    public String getSelectedIndexes() {
        String sRetVal = "";
        final int[] aIndexes = this.m_oTable.getSelectedIndexes();
        for (int iCount = 0; iCount < aIndexes.length; ++iCount) {
            sRetVal = String.valueOf(sRetVal) + aIndexes[iCount] + ",";
        }
        if (sRetVal.length() > 0) {
            sRetVal = sRetVal.substring(0, sRetVal.length() - 1);
        }
        return sRetVal;
    }
    
    public void removeSelectedRows() {
        this.m_oTable.removeSelectedRows();
    }
    
    public void setAllowColumnDragDrop(final boolean bValue) {
        this.m_bAllowColumnDragDrop = bValue;
    }
    
    public void removeAllRows() {
        this.m_oTable.removeAllRows();
    }
    
    public int[] getCheckedIndexes() {
        return this.m_oTable.getCheckedIndexes();
    }
    
    public String getCheckedIndexesStr() {
        String sRetVal = "";
        final int[] aIndexes = this.m_oTable.getCheckedIndexes();
        for (int iCount = 0; iCount < aIndexes.length; ++iCount) {
            sRetVal = String.valueOf(sRetVal) + Integer.toString(aIndexes[iCount]) + ",";
        }
        if (sRetVal.length() > 0) {
            sRetVal = sRetVal.substring(0, sRetVal.length() - 1);
        }
        return sRetVal;
    }
    
    public void setCheckedIndexes(final int[] aChecked) {
        this.m_oTable.setCheckedIndexes(aChecked);
    }
    
    public void selectIndexes(final int[] aSelected) {
        this.m_oTable.selectIndexes(aSelected);
    }
    
    public void filterFromColumnValues(String strValues, final int iColumnIndex) {
        if (strValues.length() == 0) {
            return;
        }
        strValues = "\n\r" + strValues + "\n\r";
        for (int i = 0; i < (this.m_bFiltered ? this.m_oFRows.size() : this.m_oRows.size()); ++i) {
            if (strValues.indexOf("\n\r" + this.getRowByIndex(i, iColumnIndex) + "\n\r") != -1) {
                if (!this.m_bFiltered) {
                    this.m_oFRows.addElement(this.m_oRows.elementAt(i));
                }
            }
            else if (this.m_bFiltered) {
                final int[] aTemp = { i };
                this.m_oTable.removeRowsByIndex(aTemp);
                --i;
            }
        }
        if (!this.m_bFiltered) {
            this.m_oTable.setRows(this.m_oFRows);
        }
        this.m_bFiltered = true;
    }
    
    public Image loadImage(final String s) {
        Image image = null;
        final MediaTracker mediatracker = new MediaTracker(this);
        try {
            image = this.getImage(this.getDocumentBase(), s);
            mediatracker.addImage(image, 0);
            mediatracker.waitForID(0);
        }
        catch (Exception e) {
            return null;
        }
        if (mediatracker.isErrorID(0)) {
            return null;
        }
        return image;
    }
    
    public class COMClassObject
    {
    }
}

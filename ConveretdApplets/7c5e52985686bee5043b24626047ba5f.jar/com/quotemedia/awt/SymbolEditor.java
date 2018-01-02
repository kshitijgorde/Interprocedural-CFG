// 
// Decompiled by Procyon v0.5.30
// 

package com.quotemedia.awt;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ItemEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.event.FocusEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import com.quotemedia.applet.QMCIApplet;
import java.util.Vector;
import java.awt.List;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.Frame;

public class SymbolEditor extends Frame implements FocusListener, ActionListener, ItemListener, MouseListener
{
    Dialog warning;
    Button fok;
    Button ok;
    Button cancel;
    Button add;
    Button lookup;
    Button delete;
    Label enter;
    Label link;
    Checkbox[] idx;
    String[] idxSymbols;
    String[] idxNames;
    TextField symbol;
    public List list;
    Vector olddata;
    String newsymbol;
    protected SymbolListUser symbolListUser;
    protected QMCIApplet qmciApplet;
    protected String dji;
    
    public SymbolEditor(final SymbolListUser symbolListUser, final QMCIApplet qmciApplet) {
        super("Customize Symbols");
        this.warning = null;
        this.fok = new Button("OK");
        this.ok = new Button("    OK     ");
        this.cancel = new Button(" Cancel ");
        this.add = new Button("<< Add");
        this.lookup = new Button("Symbol Lookup");
        this.delete = new Button("Delete");
        this.enter = new Label("Enter Symbol");
        this.link = new Label("QuoteMedia.com", 0);
        this.olddata = null;
        this.newsymbol = "";
        this.dji = null;
        this.symbolListUser = symbolListUser;
        this.qmciApplet = qmciApplet;
        final String[] idxs = { "^DJI", "^DJT", "^COMPX", "^NDX", "^NYA", "^RUO", "^SPX", ".SP100", ".SP400", ".SP600", "^TSX", "^CND" };
        final String[] idxn = { "Dow Jones Ind Avg", "DJ Transportation", "NASDAQ Comp", "NASDAQ 100", "NYSE Comp", "Russel 2K", "S&P 500", "S&P 100", "S&P Midcap 400", "S&P Smallcap 600", "S&P TSX", "NASDAQ Canada" };
        this.idxSymbols = idxs;
        this.idxNames = idxn;
        this.idx = new Checkbox[this.idxNames.length];
        for (int i = 0; i < this.idx.length; ++i) {
            (this.idx[i] = new Checkbox()).addItemListener(this);
        }
        final Font littleFont = new Font("SanSerif", 0, 10);
        this.list = new List(10, true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#CCCCCC"));
        this.ok.addActionListener(this);
        this.cancel.addActionListener(this);
        this.add.addActionListener(this);
        this.delete.addActionListener(this);
        this.lookup.addActionListener(this);
        (this.symbol = new TextField(10)).setSize(10, 8);
        this.symbol.setForeground(Color.black);
        this.symbol.setBackground(Color.white);
        this.symbol.setText("Symbol");
        this.symbol.addActionListener(this);
        this.symbol.addFocusListener(this);
        this.list.addItemListener(this);
        this.list.setMultipleMode(true);
        final Panel buttonpanel = new Panel();
        GridBagLayout gbl = new GridBagLayout();
        buttonpanel.setLayout(gbl);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = 2;
        gbl.setConstraints(this.symbol, gbc);
        buttonpanel.add(this.symbol);
        gbc.gridy = 1;
        gbl.setConstraints(this.add, gbc);
        buttonpanel.add(this.add);
        gbc.gridy = 2;
        gbl.setConstraints(this.delete, gbc);
        buttonpanel.add(this.delete);
        gbc.gridy = 3;
        gbl.setConstraints(this.lookup, gbc);
        buttonpanel.add(this.lookup);
        final Label l1 = new Label("Enter symbol in the");
        l1.setFont(littleFont);
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbl.setConstraints(l1, gbc);
        buttonpanel.add(l1);
        final Label l2 = new Label("box and click 'Add'.");
        l2.setFont(littleFont);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbl.setConstraints(l2, gbc);
        buttonpanel.add(l2);
        final Label l3 = new Label("To remove click a");
        l3.setFont(littleFont);
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbl.setConstraints(l3, gbc);
        buttonpanel.add(l3);
        final Label l4 = new Label("symbol in left box");
        l4.setFont(littleFont);
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbl.setConstraints(l4, gbc);
        buttonpanel.add(l4);
        final Label l5 = new Label("and click 'Delete'");
        l5.setFont(littleFont);
        gbc.gridy = 8;
        gbl.setConstraints(l5, gbc);
        buttonpanel.add(l5);
        final Panel indexes = new Panel();
        final Panel indexLabels = new Panel();
        indexes.setLayout(new GridLayout(this.idx.length, 1, 0, 0));
        indexLabels.setLayout(new GridLayout(this.idxNames.length, 1, 0, 0));
        for (int j = 0; j < this.idx.length; ++j) {
            indexes.add(this.idx[j]);
            indexLabels.add(new Label(this.idxNames[j], 0));
        }
        final Panel checkboxes = new Panel();
        checkboxes.setLayout(new FlowLayout(2, 0, 0));
        checkboxes.add(indexes);
        checkboxes.add(indexLabels);
        final Panel north0 = new Panel();
        north0.setLayout(new BorderLayout());
        final Line h1 = new Line(true, false);
        final Line h2 = new Line(false, false);
        final Line v1 = new Line(true, true);
        final Line v2 = new Line(false, true);
        north0.add("West", v1);
        north0.add("East", v2);
        north0.add("North", h1);
        north0.add("South", h2);
        final Panel center = new Panel();
        gbl = new GridBagLayout();
        center.setLayout(gbl);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = 3;
        gbl.setConstraints(this.list, gbc);
        center.add(this.list);
        gbc.gridx = 1;
        gbl.setConstraints(buttonpanel, gbc);
        center.add(buttonpanel);
        gbc.gridx = 2;
        gbl.setConstraints(checkboxes, gbc);
        center.add(checkboxes);
        north0.add("Center", center);
        final Panel north2 = new Panel();
        north2.setLayout(new FlowLayout(1, 10, 10));
        north2.add(north0);
        final Panel eastsouth = new Panel();
        eastsouth.setLayout(new FlowLayout(2, 10, 5));
        eastsouth.add(this.ok);
        eastsouth.add(this.cancel);
        final Label pbl = new Label("Powered by", 2);
        this.link.setForeground(Color.blue);
        this.link.addMouseListener(this);
        final Panel linkPanel = new Panel();
        linkPanel.setLayout(new FlowLayout(1, 0, 5));
        linkPanel.add(pbl);
        linkPanel.add(this.link);
        final Panel southgrid = new Panel();
        southgrid.setLayout(new GridLayout(1, 2));
        southgrid.add(linkPanel);
        southgrid.add(eastsouth);
        this.add("South", southgrid);
        this.add("Center", north2);
        this.addWindowListener(new WindowCloser());
        this.pack();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - this.getSize().width) / 2;
        final int y = (screenSize.height - this.getSize().height) / 2;
        this.setLocation(x, y);
    }
    
    public void focusGained(final FocusEvent e) {
        if (this.symbol.getText().equals("Symbol")) {
            this.symbol.setText("");
        }
    }
    
    public void focusLost(final FocusEvent e) {
    }
    
    public String getFrameSymbol() {
        String str = "";
        for (int i = 0; i < this.list.countItems() - 1; ++i) {
            if (this.list.getItem(i) != null) {
                str = str + this.list.getItem(i) + ",";
            }
        }
        str += this.list.getItem(this.list.countItems() - 1);
        return str;
    }
    
    public void setSymbols() {
        String indices = "";
        if (this.idxSymbols.length > 0) {
            for (int i = 0; i < this.idxSymbols.length - 1; ++i) {
                if (this.idx[i].getState()) {
                    indices = indices + this.idxSymbols[i] + ",";
                }
            }
            if (this.idx[this.idxSymbols.length - 1].getState()) {
                indices = indices + this.idxSymbols[this.idxSymbols.length - 1] + ",";
            }
        }
        String stocks = "";
        if (this.list.getItemCount() > 0) {
            for (int j = 0; j < this.list.countItems() - 1; ++j) {
                stocks = stocks + this.list.getItem(j) + ",";
            }
            stocks += this.list.getItem(this.list.countItems() - 1);
        }
        this.symbolListUser.setSymbols(stocks, indices);
    }
    
    public void setup(final String symbols) {
        this.list.removeAll();
        final StringTokenizer tok = new StringTokenizer(symbols, ",");
        while (tok.hasMoreTokens()) {
            final String sy = tok.nextToken();
            for (int i = 0; i < this.idxSymbols.length; ++i) {
                if (sy.equals(this.idxSymbols[i])) {
                    this.idx[i].setState(true);
                    break;
                }
                if (i == this.idxSymbols.length - 1) {
                    this.list.add(sy);
                }
            }
        }
    }
    
    public String splitSymbols(final String symbols, final boolean stock) {
        final StringTokenizer tok = new StringTokenizer(symbols, ",");
        String filteredSymbols = "";
        while (tok.hasMoreTokens()) {
            final String sy = tok.nextToken();
            int i = 0;
            while (i < this.idxSymbols.length) {
                if (sy.equals(this.idxSymbols[i])) {
                    if (stock) {
                        break;
                    }
                    filteredSymbols = filteredSymbols + sy + ",";
                    break;
                }
                else {
                    if (i == this.idxSymbols.length - 1 && stock) {
                        filteredSymbols = filteredSymbols + sy + ",";
                    }
                    ++i;
                }
            }
        }
        if (filteredSymbols.length() > 0) {
            filteredSymbols = filteredSymbols.substring(0, filteredSymbols.length() - 1);
        }
        return filteredSymbols;
    }
    
    public void actionPerformed(final ActionEvent ev) {
        if (ev.getSource() == this.ok) {
            this.hide();
            this.setSymbols();
            this.dispose();
        }
        else if (ev.getSource() == this.add) {
            this.addSymbol();
        }
        else if (ev.getSource() == this.delete) {
            final String[] items = this.list.getSelectedItems();
            for (int i = 0; i < items.length; ++i) {
                this.list.remove(items[i]);
            }
        }
        else if (ev.getSource() == this.symbol) {
            this.addSymbol();
        }
        else if (ev.getSource() == this.cancel) {
            this.hide();
        }
        else if (ev.getSource() == this.fok) {
            this.warning.dispose();
        }
        else if (ev.getSource() == this.lookup) {
            this.qmciApplet.openPopup("/quotetools/clientForward?targetURL=http://" + this.qmciApplet.getServer() + "/quotetools/popups/symbolLookup.jsp&links=false", 440, 500, false);
        }
    }
    
    public void mouseClicked(final MouseEvent me) {
        this.hide();
        this.qmciApplet.showDocument("http://www.quotemedia.com", "_self");
    }
    
    public void mouseEntered(final MouseEvent me) {
        this.link.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent me) {
        this.link.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent me) {
    }
    
    public void mouseReleased(final MouseEvent me) {
    }
    
    protected void addSymbol() {
        int ndxCounter = 0;
        if (this.idxSymbols != null) {
            for (int i = 0; i < this.idxSymbols.length - 1; ++i) {
                if (this.idx[i].getState()) {
                    ++ndxCounter;
                }
            }
        }
        if (this.list.countItems() + ndxCounter > 100) {
            this.showWarning();
        }
        else {
            final String sym = this.symbol.getText().toUpperCase();
            if (sym != null && !sym.equals("") && !sym.equals("Symbol")) {
                final String[] symbols = this.list.getItems();
                for (int j = 0; j < symbols.length; ++j) {
                    if (symbols[j].equalsIgnoreCase(sym)) {
                        this.list.remove(sym);
                    }
                }
                this.list.add(sym);
                this.symbol.setText("");
            }
        }
    }
    
    protected void showWarning() {
        if (this.warning == null) {
            this.warning = new Dialog(this, true);
            this.fok.addActionListener(this);
            this.warning.setLayout(new BorderLayout());
            final Label flabel = new Label("You have reached the limit of 100 Symbols");
            this.warning.add("Center", flabel);
            final Panel cntp = new Panel();
            cntp.setLayout(new FlowLayout(1, 5, 5));
            cntp.add(this.fok);
            this.warning.add("South", cntp);
            this.warning.setBackground(Color.decode("#BDBDBD"));
            this.warning.addWindowListener(new WindowCloser());
            this.warning.pack();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.warning.setLocation((screenSize.width - this.warning.getSize().width) / 2, (screenSize.width - this.warning.getSize().height) / 2);
        }
        this.warning.show();
    }
    
    public int getIndexOfList(final String str) {
        int n = -1;
        for (int i = 0; i < this.list.countItems(); ++i) {
            final String liststr = this.list.getItem(i);
            if (liststr.equals(str)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public void itemStateChanged(final ItemEvent ite) {
        final Object obj = ite.getSource();
    }
    
    class WindowCloser extends WindowAdapter
    {
        public void windowClosing(final WindowEvent e) {
            final Window w = (Window)e.getSource();
            w.hide();
        }
    }
}

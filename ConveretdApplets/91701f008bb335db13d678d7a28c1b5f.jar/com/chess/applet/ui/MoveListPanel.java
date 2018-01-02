// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import com.chess.game.ChessGame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import com.chess.game.MoveUndoer;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.chess.chessboard.MoveListViewer;
import javax.swing.JPanel;

public class MoveListPanel extends JPanel implements MoveListViewer
{
    private JScrollPane tableScrollPane;
    private JTable movesTable;
    private DefaultTableModel movesTableModel;
    private JPanel controlsPanel;
    private JButton previousMoveButton;
    private JButton nextMoveButton;
    private ActionListener controlsHandler;
    private MoveUndoer moveUndoer;
    private int highlitedMove;
    private Color highlightColor;
    private int numOfMoves;
    private int currentMove;
    
    public MoveListPanel() {
        super(new BorderLayout());
        this.controlsHandler = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (e.getSource() == MoveListPanel.this.nextMoveButton) {
                    MoveListPanel.this.doNextMove();
                }
                else if (e.getSource() == MoveListPanel.this.previousMoveButton) {
                    MoveListPanel.this.doPreviousMove();
                }
            }
        };
        this.setOpaque(false);
        this.movesTableModel = new DefaultTableModel() {
            public boolean isCellEditable(final int row, final int col) {
                return false;
            }
        };
        this.movesTable = new JTable(this.movesTableModel);
        this.add(this.tableScrollPane = new JScrollPane(this.movesTable), "Center");
        this.tableScrollPane.setViewportView(this.movesTable);
        this.movesTableModel.addColumn("");
        this.movesTableModel.addColumn("White");
        this.movesTableModel.addColumn("Black");
        this.movesTable.setShowGrid(false);
        this.movesTable.getTableHeader().setReorderingAllowed(false);
        this.movesTable.setSelectionMode(0);
        this.movesTable.setRowSelectionAllowed(true);
        this.movesTable.setColumnSelectionAllowed(false);
        (this.controlsPanel = new JPanel(new GridLayout(1, 2, 5, 5))).setOpaque(false);
        this.add(this.controlsPanel, "South");
        this.previousMoveButton = new JButton("<");
        this.nextMoveButton = new JButton(">");
        this.previousMoveButton.addActionListener(this.controlsHandler);
        this.nextMoveButton.addActionListener(this.controlsHandler);
        this.controlsPanel.add(this.previousMoveButton);
        this.controlsPanel.add(this.nextMoveButton);
        this.highlightColor = Color.yellow;
        final Dimension dimension = new Dimension(60, Integer.MAX_VALUE);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setBorder(BorderFactory.createLineBorder(new Color(7829367)));
        this.movesTable.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {
                MoveListPanel.this.updateTableScrollPosition();
            }
        });
        this.movesTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (MoveListPanel.this.movesTable.isEnabled()) {
                    final int row = e.getY() / MoveListPanel.this.movesTable.getRowHeight();
                    final int change = row - MoveListPanel.this.currentMove;
                    if (change < 0) {
                        for (int i = 0; i < -change; ++i) {
                            MoveListPanel.this.doPreviousMove();
                        }
                    }
                    else if (change > 0) {
                        for (int i = 0; i < change; ++i) {
                            MoveListPanel.this.doNextMove();
                        }
                    }
                    MoveListPanel.this.currentMove = row;
                }
            }
        });
    }
    
    private void updateTableScrollPosition() {
        this.tableScrollPane.getVerticalScrollBar().setValue(Integer.MAX_VALUE);
        if (this.movesTable.getRowCount() > 0) {
            this.movesTable.removeRowSelectionInterval(0, this.movesTable.getRowCount() - 1);
        }
    }
    
    public void setMoveList(final String moveList) {
        while (this.movesTable.getRowCount() > 0) {
            this.movesTableModel.removeRow(0);
        }
        final String[] moves = ChessGame.splitMoveList(moveList);
        this.numOfMoves = moves.length;
        for (int i = 0; i < moves.length; i += 3) {
            final String[] row = new String[3];
            for (int j = 0; j < row.length && i + j < moves.length; ++j) {
                row[j] = moves[i + j].trim();
            }
            this.movesTableModel.addRow(row);
        }
        this.highlitedMove = moves.length - 1;
        final TableCellRenderer renderer = new MoveCellRenderer();
        for (int k = 0; k < this.movesTable.getColumnCount(); ++k) {
            this.movesTable.getColumn(this.movesTable.getColumnName(k)).setCellRenderer(renderer);
        }
        this.updateTableScrollPosition();
        this.currentMove = this.movesTable.getRowCount() - 1;
    }
    
    public MoveUndoer getMoveUndoer() {
        return this.moveUndoer;
    }
    
    public void setMoveUndoer(final MoveUndoer moveUndoer) {
        this.moveUndoer = moveUndoer;
    }
    
    private void doPreviousMove() {
        if (this.moveUndoer != null && this.highlitedMove >= 2) {
            this.moveUndoer.undoMoves(2);
            if (this.highlitedMove > 0) {
                this.highlitedMove -= 3;
            }
            this.currentMove = (int)Math.floor(this.highlitedMove / 3.0);
            this.movesTable.getSelectionModel().setSelectionInterval(this.currentMove, this.currentMove);
            this.movesTable.repaint();
        }
    }
    
    public void takeBackComputerMove() {
        if (this.moveUndoer != null) {
            this.moveUndoer.undoMoves(1);
            if (this.highlitedMove > 0) {
                if (this.highlitedMove % 3 == 2) {
                    --this.highlitedMove;
                }
                else {
                    this.highlitedMove -= 2;
                }
            }
            this.currentMove = (int)Math.floor(this.highlitedMove / 3.0);
            this.movesTable.getSelectionModel().setSelectionInterval(this.currentMove, this.currentMove);
            this.movesTable.repaint();
        }
    }
    
    private void doNextMove() {
        if (this.moveUndoer != null) {
            final String[] moves = new String[2];
            int firstMove = this.highlitedMove + 1;
            if (firstMove % 3 == 0) {
                ++firstMove;
            }
            int secondMove = firstMove + 1;
            if (secondMove % 3 == 0) {
                ++secondMove;
            }
            boolean toMove = false;
            if (firstMove / 3 < this.movesTableModel.getRowCount() && firstMove % 3 > 0 && firstMove < this.numOfMoves) {
                moves[0] = (String)this.movesTableModel.getValueAt(firstMove / 3, firstMove % 3);
            }
            if (secondMove / 3 < this.movesTableModel.getRowCount() && secondMove % 3 > 0 && firstMove < this.numOfMoves) {
                moves[1] = (String)this.movesTableModel.getValueAt(secondMove / 3, secondMove % 3);
            }
            toMove = (moves[0] != null && moves[1] != null);
            if (toMove) {
                this.moveUndoer.redoMoves(moves);
                this.highlitedMove += 3;
                this.currentMove = (int)Math.floor(this.highlitedMove / 3.0);
                this.movesTable.getSelectionModel().setSelectionInterval(this.currentMove, this.currentMove);
                this.repaint();
            }
        }
    }
    
    public void setControlsEnabled(final boolean enabled) {
        this.previousMoveButton.setEnabled(enabled);
        this.nextMoveButton.setEnabled(enabled);
        this.movesTable.setEnabled(enabled);
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public void setHighlightColor(final Color highlightColor) {
        this.highlightColor = highlightColor;
    }
    
    private class MoveCellRenderer extends DefaultTableCellRenderer
    {
        private Color light;
        private Color dark;
        
        private MoveCellRenderer() {
            this.light = Color.white;
            this.dark = new Color(222, 222, 222);
        }
        
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            final Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            final Color background = component.getBackground();
            if (row * 3 + column == MoveListPanel.this.highlitedMove) {
                component.setBackground(MoveListPanel.this.highlightColor);
            }
            else if (table.getSelectedRow() == row) {
                component.setBackground(background);
            }
            else {
                component.setBackground((row % 2 == 0) ? this.light : this.dark);
            }
            component.setForeground(Color.BLACK);
            return component;
        }
    }
}

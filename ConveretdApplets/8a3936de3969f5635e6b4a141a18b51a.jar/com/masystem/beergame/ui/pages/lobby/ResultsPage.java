// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.lobby;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.FontMetrics;
import com.masystem.beergame.resource.ResourceManager;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import com.masystem.beergame.ui.component.swing.PanelComponent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextButton;
import javax.swing.JComponent;
import com.masystem.beergame.network.protocol.HistoryState;
import com.masystem.beergame.Config;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;
import com.masystem.beergame.network.protocol.Player;
import com.masystem.beergame.network.protocol.Results;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;

public final class ResultsPage extends BeerGamePage
{
    private final Results results;
    private final Player.Type localType;
    
    public ResultsPage(final Results results, final Player.Type localType) {
        this.results = results;
        this.localType = localType;
    }
    
    @Override
    public final void onSetup() {
        this.addChild(new BeerGameImage("results_bg.png"));
        final BeerGameTextLabel beerGameTextLabel = new BeerGameTextLabel("PLACED ORDERS");
        this.addChild(beerGameTextLabel);
        beerGameTextLabel.setHorizontalAlignment(0);
        beerGameTextLabel.setSizeRelativeToParent(0.17f, -1.0f);
        beerGameTextLabel.setPositionRelativeToParent(0.124f, 0.114f);
        final BeerGameTextLabel beerGameTextLabel2 = new BeerGameTextLabel((Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) ? "BALANCE" : "COST");
        this.addChild(beerGameTextLabel2);
        beerGameTextLabel2.setHorizontalAlignment(0);
        beerGameTextLabel2.setSizeRelativeToParent(0.17f, -1.0f);
        beerGameTextLabel2.setPositionRelativeToParent(0.124f, 0.544f);
        final BeerGameTextLabel beerGameTextLabel3 = new BeerGameTextLabel("NET INVENTORY");
        this.addChild(beerGameTextLabel3);
        beerGameTextLabel3.setHorizontalAlignment(0);
        beerGameTextLabel3.setSizeRelativeToParent(0.17f, -1.0f);
        beerGameTextLabel3.setPositionRelativeToParent(0.587f, 0.114f);
        final Node node = new Node(new DiagramComponent(this, this.results, this.localType) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public final int getValue(final int n, final HistoryState historyState) {
                if (n == this.nbrValues - 1) {
                    return Integer.MIN_VALUE;
                }
                return historyState.getOutgoingOrder();
            }
        });
        this.addChild(node);
        node.setPivot(0.0f, 0.5f);
        node.setSizeRelativeToParent(0.4f, 0.27f);
        node.setPositionRelativeToParent(0.086f, 0.27f);
        final Node node2 = new Node(new DiagramComponent(this, this.results, this.localType) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public final int getValue(final int n, final HistoryState historyState) {
                return historyState.getStock();
            }
        });
        this.addChild(node2);
        node2.setPivot(0.0f, 0.5f);
        node2.setSizeRelativeToParent(0.4f, 0.27f);
        node2.setPositionRelativeToParent(0.551f, 0.27f);
        final Node node3 = new Node(new DiagramComponent(this, this.results, this.localType) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public final int getValue(final int n, final HistoryState historyState) {
                if (Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) {
                    return historyState.getResult();
                }
                return -historyState.getResult();
            }
        });
        this.addChild(node3);
        node3.setPivot(0.0f, 0.5f);
        node3.setSizeRelativeToParent(0.4f, 0.27f);
        node3.setPositionRelativeToParent(0.086f, 0.7f);
        final BeerGameTextLabel beerGameTextLabel4 = new BeerGameTextLabel("SUPPLY CHAIN RESULTS");
        this.addChild(beerGameTextLabel4);
        beerGameTextLabel4.setHorizontalAlignment(2);
        beerGameTextLabel4.setPivot(0.0f, 0.5f);
        beerGameTextLabel4.setSizeRelativeToParent(0.373f, -1.0f);
        beerGameTextLabel4.setPositionRelativeToParent(0.54f, 0.544f);
        final int size = this.results.getSize();
        final int result = this.results.getHistory(Player.Type.RETAILER).get(size - 2).getResult();
        final int result2 = this.results.getHistory(Player.Type.WHOLESALER).get(size - 2).getResult();
        final int result3 = this.results.getHistory(Player.Type.DISTRIBUTOR).get(size - 2).getResult();
        final int result4 = this.results.getHistory(Player.Type.PRODUCER).get(size - 2).getResult();
        final StringBuilder sb;
        (sb = new StringBuilder(256)).append("<html>");
        sb.append(this.formatResultHtml("RETAILER", result, DiagramComponent.COLOR_RETAILER)).append("<br>");
        sb.append(this.formatResultHtml("WHOLESALER", result2, DiagramComponent.COLOR_WHOLESALER)).append("<br>");
        sb.append(this.formatResultHtml("DISTRIBUTOR", result3, DiagramComponent.COLOR_DISTRIBUTOR)).append("<br>");
        sb.append(this.formatResultHtml("PRODUCER", result4, DiagramComponent.COLOR_PRODUCER)).append("<br>");
        final BeerGameTextLabel beerGameTextLabel5 = new BeerGameTextLabel(sb.toString());
        this.addChild(beerGameTextLabel5);
        beerGameTextLabel5.setHorizontalAlignment(2);
        beerGameTextLabel5.setPivot(0.0f, 0.5f);
        beerGameTextLabel5.setSizeRelativeToParent(0.373f, -2.0f);
        beerGameTextLabel5.setPositionRelativeToParent(0.54f, 0.658f);
        final int n = result + result2 + result3 + result4;
        sb.setLength(0);
        sb.append("<html>");
        sb.append("SUPPLY CHAIN: ");
        sb.append(formatBalanceHtml(n));
        final BeerGameTextLabel beerGameTextLabel6 = new BeerGameTextLabel(sb.toString());
        this.addChild(beerGameTextLabel6);
        beerGameTextLabel6.setHorizontalAlignment(2);
        beerGameTextLabel6.setPivot(0.0f, 0.5f);
        beerGameTextLabel6.setSizeRelativeToParent(0.373f, -2.0f);
        beerGameTextLabel6.setPositionRelativeToParent(0.54f, 0.777f);
        final BeerGameTextButton beerGameTextButton = new BeerGameTextButton("EXIT");
        this.addChild(beerGameTextButton);
        beerGameTextButton.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                ResultsPage.this.gotoPreviousPage(new StartPage());
            }
        });
        beerGameTextButton.setPositionRelativeToParent(0.75f, 0.85f);
        this.pack();
    }
    
    private String formatResultHtml(final String s, final int n, final Color color) {
        String s2 = String.format("<font color=#%6x>%s:</font> %s", color.getRGB() & 0xFFFFFF, s, formatBalanceHtml(n));
        if (s.equals(this.localType.toString())) {
            s2 = "<font size=5>" + s2 + "</font>";
        }
        return s2;
    }
    
    private static String formatBalanceHtml(final int n) {
        if (n < 0) {
            return String.format("<font color=#7f0000>- $%d</font>", -n);
        }
        return String.format("$%d", n);
    }
    
    @Override
    public final void onTransitionEnd$6db0a1c1() {
    }
    
    public abstract static class DiagramComponent extends PanelComponent
    {
        private static final long serialVersionUID = 1L;
        protected static final Color COLOR_RETAILER;
        protected static final Color COLOR_WHOLESALER;
        protected static final Color COLOR_DISTRIBUTOR;
        protected static final Color COLOR_PRODUCER;
        private static Color COLOR_ZERO_LINE;
        private static Color COLOR_VALUE_TEXT;
        private static Color COLOR_VALUE_OUTLINE;
        protected int nbrValues;
        private int minValue;
        private int maxValue;
        private Results results;
        private Player.Type localPlayerType;
        
        public DiagramComponent(final Results results, final Player.Type localPlayerType) {
            this.results = results;
            this.localPlayerType = localPlayerType;
            this.nbrValues = results.getSize();
            this.minValue = Integer.MAX_VALUE;
            this.maxValue = Integer.MIN_VALUE;
            Player.Type[] values;
            for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
                final ArrayList<HistoryState> history;
                if ((history = results.getHistory(values[i])).size() - 1 < this.nbrValues) {
                    this.nbrValues = history.size() - 1;
                }
                for (int j = 0; j < history.size() - 1; ++j) {
                    final int value;
                    if ((value = this.getValue(j, history.get(j))) != Integer.MIN_VALUE) {
                        if (value < this.minValue) {
                            this.minValue = value;
                        }
                        if (value > this.maxValue) {
                            this.maxValue = value;
                        }
                    }
                }
            }
            if (this.maxValue > 0 && this.minValue > 0) {
                this.minValue = 0;
                return;
            }
            if (this.maxValue < 0 && this.minValue < 0) {
                this.maxValue = 0;
            }
        }
        
        @Override
        protected void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            final Graphics2D graphics2D;
            (graphics2D = (Graphics2D)graphics).setStroke(new BasicStroke(4.0f));
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            final int width = this.getWidth();
            final int height = this.getHeight();
            final float n = (width - 28) / Math.max(1, this.nbrValues - 1);
            final float n2 = (height - 12 - 12) / Math.max(1, this.maxValue - this.minValue);
            final Player.Type[] array = new Player.Type[4];
            int n3 = 0;
            Player.Type[] values;
            for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
                final Player.Type type;
                if (!(type = values[i]).equals(this.localPlayerType)) {
                    array[n3++] = type;
                }
            }
            array[n3] = this.localPlayerType;
            Player.Type[] array2;
            for (int length2 = (array2 = array).length, j = 0; j < length2; ++j) {
                final Player.Type type2 = array2[j];
                final Graphics2D graphics2D2 = graphics2D;
                Color color = null;
                switch (type2) {
                    case RETAILER: {
                        color = DiagramComponent.COLOR_RETAILER;
                        break;
                    }
                    case WHOLESALER: {
                        color = DiagramComponent.COLOR_WHOLESALER;
                        break;
                    }
                    case DISTRIBUTOR: {
                        color = DiagramComponent.COLOR_DISTRIBUTOR;
                        break;
                    }
                    default: {
                        color = DiagramComponent.COLOR_PRODUCER;
                        break;
                    }
                }
                graphics2D2.setColor(color);
                final ArrayList<HistoryState> history = this.results.getHistory(type2);
                float n4 = this.getValue(0, history.get(0)) - this.minValue;
                for (int k = 1; k < this.nbrValues; ++k) {
                    final float n5;
                    if ((n5 = this.getValue(k, history.get(k)) - this.minValue) != -2.14748365E9f && n4 != -2.14748365E9f) {
                        graphics2D.drawLine(0 + Math.round((k - 1) * n), height - 12 - Math.round(n4 * n2), 0 + Math.round(k * n), height - 12 - Math.round(n5 * n2));
                    }
                    n4 = n5;
                }
            }
            graphics2D.setStroke(new BasicStroke(1.0f));
            final int n7;
            final int n6 = (n7 = height - 12) - Math.round(-this.minValue * n2);
            graphics2D.setColor(DiagramComponent.COLOR_ZERO_LINE);
            graphics2D.drawLine(0, n6, width - 28, n6);
            final Font font = (Font)ResourceManager.getResource("DefaultFont");
            graphics2D.setFont(font);
            final int n8 = width - 5;
            final FontMetrics fontMetrics;
            final int n9 = (fontMetrics = graphics2D.getFontMetrics(font)).getAscent() - fontMetrics.getDescent();
            final String value = String.valueOf(this.minValue);
            final int stringWidth = fontMetrics.stringWidth(value);
            final String value2 = String.valueOf(this.maxValue);
            final int stringWidth2 = fontMetrics.stringWidth(value2);
            final int stringWidth3 = fontMetrics.stringWidth("0");
            drawOutlinedString(graphics2D, value, n8 - stringWidth, n7 + n9 / 2, DiagramComponent.COLOR_VALUE_TEXT, DiagramComponent.COLOR_VALUE_OUTLINE);
            if (this.maxValue != this.minValue) {
                drawOutlinedString(graphics2D, value2, n8 - stringWidth2, 12 + n9 / 2, DiagramComponent.COLOR_VALUE_TEXT, DiagramComponent.COLOR_VALUE_OUTLINE);
            }
            if (Math.abs(n6 - 12) > 20 && Math.abs(n6 - n7) > 20) {
                drawOutlinedString(graphics2D, "0", n8 - stringWidth3, n6 + n9 / 2, DiagramComponent.COLOR_VALUE_TEXT, DiagramComponent.COLOR_VALUE_OUTLINE);
            }
        }
        
        public abstract int getValue(final int p0, final HistoryState p1);
        
        private static void drawOutlinedString(final Graphics graphics, final String s, final int n, final int n2, final Color color, final Color color2) {
            graphics.setColor(color2);
            graphics.drawString(s, n - 1, n2 - 1);
            graphics.drawString(s, n, n2 - 1);
            graphics.drawString(s, n + 1, n2 - 1);
            graphics.drawString(s, n - 1, n2);
            graphics.drawString(s, n + 1, n2);
            graphics.drawString(s, n - 1, n2 + 1);
            graphics.drawString(s, n, n2 + 1);
            graphics.drawString(s, n + 1, n2 + 1);
            graphics.setColor(color);
            graphics.drawString(s, n, n2);
        }
        
        static {
            COLOR_RETAILER = new Color(5399462);
            COLOR_WHOLESALER = new Color(10690638);
            COLOR_DISTRIBUTOR = new Color(13400123);
            COLOR_PRODUCER = new Color(9413693);
            DiagramComponent.COLOR_ZERO_LINE = new Color(11184810);
            DiagramComponent.COLOR_VALUE_TEXT = Color.BLACK;
            DiagramComponent.COLOR_VALUE_OUTLINE = Color.WHITE;
        }
    }
}

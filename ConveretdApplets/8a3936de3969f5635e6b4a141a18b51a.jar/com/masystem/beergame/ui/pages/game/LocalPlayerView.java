// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import com.masystem.beergame.Config;
import com.masystem.beergame.ui.animation.AlphaAnimation;
import com.masystem.beergame.debug.Log;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImageButton;
import com.masystem.beergame.ui.component.swing.ValueChangeListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameIntegerField;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.network.protocol.Player;
import java.awt.Color;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;

public final class LocalPlayerView extends BeerGameImage implements PlayerView
{
    private static Color DARK_RED;
    private static float[] RETAILER_COORDS;
    private static float[] WHOLESALER_COORDS;
    private static float[] DISTRIBUTOR_COORDS;
    private static float[] PRODUCER_COORDS;
    private final Player player;
    
    public LocalPlayerView(final Player player, final String id) {
        super(ResourceManager.getOptimizedImage("local_bg.png"));
        this.setId(id);
        this.player = player;
    }
    
    @Override
    public final void onSetup() {
        final BeerGameTextLabel beerGameTextLabel = new BeerGameTextLabel(this.player.getName());
        this.addChild(beerGameTextLabel);
        beerGameTextLabel.setId("playername");
        beerGameTextLabel.setHorizontalAlignment(0);
        beerGameTextLabel.setSizeRelativeToParent(0.35f, -1.0f);
        beerGameTextLabel.setPositionRelativeToParent(0.475f, 0.205f);
        final BeerGameImage beerGameImage = new BeerGameImage(String.format("local_type_%s.png", this.id));
        this.addChild(beerGameImage);
        beerGameImage.setId("playertype");
        beerGameImage.setPositionRelativeToParent(0.17f, 0.21f);
        final BeerGameTextLabel beerGameTextLabel2 = new BeerGameTextLabel();
        this.addChild(beerGameTextLabel2);
        beerGameTextLabel2.setId("stock");
        beerGameTextLabel2.setFont("InGameBigFont");
        beerGameTextLabel2.setSizeRelativeToParent(0.14f, -1.0f);
        beerGameTextLabel2.setPositionRelativeToParent(0.152f, 0.39f);
        beerGameTextLabel2.setText(String.valueOf(0));
        final BeerGameTextLabel beerGameTextLabel3 = new BeerGameTextLabel();
        this.addChild(beerGameTextLabel3);
        beerGameTextLabel3.setId("result");
        beerGameTextLabel3.setFont("InGameBigFont");
        beerGameTextLabel3.setHorizontalAlignment(2);
        beerGameTextLabel3.setSizeRelativeToParent(0.14f, -1.0f);
        beerGameTextLabel3.setPositionRelativeToParent(0.83f, 0.39f);
        beerGameTextLabel3.setText(String.valueOf(0));
        beerGameTextLabel3.setVisible(true);
        final BeerGameTextLabel beerGameTextLabel4 = new BeerGameTextLabel();
        this.addChild(beerGameTextLabel4);
        beerGameTextLabel4.setId("report");
        beerGameTextLabel4.setFont("InGameReportFont");
        beerGameTextLabel4.setVerticalAlignment(0);
        beerGameTextLabel4.setSizeRelativeToParent(0.43f, 0.4f);
        beerGameTextLabel4.setPositionRelativeToParent(0.476f, 0.525f);
        beerGameTextLabel4.setTextColor(Color.DARK_GRAY);
        beerGameTextLabel4.setVisible(false);
        final BeerGameIntegerField beerGameIntegerField = new BeerGameIntegerField();
        this.addChild(beerGameIntegerField);
        beerGameIntegerField.setId("orderfield");
        beerGameIntegerField.setMaxLength(4);
        beerGameIntegerField.setFont("InGameBigFont");
        beerGameIntegerField.setSizeRelativeToParent(0.11f, -1.0f);
        beerGameIntegerField.setPositionRelativeToParent(0.142f, 0.66f);
        beerGameIntegerField.setEnabled(false);
        beerGameIntegerField.setValueChangeListener(new ValueChangeListener() {
            @Override
            public final void onValueChanged(final int n) {
                LocalPlayerView.this.updateSendButton(n);
            }
        });
        final BeerGameImageButton beerGameImageButton = new BeerGameImageButton("button_send_released.png", "button_send_pressed.png", "button_send_rollover.png", "button_send_disabled.png");
        this.addChild(beerGameImageButton);
        beerGameImageButton.setId("sendbutton");
        beerGameImageButton.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                try {
                    LocalPlayerView.access$100(LocalPlayerView.this).makeTurn(beerGameIntegerField.getValue());
                }
                catch (NumberFormatException ex) {
                    Log.warn("No turn was made. Illegal input.");
                }
            }
        });
        beerGameImageButton.setPositionRelativeToParent(0.86f, 0.77f);
        beerGameImageButton.setEnabled(false);
    }
    
    public final void enableInput() {
        final BeerGameIntegerField beerGameIntegerField;
        (beerGameIntegerField = (BeerGameIntegerField)this.getById("orderfield")).setEnabled(true);
        beerGameIntegerField.getComponent().requestFocusInWindow();
        this.updateSendButton(beerGameIntegerField.getValue());
    }
    
    public final void disableInput() {
        ((BeerGameImageButton)this.getById("sendbutton")).setEnabled(false);
        ((BeerGameIntegerField)this.getById("orderfield")).setEnabled(false);
    }
    
    public final void clearInput() {
        ((BeerGameIntegerField)this.getById("orderfield")).clearValue();
    }
    
    public final void showReport() {
        final BeerGameTextLabel beerGameTextLabel;
        (beerGameTextLabel = (BeerGameTextLabel)this.getById("report")).setVisible(true);
        final AlphaAnimation alphaAnimation;
        (alphaAnimation = new AlphaAnimation(beerGameTextLabel, 0.0f, 1.0f)).setCacheAutoEnabled(false);
        alphaAnimation.start();
    }
    
    public final void hideReport() {
        this.getById("report").setVisible(false);
    }
    
    public final void setReport(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final BeerGameTextLabel beerGameTextLabel = (BeerGameTextLabel)this.getById("report");
        final int n8 = n7 - n5 - n3;
        final StringBuilder sb;
        (sb = new StringBuilder(768)).append("<html>");
        if (Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) {
            sb.append("<p><strong>Week ").append(n).append(" income and expenses:</strong></p>");
        }
        else {
            sb.append("<p><strong>Week ").append(n).append(" costs:</strong></p>");
        }
        sb.append("<table width=" + beerGameTextLabel.getWidth() + ">");
        sb.append("<tr>");
        sb.append("<td width=65%>").append(Math.abs(n2)).append(" cases in ").append((n2 < 0) ? "backlog" : "stock").append(":").append("</td>");
        sb.append("<td width=35% align=right>").append(formatRevenueHtml(-n3)).append("</td>");
        sb.append("</tr>");
        sb.append("<tr>");
        if (Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) {
            sb.append("<td width=65%>").append(n4).append(" cases purchased:").append("</td>");
            sb.append("<td width=35% align=right>").append(formatRevenueHtml(-n5)).append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td width=65%>").append(n6).append(" cases sold:").append("</td>");
            sb.append("<td width=35% align=right>").append(formatRevenueHtml(n7)).append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td width=65%><strong>Net revenue:</strong>").append("</td>");
            sb.append("<td width=35% align=right><strong>").append(formatRevenueHtml(n8)).append("</strong></td>");
            sb.append("</tr>");
            sb.append("</table>");
        }
        beerGameTextLabel.setText(sb.toString());
    }
    
    private static String formatRevenueHtml(final int n) {
        if (Config.RESULT_TYPE != Config.ResultType.TRANSACTIONS_AND_STOCK) {
            return String.format("$%d", Math.abs(n));
        }
        if (n < 0) {
            return String.format("<font color=#7f0000>- $%d</font>", -n);
        }
        if (n > 0) {
            return String.format("<font color=#007f00>+ $%d</font>", n);
        }
        return "± $0";
    }
    
    private void updateSendButton(final int n) {
        final BeerGameImageButton beerGameImageButton;
        if ((beerGameImageButton = (BeerGameImageButton)this.getById("sendbutton")).isEnabled() && n == -1) {
            beerGameImageButton.setEnabled(false);
            return;
        }
        if (!beerGameImageButton.isEnabled() && n != -1) {
            beerGameImageButton.setEnabled(true);
        }
    }
    
    public final void setStock(final int text) {
        ((BeerGameTextLabel)this.getById("stock")).setText(text);
        ((BeerGameTextLabel)this.getById("stock")).setTextColor((text < 0) ? LocalPlayerView.DARK_RED : Color.BLACK);
    }
    
    public final void setResult(final int text) {
        ((BeerGameTextLabel)this.getById("result")).setText(text);
        ((BeerGameTextLabel)this.getById("result")).setTextColor((text < 0) ? LocalPlayerView.DARK_RED : Color.BLACK);
    }
    
    public final int getResult() {
        return Integer.parseInt(((BeerGameTextLabel)this.getById("result")).getText());
    }
    
    @Override
    public final void updateData(final Player player) {
        ((BeerGameTextLabel)this.getById("playername")).setText(player.getName());
    }
    
    protected static float[] getCoords(final Player.Type type) {
        switch (type) {
            case PRODUCER: {
                return LocalPlayerView.PRODUCER_COORDS;
            }
            case DISTRIBUTOR: {
                return LocalPlayerView.DISTRIBUTOR_COORDS;
            }
            case WHOLESALER: {
                return LocalPlayerView.WHOLESALER_COORDS;
            }
            default: {
                return LocalPlayerView.RETAILER_COORDS;
            }
        }
    }
    
    static /* synthetic */ GamePage access$100(LocalPlayerView localPlayerView) {
        return (GamePage)(localPlayerView = localPlayerView).findAncestorById(GamePage.class.getSimpleName());
    }
    
    static {
        LocalPlayerView.DARK_RED = new Color(8323072);
        LocalPlayerView.RETAILER_COORDS = new float[] { 0.34f, 0.325f };
        LocalPlayerView.WHOLESALER_COORDS = new float[] { 0.68f, 0.325f };
        LocalPlayerView.DISTRIBUTOR_COORDS = new float[] { 0.34f, 0.72f };
        LocalPlayerView.PRODUCER_COORDS = new float[] { 0.68f, 0.72f };
    }
}

import edu.wise.utils.FormatUtils;
import edu.wise.stats.StatUtils;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DistDraw extends DistPanel
{
    static int accuracy;
    static int spaces;
    
    static {
        DistDraw.accuracy = 12;
        DistDraw.spaces = 3;
    }
    
    DistDraw() {
    }
    
    DistDraw(final int n, final int n2) {
        super(n, n2);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(DistPanel.regularFont);
        graphics.drawLine(DistPanel.get_x_base(), (int)DistPanel.pop_scale + 1, DistPanel.get_x_base(), 10);
        graphics.drawLine(DistPanel.get_x_base(), 281, DistPanel.get_x_base(), (int)DistPanel.pop_scale + 20);
        graphics.drawLine(0, 0, super.WIDTH, 0);
        graphics.drawLine(0, super.HEIGHT - 1, super.WIDTH, super.HEIGHT - 1);
        graphics.drawLine(0, 0, 0, super.HEIGHT);
        graphics.drawLine(super.WIDTH, 0, super.WIDTH, super.HEIGHT);
        graphics.setColor(Color.black);
        graphics.drawLine(DistPanel.get_x_base(), DistPanel.get_y_base() + 1, DistPanel.get_x_base() + DistPanel.x_scale * 101, DistPanel.get_y_base() + 1);
        graphics.drawLine(DistPanel.get_x_base(), (int)DistPanel.pop_scale + 1, DistPanel.get_x_base() + DistPanel.x_scale * 101, (int)DistPanel.pop_scale + 1);
        if (DistPanel.show_pop) {
            graphics.setColor(Color.blue);
            graphics.drawPolygon(Distribution.get_alt_y_base_val(), Distribution.get_pop_dist(), Distribution.get_pop_dist().length);
            graphics.drawPolygon(Distribution.get_y_base_val(), Distribution.get_pop_dist_samp(), Distribution.get_pop_dist_samp().length);
        }
        graphics.setColor(DistPanel.darkGreen);
        if (DistPanel.show_alt) {
            graphics.drawPolygon(Distribution.get_y_base_val(), Distribution.get_alt_dist(), Distribution.get_alt_dist().length);
            graphics.drawPolygon(Distribution.get_y_base_val(), Distribution.get_alt_dist_samp(), Distribution.get_alt_dist_samp().length);
        }
        graphics.setColor(Color.black);
        int get_x_base = DistPanel.get_x_base();
        int n = 5;
        if (Values.n > 350) {
            n = 2;
        }
        else if (Values.n > 250) {
            n = 3;
        }
        else if (Values.n > 200) {
            n = 4;
        }
        for (int i = 0; i < Values.cases_arr.length; ++i) {
            if (Values.cases_arr[i] > 0) {
                graphics.drawLine(DistPanel.get_x_base() + i * DistPanel.x_scale - 2, (int)(DistPanel.pop_scale - n * Values.cases_arr[i]), DistPanel.get_x_base() + i * DistPanel.x_scale - 2, (int)DistPanel.pop_scale);
                graphics.drawLine(DistPanel.get_x_base() + i * DistPanel.x_scale - 2, (int)(DistPanel.pop_scale - n * Values.cases_arr[i]), DistPanel.get_x_base() + i * DistPanel.x_scale + 2, (int)(DistPanel.pop_scale - n * Values.cases_arr[i]));
                graphics.drawLine(DistPanel.get_x_base() + i * DistPanel.x_scale + 2, (int)(DistPanel.pop_scale - n * Values.cases_arr[i]), DistPanel.get_x_base() + i * DistPanel.x_scale + 2, (int)DistPanel.pop_scale);
            }
            get_x_base += DistPanel.x_scale;
        }
        DistPanel.x_scale = 4;
        graphics.setColor(Color.red);
        int get_x_base2 = DistPanel.get_x_base();
        for (int j = 0; j < Values.means_arr.length; ++j) {
            if (Values.means_arr[j] > 0) {
                graphics.drawLine(get_x_base2 - 2, 280 - 5 * Values.means_arr[j], get_x_base2 - 2, 280);
                graphics.drawLine(get_x_base2 - 2, 280 - 5 * Values.means_arr[j], get_x_base2 + 2, 280 - 5 * Values.means_arr[j]);
                graphics.drawLine(get_x_base2 + 2, 280 - 5 * Values.means_arr[j], get_x_base2 + 2, 280);
            }
            get_x_base2 += DistPanel.x_scale;
        }
        if (DistPanel.draw_arrow) {
            int n2;
            for (n2 = 0; Distribution.calc_z(Values.obt_mean, Distribution.alt_mu, Distribution.sigma) > Distribution.get_alt_z(n2) && n2 < 100; ++n2) {}
            final int n3 = DistPanel.get_x_base() + n2 * DistPanel.x_scale;
            final int n4 = DistPanel.get_y_base() + 18;
            graphics.setColor(Color.red);
            graphics.drawLine(n3 + 4, n4 + 7, n3 + 4, n4 + 7);
            graphics.drawLine(n3 + 3, n4 + 6, n3 + 3, n4 + 7);
            graphics.drawLine(n3 + 2, n4 + 5, n3 + 2, n4 + 7);
            graphics.drawLine(n3 + 1, n4 + 4, n3 + 1, n4 + 12);
            graphics.drawLine(n3, n4 + 3, n3, n4 + 12);
            graphics.drawLine(n3 - 1, n4 + 4, n3 - 1, n4 + 12);
            graphics.drawLine(n3 - 2, n4 + 5, n3 - 2, n4 + 7);
            graphics.drawLine(n3 - 3, n4 + 6, n3 - 3, n4 + 7);
            graphics.drawLine(n3 - 4, n4 + 7, n3 - 4, n4 + 7);
            graphics.setColor(Color.black);
        }
        graphics.setColor(Color.black);
        final String[] array = new String[9];
        for (int n5 = -4; n5 <= 4.0; ++n5) {
            array[n5 + 4] = Double.toString(StatUtils.z_to_xi(n5, Distribution.mu, Distribution.sigma));
        }
        graphics.setFont(DistPanel.smallFont);
        for (int k = 0; k < DistPanel.y_pop_lab.length; ++k) {
            String substring;
            if (DistPanel.y_pop_lab[k] == 0.0) {
                substring = "0.000";
            }
            else {
                substring = Double.toString(DistPanel.y_pop_lab[k]).substring(0, FormatUtils.word_length(DistPanel.y_pop_lab[k], 5));
            }
            String substring2;
            if (DistPanel.y_alt_lab[k] == 0.0) {
                substring2 = "0.000";
            }
            else {
                substring2 = Double.toString(DistPanel.y_alt_lab[k]).substring(0, FormatUtils.word_length(DistPanel.y_alt_lab[k], 5));
            }
            graphics.drawString(substring, 5, (int)(DistPanel.pop_scale + super.smallFM.getHeight() / 2 - k * DistPanel.y_temp));
            graphics.drawString(substring2, 5, (int)(280 + super.smallFM.getHeight() / 2 - k * DistPanel.y_temp));
            final int n6 = super.smallFM.stringWidth(substring) + 7;
        }
        int get_x_base3 = DistPanel.get_x_base();
        final int n7 = 298;
        for (int l = 0; l < array.length; ++l) {
            graphics.drawString(array[l], get_x_base3 - super.smallFM.stringWidth(array[l]) / 2, n7);
            get_x_base3 += 50;
        }
        graphics.setFont(DistPanel.smallFont);
        int get_x_base4 = DistPanel.get_x_base();
        int n8 = 6;
        for (int n9 = 0; n9 < DistPanel.x_pop_lab.length; ++n9) {
            if (n9 == 12 || n9 == 37 || n9 == 62 || n9 == 87) {
                final int n10 = 6;
                graphics.drawLine(get_x_base4 + DistPanel.x_scale / 2, (int)(DistPanel.pop_scale + n10), get_x_base4 + DistPanel.x_scale / 2, (int)(DistPanel.pop_scale + 1.0));
                graphics.drawLine(get_x_base4 + DistPanel.x_scale / 2, 280 + n10, get_x_base4 + DistPanel.x_scale / 2, 281);
                n8 = 3;
            }
            if (n9 == 0 || n9 == 25 || n9 == 50 || n9 == 75 || n9 == 100) {
                n8 = 6;
            }
            graphics.drawLine(get_x_base4, (int)(DistPanel.pop_scale + n8), get_x_base4, (int)(DistPanel.pop_scale + 1.0));
            graphics.drawLine(get_x_base4, 280 + n8, get_x_base4, 281);
            get_x_base4 += DistPanel.x_scale;
            n8 = 3;
        }
    }
}

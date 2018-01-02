import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Temp1000 extends Applet implements Runnable, KeyListener, ActionListener
{
    static final int TITLE = 0;
    static final int RUNNING = 1;
    static final int START_LEVEL = 2;
    static final int TEST = 3;
    static final int DYING = 4;
    static final int GAME_OVER = 5;
    static final int NEW_LEVEL = 6;
    static final int BEEN_SHOT = 7;
    static final int GRAV = 1;
    int frames;
    Thread runner;
    Image Buffer;
    Graphics gBuffer;
    Dimension d;
    Array3D eye;
    Image title_jpg;
    Image craft_image;
    Image game_over_image;
    URL base;
    MediaTracker mt;
    AudioClip laser;
    AudioClip mega_sound;
    AudioClip bang;
    Level3D level1;
    Craft craft1;
    Star[] star1;
    Baddy2[] bad;
    Baddy3[] bad1;
    MBlastG[] mega;
    MBlastG[] mega1;
    Shot[] shot1;
    Game game1;
    TextField textfield;
    Button okButton;
    StarFld star_field;
    MBlastG mega_icon;
    MBlastG mega1_icon;
    static int craft_status;
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void draw_level_number() {
        final String value = String.valueOf(this.game1.get_level());
        this.game1.com1.set_red_colour(this.game1.com1.get_red_colour(0) - 2, 0);
        this.game1.com1.set_green_colour(this.game1.com1.get_green_colour(0) - 2, 0);
        this.game1.com1.set_blue_colour(this.game1.com1.get_blue_colour(0) - 2, 0);
        if (this.game1.com1.get_blue_colour(0) < 0) {
            this.game1.com1.set_red_colour(0, 0);
            this.game1.com1.set_green_colour(0, 0);
            this.game1.com1.set_blue_colour(0, 0);
        }
        final int stringWidth = this.getFontMetrics(this.getFont()).stringWidth("Level " + value);
        this.gBuffer.setColor(new Color(this.game1.com1.get_red_colour(0), this.game1.com1.get_green_colour(0), this.game1.com1.get_blue_colour(0)));
        this.gBuffer.setFont(new Font("Helvetica", 0, 20));
        this.gBuffer.drawString("Level " + value, 230 - stringWidth / 2, 250);
    }
    
    public void check_mega_blast() {
        for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
            if (this.star1[i].com.alive() && this.star1[i].com.on_web()) {
                this.star1[i].com.set_alive(false);
                this.star1[i].com.move_object(0.0, 0.0, 2000.0);
                this.game1.increase_score(this.star1[i].com.get_baddy_value());
            }
        }
        for (int j = 0; j < this.game1.get_no_baddies(1); ++j) {
            if (this.bad[j].com.alive() && this.bad[j].com.on_web()) {
                this.bad[j].com.set_alive(false);
                this.bad[j].com.move_object(0.0, 0.0, 2000.0);
                this.game1.increase_score(this.bad[j].com.get_baddy_value());
            }
        }
        for (int k = 0; k < this.game1.get_no_baddies(2); ++k) {
            if (this.bad1[k].com.alive() && this.bad1[k].com.on_web()) {
                this.bad1[k].com.set_alive(false);
                this.bad1[k].com.move_object(0.0, 0.0, 2000.0);
                this.game1.increase_score(this.bad1[k].com.get_baddy_value());
            }
            if (this.bad1[k].com_shot.alive()) {
                this.bad1[k].com_shot.set_alive(false);
                this.bad1[k].com_shot.set_visible(false);
                this.game1.increase_score(this.bad1[k].com_shot.get_baddy_value());
            }
        }
    }
    
    public void drawStuff() {
        switch (this.game1.get_game_status()) {
            case 0: {
                this.gBuffer.drawImage(this.title_jpg, 0, 0, this);
                if (this.game1.get_new_game()) {
                    switch ((int)Math.round(Math.random() * 4.0)) {
                        case 0: {
                            this.gBuffer.setColor(Color.black);
                            break;
                        }
                        case 1: {
                            this.gBuffer.setColor(Color.blue);
                            break;
                        }
                        case 2: {
                            this.gBuffer.setColor(Color.red);
                            break;
                        }
                        case 3: {
                            this.gBuffer.setColor(Color.blue);
                            break;
                        }
                    }
                    this.game1.set_new_game(false);
                }
                this.gBuffer.setFont(new Font("Helvetica", 0, 10));
                this.gBuffer.drawString("Copyright 2001-Nick D'Arcy", 354, 482);
                this.gBuffer.drawString("boomahtrix@btinternet.com", 350, 400);
                this.scramble_screen();
            }
            case 1: {
                this.move_craft();
                this.check_baddies();
                this.check_shot();
                this.check_craft();
                this.check_jumping();
                this.move_eye();
                this.draw_stuff_to_screen();
                this.draw_special();
                this.draw_screen_overlay();
                this.check_new_level();
                this.check_for_bonus();
            }
            case 2: {
                this.begin_level();
                this.move_eye();
                this.draw_stuff_to_screen();
                this.draw_screen_overlay();
                this.draw_level_number();
            }
            case 3: {
                final int[] array = new int[3];
                final int[] array2 = new int[3];
                array[0] = 10;
                array[1] = 20;
                array[2] = 30;
                array2[0] = 10;
                array2[1] = 20;
                array2[2] = 50;
                this.gBuffer.setColor(Color.black);
                this.gBuffer.fillRect(0, 0, this.d.width, this.d.height);
                this.gBuffer.setColor(Color.white);
                this.gBuffer.fillPolygon(array, array2, 3);
            }
            case 4: {
                this.move_eye();
                this.kill_craft();
                this.draw_stuff_to_screen();
                this.draw_screen_overlay();
            }
            case 5: {
                if (this.game1.get_game_over_status() != 3) {
                    this.game_over_ani();
                    this.draw_stuff_to_screen();
                }
                if (this.game1.get_game_over_status() == 3 && this.game1.get_score() > this.game1.get_high_score_value(9)) {
                    this.textfield.setBounds(220, 350, 60, 20);
                    this.textfield.setBackground(Color.black);
                    this.textfield.setForeground(Color.white);
                    this.okButton.setBounds(285, 350, 20, 20);
                    this.okButton.setBackground(Color.black);
                    this.okButton.setForeground(Color.white);
                    this.okButton.addActionListener(this);
                    this.add(this.textfield);
                    this.add(this.okButton);
                    this.textfield.requestFocus();
                    this.game1.set_game_over_status(5);
                }
                else if (this.game1.get_game_over_status() == 3) {
                    this.game1.set_game_over_status(4);
                }
                if (this.game1.get_game_over_status() == 4) {
                    this.gBuffer.setColor(Color.black);
                    this.gBuffer.fillRect(0, 0, this.d.width, this.d.height);
                    this.gBuffer.drawImage(this.game_over_image, 0, 20, this);
                    this.game1.draw_high_score_table(this.gBuffer);
                }
                if (this.game1.get_game_over_status() == 5) {
                    this.gBuffer.setColor(Color.black);
                    this.gBuffer.fillRect(0, 0, this.d.width, this.d.height);
                    this.gBuffer.drawImage(this.game_over_image, 0, 20, this);
                    final String s = "Congratulations \u2013 You got a high score";
                    final String s2 = "Please enter your name";
                    final int stringWidth = this.getFontMetrics(this.getFont()).stringWidth(s);
                    final int stringWidth2 = this.getFontMetrics(this.getFont()).stringWidth(s2);
                    this.gBuffer.setColor(Color.white);
                    this.gBuffer.drawString(s, 220 - stringWidth / 2, 300);
                    this.gBuffer.drawString(s2, 225 - stringWidth2 / 2, 330);
                    return;
                }
                break;
            }
            case 6: {
                this.draw_stuff_to_screen();
                this.draw_screen_overlay();
                this.level_complete();
            }
            case 7: {
                this.draw_stuff_to_screen();
                this.craft_shot();
                this.move_eye_craft_shot();
            }
        }
    }
    
    public void game_over_ani() {
        if (this.game1.get_game_over_status() == 1) {
            this.game1.set_game_over_status(2);
            for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
                this.star1[i].com.set_visible(false);
            }
            for (int j = 0; j < this.game1.get_no_baddies(1); ++j) {
                this.bad[j].com.set_visible(false);
            }
            this.craft1.com.set_visible(false);
        }
        if (this.game1.get_game_over_status() == 2) {
            if (this.eye.get_z() < 800.0) {
                this.eye.set_z(this.eye.get_z() + 20.0);
                this.level1.com.rotate_y_axis(20.0);
                return;
            }
            this.game1.set_game_over_status(3);
        }
    }
    
    public void level_complete() {
        if (this.eye.get_z() > -(this.level1.get_length_of_level() + 500)) {
            this.craft1.com.move_object(0.0, 0.0, -25.0);
            this.eye.set_z(this.eye.get_z() - 15.0);
        }
        if (this.craft1.com.get_red_colour(0) < 20) {
            this.game1.set_game_status(2);
            this.reset_for_new_level();
            this.gBuffer.setColor(Color.white);
            this.gBuffer.fillRect(0, 0, this.d.width, this.d.height);
        }
        if (this.craft1.com.centre.get_z() < -this.level1.get_length_of_level()) {
            this.craft1.com.rotate_z_axis(15.0);
            this.game1.set_inputs_allowed(false);
            this.craft1.com.set_red_colour(this.craft1.com.get_red_colour(0) - 5, 0);
            this.craft1.com.set_green_colour(this.craft1.com.get_green_colour(0) - 5, 0);
        }
    }
    
    public void check_new_level() {
        if (!this.star1[this.get_number_of_baddies_safe(0)].com.alive() && !this.bad[this.get_number_of_baddies_safe(1)].com.alive() && !this.bad1[this.get_number_of_baddies_safe(2)].com.alive()) {
            this.mega_sound.play();
            this.game1.increase_score(this.game1.get_no_jumps() * 10000);
            if (this.game1.mega_blast_allowed()) {
                this.game1.increase_score(50000);
            }
            this.game1.set_level(this.game1.get_level() + 1);
            this.star_field.set_star_positions_for_level(this.game1.get_level());
            this.game1.set_game_status(6);
            this.game1.com1.set_red_colour(255, 0);
            this.game1.com1.set_green_colour(255, 0);
            this.game1.com1.set_blue_colour(255, 0);
            int n = 0;
            do {
                this.shot1[n].com.set_visible(false);
            } while (++n < 3);
        }
    }
    
    public void get_parameters() {
        final String parameter = this.getParameter("speed");
        if (parameter == null) {
            this.game1.set_SPEED(15);
            return;
        }
        this.game1.set_SPEED(Integer.parseInt(parameter));
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 113) {
            if (!this.game1.get_new_game()) {
                this.game1.set_new_game(true);
                this.game1.set_game_status(2);
                this.eye.set_Array3D(0.0, 0.0, 900.0);
                this.level1.com.rotate_y_axis(90.0);
                this.level1.com.rotate_x_axis(90.0);
                this.level1.com.rotate_z_axis(90.0);
            }
        }
        else if (this.game1.get_inputs_allowed()) {
            if (keyEvent.getKeyCode() == 114) {
                this.restart_level();
            }
            else if (keyEvent.getKeyCode() == 38) {
                this.game1.set_up_button_down(true);
            }
            else if (keyEvent.getKeyCode() == 40) {
                if (this.craft1.get_jumping() == 0 && this.game1.get_game_status() != 6) {
                    this.game1.set_down_button_down(true);
                }
            }
            else if (keyEvent.getKeyCode() == 37 && !this.game1.get_left_button_down()) {
                this.craft1.com.set_going_left(true);
                this.craft1.com.reset_counter();
                this.craft1.com.set_first(true);
                this.game1.set_left_button_down(true);
                this.craft1.com.decrease_grid_pos();
                this.craft1.move_craft_to_grid_position();
            }
            else if (keyEvent.getKeyCode() == 39 && !this.game1.get_right_button_down()) {
                this.craft1.com.set_going_right(true);
                this.craft1.com.reset_counter();
                this.craft1.com.set_first(true);
                this.game1.set_right_button_down(true);
                this.craft1.com.increase_grid_pos();
                this.craft1.move_craft_to_grid_position();
            }
            else if (keyEvent.getKeyCode() == 32 && this.game1.mega_blast_allowed()) {
                this.mega_sound.play();
                this.game1.set_mega_blast_allowed(false);
                this.game1.set_mega_blast(true);
                this.check_mega_blast();
            }
        }
        if (this.game1.get_game_over_status() == 4) {
            this.restart_level();
        }
    }
    
    public void check_jumping() {
        if (this.game1.get_down_button_down() && this.craft1.com.centre.get_z() == 0.0 && this.game1.get_no_jumps() > 0) {
            this.game1.decrease_no_jumps();
            this.craft1.com.velocity.set_z(30.0);
            this.craft1.com.move_object(0.0, 0.0, this.craft1.com.velocity.get_z());
            this.eye.set_z(this.craft1.com.centre.get_z());
            this.craft1.set_jumping(1);
            return;
        }
        if (this.craft1.get_jumping() == 1) {
            if (this.craft1.com.centre.get_z() - 1.0 > 0.0) {
                this.craft1.com.velocity.set_z(this.craft1.com.velocity.get_z() - 1.0);
                this.craft1.com.move_object(0.0, 0.0, this.craft1.com.velocity.get_z());
                this.eye.set_z(this.craft1.com.centre.get_z());
                return;
            }
            this.craft1.set_jumping(0);
            this.craft1.com.velocity.set_z(0.0);
            this.craft1.move_craft_to(this.craft1.com.centre.get_x(), this.craft1.com.centre.get_y(), 0.0);
        }
    }
    
    public void draw_stuff_to_screen() {
        this.gBuffer.setColor(Color.black);
        this.gBuffer.fillRect(0, 0, this.d.width, this.d.height);
        this.draw_stars();
        int n = 0;
        do {
            if (this.game1.mega_blast()) {
                this.mega[n].com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
                this.mega1[n].com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
                this.mega[n].com.move_object(0.0, 0.0, 15.0);
                this.mega[n].com.rotate_z_axis(2.0);
                this.mega1[n].com.move_object(0.0, 0.0, 15.0);
                this.mega1[n].com.rotate_z_axis(-2.0);
                if (this.mega[19].com.centre.get_z() <= 500.0) {
                    continue;
                }
                this.game1.set_mega_blast(false);
                int n2 = 0;
                do {
                    this.mega[n2].move_mega_blast_to(0.0, 0.0, -300 - n2 * 20);
                    this.mega1[n2].move_mega_blast_to(0.0, 0.0, -300 - n2 * 20);
                } while (++n2 < 20);
            }
        } while (++n < 20);
        this.level1.convert_tris_to_polys(this.d.width, this.d.height, this.eye);
        int n3 = 0;
        do {
            this.level1.com.set_total_to_single_poly(n3);
            if (Temp1000.craft_status == 1 || this.game1.get_game_status() == 1) {
                if (this.game1.get_game_status() == 1) {
                    this.set_fill_colour(n3);
                }
                this.gBuffer.setColor(new Color(this.level1.com.get_red_fill(0, n3), this.level1.com.get_green_fill(0, n3), this.level1.com.get_blue_fill(0, n3)));
                this.level1.com.draw_object_poly(this.gBuffer);
            }
            if (this.level1.com.get_red_colour(0) < this.level1.com.get_red_colour(1)) {
                this.level1.com.set_red_colour(this.level1.com.get_red_colour(0) + 1, 0);
                if (this.level1.com.get_red_colour(0) > this.level1.com.get_red_colour(1)) {
                    this.level1.com.set_red_colour(this.level1.com.get_red_colour(1), 0);
                }
            }
            if (this.level1.com.get_green_colour(0) < this.level1.com.get_green_colour(1)) {
                this.level1.com.set_green_colour(this.level1.com.get_green_colour(0) + 1, 0);
                if (this.level1.com.get_green_colour(0) > this.level1.com.get_green_colour(1)) {
                    this.level1.com.set_green_colour(this.level1.com.get_green_colour(1), 0);
                }
            }
            if (this.level1.com.get_blue_colour(0) < this.level1.com.get_blue_colour(1)) {
                this.level1.com.set_blue_colour(this.level1.com.get_blue_colour(0) + 1, 0);
                if (this.level1.com.get_blue_colour(0) > this.level1.com.get_blue_colour(1)) {
                    this.level1.com.set_blue_colour(this.level1.com.get_blue_colour(1), 0);
                }
            }
            this.gBuffer.setColor(new Color(this.level1.com.get_red_colour(0), this.level1.com.get_green_colour(0), this.level1.com.get_blue_colour(0)));
            this.level1.com.draw_object_poly_line(this.gBuffer);
        } while (++n3 < 12);
        for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
            if (this.star1[i].com.get_visible() && this.star1[i].com.alive()) {
                this.star1[i].com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
            }
        }
        for (int j = 0; j < this.game1.get_no_baddies(1); ++j) {
            if (this.bad[j].com.get_visible() && this.bad[j].com.alive()) {
                this.bad[j].com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
            }
        }
        for (int k = 0; k < this.game1.get_no_baddies(2); ++k) {
            if (this.bad1[k].com.get_visible() && this.bad1[k].com.alive()) {
                this.bad1[k].com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
            }
            if (this.bad1[k].com_shot.get_visible()) {
                this.bad1[k].com_shot.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
            }
        }
        if (this.craft1.com.get_visible()) {
            this.craft1.com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
        }
        if (this.game1.get_shot()) {
            int n4 = 0;
            do {
                if (this.shot1[n4].com.get_visible()) {
                    this.shot1[n4].com.draw_object(this.gBuffer, this.d.width, this.d.height, this.eye);
                }
            } while (++n4 < 3);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.okButton) {
            this.game1.set_high_score(this.textfield.getText());
            this.okButton.removeActionListener(this);
            this.remove(this.okButton);
            this.remove(this.textfield);
            this.requestFocus();
            this.game1.set_game_over_status(4);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public int get_tri_from_grid_pos() {
        int n = 0;
        switch (this.craft1.com.get_grid_position()) {
            case 0: {
                n = 10;
                break;
            }
            case 1: {
                n = 8;
                break;
            }
            case 2: {
                n = 6;
                break;
            }
            case 3: {
                n = 4;
                break;
            }
            case 4: {
                n = 2;
                break;
            }
            case 5: {
                n = 0;
                break;
            }
            case 6: {
                n = 22;
                break;
            }
            case 7: {
                n = 20;
                break;
            }
            case 8: {
                n = 18;
                break;
            }
            case 9: {
                n = 16;
                break;
            }
            case 10: {
                n = 14;
                break;
            }
            case 11: {
                n = 12;
                break;
            }
        }
        return n;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Tempest 1000, Copyright 2001 by Nick D'Arcy.";
    }
    
    public void check_shot() {
        if (this.game1.get_up_button_down() && !this.game1.get_shot()) {
            this.game1.set_shot(true);
            this.shot1[0].com.set_grid_position(this.craft1.com.get_grid_position());
            this.shot1[0].move_shot_to((int)(Math.sin(0.017453292519943295 * (this.shot1[0].com.get_grid_position() * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (this.shot1[0].com.get_grid_position() * 30 + 15)) * 160.0), this.craft1.com.centre.get_z());
            this.shot1[0].com.set_alive(true);
            this.shot1[0].com.set_shot_start((int)this.craft1.com.centre.get_z());
            this.laser.play();
            this.shot1[0].com.velocity.set_z(-30.0);
            this.shot1[0].com.set_visible(true);
        }
        if (this.game1.get_shot()) {
            if (this.shot1[0].com.alive()) {
                this.shot1[0].com.move_object(0.0, 0.0, this.shot1[0].com.velocity.get_z());
                this.shot1[0].com.rotate_z_axis(15.0);
            }
            if (this.shot1[0].com.centre.get_z() < this.shot1[0].com.get_shot_start() - 51 && !this.shot1[1].com.alive() && !this.shot1[1].com.get_dead_on_this_go()) {
                this.shot1[1].com.set_grid_position(this.craft1.com.get_grid_position());
                this.shot1[1].move_shot_to((int)(Math.sin(0.017453292519943295 * (this.shot1[1].com.get_grid_position() * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (this.shot1[1].com.get_grid_position() * 30 + 15)) * 160.0), this.craft1.com.centre.get_z());
                this.shot1[1].com.set_alive(true);
                this.shot1[1].com.velocity.set_z(-30.0);
                this.shot1[1].com.set_visible(true);
            }
            else if (this.shot1[1].com.alive()) {
                this.shot1[1].com.move_object(0.0, 0.0, this.shot1[1].com.velocity.get_z());
                this.shot1[1].com.rotate_z_axis(-15.0);
            }
            if (this.shot1[0].com.centre.get_z() < this.shot1[0].com.get_shot_start() - 102 && !this.shot1[2].com.alive() && !this.shot1[2].com.get_dead_on_this_go()) {
                this.shot1[2].com.set_grid_position(this.craft1.com.get_grid_position());
                this.shot1[2].move_shot_to((int)(Math.sin(0.017453292519943295 * (this.shot1[2].com.get_grid_position() * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (this.shot1[2].com.get_grid_position() * 30 + 15)) * 160.0), this.craft1.com.centre.get_z());
                this.shot1[2].com.set_alive(true);
                this.shot1[2].com.velocity.set_z(-30.0);
                this.shot1[2].com.set_visible(true);
            }
            else if (this.shot1[2].com.alive()) {
                this.shot1[2].com.move_object(0.0, 0.0, this.shot1[2].com.velocity.get_z());
                this.shot1[2].com.rotate_z_axis(15.0);
            }
            int n = 0;
            do {
                if (this.shot1[n].com.alive() && this.shot1[n].com.centre.get_z() < -this.level1.get_length_of_level()) {
                    this.shot1[n].com.set_visible(false);
                    this.shot1[n].com.set_alive(false);
                    this.shot1[n].move_shot_to(0.0, 0.0, 1000.0);
                    this.shot1[n].com.set_dead_on_this_go(true);
                }
            } while (++n < 3);
            if (!this.shot1[0].com.alive() && !this.shot1[1].com.alive() && !this.shot1[2].com.alive()) {
                this.game1.set_shot(false);
                int n2 = 0;
                do {
                    this.shot1[n2].com.set_dead_on_this_go(false);
                } while (++n2 < 3);
            }
        }
        if (this.game1.get_shot()) {
            int n3 = 0;
            do {
                for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
                    if ((this.shot1[n3].com.get_grid_position() == this.star1[i].com.get_grid_position() && this.shot1[n3].com.alive() && this.star1[i].com.alive() && this.shot1[n3].com.centre.get_z() < this.star1[i].com.centre.get_z() && !this.star1[i].com.top_of_web()) || (this.craft1.get_jumping() != 0 && this.star1[i].com.top_of_web() && this.shot1[n3].com.get_grid_position() == this.star1[i].com.get_grid_position() && this.shot1[n3].com.alive() && this.star1[i].com.alive() && this.shot1[n3].com.centre.get_z() < this.star1[i].com.centre.get_z())) {
                        this.bang.play();
                        this.game1.increase_score(this.star1[n3].com.get_baddy_value());
                        this.shot1[n3].com.set_alive(false);
                        this.shot1[n3].com.set_visible(false);
                        this.star1[i].com.set_alive(false);
                        this.star1[i].com.set_visible(false);
                        this.star1[i].com.set_on_web(false);
                        this.shot1[n3].move_shot_to(0.0, 0.0, 1000.0);
                        this.star1[i].com.move_object(0.0, 0.0, 2000.0);
                        this.shot1[n3].com.set_dead_on_this_go(true);
                    }
                }
            } while (++n3 < 3);
            int n4 = 0;
            do {
                for (int j = 0; j < this.game1.get_no_baddies(1); ++j) {
                    if ((this.shot1[n4].com.get_grid_position() == this.bad[j].com.get_grid_position() && this.shot1[n4].com.alive() && this.bad[j].com.alive() && this.shot1[n4].com.centre.get_z() < this.bad[j].com.centre.get_z() && !this.bad[j].com.top_of_web()) || (this.craft1.get_jumping() != 0 && this.bad[j].com.top_of_web() && this.shot1[n4].com.get_grid_position() == this.bad[j].com.get_grid_position() && this.shot1[n4].com.alive() && this.bad[j].com.alive() && this.shot1[n4].com.centre.get_z() < this.bad[j].com.centre.get_z())) {
                        this.bang.play();
                        this.game1.increase_score(this.bad[n4].com.get_baddy_value());
                        this.shot1[n4].com.set_alive(false);
                        this.shot1[n4].com.set_visible(false);
                        this.bad[j].com.set_alive(false);
                        this.bad[j].com.set_visible(false);
                        this.bad[j].com.set_on_web(false);
                        this.shot1[n4].move_shot_to(0.0, 0.0, 1000.0);
                        this.bad[j].com.move_object(0.0, 0.0, 2000.0);
                        this.shot1[n4].com.set_dead_on_this_go(true);
                    }
                }
            } while (++n4 < 3);
            int n5 = 0;
            do {
                for (int k = 0; k < this.game1.get_no_baddies(2); ++k) {
                    if (this.shot1[n5].com.get_grid_position() == this.bad1[k].com_shot.get_grid_position() && this.shot1[n5].com.alive() && this.bad1[k].com_shot.alive() && this.shot1[n5].com.centre.get_z() < this.bad1[k].com_shot.centre.get_z() && this.craft1.com.centre.get_z() > this.bad1[k].com_shot.centre.get_z()) {
                        this.bang.play();
                        this.game1.increase_score(this.bad1[n5].com_shot.get_baddy_value());
                        this.bad1[k].com_shot.set_alive(false);
                        this.bad1[k].com_shot.set_visible(false);
                        this.shot1[n5].com.set_alive(false);
                        this.shot1[n5].com.set_visible(false);
                    }
                    if ((this.shot1[n5].com.get_grid_position() == this.bad1[k].com.get_grid_position() && this.shot1[n5].com.alive() && this.bad1[k].com.alive() && this.shot1[n5].com.centre.get_z() < this.bad1[k].com.centre.get_z() && !this.bad1[k].com.top_of_web()) || (this.craft1.get_jumping() != 0 && this.bad1[k].com.top_of_web() && this.shot1[n5].com.get_grid_position() == this.bad1[k].com.get_grid_position() && this.shot1[n5].com.alive() && this.bad1[k].com.alive() && this.shot1[n5].com.centre.get_z() < this.bad1[k].com.centre.get_z())) {
                        this.bang.play();
                        this.game1.increase_score(this.bad1[n5].com.get_baddy_value());
                        this.shot1[n5].com.set_alive(false);
                        this.shot1[n5].com.set_visible(false);
                        this.bad1[k].com.set_alive(false);
                        this.bad1[k].com.set_visible(false);
                        this.bad1[k].com.set_on_web(false);
                        this.shot1[n5].move_shot_to(0.0, 0.0, 1000.0);
                        this.bad1[k].com.move_object(0.0, 0.0, 2000.0);
                        this.shot1[n5].com.set_dead_on_this_go(true);
                    }
                }
            } while (++n5 < 3);
        }
        if (!this.shot1[0].com.alive() && !this.shot1[1].com.alive() && !this.shot1[2].com.alive()) {
            this.game1.set_shot(false);
            int n6 = 0;
            do {
                this.shot1[n6].com.set_dead_on_this_go(false);
            } while (++n6 < 3);
        }
    }
    
    public void check_craft() {
        for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
            if (this.star1[i].com.top_of_web() && this.star1[i].com.alive() && this.star1[i].com.get_grid_position() == this.craft1.com.get_grid_position() && this.craft1.get_jumping() == 0) {
                this.game1.set_inputs_allowed(false);
                int n = 0;
                do {
                    this.shot1[n].com.set_visible(false);
                } while (++n < 3);
                for (int j = 0; j < this.game1.get_no_baddies(0); ++j) {
                    this.star1[j].com.set_top_of_web(false);
                    this.star1[j].com.set_on_web(false);
                }
                this.game1.set_killer(i);
                this.game1.set_killer_type(0);
                this.game1.set_game_status(4);
            }
        }
        for (int k = 0; k < this.game1.get_no_baddies(1); ++k) {
            if (this.bad[k].com.top_of_web() && this.bad[k].com.alive() && this.bad[k].com.get_grid_position() == this.craft1.com.get_grid_position() && this.craft1.get_jumping() == 0) {
                this.game1.set_inputs_allowed(false);
                int n2 = 0;
                do {
                    this.shot1[n2].com.set_visible(false);
                } while (++n2 < 3);
                for (int l = 0; l < this.game1.get_no_baddies(1); ++l) {
                    this.bad[l].com.set_top_of_web(false);
                    this.bad[l].com.set_on_web(false);
                }
                this.game1.set_killer(k);
                this.game1.set_killer_type(1);
                this.game1.set_game_status(4);
            }
        }
        for (int n3 = 0; n3 < this.game1.get_no_baddies(2); ++n3) {
            if (this.bad1[n3].com_shot.alive() && this.bad1[n3].com_shot.get_grid_position() == this.craft1.com.get_grid_position() && this.bad1[n3].com_shot.centre.get_z() > this.craft1.com.centre.get_z() && this.bad1[n3].com_shot.centre.get_z() - this.craft1.com.centre.get_z() < 20.0) {
                int n4 = 0;
                do {
                    this.shot1[n4].com.set_visible(false);
                } while (++n4 < 3);
                this.bad1[n3].com_shot.set_visible(false);
                this.game1.set_game_status(7);
                this.game1.set_inputs_allowed(false);
                this.bang.play();
                this.craft1.com.set_alive(false);
            }
            else if (this.bad1[n3].com.top_of_web() && this.bad1[n3].com.alive() && this.bad1[n3].com.get_grid_position() == this.craft1.com.get_grid_position() && this.craft1.get_jumping() == 0) {
                this.game1.set_inputs_allowed(false);
                int n5 = 0;
                do {
                    this.shot1[n5].com.set_visible(false);
                } while (++n5 < 3);
                for (int n6 = 0; n6 < this.game1.get_no_baddies(2); ++n6) {
                    this.bad1[n6].com.set_top_of_web(false);
                    this.bad1[n6].com.set_on_web(false);
                }
                this.game1.set_killer(n3);
                this.game1.set_killer_type(2);
                this.game1.set_game_status(4);
            }
        }
    }
    
    public void craft_shot() {
        this.craft1.com.rotate_z_axis(25.0);
    }
    
    public void move_eye() {
        if (this.eye.get_x() != (int)this.craft1.com.centre.get_x() / 4) {
            if (this.eye.get_x() > this.craft1.com.centre.get_x() / 4.0) {
                this.eye.set_x(this.eye.get_x() - 1.0);
            }
            else if (this.eye.get_x() < this.craft1.com.centre.get_x() / 4.0) {
                this.eye.set_x(this.eye.get_x() + 1.0);
            }
        }
        if (this.eye.get_y() != (int)this.craft1.com.centre.get_y() / 4) {
            if (this.eye.get_y() > this.craft1.com.centre.get_y() / 4.0) {
                this.eye.set_y(this.eye.get_y() - 1.0);
                return;
            }
            if (this.eye.get_y() < this.craft1.com.centre.get_y() / 4.0) {
                this.eye.set_y(this.eye.get_y() + 1.0);
            }
        }
    }
    
    public void begin_level() {
        if (this.eye.get_z() > 0.0) {
            this.level1.com.rotate_y_axis(2.0);
            this.level1.com.rotate_z_axis(2.0);
            this.eye.set_z(this.eye.get_z() - 14.0);
            return;
        }
        if (Temp1000.craft_status == 0) {
            this.level1.move_level_to(0, 0, -400);
            this.craft1.move_craft_to_grid_position();
            this.craft1.com.centre.set_z(this.craft1.com.centre.get_z() + 400.0);
            this.craft1.move_craft_to(this.craft1.com.centre.get_x(), this.craft1.com.centre.get_y(), this.craft1.com.centre.get_z());
            Temp1000.craft_status = 1;
            this.craft1.com.set_visible(true);
            return;
        }
        if (Temp1000.craft_status == 1) {
            if (this.craft1.com.centre.get_z() > 0.0) {
                this.craft1.com.centre.set_z(this.craft1.com.centre.get_z() - 10.0);
                this.craft1.move_craft_to(this.craft1.com.centre.get_x(), this.craft1.com.centre.get_y(), this.craft1.com.centre.get_z());
                this.level1.com.rotate_z_axis(9.0);
                int n = 0;
                do {
                    if (this.level1.com.get_blue_fill(0, n) < this.level1.com.get_base_blue_colour()) {
                        this.level1.com.set_blue_fill(this.level1.com.get_blue_fill(0, n) + 2 * (this.level1.com.get_base_blue_colour() / 100), 0, n);
                        if (this.level1.com.get_blue_fill(0, n) > this.level1.com.get_base_blue_colour()) {
                            this.level1.com.set_blue_fill(this.level1.com.get_base_blue_colour(), 0, n);
                        }
                    }
                    if (this.level1.com.get_green_fill(0, n) < this.level1.com.get_base_green_colour()) {
                        this.level1.com.set_green_fill(this.level1.com.get_green_fill(0, n) + 2 * (this.level1.com.get_base_green_colour() / 100), 0, n);
                        if (this.level1.com.get_green_fill(0, n) > this.level1.com.get_base_green_colour()) {
                            this.level1.com.set_green_fill(this.level1.com.get_base_green_colour(), 0, n);
                        }
                    }
                    if (this.level1.com.get_red_fill(0, n) < this.level1.com.get_base_red_colour()) {
                        this.level1.com.set_red_fill(this.level1.com.get_red_fill(0, n) + 2 * (this.level1.com.get_base_red_colour() / 100), 0, n);
                        if (this.level1.com.get_red_fill(0, n) <= this.level1.com.get_base_red_colour()) {
                            continue;
                        }
                        this.level1.com.set_red_fill(this.level1.com.get_base_red_colour(), 0, n);
                    }
                } while (++n < 12);
                return;
            }
            this.game1.set_game_status(1);
            this.game1.set_inputs_allowed(true);
            Temp1000.craft_status = 0;
        }
    }
    
    public void reset_for_new_level() {
        this.craft1.com.set_visible(false);
        this.craft1.com.set_grid_position(1);
        this.craft1.move_craft_to(0.0, 0.0, 0.0);
        this.craft1.move_craft_to_grid_position();
        this.craft1.com.set_red_colour(255, 0);
        this.craft1.com.set_green_colour(255, 0);
        this.game1.set_inputs_allowed(false);
        this.level1.move_level_to(0, 0, -400);
        this.level1.move_level_to(0, 0, -400);
        this.level1.com.rotate_y_axis(90.0);
        this.level1.com.rotate_x_axis(90.0);
        this.level1.com.rotate_z_axis(90.0);
        this.game1.com1.set_red_colour(255, 0);
        this.game1.com1.set_green_colour(255, 0);
        this.game1.com1.set_blue_colour(255, 0);
        this.eye.set_Array3D(0.0, 0.0, 900.0);
        this.set_baddies_for_level();
        this.set_colours_for_level();
        this.game1.set_mega_blast_allowed(true);
    }
    
    public void set_colours_for_level() {
        switch (this.game1.get_level()) {
            case 1: {
                this.level1.com.set_red_colour(0, 0);
                this.level1.com.set_green_colour(0, 0);
                this.level1.com.set_blue_colour(0, 0);
                this.level1.com.set_red_colour(0, 1);
                this.level1.com.set_green_colour(0, 1);
                this.level1.com.set_blue_colour(255, 1);
                this.level1.com.set_base_blue_colour(200);
                this.level1.com.set_base_green_colour(0);
                this.level1.com.set_base_red_colour(0);
                int n = 0;
                do {
                    this.level1.com.set_red_fill(0, 0, n);
                    this.level1.com.set_green_fill(0, 0, n);
                    this.level1.com.set_blue_fill(0, 0, n);
                    this.level1.com.set_red_fill(this.level1.com.get_base_red_colour(), 1, n);
                    this.level1.com.set_green_fill(this.level1.com.get_base_green_colour(), 1, n);
                    this.level1.com.set_blue_fill(this.level1.com.get_base_blue_colour(), 1, n);
                } while (++n < 12);
            }
            case 2: {
                this.level1.com.set_red_colour(0, 0);
                this.level1.com.set_green_colour(0, 0);
                this.level1.com.set_blue_colour(0, 0);
                this.level1.com.set_red_colour(255, 1);
                this.level1.com.set_green_colour(255, 1);
                this.level1.com.set_blue_colour(255, 1);
                this.level1.com.set_base_blue_colour(100);
                this.level1.com.set_base_green_colour(0);
                this.level1.com.set_base_red_colour(100);
                int n2 = 0;
                do {
                    this.level1.com.set_red_fill(0, 0, n2);
                    this.level1.com.set_green_fill(0, 0, n2);
                    this.level1.com.set_blue_fill(0, 0, n2);
                    this.level1.com.set_red_fill(this.level1.com.get_base_red_colour(), 1, n2);
                    this.level1.com.set_green_fill(this.level1.com.get_base_green_colour(), 1, n2);
                    this.level1.com.set_blue_fill(this.level1.com.get_base_blue_colour(), 1, n2);
                } while (++n2 < 12);
            }
            case 3: {
                this.level1.com.set_red_colour(0, 0);
                this.level1.com.set_green_colour(0, 0);
                this.level1.com.set_blue_colour(0, 0);
                this.level1.com.set_red_colour(0, 1);
                this.level1.com.set_green_colour(200, 1);
                this.level1.com.set_blue_colour(0, 1);
                this.level1.com.set_base_blue_colour(100);
                this.level1.com.set_base_green_colour(150);
                this.level1.com.set_base_red_colour(100);
                int n3 = 0;
                do {
                    this.level1.com.set_red_fill(0, 0, n3);
                    this.level1.com.set_green_fill(0, 0, n3);
                    this.level1.com.set_blue_fill(0, 0, n3);
                    this.level1.com.set_red_fill(this.level1.com.get_base_red_colour(), 1, n3);
                    this.level1.com.set_green_fill(this.level1.com.get_base_green_colour(), 1, n3);
                    this.level1.com.set_blue_fill(this.level1.com.get_base_blue_colour(), 1, n3);
                } while (++n3 < 12);
            }
            case 4: {
                this.level1.com.set_red_colour(0, 0);
                this.level1.com.set_green_colour(0, 0);
                this.level1.com.set_blue_colour(0, 0);
                this.level1.com.set_red_colour(255, 1);
                this.level1.com.set_green_colour(255, 1);
                this.level1.com.set_blue_colour(255, 1);
                this.level1.com.set_base_blue_colour(197);
                this.level1.com.set_base_green_colour(97);
                this.level1.com.set_base_red_colour(43);
                int n4 = 0;
                do {
                    this.level1.com.set_red_fill(0, 0, n4);
                    this.level1.com.set_green_fill(0, 0, n4);
                    this.level1.com.set_blue_fill(0, 0, n4);
                    this.level1.com.set_red_fill(this.level1.com.get_base_red_colour(), 1, n4);
                    this.level1.com.set_green_fill(this.level1.com.get_base_green_colour(), 1, n4);
                    this.level1.com.set_blue_fill(this.level1.com.get_base_blue_colour(), 1, n4);
                } while (++n4 < 12);
            }
            default: {}
        }
    }
    
    public void restart_level() {
        this.game1.set_game_over_status(1);
        this.game1.set_game_status(0);
        this.game1.reset_score();
        this.game1.set_level(1);
        this.game1.set_number_of_lives(3);
        this.craft1.com.set_visible(false);
        this.craft1.com.set_grid_position(1);
        this.craft1.move_craft_to(0.0, 0.0, 0.0);
        this.craft1.move_craft_to_grid_position();
        this.game1.set_inputs_allowed(false);
        this.game1.com1.set_red_colour(255, 0);
        this.game1.com1.set_green_colour(255, 0);
        this.game1.com1.set_blue_colour(255, 0);
        this.level1.move_level_to(0, 0, -400);
        this.star_field.set_star_positions_for_level(1);
        int n = 0;
        do {
            this.shot1[n].com.set_visible(false);
        } while (++n < 3);
        this.set_baddies_for_level();
        this.set_colours_for_level();
        this.game1.set_mega_blast_allowed(true);
    }
    
    public void draw_special() {
        this.gBuffer.setColor(Color.yellow);
        this.level1.com.tris[this.get_tri_from_grid_pos()].draw_triangle(this.gBuffer, this.d.width, this.d.height, this.eye, true, false, false);
        this.level1.com.tris[this.get_tri_from_grid_pos() + 1].draw_triangle(this.gBuffer, this.d.width, this.d.height, this.eye, false, true, false);
        if (this.game1.mega_blast_allowed()) {
            final Array3D array3D = new Array3D(0.0, 0.0, 0.0);
            this.mega_icon.com.draw_object(this.gBuffer, this.d.width, this.d.height, array3D);
            this.mega1_icon.com.draw_object(this.gBuffer, this.d.width, this.d.height, array3D);
            this.mega_icon.com.rotate_z_axis(2.0);
            this.mega1_icon.com.rotate_z_axis(-3.0);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.drawStuff();
        graphics.drawImage(this.Buffer, 0, 0, this);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public Temp1000() {
        this.frames = 0;
        this.game1 = new Game();
        this.textfield = new TextField();
        this.okButton = new Button("OK");
        this.star_field = new StarFld(80);
        this.mega_icon = new MBlastG(220, -220, 0, 20);
        this.mega1_icon = new MBlastG(220, -220, 0, 17);
    }
    
    public void kill_craft() {
        this.craft1.com.move_object(0.0, 0.0, -20.0);
        this.craft1.com.rotate_z_axis(15.0);
        switch (this.game1.get_killer_type()) {
            case 0: {
                this.star1[this.game1.get_killer()].com.move_object(0.0, 0.0, -20.0);
                this.star1[this.game1.get_killer()].com.rotate_z_axis(-15.0);
                break;
            }
            case 1: {
                this.bad[this.game1.get_killer()].com.move_object(0.0, 0.0, -20.0);
                this.bad[this.game1.get_killer()].com.rotate_z_axis(-15.0);
                break;
            }
            case 2: {
                this.bad1[this.game1.get_killer()].com.move_object(0.0, 0.0, -20.0);
                this.bad1[this.game1.get_killer()].com.rotate_z_axis(-15.0);
                break;
            }
        }
        if (this.craft1.com.centre.get_z() < -this.level1.get_length_of_level() - 50) {
            this.new_life();
        }
    }
    
    public void new_life() {
        this.game1.decrease_number_of_lives();
        if (this.game1.get_number_of_lives() == 0) {
            this.game1.set_game_status(5);
            return;
        }
        this.game1.com1.set_red_colour(255, 0);
        this.game1.com1.set_green_colour(255, 0);
        this.game1.com1.set_blue_colour(255, 0);
        this.game1.set_game_status(2);
        this.craft1.com.set_grid_position(1);
        this.craft1.com.set_visible(false);
        this.craft1.move_craft_to(0.0, 0.0, 0.0);
        this.eye.set_Array3D(0.0, 0.0, 900.0);
        this.level1.com.rotate_y_axis(90.0);
        this.level1.com.rotate_x_axis(90.0);
        this.level1.com.rotate_z_axis(90.0);
        this.set_baddies_for_level();
        this.set_colours_for_level();
        this.game1.set_mega_blast_allowed(true);
    }
    
    public int get_number_of_baddies_safe(final int n) {
        int n2;
        if (this.game1.get_no_baddies(n) > 0) {
            n2 = this.game1.get_no_baddies(n) - 1;
        }
        else {
            n2 = 0;
        }
        return n2;
    }
    
    public void check_baddies() {
        for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
            if (this.star1[i].com.alive() && !this.star1[i].com.top_of_web()) {
                this.star1[i].com.move_object(0.0, 0.0, this.star1[i].com.velocity.get_z());
                this.star1[i].com.rotate_z_axis(5.0);
            }
            if (this.star1[i].com.centre.get_z() > -this.level1.get_length_of_level() - 260 && this.star1[i].com.alive() && !this.star1[i].com.get_visible()) {
                this.star1[i].com.set_visible(true);
            }
            if (this.star1[i].com.get_visible() && this.star1[i].com.get_red_colour(0) < this.star1[i].com.get_red_colour(1)) {
                this.star1[i].com.set_red_colour(this.star1[i].com.get_red_colour(0) + 260 / this.star1[i].com.get_red_colour(1) * (int)this.game1.get_velocity_of_baddies(0), 0);
                if (this.star1[i].com.get_red_colour(0) > this.star1[i].com.get_red_colour(1)) {
                    this.star1[i].com.set_red_colour(this.star1[i].com.get_red_colour(1), 0);
                }
            }
            if (this.star1[i].com.get_visible() && this.star1[i].com.get_blue_colour(0) < this.star1[i].com.get_blue_colour(1)) {
                this.star1[i].com.set_blue_colour(this.star1[i].com.get_blue_colour(0) + 260 / this.star1[i].com.get_blue_colour(1) * (int)this.game1.get_velocity_of_baddies(0), 0);
                if (this.star1[i].com.get_blue_colour(0) > this.star1[i].com.get_blue_colour(1)) {
                    this.star1[i].com.set_blue_colour(this.star1[i].com.get_blue_colour(1), 0);
                }
            }
            if (this.star1[i].com.get_visible() && this.star1[i].com.get_green_colour(0) < this.star1[i].com.get_green_colour(1)) {
                this.star1[i].com.set_green_colour(this.star1[i].com.get_green_colour(0) + 260 / this.star1[i].com.get_green_colour(1) * (int)this.game1.get_velocity_of_baddies(0), 0);
                if (this.star1[i].com.get_green_colour(0) > this.star1[i].com.get_green_colour(1)) {
                    this.star1[i].com.set_green_colour(this.star1[i].com.get_green_colour(1), 0);
                }
            }
            if (this.star1[i].com.centre.get_z() > -this.level1.get_length_of_level() && !this.star1[i].com.on_web() && this.star1[i].com.alive()) {
                this.star1[i].com.set_on_web(true);
            }
            if (this.star1[i].com.centre.get_z() > 0.0 && !this.star1[i].com.top_of_web() && this.star1[i].com.alive()) {
                this.star1[i].com.set_top_of_web(true);
                this.star1[i].move_star_to(this.star1[i].com.centre.get_x(), this.star1[i].com.centre.get_y(), 0.0);
                final int n = this.craft1.com.get_grid_position() - this.star1[i].com.get_grid_position();
                if ((n < 0 || n > 5) && n > -5) {
                    this.star1[i].com.set_clockwise_direction(true);
                }
                else {
                    this.star1[i].com.set_clockwise_direction(false);
                }
            }
            else if (this.star1[i].com.top_of_web()) {
                this.star1[i].com.increase_counter(1);
                if (this.star1[i].com.get_counter() > 20) {
                    this.star1[i].com.reset_counter();
                    if (this.star1[i].com.clockwise_direction()) {
                        this.star1[i].com.decrease_grid_pos();
                    }
                    else {
                        this.star1[i].com.increase_grid_pos();
                    }
                    this.star1[i].move_star_to_grid_position();
                }
            }
        }
        for (int j = 0; j < this.game1.get_no_baddies(1); ++j) {
            if (this.bad[j].com.alive() && !this.bad[j].com.top_of_web()) {
                this.bad[j].com.move_object(0.0, 0.0, this.bad[j].com.velocity.get_z());
            }
            if (this.bad[j].com.centre.get_z() > -this.level1.get_length_of_level() - 260 && this.bad[j].com.alive() && !this.bad[j].com.get_visible()) {
                this.bad[j].com.set_visible(true);
            }
            if (this.bad[j].com.get_visible() && this.bad[j].com.get_red_colour(0) < this.bad[j].com.get_red_colour(1)) {
                this.bad[j].com.set_red_colour(this.bad[j].com.get_red_colour(0) + 260 / this.bad[j].com.get_red_colour(1) * (int)this.game1.get_velocity_of_baddies(1), 0);
                if (this.bad[j].com.get_red_colour(0) > this.bad[j].com.get_red_colour(1)) {
                    this.bad[j].com.set_red_colour(this.bad[j].com.get_red_colour(1), 0);
                }
            }
            if (this.bad[j].com.get_visible() && this.bad[j].com.get_blue_colour(0) < this.bad[j].com.get_blue_colour(1)) {
                this.bad[j].com.set_blue_colour(this.bad[j].com.get_blue_colour(0) + 260 / this.bad[j].com.get_blue_colour(1) * (int)this.game1.get_velocity_of_baddies(1), 0);
                if (this.bad[j].com.get_blue_colour(0) > this.bad[j].com.get_blue_colour(1)) {
                    this.bad[j].com.set_blue_colour(this.bad[j].com.get_blue_colour(1), 0);
                }
            }
            if (this.bad[j].com.get_visible() && this.bad[j].com.get_green_colour(0) < this.bad[j].com.get_green_colour(1)) {
                this.bad[j].com.set_green_colour(this.bad[j].com.get_green_colour(0) + 260 / this.bad[j].com.get_green_colour(1) * (int)this.game1.get_velocity_of_baddies(1), 0);
                if (this.bad[j].com.get_green_colour(0) > this.bad[j].com.get_green_colour(1)) {
                    this.bad[j].com.set_green_colour(this.bad[j].com.get_green_colour(1), 0);
                }
            }
            if (this.bad[j].com.centre.get_z() > -this.level1.get_length_of_level() && !this.bad[j].com.on_web() && this.bad[j].com.alive()) {
                this.bad[j].com.set_on_web(true);
            }
            if (this.bad[j].com.centre.get_z() > 0.0 && !this.bad[j].com.top_of_web() && this.bad[j].com.alive()) {
                this.bad[j].com.set_top_of_web(true);
                this.bad[j].move_baddy2_to(this.bad[j].com.centre.get_x(), this.bad[j].com.centre.get_y(), 0.0);
                final int n2 = this.craft1.com.get_grid_position() - this.bad[j].com.get_grid_position();
                if ((n2 < 0 || n2 > 5) && n2 > -5) {
                    this.bad[j].com.set_clockwise_direction(true);
                }
                else {
                    this.bad[j].com.set_clockwise_direction(false);
                }
            }
            else if (this.bad[j].com.top_of_web()) {
                this.bad[j].com.increase_counter(1);
                if (this.bad[j].com.get_counter() > 10) {
                    this.bad[j].com.reset_counter();
                    if (this.bad[j].com.clockwise_direction()) {
                        this.bad[j].com.decrease_grid_pos();
                    }
                    else {
                        this.bad[j].com.increase_grid_pos();
                    }
                    this.bad[j].move_baddy2_to_grid_position();
                }
            }
        }
        for (int k = 0; k < this.game1.get_no_baddies(2); ++k) {
            if (this.bad1[k].com.alive() && !this.bad1[k].com.top_of_web()) {
                this.bad1[k].com.move_object(0.0, 0.0, this.bad1[k].com.velocity.get_z());
                if (this.bad1[k].com.on_web() && !this.bad1[k].com.top_of_web() && !this.bad1[k].com_shot.get_shot()) {
                    this.bad1[k].com_shot.increase_counter(10);
                }
                if (this.bad1[k].com_shot.get_counter() > 100 && !this.bad1[k].com_shot.get_shot()) {
                    this.bad1[k].com_shot.reset_counter();
                    this.bad1[k].com_shot.set_shot(true);
                    this.bad1[k].move_baddy3_shot_to(0.0, 0.0, this.bad1[k].com.centre.get_z());
                    this.bad1[k].com_shot.set_grid_position(this.bad1[k].com.get_grid_position());
                    this.bad1[k].move_baddy3_shot_to_grid_position();
                    this.bad1[k].com_shot.set_visible(true);
                    this.bad1[k].com_shot.set_alive(true);
                    this.bad1[k].com_shot.velocity.set_Array3D(0.0, 0.0, this.bad1[k].com.velocity.get_z() + 10.0);
                }
            }
            if (this.bad1[k].com_shot.get_shot()) {
                this.bad1[k].com_shot.move_object(0.0, 0.0, this.bad1[k].com_shot.velocity.get_z());
            }
            if (this.bad1[k].com_shot.centre.get_z() > 400.0) {
                this.bad1[k].com_shot.set_shot(false);
                this.bad1[k].com_shot.set_visible(false);
                this.bad1[k].com_shot.set_alive(false);
            }
            if (this.bad1[k].com.centre.get_z() > -this.level1.get_length_of_level() - 260 && this.bad1[k].com.alive() && !this.bad1[k].com.get_visible()) {
                this.bad1[k].com.set_visible(true);
            }
            if (this.bad1[k].com.get_visible() && this.bad1[k].com.get_red_colour(0) < this.bad1[k].com.get_red_colour(1)) {
                this.bad1[k].com.set_red_colour(this.bad1[k].com.get_red_colour(0) + 260 / this.bad1[k].com.get_red_colour(1) * (int)this.game1.get_velocity_of_baddies(2), 0);
                if (this.bad1[k].com.get_red_colour(0) > this.bad1[k].com.get_red_colour(1)) {
                    this.bad1[k].com.set_red_colour(this.bad1[k].com.get_red_colour(1), 0);
                }
            }
            if (this.bad1[k].com.get_visible() && this.bad1[k].com.get_blue_colour(0) < this.bad1[k].com.get_blue_colour(1)) {
                this.bad1[k].com.set_blue_colour(this.bad1[k].com.get_blue_colour(0) + 260 / this.bad1[k].com.get_blue_colour(1) * (int)this.game1.get_velocity_of_baddies(2), 0);
                if (this.bad1[k].com.get_blue_colour(0) > this.bad1[k].com.get_blue_colour(1)) {
                    this.bad1[k].com.set_blue_colour(this.bad1[k].com.get_blue_colour(1), 0);
                }
            }
            if (this.bad1[k].com.get_visible() && this.bad1[k].com.get_green_colour(0) < this.bad1[k].com.get_green_colour(1)) {
                this.bad1[k].com.set_green_colour(this.bad1[k].com.get_green_colour(0) + 260 / this.bad1[k].com.get_green_colour(1) * (int)this.game1.get_velocity_of_baddies(2), 0);
                if (this.bad1[k].com.get_green_colour(0) > this.bad1[k].com.get_green_colour(1)) {
                    this.bad1[k].com.set_green_colour(this.bad1[k].com.get_green_colour(1), 0);
                }
            }
            if (this.bad1[k].com.centre.get_z() > -this.level1.get_length_of_level() && !this.bad1[k].com.on_web() && this.bad1[k].com.alive()) {
                this.bad1[k].com.set_on_web(true);
            }
            if (this.bad1[k].com.centre.get_z() > 0.0 && !this.bad1[k].com.top_of_web() && this.bad1[k].com.alive()) {
                this.bad1[k].com.set_top_of_web(true);
                this.bad1[k].move_baddy3_to(this.bad1[k].com.centre.get_x(), this.bad1[k].com.centre.get_y(), 0.0);
                final int n3 = this.craft1.com.get_grid_position() - this.bad1[k].com.get_grid_position();
                if ((n3 < 0 || n3 > 5) && n3 > -5) {
                    this.bad1[k].com.set_clockwise_direction(true);
                }
                else {
                    this.bad1[k].com.set_clockwise_direction(false);
                }
            }
            else if (this.bad1[k].com.top_of_web()) {
                this.bad1[k].com.increase_counter(1);
                if (this.bad1[k].com.get_counter() > 15) {
                    this.bad1[k].com.reset_counter();
                    if (this.bad1[k].com.clockwise_direction()) {
                        this.bad1[k].com.decrease_grid_pos();
                    }
                    else {
                        this.bad1[k].com.increase_grid_pos();
                    }
                    this.bad1[k].move_baddy3_to_grid_position();
                }
            }
        }
    }
    
    public void check_for_bonus() {
        if (this.game1.get_score() >= this.game1.get_bonus_level()) {
            this.game1.increase_number_of_lives();
            this.game1.set_bonus_level(this.game1.get_bonus_level() + 100000);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 37) {
            this.craft1.com.set_going_left(false);
            this.game1.set_left_button_down(false);
        }
        if (keyEvent.getKeyCode() == 39) {
            this.craft1.com.set_going_right(false);
            this.game1.set_right_button_down(false);
        }
        if (keyEvent.getKeyCode() == 38) {
            this.game1.set_up_button_down(false);
        }
        if (keyEvent.getKeyCode() == 40) {
            this.game1.set_down_button_down(false);
        }
    }
    
    public void set_baddies_for_level() {
        switch (this.game1.get_level()) {
            case 1: {
                this.game1.set_no_baddies(0, 15);
                this.game1.set_no_baddies(1, 10);
                this.game1.set_no_baddies(2, 0);
                this.bad1[0].com.set_alive(false);
                this.game1.set_velocity_of_baddies(0, 2.0);
                this.game1.set_velocity_of_baddies(1, 3.0);
                this.game1.set_velocity_of_baddies(2, 2.0);
                this.game1.set_baddies_start_pos(0, 1200);
                this.game1.set_baddies_start_pos(1, 2000);
                this.game1.set_baddies_start_pos(2, 2000);
                this.game1.set_baddies_spacing(0, 200);
                this.game1.set_baddies_spacing(1, 400);
                this.game1.set_baddies_spacing(2, 200);
                this.game1.set_no_jumps(2);
                for (int i = 0; i < this.game1.get_no_baddies(0); ++i) {
                    this.star1[i].com.set_blue_colour(0, 0);
                    this.star1[i].com.set_red_colour(0, 0);
                    this.star1[i].com.set_green_colour(0, 0);
                    this.star1[i].com.set_blue_colour(0, 1);
                    this.star1[i].com.set_red_colour(255, 1);
                    this.star1[i].com.set_green_colour(0, 1);
                }
                for (int j = 0; j < this.game1.get_no_baddies(1); ++j) {
                    this.bad[j].com.set_blue_colour(0, 0);
                    this.bad[j].com.set_red_colour(0, 0);
                    this.bad[j].com.set_green_colour(0, 0);
                    this.bad[j].com.set_blue_colour(0, 1);
                    this.bad[j].com.set_red_colour(0, 1);
                    this.bad[j].com.set_green_colour(255, 1);
                }
                for (int k = 0; k < this.game1.get_no_baddies(2); ++k) {
                    this.bad1[k].com.set_blue_colour(0, 0);
                    this.bad1[k].com.set_red_colour(0, 0);
                    this.bad1[k].com.set_green_colour(0, 0);
                    this.bad1[k].com.set_blue_colour(255, 1);
                    this.bad1[k].com.set_red_colour(0, 1);
                    this.bad1[k].com.set_green_colour(0, 1);
                }
                break;
            }
            case 2: {
                this.game1.set_no_baddies(0, 30);
                this.game1.set_no_baddies(1, 10);
                this.game1.set_no_baddies(2, 5);
                this.game1.set_velocity_of_baddies(0, 2.0);
                this.game1.set_velocity_of_baddies(1, 3.0);
                this.game1.set_velocity_of_baddies(2, 2.0);
                this.game1.set_baddies_start_pos(0, 1200);
                this.game1.set_baddies_start_pos(1, 2000);
                this.game1.set_baddies_start_pos(2, 3000);
                this.game1.set_baddies_spacing(0, 100);
                this.game1.set_baddies_spacing(1, 300);
                this.game1.set_baddies_spacing(2, 250);
                this.game1.set_no_jumps(3);
                for (int l = 0; l < this.game1.get_no_baddies(0); ++l) {
                    this.star1[l].com.set_blue_colour(0, 0);
                    this.star1[l].com.set_red_colour(0, 0);
                    this.star1[l].com.set_green_colour(0, 0);
                    this.star1[l].com.set_blue_colour(249, 1);
                    this.star1[l].com.set_red_colour(237, 1);
                    this.star1[l].com.set_green_colour(0, 1);
                }
                for (int n = 0; n < this.game1.get_no_baddies(1); ++n) {
                    this.bad[n].com.set_blue_colour(0, 0);
                    this.bad[n].com.set_red_colour(0, 0);
                    this.bad[n].com.set_green_colour(0, 0);
                    this.bad[n].com.set_blue_colour(0, 1);
                    this.bad[n].com.set_red_colour(0, 1);
                    this.bad[n].com.set_green_colour(255, 1);
                }
                for (int n2 = 0; n2 < this.game1.get_no_baddies(2); ++n2) {
                    this.bad1[n2].com.set_blue_colour(0, 0);
                    this.bad1[n2].com.set_red_colour(0, 0);
                    this.bad1[n2].com.set_green_colour(0, 0);
                    this.bad1[n2].com.set_blue_colour(255, 1);
                    this.bad1[n2].com.set_red_colour(100, 1);
                    this.bad1[n2].com.set_green_colour(150, 1);
                }
                break;
            }
            case 3: {
                this.game1.set_no_baddies(0, 10);
                this.game1.set_no_baddies(1, 35);
                this.game1.set_no_baddies(2, 14);
                this.game1.set_velocity_of_baddies(0, 4.0);
                this.game1.set_velocity_of_baddies(1, 4.0);
                this.game1.set_velocity_of_baddies(2, 2.0);
                this.game1.set_baddies_start_pos(0, 1200);
                this.game1.set_baddies_start_pos(1, 1200);
                this.game1.set_baddies_start_pos(2, 1200);
                this.game1.set_baddies_spacing(0, 1000);
                this.game1.set_baddies_spacing(1, 250);
                this.game1.set_baddies_spacing(2, 300);
                this.game1.set_no_jumps(4);
                for (int n3 = 0; n3 < this.game1.get_no_baddies(0); ++n3) {
                    this.star1[n3].com.set_blue_colour(0, 0);
                    this.star1[n3].com.set_red_colour(0, 0);
                    this.star1[n3].com.set_green_colour(0, 0);
                    this.star1[n3].com.set_blue_colour(249, 1);
                    this.star1[n3].com.set_red_colour(237, 1);
                    this.star1[n3].com.set_green_colour(0, 1);
                }
                for (int n4 = 0; n4 < this.game1.get_no_baddies(1); ++n4) {
                    this.bad[n4].com.set_blue_colour(0, 0);
                    this.bad[n4].com.set_red_colour(0, 0);
                    this.bad[n4].com.set_green_colour(0, 0);
                    this.bad[n4].com.set_blue_colour(150, 1);
                    this.bad[n4].com.set_red_colour(0, 1);
                    this.bad[n4].com.set_green_colour(255, 1);
                }
                for (int n5 = 0; n5 < this.game1.get_no_baddies(2); ++n5) {
                    this.bad1[n5].com.set_blue_colour(0, 0);
                    this.bad1[n5].com.set_red_colour(0, 0);
                    this.bad1[n5].com.set_green_colour(0, 0);
                    this.bad1[n5].com.set_blue_colour(255, 1);
                    this.bad1[n5].com.set_red_colour(100, 1);
                    this.bad1[n5].com.set_green_colour(150, 1);
                }
                break;
            }
            case 4: {
                this.game1.set_no_baddies(0, 30);
                this.game1.set_no_baddies(1, 40);
                this.game1.set_no_baddies(2, 13);
                this.game1.set_velocity_of_baddies(0, 3.0);
                this.game1.set_velocity_of_baddies(1, 4.0);
                this.game1.set_velocity_of_baddies(2, 2.0);
                this.game1.set_baddies_start_pos(0, 1200);
                this.game1.set_baddies_start_pos(1, 1000);
                this.game1.set_baddies_start_pos(2, 1200);
                this.game1.set_baddies_spacing(0, 175);
                this.game1.set_baddies_spacing(1, 175);
                this.game1.set_baddies_spacing(2, 300);
                this.game1.set_no_jumps(5);
                for (int n6 = 0; n6 < this.game1.get_no_baddies(0); ++n6) {
                    this.star1[n6].com.set_blue_colour(0, 0);
                    this.star1[n6].com.set_red_colour(0, 0);
                    this.star1[n6].com.set_green_colour(0, 0);
                    this.star1[n6].com.set_blue_colour(133, 1);
                    this.star1[n6].com.set_red_colour(226, 1);
                    this.star1[n6].com.set_green_colour(126, 1);
                }
                for (int n7 = 0; n7 < this.game1.get_no_baddies(1); ++n7) {
                    this.bad[n7].com.set_blue_colour(0, 0);
                    this.bad[n7].com.set_red_colour(0, 0);
                    this.bad[n7].com.set_green_colour(0, 0);
                    this.bad[n7].com.set_blue_colour(0, 1);
                    this.bad[n7].com.set_red_colour(0, 1);
                    this.bad[n7].com.set_green_colour(255, 1);
                }
                for (int n8 = 0; n8 < this.game1.get_no_baddies(2); ++n8) {
                    this.bad1[n8].com.set_blue_colour(0, 0);
                    this.bad1[n8].com.set_red_colour(0, 0);
                    this.bad1[n8].com.set_green_colour(0, 0);
                    this.bad1[n8].com.set_blue_colour(255, 1);
                    this.bad1[n8].com.set_red_colour(188, 1);
                    this.bad1[n8].com.set_green_colour(209, 1);
                }
                break;
            }
            default: {
                this.game1.set_game_status(5);
                this.game1.set_game_over_status(3);
                break;
            }
        }
        for (int n9 = 0; n9 < this.game1.get_no_baddies(0); ++n9) {
            final int n10 = (int)Math.round(Math.random() * 11.0);
            this.star1[n9].move_star_to((int)(Math.sin(0.017453292519943295 * (n10 * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (n10 * 30 + 15)) * 160.0), -this.game1.get_baddies_start_pos(0) - n9 * this.game1.get_baddies_spacing(0));
            this.star1[n9].clear_unwanted_lines();
            this.star1[n9].com.velocity.set_Array3D(0.0, 0.0, this.game1.get_velocity_of_baddies(0));
            this.star1[n9].com.set_grid_position(n10);
            this.star1[n9].com.set_alive(true);
            this.star1[n9].com.set_visible(false);
            this.star1[n9].com.set_top_of_web(false);
            this.star1[n9].com.set_on_web(false);
            this.star1[n9].com.set_red_colour(0, 0);
        }
        for (int n11 = 0; n11 < this.game1.get_no_baddies(1); ++n11) {
            final int n12 = (int)Math.round(Math.random() * 11.0);
            this.bad[n11].move_baddy2_to((int)(Math.sin(0.017453292519943295 * (n12 * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (n12 * 30 + 15)) * 160.0), -this.game1.get_baddies_start_pos(1) - n11 * this.game1.get_baddies_spacing(1));
            this.bad[n11].clear_unwanted_lines();
            this.bad[n11].com.velocity.set_Array3D(0.0, 0.0, this.game1.get_velocity_of_baddies(1));
            this.bad[n11].com.set_grid_position(n12);
            this.bad[n11].move_baddy2_to_grid_position();
            this.bad[n11].com.set_alive(true);
            this.bad[n11].com.set_visible(false);
            this.bad[n11].com.set_top_of_web(false);
            this.bad[n11].com.set_on_web(false);
            this.bad[n11].com.set_green_colour(0, 0);
        }
        for (int n13 = 0; n13 < this.game1.get_no_baddies(2); ++n13) {
            final int n14 = (int)Math.round(Math.random() * 11.0);
            this.bad1[n13].move_baddy3_to((int)(Math.sin(0.017453292519943295 * (n14 * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (n14 * 30 + 15)) * 160.0), -this.game1.get_baddies_start_pos(2) - n13 * this.game1.get_baddies_spacing(2));
            this.bad1[n13].clear_unwanted_lines();
            this.bad1[n13].com.velocity.set_Array3D(0.0, 0.0, this.game1.get_velocity_of_baddies(2));
            this.bad1[n13].com.set_grid_position(n14);
            this.bad1[n13].move_baddy3_to_grid_position();
            this.bad1[n13].com.set_alive(true);
            this.bad1[n13].com.set_visible(false);
            this.bad1[n13].com.set_top_of_web(false);
            this.bad1[n13].com.set_on_web(false);
            this.bad1[n13].com.set_green_colour(0, 0);
            this.bad1[n13].com_shot.set_shot(false);
            this.bad1[n13].com_shot.set_alive(false);
            this.bad1[n13].com_shot.set_visible(false);
            this.bad1[n13].com_shot.set_top_of_web(false);
            this.bad1[n13].com_shot.set_on_web(false);
        }
        this.game1.set_shot(false);
        int n15 = 0;
        do {
            this.shot1[n15].com.set_alive(false);
            this.shot1[n15].move_shot_to(0.0, 0.0, 1000.0);
        } while (++n15 < 3);
    }
    
    public void draw_stars() {
        for (int i = 0; i < this.star_field.get_number_of_stars(); ++i) {
            int n = 0;
            do {
                this.star_field.elements[i].com[n].centre.set_z(this.star_field.elements[i].com[n].centre.get_z() + this.star_field.elements[i].com[n].velocity.get_z());
                if (this.star_field.elements[i].com[n].centre.get_z() > 400.0) {
                    this.star_field.elements[i].com[n].centre.set_z(0.0);
                }
            } while (++n < 3);
        }
        this.star_field.draw_star_field(this.gBuffer, this.d.width, this.d.height, this.eye);
    }
    
    static {
        Temp1000.craft_status = 0;
    }
    
    public void move_craft() {
        if (this.craft1.com.get_going_left()) {
            if (this.craft1.com.get_first()) {
                if (this.craft1.com.get_counter() > 15) {
                    this.craft1.com.decrease_grid_pos();
                    this.craft1.move_craft_to_grid_position();
                    this.craft1.com.reset_counter();
                    this.craft1.com.set_first(false);
                }
                else {
                    this.craft1.com.increase_counter(1);
                }
            }
            else if (this.craft1.com.get_counter() > 2) {
                this.craft1.com.decrease_grid_pos();
                this.craft1.move_craft_to_grid_position();
                this.craft1.com.reset_counter();
            }
            else {
                this.craft1.com.increase_counter(1);
            }
        }
        if (this.craft1.com.get_going_right()) {
            if (this.craft1.com.get_first()) {
                if (this.craft1.com.get_counter() > 15) {
                    this.craft1.com.increase_grid_pos();
                    this.craft1.move_craft_to_grid_position();
                    this.craft1.com.reset_counter();
                    this.craft1.com.set_first(false);
                    return;
                }
                this.craft1.com.increase_counter(1);
            }
            else {
                if (this.craft1.com.get_counter() > 2) {
                    this.craft1.com.increase_grid_pos();
                    this.craft1.move_craft_to_grid_position();
                    this.craft1.com.reset_counter();
                    return;
                }
                this.craft1.com.increase_counter(1);
            }
        }
    }
    
    public void set_fill_colour(final int n) {
        int abs = Math.abs(n - this.get_poly_from_grid_pos());
        if (abs > 6) {
            if (n > 5) {
                abs = 12 - n + this.get_poly_from_grid_pos();
            }
            else {
                abs = 12 - this.get_poly_from_grid_pos() + n;
            }
        }
        this.level1.com.set_blue_fill(this.level1.com.get_base_blue_colour() - this.level1.com.get_base_blue_colour() / 10 * abs, 1, n);
        this.level1.com.set_red_fill(this.level1.com.get_base_red_colour() - this.level1.com.get_base_red_colour() / 10 * abs, 1, n);
        this.level1.com.set_green_fill(this.level1.com.get_base_green_colour() - this.level1.com.get_base_green_colour() / 10 * abs, 1, n);
        if (this.level1.com.get_red_fill(0, n) > this.level1.com.get_red_fill(1, n)) {
            this.level1.com.set_red_fill(this.level1.com.get_red_fill(0, n) - 1, 0, n);
            if (this.level1.com.get_red_fill(0, n) < this.level1.com.get_red_fill(1, n)) {
                this.level1.com.set_red_fill(this.level1.com.get_red_fill(1, n), 0, n);
            }
        }
        else if (this.level1.com.get_red_fill(0, n) < this.level1.com.get_red_fill(1, n)) {
            this.level1.com.set_red_fill(this.level1.com.get_red_fill(0, n) + 1, 0, n);
            if (this.level1.com.get_red_fill(0, n) > this.level1.com.get_red_fill(1, n)) {
                this.level1.com.set_red_fill(this.level1.com.get_red_fill(1, n), 0, n);
            }
        }
        if (this.level1.com.get_green_fill(0, n) > this.level1.com.get_green_fill(1, n)) {
            this.level1.com.set_green_fill(this.level1.com.get_green_fill(0, n) - 1, 0, n);
            if (this.level1.com.get_green_fill(0, n) < this.level1.com.get_green_fill(1, n)) {
                this.level1.com.set_green_fill(this.level1.com.get_green_fill(1, n), 0, n);
            }
        }
        else if (this.level1.com.get_green_fill(0, n) < this.level1.com.get_green_fill(1, n)) {
            this.level1.com.set_green_fill(this.level1.com.get_green_fill(0, n) + 1, 0, n);
            if (this.level1.com.get_green_fill(0, n) > this.level1.com.get_green_fill(1, n)) {
                this.level1.com.set_green_fill(this.level1.com.get_green_fill(1, n), 0, n);
            }
        }
        if (this.level1.com.get_blue_fill(0, n) > this.level1.com.get_blue_fill(1, n)) {
            this.level1.com.set_blue_fill(this.level1.com.get_blue_fill(0, n) - 1, 0, n);
            if (this.level1.com.get_blue_fill(0, n) < this.level1.com.get_blue_fill(1, n)) {
                this.level1.com.set_blue_fill(this.level1.com.get_blue_fill(1, n), 0, n);
            }
        }
        else if (this.level1.com.get_blue_fill(0, n) < this.level1.com.get_blue_fill(1, n)) {
            this.level1.com.set_blue_fill(this.level1.com.get_blue_fill(0, n) + 1, 0, n);
            if (this.level1.com.get_blue_fill(0, n) > this.level1.com.get_blue_fill(1, n)) {
                this.level1.com.set_blue_fill(this.level1.com.get_blue_fill(1, n), 0, n);
            }
        }
    }
    
    public void run() {
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                Thread.sleep(Math.max(0L, currentTimeMillis + this.game1.get_SPEED() - System.currentTimeMillis()));
            }
            catch (Exception ex) {
                return;
            }
            this.repaint();
        }
    }
    
    public void init() {
        this.Buffer = this.createImage(this.getSize().width, this.getSize().height);
        this.gBuffer = this.Buffer.getGraphics();
        this.addKeyListener(this);
        this.get_parameters();
        this.mt = new MediaTracker(this);
        try {
            this.base = this.getDocumentBase();
        }
        catch (Exception ex) {}
        this.title_jpg = this.getImage(this.base, "title1.jpg");
        this.craft_image = this.getImage(this.base, "craft.gif");
        this.game_over_image = this.getImage(this.base, "gameover.jpg");
        this.mt.addImage(this.title_jpg, 1);
        this.mt.addImage(this.craft_image, 2);
        this.mt.addImage(this.game_over_image, 3);
        this.laser = this.getAudioClip(this.base, "Laser2.au");
        this.mega_sound = this.getAudioClip(this.base, "mega.au");
        this.bang = this.getAudioClip(this.base, "explos.au");
        this.laser.play();
        this.laser.stop();
        this.mega_sound.play();
        this.mega_sound.stop();
        this.bang.play();
        this.bang.stop();
        this.game1.set_max_baddies(0, 50);
        this.game1.set_max_baddies(1, 50);
        this.game1.set_max_baddies(2, 50);
        this.game1.set_no_baddies(0, 15);
        this.game1.set_no_baddies(1, 10);
        this.game1.set_velocity_of_baddies(0, 2.0);
        this.game1.set_velocity_of_baddies(1, 3.0);
        this.game1.set_baddies_start_pos(0, 1200);
        this.game1.set_baddies_start_pos(1, 2000);
        this.game1.set_baddies_spacing(0, 200);
        this.game1.set_baddies_spacing(1, 400);
        this.game1.set_game_status(0);
        this.game1.set_game_over_status(1);
        this.game1.set_inputs_allowed(false);
        this.game1.set_new_game(true);
        this.game1.set_shot(false);
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex2) {}
        (this.level1 = new Level3D(0, 0, -400, 180, 800, 100)).clear_unwanted_lines();
        this.star1 = new Star[this.game1.get_max_baddies(0)];
        this.game1.set_no_jumps(2);
        for (int i = 0; i < this.game1.get_max_baddies(0); ++i) {
            final int n = (int)Math.round(Math.random() * 11.0);
            (this.star1[i] = new Star((int)(Math.sin(0.017453292519943295 * (n * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (n * 30 + 15)) * 160.0), -this.game1.get_baddies_start_pos(0) - i * this.game1.get_baddies_spacing(0), 10)).clear_unwanted_lines();
            this.star1[i].com.velocity.set_Array3D(0.0, 0.0, this.game1.get_velocity_of_baddies(0));
            this.star1[i].com.set_grid_position(n);
        }
        this.bad = new Baddy2[this.game1.get_max_baddies(1)];
        for (int j = 0; j < this.game1.get_max_baddies(1); ++j) {
            final int n2 = (int)Math.round(Math.random() * 11.0);
            (this.bad[j] = new Baddy2((int)(Math.sin(0.017453292519943295 * (n2 * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (n2 * 30 + 15)) * 160.0), -this.game1.get_baddies_start_pos(1) - j * this.game1.get_baddies_spacing(1), 15)).clear_unwanted_lines();
            this.bad[j].com.velocity.set_Array3D(0.0, 0.0, this.game1.get_velocity_of_baddies(1));
            this.bad[j].com.set_grid_position(n2);
            this.bad[j].move_baddy2_to_grid_position();
        }
        this.bad1 = new Baddy3[this.game1.get_max_baddies(2)];
        for (int k = 0; k < this.game1.get_max_baddies(2); ++k) {
            final int n3 = (int)Math.round(Math.random() * 11.0);
            (this.bad1[k] = new Baddy3((int)(Math.sin(0.017453292519943295 * (n3 * 30 + 15)) * 160.0), (int)(Math.cos(0.017453292519943295 * (n3 * 30 + 15)) * 160.0), -this.game1.get_baddies_start_pos(2) - k * this.game1.get_baddies_spacing(2), 10, 20)).clear_unwanted_lines();
            this.bad1[k].com.velocity.set_Array3D(0.0, 0.0, this.game1.get_velocity_of_baddies(2));
            this.bad1[k].com.set_grid_position(n3);
            this.bad1[k].move_baddy3_to_grid_position();
            this.bad1[k].com_shot.set_shot(false);
            this.bad1[k].com_shot.set_alive(false);
            this.bad1[k].com_shot.set_visible(false);
            this.bad1[k].com_shot.set_top_of_web(false);
            this.bad1[k].com_shot.set_on_web(false);
        }
        this.bad1[0].com.set_alive(false);
        this.mega = new MBlastG[20];
        this.mega1 = new MBlastG[20];
        int n4 = 0;
        do {
            this.mega[n4] = new MBlastG(0, 0, -300 - n4 * 20, 300);
            this.mega1[n4] = new MBlastG(0, 0, -300 - n4 * 20, 300);
            this.mega[n4].com.set_red_colour(255 - n4 * 12, 0);
            this.mega[n4].com.set_green_colour(255 - n4 * 12, 0);
            this.mega[n4].com.set_blue_colour(255 - n4 * 12, 0);
            this.mega1[n4].com.set_red_colour(255 - n4 * 12, 0);
            this.mega1[n4].com.set_green_colour(255 - n4 * 12, 0);
            this.mega1[n4].com.set_blue_colour(255 - n4 * 12, 0);
        } while (++n4 < 20);
        this.mega_icon.com.set_red_colour(255, 0);
        this.mega_icon.com.set_green_colour(255, 0);
        this.mega_icon.com.set_blue_colour(255, 0);
        this.mega1_icon.com.set_red_colour(255, 0);
        this.mega1_icon.com.set_green_colour(255, 0);
        this.mega1_icon.com.set_blue_colour(255, 0);
        this.shot1 = new Shot[3];
        int n5 = 0;
        do {
            (this.shot1[n5] = new Shot(0, 0, 1000, 5)).clear_unwanted_lines();
        } while (++n5 < 3);
        this.craft1 = new Craft(0, 0, 0, 12, 25, 30, 0, 20, 0, false);
        this.d = this.getSize();
        this.eye = new Array3D(0.0, 0.0, 0.0);
    }
    
    public void draw_screen_overlay() {
        for (int i = 0; i < this.game1.get_number_of_lives() - 1; ++i) {
            this.gBuffer.drawImage(this.craft_image, i * 70 + 10, 35, this);
        }
        this.gBuffer.setFont(new Font("Helvetica", 0, 20));
        final String value = String.valueOf(this.game1.get_score());
        this.gBuffer.setColor(Color.white);
        this.gBuffer.drawString(value, 10, 25);
        final String value2 = String.valueOf(this.game1.get_level());
        this.gBuffer.setColor(new Color(255 - this.game1.com1.get_red_colour(0), 255 - this.game1.com1.get_green_colour(0), 255 - this.game1.com1.get_blue_colour(0)));
        this.gBuffer.setFont(new Font("Helvetica", 0, 15));
        this.gBuffer.drawString("Level " + value2, 450, 495);
        this.gBuffer.drawString("Jumps Left: " + String.valueOf(this.game1.get_no_jumps()), 5, 495);
    }
    
    public void scramble_screen() {
    }
    
    public int get_poly_from_grid_pos() {
        int n = 0;
        switch (this.craft1.com.get_grid_position()) {
            case 0: {
                n = 5;
                break;
            }
            case 1: {
                n = 4;
                break;
            }
            case 2: {
                n = 3;
                break;
            }
            case 3: {
                n = 2;
                break;
            }
            case 4: {
                n = 1;
                break;
            }
            case 5: {
                n = 0;
                break;
            }
            case 6: {
                n = 11;
                break;
            }
            case 7: {
                n = 10;
                break;
            }
            case 8: {
                n = 9;
                break;
            }
            case 9: {
                n = 8;
                break;
            }
            case 10: {
                n = 7;
                break;
            }
            case 11: {
                n = 6;
                break;
            }
        }
        return n;
    }
    
    public void move_eye_craft_shot() {
        if (this.eye.get_z() < 1000.0) {
            this.eye.set_z(this.eye.get_z() + 25.0);
            this.craft1.com.move_object(0.0, 0.0, 3.0);
            return;
        }
        this.gBuffer.setColor(Color.white);
        this.gBuffer.fillRect(0, 0, this.d.width, this.d.height);
        this.new_life();
        this.game1.set_game_over_status(3);
    }
}

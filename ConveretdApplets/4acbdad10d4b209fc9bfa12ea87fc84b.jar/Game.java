import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Game
{
    private int score;
    private int number_of_lives;
    private int killer;
    private int killer_type;
    private String[] high_score_name;
    private int[] high_score_value;
    private boolean itsmega_blast;
    private boolean itsmega_blast_allowed;
    private int[] no_baddies;
    private double[] velocity_of_baddies;
    private int[] baddies_start_pos;
    private int[] baddies_spacing;
    private int[] max_baddies;
    private int game_status;
    private int game_over_status;
    private boolean new_game;
    private boolean shot;
    private boolean inputs_allowed;
    private boolean left_button_down;
    private boolean right_button_down;
    private boolean up_button_down;
    private boolean down_button_down;
    private int SPEED;
    private int bonus_level;
    private int no_jumps;
    private int level;
    Common com1;
    
    public int get_killer_type() {
        return this.killer_type;
    }
    
    public void set_baddies_start_pos(final int n, final int n2) {
        this.baddies_start_pos[n] = n2;
    }
    
    public void set_new_game(final boolean new_game) {
        this.new_game = new_game;
    }
    
    public void increase_number_of_lives() {
        ++this.number_of_lives;
    }
    
    public void reset_high_scores() {
        this.high_score_name[0] = "Boomah";
        this.high_score_name[1] = "Bunny";
        this.high_score_name[2] = "Big Dave";
        this.high_score_name[3] = "Cheesey";
        this.high_score_name[4] = "Tom";
        this.high_score_name[5] = "Ally";
        this.high_score_name[6] = "Housten";
        this.high_score_name[7] = "Phil";
        this.high_score_name[8] = "Jan";
        this.high_score_name[9] = "Guv";
        this.high_score_value[0] = 1000000;
        this.high_score_value[1] = 900000;
        this.high_score_value[2] = 800000;
        this.high_score_value[3] = 700000;
        this.high_score_value[4] = 600000;
        this.high_score_value[5] = 500000;
        this.high_score_value[6] = 400000;
        this.high_score_value[7] = 300000;
        this.high_score_value[8] = 200000;
        this.high_score_value[9] = 100000;
    }
    
    public void set_max_baddies(final int n, final int n2) {
        this.max_baddies[n] = n2;
    }
    
    public int get_max_baddies(final int n) {
        return this.max_baddies[n];
    }
    
    public void set_game_status(final int game_status) {
        this.game_status = game_status;
    }
    
    public int get_game_status() {
        return this.game_status;
    }
    
    public boolean get_new_game() {
        return this.new_game;
    }
    
    public void set_mega_blast_allowed(final boolean itsmega_blast_allowed) {
        this.itsmega_blast_allowed = itsmega_blast_allowed;
    }
    
    public void set_shot(final boolean shot) {
        this.shot = shot;
    }
    
    public boolean get_shot() {
        return this.shot;
    }
    
    public String get_name(final int n) {
        return this.high_score_name[n];
    }
    
    public void set_name(final int n, final String s) {
        this.high_score_name[n] = s;
    }
    
    public boolean mega_blast() {
        return this.itsmega_blast;
    }
    
    public int get_high_score_value(final int n) {
        return this.high_score_value[n];
    }
    
    public void set_no_baddies(final int n, final int n2) {
        this.no_baddies[n] = n2;
    }
    
    public int get_no_baddies(final int n) {
        return this.no_baddies[n];
    }
    
    public void set_velocity_of_baddies(final int n, final double n2) {
        this.velocity_of_baddies[n] = n2;
    }
    
    public boolean mega_blast_allowed() {
        return this.itsmega_blast_allowed;
    }
    
    public double get_velocity_of_baddies(final int n) {
        return this.velocity_of_baddies[n];
    }
    
    public void set_baddies_spacing(final int n, final int n2) {
        this.baddies_spacing[n] = n2;
    }
    
    public int get_baddies_spacing(final int n) {
        return this.baddies_spacing[n];
    }
    
    public void set_level(final int level) {
        this.level = level;
    }
    
    public int get_level() {
        return this.level;
    }
    
    public void set_bonus_level(final int bonus_level) {
        this.bonus_level = bonus_level;
    }
    
    public int get_score(final int n) {
        return this.high_score_value[n];
    }
    
    public void set_score(final int n, final int n2) {
        this.high_score_value[n] = n2;
    }
    
    public int get_score() {
        return this.score;
    }
    
    public void reset_score() {
        this.score = 0;
    }
    
    public int get_bonus_level() {
        return this.bonus_level;
    }
    
    public void set_right_button_down(final boolean right_button_down) {
        this.right_button_down = right_button_down;
    }
    
    public boolean get_right_button_down() {
        return this.right_button_down;
    }
    
    public void set_up_button_down(final boolean up_button_down) {
        this.up_button_down = up_button_down;
    }
    
    public boolean get_up_button_down() {
        return this.up_button_down;
    }
    
    public Game() {
        this.high_score_name = new String[10];
        this.high_score_value = new int[10];
        this.no_baddies = new int[10];
        this.velocity_of_baddies = new double[10];
        this.baddies_start_pos = new int[10];
        this.baddies_spacing = new int[10];
        this.max_baddies = new int[10];
        this.com1 = new Common(0, 0);
        this.score = 0;
        this.number_of_lives = 3;
        this.reset_high_scores();
        this.itsmega_blast = false;
        this.itsmega_blast_allowed = true;
        this.level = 1;
        this.com1.set_blue_colour(255, 0);
        this.com1.set_red_colour(255, 0);
        this.com1.set_green_colour(255, 0);
        this.bonus_level = 100000;
    }
    
    public void set_inputs_allowed(final boolean inputs_allowed) {
        this.inputs_allowed = inputs_allowed;
    }
    
    public boolean get_inputs_allowed() {
        return this.inputs_allowed;
    }
    
    public void set_no_jumps(final int no_jumps) {
        this.no_jumps = no_jumps;
    }
    
    public int get_no_jumps() {
        return this.no_jumps;
    }
    
    public void set_high_score(final String s) {
        int n = 9;
        if (this.score < this.high_score_value[0]) {
            while (this.score > this.high_score_value[n]) {
                if (n + 1 < 10) {
                    this.high_score_value[n + 1] = this.high_score_value[n];
                    this.high_score_name[n + 1] = this.high_score_name[n];
                }
                this.high_score_value[n] = this.score;
                this.high_score_name[n] = s;
                --n;
            }
            return;
        }
        int n2 = 8;
        do {
            this.high_score_value[n2 + 1] = this.high_score_value[n2];
            this.high_score_name[n2 + 1] = this.high_score_name[n2];
        } while (--n2 >= 0);
        this.high_score_value[0] = this.score;
        this.high_score_name[0] = s;
    }
    
    public void decrease_no_jumps() {
        --this.no_jumps;
    }
    
    public void set_killer(final int killer) {
        this.killer = killer;
    }
    
    public int get_killer() {
        return this.killer;
    }
    
    public void set_number_of_lives(final int number_of_lives) {
        this.number_of_lives = number_of_lives;
    }
    
    public int get_number_of_lives() {
        return this.number_of_lives;
    }
    
    public void decrease_number_of_lives() {
        --this.number_of_lives;
    }
    
    public void set_game_over_status(final int game_over_status) {
        this.game_over_status = game_over_status;
    }
    
    public int get_game_over_status() {
        return this.game_over_status;
    }
    
    public void set_mega_blast(final boolean itsmega_blast) {
        this.itsmega_blast = itsmega_blast;
    }
    
    public void increase_score(final int n) {
        this.score += n;
    }
    
    public void set_SPEED(final int speed) {
        this.SPEED = speed;
    }
    
    public int get_SPEED() {
        return this.SPEED;
    }
    
    public void set_left_button_down(final boolean left_button_down) {
        this.left_button_down = left_button_down;
    }
    
    public boolean get_left_button_down() {
        return this.left_button_down;
    }
    
    public void set_down_button_down(final boolean down_button_down) {
        this.down_button_down = down_button_down;
    }
    
    public boolean get_down_button_down() {
        return this.down_button_down;
    }
    
    public int get_baddies_start_pos(final int n) {
        return this.baddies_start_pos[n];
    }
    
    public void draw_high_score_table(final Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.setFont(new Font("Helvetica", 0, 16));
        int n = 0;
        do {
            graphics.drawString(String.valueOf(n + 1) + ".", 150, 250 + n * 22);
            graphics.drawString(this.high_score_name[n], 200, 250 + n * 22);
            graphics.drawString(String.valueOf(this.high_score_value[n]), 300, 250 + n * 22);
        } while (++n < 10);
        graphics.setFont(new Font("Helvetica", 1, 16));
        graphics.drawString("Press any key to continue", 150, 490);
    }
    
    public void set_killer_type(final int killer_type) {
        this.killer_type = killer_type;
    }
}

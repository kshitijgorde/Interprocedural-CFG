import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Point;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class centipedo extends Applet implements Runnable
{
    final int screen_width = 600;
    final int field_height = 416;
    final int stats_height = 32;
    final int mushroom_points = 5;
    final int centipede_points = 10;
    final int beetle_points = 20;
    final int flea_points = 50;
    final int spider_points = 75;
    final int scorpion_points = 500;
    final int extra_life_points = 4000;
    final int tracker_player_id = 0;
    final int tracker_shot_id = 1;
    final int tracker_mushroom_id = 2;
    final int tracker_centipede_id = 3;
    final int tracker_beetle_id = 4;
    final int tracker_scorpion_id = 5;
    final int tracker_spider_id = 6;
    final int tracker_flea_id = 6;
    final Color missing_image_color;
    final Color[] background_colors;
    Font big_font;
    FontMetrics big_font_metrics;
    MediaTracker tracker;
    Image player_image;
    Image dead_player_image;
    Image shot_image;
    Image mushroom_image;
    Image[] centipede_images;
    Image[] beetle_images;
    Image[] scorpion_images;
    Image spider_image;
    Image flea_image;
    AudioClip fire_shot_sound;
    AudioClip destroy_mushroom_sound;
    AudioClip destroy_centipede_sound;
    AudioClip destroy_beetle_sound;
    AudioClip destroy_scorpion_sound;
    AudioClip destroy_spider_sound;
    AudioClip destroy_flea_sound;
    AudioClip destroy_player_sound;
    AudioClip new_level_sound;
    AudioClip extra_life_sound;
    boolean game_over;
    boolean clear_screen;
    boolean mouse_down;
    boolean need_reset;
    boolean update_mushrooms;
    boolean update_level;
    boolean update_score;
    boolean update_lives;
    boolean update_player;
    boolean expand_mushrooms;
    boolean paused;
    Point mouse;
    Point mouse_loc;
    Beetle[] beetles;
    Centipede[] centipede;
    Point[] fleas;
    Scorpion[] scorpions;
    Spider[] spiders;
    Mushroom[] mushrooms;
    Point[] shots;
    Thread thread;
    int centipede_length;
    int max_shots;
    int max_mushrooms;
    int max_beetles;
    int max_scorpions;
    int max_spiders;
    int max_fleas;
    int max_creature_count;
    int shot_speed;
    int mushroom_count;
    int lives;
    int level;
    int score;
    int lscore;
    
    public void init() {
        this.big_font = new Font("TimesRoman", 1, 24);
        this.big_font_metrics = this.getFontMetrics(this.big_font);
        this.setFont(this.big_font);
        this.tracker = new MediaTracker(this);
        this.player_image = this.getImage(this.getCodeBase(), "images/player.gif");
        this.tracker.addImage(this.player_image, 0);
        this.dead_player_image = this.getImage(this.getCodeBase(), "images/dead_player.gif");
        this.tracker.addImage(this.dead_player_image, 0);
        this.shot_image = this.getImage(this.getCodeBase(), "images/shot.gif");
        this.tracker.addImage(this.shot_image, 1);
        this.mushroom_image = this.getImage(this.getCodeBase(), "images/mushroom.gif");
        this.tracker.addImage(this.mushroom_image, 2);
        (this.beetle_images = new Image[2])[0] = this.getImage(this.getCodeBase(), "images/beetle_right.gif");
        this.beetle_images[1] = this.getImage(this.getCodeBase(), "images/beetle_left.gif");
        this.tracker.addImage(this.beetle_images[0], 4);
        this.tracker.addImage(this.beetle_images[1], 4);
        (this.scorpion_images = new Image[2])[0] = this.getImage(this.getCodeBase(), "images/scorpion_right.gif");
        this.scorpion_images[1] = this.getImage(this.getCodeBase(), "images/scorpion_left.gif");
        this.tracker.addImage(this.scorpion_images[0], 5);
        this.tracker.addImage(this.scorpion_images[1], 5);
        this.spider_image = this.getImage(this.getCodeBase(), "images/spider.gif");
        this.tracker.addImage(this.spider_image, 6);
        this.flea_image = this.getImage(this.getCodeBase(), "images/flea.gif");
        this.tracker.addImage(this.flea_image, 6);
        (this.centipede_images = new Image[2])[0] = this.getImage(this.getCodeBase(), "images/head.gif");
        this.centipede_images[1] = this.getImage(this.getCodeBase(), "images/body.gif");
        this.tracker.addImage(this.centipede_images[0], 3);
        this.tracker.addImage(this.centipede_images[1], 3);
        this.fire_shot_sound = this.getAudioClip(this.getCodeBase(), "sounds/shot.au");
        this.destroy_mushroom_sound = this.getAudioClip(this.getCodeBase(), "sounds/drip.au");
        this.destroy_centipede_sound = this.getAudioClip(this.getCodeBase(), "sounds/Water.au");
        this.destroy_beetle_sound = this.getAudioClip(this.getCodeBase(), "sounds/crunch.au");
        this.destroy_scorpion_sound = this.getAudioClip(this.getCodeBase(), "sounds/wohoo.au");
        this.destroy_spider_sound = this.getAudioClip(this.getCodeBase(), "sounds/hammer.au");
        this.destroy_flea_sound = this.getAudioClip(this.getCodeBase(), "sounds/hehehehe.au");
        this.destroy_player_sound = this.getAudioClip(this.getCodeBase(), "sounds/crap1.au");
        this.new_level_sound = this.getAudioClip(this.getCodeBase(), "sounds/magic.au");
        this.extra_life_sound = this.getAudioClip(this.getCodeBase(), "sounds/bleep.au");
        this.shots = new Point[this.max_shots];
        for (int i = 0; i < this.shots.length; ++i) {
            this.shots[i] = new Point(0, 0);
        }
        this.mouse_loc = new Point(300, 208);
        this.mouse = new Point(300, 208);
        this.setBackground(this.background_colors[0]);
        this.resize(600, 448);
        (this.thread = new Thread(this)).start();
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        boolean checkID = false;
        this.tracker.checkAll(true);
        this.max_beetles = 3;
        this.max_scorpions = 1;
        this.max_spiders = 1;
        this.max_fleas = 3;
        this.reset_creatures();
        this.generate_centipede();
        this.generate_mushrooms();
        while (true) {
            final double random = Math.random();
            if (random < 0.5) {
                this.generate_beetle();
            }
            if (random < 0.3) {
                this.generate_spider();
            }
            if (random < 0.05) {
                this.generate_scorpion();
            }
            if (this.mushroom_count < this.max_mushrooms) {
                this.generate_flea();
            }
            if (this.mouse_down) {
                this.fire_shot(this.mouse_loc.x, this.mouse_loc.y);
            }
            if (!checkID) {
                checkID = this.tracker.checkID(2, true);
                if (checkID) {
                    this.clear_screen = true;
                    this.update_mushrooms = true;
                    this.update_lives = true;
                    this.update_score = true;
                    this.update_level = true;
                }
            }
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
    }
    
    void start_game() {
        this.lives = 3;
        this.level = 1;
        this.score = 0;
        this.lscore = 0;
        this.max_beetles = 1;
        this.max_scorpions = 0;
        this.max_spiders = 1;
        this.max_fleas = 1;
        this.game_over = false;
        this.clear_screen = true;
        this.setBackground(this.background_colors[(this.level - 1) % this.background_colors.length]);
        this.update_lives = true;
        this.update_score = true;
        this.update_level = true;
        this.reset_creatures();
        this.generate_mushrooms();
        this.generate_centipede();
    }
    
    void reset_creatures() {
        this.beetles = new Beetle[Math.min(this.max_beetles, this.max_creature_count)];
        for (int i = 0; i < this.beetles.length; ++i) {
            this.beetles[i] = new Beetle(0, 0, 0);
        }
        this.scorpions = new Scorpion[Math.min(this.max_scorpions, this.max_creature_count)];
        for (int j = 0; j < this.scorpions.length; ++j) {
            this.scorpions[j] = new Scorpion(0, 0, 0);
        }
        this.spiders = new Spider[Math.min(this.max_spiders, this.max_creature_count)];
        for (int k = 0; k < this.spiders.length; ++k) {
            this.spiders[k] = new Spider(0, 0, 0, 0);
        }
        this.fleas = new Point[Math.min(this.max_fleas, this.max_creature_count)];
        for (int l = 0; l < this.fleas.length; ++l) {
            this.fleas[l] = new Point(0, 0);
        }
    }
    
    void reset_game() {
        for (int i = 0; i < this.shots.length; ++i) {
            this.shots[i].x = 0;
            this.shots[i].y = 0;
        }
        if (this.centipede != null) {
            this.generate_centipede();
        }
        this.clear_screen = true;
        this.update_mushrooms = true;
        this.update_lives = true;
        this.update_score = true;
        this.update_level = true;
        this.reset_creatures();
    }
    
    void new_level() {
        if (this.new_level_sound != null) {
            this.new_level_sound.play();
        }
        ++this.level;
        this.setBackground(this.background_colors[(this.level - 1) % this.background_colors.length]);
        this.clear_screen = true;
        this.update_mushrooms = true;
        this.update_lives = true;
        this.update_score = true;
        this.update_level = true;
        this.update_player = true;
        this.generate_centipede();
        if (this.level % 3 == 0) {
            if (this.max_scorpions == 0) {
                this.max_scorpions = Math.min(this.max_scorpions + 1, this.max_creature_count);
            }
            this.max_beetles = Math.min(this.max_beetles + 1, this.max_creature_count);
            this.max_fleas = Math.min(this.max_fleas + 1, this.max_creature_count);
        }
        if (this.level % 5 == 0) {
            this.max_spiders = Math.min(this.max_spiders + 1, this.max_creature_count);
            this.max_scorpions = Math.min(this.max_scorpions + 1, this.max_creature_count);
        }
        this.reset_creatures();
    }
    
    void suspend_game() {
        if (this.thread != null && !this.game_over) {
            this.thread.suspend();
            this.paused = true;
            this.clear_screen = true;
            this.repaint();
        }
    }
    
    void resume_game() {
        if (this.thread != null && !this.game_over) {
            this.thread.resume();
            this.paused = false;
            this.clear_screen = true;
            this.update_mushrooms = true;
            this.update_lives = true;
            this.update_score = true;
            this.update_level = true;
            this.update_player = true;
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.game_over) {
            this.start_game();
        }
        this.mouse_down = false;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return this.mouse_down = true;
    }
    
    void move_mouse(final int n, int n2) {
        final int n3 = 278;
        if (n2 < n3) {
            n2 = n3;
        }
        else if (n2 > 400) {
            n2 = 400;
        }
        this.mouse_loc.move(n, n2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.move_mouse(n, n2);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.move_mouse(n, n2);
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 71:
            case 103: {
                if (!this.paused) {
                    this.start_game();
                    break;
                }
                break;
            }
            case 80:
            case 112: {
                if (this.paused) {
                    this.resume_game();
                    break;
                }
                this.suspend_game();
                break;
            }
            case 120: {
                this.new_level();
                break;
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.game_over) {
            this.resume_game();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.game_over) {
            this.suspend_game();
        }
        return true;
    }
    
    boolean collision(final Point point, final int n, final Point point2, final int n2) {
        return (int)Math.sqrt((point2.x - point.x) * (point2.x - point.x) + (point2.y - point.y) * (point2.y - point.y)) <= n + n2;
    }
    
    void fire_shot(final int x, final int y) {
        if (this.game_over) {
            return;
        }
        int i;
        for (i = 0; i < this.shots.length; ++i) {
            if (this.shots[i].y == 0) {
                this.shots[i].x = x;
                this.shots[i].y = y;
                break;
            }
        }
        if (i < this.shots.length && this.fire_shot_sound != null) {
            this.fire_shot_sound.play();
        }
    }
    
    void increment_score(final int n) {
        this.score += n;
        this.lscore += n;
        if (this.lscore >= 4000) {
            if (this.extra_life_sound != null) {
                this.extra_life_sound.play();
            }
            this.lscore -= 4000;
            ++this.lives;
            this.update_lives = true;
        }
        this.update_score = true;
    }
    
    void destroy_mushroom(final Graphics graphics, final int n) {
        this.increment_score(5);
        if (this.destroy_mushroom_sound != null) {
            this.destroy_mushroom_sound.play();
        }
        final Mushroom mushroom = this.mushrooms[n];
        ++mushroom.damage;
        if (this.mushrooms[n].damage < 3) {
            graphics.clearRect(this.mushrooms[n].x - 16, this.mushrooms[n].y - 16, 32, 32);
            this.paint_mushroom_collision(graphics, this.mushrooms[n], 16);
            return;
        }
        final Point point = new Point(this.mushrooms[n].x, this.mushrooms[n].y);
        graphics.clearRect(this.mushrooms[n].x - 16, this.mushrooms[n].y - 16, 32, 32);
        this.mushrooms[n].x = 0;
        this.mushrooms[n].y = 0;
        --this.mushroom_count;
        this.paint_mushroom_collision(graphics, point, 16);
    }
    
    void destroy_centipede(final Graphics graphics, final int n) {
        final Point point = new Point(this.centipede[n].x, this.centipede[n].y);
        this.increment_score(10);
        if (this.destroy_centipede_sound != null) {
            this.destroy_centipede_sound.play();
        }
        graphics.clearRect(this.centipede[n].x - 8, this.centipede[n].y - 8, 16, 16);
        this.centipede[n].x = 0;
        this.centipede[n].y = 0;
        final int generate_mushroom = this.generate_mushroom(point.x, point.y);
        if (generate_mushroom != -1) {
            this.paint_mushroom(graphics, generate_mushroom);
        }
        int n2;
        for (n2 = 0; n2 < this.centipede.length && (this.centipede[n2].x == 0 || this.centipede[n2].y == 0); ++n2) {}
        if (n2 == this.centipede.length) {
            this.new_level();
        }
        this.paint_mushroom_collision(graphics, point, 8);
    }
    
    void destroy_spider(final Graphics graphics, final int n) {
        final Point point = new Point(this.spiders[n].x, this.spiders[n].y);
        if (this.destroy_spider_sound != null) {
            this.destroy_spider_sound.play();
        }
        this.increment_score(75);
        graphics.clearRect(this.spiders[n].x - 16, this.spiders[n].y - 16, 32, 32);
        this.spiders[n].x = 0;
        this.spiders[n].y = 0;
        this.paint_mushroom_collision(graphics, point, 16);
    }
    
    void destroy_scorpion(final Graphics graphics, final int n) {
        final Point point = new Point(this.scorpions[n].x, this.scorpions[n].y);
        if (this.destroy_scorpion_sound != null) {
            this.destroy_scorpion_sound.play();
        }
        this.increment_score(500);
        graphics.clearRect(this.scorpions[n].x - 16, this.scorpions[n].y - 16, 32, 32);
        this.scorpions[n].x = 0;
        this.scorpions[n].y = 0;
        this.paint_mushroom_collision(graphics, point, 16);
    }
    
    void destroy_beetle(final Graphics graphics, final int n) {
        final Point point = new Point(this.beetles[n].x, this.beetles[n].y);
        if (this.destroy_beetle_sound != null) {
            this.destroy_beetle_sound.play();
        }
        this.increment_score(20);
        graphics.clearRect(this.beetles[n].x - 16, this.beetles[n].y - 16, 32, 32);
        this.beetles[n].x = 0;
        this.beetles[n].y = 0;
        this.paint_mushroom_collision(graphics, point, 16);
    }
    
    void destroy_flea(final Graphics graphics, final int n) {
        final Point point = new Point(this.fleas[n].x, this.fleas[n].y);
        if (this.destroy_flea_sound != null) {
            this.destroy_flea_sound.play();
        }
        this.increment_score(50);
        graphics.clearRect(this.fleas[n].x - 8, this.fleas[n].y - 8, 16, 16);
        this.fleas[n].x = 0;
        this.fleas[n].y = 0;
        this.paint_mushroom_collision(graphics, point, 8);
    }
    
    void destroy_player() {
        if (this.game_over) {
            return;
        }
        if (this.destroy_player_sound != null) {
            this.destroy_player_sound.play();
        }
        if (this.need_reset) {
            return;
        }
        --this.lives;
        if (this.lives < 0) {
            this.lives = 0;
            this.game_over = true;
        }
        else {
            this.need_reset = true;
            this.expand_mushrooms = true;
        }
        this.update_lives = true;
    }
    
    boolean check_player_collision(final Point point, final int n) {
        final boolean collision = this.collision(point, n, this.mouse_loc, 16);
        if (collision) {
            this.destroy_player();
            this.update_player = true;
        }
        return collision;
    }
    
    int generate_mushroom(final int x, int y) {
        if (y > 400) {
            y = 400;
        }
        for (int i = 0; i < this.mushrooms.length; ++i) {
            if (this.mushrooms[i].x == 0 && this.mushrooms[i].y == 0) {
                this.mushrooms[i].x = x;
                this.mushrooms[i].y = y;
                this.mushrooms[i].damage = 0;
                ++this.mushroom_count;
                return i;
            }
        }
        return -1;
    }
    
    void generate_mushrooms() {
        final int n = this.max_mushrooms + this.centipede_length * 3;
        this.mushrooms = new Mushroom[n];
        int i;
        for (i = 0; i < this.max_mushrooms; ++i) {
            this.mushrooms[i] = new Mushroom((int)(Math.random() * 600.0), (int)(Math.random() * 400.0));
        }
        this.mushroom_count = this.max_mushrooms;
        while (i < n) {
            this.mushrooms[i] = new Mushroom(0, 0);
            ++i;
        }
        this.update_mushrooms = true;
    }
    
    void generate_centipede() {
        int n;
        int n2;
        if (Math.random() > 0.5) {
            n = 592;
            n2 = -1;
        }
        else {
            n = 8;
            n2 = 1;
        }
        this.centipede = new Centipede[this.centipede_length];
        for (int i = 0; i < this.centipede.length; ++i) {
            if (i == 0) {
                this.centipede[i] = new Centipede(n, 8, n2, 1);
            }
            else {
                this.centipede[i] = new Centipede(this.centipede[i - 1].x + -(n2 * 16), this.centipede[i - 1].y, n2, 1);
            }
        }
    }
    
    void generate_beetle() {
        for (int i = 0; i < this.max_beetles; ++i) {
            if (this.beetles[i].x == 0 && this.beetles[i].y == 0) {
                if (Math.random() > 0.5) {
                    this.beetles[i].hort_dir = 1;
                    this.beetles[i].x = 8;
                }
                else {
                    this.beetles[i].hort_dir = -1;
                    this.beetles[i].x = 592;
                }
                this.beetles[i].y = 208 + (int)(Math.random() * 192.0);
                return;
            }
        }
    }
    
    void generate_scorpion() {
        for (int i = 0; i < this.max_scorpions; ++i) {
            if (this.scorpions[i].x == 0 && this.scorpions[i].y == 0) {
                if (Math.random() > 0.5) {
                    this.scorpions[i].hort_dir = 1;
                    this.scorpions[i].x = 16;
                }
                else {
                    this.scorpions[i].hort_dir = -1;
                    this.scorpions[i].x = 584;
                }
                this.scorpions[i].y = 100;
                return;
            }
        }
    }
    
    void generate_spider() {
        for (int i = 0; i < this.max_spiders; ++i) {
            if (this.spiders[i].x == 0 && this.spiders[i].y == 0) {
                if (Math.random() > 0.5) {
                    this.spiders[i].x = 8;
                    this.spiders[i].hort_dir = 1;
                }
                else {
                    this.spiders[i].x = 592;
                    this.spiders[i].hort_dir = -1;
                }
                this.spiders[i].y = 208 + (int)(Math.random() * 208.0) - 16;
                this.spiders[i].vert_dir = 1;
                this.spiders[i].start_dir = this.spiders[i].hort_dir;
                return;
            }
        }
    }
    
    void generate_flea() {
        for (int i = 0; i < this.max_fleas; ++i) {
            if (this.fleas[i].x == 0 && this.fleas[i].y == 0) {
                this.fleas[i].x = (int)(Math.random() * 600.0);
                this.fleas[i].y = 8;
                return;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void paint_mushroom(final Graphics graphics, final int n) {
        if (this.tracker.checkID(2, true)) {
            final int n2 = 16 - this.mushrooms[n].damage * 4;
            graphics.drawImage(this.mushroom_image, this.mushrooms[n].x - n2, this.mushrooms[n].y - n2, n2 * 2, n2 * 2, this);
            return;
        }
        final Polygon polygon = new Polygon();
        polygon.addPoint(this.mushrooms[n].x - 16, this.mushrooms[n].y + 16);
        polygon.addPoint(this.mushrooms[n].x, this.mushrooms[n].y - 16);
        polygon.addPoint(this.mushrooms[n].x + 16, this.mushrooms[n].y + 16);
        graphics.setColor(this.missing_image_color);
        graphics.fillPolygon(polygon);
    }
    
    boolean paint_mushroom_collision(final Graphics graphics, final Point point, final int n) {
        boolean b = false;
        for (int i = 0; i < this.mushrooms.length; ++i) {
            if ((this.mushrooms[i].x != 0 || this.mushrooms[i].y != 0) && this.collision(point, n, this.mushrooms[i], 16)) {
                this.paint_mushroom(graphics, i);
                b = true;
            }
        }
        return b;
    }
    
    void paint_mushrooms(final Graphics graphics) {
        for (int i = 0; i < this.mushrooms.length; ++i) {
            if (this.mushrooms[i].x != 0 || this.mushrooms[i].y != 0) {
                this.paint_mushroom(graphics, i);
            }
        }
    }
    
    void paint_mushrooms_expand(final Graphics graphics) {
        for (int i = 0; i < this.mushrooms.length; ++i) {
            if ((this.mushrooms[i].x != 0 || this.mushrooms[i].y != 0) && this.mushrooms[i].damage > 0) {
                this.mushrooms[i].damage = 0;
                this.paint_mushroom(graphics, i);
                if (this.destroy_mushroom_sound != null) {
                    this.destroy_mushroom_sound.play();
                }
            }
        }
    }
    
    void paint_player(final Graphics graphics) {
        if (!this.update_player && this.mouse.x == this.mouse_loc.x && this.mouse.y == this.mouse_loc.y) {
            return;
        }
        this.update_player = false;
        final boolean checkID = this.tracker.checkID(0, true);
        graphics.clearRect(this.mouse.x - 16, this.mouse.y - 16, 32, 32);
        this.paint_mushroom_collision(graphics, this.mouse, 16);
        this.mouse.x = this.mouse_loc.x;
        this.mouse.y = this.mouse_loc.y;
        this.paint_mushroom_collision(graphics, this.mouse, 16);
        if (!checkID) {
            graphics.setColor(this.missing_image_color);
            graphics.fillOval(this.mouse.x - 16, this.mouse.y - 16, 32, 32);
            return;
        }
        if (this.game_over) {
            graphics.drawImage(this.dead_player_image, this.mouse.x - 16, this.mouse.y - 16, this);
            return;
        }
        graphics.drawImage(this.player_image, this.mouse.x - 16, this.mouse.y - 16, this);
    }
    
    void paint_shots(final Graphics graphics) {
        final boolean checkID = this.tracker.checkID(1, true);
        for (int i = 0; i < this.shots.length; ++i) {
            if (this.shots[i].x != 0 || this.shots[i].y != 0) {
                graphics.clearRect(this.shots[i].x - 8, this.shots[i].y - 8, 16, 16);
                int n = 0;
                for (int n2 = 0; n == 0 && n2 < this.centipede.length; ++n2) {
                    if (this.collision(this.shots[i], 8, this.centipede[n2], 8)) {
                        n = 1;
                        this.destroy_centipede(graphics, n2);
                    }
                }
                for (int n3 = 0; n == 0 && n3 < this.max_spiders; ++n3) {
                    if (this.collision(this.shots[i], 8, this.spiders[n3], 16)) {
                        n = 1;
                        this.destroy_spider(graphics, n3);
                    }
                }
                for (int n4 = 0; n == 0 && n4 < this.max_scorpions; ++n4) {
                    if (this.collision(this.shots[i], 8, this.scorpions[n4], 16)) {
                        n = 1;
                        this.destroy_scorpion(graphics, n4);
                        break;
                    }
                }
                for (int n5 = 0; n == 0 && n5 < this.max_beetles; ++n5) {
                    if (this.collision(this.shots[i], 8, this.beetles[n5], 16)) {
                        n = 1;
                        this.destroy_beetle(graphics, n5);
                    }
                }
                for (int n6 = 0; n == 0 && n6 < this.max_fleas; ++n6) {
                    if (this.collision(this.shots[i], 8, this.fleas[n6], 16)) {
                        n = 1;
                        this.destroy_flea(graphics, n6);
                    }
                }
                for (int n7 = 0; n == 0 && n7 < this.mushrooms.length; ++n7) {
                    if (this.collision(this.shots[i], 8, this.mushrooms[n7], 16)) {
                        n = 1;
                        this.destroy_mushroom(graphics, n7);
                    }
                }
                if (n != 0) {
                    this.shots[i].x = 0;
                    this.shots[i].y = 0;
                }
                else {
                    final Point point = this.shots[i];
                    point.y -= this.shot_speed;
                    if (this.shots[i].y <= 0) {
                        this.shots[i].x = 0;
                        this.shots[i].y = 0;
                    }
                    else if (checkID) {
                        graphics.drawImage(this.shot_image, this.shots[i].x - 8, this.shots[i].y - 8, this);
                    }
                    else {
                        graphics.setColor(this.missing_image_color);
                        graphics.fillOval(this.shots[i].x - 8, this.shots[i].y - 8, 16, 16);
                    }
                }
            }
        }
    }
    
    void paint_centipede(final Graphics graphics) {
        final boolean checkID = this.tracker.checkID(3, true);
        for (int i = 0; i < this.centipede.length; ++i) {
            if (this.centipede[i].x != 0 || this.centipede[i].y != 0) {
                graphics.clearRect(this.centipede[i].x - 8, this.centipede[i].y - 8, 16, 16);
                final boolean paint_mushroom_collision = this.paint_mushroom_collision(graphics, this.centipede[i], 0);
                final Centipede centipede = this.centipede[i];
                centipede.x += 8 * this.centipede[i].hort_dir;
                boolean b = false;
                if (paint_mushroom_collision) {
                    final Centipede centipede2 = this.centipede[i];
                    centipede2.hort_dir *= -1;
                    final Centipede centipede3 = this.centipede[i];
                    centipede3.x += 16 * this.centipede[i].hort_dir;
                    b = true;
                }
                else if (this.centipede[i].hort_dir > 0 && this.centipede[i].x >= 592) {
                    this.centipede[i].hort_dir = -1;
                    this.centipede[i].x = 592;
                    b = true;
                }
                else if (this.centipede[i].hort_dir < 0 && this.centipede[i].x <= 8) {
                    this.centipede[i].x = 8;
                    this.centipede[i].hort_dir = 1;
                    b = true;
                }
                if (b) {
                    final Centipede centipede4 = this.centipede[i];
                    centipede4.y += 16 * this.centipede[i].vert_dir;
                    if (this.centipede[i].y < 8 || this.centipede[i].y > 408) {
                        final Centipede centipede5 = this.centipede[i];
                        centipede5.vert_dir *= -1;
                        final Centipede centipede6 = this.centipede[i];
                        centipede6.y += 32 * this.centipede[i].vert_dir;
                    }
                }
                if (checkID) {
                    if (i == 0 || (this.centipede[i - 1].x == 0 && this.centipede[i - 1].y == 0)) {
                        graphics.drawImage(this.centipede_images[0], this.centipede[i].x - 8, this.centipede[i].y - 8, this);
                    }
                    else {
                        graphics.drawImage(this.centipede_images[1], this.centipede[i].x - 8, this.centipede[i].y - 8, this);
                    }
                }
                else {
                    graphics.setColor(this.missing_image_color);
                    graphics.fillOval(this.centipede[i].x - 8, this.centipede[i].y - 8, 16, 16);
                }
                this.check_player_collision(this.centipede[i], 8);
            }
        }
    }
    
    void paint_beetles(final Graphics graphics) {
        final boolean checkID = this.tracker.checkID(4, true);
        for (int i = 0; i < this.max_beetles; ++i) {
            if (this.beetles[i].x != 0 || this.beetles[i].y != 0) {
                graphics.clearRect(this.beetles[i].x - 8, this.beetles[i].y - 8, 16, 16);
                this.paint_mushroom_collision(graphics, this.beetles[i], 8);
                if (Math.random() > 0.75) {
                    final Beetle beetle = this.beetles[i];
                    beetle.x += this.beetles[i].hort_dir * 32;
                }
                if (this.beetles[i].x < 0 || this.beetles[i].x > 600) {
                    this.beetles[i].x = 0;
                    this.beetles[i].y = 0;
                }
                else {
                    if (checkID) {
                        if (this.beetles[i].hort_dir == 1) {
                            graphics.drawImage(this.beetle_images[0], this.beetles[i].x - 8, this.beetles[i].y - 8, this);
                        }
                        else {
                            graphics.drawImage(this.beetle_images[1], this.beetles[i].x - 8, this.beetles[i].y - 8, this);
                        }
                    }
                    else {
                        graphics.setColor(this.missing_image_color);
                        graphics.fillOval(this.beetles[i].x - 8, this.beetles[i].y - 8, 16, 16);
                    }
                    this.check_player_collision(this.beetles[i], 8);
                }
            }
        }
    }
    
    void paint_scorpions(final Graphics graphics) {
        final boolean checkID = this.tracker.checkID(5, true);
        for (int i = 0; i < this.max_scorpions; ++i) {
            if (this.scorpions[i].x != 0 || this.scorpions[i].y != 0) {
                graphics.clearRect(this.scorpions[i].x - 16, this.scorpions[i].y - 16, 32, 32);
                this.paint_mushroom_collision(graphics, this.scorpions[i], 16);
                final Scorpion scorpion = this.scorpions[i];
                scorpion.x += this.scorpions[i].hort_dir * 32;
                if (this.scorpions[i].x < 0 || this.scorpions[i].x > 600) {
                    this.scorpions[i].x = 0;
                    this.scorpions[i].y = 0;
                }
                else {
                    if (checkID) {
                        if (this.scorpions[i].hort_dir == 1) {
                            graphics.drawImage(this.scorpion_images[0], this.scorpions[i].x - 16, this.scorpions[i].y - 16, this);
                        }
                        else {
                            graphics.drawImage(this.scorpion_images[1], this.scorpions[i].x - 16, this.scorpions[i].y - 16, this);
                        }
                    }
                    else {
                        graphics.setColor(this.missing_image_color);
                        graphics.fillOval(this.scorpions[i].x - 16, this.scorpions[i].y - 16, 32, 32);
                    }
                    this.check_player_collision(this.scorpions[i], 16);
                }
            }
        }
    }
    
    void paint_spiders(final Graphics graphics) {
        final boolean checkID = this.tracker.checkID(6, true);
        final double random = Math.random();
        final int n = 16;
        for (int i = 0; i < this.max_spiders; ++i) {
            if (this.spiders[i].x != 0 || this.spiders[i].y != 0) {
                graphics.clearRect(this.spiders[i].x - 16, this.spiders[i].y - 16, 32, 32);
                this.paint_mushroom_collision(graphics, this.spiders[i], 16);
                if (this.spiders[i].y < 208 || this.spiders[i].y > 400) {
                    final Spider spider = this.spiders[i];
                    spider.vert_dir *= -1;
                    final Spider spider2 = this.spiders[i];
                    spider2.y += this.spiders[i].vert_dir * n * 2;
                }
                else if (random < 0.1) {
                    final Spider spider3 = this.spiders[i];
                    spider3.hort_dir *= -1;
                    final Spider spider4 = this.spiders[i];
                    spider4.x += this.spiders[i].hort_dir * n;
                }
                else if (random > 0.9) {
                    final Spider spider5 = this.spiders[i];
                    spider5.vert_dir *= -1;
                    final Spider spider6 = this.spiders[i];
                    spider6.y += this.spiders[i].vert_dir * n;
                }
                else {
                    final Spider spider7 = this.spiders[i];
                    spider7.x += this.spiders[i].hort_dir * n;
                }
                if (this.spiders[i].x < 16 || this.spiders[i].x > 584) {
                    if (this.spiders[i].hort_dir != this.spiders[i].start_dir) {
                        this.spiders[i].x = 0;
                        this.spiders[i].y = 0;
                        continue;
                    }
                    final Spider spider8 = this.spiders[i];
                    spider8.hort_dir *= -1;
                    final Spider spider9 = this.spiders[i];
                    spider9.x += this.spiders[i].hort_dir * n * 2;
                }
                if (checkID) {
                    graphics.drawImage(this.spider_image, this.spiders[i].x - 16, this.spiders[i].y - 16, this);
                }
                else {
                    graphics.setColor(this.missing_image_color);
                    graphics.fillOval(this.spiders[i].x - 16, this.spiders[i].y - 16, 32, 32);
                }
                this.check_player_collision(this.spiders[i], 16);
            }
        }
    }
    
    void paint_fleas(final Graphics graphics) {
        final boolean checkID = this.tracker.checkID(6, true);
        for (int i = 0; i < this.max_fleas; ++i) {
            if (this.fleas[i].x != 0 || this.fleas[i].y != 0) {
                graphics.clearRect(this.fleas[i].x - 8, this.fleas[i].y - 8, 16, 16);
                this.paint_mushroom_collision(graphics, this.fleas[i], 8);
                final Point point = this.fleas[i];
                point.y += 16;
                if (this.fleas[i].y > 408) {
                    this.fleas[i].x = 0;
                    this.fleas[i].y = 0;
                }
                else {
                    if (Math.random() < 0.2) {
                        final int generate_mushroom = this.generate_mushroom(this.fleas[i].x, this.fleas[i].y);
                        if (generate_mushroom != -1) {
                            this.paint_mushroom(graphics, generate_mushroom);
                        }
                    }
                    if (checkID) {
                        graphics.drawImage(this.flea_image, this.fleas[i].x - 8, this.fleas[i].y - 8, this);
                    }
                    else {
                        graphics.setColor(this.missing_image_color);
                        graphics.fillOval(this.fleas[i].x - 8, this.fleas[i].y - 8, 16, 16);
                    }
                    this.check_player_collision(this.fleas[i], 8);
                }
            }
        }
    }
    
    void paint_string(final Graphics graphics, final String s) {
        this.big_font_metrics.getHeight();
        final int stringWidth = this.big_font_metrics.stringWidth(s);
        graphics.setColor(Color.black);
        graphics.drawString(s, 300 - stringWidth / 2, 208);
    }
    
    void paint_game_over(final Graphics graphics) {
        this.paint_string(graphics, "GAME OVER");
    }
    
    void paint_paused(final Graphics graphics) {
        this.paint_string(graphics, "PAUSED");
    }
    
    void paint_score_and_level(final Graphics graphics) {
        graphics.clearRect(0, 416, 300, 32);
        graphics.setColor(Color.black);
        graphics.drawLine(0, 416, 600, 416);
        graphics.drawString("Score: " + this.score + "  Level: " + this.level, 0, 416 + this.big_font_metrics.getHeight());
    }
    
    void paint_lives(final Graphics graphics) {
        graphics.clearRect(300, 416, 600, 32);
        graphics.setColor(Color.black);
        graphics.drawLine(0, 416, 600, 416);
        final String string = "" + this.lives;
        final int stringWidth = this.big_font_metrics.stringWidth(string);
        graphics.setColor(Color.black);
        graphics.drawString(string, 600 - stringWidth, 416 + this.big_font_metrics.getHeight());
        graphics.drawImage((this.lives > 0) ? this.player_image : this.dead_player_image, 600 - stringWidth - 32, 416, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.need_reset) {
            this.reset_game();
            this.need_reset = false;
        }
        if (this.clear_screen) {
            graphics.clearRect(0, 0, 600, 448);
            this.clear_screen = false;
        }
        if (this.paused) {
            this.paint_paused(graphics);
            return;
        }
        if (this.update_mushrooms) {
            this.paint_mushrooms(graphics);
            this.update_mushrooms = false;
        }
        this.paint_shots(graphics);
        this.paint_centipede(graphics);
        this.paint_beetles(graphics);
        this.paint_scorpions(graphics);
        this.paint_fleas(graphics);
        this.paint_spiders(graphics);
        this.paint_player(graphics);
        if (this.update_score || this.update_level) {
            this.paint_score_and_level(graphics);
            final boolean b = false;
            this.update_level = b;
            this.update_score = b;
        }
        if (this.update_lives) {
            this.paint_lives(graphics);
            this.update_lives = false;
        }
        if (this.expand_mushrooms) {
            this.paint_mushrooms_expand(graphics);
            this.expand_mushrooms = false;
        }
        if (this.game_over) {
            this.paint_game_over(graphics);
        }
    }
    
    public centipedo() {
        this.missing_image_color = Color.black;
        this.background_colors = new Color[] { Color.white, Color.cyan, Color.yellow, Color.orange, Color.green, Color.pink };
        this.game_over = true;
        this.clear_screen = false;
        this.mouse_down = false;
        this.need_reset = false;
        this.update_mushrooms = true;
        this.update_level = true;
        this.update_score = true;
        this.update_lives = true;
        this.update_player = true;
        this.expand_mushrooms = false;
        this.paused = false;
        this.centipede_length = 12;
        this.max_shots = 5;
        this.max_mushrooms = 40;
        this.max_creature_count = 100;
        this.shot_speed = 40;
    }
}

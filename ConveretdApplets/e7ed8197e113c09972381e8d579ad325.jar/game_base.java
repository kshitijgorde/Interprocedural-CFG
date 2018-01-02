import java.io.IOException;
import java.net.Socket;
import java.io.DataOutputStream;
import java.awt.Color;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class game_base extends Frame
{
    protected static final boolean DEBUG = false;
    protected static final int RED = 0;
    protected static final int BLUE = 1;
    protected static final int GREEN = 2;
    protected static final int YELLOW = 3;
    protected static final int WHITE = 4;
    protected static final int BLACK = 5;
    protected static final Color[] colours;
    protected static final String[] colour_names;
    protected int game_id;
    protected Object mutex;
    private int removed_to_go_pos;
    private DataOutputStream outsock;
    private Socket server;
    protected String our_name;
    private boolean owner;
    private int to_go_id;
    private int ourid;
    private int ownerid;
    private int minplayers;
    private int maxplayers;
    public groupboard gb;
    protected static String GAME_TITLE;
    private int numplayers;
    public player_info[] players;
    
    public int our_id() {
        return this.ourid;
    }
    
    public int num_players() {
        return this.numplayers;
    }
    
    public void set_our_id(final int ourid) {
        this.ourid = ourid;
    }
    
    public void inc_score(final int n) {
        final player_info player_with_id = this.player_with_id(n);
        if (null != player_with_id) {
            final player_info player_info = player_with_id;
            ++player_info.score;
        }
    }
    
    public int get_score(final int n) {
        final player_info player_with_id = this.player_with_id(n);
        if (null != player_with_id) {
            return player_with_id.score;
        }
        return 0;
    }
    
    public player_info us() {
        return this.player_with_id(this.ourid);
    }
    
    public boolean autostart() {
        return true;
    }
    
    public int opponent_id() {
        if (this.numplayers != 2) {
            System.out.println("opponent_id() can only be called in 2 player games!");
            return 0;
        }
        if (this.players[0].id == this.ourid) {
            return this.players[1].id;
        }
        return this.players[0].id;
    }
    
    public player_info opponent() {
        if (this.numplayers != 2) {
            if (this.numplayers > 2) {
                System.out.println("opponent() can only be called in 2 player games!");
            }
            return null;
        }
        if (this.players[0].id == this.ourid) {
            return this.players[1];
        }
        return this.players[0];
    }
    
    public player_info player_with_id(final int n) {
        for (int i = 0; i < this.numplayers; ++i) {
            if (this.players[i].id == n) {
                return this.players[i];
            }
        }
        return null;
    }
    
    public int min_players() {
        return this.minplayers;
    }
    
    public int max_players() {
        return this.maxplayers;
    }
    
    public int owner_id() {
        return this.ownerid;
    }
    
    public void set_owner_id(final int ownerid) {
        this.ownerid = ownerid;
    }
    
    public Color player_colour(final int n) {
        for (int i = 0; i < this.numplayers; ++i) {
            if (this.players[i].id == n) {
                return game_base.colours[this.players[i].colour_id];
            }
        }
        return Color.black;
    }
    
    public int to_go() {
        return this.to_go_id;
    }
    
    public void set_to_go(final int to_go_id) {
        this.to_go_id = to_go_id;
    }
    
    public void next_to_go() {
        if (this.numplayers > 1) {
            for (int i = 0; i < this.numplayers; ++i) {
                if (this.players[i].id == this.to_go_id) {
                    this.to_go_id = this.players[(i + 1) % this.numplayers].id;
                    return;
                }
            }
            if (this.removed_to_go_pos < this.numplayers) {
                this.to_go_id = this.players[this.removed_to_go_pos].id;
            }
            else {
                this.to_go_id = this.players[0].id;
            }
        }
    }
    
    public game_base() {
        this.minplayers = 2;
        this.maxplayers = 2;
        this.numplayers = 0;
        this.mutex = new Object();
    }
    
    public boolean is_owner() {
        return this.ownerid == this.ourid;
    }
    
    protected boolean new_jdk() {
        return this.gb.new_jdk;
    }
    
    void set_os(final DataOutputStream outsock) {
        this.outsock = outsock;
    }
    
    private void data_error() {
    }
    
    protected final void destruct() {
        this.dispose();
        this.gb.base = null;
    }
    
    public final void send_position_update() {
        try {
            synchronized (this.outsock) {
                if (this.numplayers > 1) {
                    final player_info us = this.us();
                    this.outsock.writeByte(137);
                    this.outsock.writeInt(us.x);
                    this.outsock.writeInt(us.y);
                    this.outsock.writeInt(us.z);
                    this.outsock.writeInt(us.rotation);
                }
            }
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_start_game() {
        try {
            this.outsock.writeByte(132);
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_end_game() {
        try {
            this.outsock.writeByte(133);
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_leave_game() {
        try {
            this.outsock.writeByte(134);
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_resign_game() {
        try {
            this.outsock.writeByte(135);
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_restart_game() {
        try {
            this.outsock.writeByte(136);
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_data_packet_to_player(final int n, final int n2, final int n3, final String s) {
        try {
            synchronized (this.outsock) {
                this.outsock.writeByte(170);
                this.outsock.writeInt(n);
                this.outsock.writeByte(n2);
                this.outsock.writeInt(n3);
                this.outsock.writeShort(s.length());
                if (s.length() > 0) {
                    this.outsock.writeBytes(s);
                }
            }
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_data_packet_to_all(final int n, final int n2, final String s) {
        try {
            synchronized (this.outsock) {
                this.outsock.writeByte(171);
                this.outsock.writeByte(n);
                this.outsock.writeInt(n2);
                this.outsock.writeShort(s.length());
                if (s.length() > 0) {
                    this.outsock.writeBytes(s);
                }
            }
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_bytearray_packet_to_player(final int n, final int n2, final byte[] array, final int n3) {
        try {
            synchronized (this.outsock) {
                this.outsock.writeByte(172);
                this.outsock.writeInt(n);
                this.outsock.writeByte(n2);
                this.outsock.writeShort(n3);
                this.outsock.write(array, 0, n3);
            }
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void send_bytearray_packet_to_all(final int n, final byte[] array, final int n2) {
        try {
            synchronized (this.outsock) {
                this.outsock.writeByte(173);
                this.outsock.writeByte(n);
                this.outsock.writeShort(n2);
                this.outsock.write(array, 0, n2);
            }
        }
        catch (IOException ex) {
            this.data_error();
        }
    }
    
    public final void handle_position_update(final int n, final int x, final int y, final int z, final int rotation, final boolean b) {
        int i = 0;
        while (i < this.numplayers) {
            if (this.players[i].id == n) {
                this.players[i].x = x;
                this.players[i].y = y;
                this.players[i].z = z;
                this.players[i].rotation = rotation;
                if (b) {
                    this.send_position_update();
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (i == this.numplayers) {
            System.out.println("handle_position_update: can't find player with id " + n);
        }
        this.game_handle_position_update(n, x, y, z, rotation, b);
    }
    
    public final void handle_init(final DataOutputStream outsock, final int game_id, final String s, final groupboard gb, final int n, final int id, final int n2, final int minplayers, final int maxplayers) {
        this.outsock = outsock;
        this.game_id = game_id;
        this.our_name = s;
        this.gb = gb;
        this.minplayers = minplayers;
        this.maxplayers = maxplayers;
        this.numplayers = 1;
        (this.players = new player_info[this.maxplayers])[0] = new player_info();
        this.players[0].name = s;
        this.players[0].id = id;
        this.players[0].x = 0;
        this.players[0].y = 0;
        this.players[0].z = 0;
        this.players[0].rotation = 0;
        this.players[0].colour_id = 0;
        this.players[0].score = 0;
        this.set_our_id(id);
        this.set_owner_id(n2);
        this.game_handle_init(game_id, s, n, id, n2, this.minplayers, this.maxplayers);
    }
    
    public final void handle_start() {
        this.to_go_id = this.owner_id();
        this.game_handle_start();
        if (this.numplayers < this.minplayers) {
            System.out.println("error: numplayers < minplayers");
            this.dispose();
        }
    }
    
    public final void handle_restart() {
        this.to_go_id = this.owner_id();
        this.game_handle_restart();
    }
    
    public final void handle_end() {
        this.game_handle_end();
    }
    
    public final void handle_new_player(final int id, final String name) {
        if (this.numplayers >= this.maxplayers) {
            System.out.println("handle_new_player: too many players!");
        }
        else {
            final player_info player_info = new player_info();
            player_info.id = id;
            player_info.name = name;
            player_info.x = 0;
            player_info.y = 0;
            player_info.z = 0;
            player_info.rotation = 0;
            player_info.score = 0;
            int n;
            for (n = 0; n < this.numplayers && this.players[n].id < id; ++n) {}
            final int colour_id = n;
            for (int i = this.numplayers - 1; i >= colour_id; --i) {
                this.players[i + 1] = this.players[i];
                this.players[i + 1].colour_id = i + 1;
            }
            this.players[colour_id] = player_info;
            player_info.colour_id = colour_id;
            ++this.numplayers;
        }
        if (this.numplayers == this.maxplayers && this.is_owner() && this.autostart()) {
            this.send_start_game();
        }
        this.game_handle_new_player(id, name);
    }
    
    public final void handle_remove_player(final int n) {
        this.game_handle_remove_player(n);
        int i;
        for (i = 0; i < this.numplayers && this.players[i].id != n; ++i) {}
        if (i < this.numplayers) {
            if (this.to_go_id == n) {
                this.removed_to_go_pos = i;
            }
            while (i < this.numplayers - 1) {
                this.players[i] = this.players[i + 1];
                ++i;
            }
            --this.numplayers;
        }
        else {
            System.out.println("handle_remove_player: can't find player with id " + n);
        }
    }
    
    public final void handle_resign_player(final int n) {
        this.game_handle_resign_player(n);
    }
    
    public final void handle_data_packet(final int n, final int n2, final int n3, final String s) {
        this.game_handle_data_packet(n, n2, n3, s);
    }
    
    public final void handle_bytearray_packet(final int n, final int n2, final byte[] array, final int n3) {
        this.game_handle_bytearray_packet(n, n2, array, n3);
    }
    
    public void game_handle_position_update(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
    }
    
    public void game_handle_bytearray_packet(final int n, final int n2, final byte[] array, final int n3) {
    }
    
    public void game_handle_data_packet(final int n, final int n2, final int n3, final String s) {
    }
    
    public void game_handle_resign_player(final int n) {
    }
    
    public void game_handle_remove_player(final int n) {
    }
    
    public void game_handle_new_player(final int n, final String s) {
    }
    
    public void game_handle_restart() {
    }
    
    public void game_handle_end() {
    }
    
    public void game_handle_start() {
    }
    
    public void game_handle_init(final int n, final String s, final int n2, final int n3, final int n4, final int n5, final int n6) {
    }
    
    static {
        colours = new Color[] { Color.red, Color.blue, new Color(51, 204, 51), Color.yellow, Color.white, Color.black };
        colour_names = new String[] { "Red", "Blue", "Green", "Yellow", "White", "Black" };
        game_base.GAME_TITLE = "Game";
    }
}

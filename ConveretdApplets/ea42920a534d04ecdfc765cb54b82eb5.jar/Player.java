import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Player
{
    String name;
    Color color;
    int[] keymap;
    int[] arsenal;
    int budget;
    String controls;
    int missile;
    int head;
    int vics;
    private static Color[] colors;
    private static int[][] keymaps;
    private String[] controlsa;
    private static String[] names;
    
    static {
        Player.colors = new Color[] { new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255) };
        Player.keymaps = new int[][] { { 37, 39, 40, 38, 10 }, { 65, 68, 83, 87, 32 }, { 74, 76, 75, 73, 32 } };
        Player.names = new String[] { "Dr. Strangelove", "Mr. Bombastic", "Bill", "Boris" };
    }
    
    Player(final int n, final Field field) {
        final int[] arsenal = new int[16];
        arsenal[6] = (arsenal[0] = 20);
        this.arsenal = arsenal;
        this.controlsa = new String[] { "arrow keys, enter", "a, d, s, w, space", "j, k, l, i, space" };
        this.name = Player.names[n % Player.names.length];
        this.color = Player.colors[n % Player.colors.length];
        this.keymap = Player.keymaps[n % Player.keymaps.length];
        this.controls = this.controlsa[n % this.controlsa.length];
        this.budget = (int)(4000000 * field.tpp * 0.75);
    }
}

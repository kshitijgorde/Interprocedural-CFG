// 
// Decompiled by Procyon v0.5.30
// 

final class Skills
{
    public static int skillsCount;
    public static String[] skillNames;
    public static boolean[] skillEnabled;
    
    static {
        Skills.skillsCount = 25;
        Skills.skillNames = new String[] { "attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility", "thieving", "slayer", "farming", "runecraft", "construction", "hunter", "summoning", "dungeoneering" };
        Skills.skillEnabled = new boolean[] { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };
    }
}

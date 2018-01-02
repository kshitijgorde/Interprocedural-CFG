// 
// Decompiled by Procyon v0.5.30
// 

public abstract class game_protocol
{
    static final int MIN_PACKET = 128;
    static final int LIST_GAMES = 128;
    static final int GET_GAME_INFO = 129;
    static final int JOIN_GAME = 130;
    static final int CREATE_GAME = 131;
    static final int START_GAME = 132;
    static final int END_GAME = 133;
    static final int LEAVE_GAME = 134;
    static final int RESIGN_GAME = 135;
    static final int RESTART_GAME = 136;
    static final int POSITION_UPDATE = 137;
    static final int GAME_LIST_ADD = 150;
    static final int GAME_INFO = 151;
    static final int GAME_INIT = 152;
    static final int GAME_START = 153;
    static final int GAME_RESTART = 154;
    static final int GAME_END = 155;
    static final int NEW_PLAYER = 156;
    static final int REMOVE_PLAYER = 157;
    static final int RESIGN_PLAYER = 158;
    static final int WRONG_GAME_PASSWORD = 159;
    static final int POSITION_UPDATES = 160;
    static final int GAME_LIST_REMOVE = 161;
    static final int DATA_PACKET_TO_PLAYER = 170;
    static final int DATA_PACKET_TO_ALL = 171;
    static final int BYTEARRAY_PACKET_TO_PLAYER = 172;
    static final int BYTEARRAY_PACKET_TO_ALL = 173;
    static final int MAX_PACKET = 173;
}

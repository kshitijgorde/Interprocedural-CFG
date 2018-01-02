// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

public enum Command
{
    NONE, 
    EXCEPTION, 
    SESSION_PING, 
    SESSION_CONNECT, 
    SESSION_DISCONNECT, 
    SESSION_LOGIN, 
    SESSION_LOGOUT, 
    LOBBY_GET_GAMELIST, 
    LOBBY_GET_PLAYERTYPELIST, 
    GAME_CREATE, 
    GAME_DESTROY, 
    GAME_JOIN, 
    GAME_LEAVE, 
    GAME_NEXT_TURN, 
    GAME_MAKE_TURN, 
    ADMIN_GAME_FINISH, 
    GAME_CHAT, 
    ADMIN_ACCESS_GRANTED, 
    ADMIN_ACCESS_DENIED, 
    ADMIN_KICK_PLAYER, 
    ADMIN_ADD_GHOST_PLAYER;
}

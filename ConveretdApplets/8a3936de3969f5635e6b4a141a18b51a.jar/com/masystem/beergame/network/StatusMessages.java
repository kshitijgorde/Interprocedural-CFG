// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network;

import com.masystem.beergame.network.protocol.Status;
import java.util.HashMap;

public class StatusMessages
{
    private static HashMap<Status, String> m;
    
    public static String get(final Status status) {
        return StatusMessages.m.get(status);
    }
    
    static {
        (StatusMessages.m = new HashMap<Status, String>()).put(Status.NONE, "None");
        StatusMessages.m.put(Status.OK, "OK");
        StatusMessages.m.put(Status.ERROR_COMMAND_ILLEGAL, "Illegal command");
        StatusMessages.m.put(Status.ERROR_COMMAND_UNKNOWN, "Unknown command");
        StatusMessages.m.put(Status.ERROR_CONNECTION_ABORTED, "Connection aborted by user");
        StatusMessages.m.put(Status.ERROR_CONNECTION_FAILED, "No network connection or server offline");
        StatusMessages.m.put(Status.ERROR_GAME_ALREADY_EXISTS, "Game with name '%s' already exists");
        StatusMessages.m.put(Status.ERROR_GAME_ALREADY_HAS_PLAYER_TYPE, "Game already has a '%s'");
        StatusMessages.m.put(Status.ERROR_GAME_DOES_NOT_EXIST, "Game with name '%s' does not exist");
        StatusMessages.m.put(Status.ERROR_GAME_INVALID_NAME, "Game must have a name");
        StatusMessages.m.put(Status.ERROR_GAME_MUST_BE_SELECTED, "Must choose a game");
        StatusMessages.m.put(Status.ERROR_NOT_AUTHORIZED, "Command not authorized");
        StatusMessages.m.put(Status.ERROR_PLAYER_ALREADY_EXISTS, "The name '%s' is already used");
        StatusMessages.m.put(Status.ERROR_PLAYER_ALREADY_LOGGED_IN, "Player '%s' is already logged in");
        StatusMessages.m.put(Status.ERROR_PLAYER_INVALID_NAME, "Player must have a name");
        StatusMessages.m.put(Status.ERROR_PLAYER_INVALID_TYPE, "No player positions available");
        StatusMessages.m.put(Status.ERROR_PLAYER_NOT_CONNECTED, "Player is not connected");
        StatusMessages.m.put(Status.ERROR_PLAYER_NOT_IN_GAME, "Player is not in a game");
        StatusMessages.m.put(Status.ERROR_PLAYER_NOT_LOGGED_IN, "Player is not logged in");
        StatusMessages.m.put(Status.ERROR_UNKNOWN, "Unknown error");
    }
}

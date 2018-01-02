// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.pws;

import java.util.Random;

final class PwsText
{
    static final String[] emotions;
    static final String[] locations;
    static final String[] people;
    private static final String[] thePathways;
    static final String[] times;
    private static final Random wheel;
    private static int prevPw;
    
    static String getRandomPathway() {
        int thisPw;
        do {
            thisPw = (PwsText.wheel.nextInt() & Integer.MAX_VALUE) % 12;
        } while (thisPw == PwsText.prevPw);
        PwsText.prevPw = thisPw;
        return PwsText.thePathways[thisPw];
    }
    
    static {
        emotions = new String[] { "despair", "sadness", "grief", "fear", "worry", "dread", "embarrassment", "guilt", "loneliness", "boredom", "jealousy", "shame", "frustration", "disgust", "disdain", "hate", "resentment", "anger", "rage" };
        locations = new String[] { "office", "car", "street", "bus", "store", "court", "outdoors", "head", "body", "home", "town", "country", "planet", "solar system", "galaxy", "universe", "other" };
        people = new String[] { "you", "spouse", "lover", "mother", "father", "son", "daughter", "relative", "neighbour", "friend", "teacher", "boss", "official", "famous person", "stranger", "country", "terrorist", "criminal", "street person", "victim", "emailer", "spammer", "netizen", "person on TV", "politician", "other" };
        thePathways = new String[] { "I am freeing myself from security, sensation, and power addictions that make me try to forcefully control situations in my life, and thus destroy my serenity and keeps me from loving myself and others.", "I am discovering how my consciousness dominating addictions create my illusory version of the changing world of people and situations around me.", "I welcome the opportunity (even if painful) that my minute to minute experience offers me to become aware of the addictions I must reprogram to be liberated from my robot-like emotional patterns.", "I always remember that I have everything I need to enjoy my here and now -- unless I am letting my consciousness be dominated by demands and expectations based on the dead past or the imagined future.", "I take full responsibility here and now for everything I experience, for it is my own programming that creates my actions and also influences the reactions of people around me.", "I accept myself completely here and now and consciously experience everything I feel, think, say, and do (including my emotion backed addictions) as a necessary part of my growth into higher consciousness.", "I open myself genuinely to all people by being willing to fully communicate my deepest feelings, since hiding in any degree keep me stuck in my illusion of separateness from other people.", "I feel with loving compassion the problems of others without getting caught up emotionally in their predicaments that are offering them messages they need for their growth.", "I act freely when I am tuned in, centred, and loving, but if possible, I avoid acting when I am emotionally upset and depriving myself of the wisdom that flows from love and expanded consciousness.", "I am continually calming the restless scanning of my rational mind in order to perceive the finer energies that enable me to unitively merge with everything around me.", "I am constantly aware of which of the Seven Centres of Consciousness I am using, and I feel my energy, perceptiveness, love and inner peace growing as I open all of the centres of consciousness.", "I am perceiving everyone, including myself, as an awakening being who is here to claim his or her birthright to the higher consciousness planes of unconditional love and oneness." };
        times = new String[] { "more than a year ago", "more than a month ago", "more than a day ago", "yesterday", "today", "now", "tomorrow", "more that a day in the future", "more than a month in the future", "more than a year in the future" };
        wheel = new Random();
        PwsText.prevPw = -1;
    }
}

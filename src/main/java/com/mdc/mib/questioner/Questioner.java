package com.mdc.mib.questioner;

import org.bukkit.command.CommandSender;

import java.util.HashMap;

/**
 * Created by Main on 11/18/15.
 */
public class Questioner {
    public static void registerQuestion(Question q){
        activeQuestions.put(q.getSender(), q);
    }
    public static void killQuestion(CommandSender s){
        activeQuestions.remove(s);
    }
    public static Question getQuestion(CommandSender s){
        return(activeQuestions.get(s));
    }

    private static HashMap<CommandSender, Question> activeQuestions = new HashMap<CommandSender, Question>();
}

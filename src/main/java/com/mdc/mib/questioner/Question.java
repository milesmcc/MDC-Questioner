package com.mdc.mib.questioner;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by Main on 11/18/15.
 */
public class Question {

    private String question;
    private CommandSender sender;
    public Question(String question, CommandSender sender){
        this.question = question;
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getQuestion() {
        return question;
    }

    public void initiateAnswerRoutine(String[] response){
        StringBuilder builder = new StringBuilder();
        for(String s : response){
            builder.append(s + " ");
        }
        if(validate(builder.toString().trim())) {
            Questioner.killQuestion(sender);
            answer(builder.toString().trim());
        }else{
            sender.sendMessage(getInvalidMessage());
        }
    }

    public void ask(){
        sender.sendMessage(ChatColor.GREEN + getQuestion());
        sender.sendMessage(ChatColor.DARK_GREEN + "Please answer with /resp <response>");
        Questioner.registerQuestion(this);
    }

    public void answer(String response){
        sender.sendMessage(ChatColor.RED + "This questioner was improperly set up. The answer(java.lang.String response) method was not overridden.");
    }


    public boolean validate(String s){
        return true;
    }

    public String getInvalidMessage(){
        return ChatColor.RED + "Invalid response! Please try again.";
    }
}


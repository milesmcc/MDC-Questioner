package com.mdc.mib.questioner;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by com.mdc.mib.questioner.Main on 11/18/15.
 */
public class Main extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("resp")){
            return onResponseCommand(sender, args);
        }else if(label.equalsIgnoreCase("ask")){
            return onAskCommand(sender, args);
        }else{
            sender.sendMessage(ChatColor.RED + "Unknown Command!");
        }
        return true;
    }

    public boolean onResponseCommand(CommandSender sender, String[] args){
        Question question = Questioner.getQuestion(sender);
        if(question == null){
            sender.sendMessage(ChatColor.RED + "You have nothing to respond to!");
            return true;
        }
        question.initiateAnswerRoutine(args);
        return true;
    }

    public boolean onAskCommand(final CommandSender sender, final String[] args){
        if(!sender.hasPermission("questioner.ask")){
            sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
            return true;
        }
        if(args.length < 2){
            sender.sendMessage(ChatColor.RED + "Usage: /ask <player> <question>");
            return true;
        }
        final Player asked = Bukkit.getPlayer(args[0]);
        if(asked == null){
            sender.sendMessage(ChatColor.RED + "Unknown Player!");
            return true;
        }
        StringBuilder question = new StringBuilder();
        for(int i = 1; i < args.length; i++){
            question.append(args[i] + " ");
        }
        new Question(question.toString().trim(), asked){
            @Override
            public void answer(String answer){
                sender.sendMessage(asked.getDisplayName() + ChatColor.GREEN + " responded with: " + ChatColor.DARK_GREEN + answer);
                asked.sendMessage(ChatColor.GREEN + "Your response has been recorded.");
            }
            @Override
            public boolean validate(String s){
                return s.length() > 0;
            }
            @Override
            public String getInvalidMessage() {
                return ChatColor.RED + "Please include a response!";
            }
        }.ask();
        sender.sendMessage(ChatColor.GREEN + "You have asked them. They should respond soon.");
        return true;
    }
}

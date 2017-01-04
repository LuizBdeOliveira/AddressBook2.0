package com.loca.addressbook.userinterface;

import com.loca.addressbook.userinterface.commands.*;

import java.util.ArrayList;


public class HelpMenu {

    private ArrayList<Command> commands;
    private String menu = "";

    public void ListCommands(){
        commands.add(new AddContactCommand());
        commands.add(new DeleteContactCommand());
        commands.add(new SearchCommand());

        commands.add(new HelpCommand());
        commands.add(new ListCommand());
        commands.add(new QuitCommand());

    }

    public ArrayList<Command> getCommands(){
        return commands;
    }
    
    private String formattingHelpMenu(Command command){
        return command.getName() + "\t\t- " + command.getDescription();
    }


    private void build(){
        for (Command c : commands) {
            menu += formattingHelpMenu(c) + "\n";
        }
    }

    public String getMenu(){
        ListCommands();
        commands = getCommands();
        build();
        return menu;
    }
}
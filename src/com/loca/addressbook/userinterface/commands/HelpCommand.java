package com.loca.addressbook.userinterface.commands;

import com.loca.addressbook.exceptions.InvalidCommandParameterException;
import com.loca.addressbook.userinterface.ConsolePrinter;
import com.loca.addressbook.userinterface.HelpMenu;

import java.util.List;

public class HelpCommand implements Command {

    private CommandType commandType = CommandType.HELP;
    private List<String> parameters;
    private ConsolePrinter consolePrinter;
    public final static String NAME = "help";
    public final static String DESCRIPTION = "displaying help menu";
    HelpMenu menu;

    public HelpCommand (ConsolePrinter consolePrinter, List<String> parameters) {
        this.parameters = parameters;
        this.consolePrinter = consolePrinter;
    }

    public HelpCommand(){}

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() throws InvalidCommandParameterException {
        validate();
        menu = new HelpMenu();
        consolePrinter.print(menu.getMenu());

    }

    private void validate() throws InvalidCommandParameterException {
        if (parameters.size() != commandType.getParametersCount()) {
            throw new InvalidCommandParameterException(commandType, parameters);
        }
    }

}

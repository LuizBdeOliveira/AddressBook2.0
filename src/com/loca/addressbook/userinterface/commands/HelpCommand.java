package com.loca.addressbook.userinterface.commands;

import com.loca.addressbook.exceptions.InvalidCommandParameterException;
import com.loca.addressbook.userinterface.ConsolePrinter;

import java.util.List;

public class HelpCommand implements Command {
    private static final String NEW_LINE = "\n";
    private static final String TAB = "\t";
    private CommandType commandType = CommandType.HELP;
    private List<String> parameters;
    private ConsolePrinter consolePrinter;
    public final static String NAME = "help";
    public final static String DESCRIPTION = "displaying help menu";

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
        String helpText = makeHelpText();
        showHelpText(helpText);

    }

    private String makeHelpText() {
        StringBuilder helpText = new StringBuilder(100);
        for (CommandType commandType: CommandType.values()) {
            helpText.append(commandType.getCommandName());
            helpText.append(TAB);
            helpText.append(commandType.getDescription());
            helpText.append(NEW_LINE);
        }
        return helpText.toString();
    }


    private void showHelpText(String helpText) {
        consolePrinter.print(helpText);
    }

    private void validate() throws InvalidCommandParameterException {
        if (parameters.size() != commandType.getParametersCount()) {
            throw new InvalidCommandParameterException(commandType, parameters);
        }
    }

}

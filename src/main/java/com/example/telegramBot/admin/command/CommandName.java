package com.example.telegramBot.admin.command;

public enum CommandName {

    StatComm("statistic");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}

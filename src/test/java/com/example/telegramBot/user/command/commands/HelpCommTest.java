package com.example.telegramBot.user.command.commands;

import org.junit.jupiter.api.DisplayName;

import static com.example.telegramBot.user.command.CommandName.HELP;
import static com.example.telegramBot.user.command.commands.HelpComm.HELP_MESSAGE;


@DisplayName("Unit-level testing for HelpComm")
class HelpCommTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpComm(sendBotMessageService);
    }

}
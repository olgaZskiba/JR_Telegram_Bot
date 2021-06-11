package com.example.telegramBot.user.command.commands;

import org.junit.jupiter.api.DisplayName;

import static com.example.telegramBot.user.command.CommandName.NO;
import static com.example.telegramBot.user.command.commands.NoComm.NO_MESSAGE;


@DisplayName("Unit-level testing for NoComm")
class NoCommTest extends AbstractCommandTest{


    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoComm(sendBotMessageService);
    }
}
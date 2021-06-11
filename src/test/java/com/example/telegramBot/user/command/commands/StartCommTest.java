package com.example.telegramBot.user.command.commands;

import org.junit.jupiter.api.DisplayName;

import static com.example.telegramBot.user.command.CommandName.START;

import static com.example.telegramBot.user.command.commands.StartComm.START_MESSAGE;


@DisplayName("Unit-level testing for StartComm")
class StartCommTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartComm(sendBotMessageService);
    }
}

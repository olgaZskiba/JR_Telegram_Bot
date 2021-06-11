package com.example.telegramBot.user.command.commands;

import org.junit.jupiter.api.DisplayName;

import static com.example.telegramBot.user.command.CommandName.STOP;
import static com.example.telegramBot.user.command.commands.StopComm.STOP_MESSAGE;

@DisplayName("Unit-level testing for StopComm")
class StopCommTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopComm(sendBotMessageService, telegramUserService);
    }
}
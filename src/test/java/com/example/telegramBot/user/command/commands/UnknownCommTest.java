package com.example.telegramBot.user.command.commands;

import org.junit.jupiter.api.DisplayName;

import static com.example.telegramBot.user.command.commands.UnknownComm.UNKNOWN_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Unit-level testing for UnknownComm")
class UnknownCommTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return "/fdgdfgdfgdbd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownComm(sendBotMessageService);
    }
}
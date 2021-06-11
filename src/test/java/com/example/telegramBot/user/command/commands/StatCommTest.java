package com.example.telegramBot.user.command.commands;

import static com.example.telegramBot.user.command.CommandName.STAT;
import static com.example.telegramBot.user.command.commands.StatComm.STAT_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class StatCommTest extends AbstractCommandTest{

        @Override
        String getCommandName() {
            return STAT.getCommandName();
        }

        @Override
        String getCommandMessage() {
            return String.format(STAT_MESSAGE, 0);
        }

        @Override
        Command getCommand() {
            return new StatComm(telegramUserService, sendBotMessageService);
        }
    }


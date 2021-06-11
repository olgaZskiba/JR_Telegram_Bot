package com.example.telegramBot.user.command;

import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.service.TelegramUserService;
import com.example.telegramBot.user.command.commands.Command;
import com.example.telegramBot.user.command.commands.UnknownComm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for UserCommandHandler")
class UserCommandHandlerTest {

    private UserCommandHandler userCommandHandler;


    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        userCommandHandler = new UserCommandHandler(sendBotMessageService, telegramUserService);
    }

    @Test
    void shouldGetAllTheExistingCommands() {
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = userCommandHandler.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownComm.class, command.getClass());
                });
    }

    @Test
    void shouldReturnUnknownCommand(){
        String unknownCommand = "/fgjhdfgdfg";

        Command command = userCommandHandler.retrieveCommand(unknownCommand);
        Assertions.assertEquals(UnknownComm.class, command.getClass());
    }
}
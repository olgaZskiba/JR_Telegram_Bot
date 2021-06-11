package com.example.telegramBot.admin.command;

import com.example.telegramBot.admin.command.commands.Command;
import com.example.telegramBot.admin.command.commands.UnknownComm;
import com.example.telegramBot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import org.telegram.telegrambots.meta.api.objects.Update;


public class AdminCommandHandler {
    private final ImmutableMap<String, Command> commandMap;
    SendBotMessageService sendBotMessageService;
    private final Command unknownComm;

    public AdminCommandHandler(SendBotMessageService sendBotMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()


                .build();

        unknownComm = new UnknownComm(sendBotMessageService);
    }

    public void handle(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String commandIdentifier = update.getMessage().getText();
                retrieveCommand(commandIdentifier).execute(update);
            } else if (update.getMessage().hasContact()) {
                String commandIdentifier = ifTheContact(update.getMessage().getContact().getPhoneNumber());
                retrieveCommand(commandIdentifier).execute(update);
            }
        } else if (update.hasCallbackQuery() && update.getCallbackQuery().getMessage().hasText()) {
            String commandIdentifier = ifTheTest(update.getCallbackQuery().getData().trim());
            retrieveCommand(commandIdentifier).execute(update);
        }
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownComm);
    }

    public String ifTheTest(String str) {
        if (str.startsWith("english")) {
            str = "english";
        }
        return str;
    }

    public String ifTheContact(String str) {
        if (str.startsWith("+")) {
            str = "shared.contact";
        }
        return str;
    }
}

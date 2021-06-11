package com.example.telegramBot.user.command.commands;


import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.user.keyboard.inline.UserInlineKeyboardSource;

import org.telegram.telegrambots.meta.api.objects.Update;


public class TranzzoComm  implements Command{
    private final SendBotMessageService sendBotMessageService;

    UserInlineKeyboardSource userInlineKeyboardSource = new UserInlineKeyboardSource();



    public TranzzoComm(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

    }
}

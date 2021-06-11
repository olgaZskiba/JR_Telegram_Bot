package com.example.telegramBot.user.command.commands;

import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.user.keyboard.inline.UserInlineKeyboardSource;
import com.example.telegramBot.user.keyboard.reply.UserReplyKeyboardSource;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class PaymentMethodComm implements Command{

    private final SendBotMessageService sendBotMessageService;

    UserInlineKeyboardSource userInlineKeyboardSource = new UserInlineKeyboardSource();
    UserReplyKeyboardSource userReplyKeyboardSource = new UserReplyKeyboardSource();

    public final InlineKeyboardMarkup paymentMenu =  userInlineKeyboardSource.getPaymentMethod();
    public final ReplyKeyboardMarkup returnToMainMenu = userReplyKeyboardSource.getReturnToMainMenu();

    public final static String MESSAGE = "Выберите способ оплаты";
    public final static String MESSAGE_MAIN_MENU_BACK = "Если Вам необходимо больше информации, Вы всегда можете вернуться в главное меню";

    public PaymentMethodComm(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

        sendBotMessageService.sendMessage(chatId, MESSAGE, returnToMainMenu);
        sendBotMessageService.sendMessage(chatId, MESSAGE_MAIN_MENU_BACK, paymentMenu);

    }
}

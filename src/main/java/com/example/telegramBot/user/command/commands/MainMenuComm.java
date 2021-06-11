package com.example.telegramBot.user.command.commands;

import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.service.TelegramUserService;
import com.example.telegramBot.user.keyboard.inline.UserInlineKeyboardSource;

import com.example.telegramBot.user.repository.entity.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class MainMenuComm implements Command {

    private final UserInlineKeyboardSource userInlineKeyboardSource = new UserInlineKeyboardSource();
    //private final UserReplyKeyboardSource userReplyKeyboardSource = new UserReplyKeyboardSource();
    //private final static String MAIN_MENU_MESSAGE = "Да выбери уже что-нить \ud83d\ude34 \ud83e\udd28 ))).";
    public final static String MAIN_MENU_MESSAGE = "Здравствуйте, если вы это читаете значит вы решили выучить английски, а мы, WorldLand, поможем Вам в этом!";
    private final ReplyKeyboard mainMenuKeyboard = userInlineKeyboardSource.getMainMenuKeyboard();

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public MainMenuComm(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        //временный вариант получ ения контакта
//        String phoneNumber = update.getMessage().getContact().getPhoneNumber();
//        Integer userID = update.getMessage().getContact().getUserID();
//        contacts.put(userID, phoneNumber);
//
//        String chatId = update.getMessage().getChatId().toString();
//        Integer message_id = update.getMessage().getMessageId();
//        sendBotMessageService.deleteMessage(chatId, message_id);
//        userReplyKeyboardSource.getPhoneNumberReplyKeyboard().getKeyboard().clear();

        String chatId = update.getMessage().getChatId().toString();
//        String name = update.getMessage().getChat().getUserName();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(user -> {
            user.setActive(true);
            telegramUserService.save(user);
        },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
//                    telegramUser.setName(name);
                    telegramUserService.save(telegramUser);
                });


        sendBotMessageService.sendMessage(chatId, MAIN_MENU_MESSAGE, mainMenuKeyboard);


    }
}

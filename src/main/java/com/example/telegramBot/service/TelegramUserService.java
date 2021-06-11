package com.example.telegramBot.service;

import com.example.telegramBot.user.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    void save(TelegramUser telegramUser);

    List<TelegramUser> retrieveAllActiveUser();
    
    Optional<TelegramUser> findByChatId(String chatId);

}

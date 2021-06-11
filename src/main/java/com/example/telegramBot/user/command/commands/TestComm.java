package com.example.telegramBot.user.command.commands;

import com.example.telegramBot.service.SendBotMessageService;
import com.example.telegramBot.user.keyboard.inline.UserInlineKeyboardSource;
import com.example.telegramBot.user.keyboard.reply.UserReplyKeyboardSource;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.example.telegramBot.user.repository.TestAnswerOptions.answers;
import static com.example.telegramBot.user.repository.TestAnswerOptions.numberOfQuestion;
import static com.example.telegramBot.user.repository.TestCorrectAnswers.ANSWERS_ON_QUESTIONS;
import static com.example.telegramBot.user.repository.TestQuestions.TEST_QUESTIONS;


public class TestComm implements Command {

    private final SendBotMessageService sendBotMessageService;


    UserInlineKeyboardSource userInlineKeyboardSource = new UserInlineKeyboardSource();

    private final UserReplyKeyboardSource userReplyKeyboardSource = new UserReplyKeyboardSource();
    public final ReplyKeyboard returnToMainMenu = userReplyKeyboardSource.getReturnToMainMenu();

    public TestComm(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public static ArrayList<Instant> timeArrayListOfAnswers = new ArrayList<>();

    @Override
    public void execute(Update update) {

        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        Integer message_id = update.getCallbackQuery().getMessage().getMessageId();

        if (numberOfQuestion > 0) {
            sendBotMessageService.deleteMessage(chatId, message_id);
        }


        addTheAnswerIntoList(update);

        if (numberOfQuestion < TEST_QUESTIONS.size()) {
            createAndSendNextQuestion(chatId);
            Instant timeLastQuestion = Instant.now();
            timeArrayListOfAnswers.add(timeLastQuestion);

            long seconds = TimeUnit.MINUTES.toSeconds(20);
            Instant twentyMinutesLater = TestComm.timeArrayListOfAnswers.get(0).plusSeconds(seconds);

            if(timeLastQuestion.isAfter(twentyMinutesLater)) {
                sendBotMessageService.sendMessage(chatId, "Time out");
                return;
            }

        } else {
            createAndSendResult(chatId);
        }

    }

    private void createAndSendNextQuestion(String chatId) {
        ReplyKeyboard answers = userInlineKeyboardSource.getAnswerOptionsInlineMarkup(numberOfQuestion);
        String question = TEST_QUESTIONS.get(numberOfQuestion);
        sendBotMessageService.sendMessage(chatId, question, answers);
        numberOfQuestion++;
    }

    private void createAndSendResult(String chatId) {
        int count = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(ANSWERS_ON_QUESTIONS.get(i))) {
                count++;
            }
        }
        int result = count * 100 / TEST_QUESTIONS.size();
        sendBotMessageService.sendMessage(chatId, String.format("Тест пройден. Ваш результат %d %%", result), userInlineKeyboardSource.getFinishAndSend());

    }

    private void addTheAnswerIntoList(Update update) {
        if (!update.getCallbackQuery().getData().equals("english")) {
            answers.add(update.getCallbackQuery().getData().replace("english", ""));
        }
    }


//todo
// 1. добавить верям на выполение теста,
// 2. записать куда-то выбранные ответы, сравнить их с базой правильных - выполнено, но без БД.
// 3. посчитать процент и вывести его на экран вместе с RESALT - выполнено
// 4. отправить на e-mail
// 5. отправить в бот преподавателю.
// 6. добавить кнопку Reply "Вернуться в гланое меню" (вылетает вместе с RESULT)
// 7. убрать баг когда можно нажать на ответ из другого вопоса - выполнено (предыдущие исчезают)
// 8. отредактировать правильные ответы - выполнено


}

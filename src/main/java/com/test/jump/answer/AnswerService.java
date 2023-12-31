package com.test.jump.answer;


import com.test.jump.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.test.jump.user.SiteUser;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);

        return answer;
    }
}

package com.test.jump.question;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import com.test.jump.DataNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import com.test.jump.user.SiteUser;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        }
        else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUser user) {
        Question q = new Question();
        q.setSubject(subject);
        q.setCreateDate(LocalDateTime.now());
        q.setContent(content);
        q.setAuthor(user);
        this.questionRepository.save(q);
    }
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }
}

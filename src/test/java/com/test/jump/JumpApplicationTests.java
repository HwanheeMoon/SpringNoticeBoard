package com.test.jump;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.test.jump.question.Question;
import com.test.jump.question.QuestionRepository;
import com.test.jump.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import com.test.jump.question.QuestionService;
import com.test.jump.user.SiteUser;
import java.util.Optional;


@SpringBootTest
class JumpApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {
		//Optional<SiteUser> siteUser = this.userRepository.findByusername("hwanhee");
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			//this.questionService.create(subject, content, siteUser);
		}
	}
}

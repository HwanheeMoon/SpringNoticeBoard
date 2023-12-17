package com.test.jump.question;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.test.jump.answer.AnswerForm;
import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;
import java.util.UUID;

import com.test.jump.user.UserService;
import com.test.jump.user.SiteUser;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public String questionCreate(@RequestPart(name = "image", required = false) MultipartFile image,@Valid QuestionForm questionForm,
                                 BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        try {
            String imagePath = imageService.saveImage(image, principal.getName());
            SiteUser siteUser = this.userService.getUser(principal.getName());

            this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, imagePath);

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/question/list";
    }



    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/imageupload/api", consumes = "multipart/form-data")
    public String UploadImg(@RequestPart(name = "image") MultipartFile image, Principal principal, @PathVariable("id") Integer id) {
        try {
            Question question = this.questionService.getQuestion(id);

            LocalDateTime now = LocalDateTime.now();
            String newFileName = "IMG" + UUID.randomUUID().toString();
            String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1");
            String username = principal.getName();
            String path = username + "/";

            File file = new File("img_storage", path);
            if (!file.exists()) {
                file.mkdirs();
            }

            file = new File("img_storage", path + newFileName + fileExtension);
            image.transferTo(file);

            this.questionService.plusImg(question, path);
        } catch (Exception e) {
            return "error";
        }

        return "redirect:/question/list";
    }



}

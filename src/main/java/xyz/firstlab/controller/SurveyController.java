package xyz.firstlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.firstlab.survey.AnsweredData;
import xyz.firstlab.survey.Question;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @GetMapping
    public String form(Model model) {
        model.addAttribute("questions", createQuestion());
        return "survey/surveyForm";
    }

    private List<Question> createQuestion() {
        Question q1 = new Question("Your roll",
                                   Arrays.asList("Server developer", "Front-end developer", "Full-stack developer"));
        Question q2 = new Question("Your develop tool", Arrays.asList("Eclipse", "Intellij", "Sublime"));
        Question q3 = new Question("P.S.");
        return Arrays.asList(q1, q2, q3);
    }

    @PostMapping
    public String submit(@ModelAttribute("ansData") AnsweredData data) {
        return "survey/submitted";
    }
}

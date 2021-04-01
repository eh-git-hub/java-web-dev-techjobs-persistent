package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    //TODO: Add private field and @Autowired annotation
    @Autowired
    private SkillRepository skillRepository;

    //TODO: Add index method
    @GetMapping
    public String displayAllSkills(Model model){
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    //Code suggestion by JC
//    @GetMapping("add")
//    public String displayAddSkillForm(Model model) {
//        model.addAttribute("skill", new Skill());
//        return "skills/add";
//    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }
        //TODO: Code to save valid object
        skillRepository.save(newSkill);

        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        //TODO: Replace initialized optSkill with appropriate argument for .findById()
        Optional optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }
}

//Caused by: org.attoparser.ParseException: Exception evaluating SpringEL expression: "'Skill: ' + skill.name" (template: "skills/index" - line 6, col 5)
//Caused by: org.thymeleaf.exceptions.TemplateProcessingException: Exception evaluating SpringEL expression: "'Skill: ' + skill.name" (template: "skills/index" - line 6, col 5)
//Caused by: org.springframework.expression.spel.SpelEvaluationException: EL1008E: Property or field 'name' cannot be found on object of type 'java.util.ArrayList' - maybe not public or not valid?
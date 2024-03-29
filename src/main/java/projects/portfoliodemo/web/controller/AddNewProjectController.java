package projects.portfoliodemo.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projects.portfoliodemo.service.ProjectService;
import projects.portfoliodemo.web.command.CreateProjectCommand;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/add")
@Slf4j @RequiredArgsConstructor
public class AddNewProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String getAddProjectPage(Model model) {
        model.addAttribute("createProjectCommand", new CreateProjectCommand());
        return "project/add";
    }

    @PostMapping
    public String processAddProject(@Valid CreateProjectCommand createProjectCommand,
                                    BindingResult bindings){
        log.debug("Dane do utworzenia projektu: {}", createProjectCommand);
        if(bindings.hasErrors()){
            log.debug("Błędne dane: {}", bindings.getAllErrors());
            return "project/add";
        }

        try {
            projectService.create(createProjectCommand);
            log.debug("Utworzono projekt");
            return "redirect:/projects";
        } catch (RuntimeException re) {
            log.warn(re.getLocalizedMessage());
            log.debug("Błąd podczas tworenia projektu", re);
            bindings.rejectValue(null, null, "Wystąpił błąd");
            return "project/add";
        }
    }
}

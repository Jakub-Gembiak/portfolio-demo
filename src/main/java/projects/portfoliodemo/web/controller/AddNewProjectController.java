package projects.portfoliodemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projects.portfoliodemo.web.command.CreateProjectCommand;

@Controller
@RequestMapping("/project")
public class AddNewProjectController {

    @GetMapping
    public String getProjectAdd(Model model) {
        model.addAttribute("createProjectCommand", new CreateProjectCommand());
        return "project/add";
    }
}

package projects.portfoliodemo.converter;

import org.springframework.stereotype.Component;
import projects.portfoliodemo.domain.model.Project;
import projects.portfoliodemo.web.command.CreateProjectCommand;

@Component
public class ProjectConverter {

    public Project from(CreateProjectCommand createProjectCommand) {
        return Project.builder()
                .name(createProjectCommand.getName())
                .description(createProjectCommand.getDescription())
                .url(createProjectCommand.getUrl())
                .build();
    }
}

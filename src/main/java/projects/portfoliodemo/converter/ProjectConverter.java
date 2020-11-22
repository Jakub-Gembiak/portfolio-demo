package projects.portfoliodemo.converter;

import org.springframework.stereotype.Component;
import projects.portfoliodemo.data.project.ProjectSummary;
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

    public ProjectSummary toProjectSummary(Project project) {
        return ProjectSummary.builder()
                .name(project.getName())
                .url(project.getUrl())
                .description(project.getDescription())
                .username(project.getUser().getUsername())
                .build();
    }
}

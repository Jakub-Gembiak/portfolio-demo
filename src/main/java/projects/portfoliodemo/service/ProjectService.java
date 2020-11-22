package projects.portfoliodemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projects.portfoliodemo.converter.ProjectConverter;
import projects.portfoliodemo.converter.UserConverter;
import projects.portfoliodemo.data.project.ProjectSummary;
import projects.portfoliodemo.domain.model.Project;
import projects.portfoliodemo.domain.model.User;
import projects.portfoliodemo.domain.repositories.ProjectRepository;
import projects.portfoliodemo.domain.repositories.UserRepository;
import projects.portfoliodemo.web.command.CreateProjectCommand;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectConverter projectConverter;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long create(CreateProjectCommand createProjectCommand) {
        log.debug("Dane do utworzenia priejktu: {}", createProjectCommand);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getAuthenticatedUser(username);
        log.debug("Dane pobranego użytkownika:{}", user);

        Project project = projectConverter.from(createProjectCommand);
        project.setUser(user);
        projectRepository.save(project);
        log.debug("Zapisano projekt: {}", project);

        return project.getId();
    }

    @Transactional
    public List<ProjectSummary> findUserProjects() {
        log.debug("Pobieranie informacji o projektach użytkownika");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return projectRepository.findAllByUserUsername(username).stream()
                .map(projectConverter::toProjectSummary)
                .collect(Collectors.toList());
    }
}

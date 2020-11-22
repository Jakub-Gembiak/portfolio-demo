package projects.portfoliodemo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.portfoliodemo.domain.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

package projects.portfoliodemo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.portfoliodemo.domain.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUserUsername(String username);
}

package projects.portfoliodemo.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "projects", uniqueConstraints = @UniqueConstraint(
        name = "projects_name_user_id_idx",
        columnNames = {"name", "user_id"}))
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(exclude = "user")
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String url;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

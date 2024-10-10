package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.ProjectApplication;
import untitled.domain.ProjectCreated;
import untitled.domain.ProjectEdited;
import untitled.domain.ProjectEnded;
import untitled.domain.ProjectStarted;
import untitled.domain.ProjectViewed;

@Entity
@Table(name = "Project_table")
@Data
//<<< DDD / Aggregate Root
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectname;

    private String year;

    private Double scale;

    private String target;

    private String host;

    private String summary;

    private String link;

    private Date startDate;

    private Date endDate;

    private Date expStartDate;

    @PostPersist
    public void onPostPersist() {
        ProjectCreated projectCreated = new ProjectCreated(this);
        projectCreated.publishAfterCommit();

        ProjectStarted projectStarted = new ProjectStarted(this);
        projectStarted.publishAfterCommit();

        ProjectEnded projectEnded = new ProjectEnded(this);
        projectEnded.publishAfterCommit();

        ProjectEdited projectEdited = new ProjectEdited(this);
        projectEdited.publishAfterCommit();

        ProjectViewed projectViewed = new ProjectViewed(this);
        projectViewed.publishAfterCommit();
    }

    public static ProjectRepository repository() {
        ProjectRepository projectRepository = ProjectApplication.applicationContext.getBean(
            ProjectRepository.class
        );
        return projectRepository;
    }
}
//>>> DDD / Aggregate Root

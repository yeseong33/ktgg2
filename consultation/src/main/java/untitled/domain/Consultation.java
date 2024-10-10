package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.ConsultationApplication;
import untitled.domain.ConsultationCreated;
import untitled.domain.ConsultationEdited;
import untitled.domain.ConsultationProgressed;

@Entity
@Table(name = "Consultation_table")
@Data
//<<< DDD / Aggregate Root
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectname;

    private String phone;

    private String customername;

    private String matchedsalesman;

    private Date consultationdate;

    private Long memo;

    private String step;

    @PostPersist
    public void onPostPersist() {
        ConsultationCreated consultationCreated = new ConsultationCreated(this);
        consultationCreated.publishAfterCommit();

        ConsultationEdited consultationEdited = new ConsultationEdited(this);
        consultationEdited.publishAfterCommit();

        ConsultationProgressed consultationProgressed = new ConsultationProgressed(
            this
        );
        consultationProgressed.publishAfterCommit();
    }

    public static ConsultationRepository repository() {
        ConsultationRepository consultationRepository = ConsultationApplication.applicationContext.getBean(
            ConsultationRepository.class
        );
        return consultationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void editSalesman(SalesmanMatched salesmanMatched) {
        //implement business logic here:

        /** Example 1:  new item 
        Consultation consultation = new Consultation();
        repository().save(consultation);

        ConsultationEdited consultationEdited = new ConsultationEdited(consultation);
        consultationEdited.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(salesmanMatched.get???()).ifPresent(consultation->{
            
            consultation // do something
            repository().save(consultation);

            ConsultationEdited consultationEdited = new ConsultationEdited(consultation);
            consultationEdited.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

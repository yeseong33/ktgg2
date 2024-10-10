package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultationProgressed extends AbstractEvent {

    private Long id;
    private String projectname;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private Date consultationdate;
    private Long memo;
    private String step;

    public ConsultationProgressed(Consultation aggregate) {
        super(aggregate);
    }

    public ConsultationProgressed() {
        super();
    }
}
//>>> DDD / Domain Event

package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@AllArgsConstructor
@Getter
public class Milestone {

    private String milestoneName;
    private String milestoneReferences;
    private String milestoneDescription;
    private Date milestoneStartDate;
    private Date milestoneEndDate;
}
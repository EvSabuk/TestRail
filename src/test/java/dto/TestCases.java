package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class TestCases {

    private String testCaseTitle;
    private String testCaseSection;
    private String testCaseTemplate;
    private String testCaseType;
    private String testCasePriority;
    private String testCaseStatus;
    private String testCaseAssignedTo;
    private String testCaseEstimate;
    private String testCaseAutomationType;
    private String testCasePreconditions;
    private List<TestCaseStep> steps;
}
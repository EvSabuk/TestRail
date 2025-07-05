package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    private String testCaseReferences;
    private String testCaseAutomationType;
    private String testCasePreconditions;
    private String testCaseActualResult1;
    private String testCaseExpectedResult1;
    private String testCaseActualResult2;
    private String testCaseExpectedResult2;
}

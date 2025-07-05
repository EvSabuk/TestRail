package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Project {

    private String projectName;
    private String defectViewUrl;
    private String defectAddUrl;
    private String referenceViewUrl;
    private String referenceAddUrl;
    private String announcement;
}
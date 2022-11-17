package api.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponse {
    private Long id;
    @JsonProperty("project_id")
    private String projectId;
    private String content;
    private String description;
    @JsonProperty("is_completed")
    private Boolean isCompleted;
    private String url;
    private DueData due;
}

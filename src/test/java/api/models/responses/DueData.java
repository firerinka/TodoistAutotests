package api.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DueData {
    private String date;
    @JsonProperty("string")
    private String dueString;
    private String lang;
    @JsonProperty("is_recurring")
    private String isRecurring;

}

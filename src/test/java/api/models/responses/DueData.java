package api.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DueData {
    private String date;
    @JsonProperty("string")
    private String dueString;
    private String lang;
    private String is_recurring;

}

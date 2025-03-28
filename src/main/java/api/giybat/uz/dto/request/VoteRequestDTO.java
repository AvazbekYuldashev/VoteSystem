package api.giybat.uz.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequestDTO {
    private Integer questionId;
    private Integer surveyId;
}

package api.giybat.uz.dto.responce;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteDTO {
    private Integer id;
    private Integer profileId;
    private Integer questionId;
    private Integer surveyId;
    private LocalDateTime createdDate;
}
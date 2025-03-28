package api.giybat.uz.dto.responce;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionDTO {
    private Integer id;
    private String text;
    private Integer surveyId;
    private LocalDateTime createdDate;
}
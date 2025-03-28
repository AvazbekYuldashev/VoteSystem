package api.giybat.uz.dto.responce;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SurveyDTO {
    private Integer id;
    private String title;
    private String description;
    private List<QuestionDTO> questions;
    private LocalDateTime createdDate;
}

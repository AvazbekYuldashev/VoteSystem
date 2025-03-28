package api.giybat.uz.service;



import api.giybat.uz.dto.responce.QuestionDTO;
import api.giybat.uz.entity.QuestionEntity;
import api.giybat.uz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionDTO create(QuestionDTO questionDTO) {

        return toDTO(questionRepository.save(toEntity(questionDTO)));
    }

    public QuestionDTO getById(Integer id) {
        Optional<QuestionEntity> optional = questionRepository.findById(id);
        if (optional.isEmpty()){
            return null;
        }
        return toDTO(optional.get());
    }

    public List<QuestionDTO> getAll() {
        List<QuestionEntity> questionEntities = questionRepository.findAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (QuestionEntity questionEntity : questionEntities){
            questionDTOs.add(toDTO(questionEntity));
        }
        return questionDTOs;
    }

    public List<QuestionDTO> getBySurveyId(Integer id) {
        List<QuestionEntity> questions = questionRepository.findBySurveyId(id);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (QuestionEntity questionEntity : questions){
            questionDTOs.add(toDTO(questionEntity));
        }
        return questionDTOs;
    }

    private QuestionDTO toDTO(QuestionEntity entity) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setSurveyId(entity.getSurveyId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private QuestionEntity toEntity(QuestionDTO dto) {
        QuestionEntity entity = new QuestionEntity();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setSurveyId(dto.getSurveyId());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }



}

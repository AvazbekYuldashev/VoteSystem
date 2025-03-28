package api.giybat.uz.service;

import api.giybat.uz.dto.responce.SurveyDTO;
import api.giybat.uz.entity.SurveyEntity;
import api.giybat.uz.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    public SurveyDTO create(SurveyDTO dto){
        return toDTO(surveyRepository.save(toEntity(dto)));
    }

    public SurveyDTO getById(Integer id){
        Optional<SurveyEntity> optional = surveyRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        return toDTO(optional.get());
    }

    public List<SurveyDTO> getAll(){
        List<SurveyEntity> entities = surveyRepository.findAll();
        List<SurveyDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    public Boolean delete(Integer id){
        Optional<SurveyEntity> optional = surveyRepository.findById(id);
        if(optional.isEmpty()){
            return false;
        }
        surveyRepository.delete(optional.get());
        return true;
    }

    private SurveyDTO toDTO(SurveyEntity entity) {
        SurveyDTO dto = new SurveyDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    private SurveyEntity toEntity(SurveyDTO dto) {
        SurveyEntity entity = new SurveyEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}


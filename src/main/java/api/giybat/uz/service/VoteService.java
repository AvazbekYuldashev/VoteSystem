package api.giybat.uz.service;


import api.giybat.uz.dto.responce.VoteDTO;
import api.giybat.uz.entity.VoteEntity;
import api.giybat.uz.exps.AppBadExeption;
import api.giybat.uz.repository.VoteRepository;
import api.giybat.uz.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public VoteDTO create(VoteDTO dto) {
        dto.setProfileId(SpringSecurityUtil.getCurrentUserId());
        if (chekVote(dto)) {throw new AppBadExeption("this user has already voted");}
        return toDTO(voteRepository.save(toEntity(dto)));
    }

    public VoteDTO getById(Integer id) {
        Optional<VoteEntity> optional = voteRepository.findById(id);
        return optional.map(this::toDTO).orElse(null);
    }

    public List<VoteDTO> getAll() {
        List<VoteEntity> entities = voteRepository.findAll();
        List<VoteDTO> dtos = new ArrayList<>();
        for (VoteEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }


    public VoteDTO getBySurveyId(VoteDTO dto) {
        Optional<VoteEntity> optional = voteRepository.findBySurveyId(dto.getSurveyId());
        return optional.map(this::toDTO).orElse(null);
    }

    public void delete(Integer id) {
        Optional<VoteEntity> optional = voteRepository.findById(id);
        if (optional.isEmpty()) {
            return;
        }
        voteRepository.delete(optional.get());
    }


    private Boolean chekVote(VoteDTO dto) {
        ///  Ovoz bermagan bolsa false qaytadi
        Integer userId = dto.getProfileId();
        Integer surveyId = dto.getSurveyId();
        Optional<VoteEntity> optional = voteRepository.findBySurveyIdAndProfileId(surveyId, userId);
        if (optional.isPresent()) {
            return true;
        }
        return false; //TODO
    }

    private VoteDTO toDTO(VoteEntity entity) {
        VoteDTO dto = new VoteDTO();
        dto.setId(entity.getId());
        dto.setProfileId(entity.getProfileId());
        dto.setQuestionId(entity.getQuestionId());
        dto.setSurveyId(entity.getSurveyId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private VoteEntity toEntity(VoteDTO dto) {
        VoteEntity entity = new VoteEntity();
        entity.setId(dto.getId());
        entity.setProfileId(dto.getProfileId());
        entity.setQuestionId(dto.getQuestionId());
        entity.setSurveyId(dto.getSurveyId());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}


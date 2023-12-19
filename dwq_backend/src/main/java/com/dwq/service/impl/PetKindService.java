package com.dwq.service.impl;

import com.dwq.entity.dto.PetKind;
import com.dwq.mapper.PetKindMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetKindService {

    private final PetKindMapper petKindMapper;

    public PetKindService(PetKindMapper petKindMapper) {
        this.petKindMapper = petKindMapper;
    }

    @Transactional
    public void addPetKind(PetKind petKind) {
        petKindMapper.insertPetKind(petKind);
    }

    @Transactional
    public void deletePetKind(Integer kindId) {
        petKindMapper.deletePetKind(kindId);
    }

    @Transactional
    public void updatePetKind(PetKind petKind) {
        petKindMapper.updatePetKind(petKind);
    }

    public PetKind getPetKind(Integer kindId) {
        return petKindMapper.selectPetKindById(kindId);
    }

    public List<PetKind> getAllPetKinds() {
        return petKindMapper.selectAllPetKinds();
    }
}

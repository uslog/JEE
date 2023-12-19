package com.dwq.controller;
import com.dwq.entity.dto.PetKind;
import com.dwq.service.impl.PetKindService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/petKinds")
public class PetKindController {

    private final PetKindService petKindService;

    public PetKindController(PetKindService petKindService) {
        this.petKindService = petKindService;
    }

    @PostMapping
    public void addPetKind(@ModelAttribute PetKind petKind) {
        petKindService.addPetKind(petKind);
    }

    @DeleteMapping("/{id}")
    public void deletePetKind(@PathVariable("id") Integer kindId) {
        petKindService.deletePetKind(kindId);
    }

    @PutMapping
    public void updatePetKind(@ModelAttribute PetKind petKind) {
        petKindService.updatePetKind(petKind);
    }

    @GetMapping("/{id}")
    public PetKind getPetKind(@PathVariable("id") Integer kindId) {
        return petKindService.getPetKind(kindId);
    }

    @GetMapping
    public List<PetKind> getAllPetKinds() {
        return petKindService.getAllPetKinds();
    }
}

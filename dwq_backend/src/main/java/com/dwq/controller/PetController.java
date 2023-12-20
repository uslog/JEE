package com.dwq.controller;

import com.dwq.entity.RestBean;
import com.dwq.entity.dto.Pet;
import com.dwq.service.impl.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/pets")
@Tag(name = "宠物相关", description = "包括宠物添加、删除、修改、查询等操作。")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @Operation(summary = "添加宠物操作")
    public RestBean<Void> addPet(@ModelAttribute Pet pet) {
        if(petService.addPet(pet))
            return RestBean.success();
        else
            return RestBean.failure(409,"添加宠物信息失败");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除宠物操作")
    public RestBean<Void> deletePet(@PathVariable Integer id) {
        if(petService.deletePet(id))
            return RestBean.success();
        else
            return RestBean.failure(409,"删除宠物信息失败");
    }

    @PutMapping
    @Operation(summary = "修改宠物操作")
    public RestBean<Void> updatePet(@ModelAttribute Pet pet) {
        if(petService.updatePet(pet))
            return RestBean.success();
        else
            return RestBean.failure(409,"修改宠物信息失败");
    }


    @GetMapping("/{id}")
    @Operation(summary = "获取单个宠物")
    public RestBean<Pet> getPet(@PathVariable Integer id) {
       return  RestBean.success(petService.getPet(id));
    }

    @GetMapping
    @Operation(summary = "获取所有宠物")
    public RestBean<List<Pet>> getAllPets() {
        return RestBean.success(petService.getAllPets());
    }
}


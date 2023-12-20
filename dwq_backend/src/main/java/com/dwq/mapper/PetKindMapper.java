package com.dwq.mapper;
import com.dwq.entity.dto.PetKind;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetKindMapper {
    @Insert("INSERT INTO dwq_pet_kind (kind_id, kind_name) VALUES (#{kindId}, #{kindName})")
    void insertPetKind(PetKind petKind);

    @Delete("DELETE FROM dwq_pet_kind WHERE kind_id = #{kindId}")
    void deletePetKind(Integer kindId);

    @Update("UPDATE dwq_pet_kind SET kind_name = #{kindName} WHERE kind_id = #{kindId}")
    void updatePetKind(PetKind petKind);

    @Select("SELECT * FROM dwq_pet_kind WHERE kind_id = #{kindId}")
    PetKind selectPetKindById(Integer kindId);

    @Select("SELECT * FROM dwq_pet_kind")
    List<PetKind> selectAllPetKinds();
}

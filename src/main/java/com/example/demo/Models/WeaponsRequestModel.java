package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeaponsRequestModel {

    @Id
    private String id;
    private String name;
    private String numberOfShots;
    private String armourPenetration;
    private List<String> weaponTypeNames;
    private String range;
    private String ballisticSkill;
    private String strength;
    private String damage;

}

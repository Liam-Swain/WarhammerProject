package com.example.demo.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Weapons")
public class WeaponsDocumentModel {

    @Id
    private String id;
    private String weaponName;
    private String numberOfShots;
    private String armourPenetration;
    private List<WeaponsDocumentTypes> weaponRequestTypes;
    private String range;
    private String ballisticSkill;
    private String strength;
    private String damage;
    private String points;
}

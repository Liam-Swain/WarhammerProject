package com.example.demo.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("UnitAbilities")
public class UnitAbilityDocumentModel {
    @Id
    private String id;
    private String abilityName;
    private String abilityDescription;
}

package com.example.demo.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("WargearAbilities")
public class WargearAbilityDocumentModel {
    @Id
    private String id;
    private String wargearAbilityName;
    private String wargearAbilityDescription;

}

package com.example.demo.Documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("WeaponTypes")
public class WeaponsDocumentTypes {

    @Id
    private String id;
    private String name;
    private String description;

}

package com.example.demo.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Keywords")
public class KeywordDocumentModel {
    @Id
    private String id;
    private String keyword;
    private String description;
}

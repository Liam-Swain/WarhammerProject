package com.example.demo.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Reports")
public class FormModel {
    private String catOrDog;
    private String hasTikTok;
    private String marvelOrDC;
    private String playsRoblox;
    private String playsFortnite;
    private String hasSiblings;
    private String iceCreamOrCake;
    private String playsSport;
    private String seenSnow;
    private String favoriteColor;
    private List<String> tempList;
}

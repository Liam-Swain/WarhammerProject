package com.example.demo.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Stats")
public class Stats {
    private String catPercent;
    private String hasTikTokPercent;
    private String marvelPercent;
    private String playsRobloxPercent;
    private String playsFortnitePercent;
    private String hasSiblingsPercent;
    private String icecreamPercent;
    private String playsSportPercent;
    private String seenSnowPercent;
    private List<String> favoriteColorPercent;
}

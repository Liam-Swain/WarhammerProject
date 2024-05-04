package com.example.demo.Schedule;


import com.example.demo.Models.FormModel;
import com.example.demo.Models.Stats;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.StatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    ProjectRepository repository;

    @Autowired
    StatRepository statRepository;

    @Scheduled(fixedRate = 300000)
    public void generateStats(){
        log.info("Running Scheduler");
        Stats statModel = new Stats();
        List<FormModel> forms = repository.findAll();
        int catCounter = 0;
        int tiktokCounter = 0;
        int marvelCounter = 0;
        int robloxCounter = 0;
        int forniteCounter = 0;
        int hasSiblingsCounter = 0;
        int iceCreamCounter = 0;
        int playsSportsCounter = 0;
        int seenSnowCounter = 0;
        List<Integer> favoriteColor = new ArrayList<>();
        favoriteColor.add(0);
        favoriteColor.add(0);
        favoriteColor.add(0);
        favoriteColor.add(0);

        if(forms.size() > 1){
            for(FormModel form : forms){
                if(form.getCatOrDog().equals("cat")){
                    catCounter++;
                }
                if(form.getHasTikTok().equals("yes")){
                    tiktokCounter++;
                }
                if(form.getMarvelOrDC().equals("marvel")){
                    marvelCounter++;
                }
                if(form.getPlaysRoblox().equals("yes")){
                    robloxCounter++;
                }
                if(form.getPlaysFortnite().equals("yes")){
                    forniteCounter++;
                }
                if(form.getHasSiblings().equals("yes")){
                    hasSiblingsCounter++;
                }
                if(form.getIceCreamOrCake().equals("ice cream")){
                    iceCreamCounter++;
                }
                if(form.getPlaysSport().equals("yes")){
                    playsSportsCounter++;
                }
                if(form.getSeenSnow().equals("yes")){
                    seenSnowCounter++;
                }

                if(form.getFavoriteColor().equals("blue")){
                    favoriteColor.set(0, favoriteColor.get(0) + 1);
                }
                if(form.getFavoriteColor().equals("green")){
                    favoriteColor.set(1, favoriteColor.get(1) + 1);
                }
                if(form.getFavoriteColor().equals("red")){
                    favoriteColor.set(2, favoriteColor.get(2) + 1);
                }
                if(form.getFavoriteColor().equals("purple")){
                    favoriteColor.set(3, favoriteColor.get(3) + 1);
                }
            }


                double catPercent = (double) catCounter / forms.size();
                double tikTokPercent = (double) tiktokCounter / forms.size();
                double marvelPercent = (double) marvelCounter / forms.size();
                double robloxPercent = (double) robloxCounter / forms.size();
                double fortnitePercent = (double) forniteCounter / forms.size();
                double siblingsPercent = (double) hasSiblingsCounter / forms.size();
                double iceCreamPercent = (double) iceCreamCounter / forms.size();
                double snowPercent = (double) seenSnowCounter / forms.size();
                double playsSportsPercent = (double) playsSportsCounter / forms.size();

                List<Double> colorPercent = new ArrayList<>();
                colorPercent.add((double) favoriteColor.get(0) / forms.size());
                colorPercent.add((double) favoriteColor.get(1) / forms.size());
                colorPercent.add((double) favoriteColor.get(2) / forms.size());
                colorPercent.add((double) favoriteColor.get(3) / forms.size());

                statModel.setCatPercent(String.valueOf(catPercent));
                statModel.setHasTikTokPercent(String.valueOf(tikTokPercent));
                statModel.setMarvelPercent(String.valueOf(marvelPercent));
                statModel.setPlaysRobloxPercent(String.valueOf(robloxPercent));
                statModel.setPlaysFortnitePercent(String.valueOf(fortnitePercent));
                statModel.setHasSiblingsPercent(String.valueOf(siblingsPercent));
                statModel.setIcecreamPercent(String.valueOf(iceCreamPercent));
                statModel.setSeenSnowPercent(String.valueOf(snowPercent));
                statModel.setPlaysSportPercent(String.valueOf(playsSportsPercent));

                List<String> stringColorPercent = new ArrayList<>();
                stringColorPercent.add(String.valueOf(colorPercent.get(0)));
                stringColorPercent.add(String.valueOf(colorPercent.get(1)));
                stringColorPercent.add(String.valueOf(colorPercent.get(2)));
                stringColorPercent.add(String.valueOf(colorPercent.get(3)));

                statModel.setFavoriteColorPercent(stringColorPercent);

                statRepository.deleteAll();
                statRepository.save(statModel);
        }


    }

}

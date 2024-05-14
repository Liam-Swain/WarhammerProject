package com.example.demo.ServiceImpl;

import com.example.demo.Documents.*;
import com.example.demo.Models.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.WarhammerService;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class WarhammerServiceImpl implements WarhammerService {

    @Autowired
    WeaponTypeRepository weaponTypeRepository;

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    CoreAbilityRepository coreAbilityRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    FactionAbilityRepository factionAbilityRepository;

    @Autowired
    UnitAbilityRepository unitAbilityRepository;
    /*

    *** We Should Build an Adapter Class to convert our request to documents ***

     */


    @Override
    public HttpResponseModel UploadWeapon(WeaponsRequestModel weapon) {
        HttpResponseModel responseModel = ValidateWeapon(weapon);
        if(responseModel == null){
            try{
                log.info("Saving Weapon...");
                WeaponsDocumentModel weaponDocument = BuildWeaponDocument(weapon);
                if(weaponDocument != null){
                    weaponRepository.save(weaponDocument);
                    log.info("Done Saving Weapon...");
                    responseModel = new HttpResponseModel("Success", HttpStatus.OK);
                }
                else{
                    log.info("Error Creating Document");
                    responseModel = new HttpResponseModel("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }catch (MongoException e){
                log.info("Error: " + e.getMessage());
                responseModel = new HttpResponseModel(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return responseModel;

    }

    private WeaponsDocumentModel BuildWeaponDocument(WeaponsRequestModel weaponsRequestModel){
        WeaponsDocumentModel weapon = new WeaponsDocumentModel();
        //weapon.setId(new ObjectId().toString());
        weapon.setWeaponName(weaponsRequestModel.getWeaponName());
        weapon.setDamage(weaponsRequestModel.getDamage());
        weapon.setPoints(weaponsRequestModel.getPoints());
        weapon.setRange(weaponsRequestModel.getRange());
        weapon.setStrength(weaponsRequestModel.getStrength());
        weapon.setArmourPenetration(weaponsRequestModel.getArmourPenetration());
        weapon.setBallisticSkill(weaponsRequestModel.getBallisticSkill());
        weapon.setNumberOfShots(weaponsRequestModel.getNumberOfShots());

        List<WeaponsDocumentTypes> weaponTypes = new ArrayList<>();

        for(String weaponType: weaponsRequestModel.getWeaponTypeNames()){
            try{
                WeaponsDocumentTypes type = weaponTypeRepository.findByName(weaponType);
                weaponTypes.add(type);
            }catch(MongoException e){
                log.info("Error" + e.getMessage());
                return null;
            }
        }

        weapon.setWeaponRequestTypes(weaponTypes);
        return weapon;

    }

    private HttpResponseModel ValidateWeapon(WeaponsRequestModel weaponsRequestModel){
        HttpResponseModel response = null;
        String responseMessage = "";
        if(weaponsRequestModel.getWeaponName().isEmpty()){
            responseMessage += "Weapon Name is Empty; ";
        }
        if(weaponsRequestModel.getRange().isEmpty()){
            responseMessage += "Weapon Range Is Empty; ";
        }
        if(weaponsRequestModel.getArmourPenetration().isEmpty()){
            responseMessage += "Weapon Armor Penetration Is Empty; ";
        }
        if(weaponsRequestModel.getStrength().isEmpty()){
            responseMessage += "Weapon Strength Is Empty; ";
        }
        if(weaponsRequestModel.getDamage().isEmpty()){
            responseMessage += "Weapon Damage is Empty; ";
        }
        if(weaponsRequestModel.getBallisticSkill().isEmpty()){
            responseMessage += "Weapon Ballistic Skill is Empty; ";
        }
        if(weaponsRequestModel.getNumberOfShots().isEmpty()){
            responseMessage += "Weapon Number of Shots is Empty; ";
        }
        if(weaponsRequestModel.getPoints().isEmpty()){
            responseMessage += "Weapon Points is Empty; ";
        }
        if(weaponsRequestModel.getWeaponTypeNames().isEmpty()){
            responseMessage += "Weapon Type Names is Empty; ";
        }


        if(responseMessage.isEmpty()){
            WeaponsDocumentModel weaponsDocumentModel = weaponRepository.findOneByWeaponName(weaponsRequestModel.getWeaponName());
            if(weaponsDocumentModel != null){
                responseMessage = "Weapon with this name already exist";
            }
        }

        if(!responseMessage.isEmpty()){
            response = new HttpResponseModel(responseMessage, HttpStatus.BAD_REQUEST);
        }

        return response;

    }

    @Override
    public HttpResponseModel UploadWeaponType(WeaponRequestTypes weaponRequestTypes) {
        HttpResponseModel response = ValidateWeaponType(weaponRequestTypes);

        if(response == null){
            try{
                log.info("Trying To Save Weapon Type");
                weaponTypeRepository.save(BuildWeaponTypeDocument(weaponRequestTypes));
                log.info("Weapon Type Saved");
                response = new HttpResponseModel("Success", HttpStatus.OK);
            }catch(MongoException e){
                log.info("Error: " + e.getMessage());
                response = new HttpResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return response;
    }

    @Override
    public HttpResponseModel UploadKeyword(KeywordRequestModel keywordRequestModel) {
        HttpResponseModel response = ValidateKeyword(keywordRequestModel);
        if(response == null){
            try {
                log.info("Trying to save Keyword");
                keywordRepository.save(BuildKeywordDocument(keywordRequestModel));
                log.info("Keyword saved");
                response = new HttpResponseModel("Success", HttpStatus.OK);
            }catch(MongoException e){
                log.info("Error: " + e.getMessage());
                response = new HttpResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    private HttpResponseModel ValidateKeyword(KeywordRequestModel keywordRequestModel){
        HttpResponseModel response = null;
        String responseMessage = "";
        if(keywordRequestModel.getKeyword().isEmpty()){
            responseMessage += "Keyword is empty; ";
        }
        if(keywordRequestModel.getDescription().isEmpty()){
            responseMessage += "Description is empty; ";
        }

        if(responseMessage.isEmpty()){
            KeywordDocumentModel keywordDocumentModel = keywordRepository.findOneByKeyword(keywordRequestModel.getKeyword());
            if(keywordDocumentModel != null){
                responseMessage = "Keyword with this name already exists";
            }
        }

        if(!responseMessage.isEmpty()){
            response = new HttpResponseModel(responseMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    private KeywordDocumentModel BuildKeywordDocument(KeywordRequestModel keywordRequestModel){
        KeywordDocumentModel keywordDocumentModel = new KeywordDocumentModel();
        //keywordDocumentModel.setId(new ObjectId().toString());
        keywordDocumentModel.setKeyword(keywordRequestModel.getKeyword());
        keywordDocumentModel.setDescription(keywordRequestModel.getDescription());
        return keywordDocumentModel;
    }

    @Override
    public HttpResponseModel UploadCoreAbility(CoreAbilityRequestModel coreAbilityRequestModel) {
        HttpResponseModel response = ValidateCoreAbility(coreAbilityRequestModel);
        if(response == null){
            try {
                log.info("Trying to save Core Ability");
                coreAbilityRepository.save(BuildCoreAbilityDocument(coreAbilityRequestModel));
                log.info("Core Ability Saved");
                response = new HttpResponseModel("Success", HttpStatus.OK);
            }catch(MongoException e){
                log.info("Error: " + e.getMessage());
                response = new HttpResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    private HttpResponseModel ValidateCoreAbility(CoreAbilityRequestModel coreAbilityRequestModel) {
        HttpResponseModel response = null;
        String responseMessage = "";

        if(coreAbilityRequestModel.getName().isEmpty()){
            responseMessage += "Core Ability name is empty; ";
        }
        if(coreAbilityRequestModel.getDescription().isEmpty()){
            responseMessage += "Core Ability description is empty; ";
        }

        if(responseMessage.isEmpty()) {
            CoreAbilityDocumentModel coreAbilityDocumentModel = coreAbilityRepository.findOneByName(coreAbilityRequestModel.getName());
            if(coreAbilityDocumentModel != null){
                responseMessage += "Core Ability with this name already exists.";
            }
        }
        if(!responseMessage.isEmpty()){
            response = new HttpResponseModel(responseMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    private CoreAbilityDocumentModel BuildCoreAbilityDocument(CoreAbilityRequestModel coreAbilityRequestModel){
        CoreAbilityDocumentModel coreAbilityDocumentModel = new CoreAbilityDocumentModel();
        //coreAbilityDocumentModel.setId(new ObjectId().toString());
        coreAbilityDocumentModel.setName(coreAbilityRequestModel.getName());
        coreAbilityDocumentModel.setDescription(coreAbilityRequestModel.getDescription());
        return coreAbilityDocumentModel;
    }

    private WeaponsDocumentTypes BuildWeaponTypeDocument(WeaponRequestTypes weaponRequestTypes){
        WeaponsDocumentTypes weaponsDocumentTypes = new WeaponsDocumentTypes();
        //weaponsDocumentTypes.setId(new ObjectId().toString());
        weaponsDocumentTypes.setName(weaponRequestTypes.getName());
        weaponsDocumentTypes.setDescription(weaponRequestTypes.getDescription());
        return weaponsDocumentTypes;
    }

    private HttpResponseModel ValidateWeaponType(WeaponRequestTypes weaponType){
        HttpResponseModel httpResponseModel = null;
        String responseMessage = "";
        if(weaponType.getName().isEmpty()){
            responseMessage += "Weapon Type Name is Empty; ";
        }
        if(weaponType.getDescription().isEmpty()){
            responseMessage += "Weapon Type Description is Empty; ";
        }

        if(responseMessage.isEmpty()){
            WeaponsDocumentTypes weaponTypeDocumentModel = weaponTypeRepository.findByName(weaponType.getName());
            if(weaponTypeDocumentModel != null){
                responseMessage = "Weapon Type with this name already exist";
            }
        }
        if(!responseMessage.isEmpty()){
            httpResponseModel = new HttpResponseModel(responseMessage, HttpStatus.BAD_REQUEST);
        }

        return httpResponseModel;
    }

    @Override
    public HttpResponseModel UploadFactionAbility(FactionAbilityRequestModel factionAbilityRequestModel){
        HttpResponseModel response = ValidateFactionAbility(factionAbilityRequestModel);
        if(response == null){
            try{
                log.info("Trying to save Faction Ability");
                factionAbilityRepository.save(BuildFactionAbility(factionAbilityRequestModel));
                log.info("Faction Ability saved");
                response = new HttpResponseModel("Success", HttpStatus.OK);
            }catch(MongoException e){
                log.info("Error " + e.getMessage());
                response = new HttpResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    private FactionAbilityDocumentModel BuildFactionAbility(FactionAbilityRequestModel factionAbilityRequestModel){
        FactionAbilityDocumentModel factionAbilityDocumentModel = new FactionAbilityDocumentModel();
        //factionAbilityDocumentModel.setId(new ObjectId().toString());
        factionAbilityDocumentModel.setFactionAbility(factionAbilityRequestModel.getFactionAbility());
        factionAbilityDocumentModel.setDescription(factionAbilityRequestModel.getDescription());
        return factionAbilityDocumentModel;
    }

    private HttpResponseModel ValidateFactionAbility(FactionAbilityRequestModel factionAbilityRequestModel){
        HttpResponseModel httpResponseModel = null;
        String responseMessage = "";
        if(factionAbilityRequestModel.getFactionAbility().isEmpty()){
            responseMessage += "Missing Faction Ability Name; ";
        }
        if(factionAbilityRequestModel.getDescription().isEmpty()){
            responseMessage += "Missing Faction Ability Description; ";
        }
        if(responseMessage.isEmpty()){
            FactionAbilityDocumentModel factionAbilityDocumentModel = factionAbilityRepository.findOneByFactionAbility(factionAbilityRequestModel.getFactionAbility());
            if(factionAbilityDocumentModel != null){
                responseMessage += "Faction Ability with this name already exists; ";
            }
        }
        if(!responseMessage.isEmpty()){
            httpResponseModel = new HttpResponseModel(responseMessage, HttpStatus.BAD_REQUEST);
        }
        return httpResponseModel;
    }

    @Override
    public HttpResponseModel UploadUnitAbility(UnitAbilityRequestModel unitAbilityRequestModel){
        HttpResponseModel response = ValidateUnitAbility(unitAbilityRequestModel);
        if(response == null){
            try{
                log.info("Trying to save Unit Ability");
                unitAbilityRepository.save(BuildUnitAbility(unitAbilityRequestModel));
                log.info("Unit Ability Saved");
                response = new HttpResponseModel("Success", HttpStatus.OK);
            }catch(MongoException e){
                log.info("Error " + e.getMessage());
                response = new HttpResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    private UnitAbilityDocumentModel BuildUnitAbility(UnitAbilityRequestModel unitAbilityRequestModel){
        UnitAbilityDocumentModel unitAbilityDocumentModel = new UnitAbilityDocumentModel();
        unitAbilityDocumentModel.setAbilityName(unitAbilityRequestModel.getAbilityName());
        unitAbilityDocumentModel.setAbilityDescription(unitAbilityRequestModel.getAbilityDescription());
        return unitAbilityDocumentModel;
    }

    private HttpResponseModel ValidateUnitAbility(UnitAbilityRequestModel unitAbilityRequestModel){
        HttpResponseModel httpResponseModel = null;
        String responseMessage = "";
        if(unitAbilityRequestModel.getAbilityName().isEmpty()){
            responseMessage += "Missing Unit Ability Name; ";
        }
        if(unitAbilityRequestModel.getAbilityDescription().isEmpty()){
            responseMessage += "Missing Unit Ability Description; ";
        }
        if(responseMessage.isEmpty()){
            UnitAbilityDocumentModel unitAbilityDocumentModel = unitAbilityRepository.findOneByAbilityName(unitAbilityRequestModel.getAbilityName());
            if(unitAbilityDocumentModel != null){
                responseMessage += "Unit Ability with this name already exists; ";
            }
        }
        if(!responseMessage.isEmpty()){
            httpResponseModel = new HttpResponseModel(responseMessage, HttpStatus.BAD_REQUEST);
        }
        return httpResponseModel;
    }
}

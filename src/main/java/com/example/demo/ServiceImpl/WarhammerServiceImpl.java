package com.example.demo.ServiceImpl;

import com.example.demo.Models.*;
import com.example.demo.Repository.WeaponRepository;
import com.example.demo.Repository.WeaponTypeRepository;
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
        weapon.setId(new ObjectId().toString());
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
        /*
        Need to do rest of cases
         */

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

    private WeaponsDocumentTypes BuildWeaponTypeDocument(WeaponRequestTypes weaponRequestTypes){
        WeaponsDocumentTypes weaponsDocumentTypes = new WeaponsDocumentTypes();
        weaponsDocumentTypes.setId(new ObjectId().toString());
        weaponsDocumentTypes.setName(weaponRequestTypes.getName());
        weaponsDocumentTypes.setDescription(weaponRequestTypes.getDescription());
        return weaponsDocumentTypes;
    }

    private HttpResponseModel ValidateWeaponType(WeaponRequestTypes weaponType){
        HttpResponseModel httpResponseModel = null;

        if(weaponType.getName().isEmpty()){
            httpResponseModel = new HttpResponseModel("Missing Weapon Name", HttpStatus.BAD_REQUEST);
        }
        if(weaponType.getDescription().isEmpty()){
            httpResponseModel = new HttpResponseModel("Missing Weapon Description", HttpStatus.BAD_REQUEST);
        }
        return httpResponseModel;
    }


}

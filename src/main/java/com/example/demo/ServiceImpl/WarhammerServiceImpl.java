package com.example.demo.ServiceImpl;

import com.example.demo.Models.HttpResponseModel;
import com.example.demo.Models.WeaponRequestTypes;
import com.example.demo.Models.WeaponsDocumentModel;
import com.example.demo.Models.WeaponsDocumentTypes;
import com.example.demo.Repository.WeaponTypeRepository;
import com.example.demo.Service.WarhammerService;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class WarhammerServiceImpl implements WarhammerService {

    @Autowired
    WeaponTypeRepository weaponTypeRepository;

    @Override
    public HttpResponseModel UploadWeapon(WeaponsDocumentModel weapon) {
        return null;
    }

    @Override
    public HttpResponseModel UploadWeaponType(WeaponRequestTypes weaponRequestTypes) {
        HttpResponseModel response = ValidateWeaponType(weaponRequestTypes);

        if(response == null){
            try{
                log.info("Trying To Save Weapon Type");
                weaponTypeRepository.save(BuildWeaponsDocument(weaponRequestTypes));
                log.info("Weapon Type Saved");
                response = new HttpResponseModel("Success", HttpStatus.OK);
            }catch(MongoException e){
                log.info("Error: " + e.getMessage());
                response = new HttpResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return response;
    }

    private WeaponsDocumentTypes BuildWeaponsDocument(WeaponRequestTypes weaponRequestTypes){
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

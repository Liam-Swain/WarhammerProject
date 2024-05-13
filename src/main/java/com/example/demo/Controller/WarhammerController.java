package com.example.demo.Controller;


import com.example.demo.Models.CoreAbilityRequestModel;
import com.example.demo.Models.HttpResponseModel;
import com.example.demo.Models.WeaponRequestTypes;
import com.example.demo.Models.WeaponsRequestModel;
import com.example.demo.Service.WarhammerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class WarhammerController {

    @Autowired
    WarhammerService warhammerService;

    @PostMapping("/PostWeapons")
    public HttpResponseModel UploadWeapons(@RequestBody WeaponsRequestModel weapon){
        return warhammerService.UploadWeapon(weapon);
    }

    @PostMapping("/WeaponType")
    public HttpResponseModel UploadWeaponType(@RequestBody WeaponRequestTypes weaponType){
        log.info("Entering Weapon Type Upload");
        HttpResponseModel response = warhammerService.UploadWeaponType(weaponType);
        log.info("Exiting Weapon Type Upload");
        return response;
    }

    @PostMapping("/CoreAbilities")
    public HttpResponseModel UploadCoreAbility(@RequestBody CoreAbilityRequestModel coreAbilityRequestModel){
        log.info("Entering Core Ability Upload");
        HttpResponseModel response = warhammerService.UploadCoreAbility((coreAbilityRequestModel));
        log.info("Exiting Core Ability Upload");
        return response;
    }



    
}

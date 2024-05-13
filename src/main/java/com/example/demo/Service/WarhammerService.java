package com.example.demo.Service;

import com.example.demo.Models.CoreAbilityRequestModel;
import com.example.demo.Models.HttpResponseModel;
import com.example.demo.Models.WeaponRequestTypes;
import com.example.demo.Models.WeaponsRequestModel;

public interface WarhammerService {



    public HttpResponseModel UploadWeapon(WeaponsRequestModel weapon);

    public HttpResponseModel UploadWeaponType(WeaponRequestTypes weaponRequestTypes);

    public HttpResponseModel UploadCoreAbility(CoreAbilityRequestModel coreAbilityRequestModel);
}

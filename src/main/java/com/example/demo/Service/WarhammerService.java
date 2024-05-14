package com.example.demo.Service;

import com.example.demo.Models.*;

public interface WarhammerService {



    public HttpResponseModel UploadWeapon(WeaponsRequestModel weapon);

    public HttpResponseModel UploadWeaponType(WeaponRequestTypes weaponRequestTypes);

    HttpResponseModel UploadKeyword(KeywordRequestModel keywordRequestModel);

    public HttpResponseModel UploadCoreAbility(CoreAbilityRequestModel coreAbilityRequestModel);

    HttpResponseModel UploadFactionAbility(FactionAbilityRequestModel factionAbilityRequestModel);

    HttpResponseModel UploadUnitAbility(UnitAbilityRequestModel unitAbilityRequestModel);
}

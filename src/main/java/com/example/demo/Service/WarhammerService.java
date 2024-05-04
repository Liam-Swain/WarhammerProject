package com.example.demo.Service;

import com.example.demo.Models.HttpResponseModel;
import com.example.demo.Models.WeaponRequestTypes;
import com.example.demo.Models.WeaponsDocumentModel;

public interface WarhammerService {



    public HttpResponseModel UploadWeapon(WeaponsDocumentModel weapon);

    public HttpResponseModel UploadWeaponType(WeaponRequestTypes weaponRequestTypes);

}

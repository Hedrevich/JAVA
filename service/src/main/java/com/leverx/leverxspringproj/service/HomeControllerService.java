package com.leverx.leverxspringproj.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.leverx.leverxspringproj.domain.Destination;
import com.sap.cloud.sdk.cloudplatform.security.AuthToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Map;
import java.util.Optional;




@Service
public class HomeControllerService {

    private static final String HANATRIAL = "hanatrial";
    private static final String SCHEMA = "schema";
    private static final String CREDENTIALS = "credentials";
    private static final String SPACE_NAME = "space_name";
    private static final String FINAL_NAME = "given_name";
    private static final String FAMILY_NAME = "family_name";

    public Model setAttributes(Model model, CloudService cloudService){

        String appName = cloudService.getApplicationName();
        Map<String, JsonElement> vcap = cloudService.getSpaceName();
        JsonElement vc = vcap.get(SPACE_NAME);
        JsonArray hanatrial = cloudService.getSchemaName().get(HANATRIAL);
        JsonElement schema = hanatrial.get(0).getAsJsonObject().get(CREDENTIALS).getAsJsonObject().get(SCHEMA);
        model.addAttribute("VCAP",vc.toString());
        model.addAttribute("appName", appName);
        model.addAttribute("schema", schema);
        List<Destination> destinations = cloudService.getDestinations();
        model.addAttribute("destinations", destinations);

        return model;
    }

    public Model parseJWT(Model model, CloudService cloudService){

        Optional<AuthToken> token = cloudService.getCurrToken();
        JsonObject jo = cloudService.getInfo(token);
        JsonElement name = jo.get(FINAL_NAME);
        JsonElement familyName = jo.get(FAMILY_NAME);
        model.addAttribute("token", jo);
        model.addAttribute("name", name);
        model.addAttribute("familyname", familyName);

        return model;
    }

}

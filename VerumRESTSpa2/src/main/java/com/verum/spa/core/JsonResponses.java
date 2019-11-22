package com.verum.spa.core;

import com.google.gson.Gson;

public class JsonResponses {

    public static String jsonResponse(boolean value) {
        Gson gson = new Gson();
        String response = "";
        if (value) {
            response = gson.toJson("Success");
            return response;
        } else {
            response = gson.toJson("Fail");
            return response;
        }
    }
}

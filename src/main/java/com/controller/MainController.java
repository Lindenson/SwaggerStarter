package com.controller;

import api.PetsApi;
import model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController implements PetsApi {

    @Override
    public ResponseEntity<Void> createPets() {
        return null;
    }

    @Override
    public ResponseEntity<Pets> listPets(Integer limit) {
        return null;
    }

    @Override
    public ResponseEntity<Pet> showPetById(String petId) {
        return null;
    }
}
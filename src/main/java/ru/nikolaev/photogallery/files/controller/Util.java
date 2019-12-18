package ru.nikolaev.photogallery.files.controller;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class Util {

   private Map<String, Long> stringLongMap = new HashMap<>();

}

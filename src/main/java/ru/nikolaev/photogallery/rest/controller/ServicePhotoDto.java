package ru.nikolaev.photogallery.rest.controller;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class ServicePhotoDto {

   private String aDate;
   private Double discount;
   private Long serviceId;

}

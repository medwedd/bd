package ru.nikolaev.photogallery.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class ClientDto {

   private Long id;
   private String name;
   private String email;
   private String phone;

}

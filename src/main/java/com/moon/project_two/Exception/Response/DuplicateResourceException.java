package com.moon.project_two.Exception.Response;


public class DuplicateResourceException extends RuntimeException{

   public DuplicateResourceException(String message){
        super(message);
   }
}

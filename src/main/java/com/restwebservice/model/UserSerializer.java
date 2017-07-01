package com.restwebservice.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grand on 5/21/2017.
 */
public class UserSerializer extends JsonSerializer<User> {

   @Override
   public void serialize(User user,
                         JsonGenerator jsonGenerator,
                         SerializerProvider serializerProvider)
           throws IOException, JsonProcessingException {
      jsonGenerator.writeObject(user.getId());
   }


}

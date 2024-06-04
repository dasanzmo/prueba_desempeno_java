package com.riwi.prueba_desempeno.infraestructure.helpers;

import com.riwi.prueba_desempeno.api.dto.request.UserRequest;
import com.riwi.prueba_desempeno.api.dto.response.UserReponse;
import com.riwi.prueba_desempeno.domain.entities.User;

public class UserHelper {
    
    public static UserReponse userToResp(User user) {
        return UserReponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.isActive())
                .build();
    }

    public static User reqToUser(UserRequest userReq) {
        return User.builder()
                .name(userReq.getName())
                .password(userReq.getPassword())
                .email(userReq.getEmail())
                .active(userReq.isActive())
                .build();
    }
}

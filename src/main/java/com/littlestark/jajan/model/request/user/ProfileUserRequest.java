package com.littlestark.jajan.model.request.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserRequest {

    private String name;

    private String storeName;

    private Integer phoneNumber;

    private String address;
}

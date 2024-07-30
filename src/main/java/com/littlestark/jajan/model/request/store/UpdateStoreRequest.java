package com.littlestark.jajan.model.request.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStoreRequest {

    private String nameStore;

    private String address;

    private String imageStore;
}

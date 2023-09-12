package com.littlestark.jajan.model.request.store;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {

    private String nameStore;

    private String address;

    private String district;

    private String regency;
}

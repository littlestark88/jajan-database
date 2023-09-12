package com.littlestark.jajan.model.response.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponse {
    private String id;
    private String nameStore;
    private String address;
    private String district;
    private String regency;
    private String userId;
}

package com.littlestark.jajan.model.request.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationStoreListByNameRequest {
    private int page;
    private int size;
    private String name;
}

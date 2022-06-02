package com.micropos.common.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    private String id;
    private String orderId;
}

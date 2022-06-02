package com.micropos.common.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String id;
    private List<Item> items = new ArrayList<>();
    private double counter;
}

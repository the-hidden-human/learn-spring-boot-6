package com.h2hm.springbootpart1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;

}

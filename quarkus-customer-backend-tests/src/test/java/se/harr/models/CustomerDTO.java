package se.harr.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDTO {
    private String name;
    private String address;
    private String email;
    private String telephone;
}

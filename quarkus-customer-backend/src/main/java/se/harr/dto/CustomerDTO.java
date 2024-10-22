package se.harr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerDTO {
    private String name;
    private String address;
    private String email;
    private String telephone;
}

package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizedBodyModel {

    private String userName, password;

    public AuthorizedBodyModel(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}

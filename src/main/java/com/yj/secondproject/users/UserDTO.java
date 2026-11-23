package com.yj.secondproject.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String uid;
    private String uLoginId;
    private String uPassword;
    private String uNickname;
    private String uEmail;
    private Date uCreateAt;

}

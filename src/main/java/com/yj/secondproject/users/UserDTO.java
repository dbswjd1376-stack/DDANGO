
package com.yj.secondproject.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private int u_id;
    private String u_login_id;
    private String u_password;
    private String u_nickname;
    private String u_email;
    private Date u_created_at;

}

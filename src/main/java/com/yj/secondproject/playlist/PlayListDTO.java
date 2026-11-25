package com.yj.secondproject.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayListDTO {
    private int p_id;
    private String p_img;
    private String p_name;
    private String p_desc;
    private int m_type;
    private Date p_date;
    private String u_id;
}

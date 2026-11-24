package com.yj.secondproject.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookChapterDTO {
    private int bc_id;
    private int b_id;
    private int bc_number;
    private String bc_title;
    private String bc_description;
    private String b_quote;
    private String b_image;
    private String b_content;

}

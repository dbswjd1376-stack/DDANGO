package com.yj.secondproject.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {
    private int b_id;
    private String b_title;
    private String b_author;
    private String b_genre;
    private String b_description;
    private String b_quote;
    private String b_image;
    private String b_content;

}

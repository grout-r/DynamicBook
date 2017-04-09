package com.example.roman.dynamicbook;

/**
 * Created by Roman on 09/04/2017.
 */

public class Page
{
    Page(int id, int is_plain, String content)
    {
        if (is_plain == 1) { plain_content = content; }
        else { url_content = content; }
    }

    int id;
    int  is_plain;
    String plain_content;
    String url_content;
}

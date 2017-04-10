package com.example.roman.dynamicbook;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Roman on 09/04/2017.
 */

public class Page implements Parcelable
{
    Page(int id, int is_plain, String content)
    {

        this.id = id;
        this.is_plain = is_plain;
        if (is_plain == 1) { plain_content = content; }
        else { url_content = content; }
    }

    public static final Parcelable.Creator<Page> CREATOR = new Creator<Page>() {
        public Page createFromParcel(Parcel source) {
            int id = source.readInt();
            int is_plain = source.readInt();
            String plain = source.readString();
            String url = source.readString();
            if (is_plain == 1)
            {
                return new Page(id, is_plain, plain);
            }
            else
            {
                return new Page(id, is_plain, url);
            }
        }
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };

    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeInt(is_plain);
        parcel.writeString(plain_content);
        parcel.writeString(url_content);
    }

    int id;
    int  is_plain;
    String plain_content;
    String url_content;
}

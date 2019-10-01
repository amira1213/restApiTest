package com.example.lenovog50.keygeneratorapp;

import java.util.Date;

/**
 * Created by lenovo g50 on 29/09/2019.
 */

public class Key {
    String name,value;
    Date date;

    Key(String name, String value, Date date)
    {
        this.name=name;
        this.value=value;
        this.date=date;
    }
}

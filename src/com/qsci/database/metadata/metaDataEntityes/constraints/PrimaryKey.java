package com.qsci.database.metadata.metaDataEntityes.constraints;


import java.util.ArrayList;
import java.util.List;

public class PrimaryKey {
    List<String> fields;

    public PrimaryKey() {
        this.fields = new ArrayList<String>();
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        if(fields.isEmpty()){
            return "";
        }
        StringBuilder result = new StringBuilder("Primary Key: ").
                append("By fields: ");
        fields.forEach(f -> result.append(f + " "));
        return result.toString();

    }


}


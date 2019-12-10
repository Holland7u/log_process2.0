package com.cm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "occ1-#{T(com.cm.config.ConfigBean).getIndexName()}")
public class Log{

    @Id
    private String id;
    @Field(index = true,type = FieldType.Text)
    private String contentType;
    @Field(index = true,type = FieldType.Text)
    private String description;
    @Field(index = true,type = FieldType.Text)
    private String message;
    @Field(index = true,type = FieldType.Text)
    private String host_ip;
    @Field(index = true,type = FieldType.Text)
    private String timestamp;
    @Field(index = true,type = FieldType.Text)
    private String fileType;
}

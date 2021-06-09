package com.agenda.exceptions.handler;

import lombok.Data;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ProblemDetail {


    private Integer status;
    private URI type;
    private String title;
    private List<String> detail;

    public List<String> getDetail() {
        if(Objects.isNull(this.detail)){
            this.detail = new ArrayList<>();
        }
        return detail;
    }

}

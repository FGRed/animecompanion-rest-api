package com.animecompanion.model;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<AnimeRDTO> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AnimeRDTO> getResult() {
        return result;
    }

    public void setResult(List<AnimeRDTO> result) {
        this.result = result;
    }

}

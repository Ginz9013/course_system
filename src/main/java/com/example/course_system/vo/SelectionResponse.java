package com.example.course_system.vo;

import com.example.course_system.entity.Selection;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectionResponse {
    private String message;
    private Selection selection;
    private List<Selection> selectionList;

//    Constructor
    public SelectionResponse() {}
    public SelectionResponse(String message) {
        this.message = message;
    }
    public SelectionResponse(String message, Selection selection) {
        this.message = message;
        this.selection = selection;
    }
    public SelectionResponse(String message,List<Selection> selectionList) {
        this.message = message;
        this.selectionList = selectionList;
    }

//    Getter
    public String getMessage() {
        return message;
    }
    public Selection getSelection() {
        return selection;
    }
    public List<Selection> getSelectionList() {
        return selectionList;
    }

    //    Setter
    public void setMessage(String message) {
        this.message = message;
    }
    public void setSelection(Selection selection) {
        this.selection = selection;
    }
    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }
}

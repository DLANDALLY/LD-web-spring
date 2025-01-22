package com.dly.safetynet.dto.childAlert;

import java.util.List;

public class ChildAlertDto {
    private List<ChildDto> children;

    public ChildAlertDto() {
    }

    public ChildAlertDto(List<ChildDto> children) {
        this.children = children;
    }

    public List<ChildDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildDto> children) {
        this.children = children;
    }
}

package com.dly.safetynet.dto.childAlert;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChildAlertDto {
    private List<ChildDto> children;

    public ChildAlertDto() {
    }

    public ChildAlertDto(List<ChildDto> children) {
        this.children = children;
    }

}

package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log{
    private Float distanceSwam;
    private Float distanceBiked;
    private Float distanceRan;
    private Boolean integrityCheckbox;

    public Log(){}
}
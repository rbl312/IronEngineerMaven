package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log{
    private float distanceSwam;
    private float distanceBiked;
    private float distanceRan;
    private boolean integrityChecked;

    public Log(){}
}
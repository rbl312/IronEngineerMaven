package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Class Name: Log
// Class Description: Class used for adding and modifying distances completed in the Iron Engineer competition by competitors.
// Class Attributes: floats to represent the distances completed to be added to the system, distanceSwam, distanceBiked, distanceRan, and integrityChecked (boolean) used for the
// integrity check when entering distances into the system.
public class Log{
    private float distanceSwam;
    private float distanceBiked;
    private float distanceRan;
    private boolean integrityChecked;

    public Log(){}
}
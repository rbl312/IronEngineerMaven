package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "log")
// Class Name: Log
// Class Description: Class used for adding and modifying distances completed in the Iron Engineer competition by competitors.
// Class Attributes: floats to represent the distances completed to be added to the system, distanceSwam, distanceBiked, distanceRan, and integrityChecked (boolean) used for the
// integrity check when entering distances into the system.
public class Log{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "competitor_id")
    private Integer competitorId;

    /** All distance values stored in miles */

    @Column(name = "distance_ran")
    private Float distanceRan;

    @Column(name = "distance_swam")
    private Float distanceSwam;

    @Column(name = "distance_biked")
    private Float distanceBiked;

    @Column(name = "is_approved")
    private boolean isApproved;

    private boolean integrityChecked;

    public Log(){}
}
package com.ussd.app.demo.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "sessions")
public class Session {
    @Id

    @NotNull
    private String     sessionId;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "SESSION")
    private Integer SessionLvl;

    @Column
    private Integer lvlprt;
    @Column
    private String input;


}

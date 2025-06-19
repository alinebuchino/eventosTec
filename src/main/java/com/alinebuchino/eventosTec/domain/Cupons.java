package com.alinebuchino.eventosTec.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table (name = "cupons")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cupons {

    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private Integer discount;
    private Date valid;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}

package com.levio.wallet.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Cagnotte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String address;

    public Boolean open;

    public Date dateDebut;

    public Date dateFin;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public Users winners;
}

package com.yurisaito.gestore.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Association implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

    @ManyToOne
    private Seller mainSeller;

    @ManyToOne
    private Seller associatedSeller;

    public UUID getId() {
        return id;
    }

    public Seller getMainSeller() {
        return mainSeller;
    }

    public Seller getAssociatedSeller() {
        return associatedSeller;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMainSeller(Seller mainSeller) {
        this.mainSeller = mainSeller;
    }

    public void setAssociatedSeller(Seller associatedSeller) {
        this.associatedSeller = associatedSeller;
    }    
}

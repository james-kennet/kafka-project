package com.demo.kafka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter@Setter
@Entity
@Table(name = "address_transaction")
public class AddressTransaction implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String nric;

  private String status;

  private String unitNo;

  private String streetName;

  private String country;

  private String postalCode;

  private String clientType;

}

package com.demo.kafka.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Getter@Setter
@Entity
@Table(name = "address_life400")
@Where(clause = "deleted=0")
public class AddressLife400 implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @OrderBy
  private String nric;

  private String unitNo;

  private String streetName;

  private String country;

  private String postalCode;

  private boolean deleted;

}

package com.manolitsas.david.client.model;

import lombok.Data;

@Data
public class Company {

  private String name;
  private Type type;

  public enum Type {
    PUBLISHER,
    DEVELOPER
  }
}

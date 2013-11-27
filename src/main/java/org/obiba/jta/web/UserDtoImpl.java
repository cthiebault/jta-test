package org.obiba.jta.web;

public class UserDtoImpl implements UserDto {

  private String firstName;

  private String lastName;

  public UserDtoImpl() {
  }

  public UserDtoImpl(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}

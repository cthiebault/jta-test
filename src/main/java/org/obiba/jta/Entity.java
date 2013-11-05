package org.obiba.jta;

import javax.persistence.Id;
import javax.persistence.Version;

@javax.persistence.Entity
public class Entity {

  @Id
  private long id;

  @Version
  private long version;

  private String name;

  public Entity() {
  }

  public Entity(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(!(o instanceof Entity)) return false;
    Entity entity = (Entity) o;
    return id == entity.id;
  }

  @Override
  public int hashCode() {
    return (int) (id ^ id >>> 32);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Entity{");
    sb.append("id=").append(id);
    sb.append(", version=").append(version);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

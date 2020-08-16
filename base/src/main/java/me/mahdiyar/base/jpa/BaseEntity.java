package me.mahdiyar.base.jpa;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity<T extends BaseEntity<T>> implements Serializable {
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;
    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public BaseEntity() {
    }

    public boolean sameIdentityAs(final T that) {
        return this.equals(that);
    }

    public boolean equals(final Object object) {
        if (!(object instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity<?> that = (BaseEntity<?>) object;
            this.checkIdentity(this);
            this.checkIdentity(that);
            return this.id.equals(that.id);
        }
    }

    private void checkIdentity(final BaseEntity<?> entity) {
        if (entity.id == null) {
            throw new IllegalStateException("Identity missing in entity: " + entity);
        }
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", this.getClass().getSimpleName(), this.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    public String getId() {
        return this.id;
    }

    protected void setId(final String id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    protected void setCreationTime(final LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    protected void setUpdateTime(final LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

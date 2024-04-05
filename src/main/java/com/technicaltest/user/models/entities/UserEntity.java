package com.technicaltest.user.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modified;

    @Column(nullable = true, name = "last_login")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLogin;

    @Column(nullable = false)
    private String token;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones = new ArrayList<>();

    public void setPhones(List<PhoneEntity> phones){
        this.phones = phones;
        for(PhoneEntity phone : phones){
            phone.setUser(this);
        }
    }
    public List<PhoneEntity> getPhones(){
        return phones;
    }
}

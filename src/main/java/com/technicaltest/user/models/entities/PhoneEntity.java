package com.technicaltest.user.models.entities;

import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "phones")
public class PhoneEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String citycode;
    @Column(nullable = false)
    private String contrycode;
    @Setter(AccessLevel.PROTECTED)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private UserEntity user;
}
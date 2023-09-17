package com.h2hm.springbootpart1.entity;

import com.h2hm.springbootpart1.entity.User;
import com.h2hm.springbootpart1.model.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens",  schema = "public", catalog = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokens_seq")
    @SequenceGenerator(name = "tokens_seq", sequenceName = "tokens_seq", allocationSize = 1)
    public Integer id;

    @Column(unique = true, name = "token")
    public String token;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    public boolean revoked;

    @Column(name = "expired")
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

}

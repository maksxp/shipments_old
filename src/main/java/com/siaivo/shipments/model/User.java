package com.siaivo.shipments.model;

import javax.validation.constraints.*;
import org.springframework.data.annotation.Transient;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "password")
    @Size (min = 5, message = "*Your password must have at least 5 characters" )
    @NotEmpty(message = "*Please provide user password")
    @Transient
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide user name")
    private String name;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "enabled")
    private Boolean enabled;
    //	@OneToMany (mappedBy = "user", cascade= {CascadeType.ALL})
//	private Set<PurchaseData> purchaseData;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {	this.id = id;	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//	public Set<PurchaseData> getPurchaseData() {
//		return purchaseData;
//	}
//
//	public void setPurchaseData(Set<PurchaseData> purchaseData) {
//		this.purchaseData = purchaseData;
//	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}

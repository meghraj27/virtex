package com.meghrajswami.bitex.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by megh on 6/30/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`USER`",
        indexes = {
                @Index(columnList = "NAME")})
public class User implements Serializable {

    //    @JsonView(View.PublicSummary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(View.PublicSummary.class)
    @Column(name = "PHOTO_URL_SMALL")
    private String photoUrlSmall;

    @JsonView(View.PublicSummary.class)
    @Column(name = "PHOTO_URL_MEDIUM")
    private String photoUrlMedium;

    @JsonView(View.PublicSummary.class)
    @Column(name = "PHOTO_URL_LARGE")
    private String photoUrlLarge;

    @JsonView(View.PublicSummary.class)
    @Column(name = "NAME", length = 100)
    private String name;

    @JsonView(View.RoleOwner.class)
    @Column(name = "USERNAME")
    private String username;

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD")
    private String password;

    @JsonView(View.RoleAdmin.class)
    @Column(name = "ENABLED")
    private Boolean enabled;

    @JsonView(View.RoleAdmin.class)
    @ManyToMany
    private Collection<Role> roles;

    @JsonView(View.RoleOwner.class)
    @Column(name = "EMAIL")
    private String email;

    @JsonView(View.RoleOwner.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "EMAIL_VERIFIED")
    private Boolean emailVerified;

    @JsonView(View.RoleUser.class)
    @Column(name = "DESCRIPTION")
    private String description;

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "FB_ID")
    private Long fbId;

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "GOOGLE_ID", length = 30)
    private String googleId;

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "AUTH_SITE")
    private String authSite; //value = facebook or google

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @JsonView(View.RoleAdmin.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "TOKEN_EXPIRE_AT")
    private Long tokenExpireAt;

    @JsonView(View.RoleUser.class)
    @Column(name = "FACEBOOK_LINK")
    private String facebookLink;

    @JsonView(View.RoleUser.class)
    @Column(name = "LINKED_IN_LINK")
    private String linkedInLink;

    @JsonView(View.RoleUser.class)
    @Column(name = "TWITTER_LINK")
    private String twitterLink;

    @JsonView(View.RoleUser.class)
    @Column(name = "GOOGLE_LINK")
    private String googleLink;

    @JsonView(View.RoleOwner.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "PAID_ORDER_ID")
    private Long paidOrderId;

    @JsonView(View.RoleOwner.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "DEFAULT_CURRENCY")
    private String defaultCurrency;

    @JsonView(View.RoleOwner.class)
    @Column(name = "MAIN_BALANCE", precision = 19, scale = 4)
    private BigDecimal mainBalance;

    @JsonView(View.RoleOwner.class)
    @Column(name = "AVAILABLE_MARGIN", precision = 19, scale = 4)
    private BigDecimal availableMargin;

    @ElementCollection(targetClass = Holding.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_HOLDING")
    // @MapKey(name = "symbol")
    @MapKeyJoinColumn(name = "HOLDING_ID", referencedColumnName = "ID")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Symbol, Holding> holdings = new HashMap<>();

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created = new Date();

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated = new Date();

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    protected User() {
    }

    public User(String name, String email, String photoUrlLarge) {
        this.name = name;
        this.email = email;
        this.photoUrlLarge = photoUrlLarge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrlSmall() {
        return photoUrlSmall;
    }

    public void setPhotoUrlSmall(String photoUrlSmall) {
        this.photoUrlSmall = photoUrlSmall;
    }

    public String getPhotoUrlMedium() {
        return photoUrlMedium;
    }

    public void setPhotoUrlMedium(String photoUrlMedium) {
        this.photoUrlMedium = photoUrlMedium;
    }

    public String getPhotoUrlLarge() {
        return photoUrlLarge;
    }

    public void setPhotoUrlLarge(String photoUrlLarge) {
        this.photoUrlLarge = photoUrlLarge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
        //        role.getLinkedUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        //        role.getLinkedUsers().remove(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getAuthSite() {
        return authSite;
    }

    public void setAuthSite(String authSite) {
        this.authSite = authSite;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getTokenExpireAt() {
        return tokenExpireAt;
    }

    public void setTokenExpireAt(Long tokenExpireAt) {
        this.tokenExpireAt = tokenExpireAt;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getGoogleLink() {
        return googleLink;
    }

    public void setGoogleLink(String googleLink) {
        this.googleLink = googleLink;
    }

    public Long getPaidOrderId() {
        return paidOrderId;
    }

    public void setPaidOrderId(Long paidOrderId) {
        this.paidOrderId = paidOrderId;
    }

    public BigDecimal getMainBalance() {
        return mainBalance;
    }

    public void setMainBalance(BigDecimal mainBalance) {
        this.mainBalance = mainBalance;
    }

    public Currency getDefaultCurrency() {
        return Currency.getInstance(defaultCurrency);
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency.getCurrencyCode();
    }

    public BigDecimal getAvailableMargin() {
        return availableMargin;
    }

    public void setAvailableMargin(BigDecimal availableMargin) {
        this.availableMargin = availableMargin;
    }

    public Map<Symbol, Holding> getHoldings() {
        return holdings;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void addDebugData(String data) {
        JsonObject jsonObjectDebugData = new Gson().fromJson(data, JsonObject.class);
        JsonObject o = jsonObjectDebugData.getAsJsonObject("data");
        setFbId(o.get("user_id").getAsLong());
        setTokenExpireAt(o.get("expires_at").getAsLong());
    }

    public Holding getHolding(Symbol symbol) {
        return holdings.get(symbol);
    }

    public void blockMargin(BigDecimal margin) {
        availableMargin = availableMargin.subtract(margin);
    }

    public void releaseMargin(BigDecimal margin) {
        availableMargin = availableMargin.add(margin);
    }

    // TODO: 12/5/2017 dependent on Side enum from parity match lib, need to be fixed
    public void adjustMarginOnTradeBuy(Symbol symbol, BigDecimal executionPrice, BigDecimal orderPrice, BigDecimal quantity) {
        mainBalance = mainBalance.subtract(executionPrice.multiply(quantity));
        // am+=q*(op-ep)
        ///av=available margin, q=executed quantity, op= order price, ep= execution price
        availableMargin = availableMargin.add(quantity.multiply(orderPrice.subtract(executionPrice)));

        // TODO: 12/5/2017 holding could be null
        if ((holdings.get(symbol)) == null)
            holdings.put(symbol, new Holding(symbol, quantity));
        else holdings.get(symbol).add(quantity);
    }

    public void adjustMarginOnTradeSell(Symbol symbol, BigDecimal executionPrice, BigDecimal quantity) {
        mainBalance = mainBalance.add(executionPrice.multiply(quantity));
        availableMargin = availableMargin.add(executionPrice.multiply(quantity));

        holdings.get(symbol).remove(quantity);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", photoUrlSmall='" + photoUrlSmall + '\'' +
                ", photoUrlMedium='" + photoUrlMedium + '\'' +
                ", photoUrlLarge='" + photoUrlLarge + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", description='" + description + '\'' +
                ", fbId=" + fbId +
                ", googleId='" + googleId + '\'' +
                ", authSite='" + authSite + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenExpireAt=" + tokenExpireAt +
                ", facebookLink='" + facebookLink + '\'' +
                ", linkedInLink='" + linkedInLink + '\'' +
                ", twitterLink='" + twitterLink + '\'' +
                ", googleLink='" + googleLink + '\'' +
                ", paidOrderId=" + paidOrderId +
                ", created=" + created +
                ", createdBy=" + createdBy +
                ", updated=" + updated +
                ", updatedBy=" + updatedBy +
                '}';
    }

    public static class AuthSite {
        public static final String FACEBOOK = "facebook";
        public static final String GOOGLE = "google";
    }

}
